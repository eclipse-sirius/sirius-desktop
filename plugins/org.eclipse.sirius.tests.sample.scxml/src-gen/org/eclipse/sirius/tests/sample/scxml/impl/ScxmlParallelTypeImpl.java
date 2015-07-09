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
import org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlStateType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Parallel Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getScxmlParallelMix
 * <em>Scxml Parallel Mix</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getOnentry
 * <em>Onentry</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getOnexit
 * <em>Onexit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getTransition
 * <em>Transition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getState
 * <em>State</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getParallel
 * <em>Parallel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getHistory
 * <em>History</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getDatamodel
 * <em>Datamodel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getInvoke
 * <em>Invoke</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlParallelTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlParallelTypeImpl extends MinimalEObjectImpl.Container implements ScxmlParallelType {
    /**
     * The cached value of the '{@link #getScxmlParallelMix()
     * <em>Scxml Parallel Mix</em>}' attribute list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getScxmlParallelMix()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlParallelMix;

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
    protected String id = ScxmlParallelTypeImpl.ID_EDEFAULT;

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
    protected ScxmlParallelTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_PARALLEL_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlParallelMix() {
        if (scxmlParallelMix == null) {
            scxmlParallelMix = new BasicFeatureMap(this, ScxmlPackage.SCXML_PARALLEL_TYPE__SCXML_PARALLEL_MIX);
        }
        return scxmlParallelMix;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlOnentryType> getOnentry() {
        return getScxmlParallelMix().list(ScxmlPackage.Literals.SCXML_PARALLEL_TYPE__ONENTRY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlOnexitType> getOnexit() {
        return getScxmlParallelMix().list(ScxmlPackage.Literals.SCXML_PARALLEL_TYPE__ONEXIT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlTransitionType> getTransition() {
        return getScxmlParallelMix().list(ScxmlPackage.Literals.SCXML_PARALLEL_TYPE__TRANSITION);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlStateType> getState() {
        return getScxmlParallelMix().list(ScxmlPackage.Literals.SCXML_PARALLEL_TYPE__STATE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlParallelType> getParallel() {
        return getScxmlParallelMix().list(ScxmlPackage.Literals.SCXML_PARALLEL_TYPE__PARALLEL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlHistoryType> getHistory() {
        return getScxmlParallelMix().list(ScxmlPackage.Literals.SCXML_PARALLEL_TYPE__HISTORY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlDatamodelType> getDatamodel() {
        return getScxmlParallelMix().list(ScxmlPackage.Literals.SCXML_PARALLEL_TYPE__DATAMODEL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlInvokeType> getInvoke() {
        return getScxmlParallelMix().list(ScxmlPackage.Literals.SCXML_PARALLEL_TYPE__INVOKE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap) getScxmlParallelMix().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_PARALLEL_TYPE__ANY);
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_PARALLEL_TYPE__ID, oldId, id));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_PARALLEL_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_PARALLEL_TYPE__SCXML_PARALLEL_MIX:
            return ((InternalEList<?>) getScxmlParallelMix()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ONENTRY:
            return ((InternalEList<?>) getOnentry()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ONEXIT:
            return ((InternalEList<?>) getOnexit()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__TRANSITION:
            return ((InternalEList<?>) getTransition()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__STATE:
            return ((InternalEList<?>) getState()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__PARALLEL:
            return ((InternalEList<?>) getParallel()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__HISTORY:
            return ((InternalEList<?>) getHistory()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__DATAMODEL:
            return ((InternalEList<?>) getDatamodel()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__INVOKE:
            return ((InternalEList<?>) getInvoke()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_PARALLEL_TYPE__SCXML_PARALLEL_MIX:
            if (coreType) {
                return getScxmlParallelMix();
            }
            return ((FeatureMap.Internal) getScxmlParallelMix()).getWrapper();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ONENTRY:
            return getOnentry();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ONEXIT:
            return getOnexit();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__TRANSITION:
            return getTransition();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__STATE:
            return getState();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__PARALLEL:
            return getParallel();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__HISTORY:
            return getHistory();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__DATAMODEL:
            return getDatamodel();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__INVOKE:
            return getInvoke();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ID:
            return getId();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_PARALLEL_TYPE__SCXML_PARALLEL_MIX:
            ((FeatureMap.Internal) getScxmlParallelMix()).set(newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ONENTRY:
            getOnentry().clear();
            getOnentry().addAll((Collection<? extends ScxmlOnentryType>) newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ONEXIT:
            getOnexit().clear();
            getOnexit().addAll((Collection<? extends ScxmlOnexitType>) newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__TRANSITION:
            getTransition().clear();
            getTransition().addAll((Collection<? extends ScxmlTransitionType>) newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__STATE:
            getState().clear();
            getState().addAll((Collection<? extends ScxmlStateType>) newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__PARALLEL:
            getParallel().clear();
            getParallel().addAll((Collection<? extends ScxmlParallelType>) newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__HISTORY:
            getHistory().clear();
            getHistory().addAll((Collection<? extends ScxmlHistoryType>) newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__DATAMODEL:
            getDatamodel().clear();
            getDatamodel().addAll((Collection<? extends ScxmlDatamodelType>) newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__INVOKE:
            getInvoke().clear();
            getInvoke().addAll((Collection<? extends ScxmlInvokeType>) newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ID:
            setId((String) newValue);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_PARALLEL_TYPE__SCXML_PARALLEL_MIX:
            getScxmlParallelMix().clear();
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ONENTRY:
            getOnentry().clear();
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ONEXIT:
            getOnexit().clear();
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__TRANSITION:
            getTransition().clear();
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__STATE:
            getState().clear();
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__PARALLEL:
            getParallel().clear();
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__HISTORY:
            getHistory().clear();
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__DATAMODEL:
            getDatamodel().clear();
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__INVOKE:
            getInvoke().clear();
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ID:
            setId(ScxmlParallelTypeImpl.ID_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_PARALLEL_TYPE__SCXML_PARALLEL_MIX:
            return scxmlParallelMix != null && !scxmlParallelMix.isEmpty();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ONENTRY:
            return !getOnentry().isEmpty();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ONEXIT:
            return !getOnexit().isEmpty();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__TRANSITION:
            return !getTransition().isEmpty();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__STATE:
            return !getState().isEmpty();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__PARALLEL:
            return !getParallel().isEmpty();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__HISTORY:
            return !getHistory().isEmpty();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__DATAMODEL:
            return !getDatamodel().isEmpty();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__INVOKE:
            return !getInvoke().isEmpty();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ID:
            return ScxmlParallelTypeImpl.ID_EDEFAULT == null ? id != null : !ScxmlParallelTypeImpl.ID_EDEFAULT.equals(id);
        case ScxmlPackage.SCXML_PARALLEL_TYPE__ANY_ATTRIBUTE:
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
        result.append(" (scxmlParallelMix: "); //$NON-NLS-1$
        result.append(scxmlParallelMix);
        result.append(", id: "); //$NON-NLS-1$
        result.append(id);
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlParallelTypeImpl
