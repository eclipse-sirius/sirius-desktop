/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.componentization;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

public interface DiagramExtensionDescriptionModeler {

    String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/componentization/example.ecore";

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/componentization/description.odesign";

    String MODELER_EXTENSION1_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/componentization/extensionUn.odesign";

    String MODELER_EXTENSION2_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/componentization/extensionDeux.odesign";

    String DESCRIPTION_VIEWPOINT_NAME = "Test";

    String DESCRIPTION_EXTENSION_UN_VIEWPOINT_NAME = "Extension 1";

    String DESCRIPTION_EXTENSION_DEUX_VIEWPOINT_NAME = "Extension 2";

    String ECORE_VIEWPOINT_NAME = "Ecore";

    String DIAGRAM_DESCRIPTION = "DiagramDescription";

    String EXTENSION_UN = "Extension 1 Diagram";

    String EXTENSION_DEUX = "Extension 2 Diagram";

}
