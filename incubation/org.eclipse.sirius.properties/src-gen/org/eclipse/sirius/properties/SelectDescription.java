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

import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Select Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.sirius.properties.SelectDescription#getValueExpression <em>Value Expression</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.SelectDescription#getInitialOperation <em>Initial Operation</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.SelectDescription#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.SelectDescription#getCandidatesExpression <em>Candidates Expression</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.SelectDescription#getCandidateDisplayExpression <em>Candidate Display Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getSelectDescription()
 * @model
 * @generated
 */
public interface SelectDescription extends WidgetDescription {
    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSelectDescription_ValueExpression()
     * @model dataType="org.eclipse.sirius.expression.Expression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.SelectDescription#getValueExpression <em>Value Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Value Expression</em>' attribute.
     * @see #getValueExpression()
     * @generated
     */
    void setValueExpression(String value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial Operation</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSelectDescription_InitialOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.SelectDescription#getInitialOperation <em>Initial Operation</em>}' containment reference.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

    /**
     * Returns the value of the '<em><b>Multiple</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Multiple</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Multiple</em>' attribute.
     * @see #setMultiple(boolean)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSelectDescription_Multiple()
     * @model
     * @generated
     */
    boolean isMultiple();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.SelectDescription#isMultiple
     * <em>Multiple</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Multiple</em>' attribute.
     * @see #isMultiple()
     * @generated
     */
    void setMultiple(boolean value);

    /**
     * Returns the value of the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Candidates Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Candidates Expression</em>' attribute.
     * @see #setCandidatesExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSelectDescription_CandidatesExpression()
     * @model dataType="org.eclipse.sirius.expression.Expression"
     * @generated
     */
    String getCandidatesExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.SelectDescription#getCandidatesExpression <em>Candidates Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Candidates Expression</em>' attribute.
     * @see #getCandidatesExpression()
     * @generated
     */
    void setCandidatesExpression(String value);

    /**
     * Returns the value of the '<em><b>Candidate Display Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Candidate Display Expression</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Candidate Display Expression</em>' attribute.
     * @see #setCandidateDisplayExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSelectDescription_CandidateDisplayExpression()
     * @model dataType="org.eclipse.sirius.expression.Expression"
     * @generated
     */
    String getCandidateDisplayExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.SelectDescription#getCandidateDisplayExpression <em>Candidate Display Expression</em>}' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Candidate Display Expression</em>' attribute.
     * @see #getCandidateDisplayExpression()
     * @generated
     */
    void setCandidateDisplayExpression(String value);

} // SelectDescription
