/******************************************************************************
 * Copyright (c) 2002, 2023 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Paste the layout on the selected diagram or on the selected container.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class PasteLayoutAction extends AbstractCopyPasteFormatAction {

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the active workbench page
     * @param actionWorkbenchPart
     *            the part concerned by this action. Could be null.
     */
    public PasteLayoutAction(final IWorkbenchPage workbenchPage, IWorkbenchPart actionWorkbenchPart) {
        super(workbenchPage, actionWorkbenchPart);

        setText(Messages.PasteLayoutAction_text);
        setId(ActionIds.PASTE_LAYOUT);
        setToolTipText(Messages.PasteLayoutAction_toolTipText);

        setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_LAYOUT_ICON));
        setDisabledImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_LAYOUT_DISABLED_ICON));
        setHoverImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_LAYOUT_ICON));
    }

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the active workbench page
     */
    public PasteLayoutAction(final IWorkbenchPage workbenchPage) {
        this(workbenchPage, null);
    }

    @Override
    protected String getCommandLabel() {
        return Messages.PasteLayoutAction_commandLabel;
    }

    @Override
    protected Command getCommand() {
        Command pasteLayoutCommand = UnexecutableCommand.INSTANCE;
        if (SiriusFormatDataManagerForSemanticElementsFactory.getInstance().getSiriusFormatDataManager().containsData()) {

            // Create a compound command to hold the paste commands
            CompoundCommand doPasteLayoutsCmd = new CompoundCommand(Messages.PasteLayoutAction_restoreLayoutCommandLabel);

            DiagramEditPart diagramEditPart = getDiagramEditPart();
            if (diagramEditPart instanceof IDDiagramEditPart) {
                final Option<DDiagram> diagram = ((IDDiagramEditPart) diagramEditPart).resolveDDiagram();

                // If ddiagram is locked, we will return an unexecutableCommand
                // so that action is disabled
                if (diagram.some() && PermissionAuthorityRegistry.getDefault().getPermissionAuthority(diagram.get()).canEditInstance(diagram.get())) {
                    // Sort selection by common parent, to allow a common PasteFormatDataCommand for brothers (and
                    // allow a right "bounding box" mode application).
                    Map<IGraphicalEditPart, List<IGraphicalEditPart>> selectionSortedByCommonParent = sortSelection(getSelectedObjects());
                    if (!selectionSortedByCommonParent.isEmpty()) {
                        Iterator<Entry<IGraphicalEditPart, List<IGraphicalEditPart>>> iter = selectionSortedByCommonParent.entrySet().iterator();
                        while (iter.hasNext()) {
                            doPasteLayoutsCmd.add(getCommand(diagram.get(), iter.next()));
                        }
                        doPasteLayoutsCmd.add(new Command(Messages.SelectPasteModeDialog_tearDownCommandName) {
                            @Override
                            public void execute() {
                                SelectPasteModeDialog.tearDownPromptResult();
                            }
                        });
                    }
                }
            }
            pasteLayoutCommand = doPasteLayoutsCmd.unwrap();
        }
        return pasteLayoutCommand;
    }

    private Command getCommand(DDiagram dDiagram, Entry<IGraphicalEditPart, List<IGraphicalEditPart>> entry) {
        if (entry.getValue().size() == 1) {
            return new ICommandProxy(
                    new PasteLayoutDataCommand(entry.getValue().get(0).getEditingDomain(), dDiagram, entry.getValue().get(0), getWorkbenchPage().getActivePart().getSite().getShell()));
        } else {
            return new ICommandProxy(
                    new PasteLayoutDataCommand(entry.getValue().get(0).getEditingDomain(), dDiagram, entry.getKey(), entry.getValue(), getWorkbenchPage().getActivePart().getSite().getShell()));
        }
    }

    /**
     * A command allowing to paste layout data.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     * 
     */
    private static final class PasteLayoutDataCommand extends AbstractTransactionalCommand {

        private IGraphicalEditPart containerOrMainEditPartToRestore;

        private List<IGraphicalEditPart> editPartsToRestore;

        private DDiagram dDiagram;

        private Shell shell;

        /**
         * Default constructor.
         * 
         * @param domain
         *            the editing domain on which this command will be executed
         * @param dDiagram
         *            the {@link DDiagram} on which layout will be pasted
         * @param editPartToRestore
         *            the edit part to restore
         * @param shell
         *            the parent shell, or <code>null</code> to create a top-level shell
         */
        PasteLayoutDataCommand(TransactionalEditingDomain domain, DDiagram dDiagram, IGraphicalEditPart editPartToRestore, Shell shell) {
            super(domain, Messages.PasteLayoutDataCommand_label, null);
            this.dDiagram = dDiagram;
            this.containerOrMainEditPartToRestore = editPartToRestore;
            this.shell = shell;
        }

        /**
         * Constructor to apply the layout to a subpart of a container.
         * 
         * @param domain
         *            the editing domain on which this command will be executed
         * @param dDiagram
         *            the {@link DDiagram} on which layout will be pasted
         * @param editPartContainer
         *            the container of editPartsToRestore, or null if editPartsToRestore are a list edges (as edge edit
         *            part has not really a parent).
         * @param editPartsToRestore
         *            the edit parts to restore (children of the editPartContainer, except for edges)
         * @param shell
         *            the parent shell, or <code>null</code> to create a top-level shell
         */
        PasteLayoutDataCommand(TransactionalEditingDomain domain, DDiagram dDiagram, IGraphicalEditPart editPartContainer, List<IGraphicalEditPart> editPartsToRestore, Shell shell) {
            super(domain, Messages.PasteLayoutDataCommand_label, null);
            this.dDiagram = dDiagram;
            this.containerOrMainEditPartToRestore = editPartContainer;
            this.editPartsToRestore = editPartsToRestore;
            this.shell = shell;
        }

        @Override
        protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
            List<SiriusFormatDataManager> formatDataManagers = FormatDataManagerRegistry.getSiriusFormatDataManagers(dDiagram);
            if (!formatDataManagers.isEmpty()) {
                try {
                    if (editPartsToRestore == null) {
                        formatDataManagers.get(0).applyLayout(containerOrMainEditPartToRestore, SelectPasteModeDialog.promptIsAbsolutePasteMode(shell));
                    } else {
                        formatDataManagers.get(0).applyLayout(containerOrMainEditPartToRestore, editPartsToRestore, SelectPasteModeDialog.promptIsAbsolutePasteMode(shell));
                    }
                } catch (OperationCanceledException e) {
                    return CommandResult.newCancelledCommandResult();
                }
            }
            return CommandResult.newOKCommandResult();
        }
    }
}
