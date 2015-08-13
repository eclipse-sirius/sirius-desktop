/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ordering.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Single Event End</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.SingleEventEndImpl#isStart
 * <em>Start</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.ordering.impl.SingleEventEndImpl#getSemanticEvent
 * <em>Semantic Event</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SingleEventEndImpl extends EventEndImpl implements SingleEventEnd {
    /**
     * The default value of the '{@link #isStart() <em>Start</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isStart()
     * @generated
     * @ordered
     */
    protected static final boolean START_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isStart() <em>Start</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isStart()
     * @generated
     * @ordered
     */
    protected boolean start = SingleEventEndImpl.START_EDEFAULT;

    /**
     * The cached value of the '{@link #getSemanticEvent()
     * <em>Semantic Event</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSemanticEvent()
     * @generated
     * @ordered
     */
    protected EObject semanticEvent;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected SingleEventEndImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OrderingPackage.Literals.SINGLE_EVENT_END;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isStart() {
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setStart(boolean newStart) {
        boolean oldStart = start;
        start = newStart;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, OrderingPackage.SINGLE_EVENT_END__START, oldStart, start));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject getSemanticEvent() {
        if (semanticEvent != null && semanticEvent.eIsProxy()) {
            InternalEObject oldSemanticEvent = (InternalEObject) semanticEvent;
            semanticEvent = eResolveProxy(oldSemanticEvent);
            if (semanticEvent != oldSemanticEvent) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, OrderingPackage.SINGLE_EVENT_END__SEMANTIC_EVENT, oldSemanticEvent, semanticEvent));
                }
            }
        }
        return semanticEvent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetSemanticEvent() {
        return semanticEvent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSemanticEvent(EObject newSemanticEvent) {
        EObject oldSemanticEvent = semanticEvent;
        semanticEvent = newSemanticEvent;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, OrderingPackage.SINGLE_EVENT_END__SEMANTIC_EVENT, oldSemanticEvent, semanticEvent));
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
        case OrderingPackage.SINGLE_EVENT_END__START:
            return isStart();
        case OrderingPackage.SINGLE_EVENT_END__SEMANTIC_EVENT:
            if (resolve) {
                return getSemanticEvent();
            }
            return basicGetSemanticEvent();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case OrderingPackage.SINGLE_EVENT_END__START:
            setStart((Boolean) newValue);
            return;
        case OrderingPackage.SINGLE_EVENT_END__SEMANTIC_EVENT:
            setSemanticEvent((EObject) newValue);
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
        case OrderingPackage.SINGLE_EVENT_END__START:
            setStart(SingleEventEndImpl.START_EDEFAULT);
            return;
        case OrderingPackage.SINGLE_EVENT_END__SEMANTIC_EVENT:
            setSemanticEvent((EObject) null);
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
        case OrderingPackage.SINGLE_EVENT_END__START:
            return start != SingleEventEndImpl.START_EDEFAULT;
        case OrderingPackage.SINGLE_EVENT_END__SEMANTIC_EVENT:
            return semanticEvent != null;
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
        result.append(" (start: "); //$NON-NLS-1$
        result.append(start);
        result.append(')');
        return result.toString();
    }
} // SingleEventEndImpl
