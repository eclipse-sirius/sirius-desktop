/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManagerForSemanticElementsFactory;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.format.data.extension.FormatDataManagerRegistry;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Paste the format on the selected diagram or on the selected container.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class PasteFormatAction extends AbstractCopyPasteFormatAction {

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the active workbench page
     * @param actionWorkbenchPart
     *            the part concerned by this action. Could be null.
     */
    public PasteFormatAction(final IWorkbenchPage workbenchPage, IWorkbenchPart actionWorkbenchPart) {
        super(workbenchPage, actionWorkbenchPart);

        setText(Messages.PasteFormatAction_text);
        setAccelerator(SWT.CTRL | SWT.SHIFT | SWT.ALT | 'V');
        setId(ActionIds.PASTE_FORMAT);
        setToolTipText(Messages.PasteFormatAction_toolTipText);

        setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_FORMAT_ICON));
        setDisabledImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_FORMAT_DISABLED_ICON));
        setHoverImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_FORMAT_ICON));
    }

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the active workbench page
     */
    public PasteFormatAction(final IWorkbenchPage workbenchPage) {
        this(workbenchPage, null);
    }

    @Override
    protected String getCommandLabel() {
        return Messages.PasteFormatAction_commandLabel;
    }

    @Override
    protected Command getCommand() {
        Command pasteFormatCommand = UnexecutableCommand.INSTANCE;
        if (SiriusFormatDataManagerForSemanticElementsFactory.getInstance().getSiriusFormatDataManager().containsData()) {

            // Create a compound command to hold the paste commands
            CompoundCommand doPasteFormatsCmd = new CompoundCommand(Messages.PasteFormatAction_restoreFormatCommandLabel);

            // Create an iterator for the selection
            final Iterator<?> iter = getSelectedObjects().iterator();
            DiagramEditPart diagramEditPart = getDiagramEditPart();
            if (diagramEditPart instanceof IDDiagramEditPart) {
                final Option<DDiagram> diagram = ((IDDiagramEditPart) diagramEditPart).resolveDDiagram();

                // If ddiagram is locked, we will return an unexecutableCommand
                // so that action is disabled
                if (diagram.some() && PermissionAuthorityRegistry.getDefault().getPermissionAuthority(diagram.get()).canEditInstance(diagram.get())) {
                    while (iter.hasNext()) {
                        final Object next = iter.next();
                        if (next instanceof IGraphicalEditPart) {
                            final IGraphicalEditPart torestore = (IGraphicalEditPart) next;
                            doPasteFormatsCmd.add(new ICommandProxy(new PasteFormatDataCommand(torestore.getEditingDomain(), diagram.get(), torestore)));
                        }
                    }
                }
            }
            pasteFormatCommand = doPasteFormatsCmd.unwrap();
        }
        return pasteFormatCommand;
    }

    /**
     * A command allowing to paste format data.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     * 
     */
    private static final class PasteFormatDataCommand extends AbstractTransactionalCommand {

        private IGraphicalEditPart editPartToRestore;

        private DDiagram dDiagram;

        /**
         * Default constructor.
         * 
         * @param domain
         *            the editing domain on which this command will be executed
         * @param dDiagram
         *            the {@link DDiagram} on which format will be pasted
         * @param editPartToRestore
         *            the edit part to restore
         */
        PasteFormatDataCommand(TransactionalEditingDomain domain, DDiagram dDiagram, IGraphicalEditPart editPartToRestore) {
            super(domain, Messages.PasteFormatDataCommand_label, null);
            this.dDiagram = dDiagram;
            this.editPartToRestore = editPartToRestore;
        }

        /**
         * 
         * {@inheritDoc}
         * 
         * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
         *      org.eclipse.core.runtime.IAdaptable)
         */
        @Override
        protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
            List<SiriusFormatDataManager> formatDataManagers = FormatDataManagerRegistry.getSiriusFormatDataManagers(dDiagram);
            if (!formatDataManagers.isEmpty()) {
                formatDataManagers.get(0).applyFormat(editPartToRestore);
            }
            return CommandResult.newOKCommandResult();
        }

    }
}
