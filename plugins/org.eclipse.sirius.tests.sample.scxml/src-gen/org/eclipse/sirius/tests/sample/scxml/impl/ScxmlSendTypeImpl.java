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
import org.eclipse.sirius.tests.sample.scxml.ScxmlContentType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParamType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlSendType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Send Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getScxmlSendMix
 * <em>Scxml Send Mix</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getContent
 * <em>Content</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getParam
 * <em>Param</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getDelay
 * <em>Delay</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getDelayexpr
 * <em>Delayexpr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getEvent
 * <em>Event</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getEventexpr
 * <em>Eventexpr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getIdlocation
 * <em>Idlocation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getNamelist
 * <em>Namelist</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getTargetexpr
 * <em>Targetexpr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getType
 * <em>Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getTypeexpr
 * <em>Typeexpr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlSendTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlSendTypeImpl extends MinimalEObjectImpl.Container implements ScxmlSendType {
    /**
     * The cached value of the '{@link #getScxmlSendMix()
     * <em>Scxml Send Mix</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getScxmlSendMix()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlSendMix;

    /**
     * The default value of the '{@link #getDelay() <em>Delay</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDelay()
     * @generated
     * @ordered
     */
    protected static final String DELAY_EDEFAULT = "0s";

    /**
     * The cached value of the '{@link #getDelay() <em>Delay</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDelay()
     * @generated
     * @ordered
     */
    protected String delay = ScxmlSendTypeImpl.DELAY_EDEFAULT;

    /**
     * This is true if the Delay attribute has been set. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    protected boolean delayESet;

    /**
     * The default value of the '{@link #getDelayexpr() <em>Delayexpr</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDelayexpr()
     * @generated
     * @ordered
     */
    protected static final String DELAYEXPR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDelayexpr() <em>Delayexpr</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDelayexpr()
     * @generated
     * @ordered
     */
    protected String delayexpr = ScxmlSendTypeImpl.DELAYEXPR_EDEFAULT;

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
    protected String event = ScxmlSendTypeImpl.EVENT_EDEFAULT;

    /**
     * The default value of the '{@link #getEventexpr() <em>Eventexpr</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEventexpr()
     * @generated
     * @ordered
     */
    protected static final String EVENTEXPR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEventexpr() <em>Eventexpr</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEventexpr()
     * @generated
     * @ordered
     */
    protected String eventexpr = ScxmlSendTypeImpl.EVENTEXPR_EDEFAULT;

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
    protected String id = ScxmlSendTypeImpl.ID_EDEFAULT;

    /**
     * The default value of the '{@link #getIdlocation() <em>Idlocation</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIdlocation()
     * @generated
     * @ordered
     */
    protected static final String IDLOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIdlocation() <em>Idlocation</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIdlocation()
     * @generated
     * @ordered
     */
    protected String idlocation = ScxmlSendTypeImpl.IDLOCATION_EDEFAULT;

    /**
     * The default value of the '{@link #getNamelist() <em>Namelist</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNamelist()
     * @generated
     * @ordered
     */
    protected static final String NAMELIST_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNamelist() <em>Namelist</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNamelist()
     * @generated
     * @ordered
     */
    protected String namelist = ScxmlSendTypeImpl.NAMELIST_EDEFAULT;

    /**
     * The default value of the '{@link #getTarget() <em>Target</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected static final String TARGET_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected String target = ScxmlSendTypeImpl.TARGET_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetexpr() <em>Targetexpr</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTargetexpr()
     * @generated
     * @ordered
     */
    protected static final String TARGETEXPR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetexpr() <em>Targetexpr</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTargetexpr()
     * @generated
     * @ordered
     */
    protected String targetexpr = ScxmlSendTypeImpl.TARGETEXPR_EDEFAULT;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = "scxml";

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected String type = ScxmlSendTypeImpl.TYPE_EDEFAULT;

    /**
     * This is true if the Type attribute has been set. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    protected boolean typeESet;

    /**
     * The default value of the '{@link #getTypeexpr() <em>Typeexpr</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTypeexpr()
     * @generated
     * @ordered
     */
    protected static final String TYPEEXPR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTypeexpr() <em>Typeexpr</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTypeexpr()
     * @generated
     * @ordered
     */
    protected String typeexpr = ScxmlSendTypeImpl.TYPEEXPR_EDEFAULT;

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
    protected ScxmlSendTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_SEND_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlSendMix() {
        if (scxmlSendMix == null) {
            scxmlSendMix = new BasicFeatureMap(this, ScxmlPackage.SCXML_SEND_TYPE__SCXML_SEND_MIX);
        }
        return scxmlSendMix;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlContentType> getContent() {
        return getScxmlSendMix().list(ScxmlPackage.Literals.SCXML_SEND_TYPE__CONTENT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlParamType> getParam() {
        return getScxmlSendMix().list(ScxmlPackage.Literals.SCXML_SEND_TYPE__PARAM);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap) getScxmlSendMix().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_SEND_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDelay() {
        return delay;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDelay(String newDelay) {
        String oldDelay = delay;
        delay = newDelay;
        boolean oldDelayESet = delayESet;
        delayESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__DELAY, oldDelay, delay, !oldDelayESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetDelay() {
        String oldDelay = delay;
        boolean oldDelayESet = delayESet;
        delay = ScxmlSendTypeImpl.DELAY_EDEFAULT;
        delayESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_SEND_TYPE__DELAY, oldDelay, ScxmlSendTypeImpl.DELAY_EDEFAULT, oldDelayESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSetDelay() {
        return delayESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDelayexpr() {
        return delayexpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDelayexpr(String newDelayexpr) {
        String oldDelayexpr = delayexpr;
        delayexpr = newDelayexpr;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__DELAYEXPR, oldDelayexpr, delayexpr));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__EVENT, oldEvent, event));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getEventexpr() {
        return eventexpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEventexpr(String newEventexpr) {
        String oldEventexpr = eventexpr;
        eventexpr = newEventexpr;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__EVENTEXPR, oldEventexpr, eventexpr));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__ID, oldId, id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIdlocation() {
        return idlocation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIdlocation(String newIdlocation) {
        String oldIdlocation = idlocation;
        idlocation = newIdlocation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__IDLOCATION, oldIdlocation, idlocation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getNamelist() {
        return namelist;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setNamelist(String newNamelist) {
        String oldNamelist = namelist;
        namelist = newNamelist;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__NAMELIST, oldNamelist, namelist));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTarget(String newTarget) {
        String oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__TARGET, oldTarget, target));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTargetexpr() {
        return targetexpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTargetexpr(String newTargetexpr) {
        String oldTargetexpr = targetexpr;
        targetexpr = newTargetexpr;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__TARGETEXPR, oldTargetexpr, targetexpr));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setType(String newType) {
        String oldType = type;
        type = newType;
        boolean oldTypeESet = typeESet;
        typeESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__TYPE, oldType, type, !oldTypeESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetType() {
        String oldType = type;
        boolean oldTypeESet = typeESet;
        type = ScxmlSendTypeImpl.TYPE_EDEFAULT;
        typeESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_SEND_TYPE__TYPE, oldType, ScxmlSendTypeImpl.TYPE_EDEFAULT, oldTypeESet));
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
    public String getTypeexpr() {
        return typeexpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTypeexpr(String newTypeexpr) {
        String oldTypeexpr = typeexpr;
        typeexpr = newTypeexpr;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SEND_TYPE__TYPEEXPR, oldTypeexpr, typeexpr));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_SEND_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_SEND_TYPE__SCXML_SEND_MIX:
            return ((InternalEList<?>) getScxmlSendMix()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SEND_TYPE__CONTENT:
            return ((InternalEList<?>) getContent()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SEND_TYPE__PARAM:
            return ((InternalEList<?>) getParam()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SEND_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SEND_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SEND_TYPE__SCXML_SEND_MIX:
            if (coreType) {
                return getScxmlSendMix();
            }
            return ((FeatureMap.Internal) getScxmlSendMix()).getWrapper();
        case ScxmlPackage.SCXML_SEND_TYPE__CONTENT:
            return getContent();
        case ScxmlPackage.SCXML_SEND_TYPE__PARAM:
            return getParam();
        case ScxmlPackage.SCXML_SEND_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_SEND_TYPE__DELAY:
            return getDelay();
        case ScxmlPackage.SCXML_SEND_TYPE__DELAYEXPR:
            return getDelayexpr();
        case ScxmlPackage.SCXML_SEND_TYPE__EVENT:
            return getEvent();
        case ScxmlPackage.SCXML_SEND_TYPE__EVENTEXPR:
            return getEventexpr();
        case ScxmlPackage.SCXML_SEND_TYPE__ID:
            return getId();
        case ScxmlPackage.SCXML_SEND_TYPE__IDLOCATION:
            return getIdlocation();
        case ScxmlPackage.SCXML_SEND_TYPE__NAMELIST:
            return getNamelist();
        case ScxmlPackage.SCXML_SEND_TYPE__TARGET:
            return getTarget();
        case ScxmlPackage.SCXML_SEND_TYPE__TARGETEXPR:
            return getTargetexpr();
        case ScxmlPackage.SCXML_SEND_TYPE__TYPE:
            return getType();
        case ScxmlPackage.SCXML_SEND_TYPE__TYPEEXPR:
            return getTypeexpr();
        case ScxmlPackage.SCXML_SEND_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SEND_TYPE__SCXML_SEND_MIX:
            ((FeatureMap.Internal) getScxmlSendMix()).set(newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__CONTENT:
            getContent().clear();
            getContent().addAll((Collection<? extends ScxmlContentType>) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__PARAM:
            getParam().clear();
            getParam().addAll((Collection<? extends ScxmlParamType>) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__DELAY:
            setDelay((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__DELAYEXPR:
            setDelayexpr((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__EVENT:
            setEvent((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__EVENTEXPR:
            setEventexpr((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__ID:
            setId((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__IDLOCATION:
            setIdlocation((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__NAMELIST:
            setNamelist((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__TARGET:
            setTarget((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__TARGETEXPR:
            setTargetexpr((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__TYPE:
            setType((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__TYPEEXPR:
            setTypeexpr((String) newValue);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SEND_TYPE__SCXML_SEND_MIX:
            getScxmlSendMix().clear();
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__CONTENT:
            getContent().clear();
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__PARAM:
            getParam().clear();
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__DELAY:
            unsetDelay();
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__DELAYEXPR:
            setDelayexpr(ScxmlSendTypeImpl.DELAYEXPR_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__EVENT:
            setEvent(ScxmlSendTypeImpl.EVENT_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__EVENTEXPR:
            setEventexpr(ScxmlSendTypeImpl.EVENTEXPR_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__ID:
            setId(ScxmlSendTypeImpl.ID_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__IDLOCATION:
            setIdlocation(ScxmlSendTypeImpl.IDLOCATION_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__NAMELIST:
            setNamelist(ScxmlSendTypeImpl.NAMELIST_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__TARGET:
            setTarget(ScxmlSendTypeImpl.TARGET_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__TARGETEXPR:
            setTargetexpr(ScxmlSendTypeImpl.TARGETEXPR_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__TYPE:
            unsetType();
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__TYPEEXPR:
            setTypeexpr(ScxmlSendTypeImpl.TYPEEXPR_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SEND_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SEND_TYPE__SCXML_SEND_MIX:
            return scxmlSendMix != null && !scxmlSendMix.isEmpty();
        case ScxmlPackage.SCXML_SEND_TYPE__CONTENT:
            return !getContent().isEmpty();
        case ScxmlPackage.SCXML_SEND_TYPE__PARAM:
            return !getParam().isEmpty();
        case ScxmlPackage.SCXML_SEND_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_SEND_TYPE__DELAY:
            return isSetDelay();
        case ScxmlPackage.SCXML_SEND_TYPE__DELAYEXPR:
            return ScxmlSendTypeImpl.DELAYEXPR_EDEFAULT == null ? delayexpr != null : !ScxmlSendTypeImpl.DELAYEXPR_EDEFAULT.equals(delayexpr);
        case ScxmlPackage.SCXML_SEND_TYPE__EVENT:
            return ScxmlSendTypeImpl.EVENT_EDEFAULT == null ? event != null : !ScxmlSendTypeImpl.EVENT_EDEFAULT.equals(event);
        case ScxmlPackage.SCXML_SEND_TYPE__EVENTEXPR:
            return ScxmlSendTypeImpl.EVENTEXPR_EDEFAULT == null ? eventexpr != null : !ScxmlSendTypeImpl.EVENTEXPR_EDEFAULT.equals(eventexpr);
        case ScxmlPackage.SCXML_SEND_TYPE__ID:
            return ScxmlSendTypeImpl.ID_EDEFAULT == null ? id != null : !ScxmlSendTypeImpl.ID_EDEFAULT.equals(id);
        case ScxmlPackage.SCXML_SEND_TYPE__IDLOCATION:
            return ScxmlSendTypeImpl.IDLOCATION_EDEFAULT == null ? idlocation != null : !ScxmlSendTypeImpl.IDLOCATION_EDEFAULT.equals(idlocation);
        case ScxmlPackage.SCXML_SEND_TYPE__NAMELIST:
            return ScxmlSendTypeImpl.NAMELIST_EDEFAULT == null ? namelist != null : !ScxmlSendTypeImpl.NAMELIST_EDEFAULT.equals(namelist);
        case ScxmlPackage.SCXML_SEND_TYPE__TARGET:
            return ScxmlSendTypeImpl.TARGET_EDEFAULT == null ? target != null : !ScxmlSendTypeImpl.TARGET_EDEFAULT.equals(target);
        case ScxmlPackage.SCXML_SEND_TYPE__TARGETEXPR:
            return ScxmlSendTypeImpl.TARGETEXPR_EDEFAULT == null ? targetexpr != null : !ScxmlSendTypeImpl.TARGETEXPR_EDEFAULT.equals(targetexpr);
        case ScxmlPackage.SCXML_SEND_TYPE__TYPE:
            return isSetType();
        case ScxmlPackage.SCXML_SEND_TYPE__TYPEEXPR:
            return ScxmlSendTypeImpl.TYPEEXPR_EDEFAULT == null ? typeexpr != null : !ScxmlSendTypeImpl.TYPEEXPR_EDEFAULT.equals(typeexpr);
        case ScxmlPackage.SCXML_SEND_TYPE__ANY_ATTRIBUTE:
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
        result.append(" (scxmlSendMix: "); //$NON-NLS-1$
        result.append(scxmlSendMix);
        result.append(", delay: "); //$NON-NLS-1$
        if (delayESet) {
            result.append(delay);
        } else {
            result.append("<unset>"); //$NON-NLS-1$
        }
        result.append(", delayexpr: "); //$NON-NLS-1$
        result.append(delayexpr);
        result.append(", event: "); //$NON-NLS-1$
        result.append(event);
        result.append(", eventexpr: "); //$NON-NLS-1$
        result.append(eventexpr);
        result.append(", id: "); //$NON-NLS-1$
        result.append(id);
        result.append(", idlocation: "); //$NON-NLS-1$
        result.append(idlocation);
        result.append(", namelist: "); //$NON-NLS-1$
        result.append(namelist);
        result.append(", target: "); //$NON-NLS-1$
        result.append(target);
        result.append(", targetexpr: "); //$NON-NLS-1$
        result.append(targetexpr);
        result.append(", type: "); //$NON-NLS-1$
        if (typeESet) {
            result.append(type);
        } else {
            result.append("<unset>"); //$NON-NLS-1$
        }
        result.append(", typeexpr: "); //$NON-NLS-1$
        result.append(typeexpr);
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlSendTypeImpl
