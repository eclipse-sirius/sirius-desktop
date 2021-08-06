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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Unset</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.Unset#getFeatureName <em>Feature Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.Unset#getElementExpression <em>Element Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getUnset()
 * @model
 * @generated
 */
public interface Unset extends ContainerModelOperation {
    /**
     * Returns the value of the '<em><b>Feature Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Name of the feature to unset. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Feature Name</em>' attribute.
     * @see #setFeatureName(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getUnset_FeatureName()
     * @model default="" dataType="org.eclipse.sirius.viewpoint.description.FeatureName" required="true"
     * @generated
     */
    String getFeatureName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.Unset#getFeatureName <em>Feature
     * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Feature Name</em>' attribute.
     * @see #getFeatureName()
     * @generated
     */
    void setFeatureName(String value);

    /**
     * Returns the value of the '<em><b>Element Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Expression returning the elements to unset from the feature. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Element Expression</em>' attribute.
     * @see #setElementExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getUnset_ElementExpression()
     * @model dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an
     *        EObject.'"
     * @generated
     */
    String getElementExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.Unset#getElementExpression
     * <em>Element Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Element Expression</em>' attribute.
     * @see #getElementExpression()
     * @generated
     */
    void setElementExpression(String value);

} // Unset
