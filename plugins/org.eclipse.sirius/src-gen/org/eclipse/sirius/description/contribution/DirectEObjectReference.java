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
 * <em><b>Direct EObject Reference</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.DirectEObjectReference#getValue
 * <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.description.contribution.ContributionPackage#getDirectEObjectReference()
 * @model
 * @generated
 */
public interface DirectEObjectReference extends EObjectReference {
    /**
     * Returns the value of the '<em><b>Value</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Value</em>' reference.
     * @see #setValue(EObject)
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#getDirectEObjectReference_Value()
     * @model required="true"
     * @generated
     */
    EObject getValue();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.contribution.DirectEObjectReference#getValue
     * <em>Value</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value</em>' reference.
     * @see #getValue()
     * @generated
     */
    void setValue(EObject value);

} // DirectEObjectReference
