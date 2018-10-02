/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.tools.api.interpreter;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;

/**
 * Sirius variables strings (specific for table).
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public interface IInterpreterSiriusTreeVariables extends IInterpreterSiriusVariables {

    /** "diagram". */
    String TREE = "tree"; //$NON-NLS-1$

    /**
     * the tree item element.
     */
    String TREE_ITEM = "treeitem"; //$NON-NLS-1$

    /**
     * the line semantic element.
     */
    String TREE_ITEM_SEMANTIC = "treeitemSemantic"; //$NON-NLS-1$

    /**
     * The preceding siblings in a Drag and Drop operation.
     */
    String PRECEDING_SIBLINGS = "precedingSiblings"; //$NON-NLS-1$

}
