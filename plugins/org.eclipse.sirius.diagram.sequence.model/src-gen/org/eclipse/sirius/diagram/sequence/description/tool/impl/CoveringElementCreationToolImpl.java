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
package org.eclipse.sirius.diagram.sequence.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.diagram.sequence.description.CoveredLifelinesVariable;
import org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Covering Element Creation Tool</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.CoveringElementCreationToolImpl#getCoveredLifelines
 * <em>Covered Lifelines</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CoveringElementCreationToolImpl extends MinimalEObjectImpl.Container implements CoveringElementCreationTool {
    /**
     * The cached value of the '{@link #getCoveredLifelines() <em>Covered Lifelines</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCoveredLifelines()
     * @generated
     * @ordered
     */
    protected CoveredLifelinesVariable coveredLifelines;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CoveringElementCreationToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.COVERING_ELEMENT_CREATION_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CoveredLifelinesVariable getCoveredLifelines() {
        return coveredLifelines;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCoveredLifelines(CoveredLifelinesVariable newCoveredLifelines, NotificationChain msgs) {
        CoveredLifelinesVariable oldCoveredLifelines = coveredLifelines;
        coveredLifelines = newCoveredLifelines;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES, oldCoveredLifelines, newCoveredLifelines);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCoveredLifelines(CoveredLifelinesVariable newCoveredLifelines) {
        if (newCoveredLifelines != coveredLifelines) {
            NotificationChain msgs = null;
            if (coveredLifelines != null) {
                msgs = ((InternalEObject) coveredLifelines).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES, null, msgs);
            }
            if (newCoveredLifelines != null) {
                msgs = ((InternalEObject) newCoveredLifelines).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES, null, msgs);
            }
            msgs = basicSetCoveredLifelines(newCoveredLifelines, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES, newCoveredLifelines, newCoveredLifelines));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES:
            return basicSetCoveredLifelines(null, msgs);
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
        case ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES:
            return getCoveredLifelines();
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
        case ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES:
            setCoveredLifelines((CoveredLifelinesVariable) newValue);
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
        case ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES:
            setCoveredLifelines((CoveredLifelinesVariable) null);
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
        case ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES:
            return coveredLifelines != null;
        }
        return super.eIsSet(featureID);
    }

} // CoveringElementCreationToolImpl
