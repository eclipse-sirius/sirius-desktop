/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.description.tool;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Set Value</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> This operation allows to set a value of a feature of
 * the current context. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.description.tool.SetValue#getFeatureName
 * <em>Feature Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.tool.SetValue#getValueExpression
 * <em>Value Expression</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.description.tool.ToolPackage#getSetValue()
 * @model
 * @generated
 */
public interface SetValue extends ContainerModelOperation {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The name of the feature to
     * set. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Feature Name</em>' attribute.
     * @see #setFeatureName(String)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getSetValue_FeatureName()
     * @model dataType="viewpoint.description.ReferenceName" required="true"
     * @generated
     */
    String getFeatureName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.SetValue#getFeatureName
     * <em>Feature Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Feature Name</em>' attribute.
     * @see #getFeatureName()
     * @generated
     */
    void setFeatureName(String value);

    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The value of the feature.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getSetValue_ValueExpression()
     * @model dataType="viewpoint.description.AcceleoExpression"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.SetValue#getValueExpression
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Value Expression</em>' attribute.
     * @see #getValueExpression()
     * @generated
     */
    void setValueExpression(String value);

} // SetValue
