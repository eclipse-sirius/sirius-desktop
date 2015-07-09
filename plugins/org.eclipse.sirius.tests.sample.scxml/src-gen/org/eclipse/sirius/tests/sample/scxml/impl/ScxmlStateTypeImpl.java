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
import java.util.List;

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
import org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlStateType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>State Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getScxmlStateMix
 * <em>Scxml State Mix</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getOnentry
 * <em>Onentry</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getOnexit
 * <em>Onexit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getTransition
 * <em>Transition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getInitial
 * <em>Initial</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getState
 * <em>State</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getParallel
 * <em>Parallel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getFinal
 * <em>Final</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getHistory
 * <em>History</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getDatamodel
 * <em>Datamodel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getInvoke
 * <em>Invoke</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getInitial1
 * <em>Initial1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlStateTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlStateTypeImpl extends MinimalEObjectImpl.Container implements ScxmlStateType {
    /**
     * The cached value of the '{@link #getScxmlStateMix()
     * <em>Scxml State Mix</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getScxmlStateMix()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlStateMix;

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
    protected String id = ScxmlStateTypeImpl.ID_EDEFAULT;

    /**
     * The default value of the '{@link #getInitial1() <em>Initial1</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInitial1()
     * @generated
     * @ordered
     */
    protected static final List<String> INITIAL1_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInitial1() <em>Initial1</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInitial1()
     * @generated
     * @ordered
     */
    protected List<String> initial1 = ScxmlStateTypeImpl.INITIAL1_EDEFAULT;

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
    protected ScxmlStateTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_STATE_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlStateMix() {
        if (scxmlStateMix == null) {
            scxmlStateMix = new BasicFeatureMap(this, ScxmlPackage.SCXML_STATE_TYPE__SCXML_STATE_MIX);
        }
        return scxmlStateMix;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlOnentryType> getOnentry() {
        return getScxmlStateMix().list(ScxmlPackage.Literals.SCXML_STATE_TYPE__ONENTRY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlOnexitType> getOnexit() {
        return getScxmlStateMix().list(ScxmlPackage.Literals.SCXML_STATE_TYPE__ONEXIT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlTransitionType> getTransition() {
        return getScxmlStateMix().list(ScxmlPackage.Literals.SCXML_STATE_TYPE__TRANSITION);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlInitialType> getInitial() {
        return getScxmlStateMix().list(ScxmlPackage.Literals.SCXML_STATE_TYPE__INITIAL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlStateType> getState() {
        return getScxmlStateMix().list(ScxmlPackage.Literals.SCXML_STATE_TYPE__STATE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlParallelType> getParallel() {
        return getScxmlStateMix().list(ScxmlPackage.Literals.SCXML_STATE_TYPE__PARALLEL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlFinalType> getFinal() {
        return getScxmlStateMix().list(ScxmlPackage.Literals.SCXML_STATE_TYPE__FINAL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlHistoryType> getHistory() {
        return getScxmlStateMix().list(ScxmlPackage.Literals.SCXML_STATE_TYPE__HISTORY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlDatamodelType> getDatamodel() {
        return getScxmlStateMix().list(ScxmlPackage.Literals.SCXML_STATE_TYPE__DATAMODEL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlInvokeType> getInvoke() {
        return getScxmlStateMix().list(ScxmlPackage.Literals.SCXML_STATE_TYPE__INVOKE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap) getScxmlStateMix().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_STATE_TYPE__ANY);
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_STATE_TYPE__ID, oldId, id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public List<String> getInitial1() {
        return initial1;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInitial1(List<String> newInitial1) {
        List<String> oldInitial1 = initial1;
        initial1 = newInitial1;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_STATE_TYPE__INITIAL1, oldInitial1, initial1));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_STATE_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_STATE_TYPE__SCXML_STATE_MIX:
            return ((InternalEList<?>) getScxmlStateMix()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__ONENTRY:
            return ((InternalEList<?>) getOnentry()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__ONEXIT:
            return ((InternalEList<?>) getOnexit()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__TRANSITION:
            return ((InternalEList<?>) getTransition()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__INITIAL:
            return ((InternalEList<?>) getInitial()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__STATE:
            return ((InternalEList<?>) getState()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__PARALLEL:
            return ((InternalEList<?>) getParallel()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__FINAL:
            return ((InternalEList<?>) getFinal()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__HISTORY:
            return ((InternalEList<?>) getHistory()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__DATAMODEL:
            return ((InternalEList<?>) getDatamodel()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__INVOKE:
            return ((InternalEList<?>) getInvoke()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_STATE_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_STATE_TYPE__SCXML_STATE_MIX:
            if (coreType) {
                return getScxmlStateMix();
            }
            return ((FeatureMap.Internal) getScxmlStateMix()).getWrapper();
        case ScxmlPackage.SCXML_STATE_TYPE__ONENTRY:
            return getOnentry();
        case ScxmlPackage.SCXML_STATE_TYPE__ONEXIT:
            return getOnexit();
        case ScxmlPackage.SCXML_STATE_TYPE__TRANSITION:
            return getTransition();
        case ScxmlPackage.SCXML_STATE_TYPE__INITIAL:
            return getInitial();
        case ScxmlPackage.SCXML_STATE_TYPE__STATE:
            return getState();
        case ScxmlPackage.SCXML_STATE_TYPE__PARALLEL:
            return getParallel();
        case ScxmlPackage.SCXML_STATE_TYPE__FINAL:
            return getFinal();
        case ScxmlPackage.SCXML_STATE_TYPE__HISTORY:
            return getHistory();
        case ScxmlPackage.SCXML_STATE_TYPE__DATAMODEL:
            return getDatamodel();
        case ScxmlPackage.SCXML_STATE_TYPE__INVOKE:
            return getInvoke();
        case ScxmlPackage.SCXML_STATE_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_STATE_TYPE__ID:
            return getId();
        case ScxmlPackage.SCXML_STATE_TYPE__INITIAL1:
            return getInitial1();
        case ScxmlPackage.SCXML_STATE_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_STATE_TYPE__SCXML_STATE_MIX:
            ((FeatureMap.Internal) getScxmlStateMix()).set(newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__ONENTRY:
            getOnentry().clear();
            getOnentry().addAll((Collection<? extends ScxmlOnentryType>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__ONEXIT:
            getOnexit().clear();
            getOnexit().addAll((Collection<? extends ScxmlOnexitType>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__TRANSITION:
            getTransition().clear();
            getTransition().addAll((Collection<? extends ScxmlTransitionType>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__INITIAL:
            getInitial().clear();
            getInitial().addAll((Collection<? extends ScxmlInitialType>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__STATE:
            getState().clear();
            getState().addAll((Collection<? extends ScxmlStateType>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__PARALLEL:
            getParallel().clear();
            getParallel().addAll((Collection<? extends ScxmlParallelType>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__FINAL:
            getFinal().clear();
            getFinal().addAll((Collection<? extends ScxmlFinalType>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__HISTORY:
            getHistory().clear();
            getHistory().addAll((Collection<? extends ScxmlHistoryType>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__DATAMODEL:
            getDatamodel().clear();
            getDatamodel().addAll((Collection<? extends ScxmlDatamodelType>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__INVOKE:
            getInvoke().clear();
            getInvoke().addAll((Collection<? extends ScxmlInvokeType>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__ID:
            setId((String) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__INITIAL1:
            setInitial1((List<String>) newValue);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_STATE_TYPE__SCXML_STATE_MIX:
            getScxmlStateMix().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__ONENTRY:
            getOnentry().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__ONEXIT:
            getOnexit().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__TRANSITION:
            getTransition().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__INITIAL:
            getInitial().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__STATE:
            getState().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__PARALLEL:
            getParallel().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__FINAL:
            getFinal().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__HISTORY:
            getHistory().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__DATAMODEL:
            getDatamodel().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__INVOKE:
            getInvoke().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__ID:
            setId(ScxmlStateTypeImpl.ID_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__INITIAL1:
            setInitial1(ScxmlStateTypeImpl.INITIAL1_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_STATE_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_STATE_TYPE__SCXML_STATE_MIX:
            return scxmlStateMix != null && !scxmlStateMix.isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__ONENTRY:
            return !getOnentry().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__ONEXIT:
            return !getOnexit().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__TRANSITION:
            return !getTransition().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__INITIAL:
            return !getInitial().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__STATE:
            return !getState().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__PARALLEL:
            return !getParallel().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__FINAL:
            return !getFinal().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__HISTORY:
            return !getHistory().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__DATAMODEL:
            return !getDatamodel().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__INVOKE:
            return !getInvoke().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_STATE_TYPE__ID:
            return ScxmlStateTypeImpl.ID_EDEFAULT == null ? id != null : !ScxmlStateTypeImpl.ID_EDEFAULT.equals(id);
        case ScxmlPackage.SCXML_STATE_TYPE__INITIAL1:
            return ScxmlStateTypeImpl.INITIAL1_EDEFAULT == null ? initial1 != null : !ScxmlStateTypeImpl.INITIAL1_EDEFAULT.equals(initial1);
        case ScxmlPackage.SCXML_STATE_TYPE__ANY_ATTRIBUTE:
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
        result.append(" (scxmlStateMix: "); //$NON-NLS-1$
        result.append(scxmlStateMix);
        result.append(", id: "); //$NON-NLS-1$
        result.append(id);
        result.append(", initial1: "); //$NON-NLS-1$
        result.append(initial1);
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlStateTypeImpl
