/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.metamodel.table.description;

import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Table Navigation Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription#getTableDescription
 * <em>Table Description</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableNavigationDescription()
 * @model
 * @generated
 */
public interface TableNavigationDescription extends RepresentationNavigationDescription {
    /**
     * Returns the value of the '<em><b>Table Description</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Description</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Table Description</em>' reference.
     * @see #setTableDescription(TableDescription)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableNavigationDescription_TableDescription()
     * @model required="true"
     * @generated
     */
    TableDescription getTableDescription();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription#getTableDescription
     * <em>Table Description</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Table Description</em>' reference.
     * @see #getTableDescription()
     * @generated
     */
    void setTableDescription(TableDescription value);

} // TableNavigationDescription
