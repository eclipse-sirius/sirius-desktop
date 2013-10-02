/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.NodeMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>View Point Element Container</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> The referenced ViewPoint. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getNodes
 * <em>Nodes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getContainers
 * <em>Containers</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getElements
 * <em>Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOwnedStyle
 * <em>Owned Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOwnedDetails
 * <em>Owned Details</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOriginalStyle
 * <em>Original Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getActualMapping
 * <em>Actual Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getCandidatesMapping
 * <em>Candidates Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getWidth
 * <em>Width</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getHeight
 * <em>Height</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagramElementContainer()
 * @model abstract="true"
 * @generated
 */
public interface DDiagramElementContainer extends AbstractDNode, EdgeTarget, DragAndDropTarget, DContainer {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Origin Mapping</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Origin Mapping</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The mapping that has
     * created this container. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Origin Mapping</em>' reference.
     * @see #setActualMapping(ContainerMapping)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSiriusElementContainer_OriginMapping()
     * @model required="true"
     * @generated
     */
    ContainerMapping getActualMapping();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getActualMapping
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
     * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * candidates mapping of this node. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Candidates Mapping</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagramElementContainer_CandidatesMapping()
     * @model type="org.eclipse.sirius.description.ContainerMapping"
     *        required="true"
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
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagramElementContainer_Width()
     * @model
     * @generated
     */
    Integer getWidth();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getWidth
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
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDDiagramElementContainer_Height()
     * @model
     * @generated
     */
    Integer getHeight();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getHeight
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
     * Returns the value of the '<em><b>Nodes</b></em>' reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.DNode}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nodes</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Nodes</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSiriusElementContainer_Nodes()
     * @model type="viewpoint.DNode" transient="true" changeable="false"
     *        volatile="true" derived="true"
     * @generated
     */
    EList<DNode> getNodes();

    /**
     * Returns the value of the '<em><b>Containers</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Containers</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Containers owned by this
     * container. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Containers</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSiriusElementContainer_Containers()
     * @model type="viewpoint.DDiagramElementContainer" transient="true"
     *        changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DDiagramElementContainer> getContainers();

    /**
     * Returns the value of the '<em><b>Elements</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Elements</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> elements owned by this
     * container. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Elements</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getDDiagramElementContainer_Elements()
     * @model type="org.eclipse.sirius.DDiagramElement" transient="true"
     *        changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DDiagramElement> getElements();

    /**
     * Returns the value of the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Style</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The style of the
     * container. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Owned Style</em>' containment reference.
     * @see #setOwnedStyle(ContainerStyle)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSiriusElementContainer_OwnedStyle()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    ContainerStyle getOwnedStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOwnedStyle
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
     * Returns the value of the '<em><b>Owned Details</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.DDiagram}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Details</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The details of this
     * container. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Owned Details</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSiriusElementContainer_OwnedDetails()
     * @model type="viewpoint.DDiagram" containment="true" resolveProxies="true"
     * @generated
     */
    EList<DDiagram> getOwnedDetails();

    /**
     * Returns the value of the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * instance of style that is contained by the mapping. The ownedStyle
     * reference should be a copy of this style. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Original Style</em>' reference.
     * @see #setOriginalStyle(Style)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getSiriusElementContainer_OriginalStyle()
     * @model
     * @generated
     */
    Style getOriginalStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer#getOriginalStyle
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
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return all nodes that have been created with the specified mapping.
     * 
     * @param mapping
     *            The node mapping. <!-- end-model-doc -->
     * @model type="viewpoint.DNode"
     * @generated
     */
    EList<DNode> getNodesFromMapping(NodeMapping mapping);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return all containers that have been created with the specified mapping.
     * 
     * @param mapping
     *            The container mapping <!-- end-model-doc -->
     * @model type="viewpoint.DDiagramElementContainer"
     * @generated
     */
    EList<DDiagramElementContainer> getContainersFromMapping(ContainerMapping mapping);
} // DDiagramElementContainer
