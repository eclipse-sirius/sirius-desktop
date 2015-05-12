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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Itemized List</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.ItemizedList#getMark <em>
 * Mark</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.ItemizedList#getListitem
 * <em>Listitem</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getItemizedList()
 * @model
 * @generated
 */
public interface ItemizedList extends EObject {
    /**
     * Returns the value of the '<em><b>Mark</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mark</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mark</em>' attribute.
     * @see #setMark(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getItemizedList_Mark()
     * @model
     * @generated
     */
    String getMark();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.ItemizedList#getMark
     * <em>Mark</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mark</em>' attribute.
     * @see #getMark()
     * @generated
     */
    void setMark(String value);

    /**
     * Returns the value of the '<em><b>Listitem</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.docbook.ListItem}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Listitem</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Listitem</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getItemizedList_Listitem()
     * @model containment="true"
     * @generated
     */
    EList<ListItem> getListitem();

} // ItemizedList
