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
 * <em><b>Book</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Book#getBookinfo <em>
 * Bookinfo</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Book#getChapter <em>
 * Chapter</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Book#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Book#getLang <em>Lang
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Book#getVersion <em>
 * Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getBook()
 * @model
 * @generated
 */
public interface Book extends EObject {
    /**
     * Returns the value of the '<em><b>Bookinfo</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bookinfo</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Bookinfo</em>' containment reference.
     * @see #setBookinfo(Info)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getBook_Bookinfo()
     * @model containment="true"
     * @generated
     */
    Info getBookinfo();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book#getBookinfo
     * <em>Bookinfo</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Bookinfo</em>' containment
     *            reference.
     * @see #getBookinfo()
     * @generated
     */
    void setBookinfo(Info value);

    /**
     * Returns the value of the '<em><b>Chapter</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.docbook.Chapter}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Chapter</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Chapter</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getBook_Chapter()
     * @model containment="true"
     * @generated
     */
    EList<Chapter> getChapter();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getBook_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book#getId <em>Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Lang</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lang</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Lang</em>' attribute.
     * @see #setLang(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getBook_Lang()
     * @model
     * @generated
     */
    String getLang();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book#getLang
     * <em>Lang</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Lang</em>' attribute.
     * @see #getLang()
     * @generated
     */
    void setLang(String value);

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getBook_Version()
     * @model
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book#getVersion
     * <em>Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

} // Book
