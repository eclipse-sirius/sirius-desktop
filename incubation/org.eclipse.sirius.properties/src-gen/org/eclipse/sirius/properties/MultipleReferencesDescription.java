/**
 * Copyright (c) 2016 Obeo.
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

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Multiple References Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Represents a radio button in the user interface.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getValueExpression
 * <em>Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getDisplayExpression
 * <em>Display Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getCreateOperation
 * <em>Create Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getSearchOperation
 * <em>Search Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getUnsetOperation
 * <em>Unset Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getOnClickOperation
 * <em>On Click Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getUpOperation
 * <em>Up Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getDownOperation
 * <em>Down Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getMultipleReferencesDescription()
 * @model
 * @generated
 */
public interface MultipleReferencesDescription extends WidgetDescription {
    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The initial values. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getMultipleReferencesDescription_ValueExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getValueExpression
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Value Expression</em>' attribute.
     * @see #getValueExpression()
     * @generated
     */
    void setValueExpression(String value);

    /**
     * Returns the value of the '<em><b>Display Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Indicates how to display the input value. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Display Expression</em>' attribute.
     * @see #setDisplayExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getMultipleReferencesDescription_DisplayExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getDisplayExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getDisplayExpression
     * <em>Display Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Display Expression</em>' attribute.
     * @see #getDisplayExpression()
     * @generated
     */
    void setDisplayExpression(String value);

    /**
     * Returns the value of the '<em><b>Create Operation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Create Operation</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Create Operation</em>' containment
     *         reference.
     * @see #setCreateOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getMultipleReferencesDescription_CreateOperation()
     * @model containment="true"
     * @generated
     */
    OperationDescription getCreateOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getCreateOperation
     * <em>Create Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Create Operation</em>' containment
     *            reference.
     * @see #getCreateOperation()
     * @generated
     */
    void setCreateOperation(OperationDescription value);

    /**
     * Returns the value of the '<em><b>Search Operation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Search Operation</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Search Operation</em>' containment
     *         reference.
     * @see #setSearchOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getMultipleReferencesDescription_SearchOperation()
     * @model containment="true"
     * @generated
     */
    OperationDescription getSearchOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getSearchOperation
     * <em>Search Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Search Operation</em>' containment
     *            reference.
     * @see #getSearchOperation()
     * @generated
     */
    void setSearchOperation(OperationDescription value);

    /**
     * Returns the value of the '<em><b>Unset Operation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Unset Operation</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Unset Operation</em>' containment
     *         reference.
     * @see #setUnsetOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getMultipleReferencesDescription_UnsetOperation()
     * @model containment="true"
     * @generated
     */
    OperationDescription getUnsetOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getUnsetOperation
     * <em>Unset Operation</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Unset Operation</em>' containment
     *            reference.
     * @see #getUnsetOperation()
     * @generated
     */
    void setUnsetOperation(OperationDescription value);

    /**
     * Returns the value of the '<em><b>On Click Operation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>On Click Operation</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>On Click Operation</em>' containment
     *         reference.
     * @see #setOnClickOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getMultipleReferencesDescription_OnClickOperation()
     * @model containment="true"
     * @generated
     */
    OperationDescription getOnClickOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getOnClickOperation
     * <em>On Click Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>On Click Operation</em>' containment
     *            reference.
     * @see #getOnClickOperation()
     * @generated
     */
    void setOnClickOperation(OperationDescription value);

    /**
     * Returns the value of the '<em><b>Up Operation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Up Operation</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Up Operation</em>' containment reference.
     * @see #setUpOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getMultipleReferencesDescription_UpOperation()
     * @model containment="true"
     * @generated
     */
    OperationDescription getUpOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getUpOperation
     * <em>Up Operation</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Up Operation</em>' containment
     *            reference.
     * @see #getUpOperation()
     * @generated
     */
    void setUpOperation(OperationDescription value);

    /**
     * Returns the value of the '<em><b>Down Operation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Down Operation</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Down Operation</em>' containment reference.
     * @see #setDownOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getMultipleReferencesDescription_DownOperation()
     * @model containment="true"
     * @generated
     */
    OperationDescription getDownOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getDownOperation
     * <em>Down Operation</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Down Operation</em>' containment
     *            reference.
     * @see #getDownOperation()
     * @generated
     */
    void setDownOperation(OperationDescription value);

} // MultipleReferencesDescription
