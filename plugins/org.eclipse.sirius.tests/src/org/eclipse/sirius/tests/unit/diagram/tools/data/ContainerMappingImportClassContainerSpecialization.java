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

import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.NodeMappingImport;

@SuppressWarnings("hiding")
public class ContainerMappingImportClassContainerSpecialization {

    private ContainerMappingImport val;

    public ContainerMappingImportClassContainerSpecialization(ContainerMappingImport val) {
        this.val = val;
    }

    public NodeMappingImportOtherNodeSpecialization othernodespecialization() {
        return new NodeMappingImportOtherNodeSpecialization((NodeMappingImport) val.getSubNodeMappings().get(0));
    }

    public class NodeMappingImportOtherNodeSpecialization {

        private NodeMappingImport val;

        public NodeMappingImportOtherNodeSpecialization(NodeMappingImport val) {
            this.val = val;
        }

        public NodeMappingImport object() {
            return this.val;
        }
    }

    public ContainerMappingImport object() {
        return this.val;
    }
}
