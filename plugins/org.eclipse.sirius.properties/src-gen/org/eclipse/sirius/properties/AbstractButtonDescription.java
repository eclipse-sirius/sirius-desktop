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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Button Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractButtonDescription#getButtonLabelExpression <em>Button Label
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractButtonDescription#getImageExpression <em>Image Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractButtonDescription#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractButtonDescription#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractButtonDescription#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractButtonDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractButtonDescription#getFilterConditionalStylesFromExtendedButtonExpression
 * <em>Filter Conditional Styles From Extended Button Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractButtonDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractButtonDescription extends AbstractWidgetDescription {
    /**
     * Returns the value of the '<em><b>Button Label Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Button Label Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Button Label Expression</em>' attribute.
     * @see #setButtonLabelExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractButtonDescription_ButtonLabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getButtonLabelExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getButtonLabelExpression
     * <em>Button Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Button Label Expression</em>' attribute.
     * @see #getButtonLabelExpression()
     * @generated
     */
    void setButtonLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>Image Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Image Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Image Expression</em>' attribute.
     * @see #setImageExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractButtonDescription_ImageExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getImageExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getImageExpression
     * <em>Image Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Image Expression</em>' attribute.
     * @see #getImageExpression()
     * @generated
     */
    void setImageExpression(String value);

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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractButtonDescription_InitialOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getInitialOperation
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
     * @see #setStyle(ButtonWidgetStyle)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractButtonDescription_Style()
     * @model containment="true"
     * @generated
     */
    ButtonWidgetStyle getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getStyle <em>Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(ButtonWidgetStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.properties.ButtonWidgetConditionalStyle}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractButtonDescription_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<ButtonWidgetConditionalStyle> getConditionalStyles();

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(ButtonDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractButtonDescription_Extends()
     * @model keys="name"
     * @generated
     */
    ButtonDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getExtends
     * <em>Extends</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(ButtonDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Extended Button Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Extended Button Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Extended Button Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromExtendedButtonExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractButtonDescription_FilterConditionalStylesFromExtendedButtonExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromExtendedButtonExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getFilterConditionalStylesFromExtendedButtonExpression
     * <em>Filter Conditional Styles From Extended Button Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Extended Button Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromExtendedButtonExpression()
     * @generated
     */
    void setFilterConditionalStylesFromExtendedButtonExpression(String value);

} // AbstractButtonDescription
