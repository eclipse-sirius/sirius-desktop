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
import org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription;
import org.eclipse.sirius.properties.DynamicMappingIfDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Abstract Dynamic Mapping If
 * Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingIfDescriptionImpl#getPredicateExpression
 * <em>Predicate Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingIfDescriptionImpl#getWidget <em>Widget</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingIfDescriptionImpl#getExtends
 * <em>Extends</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractDynamicMappingIfDescriptionImpl extends IdentifiedElementImpl implements AbstractDynamicMappingIfDescription {
    /**
     * The default value of the '{@link #getPredicateExpression() <em>Predicate Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPredicateExpression()
     * @generated
     * @ordered
     */
    protected static final String PREDICATE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPredicateExpression() <em>Predicate Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPredicateExpression()
     * @generated
     * @ordered
     */
    protected String predicateExpression = AbstractDynamicMappingIfDescriptionImpl.PREDICATE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getWidget() <em>Widget</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getWidget()
     * @generated
     * @ordered
     */
    protected WidgetDescription widget;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected DynamicMappingIfDescription extends_;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractDynamicMappingIfDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getPredicateExpression() {
        return predicateExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setPredicateExpression(String newPredicateExpression) {
        String oldPredicateExpression = predicateExpression;
        predicateExpression = newPredicateExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION, oldPredicateExpression, predicateExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public WidgetDescription getWidget() {
        return widget;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetWidget(WidgetDescription newWidget, NotificationChain msgs) {
        WidgetDescription oldWidget = widget;
        widget = newWidget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, oldWidget, newWidget);
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
    public void setWidget(WidgetDescription newWidget) {
        if (newWidget != widget) {
            NotificationChain msgs = null;
            if (widget != null) {
                msgs = ((InternalEObject) widget).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, null, msgs);
            }
            if (newWidget != null) {
                msgs = ((InternalEObject) newWidget).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, null, msgs);
            }
            msgs = basicSetWidget(newWidget, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET, newWidget, newWidget));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DynamicMappingIfDescription getExtends() {
        if (extends_ != null && extends_.eIsProxy()) {
            InternalEObject oldExtends = (InternalEObject) extends_;
            extends_ = (DynamicMappingIfDescription) eResolveProxy(oldExtends);
            if (extends_ != oldExtends) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
    public DynamicMappingIfDescription basicGetExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtends(DynamicMappingIfDescription newExtends) {
        DynamicMappingIfDescription oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS, oldExtends, extends_));
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
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET:
            return basicSetWidget(null, msgs);
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
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION:
            return getPredicateExpression();
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET:
            return getWidget();
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS:
            if (resolve) {
                return getExtends();
            }
            return basicGetExtends();
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
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION:
            setPredicateExpression((String) newValue);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET:
            setWidget((WidgetDescription) newValue);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS:
            setExtends((DynamicMappingIfDescription) newValue);
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
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION:
            setPredicateExpression(AbstractDynamicMappingIfDescriptionImpl.PREDICATE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET:
            setWidget((WidgetDescription) null);
            return;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS:
            setExtends((DynamicMappingIfDescription) null);
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
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION:
            return AbstractDynamicMappingIfDescriptionImpl.PREDICATE_EXPRESSION_EDEFAULT == null ? predicateExpression != null
                    : !AbstractDynamicMappingIfDescriptionImpl.PREDICATE_EXPRESSION_EDEFAULT.equals(predicateExpression);
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET:
            return widget != null;
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS:
            return extends_ != null;
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
        result.append(" (predicateExpression: "); //$NON-NLS-1$
        result.append(predicateExpression);
        result.append(')');
        return result.toString();
    }

} // AbstractDynamicMappingIfDescriptionImpl
