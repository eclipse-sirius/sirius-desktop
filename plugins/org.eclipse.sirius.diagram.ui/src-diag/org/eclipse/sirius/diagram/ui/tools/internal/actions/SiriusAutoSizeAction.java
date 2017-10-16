/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.AutoSizeAction;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Overrides the GMF AutoSizeAction to authorize auto-size for region container.
 * 
 * @author fbarbin
 *
 */
@SuppressWarnings("restriction")
public class SiriusAutoSizeAction extends AutoSizeAction {

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the {@link IWorkbenchPage}
     */
    public SiriusAutoSizeAction(IWorkbenchPage workbenchPage) {
        super(workbenchPage);
    }

    /**
     * Duplicated from org.eclipse.gmf.runtime.diagram.ui.actions.internal.AutoSizeAction.getCommand(Request) to
     * authorized autoSize for container with regions.
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected Command getCommand(Request request) {
        boolean foundNonAutosizedPart = false;
        List operationSet = getOperationSet();
        Iterator editParts = operationSet.iterator();
        CompoundCommand command = new CompoundCommand(getCommandLabel());
        while (editParts.hasNext()) {
            EditPart editPart = (EditPart) editParts.next();
            // check if the editpart is autosized
            if (concernRegion(editPart)) {
                foundNonAutosizedPart = true;
            } else if (editPart instanceof GraphicalEditPart) {
                GraphicalEditPart graphicalEditPart = (GraphicalEditPart) editPart;
                Integer containerWidth = (Integer) graphicalEditPart.getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width());
                Integer containerHeight = (Integer) graphicalEditPart.getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height());
                if (containerWidth.intValue() != -1 || containerHeight.intValue() != -1) {
                    foundNonAutosizedPart = true;
                }
            }

            Command curCommand = editPart.getCommand(request);
            if (curCommand != null) {
                command.add(curCommand);
            }
        }
        return command.isEmpty() || command.size() != operationSet.size() || !foundNonAutosizedPart ? UnexecutableCommand.INSTANCE : (Command) command;
    }

    private boolean concernRegion(EditPart hostPart) {
        if (hostPart instanceof AbstractDiagramContainerEditPart && ((AbstractDiagramContainerEditPart) hostPart).isRegionContainer()) {
            // We collect compartment children
            // The region auto size is available if at least one child is not auto-sized.
            return ((AbstractDiagramContainerEditPart) hostPart).getResizableCompartments().stream().flatMap(el -> ((ResizableCompartmentEditPart) el).getChildren().stream())
                    .filter(AbstractDiagramElementContainerEditPart.class::isInstance).filter(el -> {
                Integer containerWidth = (Integer) ((AbstractDiagramElementContainerEditPart) el).getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width());
                Integer containerHeight = (Integer) ((AbstractDiagramElementContainerEditPart) el).getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height());
                return containerWidth.intValue() != -1 || containerHeight.intValue() != -1;
            }).findFirst().isPresent();
        }
        return false;
    }
}
