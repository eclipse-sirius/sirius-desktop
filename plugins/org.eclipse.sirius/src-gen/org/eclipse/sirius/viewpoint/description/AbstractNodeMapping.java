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
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Abstract Node Mapping</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> An abstract mapping. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.AbstractNodeMapping#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.AbstractNodeMapping#getBorderedNodeMappings
 * <em>Bordered Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.AbstractNodeMapping#getReusedBorderedNodeMappings
 * <em>Reused Bordered Node Mappings</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getAbstractNodeMapping()
 * @model abstract="true"
 * @generated
 */
public interface AbstractNodeMapping extends DiagramElementMapping, DocumentedElement {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Domain Class</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The domain class of the
     * mapping. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Domain Class</em>' attribute.
     * @see #setDomainClass(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getAbstractNodeMapping_DomainClass()
     * @model dataType="viewpoint.description.TypeName" required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.AbstractNodeMapping#getDomainClass
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
     * {@link org.eclipse.sirius.viewpoint.description.NodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bordered Node Mappings</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The mapping for nodes that
     * are on the border of nodes created by this mapping. <!-- end-model-doc
     * -->
     * 
     * @return the value of the '<em>Bordered Node Mappings</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getAbstractNodeMapping_BorderedNodeMappings()
     * @model type="viewpoint.description.NodeMapping" containment="true"
     *        resolveProxies="true" keys="name"
     * @generated
     */
    EList<NodeMapping> getBorderedNodeMappings();

    /**
     * Returns the value of the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.NodeMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Bordered Node Mappings</em>' reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Reused Bordered Node Mappings</em>'
     *         reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getAbstractNodeMapping_ReusedBorderedNodeMappings()
     * @model type="org.eclipse.sirius.description.NodeMapping"
     * @generated
     */
    EList<NodeMapping> getReusedBorderedNodeMappings();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return all nodes that have been created by this mapping. <!--
     * end-model-doc -->
     * 
     * @model kind="operation" type="org.eclipse.sirius.DDiagramElement"
     * @generated
     */
    @Deprecated
    EList<DDiagramElement> getDNodesDone();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return the node that has been created by this mapping and the specified
     * EObject as semantic element.
     * 
     * @param eObject
     *            The semantic element. <!-- end-model-doc -->
     * @model type="org.eclipse.sirius.DDiagramElement"
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
