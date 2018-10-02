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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Create Instance</b></em>'. <!-- end-user-doc
 * -->
 *
 * <!-- begin-model-doc --> This operation allows to create a new instance. The context must be the container of the new
 * instance. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getTypeName <em>Type Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getReferenceName <em>Reference Name</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getVariableName <em>Variable Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getCreateInstance()
 * @model
 * @generated
 */
public interface CreateInstance extends ContainerModelOperation {
    /**
     * Returns the value of the '<em><b>Type Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The type of the new instance. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Type Name</em>' attribute.
     * @see #setTypeName(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getCreateInstance_TypeName()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName"
     * @generated
     */
    String getTypeName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getTypeName <em>Type
     * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Type Name</em>' attribute.
     * @see #getTypeName()
     * @generated
     */
    void setTypeName(String value);

    /**
     * Returns the value of the '<em><b>Reference Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> The name of the reference that contained the new instance. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Reference Name</em>' attribute.
     * @see #setReferenceName(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getCreateInstance_ReferenceName()
     * @model dataType="org.eclipse.sirius.viewpoint.description.FeatureName" required="true"
     * @generated
     */
    String getReferenceName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getReferenceName
     * <em>Reference Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Reference Name</em>' attribute.
     * @see #getReferenceName()
     * @generated
     */
    void setReferenceName(String value);

    /**
     * Returns the value of the '<em><b>Variable Name</b></em>' attribute. The default value is <code>"instance"</code>.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Once the instance is created, a new
     * variable will be bound with the name given here and will be available to any contained operation. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Variable Name</em>' attribute.
     * @see #setVariableName(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getCreateInstance_VariableName()
     * @model default="instance"
     * @generated
     */
    String getVariableName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getVariableName
     * <em>Variable Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Variable Name</em>' attribute.
     * @see #getVariableName()
     * @generated
     */
    void setVariableName(String value);

} // CreateInstance
