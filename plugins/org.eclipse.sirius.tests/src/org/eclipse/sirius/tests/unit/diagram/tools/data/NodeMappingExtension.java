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

import org.eclipse.sirius.diagram.description.NodeMapping;

public class NodeMappingExtension {

    private NodeMapping val;

    public NodeMappingExtension(NodeMapping val) {
        this.val = val;
    }

    public NodeMapping object() {
        return this.val;
    }
}
