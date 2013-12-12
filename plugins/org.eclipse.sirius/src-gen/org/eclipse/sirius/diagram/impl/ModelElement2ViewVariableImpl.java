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
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Model Element2 View Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.ModelElement2ViewVariableImpl#getTypedKey
 * <em>Key</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.ModelElement2ViewVariableImpl#getTypedValue
 * <em>Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ModelElement2ViewVariableImpl extends EObjectImpl implements BasicEMap.Entry<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> {
    /**
     * The cached value of the '{@link #getTypedKey() <em>Key</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTypedKey()
     * @generated
     * @ordered
     */
    protected EObject key;

    /**
     * The cached value of the '{@link #getTypedValue() <em>Value</em>}' map.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTypedValue()
     * @generated
     * @ordered
     */
    protected EMap<EObject, EMap<EObject, StyleDescription>> value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ModelElement2ViewVariableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.MODEL_ELEMENT2_VIEW_VARIABLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject getTypedKey() {
        if (key != null && key.eIsProxy()) {
            InternalEObject oldKey = (InternalEObject) key;
            key = eResolveProxy(oldKey);
            if (key != oldKey) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__KEY, oldKey, key));
            }
        }
        return key;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetTypedKey() {
        return key;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTypedKey(EObject newKey) {
        EObject oldKey = key;
        key = newKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__KEY, oldKey, key));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EMap<EObject, EMap<EObject, StyleDescription>> getTypedValue() {
        if (value == null) {
            value = new EcoreEMap<EObject, EMap<EObject, StyleDescription>>(DiagramPackage.Literals.VIEW_VARIABLE2_CONTAINER_VARIABLE, ViewVariable2ContainerVariableImpl.class, this,
                    DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__VALUE);
        }
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__VALUE:
            return ((InternalEList<?>) getTypedValue()).basicRemove(otherEnd, msgs);
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
        case DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__KEY:
            if (resolve)
                return getTypedKey();
            return basicGetTypedKey();
        case DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__VALUE:
            if (coreType)
                return getTypedValue();
            else
                return getTypedValue().map();
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
        case DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__KEY:
            setTypedKey((EObject) newValue);
            return;
        case DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__VALUE:
            ((EStructuralFeature.Setting) getTypedValue()).set(newValue);
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
        case DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__KEY:
            setTypedKey((EObject) null);
            return;
        case DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__VALUE:
            getTypedValue().clear();
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
        case DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__KEY:
            return key != null;
        case DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE__VALUE:
            return value != null && !value.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected int hash = -1;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getHash() {
        if (hash == -1) {
            Object theKey = getKey();
            hash = (theKey == null ? 0 : theKey.hashCode());
        }
        return hash;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setHash(int hash) {
        this.hash = hash;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject getKey() {
        return getTypedKey();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setKey(EObject key) {
        setTypedKey(key);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EMap<EObject, EMap<EObject, StyleDescription>> getValue() {
        return getTypedValue();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EMap<EObject, EMap<EObject, StyleDescription>> setValue(EMap<EObject, EMap<EObject, StyleDescription>> value) {
        EMap<EObject, EMap<EObject, StyleDescription>> oldValue = getValue();
        getTypedValue().clear();
        getTypedValue().addAll(value);
        return oldValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    public EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> getEMap() {
        EObject container = eContainer();
        return container == null ? null : (EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>) container.eGet(eContainmentFeature());
    }

} // ModelElement2ViewVariableImpl
