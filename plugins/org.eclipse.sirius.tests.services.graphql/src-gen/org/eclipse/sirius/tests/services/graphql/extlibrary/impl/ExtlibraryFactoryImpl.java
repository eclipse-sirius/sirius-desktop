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
package org.eclipse.sirius.tests.services.graphql.extlibrary.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.sirius.tests.services.graphql.extlibrary.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtlibraryFactoryImpl extends EFactoryImpl implements ExtlibraryFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ExtlibraryFactory init() {
        try {
            ExtlibraryFactory theExtlibraryFactory = (ExtlibraryFactory)EPackage.Registry.INSTANCE.getEFactory(ExtlibraryPackage.eNS_URI);
            if (theExtlibraryFactory != null) {
                return theExtlibraryFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ExtlibraryFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExtlibraryFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case ExtlibraryPackage.BOOK: return createBook();
            case ExtlibraryPackage.LIBRARY: return createLibrary();
            case ExtlibraryPackage.WRITER: return createWriter();
            case ExtlibraryPackage.BOOK_ON_TAPE: return createBookOnTape();
            case ExtlibraryPackage.VIDEO_CASSETTE: return createVideoCassette();
            case ExtlibraryPackage.BORROWER: return createBorrower();
            case ExtlibraryPackage.PERSON: return createPerson();
            case ExtlibraryPackage.EMPLOYEE: return createEmployee();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case ExtlibraryPackage.BOOK_CATEGORY:
                return createBookCategoryFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case ExtlibraryPackage.BOOK_CATEGORY:
                return convertBookCategoryToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Book createBook() {
        BookImpl book = new BookImpl();
        return book;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Library createLibrary() {
        LibraryImpl library = new LibraryImpl();
        return library;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Writer createWriter() {
        WriterImpl writer = new WriterImpl();
        return writer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BookOnTape createBookOnTape() {
        BookOnTapeImpl bookOnTape = new BookOnTapeImpl();
        return bookOnTape;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VideoCassette createVideoCassette() {
        VideoCassetteImpl videoCassette = new VideoCassetteImpl();
        return videoCassette;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Borrower createBorrower() {
        BorrowerImpl borrower = new BorrowerImpl();
        return borrower;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Person createPerson() {
        PersonImpl person = new PersonImpl();
        return person;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Employee createEmployee() {
        EmployeeImpl employee = new EmployeeImpl();
        return employee;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BookCategory createBookCategoryFromString(EDataType eDataType, String initialValue) {
        BookCategory result = BookCategory.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertBookCategoryToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExtlibraryPackage getExtlibraryPackage() {
        return (ExtlibraryPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ExtlibraryPackage getPackage() {
        return ExtlibraryPackage.eINSTANCE;
    }

} //ExtlibraryFactoryImpl
