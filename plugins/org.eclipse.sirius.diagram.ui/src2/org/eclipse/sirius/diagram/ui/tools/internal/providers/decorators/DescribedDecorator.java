/*******************************************************************************
 * Copyright (c) 2008, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.providers.decorators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.Position;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * Decorator from a {@link Decoration}.
 * 
 * @author mPorhel
 * 
 */
public class DescribedDecorator extends AbstractDecorator {

    /** the decorations being displayed */
    private List<IDecoration> decorations = Collections.<IDecoration> emptyList();

    /**
     * Create a decorator.
     * 
     * @param decoratorTarget
     *            target to decorate.
     */
    public DescribedDecorator(final IDecoratorTarget decoratorTarget) {
        super(decoratorTarget);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator#activate()
     */
    public void activate() {
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator#refresh()
     */
    public void refresh() {
        removeDecorations();
        final View view = (View) getDecoratorTarget().getAdapter(View.class);
        if (view != null && view.eResource() != null) {
            final EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
            if (editPart == null || editPart.getParent() == null || editPart.getViewer() == null) {
                return;
            }
            if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
                final IFigure figure = ((org.eclipse.gef.GraphicalEditPart) editPart).getFigure();

                final Dimension size = figure.getSize();

                if (size.width < 10 && size.width > 0 && size.height < 10 && size.height > 0) {
                    return;
                }
            }
            int margin = -1;
            if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
                margin = MapModeUtil.getMapMode(((org.eclipse.gef.GraphicalEditPart) editPart).getFigure()).DPtoLP(margin);
            }

            final DDiagramElement element = ((IDiagramElementEditPart) editPart).resolveDiagramElement();
            if (element != null) {
                for (final Decoration decoration : element.getDecorations()) {
                    Image image = getImage(decoration);
                    if (image != null) {
                        addDecoration(getDecoratorTarget().addShapeDecoration(image, getPosition(decoration), margin, false));
                    }
                }
            }

        }
    }

    /**
     * Converts the position to a Direction.
     * 
     * @param direction
     * @return the int as defined in PositionConstant
     */
    private Direction getPosition(final Decoration decoration) {
        Direction direction = Direction.SOUTH_WEST;
        if (decoration.getDescription() != null) {
            switch (decoration.getDescription().getPosition().getValue()) {
            case Position.CENTER:
                direction = Direction.CENTER;
                break;
            case Position.NORTH:
                direction = Direction.NORTH;
                break;
            case Position.SOUTH:
                direction = Direction.SOUTH;
                break;
            case Position.WEST:
                direction = Direction.WEST;
                break;
            case Position.EAST:
                direction = Direction.EAST;
                break;
            case Position.NORTH_EAST:
                direction = Direction.NORTH_EAST;
                break;
            case Position.NORTH_WEST:
                direction = Direction.NORTH_WEST;
                break;
            case Position.SOUTH_EAST:
                direction = Direction.SOUTH_EAST;
                break;
            case Position.SOUTH_WEST:
                direction = Direction.SOUTH_WEST;
                break;
            default:
                break;
            }
        }
        return direction;
    }

    private Image getImage(final Decoration decoration) {
        DecorationDescription description = decoration.getDescription();
        if (description != null) {
            return WorkspaceImageFigure.flyWeightImage(description.getDecoratorPath());
        } else {
            return null;
        }
    }

    private void removeDecorations() {
        for (final IDecoration decoration : decorations) {
            // getDecoratorTarget().removeDecoration(decoration);
            if (decoration instanceof IFigure && ((IFigure) decoration).getParent() != null) {
                ((IFigure) decoration).getParent().remove((IFigure) decoration);
            }

            final GraphicalEditPart ownerEditPart = (GraphicalEditPart) getDecoratorTarget().getAdapter(GraphicalEditPart.class);
            ownerEditPart.getViewer().getVisualPartMap().remove(decoration);
        }
        decorations = new ArrayList<IDecoration>();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator#deactivate()
     */
    @Override
    public void deactivate() {
        removeDecorations();
    }

    /**
     * Get the IDecorations of this DescribedDecorator.
     * 
     * @return Returns the decorations.
     */
    public List<IDecoration> getDecorations() {
        return decorations;
    }

    /**
     * 
     * Add an IDecoration to this DescribedDecorator.
     * 
     * @param decoration
     *            IDecoration to add.
     */
    public void addDecoration(final IDecoration decoration) {
        decorations.add(decoration);
    }

}
