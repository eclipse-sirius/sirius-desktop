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
package org.eclipse.sirius.diagram.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Container Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A container mapping allows to create containers
 * (ViewNodeContainer or ViewNodeList). <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.ContainerMapping#getSubNodeMappings
 * <em>Sub Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.ContainerMapping#getAllNodeMappings
 * <em>All Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.ContainerMapping#getReusedNodeMappings
 * <em>Reused Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.ContainerMapping#getSubContainerMappings
 * <em>Sub Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.ContainerMapping#getReusedContainerMappings
 * <em>Reused Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.ContainerMapping#getAllContainerMappings
 * <em>All Container Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.ContainerMapping#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.ContainerMapping#getConditionnalStyles
 * <em>Conditionnal Styles</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.ContainerMapping#getChildrenPresentation
 * <em>Children Presentation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMapping()
 * @model
 * @generated
 */
public interface ContainerMapping extends AbstractNodeMapping, DragAndDropTargetDescription {
    /**
     * Returns the value of the '<em><b>Sub Node Mappings</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.NodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Node Mappings</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sub Node Mappings</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMapping_SubNodeMappings()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<NodeMapping> getSubNodeMappings();

    /**
     * Returns the value of the '<em><b>All Node Mappings</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.NodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Node Mappings</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>All Node Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMapping_AllNodeMappings()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<NodeMapping> getAllNodeMappings();

    /**
     * Returns the value of the '<em><b>Reused Node Mappings</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.NodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Node Mappings</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reused Node Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMapping_ReusedNodeMappings()
     * @model
     * @generated
     */
    EList<NodeMapping> getReusedNodeMappings();

    /**
     * Returns the value of the '<em><b>Sub Container Mappings</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.ContainerMapping}. <!--
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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMapping_SubContainerMappings()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<ContainerMapping> getSubContainerMappings();

    /**
     * Returns the value of the '<em><b>Reused Container Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.ContainerMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Container Mappings</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reused Container Mappings</em>' reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMapping_ReusedContainerMappings()
     * @model
     * @generated
     */
    EList<ContainerMapping> getReusedContainerMappings();

    /**
     * Returns the value of the '<em><b>All Container Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.ContainerMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Container Mappings</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>All Container Mappings</em>' reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMapping_AllContainerMappings()
     * @model transient="true" changeable="false" volatile="true" derived="true"
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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMapping_Style()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    ContainerStyleDescription getStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.ContainerMapping#getStyle
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
     * {@link org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription}
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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMapping_ConditionnalStyles()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ConditionalContainerStyleDescription> getConditionnalStyles();

    /**
     * Returns the value of the '<em><b>Children Presentation</b></em>'
     * attribute. The default value is <code>"FreeForm"</code>. The literals are
     * from the enumeration {@link org.eclipse.sirius.diagram.ContainerLayout}.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Set to List if you want a container acting like a list. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Children Presentation</em>' attribute.
     * @see org.eclipse.sirius.diagram.ContainerLayout
     * @see #setChildrenPresentation(ContainerLayout)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getContainerMapping_ChildrenPresentation()
     * @model default="FreeForm" required="true"
     * @generated
     */
    ContainerLayout getChildrenPresentation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.ContainerMapping#getChildrenPresentation
     * <em>Children Presentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Children Presentation</em>'
     *            attribute.
     * @see org.eclipse.sirius.diagram.ContainerLayout
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
    ContainerStyle getBestStyle(EObject modelElement, EObject viewVariable, EObject containerVariable);

} // ContainerMapping
