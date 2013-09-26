/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.DNodeList;
import org.eclipse.sirius.viewpoint.DNodeListElement;
import org.eclipse.sirius.viewpoint.description.DragAndDropTargetDescription;
import org.eclipse.sirius.viewpoint.description.filter.FilterDescription;

/**
 * A class aggregating all the queries (read-only!) having a {@link DDiagram} as
 * a starting point.
 * 
 * @author mporhel
 * 
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
     * Returns all diagram elements (directly and indirectly contained) of the
     * given diagram.
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
}
