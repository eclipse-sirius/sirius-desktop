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
import org.eclipse.sirius.tree.description.TreeItemMapping;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * A {@link Mapping}.
 * 
 * @author cbrun
 */
class RTreeItemMapping implements Mapping {

    private TreeItemMapping nm;

    private TreeMappingProvider provider;

    private AutomaticCreator creator;

    /**
     * Default constructor
     * 
     * @param mapping
     *            a {@link TreeItemMapping}
     * @param provider
     *            a {@link TreeMappingProvider}
     */
    RTreeItemMapping(TreeItemMapping mapping, TreeMappingProvider provider) {
        this.nm = mapping;
        this.provider = provider;
        this.creator = new TreeItemCreator(nm, provider);
    }

    @Override
    public Option<? extends Mapping> getSuper() {
        if (nm.getSpecialize() != null) {
            return Options.newSome(provider.getOrCreate(nm.getSpecialize()));
        }
        return Options.newNone();
    }

    @Override
    public SemanticPartition getSemanticPartition() {
        return provider.getSemanticProvider().getSemanticPartition(nm);
    }

    public List<Mapping> getChildMappings() {
        List<Mapping> result = new ArrayList<>();
        result.addAll(Collections2.transform(nm.getAllSubMappings(), new Function<TreeItemMapping, RTreeItemMapping>() {

            @Override
            public RTreeItemMapping apply(TreeItemMapping from) {
                return provider.getOrCreate(from);
            }
        }));
        return result;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public TreeItemMapping getDescription() {
        return nm;
    }

    @Override
    public Option<AutomaticCreator> getCreator() {
        return Options.newSome(creator);
    }
}
