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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.scxml.ScxmlContentType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParamType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Donedata Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDonedataTypeImpl#getContent
 * <em>Content</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDonedataTypeImpl#getParam
 * <em>Param</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlDonedataTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlDonedataTypeImpl extends MinimalEObjectImpl.Container implements ScxmlDonedataType {
    /**
     * The cached value of the '{@link #getContent() <em>Content</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getContent()
     * @generated
     * @ordered
     */
    protected ScxmlContentType content;

    /**
     * The cached value of the '{@link #getParam() <em>Param</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getParam()
     * @generated
     * @ordered
     */
    protected EList<ScxmlParamType> param;

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
    protected ScxmlDonedataTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_DONEDATA_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlContentType getContent() {
        return content;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetContent(ScxmlContentType newContent, NotificationChain msgs) {
        ScxmlContentType oldContent = content;
        content = newContent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_DONEDATA_TYPE__CONTENT, oldContent, newContent);
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
    public void setContent(ScxmlContentType newContent) {
        if (newContent != content) {
            NotificationChain msgs = null;
            if (content != null) {
                msgs = ((InternalEObject) content).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ScxmlPackage.SCXML_DONEDATA_TYPE__CONTENT, null, msgs);
            }
            if (newContent != null) {
                msgs = ((InternalEObject) newContent).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ScxmlPackage.SCXML_DONEDATA_TYPE__CONTENT, null, msgs);
            }
            msgs = basicSetContent(newContent, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_DONEDATA_TYPE__CONTENT, newContent, newContent));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlParamType> getParam() {
        if (param == null) {
            param = new EObjectContainmentEList<ScxmlParamType>(ScxmlParamType.class, this, ScxmlPackage.SCXML_DONEDATA_TYPE__PARAM);
        }
        return param;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAnyAttribute() {
        if (anyAttribute == null) {
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_DONEDATA_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_DONEDATA_TYPE__CONTENT:
            return basicSetContent(null, msgs);
        case ScxmlPackage.SCXML_DONEDATA_TYPE__PARAM:
            return ((InternalEList<?>) getParam()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_DONEDATA_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DONEDATA_TYPE__CONTENT:
            return getContent();
        case ScxmlPackage.SCXML_DONEDATA_TYPE__PARAM:
            return getParam();
        case ScxmlPackage.SCXML_DONEDATA_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DONEDATA_TYPE__CONTENT:
            setContent((ScxmlContentType) newValue);
            return;
        case ScxmlPackage.SCXML_DONEDATA_TYPE__PARAM:
            getParam().clear();
            getParam().addAll((Collection<? extends ScxmlParamType>) newValue);
            return;
        case ScxmlPackage.SCXML_DONEDATA_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DONEDATA_TYPE__CONTENT:
            setContent((ScxmlContentType) null);
            return;
        case ScxmlPackage.SCXML_DONEDATA_TYPE__PARAM:
            getParam().clear();
            return;
        case ScxmlPackage.SCXML_DONEDATA_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_DONEDATA_TYPE__CONTENT:
            return content != null;
        case ScxmlPackage.SCXML_DONEDATA_TYPE__PARAM:
            return param != null && !param.isEmpty();
        case ScxmlPackage.SCXML_DONEDATA_TYPE__ANY_ATTRIBUTE:
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
        result.append(" (anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlDonedataTypeImpl
