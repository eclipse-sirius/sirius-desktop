/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Set Value</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> This operation allows to set a value of a feature of the current context. <!-- end-model-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.SetValue#getFeatureName <em>Feature Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.SetValue#getValueExpression <em>Value Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSetValue()
 * @model
 * @generated
 */
public interface SetValue extends ContainerModelOperation {
    /**
     * Returns the value of the '<em><b>Feature Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The name of the feature to set. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Feature Name</em>' attribute.
     * @see #setFeatureName(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSetValue_FeatureName()
     * @model dataType="org.eclipse.sirius.viewpoint.description.FeatureName" required="true"
     * @generated
     */
    String getFeatureName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.SetValue#getFeatureName <em>Feature
     * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Feature Name</em>' attribute.
     * @see #getFeatureName()
     * @generated
     */
    void setFeatureName(String value);

    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> An expression computing the value to set. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSetValue_ValueExpression()
     * @model dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation =
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='any type supported by the
     *        feature.'"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.SetValue#getValueExpression <em>Value
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value Expression</em>' attribute.
     * @see #getValueExpression()
     * @generated
     */
    void setValueExpression(String value);

} // SetValue
