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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.synchronizer.ChildCreationSupport;
import org.eclipse.sirius.synchronizer.Mapping;
import org.eclipse.sirius.synchronizer.OutputDescriptor;
import org.eclipse.sirius.synchronizer.RefreshPlan;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.refresh.DTreeElementSynchronizerSpec;
import org.eclipse.sirius.tree.tools.internal.Messages;

/**
 * A {@link AbstractCreatedDTreeItemContainer}.
 * 
 * @author cbrun
 */
class CreatedTreeItem extends AbstractCreatedDTreeItemContainer {

    private DTreeItem tItem;

    private OutputTreeItemDescriptor descriptor;

    private int newIndex;

    private Option<Mapping> newMapping = Options.newNone();

    /**
     * Default constructor.
     * 
     * @param ctx
     *            a {@link GlobalContext}
     * @param tItem
     *            a {@link DTreeItem}
     * @param descriptor
     *            a {@link OutputTreeItemDescriptor}
     */
    CreatedTreeItem(GlobalContext ctx, DTreeItem tItem, OutputTreeItemDescriptor descriptor) {
        super(ctx);
        this.tItem = tItem;
        this.descriptor = descriptor;
        this.newIndex = descriptor.getIndex();
    }

    @Override
    public OutputTreeItemDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public void setNewIndex(int nextIndex) {
        this.newIndex = nextIndex;
    }

    @Override
    public int getNewIndex() {
        return this.newIndex;
    }

    @Override
    public EObject getCreatedElement() {
        return tItem;
    }

    @Override
    public void updateMapping() {
        if (newMapping.some()) {
            tItem.setActualMapping(((RTreeItemMapping) newMapping.get()).getDescription());
        } else {
            throw new RuntimeException(Messages.DTreeRefresh_noMapping);
        }

    }

    @Override
    public void refresh() {
        DTreeElementSynchronizerSpec sync = new DTreeElementSynchronizerSpec(getGlobalContext().getInterpreter(), getGlobalContext().getModelAccessor());
        sync.refresh(tItem);
    }

    @Override
    public void setNewMapping(Mapping map) {
        newMapping = Options.newSome(map);

    }

    @Override
    public Option<? extends ChildCreationSupport> getChildSupport() {
        return Options.newSome(new TreeItemContainerChildSupport(getGlobalContext(), tItem));
    }

    /**
     * Synchronize direct children only if the current {@link DTreeItem} is
     * expanded.
     */
    @Override
    public boolean synchronizeChildren(RefreshPlan refreshPlan) {
        boolean synchronizeChildren = tItem.isExpanded();
        if (!synchronizeChildren && !refreshPlan.getDescriptorsToCreate().isEmpty() && willBeExpandedOnSelection(refreshPlan)) {
            tItem.setExpanded(true);
            synchronizeChildren = true;
        }
        return synchronizeChildren;
    }

    /**
     * Tells if the current {@link DTreeItem} must be expanded to show children.
     * 
     * . And as being based on
     * {@link org.eclipse.emf.ecore.change.ChangeDescription#getObjectsToDetach()}
     * work only from Eclipse Mars. See Bug 460206.
     */
    private boolean willBeExpandedOnSelection(RefreshPlan refreshPlan) {
        boolean willBeExpandedOnSelection = false;
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(tItem);
        if (domain instanceof InternalTransactionalEditingDomain) {
            InternalTransaction transaction = ((InternalTransactionalEditingDomain) domain).getActiveTransaction().getRoot();
            TransactionChangeDescription changeDescription = transaction.getChangeDescription();
            if (changeDescription != null) {
                Collection<EObject> createdObjects = changeDescription.getObjectsToDetach();
                for (OutputDescriptor descriptorToCreate : refreshPlan.getDescriptorsToCreate()) {
                    if (EcoreUtil.isAncestor(createdObjects, descriptorToCreate.getSourceElement())) {
                        willBeExpandedOnSelection = true;
                        break;
                    }
                }
            }
        }
        return willBeExpandedOnSelection;
    }

    @Override
    public List<Mapping> getChildMappings() {
        return getDescriptor().getMapping().getChildMappings();
    }
}
