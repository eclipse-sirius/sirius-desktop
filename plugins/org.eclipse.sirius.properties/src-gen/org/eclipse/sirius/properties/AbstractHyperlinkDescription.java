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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Hyperlink Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getValueExpression <em>Value
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getDisplayExpression <em>Display
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getFilterConditionalStylesFromExtendedHyperlinkExpression
 * <em>Filter Conditional Styles From Extended Hyperlink Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getFilterActionsFromExtendedHyperlinkExpression
 * <em>Filter Actions From Extended Hyperlink Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractHyperlinkDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractHyperlinkDescription extends AbstractWidgetDescription {
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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractHyperlinkDescription_ValueExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getValueExpression
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractHyperlinkDescription_DisplayExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getDisplayExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getDisplayExpression
     * <em>Display Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Display Expression</em>' attribute.
     * @see #getDisplayExpression()
     * @generated
     */
    void setDisplayExpression(String value);

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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractHyperlinkDescription_InitialOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getInitialOperation
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
     * @see #setStyle(HyperlinkWidgetStyle)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractHyperlinkDescription_Style()
     * @model containment="true"
     * @generated
     */
    HyperlinkWidgetStyle getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getStyle
     * <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(HyperlinkWidgetStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractHyperlinkDescription_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<HyperlinkWidgetConditionalStyle> getConditionalStyles();

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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractHyperlinkDescription_Actions()
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
     * @see #setExtends(HyperlinkDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractHyperlinkDescription_Extends()
     * @model
     * @generated
     */
    HyperlinkDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getExtends
     * <em>Extends</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(HyperlinkDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Extended Hyperlink Expression</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Extended Hyperlink Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Extended Hyperlink Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromExtendedHyperlinkExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractHyperlinkDescription_FilterConditionalStylesFromExtendedHyperlinkExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromExtendedHyperlinkExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getFilterConditionalStylesFromExtendedHyperlinkExpression
     * <em>Filter Conditional Styles From Extended Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Extended Hyperlink Expression</em>'
     *            attribute.
     * @see #getFilterConditionalStylesFromExtendedHyperlinkExpression()
     * @generated
     */
    void setFilterConditionalStylesFromExtendedHyperlinkExpression(String value);

    /**
     * Returns the value of the '<em><b>Filter Actions From Extended Hyperlink Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Actions From Extended Hyperlink Expression</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Actions From Extended Hyperlink Expression</em>' attribute.
     * @see #setFilterActionsFromExtendedHyperlinkExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractHyperlinkDescription_FilterActionsFromExtendedHyperlinkExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterActionsFromExtendedHyperlinkExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getFilterActionsFromExtendedHyperlinkExpression
     * <em>Filter Actions From Extended Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Filter Actions From Extended Hyperlink Expression</em>' attribute.
     * @see #getFilterActionsFromExtendedHyperlinkExpression()
     * @generated
     */
    void setFilterActionsFromExtendedHyperlinkExpression(String value);

} // AbstractHyperlinkDescription
