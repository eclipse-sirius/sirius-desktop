/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.tool;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Delete Hook Parameter</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DeleteHookParameter#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DeleteHookParameter#getValue
 * <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDeleteHookParameter()
 * @model
 * @generated
 */
public interface DeleteHookParameter extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Name of
     * the parameter <!-- end-model-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDeleteHookParameter_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.DeleteHookParameter#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Value
     * of the parameter. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDeleteHookParameter_Value()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an EObject.'"
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.DeleteHookParameter#getValue
     * <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

} // DeleteHookParameter
