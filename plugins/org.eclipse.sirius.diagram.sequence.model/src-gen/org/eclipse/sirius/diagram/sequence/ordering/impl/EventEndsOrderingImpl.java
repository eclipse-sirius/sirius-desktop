/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ordering.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Event Ends Ordering</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndsOrderingImpl#getSequenceDiagram <em>Sequence
 * Diagram</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndsOrderingImpl#getEventEnds <em>Event
 * Ends</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EventEndsOrderingImpl extends MinimalEObjectImpl.Container implements EventEndsOrdering {
    /**
     * The cached value of the '{@link #getSequenceDiagram() <em>Sequence Diagram</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getSequenceDiagram()
     * @generated
     * @ordered
     */
    protected SequenceDDiagram sequenceDiagram;

    /**
     * The cached value of the '{@link #getEventEnds() <em>Event Ends</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getEventEnds()
     * @generated
     * @ordered
     */
    protected EList<EventEnd> eventEnds;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EventEndsOrderingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OrderingPackage.Literals.EVENT_ENDS_ORDERING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SequenceDDiagram getSequenceDiagram() {
        if (sequenceDiagram != null && sequenceDiagram.eIsProxy()) {
            InternalEObject oldSequenceDiagram = (InternalEObject) sequenceDiagram;
            sequenceDiagram = (SequenceDDiagram) eResolveProxy(oldSequenceDiagram);
            if (sequenceDiagram != oldSequenceDiagram) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, OrderingPackage.EVENT_ENDS_ORDERING__SEQUENCE_DIAGRAM, oldSequenceDiagram, sequenceDiagram));
                }
            }
        }
        return sequenceDiagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SequenceDDiagram basicGetSequenceDiagram() {
        return sequenceDiagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSequenceDiagram(SequenceDDiagram newSequenceDiagram) {
        SequenceDDiagram oldSequenceDiagram = sequenceDiagram;
        sequenceDiagram = newSequenceDiagram;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, OrderingPackage.EVENT_ENDS_ORDERING__SEQUENCE_DIAGRAM, oldSequenceDiagram, sequenceDiagram));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EventEnd> getEventEnds() {
        if (eventEnds == null) {
            eventEnds = new EObjectResolvingEList<>(EventEnd.class, this, OrderingPackage.EVENT_ENDS_ORDERING__EVENT_ENDS);
        }
        return eventEnds;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case OrderingPackage.EVENT_ENDS_ORDERING__SEQUENCE_DIAGRAM:
            if (resolve) {
                return getSequenceDiagram();
            }
            return basicGetSequenceDiagram();
        case OrderingPackage.EVENT_ENDS_ORDERING__EVENT_ENDS:
            return getEventEnds();
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
        case OrderingPackage.EVENT_ENDS_ORDERING__SEQUENCE_DIAGRAM:
            setSequenceDiagram((SequenceDDiagram) newValue);
            return;
        case OrderingPackage.EVENT_ENDS_ORDERING__EVENT_ENDS:
            getEventEnds().clear();
            getEventEnds().addAll((Collection<? extends EventEnd>) newValue);
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
        case OrderingPackage.EVENT_ENDS_ORDERING__SEQUENCE_DIAGRAM:
            setSequenceDiagram((SequenceDDiagram) null);
            return;
        case OrderingPackage.EVENT_ENDS_ORDERING__EVENT_ENDS:
            getEventEnds().clear();
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
        case OrderingPackage.EVENT_ENDS_ORDERING__SEQUENCE_DIAGRAM:
            return sequenceDiagram != null;
        case OrderingPackage.EVENT_ENDS_ORDERING__EVENT_ENDS:
            return eventEnds != null && !eventEnds.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // EventEndsOrderingImpl
