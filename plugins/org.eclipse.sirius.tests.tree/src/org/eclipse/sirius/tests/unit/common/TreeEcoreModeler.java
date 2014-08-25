/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Defines all location elements for tree test.
 * 
 * @author jdupont
 */
public interface TreeEcoreModeler {

    /** The Sirius name of the tree.odesign. */
    String TREE_VIEWPOINT_NAME = "TreeSirius";

    /**
     * Tree description id.
     */
    String TREE_DESCRIPTION_ID = "Tree";

    /**
     * The path for resources test.
     */
    String PATH = "/data/tree/unit/tools/";

    /**
     * Semantic model path.
     */
    String SEMANTIC_RESOURCE_FILENAME = "tree.ecore";

    /**
     * Sirius specific model path.
     */
    String MODELER_RESOURCE_FILENAME = "tree.odesign";

    /**
     * Session name.
     */
    String SESSION_RESOURCE_FILENAME = "tree.aird";

    final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_RESOURCE_FILENAME;

    final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_RESOURCE_FILENAME;

    final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_FILENAME;

}
