/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.operations;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;

/**
 * Implementation of DDiagramElementContainerImpl.java used for diagram
 * metamodel only.
 * 
 * @author jmallet
 */
public final class DDiagramElementContainerOperations {

    /** Avoid instanciations */
    private DDiagramElementContainerOperations() {
        // empty.
    }

    /**
     * Return all nodes that are directly contained in the specified container.
     * 
     * @param container
     *            the container.
     * @return all nodes that are directly contained in the specified container.
     */
    public static Collection<AbstractDNode> getNodes(final DDiagramElementContainer container) {
        final Collection<AbstractDNode> result = new ArrayList<AbstractDNode>();
        for (final DDiagramElement elem : container.getElements()) {
            if (elem instanceof DNode) {
                result.add((DNode) elem);
                final DNode node = (DNode) elem;
                for (DNode borderNode : node.getOwnedBorderedNodes()) {
                    result.add(borderNode);
                }
            } else if (elem instanceof DNodeListElement) {
                result.add((DNodeListElement) elem);
            }

            if (elem instanceof DDiagramElementContainer) {
                DDiagramElementContainerOperations.addViewNodes((DDiagramElementContainer) elem, result);
            }
        }
        return result;
    }

    private static void addViewNodes(final DDiagramElementContainer elem, final Collection<AbstractDNode> result) {
        if (elem instanceof DNodeContainer) {
            final DNodeContainer container = (DNodeContainer) elem;
            result.addAll(container.getOwnedBorderedNodes());
            for (final DDiagramElement subElem : container.getOwnedDiagramElements()) {
                if (subElem instanceof DNode) {
                    result.add((DNode) subElem);
                }
                if (subElem instanceof DDiagramElementContainer) {
                    DDiagramElementContainerOperations.addViewNodes((DDiagramElementContainer) subElem, result);
                }
            }
        } else if (elem instanceof DNodeList) {
            final DNodeList container = (DNodeList) elem;
            for (final DDiagramElement subElem : container.getOwnedElements()) {
                if (subElem instanceof DNode) {
                    result.add((DNode) subElem);
                }
                if (subElem instanceof DDiagramElementContainer) {
                    DDiagramElementContainerOperations.addViewNodes((DDiagramElementContainer) subElem, result);
                }
            }
        }

    }

    /**
     * Return all containers that are directly contained in the specified
     * container.
     * 
     * @param container
     *            the container.
     * @return all containers that are directly contained in the specified
     *         container.
     */
    public static Collection<DDiagramElementContainer> getContainers(final DDiagramElementContainer container) {
        final Collection<DDiagramElementContainer> result = new ArrayList<DDiagramElementContainer>();
        for (final DDiagramElement elem : container.getElements()) {
            if (elem instanceof DDiagramElementContainer) {
                result.add((DDiagramElementContainer) elem);
            }
            if (elem instanceof DNodeContainer) {
                DDiagramElementContainerOperations.addSiriusElementContainers((DNodeContainer) elem, result);
            }
        }
        return result;

    }

    private static void addSiriusElementContainers(final DNodeContainer container, final Collection<DDiagramElementContainer> result) {
        for (final DDiagramElement elem : container.getOwnedDiagramElements()) {
            if (elem instanceof DDiagramElementContainer) {
                result.add((DDiagramElementContainer) elem);
            }
            if (elem instanceof DNodeContainer) {
                DDiagramElementContainerOperations.addSiriusElementContainers((DNodeContainer) elem, result);
            }
        }
    }

    /**
     * Return all nodes that are in the specified container and that have been
     * created from the specified mapping.
     * 
     * @param container
     *            the container.
     * @param mapping
     *            the node mapping.
     * @return all nodes that are in the specified container and that have been
     *         created from the specified mapping.
     */
    public static EList<DNode> getNodesFromMapping(final DDiagramElementContainer container, final NodeMapping mapping) {
        final EList<DNode> result = new BasicEList<DNode>();
        for (final DNode node : container.getNodes()) {
            if (node.getMapping() == mapping) {
                result.add(node);
            }
        }
        return result;
    }

    /**
     * Return all containers that are in the specified container and that have
     * been created from the specified mapping.
     * 
     * @param current
     *            the container.
     * @param mapping
     *            the node mapping.
     * @return all containers that are in the specified container and that have
     *         been created from the specified mapping.
     */
    public static EList<DDiagramElementContainer> getContainersFromMapping(final DDiagramElementContainer current, final ContainerMapping mapping) {
        final EList<DDiagramElementContainer> result = new BasicEList<DDiagramElementContainer>();
        for (final DDiagramElementContainer container : DDiagramElementContainerOperations.getContainers(current)) {
            if (container.getActualMapping() == mapping) {
                result.add(container);
            }
        }
        return result;
    }

    /**
     * Return the parent {@link DDiagram} of the specified container.
     * 
     * @param container
     *            the container.
     * @return the parent {@link DDiagram} of the specified container.
     */
    public static DDiagram getParentDiagram(final DDiagramElementContainer container) {
        return DDiagramElementSpecOperations.getParentDiagram(container);
    }

    /**
     * Return the description of this D&D target.
     * 
     * @param self
     *            the container.
     * @return the description of this D&D target.
     */
    public static DragAndDropTargetDescription getDragAndDropDescription(final DDiagramElementContainer self) {
        return self.getActualMapping();
    }
}
