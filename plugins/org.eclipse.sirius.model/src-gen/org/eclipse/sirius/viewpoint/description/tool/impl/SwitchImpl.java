/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.tool.Case;
import org.eclipse.sirius.viewpoint.description.tool.Default;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Switch</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.SwitchImpl#getCases <em>Cases</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.SwitchImpl#getDefault <em>Default</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SwitchImpl extends ModelOperationImpl implements Switch {
    /**
     * The cached value of the '{@link #getCases() <em>Cases</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getCases()
     * @generated
     * @ordered
     */
    protected EList<Case> cases;

    /**
     * The cached value of the '{@link #getDefault() <em>Default</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDefault()
     * @generated
     * @ordered
     */
    protected Default default_;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected SwitchImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.SWITCH;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Case> getCases() {
        if (cases == null) {
            cases = new EObjectContainmentEList.Resolving<Case>(Case.class, this, ToolPackage.SWITCH__CASES);
        }
        return cases;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Default getDefault() {
        if (default_ != null && default_.eIsProxy()) {
            InternalEObject oldDefault = (InternalEObject) default_;
            default_ = (Default) eResolveProxy(oldDefault);
            if (default_ != oldDefault) {
                InternalEObject newDefault = (InternalEObject) default_;
                NotificationChain msgs = oldDefault.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.SWITCH__DEFAULT, null, null);
                if (newDefault.eInternalContainer() == null) {
                    msgs = newDefault.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.SWITCH__DEFAULT, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.SWITCH__DEFAULT, oldDefault, default_));
                }
            }
        }
        return default_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Default basicGetDefault() {
        return default_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDefault(Default newDefault, NotificationChain msgs) {
        Default oldDefault = default_;
        default_ = newDefault;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.SWITCH__DEFAULT, oldDefault, newDefault);
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
    public void setDefault(Default newDefault) {
        if (newDefault != default_) {
            NotificationChain msgs = null;
            if (default_ != null) {
                msgs = ((InternalEObject) default_).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.SWITCH__DEFAULT, null, msgs);
            }
            if (newDefault != null) {
                msgs = ((InternalEObject) newDefault).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.SWITCH__DEFAULT, null, msgs);
            }
            msgs = basicSetDefault(newDefault, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.SWITCH__DEFAULT, newDefault, newDefault));
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
        case ToolPackage.SWITCH__CASES:
            return ((InternalEList<?>) getCases()).basicRemove(otherEnd, msgs);
        case ToolPackage.SWITCH__DEFAULT:
            return basicSetDefault(null, msgs);
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
        case ToolPackage.SWITCH__CASES:
            return getCases();
        case ToolPackage.SWITCH__DEFAULT:
            if (resolve) {
                return getDefault();
            }
            return basicGetDefault();
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
        case ToolPackage.SWITCH__CASES:
            getCases().clear();
            getCases().addAll((Collection<? extends Case>) newValue);
            return;
        case ToolPackage.SWITCH__DEFAULT:
            setDefault((Default) newValue);
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
        case ToolPackage.SWITCH__CASES:
            getCases().clear();
            return;
        case ToolPackage.SWITCH__DEFAULT:
            setDefault((Default) null);
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
        case ToolPackage.SWITCH__CASES:
            return cases != null && !cases.isEmpty();
        case ToolPackage.SWITCH__DEFAULT:
            return default_ != null;
        }
        return super.eIsSet(featureID);
    }

} // SwitchImpl
