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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>External Java Action</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getExternalJavaAction()
 * @model
 * @generated
 */
public interface ExternalJavaAction extends MenuItemDescriptionWithIcon, ContainerModelOperation, GroupMenuItem {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Identifier for your External Java Action provided through extension points. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getExternalJavaAction_Id()
     * @model required="true"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction#getId
     * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Parameters</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Parameters</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getExternalJavaAction_Parameters()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ExternalJavaActionParameter> getParameters();

} // ExternalJavaAction
