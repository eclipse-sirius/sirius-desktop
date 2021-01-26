/*******************************************************************************
 * Copyright (c) 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.api.editor;

import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.CellEditorTool;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.swt.widgets.Tree;

/**
 * This interface must be implemented by classes used for {@link CellEditorTool} tool of Sirius {@link DTable}.
 * 
 * @author lredor
 *
 */
public interface ITableCellEditorFactory {
    /**
     * This method is called by Sirius when a cell needs to be edit (double click on the cell by end-user).<BR/>
     * The result returned by the {@link CellEditor} through method {@link CellEditor#getValue()} is the set into
     * variable {@link IInterpreterSiriusTableVariables#CELL_EDITOR_RESULT} and can be used in operations of the
     * tool.<BR/>
     * If the returned CellEditor is null, the standard behavior will be used to edit the cell and the returned value
     * will be set into variable {@link IInterpreterSiriusTableVariables#CELL_EDITOR_RESULT} to execute the operations
     * of the tool.
     * 
     * @param tree
     *            The composite to used as parent of the CellEditor to returned.
     * @param parameters
     *            Map with the values associated to each variable visible under the CellEditorTool in the VSM. The key
     *            is one of the following constant:
     *            <UL>
     *            <LI>{@link IInterpreterSiriusVariables.ELEMENT}: The semantic currently edited element
     *            (<code>target</code> attribute of the current cell).</LI>
     *            <LI>{@link IInterpreterSiriusTableVariables.TABLE}: The current <code>DTable</code></LI>
     *            <LI>{@link IInterpreterSiriusTableVariables#LINE}: The <code>DLine</code> of the current
     *            <code>DCell</code>.</LI>
     *            <LI>{@link IInterpreterSiriusTableVariables#LINE_SEMANTIC}: The semantic element corresponding to the
     *            line (<code>target</code> attribute of the current line).</LI>
     *            <LI>{@link IInterpreterSiriusVariables.ROOT}: The semantic root element of the table.</LI>
     *            </UL>
     * @return the expected {@link CellEditor} according to the parameters.
     */
    CellEditor getCellEditor(Tree tree, Map<String, Object> parameters);
}
