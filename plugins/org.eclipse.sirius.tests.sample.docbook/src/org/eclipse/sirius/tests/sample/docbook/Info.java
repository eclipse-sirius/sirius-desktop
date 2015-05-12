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
 * <em><b>Info</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Info#getAuthor <em>Author
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Info#getDate <em>Date
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Info#getPubdate <em>
 * Pubdate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getInfo()
 * @model
 * @generated
 */
public interface Info extends EObject {
    /**
     * Returns the value of the '<em><b>Author</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.docbook.Author}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Author</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Author</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getInfo_Author()
     * @model containment="true"
     * @generated
     */
    EList<Author> getAuthor();

    /**
     * Returns the value of the '<em><b>Date</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Date</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Date</em>' attribute.
     * @see #setDate(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getInfo_Date()
     * @model
     * @generated
     */
    String getDate();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Info#getDate
     * <em>Date</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Date</em>' attribute.
     * @see #getDate()
     * @generated
     */
    void setDate(String value);

    /**
     * Returns the value of the '<em><b>Pubdate</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pubdate</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Pubdate</em>' attribute.
     * @see #setPubdate(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getInfo_Pubdate()
     * @model
     * @generated
     */
    String getPubdate();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Info#getPubdate
     * <em>Pubdate</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Pubdate</em>' attribute.
     * @see #getPubdate()
     * @generated
     */
    void setPubdate(String value);

} // Info
