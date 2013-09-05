/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    String TREE = "tree";

    /**
     * the tree item element.
     */
    String TREE_ITEM = "treeitem";

    /**
     * the line semantic element.
     */
    String TREE_ITEM_SEMANTIC = "treeitemSemantic";

    /**
     * The preceding siblings in a Drag and Drop operation.
     */
    String PRECEDING_SIBLINGS = "precedingSiblings";

}
