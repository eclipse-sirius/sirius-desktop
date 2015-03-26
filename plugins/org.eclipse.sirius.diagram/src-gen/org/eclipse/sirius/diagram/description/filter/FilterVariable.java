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
package org.eclipse.sirius.diagram.description.filter;

import org.eclipse.sirius.viewpoint.description.SelectionDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Variable</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.FilterVariable#getName
 * <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getFilterVariable()
 * @model
 * @generated
 */
public interface FilterVariable extends SelectionDescription {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Name of
     * the variable. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getFilterVariable_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.filter.FilterVariable#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // FilterVariable
