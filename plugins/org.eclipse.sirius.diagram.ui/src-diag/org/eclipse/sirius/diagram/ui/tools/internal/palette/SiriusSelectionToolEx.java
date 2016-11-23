/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.SelectionToolEx;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.part.SiriusDiagramGraphicalViewer;

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

    private boolean isEditPartWithMouseEventTargetFigure(EditPart editPart) {
        return editPart instanceof DNodeNameEditPart || editPart instanceof AbstractDiagramEdgeEditPart || editPart instanceof AbstractDEdgeNameEditPart
                || editPart instanceof DNodeListElementEditPart;
    }
}
