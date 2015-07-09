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
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Script Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScriptTypeImpl#getMixed
 * <em>Mixed</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScriptTypeImpl#getScxmlExtraContent
 * <em>Scxml Extra Content</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScriptTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScriptTypeImpl#getSrc
 * <em>Src</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScriptTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlScriptTypeImpl extends MinimalEObjectImpl.Container implements ScxmlScriptType {
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
    protected String src = ScxmlScriptTypeImpl.SRC_EDEFAULT;

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
    protected ScxmlScriptTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_SCRIPT_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, ScxmlPackage.SCXML_SCRIPT_TYPE__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlExtraContent() {
        return (FeatureMap) getMixed().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_SCRIPT_TYPE__SCXML_EXTRA_CONTENT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap) getScxmlExtraContent().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_SCRIPT_TYPE__ANY);
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SCRIPT_TYPE__SRC, oldSrc, src));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_SCRIPT_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_SCRIPT_TYPE__MIXED:
            return ((InternalEList<?>) getMixed()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SCRIPT_TYPE__SCXML_EXTRA_CONTENT:
            return ((InternalEList<?>) getScxmlExtraContent()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SCRIPT_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SCRIPT_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SCRIPT_TYPE__MIXED:
            if (coreType) {
                return getMixed();
            }
            return ((FeatureMap.Internal) getMixed()).getWrapper();
        case ScxmlPackage.SCXML_SCRIPT_TYPE__SCXML_EXTRA_CONTENT:
            if (coreType) {
                return getScxmlExtraContent();
            }
            return ((FeatureMap.Internal) getScxmlExtraContent()).getWrapper();
        case ScxmlPackage.SCXML_SCRIPT_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_SCRIPT_TYPE__SRC:
            return getSrc();
        case ScxmlPackage.SCXML_SCRIPT_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SCRIPT_TYPE__MIXED:
            ((FeatureMap.Internal) getMixed()).set(newValue);
            return;
        case ScxmlPackage.SCXML_SCRIPT_TYPE__SCXML_EXTRA_CONTENT:
            ((FeatureMap.Internal) getScxmlExtraContent()).set(newValue);
            return;
        case ScxmlPackage.SCXML_SCRIPT_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_SCRIPT_TYPE__SRC:
            setSrc((String) newValue);
            return;
        case ScxmlPackage.SCXML_SCRIPT_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SCRIPT_TYPE__MIXED:
            getMixed().clear();
            return;
        case ScxmlPackage.SCXML_SCRIPT_TYPE__SCXML_EXTRA_CONTENT:
            getScxmlExtraContent().clear();
            return;
        case ScxmlPackage.SCXML_SCRIPT_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_SCRIPT_TYPE__SRC:
            setSrc(ScxmlScriptTypeImpl.SRC_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SCRIPT_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SCRIPT_TYPE__MIXED:
            return mixed != null && !mixed.isEmpty();
        case ScxmlPackage.SCXML_SCRIPT_TYPE__SCXML_EXTRA_CONTENT:
            return !getScxmlExtraContent().isEmpty();
        case ScxmlPackage.SCXML_SCRIPT_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_SCRIPT_TYPE__SRC:
            return ScxmlScriptTypeImpl.SRC_EDEFAULT == null ? src != null : !ScxmlScriptTypeImpl.SRC_EDEFAULT.equals(src);
        case ScxmlPackage.SCXML_SCRIPT_TYPE__ANY_ATTRIBUTE:
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
        result.append(", src: "); //$NON-NLS-1$
        result.append(src);
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlScriptTypeImpl
