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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Text Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractTextDescription#getValueExpression <em>Value Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractTextDescription#getInitialOperation <em>Initial Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractTextDescription#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractTextDescription#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractTextDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractTextDescription#getFilterConditionalStylesFromExtendedTextExpression
 * <em>Filter Conditional Styles From Extended Text Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractTextDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractTextDescription extends AbstractWidgetDescription {
    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractTextDescription_ValueExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractTextDescription#getValueExpression <em>Value
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value Expression</em>' attribute.
     * @see #getValueExpression()
     * @generated
     */
    void setValueExpression(String value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial Operation</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractTextDescription_InitialOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractTextDescription#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(TextWidgetStyle)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractTextDescription_Style()
     * @model containment="true"
     * @generated
     */
    TextWidgetStyle getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractTextDescription#getStyle <em>Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(TextWidgetStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.properties.TextWidgetConditionalStyle}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractTextDescription_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<TextWidgetConditionalStyle> getConditionalStyles();

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(TextDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractTextDescription_Extends()
     * @model keys="name"
     * @generated
     */
    TextDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractTextDescription#getExtends <em>Extends</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(TextDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Extended Text Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Extended Text Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Extended Text Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromExtendedTextExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractTextDescription_FilterConditionalStylesFromExtendedTextExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromExtendedTextExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractTextDescription#getFilterConditionalStylesFromExtendedTextExpression
     * <em>Filter Conditional Styles From Extended Text Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Extended Text Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromExtendedTextExpression()
     * @generated
     */
    void setFilterConditionalStylesFromExtendedTextExpression(String value);

} // AbstractTextDescription
