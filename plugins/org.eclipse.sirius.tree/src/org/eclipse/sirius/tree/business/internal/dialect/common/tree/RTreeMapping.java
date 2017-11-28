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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.synchronizer.AutomaticCreator;
import org.eclipse.sirius.synchronizer.Mapping;
import org.eclipse.sirius.synchronizer.SemanticPartition;
import org.eclipse.sirius.synchronizer.SemanticPartitions;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemMapping;

/**
 * A {@link Mapping}
 * 
 * @author cbrun
 */
class RTreeMapping implements Mapping {

    private TreeDescription treeDescription;

    private TreeMappingProvider provider;

    private SemanticPartition semPartition;

    /**
     * Default constructor.
     * 
     * @param description
     *            a {@link TreeDescription}
     * @param provider
     *            a {@link TreeMappingProvider}
     */
    RTreeMapping(TreeDescription description, TreeMappingProvider provider) {
        this.treeDescription = description;
        this.provider = provider;
        this.semPartition = SemanticPartitions.eAllContents(description.getDomainClass());
    }

    @Override
    public Option<Mapping> getSuper() {
        return Options.newNone();
    }

    @Override
    public SemanticPartition getSemanticPartition() {
        return semPartition;
    }

    public List<Mapping> getChildMappings() {
        List<Mapping> result = new ArrayList<>();
        for (TreeItemMapping mapping : treeDescription.getSubItemMappings()) {
            result.add(provider.getOrCreate(mapping));
        }

        return result;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Option<AutomaticCreator> getCreator() {
        return Options.newNone();
    }

}
