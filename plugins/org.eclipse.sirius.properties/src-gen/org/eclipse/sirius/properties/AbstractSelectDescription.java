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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Select Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractSelectDescription#getValueExpression <em>Value Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractSelectDescription#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractSelectDescription#getCandidatesExpression <em>Candidates
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractSelectDescription#getCandidateDisplayExpression <em>Candidate
 * Display Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractSelectDescription#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractSelectDescription#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractSelectDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractSelectDescription#getFilterConditionalStylesFromExtendedSelectExpression
 * <em>Filter Conditional Styles From Extended Select Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractSelectDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractSelectDescription extends AbstractWidgetDescription {
    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The initial selected values of the combo. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractSelectDescription_ValueExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getValueExpression
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value Expression</em>' attribute.
     * @see #getValueExpression()
     * @generated
     */
    void setValueExpression(String value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Defines the behavior executed when the end-user updates the value of
     * the combo. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractSelectDescription_InitialOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

    /**
     * Returns the value of the '<em><b>Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Defines the various proposals available. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Candidates Expression</em>' attribute.
     * @see #setCandidatesExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractSelectDescription_CandidatesExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getCandidatesExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getCandidatesExpression
     * <em>Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Candidates Expression</em>' attribute.
     * @see #getCandidatesExpression()
     * @generated
     */
    void setCandidatesExpression(String value);

    /**
     * Returns the value of the '<em><b>Candidate Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Indicates how to display the input value. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Candidate Display Expression</em>' attribute.
     * @see #setCandidateDisplayExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractSelectDescription_CandidateDisplayExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getCandidateDisplayExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getCandidateDisplayExpression <em>Candidate
     * Display Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Candidate Display Expression</em>' attribute.
     * @see #getCandidateDisplayExpression()
     * @generated
     */
    void setCandidateDisplayExpression(String value);

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(SelectWidgetStyle)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractSelectDescription_Style()
     * @model containment="true"
     * @generated
     */
    SelectWidgetStyle getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getStyle <em>Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(SelectWidgetStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.properties.SelectWidgetConditionalStyle}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractSelectDescription_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<SelectWidgetConditionalStyle> getConditionalStyles();

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(SelectDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractSelectDescription_Extends()
     * @model keys="name"
     * @generated
     */
    SelectDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getExtends
     * <em>Extends</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(SelectDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Extended Select Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Extended Select Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Extended Select Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromExtendedSelectExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractSelectDescription_FilterConditionalStylesFromExtendedSelectExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromExtendedSelectExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getFilterConditionalStylesFromExtendedSelectExpression
     * <em>Filter Conditional Styles From Extended Select Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Extended Select Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromExtendedSelectExpression()
     * @generated
     */
    void setFilterConditionalStylesFromExtendedSelectExpression(String value);

} // AbstractSelectDescription
