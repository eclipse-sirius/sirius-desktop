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
 * <em><b>Author</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Author#getEmail <em>Email
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Author#getPersonname <em>
 * Personname</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Author#getAddress <em>
 * Address</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getAuthor()
 * @model
 * @generated
 */
public interface Author extends EObject {
    /**
     * Returns the value of the '<em><b>Email</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Email</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Email</em>' attribute.
     * @see #setEmail(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getAuthor_Email()
     * @model
     * @generated
     */
    String getEmail();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Author#getEmail
     * <em>Email</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Email</em>' attribute.
     * @see #getEmail()
     * @generated
     */
    void setEmail(String value);

    /**
     * Returns the value of the '<em><b>Personname</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Personname</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Personname</em>' attribute.
     * @see #setPersonname(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getAuthor_Personname()
     * @model
     * @generated
     */
    String getPersonname();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Author#getPersonname
     * <em>Personname</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Personname</em>' attribute.
     * @see #getPersonname()
     * @generated
     */
    void setPersonname(String value);

    /**
     * Returns the value of the '<em><b>Address</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Address</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Address</em>' attribute.
     * @see #setAddress(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getAuthor_Address()
     * @model
     * @generated
     */
    String getAddress();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Author#getAddress
     * <em>Address</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Address</em>' attribute.
     * @see #getAddress()
     * @generated
     */
    void setAddress(String value);

} // Author
