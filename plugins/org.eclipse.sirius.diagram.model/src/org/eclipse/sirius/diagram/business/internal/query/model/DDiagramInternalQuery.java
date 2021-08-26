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
package org.eclipse.sirius.diagram.business.internal.query.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.StyleConstants;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.model.business.internal.query.DRepresentationInternalQuery;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * A class aggregating all the queries (read-only!) having a {@link DDiagram} as a starting point.
 * 
 * @author mporhel
 */
public class DDiagramInternalQuery {

    private DDiagram dDiagram;

    /**
     * Create a new query.
     * 
     * @param dDiagram
     *            the element to query.
     */
    public DDiagramInternalQuery(DDiagram dDiagram) {
        this.dDiagram = dDiagram;
    }

    /**
     * Return all activated filters for the specified diagram.
     * 
     * @return all activated filters for the specified diagram.
     */
    public Collection<FilterDescription> getAllFilters() {
        final Collection<FilterDescription> result = new ArrayList<FilterDescription>();
        /*
         * check the activated filters
         */
        result.addAll(dDiagram.getActivatedFilters());
        return result;
    }

    /**
     * Returns the description of this D&D target.
     * 
     * @return the description of this D&D target.
     */
    public DragAndDropTargetDescription getDragAndDropDescription() {
        return dDiagram.getDescription();
    }

    private void addBorderNodes(AbstractDNode abstractNode, Collection<DNode> result) {
        final Iterator<DNode> itBorder = abstractNode.getOwnedBorderedNodes().iterator();
        while (itBorder.hasNext()) {
            final DNode borderNode = itBorder.next();
            result.add(borderNode);
            addBorderNodes(borderNode, result);
        }
    }

    private void addDiagramElementContainers(final DDiagram diagram, final DNodeContainer container, final Collection<DDiagramElementContainer> result) {
        final Iterator<DDiagramElement> it = container.getOwnedDiagramElements().iterator();
        while (it.hasNext()) {
            final DDiagramElement elem = it.next();
            if (elem instanceof DDiagramElementContainer) {
                result.add((DDiagramElementContainer) elem);
            }
            if (elem instanceof DNodeContainer) {
                addDiagramElementContainers(diagram, (DNodeContainer) elem, result);
            }
        }
    }

    /**
     * FIXME comment.
     * 
     * @param elem
     * @param result
     */
    private void addDNodeListElements(final DDiagramElementContainer elem, final Collection<DNodeListElement> result) {
        if (elem instanceof DNodeContainer) {
            final DNodeContainer container = (DNodeContainer) elem;
            final Iterator<DDiagramElement> it = container.getOwnedDiagramElements().iterator();
            while (it.hasNext()) {
                final DDiagramElement subElem = it.next();
                if (subElem instanceof DNodeListElement) {
                    result.add((DNodeListElement) subElem);
                }
                if (subElem instanceof DDiagramElementContainer) {
                    addDNodeListElements((DDiagramElementContainer) subElem, result);
                }

            }
        } else if (elem instanceof DNodeList) {
            final DNodeList list = (DNodeList) elem;
            result.addAll(list.getOwnedElements());
        }
    }

    /**
     * FIXME comment.
     * 
     * @param elem
     * @param result
     */
    private void addDNodes(final DDiagramElementContainer elem, final Collection<DNode> result) {
        if (elem instanceof DNodeContainer) {
            final DNodeContainer container = (DNodeContainer) elem;
            addBorderNodes(container, result);
            final Iterator<DDiagramElement> it = container.getOwnedDiagramElements().iterator();
            while (it.hasNext()) {
                final DDiagramElement subElem = it.next();
                if (subElem instanceof DNode) {
                    result.add((DNode) subElem);
                    final DNode node = (DNode) subElem;
                    addBorderNodes(node, result);
                }
                if (subElem instanceof DDiagramElementContainer) {
                    addDNodes((DDiagramElementContainer) subElem, result);
                }

            }
        } else if (elem instanceof DNodeList) {
            /* Now DNodeList contains DNodeListElements and bordered nodes */
            addBorderNodes(elem, result);
        }
    }

    /**
     * Return all edges owned by the specified diagram.
     * 
     * @return all edges owned by the specified diagram.
     */
    public Collection<DEdge> getEdges() {
        final Collection<DEdge> result = new ArrayList<DEdge>();
        final Iterator<DDiagramElement> it = dDiagram.getOwnedDiagramElements().iterator();
        while (it.hasNext()) {
            final DDiagramElement elem = it.next();
            if (elem instanceof DEdge) {
                result.add((DEdge) elem);
            }
        }
        return result;
    }

    /**
     * Return all node list elements owned by the specified diagram.
     * 
     * @return Return all node list elements owned by the specified diagram.
     */
    public Collection<DNodeListElement> getNodeListElements() {
        final Collection<DNodeListElement> result = new ArrayList<DNodeListElement>();
        final Iterator<DDiagramElement> it = dDiagram.getOwnedDiagramElements().iterator();
        while (it.hasNext()) {
            final DDiagramElement elem = it.next();
            if (elem instanceof DNodeListElement) {
                final DNodeListElement nodeListElement = (DNodeListElement) elem;
                result.add(nodeListElement);
            }
            if (elem instanceof DDiagramElementContainer) {
                addDNodeListElements((DDiagramElementContainer) elem, result);
            }
        }
        return result;
    }

    /**
     * Return all nodes owned by the specified diagram.
     * 
     * @return Return all nodes owned by the specified diagram.
     */
    public Collection<DNode> getNodes() {
        final Collection<DNode> result = new ArrayList<DNode>();
        final Iterator<DDiagramElement> it = dDiagram.getOwnedDiagramElements().iterator();
        while (it.hasNext()) {
            final DDiagramElement elem = it.next();
            if (elem instanceof DNode) {
                final DNode node = (DNode) elem;
                result.add(node);
                addBorderNodes(node, result);
            }
            if (elem instanceof DDiagramElementContainer) {
                addDNodes((DDiagramElementContainer) elem, result);
            }
        }
        return result;
    }

    /**
     * Returns all diagram elements (directly and indirectly contained) of the given diagram.
     * 
     * @return all diagram elements of the given diagram.
     */
    public Collection<DDiagramElement> getDiagramElements() {
        final Collection<DDiagramElement> elements = new ArrayList<DDiagramElement>();
        elements.addAll(getEdges());
        elements.addAll(getNodes());
        elements.addAll(getNodeListElements());
        elements.addAll(getContainers());
        return elements;
    }

    /**
     * Implementation of {@link DDiagram#getContainers()}.
     * 
     * @return all containers of the diagram.
     */
    public Collection<DDiagramElementContainer> getContainers() {
        final Collection<DDiagramElementContainer> result = new ArrayList<DDiagramElementContainer>();
        final Iterator<DDiagramElement> it = dDiagram.getOwnedDiagramElements().iterator();
        while (it.hasNext()) {
            final DDiagramElement elem = it.next();
            if (elem instanceof DDiagramElementContainer) {
                result.add((DDiagramElementContainer) elem);
            }
            if (elem instanceof DNodeContainer) {
                addDiagramElementContainers(dDiagram, (DNodeContainer) elem, result);
            }
        }
        return result;
        // return new EcoreEList.UnmodifiableEList(eInternalContainer(),
        // ViewpointPackage.eINSTANCE.getSirius_Containers(), result.size(),
        // result.toArray());
    }

    /**
     * return the collection {@link Style} instances currently in the diagram.
     * 
     * @return the collection {@link Style} instances currently in the diagram.
     */
    public Collection<Style> getAllStyles() {
        Collection<Style> result = new ArrayList<Style>();
        for (DDiagramElement dDiagramElement : getDiagramElements()) {
            Style style = dDiagramElement.getStyle();
            if (style != null) {
                result.add(style);
            }
        }
        return result;
    }

    /**
     * Get the {@link ComputedStyleDescriptionRegistry} of the specified {@link DDiagram}.
     * 
     * @param createIfNotExists
     *            true if we want to create a {@link ComputedStyleDescriptionRegistry} for the specified
     *            {@link DDiagram} if there is not one, false otherwise
     * @return the {@link ComputedStyleDescriptionRegistry} of the {@link DDiagram} or null if this last has not one
     */
    public ComputedStyleDescriptionRegistry getComputedStyleDescriptionRegistry(boolean createIfNotExists) {
        ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry = null;
        AnnotationEntry annotationEntry = null;
        Collection<AnnotationEntry> annotationEntries = new DRepresentationInternalQuery(dDiagram).getAnnotation(StyleConstants.DANNOTATION_CUSTOMIZATION_KEY);
        if (annotationEntries == null || annotationEntries.isEmpty()) {
            annotationEntry = DescriptionFactory.eINSTANCE.createAnnotationEntry();
            annotationEntry.setSource(StyleConstants.DANNOTATION_CUSTOMIZATION_KEY);
            dDiagram.getOwnedAnnotationEntries().add(annotationEntry);
        } else {
            annotationEntry = annotationEntries.iterator().next();
        }
        if (annotationEntry.getData() == null || !(annotationEntry.getData() instanceof ComputedStyleDescriptionRegistry)) {
            computedStyleDescriptionRegistry = DiagramFactory.eINSTANCE.createComputedStyleDescriptionRegistry();
            annotationEntry.setData(computedStyleDescriptionRegistry);
        } else {
            computedStyleDescriptionRegistry = (ComputedStyleDescriptionRegistry) annotationEntry.getData();
        }
        return computedStyleDescriptionRegistry;
    }

    /**
     * Get computed {@link StyleDescription} really used by the specified {@link DDiagram}.
     * 
     * @return the computed {@link StyleDescription} really used
     */
    public Collection<StyleDescription> getUsedComputedStyleDescritions() {
        Collection<StyleDescription> usedComputedStyleDescriptions = new ArrayList<StyleDescription>();
        ComputedStyleDescriptionRegistry registry = getComputedStyleDescriptionRegistry(false);
        if (registry != null) {
            Collection<StyleDescription> computedStyleDescriptions = registry.getComputedStyleDescriptions();
            if (!computedStyleDescriptions.isEmpty()) {
                for (Style style : getAllStyles()) {
                    StyleDescription usedDescription = style.getDescription();
                    if (usedDescription != null) {
                        usedComputedStyleDescriptions.add(usedDescription);
                    }
                }
            }
        }
        return usedComputedStyleDescriptions;
    }
}
