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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.CombinedFragmentEnd;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Operand;
import org.eclipse.sirius.sample.interactions.Participant;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Combined Fragment</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.CombinedFragmentImpl#getOperator
 * <em>Operator</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.CombinedFragmentImpl#getCoveredParticipants
 * <em>Covered Participants</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.CombinedFragmentImpl#getStart
 * <em>Start</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.CombinedFragmentImpl#getFinish
 * <em>Finish</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.CombinedFragmentImpl#getOwnedOperands
 * <em>Owned Operands</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CombinedFragmentImpl extends EObjectImpl implements CombinedFragment {
    /**
     * The default value of the '{@link #getOperator() <em>Operator</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOperator()
     * @generated
     * @ordered
     */
    protected static final String OPERATOR_EDEFAULT = "\"opt\""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getOperator() <em>Operator</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOperator()
     * @generated
     * @ordered
     */
    protected String operator = CombinedFragmentImpl.OPERATOR_EDEFAULT;

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
    protected CombinedFragmentEnd start;

    /**
     * The cached value of the '{@link #getFinish() <em>Finish</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFinish()
     * @generated
     * @ordered
     */
    protected CombinedFragmentEnd finish;

    /**
     * The cached value of the '{@link #getOwnedOperands()
     * <em>Owned Operands</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getOwnedOperands()
     * @generated
     * @ordered
     */
    protected EList<Operand> ownedOperands;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CombinedFragmentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.COMBINED_FRAGMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getOperator() {
        return operator;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOperator(String newOperator) {
        String oldOperator = operator;
        operator = newOperator;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.COMBINED_FRAGMENT__OPERATOR, oldOperator, operator));
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
            coveredParticipants = new EObjectResolvingEList<Participant>(Participant.class, this, InteractionsPackage.COMBINED_FRAGMENT__COVERED_PARTICIPANTS);
        }
        return coveredParticipants;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CombinedFragmentEnd getStart() {
        if (start != null && start.eIsProxy()) {
            InternalEObject oldStart = (InternalEObject) start;
            start = (CombinedFragmentEnd) eResolveProxy(oldStart);
            if (start != oldStart) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.COMBINED_FRAGMENT__START, oldStart, start));
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
    public CombinedFragmentEnd basicGetStart() {
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStart(CombinedFragmentEnd newStart) {
        CombinedFragmentEnd oldStart = start;
        start = newStart;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.COMBINED_FRAGMENT__START, oldStart, start));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CombinedFragmentEnd getFinish() {
        if (finish != null && finish.eIsProxy()) {
            InternalEObject oldFinish = (InternalEObject) finish;
            finish = (CombinedFragmentEnd) eResolveProxy(oldFinish);
            if (finish != oldFinish) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.COMBINED_FRAGMENT__FINISH, oldFinish, finish));
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
    public CombinedFragmentEnd basicGetFinish() {
        return finish;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFinish(CombinedFragmentEnd newFinish) {
        CombinedFragmentEnd oldFinish = finish;
        finish = newFinish;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.COMBINED_FRAGMENT__FINISH, oldFinish, finish));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Operand> getOwnedOperands() {
        if (ownedOperands == null) {
            ownedOperands = new EObjectContainmentEList<Operand>(Operand.class, this, InteractionsPackage.COMBINED_FRAGMENT__OWNED_OPERANDS);
        }
        return ownedOperands;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case InteractionsPackage.COMBINED_FRAGMENT__OWNED_OPERANDS:
            return ((InternalEList<?>) getOwnedOperands()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case InteractionsPackage.COMBINED_FRAGMENT__OPERATOR:
            return getOperator();
        case InteractionsPackage.COMBINED_FRAGMENT__COVERED_PARTICIPANTS:
            return getCoveredParticipants();
        case InteractionsPackage.COMBINED_FRAGMENT__START:
            if (resolve) {
                return getStart();
            }
            return basicGetStart();
        case InteractionsPackage.COMBINED_FRAGMENT__FINISH:
            if (resolve) {
                return getFinish();
            }
            return basicGetFinish();
        case InteractionsPackage.COMBINED_FRAGMENT__OWNED_OPERANDS:
            return getOwnedOperands();
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
        case InteractionsPackage.COMBINED_FRAGMENT__OPERATOR:
            setOperator((String) newValue);
            return;
        case InteractionsPackage.COMBINED_FRAGMENT__COVERED_PARTICIPANTS:
            getCoveredParticipants().clear();
            getCoveredParticipants().addAll((Collection<? extends Participant>) newValue);
            return;
        case InteractionsPackage.COMBINED_FRAGMENT__START:
            setStart((CombinedFragmentEnd) newValue);
            return;
        case InteractionsPackage.COMBINED_FRAGMENT__FINISH:
            setFinish((CombinedFragmentEnd) newValue);
            return;
        case InteractionsPackage.COMBINED_FRAGMENT__OWNED_OPERANDS:
            getOwnedOperands().clear();
            getOwnedOperands().addAll((Collection<? extends Operand>) newValue);
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
        case InteractionsPackage.COMBINED_FRAGMENT__OPERATOR:
            setOperator(CombinedFragmentImpl.OPERATOR_EDEFAULT);
            return;
        case InteractionsPackage.COMBINED_FRAGMENT__COVERED_PARTICIPANTS:
            getCoveredParticipants().clear();
            return;
        case InteractionsPackage.COMBINED_FRAGMENT__START:
            setStart((CombinedFragmentEnd) null);
            return;
        case InteractionsPackage.COMBINED_FRAGMENT__FINISH:
            setFinish((CombinedFragmentEnd) null);
            return;
        case InteractionsPackage.COMBINED_FRAGMENT__OWNED_OPERANDS:
            getOwnedOperands().clear();
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
        case InteractionsPackage.COMBINED_FRAGMENT__OPERATOR:
            return CombinedFragmentImpl.OPERATOR_EDEFAULT == null ? operator != null : !CombinedFragmentImpl.OPERATOR_EDEFAULT.equals(operator);
        case InteractionsPackage.COMBINED_FRAGMENT__COVERED_PARTICIPANTS:
            return coveredParticipants != null && !coveredParticipants.isEmpty();
        case InteractionsPackage.COMBINED_FRAGMENT__START:
            return start != null;
        case InteractionsPackage.COMBINED_FRAGMENT__FINISH:
            return finish != null;
        case InteractionsPackage.COMBINED_FRAGMENT__OWNED_OPERANDS:
            return ownedOperands != null && !ownedOperands.isEmpty();
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
        result.append(" (operator: "); //$NON-NLS-1$
        result.append(operator);
        result.append(')');
        return result.toString();
    }

} // CombinedFragmentImpl
