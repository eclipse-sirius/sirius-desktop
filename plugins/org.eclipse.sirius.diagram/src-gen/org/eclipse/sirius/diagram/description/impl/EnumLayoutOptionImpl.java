/**
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Enum Layout Option</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.EnumLayoutOptionImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnumLayoutOptionImpl extends LayoutOptionImpl implements EnumLayoutOption {
    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected EnumLayoutValue value;

    /**
     * The cached value of the '{@link #getChoices() <em>Choices</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getChoices()
     * @generated
     * @ordered
     */
    protected EList<EnumLayoutValue> choices;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EnumLayoutOptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.ENUM_LAYOUT_OPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EnumLayoutValue getValue() {
        if (value != null && value.eIsProxy()) {
            InternalEObject oldValue = (InternalEObject) value;
            value = (EnumLayoutValue) eResolveProxy(oldValue);
            if (value != oldValue) {
                InternalEObject newValue = (InternalEObject) value;
                NotificationChain msgs = oldValue.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE, null, null);
                if (newValue.eInternalContainer() == null) {
                    msgs = newValue.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE, oldValue, value));
                }
            }
        }
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EnumLayoutValue basicGetValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetValue(EnumLayoutValue newValue, NotificationChain msgs) {
        EnumLayoutValue oldValue = value;
        value = newValue;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE, oldValue, newValue);
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
    public void setValue(EnumLayoutValue newValue) {
        if (newValue != value) {
            NotificationChain msgs = null;
            if (value != null) {
                msgs = ((InternalEObject) value).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE, null, msgs);
            }
            if (newValue != null) {
                msgs = ((InternalEObject) newValue).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE, null, msgs);
            }
            msgs = basicSetValue(newValue, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE, newValue, newValue));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EnumLayoutValue> getChoices() {
        if (choices == null) {
            choices = new EObjectContainmentEList.Resolving<EnumLayoutValue>(EnumLayoutValue.class, this, DescriptionPackage.ENUM_LAYOUT_OPTION__CHOICES);
        }
        return choices;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE:
            return basicSetValue(null, msgs);
        case DescriptionPackage.ENUM_LAYOUT_OPTION__CHOICES:
            return ((InternalEList<?>) getChoices()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE:
            if (resolve) {
                return getValue();
            }
            return basicGetValue();
        case DescriptionPackage.ENUM_LAYOUT_OPTION__CHOICES:
            return getChoices();
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
        case DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE:
            setValue((EnumLayoutValue) newValue);
            return;
        case DescriptionPackage.ENUM_LAYOUT_OPTION__CHOICES:
            getChoices().clear();
            getChoices().addAll((Collection<? extends EnumLayoutValue>) newValue);
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
        case DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE:
            setValue((EnumLayoutValue) null);
            return;
        case DescriptionPackage.ENUM_LAYOUT_OPTION__CHOICES:
            getChoices().clear();
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
        case DescriptionPackage.ENUM_LAYOUT_OPTION__VALUE:
            return value != null;
        case DescriptionPackage.ENUM_LAYOUT_OPTION__CHOICES:
            return choices != null && !choices.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // EnumLayoutOptionImpl
