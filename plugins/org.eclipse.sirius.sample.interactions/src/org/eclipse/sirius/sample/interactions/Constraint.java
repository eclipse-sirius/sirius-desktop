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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Constraint</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A constraint between two abstract ends. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.Constraint#getExpression <em>Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Constraint#getConstrainedEnds <em>Constrained Ends</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getConstraint()
 * @model
 * @generated
 */
public interface Constraint extends EObject {
    /**
     * Returns the value of the '<em><b>Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Expression</em>' attribute.
     * @see #setExpression(String)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getConstraint_Expression()
     * @model required="true"
     * @generated
     */
    String getExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.Constraint#getExpression
     * <em>Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Expression</em>' attribute.
     * @see #getExpression()
     * @generated
     */
    void setExpression(String value);

    /**
     * Returns the value of the '<em><b>Constrained Ends</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.AbstractEnd}. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Constrained Ends</em>' reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getConstraint_ConstrainedEnds()
     * @model
     * @generated
     */
    EList<AbstractEnd> getConstrainedEnds();

} // Constraint
