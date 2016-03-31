/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.Style;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DDiagram Element Container</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The referenced ViewPoint. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getNodes
 * <em>Nodes</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getContainers
 * <em>Containers</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getElements
 * <em>Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getOwnedStyle
 * <em>Owned Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getOriginalStyle
 * <em>Original Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getActualMapping
 * <em>Actual Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getCandidatesMapping
 * <em>Candidates Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getWidth
 * <em>Width</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getHeight
 * <em>Height</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElementContainer()
 * @model abstract="true"
 * @generated
 */
public interface DDiagramElementContainer extends AbstractDNode, EdgeTarget, DragAndDropTarget {
    /**
     * Returns the value of the '<em><b>Nodes</b></em>' reference list. The list
     * contents are of type {@link org.eclipse.sirius.diagram.DNode}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Nodes
     * owned by this container. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Nodes</em>' reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElementContainer_Nodes()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DNode> getNodes();

    /**
     * Returns the value of the '<em><b>Containers</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Containers owned by this container. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Containers</em>' reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElementContainer_Containers()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DDiagramElementContainer> getContainers();

    /**
     * Returns the value of the '<em><b>Elements</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.diagram.DDiagramElement}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> elements owned by this
     * container. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Elements</em>' reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElementContainer_Elements()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DDiagramElement> getElements();

    /**
     * Returns the value of the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The style of the container. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Style</em>' containment reference.
     * @see #setOwnedStyle(ContainerStyle)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElementContainer_OwnedStyle()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    ContainerStyle getOwnedStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getOwnedStyle
     * <em>Owned Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Owned Style</em>' containment
     *            reference.
     * @see #getOwnedStyle()
     * @generated
     */
    void setOwnedStyle(ContainerStyle value);

    /**
     * Returns the value of the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * instance of style that is contained by the mapping. The ownedStyle
     * reference should be a copy of this style. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Original Style</em>' reference.
     * @see #setOriginalStyle(Style)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElementContainer_OriginalStyle()
     * @model
     * @generated
     */
    Style getOriginalStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getOriginalStyle
     * <em>Original Style</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Original Style</em>' reference.
     * @see #getOriginalStyle()
     * @generated
     */
    void setOriginalStyle(Style value);

    /**
     * Returns the value of the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * actual mapping of this node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Actual Mapping</em>' reference.
     * @see #setActualMapping(ContainerMapping)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElementContainer_ActualMapping()
     * @model required="true"
     * @generated
     */
    ContainerMapping getActualMapping();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getActualMapping
     * <em>Actual Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Actual Mapping</em>' reference.
     * @see #getActualMapping()
     * @generated
     */
    void setActualMapping(ContainerMapping value);

    /**
     * Returns the value of the '<em><b>Candidates Mapping</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.ContainerMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * candidates mapping of this node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Candidates Mapping</em>' reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElementContainer_CandidatesMapping()
     * @model annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel deprecated='This field should not be used'"
     * @generated
     */
    EList<ContainerMapping> getCandidatesMapping();

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElementContainer_Width()
     * @model
     * @generated
     */
    Integer getWidth();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getWidth
     * <em>Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(Integer value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Height</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getDDiagramElementContainer_Height()
     * @model
     * @generated
     */
    Integer getHeight();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getHeight
     * <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(Integer value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return all nodes that have been created with the specified mapping.
     *
     * @param mapping
     *            The node mapping. <!-- end-model-doc -->
     * @model
     * @generated
     */
    EList<DNode> getNodesFromMapping(NodeMapping mapping);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return all containers that have been created with the specified mapping.
     *
     * @param mapping
     *            The container mapping <!-- end-model-doc -->
     * @model
     * @generated
     */
    EList<DDiagramElementContainer> getContainersFromMapping(ContainerMapping mapping);

} // DDiagramElementContainer
