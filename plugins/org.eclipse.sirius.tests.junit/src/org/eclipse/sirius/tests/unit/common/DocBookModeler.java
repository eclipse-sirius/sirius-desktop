/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
