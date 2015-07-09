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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.sample.interactions.AbstractEnd;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.Constraint;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Message;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.sample.interactions.State;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Interaction</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl#getParticipants
 * <em>Participants</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl#getMessages
 * <em>Messages</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl#getExecutions
 * <em>Executions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl#getStates
 * <em>States</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl#getInteractionUses
 * <em>Interaction Uses</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl#getCombinedFragments
 * <em>Combined Fragments</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl#getEnds
 * <em>Ends</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionImpl#getConstraints
 * <em>Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InteractionImpl extends EObjectImpl implements Interaction {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = InteractionImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getParticipants() <em>Participants</em>}
     * ' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getParticipants()
     * @generated
     * @ordered
     */
    protected EList<Participant> participants;

    /**
     * The cached value of the '{@link #getMessages() <em>Messages</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMessages()
     * @generated
     * @ordered
     */
    protected EList<Message> messages;

    /**
     * The cached value of the '{@link #getExecutions() <em>Executions</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getExecutions()
     * @generated
     * @ordered
     */
    protected EList<Execution> executions;

    /**
     * The cached value of the '{@link #getStates() <em>States</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStates()
     * @generated
     * @ordered
     */
    protected EList<State> states;

    /**
     * The cached value of the '{@link #getInteractionUses()
     * <em>Interaction Uses</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInteractionUses()
     * @generated
     * @ordered
     */
    protected EList<InteractionUse> interactionUses;

    /**
     * The cached value of the '{@link #getCombinedFragments()
     * <em>Combined Fragments</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCombinedFragments()
     * @generated
     * @ordered
     */
    protected EList<CombinedFragment> combinedFragments;

    /**
     * The cached value of the '{@link #getEnds() <em>Ends</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEnds()
     * @generated
     * @ordered
     */
    protected EList<AbstractEnd> ends;

    /**
     * The cached value of the '{@link #getConstraints() <em>Constraints</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConstraints()
     * @generated
     * @ordered
     */
    protected EList<Constraint> constraints;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InteractionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.INTERACTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.INTERACTION__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Participant> getParticipants() {
        if (participants == null) {
            participants = new EObjectContainmentEList<Participant>(Participant.class, this, InteractionsPackage.INTERACTION__PARTICIPANTS);
        }
        return participants;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Message> getMessages() {
        if (messages == null) {
            messages = new EObjectContainmentEList<Message>(Message.class, this, InteractionsPackage.INTERACTION__MESSAGES);
        }
        return messages;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Execution> getExecutions() {
        if (executions == null) {
            executions = new EObjectContainmentEList<Execution>(Execution.class, this, InteractionsPackage.INTERACTION__EXECUTIONS);
        }
        return executions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<State> getStates() {
        if (states == null) {
            states = new EObjectContainmentEList<State>(State.class, this, InteractionsPackage.INTERACTION__STATES);
        }
        return states;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<InteractionUse> getInteractionUses() {
        if (interactionUses == null) {
            interactionUses = new EObjectContainmentEList<InteractionUse>(InteractionUse.class, this, InteractionsPackage.INTERACTION__INTERACTION_USES);
        }
        return interactionUses;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<CombinedFragment> getCombinedFragments() {
        if (combinedFragments == null) {
            combinedFragments = new EObjectContainmentEList<CombinedFragment>(CombinedFragment.class, this, InteractionsPackage.INTERACTION__COMBINED_FRAGMENTS);
        }
        return combinedFragments;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractEnd> getEnds() {
        if (ends == null) {
            ends = new EObjectContainmentEList<AbstractEnd>(AbstractEnd.class, this, InteractionsPackage.INTERACTION__ENDS);
        }
        return ends;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Constraint> getConstraints() {
        if (constraints == null) {
            constraints = new EObjectContainmentEList<Constraint>(Constraint.class, this, InteractionsPackage.INTERACTION__CONSTRAINTS);
        }
        return constraints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case InteractionsPackage.INTERACTION__PARTICIPANTS:
            return ((InternalEList<?>) getParticipants()).basicRemove(otherEnd, msgs);
        case InteractionsPackage.INTERACTION__MESSAGES:
            return ((InternalEList<?>) getMessages()).basicRemove(otherEnd, msgs);
        case InteractionsPackage.INTERACTION__EXECUTIONS:
            return ((InternalEList<?>) getExecutions()).basicRemove(otherEnd, msgs);
        case InteractionsPackage.INTERACTION__STATES:
            return ((InternalEList<?>) getStates()).basicRemove(otherEnd, msgs);
        case InteractionsPackage.INTERACTION__INTERACTION_USES:
            return ((InternalEList<?>) getInteractionUses()).basicRemove(otherEnd, msgs);
        case InteractionsPackage.INTERACTION__COMBINED_FRAGMENTS:
            return ((InternalEList<?>) getCombinedFragments()).basicRemove(otherEnd, msgs);
        case InteractionsPackage.INTERACTION__ENDS:
            return ((InternalEList<?>) getEnds()).basicRemove(otherEnd, msgs);
        case InteractionsPackage.INTERACTION__CONSTRAINTS:
            return ((InternalEList<?>) getConstraints()).basicRemove(otherEnd, msgs);
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
        case InteractionsPackage.INTERACTION__NAME:
            return getName();
        case InteractionsPackage.INTERACTION__PARTICIPANTS:
            return getParticipants();
        case InteractionsPackage.INTERACTION__MESSAGES:
            return getMessages();
        case InteractionsPackage.INTERACTION__EXECUTIONS:
            return getExecutions();
        case InteractionsPackage.INTERACTION__STATES:
            return getStates();
        case InteractionsPackage.INTERACTION__INTERACTION_USES:
            return getInteractionUses();
        case InteractionsPackage.INTERACTION__COMBINED_FRAGMENTS:
            return getCombinedFragments();
        case InteractionsPackage.INTERACTION__ENDS:
            return getEnds();
        case InteractionsPackage.INTERACTION__CONSTRAINTS:
            return getConstraints();
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
        case InteractionsPackage.INTERACTION__NAME:
            setName((String) newValue);
            return;
        case InteractionsPackage.INTERACTION__PARTICIPANTS:
            getParticipants().clear();
            getParticipants().addAll((Collection<? extends Participant>) newValue);
            return;
        case InteractionsPackage.INTERACTION__MESSAGES:
            getMessages().clear();
            getMessages().addAll((Collection<? extends Message>) newValue);
            return;
        case InteractionsPackage.INTERACTION__EXECUTIONS:
            getExecutions().clear();
            getExecutions().addAll((Collection<? extends Execution>) newValue);
            return;
        case InteractionsPackage.INTERACTION__STATES:
            getStates().clear();
            getStates().addAll((Collection<? extends State>) newValue);
            return;
        case InteractionsPackage.INTERACTION__INTERACTION_USES:
            getInteractionUses().clear();
            getInteractionUses().addAll((Collection<? extends InteractionUse>) newValue);
            return;
        case InteractionsPackage.INTERACTION__COMBINED_FRAGMENTS:
            getCombinedFragments().clear();
            getCombinedFragments().addAll((Collection<? extends CombinedFragment>) newValue);
            return;
        case InteractionsPackage.INTERACTION__ENDS:
            getEnds().clear();
            getEnds().addAll((Collection<? extends AbstractEnd>) newValue);
            return;
        case InteractionsPackage.INTERACTION__CONSTRAINTS:
            getConstraints().clear();
            getConstraints().addAll((Collection<? extends Constraint>) newValue);
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
        case InteractionsPackage.INTERACTION__NAME:
            setName(InteractionImpl.NAME_EDEFAULT);
            return;
        case InteractionsPackage.INTERACTION__PARTICIPANTS:
            getParticipants().clear();
            return;
        case InteractionsPackage.INTERACTION__MESSAGES:
            getMessages().clear();
            return;
        case InteractionsPackage.INTERACTION__EXECUTIONS:
            getExecutions().clear();
            return;
        case InteractionsPackage.INTERACTION__STATES:
            getStates().clear();
            return;
        case InteractionsPackage.INTERACTION__INTERACTION_USES:
            getInteractionUses().clear();
            return;
        case InteractionsPackage.INTERACTION__COMBINED_FRAGMENTS:
            getCombinedFragments().clear();
            return;
        case InteractionsPackage.INTERACTION__ENDS:
            getEnds().clear();
            return;
        case InteractionsPackage.INTERACTION__CONSTRAINTS:
            getConstraints().clear();
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
        case InteractionsPackage.INTERACTION__NAME:
            return InteractionImpl.NAME_EDEFAULT == null ? name != null : !InteractionImpl.NAME_EDEFAULT.equals(name);
        case InteractionsPackage.INTERACTION__PARTICIPANTS:
            return participants != null && !participants.isEmpty();
        case InteractionsPackage.INTERACTION__MESSAGES:
            return messages != null && !messages.isEmpty();
        case InteractionsPackage.INTERACTION__EXECUTIONS:
            return executions != null && !executions.isEmpty();
        case InteractionsPackage.INTERACTION__STATES:
            return states != null && !states.isEmpty();
        case InteractionsPackage.INTERACTION__INTERACTION_USES:
            return interactionUses != null && !interactionUses.isEmpty();
        case InteractionsPackage.INTERACTION__COMBINED_FRAGMENTS:
            return combinedFragments != null && !combinedFragments.isEmpty();
        case InteractionsPackage.INTERACTION__ENDS:
            return ends != null && !ends.isEmpty();
        case InteractionsPackage.INTERACTION__CONSTRAINTS:
            return constraints != null && !constraints.isEmpty();
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} // InteractionImpl
