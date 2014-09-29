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
package org.eclipse.sirius.tests.unit.api.mappings;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

public interface MappingsReuseTestModeler {

    String TEST_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/Mappings_reuse.ecore";
    
    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/Mappings_reuse.odesign";

    String DEFAULT_VIEWPOINT_NAME = "Mapping Reuse Tests";

    String REUSE_NODE_MAPPING_DESC_NAME = "Reuse node mapping";

    String REUSE_CONTAINER_MAPPING_DESC_NAME = "Reuse container mapping";

    String REUSE_NODE_MAPPING_IN_CONTAINER_MAPPING_DESC_NAME = "Reuse node mapping in container mapping";

    String REUSE_BORDER_NODE_MAPPING_IN_CONTAINER_MAPPING_DESC_NAME = "Reuse border node mapping in container mapping";

    String REUSE_BORDER_NODE_MAPPING_IN_CONTAINER_MAPPING_IMPORT_DESC_NAME = "Reuse border node mapping in container mapping import";

    String COMMON_NODE_MAPPING_ON_ECLASS = "Common node mapping - EClass";

    String COMMON_NODE_MAPPING_ON_ESTRUCTURAL_FEATURE = "Common node mapping - EStructuralFeature";

    String COMMON_CONTAINER_MAPPING_ON_ECLASS = "Common container mapping - EClass";
}
