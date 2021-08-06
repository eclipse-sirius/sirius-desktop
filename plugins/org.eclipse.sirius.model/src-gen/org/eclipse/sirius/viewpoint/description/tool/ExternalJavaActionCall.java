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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>External Java Action Call</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> An operation which can be used to call an ExternalJavaAction defined elsewhere. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall#getAction <em>Action</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getExternalJavaActionCall()
 * @model
 * @generated
 */
public interface ExternalJavaActionCall extends MenuItemDescriptionWithIcon, ContainerModelOperation, GroupMenuItem {
    /**
     * Returns the value of the '<em><b>Action</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The action to call. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Action</em>' reference.
     * @see #setAction(ExternalJavaAction)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getExternalJavaActionCall_Action()
     * @model required="true"
     * @generated
     */
    ExternalJavaAction getAction();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall#getAction
     * <em>Action</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Action</em>' reference.
     * @see #getAction()
     * @generated
     */
    void setAction(ExternalJavaAction value);

} // ExternalJavaActionCall
