/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.synchronizer.CreatedOutput;
import org.eclipse.sirius.synchronizer.Mapping;
import org.eclipse.sirius.synchronizer.PreRefreshStatus;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;

/**
 * A {@link PreRefreshStatus}.
 * 
 * @author cbrun
 */
class DTreePreRefreshStatus implements PreRefreshStatus {

    private TreeMappingProvider provider;

    private Option<List<CreatedOutput>> computedOutputs;

    private GlobalContext ctx;

    /**
     * Default constructor.
     * 
     * @param ctx
     *            a {@link GlobalContext}.
     * @param provider
     *            a {@link TreeMappingProvider}
     */
    DTreePreRefreshStatus(GlobalContext ctx, TreeMappingProvider provider) {
        this.provider = provider;
        computedOutputs = Options.newNone();
        this.ctx = ctx;
    }

    @Override
    public Iterable<CreatedOutput> getExistingOutputs() {
        if (computedOutputs.some()) {
            return computedOutputs.get();
        }
        return new ArrayList<>();
    }

    @Override
    public void computeStatus(CreatedOutput container, Collection<? extends Mapping> childMappings) {
        List<CreatedOutput> result = new ArrayList<>();
        int i = 0;
        DTreeItemContainer viewContainer = (DTreeItemContainer) container.getCreatedElement();
        Iterator<DTreeItem> it = viewContainer.getOwnedTreeItems().iterator();
        while (it.hasNext()) {
            DTreeItem cur = it.next();
            /*
             * we should create the output
             */
            OutputTreeItemDescriptor descriptor = new OutputTreeItemDescriptor((DTreeItemContainer) container.getCreatedElement(), cur.getTarget(), cur.getActualMapping(), i, provider);
            CreatedTreeItem newOuput = new CreatedTreeItem(ctx, cur, descriptor);
            result.add(newOuput);
            i++;
        }
        computedOutputs = Options.newSome(result);
    }
}
