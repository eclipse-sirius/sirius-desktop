/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.properties.DynamicMappingFor;
import org.eclipse.sirius.properties.DynamicMappingSwitch;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Dynamic Mapping For</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.DynamicMappingForImpl#getIterator
 * <em>Iterator</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.DynamicMappingForImpl#getDomainClassExpression
 * <em>Domain Class Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.DynamicMappingForImpl#getSwitch
 * <em>Switch</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DynamicMappingForImpl extends MinimalEObjectImpl.Container implements DynamicMappingFor {
    /**
     * The default value of the '{@link #getIterator() <em>Iterator</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIterator()
     * @generated
     * @ordered
     */
    protected static final String ITERATOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIterator() <em>Iterator</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIterator()
     * @generated
     * @ordered
     */
    protected String iterator = DynamicMappingForImpl.ITERATOR_EDEFAULT;

    /**
     * The default value of the '{@link #getDomainClassExpression()
     * <em>Domain Class Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDomainClassExpression()
     * @generated
     * @ordered
     */
    protected static final String DOMAIN_CLASS_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDomainClassExpression()
     * <em>Domain Class Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDomainClassExpression()
     * @generated
     * @ordered
     */
    protected String domainClassExpression = DynamicMappingForImpl.DOMAIN_CLASS_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getSwitch() <em>Switch</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSwitch()
     * @generated
     * @ordered
     */
    protected DynamicMappingSwitch switch_;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DynamicMappingForImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.DYNAMIC_MAPPING_FOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getIterator() {
        return iterator;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setIterator(String newIterator) {
        String oldIterator = iterator;
        iterator = newIterator;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DYNAMIC_MAPPING_FOR__ITERATOR, oldIterator, iterator));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getDomainClassExpression() {
        return domainClassExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDomainClassExpression(String newDomainClassExpression) {
        String oldDomainClassExpression = domainClassExpression;
        domainClassExpression = newDomainClassExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DYNAMIC_MAPPING_FOR__DOMAIN_CLASS_EXPRESSION, oldDomainClassExpression, domainClassExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DynamicMappingSwitch getSwitch() {
        return switch_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSwitch(DynamicMappingSwitch newSwitch, NotificationChain msgs) {
        DynamicMappingSwitch oldSwitch = switch_;
        switch_ = newSwitch;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.DYNAMIC_MAPPING_FOR__SWITCH, oldSwitch, newSwitch);
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
    public void setSwitch(DynamicMappingSwitch newSwitch) {
        if (newSwitch != switch_) {
            NotificationChain msgs = null;
            if (switch_ != null) {
                msgs = ((InternalEObject) switch_).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.DYNAMIC_MAPPING_FOR__SWITCH, null, msgs);
            }
            if (newSwitch != null) {
                msgs = ((InternalEObject) newSwitch).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.DYNAMIC_MAPPING_FOR__SWITCH, null, msgs);
            }
            msgs = basicSetSwitch(newSwitch, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DYNAMIC_MAPPING_FOR__SWITCH, newSwitch, newSwitch));
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
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__SWITCH:
            return basicSetSwitch(null, msgs);
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
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__ITERATOR:
            return getIterator();
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__DOMAIN_CLASS_EXPRESSION:
            return getDomainClassExpression();
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__SWITCH:
            return getSwitch();
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
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__ITERATOR:
            setIterator((String) newValue);
            return;
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__DOMAIN_CLASS_EXPRESSION:
            setDomainClassExpression((String) newValue);
            return;
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__SWITCH:
            setSwitch((DynamicMappingSwitch) newValue);
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
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__ITERATOR:
            setIterator(DynamicMappingForImpl.ITERATOR_EDEFAULT);
            return;
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__DOMAIN_CLASS_EXPRESSION:
            setDomainClassExpression(DynamicMappingForImpl.DOMAIN_CLASS_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__SWITCH:
            setSwitch((DynamicMappingSwitch) null);
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
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__ITERATOR:
            return DynamicMappingForImpl.ITERATOR_EDEFAULT == null ? iterator != null : !DynamicMappingForImpl.ITERATOR_EDEFAULT.equals(iterator);
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__DOMAIN_CLASS_EXPRESSION:
            return DynamicMappingForImpl.DOMAIN_CLASS_EXPRESSION_EDEFAULT == null ? domainClassExpression != null : !DynamicMappingForImpl.DOMAIN_CLASS_EXPRESSION_EDEFAULT
                    .equals(domainClassExpression);
        case PropertiesPackage.DYNAMIC_MAPPING_FOR__SWITCH:
            return switch_ != null;
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
        result.append(" (iterator: ");
        result.append(iterator);
        result.append(", domainClassExpression: ");
        result.append(domainClassExpression);
        result.append(')');
        return result.toString();
    }

} // DynamicMappingForImpl
