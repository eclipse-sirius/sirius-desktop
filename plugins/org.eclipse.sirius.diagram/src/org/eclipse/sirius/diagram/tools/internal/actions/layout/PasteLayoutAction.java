/******************************************************************************
 * Copyright (c) 2002, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.sirius.diagram.tools.internal.actions.layout;

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
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.diagram.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManagerForSemanticElementsFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.data.extension.LayoutDataManagerRegistry;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;

/**
 * Paste the layout on the selected diagram or on the selected container.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class PasteLayoutAction extends AbstractCopyPasteLayoutAction {

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

        setText("Paste layout");
        setAccelerator(SWT.CTRL | SWT.SHIFT | SWT.ALT | 'V');
        setId(ActionIds.PASTE_LAYOUT);
        setToolTipText("Paste the current recorded layout to the selected diagram");

        setImageDescriptor(SiriusEditPlugin.INSTANCE.getImageDescriptor("obj16/pasteLayout.gif"));
        setDisabledImageDescriptor(SiriusEditPlugin.INSTANCE.getImageDescriptor("obj16/pasteLayoutDisabled.gif"));
        setHoverImageDescriptor(SiriusEditPlugin.INSTANCE.getImageDescriptor("obj16/pasteLayout.gif"));
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#getCommandLabel()
     */
    @Override
    protected String getCommandLabel() {
        return "Paste Layout";
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#getCommand()
     */
    @Override
    protected Command getCommand() {
        Command pasteLayoutCommand = UnexecutableCommand.INSTANCE;
        if (SiriusLayoutDataManagerForSemanticElementsFactory.getInstance().getSiriusLayoutDataManager().containsData()) {

            // Create a compound command to hold the resize commands
            CompoundCommand doStoreLayoutsCmd = new CompoundCommand("Restore layouts");

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
                            doStoreLayoutsCmd.add(new ICommandProxy(new PasteLayoutDataCommand(torestore.getEditingDomain(), diagram.get(), torestore)));
                        }
                    }
                }
            }
            pasteLayoutCommand = doStoreLayoutsCmd.unwrap();
        }
        return pasteLayoutCommand;
    }

    /**
     * A command allowing to paste layout data.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     * 
     */
    private final class PasteLayoutDataCommand extends AbstractTransactionalCommand {

        private IGraphicalEditPart editPartToRestore;

        private DDiagram dDiagram;

        /**
         * Default constructor.
         * 
         * @param domain
         *            the editing domain on which this command will be executed
         * @param dDiagram
         *            the {@link DDiagram} on which layout will be pasted
         * @param editPartToRestore
         *            the edit part to restore
         */
        public PasteLayoutDataCommand(TransactionalEditingDomain domain, DDiagram dDiagram, IGraphicalEditPart editPartToRestore) {
            super(domain, "Paste layout data", null);
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
            List<SiriusLayoutDataManager> layoutDataManagers = LayoutDataManagerRegistry.getSiriusLayoutDataManagers(dDiagram);

            if (!layoutDataManagers.isEmpty()) {
                layoutDataManagers.get(0).applyLayout(editPartToRestore);
            }

            return CommandResult.newOKCommandResult();
        }

    }
}
