/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.providers.decorators;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.decorators.AbstractSiriusDecorator;
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
 */
public class DescribedDecorator extends AbstractSiriusDecorator {

    /**
     * Create a decorator.
     * 
     * @param decoratorTarget
     *            target to decorate.
     */
    public DescribedDecorator(final IDecoratorTarget decoratorTarget) {
        super(decoratorTarget);
    }

    @Override
    public void refresh() {
        super.refresh();
        EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
        int margin = -1;
        if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
            margin = MapModeUtil.getMapMode(((org.eclipse.gef.GraphicalEditPart) editPart).getFigure()).DPtoLP(margin);
        }
        if (editPart instanceof IDiagramElementEditPart) {
            DDiagramElement element = ((IDiagramElementEditPart) editPart).resolveDiagramElement();
            if (element != null) {
                for (Decoration decoration : element.getDecorations()) {
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
            return WorkspaceImageFigure.getImageInstanceFromPath(description.getImageExpression());
        } else {
            return null;
        }
    }

    @Override
    protected Direction getDirection(EditPart editPart) {
        return null;
    }

    @Override
    protected Image getDecorationImage(EditPart editPart) {
        return null;
    }

}
