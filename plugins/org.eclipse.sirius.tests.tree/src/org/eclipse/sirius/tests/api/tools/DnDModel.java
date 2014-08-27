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
package org.eclipse.sirius.tests.api.tools;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

public interface DnDModel {

    public static final String PATH = "/data/tree/unit/dnd/vp926/";

    public static final String SEMANTIC_MODEL_FILENAME_1 = "company1.xmi";

    public static final String SEMANTIC_MODEL_FILENAME_2 = "company2.xmi";

    public static final String SEMANTIC_META_MODEL_FILENAME = "vp926.ecore";

    public static final String SESSION_MODEL_FILENAME = "vp926.aird";

    public static final String MODELER_MODEL_FILENAME = "vp926.odesign";

    public static final String REPRESENTATION_NAME = "Tree With DnD";

    public static final String SEMANTIC_META_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_META_MODEL_FILENAME;

    public static final String SEMANTIC_MODEL_1_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_MODEL_FILENAME_1;

    public static final String SEMANTIC_MODEL_2_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_MODEL_FILENAME_2;

    public static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_MODEL_FILENAME;

    public static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_MODEL_FILENAME;
}
