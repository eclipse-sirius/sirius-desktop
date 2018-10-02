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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Switch</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Swich contains 1 or more Case and 1 Default Case. If no Case condition return true, Default
 * Case is executed. If there are more Case condition who return true it's the first Condition Case true who is
 * executed. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.Switch#getCases <em>Cases</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.Switch#getDefault <em>Default</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSwitch()
 * @model
 * @generated
 */
public interface Switch extends ModelOperation {
    /**
     * Returns the value of the '<em><b>Cases</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.Case}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cases</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Cases</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSwitch_Cases()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    EList<Case> getCases();

    /**
     * Returns the value of the '<em><b>Default</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Default</em>' containment reference.
     * @see #setDefault(Default)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getSwitch_Default()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    Default getDefault();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.Switch#getDefault <em>Default</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Default</em>' containment reference.
     * @see #getDefault()
     * @generated
     */
    void setDefault(Default value);

} // Switch
