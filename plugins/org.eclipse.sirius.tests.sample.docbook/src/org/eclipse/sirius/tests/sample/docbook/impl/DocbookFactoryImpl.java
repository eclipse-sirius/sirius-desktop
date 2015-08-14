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
package org.eclipse.sirius.tests.sample.docbook.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.tests.sample.docbook.Author;
import org.eclipse.sirius.tests.sample.docbook.Book;
import org.eclipse.sirius.tests.sample.docbook.Chapter;
import org.eclipse.sirius.tests.sample.docbook.DocbookFactory;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.Emphasis;
import org.eclipse.sirius.tests.sample.docbook.Example;
import org.eclipse.sirius.tests.sample.docbook.Info;
import org.eclipse.sirius.tests.sample.docbook.ItemizedList;
import org.eclipse.sirius.tests.sample.docbook.Link;
import org.eclipse.sirius.tests.sample.docbook.ListItem;
import org.eclipse.sirius.tests.sample.docbook.OrderedList;
import org.eclipse.sirius.tests.sample.docbook.Para;
import org.eclipse.sirius.tests.sample.docbook.Sect1;
import org.eclipse.sirius.tests.sample.docbook.Sect2;
import org.eclipse.sirius.tests.sample.docbook.Sect3;
import org.eclipse.sirius.tests.sample.docbook.SimpleList;
import org.eclipse.sirius.tests.sample.docbook.Title;
import org.eclipse.sirius.tests.sample.docbook.ULink;
import org.eclipse.sirius.tests.sample.docbook.XRef;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class DocbookFactoryImpl extends EFactoryImpl implements DocbookFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static DocbookFactory init() {
        try {
            DocbookFactory theDocbookFactory = (DocbookFactory) EPackage.Registry.INSTANCE.getEFactory(DocbookPackage.eNS_URI);
            if (theDocbookFactory != null) {
                return theDocbookFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new DocbookFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public DocbookFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case DocbookPackage.BOOK:
            return createBook();
        case DocbookPackage.INFO:
            return createInfo();
        case DocbookPackage.AUTHOR:
            return createAuthor();
        case DocbookPackage.CHAPTER:
            return createChapter();
        case DocbookPackage.TITLE:
            return createTitle();
        case DocbookPackage.PARA:
            return createPara();
        case DocbookPackage.SIMPLE_LIST:
            return createSimpleList();
        case DocbookPackage.ITEMIZED_LIST:
            return createItemizedList();
        case DocbookPackage.ORDERED_LIST:
            return createOrderedList();
        case DocbookPackage.SECT1:
            return createSect1();
        case DocbookPackage.SECT2:
            return createSect2();
        case DocbookPackage.EMPHASIS:
            return createEmphasis();
        case DocbookPackage.ULINK:
            return createULink();
        case DocbookPackage.LINK:
            return createLink();
        case DocbookPackage.XREF:
            return createXRef();
        case DocbookPackage.EXAMPLE:
            return createExample();
        case DocbookPackage.SECT3:
            return createSect3();
        case DocbookPackage.LIST_ITEM:
            return createListItem();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Book createBook() {
        BookImpl book = new BookImpl();
        return book;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Info createInfo() {
        InfoImpl info = new InfoImpl();
        return info;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Author createAuthor() {
        AuthorImpl author = new AuthorImpl();
        return author;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Chapter createChapter() {
        ChapterImpl chapter = new ChapterImpl();
        return chapter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Title createTitle() {
        TitleImpl title = new TitleImpl();
        return title;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Para createPara() {
        ParaImpl para = new ParaImpl();
        return para;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SimpleList createSimpleList() {
        SimpleListImpl simpleList = new SimpleListImpl();
        return simpleList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ItemizedList createItemizedList() {
        ItemizedListImpl itemizedList = new ItemizedListImpl();
        return itemizedList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OrderedList createOrderedList() {
        OrderedListImpl orderedList = new OrderedListImpl();
        return orderedList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Sect1 createSect1() {
        Sect1Impl sect1 = new Sect1Impl();
        return sect1;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Sect2 createSect2() {
        Sect2Impl sect2 = new Sect2Impl();
        return sect2;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Emphasis createEmphasis() {
        EmphasisImpl emphasis = new EmphasisImpl();
        return emphasis;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ULink createULink() {
        ULinkImpl uLink = new ULinkImpl();
        return uLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Link createLink() {
        LinkImpl link = new LinkImpl();
        return link;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public XRef createXRef() {
        XRefImpl xRef = new XRefImpl();
        return xRef;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Example createExample() {
        ExampleImpl example = new ExampleImpl();
        return example;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Sect3 createSect3() {
        Sect3Impl sect3 = new Sect3Impl();
        return sect3;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ListItem createListItem() {
        ListItemImpl listItem = new ListItemImpl();
        return listItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DocbookPackage getDocbookPackage() {
        return (DocbookPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DocbookPackage getPackage() {
        return DocbookPackage.eINSTANCE;
    }

} // DocbookFactoryImpl
