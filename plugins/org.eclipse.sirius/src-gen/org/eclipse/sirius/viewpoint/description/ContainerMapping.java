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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.ContainerLayout;
import org.eclipse.sirius.viewpoint.ContainerStyle;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.style.ContainerStyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Container Mapping</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A container mapping allows to create containers
 * (ViewNodeContainer or ViewNodeList). <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getSubNodeMappings
 * <em>Sub Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getAllNodeMappings
 * <em>All Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getReusedNodeMappings
 * <em>Reused Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getSubContainerMappings
 * <em>Sub Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getReusedContainerMappings
 * <em>Reused Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getAllContainerMappings
 * <em>All Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getConditionnalStyles
 * <em>Conditionnal Styles</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getChildrenPresentation
 * <em>Children Presentation</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getContainerMapping()
 * @model
 * @generated
 */
public interface ContainerMapping extends AbstractNodeMapping, DragAndDropTargetDescription {

    /**
     * Returns the value of the '<em><b>Sub Node Mappings</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.NodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Node Mappings</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Sub Node Mappings</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getContainerMapping_SubNodeMappings()
     * @model type="org.eclipse.sirius.description.NodeMapping"
     *        containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<NodeMapping> getSubNodeMappings();

    /**
     * Returns the value of the '<em><b>All Node Mappings</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.NodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Node Mappings</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>All Node Mappings</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getContainerMapping_AllNodeMappings()
     * @model type="org.eclipse.sirius.description.NodeMapping" transient="true"
     *        changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<NodeMapping> getAllNodeMappings();

    /**
     * Returns the value of the '<em><b>Reused Node Mappings</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.NodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Node Mappings</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Reused Node Mappings</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getContainerMapping_ReusedNodeMappings()
     * @model
     * @generated
     */
    EList<NodeMapping> getReusedNodeMappings();

    /**
     * Returns the value of the '<em><b>Sub Container Mappings</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Container Mappings</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Sub Container Mappings</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getContainerMapping_SubContainerMappings()
     * @model type="org.eclipse.sirius.description.ContainerMapping"
     *        containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<ContainerMapping> getSubContainerMappings();

    /**
     * Returns the value of the '<em><b>Reused Container Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Container Mappings</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Reused Container Mappings</em>' reference
     *         list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getContainerMapping_ReusedContainerMappings()
     * @model
     * @generated
     */
    EList<ContainerMapping> getReusedContainerMappings();

    /**
     * Returns the value of the '<em><b>All Container Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Container Mappings</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>All Container Mappings</em>' reference
     *         list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getContainerMapping_AllContainerMappings()
     * @model type="org.eclipse.sirius.description.ContainerMapping"
     *        transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<ContainerMapping> getAllContainerMappings();

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(ContainerStyleDescription)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getContainerMapping_Style()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    ContainerStyleDescription getStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getStyle
     * <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(ContainerStyleDescription value);

    /**
     * Returns the value of the '<em><b>Conditionnal Styles</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.ConditionalContainerStyleDescription}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditionnal Styles</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Conditionnal Styles</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getContainerMapping_ConditionnalStyles()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ConditionalContainerStyleDescription> getConditionnalStyles();

    /**
     * Returns the value of the '<em><b>Children Presentation</b></em>'
     * attribute. The default value is <code>"FreeForm"</code>. The literals are
     * from the enumeration
     * {@link org.eclipse.sirius.description.ContainerLayout}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Set to
     * List if you want a container acting like a list. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Children Presentation</em>' attribute.
     * @see org.eclipse.sirius.description.ContainerLayout
     * @see #setChildrenPresentation(ContainerLayout)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getContainerMapping_ChildrenPresentation()
     * @model default="FreeForm" required="true"
     * @generated
     */
    ContainerLayout getChildrenPresentation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.ContainerMapping#getChildrenPresentation
     * <em>Children Presentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Children Presentation</em>'
     *            attribute.
     * @see org.eclipse.sirius.viewpoint.ContainerLayout
     * @see #getChildrenPresentation()
     * @generated
     */
    void setChildrenPresentation(ContainerLayout value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    @Deprecated
    DDiagramElementContainer createContainer(EObject modelElement, EObject container, DDiagram viewPoint);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    @Deprecated
    void updateContainer(DDiagramElementContainer node);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    ContainerStyle getBestStyle(EObject modelElement, EObject viewVariable, EObject containerVariable);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    @Deprecated
    EList<EObject> getNodesCandidates(EObject semanticOrigin, EObject container);

    /**
     * <!-- begin-user-doc -->
     * <p>
     * Return all semantic candidates for this mapping. It checks the
     * {@link DiagramElementMapping#getPreconditionExpression()} and return all
     * objects that satisfied the expression.
     * </p>
     * <p>
     * Default candidates are all objects under <code>semanticOrigin</code> if
     * the {@link DiagramElementMapping#getSemanticCandidatesExpression()} is
     * set then default candidates are the elements that are returned by the
     * evaluation of the expression (the context is <code>semanticOrigin</code>
     * ).
     * </p>
     * <p>
     * 
     * @param semanticOrigin
     *            the origin of the computation.
     * @param container
     *            the semantic element ({@link DSemanticDecorator#getTarget()})
     *            of the {@link DDiagramElement} (or
     *            {@link DDiagramElementContainer} or {@link DDiagram}) that
     *            contains the containers.
     * @param containerView
     *            the {@link DDiagramElement} (or
     *            {@link DDiagramElementContainer} or {@link DDiagram}) that
     *            contains the containers.
     * 
     *            </p>
     *            <!-- end-user-doc -->
     * @model
     * @generated
     */
    @Deprecated
    EList<EObject> getNodesCandidates(EObject semanticOrigin, EObject container, EObject containerView);
} // ContainerMapping
