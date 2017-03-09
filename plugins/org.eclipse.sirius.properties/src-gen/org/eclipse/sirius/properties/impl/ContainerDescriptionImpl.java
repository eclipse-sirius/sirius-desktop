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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.AbstractContainerDescription;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.ControlDescription;
import org.eclipse.sirius.properties.LayoutDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Container Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl#getControls <em>Controls</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl#getLayout <em>Layout</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl#getFilterControlsFromExtendedContainerExpression
 * <em>Filter Controls From Extended Container Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerDescriptionImpl extends ControlDescriptionImpl implements ContainerDescription {
    /**
     * The cached value of the '{@link #getControls() <em>Controls</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getControls()
     * @generated
     * @ordered
     */
    protected EList<ControlDescription> controls;

    /**
     * The cached value of the '{@link #getLayout() <em>Layout</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getLayout()
     * @generated
     * @ordered
     */
    protected LayoutDescription layout;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected ContainerDescription extends_;

    /**
     * The default value of the '{@link #getFilterControlsFromExtendedContainerExpression() <em>Filter Controls From
     * Extended Container Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterControlsFromExtendedContainerExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterControlsFromExtendedContainerExpression() <em>Filter Controls From
     * Extended Container Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterControlsFromExtendedContainerExpression()
     * @generated
     * @ordered
     */
    protected String filterControlsFromExtendedContainerExpression = ContainerDescriptionImpl.FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ContainerDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.CONTAINER_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ControlDescription> getControls() {
        if (controls == null) {
            controls = new EObjectContainmentEList<ControlDescription>(ControlDescription.class, this, PropertiesPackage.CONTAINER_DESCRIPTION__CONTROLS);
        }
        return controls;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LayoutDescription getLayout() {
        return layout;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetLayout(LayoutDescription newLayout, NotificationChain msgs) {
        LayoutDescription oldLayout = layout;
        layout = newLayout;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT, oldLayout, newLayout);
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
    public void setLayout(LayoutDescription newLayout) {
        if (newLayout != layout) {
            NotificationChain msgs = null;
            if (layout != null) {
                msgs = ((InternalEObject) layout).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT, null, msgs);
            }
            if (newLayout != null) {
                msgs = ((InternalEObject) newLayout).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT, null, msgs);
            }
            msgs = basicSetLayout(newLayout, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT, newLayout, newLayout));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (ContainerDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.CONTAINER_DESCRIPTION__EXTENDS, oldExtends, extends_));
                }
            }
        }
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(ContainerDescription newExtends) {
        ContainerDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CONTAINER_DESCRIPTION__EXTENDS, oldExtends, extends_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterControlsFromExtendedContainerExpression() {
        return filterControlsFromExtendedContainerExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterControlsFromExtendedContainerExpression(String newFilterControlsFromExtendedContainerExpression) {
        String oldFilterControlsFromExtendedContainerExpression = filterControlsFromExtendedContainerExpression;
        filterControlsFromExtendedContainerExpression = newFilterControlsFromExtendedContainerExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION,
                    oldFilterControlsFromExtendedContainerExpression, filterControlsFromExtendedContainerExpression));
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
        case PropertiesPackage.CONTAINER_DESCRIPTION__CONTROLS:
            return ((InternalEList<?>) getControls()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT:
            return basicSetLayout(null, msgs);
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
        case PropertiesPackage.CONTAINER_DESCRIPTION__CONTROLS:
            return getControls();
        case PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT:
            return getLayout();
        case PropertiesPackage.CONTAINER_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
        case PropertiesPackage.CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION:
            return getFilterControlsFromExtendedContainerExpression();
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
        case PropertiesPackage.CONTAINER_DESCRIPTION__CONTROLS:
            getControls().clear();
            getControls().addAll((Collection<? extends ControlDescription>) newValue);
            return;
        case PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT:
            setLayout((LayoutDescription) newValue);
            return;
        case PropertiesPackage.CONTAINER_DESCRIPTION__EXTENDS:
            setExtends((ContainerDescription) newValue);
            return;
        case PropertiesPackage.CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION:
            setFilterControlsFromExtendedContainerExpression((String) newValue);
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
        case PropertiesPackage.CONTAINER_DESCRIPTION__CONTROLS:
            getControls().clear();
            return;
        case PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT:
            setLayout((LayoutDescription) null);
            return;
        case PropertiesPackage.CONTAINER_DESCRIPTION__EXTENDS:
            setExtends((ContainerDescription) null);
            return;
        case PropertiesPackage.CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION:
            setFilterControlsFromExtendedContainerExpression(ContainerDescriptionImpl.FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.CONTAINER_DESCRIPTION__CONTROLS:
            return controls != null && !controls.isEmpty();
        case PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT:
            return layout != null;
        case PropertiesPackage.CONTAINER_DESCRIPTION__EXTENDS:
            return extends_ != null;
        case PropertiesPackage.CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION:
            return ContainerDescriptionImpl.FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION_EDEFAULT == null ? filterControlsFromExtendedContainerExpression != null
                    : !ContainerDescriptionImpl.FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION_EDEFAULT.equals(filterControlsFromExtendedContainerExpression);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractContainerDescription.class) {
            switch (derivedFeatureID) {
            case PropertiesPackage.CONTAINER_DESCRIPTION__CONTROLS:
                return PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__CONTROLS;
            case PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT:
                return PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__LAYOUT;
            case PropertiesPackage.CONTAINER_DESCRIPTION__EXTENDS:
                return PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__EXTENDS;
            case PropertiesPackage.CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION:
                return PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractContainerDescription.class) {
            switch (baseFeatureID) {
            case PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__CONTROLS:
                return PropertiesPackage.CONTAINER_DESCRIPTION__CONTROLS;
            case PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__LAYOUT:
                return PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT;
            case PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__EXTENDS:
                return PropertiesPackage.CONTAINER_DESCRIPTION__EXTENDS;
            case PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION:
                return PropertiesPackage.CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (filterControlsFromExtendedContainerExpression: "); //$NON-NLS-1$
        result.append(filterControlsFromExtendedContainerExpression);
        result.append(')');
        return result.toString();
    }

} // ContainerDescriptionImpl
