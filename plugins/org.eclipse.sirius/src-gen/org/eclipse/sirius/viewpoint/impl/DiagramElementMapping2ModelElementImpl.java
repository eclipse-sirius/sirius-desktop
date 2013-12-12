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
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Diagram Element Mapping2 Model Element</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DiagramElementMapping2ModelElementImpl#getTypedKey
 * <em>Key</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DiagramElementMapping2ModelElementImpl#getTypedValue
 * <em>Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DiagramElementMapping2ModelElementImpl extends EObjectImpl implements BasicEMap.Entry<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>> {
    /**
     * The cached value of the '{@link #getTypedKey() <em>Key</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTypedKey()
     * @generated
     * @ordered
     */
    protected DiagramElementMapping key;

    /**
     * The cached value of the '{@link #getTypedValue() <em>Value</em>}' map.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTypedValue()
     * @generated
     * @ordered
     */
    protected EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DiagramElementMapping2ModelElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DiagramElementMapping getTypedKey() {
        if (key != null && key.eIsProxy()) {
            InternalEObject oldKey = (InternalEObject) key;
            key = (DiagramElementMapping) eResolveProxy(oldKey);
            if (key != oldKey) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY, oldKey, key));
            }
        }
        return key;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DiagramElementMapping basicGetTypedKey() {
        return key;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTypedKey(DiagramElementMapping newKey) {
        DiagramElementMapping oldKey = key;
        key = newKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY, oldKey, key));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> getTypedValue() {
        if (value == null) {
            value = new EcoreEMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>(ViewpointPackage.Literals.MODEL_ELEMENT2_VIEW_VARIABLE, ModelElement2ViewVariableImpl.class, this,
                    ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE);
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
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE:
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
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY:
            if (resolve)
                return getTypedKey();
            return basicGetTypedKey();
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE:
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
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY:
            setTypedKey((DiagramElementMapping) newValue);
            return;
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE:
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
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY:
            setTypedKey((DiagramElementMapping) null);
            return;
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE:
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
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY:
            return key != null;
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE:
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
    public DiagramElementMapping getKey() {
        return getTypedKey();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setKey(DiagramElementMapping key) {
        setTypedKey(key);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> getValue() {
        return getTypedValue();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> setValue(EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> value) {
        EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> oldValue = getValue();
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
    public EMap<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>> getEMap() {
        EObject container = eContainer();
        return container == null ? null : (EMap<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>>) container.eGet(eContainmentFeature());
    }

} // DiagramElementMapping2ModelElementImpl
