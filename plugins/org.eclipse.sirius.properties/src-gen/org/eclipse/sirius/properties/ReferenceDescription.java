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
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Reference Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Represents a reference in the user interface. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.ReferenceDescription#isMultiple
 * <em>Multiple</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.ReferenceDescription#getValueExpression
 * <em>Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.ReferenceDescription#getDisplayExpression
 * <em>Display Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.ReferenceDescription#getOnClickOperation
 * <em>On Click Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ReferenceDescription#getActions
 * <em>Actions</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ReferenceDescription#getStyle
 * <em>Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.ReferenceDescription#getConditionalStyles
 * <em>Conditional Styles</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getReferenceDescription()
 * @model
 * @generated
 */
public interface ReferenceDescription extends WidgetDescription {
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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getReferenceDescription_Multiple()
     * @model
     * @generated
     */
    boolean isMultiple();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.ReferenceDescription#isMultiple
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
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The initial value. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getReferenceDescription_ValueExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.ReferenceDescription#getValueExpression
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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getReferenceDescription_DisplayExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getDisplayExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.ReferenceDescription#getDisplayExpression
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
     * Returns the value of the '<em><b>On Click Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Defines the expected behavior when the user clicks on
     * the hyperlink. <!-- end-model-doc -->
     *
     * @return the value of the '<em>On Click Operation</em>' containment
     *         reference.
     * @see #setOnClickOperation(OperationDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getReferenceDescription_OnClickOperation()
     * @model containment="true"
     * @generated
     */
    InitialOperation getOnClickOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.ReferenceDescription#getOnClickOperation
     * <em>On Click Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>On Click Operation</em>' containment
     *            reference.
     * @see #getOnClickOperation()
     * @generated
     */
    void setOnClickOperation(InitialOperation value);

    /**
     * Returns the value of the '<em><b>Actions</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.properties.WidgetAction}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Actions</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Actions</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getReferenceDescription_Actions()
     * @model containment="true"
     * @generated
     */
    EList<WidgetAction> getActions();

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(ReferenceWidgetStyle)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getReferenceDescription_Style()
     * @model containment="true"
     * @generated
     */
    ReferenceWidgetStyle getStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.ReferenceDescription#getStyle
     * <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(ReferenceWidgetStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.ReferenceWidgetConditionalStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Conditional Styles</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getReferenceDescription_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<ReferenceWidgetConditionalStyle> getConditionalStyles();

} // ReferenceDescription
