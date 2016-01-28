/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.properties;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.sirius.properties.ContainerDescription#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.ContainerDescription#getWidgets <em>Widgets</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.ContainerDescription#getDynamicMappings <em>Dynamic Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getContainerDescription()
 * @model
 * @generated
 */
public interface ContainerDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Identifier</em>' attribute.
     * @see #setIdentifier(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getContainerDescription_Identifier()
     * @model required="true"
     * @generated
     */
    String getIdentifier();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.ContainerDescription#getIdentifier <em>Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Identifier</em>' attribute.
     * @see #getIdentifier()
     * @generated
     */
    void setIdentifier(String value);

    /**
     * Returns the value of the '<em><b>Widgets</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.sirius.properties.WidgetDescription}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Widgets</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Widgets</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getContainerDescription_Widgets()
     * @model containment="true"
     * @generated
     */
    EList<WidgetDescription> getWidgets();

    /**
     * Returns the value of the '<em><b>Dynamic Mappings</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.sirius.properties.DynamicMappingFor}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dynamic Mappings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dynamic Mappings</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getContainerDescription_DynamicMappings()
     * @model containment="true"
     * @generated
     */
    EList<DynamicMappingFor> getDynamicMappings();

} // ContainerDescription
