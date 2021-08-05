/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.refresh;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.business.internal.helper.refresh.EdgeFilter;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.model.DDiagramSpecOperations;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.collect.Lists;

/**
 * A refresh extension that constrained all views that are issued from the mappings that are "unique"
 * ({@link #isUnique(DiagramElementMapping)}) to be unique.
 * 
 * @author ymortier
 */
public abstract class AbstractUniqueRefreshExtension implements IRefreshExtension {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension#beforeRefresh(DDiagram)
     */
    @Override
    public void beforeRefresh(final DDiagram viewPoint) {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension#postRefresh(DDiagram)
     */
    @Override
    public void postRefresh(final DDiagram diagram) {
        final DiagramDescription desc = diagram.getDescription();
        if (desc != null) {
            for (DiagramElementMapping currentMapping : this.getAllMappings(desc)) {
                if (this.isUnique(currentMapping)) {
                    if (currentMapping instanceof NodeMapping) {
                        final NodeMapping mapping = (NodeMapping) currentMapping;
                        this.clearNode(mapping, diagram);
                    } else if (currentMapping instanceof ContainerMapping) {
                        final ContainerMapping mapping = (ContainerMapping) currentMapping;
                        this.clearContainer(mapping, diagram);
                    } else if (currentMapping instanceof EdgeMapping) {
                        final EdgeMapping mapping = (EdgeMapping) currentMapping;
                        this.clearEdge(mapping, diagram);
                    }
                }
            }
        }
    }

    private void clearNode(final NodeMapping nodeMapping, final DDiagram viewPoint) {
        final Set<NodeFilter> allViews = new HashSet<NodeFilter>();
        final List<? extends DDiagramElement> nodes = Lists.newArrayList(DDiagramSpecOperations.getNodesFromMapping(viewPoint, nodeMapping));
        for (DDiagramElement vpElement : nodes) {
            NodeFilter nodeFilter = null;
            if (vpElement instanceof DNodeListElement) {
                nodeFilter = new NodeFilter((DNodeListElement) vpElement);
            } else if (vpElement instanceof DNode) {
                nodeFilter = new NodeFilter((DNode) vpElement);
            }
            if (nodeFilter != null) {
                if (!allViews.contains(nodeFilter)) {
                    allViews.add(nodeFilter);
                } else {
                    delete(vpElement); // we must remove the view.
                }
            }
        }
    }

    private void clearContainer(final ContainerMapping containerMapping, final DDiagram viewPoint) {
        final Set<NodeFilter> allViews = new HashSet<NodeFilter>();
        final List<? extends DDiagramElement> containers = Lists.newArrayList(DDiagramSpecOperations.getContainersFromMapping(viewPoint, containerMapping));
        for (DDiagramElement vpElement : containers) {
            NodeFilter nodeFilter = null;
            if (vpElement instanceof DNodeContainer) {
                nodeFilter = new NodeFilter((DNodeContainer) vpElement);
            } else if (vpElement instanceof DNodeList) {
                nodeFilter = new NodeFilter((DNodeList) vpElement);
            }
            if (nodeFilter != null) {
                if (!allViews.contains(nodeFilter)) {
                    allViews.add(nodeFilter);
                } else {
                    delete(vpElement); // we must remove the view.
                }
            }
        }
    }

    private void clearEdge(final EdgeMapping edgeMapping, final DDiagram diagram) {
        final Set<EdgeFilter> allViews = new HashSet<EdgeFilter>();
        final List<DEdge> edges = Lists.newArrayList(DDiagramSpecOperations.getEdgesFromMapping(diagram, edgeMapping));
        for (DEdge edge : edges) {
            final EdgeFilter edgeFilter = new EdgeFilter(edge);
            if (!allViews.contains(edgeFilter)) {
                allViews.add(edgeFilter);
            } else {
                delete(edge); // we must remove the view.
            }
        }
    }

    /**
     * Delete a diagram element and all the references pointing to it.
     */
    private void delete(final DDiagramElement element) {
        final Session session = SessionManager.INSTANCE.getSession(element.getTarget());
        final ECrossReferenceAdapter xref = session != null ? session.getSemanticCrossReferencer() : null;
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(element).eDelete(element, xref);
    }

    /**
     * Returns <code>true</code> if all views for this mapping should be unique.
     * 
     * @param mapping
     *            the mapping.
     * @return <code>true</code> if all views for this mapping should be unique.
     */
    public abstract boolean isUnique(DiagramElementMapping mapping);

    /**
     * Returns all mappings that are directly or indirectly contained by the specified {@link DiagramDescription}.
     * 
     * @param diagramDescription
     *            the {@link DiagramDescription}.
     * @return all mappings that are directly or indirectly contained by the specified {@link DiagramDescription}.
     */
    private List<DiagramElementMapping> getAllMappings(final DiagramDescription diagramDescription) {
        final List<DiagramElementMapping> result = new LinkedList<DiagramElementMapping>();
        final Iterator<EObject> iterMappings = diagramDescription.eAllContents();
        while (iterMappings.hasNext()) {
            final Object next = iterMappings.next();
            if (next instanceof DiagramElementMapping) {
                result.add((DiagramElementMapping) next);
            }
        }
        return result;
    }

    /**
     * This class allows to filter nodes and containers.
     * 
     * @author ymortier
     */
    private static class NodeFilter {

        /** The targeted element. */
        private EObject targetedElement;

        /** the semantic elements. */
        private List semanticElements;

        /**
         * Create a new filter with the specified element.
         * 
         * @param viewNode
         *            the element.
         */
        NodeFilter(final DNode viewNode) {
            if (viewNode == null) {
                throw new IllegalArgumentException(Messages.NodeFilter_notNullErrorMsg);
            }
            this.targetedElement = viewNode.getTarget();
            this.semanticElements = viewNode.getSemanticElements();
        }

        /**
         * Create a new filter with the specified element.
         * 
         * @param viewNodeListElement
         *            the element.
         */
        NodeFilter(final DNodeListElement viewNodeListElement) {
            if (viewNodeListElement == null) {
                throw new IllegalArgumentException(Messages.NodeFilter_notNullErrorMsg);
            }
            this.targetedElement = viewNodeListElement.getTarget();
            this.semanticElements = viewNodeListElement.getSemanticElements();
        }

        /**
         * Create a new filter with the specified element.
         * 
         * @param viewNodeList
         *            the element.
         */
        NodeFilter(final DNodeList viewNodeList) {
            if (viewNodeList == null) {
                throw new IllegalArgumentException(Messages.NodeFilter_notNullErrorMsg);
            }
            this.targetedElement = viewNodeList.getTarget();
            this.semanticElements = viewNodeList.getSemanticElements();
        }

        /**
         * Create a new filter with the specified element.
         * 
         * @param viewNodeContainer
         *            the element.
         */
        NodeFilter(final DNodeContainer viewNodeContainer) {
            if (viewNodeContainer == null) {
                throw new IllegalArgumentException(Messages.NodeFilter_notNullErrorMsg);
            }
            this.targetedElement = viewNodeContainer.getTarget();
            this.semanticElements = viewNodeContainer.getSemanticElements();
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof NodeFilter) {
                final NodeFilter nodeFilter = (NodeFilter) obj;
                boolean areEquals;
                if (nodeFilter.semanticElements == null) {
                    areEquals = this.semanticElements == null;
                } else {
                    if (this.semanticElements == null) {
                        areEquals = false;
                    } else {
                        areEquals = this.semanticElements.containsAll(nodeFilter.semanticElements) && nodeFilter.semanticElements.contains(this.semanticElements);
                    }
                }
                if (areEquals) {
                    if (nodeFilter.targetedElement == null) {
                        areEquals = this.targetedElement == null;
                    } else {
                        areEquals = this.targetedElement != null && this.targetedElement.equals(nodeFilter.targetedElement);
                    }
                    return areEquals;
                }
            }
            return false;
        }

        /**
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return this.targetedElement == null ? 0 : this.targetedElement.hashCode();
        }
    }

}
