/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.quickoutline;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.IQuickOutlineCallback;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * A table specific {@link IQuickOutlineCallback}.
 * 
 * @author ymortier
 */
public class TableOutlineCallback implements IQuickOutlineCallback {

    private DTableEditor editorPart;

    /**
     * Default constructor.
     * 
     * @param tableEditor
     *            a {@link DTableEditor}
     */
    public TableOutlineCallback(DTableEditor tableEditor) {
        this.editorPart = tableEditor;
    }

    @Override
    public void select(Object selectedElement) {
        if (editorPart.getControl() instanceof Tree && selectedElement instanceof DColumn) {
            Tree tree = (Tree) editorPart.getControl();

            for (TreeColumn treeColumn : tree.getColumns()) {
                if (treeColumn.getData(DTableViewerManager.TABLE_COLUMN_DATA) == selectedElement) {
                    tree.showColumn(treeColumn);
                }
            }

        } else if (selectedElement != null) {
            // Just select the line :)
            ISelection selection = new StructuredSelection(selectedElement);
            editorPart.getEditorSite().getSelectionProvider().setSelection(selection);
        }
    }

}
