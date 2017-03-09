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

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Label Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractLabelDescription#getValueExpression <em>Value Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractLabelDescription#getDisplayExpression <em>Display
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractLabelDescription#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractLabelDescription#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractLabelDescription#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractLabelDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractLabelDescription#getFilterConditionalStylesFromExtendedLabelExpression
 * <em>Filter Conditional Styles From Extended Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractLabelDescription#getFilterActionsFromExtendedLabelExpression
 * <em>Filter Actions From Extended Label Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractLabelDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractLabelDescription extends AbstractWidgetDescription {
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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractLabelDescription_ValueExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getValueExpression <em>Value
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value Expression</em>' attribute.
     * @see #getValueExpression()
     * @generated
     */
    void setValueExpression(String value);

    /**
     * Returns the value of the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Display Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Display Expression</em>' attribute.
     * @see #setDisplayExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractLabelDescription_DisplayExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getDisplayExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getDisplayExpression
     * <em>Display Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Display Expression</em>' attribute.
     * @see #getDisplayExpression()
     * @generated
     */
    void setDisplayExpression(String value);

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(LabelWidgetStyle)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractLabelDescription_Style()
     * @model containment="true"
     * @generated
     */
    LabelWidgetStyle getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getStyle <em>Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(LabelWidgetStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.properties.LabelWidgetConditionalStyle}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractLabelDescription_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<LabelWidgetConditionalStyle> getConditionalStyles();

    /**
     * Returns the value of the '<em><b>Actions</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.WidgetAction}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Actions</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Actions</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractLabelDescription_Actions()
     * @model containment="true"
     * @generated
     */
    EList<WidgetAction> getActions();

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(LabelDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractLabelDescription_Extends()
     * @model keys="name"
     * @generated
     */
    LabelDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getExtends
     * <em>Extends</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(LabelDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Extended Label Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Extended Label Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Extended Label Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromExtendedLabelExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractLabelDescription_FilterConditionalStylesFromExtendedLabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromExtendedLabelExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getFilterConditionalStylesFromExtendedLabelExpression
     * <em>Filter Conditional Styles From Extended Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Extended Label Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromExtendedLabelExpression()
     * @generated
     */
    void setFilterConditionalStylesFromExtendedLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>Filter Actions From Extended Label Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Actions From Extended Label Expression</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Actions From Extended Label Expression</em>' attribute.
     * @see #setFilterActionsFromExtendedLabelExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractLabelDescription_FilterActionsFromExtendedLabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterActionsFromExtendedLabelExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getFilterActionsFromExtendedLabelExpression
     * <em>Filter Actions From Extended Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Actions From Extended Label Expression</em>' attribute.
     * @see #getFilterActionsFromExtendedLabelExpression()
     * @generated
     */
    void setFilterActionsFromExtendedLabelExpression(String value);

} // AbstractLabelDescription
