/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.handler;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * The Reset Diagram or Container Origin command handler.
 * 
 * @author Florian Barbin
 * 
 */
public class ResetOriginHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        IWorkbenchPart workbenchPart = HandlerUtil.getActivePart(event);
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            if (structuredSelection.getFirstElement() instanceof DiagramEditPart) {
                getAndExecuteCmd(workbenchPart, (DiagramEditPart) structuredSelection.getFirstElement());
            } else {
                Predicate<AbstractDiagramContainerEditPart> noRegionContainer = new Predicate<AbstractDiagramContainerEditPart>() {
                    @Override
                    public boolean apply(AbstractDiagramContainerEditPart input) {
                        return !input.isRegionContainer();
                    }
                };
                
                List<AbstractDiagramContainerEditPart> selectedAbstractDiagramContainerEditParts = Lists.newArrayList(Iterators.filter(
                        Iterators.filter(structuredSelection.iterator(), AbstractDiagramContainerEditPart.class), noRegionContainer));
               
                if (!selectedAbstractDiagramContainerEditParts.isEmpty()) {
                    getAndExecuteCmd(workbenchPart, selectedAbstractDiagramContainerEditParts.toArray(new AbstractDiagramContainerEditPart[0]));
                }
            }
        }
        return null;
    }

    private void getAndExecuteCmd(IWorkbenchPart workbenchPart, GraphicalEditPart... selection) {
        Command command;
        if (selection.length == 1) {
            command = selection[0].getCommand(new Request(RequestConstants.REQ_RESET_ORIGIN));
        } else {
            CompoundCommand compoundCommand = new CompoundCommand();
            for (int i = 0; i < selection.length; i++) {
                compoundCommand.add(selection[i].getCommand(new Request(RequestConstants.REQ_RESET_ORIGIN)));
            }
            command = compoundCommand;
        }
        DiagramCommandStack commandStack = getDiagramCommandStack(workbenchPart);
        if (commandStack != null) {
            commandStack.execute(command);
        }
    }

    private DiagramCommandStack getDiagramCommandStack(IWorkbenchPart workbenchPart) {
        Object stack = workbenchPart.getAdapter(CommandStack.class);
        return (stack instanceof DiagramCommandStack) ? (DiagramCommandStack) stack : null;
    }
}
