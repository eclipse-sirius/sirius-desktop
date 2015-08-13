/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.description.contribution;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Point</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.ContributionPoint#getOrigin
 * <em>Origin</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.ContributionPoint#getContributed
 * <em>Contributed</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContributionPoint()
 * @model
 * @generated
 */
public interface ContributionPoint extends EObject {
    /**
     * Returns the value of the '<em><b>Origin</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Origin</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Origin</em>' attribute.
     * @see #setOrigin(String)
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContributionPoint_Origin()
     * @model required="true"
     * @generated
     */
    String getOrigin();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.contribution.ContributionPoint#getOrigin
     * <em>Origin</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Origin</em>' attribute.
     * @see #getOrigin()
     * @generated
     */
    void setOrigin(String value);

    /**
     * Returns the value of the '<em><b>Contributed</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Contributed</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Contributed</em>' reference.
     * @see #setContributed(EObject)
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getContributionPoint_Contributed()
     * @model required="true"
     * @generated
     */
    EObject getContributed();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.contribution.ContributionPoint#getContributed
     * <em>Contributed</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Contributed</em>' reference.
     * @see #getContributed()
     * @generated
     */
    void setContributed(EObject value);

} // ContributionPoint
