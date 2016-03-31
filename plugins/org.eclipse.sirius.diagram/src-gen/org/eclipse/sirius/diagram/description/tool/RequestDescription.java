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

import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Request Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.RequestDescription#getType
 * <em>Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getRequestDescription()
 * @model
 * @generated
 */
public interface RequestDescription extends AbstractToolDescription {
    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> An
     * Identifier for your request. This request will be send through GEF and
     * any EditPolicy handling this request will then be able to perform
     * commands. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getRequestDescription_Type()
     * @model required="true"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.RequestDescription#getType
     * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

} // RequestDescription
