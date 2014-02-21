/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.synchronizer.AutomaticCreator;
import org.eclipse.sirius.synchronizer.ChildCreationSupport;
import org.eclipse.sirius.synchronizer.CreatedOutput;
import org.eclipse.sirius.synchronizer.IntegerProvider;
import org.eclipse.sirius.synchronizer.Mapping;
import org.eclipse.sirius.synchronizer.MappingHiearchy;
import org.eclipse.sirius.synchronizer.MappingHiearchyTable;
import org.eclipse.sirius.synchronizer.Maybe;
import org.eclipse.sirius.synchronizer.MaybeFactory;
import org.eclipse.sirius.synchronizer.ModelToModelSynchronizer;
import org.eclipse.sirius.synchronizer.OutputDescriptor;
import org.eclipse.sirius.synchronizer.PreRefreshStatus;
import org.eclipse.sirius.synchronizer.SemanticPartition;
import org.eclipse.sirius.synchronizer.SemanticPartitionInvalidator;
import org.eclipse.sirius.synchronizer.SemanticPartitions;
import org.eclipse.sirius.synchronizer.Signature;
import org.eclipse.sirius.synchronizer.SignatureProvider;
import org.eclipse.sirius.synchronizer.StringSignature;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.MappingBasedPartition;
import org.eclipse.sirius.tree.business.internal.refresh.DTreeElementSynchronizerSpec;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemMapping;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.BiMap;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;

/**
 * Update the {@link DTree} model according to the semantic model and the
 * {@link TreeDescription} model.
 * 
 * @author cbrun
 */
public class DTreeRefresh {

    private DTreeItemContainer container;

    private SemanticPartitionInvalidator invalidator;

    private Iterable<? extends TreeItemMapping> mappings;

    private GlobalContext ctx;

    /**
     * Creates a new DTreeRefresh.
     * 
     * @param tree
     *            The tree to refresh
     * @param mappings
     *            the item mappings
     * @param invalidator
     *            the {@link SemanticPartitionInvalidator}
     * @param ctx
     *            the global context
     */
    public DTreeRefresh(DTreeItemContainer tree, Iterable<? extends TreeItemMapping> mappings, SemanticPartitionInvalidator invalidator, GlobalContext ctx) {
        this.container = tree;
        this.invalidator = invalidator;
        this.ctx = ctx;
        this.mappings = mappings;
    }

    /**
     * Refreshes the tree.
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to use
     */
    public void refresh(IProgressMonitor monitor) {
        try {
            monitor.beginTask("Refresh tree", 4);
            if (ctx.getModelAccessor().getPermissionAuthority().canEditInstance(container)) {
                MappingHiearchyTable hierarchy = new MappingHiearchyTable();
                SignatureProvider signProvider = new TreeSignatureProvider(hierarchy);

                SemanticPartitionProvider semProvider = new SemanticPartitionProvider(this.ctx);
                final TreeMappingProvider provider = new TreeMappingProvider(semProvider);
                Iterable<Mapping> providedMappings = Iterables.transform(mappings, new Function<TreeItemMapping, Mapping>() {

                    public Mapping apply(TreeItemMapping from) {
                        return provider.getOrCreate(from);
                    }
                });
                monitor.worked(1);

                hierarchy.compute(Lists.newArrayList(providedMappings));
                monitor.worked(1);

                DTreePreRefreshStatus pre = new DTreePreRefreshStatus(ctx, provider);
                ModelToModelSynchronizer refresher = new ModelToModelSynchronizer(this.invalidator, hierarchy, pre, signProvider);

                CreatedOutput cDiag = buildOutput(provider);
                monitor.worked(1);

                refresher.update(cDiag);
                monitor.worked(1);
            }
        } finally {
            monitor.done();
        }
    }

    private CreatedOutput buildOutput(final TreeMappingProvider provider) {
        if (container instanceof DTree) {
            DTree tree = (DTree) container;
            OutputDTreeDescriptor existingDTree = new OutputDTreeDescriptor(tree.getTarget(), tree.getDescription(), provider);
            CreatedDTree cDiag = new CreatedDTree(ctx, tree, existingDTree);
            return cDiag;
        } else if (container instanceof DTreeItem) {
            DTreeItem item = (DTreeItem) container;
            DTreeItemContainer treeItemcontainer = item.getContainer();
            OutputTreeItemDescriptor descriptor = new OutputTreeItemDescriptor(treeItemcontainer, item.getTarget(), item.getActualMapping(), 0, provider);
            CreatedTreeItem created = new CreatedTreeItem(ctx, item, descriptor);
            return created;
        }
        throw new RuntimeException("Unkown representation container instance");
    }
}

class DTreePreRefreshStatus implements PreRefreshStatus {

    private TreeMappingProvider provider;

    private Maybe<List<CreatedOutput>> computedOutputs;

    private GlobalContext ctx;

    public DTreePreRefreshStatus(GlobalContext ctx, TreeMappingProvider provider) {
        this.provider = provider;
        computedOutputs = MaybeFactory.newNone();
        this.ctx = ctx;
    }

    public Iterable<CreatedOutput> getExistingOutputs() {
        if (computedOutputs.some()) {
            return computedOutputs.value();
        }
        return Lists.newArrayList();
    }

    public void computeStatus(CreatedOutput container, Collection<? extends Mapping> childMappings) {
        List<CreatedOutput> result = Lists.newArrayList();
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
        computedOutputs = MaybeFactory.newSome(result);
    }
}

class TreeMappingProvider {

    protected BiMap<EObject, Mapping> mappingToRMapping = HashBiMap.create();

    private SemanticPartitionProvider semProvider;

    public TreeMappingProvider(SemanticPartitionProvider semProvider) {
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

class SemanticPartitionProvider {

    private GlobalContext ctx;

    public SemanticPartitionProvider(GlobalContext ctx) {
        this.ctx = ctx;
    }

    public SemanticPartition getSemanticPartition(TreeItemMapping nm) {
        return new MappingBasedPartition(ctx, nm.getDomainClass(), MaybeFactory.newSome(nm.getSemanticCandidatesExpression()), MaybeFactory.newSome(nm));
    }

}

class RTreeMapping implements Mapping {

    private TreeDescription treeDescription;

    private TreeMappingProvider provider;

    private SemanticPartition semPartition;

    public RTreeMapping(TreeDescription description, TreeMappingProvider provider) {
        this.treeDescription = description;
        this.provider = provider;
        this.semPartition = SemanticPartitions.eAllContents(description.getDomainClass());
    }

    public Maybe<Mapping> getSuper() {
        return MaybeFactory.newNone();
    }

    public SemanticPartition getSemanticPartition() {
        return semPartition;
    }

    public List<Mapping> getChildMappings() {
        List<Mapping> result = Lists.newArrayList();
        for (TreeItemMapping mapping : treeDescription.getSubItemMappings()) {
            result.add(provider.getOrCreate(mapping));
        }

        return result;
    }

    public boolean isEnabled() {
        return true;
    }

    public Maybe<AutomaticCreator> getCreator() {
        return MaybeFactory.newNone();
    }

}

class CreatedTreeItem extends AbstractCreatedDTreeItemContainer {

    private DTreeItem tItem;

    private OutputTreeItemDescriptor descriptor;

    private int newIndex;

    private Maybe<Mapping> newMapping = MaybeFactory.newNone();

    public CreatedTreeItem(GlobalContext ctx, DTreeItem tItem, OutputTreeItemDescriptor descriptor) {
        super(ctx);
        this.tItem = tItem;
        this.descriptor = descriptor;
        this.newIndex = descriptor.getIndex();
    }

    public OutputTreeItemDescriptor getDescriptor() {
        return descriptor;
    }

    public void setNewIndex(int nextIndex) {
        this.newIndex = nextIndex;
    }

    public int getNewIndex() {
        return this.newIndex;
    }

    public EObject getCreatedElement() {
        return tItem;
    }

    public void updateMapping() {
        if (newMapping.some()) {
            tItem.setActualMapping(((RTreeItemMapping) newMapping.value()).getDescription());
        } else {
            throw new RuntimeException("no mapping to set");
        }

    }

    public void refresh() {
        DTreeElementSynchronizerSpec sync = new DTreeElementSynchronizerSpec(getGlobalContext().getInterpreter(), getGlobalContext().getModelAccessor());
        sync.refresh(tItem);
    }

    public void setNewMapping(Mapping map) {
        newMapping = MaybeFactory.newSome(map);

    }

    /**
     * Synchronize direct children only if the current {@link DTreeItem} is
     * expanded.
     */
    public boolean synchronizeChildren() {
        return tItem.isExpanded();
    }

    public Maybe<? extends ChildCreationSupport> getChildSupport() {
        return MaybeFactory.newSome(new TreeItemContainerChildSupport(getGlobalContext(), tItem));
    }

    public List<Mapping> getChildMappings() {
        EObject papa = tItem.eContainer();
        if (papa instanceof DTreeItem) {
            EObject grandpa = papa.eContainer();
            if (grandpa instanceof DTreeItem) {
                if (!((DTreeItem) grandpa).isExpanded()) {
                    return Lists.newArrayList();
                }
            }
        }
        return getDescriptor().getMapping().getChildMappings();
    }
}

class TreeItemContainerChildSupport implements ChildCreationSupport {

    private DTreeItemContainer container;

    private GlobalContext ctx;

    public TreeItemContainerChildSupport(GlobalContext ctx, DTreeItemContainer container) {
        this.container = container;
        this.ctx = ctx;

    }

    public void reorderChilds(Iterable<CreatedOutput> outDesc) {
        final Multiset<TreeItemMapping> subMappings = LinkedHashMultiset.create();
        Set<TreeItemMapping> mappings = new HashSet<TreeItemMapping>();
        final Map<EObject, CreatedOutput> outputToItem = Maps.newHashMap();
        for (CreatedOutput createdOutput : outDesc) {
            EObject createdElement = createdOutput.getCreatedElement();
            outputToItem.put(createdElement, createdOutput);
            if (createdElement instanceof DTreeItem) {
                DTreeItem createdDTreeItem = (DTreeItem) createdElement;
                TreeItemMapping actualMapping = createdDTreeItem.getActualMapping();
                subMappings.add(actualMapping);
                mappings.add(actualMapping);
            }
        }

        // Does not need to sort DTreeItem according to their mapping if there
        // is only one mapping
        if (mappings.size() > 1) {

            // Counts subMappings to correctly sort tree items regarding mapping
            // order (items have been created regarding the semantic candidates
            // order)
            int startIndex = 0;
            final Map<TreeItemMapping, Integer> startIndexes = Maps.newHashMap();
            for (TreeItemMapping itemMapping : subMappings) {
                startIndexes.put(itemMapping, startIndex);
                startIndex += subMappings.count(itemMapping);
            }

            Function<DTreeItem, Integer> getNewIndex = new Function<DTreeItem, Integer>() {

                public Integer apply(DTreeItem from) {
                    // init with element count : elements with unknown mapping
                    // will
                    // be placed at
                    // the end.
                    int index = outputToItem.size();
                    TreeItemMapping itemMapping = from.getActualMapping();
                    if (itemMapping != null && startIndexes.containsKey(itemMapping)) {
                        index = startIndexes.get(itemMapping);
                    }

                    CreatedOutput createdOutput = outputToItem.get(from);
                    if (createdOutput != null) {
                        return index + createdOutput.getNewIndex();
                    }
                    return -1;
                }
            };

            ECollections.sort(container.getOwnedTreeItems(), Ordering.natural().onResultOf(getNewIndex));
        }
    }

    public void deleteChild(CreatedOutput outDesc) {
        EcoreUtil.delete(outDesc.getCreatedElement());
    }

    public CreatedOutput createChild(OutputDescriptor outDesc) {
        OutputTreeItemDescriptor descriptor = (OutputTreeItemDescriptor) outDesc;
        final DTreeItem line = TreeFactory.eINSTANCE.createDTreeItem();
        line.setActualMapping(descriptor.getMapping().getDescription());
        line.setTarget(outDesc.getSourceElement());
        container.getOwnedTreeItems().add(line);
        CreatedTreeItem newOne = new CreatedTreeItem(ctx, line, descriptor);
        return newOne;
    }
}

abstract class AbstractCreatedDTreeItemContainer implements CreatedOutput {

    private GlobalContext ctx;

    public AbstractCreatedDTreeItemContainer(GlobalContext ctx) {
        this.ctx = ctx;
    }

    public GlobalContext getGlobalContext() {
        return ctx;
    }
}

class CreatedDTree extends AbstractCreatedDTreeItemContainer {

    private static final String ILLEGAL_ARGUMENT_MESSAGE = "It has no sense";

    private DTree dnode;

    private OutputDescriptor descriptor;

    private int newIndex;

    public CreatedDTree(GlobalContext ctx, DTree tree, OutputDescriptor descriptor) {
        super(ctx);
        this.dnode = tree;
        this.descriptor = descriptor;
        this.newIndex = descriptor.getIndex();
    }

    public OutputDescriptor getDescriptor() {
        return descriptor;
    }

    public void setNewIndex(int nextIndex) {
        throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
    }

    public EObject getCreatedElement() {
        return dnode;
    }

    public void updateMapping() {
        throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);

    }

    public void refresh() {
        /*
         * here we should update any DTree info computed from semantic elements.
         * So far nothing...
         */
    }

    public void setNewMapping(Mapping map) {
        throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
    }

    /**
     * Always synchronize direct children of a {@link DTree} as it has not the
     * capability of collapse as {@link fr.obeo.dsl.viewpoint.tree.DTreeItem}.
     */
    public boolean synchronizeChildren() {
        return true;
    }


    public Maybe<? extends ChildCreationSupport> getChildSupport() {
        return MaybeFactory.newSome(new TreeItemContainerChildSupport(getGlobalContext(), dnode));
    }

    public List<Mapping> getChildMappings() {
        return ((RTreeMapping) getDescriptor().getMapping()).getChildMappings();
    }

    public int getNewIndex() {
        return newIndex;
    }
}

class TreeItemCreator implements AutomaticCreator {
    private TreeItemMapping nm;

    private TreeMappingProvider provider;

    public TreeItemCreator(TreeItemMapping nm, TreeMappingProvider provider) {
        super();
        this.nm = nm;
        this.provider = provider;
    }

    public Collection<? extends OutputDescriptor> computeDescriptors(final CreatedOutput context, Iterator<EObject> it) {
        final AbstractCreatedDTreeItemContainer castedContext = (AbstractCreatedDTreeItemContainer) context;
        Predicate<OutputTreeItemDescriptor> filterPredicates = new Predicate<OutputTreeItemDescriptor>() {

            public boolean apply(OutputTreeItemDescriptor input) {
                return new TreeItemMappingExpression(castedContext.getGlobalContext(), input.getMapping().getDescription()).checkPrecondition(input.getSourceElement(), input.getViewContainer());
            }
        };
        List<OutputTreeItemDescriptor> outputs = Lists.newArrayList();
        int i = 0;
        while (it.hasNext()) {
            EObject from = it.next();
            outputs.add(new OutputTreeItemDescriptor((DTreeItemContainer) context.getCreatedElement(), from, this.nm, i, this.provider));
            i++;
        }

        return Collections2.filter(outputs, filterPredicates);
    }
};

class RTreeItemMapping implements Mapping {

    private TreeItemMapping nm;

    private TreeMappingProvider provider;

    private AutomaticCreator creator;

    public RTreeItemMapping(TreeItemMapping mapping, TreeMappingProvider provider) {
        this.nm = mapping;
        this.provider = provider;
        this.creator = new TreeItemCreator(nm, provider);
    }

    public Maybe<? extends Mapping> getSuper() {
        if (nm.getSpecialize() != null) {
            return MaybeFactory.newSome(provider.getOrCreate(nm.getSpecialize()));
        }
        return MaybeFactory.newNone();
    }

    public SemanticPartition getSemanticPartition() {
        return provider.getSemanticProvider().getSemanticPartition(nm);
    }

    public List<Mapping> getChildMappings() {
        List<Mapping> result = Lists.newArrayList();
        result.addAll(Collections2.transform(nm.getAllSubMappings(), new Function<TreeItemMapping, RTreeItemMapping>() {

            public RTreeItemMapping apply(TreeItemMapping from) {
                return provider.getOrCreate(from);
            }
        }));
        return result;
    }

    public boolean isEnabled() {
        return true;
    }

    public TreeItemMapping getDescription() {
        return nm;
    }

    public Maybe<AutomaticCreator> getCreator() {
        return MaybeFactory.newSome(creator);
    }
}

class TreeSignatureProvider implements SignatureProvider {

    private Map<String, Signature> allSignatures = Maps.newHashMap();

    private MappingHiearchyTable hierarchyTable;

    public TreeSignatureProvider(MappingHiearchyTable hierarchyTable) {
        this.hierarchyTable = hierarchyTable;
    }

    public Signature getSignature(OutputDescriptor descriptor) {
        if (descriptor instanceof OutputTreeItemDescriptor) {
            return doGetSignature((OutputTreeItemDescriptor) descriptor);
        } else if (descriptor instanceof OutputDTreeDescriptor) {
            return doGetSignature((OutputDTreeDescriptor) descriptor);
        }
        throw new RuntimeException("Unkown descriptor:" + descriptor);

    }

    private Signature doGetSignature(OutputDTreeDescriptor desc) {
        return getOrCreate("tree " + getURI(desc.getSourceElement()) + desc.getMapping().toString());
    }

    private Signature getOrCreate(String string) {
        Signature existing = allSignatures.get(string);
        if (existing == null) {
            existing = new StringSignature(string);
            allSignatures.put(string, existing);
        }
        return existing;
    }

    private String getURI(EObject sourceElement) {
        return EcoreUtil.getURI(sourceElement).toString();
    }

    private Signature doGetSignature(OutputTreeItemDescriptor desc) {
        String sourceID = desc.getSourceElement() == null ? String.valueOf(desc.hashCode()) : IntegerProvider.getInteger(desc.getSourceElement()).toString();
        String containerID = IntegerProvider.getInteger(desc.getViewContainer()).toString();
        Collection<MappingHiearchy> hierarchy = hierarchyTable.getHierarchy(desc.getMapping());
        return getOrCreate(hierarchy + sourceID + containerID);
    }

}

class OutputDTreeDescriptor implements OutputDescriptor {

    private EObject from;

    private TreeDescription mapping;

    private TreeMappingProvider provider;

    public OutputDTreeDescriptor(EObject from, TreeDescription nm, TreeMappingProvider provider) {
        this.from = from;
        this.mapping = nm;
        this.provider = provider;

    }

    public int getIndex() {
        return 0;
    }

    public EObject getSourceElement() {
        return from;
    }

    public Mapping getMapping() {
        return provider.getOrCreate(mapping);
    }

    public boolean isSame(OutputDescriptor other) {

        return false;
    }

}

class OutputTreeItemDescriptor implements OutputDescriptor {

    private TreeItemMapping mapping;

    private EObject from;

    private int position;

    private TreeMappingProvider provider;

    private DTreeItemContainer container;

    public OutputTreeItemDescriptor(DTreeItemContainer container, EObject from, TreeItemMapping nm, int position, TreeMappingProvider provider) {
        this.from = from;
        this.mapping = nm;
        this.position = position;
        this.provider = provider;
        this.container = container;

    }

    public DTreeItemContainer getViewContainer() {
        return container;
    }

    public int getIndex() {
        return position;
    }

    public EObject getSourceElement() {
        return from;
    }

    public RTreeItemMapping getMapping() {
        return provider.getOrCreate(mapping);
    }

}
