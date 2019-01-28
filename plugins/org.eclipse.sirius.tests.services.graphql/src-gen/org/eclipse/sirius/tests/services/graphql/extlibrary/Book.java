/**
 *  Copyright (c) 2019 Obeo.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.services.graphql.extlibrary;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book#getPages <em>Pages</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book#getAuthor <em>Author</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getBook()
 * @model
 * @generated
 */
public interface Book extends CirculatingItem {
    /**
     * Returns the value of the '<em><b>Title</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Title</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Title</em>' attribute.
     * @see #setTitle(String)
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getBook_Title()
     * @model
     * @generated
     */
    String getTitle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book#getTitle <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Title</em>' attribute.
     * @see #getTitle()
     * @generated
     */
    void setTitle(String value);

    /**
     * Returns the value of the '<em><b>Pages</b></em>' attribute.
     * The default value is <code>"100"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pages</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pages</em>' attribute.
     * @see #setPages(int)
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getBook_Pages()
     * @model default="100"
     * @generated
     */
    int getPages();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book#getPages <em>Pages</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pages</em>' attribute.
     * @see #getPages()
     * @generated
     */
    void setPages(int value);

    /**
     * Returns the value of the '<em><b>Category</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.sirius.tests.services.graphql.extlibrary.BookCategory}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Category</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category</em>' attribute.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.BookCategory
     * @see #isSetCategory()
     * @see #unsetCategory()
     * @see #setCategory(BookCategory)
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getBook_Category()
     * @model unsettable="true"
     * @generated
     */
    BookCategory getCategory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book#getCategory <em>Category</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Category</em>' attribute.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.BookCategory
     * @see #isSetCategory()
     * @see #unsetCategory()
     * @see #getCategory()
     * @generated
     */
    void setCategory(BookCategory value);

    /**
     * Unsets the value of the '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book#getCategory <em>Category</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetCategory()
     * @see #getCategory()
     * @see #setCategory(BookCategory)
     * @generated
     */
    void unsetCategory();

    /**
     * Returns whether the value of the '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book#getCategory <em>Category</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Category</em>' attribute is set.
     * @see #unsetCategory()
     * @see #getCategory()
     * @see #setCategory(BookCategory)
     * @generated
     */
    boolean isSetCategory();

    /**
     * Returns the value of the '<em><b>Author</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Writer#getBooks <em>Books</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Author</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Author</em>' reference.
     * @see #setAuthor(Writer)
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getBook_Author()
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.Writer#getBooks
     * @model opposite="books" required="true"
     * @generated
     */
    Writer getAuthor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book#getAuthor <em>Author</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Author</em>' reference.
     * @see #getAuthor()
     * @generated
     */
    void setAuthor(Writer value);

} // Book
