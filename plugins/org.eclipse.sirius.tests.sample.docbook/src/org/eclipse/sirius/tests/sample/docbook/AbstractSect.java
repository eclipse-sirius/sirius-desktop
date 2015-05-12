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
 * <em><b>Abstract Sect</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.AbstractSect#getTitle <em>
 * Title</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.AbstractSect#getPara <em>
 * Para</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getAbstractSect()
 * @model abstract="true"
 * @generated
 */
public interface AbstractSect extends EObject {
    /**
     * Returns the value of the '<em><b>Title</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Title</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Title</em>' containment reference.
     * @see #setTitle(Title)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getAbstractSect_Title()
     * @model containment="true"
     * @generated
     */
    Title getTitle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.AbstractSect#getTitle
     * <em>Title</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Title</em>' containment reference.
     * @see #getTitle()
     * @generated
     */
    void setTitle(Title value);

    /**
     * Returns the value of the '<em><b>Para</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.docbook.Para}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Para</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Para</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getAbstractSect_Para()
     * @model containment="true"
     * @generated
     */
    EList<Para> getPara();

} // AbstractSect
