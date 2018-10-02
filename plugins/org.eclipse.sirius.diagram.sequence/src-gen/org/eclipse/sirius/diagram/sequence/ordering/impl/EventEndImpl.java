/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Event End</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.ordering.impl.EventEndImpl#getSemanticEnd <em>Semantic End</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EventEndImpl extends MinimalEObjectImpl.Container implements EventEnd {
    /**
     * The cached value of the '{@link #getSemanticEnd() <em>Semantic End</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getSemanticEnd()
     * @generated
     * @ordered
     */
    protected EObject semanticEnd;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EventEndImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OrderingPackage.Literals.EVENT_END;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject getSemanticEnd() {
        if (semanticEnd != null && semanticEnd.eIsProxy()) {
            InternalEObject oldSemanticEnd = (InternalEObject) semanticEnd;
            semanticEnd = eResolveProxy(oldSemanticEnd);
            if (semanticEnd != oldSemanticEnd) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, OrderingPackage.EVENT_END__SEMANTIC_END, oldSemanticEnd, semanticEnd));
                }
            }
        }
        return semanticEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EObject basicGetSemanticEnd() {
        return semanticEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSemanticEnd(EObject newSemanticEnd) {
        EObject oldSemanticEnd = semanticEnd;
        semanticEnd = newSemanticEnd;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, OrderingPackage.EVENT_END__SEMANTIC_END, oldSemanticEnd, semanticEnd));
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
        case OrderingPackage.EVENT_END__SEMANTIC_END:
            if (resolve) {
                return getSemanticEnd();
            }
            return basicGetSemanticEnd();
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
        case OrderingPackage.EVENT_END__SEMANTIC_END:
            setSemanticEnd((EObject) newValue);
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
        case OrderingPackage.EVENT_END__SEMANTIC_END:
            setSemanticEnd((EObject) null);
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
        case OrderingPackage.EVENT_END__SEMANTIC_END:
            return semanticEnd != null;
        }
        return super.eIsSet(featureID);
    }

} // EventEndImpl
