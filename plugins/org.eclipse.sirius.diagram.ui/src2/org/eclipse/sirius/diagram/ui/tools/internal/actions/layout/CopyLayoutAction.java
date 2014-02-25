/******************************************************************************
 * Copyright (c) 2002, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.actions.layout;

import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.data.extension.LayoutDataManagerRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Copy the layout of the selected diagram or of the selected diagram elements.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class CopyLayoutAction extends AbstractCopyPasteLayoutAction {

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the active workbench page
     * @param actionWorkbenchPart
     *            the part concerned by this action. Could be null.
     */
    public CopyLayoutAction(final IWorkbenchPage workbenchPage, IWorkbenchPart actionWorkbenchPart) {
        super(workbenchPage, actionWorkbenchPart);

        setText("Copy layout");
        setAccelerator(SWT.CTRL | SWT.SHIFT | SWT.ALT | 'C');
        setId(ActionIds.COPY_LAYOUT);
        setToolTipText("Copy the layout of the selected diagram elements");

        setImageDescriptor(SiriusEditPlugin.INSTANCE.getImageDescriptor("obj16/copyLayout.gif"));
        setDisabledImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor("obj16/copyLayoutDisabled.gif"));
        setHoverImageDescriptor(SiriusEditPlugin.INSTANCE.getImageDescriptor("obj16/copyLayout.gif"));
    }

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the active workbench page
     */
    public CopyLayoutAction(final IWorkbenchPage workbenchPage) {
        this(workbenchPage, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#getCommandLabel()
     */
    @Override
    protected String getCommandLabel() {
        return "Copy Layout";
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#getCommand()
     */
    @Override
    protected Command getCommand() {
        // Create a compound command to hold the store layout commands
        final CompoundCommand doStoreLayoutsCmd = new CompoundCommand("Store layouts");
        doStoreLayoutsCmd.add(new Command("Clear previous layout data") {
            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.gef.commands.Command#canUndo()
             */
            // FIXME LRE : this should be undoable, and it should not be a
            // command in the stack ! !
            @Override
            public boolean canUndo() {
                return false;
            }

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.gef.commands.Command#canExecute()
             */
            @Override
            public boolean canExecute() {
                return super.canExecute();
            }

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.gef.commands.Command#execute()
             */
            @Override
            public void execute() {
                for (SiriusLayoutDataManager layoutDataManager : LayoutDataManagerRegistry.getAllSiriusLayoutDataManagers()) {
                    layoutDataManager.clearLayoutData();
                }
            }
        });
        // Create an iterator for the selection
        final Iterator<?> iter = getSelectedObjects().iterator();
        DiagramEditPart diagramEditPart = getDiagramEditPart();
        if (diagramEditPart instanceof IDDiagramEditPart) {
            final Option<DDiagram> diagram = ((IDDiagramEditPart) diagramEditPart).resolveDDiagram();
            while (iter.hasNext() && diagram.some()) {
                final Object next = iter.next();
                if (next instanceof IGraphicalEditPart) {
                    final IGraphicalEditPart toStore = (IGraphicalEditPart) next;

                    doStoreLayoutsCmd.add(new ICommandProxy(new AbstractTransactionalCommand(toStore.getEditingDomain(), "Copy layout data", null) {

                        @Override
                        protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
                            for (SiriusLayoutDataManager layoutDataManager : LayoutDataManagerRegistry.getSiriusLayoutDataManagers(diagram.get())) {
                                layoutDataManager.storeLayoutData(toStore);
                            }
                            return CommandResult.newOKCommandResult();
                        }

                        /**
                         * {@inheritDoc}
                         * 
                         * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doUndo(org.eclipse.core.runtime.IProgressMonitor,
                         *      org.eclipse.core.runtime.IAdaptable)
                         */
                        @Override
                        protected IStatus doUndo(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
                            for (SiriusLayoutDataManager layoutDataManager : LayoutDataManagerRegistry.getSiriusLayoutDataManagers(diagram.get())) {
                                layoutDataManager.clearLayoutData();
                            }
                            return super.doUndo(monitor, info);
                        }
                    }));
                }
            }
        }

        return doStoreLayoutsCmd.unwrap();
    }
}
