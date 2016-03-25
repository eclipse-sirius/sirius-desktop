/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DecorationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.decorator.DecoratorService;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.Decoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;

/**
 * A specific {@link DecorationEditPolicy} that provides its own
 * {@link DecoratorTarget} in order to ignore the decorator when selected and
 * select the diagram element behind it instead. This way if a border node is
 * under a decorator of the parent node, it is still selectable.
 * 
 * @author <a href="mailto:florian.barbin@obeo.fr">Florian Barbin</a>
 *
 */
@SuppressWarnings("restriction")
public class SiriusDecoratorEditPolicy extends DecorationEditPolicy {

    /**
     * This method was overridden in order to create a
     * {@link SiriusDecoratorTarget} instead of a default
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
            for (Iterator iter = decorators.values().iterator(); iter.hasNext(); ) {
                IDecorator decorator = (IDecorator) iter.next();
                decorator.activate();
            }
        }
    }

    /**
     * This method was overridden in order to create a
     * {@link SiriusDecoratorTarget} instead of a default
     * {@link DecoratorTarget}.
     */
    @SuppressWarnings({ "rawtypes" })
    @Override
    public void refresh() {
        if (decorators == null) {
            decorators = new HashMap();
            DecoratorService.getInstance().createDecorators(new SiriusDecoratorTarget());
        }
        for (Iterator iter = decorators.values().iterator(); iter.hasNext(); ) {
            IDecorator decorator = (IDecorator) iter.next();
            decorator.refresh();
        }
    }

    private final class SiriusDecoratorTarget extends DecoratorTarget {

        /**
         * This method was overridden in order to create a decoration that on
         * selection behave as if it did not exist (allowing to select the
         * diagram element underneath).
         */
        @SuppressWarnings("unchecked")
        @Override
        public IDecoration addDecoration(IFigure figure, Locator locator, boolean isVolatile) {

            Decoration decoration = new Decoration() {
                @Override
                public IFigure findFigureAt(int x, int y, TreeSearch search) {
                    return null;
                }
            };
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
