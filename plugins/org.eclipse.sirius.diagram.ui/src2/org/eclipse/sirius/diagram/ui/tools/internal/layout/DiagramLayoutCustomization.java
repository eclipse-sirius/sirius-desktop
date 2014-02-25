/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout;

import java.util.Collection;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.CompositeLayout;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layout;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

/**
 * Class capturing the diagram layout customization made in a viewpoint
 * specification model.
 * 
 * @author cbrun
 * 
 */
public class DiagramLayoutCustomization {
    /**
     * This constant represent the distance from the side of a border node to
     * the first bendpoint when the router is a border item "aware" one.
     */
    private static final int BORDER_NODE_ROUTING_SPACE = 10;

    private int padding = 30;

    /**
     * Create a new Diagram Customization.
     */
    public DiagramLayoutCustomization() {

    }

    /**
     * Return the Insets that one should use as a padding for the node.
     * 
     * @param ep
     *            any editpart.
     * @return the Insets that one should use as a padding for the node.
     */
    public Insets getNodePadding(final GraphicalEditPart ep) {
        /*
         * This method has been redirected to allow redefinition of the node
         * padding (which is final in the super class).
         */
        Insets inSetPadding = new Insets(this.padding);
        if (ep instanceof CompartmentEditPart || ep instanceof AbstractDiagramElementContainerEditPart && ((AbstractDiagramElementContainerEditPart) ep).isRegion()) {
            // No padding for regions.
            inSetPadding = new Insets(0);
        } else if (ep instanceof AbstractBorderedShapeEditPart) {
            // check if the direct parent is added already to the graph

            int maxWidth = -1;
            int maxHeight = -1;
            for (final AbstractDiagramBorderNodeEditPart borderNode : Iterables.filter(ep.getChildren(), AbstractDiagramBorderNodeEditPart.class)) {
                int figTopMargin = borderNode.getFigure().getBounds().width / 4;
                int figLeftMargin = borderNode.getFigure().getBounds().height / 4;
                /*
                 * If we've got connections then we should add a bit more
                 * padding to avoid the first bendpoints of the connection as
                 * the router starts with an horizontal or vertical straight
                 * line when being from a port.
                 */
                final int nbConnections = borderNode.getSourceConnections().size() + ep.getTargetConnections().size();
                if (nbConnections > 0) {
                    figLeftMargin += figLeftMargin + BORDER_NODE_ROUTING_SPACE;
                    figTopMargin += figTopMargin + BORDER_NODE_ROUTING_SPACE;
                }
                if (figTopMargin > maxWidth) {
                    maxWidth = figTopMargin;
                }
                if (figLeftMargin > maxHeight) {
                    maxHeight = figLeftMargin;
                }
            }
            inSetPadding = new Insets(Math.max(inSetPadding.top, maxWidth), Math.max(inSetPadding.left, maxHeight), Math.max(inSetPadding.bottom, maxWidth), Math.max(inSetPadding.right, maxHeight));
        }
        return inSetPadding;

    }

    /**
     * Initialize the diagram customization padding looking for specific
     * settings in the given objects.
     * 
     * @param selectedObjects
     *            collection of {@link IGraphicalEditPart}
     */
    public void initializePaddingWithEditParts(final Collection selectedObjects) {
        padding = findPaddingFromSelection(selectedObjects);
    }

    /**
     * Initialize the diagram customization padding looking for specific
     * settings in the given views.
     * 
     * @param views
     *            collection {@link View}
     */
    public void initializePaddingWithViews(final Collection<View> views) {
        padding = findPaddingFromViews(views);
    }

    private int findPaddingFromViews(final Collection<View> views) {
        int foundPadding = 30;
        for (final View obj : views) {
            foundPadding = getPadding(obj);
            if (foundPadding != 30)
                break;
        }
        return foundPadding;
    }

    private int findPaddingFromSelection(final Collection selectedObjects) {
        int foundPadding = 30;
        Collection<IGraphicalEditPart> filteredSelection = Collections2.filter(selectedObjects, Predicates.instanceOf(GraphicalEditPart.class));
        for (final IGraphicalEditPart obj : filteredSelection) {
            foundPadding = getEditPartPadding(obj);
            if (foundPadding != 30)
                break;
        }
        return foundPadding;
    }

    private int getEditPartPadding(final IGraphicalEditPart container) {
        return getPadding(container.getNotationView());
    }

    private int getPadding(final View gmfView) {
        final Layout foundLayout = DiagramLayoutCustomization.findLayoutSettings(gmfView);
        if (foundLayout instanceof CompositeLayout) {
            return ((CompositeLayout) foundLayout).getPadding();
        }
        return 30;
    }

    /**
     * return the layout setting associated with a given {@link View}.
     * 
     * @param view
     *            any GMF View.
     * @return the layout setting associated with a given {@link View}. Null if
     *         there is no such setting.
     */
    public static Layout findLayoutSettings(final View view) {
        Layout foundLayout = null;
        if (view.getDiagram() != null) {
            final EObject modelElement = view.getDiagram().getElement();
            if (modelElement instanceof DDiagram) {
                final DDiagram vp = (DDiagram) modelElement;
                final DiagramDescription desc = vp.getDescription();
                if (desc != null) {
                    foundLayout = desc.getLayout();
                }
                return foundLayout;
            }
        }
        return foundLayout;
    }

}
