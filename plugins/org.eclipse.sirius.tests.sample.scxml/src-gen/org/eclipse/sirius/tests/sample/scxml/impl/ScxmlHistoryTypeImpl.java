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
import org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype;
import org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>History Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl#getScxmlExtraContent
 * <em>Scxml Extra Content</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl#getTransition
 * <em>Transition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl#getScxmlExtraContent1
 * <em>Scxml Extra Content1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl#getAny1
 * <em>Any1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl#getType
 * <em>Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlHistoryTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlHistoryTypeImpl extends MinimalEObjectImpl.Container implements ScxmlHistoryType {
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
     * The cached value of the '{@link #getTransition() <em>Transition</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTransition()
     * @generated
     * @ordered
     */
    protected ScxmlTransitionType transition;

    /**
     * The cached value of the '{@link #getScxmlExtraContent1()
     * <em>Scxml Extra Content1</em>}' attribute list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getScxmlExtraContent1()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlExtraContent1;

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
    protected String id = ScxmlHistoryTypeImpl.ID_EDEFAULT;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final HistoryTypeDatatype TYPE_EDEFAULT = HistoryTypeDatatype.SHALLOW;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected HistoryTypeDatatype type = ScxmlHistoryTypeImpl.TYPE_EDEFAULT;

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
    protected ScxmlHistoryTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_HISTORY_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlExtraContent() {
        if (scxmlExtraContent == null) {
            scxmlExtraContent = new BasicFeatureMap(this, ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT);
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
        return (FeatureMap) getScxmlExtraContent().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_HISTORY_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlTransitionType getTransition() {
        return transition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetTransition(ScxmlTransitionType newTransition, NotificationChain msgs) {
        ScxmlTransitionType oldTransition = transition;
        transition = newTransition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_HISTORY_TYPE__TRANSITION, oldTransition, newTransition);
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
    public void setTransition(ScxmlTransitionType newTransition) {
        if (newTransition != transition) {
            NotificationChain msgs = null;
            if (transition != null) {
                msgs = ((InternalEObject) transition).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ScxmlPackage.SCXML_HISTORY_TYPE__TRANSITION, null, msgs);
            }
            if (newTransition != null) {
                msgs = ((InternalEObject) newTransition).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ScxmlPackage.SCXML_HISTORY_TYPE__TRANSITION, null, msgs);
            }
            msgs = basicSetTransition(newTransition, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_HISTORY_TYPE__TRANSITION, newTransition, newTransition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlExtraContent1() {
        if (scxmlExtraContent1 == null) {
            scxmlExtraContent1 = new BasicFeatureMap(this, ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT1);
        }
        return scxmlExtraContent1;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny1() {
        return (FeatureMap) getScxmlExtraContent1().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_HISTORY_TYPE__ANY1);
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_HISTORY_TYPE__ID, oldId, id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public HistoryTypeDatatype getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setType(HistoryTypeDatatype newType) {
        HistoryTypeDatatype oldType = type;
        type = newType == null ? ScxmlHistoryTypeImpl.TYPE_EDEFAULT : newType;
        boolean oldTypeESet = typeESet;
        typeESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_HISTORY_TYPE__TYPE, oldType, type, !oldTypeESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetType() {
        HistoryTypeDatatype oldType = type;
        boolean oldTypeESet = typeESet;
        type = ScxmlHistoryTypeImpl.TYPE_EDEFAULT;
        typeESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_HISTORY_TYPE__TYPE, oldType, ScxmlHistoryTypeImpl.TYPE_EDEFAULT, oldTypeESet));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_HISTORY_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT:
            return ((InternalEList<?>) getScxmlExtraContent()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_HISTORY_TYPE__TRANSITION:
            return basicSetTransition(null, msgs);
        case ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT1:
            return ((InternalEList<?>) getScxmlExtraContent1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY1:
            return ((InternalEList<?>) getAny1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT:
            if (coreType) {
                return getScxmlExtraContent();
            }
            return ((FeatureMap.Internal) getScxmlExtraContent()).getWrapper();
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_HISTORY_TYPE__TRANSITION:
            return getTransition();
        case ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT1:
            if (coreType) {
                return getScxmlExtraContent1();
            }
            return ((FeatureMap.Internal) getScxmlExtraContent1()).getWrapper();
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY1:
            if (coreType) {
                return getAny1();
            }
            return ((FeatureMap.Internal) getAny1()).getWrapper();
        case ScxmlPackage.SCXML_HISTORY_TYPE__ID:
            return getId();
        case ScxmlPackage.SCXML_HISTORY_TYPE__TYPE:
            return getType();
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT:
            ((FeatureMap.Internal) getScxmlExtraContent()).set(newValue);
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__TRANSITION:
            setTransition((ScxmlTransitionType) newValue);
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT1:
            ((FeatureMap.Internal) getScxmlExtraContent1()).set(newValue);
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY1:
            ((FeatureMap.Internal) getAny1()).set(newValue);
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__ID:
            setId((String) newValue);
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__TYPE:
            setType((HistoryTypeDatatype) newValue);
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT:
            getScxmlExtraContent().clear();
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__TRANSITION:
            setTransition((ScxmlTransitionType) null);
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT1:
            getScxmlExtraContent1().clear();
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY1:
            getAny1().clear();
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__ID:
            setId(ScxmlHistoryTypeImpl.ID_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__TYPE:
            unsetType();
            return;
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT:
            return scxmlExtraContent != null && !scxmlExtraContent.isEmpty();
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_HISTORY_TYPE__TRANSITION:
            return transition != null;
        case ScxmlPackage.SCXML_HISTORY_TYPE__SCXML_EXTRA_CONTENT1:
            return scxmlExtraContent1 != null && !scxmlExtraContent1.isEmpty();
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY1:
            return !getAny1().isEmpty();
        case ScxmlPackage.SCXML_HISTORY_TYPE__ID:
            return ScxmlHistoryTypeImpl.ID_EDEFAULT == null ? id != null : !ScxmlHistoryTypeImpl.ID_EDEFAULT.equals(id);
        case ScxmlPackage.SCXML_HISTORY_TYPE__TYPE:
            return isSetType();
        case ScxmlPackage.SCXML_HISTORY_TYPE__ANY_ATTRIBUTE:
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
        result.append(", scxmlExtraContent1: "); //$NON-NLS-1$
        result.append(scxmlExtraContent1);
        result.append(", id: "); //$NON-NLS-1$
        result.append(id);
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

} // ScxmlHistoryTypeImpl
