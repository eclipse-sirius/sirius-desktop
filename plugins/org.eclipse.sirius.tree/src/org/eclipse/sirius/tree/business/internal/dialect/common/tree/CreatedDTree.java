/*******************************************************************************
 * Copyright (c) 2015, 2021 THALES GLOBAL SERVICES and others.
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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.synchronizer.ChildCreationSupport;
import org.eclipse.sirius.synchronizer.Mapping;
import org.eclipse.sirius.synchronizer.OutputDescriptor;
import org.eclipse.sirius.synchronizer.RefreshPlan;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.tools.internal.Messages;

/**
 * A {@link AbstractCreatedDTreeItemContainer}.
 * 
 * @author cbrun
 */
class CreatedDTree extends AbstractCreatedDTreeItemContainer {

    private DTree dnode;

    private OutputDescriptor descriptor;

    private int newIndex;

    /**
     * Default constructor.
     * 
     * @param ctx
     *            a {@link GlobalContext}
     * @param tree
     *            a {@link DTree}
     * @param descriptor
     *            a {@link OutputDescriptor}
     */
    CreatedDTree(GlobalContext ctx, DTree tree, OutputDescriptor descriptor) {
        super(ctx);
        this.dnode = tree;
        this.descriptor = descriptor;
        this.newIndex = descriptor.getIndex();
    }

    @Override
    public OutputDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public void setNewIndex(int nextIndex) {
        throw new IllegalArgumentException(Messages.DTreeRefresh_nonsense);
    }

    @Override
    public EObject getCreatedElement() {
        return dnode;
    }

    @Override
    public void updateMapping() {
        throw new IllegalArgumentException(Messages.DTreeRefresh_nonsense);

    }

    @Override
    public void refresh() {
        /*
         * here we should update any DTree info computed from semantic elements. So far nothing...
         */
    }

    @Override
    public void setNewMapping(Mapping map) {
        throw new IllegalArgumentException(Messages.DTreeRefresh_nonsense);
    }

    @Override
    public Option<? extends ChildCreationSupport> getChildSupport() {
        return Options.newSome(new TreeItemContainerChildSupport(getGlobalContext(), dnode));
    }

    /**
     * Always synchronize direct children of a {@link DTree} as it has not the capability of collapse as
     * {@link fr.obeo.dsl.viewpoint.tree.DTreeItem}.
     */
    @Override
    public boolean synchronizeChildren(RefreshPlan refreshPlan) {
        return true;
    }

    @Override
    public List<Mapping> getChildMappings() {
        return ((RTreeMapping) getDescriptor().getMapping()).getChildMappings();
    }

    @Override
    public int getNewIndex() {
        return newIndex;
    }
}
