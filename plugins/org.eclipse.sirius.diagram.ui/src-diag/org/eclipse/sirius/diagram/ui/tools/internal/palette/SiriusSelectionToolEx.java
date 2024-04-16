/*******************************************************************************
 * Copyright (c) 2017, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import java.util.Optional;

import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.BorderItemContainerFigure;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.SelectionToolEx;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.part.SiriusDiagramGraphicalViewer;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.SnapToAllDragEditPartsTracker;
import org.eclipse.swt.events.KeyEvent;

/**
 * Specific Sirius SelectionToolEx to use findMouseEventTargetAt instead of findObjectAtExcluding. This allows to
 * dissociate the editPart that displays the tooltip from the editPart that gets the focus in case of a click. This is
 * correctly handled with SWTEventDispatcher (mouseTarget field is initialized with findMouseEventTargetAt and
 * hoverSource field is initialized with findFigureAt) but not by the selection tool. The overridden method
 * updateTargetUnderMouse() is used to set the targetEditPart then used by handleButtonDown latter. This override has a
 * limitation for figures of editPart that is not "MouseEventTarget". This is the case of NameEditPart or EdgeEditPart
 * like: {@link DNodeNameEditPart}, {@link AbstractDEdgeNameEditPart}, {@link DNodeListElementEditPart} and
 * {@link AbstractDiagramEdgeEditPart}. In this case, we can not use findMouseEventTargetAt, so the selected element is
 * not the right one.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusSelectionToolEx extends SelectionToolEx {

    /**
     * Constructor.
     */
    public SiriusSelectionToolEx() {
    }

    @Override
    protected boolean updateTargetUnderMouse() {
        if (!isTargetLocked()) {
            EditPart editPart = null;
            if (getCurrentViewer() != null) {
                // Firstly search the object under mouse (as super method)
                EditPart editPartWithFindObject = getCurrentViewer().findObjectAtExcluding(getLocation(), getExclusionSet(), getTargetingConditional());
                // Then if this edit part has a figure that is "MouseEventTarget", we search the mouseEvent target at
                // this location. The figure that is not a "MouseEventTarget" (returning false to isMouseEventTarget())
                // can not receive MouseEvent, this is why we can not use findMouseEventTargetAt for them.
                if (getCurrentViewer() instanceof SiriusDiagramGraphicalViewer && !isEditPartWithMouseEventTargetFigure(editPartWithFindObject)) {
                    editPart = ((SiriusDiagramGraphicalViewer) getCurrentViewer()).findMouseEventTargetAt(getLocation());
                } else {
                    editPart = editPartWithFindObject;
                }
            }
            if (editPart != null)
                editPart = editPart.getTargetEditPart(getTargetRequest());
            boolean changed = getTargetEditPart() != editPart;
            setTargetEditPart(editPart);
            return changed;
        } else
            return false;
    }

    @SuppressWarnings("restriction")
    private boolean isEditPartWithMouseEventTargetFigure(EditPart editPart) {
        return isSiriusSpecificEditPart(editPart) || editPart instanceof NoteAttachmentEditPart;
    }

    private boolean isSiriusSpecificEditPart(EditPart editPart) {
        return editPart instanceof DNodeNameEditPart || editPart instanceof AbstractDiagramEdgeEditPart || editPart instanceof AbstractDEdgeNameEditPart
                || editPart instanceof DNodeListElementEditPart;
    }


    /**
     * Method overridden to allow arrow key press with modifier.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.palette.SelectionToolEx#handleKeyDown(org.eclipse.swt.events.KeyEvent)
     */
    @Override
    protected boolean handleKeyDown(KeyEvent e) {
        Optional<Boolean> optionalLocalresult = specificHandleKeyDown(e);
        if (optionalLocalresult.isEmpty()) {
            return super.handleKeyDown(e);
        } else {
            return optionalLocalresult.get().booleanValue();
        }
    }

    /**
     * Method inspired by org.eclipse.gmf.runtime.diagram.ui.services.palette.SelectionToolEx.handleKeyDown(KeyEvent) to
     * authorize arrow keys with modifiers ({@link #acceptArrowKey(KeyEvent)} instead of
     * {@link SelectionToolEx#acceptArrowKeyOnly(KeyEvent)}; Mainly for the "Alt" modifier to disable the snap.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.palette.SelectionToolEx#handleKeyDown(org.eclipse.swt.events.KeyEvent)
     */
    protected Optional<Boolean> specificHandleKeyDown(KeyEvent e) {
        Optional<Boolean> optionalLocalresult = Optional.empty();
        if (acceptArrowKey(e) && getState() == STATE_INITIAL && !getCurrentViewer().getSelectedEditParts().isEmpty()) {

            EditPart selectedEP = (EditPart) getCurrentViewer().getSelectedEditParts().get(0);

            if (selectedEP instanceof GraphicalEditPart) {

                GraphicalEditPart gep = (GraphicalEditPart) selectedEP;

                /*
                 * The shape we'll be moved in the direction of the arrow key if: 1) It has the appropriate edit policy
                 * that supports shape moving installed on the editpart 2) The editparts figure's parent layout manager
                 * is some sort of XYLayout In all other cases we just change the selection based on arrow key
                 * (implemented in GEF).
                 */
                if (gep.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE) instanceof NonResizableEditPolicy && gep.getFigure().getParent() != null
                        && (gep.getFigure().getParent().getLayoutManager() instanceof XYLayout || gep.getFigure().getParent() instanceof BorderItemContainerFigure)) {

                    resetHover();

                    if (getDragTracker() != null) {
                        getDragTracker().deactivate();
                    }

                    setState(STATE_ACCESSIBLE_DRAG_IN_PROGRESS);

                    setTargetEditPart(gep);

                    updateTargetRequest();
                    DragTracker dragTracker = gep.getDragTracker(getTargetRequest());
                    if (dragTracker != null) {
                        setDragTracker(dragTracker);
                        dragTracker.keyDown(e, getCurrentViewer());
                        lockTargetEditPart(gep);
                        optionalLocalresult = Optional.of(true);
                    } else {
                        optionalLocalresult = Optional.of(false);
                    }
                }
            }
        }
        return optionalLocalresult;
    }

    /**
     * As for method {@link #handleKeyDown(KeyEvent)}, this method is overridden to "finish" the move in case of arrow
     * key press.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.palette.SelectionToolEx#handleKeyUp(org.eclipse.swt.events.KeyEvent)
     */
    @Override
    protected boolean handleKeyUp(KeyEvent e) {
        boolean returnVal = super.handleKeyUp(e);
        if (acceptArrowKey(e)) {
            // In superclass SelectionToolEx.handleKeyUp(KeyEvent), it was "if (acceptArrowKeyOnly(e) &&
            // !isUsingTraverseHandles) {".
            if (getDragTracker() != null) {
                getDragTracker().commitDrag();
            }
            setDragTracker(null);
            setState(STATE_INITIAL);
            unlockTargetEditPart();
        }
        return returnVal;
    }

    @Override
    protected boolean handleViewerExited() {
        boolean doNothing = false;
        if (isInState(STATE_ACCESSIBLE_DRAG | STATE_ACCESSIBLE_DRAG_IN_PROGRESS | STATE_TRAVERSE_HANDLE | STATE_DRAG | STATE_DRAG_IN_PROGRESS)) {
            if (getDragTracker() instanceof SnapToAllDragEditPartsTracker && ((SnapToAllDragEditPartsTracker) getDragTracker()).isMoveWithArrowKeysSiriusMode()) {
                // We do nothing as the key continue to be pressed
                doNothing = true;
            }
        }
        if (doNothing) {
            return false;
        } else {
            return super.handleViewerExited();
        }
    }
}
