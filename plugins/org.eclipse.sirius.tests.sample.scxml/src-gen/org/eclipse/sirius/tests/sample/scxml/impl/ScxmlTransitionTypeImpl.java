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
import org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlIfType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlLogType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlSendType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType;
import org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Transition Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getScxmlCoreExecutablecontent
 * <em>Scxml Core Executablecontent</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getRaise
 * <em>Raise</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getIf
 * <em>If</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getForeach
 * <em>Foreach</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getSend
 * <em>Send</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getScript
 * <em>Script</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getAssign
 * <em>Assign</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getLog
 * <em>Log</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getCancel
 * <em>Cancel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getCond
 * <em>Cond</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getEvent
 * <em>Event</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getType
 * <em>Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlTransitionTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlTransitionTypeImpl extends MinimalEObjectImpl.Container implements ScxmlTransitionType {
    /**
     * The cached value of the '{@link #getScxmlCoreExecutablecontent()
     * <em>Scxml Core Executablecontent</em>}' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getScxmlCoreExecutablecontent()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlCoreExecutablecontent;

    /**
     * The default value of the '{@link #getCond() <em>Cond</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCond()
     * @generated
     * @ordered
     */
    protected static final String COND_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCond() <em>Cond</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCond()
     * @generated
     * @ordered
     */
    protected String cond = ScxmlTransitionTypeImpl.COND_EDEFAULT;

    /**
     * The default value of the '{@link #getEvent() <em>Event</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEvent()
     * @generated
     * @ordered
     */
    protected static final String EVENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEvent() <em>Event</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEvent()
     * @generated
     * @ordered
     */
    protected String event = ScxmlTransitionTypeImpl.EVENT_EDEFAULT;

    /**
     * The default value of the '{@link #getTarget() <em>Target</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected static final List<String> TARGET_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected List<String> target = ScxmlTransitionTypeImpl.TARGET_EDEFAULT;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final TransitionTypeDatatype TYPE_EDEFAULT = TransitionTypeDatatype.INTERNAL;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected TransitionTypeDatatype type = ScxmlTransitionTypeImpl.TYPE_EDEFAULT;

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
    protected ScxmlTransitionTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_TRANSITION_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlCoreExecutablecontent() {
        if (scxmlCoreExecutablecontent == null) {
            scxmlCoreExecutablecontent = new BasicFeatureMap(this, ScxmlPackage.SCXML_TRANSITION_TYPE__SCXML_CORE_EXECUTABLECONTENT);
        }
        return scxmlCoreExecutablecontent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap) getScxmlCoreExecutablecontent().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_TRANSITION_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlRaiseType> getRaise() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_TRANSITION_TYPE__RAISE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlIfType> getIf() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_TRANSITION_TYPE__IF);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlForeachType> getForeach() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_TRANSITION_TYPE__FOREACH);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlSendType> getSend() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_TRANSITION_TYPE__SEND);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlScriptType> getScript() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_TRANSITION_TYPE__SCRIPT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlAssignType> getAssign() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_TRANSITION_TYPE__ASSIGN);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlLogType> getLog() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_TRANSITION_TYPE__LOG);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlCancelType> getCancel() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_TRANSITION_TYPE__CANCEL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getCond() {
        return cond;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCond(String newCond) {
        String oldCond = cond;
        cond = newCond;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_TRANSITION_TYPE__COND, oldCond, cond));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getEvent() {
        return event;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEvent(String newEvent) {
        String oldEvent = event;
        event = newEvent;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_TRANSITION_TYPE__EVENT, oldEvent, event));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public List<String> getTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTarget(List<String> newTarget) {
        List<String> oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_TRANSITION_TYPE__TARGET, oldTarget, target));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TransitionTypeDatatype getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setType(TransitionTypeDatatype newType) {
        TransitionTypeDatatype oldType = type;
        type = newType == null ? ScxmlTransitionTypeImpl.TYPE_EDEFAULT : newType;
        boolean oldTypeESet = typeESet;
        typeESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_TRANSITION_TYPE__TYPE, oldType, type, !oldTypeESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetType() {
        TransitionTypeDatatype oldType = type;
        boolean oldTypeESet = typeESet;
        type = ScxmlTransitionTypeImpl.TYPE_EDEFAULT;
        typeESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_TRANSITION_TYPE__TYPE, oldType, ScxmlTransitionTypeImpl.TYPE_EDEFAULT, oldTypeESet));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_TRANSITION_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            return ((InternalEList<?>) getScxmlCoreExecutablecontent()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__RAISE:
            return ((InternalEList<?>) getRaise()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__IF:
            return ((InternalEList<?>) getIf()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__FOREACH:
            return ((InternalEList<?>) getForeach()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SEND:
            return ((InternalEList<?>) getSend()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SCRIPT:
            return ((InternalEList<?>) getScript()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ASSIGN:
            return ((InternalEList<?>) getAssign()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__LOG:
            return ((InternalEList<?>) getLog()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__CANCEL:
            return ((InternalEList<?>) getCancel()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            if (coreType) {
                return getScxmlCoreExecutablecontent();
            }
            return ((FeatureMap.Internal) getScxmlCoreExecutablecontent()).getWrapper();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__RAISE:
            return getRaise();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__IF:
            return getIf();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__FOREACH:
            return getForeach();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SEND:
            return getSend();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SCRIPT:
            return getScript();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ASSIGN:
            return getAssign();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__LOG:
            return getLog();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__CANCEL:
            return getCancel();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__COND:
            return getCond();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__EVENT:
            return getEvent();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__TARGET:
            return getTarget();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__TYPE:
            return getType();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            ((FeatureMap.Internal) getScxmlCoreExecutablecontent()).set(newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__RAISE:
            getRaise().clear();
            getRaise().addAll((Collection<? extends ScxmlRaiseType>) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__IF:
            getIf().clear();
            getIf().addAll((Collection<? extends ScxmlIfType>) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__FOREACH:
            getForeach().clear();
            getForeach().addAll((Collection<? extends ScxmlForeachType>) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SEND:
            getSend().clear();
            getSend().addAll((Collection<? extends ScxmlSendType>) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SCRIPT:
            getScript().clear();
            getScript().addAll((Collection<? extends ScxmlScriptType>) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ASSIGN:
            getAssign().clear();
            getAssign().addAll((Collection<? extends ScxmlAssignType>) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__LOG:
            getLog().clear();
            getLog().addAll((Collection<? extends ScxmlLogType>) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__CANCEL:
            getCancel().clear();
            getCancel().addAll((Collection<? extends ScxmlCancelType>) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__COND:
            setCond((String) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__EVENT:
            setEvent((String) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__TARGET:
            setTarget((List<String>) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__TYPE:
            setType((TransitionTypeDatatype) newValue);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            getScxmlCoreExecutablecontent().clear();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__RAISE:
            getRaise().clear();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__IF:
            getIf().clear();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__FOREACH:
            getForeach().clear();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SEND:
            getSend().clear();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SCRIPT:
            getScript().clear();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ASSIGN:
            getAssign().clear();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__LOG:
            getLog().clear();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__CANCEL:
            getCancel().clear();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__COND:
            setCond(ScxmlTransitionTypeImpl.COND_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__EVENT:
            setEvent(ScxmlTransitionTypeImpl.EVENT_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__TARGET:
            setTarget(ScxmlTransitionTypeImpl.TARGET_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__TYPE:
            unsetType();
            return;
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            return scxmlCoreExecutablecontent != null && !scxmlCoreExecutablecontent.isEmpty();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__RAISE:
            return !getRaise().isEmpty();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__IF:
            return !getIf().isEmpty();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__FOREACH:
            return !getForeach().isEmpty();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SEND:
            return !getSend().isEmpty();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__SCRIPT:
            return !getScript().isEmpty();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ASSIGN:
            return !getAssign().isEmpty();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__LOG:
            return !getLog().isEmpty();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__CANCEL:
            return !getCancel().isEmpty();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__COND:
            return ScxmlTransitionTypeImpl.COND_EDEFAULT == null ? cond != null : !ScxmlTransitionTypeImpl.COND_EDEFAULT.equals(cond);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__EVENT:
            return ScxmlTransitionTypeImpl.EVENT_EDEFAULT == null ? event != null : !ScxmlTransitionTypeImpl.EVENT_EDEFAULT.equals(event);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__TARGET:
            return ScxmlTransitionTypeImpl.TARGET_EDEFAULT == null ? target != null : !ScxmlTransitionTypeImpl.TARGET_EDEFAULT.equals(target);
        case ScxmlPackage.SCXML_TRANSITION_TYPE__TYPE:
            return isSetType();
        case ScxmlPackage.SCXML_TRANSITION_TYPE__ANY_ATTRIBUTE:
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
        result.append(" (scxmlCoreExecutablecontent: "); //$NON-NLS-1$
        result.append(scxmlCoreExecutablecontent);
        result.append(", cond: "); //$NON-NLS-1$
        result.append(cond);
        result.append(", event: "); //$NON-NLS-1$
        result.append(event);
        result.append(", target: "); //$NON-NLS-1$
        result.append(target);
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

} // ScxmlTransitionTypeImpl
