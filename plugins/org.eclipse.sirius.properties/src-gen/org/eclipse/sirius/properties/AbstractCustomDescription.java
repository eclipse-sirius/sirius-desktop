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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Custom Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractCustomDescription#getCustomExpressions <em>Custom
 * Expressions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractCustomDescription#getCustomOperations <em>Custom
 * Operations</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractCustomDescription#getStyle <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractCustomDescription#getConditionalStyles <em>Conditional
 * Styles</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractCustomDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractCustomDescription#getFilterConditionalStylesFromExtendedCustomExpression
 * <em>Filter Conditional Styles From Extended Custom Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractCustomDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractCustomDescription extends AbstractWidgetDescription {
    /**
     * Returns the value of the '<em><b>Custom Expressions</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.properties.CustomExpression}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Custom Expressions</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Custom Expressions</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractCustomDescription_CustomExpressions()
     * @model containment="true"
     * @generated
     */
    EList<CustomExpression> getCustomExpressions();

    /**
     * Returns the value of the '<em><b>Custom Operations</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.properties.CustomOperation}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Custom Operations</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Custom Operations</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractCustomDescription_CustomOperations()
     * @model containment="true"
     * @generated
     */
    EList<CustomOperation> getCustomOperations();

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(CustomWidgetStyle)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractCustomDescription_Style()
     * @model containment="true"
     * @generated
     */
    CustomWidgetStyle getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractCustomDescription#getStyle <em>Style</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(CustomWidgetStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.properties.CustomWidgetConditionalStyle}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractCustomDescription_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<CustomWidgetConditionalStyle> getConditionalStyles();

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(CustomDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractCustomDescription_Extends()
     * @model
     * @generated
     */
    CustomDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractCustomDescription#getExtends
     * <em>Extends</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(CustomDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Extended Custom Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Extended Custom Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Extended Custom Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromExtendedCustomExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractCustomDescription_FilterConditionalStylesFromExtendedCustomExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromExtendedCustomExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractCustomDescription#getFilterConditionalStylesFromExtendedCustomExpression
     * <em>Filter Conditional Styles From Extended Custom Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Extended Custom Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromExtendedCustomExpression()
     * @generated
     */
    void setFilterConditionalStylesFromExtendedCustomExpression(String value);

} // AbstractCustomDescription
