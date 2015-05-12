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
 * <em><b>Emphasis</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Emphasis#getRemap <em>
 * Remap</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getEmphasis()
 * @model
 * @generated
 */
public interface Emphasis extends EObject {
    /**
     * Returns the value of the '<em><b>Remap</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Remap</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Remap</em>' attribute.
     * @see #setRemap(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getEmphasis_Remap()
     * @model
     * @generated
     */
    String getRemap();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Emphasis#getRemap
     * <em>Remap</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Remap</em>' attribute.
     * @see #getRemap()
     * @generated
     */
    void setRemap(String value);

} // Emphasis
