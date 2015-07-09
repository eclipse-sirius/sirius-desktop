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
import org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlElseType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlElseifType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlIfType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlLogType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlSendType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>If Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getScxmlCoreExecutablecontent
 * <em>Scxml Core Executablecontent</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getRaise
 * <em>Raise</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getIf
 * <em>If</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getForeach
 * <em>Foreach</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getSend
 * <em>Send</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getScript
 * <em>Script</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getAssign
 * <em>Assign</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getLog
 * <em>Log</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getCancel
 * <em>Cancel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getElseif
 * <em>Elseif</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getScxmlCoreExecutablecontent1
 * <em>Scxml Core Executablecontent1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getAny1
 * <em>Any1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getRaise1
 * <em>Raise1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getIf1
 * <em>If1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getForeach1
 * <em>Foreach1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getSend1
 * <em>Send1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getScript1
 * <em>Script1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getAssign1
 * <em>Assign1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getLog1
 * <em>Log1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getCancel1
 * <em>Cancel1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getElse
 * <em>Else</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getScxmlCoreExecutablecontent2
 * <em>Scxml Core Executablecontent2</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getAny2
 * <em>Any2</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getRaise2
 * <em>Raise2</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getIf2
 * <em>If2</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getForeach2
 * <em>Foreach2</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getSend2
 * <em>Send2</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getScript2
 * <em>Script2</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getAssign2
 * <em>Assign2</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getLog2
 * <em>Log2</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getCancel2
 * <em>Cancel2</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getCond
 * <em>Cond</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlIfTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlIfTypeImpl extends MinimalEObjectImpl.Container implements ScxmlIfType {
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
     * The cached value of the '{@link #getElseif() <em>Elseif</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getElseif()
     * @generated
     * @ordered
     */
    protected ScxmlElseifType elseif;

    /**
     * The cached value of the '{@link #getScxmlCoreExecutablecontent1()
     * <em>Scxml Core Executablecontent1</em>}' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getScxmlCoreExecutablecontent1()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlCoreExecutablecontent1;

    /**
     * The cached value of the '{@link #getElse() <em>Else</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getElse()
     * @generated
     * @ordered
     */
    protected ScxmlElseType else_;

    /**
     * The cached value of the '{@link #getScxmlCoreExecutablecontent2()
     * <em>Scxml Core Executablecontent2</em>}' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getScxmlCoreExecutablecontent2()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlCoreExecutablecontent2;

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
    protected String cond = ScxmlIfTypeImpl.COND_EDEFAULT;

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
    protected ScxmlIfTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_IF_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlCoreExecutablecontent() {
        if (scxmlCoreExecutablecontent == null) {
            scxmlCoreExecutablecontent = new BasicFeatureMap(this, ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT);
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
        return (FeatureMap) getScxmlCoreExecutablecontent().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_IF_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlRaiseType> getRaise() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_IF_TYPE__RAISE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlIfType> getIf() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_IF_TYPE__IF);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlForeachType> getForeach() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_IF_TYPE__FOREACH);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlSendType> getSend() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_IF_TYPE__SEND);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlScriptType> getScript() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_IF_TYPE__SCRIPT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlAssignType> getAssign() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_IF_TYPE__ASSIGN);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlLogType> getLog() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_IF_TYPE__LOG);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlCancelType> getCancel() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_IF_TYPE__CANCEL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlElseifType getElseif() {
        return elseif;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElseif(ScxmlElseifType newElseif, NotificationChain msgs) {
        ScxmlElseifType oldElseif = elseif;
        elseif = newElseif;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_IF_TYPE__ELSEIF, oldElseif, newElseif);
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
    public void setElseif(ScxmlElseifType newElseif) {
        if (newElseif != elseif) {
            NotificationChain msgs = null;
            if (elseif != null) {
                msgs = ((InternalEObject) elseif).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ScxmlPackage.SCXML_IF_TYPE__ELSEIF, null, msgs);
            }
            if (newElseif != null) {
                msgs = ((InternalEObject) newElseif).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ScxmlPackage.SCXML_IF_TYPE__ELSEIF, null, msgs);
            }
            msgs = basicSetElseif(newElseif, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_IF_TYPE__ELSEIF, newElseif, newElseif));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlCoreExecutablecontent1() {
        if (scxmlCoreExecutablecontent1 == null) {
            scxmlCoreExecutablecontent1 = new BasicFeatureMap(this, ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT1);
        }
        return scxmlCoreExecutablecontent1;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny1() {
        return (FeatureMap) getScxmlCoreExecutablecontent1().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_IF_TYPE__ANY1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlRaiseType> getRaise1() {
        return getScxmlCoreExecutablecontent1().list(ScxmlPackage.Literals.SCXML_IF_TYPE__RAISE1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlIfType> getIf1() {
        return getScxmlCoreExecutablecontent1().list(ScxmlPackage.Literals.SCXML_IF_TYPE__IF1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlForeachType> getForeach1() {
        return getScxmlCoreExecutablecontent1().list(ScxmlPackage.Literals.SCXML_IF_TYPE__FOREACH1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlSendType> getSend1() {
        return getScxmlCoreExecutablecontent1().list(ScxmlPackage.Literals.SCXML_IF_TYPE__SEND1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlScriptType> getScript1() {
        return getScxmlCoreExecutablecontent1().list(ScxmlPackage.Literals.SCXML_IF_TYPE__SCRIPT1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlAssignType> getAssign1() {
        return getScxmlCoreExecutablecontent1().list(ScxmlPackage.Literals.SCXML_IF_TYPE__ASSIGN1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlLogType> getLog1() {
        return getScxmlCoreExecutablecontent1().list(ScxmlPackage.Literals.SCXML_IF_TYPE__LOG1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlCancelType> getCancel1() {
        return getScxmlCoreExecutablecontent1().list(ScxmlPackage.Literals.SCXML_IF_TYPE__CANCEL1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlElseType getElse() {
        return else_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElse(ScxmlElseType newElse, NotificationChain msgs) {
        ScxmlElseType oldElse = else_;
        else_ = newElse;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_IF_TYPE__ELSE, oldElse, newElse);
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
    public void setElse(ScxmlElseType newElse) {
        if (newElse != else_) {
            NotificationChain msgs = null;
            if (else_ != null) {
                msgs = ((InternalEObject) else_).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ScxmlPackage.SCXML_IF_TYPE__ELSE, null, msgs);
            }
            if (newElse != null) {
                msgs = ((InternalEObject) newElse).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ScxmlPackage.SCXML_IF_TYPE__ELSE, null, msgs);
            }
            msgs = basicSetElse(newElse, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_IF_TYPE__ELSE, newElse, newElse));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlCoreExecutablecontent2() {
        if (scxmlCoreExecutablecontent2 == null) {
            scxmlCoreExecutablecontent2 = new BasicFeatureMap(this, ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT2);
        }
        return scxmlCoreExecutablecontent2;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny2() {
        return (FeatureMap) getScxmlCoreExecutablecontent2().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_IF_TYPE__ANY2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlRaiseType> getRaise2() {
        return getScxmlCoreExecutablecontent2().list(ScxmlPackage.Literals.SCXML_IF_TYPE__RAISE2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlIfType> getIf2() {
        return getScxmlCoreExecutablecontent2().list(ScxmlPackage.Literals.SCXML_IF_TYPE__IF2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlForeachType> getForeach2() {
        return getScxmlCoreExecutablecontent2().list(ScxmlPackage.Literals.SCXML_IF_TYPE__FOREACH2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlSendType> getSend2() {
        return getScxmlCoreExecutablecontent2().list(ScxmlPackage.Literals.SCXML_IF_TYPE__SEND2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlScriptType> getScript2() {
        return getScxmlCoreExecutablecontent2().list(ScxmlPackage.Literals.SCXML_IF_TYPE__SCRIPT2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlAssignType> getAssign2() {
        return getScxmlCoreExecutablecontent2().list(ScxmlPackage.Literals.SCXML_IF_TYPE__ASSIGN2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlLogType> getLog2() {
        return getScxmlCoreExecutablecontent2().list(ScxmlPackage.Literals.SCXML_IF_TYPE__LOG2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlCancelType> getCancel2() {
        return getScxmlCoreExecutablecontent2().list(ScxmlPackage.Literals.SCXML_IF_TYPE__CANCEL2);
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_IF_TYPE__COND, oldCond, cond));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_IF_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            return ((InternalEList<?>) getScxmlCoreExecutablecontent()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__RAISE:
            return ((InternalEList<?>) getRaise()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__IF:
            return ((InternalEList<?>) getIf()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH:
            return ((InternalEList<?>) getForeach()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__SEND:
            return ((InternalEList<?>) getSend()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT:
            return ((InternalEList<?>) getScript()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN:
            return ((InternalEList<?>) getAssign()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__LOG:
            return ((InternalEList<?>) getLog()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL:
            return ((InternalEList<?>) getCancel()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__ELSEIF:
            return basicSetElseif(null, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT1:
            return ((InternalEList<?>) getScxmlCoreExecutablecontent1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__ANY1:
            return ((InternalEList<?>) getAny1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__RAISE1:
            return ((InternalEList<?>) getRaise1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__IF1:
            return ((InternalEList<?>) getIf1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH1:
            return ((InternalEList<?>) getForeach1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__SEND1:
            return ((InternalEList<?>) getSend1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT1:
            return ((InternalEList<?>) getScript1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN1:
            return ((InternalEList<?>) getAssign1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__LOG1:
            return ((InternalEList<?>) getLog1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL1:
            return ((InternalEList<?>) getCancel1()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__ELSE:
            return basicSetElse(null, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT2:
            return ((InternalEList<?>) getScxmlCoreExecutablecontent2()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__ANY2:
            return ((InternalEList<?>) getAny2()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__RAISE2:
            return ((InternalEList<?>) getRaise2()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__IF2:
            return ((InternalEList<?>) getIf2()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH2:
            return ((InternalEList<?>) getForeach2()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__SEND2:
            return ((InternalEList<?>) getSend2()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT2:
            return ((InternalEList<?>) getScript2()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN2:
            return ((InternalEList<?>) getAssign2()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__LOG2:
            return ((InternalEList<?>) getLog2()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL2:
            return ((InternalEList<?>) getCancel2()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_IF_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            if (coreType) {
                return getScxmlCoreExecutablecontent();
            }
            return ((FeatureMap.Internal) getScxmlCoreExecutablecontent()).getWrapper();
        case ScxmlPackage.SCXML_IF_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_IF_TYPE__RAISE:
            return getRaise();
        case ScxmlPackage.SCXML_IF_TYPE__IF:
            return getIf();
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH:
            return getForeach();
        case ScxmlPackage.SCXML_IF_TYPE__SEND:
            return getSend();
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT:
            return getScript();
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN:
            return getAssign();
        case ScxmlPackage.SCXML_IF_TYPE__LOG:
            return getLog();
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL:
            return getCancel();
        case ScxmlPackage.SCXML_IF_TYPE__ELSEIF:
            return getElseif();
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT1:
            if (coreType) {
                return getScxmlCoreExecutablecontent1();
            }
            return ((FeatureMap.Internal) getScxmlCoreExecutablecontent1()).getWrapper();
        case ScxmlPackage.SCXML_IF_TYPE__ANY1:
            if (coreType) {
                return getAny1();
            }
            return ((FeatureMap.Internal) getAny1()).getWrapper();
        case ScxmlPackage.SCXML_IF_TYPE__RAISE1:
            return getRaise1();
        case ScxmlPackage.SCXML_IF_TYPE__IF1:
            return getIf1();
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH1:
            return getForeach1();
        case ScxmlPackage.SCXML_IF_TYPE__SEND1:
            return getSend1();
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT1:
            return getScript1();
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN1:
            return getAssign1();
        case ScxmlPackage.SCXML_IF_TYPE__LOG1:
            return getLog1();
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL1:
            return getCancel1();
        case ScxmlPackage.SCXML_IF_TYPE__ELSE:
            return getElse();
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT2:
            if (coreType) {
                return getScxmlCoreExecutablecontent2();
            }
            return ((FeatureMap.Internal) getScxmlCoreExecutablecontent2()).getWrapper();
        case ScxmlPackage.SCXML_IF_TYPE__ANY2:
            if (coreType) {
                return getAny2();
            }
            return ((FeatureMap.Internal) getAny2()).getWrapper();
        case ScxmlPackage.SCXML_IF_TYPE__RAISE2:
            return getRaise2();
        case ScxmlPackage.SCXML_IF_TYPE__IF2:
            return getIf2();
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH2:
            return getForeach2();
        case ScxmlPackage.SCXML_IF_TYPE__SEND2:
            return getSend2();
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT2:
            return getScript2();
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN2:
            return getAssign2();
        case ScxmlPackage.SCXML_IF_TYPE__LOG2:
            return getLog2();
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL2:
            return getCancel2();
        case ScxmlPackage.SCXML_IF_TYPE__COND:
            return getCond();
        case ScxmlPackage.SCXML_IF_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            ((FeatureMap.Internal) getScxmlCoreExecutablecontent()).set(newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__RAISE:
            getRaise().clear();
            getRaise().addAll((Collection<? extends ScxmlRaiseType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__IF:
            getIf().clear();
            getIf().addAll((Collection<? extends ScxmlIfType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH:
            getForeach().clear();
            getForeach().addAll((Collection<? extends ScxmlForeachType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SEND:
            getSend().clear();
            getSend().addAll((Collection<? extends ScxmlSendType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT:
            getScript().clear();
            getScript().addAll((Collection<? extends ScxmlScriptType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN:
            getAssign().clear();
            getAssign().addAll((Collection<? extends ScxmlAssignType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__LOG:
            getLog().clear();
            getLog().addAll((Collection<? extends ScxmlLogType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL:
            getCancel().clear();
            getCancel().addAll((Collection<? extends ScxmlCancelType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ELSEIF:
            setElseif((ScxmlElseifType) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT1:
            ((FeatureMap.Internal) getScxmlCoreExecutablecontent1()).set(newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ANY1:
            ((FeatureMap.Internal) getAny1()).set(newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__RAISE1:
            getRaise1().clear();
            getRaise1().addAll((Collection<? extends ScxmlRaiseType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__IF1:
            getIf1().clear();
            getIf1().addAll((Collection<? extends ScxmlIfType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH1:
            getForeach1().clear();
            getForeach1().addAll((Collection<? extends ScxmlForeachType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SEND1:
            getSend1().clear();
            getSend1().addAll((Collection<? extends ScxmlSendType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT1:
            getScript1().clear();
            getScript1().addAll((Collection<? extends ScxmlScriptType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN1:
            getAssign1().clear();
            getAssign1().addAll((Collection<? extends ScxmlAssignType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__LOG1:
            getLog1().clear();
            getLog1().addAll((Collection<? extends ScxmlLogType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL1:
            getCancel1().clear();
            getCancel1().addAll((Collection<? extends ScxmlCancelType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ELSE:
            setElse((ScxmlElseType) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT2:
            ((FeatureMap.Internal) getScxmlCoreExecutablecontent2()).set(newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ANY2:
            ((FeatureMap.Internal) getAny2()).set(newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__RAISE2:
            getRaise2().clear();
            getRaise2().addAll((Collection<? extends ScxmlRaiseType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__IF2:
            getIf2().clear();
            getIf2().addAll((Collection<? extends ScxmlIfType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH2:
            getForeach2().clear();
            getForeach2().addAll((Collection<? extends ScxmlForeachType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SEND2:
            getSend2().clear();
            getSend2().addAll((Collection<? extends ScxmlSendType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT2:
            getScript2().clear();
            getScript2().addAll((Collection<? extends ScxmlScriptType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN2:
            getAssign2().clear();
            getAssign2().addAll((Collection<? extends ScxmlAssignType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__LOG2:
            getLog2().clear();
            getLog2().addAll((Collection<? extends ScxmlLogType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL2:
            getCancel2().clear();
            getCancel2().addAll((Collection<? extends ScxmlCancelType>) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__COND:
            setCond((String) newValue);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            getScxmlCoreExecutablecontent().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__RAISE:
            getRaise().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__IF:
            getIf().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH:
            getForeach().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SEND:
            getSend().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT:
            getScript().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN:
            getAssign().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__LOG:
            getLog().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL:
            getCancel().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ELSEIF:
            setElseif((ScxmlElseifType) null);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT1:
            getScxmlCoreExecutablecontent1().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ANY1:
            getAny1().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__RAISE1:
            getRaise1().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__IF1:
            getIf1().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH1:
            getForeach1().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SEND1:
            getSend1().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT1:
            getScript1().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN1:
            getAssign1().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__LOG1:
            getLog1().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL1:
            getCancel1().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ELSE:
            setElse((ScxmlElseType) null);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT2:
            getScxmlCoreExecutablecontent2().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ANY2:
            getAny2().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__RAISE2:
            getRaise2().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__IF2:
            getIf2().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH2:
            getForeach2().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SEND2:
            getSend2().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT2:
            getScript2().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN2:
            getAssign2().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__LOG2:
            getLog2().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL2:
            getCancel2().clear();
            return;
        case ScxmlPackage.SCXML_IF_TYPE__COND:
            setCond(ScxmlIfTypeImpl.COND_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_IF_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            return scxmlCoreExecutablecontent != null && !scxmlCoreExecutablecontent.isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__RAISE:
            return !getRaise().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__IF:
            return !getIf().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH:
            return !getForeach().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__SEND:
            return !getSend().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT:
            return !getScript().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN:
            return !getAssign().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__LOG:
            return !getLog().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL:
            return !getCancel().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__ELSEIF:
            return elseif != null;
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT1:
            return scxmlCoreExecutablecontent1 != null && !scxmlCoreExecutablecontent1.isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__ANY1:
            return !getAny1().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__RAISE1:
            return !getRaise1().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__IF1:
            return !getIf1().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH1:
            return !getForeach1().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__SEND1:
            return !getSend1().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT1:
            return !getScript1().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN1:
            return !getAssign1().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__LOG1:
            return !getLog1().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL1:
            return !getCancel1().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__ELSE:
            return else_ != null;
        case ScxmlPackage.SCXML_IF_TYPE__SCXML_CORE_EXECUTABLECONTENT2:
            return scxmlCoreExecutablecontent2 != null && !scxmlCoreExecutablecontent2.isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__ANY2:
            return !getAny2().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__RAISE2:
            return !getRaise2().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__IF2:
            return !getIf2().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__FOREACH2:
            return !getForeach2().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__SEND2:
            return !getSend2().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__SCRIPT2:
            return !getScript2().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__ASSIGN2:
            return !getAssign2().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__LOG2:
            return !getLog2().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__CANCEL2:
            return !getCancel2().isEmpty();
        case ScxmlPackage.SCXML_IF_TYPE__COND:
            return ScxmlIfTypeImpl.COND_EDEFAULT == null ? cond != null : !ScxmlIfTypeImpl.COND_EDEFAULT.equals(cond);
        case ScxmlPackage.SCXML_IF_TYPE__ANY_ATTRIBUTE:
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
        result.append(", scxmlCoreExecutablecontent1: "); //$NON-NLS-1$
        result.append(scxmlCoreExecutablecontent1);
        result.append(", scxmlCoreExecutablecontent2: "); //$NON-NLS-1$
        result.append(scxmlCoreExecutablecontent2);
        result.append(", cond: "); //$NON-NLS-1$
        result.append(cond);
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlIfTypeImpl
