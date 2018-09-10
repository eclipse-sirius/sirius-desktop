/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;

import com.google.common.collect.Sets;

/**
 * Utility class to factor customizations for ContainerMapping and related.
 * 
 * @author pcdavid
 */
public final class ContainerMappingHelper {

    private ContainerMappingHelper() {
    }

    /**
     * Helper for {@link ContainerMapping#getAllNodeMappings()}. The result should be wrapped in an appropriate EList by
     * users.
     * 
     * @param self
     *            the container mapping.
     * @return the node mappings.
     */
    public static Collection<NodeMapping> getAllNodeMappings(ContainerMapping self) {
        return Sets.union(Sets.newLinkedHashSet(self.getSubNodeMappings()), Sets.newLinkedHashSet(self.getReusedNodeMappings()));
    }

    /**
     * Helper for {@link ContainerMapping#getAllContainerMappings()}. The result should be wrapped in an appropriate
     * EList by users.
     * 
     * @param self
     *            the container mapping.
     * @return the container mappings.
     */
    public static Collection<ContainerMapping> getAllContainerMappings(ContainerMapping self) {
        return Sets.union(Sets.newLinkedHashSet(self.getSubContainerMappings()), Sets.newLinkedHashSet(self.getReusedContainerMappings()));
    }

    /**
     * Implementation of {@link ContainerMapping#getAllMappings()}.
     * 
     * @param self
     *            the container mapping.
     * @return all sub mappings.
     */
    public static EList<DiagramElementMapping> getAllMappings(ContainerMapping self) {
        final BasicEList<DiagramElementMapping> allMappings = new BasicEList<DiagramElementMapping>();
        allMappings.addAll(MappingHelper.getAllContainerMappings(self));
        allMappings.addAll(MappingHelper.getAllNodeMappings(self));
        allMappings.addAll(MappingHelper.getAllBorderedNodeMappings(self));
        return new BasicEList.UnmodifiableEList<DiagramElementMapping>(allMappings.size(), allMappings.toArray());
    }

}
