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
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Dynamic Mapping Switch</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.DynamicMappingSwitch#getSwitchExpression
 * <em>Switch Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DynamicMappingSwitch#getCases <em>
 * Cases</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingSwitch()
 * @model
 * @generated
 */
public interface DynamicMappingSwitch extends EObject {
    /**
     * Returns the value of the '<em><b>Switch Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Switch Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Switch Expression</em>' attribute.
     * @see #setSwitchExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingSwitch_SwitchExpression()
     * @model required="true"
     * @generated
     */
    String getSwitchExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.DynamicMappingSwitch#getSwitchExpression
     * <em>Switch Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Switch Expression</em>' attribute.
     * @see #getSwitchExpression()
     * @generated
     */
    void setSwitchExpression(String value);

    /**
     * Returns the value of the '<em><b>Cases</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.properties.DynamicMappingCase}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cases</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Cases</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingSwitch_Cases()
     * @model containment="true" required="true"
     * @generated
     */
    EList<DynamicMappingCase> getCases();

} // DynamicMappingSwitch
