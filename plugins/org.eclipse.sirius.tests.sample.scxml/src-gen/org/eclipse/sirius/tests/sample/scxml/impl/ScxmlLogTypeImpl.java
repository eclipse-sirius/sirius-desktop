/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.sample.scxml.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.scxml.ScxmlLogType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Log Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlLogTypeImpl#getScxmlExtraContent
 * <em>Scxml Extra Content</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlLogTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlLogTypeImpl#getExpr
 * <em>Expr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlLogTypeImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlLogTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlLogTypeImpl extends MinimalEObjectImpl.Container implements ScxmlLogType {
    /**
     * The cached value of the '{@link #getScxmlExtraContent()
     * <em>Scxml Extra Content</em>}' attribute list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getScxmlExtraContent()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlExtraContent;

    /**
     * The default value of the '{@link #getExpr() <em>Expr</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getExpr()
     * @generated
     * @ordered
     */
    protected static final String EXPR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExpr() <em>Expr</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getExpr()
     * @generated
     * @ordered
     */
    protected String expr = ScxmlLogTypeImpl.EXPR_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = ScxmlLogTypeImpl.LABEL_EDEFAULT;

    /**
     * The cached value of the '{@link #getAnyAttribute()
     * <em>Any Attribute</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getAnyAttribute()
     * @generated
     * @ordered
     */
    protected FeatureMap anyAttribute;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ScxmlLogTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_LOG_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlExtraContent() {
        if (scxmlExtraContent == null) {
            scxmlExtraContent = new BasicFeatureMap(this, ScxmlPackage.SCXML_LOG_TYPE__SCXML_EXTRA_CONTENT);
        }
        return scxmlExtraContent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap) getScxmlExtraContent().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_LOG_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getExpr() {
        return expr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExpr(String newExpr) {
        String oldExpr = expr;
        expr = newExpr;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_LOG_TYPE__EXPR, oldExpr, expr));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_LOG_TYPE__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAnyAttribute() {
        if (anyAttribute == null) {
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_LOG_TYPE__ANY_ATTRIBUTE);
        }
        return anyAttribute;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ScxmlPackage.SCXML_LOG_TYPE__SCXML_EXTRA_CONTENT:
            return ((InternalEList<?>) getScxmlExtraContent()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_LOG_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_LOG_TYPE__ANY_ATTRIBUTE:
            return ((InternalEList<?>) getAnyAttribute()).basicRemove(otherEnd, msgs);
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
        case ScxmlPackage.SCXML_LOG_TYPE__SCXML_EXTRA_CONTENT:
            if (coreType) {
                return getScxmlExtraContent();
            }
            return ((FeatureMap.Internal) getScxmlExtraContent()).getWrapper();
        case ScxmlPackage.SCXML_LOG_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_LOG_TYPE__EXPR:
            return getExpr();
        case ScxmlPackage.SCXML_LOG_TYPE__LABEL:
            return getLabel();
        case ScxmlPackage.SCXML_LOG_TYPE__ANY_ATTRIBUTE:
            if (coreType) {
                return getAnyAttribute();
            }
            return ((FeatureMap.Internal) getAnyAttribute()).getWrapper();
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
        case ScxmlPackage.SCXML_LOG_TYPE__SCXML_EXTRA_CONTENT:
            ((FeatureMap.Internal) getScxmlExtraContent()).set(newValue);
            return;
        case ScxmlPackage.SCXML_LOG_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_LOG_TYPE__EXPR:
            setExpr((String) newValue);
            return;
        case ScxmlPackage.SCXML_LOG_TYPE__LABEL:
            setLabel((String) newValue);
            return;
        case ScxmlPackage.SCXML_LOG_TYPE__ANY_ATTRIBUTE:
            ((FeatureMap.Internal) getAnyAttribute()).set(newValue);
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
        case ScxmlPackage.SCXML_LOG_TYPE__SCXML_EXTRA_CONTENT:
            getScxmlExtraContent().clear();
            return;
        case ScxmlPackage.SCXML_LOG_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_LOG_TYPE__EXPR:
            setExpr(ScxmlLogTypeImpl.EXPR_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_LOG_TYPE__LABEL:
            setLabel(ScxmlLogTypeImpl.LABEL_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_LOG_TYPE__ANY_ATTRIBUTE:
            getAnyAttribute().clear();
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
        case ScxmlPackage.SCXML_LOG_TYPE__SCXML_EXTRA_CONTENT:
            return scxmlExtraContent != null && !scxmlExtraContent.isEmpty();
        case ScxmlPackage.SCXML_LOG_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_LOG_TYPE__EXPR:
            return ScxmlLogTypeImpl.EXPR_EDEFAULT == null ? expr != null : !ScxmlLogTypeImpl.EXPR_EDEFAULT.equals(expr);
        case ScxmlPackage.SCXML_LOG_TYPE__LABEL:
            return ScxmlLogTypeImpl.LABEL_EDEFAULT == null ? label != null : !ScxmlLogTypeImpl.LABEL_EDEFAULT.equals(label);
        case ScxmlPackage.SCXML_LOG_TYPE__ANY_ATTRIBUTE:
            return anyAttribute != null && !anyAttribute.isEmpty();
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
        result.append(" (scxmlExtraContent: "); //$NON-NLS-1$
        result.append(scxmlExtraContent);
        result.append(", expr: "); //$NON-NLS-1$
        result.append(expr);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlLogTypeImpl
