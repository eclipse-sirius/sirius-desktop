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
package org.eclipse.sirius.sample.interactions.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.InteractionUseEnd;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Participant;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Interaction Use</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionUseImpl#getType
 * <em>Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionUseImpl#getInteraction
 * <em>Interaction</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionUseImpl#getCoveredParticipants
 * <em>Covered Participants</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionUseImpl#getStart
 * <em>Start</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionUseImpl#getFinish
 * <em>Finish</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InteractionUseImpl extends EObjectImpl implements InteractionUse {
    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = "\"ref\""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected String type = InteractionUseImpl.TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getInteraction() <em>Interaction</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInteraction()
     * @generated
     * @ordered
     */
    protected Interaction interaction;

    /**
     * The cached value of the '{@link #getCoveredParticipants()
     * <em>Covered Participants</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getCoveredParticipants()
     * @generated
     * @ordered
     */
    protected EList<Participant> coveredParticipants;

    /**
     * The cached value of the '{@link #getStart() <em>Start</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStart()
     * @generated
     * @ordered
     */
    protected InteractionUseEnd start;

    /**
     * The cached value of the '{@link #getFinish() <em>Finish</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFinish()
     * @generated
     * @ordered
     */
    protected InteractionUseEnd finish;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InteractionUseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.INTERACTION_USE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.INTERACTION_USE__TYPE, oldType, type));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Interaction getInteraction() {
        if (interaction != null && interaction.eIsProxy()) {
            InternalEObject oldInteraction = (InternalEObject) interaction;
            interaction = (Interaction) eResolveProxy(oldInteraction);
            if (interaction != oldInteraction) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.INTERACTION_USE__INTERACTION, oldInteraction, interaction));
                }
            }
        }
        return interaction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Interaction basicGetInteraction() {
        return interaction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInteraction(Interaction newInteraction) {
        Interaction oldInteraction = interaction;
        interaction = newInteraction;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.INTERACTION_USE__INTERACTION, oldInteraction, interaction));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Participant> getCoveredParticipants() {
        if (coveredParticipants == null) {
            coveredParticipants = new EObjectResolvingEList<Participant>(Participant.class, this, InteractionsPackage.INTERACTION_USE__COVERED_PARTICIPANTS);
        }
        return coveredParticipants;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InteractionUseEnd getStart() {
        if (start != null && start.eIsProxy()) {
            InternalEObject oldStart = (InternalEObject) start;
            start = (InteractionUseEnd) eResolveProxy(oldStart);
            if (start != oldStart) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.INTERACTION_USE__START, oldStart, start));
                }
            }
        }
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InteractionUseEnd basicGetStart() {
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStart(InteractionUseEnd newStart) {
        InteractionUseEnd oldStart = start;
        start = newStart;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.INTERACTION_USE__START, oldStart, start));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InteractionUseEnd getFinish() {
        if (finish != null && finish.eIsProxy()) {
            InternalEObject oldFinish = (InternalEObject) finish;
            finish = (InteractionUseEnd) eResolveProxy(oldFinish);
            if (finish != oldFinish) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.INTERACTION_USE__FINISH, oldFinish, finish));
                }
            }
        }
        return finish;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InteractionUseEnd basicGetFinish() {
        return finish;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFinish(InteractionUseEnd newFinish) {
        InteractionUseEnd oldFinish = finish;
        finish = newFinish;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.INTERACTION_USE__FINISH, oldFinish, finish));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case InteractionsPackage.INTERACTION_USE__TYPE:
            return getType();
        case InteractionsPackage.INTERACTION_USE__INTERACTION:
            if (resolve) {
                return getInteraction();
            }
            return basicGetInteraction();
        case InteractionsPackage.INTERACTION_USE__COVERED_PARTICIPANTS:
            return getCoveredParticipants();
        case InteractionsPackage.INTERACTION_USE__START:
            if (resolve) {
                return getStart();
            }
            return basicGetStart();
        case InteractionsPackage.INTERACTION_USE__FINISH:
            if (resolve) {
                return getFinish();
            }
            return basicGetFinish();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case InteractionsPackage.INTERACTION_USE__TYPE:
            setType((String) newValue);
            return;
        case InteractionsPackage.INTERACTION_USE__INTERACTION:
            setInteraction((Interaction) newValue);
            return;
        case InteractionsPackage.INTERACTION_USE__COVERED_PARTICIPANTS:
            getCoveredParticipants().clear();
            getCoveredParticipants().addAll((Collection<? extends Participant>) newValue);
            return;
        case InteractionsPackage.INTERACTION_USE__START:
            setStart((InteractionUseEnd) newValue);
            return;
        case InteractionsPackage.INTERACTION_USE__FINISH:
            setFinish((InteractionUseEnd) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case InteractionsPackage.INTERACTION_USE__TYPE:
            setType(InteractionUseImpl.TYPE_EDEFAULT);
            return;
        case InteractionsPackage.INTERACTION_USE__INTERACTION:
            setInteraction((Interaction) null);
            return;
        case InteractionsPackage.INTERACTION_USE__COVERED_PARTICIPANTS:
            getCoveredParticipants().clear();
            return;
        case InteractionsPackage.INTERACTION_USE__START:
            setStart((InteractionUseEnd) null);
            return;
        case InteractionsPackage.INTERACTION_USE__FINISH:
            setFinish((InteractionUseEnd) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case InteractionsPackage.INTERACTION_USE__TYPE:
            return InteractionUseImpl.TYPE_EDEFAULT == null ? type != null : !InteractionUseImpl.TYPE_EDEFAULT.equals(type);
        case InteractionsPackage.INTERACTION_USE__INTERACTION:
            return interaction != null;
        case InteractionsPackage.INTERACTION_USE__COVERED_PARTICIPANTS:
            return coveredParticipants != null && !coveredParticipants.isEmpty();
        case InteractionsPackage.INTERACTION_USE__START:
            return start != null;
        case InteractionsPackage.INTERACTION_USE__FINISH:
            return finish != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (type: "); //$NON-NLS-1$
        result.append(type);
        result.append(')');
        return result.toString();
    }

} // InteractionUseImpl
