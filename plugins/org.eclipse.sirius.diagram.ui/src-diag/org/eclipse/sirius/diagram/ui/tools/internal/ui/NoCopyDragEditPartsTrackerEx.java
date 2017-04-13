/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.ui;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractBorderedDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A specific dragEditPartTracket that disable the clone feature. Indeed, in Sirius it's not natural to clone a
 * graphical element that will be removed on the next refresh.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class NoCopyDragEditPartsTrackerEx extends SnapToAllDragEditPartsTracker {

    /**
     * Default constructor.
     * 
     * @param sourceEditPart
     *            the source edit part
     */
    public NoCopyDragEditPartsTrackerEx(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    /**
     * Always disable the clone with Ctrl key in Sirius because it only clone the graphical element and not the semantic
     * element.
     * 
     * @param cloneActive
     *            true if cloning should be active (never considered here)
     * @see org.eclipse.gef.tools.DragEditPartsTracker#setCloneActive(boolean)
     */
    @Override
    protected void setCloneActive(boolean cloneActive) {
        super.setCloneActive(false);
    }

    @Override
    protected void reveal(EditPart editpart) {
        // In Sirius, the drag'n'drop can change (delete and create a new
        // one) the previous container of the drag'n'droped element. In this
        // case, the reveal causes a NPE because the hierarchy of edit part is
        // broken.
        if (editpart.getRoot() != null) {
            super.reveal(editpart);
        }
    }

    /**
     * Overridden to return a command only if we are not in case of a drag'n'drop of a node/container outside of its
     * current container.
     * 
     * @return A command only if authorized
     */
    @Override
    protected Command getCommand() {
        if (isAuthorized()) {
            return super.getCommand();
        }
        return UnexecutableCommand.INSTANCE;
    }

    @Override
    protected void updateAutoexposeHelper() {
        // The updateAutoexposeHelper is not called if the drag'n'drop is not
        // authorized because:
        // * There is no reason to scroll the diagram
        // * It causes a strange effect and the forbidden icon disappears.
        if (isAuthorized()) {
            super.updateAutoexposeHelper();
        }
    }

    /**
     * @return true if the drag'n'drop is authorized (according to layouting mode specificity), false otherwise.
     */
    private boolean isAuthorized() {
        boolean isAuthorized = true;
        EditPart movedEditPart = getSourceEditPart();
        if (movedEditPart instanceof AbstractBorderedDiagramElementEditPart && new EditPartQuery((AbstractBorderedDiagramElementEditPart) movedEditPart).isInLayoutingMode()) {
            // We are in layouting mode
            EditPart targetEditPart = getTargetEditPart();
            // Check if the target container is not the same as before move
            boolean notSameContainer = targetEditPart != null
                    && !(movedEditPart.getParent() == targetEditPart || targetEditPart instanceof CompartmentEditPart && targetEditPart.getParent() == movedEditPart.getParent());
            notSameContainer = notSameContainer && (targetEditPart instanceof IGraphicalEditPart && ((IGraphicalEditPart) targetEditPart).resolveSemanticElement() instanceof DSemanticDecorator);
            if (notSameContainer) {
                isAuthorized = false;
            }
        }
        return isAuthorized;
    }

    @Override
    protected boolean handleButtonUp(int button) {
        // The above code is copied from the super classes (SnapToAllDragEditPartsTracker, DragEditPartsTracker and
        // SelectEditPartTracker) to disable the reveal.
        boolean result = false;
        if (stateTransition(STATE_DRAG_IN_PROGRESS, STATE_TERMINAL)) {
            eraseSourceFeedback();
            eraseTargetFeedback();
            performDrag();
            result = true;
        } else if (isInState(STATE_DRAG)) {
            performSelection();
            if (getFlag(org.eclipse.gef.tools.SelectEditPartTracker.MAX_FLAG))
                // SelectEditPartTracker.MAX_FLAG is a protected constant equals to
                // SelectEditPartTracker.FLAG_ENABLE_DIRECT_EDIT (that must be originally used here).
                performDirectEdit();
            // The SelectEditPartTracker behavior is overridden here to never reveal the selected element.
            // if (button == 1 && getSourceEditPart().getSelected() != EditPart.SELECTED_NONE)
            // getCurrentViewer().reveal(getSourceEditPart());
            setState(STATE_TERMINAL);
            result = true;
        }
        // Clean up the mode to original state.
        snapToAllShape = SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;
        return result;
    }
}
