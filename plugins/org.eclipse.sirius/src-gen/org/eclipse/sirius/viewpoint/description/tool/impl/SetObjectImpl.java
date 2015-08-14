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
package org.eclipse.sirius.viewpoint.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Set Object</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SetObjectImpl#getFeatureName
 * <em>Feature Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SetObjectImpl#getObject
 * <em>Object</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SetObjectImpl extends ContainerModelOperationImpl implements SetObject {
    /**
     * The default value of the '{@link #getFeatureName() <em>Feature Name</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFeatureName()
     * @generated
     * @ordered
     */
    protected static final String FEATURE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFeatureName() <em>Feature Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFeatureName()
     * @generated
     * @ordered
     */
    protected String featureName = SetObjectImpl.FEATURE_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getObject() <em>Object</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getObject()
     * @generated
     * @ordered
     */
    protected EObject object;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected SetObjectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.SET_OBJECT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getFeatureName() {
        return featureName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFeatureName(String newFeatureName) {
        String oldFeatureName = featureName;
        featureName = newFeatureName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.SET_OBJECT__FEATURE_NAME, oldFeatureName, featureName));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject getObject() {
        if (object != null && object.eIsProxy()) {
            InternalEObject oldObject = (InternalEObject) object;
            object = eResolveProxy(oldObject);
            if (object != oldObject) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.SET_OBJECT__OBJECT, oldObject, object));
                }
            }
        }
        return object;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetObject() {
        return object;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setObject(EObject newObject) {
        EObject oldObject = object;
        object = newObject;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.SET_OBJECT__OBJECT, oldObject, object));
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
        case ToolPackage.SET_OBJECT__FEATURE_NAME:
            return getFeatureName();
        case ToolPackage.SET_OBJECT__OBJECT:
            if (resolve) {
                return getObject();
            }
            return basicGetObject();
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
        case ToolPackage.SET_OBJECT__FEATURE_NAME:
            setFeatureName((String) newValue);
            return;
        case ToolPackage.SET_OBJECT__OBJECT:
            setObject((EObject) newValue);
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
        case ToolPackage.SET_OBJECT__FEATURE_NAME:
            setFeatureName(SetObjectImpl.FEATURE_NAME_EDEFAULT);
            return;
        case ToolPackage.SET_OBJECT__OBJECT:
            setObject((EObject) null);
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
        case ToolPackage.SET_OBJECT__FEATURE_NAME:
            return SetObjectImpl.FEATURE_NAME_EDEFAULT == null ? featureName != null : !SetObjectImpl.FEATURE_NAME_EDEFAULT.equals(featureName);
        case ToolPackage.SET_OBJECT__OBJECT:
            return object != null;
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
        result.append(" (featureName: "); //$NON-NLS-1$
        result.append(featureName);
        result.append(')');
        return result.toString();
    }

} // SetObjectImpl
