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
import org.eclipse.sirius.properties.DynamicMappingIf;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.WidgetDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Dynamic Mapping If</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.DynamicMappingIfImpl#getPredicateExpression
 * <em>Predicate Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.DynamicMappingIfImpl#getWidget
 * <em>Widget</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DynamicMappingIfImpl extends MinimalEObjectImpl.Container implements DynamicMappingIf {
    /**
     * The default value of the '{@link #getPredicateExpression()
     * <em>Predicate Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getPredicateExpression()
     * @generated
     * @ordered
     */
    protected static final String PREDICATE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPredicateExpression()
     * <em>Predicate Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getPredicateExpression()
     * @generated
     * @ordered
     */
    protected String predicateExpression = DynamicMappingIfImpl.PREDICATE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getWidget() <em>Widget</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWidget()
     * @generated
     * @ordered
     */
    protected WidgetDescription widget;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DynamicMappingIfImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.DYNAMIC_MAPPING_IF;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DYNAMIC_MAPPING_IF__PREDICATE_EXPRESSION, oldPredicateExpression, predicateExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET, oldWidget, newWidget);
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
                msgs = ((InternalEObject) widget).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET, null, msgs);
            }
            if (newWidget != null) {
                msgs = ((InternalEObject) newWidget).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET, null, msgs);
            }
            msgs = basicSetWidget(newWidget, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET, newWidget, newWidget));
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
        case PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET:
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
        case PropertiesPackage.DYNAMIC_MAPPING_IF__PREDICATE_EXPRESSION:
            return getPredicateExpression();
        case PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET:
            return getWidget();
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
        case PropertiesPackage.DYNAMIC_MAPPING_IF__PREDICATE_EXPRESSION:
            setPredicateExpression((String) newValue);
            return;
        case PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET:
            setWidget((WidgetDescription) newValue);
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
        case PropertiesPackage.DYNAMIC_MAPPING_IF__PREDICATE_EXPRESSION:
            setPredicateExpression(DynamicMappingIfImpl.PREDICATE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET:
            setWidget((WidgetDescription) null);
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
        case PropertiesPackage.DYNAMIC_MAPPING_IF__PREDICATE_EXPRESSION:
            return DynamicMappingIfImpl.PREDICATE_EXPRESSION_EDEFAULT == null ? predicateExpression != null : !DynamicMappingIfImpl.PREDICATE_EXPRESSION_EDEFAULT.equals(predicateExpression);
        case PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET:
            return widget != null;
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

} // DynamicMappingIfImpl
