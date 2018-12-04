/*******************************************************************************
 * Copyright (c) 2011, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.api.tools;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

public interface DnDModel {

    String PATH = "/data/tree/unit/dnd/vp926/";

    String SEMANTIC_MODEL_FILENAME_1 = "company1.xmi";

    String SEMANTIC_MODEL_FILENAME_2 = "company2.xmi";

    String SEMANTIC_META_MODEL_FILENAME = "vp926.ecore";

    String SESSION_MODEL_FILENAME = "vp926.aird";

    String MODELER_MODEL_FILENAME = "vp926.odesign";

    String REPRESENTATION_NAME = "Tree With DnD";

    String SEMANTIC_META_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_META_MODEL_FILENAME;

    String SEMANTIC_MODEL_1_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_MODEL_FILENAME_1;

    String SEMANTIC_MODEL_2_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_MODEL_FILENAME_2;

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_MODEL_FILENAME;

    String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_MODEL_FILENAME;
}
