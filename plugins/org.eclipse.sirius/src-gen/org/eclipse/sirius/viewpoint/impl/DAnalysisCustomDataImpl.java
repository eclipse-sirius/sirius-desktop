/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DAnalysis Custom Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl#getKey
 * <em>Key</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DAnalysisCustomDataImpl#getData
 * <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DAnalysisCustomDataImpl extends MinimalEObjectImpl.Container implements DAnalysisCustomData {
    /**
     * The default value of the '{@link #getKey() <em>Key</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getKey()
     * @generated
     * @ordered
     */
    protected static final String KEY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getKey() <em>Key</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getKey()
     * @generated
     * @ordered
     */
    protected String key = DAnalysisCustomDataImpl.KEY_EDEFAULT;

    /**
     * The cached value of the '{@link #getData() <em>Data</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getData()
     * @generated
     * @ordered
     */
    protected EObject data;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DAnalysisCustomDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DANALYSIS_CUSTOM_DATA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setKey(String newKey) {
        String oldKey = key;
        key = newKey;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DANALYSIS_CUSTOM_DATA__KEY, oldKey, key));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject getData() {
        if (data != null && data.eIsProxy()) {
            InternalEObject oldData = (InternalEObject) data;
            data = eResolveProxy(oldData);
            if (data != oldData) {
                InternalEObject newData = (InternalEObject) data;
                NotificationChain msgs = oldData.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA, null, null);
                if (newData.eInternalContainer() == null) {
                    msgs = newData.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA, oldData, data));
                }
            }
        }
        return data;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetData() {
        return data;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetData(EObject newData, NotificationChain msgs) {
        EObject oldData = data;
        data = newData;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA, oldData, newData);
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
    public void setData(EObject newData) {
        if (newData != data) {
            NotificationChain msgs = null;
            if (data != null) {
                msgs = ((InternalEObject) data).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA, null, msgs);
            }
            if (newData != null) {
                msgs = ((InternalEObject) newData).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA, null, msgs);
            }
            msgs = basicSetData(newData, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA, newData, newData));
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
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA:
            return basicSetData(null, msgs);
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
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA__KEY:
            return getKey();
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA:
            if (resolve) {
                return getData();
            }
            return basicGetData();
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
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA__KEY:
            setKey((String) newValue);
            return;
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA:
            setData((EObject) newValue);
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
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA__KEY:
            setKey(DAnalysisCustomDataImpl.KEY_EDEFAULT);
            return;
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA:
            setData((EObject) null);
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
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA__KEY:
            return DAnalysisCustomDataImpl.KEY_EDEFAULT == null ? key != null : !DAnalysisCustomDataImpl.KEY_EDEFAULT.equals(key);
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA__DATA:
            return data != null;
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
        result.append(" (key: "); //$NON-NLS-1$
        result.append(key);
        result.append(')');
        return result.toString();
    }

} // DAnalysisCustomDataImpl
