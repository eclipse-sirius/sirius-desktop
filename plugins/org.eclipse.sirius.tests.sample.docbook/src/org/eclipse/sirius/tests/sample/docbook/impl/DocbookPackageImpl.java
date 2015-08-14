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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.tests.sample.docbook.AbstractSect;
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
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class DocbookPackageImpl extends EPackageImpl implements DocbookPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass bookEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass infoEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass authorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass chapterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass titleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass paraEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass simpleListEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass itemizedListEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass orderedListEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass sect1EClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractSectEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass sect2EClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass emphasisEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass uLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass linkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass xRefEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass exampleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass sect3EClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass listItemEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DocbookPackageImpl() {
        super(DocbookPackage.eNS_URI, DocbookFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link DocbookPackage#eINSTANCE} when
     * that field is accessed. Clients should not invoke it directly. Instead,
     * they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DocbookPackage init() {
        if (DocbookPackageImpl.isInited) {
            return (DocbookPackage) EPackage.Registry.INSTANCE.getEPackage(DocbookPackage.eNS_URI);
        }

        // Obtain or create and register package
        DocbookPackageImpl theDocbookPackage = (DocbookPackageImpl) (EPackage.Registry.INSTANCE.get(DocbookPackage.eNS_URI) instanceof DocbookPackageImpl ? EPackage.Registry.INSTANCE
                .get(DocbookPackage.eNS_URI) : new DocbookPackageImpl());

        DocbookPackageImpl.isInited = true;

        // Create package meta-data objects
        theDocbookPackage.createPackageContents();

        // Initialize created meta-data
        theDocbookPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theDocbookPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DocbookPackage.eNS_URI, theDocbookPackage);
        return theDocbookPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBook() {
        return bookEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getBook_Bookinfo() {
        return (EReference) bookEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getBook_Chapter() {
        return (EReference) bookEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBook_Id() {
        return (EAttribute) bookEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBook_Lang() {
        return (EAttribute) bookEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBook_Version() {
        return (EAttribute) bookEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getInfo() {
        return infoEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInfo_Author() {
        return (EReference) infoEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getInfo_Date() {
        return (EAttribute) infoEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getInfo_Pubdate() {
        return (EAttribute) infoEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAuthor() {
        return authorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAuthor_Email() {
        return (EAttribute) authorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAuthor_Personname() {
        return (EAttribute) authorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAuthor_Address() {
        return (EAttribute) authorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getChapter() {
        return chapterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getChapter_Title() {
        return (EReference) chapterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getChapter_Para() {
        return (EReference) chapterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getChapter_Sect1() {
        return (EReference) chapterEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getChapter_Id() {
        return (EAttribute) chapterEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTitle() {
        return titleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTitle_Data() {
        return (EAttribute) titleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getPara() {
        return paraEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPara_Data() {
        return (EAttribute) paraEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSimpleList() {
        return simpleListEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getItemizedList() {
        return itemizedListEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getItemizedList_Mark() {
        return (EAttribute) itemizedListEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getItemizedList_Listitem() {
        return (EReference) itemizedListEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getOrderedList() {
        return orderedListEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getOrderedList_Numeration() {
        return (EAttribute) orderedListEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSect1() {
        return sect1EClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSect1_Id() {
        return (EAttribute) sect1EClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSect1_Sect2() {
        return (EReference) sect1EClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractSect() {
        return abstractSectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractSect_Title() {
        return (EReference) abstractSectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractSect_Para() {
        return (EReference) abstractSectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSect2() {
        return sect2EClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSect2_Id() {
        return (EAttribute) sect2EClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSect2_Sect3() {
        return (EReference) sect2EClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEmphasis() {
        return emphasisEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEmphasis_Remap() {
        return (EAttribute) emphasisEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getULink() {
        return uLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getULink_Url() {
        return (EAttribute) uLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getULink_Data() {
        return (EAttribute) uLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLink() {
        return linkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getXRef() {
        return xRefEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getXRef_Linkend() {
        return (EAttribute) xRefEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getExample() {
        return exampleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSect3() {
        return sect3EClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSect3_Id() {
        return (EAttribute) sect3EClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getListItem() {
        return listItemEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DocbookFactory getDocbookFactory() {
        return (DocbookFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        bookEClass = createEClass(DocbookPackage.BOOK);
        createEReference(bookEClass, DocbookPackage.BOOK__BOOKINFO);
        createEReference(bookEClass, DocbookPackage.BOOK__CHAPTER);
        createEAttribute(bookEClass, DocbookPackage.BOOK__ID);
        createEAttribute(bookEClass, DocbookPackage.BOOK__LANG);
        createEAttribute(bookEClass, DocbookPackage.BOOK__VERSION);

        infoEClass = createEClass(DocbookPackage.INFO);
        createEReference(infoEClass, DocbookPackage.INFO__AUTHOR);
        createEAttribute(infoEClass, DocbookPackage.INFO__DATE);
        createEAttribute(infoEClass, DocbookPackage.INFO__PUBDATE);

        authorEClass = createEClass(DocbookPackage.AUTHOR);
        createEAttribute(authorEClass, DocbookPackage.AUTHOR__EMAIL);
        createEAttribute(authorEClass, DocbookPackage.AUTHOR__PERSONNAME);
        createEAttribute(authorEClass, DocbookPackage.AUTHOR__ADDRESS);

        chapterEClass = createEClass(DocbookPackage.CHAPTER);
        createEReference(chapterEClass, DocbookPackage.CHAPTER__TITLE);
        createEReference(chapterEClass, DocbookPackage.CHAPTER__PARA);
        createEReference(chapterEClass, DocbookPackage.CHAPTER__SECT1);
        createEAttribute(chapterEClass, DocbookPackage.CHAPTER__ID);

        titleEClass = createEClass(DocbookPackage.TITLE);
        createEAttribute(titleEClass, DocbookPackage.TITLE__DATA);

        paraEClass = createEClass(DocbookPackage.PARA);
        createEAttribute(paraEClass, DocbookPackage.PARA__DATA);

        simpleListEClass = createEClass(DocbookPackage.SIMPLE_LIST);

        itemizedListEClass = createEClass(DocbookPackage.ITEMIZED_LIST);
        createEAttribute(itemizedListEClass, DocbookPackage.ITEMIZED_LIST__MARK);
        createEReference(itemizedListEClass, DocbookPackage.ITEMIZED_LIST__LISTITEM);

        orderedListEClass = createEClass(DocbookPackage.ORDERED_LIST);
        createEAttribute(orderedListEClass, DocbookPackage.ORDERED_LIST__NUMERATION);

        sect1EClass = createEClass(DocbookPackage.SECT1);
        createEAttribute(sect1EClass, DocbookPackage.SECT1__ID);
        createEReference(sect1EClass, DocbookPackage.SECT1__SECT2);

        abstractSectEClass = createEClass(DocbookPackage.ABSTRACT_SECT);
        createEReference(abstractSectEClass, DocbookPackage.ABSTRACT_SECT__TITLE);
        createEReference(abstractSectEClass, DocbookPackage.ABSTRACT_SECT__PARA);

        sect2EClass = createEClass(DocbookPackage.SECT2);
        createEAttribute(sect2EClass, DocbookPackage.SECT2__ID);
        createEReference(sect2EClass, DocbookPackage.SECT2__SECT3);

        emphasisEClass = createEClass(DocbookPackage.EMPHASIS);
        createEAttribute(emphasisEClass, DocbookPackage.EMPHASIS__REMAP);

        uLinkEClass = createEClass(DocbookPackage.ULINK);
        createEAttribute(uLinkEClass, DocbookPackage.ULINK__URL);
        createEAttribute(uLinkEClass, DocbookPackage.ULINK__DATA);

        linkEClass = createEClass(DocbookPackage.LINK);

        xRefEClass = createEClass(DocbookPackage.XREF);
        createEAttribute(xRefEClass, DocbookPackage.XREF__LINKEND);

        exampleEClass = createEClass(DocbookPackage.EXAMPLE);

        sect3EClass = createEClass(DocbookPackage.SECT3);
        createEAttribute(sect3EClass, DocbookPackage.SECT3__ID);

        listItemEClass = createEClass(DocbookPackage.LIST_ITEM);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(DocbookPackage.eNAME);
        setNsPrefix(DocbookPackage.eNS_PREFIX);
        setNsURI(DocbookPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        sect1EClass.getESuperTypes().add(this.getAbstractSect());
        sect2EClass.getESuperTypes().add(this.getAbstractSect());
        exampleEClass.getESuperTypes().add(this.getAbstractSect());
        sect3EClass.getESuperTypes().add(this.getAbstractSect());

        // Initialize classes and features; add operations and parameters
        initEClass(bookEClass, Book.class, "Book", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getBook_Bookinfo(),
                this.getInfo(),
                null,
                "bookinfo", null, 0, 1, Book.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getBook_Chapter(),
                this.getChapter(),
                null,
                "chapter", null, 0, -1, Book.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getBook_Id(),
                ecorePackage.getEString(),
                "id", null, 0, 1, Book.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getBook_Lang(),
                ecorePackage.getEString(),
                "lang", null, 0, 1, Book.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getBook_Version(),
                ecorePackage.getEString(),
                "version", null, 0, 1, Book.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(infoEClass, Info.class, "Info", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getInfo_Author(),
                this.getAuthor(),
                null,
                "author", null, 0, -1, Info.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getInfo_Date(),
                ecorePackage.getEString(),
                "date", null, 0, 1, Info.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getInfo_Pubdate(),
                ecorePackage.getEString(),
                "pubdate", null, 0, 1, Info.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(authorEClass, Author.class, "Author", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getAuthor_Email(),
                ecorePackage.getEString(),
                "email", null, 0, 1, Author.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getAuthor_Personname(),
                ecorePackage.getEString(),
                "personname", null, 0, 1, Author.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getAuthor_Address(),
                ecorePackage.getEString(),
                "address", null, 0, 1, Author.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(chapterEClass, Chapter.class, "Chapter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getChapter_Title(),
                this.getTitle(),
                null,
                "title", null, 0, 1, Chapter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getChapter_Para(),
                this.getPara(),
                null,
                "para", null, 0, -1, Chapter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getChapter_Sect1(),
                this.getSect1(),
                null,
                "sect1", null, 0, -1, Chapter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getChapter_Id(),
                ecorePackage.getEString(),
                "id", null, 0, 1, Chapter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(titleEClass, Title.class, "Title", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getTitle_Data(),
                ecorePackage.getEString(),
                "data", null, 0, 1, Title.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(paraEClass, Para.class, "Para", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getPara_Data(),
                ecorePackage.getEString(),
                "data", null, 0, 1, Para.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(simpleListEClass, SimpleList.class, "SimpleList", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(itemizedListEClass, ItemizedList.class, "ItemizedList", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getItemizedList_Mark(),
                ecorePackage.getEString(),
                "mark", null, 0, 1, ItemizedList.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getItemizedList_Listitem(),
                this.getListItem(),
                null,
                "listitem", null, 0, -1, ItemizedList.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(orderedListEClass, OrderedList.class, "OrderedList", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getOrderedList_Numeration(),
                ecorePackage.getEString(),
                "numeration", null, 0, 1, OrderedList.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(sect1EClass, Sect1.class, "Sect1", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getSect1_Id(),
                ecorePackage.getEString(),
                "id", null, 0, 1, Sect1.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getSect1_Sect2(),
                this.getSect2(),
                null,
                "sect2", null, 0, -1, Sect1.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(abstractSectEClass, AbstractSect.class, "AbstractSect", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getAbstractSect_Title(),
                this.getTitle(),
                null,
                "title", null, 0, 1, AbstractSect.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getAbstractSect_Para(),
                this.getPara(),
                null,
                "para", null, 0, -1, AbstractSect.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(sect2EClass, Sect2.class, "Sect2", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getSect2_Id(),
                ecorePackage.getEString(),
                "id", null, 0, 1, Sect2.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getSect2_Sect3(),
                this.getSect3(),
                null,
                "sect3", null, 0, -1, Sect2.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(emphasisEClass, Emphasis.class, "Emphasis", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getEmphasis_Remap(),
                ecorePackage.getEString(),
                "remap", null, 0, 1, Emphasis.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(uLinkEClass, ULink.class, "ULink", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getULink_Url(),
                ecorePackage.getEString(),
                "url", null, 0, 1, ULink.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getULink_Data(),
                ecorePackage.getEString(),
                "data", null, 0, 1, ULink.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(linkEClass, Link.class, "Link", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(xRefEClass, XRef.class, "XRef", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getXRef_Linkend(),
                ecorePackage.getEString(),
                "linkend", null, 0, 1, XRef.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(exampleEClass, Example.class, "Example", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(sect3EClass, Sect3.class, "Sect3", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getSect3_Id(),
                ecorePackage.getEString(),
                "id", null, 0, 1, Sect3.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(listItemEClass, ListItem.class, "ListItem", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        // Create resource
        createResource(DocbookPackage.eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$
        addAnnotation(titleEClass, source, new String[] { "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getTitle_Data(), source, new String[] { "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(paraEClass, source, new String[] { "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getPara_Data(), source, new String[] { "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(uLinkEClass, source, new String[] { "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getULink_Data(), source, new String[] { "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

} // DocbookPackageImpl
