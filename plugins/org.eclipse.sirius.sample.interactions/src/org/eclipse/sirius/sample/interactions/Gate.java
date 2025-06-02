/**
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.interactions;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents an interval of time in which a particapant is active executing some code.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.sample.interactions.Gate#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.sirius.sample.interactions.Gate#getEnd <em>End</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getGate()
 * @model
 * @generated
 */
public interface Gate extends AbstractEndContext {
    /**
     * Returns the value of the '<em><b>Start</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Start</em>' reference.
     * @see #setStart(GateEnd)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getGate_Start()
     * @model required="true"
     * @generated
     */
    GateEnd getStart();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.Gate#getStart <em>Start</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start</em>' reference.
     * @see #getStart()
     * @generated
     */
    void setStart(GateEnd value);

    /**
     * Returns the value of the '<em><b>End</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>End</em>' reference.
     * @see #setEnd(GateEnd)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getGate_End()
     * @model required="true"
     * @generated
     */
    GateEnd getEnd();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.Gate#getEnd <em>End</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>End</em>' reference.
     * @see #getEnd()
     * @generated
     */
    void setEnd(GateEnd value);

} // Gate
