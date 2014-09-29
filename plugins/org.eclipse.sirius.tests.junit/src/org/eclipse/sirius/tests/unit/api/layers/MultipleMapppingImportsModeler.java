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
package org.eclipse.sirius.tests.unit.api.layers;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

public interface MultipleMapppingImportsModeler {

    String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layers/MultipleMappingImport.ecore";
    
    

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layers/MultipleMappingImport.odesign";

    String MULTIPLE_MAPPING_IMPORT_VIEWPOINT_NAME = "MultipleMappingImport";

    String PRECONDITION_CHANGE_NODE_DIAGRAM = "PC_NodeImportDiagram";

    String PRECONDITION_CHANGE_CONTAINER_DIAGRAM = "PC_ContainerMappingImportDiagram";

    String DOMAINCLASS_CHANGE_NODE_DIAGRAM = "DC_NodeImportDiagram";

    String DOMAINCLASS_CHANGE_CONTAINER_DIAGRAM = "DC_ContainerMappingImportDiagram";

    String PRECONDITION_CHANGE_NODE_DIAGRAM_WITH_HIDESUBMAPPING = "PC_NodeImportDiagram_WithHideSubMapping";

    String PRECONDITION_CHANGE_CONTAINER_DIAGRAM_WITH_HIDESUBMAPPING = "PC_ContainerMappingImportDiagram_WithHideSubMapping";

    String DOMAINCLASS_CHANGE_NODE_DIAGRAM_WITH_HIDESUBMAPPING = "DC_NodeImportDiagram_WithHideSubMapping";

    String DOMAINCLASS_CHANGE_CONTAINER_DIAGRAM_WITH_HIDESUBMAPPING = "DC_ContainerMappingImportDiagram_WithHideSubMapping";

    String DOMAINCLASS_CHANGE_SUBNODE_DIAGRAM = "DC_NodeMappingImportInContainerMappingDiagram";

    String DOMAINCLASS_CHANGE_SUBNODE_DIAGRAM_WITH_HIDESUBMAPPING = "DC_NodeMappingImportInContainerMappingDiagram_WithHideSubMapping";

    String PRECONDITION_CHANGE_SUBNODE_DIAGRAM = "PC_NodeMappingImportInContainerMappingDiagram";

    String PRECONDITION_CHANGE_SUBNODE_DIAGRAM_WITH_HIDESUBMAPPING = "PC_NodeMappingImportInContainerMappingDiagram_WithHideSubMapping";

    String ALPHA_LAYER = "alpha";

    String ALPHA_NODE = "AlphaNode";

    String ALPHA_CONTAINER = "AlphaContainer";

    String ALPHA_SEMANTIC_CONTAINER = "ALPHA_CONTAINER";

    String ALPHA_SEMANTIC = "ALPHA";

    String BETA_LAYER = "beta";

    String BETA_NODE = "BetaNode";

    String BETA_CONTAINER = "BetaContainer";

    String BETA_SEMANTIC_CONTAINER = "BETA_CONTAINER";

    String BETA_SEMANTIC = "BETA";

    String DEFAULT_LAYER = "default";

    String DEFAULT_NODE = "DefaultNode";

    String DEFAULT_CONTAINER = "DefaultContainer";

    String DEFAULT_SEMANTIC = "RootClass";

    String PRECONDITION_CHANGE_NODE_TREE = "PC_NodeMappingImportTree";

    String TREE_LAYER = "tree";

    String F_NODE = "Class node mapping";
    
    String SEMANTIC_MODEL_PATH2 = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layers/MultipleMappingImport2.ecore";

    String F = "F";

    String F1 = "F1";

    String F2 = "F2";

    String F11 = "F11";

    String F12 = "F12";

    String F21 = "F21";

    String F22 = "F22";

}
