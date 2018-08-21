/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Felix Dorner <felix.dorner@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ui.tools.internal.dialogs.SingleRepresentationTreeSelectionDialog;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Changes the target representation descriptor for representation link notes.
 */
public class SetLinkNoteTargetHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            if (structuredSelection.getFirstElement() instanceof SiriusNoteEditPart) {
                SiriusNoteEditPart note = (SiriusNoteEditPart) structuredSelection.getFirstElement();
                IEditorPart editor = HandlerUtil.getActiveEditor(event);
                if (editor instanceof DDiagramEditor) {
                    Shell shell = HandlerUtil.getActiveShell(event);
                    Session session = ((DDiagramEditor) editor).getSession();
                    DRepresentationDescriptor descriptor = SingleRepresentationTreeSelectionDialog.openSelectRepresentationDescriptor(shell, session);
                    if (descriptor != null) {
                        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                        note.getDiagramEditDomain().getDiagramCommandStack()
                                .execute(new ICommandProxy(new GMFCommandWrapper(domain, SetCommand.create(domain, note.getModel(), NotationPackage.Literals.VIEW__ELEMENT, descriptor))));
                    }
                }
            }
        }
        return null;
    }

}
