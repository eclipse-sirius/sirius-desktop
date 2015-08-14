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

import java.math.BigDecimal;
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
import org.eclipse.sirius.tests.sample.scxml.BindingDatatype;
import org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlStateType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Scxml Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getScxmlScxmlMix
 * <em>Scxml Scxml Mix</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getState
 * <em>State</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getParallel
 * <em>Parallel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getFinal
 * <em>Final</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getDatamodel
 * <em>Datamodel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getScript
 * <em>Script</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getBinding
 * <em>Binding</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getDatamodel1
 * <em>Datamodel1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getExmode
 * <em>Exmode</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getInitial
 * <em>Initial</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getVersion
 * <em>Version</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlScxmlTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlScxmlTypeImpl extends MinimalEObjectImpl.Container implements ScxmlScxmlType {
    /**
     * The cached value of the '{@link #getScxmlScxmlMix()
     * <em>Scxml Scxml Mix</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getScxmlScxmlMix()
     * @generated
     * @ordered
     */
    protected FeatureMap scxmlScxmlMix;

    /**
     * The default value of the '{@link #getBinding() <em>Binding</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBinding()
     * @generated
     * @ordered
     */
    protected static final BindingDatatype BINDING_EDEFAULT = BindingDatatype.EARLY;

    /**
     * The cached value of the '{@link #getBinding() <em>Binding</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBinding()
     * @generated
     * @ordered
     */
    protected BindingDatatype binding = ScxmlScxmlTypeImpl.BINDING_EDEFAULT;

    /**
     * This is true if the Binding attribute has been set. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    protected boolean bindingESet;

    /**
     * The default value of the '{@link #getDatamodel1() <em>Datamodel1</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDatamodel1()
     * @generated
     * @ordered
     */
    protected static final String DATAMODEL1_EDEFAULT = "null";

    /**
     * The cached value of the '{@link #getDatamodel1() <em>Datamodel1</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDatamodel1()
     * @generated
     * @ordered
     */
    protected String datamodel1 = ScxmlScxmlTypeImpl.DATAMODEL1_EDEFAULT;

    /**
     * This is true if the Datamodel1 attribute has been set. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    protected boolean datamodel1ESet;

    /**
     * The default value of the '{@link #getExmode() <em>Exmode</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getExmode()
     * @generated
     * @ordered
     */
    protected static final ExmodeDatatype EXMODE_EDEFAULT = ExmodeDatatype.LAX;

    /**
     * The cached value of the '{@link #getExmode() <em>Exmode</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getExmode()
     * @generated
     * @ordered
     */
    protected ExmodeDatatype exmode = ScxmlScxmlTypeImpl.EXMODE_EDEFAULT;

    /**
     * This is true if the Exmode attribute has been set. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    protected boolean exmodeESet;

    /**
     * The default value of the '{@link #getInitial() <em>Initial</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInitial()
     * @generated
     * @ordered
     */
    protected static final List<String> INITIAL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInitial() <em>Initial</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInitial()
     * @generated
     * @ordered
     */
    protected List<String> initial = ScxmlScxmlTypeImpl.INITIAL_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = ScxmlScxmlTypeImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final BigDecimal VERSION_EDEFAULT = new BigDecimal("1.0");

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected BigDecimal version = ScxmlScxmlTypeImpl.VERSION_EDEFAULT;

    /**
     * This is true if the Version attribute has been set. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    protected boolean versionESet;

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
    protected ScxmlScxmlTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_SCXML_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlScxmlMix() {
        if (scxmlScxmlMix == null) {
            scxmlScxmlMix = new BasicFeatureMap(this, ScxmlPackage.SCXML_SCXML_TYPE__SCXML_SCXML_MIX);
        }
        return scxmlScxmlMix;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlStateType> getState() {
        return getScxmlScxmlMix().list(ScxmlPackage.Literals.SCXML_SCXML_TYPE__STATE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlParallelType> getParallel() {
        return getScxmlScxmlMix().list(ScxmlPackage.Literals.SCXML_SCXML_TYPE__PARALLEL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlFinalType> getFinal() {
        return getScxmlScxmlMix().list(ScxmlPackage.Literals.SCXML_SCXML_TYPE__FINAL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlDatamodelType> getDatamodel() {
        return getScxmlScxmlMix().list(ScxmlPackage.Literals.SCXML_SCXML_TYPE__DATAMODEL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlScriptType> getScript() {
        return getScxmlScxmlMix().list(ScxmlPackage.Literals.SCXML_SCXML_TYPE__SCRIPT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAny() {
        return (FeatureMap) getScxmlScxmlMix().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_SCXML_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BindingDatatype getBinding() {
        return binding;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBinding(BindingDatatype newBinding) {
        BindingDatatype oldBinding = binding;
        binding = newBinding == null ? ScxmlScxmlTypeImpl.BINDING_EDEFAULT : newBinding;
        boolean oldBindingESet = bindingESet;
        bindingESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SCXML_TYPE__BINDING, oldBinding, binding, !oldBindingESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetBinding() {
        BindingDatatype oldBinding = binding;
        boolean oldBindingESet = bindingESet;
        binding = ScxmlScxmlTypeImpl.BINDING_EDEFAULT;
        bindingESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_SCXML_TYPE__BINDING, oldBinding, ScxmlScxmlTypeImpl.BINDING_EDEFAULT, oldBindingESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSetBinding() {
        return bindingESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDatamodel1() {
        return datamodel1;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDatamodel1(String newDatamodel1) {
        String oldDatamodel1 = datamodel1;
        datamodel1 = newDatamodel1;
        boolean oldDatamodel1ESet = datamodel1ESet;
        datamodel1ESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL1, oldDatamodel1, datamodel1, !oldDatamodel1ESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetDatamodel1() {
        String oldDatamodel1 = datamodel1;
        boolean oldDatamodel1ESet = datamodel1ESet;
        datamodel1 = ScxmlScxmlTypeImpl.DATAMODEL1_EDEFAULT;
        datamodel1ESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL1, oldDatamodel1, ScxmlScxmlTypeImpl.DATAMODEL1_EDEFAULT, oldDatamodel1ESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSetDatamodel1() {
        return datamodel1ESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExmodeDatatype getExmode() {
        return exmode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExmode(ExmodeDatatype newExmode) {
        ExmodeDatatype oldExmode = exmode;
        exmode = newExmode == null ? ScxmlScxmlTypeImpl.EXMODE_EDEFAULT : newExmode;
        boolean oldExmodeESet = exmodeESet;
        exmodeESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SCXML_TYPE__EXMODE, oldExmode, exmode, !oldExmodeESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetExmode() {
        ExmodeDatatype oldExmode = exmode;
        boolean oldExmodeESet = exmodeESet;
        exmode = ScxmlScxmlTypeImpl.EXMODE_EDEFAULT;
        exmodeESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_SCXML_TYPE__EXMODE, oldExmode, ScxmlScxmlTypeImpl.EXMODE_EDEFAULT, oldExmodeESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSetExmode() {
        return exmodeESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public List<String> getInitial() {
        return initial;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInitial(List<String> newInitial) {
        List<String> oldInitial = initial;
        initial = newInitial;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SCXML_TYPE__INITIAL, oldInitial, initial));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SCXML_TYPE__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BigDecimal getVersion() {
        return version;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setVersion(BigDecimal newVersion) {
        BigDecimal oldVersion = version;
        version = newVersion;
        boolean oldVersionESet = versionESet;
        versionESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_SCXML_TYPE__VERSION, oldVersion, version, !oldVersionESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void unsetVersion() {
        BigDecimal oldVersion = version;
        boolean oldVersionESet = versionESet;
        version = ScxmlScxmlTypeImpl.VERSION_EDEFAULT;
        versionESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScxmlPackage.SCXML_SCXML_TYPE__VERSION, oldVersion, ScxmlScxmlTypeImpl.VERSION_EDEFAULT, oldVersionESet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSetVersion() {
        return versionESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getAnyAttribute() {
        if (anyAttribute == null) {
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_SCXML_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_SCXML_TYPE__SCXML_SCXML_MIX:
            return ((InternalEList<?>) getScxmlScxmlMix()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SCXML_TYPE__STATE:
            return ((InternalEList<?>) getState()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SCXML_TYPE__PARALLEL:
            return ((InternalEList<?>) getParallel()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SCXML_TYPE__FINAL:
            return ((InternalEList<?>) getFinal()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL:
            return ((InternalEList<?>) getDatamodel()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SCXML_TYPE__SCRIPT:
            return ((InternalEList<?>) getScript()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SCXML_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_SCXML_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SCXML_TYPE__SCXML_SCXML_MIX:
            if (coreType) {
                return getScxmlScxmlMix();
            }
            return ((FeatureMap.Internal) getScxmlScxmlMix()).getWrapper();
        case ScxmlPackage.SCXML_SCXML_TYPE__STATE:
            return getState();
        case ScxmlPackage.SCXML_SCXML_TYPE__PARALLEL:
            return getParallel();
        case ScxmlPackage.SCXML_SCXML_TYPE__FINAL:
            return getFinal();
        case ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL:
            return getDatamodel();
        case ScxmlPackage.SCXML_SCXML_TYPE__SCRIPT:
            return getScript();
        case ScxmlPackage.SCXML_SCXML_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_SCXML_TYPE__BINDING:
            return getBinding();
        case ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL1:
            return getDatamodel1();
        case ScxmlPackage.SCXML_SCXML_TYPE__EXMODE:
            return getExmode();
        case ScxmlPackage.SCXML_SCXML_TYPE__INITIAL:
            return getInitial();
        case ScxmlPackage.SCXML_SCXML_TYPE__NAME:
            return getName();
        case ScxmlPackage.SCXML_SCXML_TYPE__VERSION:
            return getVersion();
        case ScxmlPackage.SCXML_SCXML_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SCXML_TYPE__SCXML_SCXML_MIX:
            ((FeatureMap.Internal) getScxmlScxmlMix()).set(newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__STATE:
            getState().clear();
            getState().addAll((Collection<? extends ScxmlStateType>) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__PARALLEL:
            getParallel().clear();
            getParallel().addAll((Collection<? extends ScxmlParallelType>) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__FINAL:
            getFinal().clear();
            getFinal().addAll((Collection<? extends ScxmlFinalType>) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL:
            getDatamodel().clear();
            getDatamodel().addAll((Collection<? extends ScxmlDatamodelType>) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__SCRIPT:
            getScript().clear();
            getScript().addAll((Collection<? extends ScxmlScriptType>) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__BINDING:
            setBinding((BindingDatatype) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL1:
            setDatamodel1((String) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__EXMODE:
            setExmode((ExmodeDatatype) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__INITIAL:
            setInitial((List<String>) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__NAME:
            setName((String) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__VERSION:
            setVersion((BigDecimal) newValue);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SCXML_TYPE__SCXML_SCXML_MIX:
            getScxmlScxmlMix().clear();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__STATE:
            getState().clear();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__PARALLEL:
            getParallel().clear();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__FINAL:
            getFinal().clear();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL:
            getDatamodel().clear();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__SCRIPT:
            getScript().clear();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__BINDING:
            unsetBinding();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL1:
            unsetDatamodel1();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__EXMODE:
            unsetExmode();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__INITIAL:
            setInitial(ScxmlScxmlTypeImpl.INITIAL_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__NAME:
            setName(ScxmlScxmlTypeImpl.NAME_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__VERSION:
            unsetVersion();
            return;
        case ScxmlPackage.SCXML_SCXML_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_SCXML_TYPE__SCXML_SCXML_MIX:
            return scxmlScxmlMix != null && !scxmlScxmlMix.isEmpty();
        case ScxmlPackage.SCXML_SCXML_TYPE__STATE:
            return !getState().isEmpty();
        case ScxmlPackage.SCXML_SCXML_TYPE__PARALLEL:
            return !getParallel().isEmpty();
        case ScxmlPackage.SCXML_SCXML_TYPE__FINAL:
            return !getFinal().isEmpty();
        case ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL:
            return !getDatamodel().isEmpty();
        case ScxmlPackage.SCXML_SCXML_TYPE__SCRIPT:
            return !getScript().isEmpty();
        case ScxmlPackage.SCXML_SCXML_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_SCXML_TYPE__BINDING:
            return isSetBinding();
        case ScxmlPackage.SCXML_SCXML_TYPE__DATAMODEL1:
            return isSetDatamodel1();
        case ScxmlPackage.SCXML_SCXML_TYPE__EXMODE:
            return isSetExmode();
        case ScxmlPackage.SCXML_SCXML_TYPE__INITIAL:
            return ScxmlScxmlTypeImpl.INITIAL_EDEFAULT == null ? initial != null : !ScxmlScxmlTypeImpl.INITIAL_EDEFAULT.equals(initial);
        case ScxmlPackage.SCXML_SCXML_TYPE__NAME:
            return ScxmlScxmlTypeImpl.NAME_EDEFAULT == null ? name != null : !ScxmlScxmlTypeImpl.NAME_EDEFAULT.equals(name);
        case ScxmlPackage.SCXML_SCXML_TYPE__VERSION:
            return isSetVersion();
        case ScxmlPackage.SCXML_SCXML_TYPE__ANY_ATTRIBUTE:
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
        result.append(" (scxmlScxmlMix: "); //$NON-NLS-1$
        result.append(scxmlScxmlMix);
        result.append(", binding: "); //$NON-NLS-1$
        if (bindingESet) {
            result.append(binding);
        } else {
            result.append("<unset>"); //$NON-NLS-1$
        }
        result.append(", datamodel1: "); //$NON-NLS-1$
        if (datamodel1ESet) {
            result.append(datamodel1);
        } else {
            result.append("<unset>"); //$NON-NLS-1$
        }
        result.append(", exmode: "); //$NON-NLS-1$
        if (exmodeESet) {
            result.append(exmode);
        } else {
            result.append("<unset>"); //$NON-NLS-1$
        }
        result.append(", initial: "); //$NON-NLS-1$
        result.append(initial);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", version: "); //$NON-NLS-1$
        if (versionESet) {
            result.append(version);
        } else {
            result.append("<unset>"); //$NON-NLS-1$
        }
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlScxmlTypeImpl
