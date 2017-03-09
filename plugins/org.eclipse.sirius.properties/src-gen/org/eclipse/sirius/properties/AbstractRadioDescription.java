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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Radio Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractRadioDescription#getValueExpression <em>Value Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractRadioDescription#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractRadioDescription#getCandidatesExpression <em>Candidates
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractRadioDescription#getCandidateDisplayExpression <em>Candidate Display
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractRadioDescription#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractRadioDescription#getNumberOfColumns <em>Number Of Columns</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractRadioDescription#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractRadioDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractRadioDescription#getFilterConditionalStylesFromExtendedRadioExpression
 * <em>Filter Conditional Styles From Extended Radio Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractRadioDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractRadioDescription extends AbstractWidgetDescription {
    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The initial selected values of the radio. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractRadioDescription_ValueExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getValueExpression <em>Value
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * the radio. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractRadioDescription_InitialOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getInitialOperation
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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractRadioDescription_CandidatesExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getCandidatesExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getCandidatesExpression
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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractRadioDescription_CandidateDisplayExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getCandidateDisplayExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getCandidateDisplayExpression <em>Candidate
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
     * @see #setStyle(RadioWidgetStyle)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractRadioDescription_Style()
     * @model containment="true"
     * @generated
     */
    RadioWidgetStyle getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getStyle <em>Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(RadioWidgetStyle value);

    /**
     * Returns the value of the '<em><b>Number Of Columns</b></em>' attribute. The default value is <code>"-1"</code>.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The number of columns to use to display
     * the candidates. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Number Of Columns</em>' attribute.
     * @see #setNumberOfColumns(int)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractRadioDescription_NumberOfColumns()
     * @model default="-1"
     * @generated
     */
    int getNumberOfColumns();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getNumberOfColumns
     * <em>Number Of Columns</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Number Of Columns</em>' attribute.
     * @see #getNumberOfColumns()
     * @generated
     */
    void setNumberOfColumns(int value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.properties.RadioWidgetConditionalStyle}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractRadioDescription_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<RadioWidgetConditionalStyle> getConditionalStyles();

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(RadioDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractRadioDescription_Extends()
     * @model keys="name"
     * @generated
     */
    RadioDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getExtends
     * <em>Extends</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(RadioDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Extended Radio Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Extended Radio Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Extended Radio Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromExtendedRadioExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractRadioDescription_FilterConditionalStylesFromExtendedRadioExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromExtendedRadioExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getFilterConditionalStylesFromExtendedRadioExpression
     * <em>Filter Conditional Styles From Extended Radio Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Extended Radio Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromExtendedRadioExpression()
     * @generated
     */
    void setFilterConditionalStylesFromExtendedRadioExpression(String value);

} // AbstractRadioDescription
