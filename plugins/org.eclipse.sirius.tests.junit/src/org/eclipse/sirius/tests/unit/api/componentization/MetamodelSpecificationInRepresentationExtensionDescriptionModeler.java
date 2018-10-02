/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.componentization;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

public interface MetamodelSpecificationInRepresentationExtensionDescriptionModeler {

    String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/componentization/trac2085.interactions";
    
    String ANOTHER_SEMANTIC_MODEL_PATH =  "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/componentization/trac2085.ecore";

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/componentization/trac2085.odesign";

    String INTERACTIONS_VIEWPOINT_NAME = "Interactions";

    String ECORE_VIEWPOINT_NAME = "Ecore";

    String PARTICIPANTS_DESC_NAME = "Participants";

}
