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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Widget Action</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.WidgetAction#getLabelExpression <em>Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WidgetAction#getImageExpression <em>Image Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WidgetAction#getInitialOperation <em>Initial Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetAction()
 * @model
 * @generated
 */
public interface WidgetAction extends EObject {
    /**
     * Returns the value of the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Expression</em>' attribute.
     * @see #setLabelExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetAction_LabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getLabelExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WidgetAction#getLabelExpression <em>Label
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Expression</em>' attribute.
     * @see #getLabelExpression()
     * @generated
     */
    void setLabelExpression(String value);

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
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetAction_ImageExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getImageExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WidgetAction#getImageExpression <em>Image
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Image Expression</em>' attribute.
     * @see #getImageExpression()
     * @generated
     */
    void setImageExpression(String value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Defines the behavior executed when the end-user updates the value of
     * the radio. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetAction_InitialOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WidgetAction#getInitialOperation <em>Initial
     * Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

} // WidgetAction
