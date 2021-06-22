/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.business.internal.metamodel.helper;

import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.description.TreeVariable;

/**
 * Utility methods to handle Tree models.
 * 
 * @author nlepine
 * 
 */
public final class TreeModelHelper {

    private TreeModelHelper() {

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
    public static TreeVariable getVariable(final TreeItemTool tool, final String name) {
        for (final TreeVariable tableVariable : tool.getVariables()) {
            if (name != null && name.equals(tableVariable.getName())) {
                return tableVariable;
            }
        }
        return null;
    }

}
