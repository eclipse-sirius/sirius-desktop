/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.table.business.api.helper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;

/**
 * Utility methods to handle Table variables.
 * 
 * @author nperansin
 */
public final class TableVariablesHelper {
    
    private TableVariablesHelper() {
        // No intance
    }
    
    
    /**
     * Returns applicable variables for a table expressions and operations.
     * <p>
     * According to the element type, the proper variables are provided.
     * </p>
     * 
     * @param decorator current context
     * @return variables
     */
    public static Map<String, EObject> getVariables(DSemanticDecorator decorator) {
        Map<String, EObject> result = Collections.emptyMap();
        if (decorator instanceof DLine) {
            result = getVariables((DLine) decorator);
        } else if (decorator instanceof DTargetColumn) {
            result = getVariables((DTargetColumn) decorator);
        } else if (decorator instanceof DTable) {
            result = getVariables((DTable) decorator);
        } else if (decorator instanceof DCell) {
            result = getVariables((DCell) decorator);
        }
        return result;
    }
    
    /**
     * Adapts a map of variable name to value into a map variable to value.
     * 
     * @param tool with variables
     * @param values to adapt
     * @return map of variable to value.
     */
    public static Map<AbstractVariable, Object> getTableVariables(TableTool tool, Map<String, ?> values) {
        Map<AbstractVariable, Object> result = new HashMap<>();
        values.forEach((key, value) -> {
            TableVariable variable = TableHelper.getVariable(tool, key);
            if (variable != null) {
                result.put(variable, value);
            }
        });
        return result;
    }
    
    
    /**
     * Creates a map for context to evaluate candidate of lines.
     * 
     * @param container of lines
     * @return map of values
     */
    public static Map<String, EObject> getVariablesForCandidates(LineContainer container) {
        DTable table = TableHelper.getTable(container);
        Map<String, EObject> result = new HashMap<>();

        result.put(IInterpreterSiriusVariables.CONTAINER_VIEW, container);
        result.put(IInterpreterSiriusVariables.CONTAINER, container.getTarget());

        result.put(IInterpreterSiriusVariables.TABLE, table);
        result.put(IInterpreterSiriusVariables.ROOT, table.getTarget());
        return result;
    }
    
    /**
     * Returns applicable variables for line expressions and operations.
     * 
     * @param line current context
     * @return variables
     */
    private static Map<String, EObject> getVariables(DLine line) {
        Map<String, EObject> result = new HashMap<>();
        DTable table = TableHelper.getTable(line);

        
        result.put(IInterpreterSiriusVariables.VIEW, line);
        result.put(IInterpreterSiriusVariables.ELEMENT, line.getTarget());

        result.put(IInterpreterSiriusVariables.CONTAINER_VIEW, line.getContainer());
        result.put(IInterpreterSiriusVariables.CONTAINER, line.getContainer().getTarget());

        result.put(IInterpreterSiriusVariables.TABLE, table);
        result.put(IInterpreterSiriusVariables.ROOT, table.getTarget());
        
        return result;
    }
    
    /**
     * Returns applicable variables for expressions and operations at.
     * 
     * @param table current context
     * @return variables
     */
    private static Map<String, EObject> getVariables(DTable table) {
        Map<String, EObject> result = new HashMap<>();

        result.put(IInterpreterSiriusVariables.VIEW, table);
        result.put(IInterpreterSiriusVariables.ELEMENT, table.getTarget());

        result.put(IInterpreterSiriusVariables.TABLE, table);
        result.put(IInterpreterSiriusVariables.ROOT, table.getTarget());
        
        result.put(IInterpreterSiriusVariables.CONTAINER_VIEW, table);
        result.put(IInterpreterSiriusVariables.CONTAINER, table.getTarget());

        return result;
    }
    
    /**
     * Returns applicable variables for line expressions and operations.
     * 
     * @param column current context
     * @return variables
     */
    private static Map<String, EObject> getVariables(DTargetColumn column) {
        Map<String, EObject> result = new HashMap<>();
        DTable table = TableHelper.getTable(column);

        result.put(IInterpreterSiriusVariables.TABLE, table);
        result.put(IInterpreterSiriusVariables.ROOT, table.getTarget());
        
        result.put(IInterpreterSiriusVariables.VIEW, column);
        result.put(IInterpreterSiriusVariables.ELEMENT, column.getTarget());
        
        result.put(IInterpreterSiriusVariables.CONTAINER_VIEW, table);
        result.put(IInterpreterSiriusVariables.CONTAINER, table.getTarget());

        return result;
    }
    
    
    /**
     * Returns applicable variables for cell expressions and operations.
     * 
     * @param cell current context
     * @return variables
     */
    public static Map<String, EObject> getVariables(DLine line, final DColumn column) {
        Map<String, EObject> result = new HashMap<>();
        DTable table = TableHelper.getTable(line);

        result.put(IInterpreterSiriusVariables.TABLE, table);
        result.put(IInterpreterSiriusVariables.ROOT, table.getTarget());
        
        result.put(IInterpreterSiriusVariables.CONTAINER_VIEW, line);
        result.put(IInterpreterSiriusVariables.CONTAINER, line.getTarget());

        result.put(IInterpreterSiriusTableVariables.LINE, line);
        result.put(IInterpreterSiriusTableVariables.LINE_SEMANTIC, line.getTarget());

        result.put(IInterpreterSiriusTableVariables.COLUMN, column);
        if (column instanceof DTargetColumn) {
            result.put(IInterpreterSiriusTableVariables.COLUMN_SEMANTIC, column.getTarget());
        }
        
        return result;
    }
    
    /**
     * Returns applicable variables for cell expressions and operations.
     * 
     * @param cell current context
     * @return variables
     */
    public static Map<String, EObject> getVariables(DCell cell) {
        Map<String, EObject> result = getVariables(cell.getLine(), cell.getColumn());
        result.put(IInterpreterSiriusVariables.VIEW, cell);
        result.put(IInterpreterSiriusVariables.ELEMENT, cell.getTarget());        
        return result;
    }

}
