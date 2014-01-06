/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.AbstractEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.GroupQuery;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DragAndDropTarget;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Utility class for managing DDiagram models.
 * 
 * @author cbrun
 */
public final class SiriusHelper {

    /**
     * Avoid instantiation.
     */
    private SiriusHelper() {

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
        if (mapping instanceof NodeMapping) {
            created = ((NodeMapping) mapping).createNode(modelElement, diagram.getTarget(), diagram);
        }
        if (mapping instanceof ContainerMapping) {
            created = ((ContainerMapping) mapping).createContainer(modelElement, diagram.getTarget(), diagram);
        }
        return created;
    }

    /**
     * Return the {@link DiagramDescription} having the same name has the given
     * one .
     * 
     * @param groups
     *            a list of groups to search in.
     * @param diagramDescriptionName
     *            the {@link DiagramDescription} name.
     * @return the {@link DiagramDescription} having the same name has the given
     *         one .
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
     * Clear all the harmless dangling references from the given element.
     * 
     * @param elem
     *            nay representation element.
     */
    public static void unSetHarmlessDanglingReferences(final DRepresentationElement elem) {
        final Iterator<EObject> semantics = elem.getSemanticElements().iterator();
        final Set<EObject> referedElementsToRemove = new HashSet<EObject>();
        while (semantics.hasNext()) {
            final EObject semantic = semantics.next();
            if (semantic.eResource() == null) {
                referedElementsToRemove.add(semantic);
            }
        }
        final Iterator<EObject> toRemoves = referedElementsToRemove.iterator();
        while (toRemoves.hasNext()) {
            EcoreUtil.remove(elem, ViewpointPackage.eINSTANCE.getDRepresentationElement_SemanticElements(), toRemoves.next());
        }
        /*
         * We'll do the same kind of trick for possible dangling reference to an
         * OriginalStyle. Then let's just re-set the value with the best style.
         */
        final EStructuralFeature originalStyleFeat = elem.eClass().getEStructuralFeature("originalStyle");
        if (originalStyleFeat != null) {
            final EObject originalStyle = (EObject) elem.eGet(originalStyleFeat);
            if (originalStyle != null && originalStyle.eResource() == null) {
                elem.eSet(originalStyleFeat, null);
            }
        }
    }

    /**
     * Adds the given elementToAdd at the end of the given list; it does no
     * uniqueness checking. <br/>
     * If the given list has no <i>addUnique</i> method available, a simple add
     * is realized.
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
     * Adds the given elementToAdd in the given list at the given index; it does
     * no uniqueness checking. <br/>
     * If the given list has no <i>addUnique</i> method available, a simple add
     * is realized.
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
     * Add an abstract node to the corresponding container, checking if it's a
     * border node or not.
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
            SiriusHelper.addUnique(((DDiagram) container).getOwnedDiagramElements(), newNode);
        } else if (container instanceof DNodeContainer) {
            if (border) {
                if (newNode instanceof DNode) {
                    SiriusHelper.addUnique(((DNodeContainer) container).getOwnedBorderedNodes(), (DNode) newNode);
                }
            } else {
                SiriusHelper.addUnique(((DNodeContainer) container).getOwnedDiagramElements(), newNode);
            }
        } else if (container instanceof DNode) {
            if (newNode instanceof DNode) {
                SiriusHelper.addUnique(((DNode) container).getOwnedBorderedNodes(), (DNode) newNode);
            }
        } else if (container instanceof DNodeList) {
            if (border) {
                if (newNode instanceof DNode) {
                    SiriusHelper.addUnique(((DNodeList) container).getOwnedBorderedNodes(), (DNode) newNode);
                }
            } else {
                SiriusHelper.addUnique(((DNodeList) container).getOwnedElements(), (DNodeListElement) newNode);
            }
        }
    }

    /**
     * Remove an abstract node to the corresponding container, checking if it's
     * a border node or not.
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
     * Add an abstract node to the corresponding container, checking if it's a
     * border node or not.
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
            SiriusHelper.addUnique(((DDiagram) container).getOwnedDiagramElements(), insertionIndex, newNode);
        } else if (container instanceof DNodeContainer) {
            if (border) {
                if (newNode instanceof DNode) {
                    SiriusHelper.addUnique(((DNodeContainer) container).getOwnedBorderedNodes(), insertionIndex, (DNode) newNode);
                }
            } else {
                SiriusHelper.addUnique(((DNodeContainer) container).getOwnedDiagramElements(), insertionIndex, newNode);
            }
        } else if (container instanceof DNode) {
            if (newNode instanceof DNode) {
                SiriusHelper.addUnique(((DNode) container).getOwnedBorderedNodes(), insertionIndex, (DNode) newNode);
            }
        } else if (container instanceof DNodeList) {
            if (border) {
                if (newNode instanceof DNode) {
                    SiriusHelper.addUnique(((DNodeList) container).getOwnedBorderedNodes(), insertionIndex, (DNode) newNode);
                }
            } else {
                SiriusHelper.addUnique(((DNodeList) container).getOwnedElements(), insertionIndex, (DNodeListElement) newNode);
            }
        }
    }

    /**
     * Get, or create if there is none, a migration annotation for a group.
     * 
     * @param source
     *            the source of the annotation
     * 
     * @param representation
     *            the DRepresentation
     * @param eObject
     *            the data of the annotation
     * @return the annotation entry
     */
    public static AnnotationEntry getOrCreateAnnotation(final String source, final DRepresentation representation, final EObject eObject) {
        AnnotationEntry annotation = new DRepresentationQuery(representation).getAnnotation(source, eObject).get();
        if (annotation == null && eObject != null) {
            annotation = DescriptionFactory.eINSTANCE.createAnnotationEntry();
            annotation.setSource(source);
            annotation.setData(eObject);
            representation.getOwnedAnnotationEntries().add(annotation);
        }
        return annotation;
    }
}
