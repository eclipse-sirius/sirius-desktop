/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.business.api.helper.TableToolHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableTreeViewer;
import org.eclipse.swt.widgets.Tree;

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
    public DTargetColumnEditingSupport(final DTableTreeViewer viewer, final DTargetColumn targetColumn, final TransactionalEditingDomain editingDomain, final ModelAccessor accessor,
            final ITableCommandFactory tableCommandFactory, final AbstractDTableEditor tableEditor) {
        super(viewer);
        this.targetColumn = targetColumn;
        this.editingDomain = editingDomain;
        this.accessor = accessor;
        this.tableCommandFactory = tableCommandFactory;
        this.tableEditor = tableEditor;
    }
    
    public DTableTreeViewer getViewer() {
        return (DTableTreeViewer) super.getViewer();
    }

    @Override
    protected boolean canEdit(final Object element) {
        return element instanceof DLine // table selection is always line. See DTableContentProvider class.
                && new TableToolHelper(accessor).canEdit((DLine) element, getTargetColumn());
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        // XXX Improvements: this method should be replaced by:
        // org.eclipse.sirius.table.ui.tools.internal.editor.provider
        //  .DFeatureColumnEditingSupport#getCellEditor(Object, DCell, CellUpdater)

        // Using TableHelper.getCell, retrieve the IntersectionMapping
        // when no intersection, search for a candidate with creationTool.
        
        if (element instanceof DLine) {
            return getBestCellEditor(((DLine) element).getTarget());
        }
        return null;
    }

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
                result = ""; //$NON-NLS-1$
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
            final Option<DCell> optionalEditedCell = TableHelper.getCell(line, targetColumn);

            Command command = null;
            if (!optionalEditedCell.some()) {
                command = tableCommandFactory.buildCreateCellFromTool(line, targetColumn, value);
            } else if (!value.equals(optionalEditedCell.get().getLabel())) {
                // To increase performance, we do nothing if the new value is the
                // same as the old one
                command = tableCommandFactory.buildSetCellValueFromTool(optionalEditedCell.get(), value);
            }
            
            if (command != null) {
                tableEditor.enablePropertiesUpdate(false);
                if (command.canExecute()) {
                    getEditingDomain().getCommandStack().execute(command);
                }
                tableEditor.enablePropertiesUpdate(true);
                tableEditor.forceRefreshProperties();
            }
            
        }
    }

    /**
     * Chooses the best CellEditor depending on the type of value to edit
     * 
     * @param element
     *            The current edited element
     * @return An adapted cell Editor
     */
    private CellEditor getBestCellEditor(final EObject element) {
        final Tree tree = getViewer().getTree();
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

    @Override
    protected void initializeCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
        if (getViewer().getFirstEditionCharacter() != null) {
            cellEditor.setValue(getViewer().getFirstEditionCharacter().toString());
        } else {
            super.initializeCellEditorValue(cellEditor, cell);
        }
    }
}
