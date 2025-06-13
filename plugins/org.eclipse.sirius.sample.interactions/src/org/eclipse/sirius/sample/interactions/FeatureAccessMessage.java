/**
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.interactions;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Feature Access Message</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage#isIsWrite <em>Is Write</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage#getFeature <em>Feature</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getFeatureAccessMessage()
 * @model
 * @generated
 */
public interface FeatureAccessMessage extends Message {
    /**
     * Returns the value of the '<em><b>Is Write</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Is Write</em>' attribute.
     * @see #setIsWrite(boolean)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getFeatureAccessMessage_IsWrite()
     * @model required="true"
     * @generated
     */
    boolean isIsWrite();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage#isIsWrite <em>Is
     * Write</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Is Write</em>' attribute.
     * @see #isIsWrite()
     * @generated
     */
    void setIsWrite(boolean value);

    /**
     * Returns the value of the '<em><b>Feature</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Feature</em>' reference.
     * @see #setFeature(EStructuralFeature)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getFeatureAccessMessage_Feature()
     * @model required="true"
     * @generated
     */
    EStructuralFeature getFeature();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage#getFeature
     * <em>Feature</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Feature</em>' reference.
     * @see #getFeature()
     * @generated
     */
    void setFeature(EStructuralFeature value);

} // FeatureAccessMessage
