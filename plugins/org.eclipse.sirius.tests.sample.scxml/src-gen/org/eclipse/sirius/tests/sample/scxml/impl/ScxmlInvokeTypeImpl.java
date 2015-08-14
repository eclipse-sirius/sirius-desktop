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
import org.eclipse.sirius.tests.sample.scxml.BooleanDatatype;
import org.eclipse.sirius.tests.sample.scxml.ScxmlContentType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParamType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Invoke Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getScxmlInvokeMix
 * <em>Scxml Invoke Mix</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getContent
 * <em>Content</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getParam
 * <em>Param</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getFinalize
 * <em>Finalize</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getAutoforward
 * <em>Autoforward</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getIdlocation
 * <em>Idlocation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getNamelist
 * <em>Namelist</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getSrc
 * <em>Src</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getSrcexpr
 * <em>Srcexpr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getType
 * <em>Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getTypeexpr
 * <em>Typeexpr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlInvokeTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlInvokeTypeImpl extends MinimalEObjectImpl.Container implements ScxmlInvokeType {
    /**
     * The cached value of the '{@link #getScxmlInvokeMix()
     * <em>Scxml Invoke Mix</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getScxmlInvokeMix()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlInvokeMix;

    /**
     * The default value of the '{@link #getAutoforward() <em>Autoforward</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAutoforward()
     * @generated
     * @ordered
     */
    protected static final BooleanDatatype AUTOFORWARD_EDEFAULT = BooleanDatatype.FALSE;

    /**
     * The cached value of the '{@link #getAutoforward() <em>Autoforward</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAutoforward()
     * @generated
     * @ordered
     */
    protected BooleanDatatype autoforward = ScxmlInvokeTypeImpl.AUTOFORWARD_EDEFAULT;

    /**
     * This is true if the Autoforward attribute has been set. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    protected boolean autoforwardESet;

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
    protected String id = ScxmlInvokeTypeImpl.ID_EDEFAULT;

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
    protected String idlocation = ScxmlInvokeTypeImpl.IDLOCATION_EDEFAULT;

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
    protected String namelist = ScxmlInvokeTypeImpl.NAMELIST_EDEFAULT;

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
    protected String src = ScxmlInvokeTypeImpl.SRC_EDEFAULT;

    /**
     * The default value of the '{@link #getSrcexpr() <em>Srcexpr</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSrcexpr()
     * @generated
     * @ordered
     */
    protected static final String SRCEXPR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSrcexpr() <em>Srcexpr</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSrcexpr()
     * @generated
     * @ordered
     */
    protected String srcexpr = ScxmlInvokeTypeImpl.SRCEXPR_EDEFAULT;

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
    protected String type = ScxmlInvokeTypeImpl.TYPE_EDEFAULT;

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
    protected String typeexpr = ScxmlInvokeTypeImpl.TYPEEXPR_EDEFAULT;

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
    protected ScxmlInvokeTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_INVOKE_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlInvokeMix() {
        if (scxmlInvokeMix == null) {
            scxmlInvokeMix = new BasicFeatureMap(this, ScxmlPackage.SCXML_INVOKE_TYPE__SCXML_INVOKE_MIX);
        }
        return scxmlInvokeMix;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlContentType> getContent() {
        return getScxmlInvokeMix().list(ScxmlPackage.Literals.SCXML_INVOKE_TYPE__CONTENT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlParamType> getParam() {
        return getScxmlInvokeMix().list(ScxmlPackage.Literals.SCXML_INVOKE_TYPE__PARAM);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlFinalizeType> getFinalize() {
        return getScxmlInvokeMix().list(ScxmlPackage.Literals.SCXML_INVOKE_TYPE__FINALIZE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap) getScxmlInvokeMix().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_INVOKE_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BooleanDatatype getAutoforward() {
        return autoforward;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAutoforward(BooleanDatatype newAutoforward) {
        BooleanDatatype oldAutoforward = autoforward;
        autoforward = newAutoforward == null ? ScxmlInvokeTypeImpl.AUTOFORWARD_EDEFAULT : newAutoforward;
        boolean oldAutoforwardESet = autoforwardESet;
        autoforwardESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_INVOKE_TYPE__AUTOFORWARD, oldAutoforward, autoforward, !oldAutoforwardESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetAutoforward() {
        BooleanDatatype oldAutoforward = autoforward;
        boolean oldAutoforwardESet = autoforwardESet;
        autoforward = ScxmlInvokeTypeImpl.AUTOFORWARD_EDEFAULT;
        autoforwardESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_INVOKE_TYPE__AUTOFORWARD, oldAutoforward, ScxmlInvokeTypeImpl.AUTOFORWARD_EDEFAULT, oldAutoforwardESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSetAutoforward() {
        return autoforwardESet;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_INVOKE_TYPE__ID, oldId, id));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_INVOKE_TYPE__IDLOCATION, oldIdlocation, idlocation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_INVOKE_TYPE__NAMELIST, oldNamelist, namelist));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_INVOKE_TYPE__SRC, oldSrc, src));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getSrcexpr() {
        return srcexpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSrcexpr(String newSrcexpr) {
        String oldSrcexpr = srcexpr;
        srcexpr = newSrcexpr;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_INVOKE_TYPE__SRCEXPR, oldSrcexpr, srcexpr));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_INVOKE_TYPE__TYPE, oldType, type, !oldTypeESet));
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
        type = ScxmlInvokeTypeImpl.TYPE_EDEFAULT;
        typeESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_INVOKE_TYPE__TYPE, oldType, ScxmlInvokeTypeImpl.TYPE_EDEFAULT, oldTypeESet));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_INVOKE_TYPE__TYPEEXPR, oldTypeexpr, typeexpr));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_INVOKE_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_INVOKE_TYPE__SCXML_INVOKE_MIX:
            return ((InternalEList<?>) getScxmlInvokeMix()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_INVOKE_TYPE__CONTENT:
            return ((InternalEList<?>) getContent()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_INVOKE_TYPE__PARAM:
            return ((InternalEList<?>) getParam()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_INVOKE_TYPE__FINALIZE:
            return ((InternalEList<?>) getFinalize()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_INVOKE_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_INVOKE_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_INVOKE_TYPE__SCXML_INVOKE_MIX:
            if (coreType) {
                return getScxmlInvokeMix();
            }
            return ((FeatureMap.Internal) getScxmlInvokeMix()).getWrapper();
        case ScxmlPackage.SCXML_INVOKE_TYPE__CONTENT:
            return getContent();
        case ScxmlPackage.SCXML_INVOKE_TYPE__PARAM:
            return getParam();
        case ScxmlPackage.SCXML_INVOKE_TYPE__FINALIZE:
            return getFinalize();
        case ScxmlPackage.SCXML_INVOKE_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_INVOKE_TYPE__AUTOFORWARD:
            return getAutoforward();
        case ScxmlPackage.SCXML_INVOKE_TYPE__ID:
            return getId();
        case ScxmlPackage.SCXML_INVOKE_TYPE__IDLOCATION:
            return getIdlocation();
        case ScxmlPackage.SCXML_INVOKE_TYPE__NAMELIST:
            return getNamelist();
        case ScxmlPackage.SCXML_INVOKE_TYPE__SRC:
            return getSrc();
        case ScxmlPackage.SCXML_INVOKE_TYPE__SRCEXPR:
            return getSrcexpr();
        case ScxmlPackage.SCXML_INVOKE_TYPE__TYPE:
            return getType();
        case ScxmlPackage.SCXML_INVOKE_TYPE__TYPEEXPR:
            return getTypeexpr();
        case ScxmlPackage.SCXML_INVOKE_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_INVOKE_TYPE__SCXML_INVOKE_MIX:
            ((FeatureMap.Internal) getScxmlInvokeMix()).set(newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__CONTENT:
            getContent().clear();
            getContent().addAll((Collection<? extends ScxmlContentType>) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__PARAM:
            getParam().clear();
            getParam().addAll((Collection<? extends ScxmlParamType>) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__FINALIZE:
            getFinalize().clear();
            getFinalize().addAll((Collection<? extends ScxmlFinalizeType>) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__AUTOFORWARD:
            setAutoforward((BooleanDatatype) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__ID:
            setId((String) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__IDLOCATION:
            setIdlocation((String) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__NAMELIST:
            setNamelist((String) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__SRC:
            setSrc((String) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__SRCEXPR:
            setSrcexpr((String) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__TYPE:
            setType((String) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__TYPEEXPR:
            setTypeexpr((String) newValue);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_INVOKE_TYPE__SCXML_INVOKE_MIX:
            getScxmlInvokeMix().clear();
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__CONTENT:
            getContent().clear();
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__PARAM:
            getParam().clear();
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__FINALIZE:
            getFinalize().clear();
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__AUTOFORWARD:
            unsetAutoforward();
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__ID:
            setId(ScxmlInvokeTypeImpl.ID_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__IDLOCATION:
            setIdlocation(ScxmlInvokeTypeImpl.IDLOCATION_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__NAMELIST:
            setNamelist(ScxmlInvokeTypeImpl.NAMELIST_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__SRC:
            setSrc(ScxmlInvokeTypeImpl.SRC_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__SRCEXPR:
            setSrcexpr(ScxmlInvokeTypeImpl.SRCEXPR_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__TYPE:
            unsetType();
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__TYPEEXPR:
            setTypeexpr(ScxmlInvokeTypeImpl.TYPEEXPR_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_INVOKE_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_INVOKE_TYPE__SCXML_INVOKE_MIX:
            return scxmlInvokeMix != null && !scxmlInvokeMix.isEmpty();
        case ScxmlPackage.SCXML_INVOKE_TYPE__CONTENT:
            return !getContent().isEmpty();
        case ScxmlPackage.SCXML_INVOKE_TYPE__PARAM:
            return !getParam().isEmpty();
        case ScxmlPackage.SCXML_INVOKE_TYPE__FINALIZE:
            return !getFinalize().isEmpty();
        case ScxmlPackage.SCXML_INVOKE_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_INVOKE_TYPE__AUTOFORWARD:
            return isSetAutoforward();
        case ScxmlPackage.SCXML_INVOKE_TYPE__ID:
            return ScxmlInvokeTypeImpl.ID_EDEFAULT == null ? id != null : !ScxmlInvokeTypeImpl.ID_EDEFAULT.equals(id);
        case ScxmlPackage.SCXML_INVOKE_TYPE__IDLOCATION:
            return ScxmlInvokeTypeImpl.IDLOCATION_EDEFAULT == null ? idlocation != null : !ScxmlInvokeTypeImpl.IDLOCATION_EDEFAULT.equals(idlocation);
        case ScxmlPackage.SCXML_INVOKE_TYPE__NAMELIST:
            return ScxmlInvokeTypeImpl.NAMELIST_EDEFAULT == null ? namelist != null : !ScxmlInvokeTypeImpl.NAMELIST_EDEFAULT.equals(namelist);
        case ScxmlPackage.SCXML_INVOKE_TYPE__SRC:
            return ScxmlInvokeTypeImpl.SRC_EDEFAULT == null ? src != null : !ScxmlInvokeTypeImpl.SRC_EDEFAULT.equals(src);
        case ScxmlPackage.SCXML_INVOKE_TYPE__SRCEXPR:
            return ScxmlInvokeTypeImpl.SRCEXPR_EDEFAULT == null ? srcexpr != null : !ScxmlInvokeTypeImpl.SRCEXPR_EDEFAULT.equals(srcexpr);
        case ScxmlPackage.SCXML_INVOKE_TYPE__TYPE:
            return isSetType();
        case ScxmlPackage.SCXML_INVOKE_TYPE__TYPEEXPR:
            return ScxmlInvokeTypeImpl.TYPEEXPR_EDEFAULT == null ? typeexpr != null : !ScxmlInvokeTypeImpl.TYPEEXPR_EDEFAULT.equals(typeexpr);
        case ScxmlPackage.SCXML_INVOKE_TYPE__ANY_ATTRIBUTE:
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
        result.append(" (scxmlInvokeMix: "); //$NON-NLS-1$
        result.append(scxmlInvokeMix);
        result.append(", autoforward: "); //$NON-NLS-1$
        if (autoforwardESet) {
            result.append(autoforward);
        } else {
            result.append("<unset>"); //$NON-NLS-1$
        }
        result.append(", id: "); //$NON-NLS-1$
        result.append(id);
        result.append(", idlocation: "); //$NON-NLS-1$
        result.append(idlocation);
        result.append(", namelist: "); //$NON-NLS-1$
        result.append(namelist);
        result.append(", src: "); //$NON-NLS-1$
        result.append(src);
        result.append(", srcexpr: "); //$NON-NLS-1$
        result.append(srcexpr);
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

} // ScxmlInvokeTypeImpl
