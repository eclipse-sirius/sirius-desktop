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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gate End</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.sample.interactions.GateEnd#getGate <em>Gate</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getGateEnd()
 * @model
 * @generated
 */
public interface GateEnd extends AbstractEnd {
    /**
     * Returns the value of the '<em><b>Gate</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Gate</em>' reference.
     * @see #setGate(Gate)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getGateEnd_Gate()
     * @model required="true"
     * @generated
     */
    Gate getGate();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.GateEnd#getGate <em>Gate</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Gate</em>' reference.
     * @see #getGate()
     * @generated
     */
    void setGate(Gate value);

} // GateEnd
