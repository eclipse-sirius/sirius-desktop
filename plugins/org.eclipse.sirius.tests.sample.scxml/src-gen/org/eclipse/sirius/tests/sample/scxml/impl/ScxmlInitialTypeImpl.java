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
import org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Initial Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInitialTypeImpl#getScxmlExtraContent
 * <em>Scxml Extra Content</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInitialTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInitialTypeImpl#getTransition
 * <em>Transition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInitialTypeImpl#getScxmlExtraContent1
 * <em>Scxml Extra Content1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInitialTypeImpl#getAny1
 * <em>Any1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInitialTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlInitialTypeImpl extends MinimalEObjectImpl.Container implements ScxmlInitialType {
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
    protected ScxmlInitialTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_INITIAL_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlExtraContent() {
        if (scxmlExtraContent == null) {
            scxmlExtraContent = new BasicFeatureMap(this, ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT);
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
        return (FeatureMap) getScxmlExtraContent().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_INITIAL_TYPE__ANY);
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_INITIAL_TYPE__TRANSITION, oldTransition, newTransition);
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
                msgs = ((InternalEObject) transition).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ScxmlPackage.SCXML_INITIAL_TYPE__TRANSITION, null, msgs);
            }
            if (newTransition != null) {
                msgs = ((InternalEObject) newTransition).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ScxmlPackage.SCXML_INITIAL_TYPE__TRANSITION, null, msgs);
            }
            msgs = basicSetTransition(newTransition, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_INITIAL_TYPE__TRANSITION, newTransition, newTransition));
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
            scxmlExtraContent1 = new BasicFeatureMap(this, ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT1);
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
        return (FeatureMap) getScxmlExtraContent1().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_INITIAL_TYPE__ANY1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAnyAttribute() {
        if (anyAttribute == null) {
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_INITIAL_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT:
            return ((InternalEList<?>) getScxmlExtraContent()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_INITIAL_TYPE__TRANSITION:
            return basicSetTransition(null, msgs);
        case ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT1:
            return ((InternalEList<?>) getScxmlExtraContent1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY1:
            return ((InternalEList<?>) getAny1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT:
            if (coreType) {
                return getScxmlExtraContent();
            }
            return ((FeatureMap.Internal) getScxmlExtraContent()).getWrapper();
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_INITIAL_TYPE__TRANSITION:
            return getTransition();
        case ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT1:
            if (coreType) {
                return getScxmlExtraContent1();
            }
            return ((FeatureMap.Internal) getScxmlExtraContent1()).getWrapper();
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY1:
            if (coreType) {
                return getAny1();
            }
            return ((FeatureMap.Internal) getAny1()).getWrapper();
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT:
            ((FeatureMap.Internal) getScxmlExtraContent()).set(newValue);
            return;
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_INITIAL_TYPE__TRANSITION:
            setTransition((ScxmlTransitionType) newValue);
            return;
        case ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT1:
            ((FeatureMap.Internal) getScxmlExtraContent1()).set(newValue);
            return;
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY1:
            ((FeatureMap.Internal) getAny1()).set(newValue);
            return;
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT:
            getScxmlExtraContent().clear();
            return;
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_INITIAL_TYPE__TRANSITION:
            setTransition((ScxmlTransitionType) null);
            return;
        case ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT1:
            getScxmlExtraContent1().clear();
            return;
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY1:
            getAny1().clear();
            return;
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT:
            return scxmlExtraContent != null && !scxmlExtraContent.isEmpty();
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_INITIAL_TYPE__TRANSITION:
            return transition != null;
        case ScxmlPackage.SCXML_INITIAL_TYPE__SCXML_EXTRA_CONTENT1:
            return scxmlExtraContent1 != null && !scxmlExtraContent1.isEmpty();
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY1:
            return !getAny1().isEmpty();
        case ScxmlPackage.SCXML_INITIAL_TYPE__ANY_ATTRIBUTE:
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
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlInitialTypeImpl
