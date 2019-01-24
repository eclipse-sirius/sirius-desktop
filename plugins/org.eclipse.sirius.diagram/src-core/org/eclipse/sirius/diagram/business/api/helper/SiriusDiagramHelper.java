/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.helper;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.api.query.GroupQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.IContainerMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContainerMappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Utility class for managing DDiagram models.
 * 
 * @author cbrun
 */
public final class SiriusDiagramHelper {

    /**
     * Avoid instantiation.
     */
    private SiriusDiagramHelper() {

    }

    /**
     * Create the DDiagram element corresponding to the given mapping.
     * 
     * @param mapping
     *            any mapping.
     * @param diagram
     *            the containing diagram.
     * @param modelElement
     *            the target semantic element.
     * @return the newly created {@link DDiagramElement}.
     */
    public static DDiagramElement createElement(final RepresentationElementMapping mapping, final DSemanticDiagram diagram, final EObject modelElement) {
        DDiagramElement created = null;
        if (mapping instanceof INodeMappingExt) {
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
            created = new NodeMappingHelper(interpreter).createNode((INodeMappingExt) mapping, modelElement, diagram.getTarget(), diagram);
        }
        if (mapping instanceof IContainerMappingExt) {
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(diagram.getTarget());
            return new ContainerMappingWithInterpreterHelper(interpreter).createContainer((IContainerMappingExt) mapping, modelElement, diagram.getTarget(), diagram);
        }
        return created;
    }

    /**
     * Return the {@link DiagramDescription} having the same name has the given one .
     * 
     * @param groups
     *            a list of groups to search in.
     * @param diagramDescriptionName
     *            the {@link DiagramDescription} name.
     * @return the {@link DiagramDescription} having the same name has the given one .
     */
    public static DiagramDescription findDiagramDescription(final Collection<Group> groups, final String diagramDescriptionName) {
        final Iterator<Group> itG = groups.iterator();
        while (itG.hasNext()) {
            final Group group = itG.next();
            Option<DiagramDescription> diagramDesc = new GroupQuery(group).findDiagramDescription(diagramDescriptionName);
            if (diagramDesc.some()) {
                return diagramDesc.get();
            }
        }
        return null;
    }

    /**
     * Adds the given elementToAdd at the end of the given list; it does no uniqueness checking. <br/>
     * If the given list has no <i>addUnique</i> method available, a simple add is realized.
     * 
     * @param <T>
     *            the type of the elementToAdd
     * @param list
     *            the list
     * @param elementToAdd
     *            the element to Add
     */
    private static <T> void addUnique(EList<T> list, T elementToAdd) {
        if (list instanceof AbstractEList) {
            ((AbstractEList<T>) list).addUnique(elementToAdd);
        } else {
            list.add(elementToAdd);
        }
    }

    /**
     * Adds the given elementToAdd in the given list at the given index; it does no uniqueness checking. <br/>
     * If the given list has no <i>addUnique</i> method available, a simple add is realized.
     * 
     * @param <T>
     *            the type of the elementToAdd
     * @param list
     *            the list
     * @param index
     *            the index
     * @param elementToAdd
     *            the element to Add
     */
    private static <T> void addUnique(EList<T> list, int index, T elementToAdd) {
        if (list instanceof AbstractEList) {
            ((AbstractEList<T>) list).addUnique(index, elementToAdd);
        } else {
            list.add(index, elementToAdd);
        }
    }

    /**
     * Add an abstract node to the corresponding container, checking if it's a border node or not.
     * 
     * @param container
     *            a view container.
     * @param border
     *            true if the node should be put on the border, false otherwise.
     * @param newNode
     *            the node to add in the container.
     */
    public static void addNodeInContainer(final DragAndDropTarget container, final boolean border, final AbstractDNode newNode) {
        if (container instanceof DDiagram) {
            SiriusDiagramHelper.addUnique(((DDiagram) container).getOwnedDiagramElements(), newNode);
        } else if (container instanceof DNodeContainer) {
            if (border) {
                if (newNode instanceof DNode) {
                    SiriusDiagramHelper.addUnique(((DNodeContainer) container).getOwnedBorderedNodes(), (DNode) newNode);
                }
            } else {
                SiriusDiagramHelper.addUnique(((DNodeContainer) container).getOwnedDiagramElements(), newNode);
            }
        } else if (container instanceof DNode) {
            if (newNode instanceof DNode) {
                SiriusDiagramHelper.addUnique(((DNode) container).getOwnedBorderedNodes(), (DNode) newNode);
            }
        } else if (container instanceof DNodeList) {
            if (border) {
                if (newNode instanceof DNode) {
                    SiriusDiagramHelper.addUnique(((DNodeList) container).getOwnedBorderedNodes(), (DNode) newNode);
                }
            } else {
                SiriusDiagramHelper.addUnique(((DNodeList) container).getOwnedElements(), (DNodeListElement) newNode);
            }
        }
    }

    /**
     * Remove an abstract node to the corresponding container, checking if it's a border node or not.
     * 
     * @param container
     *            a view container.
     * @param border
     *            true if the node should be put on the border, false otherwise.
     * @param newNode
     *            the node to add in the container.
     */
    public static void removeNodeFromContainer(final DragAndDropTarget container, final boolean border, final AbstractDNode newNode) {
        if (container instanceof DDiagram) {
            ((DDiagram) container).getOwnedDiagramElements().remove(newNode);
        } else if (container instanceof DNodeContainer) {
            if (border) {
                ((DNodeContainer) container).getOwnedBorderedNodes().remove(newNode);
            } else {
                ((DNodeContainer) container).getOwnedDiagramElements().remove(newNode);
            }
        } else if (container instanceof DNode) {
            ((DNode) container).getOwnedBorderedNodes().remove(newNode);
        } else if (container instanceof DNodeList) {
            if (border) {
                ((DNodeList) container).getOwnedBorderedNodes().remove(newNode);
            } else {
                ((DNodeList) container).getOwnedElements().remove(newNode);
            }
        }
    }

    /**
     * Add an abstract node to the corresponding container, checking if it's a border node or not.
     * 
     * @param container
     *            a view container.
     * @param border
     *            true if the node should be put on the border, false otherwise.
     * @param newNode
     *            the node to add in the container.
     * @param insertionIndex
     *            the index of the node to add.
     */
    public static void addNodeInContainer(final DragAndDropTarget container, final boolean border, final AbstractDNode newNode, final int insertionIndex) {
        if (container instanceof DDiagram) {
            SiriusDiagramHelper.addUnique(((DDiagram) container).getOwnedDiagramElements(), insertionIndex, newNode);
        } else if (container instanceof DNodeContainer) {
            if (border) {
                if (newNode instanceof DNode) {
                    SiriusDiagramHelper.addUnique(((DNodeContainer) container).getOwnedBorderedNodes(), insertionIndex, (DNode) newNode);
                }
            } else {
                SiriusDiagramHelper.addUnique(((DNodeContainer) container).getOwnedDiagramElements(), insertionIndex, newNode);
            }
        } else if (container instanceof DNode) {
            if (newNode instanceof DNode) {
                SiriusDiagramHelper.addUnique(((DNode) container).getOwnedBorderedNodes(), insertionIndex, (DNode) newNode);
            }
        } else if (container instanceof DNodeList) {
            if (border) {
                if (newNode instanceof DNode) {
                    SiriusDiagramHelper.addUnique(((DNodeList) container).getOwnedBorderedNodes(), insertionIndex, (DNode) newNode);
                }
            } else {
                SiriusDiagramHelper.addUnique(((DNodeList) container).getOwnedElements(), insertionIndex, (DNodeListElement) newNode);
            }
        }
    }
}
