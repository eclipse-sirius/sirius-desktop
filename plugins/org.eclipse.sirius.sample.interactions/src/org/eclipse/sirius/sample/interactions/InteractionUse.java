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
 * <em><b>Interaction Use</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A reference to or invocation of another interaction
 * defined elsewhere. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.InteractionUse#getType <em>
 * Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getInteraction
 * <em>Interaction</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getCoveredParticipants
 * <em>Covered Participants</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.InteractionUse#getStart
 * <em>Start</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.InteractionUse#getFinish
 * <em>Finish</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteractionUse()
 * @model
 * @generated
 */
public interface InteractionUse extends EObject {
    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. The default
     * value is <code>"\"ref\""</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteractionUse_Type()
     * @model default="\"ref\"" required="true"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getType
     * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

    /**
     * Returns the value of the '<em><b>Interaction</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interaction</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Interaction</em>' reference.
     * @see #setInteraction(Interaction)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteractionUse_Interaction()
     * @model required="true"
     * @generated
     */
    Interaction getInteraction();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getInteraction
     * <em>Interaction</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Interaction</em>' reference.
     * @see #getInteraction()
     * @generated
     */
    void setInteraction(Interaction value);

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
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteractionUse_CoveredParticipants()
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
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteractionUse_Start()
     * @model required="true"
     * @generated
     */
    InteractionUseEnd getStart();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getStart
     * <em>Start</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Start</em>' reference.
     * @see #getStart()
     * @generated
     */
    void setStart(InteractionUseEnd value);

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
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteractionUse_Finish()
     * @model required="true"
     * @generated
     */
    InteractionUseEnd getFinish();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse#getFinish
     * <em>Finish</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Finish</em>' reference.
     * @see #getFinish()
     * @generated
     */
    void setFinish(InteractionUseEnd value);

} // InteractionUse
