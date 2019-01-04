/******************************************************************************
 * Copyright (c) 2002, 2019 IBM Corporation and others.
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.format.data.extension.FormatDataManagerRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Copy the format of the selected diagram or of the selected diagram elements.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class CopyFormatAction extends AbstractCopyPasteFormatAction {

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the active workbench page
     * @param actionWorkbenchPart
     *            the part concerned by this action. Could be null.
     */
    public CopyFormatAction(final IWorkbenchPage workbenchPage, IWorkbenchPart actionWorkbenchPart) {
        super(workbenchPage, actionWorkbenchPart);

        setText(Messages.CopyFormatAction_text);
        setAccelerator(SWT.CTRL | SWT.SHIFT | SWT.ALT | 'C');
        setId(ActionIds.COPY_FORMAT);
        setToolTipText(Messages.CopyFormatAction_toolTipText);

        setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.COPY_FORMAT_ICON));
        setDisabledImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.COPY_FORMAT_DISABLED_ICON));
        setHoverImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.COPY_FORMAT_ICON));
    }

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the active workbench page
     */
    public CopyFormatAction(final IWorkbenchPage workbenchPage) {
        this(workbenchPage, null);
    }

    @Override
    protected String getCommandLabel() {
        return Messages.CopyFormatAction_commandLabel;
    }

    @Override
    protected Command getCommand() {
        // Create a compound command to hold the store format commands
        final CompoundCommand doStoreFormatsCmd = new CompoundCommand(Messages.CopyFormatAction_storeFormatCommandLabel);
        doStoreFormatsCmd.add(new Command(Messages.CopyFormatAction_clearPreviousFormatDateCommandLabel) {
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
             * @see org.eclipse.gef.commands.Command#execute()
             */
            @Override
            public void execute() {
                for (SiriusFormatDataManager formatDataManager : FormatDataManagerRegistry.getAllSiriusFormatDataManagers()) {
                    formatDataManager.clearFormatData();
                }
            }
        });
        DiagramEditPart diagramEditPart = getDiagramEditPart();
        if (diagramEditPart instanceof IDDiagramEditPart) {
            final Option<DDiagram> diagram = ((IDDiagramEditPart) diagramEditPart).resolveDDiagram();
            if (diagram.some()) {
                // Clean the selection to keep only one data if both node and
                // its label are selected.
                List<IGraphicalEditPart> selectedEditParts = cleanSelectedObjects(getSelectedObjects());
                // For each selected edit part, store its format.
                for (final IGraphicalEditPart toStore : selectedEditParts) {
                    doStoreFormatsCmd.add(new ICommandProxy(new CopyFormatDataCommand(toStore.getEditingDomain(), diagram.get(), toStore)));
                }
            }
        }
        doStoreFormatsCmd.add(new Command(Messages.CopyFormatAction_notifyEditors) {
            @Override
            public boolean canUndo() {
                return false;
            }

            @Override
            public void execute() {
                // Reinit tabbar of each open editor (to refresh PasteAction
                // that depends on the layoutDataManager empty state)
                IEditorReference[] editorReferences = null;

                IWorkbenchPage page = EclipseUIUtil.getActivePage();
                if (page != null) {
                    editorReferences = page.getEditorReferences();
                }

                if (editorReferences != null) {
                    for (IEditorReference ref : editorReferences) {
                        IEditorPart editor = ref.getEditor(false);
                        if (editor instanceof DDiagramEditorImpl) {
                            DDiagramEditorImpl diagramEditor = (DDiagramEditorImpl) editor;
                            if (diagramEditor.getTabbar() != null) {
                                diagramEditor.getTabbar().reinitToolBar(diagramEditor.getDiagramGraphicalViewer().getSelection());
                            }
                        }
                    }
                }
            }
        });
        return doStoreFormatsCmd.unwrap();
    }

    /**
     * Remove label from selection if its parent's node is also selected.
     * 
     * @param selectedObjects
     *            The current selected objects.
     * @return The current selected {@link IGraphicalEditPart}
     */
    private List<IGraphicalEditPart> cleanSelectedObjects(List<?> selectedObjects) {
        List<IGraphicalEditPart> result = new ArrayList<>();
        // Transform List to Set to optimize the contains() called in the below
        // loop
        final Set<Object> selection = new HashSet<Object>(selectedObjects);
        for (Object selectedObject : selection) {
            boolean isLabelOfSelectedParent = (selectedObject instanceof IDiagramNameEditPart) && selection.contains(((IDiagramNameEditPart) selectedObject).getParent());
            if (!isLabelOfSelectedParent) {
                result.add((IGraphicalEditPart) selectedObject);
            }
        }
        return result;
    }

    /**
     * A command allowing to copy format data.
     */
    private static final class CopyFormatDataCommand extends AbstractTransactionalCommand {

        private IGraphicalEditPart toStore;

        private DDiagram dDiagram;

        /**
         * Default constructor.
         * 
         * @param domain
         *            the editing domain on which this command will be executed
         * @param dDiagram
         *            the {@link DDiagram} on which format will be pasted
         * @param editPartToStore
         *            the edit part to store
         */
        CopyFormatDataCommand(TransactionalEditingDomain domain, DDiagram dDiagram, IGraphicalEditPart editPartToStore) {
            super(domain, Messages.CopyFormatDataCommand_label, null);
            this.dDiagram = dDiagram;
            this.toStore = editPartToStore;
        }

        @Override
        protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
            for (SiriusFormatDataManager formatDataManager : FormatDataManagerRegistry.getSiriusFormatDataManagers(dDiagram)) {
                formatDataManager.storeFormatData(toStore);
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
            for (SiriusFormatDataManager formatDataManager : FormatDataManagerRegistry.getSiriusFormatDataManagers(dDiagram)) {
                formatDataManager.clearFormatData();
            }
            return super.doUndo(monitor, info);
        }
    }
}
