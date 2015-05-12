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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookFactory
 * @model kind="package"
 * @generated
 */
public interface DocbookPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "docbook";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://docbook.org/ns/docbook";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "docbook";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    DocbookPackage eINSTANCE = org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.BookImpl
     * <em>Book</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.BookImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getBook()
     * @generated
     */
    int BOOK = 0;

    /**
     * The feature id for the '<em><b>Bookinfo</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOK__BOOKINFO = 0;

    /**
     * The feature id for the '<em><b>Chapter</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOK__CHAPTER = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOK__ID = 2;

    /**
     * The feature id for the '<em><b>Lang</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOK__LANG = 3;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOK__VERSION = 4;

    /**
     * The number of structural features of the '<em>Book</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOK_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.InfoImpl
     * <em>Info</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.InfoImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getInfo()
     * @generated
     */
    int INFO = 1;

    /**
     * The feature id for the '<em><b>Author</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INFO__AUTHOR = 0;

    /**
     * The feature id for the '<em><b>Date</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INFO__DATE = 1;

    /**
     * The feature id for the '<em><b>Pubdate</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INFO__PUBDATE = 2;

    /**
     * The number of structural features of the '<em>Info</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INFO_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.AuthorImpl
     * <em>Author</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.AuthorImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getAuthor()
     * @generated
     */
    int AUTHOR = 2;

    /**
     * The feature id for the '<em><b>Email</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AUTHOR__EMAIL = 0;

    /**
     * The feature id for the '<em><b>Personname</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AUTHOR__PERSONNAME = 1;

    /**
     * The feature id for the '<em><b>Address</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AUTHOR__ADDRESS = 2;

    /**
     * The number of structural features of the '<em>Author</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AUTHOR_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.ChapterImpl
     * <em>Chapter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.ChapterImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getChapter()
     * @generated
     */
    int CHAPTER = 3;

    /**
     * The feature id for the '<em><b>Title</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHAPTER__TITLE = 0;

    /**
     * The feature id for the '<em><b>Para</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHAPTER__PARA = 1;

    /**
     * The feature id for the '<em><b>Sect1</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHAPTER__SECT1 = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHAPTER__ID = 3;

    /**
     * The number of structural features of the '<em>Chapter</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHAPTER_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.TitleImpl
     * <em>Title</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.TitleImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getTitle()
     * @generated
     */
    int TITLE = 4;

    /**
     * The feature id for the '<em><b>Data</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TITLE__DATA = 0;

    /**
     * The number of structural features of the '<em>Title</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TITLE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.ParaImpl
     * <em>Para</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.ParaImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getPara()
     * @generated
     */
    int PARA = 5;

    /**
     * The feature id for the '<em><b>Data</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PARA__DATA = 0;

    /**
     * The number of structural features of the '<em>Para</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PARA_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.SimpleListImpl
     * <em>Simple List</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.SimpleListImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getSimpleList()
     * @generated
     */
    int SIMPLE_LIST = 6;

    /**
     * The number of structural features of the '<em>Simple List</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SIMPLE_LIST_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.ItemizedListImpl
     * <em>Itemized List</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.ItemizedListImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getItemizedList()
     * @generated
     */
    int ITEMIZED_LIST = 7;

    /**
     * The feature id for the '<em><b>Mark</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITEMIZED_LIST__MARK = 0;

    /**
     * The feature id for the '<em><b>Listitem</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITEMIZED_LIST__LISTITEM = 1;

    /**
     * The number of structural features of the '<em>Itemized List</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITEMIZED_LIST_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.OrderedListImpl
     * <em>Ordered List</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.OrderedListImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getOrderedList()
     * @generated
     */
    int ORDERED_LIST = 8;

    /**
     * The feature id for the '<em><b>Numeration</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ORDERED_LIST__NUMERATION = 0;

    /**
     * The number of structural features of the '<em>Ordered List</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ORDERED_LIST_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.AbstractSectImpl
     * <em>Abstract Sect</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.AbstractSectImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getAbstractSect()
     * @generated
     */
    int ABSTRACT_SECT = 10;

    /**
     * The feature id for the '<em><b>Title</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SECT__TITLE = 0;

    /**
     * The feature id for the '<em><b>Para</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SECT__PARA = 1;

    /**
     * The number of structural features of the '<em>Abstract Sect</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SECT_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.Sect1Impl
     * <em>Sect1</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.Sect1Impl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getSect1()
     * @generated
     */
    int SECT1 = 9;

    /**
     * The feature id for the '<em><b>Title</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT1__TITLE = DocbookPackage.ABSTRACT_SECT__TITLE;

    /**
     * The feature id for the '<em><b>Para</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT1__PARA = DocbookPackage.ABSTRACT_SECT__PARA;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT1__ID = DocbookPackage.ABSTRACT_SECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sect2</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT1__SECT2 = DocbookPackage.ABSTRACT_SECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Sect1</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT1_FEATURE_COUNT = DocbookPackage.ABSTRACT_SECT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.Sect2Impl
     * <em>Sect2</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.Sect2Impl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getSect2()
     * @generated
     */
    int SECT2 = 11;

    /**
     * The feature id for the '<em><b>Title</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT2__TITLE = DocbookPackage.ABSTRACT_SECT__TITLE;

    /**
     * The feature id for the '<em><b>Para</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT2__PARA = DocbookPackage.ABSTRACT_SECT__PARA;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT2__ID = DocbookPackage.ABSTRACT_SECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sect3</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT2__SECT3 = DocbookPackage.ABSTRACT_SECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Sect2</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT2_FEATURE_COUNT = DocbookPackage.ABSTRACT_SECT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.EmphasisImpl
     * <em>Emphasis</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.EmphasisImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getEmphasis()
     * @generated
     */
    int EMPHASIS = 12;

    /**
     * The feature id for the '<em><b>Remap</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EMPHASIS__REMAP = 0;

    /**
     * The number of structural features of the '<em>Emphasis</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EMPHASIS_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.ULinkImpl
     * <em>ULink</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.ULinkImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getULink()
     * @generated
     */
    int ULINK = 13;

    /**
     * The feature id for the '<em><b>Url</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ULINK__URL = 0;

    /**
     * The feature id for the '<em><b>Data</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ULINK__DATA = 1;

    /**
     * The number of structural features of the '<em>ULink</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ULINK_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.LinkImpl
     * <em>Link</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.LinkImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getLink()
     * @generated
     */
    int LINK = 14;

    /**
     * The number of structural features of the '<em>Link</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LINK_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.XRefImpl
     * <em>XRef</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.XRefImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getXRef()
     * @generated
     */
    int XREF = 15;

    /**
     * The feature id for the '<em><b>Linkend</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int XREF__LINKEND = 0;

    /**
     * The number of structural features of the '<em>XRef</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int XREF_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.ExampleImpl
     * <em>Example</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.ExampleImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getExample()
     * @generated
     */
    int EXAMPLE = 16;

    /**
     * The feature id for the '<em><b>Title</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXAMPLE__TITLE = DocbookPackage.ABSTRACT_SECT__TITLE;

    /**
     * The feature id for the '<em><b>Para</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXAMPLE__PARA = DocbookPackage.ABSTRACT_SECT__PARA;

    /**
     * The number of structural features of the '<em>Example</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXAMPLE_FEATURE_COUNT = DocbookPackage.ABSTRACT_SECT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.Sect3Impl
     * <em>Sect3</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.Sect3Impl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getSect3()
     * @generated
     */
    int SECT3 = 17;

    /**
     * The feature id for the '<em><b>Title</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT3__TITLE = DocbookPackage.ABSTRACT_SECT__TITLE;

    /**
     * The feature id for the '<em><b>Para</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT3__PARA = DocbookPackage.ABSTRACT_SECT__PARA;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT3__ID = DocbookPackage.ABSTRACT_SECT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Sect3</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SECT3_FEATURE_COUNT = DocbookPackage.ABSTRACT_SECT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.docbook.impl.ListItemImpl
     * <em>List Item</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.docbook.impl.ListItemImpl
     * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getListItem()
     * @generated
     */
    int LIST_ITEM = 18;

    /**
     * The number of structural features of the '<em>List Item</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_ITEM_FEATURE_COUNT = 0;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book <em>Book</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Book</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Book
     * @generated
     */
    EClass getBook();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book#getBookinfo
     * <em>Bookinfo</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Bookinfo</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.docbook.Book#getBookinfo()
     * @see #getBook()
     * @generated
     */
    EReference getBook_Bookinfo();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book#getChapter
     * <em>Chapter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Chapter</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Book#getChapter()
     * @see #getBook()
     * @generated
     */
    EReference getBook_Chapter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Book#getId()
     * @see #getBook()
     * @generated
     */
    EAttribute getBook_Id();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book#getLang
     * <em>Lang</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Lang</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Book#getLang()
     * @see #getBook()
     * @generated
     */
    EAttribute getBook_Lang();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Book#getVersion
     * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Book#getVersion()
     * @see #getBook()
     * @generated
     */
    EAttribute getBook_Version();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Info <em>Info</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Info</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Info
     * @generated
     */
    EClass getInfo();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.docbook.Info#getAuthor
     * <em>Author</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Author</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Info#getAuthor()
     * @see #getInfo()
     * @generated
     */
    EReference getInfo_Author();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Info#getDate
     * <em>Date</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Date</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Info#getDate()
     * @see #getInfo()
     * @generated
     */
    EAttribute getInfo_Date();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Info#getPubdate
     * <em>Pubdate</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Pubdate</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Info#getPubdate()
     * @see #getInfo()
     * @generated
     */
    EAttribute getInfo_Pubdate();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Author <em>Author</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Author</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Author
     * @generated
     */
    EClass getAuthor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Author#getEmail
     * <em>Email</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Email</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Author#getEmail()
     * @see #getAuthor()
     * @generated
     */
    EAttribute getAuthor_Email();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Author#getPersonname
     * <em>Personname</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Personname</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Author#getPersonname()
     * @see #getAuthor()
     * @generated
     */
    EAttribute getAuthor_Personname();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Author#getAddress
     * <em>Address</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Address</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Author#getAddress()
     * @see #getAuthor()
     * @generated
     */
    EAttribute getAuthor_Address();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Chapter <em>Chapter</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Chapter</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Chapter
     * @generated
     */
    EClass getChapter();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.docbook.Chapter#getTitle
     * <em>Title</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Title</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Chapter#getTitle()
     * @see #getChapter()
     * @generated
     */
    EReference getChapter_Title();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.docbook.Chapter#getPara
     * <em>Para</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Para</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.docbook.Chapter#getPara()
     * @see #getChapter()
     * @generated
     */
    EReference getChapter_Para();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.docbook.Chapter#getSect1
     * <em>Sect1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Sect1</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Chapter#getSect1()
     * @see #getChapter()
     * @generated
     */
    EReference getChapter_Sect1();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Chapter#getId <em>Id</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Chapter#getId()
     * @see #getChapter()
     * @generated
     */
    EAttribute getChapter_Id();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Title <em>Title</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Title</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Title
     * @generated
     */
    EClass getTitle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Title#getData
     * <em>Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Data</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Title#getData()
     * @see #getTitle()
     * @generated
     */
    EAttribute getTitle_Data();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Para <em>Para</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Para</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Para
     * @generated
     */
    EClass getPara();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Para#getData
     * <em>Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Data</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Para#getData()
     * @see #getPara()
     * @generated
     */
    EAttribute getPara_Data();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.SimpleList
     * <em>Simple List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Simple List</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.SimpleList
     * @generated
     */
    EClass getSimpleList();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.ItemizedList
     * <em>Itemized List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Itemized List</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.ItemizedList
     * @generated
     */
    EClass getItemizedList();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.ItemizedList#getMark
     * <em>Mark</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Mark</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.ItemizedList#getMark()
     * @see #getItemizedList()
     * @generated
     */
    EAttribute getItemizedList_Mark();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.docbook.ItemizedList#getListitem
     * <em>Listitem</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Listitem</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.ItemizedList#getListitem()
     * @see #getItemizedList()
     * @generated
     */
    EReference getItemizedList_Listitem();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.OrderedList
     * <em>Ordered List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Ordered List</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.OrderedList
     * @generated
     */
    EClass getOrderedList();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.OrderedList#getNumeration
     * <em>Numeration</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Numeration</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.OrderedList#getNumeration()
     * @see #getOrderedList()
     * @generated
     */
    EAttribute getOrderedList_Numeration();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect1 <em>Sect1</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Sect1</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect1
     * @generated
     */
    EClass getSect1();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect1#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect1#getId()
     * @see #getSect1()
     * @generated
     */
    EAttribute getSect1_Id();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect1#getSect2
     * <em>Sect2</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Sect2</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect1#getSect2()
     * @see #getSect1()
     * @generated
     */
    EReference getSect1_Sect2();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.AbstractSect
     * <em>Abstract Sect</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Sect</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.AbstractSect
     * @generated
     */
    EClass getAbstractSect();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.docbook.AbstractSect#getTitle
     * <em>Title</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Title</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.AbstractSect#getTitle()
     * @see #getAbstractSect()
     * @generated
     */
    EReference getAbstractSect_Title();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.docbook.AbstractSect#getPara
     * <em>Para</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Para</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.docbook.AbstractSect#getPara()
     * @see #getAbstractSect()
     * @generated
     */
    EReference getAbstractSect_Para();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect2 <em>Sect2</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Sect2</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect2
     * @generated
     */
    EClass getSect2();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect2#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect2#getId()
     * @see #getSect2()
     * @generated
     */
    EAttribute getSect2_Id();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect2#getSect3
     * <em>Sect3</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Sect3</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect2#getSect3()
     * @see #getSect2()
     * @generated
     */
    EReference getSect2_Sect3();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Emphasis
     * <em>Emphasis</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Emphasis</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Emphasis
     * @generated
     */
    EClass getEmphasis();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Emphasis#getRemap
     * <em>Remap</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Remap</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Emphasis#getRemap()
     * @see #getEmphasis()
     * @generated
     */
    EAttribute getEmphasis_Remap();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.ULink <em>ULink</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>ULink</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.ULink
     * @generated
     */
    EClass getULink();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.ULink#getUrl <em>Url</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Url</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.ULink#getUrl()
     * @see #getULink()
     * @generated
     */
    EAttribute getULink_Url();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.ULink#getData
     * <em>Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Data</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.ULink#getData()
     * @see #getULink()
     * @generated
     */
    EAttribute getULink_Data();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Link <em>Link</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Link</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Link
     * @generated
     */
    EClass getLink();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.XRef <em>XRef</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>XRef</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.XRef
     * @generated
     */
    EClass getXRef();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.XRef#getLinkend
     * <em>Linkend</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Linkend</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.XRef#getLinkend()
     * @see #getXRef()
     * @generated
     */
    EAttribute getXRef_Linkend();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Example <em>Example</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Example</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Example
     * @generated
     */
    EClass getExample();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect3 <em>Sect3</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Sect3</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect3
     * @generated
     */
    EClass getSect3();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect3#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.Sect3#getId()
     * @see #getSect3()
     * @generated
     */
    EAttribute getSect3_Id();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.docbook.ListItem
     * <em>List Item</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>List Item</em>'.
     * @see org.eclipse.sirius.tests.sample.docbook.ListItem
     * @generated
     */
    EClass getListItem();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DocbookFactory getDocbookFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.BookImpl
         * <em>Book</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.BookImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getBook()
         * @generated
         */
        EClass BOOK = DocbookPackage.eINSTANCE.getBook();

        /**
         * The meta object literal for the '<em><b>Bookinfo</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference BOOK__BOOKINFO = DocbookPackage.eINSTANCE.getBook_Bookinfo();

        /**
         * The meta object literal for the '<em><b>Chapter</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference BOOK__CHAPTER = DocbookPackage.eINSTANCE.getBook_Chapter();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BOOK__ID = DocbookPackage.eINSTANCE.getBook_Id();

        /**
         * The meta object literal for the '<em><b>Lang</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BOOK__LANG = DocbookPackage.eINSTANCE.getBook_Lang();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BOOK__VERSION = DocbookPackage.eINSTANCE.getBook_Version();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.InfoImpl
         * <em>Info</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.InfoImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getInfo()
         * @generated
         */
        EClass INFO = DocbookPackage.eINSTANCE.getInfo();

        /**
         * The meta object literal for the '<em><b>Author</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference INFO__AUTHOR = DocbookPackage.eINSTANCE.getInfo_Author();

        /**
         * The meta object literal for the '<em><b>Date</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute INFO__DATE = DocbookPackage.eINSTANCE.getInfo_Date();

        /**
         * The meta object literal for the '<em><b>Pubdate</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute INFO__PUBDATE = DocbookPackage.eINSTANCE.getInfo_Pubdate();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.AuthorImpl
         * <em>Author</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.AuthorImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getAuthor()
         * @generated
         */
        EClass AUTHOR = DocbookPackage.eINSTANCE.getAuthor();

        /**
         * The meta object literal for the '<em><b>Email</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute AUTHOR__EMAIL = DocbookPackage.eINSTANCE.getAuthor_Email();

        /**
         * The meta object literal for the '<em><b>Personname</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute AUTHOR__PERSONNAME = DocbookPackage.eINSTANCE.getAuthor_Personname();

        /**
         * The meta object literal for the '<em><b>Address</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute AUTHOR__ADDRESS = DocbookPackage.eINSTANCE.getAuthor_Address();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.ChapterImpl
         * <em>Chapter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.ChapterImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getChapter()
         * @generated
         */
        EClass CHAPTER = DocbookPackage.eINSTANCE.getChapter();

        /**
         * The meta object literal for the '<em><b>Title</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CHAPTER__TITLE = DocbookPackage.eINSTANCE.getChapter_Title();

        /**
         * The meta object literal for the '<em><b>Para</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CHAPTER__PARA = DocbookPackage.eINSTANCE.getChapter_Para();

        /**
         * The meta object literal for the '<em><b>Sect1</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CHAPTER__SECT1 = DocbookPackage.eINSTANCE.getChapter_Sect1();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CHAPTER__ID = DocbookPackage.eINSTANCE.getChapter_Id();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.TitleImpl
         * <em>Title</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.TitleImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getTitle()
         * @generated
         */
        EClass TITLE = DocbookPackage.eINSTANCE.getTitle();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TITLE__DATA = DocbookPackage.eINSTANCE.getTitle_Data();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.ParaImpl
         * <em>Para</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.ParaImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getPara()
         * @generated
         */
        EClass PARA = DocbookPackage.eINSTANCE.getPara();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PARA__DATA = DocbookPackage.eINSTANCE.getPara_Data();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.SimpleListImpl
         * <em>Simple List</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.SimpleListImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getSimpleList()
         * @generated
         */
        EClass SIMPLE_LIST = DocbookPackage.eINSTANCE.getSimpleList();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.ItemizedListImpl
         * <em>Itemized List</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.ItemizedListImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getItemizedList()
         * @generated
         */
        EClass ITEMIZED_LIST = DocbookPackage.eINSTANCE.getItemizedList();

        /**
         * The meta object literal for the '<em><b>Mark</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ITEMIZED_LIST__MARK = DocbookPackage.eINSTANCE.getItemizedList_Mark();

        /**
         * The meta object literal for the '<em><b>Listitem</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ITEMIZED_LIST__LISTITEM = DocbookPackage.eINSTANCE.getItemizedList_Listitem();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.OrderedListImpl
         * <em>Ordered List</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.OrderedListImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getOrderedList()
         * @generated
         */
        EClass ORDERED_LIST = DocbookPackage.eINSTANCE.getOrderedList();

        /**
         * The meta object literal for the '<em><b>Numeration</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ORDERED_LIST__NUMERATION = DocbookPackage.eINSTANCE.getOrderedList_Numeration();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.Sect1Impl
         * <em>Sect1</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.Sect1Impl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getSect1()
         * @generated
         */
        EClass SECT1 = DocbookPackage.eINSTANCE.getSect1();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SECT1__ID = DocbookPackage.eINSTANCE.getSect1_Id();

        /**
         * The meta object literal for the '<em><b>Sect2</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SECT1__SECT2 = DocbookPackage.eINSTANCE.getSect1_Sect2();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.AbstractSectImpl
         * <em>Abstract Sect</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.AbstractSectImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getAbstractSect()
         * @generated
         */
        EClass ABSTRACT_SECT = DocbookPackage.eINSTANCE.getAbstractSect();

        /**
         * The meta object literal for the '<em><b>Title</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_SECT__TITLE = DocbookPackage.eINSTANCE.getAbstractSect_Title();

        /**
         * The meta object literal for the '<em><b>Para</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_SECT__PARA = DocbookPackage.eINSTANCE.getAbstractSect_Para();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.Sect2Impl
         * <em>Sect2</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.Sect2Impl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getSect2()
         * @generated
         */
        EClass SECT2 = DocbookPackage.eINSTANCE.getSect2();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SECT2__ID = DocbookPackage.eINSTANCE.getSect2_Id();

        /**
         * The meta object literal for the '<em><b>Sect3</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SECT2__SECT3 = DocbookPackage.eINSTANCE.getSect2_Sect3();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.EmphasisImpl
         * <em>Emphasis</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.EmphasisImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getEmphasis()
         * @generated
         */
        EClass EMPHASIS = DocbookPackage.eINSTANCE.getEmphasis();

        /**
         * The meta object literal for the '<em><b>Remap</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EMPHASIS__REMAP = DocbookPackage.eINSTANCE.getEmphasis_Remap();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.ULinkImpl
         * <em>ULink</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.ULinkImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getULink()
         * @generated
         */
        EClass ULINK = DocbookPackage.eINSTANCE.getULink();

        /**
         * The meta object literal for the '<em><b>Url</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ULINK__URL = DocbookPackage.eINSTANCE.getULink_Url();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ULINK__DATA = DocbookPackage.eINSTANCE.getULink_Data();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.LinkImpl
         * <em>Link</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.LinkImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getLink()
         * @generated
         */
        EClass LINK = DocbookPackage.eINSTANCE.getLink();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.XRefImpl
         * <em>XRef</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.XRefImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getXRef()
         * @generated
         */
        EClass XREF = DocbookPackage.eINSTANCE.getXRef();

        /**
         * The meta object literal for the '<em><b>Linkend</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute XREF__LINKEND = DocbookPackage.eINSTANCE.getXRef_Linkend();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.ExampleImpl
         * <em>Example</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.ExampleImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getExample()
         * @generated
         */
        EClass EXAMPLE = DocbookPackage.eINSTANCE.getExample();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.Sect3Impl
         * <em>Sect3</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.Sect3Impl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getSect3()
         * @generated
         */
        EClass SECT3 = DocbookPackage.eINSTANCE.getSect3();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SECT3__ID = DocbookPackage.eINSTANCE.getSect3_Id();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.docbook.impl.ListItemImpl
         * <em>List Item</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.docbook.impl.ListItemImpl
         * @see org.eclipse.sirius.tests.sample.docbook.impl.DocbookPackageImpl#getListItem()
         * @generated
         */
        EClass LIST_ITEM = DocbookPackage.eINSTANCE.getListItem();

    }

} // DocbookPackage
