/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.provider;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.swt.widgets.Tree;

/**
 * Support for editing DTreeItems of a DTree.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DTreeItemEditingSupport extends EditingSupport {

    /**
     * The transactional editing domain of this viewer.
     */
    private final TransactionalEditingDomain editingDomain;

    /**
     * The model accessor to use for get and modify model elements.
     */
    private final ModelAccessor accessor;

    /**
     * The command Factory to use for building tools commands.
     */
    private final ITreeCommandFactory treeCommandFactory;

    /**
     * The managed TreeEditor.
     */
    private final AbstractDTreeEditor treeEditor;

    /**
     * Creates a new editing support for editing DTreeItems of a DTree.
     * 
     * @param viewer
     *            The treeViewer for this editingSupport
     * @param editingDomain
     *            The transactional editing domain of this viewer
     * @param accessor
     *            The accessor for the model
     * @param treeCommandFactory
     *            the command Factory to use for building tools commands
     * @param abstractDTreeEditor
     *            The associated editor
     */
    public DTreeItemEditingSupport(final TreeViewer viewer, final TransactionalEditingDomain editingDomain, final ModelAccessor accessor, final ITreeCommandFactory treeCommandFactory,
            final AbstractDTreeEditor abstractDTreeEditor) {
        super(viewer);
        this.editingDomain = editingDomain;
        this.accessor = accessor;
        this.treeCommandFactory = treeCommandFactory;
        this.treeEditor = abstractDTreeEditor;
    }

    @Override
    protected boolean canEdit(final Object element) {

        if (element instanceof DTreeItem) {
            final DTreeItem item = (DTreeItem) element;
            boolean canEdit = false;
            IInterpreter interpreter = null;
            // If the DTreeItem to edit is associated to a canEdit expression
            // if (item.getUpdater() != null && item.getUpdater().getCanEdit()
            // != null && item.getUpdater().getCanEdit().length() > 0) {
            // interpreter = InterpreterUtil.getInterpreter(item.getTarget());
            // try {
            // // The evaluation of this expression must return true
            // canEdit = interpreter.evaluateBoolean(item.getTarget(),
            // item.getUpdater().getCanEdit());
            // } catch (final EvaluationException e) {
            // RuntimeLoggerManager.INSTANCE.error(item.getUpdater(),
            // DescriptionPackage.eINSTANCE.getTreeItemUpdater_CanEdit(), e);
            // }
            // }
            // If the Edition tool has a precondition
            if (item.getUpdater() != null && item.getUpdater().getDirectEdit() != null) {
                canEdit = true;
                if (!StringUtil.isEmpty(item.getUpdater().getDirectEdit().getPrecondition())) {
                    if (interpreter == null) {
                        interpreter = InterpreterUtil.getInterpreter(item.getTarget());
                    }
                    String precondition = item.getUpdater().getDirectEdit().getPrecondition();
                    try {
                        canEdit = interpreter.evaluateBoolean(item.getTarget(), precondition);
                    } catch (final EvaluationException e) {
                        RuntimeLoggerManager.INSTANCE.error(item.getUpdater().getDirectEdit(),
                                org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
                    }

                }
            }

            // DTreeItem also must be editable
            return canEdit && getAuthority().canEditInstance(item);
        }
        return false;
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        if (element instanceof DTreeItem) {
            DTreeItem editedItem = (DTreeItem) element;
            final boolean directEdit = editedItem.getUpdater() != null && editedItem.getUpdater().getDirectEdit() != null;
            return getBestCellEditor(editedItem.getTarget(), directEdit);
        }
        return null;
    }

    @Override
    protected Object getValue(final Object element) {
        Object result = null;
        if (element instanceof DTreeItem) {
            result = ((DTreeItem) element).getName();
        }
        return result;
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        if (element instanceof DTreeItem) {
            final DTreeItem treeItem = (DTreeItem) element;

            if (value != null) {
                final Object finalValue = value;
                if (treeItem.getUpdater() != null && treeItem.getUpdater().getDirectEdit() != null) {
                    // Specific set : we use the DirectEditTool defined on this
                    // DTreeItem to modify semantic elements
                    specificSetValue(treeItem, finalValue);
                } else {
                    // Normal set : we only change the DTreeItem name
                    standardSetValue(treeItem, finalValue);
                }
            }

        }
    }

    /**
     * Only changes the name of the given DTreeItem.
     * 
     * @param itemToSet
     *            the edited DTreeItem
     * @param value
     *            the new value for this DTreeItem
     */
    private void standardSetValue(final DTreeItem itemToSet, final Object value) {
        // We simply change the name of the given itemToSet
        final DTreeItem treeItem = itemToSet;
        if (value instanceof String) {
            getEditingDomain().getCommandStack().execute(
                    new StandardSetValueRecordingCommand(getEditingDomain(), MessageFormat.format(Messages.DTreeItemEditingSupport_directEditCommand, itemToSet.getName()), treeItem, value));
        }
    }

    /**
     * Set the value by calling the defined Edition Tool.
     * 
     * @param editedTreeItem
     *            The DTreeItem to set
     * @param value
     *            the new value
     * 
     */
    private void specificSetValue(final DTreeItem editedTreeItem, final Object value) {
        treeEditor.enablePropertiesUpdate(false);
        // We use the command Factory to create the command corresponding to the
        // Editing Tool
        final TreeItemEditionTool editTool = editedTreeItem.getUpdater().getDirectEdit();
        if (editTool.getFirstModelOperation() != null) {
            getEditingDomain().getCommandStack().execute(treeCommandFactory.buildDirectEditLabelFromTool(editedTreeItem, editTool, (String) value));
        }
        // We allow properties update so that the treeItem is correctly
        // refreshed
        treeEditor.enablePropertiesUpdate(true);
        treeEditor.forceRefreshProperties();
    }

    /**
     * Chooses the best CellEditor depending on the type of value to edit
     * 
     * @param element
     *            The current edited element
     * @param directEdit
     *            true if this cell has a direct edit tool, false otherwise
     * @return An adapted cell Editor
     */
    private CellEditor getBestCellEditor(final EObject element, final boolean directEdit) {
        final Tree tree = ((TreeViewer) getViewer()).getTree();
        final TextCellEditor textEditor = new TextCellEditor(tree) {
            /**
             * {@inheritDoc} We override the doSetFocus for clearing the
             * selection for the direct edition of the cell.
             * 
             * @see org.eclipse.jface.viewers.TextCellEditor#doSetFocus()
             */
            @Override
            protected void doSetFocus() {
                super.doSetFocus();
                if (text != null) {
                    text.clearSelection();
                }
            }
        };
        textEditor.getControl().addFocusListener(new DTreeItemEditorFocusListener(this.treeEditor, textEditor));
        return textEditor;
    }

    /**
     * Return the transactional editing domain.
     * 
     * @return the transactional editing domain
     */
    public TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * @return The permission authority
     */
    private IPermissionAuthority getAuthority() {
        return getAccessor().getPermissionAuthority();
    }

    /**
     * @return the accessor
     */
    private ModelAccessor getAccessor() {
        return accessor;
    }

    private class StandardSetValueRecordingCommand extends RecordingCommand {

        private DTreeItem treeItem;

        private Object value;

        StandardSetValueRecordingCommand(TransactionalEditingDomain domain, String label, DTreeItem treeItem, Object value) {
            super(domain, label);
            this.treeItem = treeItem;
            this.value = value;
        }

        @Override
        protected void doExecute() {
            treeItem.setName((String) value);
        }

    }
}
