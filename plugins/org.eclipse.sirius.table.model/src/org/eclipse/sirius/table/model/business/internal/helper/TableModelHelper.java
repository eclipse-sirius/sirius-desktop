/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.table.model.business.internal.helper;

import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;

/**
 * Utility methods to handle Table models.
 * 
 * @author cbrun
 * 
 */
public final class TableModelHelper {

    private TableModelHelper() {

    }

    /**
     * Get the variable with the name in this tool.
     * 
     * @param tool
     *            The tool in which search the variable
     * @param name
     *            The variable name
     * @return The corresponding variable or null if not found
     */
    public static TableVariable getVariable(final TableTool tool, final String name) {
        for (final TableVariable tableVariable : tool.getVariables()) {
            if (name != null && name.equals(tableVariable.getName())) {
                return tableVariable;
            }
        }
        return null;
    }
}
