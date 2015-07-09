/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.interactions;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Feature Access Message</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage#isIsWrite
 * <em>Is Write</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage#getFeature
 * <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getFeatureAccessMessage()
 * @model
 * @generated
 */
public interface FeatureAccessMessage extends Message {
    /**
     * Returns the value of the '<em><b>Is Write</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Write</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Is Write</em>' attribute.
     * @see #setIsWrite(boolean)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getFeatureAccessMessage_IsWrite()
     * @model required="true"
     * @generated
     */
    boolean isIsWrite();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage#isIsWrite
     * <em>Is Write</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Is Write</em>' attribute.
     * @see #isIsWrite()
     * @generated
     */
    void setIsWrite(boolean value);

    /**
     * Returns the value of the '<em><b>Feature</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Feature</em>' reference.
     * @see #setFeature(EStructuralFeature)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getFeatureAccessMessage_Feature()
     * @model required="true"
     * @generated
     */
    EStructuralFeature getFeature();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage#getFeature
     * <em>Feature</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Feature</em>' reference.
     * @see #getFeature()
     * @generated
     */
    void setFeature(EStructuralFeature value);

} // FeatureAccessMessage
