/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>EAttribute Customization</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.EAttributeCustomization#getAttributeName <em>Attribute
 * Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.EAttributeCustomization#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEAttributeCustomization()
 * @model
 * @generated
 */
public interface EAttributeCustomization extends EStructuralFeatureCustomization {
    /**
     * Returns the value of the '<em><b>Attribute Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attribute Name</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Attribute Name</em>' attribute.
     * @see #setAttributeName(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEAttributeCustomization_AttributeName()
     * @model required="true"
     * @generated
     */
    String getAttributeName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.EAttributeCustomization#getAttributeName
     * <em>Attribute Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Attribute Name</em>' attribute.
     * @see #getAttributeName()
     * @generated
     */
    void setAttributeName(String value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEAttributeCustomization_Value()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='A java Object to
     *        affect as new value of a EAttribute, for example a java primitive.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables view='ecore.EObject | the
     *        current view.' container='ecore.EObject | the semantic container.'"
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.EAttributeCustomization#getValue
     * <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

} // EAttributeCustomization
