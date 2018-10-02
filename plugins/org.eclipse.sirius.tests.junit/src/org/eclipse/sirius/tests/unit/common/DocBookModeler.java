/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common;

public interface DocBookModeler {

    String DOCBOOK_PLUGIN = "org.eclipse.sirius.tests.sample.docbook.design";

    /** Path to the directory containing resources. */
    String DIRECTORY_PATH = "/sample";

    /** Path to the input model's description. */
    String SEMANTIC_MODEL_FILENAME = "simple.docbook";

    String MODELER_FILENAME = "docbook.odesign";

    /** session resource filename. */
    String SESSION_MODEL_FILENAME = "docbook.aird";

    String SEMANTIC_MODEL_PATH = "/" + DOCBOOK_PLUGIN + DIRECTORY_PATH + "/" + SEMANTIC_MODEL_FILENAME;

    String SESSION_PATH = "/" + DOCBOOK_PLUGIN + DIRECTORY_PATH + "/" + SESSION_MODEL_FILENAME;

    String MODELER_PATH = "/" + DOCBOOK_PLUGIN + "/" + "model/" + MODELER_FILENAME;

    String DOCKBOOK_VIEWPOINT_NAME = "docbook1";

}
