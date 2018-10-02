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
package org.eclipse.sirius.tests.unit.diagram.tools.data;

import org.eclipse.sirius.diagram.description.NodeMappingImport;

public class NodeMappingImportClassNodeSpecialization {

    private NodeMappingImport val;

    public NodeMappingImportClassNodeSpecialization(NodeMappingImport val) {
        this.val = val;
    }

    public NodeMappingImport object() {
        return this.val;
    }
}
