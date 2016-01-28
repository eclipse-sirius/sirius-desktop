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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Mapping For</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.sirius.properties.DynamicMappingFor#getIterator <em>Iterator</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.DynamicMappingFor#getDomainClassExpression <em>Domain Class Expression</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.DynamicMappingFor#getSwitch <em>Switch</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingFor()
 * @model
 * @generated
 */
public interface DynamicMappingFor extends EObject {
    /**
     * Returns the value of the '<em><b>Iterator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Iterator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Iterator</em>' attribute.
     * @see #setIterator(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingFor_Iterator()
     * @model required="true"
     * @generated
     */
    String getIterator();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DynamicMappingFor#getIterator <em>Iterator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Iterator</em>' attribute.
     * @see #getIterator()
     * @generated
     */
    void setIterator(String value);

    /**
     * Returns the value of the '<em><b>Domain Class Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Domain Class Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Domain Class Expression</em>' attribute.
     * @see #setDomainClassExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingFor_DomainClassExpression()
     * @model required="true"
     * @generated
     */
    String getDomainClassExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DynamicMappingFor#getDomainClassExpression <em>Domain Class Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Domain Class Expression</em>' attribute.
     * @see #getDomainClassExpression()
     * @generated
     */
    void setDomainClassExpression(String value);

    /**
     * Returns the value of the '<em><b>Switch</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Switch</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Switch</em>' containment reference.
     * @see #setSwitch(DynamicMappingSwitch)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingFor_Switch()
     * @model containment="true" required="true"
     * @generated
     */
    DynamicMappingSwitch getSwitch();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DynamicMappingFor#getSwitch <em>Switch</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Switch</em>' containment reference.
     * @see #getSwitch()
     * @generated
     */
    void setSwitch(DynamicMappingSwitch value);

} // DynamicMappingFor
