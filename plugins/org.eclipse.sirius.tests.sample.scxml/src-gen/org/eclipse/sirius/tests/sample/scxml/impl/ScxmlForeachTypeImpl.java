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
import org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlIfType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlLogType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlSendType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Foreach Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getScxmlCoreExecutablecontent
 * <em>Scxml Core Executablecontent</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getRaise
 * <em>Raise</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getIf
 * <em>If</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getForeach
 * <em>Foreach</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getSend
 * <em>Send</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getScript
 * <em>Script</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getAssign
 * <em>Assign</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getLog
 * <em>Log</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getCancel
 * <em>Cancel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getArray
 * <em>Array</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getIndex
 * <em>Index</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getItem
 * <em>Item</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.ScxmlForeachTypeImpl#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScxmlForeachTypeImpl extends MinimalEObjectImpl.Container implements ScxmlForeachType {
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
     * The default value of the '{@link #getArray() <em>Array</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getArray()
     * @generated
     * @ordered
     */
    protected static final String ARRAY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getArray() <em>Array</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getArray()
     * @generated
     * @ordered
     */
    protected String array = ScxmlForeachTypeImpl.ARRAY_EDEFAULT;

    /**
     * The default value of the '{@link #getIndex() <em>Index</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIndex()
     * @generated
     * @ordered
     */
    protected static final String INDEX_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIndex() <em>Index</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIndex()
     * @generated
     * @ordered
     */
    protected String index = ScxmlForeachTypeImpl.INDEX_EDEFAULT;

    /**
     * The default value of the '{@link #getItem() <em>Item</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getItem()
     * @generated
     * @ordered
     */
    protected static final String ITEM_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getItem() <em>Item</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getItem()
     * @generated
     * @ordered
     */
    protected String item = ScxmlForeachTypeImpl.ITEM_EDEFAULT;

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
    protected ScxmlForeachTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.SCXML_FOREACH_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getScxmlCoreExecutablecontent() {
        if (scxmlCoreExecutablecontent == null) {
            scxmlCoreExecutablecontent = new BasicFeatureMap(this, ScxmlPackage.SCXML_FOREACH_TYPE__SCXML_CORE_EXECUTABLECONTENT);
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
        return (FeatureMap) getScxmlCoreExecutablecontent().<FeatureMap.Entry> list(ScxmlPackage.Literals.SCXML_FOREACH_TYPE__ANY);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlRaiseType> getRaise() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_FOREACH_TYPE__RAISE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlIfType> getIf() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_FOREACH_TYPE__IF);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlForeachType> getForeach() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_FOREACH_TYPE__FOREACH);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlSendType> getSend() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_FOREACH_TYPE__SEND);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlScriptType> getScript() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_FOREACH_TYPE__SCRIPT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlAssignType> getAssign() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_FOREACH_TYPE__ASSIGN);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlLogType> getLog() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_FOREACH_TYPE__LOG);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ScxmlCancelType> getCancel() {
        return getScxmlCoreExecutablecontent().list(ScxmlPackage.Literals.SCXML_FOREACH_TYPE__CANCEL);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getArray() {
        return array;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setArray(String newArray) {
        String oldArray = array;
        array = newArray;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_FOREACH_TYPE__ARRAY, oldArray, array));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIndex() {
        return index;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIndex(String newIndex) {
        String oldIndex = index;
        index = newIndex;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_FOREACH_TYPE__INDEX, oldIndex, index));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getItem() {
        return item;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setItem(String newItem) {
        String oldItem = item;
        item = newItem;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScxmlPackage.SCXML_FOREACH_TYPE__ITEM, oldItem, item));
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
            anyAttribute = new BasicFeatureMap(this, ScxmlPackage.SCXML_FOREACH_TYPE__ANY_ATTRIBUTE);
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
        case ScxmlPackage.SCXML_FOREACH_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            return ((InternalEList<?>) getScxmlCoreExecutablecontent()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FOREACH_TYPE__ANY:
            return ((InternalEList<?>) getAny()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FOREACH_TYPE__RAISE:
            return ((InternalEList<?>) getRaise()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FOREACH_TYPE__IF:
            return ((InternalEList<?>) getIf()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FOREACH_TYPE__FOREACH:
            return ((InternalEList<?>) getForeach()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FOREACH_TYPE__SEND:
            return ((InternalEList<?>) getSend()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FOREACH_TYPE__SCRIPT:
            return ((InternalEList<?>) getScript()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FOREACH_TYPE__ASSIGN:
            return ((InternalEList<?>) getAssign()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FOREACH_TYPE__LOG:
            return ((InternalEList<?>) getLog()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FOREACH_TYPE__CANCEL:
            return ((InternalEList<?>) getCancel()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.SCXML_FOREACH_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_FOREACH_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            if (coreType) {
                return getScxmlCoreExecutablecontent();
            }
            return ((FeatureMap.Internal) getScxmlCoreExecutablecontent()).getWrapper();
        case ScxmlPackage.SCXML_FOREACH_TYPE__ANY:
            if (coreType) {
                return getAny();
            }
            return ((FeatureMap.Internal) getAny()).getWrapper();
        case ScxmlPackage.SCXML_FOREACH_TYPE__RAISE:
            return getRaise();
        case ScxmlPackage.SCXML_FOREACH_TYPE__IF:
            return getIf();
        case ScxmlPackage.SCXML_FOREACH_TYPE__FOREACH:
            return getForeach();
        case ScxmlPackage.SCXML_FOREACH_TYPE__SEND:
            return getSend();
        case ScxmlPackage.SCXML_FOREACH_TYPE__SCRIPT:
            return getScript();
        case ScxmlPackage.SCXML_FOREACH_TYPE__ASSIGN:
            return getAssign();
        case ScxmlPackage.SCXML_FOREACH_TYPE__LOG:
            return getLog();
        case ScxmlPackage.SCXML_FOREACH_TYPE__CANCEL:
            return getCancel();
        case ScxmlPackage.SCXML_FOREACH_TYPE__ARRAY:
            return getArray();
        case ScxmlPackage.SCXML_FOREACH_TYPE__INDEX:
            return getIndex();
        case ScxmlPackage.SCXML_FOREACH_TYPE__ITEM:
            return getItem();
        case ScxmlPackage.SCXML_FOREACH_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_FOREACH_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            ((FeatureMap.Internal) getScxmlCoreExecutablecontent()).set(newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__ANY:
            ((FeatureMap.Internal) getAny()).set(newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__RAISE:
            getRaise().clear();
            getRaise().addAll((Collection<? extends ScxmlRaiseType>) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__IF:
            getIf().clear();
            getIf().addAll((Collection<? extends ScxmlIfType>) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__FOREACH:
            getForeach().clear();
            getForeach().addAll((Collection<? extends ScxmlForeachType>) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__SEND:
            getSend().clear();
            getSend().addAll((Collection<? extends ScxmlSendType>) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__SCRIPT:
            getScript().clear();
            getScript().addAll((Collection<? extends ScxmlScriptType>) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__ASSIGN:
            getAssign().clear();
            getAssign().addAll((Collection<? extends ScxmlAssignType>) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__LOG:
            getLog().clear();
            getLog().addAll((Collection<? extends ScxmlLogType>) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__CANCEL:
            getCancel().clear();
            getCancel().addAll((Collection<? extends ScxmlCancelType>) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__ARRAY:
            setArray((String) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__INDEX:
            setIndex((String) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__ITEM:
            setItem((String) newValue);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_FOREACH_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            getScxmlCoreExecutablecontent().clear();
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__ANY:
            getAny().clear();
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__RAISE:
            getRaise().clear();
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__IF:
            getIf().clear();
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__FOREACH:
            getForeach().clear();
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__SEND:
            getSend().clear();
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__SCRIPT:
            getScript().clear();
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__ASSIGN:
            getAssign().clear();
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__LOG:
            getLog().clear();
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__CANCEL:
            getCancel().clear();
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__ARRAY:
            setArray(ScxmlForeachTypeImpl.ARRAY_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__INDEX:
            setIndex(ScxmlForeachTypeImpl.INDEX_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__ITEM:
            setItem(ScxmlForeachTypeImpl.ITEM_EDEFAULT);
            return;
        case ScxmlPackage.SCXML_FOREACH_TYPE__ANY_ATTRIBUTE:
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
        case ScxmlPackage.SCXML_FOREACH_TYPE__SCXML_CORE_EXECUTABLECONTENT:
            return scxmlCoreExecutablecontent != null && !scxmlCoreExecutablecontent.isEmpty();
        case ScxmlPackage.SCXML_FOREACH_TYPE__ANY:
            return !getAny().isEmpty();
        case ScxmlPackage.SCXML_FOREACH_TYPE__RAISE:
            return !getRaise().isEmpty();
        case ScxmlPackage.SCXML_FOREACH_TYPE__IF:
            return !getIf().isEmpty();
        case ScxmlPackage.SCXML_FOREACH_TYPE__FOREACH:
            return !getForeach().isEmpty();
        case ScxmlPackage.SCXML_FOREACH_TYPE__SEND:
            return !getSend().isEmpty();
        case ScxmlPackage.SCXML_FOREACH_TYPE__SCRIPT:
            return !getScript().isEmpty();
        case ScxmlPackage.SCXML_FOREACH_TYPE__ASSIGN:
            return !getAssign().isEmpty();
        case ScxmlPackage.SCXML_FOREACH_TYPE__LOG:
            return !getLog().isEmpty();
        case ScxmlPackage.SCXML_FOREACH_TYPE__CANCEL:
            return !getCancel().isEmpty();
        case ScxmlPackage.SCXML_FOREACH_TYPE__ARRAY:
            return ScxmlForeachTypeImpl.ARRAY_EDEFAULT == null ? array != null : !ScxmlForeachTypeImpl.ARRAY_EDEFAULT.equals(array);
        case ScxmlPackage.SCXML_FOREACH_TYPE__INDEX:
            return ScxmlForeachTypeImpl.INDEX_EDEFAULT == null ? index != null : !ScxmlForeachTypeImpl.INDEX_EDEFAULT.equals(index);
        case ScxmlPackage.SCXML_FOREACH_TYPE__ITEM:
            return ScxmlForeachTypeImpl.ITEM_EDEFAULT == null ? item != null : !ScxmlForeachTypeImpl.ITEM_EDEFAULT.equals(item);
        case ScxmlPackage.SCXML_FOREACH_TYPE__ANY_ATTRIBUTE:
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
        result.append(", array: "); //$NON-NLS-1$
        result.append(array);
        result.append(", index: "); //$NON-NLS-1$
        result.append(index);
        result.append(", item: "); //$NON-NLS-1$
        result.append(item);
        result.append(", anyAttribute: "); //$NON-NLS-1$
        result.append(anyAttribute);
        result.append(')');
        return result.toString();
    }

} // ScxmlForeachTypeImpl
