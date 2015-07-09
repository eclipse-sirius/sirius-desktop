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
import org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype;
import org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Assign Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl#getMixed
 * <em>Mixed</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl#getAttr
 * <em>Attr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl#getExpr
 * <em>Expr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl#getLocation
 * <em>Location</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl#getType
 * <em>Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlAssignTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlAssignTypeImpl extends MinimalEObjectImpl.Container implements ScxmlAssignType {
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
     * The default value of the '{@link #getAttr() <em>Attr</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAttr()
     * @generated
     * @ordered
     */
    protected static final String ATTR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAttr() <em>Attr</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAttr()
     * @generated
     * @ordered
     */
    protected String attr = ScxmlAssignTypeImpl.ATTR_EDEFAULT;

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
    protected String expr = ScxmlAssignTypeImpl.EXPR_EDEFAULT;

    /**
     * The default value of the '{@link #getLocation() <em>Location</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLocation()
     * @generated
     * @ordered
     */
    protected static final String LOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLocation() <em>Location</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLocation()
     * @generated
     * @ordered
     */
    protected String location = ScxmlAssignTypeImpl.LOCATION_EDEFAULT;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final AssignTypeDatatype TYPE_EDEFAULT = AssignTypeDatatype.REPLACECHILDREN;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected AssignTypeDatatype type = ScxmlAssignTypeImpl.TYPE_EDEFAULT;

    /**
     * This is true if the Type attribute has been set. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    protected boolean typeESet;

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
    protected ScxmlAssignTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_ASSIGN_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, ScxmlPackage.SCXML_ASSIGN_TYPE__MIXED);
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
        return (FeatureMap) getMixed().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_ASSIGN_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getAttr() {
        return attr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAttr(String newAttr) {
        String oldAttr = attr;
        attr = newAttr;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_ASSIGN_TYPE__ATTR, oldAttr, attr));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_ASSIGN_TYPE__EXPR, oldExpr, expr));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLocation() {
        return location;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLocation(String newLocation) {
        String oldLocation = location;
        location = newLocation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_ASSIGN_TYPE__LOCATION, oldLocation, location));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AssignTypeDatatype getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setType(AssignTypeDatatype newType) {
        AssignTypeDatatype oldType = type;
        type = newType == null ? ScxmlAssignTypeImpl.TYPE_EDEFAULT : newType;
        boolean oldTypeESet = typeESet;
        typeESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_ASSIGN_TYPE__TYPE, oldType, type, !oldTypeESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetType() {
        AssignTypeDatatype oldType = type;
        boolean oldTypeESet = typeESet;
        type = ScxmlAssignTypeImpl.TYPE_EDEFAULT;
        typeESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_ASSIGN_TYPE__TYPE, oldType, ScxmlAssignTypeImpl.TYPE_EDEFAULT, oldTypeESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSetType() {
        return typeESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAnyAttribute() {
        if (anyAttribute == null) {
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_ASSIGN_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_ASSIGN_TYPE__MIXED:
            return ((InternalEList<?>) getMixed()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_ASSIGN_TYPE__MIXED:
            if (coreType) {
                return getMixed();
            }
            return ((FeatureMap.Internal) getMixed()).getWrapper();
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ATTR:
            return getAttr();
        case ScxmlPackage.SCXML_ASSIGN_TYPE__EXPR:
            return getExpr();
        case ScxmlPackage.SCXML_ASSIGN_TYPE__LOCATION:
            return getLocation();
        case ScxmlPackage.SCXML_ASSIGN_TYPE__TYPE:
            return getType();
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_ASSIGN_TYPE__MIXED:
            ((FeatureMap.Internal) getMixed()).set(newValue);
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ATTR:
            setAttr((String) newValue);
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__EXPR:
            setExpr((String) newValue);
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__LOCATION:
            setLocation((String) newValue);
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__TYPE:
            setType((AssignTypeDatatype) newValue);
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_ASSIGN_TYPE__MIXED:
            getMixed().clear();
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ATTR:
            setAttr(ScxmlAssignTypeImpl.ATTR_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__EXPR:
            setExpr(ScxmlAssignTypeImpl.EXPR_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__LOCATION:
            setLocation(ScxmlAssignTypeImpl.LOCATION_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__TYPE:
            unsetType();
            return;
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_ASSIGN_TYPE__MIXED:
            return mixed != null && !mixed.isEmpty();
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ATTR:
            return ScxmlAssignTypeImpl.ATTR_EDEFAULT == null ? attr != null : !ScxmlAssignTypeImpl.ATTR_EDEFAULT.equals(attr);
        case ScxmlPackage.SCXML_ASSIGN_TYPE__EXPR:
            return ScxmlAssignTypeImpl.EXPR_EDEFAULT == null ? expr != null : !ScxmlAssignTypeImpl.EXPR_EDEFAULT.equals(expr);
        case ScxmlPackage.SCXML_ASSIGN_TYPE__LOCATION:
            return ScxmlAssignTypeImpl.LOCATION_EDEFAULT == null ? location != null : !ScxmlAssignTypeImpl.LOCATION_EDEFAULT.equals(location);
        case ScxmlPackage.SCXML_ASSIGN_TYPE__TYPE:
            return isSetType();
        case ScxmlPackage.SCXML_ASSIGN_TYPE__ANY_ATTRIBUTE:
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
        result.append(", attr: "); //$NON-NLS-1$
        result.append(attr);
        result.append(", expr: "); //$NON-NLS-1$
        result.append(expr);
        result.append(", location: "); //$NON-NLS-1$
        result.append(location);
        result.append(", type: "); //$NON-NLS-1$
        if (typeESet) {
            result.append(type);
        } else {
            result.append("<unset>"); //$NON-NLS-1$
        }
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlAssignTypeImpl
