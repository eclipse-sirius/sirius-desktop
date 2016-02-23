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
 * <em><b>Single Reference Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Represents a single reference in the user interface.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getValueExpression
 * <em>Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getDisplayExpression
 * <em>Display Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getCreateOperation
 * <em>Create Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getSearchOperation
 * <em>Search Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getUnsetOperation
 * <em>Unset Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getOnClickOperation
 * <em>On Click Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getSingleReferenceDescription()
 * @model
 * @generated
 */
public interface SingleReferenceDescription extends WidgetDescription {
    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The initial value. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSingleReferenceDescription_ValueExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getValueExpression
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
     * Represents how to display the value. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Display Expression</em>' attribute.
     * @see #setDisplayExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSingleReferenceDescription_DisplayExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getDisplayExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getDisplayExpression
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
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Defines the expected behavior when the user clicks on
     * the create button. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Create Operation</em>' containment
     *         reference.
     * @see #setCreateOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSingleReferenceDescription_CreateOperation()
     * @model containment="true"
     * @generated
     */
    OperationDescription getCreateOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getCreateOperation
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
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Defines the expected behavior when the user clicks on
     * the search button. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Search Operation</em>' containment
     *         reference.
     * @see #setSearchOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSingleReferenceDescription_SearchOperation()
     * @model containment="true"
     * @generated
     */
    OperationDescription getSearchOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getSearchOperation
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
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Defines the expected behavior when the user clicks on
     * the unset button. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Unset Operation</em>' containment
     *         reference.
     * @see #setUnsetOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSingleReferenceDescription_UnsetOperation()
     * @model containment="true"
     * @generated
     */
    OperationDescription getUnsetOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getUnsetOperation
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
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Defines the expected behavior when the user clicks on
     * the hyperlink. <!-- end-model-doc -->
     *
     * @return the value of the '<em>On Click Operation</em>' containment
     *         reference.
     * @see #setOnClickOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSingleReferenceDescription_OnClickOperation()
     * @model containment="true"
     * @generated
     */
    OperationDescription getOnClickOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getOnClickOperation
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

} // SingleReferenceDescription
