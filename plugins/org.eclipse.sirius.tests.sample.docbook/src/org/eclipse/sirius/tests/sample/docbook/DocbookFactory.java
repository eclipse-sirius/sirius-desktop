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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage
 * @generated
 */
public interface DocbookFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    DocbookFactory eINSTANCE = org.eclipse.sirius.tests.sample.docbook.impl.DocbookFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Book</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Book</em>'.
     * @generated
     */
    Book createBook();

    /**
     * Returns a new object of class '<em>Info</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Info</em>'.
     * @generated
     */
    Info createInfo();

    /**
     * Returns a new object of class '<em>Author</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Author</em>'.
     * @generated
     */
    Author createAuthor();

    /**
     * Returns a new object of class '<em>Chapter</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Chapter</em>'.
     * @generated
     */
    Chapter createChapter();

    /**
     * Returns a new object of class '<em>Title</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Title</em>'.
     * @generated
     */
    Title createTitle();

    /**
     * Returns a new object of class '<em>Para</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Para</em>'.
     * @generated
     */
    Para createPara();

    /**
     * Returns a new object of class '<em>Simple List</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Simple List</em>'.
     * @generated
     */
    SimpleList createSimpleList();

    /**
     * Returns a new object of class '<em>Itemized List</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Itemized List</em>'.
     * @generated
     */
    ItemizedList createItemizedList();

    /**
     * Returns a new object of class '<em>Ordered List</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Ordered List</em>'.
     * @generated
     */
    OrderedList createOrderedList();

    /**
     * Returns a new object of class '<em>Sect1</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Sect1</em>'.
     * @generated
     */
    Sect1 createSect1();

    /**
     * Returns a new object of class '<em>Sect2</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Sect2</em>'.
     * @generated
     */
    Sect2 createSect2();

    /**
     * Returns a new object of class '<em>Emphasis</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Emphasis</em>'.
     * @generated
     */
    Emphasis createEmphasis();

    /**
     * Returns a new object of class '<em>ULink</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>ULink</em>'.
     * @generated
     */
    ULink createULink();

    /**
     * Returns a new object of class '<em>Link</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Link</em>'.
     * @generated
     */
    Link createLink();

    /**
     * Returns a new object of class '<em>XRef</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>XRef</em>'.
     * @generated
     */
    XRef createXRef();

    /**
     * Returns a new object of class '<em>Example</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Example</em>'.
     * @generated
     */
    Example createExample();

    /**
     * Returns a new object of class '<em>Sect3</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Sect3</em>'.
     * @generated
     */
    Sect3 createSect3();

    /**
     * Returns a new object of class '<em>List Item</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>List Item</em>'.
     * @generated
     */
    ListItem createListItem();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    DocbookPackage getDocbookPackage();

} // DocbookFactory
