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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Paste the style on the selected diagram or on the selected container.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class PasteStyleAction extends AbstractCopyPasteFormatAction {

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the active workbench page
     * @param actionWorkbenchPart
     *            the part concerned by this action. Could be null.
     */
    public PasteStyleAction(final IWorkbenchPage workbenchPage, IWorkbenchPart actionWorkbenchPart) {
        super(workbenchPage, actionWorkbenchPart);

        setText(Messages.PasteStyleAction_text);
        setId(ActionIds.PASTE_STYLE);
        setToolTipText(Messages.PasteStyleAction_toolTipText);

        setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_STYLE_ICON));
        setDisabledImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_STYLE_DISABLED_ICON));
        setHoverImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.PASTE_STYLE_ICON));
    }

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the active workbench page
     */
    public PasteStyleAction(final IWorkbenchPage workbenchPage) {
        this(workbenchPage, null);
    }

    @Override
    protected String getCommandLabel() {
        return Messages.PasteStyleAction_commandLabel;
    }

    @Override
    protected Command getCommand() {
        Command pasteStyleCommand = UnexecutableCommand.INSTANCE;
        if (SiriusFormatDataManagerForSemanticElementsFactory.getInstance().getSiriusFormatDataManager().containsData()) {

            // Create a compound command to hold the paste commands
            CompoundCommand doPasteStylesCmd = new CompoundCommand(Messages.PasteStyleAction_restoreStyleCommandLabel);

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
                            doPasteStylesCmd.add(new ICommandProxy(new PasteStyleDataCommand(torestore.getEditingDomain(), diagram.get(), torestore)));
                        }
                    }
                }
            }
            pasteStyleCommand = doPasteStylesCmd.unwrap();
        }
        return pasteStyleCommand;
    }

    /**
     * A command allowing to paste style data.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     */
    private static final class PasteStyleDataCommand extends AbstractTransactionalCommand {

        private IGraphicalEditPart editPartToRestore;

        private DDiagram dDiagram;

        /**
         * Default constructor.
         * 
         * @param domain
         *            the editing domain on which this command will be executed
         * @param dDiagram
         *            the {@link DDiagram} on which style will be pasted
         * @param editPartToRestore
         *            the edit part to restore
         */
        PasteStyleDataCommand(TransactionalEditingDomain domain, DDiagram dDiagram, IGraphicalEditPart editPartToRestore) {
            super(domain, Messages.PasteStyleDataCommand_label, null);
            this.dDiagram = dDiagram;
            this.editPartToRestore = editPartToRestore;
        }

        @Override
        protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
            List<SiriusFormatDataManager> formatDataManagers = FormatDataManagerRegistry.getSiriusFormatDataManagers(dDiagram);
            if (!formatDataManagers.isEmpty()) {
                formatDataManagers.get(0).applyStyle(editPartToRestore);
            }

            return CommandResult.newOKCommandResult();
        }

    }
}
