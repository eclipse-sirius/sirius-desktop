/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.componentization.mappings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManagerListener;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.internal.layers.GlobalMappingsTable;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingsListVisitor;
import org.eclipse.sirius.diagram.business.internal.sync.DNodeCandidate;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.model.business.internal.query.DSemanticDecoratorQuery;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A manager of mappings available for a given diagram depending of the activated layers.
 * 
 * @author mchauvin
 */
public final class DiagramMappingsManagerImpl implements DiagramMappingsManager, DiagramDescriptionMappingsManagerListener {

    private final DDiagram diagram;

    private final DiagramDescriptionMappingsManager descriptionMappings;

    private GlobalMappingsTable mappingsTable;

    /**
     * Construct a new instance of.
     * 
     * @param diagram
     *            the diagram for which to manage mappings
     * @param descriptionMappings
     *            the diagram description mappings manager to rely on
     */
    public DiagramMappingsManagerImpl(final DDiagram diagram, final DiagramDescriptionMappingsManager descriptionMappings) {
        this.diagram = diagram;
        this.descriptionMappings = descriptionMappings;
        this.descriptionMappings.addListener(this);
        this.mappingsTable = new GlobalMappingsTable(descriptionMappings);
    }

    /**
     * {@inheritDoc}
     **/

    @Override
    public void computeMappings(Collection<Viewpoint> enabledVPs, final boolean computeDescriptionMappings) {
        if (computeDescriptionMappings) {
            this.descriptionMappings.computeMappings(enabledVPs);
        }
        mappingsTable.build(diagram.getActivatedLayers());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager#getNodeMappings()
     */
    @Override
    public List<NodeMapping> getNodeMappings() {
        if (descriptionMappings.isLayerMode()) {
            return mappingsTable.getNodeMappings();
        } else {
            return descriptionMappings.getNodeMappings();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager#getContainerMappings()
     */
    @Override
    public List<ContainerMapping> getContainerMappings() {
        if (descriptionMappings.isLayerMode()) {
            return mappingsTable.getContainerMappings();
        } else {
            return descriptionMappings.getContainerMappings();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager#getEdgeMappings()
     */
    @Override
    public List<EdgeMapping> getEdgeMappings() {
        if (descriptionMappings.isLayerMode()) {
            return mappingsTable.getEdgeMappings();
        } else {
            return descriptionMappings.getEdgeMappings();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager#getContainerMappings(org.eclipse.sirius.viewpoint.DNodeContainer)
     */
    @Override
    public List<ContainerMapping> getContainerMappings(final DNodeContainer container) {
        return getContainerMappings(container, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.componentization.DiagramMappingsManager#getContainerMappings(DNodeContainer,
     *      boolean))
     */
    @Override
    public List<ContainerMapping> getContainerMappings(DNodeContainer container, boolean takeAlsoChildrenOfAllImportedMappings) {
        List<ContainerMapping> childrenContainerMappings = new ArrayList<ContainerMapping>();
        ContainerMapping containerViewMapping = container.getActualMapping();
        List<ContainerMapping> containerMappings = descriptionMappings.getContainerMappings(containerViewMapping);
        if (descriptionMappings.isLayerMode()) {
            containerMappings = mappingsTable.getContainerMappings(containerMappings);
        }
        childrenContainerMappings.addAll(containerMappings);
        if (takeAlsoChildrenOfAllImportedMappings && containerViewMapping instanceof ContainerMappingImport && !((ContainerMappingImport) containerViewMapping).isHideSubMappings()) {
            DiagramElementMappingQuery diagramElementMappingQuery = new DiagramElementMappingQuery(containerViewMapping);
            for (DiagramElementMapping diagramElementMapping : diagramElementMappingQuery.hierachy()) {
                if (diagramElementMapping instanceof ContainerMapping) {
                    containerViewMapping = (ContainerMapping) diagramElementMapping;
                    containerMappings = descriptionMappings.getContainerMappings(containerViewMapping);
                    if (descriptionMappings.isLayerMode()) {
                        containerMappings = mappingsTable.getContainerMappings(containerMappings);
                    }
                    childrenContainerMappings.addAll(childrenContainerMappings.size(), containerMappings);
                    // If in the mapping import hierarchy we encounter a mapping
                    // import with hideSubMappings at true we stop the hierarchy
                    // traversal because we cannot use children mapping of
                    // remaining imported mappings
                    if (containerViewMapping instanceof ContainerMappingImport && ((ContainerMappingImport) containerViewMapping).isHideSubMappings()) {
                        break;
                    }
                }
            }
        }
        return childrenContainerMappings;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager#getNodeMappings(org.eclipse.sirius.viewpoint.DNodeList)
     */
    @Override
    public List<NodeMapping> getNodeMappings(final DNodeList container) {
        return getNodeMappings(container, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.componentization.DiagramMappingsManager#getNodeMappings(DNodeList, boolean))
     */
    @Override
    public List<NodeMapping> getNodeMappings(DNodeList container, boolean takeAlsoChildrenOfAllImportedMappings) {
        List<NodeMapping> childrenNodeMappings = new ArrayList<NodeMapping>();
        ContainerMapping containerViewMapping = container.getActualMapping();
        List<NodeMapping> nodeMappings = descriptionMappings.getNodeMappings(containerViewMapping);
        if (descriptionMappings.isLayerMode()) {
            nodeMappings = mappingsTable.getNodeMappings(nodeMappings);
        }
        childrenNodeMappings.addAll(nodeMappings);
        if (takeAlsoChildrenOfAllImportedMappings && containerViewMapping instanceof ContainerMappingImport && !((ContainerMappingImport) containerViewMapping).isHideSubMappings()) {
            DiagramElementMappingQuery diagramElementMappingQuery = new DiagramElementMappingQuery(containerViewMapping);
            for (DiagramElementMapping diagramElementMapping : diagramElementMappingQuery.hierachy()) {
                if (diagramElementMapping instanceof ContainerMapping) {
                    containerViewMapping = (ContainerMapping) diagramElementMapping;
                    nodeMappings = descriptionMappings.getNodeMappings(containerViewMapping);
                    if (descriptionMappings.isLayerMode()) {
                        nodeMappings = mappingsTable.getNodeMappings(nodeMappings);
                    }
                    childrenNodeMappings.addAll(childrenNodeMappings.size(), nodeMappings);
                    // If in the mapping import hierarchy we encounter a mapping
                    // import with hideSubMappings at true we stop the hierarchy
                    // traversal because we cannot use children mapping of
                    // remaining imported mappings
                    if (containerViewMapping instanceof ContainerMappingImport && ((ContainerMappingImport) containerViewMapping).isHideSubMappings()) {
                        break;
                    }
                }
            }
        }
        return childrenNodeMappings;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager#getNodeMappings(org.eclipse.sirius.viewpoint.DNodeContainer)
     */
    @Override
    public List<NodeMapping> getNodeMappings(final DNodeContainer container) {
        return getNodeMappings(container, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.componentization.DiagramMappingsManager#getNodeMappings(DNodeContainer,
     *      boolean))
     */
    @Override
    public List<NodeMapping> getNodeMappings(DNodeContainer container, boolean takeAlsoChildrenOfAllImportedMappings) {
        List<NodeMapping> childrenNodeMappings = new ArrayList<NodeMapping>();
        ContainerMapping containerViewMapping = container.getActualMapping();
        List<NodeMapping> nodeMappings = descriptionMappings.getNodeMappings(containerViewMapping);
        if (descriptionMappings.isLayerMode()) {
            nodeMappings = mappingsTable.getNodeMappings(nodeMappings);
        }
        childrenNodeMappings.addAll(nodeMappings);
        if (takeAlsoChildrenOfAllImportedMappings && containerViewMapping instanceof ContainerMappingImport && !((ContainerMappingImport) containerViewMapping).isHideSubMappings()) {
            DiagramElementMappingQuery diagramElementMappingQuery = new DiagramElementMappingQuery(containerViewMapping);
            for (DiagramElementMapping diagramElementMapping : diagramElementMappingQuery.hierachy()) {
                if (diagramElementMapping instanceof ContainerMapping) {
                    containerViewMapping = (ContainerMapping) diagramElementMapping;
                    nodeMappings = descriptionMappings.getNodeMappings(containerViewMapping);
                    if (descriptionMappings.isLayerMode()) {
                        nodeMappings = mappingsTable.getNodeMappings(nodeMappings);
                    }
                    childrenNodeMappings.addAll(childrenNodeMappings.size(), nodeMappings);
                    // If in the mapping import hierarchy we encounter a mapping
                    // import with hideSubMappings at true we stop the hierarchy
                    // traversal because we cannot use children mapping of
                    // remaining imported mappings
                    if (containerViewMapping instanceof ContainerMappingImport && ((ContainerMappingImport) containerViewMapping).isHideSubMappings()) {
                        break;
                    }
                }
            }
        }
        return childrenNodeMappings;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager#getBorderedNodeMappings(org.eclipse.sirius.viewpoint.AbstractDNode)
     */
    @Override
    public List<NodeMapping> getBorderedNodeMappings(final AbstractDNode node) {
        return getBorderedNodeMappings(node, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager#getBorderedNodeMappings(org.eclipse.sirius.viewpoint.AbstractDNode,
     *      boolean)
     */
    @Override
    public List<NodeMapping> getBorderedNodeMappings(AbstractDNode node, boolean takeAlsoChildrenOfAllImportedMappings) {
        List<NodeMapping> childrenBorderedNodeMappings = new ArrayList<NodeMapping>();
        AbstractNodeMapping containerViewMapping = (AbstractNodeMapping) node.getMapping();
        List<NodeMapping> borderNodeMappings = descriptionMappings.getBorderedNodeMappings(containerViewMapping);
        if (descriptionMappings.isLayerMode()) {
            borderNodeMappings = mappingsTable.getBorderedNodeMappings(borderNodeMappings);
        }
        childrenBorderedNodeMappings.addAll(borderNodeMappings);
        if (takeAlsoChildrenOfAllImportedMappings && containerViewMapping instanceof AbstractMappingImport && !((AbstractMappingImport) containerViewMapping).isHideSubMappings()) {
            DiagramElementMappingQuery diagramElementMappingQuery = new DiagramElementMappingQuery(containerViewMapping);
            for (DiagramElementMapping diagramElementMapping : diagramElementMappingQuery.hierachy()) {
                if (diagramElementMapping instanceof ContainerMapping) {
                    containerViewMapping = (ContainerMapping) diagramElementMapping;
                    borderNodeMappings = descriptionMappings.getBorderedNodeMappings(containerViewMapping);
                    if (descriptionMappings.isLayerMode()) {
                        borderNodeMappings = mappingsTable.getNodeMappings(borderNodeMappings);
                    }
                    childrenBorderedNodeMappings.addAll(childrenBorderedNodeMappings.size(), borderNodeMappings);
                    // If in the mapping import hierarchy we encounter a mapping
                    // import with hideSubMappings at true we stop the hierarchy
                    // traversal because we cannot use children mapping of
                    // remaining imported mappings
                    if (containerViewMapping instanceof AbstractMappingImport && ((AbstractMappingImport) containerViewMapping).isHideSubMappings()) {
                        break;
                    }
                }
            }
        }
        return childrenBorderedNodeMappings;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager#getOtherImportersMappings()
     */
    @Override
    public List<DiagramElementMapping> getOtherImportersMappings() {
        if (descriptionMappings.isLayerMode()) {
            return mappingsTable.getOtherImportersMappings();
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManagerListener#mappingsComputed()
     */
    @Override
    public void mappingsComputed(Collection<Viewpoint> enabledViewpoints) {
        computeMappings(enabledViewpoints, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManagerListener#dispose()
     */
    @Override
    public void dispose() {
        DiagramMappingsManagerRegistry.INSTANCE.removeDiagramMappingsManagers(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Layer> getActiveParentLayers(final DiagramElementMapping mapping) {
        return mappingsTable.getIndirectParentLayers(mapping);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager#iterate(org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingsListVisitor,
     *      org.eclipse.sirius.diagram.DragAndDropTarget)
     */
    @Override
    public <T extends AbstractNodeMapping> void iterate(final MappingsListVisitor visitor, final DragAndDropTarget container) {
        if (descriptionMappings.isLayerMode()) {
            if (container instanceof DSemanticDecorator) {
                DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) container;
                if (!new DSemanticDecoratorQuery(dSemanticDecorator).hasDetachedTarget()) {
                    safeIterate(visitor, container);
                }
            } else {
                safeIterate(visitor, container);
            }
        }
    }

    private void safeIterate(MappingsListVisitor visitor, DragAndDropTarget container) {
        if (container instanceof DDiagram) {
            iterateOnMappings(getContainerMappings(), visitor);
            iterateOnMappings(getNodeMappings(), visitor);
        } else if (container instanceof DNodeContainer) {
            iterateOnMappings(getContainerMappings((DNodeContainer) container), visitor);
            iterateOnMappings(getNodeMappings((DNodeContainer) container), visitor);
            iterateOnMappings(getBorderedNodeMappings((DNodeContainer) container), visitor);
        } else if (container instanceof DNodeList) {
            iterateOnMappings(getNodeMappings((DNodeList) container), visitor);
        } else if (container instanceof DNode) {
            iterateOnMappings(getBorderedNodeMappings((DNode) container), visitor);
        }
    }

    private void iterateOnMappings(final List<? extends DiagramElementMapping> mappings, final MappingsListVisitor visitor) {

        final Set<DNodeCandidate> candidateFilter = new HashSet<>();

        for (final DiagramElementMapping mapping : mappings) {
            Collection<DNodeCandidate> candidateElementsProceed = visitor.visit(mapping, candidateFilter);
            candidateFilter.addAll(candidateElementsProceed);
        }
    }
}
