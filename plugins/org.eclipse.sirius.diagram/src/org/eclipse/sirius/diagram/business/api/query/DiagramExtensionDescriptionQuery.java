/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.query;

import java.util.Collection;

import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.model.business.internal.helper.ContentHelper;

/**
 * A class aggregating all the queries (read-only!) having a DiagramDescription as a starting point.
 * 
 * @author jdupont
 * 
 */
public class DiagramExtensionDescriptionQuery {

    private DiagramExtensionDescription extensionDescription;

    /**
     * Create a new query.
     * 
     * @param extensionDescription
     *            the element to query.
     */
    public DiagramExtensionDescriptionQuery(DiagramExtensionDescription extensionDescription) {
        this.extensionDescription = extensionDescription;
    }

    /**
     * Get all Container mapping of Diagram extension description.
     * 
     * @return all container mapping of Diagram extension description
     */
    public Collection<ContainerMapping> getAllContainerMappings() {
        return ContentHelper.getAllContainerMappings(extensionDescription);
    }

    /**
     * Get all Node mapping of Diagram extension description.
     * 
     * @return all node mapping of Diagram extension description
     */
    public Collection<NodeMapping> getAllNodeMappings() {
        return ContentHelper.getAllNodeMappings(extensionDescription);
    }

}
