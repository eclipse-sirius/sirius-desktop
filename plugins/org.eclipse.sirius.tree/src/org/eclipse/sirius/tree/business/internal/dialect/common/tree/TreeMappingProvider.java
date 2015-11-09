/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
