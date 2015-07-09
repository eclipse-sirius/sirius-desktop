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
import org.eclipse.sirius.tests.sample.scxml.ScxmlDataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Data Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDataTypeImpl#getMixed
 * <em>Mixed</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDataTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDataTypeImpl#getExpr
 * <em>Expr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDataTypeImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDataTypeImpl#getSrc
 * <em>Src</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDataTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlDataTypeImpl extends MinimalEObjectImpl.Container implements ScxmlDataType {
    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap mixed;

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
    protected String expr = ScxmlDataTypeImpl.EXPR_EDEFAULT;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ScxmlDataTypeImpl.ID_EDEFAULT;

    /**
     * The default value of the '{@link #getSrc() <em>Src</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSrc()
     * @generated
     * @ordered
     */
    protected static final String SRC_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSrc() <em>Src</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSrc()
     * @generated
     * @ordered
     */
    protected String src = ScxmlDataTypeImpl.SRC_EDEFAULT;

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
    protected ScxmlDataTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_DATA_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, ScxmlPackage.SCXML_DATA_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap) getMixed().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_DATA_TYPE__ANY);
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_DATA_TYPE__EXPR, oldExpr, expr));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_DATA_TYPE__ID, oldId, id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getSrc() {
        return src;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSrc(String newSrc) {
        String oldSrc = src;
        src = newSrc;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_DATA_TYPE__SRC, oldSrc, src));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_DATA_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_DATA_TYPE__MIXED:
            return ((InternalEList<?>) getMixed()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_DATA_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_DATA_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DATA_TYPE__MIXED:
            if (coreType) {
                return getMixed();
            }
            return ((FeatureMap.Internal) getMixed()).getWrapper();
        case ScxmlPackage.SCXML_DATA_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_DATA_TYPE__EXPR:
            return getExpr();
        case ScxmlPackage.SCXML_DATA_TYPE__ID:
            return getId();
        case ScxmlPackage.SCXML_DATA_TYPE__SRC:
            return getSrc();
        case ScxmlPackage.SCXML_DATA_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DATA_TYPE__MIXED:
            ((FeatureMap.Internal) getMixed()).set(newValue);
            return;
        case ScxmlPackage.SCXML_DATA_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_DATA_TYPE__EXPR:
            setExpr((String) newValue);
            return;
        case ScxmlPackage.SCXML_DATA_TYPE__ID:
            setId((String) newValue);
            return;
        case ScxmlPackage.SCXML_DATA_TYPE__SRC:
            setSrc((String) newValue);
            return;
        case ScxmlPackage.SCXML_DATA_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DATA_TYPE__MIXED:
            getMixed().clear();
            return;
        case ScxmlPackage.SCXML_DATA_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_DATA_TYPE__EXPR:
            setExpr(ScxmlDataTypeImpl.EXPR_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_DATA_TYPE__ID:
            setId(ScxmlDataTypeImpl.ID_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_DATA_TYPE__SRC:
            setSrc(ScxmlDataTypeImpl.SRC_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_DATA_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DATA_TYPE__MIXED:
            return mixed != null && !mixed.isEmpty();
        case ScxmlPackage.SCXML_DATA_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_DATA_TYPE__EXPR:
            return ScxmlDataTypeImpl.EXPR_EDEFAULT == null ? expr != null : !ScxmlDataTypeImpl.EXPR_EDEFAULT.equals(expr);
        case ScxmlPackage.SCXML_DATA_TYPE__ID:
            return ScxmlDataTypeImpl.ID_EDEFAULT == null ? id != null : !ScxmlDataTypeImpl.ID_EDEFAULT.equals(id);
        case ScxmlPackage.SCXML_DATA_TYPE__SRC:
            return ScxmlDataTypeImpl.SRC_EDEFAULT == null ? src != null : !ScxmlDataTypeImpl.SRC_EDEFAULT.equals(src);
        case ScxmlPackage.SCXML_DATA_TYPE__ANY_ATTRIBUTE:
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
        result.append(" (mixed: "); //$NON-NLS-1$
        result.append(mixed);
        result.append(", expr: "); //$NON-NLS-1$
        result.append(expr);
        result.append(", id: "); //$NON-NLS-1$
        result.append(id);
        result.append(", src: "); //$NON-NLS-1$
        result.append(src);
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlDataTypeImpl
