/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.docbook;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Ordered List</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.OrderedList#getNumeration
 * <em>Numeration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getOrderedList()
 * @model
 * @generated
 */
public interface OrderedList extends EObject {
    /**
     * Returns the value of the '<em><b>Numeration</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Numeration</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Numeration</em>' attribute.
     * @see #setNumeration(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getOrderedList_Numeration()
     * @model
     * @generated
     */
    String getNumeration();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.OrderedList#getNumeration
     * <em>Numeration</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Numeration</em>' attribute.
     * @see #getNumeration()
     * @generated
     */
    void setNumeration(String value);

} // OrderedList
