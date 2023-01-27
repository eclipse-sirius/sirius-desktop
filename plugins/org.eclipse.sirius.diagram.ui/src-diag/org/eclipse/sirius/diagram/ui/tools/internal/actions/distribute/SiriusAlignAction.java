/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.distribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.gmf.runtime.diagram.ui.actions.AlignAction;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.ui.IWorkbenchPage;

/**
 *  Overrides the GMF AlignAction.<BR>
 *  @author lredor
 */
public class SiriusAlignAction extends AlignAction {

    /**
     * Constructs an AlignAction with the given part and alignment ID.  The alignment ID
     * must by one of:
     * <UL>
     *   <LI>GEFActionConstants.ALIGN_LEFT
     *   <LI>GEFActionConstants.ALIGN_RIGHT
     *   <LI>GEFActionConstants.ALIGN_CENTER
     *   <LI>GEFActionConstants.ALIGN_TOP
     *   <LI>GEFActionConstants.ALIGN_BOTTOM
     *   <LI>GEFActionConstants.ALIGN_MIDDLE
     * </UL>  
     * @param part the workbench part used to obtain context
     * @param id the action ID.
     * @param align the aligment ID.
     */
    public SiriusAlignAction(IWorkbenchPage workbenchPage, String id, int align) {
        super(workbenchPage, id, align);
    }

    /**
     * Constructs an AlignAction with the given part and alignment ID.  The alignment ID
     * must by one of:
     * <UL>
     *   <LI>GEFActionConstants.ALIGN_LEFT
     *   <LI>GEFActionConstants.ALIGN_RIGHT
     *   <LI>GEFActionConstants.ALIGN_CENTER
     *   <LI>GEFActionConstants.ALIGN_TOP
     *   <LI>GEFActionConstants.ALIGN_BOTTOM
     *   <LI>GEFActionConstants.ALIGN_MIDDLE
     * </UL>  
     * @param part the workbench part used to obtain context
     * @param id the action ID.
     * @param align the aligment ID.
     * @param isToolbarItem the indicator of whether or not this is a toolbar action
     * -as opposed to a context-menu action.
     */ 
    public SiriusAlignAction(IWorkbenchPage workbenchPage, String id, int align, boolean isToolbarItem) {
        super(workbenchPage, id, align, isToolbarItem);
    }

    @Override
    protected List createOperationSet() {
        List result = Collections.EMPTY_LIST;
        List editparts = createOperationSetFromDiagramAction();
        editparts = ToolUtilities.getSelectionWithoutDependants(editparts);
        if (editparts.size() >= 2) {
            // Check that every selected elements are labels of edges
            if (editparts.size() == editparts.stream().filter(AbstractDEdgeNameEditPart.class::isInstance).count()) {
                result = editparts;
            } else {
                // Otherwise, check that the parent of every selected shapes is the same.
                result = editparts;
                EditPart parent = ((EditPart) editparts.get(0)).getParent();
                for (int i = 1; i < editparts.size(); i++) {
                    EditPart part = (EditPart) editparts.get(i);
                    if (part.getParent() != parent) {
                        result = Collections.EMPTY_LIST;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private List createOperationSetFromDiagramAction() {
        List selection = getSelectedObjects();
        if (selection.isEmpty() || !(selection.get(0) instanceof IGraphicalEditPart)) {
            return Collections.EMPTY_LIST;
        }
        Iterator selectedEPs = selection.iterator();
        List targetedEPs = new ArrayList();
        while (selectedEPs.hasNext()) {
            EditPart selectedEP = (EditPart) selectedEPs.next();
            targetedEPs.addAll(getTargetEditParts(selectedEP));
        }
        return targetedEPs;
    }
}
