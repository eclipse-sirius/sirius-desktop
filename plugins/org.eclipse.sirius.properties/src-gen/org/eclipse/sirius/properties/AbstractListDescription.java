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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract List Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractListDescription#getValueExpression <em>Value Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractListDescription#getDisplayExpression <em>Display
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractListDescription#getOnClickOperation <em>On Click
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractListDescription#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractListDescription#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractListDescription#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractListDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractListDescription#getFilterConditionalStylesFromExtendedListExpression
 * <em>Filter Conditional Styles From Extended List Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractListDescription#getFilterActionsFromExtendedListExpression
 * <em>Filter Actions From Extended List Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractListDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractListDescription extends AbstractWidgetDescription {
    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The initial value. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractListDescription_ValueExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractListDescription#getValueExpression <em>Value
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value Expression</em>' attribute.
     * @see #getValueExpression()
     * @generated
     */
    void setValueExpression(String value);

    /**
     * Returns the value of the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Represents how to display the value. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Display Expression</em>' attribute.
     * @see #setDisplayExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractListDescription_DisplayExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getDisplayExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractListDescription#getDisplayExpression
     * <em>Display Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Display Expression</em>' attribute.
     * @see #getDisplayExpression()
     * @generated
     */
    void setDisplayExpression(String value);

    /**
     * Returns the value of the '<em><b>On Click Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Defines the expected behavior when the user clicks on the hyperlink.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>On Click Operation</em>' containment reference.
     * @see #setOnClickOperation(InitialOperation)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractListDescription_OnClickOperation()
     * @model containment="true"
     * @generated
     */
    InitialOperation getOnClickOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractListDescription#getOnClickOperation <em>On
     * Click Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>On Click Operation</em>' containment reference.
     * @see #getOnClickOperation()
     * @generated
     */
    void setOnClickOperation(InitialOperation value);

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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractListDescription_Actions()
     * @model containment="true"
     * @generated
     */
    EList<WidgetAction> getActions();

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(ListWidgetStyle)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractListDescription_Style()
     * @model containment="true"
     * @generated
     */
    ListWidgetStyle getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractListDescription#getStyle <em>Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(ListWidgetStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.properties.ListWidgetConditionalStyle}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractListDescription_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<ListWidgetConditionalStyle> getConditionalStyles();

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(ListDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractListDescription_Extends()
     * @model keys="name"
     * @generated
     */
    ListDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractListDescription#getExtends <em>Extends</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(ListDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Extended List Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Extended List Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Extended List Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromExtendedListExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractListDescription_FilterConditionalStylesFromExtendedListExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromExtendedListExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractListDescription#getFilterConditionalStylesFromExtendedListExpression
     * <em>Filter Conditional Styles From Extended List Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Extended List Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromExtendedListExpression()
     * @generated
     */
    void setFilterConditionalStylesFromExtendedListExpression(String value);

    /**
     * Returns the value of the '<em><b>Filter Actions From Extended List Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Actions From Extended List Expression</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Actions From Extended List Expression</em>' attribute.
     * @see #setFilterActionsFromExtendedListExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractListDescription_FilterActionsFromExtendedListExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterActionsFromExtendedListExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractListDescription#getFilterActionsFromExtendedListExpression
     * <em>Filter Actions From Extended List Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Actions From Extended List Expression</em>' attribute.
     * @see #getFilterActionsFromExtendedListExpression()
     * @generated
     */
    void setFilterActionsFromExtendedListExpression(String value);

} // AbstractListDescription
