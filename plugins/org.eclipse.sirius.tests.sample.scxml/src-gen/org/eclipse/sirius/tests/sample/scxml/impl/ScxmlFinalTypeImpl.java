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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Final Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl#getScxmlFinalMix
 * <em>Scxml Final Mix</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl#getOnentry
 * <em>Onentry</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl#getOnexit
 * <em>Onexit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl#getDonedata
 * <em>Donedata</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFinalTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlFinalTypeImpl extends MinimalEObjectImpl.Container implements ScxmlFinalType {
    /**
     * The cached value of the '{@link #getScxmlFinalMix()
     * <em>Scxml Final Mix</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getScxmlFinalMix()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlFinalMix;

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
    protected String id = ScxmlFinalTypeImpl.ID_EDEFAULT;

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
    protected ScxmlFinalTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_FINAL_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlFinalMix() {
        if (scxmlFinalMix == null) {
            scxmlFinalMix = new BasicFeatureMap(this, ScxmlPackage.SCXML_FINAL_TYPE__SCXML_FINAL_MIX);
        }
        return scxmlFinalMix;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlOnentryType> getOnentry() {
        return getScxmlFinalMix().list(ScxmlPackage.Literals.SCXML_FINAL_TYPE__ONENTRY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlOnexitType> getOnexit() {
        return getScxmlFinalMix().list(ScxmlPackage.Literals.SCXML_FINAL_TYPE__ONEXIT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlDonedataType> getDonedata() {
        return getScxmlFinalMix().list(ScxmlPackage.Literals.SCXML_FINAL_TYPE__DONEDATA);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap) getScxmlFinalMix().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_FINAL_TYPE__ANY);
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_FINAL_TYPE__ID, oldId, id));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_FINAL_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_FINAL_TYPE__SCXML_FINAL_MIX:
            return ((InternalEList<?>) getScxmlFinalMix()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FINAL_TYPE__ONENTRY:
            return ((InternalEList<?>) getOnentry()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FINAL_TYPE__ONEXIT:
            return ((InternalEList<?>) getOnexit()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FINAL_TYPE__DONEDATA:
            return ((InternalEList<?>) getDonedata()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FINAL_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FINAL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_FINAL_TYPE__SCXML_FINAL_MIX:
            if (coreType) {
                return getScxmlFinalMix();
            }
            return ((FeatureMap.Internal) getScxmlFinalMix()).getWrapper();
        case ScxmlPackage.SCXML_FINAL_TYPE__ONENTRY:
            return getOnentry();
        case ScxmlPackage.SCXML_FINAL_TYPE__ONEXIT:
            return getOnexit();
        case ScxmlPackage.SCXML_FINAL_TYPE__DONEDATA:
            return getDonedata();
        case ScxmlPackage.SCXML_FINAL_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_FINAL_TYPE__ID:
            return getId();
        case ScxmlPackage.SCXML_FINAL_TYPE__ANY_ATTRIBUTE:
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
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ScxmlPackage.SCXML_FINAL_TYPE__SCXML_FINAL_MIX:
            ((FeatureMap.Internal) getScxmlFinalMix()).set(newValue);
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__ONENTRY:
            getOnentry().clear();
            getOnentry().addAll((Collection<? extends ScxmlOnentryType>) newValue);
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__ONEXIT:
            getOnexit().clear();
            getOnexit().addAll((Collection<? extends ScxmlOnexitType>) newValue);
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__DONEDATA:
            getDonedata().clear();
            getDonedata().addAll((Collection<? extends ScxmlDonedataType>) newValue);
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__ID:
            setId((String) newValue);
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_FINAL_TYPE__SCXML_FINAL_MIX:
            getScxmlFinalMix().clear();
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__ONENTRY:
            getOnentry().clear();
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__ONEXIT:
            getOnexit().clear();
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__DONEDATA:
            getDonedata().clear();
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__ID:
            setId(ScxmlFinalTypeImpl.ID_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_FINAL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_FINAL_TYPE__SCXML_FINAL_MIX:
            return scxmlFinalMix != null && !scxmlFinalMix.isEmpty();
        case ScxmlPackage.SCXML_FINAL_TYPE__ONENTRY:
            return !getOnentry().isEmpty();
        case ScxmlPackage.SCXML_FINAL_TYPE__ONEXIT:
            return !getOnexit().isEmpty();
        case ScxmlPackage.SCXML_FINAL_TYPE__DONEDATA:
            return !getDonedata().isEmpty();
        case ScxmlPackage.SCXML_FINAL_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_FINAL_TYPE__ID:
            return ScxmlFinalTypeImpl.ID_EDEFAULT == null ? id != null : !ScxmlFinalTypeImpl.ID_EDEFAULT.equals(id);
        case ScxmlPackage.SCXML_FINAL_TYPE__ANY_ATTRIBUTE:
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
        result.append(" (scxmlFinalMix: "); //$NON-NLS-1$
        result.append(scxmlFinalMix);
        result.append(", id: "); //$NON-NLS-1$
        result.append(id);
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlFinalTypeImpl
