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
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Abstract Node Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> An abstract mapping. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.AbstractNodeMapping#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.AbstractNodeMapping#getBorderedNodeMappings
 * <em>Bordered Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.AbstractNodeMapping#getReusedBorderedNodeMappings
 * <em>Reused Bordered Node Mappings</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getAbstractNodeMapping()
 * @model abstract="true"
 * @generated
 */
public interface AbstractNodeMapping extends DiagramElementMapping, DocumentedElement {
    /**
     * Returns the value of the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * domain class of the mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Domain Class</em>' attribute.
     * @see #setDomainClass(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getAbstractNodeMapping_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName"
     *        required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.AbstractNodeMapping#getDomainClass
     * <em>Domain Class</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Domain Class</em>' attribute.
     * @see #getDomainClass()
     * @generated
     */
    void setDomainClass(String value);

    /**
     * Returns the value of the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.NodeMapping}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * mapping for nodes that are on the border of nodes created by this
     * mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Bordered Node Mappings</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getAbstractNodeMapping_BorderedNodeMappings()
     * @model containment="true" resolveProxies="true" keys="name"
     * @generated
     */
    EList<NodeMapping> getBorderedNodeMappings();

    /**
     * Returns the value of the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.NodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Bordered Node Mappings</em>' reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reused Bordered Node Mappings</em>'
     *         reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getAbstractNodeMapping_ReusedBorderedNodeMappings()
     * @model
     * @generated
     */
    EList<NodeMapping> getReusedBorderedNodeMappings();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return the node that has been created by this mapping and the specified
     * EObject as semantic element.
     *
     * @param eObject
     *            The semantic element. <!-- end-model-doc -->
     * @model
     * @generated
     */
    @Deprecated
    EList<DDiagramElement> findDNodeFromEObject(EObject eObject);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Clear the list of done nodes. <!-- end-model-doc -->
     *
     * @model
     * @generated
     */
    @Deprecated
    void clearDNodesDone();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Add a new node in the done nodes.
     *
     * <!-- end-model-doc -->
     *
     * @model nodeRequired="true"
     * @generated
     */
    @Deprecated
    void addDoneNode(DSemanticDecorator node);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    EList<NodeMapping> getAllBorderedNodeMappings();

} // AbstractNodeMapping
