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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Model</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A simple container for Interactions, so that one can
 * put several interactions inside the same resource. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.Model#getName <em>Name
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Model#getOwnedInteractions
 * <em>Owned Interactions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getModel_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.Model#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Owned Interactions</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.Interaction}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Interactions</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Interactions</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getModel_OwnedInteractions()
     * @model containment="true"
     * @generated
     */
    EList<Interaction> getOwnedInteractions();

} // Model
