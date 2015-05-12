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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>ULink</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.ULink#getUrl <em>Url</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.ULink#getData <em>Data
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getULink()
 * @model extendedMetaData="kind='simple'"
 * @generated
 */
public interface ULink extends EObject {
    /**
     * Returns the value of the '<em><b>Url</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Url</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Url</em>' attribute.
     * @see #setUrl(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getULink_Url()
     * @model
     * @generated
     */
    String getUrl();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.ULink#getUrl <em>Url</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Url</em>' attribute.
     * @see #getUrl()
     * @generated
     */
    void setUrl(String value);

    /**
     * Returns the value of the '<em><b>Data</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Data</em>' attribute.
     * @see #setData(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getULink_Data()
     * @model extendedMetaData="kind='simple'"
     * @generated
     */
    String getData();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.ULink#getData
     * <em>Data</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Data</em>' attribute.
     * @see #getData()
     * @generated
     */
    void setData(String value);

} // ULink
