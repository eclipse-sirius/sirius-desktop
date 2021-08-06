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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Operation Action</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.OperationAction#getView <em>View</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.OperationAction#getInitialOperation <em>Initial
 * Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getOperationAction()
 * @model
 * @generated
 */
public interface OperationAction extends MenuItemDescriptionWithIcon, GroupMenuItem {
    /**
     * Returns the value of the '<em><b>View</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>View</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>View</em>' containment reference.
     * @see #setView(ContainerViewVariable)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getOperationAction_View()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    ContainerViewVariable getView();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.OperationAction#getView
     * <em>View</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>View</em>' containment reference.
     * @see #getView()
     * @generated
     */
    void setView(ContainerViewVariable value);

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
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getOperationAction_InitialOperation()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.OperationAction#getInitialOperation
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

} // OperationAction
