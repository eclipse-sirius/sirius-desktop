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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

public interface EcoreModeler {

    String TEST_SEMANTIC_MODEL_FILENAME = "test.ecore"; //$NON-NLS-1$

    String TEST_SEMANTIC_MODEL_PROJECT_RELATIVE_PATH = "/data/unit/modelers/ecore/" + TEST_SEMANTIC_MODEL_FILENAME; //$NON-NLS-1$

    String TEST_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_SEMANTIC_MODEL_PROJECT_RELATIVE_PATH; //$NON-NLS-1$

    String PACKAGES_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/packages.ecore"; //$NON-NLS-1$ $NON-NLS-2$

    String ZOOM_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/design/zoom.ecore"; //$NON-NLS-1$ $NON-NLS-2$

    String MODELER_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";//$NON-NLS-1$

    String MODELER_EXTENSION_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore_extension.odesign";//$NON-NLS-1$

    String DESIGN_VIEWPOINT_NAME = "Design";//$NON-NLS-1$

    String REVIEW_VIEWPOINT_NAME = "Review";//$NON-NLS-1$

    String ENTITIES_DESC_NAME = "Entities";//$NON-NLS-1$

    String TABLES_DESC_NAME = "Classes";//$NON-NLS-1$

    String CROSS_TABLES_DESC_NAME = "Documentation";//$NON-NLS-1$

    String DOCUMENTATION_VIEWPOINT_NAME = "Documentation";//$NON-NLS-1$

    String QUALITY_VIEWPOINT_NAME = "Quality";//$NON-NLS-1$

    String HIERARCHY_DESC_NAME = "Hierarchy";

    String DEPENDENCIES_DESC_NAME = "Dependencies";

    String RELATIONS_DESC_NAME = "Relations";//$NON-NLS-1$
    
    String TAGS_DESC_NAME = "Tags";//$NON-NLS-1$

    String LAYER_DOCUMENTATION_NAME = "Documentation";//$NON-NLS-1$

    String LAYER_PACKAGE_NAME = "Package";//$NON-NLS-1$

    String TOOL_CREATION_CLASS_NAME = "Class"; //$NON-NLS-1$

}
