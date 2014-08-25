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

public interface MetamodelSpecificationInRepresentationExtensionDescriptionModeler {

    String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/componentization/trac2085.interactions";
    
    String ANOTHER_SEMANTIC_MODEL_PATH =  "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/componentization/trac2085.ecore";

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/componentization/trac2085.odesign";

    String INTERACTIONS_VIEWPOINT_NAME = "Interactions";

    String ECORE_VIEWPOINT_NAME = "Ecore";

    String PARTICIPANTS_DESC_NAME = "Participants";

}
