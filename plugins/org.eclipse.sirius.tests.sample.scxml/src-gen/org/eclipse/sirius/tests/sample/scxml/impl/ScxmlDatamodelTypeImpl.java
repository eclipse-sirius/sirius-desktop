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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Datamodel Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDatamodelTypeImpl#getData
 * <em>Data</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDatamodelTypeImpl#getScxmlExtraContent
 * <em>Scxml Extra Content</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDatamodelTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDatamodelTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlDatamodelTypeImpl extends MinimalEObjectImpl.Container implements ScxmlDatamodelType {
    /**
     * The cached value of the '{@link #getData() <em>Data</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getData()
     * @generated
     * @ordered
     */
    protected EList<ScxmlDataType> data;

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
    protected ScxmlDatamodelTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_DATAMODEL_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlDataType> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<ScxmlDataType>(ScxmlDataType.class, this, ScxmlPackage.SCXML_DATAMODEL_TYPE__DATA);
        }
        return data;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlExtraContent() {
        if (scxmlExtraContent == null) {
            scxmlExtraContent = new BasicFeatureMap(this, ScxmlPackage.SCXML_DATAMODEL_TYPE__SCXML_EXTRA_CONTENT);
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
        return (FeatureMap) getScxmlExtraContent().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_DATAMODEL_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAnyAttribute() {
        if (anyAttribute == null) {
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__DATA:
            return ((InternalEList<?>) getData()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__SCXML_EXTRA_CONTENT:
            return ((InternalEList<?>) getScxmlExtraContent()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__DATA:
            return getData();
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__SCXML_EXTRA_CONTENT:
            if (coreType) {
                return getScxmlExtraContent();
            }
            return ((FeatureMap.Internal) getScxmlExtraContent()).getWrapper();
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__DATA:
            getData().clear();
            getData().addAll((Collection<? extends ScxmlDataType>) newValue);
            return;
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__SCXML_EXTRA_CONTENT:
            ((FeatureMap.Internal) getScxmlExtraContent()).set(newValue);
            return;
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__DATA:
            getData().clear();
            return;
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__SCXML_EXTRA_CONTENT:
            getScxmlExtraContent().clear();
            return;
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__DATA:
            return data != null && !data.isEmpty();
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__SCXML_EXTRA_CONTENT:
            return scxmlExtraContent != null && !scxmlExtraContent.isEmpty();
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_DATAMODEL_TYPE__ANY_ATTRIBUTE:
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
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlDatamodelTypeImpl
