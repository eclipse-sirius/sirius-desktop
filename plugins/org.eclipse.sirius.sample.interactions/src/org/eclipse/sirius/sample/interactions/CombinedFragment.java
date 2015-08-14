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
 * <em><b>Combined Fragment</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A group of events with some special semantics (e.g.
 * optional). <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getOperator
 * <em>Operator</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getCoveredParticipants
 * <em>Covered Participants</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.CombinedFragment#getStart
 * <em>Start</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.CombinedFragment#getFinish
 * <em>Finish</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getOwnedOperands
 * <em>Owned Operands</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getCombinedFragment()
 * @model
 * @generated
 */
public interface CombinedFragment extends EObject {
    /**
     * Returns the value of the '<em><b>Operator</b></em>' attribute. The
     * default value is <code>"\"opt\""</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operator</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Operator</em>' attribute.
     * @see #setOperator(String)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getCombinedFragment_Operator()
     * @model default="\"opt\"" required="true"
     * @generated
     */
    String getOperator();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getOperator
     * <em>Operator</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Operator</em>' attribute.
     * @see #getOperator()
     * @generated
     */
    void setOperator(String value);

    /**
     * Returns the value of the '<em><b>Covered Participants</b></em>' reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.Participant}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Covered Participants</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Covered Participants</em>' reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getCombinedFragment_CoveredParticipants()
     * @model required="true"
     * @generated
     */
    EList<Participant> getCoveredParticipants();

    /**
     * Returns the value of the '<em><b>Start</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Start</em>' reference.
     * @see #setStart(InteractionUseEnd)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getCombinedFragment_Start()
     * @model required="true"
     * @generated
     */
    CombinedFragmentEnd getStart();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getStart
     * <em>Start</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Start</em>' reference.
     * @see #getStart()
     * @generated
     */
    void setStart(CombinedFragmentEnd value);

    /**
     * Returns the value of the '<em><b>Finish</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Finish</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Finish</em>' reference.
     * @see #setFinish(InteractionUseEnd)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getCombinedFragment_Finish()
     * @model required="true"
     * @generated
     */
    CombinedFragmentEnd getFinish();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragment#getFinish
     * <em>Finish</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Finish</em>' reference.
     * @see #getFinish()
     * @generated
     */
    void setFinish(CombinedFragmentEnd value);

    /**
     * Returns the value of the '<em><b>Owned Operands</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.Operand}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Operands</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Operands</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getCombinedFragment_OwnedOperands()
     * @model containment="true" required="true"
     * @generated
     */
    EList<Operand> getOwnedOperands();

} // CombinedFragment
