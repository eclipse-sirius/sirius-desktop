/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Tree;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableTreeViewer;
import org.eclipse.sirius.table.ui.tools.internal.editor.command.CreateCellRecordingCommand;
import org.eclipse.sirius.table.ui.tools.internal.editor.command.SetCellValueRecordingCommand;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;

/**
 * Support for the cells editing of a Cross DTable (with {@link DTarget}).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DTargetColumnEditingSupport extends EditingSupport {

    // The column of this EditingSupport
    private DTargetColumn targetColumn;

    private TransactionalEditingDomain editingDomain;

    private ModelAccessor accessor;

    private ITableCommandFactory tableCommandFactory;

    private AbstractDTableEditor tableEditor;

    /**
     * Constructor.
     * 
     * @param viewer
     *            The columnViewer for this editingSupport
     * @param targetColumn
     *            The targetColumn of this EditingSupport
     * @param editingDomain
     *            The transactional editing domain of this viewer
     * @param accessor
     *            The accessor for the model
     * @param tableCommandFactory
     *            The EMF command factory
     * @param tableEditor
     *            The associated editor
     */
    public DTargetColumnEditingSupport(final ColumnViewer viewer, final DTargetColumn targetColumn, final TransactionalEditingDomain editingDomain, final ModelAccessor accessor,
            final ITableCommandFactory tableCommandFactory, final AbstractDTableEditor tableEditor) {
        super(viewer);
        this.targetColumn = targetColumn;
        this.editingDomain = editingDomain;
        this.accessor = accessor;
        this.tableCommandFactory = tableCommandFactory;
        this.tableEditor = tableEditor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(final Object element) {
        boolean canEdit = false;
        if (element instanceof DLine) {
            final DLine line = (DLine) element;
            final Option<DCell> optionalCell = TableHelper.getCell(line, getTargetColumn());
            if (optionalCell.some()) {
                canEdit = getAuthority().canEditInstance(optionalCell.get().getTarget()) && TableHelper.canEditCrossTableCell(optionalCell.get());
            } else {
                canEdit = getAuthority().canEditInstance(line) && getAuthority().canEditInstance(getTargetColumn()) && TableHelper.canEditCrossTableCell(line, getTargetColumn());
            }
        }
        return canEdit;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(final Object element) {
        if (element instanceof DLine) {
            return getBestCellEditor(((DLine) element).getTarget());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected Object getValue(final Object element) {
        Object result = null;
        if (element instanceof DLine) {
            final DLine line = (DLine) element;
            final Option<DCell> optionalEditedCell = TableHelper.getCell(line, targetColumn);
            if (optionalEditedCell.some()) {
                result = optionalEditedCell.get().getLabel();
            }
            if (result == null) {
                result = "";
            }
        }
        return result;
    }

    /**
     * We set the value only if the value is different.<BR>
     * We disabled the update of the properties view during the setting (because
     * there it causes a refresh) because it's very slow. We force a refresh of
     * the properties view after the setting. {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    protected void setValue(final Object element, final Object value) {
        if (element instanceof DLine && value instanceof String) {
            final DLine line = (DLine) element;
            final Option<DCell> optionnalEditedCell = TableHelper.getCell(line, targetColumn);

            // To increase performance, we do nothing if the new value is the
            // same as the old one
            if (optionnalEditedCell.some()) {
                if (!value.equals(optionnalEditedCell.get().getLabel())) {
                    tableEditor.enablePropertiesUpdate(false);
                    getEditingDomain().getCommandStack().execute(new SetCellValueRecordingCommand(getEditingDomain(), "Set cell content", tableCommandFactory, optionnalEditedCell.get(), value));
                    tableEditor.enablePropertiesUpdate(true);
                    tableEditor.forceRefreshProperties();
                }
            } else if (value != null) {
                tableEditor.enablePropertiesUpdate(false);
                getEditingDomain().getCommandStack().execute(new CreateCellRecordingCommand(getEditingDomain(), "Set cell content", tableCommandFactory, line, targetColumn, value));
                tableEditor.enablePropertiesUpdate(true);
                tableEditor.forceRefreshProperties();
            }
        }
        // This update is not needed because there is a full refresh done before
        // (see eventually to improve perf)
        // getViewer().update(element, null);
    }

    /**
     * Chooses the best CellEditor depending on the type of value to edit
     * 
     * @param element
     *            The current edited element
     * @return An adapted cell Editor
     */
    private CellEditor getBestCellEditor(final EObject element) {
        final Tree tree = ((TreeViewer) getViewer()).getTree();
        final TextCellEditor textEditor = new TextCellEditor(tree) {
            /**
             * {@inheritDoc} We override the doSetFocus for clearing the
             * selection for the direct edition of the cell (Trac #1122).
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
        textEditor.getControl().addFocusListener(new DTableCellEditorFocusListener(tableEditor, textEditor));
        return textEditor;
    }

    /**
     * The targetColumn associated with this editingSupport.
     * 
     * @return the dTargetColumn
     */
    protected DTargetColumn getTargetColumn() {
        return targetColumn;
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#initializeCellEditorValue(org.eclipse.jface.viewers.CellEditor,
     *      org.eclipse.jface.viewers.ViewerCell)
     */
    @Override
    protected void initializeCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
        if (((DTableTreeViewer) getViewer()).getFirstEditionCharacter() != null) {
            cellEditor.setValue(((DTableTreeViewer) getViewer()).getFirstEditionCharacter().toString());
        } else {
            super.initializeCellEditorValue(cellEditor, cell);
        }
    }
}
