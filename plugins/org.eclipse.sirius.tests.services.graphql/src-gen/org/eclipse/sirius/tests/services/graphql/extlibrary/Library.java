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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getWriters <em>Writers</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getEmployees <em>Employees</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getBorrowers <em>Borrowers</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getStock <em>Stock</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getBooks <em>Books</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getBranches <em>Branches</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getParentBranch <em>Parent Branch</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getPeople <em>People</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getLibrary()
 * @model
 * @generated
 */
public interface Library extends Addressable {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getLibrary_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Writers</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.services.graphql.extlibrary.Writer}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Writers</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Writers</em>' containment reference list.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getLibrary_Writers()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="group='#people'"
     * @generated
     */
    EList<Writer> getWriters();

    /**
     * Returns the value of the '<em><b>Employees</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.services.graphql.extlibrary.Employee}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Employees</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Employees</em>' containment reference list.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getLibrary_Employees()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="group='#people'"
     * @generated
     */
    EList<Employee> getEmployees();

    /**
     * Returns the value of the '<em><b>Borrowers</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.services.graphql.extlibrary.Borrower}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Borrowers</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Borrowers</em>' containment reference list.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getLibrary_Borrowers()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="group='#people'"
     * @generated
     */
    EList<Borrower> getBorrowers();

    /**
     * Returns the value of the '<em><b>Stock</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.services.graphql.extlibrary.Item}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stock</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Stock</em>' containment reference list.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getLibrary_Stock()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<Item> getStock();

    /**
     * Returns the value of the '<em><b>Books</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.services.graphql.extlibrary.Book}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Books</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Books</em>' reference list.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getLibrary_Books()
     * @model transient="true" derived="true" ordered="false"
     * @generated
     */
    EList<Book> getBooks();

    /**
     * Returns the value of the '<em><b>Branches</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library}.
     * It is bidirectional and its opposite is '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getParentBranch <em>Parent Branch</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Branches</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Branches</em>' containment reference list.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getLibrary_Branches()
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getParentBranch
     * @model opposite="parentBranch" containment="true"
     * @generated
     */
    EList<Library> getBranches();

    /**
     * Returns the value of the '<em><b>Parent Branch</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getBranches <em>Branches</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent Branch</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent Branch</em>' container reference.
     * @see #setParentBranch(Library)
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getLibrary_ParentBranch()
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getBranches
     * @model opposite="branches" transient="false"
     * @generated
     */
    Library getParentBranch();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Library#getParentBranch <em>Parent Branch</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent Branch</em>' container reference.
     * @see #getParentBranch()
     * @generated
     */
    void setParentBranch(Library value);

    /**
     * Returns the value of the '<em><b>People</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>People</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>People</em>' attribute list.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getLibrary_People()
     * @model dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group'"
     * @generated
     */
    FeatureMap getPeople();

} // Library
