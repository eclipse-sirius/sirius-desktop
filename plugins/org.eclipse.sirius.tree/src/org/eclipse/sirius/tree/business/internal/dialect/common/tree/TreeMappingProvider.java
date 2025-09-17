/*******************************************************************************
 * Copyright (c) 2015, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tree.business.internal.dialect.common.tree;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.synchronizer.Mapping;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemMapping;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * TreeMappingProvider
 * 
 * @author cbrun
 */
class TreeMappingProvider {

    /**
     * A mapping between {@link TreeItemMapping} and {@link RTreeItemMapping}.
     */
    protected BiMap<EObject, Mapping> mappingToRMapping = HashBiMap.create();

    private SemanticPartitionProvider semProvider;

    /**
     * Default constructor.
     * 
     * @param semProvider
     *            a {@link SemanticPartitionProvider}
     */
    TreeMappingProvider(SemanticPartitionProvider semProvider) {
        this.semProvider = semProvider;
    }

    public RTreeItemMapping getOrCreate(TreeItemMapping mapping) {
        if (mappingToRMapping.get(mapping) != null) {
            return (RTreeItemMapping) mappingToRMapping.get(mapping);
        }
        RTreeItemMapping newOne = new RTreeItemMapping(mapping, this);
        mappingToRMapping.put(mapping, newOne);
        return newOne;
    }

    public RTreeMapping getOrCreate(TreeDescription mapping) {
        if (mappingToRMapping.get(mapping) != null) {
            return (RTreeMapping) mappingToRMapping.get(mapping);
        }
        RTreeMapping newOne = new RTreeMapping(mapping, this);
        mappingToRMapping.put(mapping, newOne);
        return newOne;
    }

    public SemanticPartitionProvider getSemanticProvider() {
        return semProvider;
    }

}
