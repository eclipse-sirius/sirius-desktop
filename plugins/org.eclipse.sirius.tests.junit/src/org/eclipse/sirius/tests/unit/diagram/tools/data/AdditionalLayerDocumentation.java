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
package org.eclipse.sirius.tests.unit.diagram.tools.data;

import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.tool.ToolSection;

public class AdditionalLayerDocumentation {

    private AdditionalLayer val;

    public AdditionalLayerDocumentation(AdditionalLayer val) {
        this.val = val;
    }

    public NodeMappingImportClassNodeSpecialization classnodespecialization() {
        return new NodeMappingImportClassNodeSpecialization((NodeMappingImport) val.getNodeMappings().get(0));
    }

    public NodeMappingImportOtherRootNodeSpecialization otherrootnodespecialization() {
        return new NodeMappingImportOtherRootNodeSpecialization((NodeMappingImport) val.getNodeMappings().get(1));
    }

    public EdgeMappingNodeSpecToOtherSpec nodespectootherspec() {
        return new EdgeMappingNodeSpecToOtherSpec((EdgeMapping) val.getEdgeMappings().get(0));
    }

    public EdgeMappingNodeSpecToOtherNonSpecialized nodespectoothernonspecialized() {
        return new EdgeMappingNodeSpecToOtherNonSpecialized((EdgeMapping) val.getEdgeMappings().get(1));
    }

    public ContainerMappingImportClassContainerSpecialization classcontainerspecialization() {
        return new ContainerMappingImportClassContainerSpecialization((ContainerMappingImport) val.getContainerMappings().get(0));
    }

    public ToolSectionTools tools() {
        return new ToolSectionTools((ToolSection) val.getToolSections().get(0));
    }

    public EdgeMappingImportClass2OtherSpecialization class2otherspecialization() {
        return new EdgeMappingImportClass2OtherSpecialization((EdgeMappingImport) val.getEdgeMappingImports().get(0));
    }

    public AdditionalLayer object() {
        return this.val;
    }
}
