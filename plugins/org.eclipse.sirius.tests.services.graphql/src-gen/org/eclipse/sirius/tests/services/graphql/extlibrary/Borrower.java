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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Borrower</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Borrower#getBorrowed <em>Borrowed</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getBorrower()
 * @model
 * @generated
 */
public interface Borrower extends Person {
    /**
     * Returns the value of the '<em><b>Borrowed</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.services.graphql.extlibrary.Lendable}.
     * It is bidirectional and its opposite is '{@link org.eclipse.sirius.tests.services.graphql.extlibrary.Lendable#getBorrowers <em>Borrowers</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Borrowed</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Borrowed</em>' reference list.
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.ExtlibraryPackage#getBorrower_Borrowed()
     * @see org.eclipse.sirius.tests.services.graphql.extlibrary.Lendable#getBorrowers
     * @model opposite="borrowers"
     * @generated
     */
    EList<Lendable> getBorrowed();

} // Borrower
