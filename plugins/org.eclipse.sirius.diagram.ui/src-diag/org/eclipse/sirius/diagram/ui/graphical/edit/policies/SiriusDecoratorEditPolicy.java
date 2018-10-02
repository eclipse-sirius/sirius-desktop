/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DecorationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.decorator.DecoratorService;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.Decoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.collect.Iterables;

/**
 * A specific {@link DecorationEditPolicy} that provides its own {@link DecoratorTarget} in order to ignore the
 * decorator when selected and select the diagram element behind it instead. This way if a children (node or border
 * node) is under a decorator of the parent node, it is still selectable.
 * 
 * @author <a href="mailto:florian.barbin@obeo.fr">Florian Barbin</a>
 *
 */
@SuppressWarnings("restriction")
public class SiriusDecoratorEditPolicy extends DecorationEditPolicy {

    /**
     * This method was overridden in order to create a {@link SiriusDecoratorTarget} instead of a default
     * {@link DecoratorTarget}.
     */
    @SuppressWarnings({ "rawtypes" })
    @Override
    public void activate() {
        if (decorators == null) {
            decorators = new HashMap();
            DecoratorService.getInstance().createDecorators(new SiriusDecoratorTarget());
        }
        if (decorators != null) {
            for (Iterator iter = decorators.values().iterator(); iter.hasNext(); /* */) {
                IDecorator decorator = (IDecorator) iter.next();
                decorator.activate();
            }
        }
    }

    /**
     * This method was overridden in order to create a {@link SiriusDecoratorTarget} instead of a default
     * {@link DecoratorTarget}.
     */
    @SuppressWarnings({ "rawtypes" })
    @Override
    public void refresh() {
        if (decorators == null) {
            decorators = new HashMap();
            DecoratorService.getInstance().createDecorators(new SiriusDecoratorTarget());
        }
        for (Iterator iter = decorators.values().iterator(); iter.hasNext(); /* */) {
            IDecorator decorator = (IDecorator) iter.next();
            decorator.refresh();
        }
    }

    /**
     * A custom {@link DecoratorTarget} to provide a custom {@link Decoration}.
     * 
     * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
     *
     */
    private final class SiriusDecoratorTarget extends DecoratorTarget {

        /**
         * A specific {@link Decoration} whose selection selects the EditPart below, not automatically the parent
         * EditPart.
         * 
         * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
         *
         */
        private final class SiriusDecoration extends Decoration {
            @Override
            public IFigure findMouseEventTargetAt(int x, int y) {
                if (checkHostEditPart(x, y)) {
                    IGraphicalEditPart bestChildCandidate = findBestChildCandidate((IGraphicalEditPart) getHost(), new Point(x, y));
                    if (bestChildCandidate != null) {
                        return bestChildCandidate.getFigure();
                    }
                }
                return super.findMouseEventTargetAt(x, y);
            }

            private boolean checkHostEditPart(int x, int y) {
                if (getHost() instanceof IGraphicalEditPart) {
                    EditPolicy editPolicy = getHost().getEditPolicy(EditPolicyRoles.DECORATION_ROLE);
                    IFigure figure = ((IGraphicalEditPart) getHost()).getFigure();
                    if (editPolicy instanceof SiriusDecoratorEditPolicy && figure.getBounds().contains(x, y)) {
                        return true;
                    }
                }
                return false;
            }

            /**
             * Looks recursively in the border node for the "deepest" one that contain the location where the mouse
             * clicked.
             * 
             * @param graphicalEditPart
             *            the current {@link IGraphicalEditPart} to investigate
             * @param startLocation
             *            the location where the mouse clicked
             * @return the best candidate for the selection
             */
            private IGraphicalEditPart findBestChildCandidate(IGraphicalEditPart graphicalEditPart, Point startLocation) {
                IGraphicalEditPart bestCandidate = null;
                // Firstly, search in border node (they are over other children)
                for (AbstractDiagramBorderNodeEditPart editPart : Iterables.filter(graphicalEditPart.getChildren(), AbstractDiagramBorderNodeEditPart.class)) {
                    if (editPart.getFigure().getBounds().contains(startLocation)) {
                        bestCandidate = editPart;
                    }
                    IGraphicalEditPart bestChildBorderNodeCandidate = findBestChildCandidate(editPart, startLocation);
                    if (bestChildBorderNodeCandidate != null) {
                        bestCandidate = bestChildBorderNodeCandidate;
                    }
                }
                // Secondly, if nothing was found, search in node children
                if (bestCandidate == null) {
                    if (graphicalEditPart instanceof AbstractDiagramContainerEditPart) {
                        for (AbstractDNodeContainerCompartmentEditPart compartmentEditPart : Iterables.filter(graphicalEditPart.getChildren(), AbstractDNodeContainerCompartmentEditPart.class)) {
                            for (AbstractDiagramNodeEditPart editPart : Iterables.filter(compartmentEditPart.getChildren(), AbstractDiagramNodeEditPart.class)) {
                                if (GraphicalHelper.getAbsoluteBoundsIn100Percent(editPart).contains(startLocation)) {
                                    bestCandidate = editPart;
                                    IGraphicalEditPart childBestCandidate = findBestChildCandidate(editPart, startLocation);
                                    if (childBestCandidate != null) {
                                        bestCandidate = childBestCandidate;
                                    }
                                    break;
                                }
                            }
                            if (bestCandidate != null) {
                                break;
                            }
                        }
                    }
                }
                return bestCandidate;
            }
        }

        /**
         * This method was overridden in order to create a decoration that on selection behave as if it did not exist
         * (allowing to select the diagram element underneath).
         */
        @Override
        public IDecoration addDecoration(IFigure figure, Locator locator, boolean isVolatile) {

            Decoration decoration = new SiriusDecoration();
            decoration.add(figure);
            decoration.setSize(figure.getSize());

            GraphicalEditPart ownerEditPart = (GraphicalEditPart) getAdapter(GraphicalEditPart.class);
            decoration.setOwnerFigure(ownerEditPart.getFigure());
            decoration.setLocator(locator);

            // Register this figure with it's owner editpart so mouse events
            // will be propagated to it's host.
            ownerEditPart.getViewer().getVisualPartMap().put(decoration, ownerEditPart);

            IFigure pane = getLayer(isVolatile ? DiagramRootEditPart.DECORATION_UNPRINTABLE_LAYER : DiagramRootEditPart.DECORATION_PRINTABLE_LAYER);

            pane.add(decoration);
            return decoration;
        }
    }
}
