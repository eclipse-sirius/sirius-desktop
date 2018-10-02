/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.docbook;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>XRef</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.XRef#getLinkend <em>
 * Linkend</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getXRef()
 * @model
 * @generated
 */
public interface XRef extends EObject {
    /**
     * Returns the value of the '<em><b>Linkend</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Linkend</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Linkend</em>' attribute.
     * @see #setLinkend(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getXRef_Linkend()
     * @model
     * @generated
     */
    String getLinkend();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.XRef#getLinkend
     * <em>Linkend</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Linkend</em>' attribute.
     * @see #getLinkend()
     * @generated
     */
    void setLinkend(String value);

} // XRef
