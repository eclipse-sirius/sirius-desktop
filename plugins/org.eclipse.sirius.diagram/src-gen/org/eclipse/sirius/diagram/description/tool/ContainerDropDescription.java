/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.tool;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Container Drop Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Tool that describes a Drag & Drop operation. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getMappings
 * <em>Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getOldContainer
 * <em>Old Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getNewContainer
 * <em>New Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getNewViewContainer
 * <em>New View Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getDragSource
 * <em>Drag Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#isMoveEdges
 * <em>Move Edges</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getContainerDropDescription()
 * @model
 * @generated
 */
public interface ContainerDropDescription extends MappingBasedToolDescription {
    /**
     * Returns the value of the '<em><b>Mappings</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.diagram.description.DiagramElementMapping}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * All mapping that can create the target view. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getContainerDropDescription_Mappings()
     * @model
     * @generated
     */
    EList<DiagramElementMapping> getMappings();

    /**
     * Returns the value of the '<em><b>Old Container</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The semantic element of the old container view. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Old Container</em>' containment reference.
     * @see #setOldContainer(DropContainerVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getContainerDropDescription_OldContainer()
     * @model containment="true" resolveProxies="true" required="true"
     *        annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='oldSemanticElement'"
     * @generated
     */
    DropContainerVariable getOldContainer();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getOldContainer
     * <em>Old Container</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Old Container</em>' containment
     *            reference.
     * @see #getOldContainer()
     * @generated
     */
    void setOldContainer(DropContainerVariable value);

    /**
     * Returns the value of the '<em><b>New Container</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The semantic element of the new container view. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>New Container</em>' containment reference.
     * @see #setNewContainer(DropContainerVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getContainerDropDescription_NewContainer()
     * @model containment="true" resolveProxies="true" required="true"
     *        annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='newSemanticContainer'"
     * @generated
     */
    DropContainerVariable getNewContainer();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getNewContainer
     * <em>New Container</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>New Container</em>' containment
     *            reference.
     * @see #getNewContainer()
     * @generated
     */
    void setNewContainer(DropContainerVariable value);

    /**
     * Returns the value of the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The semantic element that is dragged and dropped. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Element</em>' containment reference.
     * @see #setElement(ElementDropVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getContainerDropDescription_Element()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ElementDropVariable getElement();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getElement
     * <em>Element</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Element</em>' containment reference.
     * @see #getElement()
     * @generated
     */
    void setElement(ElementDropVariable value);

    /**
     * Returns the value of the '<em><b>New View Container</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The new view container (instance of ViewPoint or
     * ViewPointElement). <!-- end-model-doc -->
     *
     * @return the value of the '<em>New View Container</em>' containment
     *         reference.
     * @see #setNewViewContainer(ContainerViewVariable)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getContainerDropDescription_NewViewContainer()
     * @model containment="true" resolveProxies="true" required="true"
     *        annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel documentedName='newContainerView'"
     * @generated
     */
    ContainerViewVariable getNewViewContainer();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getNewViewContainer
     * <em>New View Container</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>New View Container</em>' containment
     *            reference.
     * @see #getNewViewContainer()
     * @generated
     */
    void setNewViewContainer(ContainerViewVariable value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The first operation. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment
     *         reference.
     * @see #setInitialOperation(InitialContainerDropOperation)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getContainerDropDescription_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialContainerDropOperation getInitialOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment
     *            reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialContainerDropOperation value);

    /**
     * Returns the value of the '<em><b>Drag Source</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.eclipse.sirius.viewpoint.description.tool.DragSource}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Authorized sources of the drag. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Drag Source</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.tool.DragSource
     * @see #setDragSource(DragSource)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getContainerDropDescription_DragSource()
     * @model required="true"
     * @generated
     */
    DragSource getDragSource();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getDragSource
     * <em>Drag Source</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Drag Source</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.tool.DragSource
     * @see #getDragSource()
     * @generated
     */
    void setDragSource(DragSource value);

    /**
     * Returns the value of the '<em><b>Move Edges</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Set to
     * true if you want to automatically move the edges associated with a node.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Move Edges</em>' attribute.
     * @see #setMoveEdges(boolean)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getContainerDropDescription_MoveEdges()
     * @model required="true"
     * @generated
     */
    boolean isMoveEdges();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#isMoveEdges
     * <em>Move Edges</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Move Edges</em>' attribute.
     * @see #isMoveEdges()
     * @generated
     */
    void setMoveEdges(boolean value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    DiagramElementMapping getBestMapping(DragAndDropTarget targetContainer, EObject droppedElement);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation" required="true"
     * @generated
     */
    EList<DragAndDropTargetDescription> getContainers();

} // ContainerDropDescription
