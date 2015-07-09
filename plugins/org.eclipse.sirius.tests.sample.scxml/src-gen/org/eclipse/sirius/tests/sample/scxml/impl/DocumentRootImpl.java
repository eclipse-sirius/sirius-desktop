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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.scxml.DocumentRoot;
import org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlContentType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlElseType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlElseifType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlIfType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlLogType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParamType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlSendType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlStateType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Document Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getMixed
 * <em>Mixed</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getXMLNSPrefixMap
 * <em>XMLNS Prefix Map</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getXSISchemaLocation
 * <em>XSI Schema Location</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getAssign
 * <em>Assign</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getCancel
 * <em>Cancel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getContent
 * <em>Content</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getData
 * <em>Data</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getDatamodel
 * <em>Datamodel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getDonedata
 * <em>Donedata</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getElse
 * <em>Else</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getElseif
 * <em>Elseif</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getFinal
 * <em>Final</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getFinalize
 * <em>Finalize</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getForeach
 * <em>Foreach</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getHistory
 * <em>History</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getIf
 * <em>If</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getInitial
 * <em>Initial</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getInvoke
 * <em>Invoke</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getLog
 * <em>Log</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getOnentry
 * <em>Onentry</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getOnexit
 * <em>Onexit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getParallel
 * <em>Parallel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getParam
 * <em>Param</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getRaise
 * <em>Raise</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getScript
 * <em>Script</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getScxml
 * <em>Scxml</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getSend
 * <em>Send</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getState
 * <em>State</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.impl.DocumentRootImpl#getTransition
 * <em>Transition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends MinimalEObjectImpl.Container implements DocumentRoot {
    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap mixed;

    /**
     * The cached value of the '{@link #getXMLNSPrefixMap()
     * <em>XMLNS Prefix Map</em>}' map. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getXMLNSPrefixMap()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xMLNSPrefixMap;

    /**
     * The cached value of the '{@link #getXSISchemaLocation()
     * <em>XSI Schema Location</em>}' map. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getXSISchemaLocation()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xSISchemaLocation;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DocumentRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScxmlPackage.Literals.DOCUMENT_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, ScxmlPackage.DOCUMENT_ROOT__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EMap<String, String> getXMLNSPrefixMap() {
        if (xMLNSPrefixMap == null) {
            xMLNSPrefixMap = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, ScxmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        }
        return xMLNSPrefixMap;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EMap<String, String> getXSISchemaLocation() {
        if (xSISchemaLocation == null) {
            xSISchemaLocation = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this,
                    ScxmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        }
        return xSISchemaLocation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlAssignType getAssign() {
        return (ScxmlAssignType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__ASSIGN, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetAssign(ScxmlAssignType newAssign, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__ASSIGN, newAssign, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAssign(ScxmlAssignType newAssign) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__ASSIGN, newAssign);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlCancelType getCancel() {
        return (ScxmlCancelType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__CANCEL, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCancel(ScxmlCancelType newCancel, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__CANCEL, newCancel, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCancel(ScxmlCancelType newCancel) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__CANCEL, newCancel);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlContentType getContent() {
        return (ScxmlContentType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__CONTENT, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetContent(ScxmlContentType newContent, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__CONTENT, newContent, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setContent(ScxmlContentType newContent) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__CONTENT, newContent);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlDataType getData() {
        return (ScxmlDataType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__DATA, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetData(ScxmlDataType newData, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__DATA, newData, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setData(ScxmlDataType newData) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__DATA, newData);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlDatamodelType getDatamodel() {
        return (ScxmlDatamodelType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__DATAMODEL, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDatamodel(ScxmlDatamodelType newDatamodel, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__DATAMODEL, newDatamodel, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDatamodel(ScxmlDatamodelType newDatamodel) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__DATAMODEL, newDatamodel);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlDonedataType getDonedata() {
        return (ScxmlDonedataType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__DONEDATA, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDonedata(ScxmlDonedataType newDonedata, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__DONEDATA, newDonedata, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDonedata(ScxmlDonedataType newDonedata) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__DONEDATA, newDonedata);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlElseType getElse() {
        return (ScxmlElseType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__ELSE, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElse(ScxmlElseType newElse, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__ELSE, newElse, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setElse(ScxmlElseType newElse) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__ELSE, newElse);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlElseifType getElseif() {
        return (ScxmlElseifType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__ELSEIF, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElseif(ScxmlElseifType newElseif, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__ELSEIF, newElseif, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setElseif(ScxmlElseifType newElseif) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__ELSEIF, newElseif);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlFinalType getFinal() {
        return (ScxmlFinalType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__FINAL, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetFinal(ScxmlFinalType newFinal, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__FINAL, newFinal, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFinal(ScxmlFinalType newFinal) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__FINAL, newFinal);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlFinalizeType getFinalize() {
        return (ScxmlFinalizeType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__FINALIZE, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetFinalize(ScxmlFinalizeType newFinalize, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__FINALIZE, newFinalize, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFinalize(ScxmlFinalizeType newFinalize) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__FINALIZE, newFinalize);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlForeachType getForeach() {
        return (ScxmlForeachType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__FOREACH, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetForeach(ScxmlForeachType newForeach, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__FOREACH, newForeach, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setForeach(ScxmlForeachType newForeach) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__FOREACH, newForeach);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlHistoryType getHistory() {
        return (ScxmlHistoryType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__HISTORY, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetHistory(ScxmlHistoryType newHistory, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__HISTORY, newHistory, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHistory(ScxmlHistoryType newHistory) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__HISTORY, newHistory);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlIfType getIf() {
        return (ScxmlIfType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__IF, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetIf(ScxmlIfType newIf, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__IF, newIf, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIf(ScxmlIfType newIf) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__IF, newIf);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlInitialType getInitial() {
        return (ScxmlInitialType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__INITIAL, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInitial(ScxmlInitialType newInitial, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__INITIAL, newInitial, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInitial(ScxmlInitialType newInitial) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__INITIAL, newInitial);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlInvokeType getInvoke() {
        return (ScxmlInvokeType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__INVOKE, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInvoke(ScxmlInvokeType newInvoke, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__INVOKE, newInvoke, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInvoke(ScxmlInvokeType newInvoke) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__INVOKE, newInvoke);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlLogType getLog() {
        return (ScxmlLogType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__LOG, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetLog(ScxmlLogType newLog, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__LOG, newLog, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLog(ScxmlLogType newLog) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__LOG, newLog);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlOnentryType getOnentry() {
        return (ScxmlOnentryType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__ONENTRY, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOnentry(ScxmlOnentryType newOnentry, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__ONENTRY, newOnentry, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOnentry(ScxmlOnentryType newOnentry) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__ONENTRY, newOnentry);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlOnexitType getOnexit() {
        return (ScxmlOnexitType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__ONEXIT, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOnexit(ScxmlOnexitType newOnexit, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__ONEXIT, newOnexit, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOnexit(ScxmlOnexitType newOnexit) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__ONEXIT, newOnexit);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlParallelType getParallel() {
        return (ScxmlParallelType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__PARALLEL, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetParallel(ScxmlParallelType newParallel, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__PARALLEL, newParallel, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setParallel(ScxmlParallelType newParallel) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__PARALLEL, newParallel);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlParamType getParam() {
        return (ScxmlParamType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__PARAM, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetParam(ScxmlParamType newParam, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__PARAM, newParam, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setParam(ScxmlParamType newParam) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__PARAM, newParam);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlRaiseType getRaise() {
        return (ScxmlRaiseType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__RAISE, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetRaise(ScxmlRaiseType newRaise, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__RAISE, newRaise, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRaise(ScxmlRaiseType newRaise) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__RAISE, newRaise);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlScriptType getScript() {
        return (ScxmlScriptType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__SCRIPT, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetScript(ScxmlScriptType newScript, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__SCRIPT, newScript, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setScript(ScxmlScriptType newScript) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__SCRIPT, newScript);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlScxmlType getScxml() {
        return (ScxmlScxmlType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__SCXML, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetScxml(ScxmlScxmlType newScxml, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__SCXML, newScxml, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setScxml(ScxmlScxmlType newScxml) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__SCXML, newScxml);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlSendType getSend() {
        return (ScxmlSendType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__SEND, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSend(ScxmlSendType newSend, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__SEND, newSend, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSend(ScxmlSendType newSend) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__SEND, newSend);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlStateType getState() {
        return (ScxmlStateType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__STATE, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetState(ScxmlStateType newState, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__STATE, newState, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setState(ScxmlStateType newState) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__STATE, newState);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlTransitionType getTransition() {
        return (ScxmlTransitionType) getMixed().get(ScxmlPackage.Literals.DOCUMENT_ROOT__TRANSITION, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetTransition(ScxmlTransitionType newTransition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(ScxmlPackage.Literals.DOCUMENT_ROOT__TRANSITION, newTransition, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTransition(ScxmlTransitionType newTransition) {
        ((FeatureMap.Internal) getMixed()).set(ScxmlPackage.Literals.DOCUMENT_ROOT__TRANSITION, newTransition);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ScxmlPackage.DOCUMENT_ROOT__MIXED:
            return ((InternalEList<?>) getMixed()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            return ((InternalEList<?>) getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            return ((InternalEList<?>) getXSISchemaLocation()).basicRemove(otherEnd, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__ASSIGN:
            return basicSetAssign(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__CANCEL:
            return basicSetCancel(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__CONTENT:
            return basicSetContent(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__DATA:
            return basicSetData(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__DATAMODEL:
            return basicSetDatamodel(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__DONEDATA:
            return basicSetDonedata(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__ELSE:
            return basicSetElse(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__ELSEIF:
            return basicSetElseif(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__FINAL:
            return basicSetFinal(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__FINALIZE:
            return basicSetFinalize(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__FOREACH:
            return basicSetForeach(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__HISTORY:
            return basicSetHistory(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__IF:
            return basicSetIf(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__INITIAL:
            return basicSetInitial(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__INVOKE:
            return basicSetInvoke(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__LOG:
            return basicSetLog(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__ONENTRY:
            return basicSetOnentry(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__ONEXIT:
            return basicSetOnexit(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__PARALLEL:
            return basicSetParallel(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__PARAM:
            return basicSetParam(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__RAISE:
            return basicSetRaise(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__SCRIPT:
            return basicSetScript(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__SCXML:
            return basicSetScxml(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__SEND:
            return basicSetSend(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__STATE:
            return basicSetState(null, msgs);
        case ScxmlPackage.DOCUMENT_ROOT__TRANSITION:
            return basicSetTransition(null, msgs);
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
        case ScxmlPackage.DOCUMENT_ROOT__MIXED:
            if (coreType) {
                return getMixed();
            }
            return ((FeatureMap.Internal) getMixed()).getWrapper();
        case ScxmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            if (coreType) {
                return getXMLNSPrefixMap();
            } else {
                return getXMLNSPrefixMap().map();
            }
        case ScxmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            if (coreType) {
                return getXSISchemaLocation();
            } else {
                return getXSISchemaLocation().map();
            }
        case ScxmlPackage.DOCUMENT_ROOT__ASSIGN:
            return getAssign();
        case ScxmlPackage.DOCUMENT_ROOT__CANCEL:
            return getCancel();
        case ScxmlPackage.DOCUMENT_ROOT__CONTENT:
            return getContent();
        case ScxmlPackage.DOCUMENT_ROOT__DATA:
            return getData();
        case ScxmlPackage.DOCUMENT_ROOT__DATAMODEL:
            return getDatamodel();
        case ScxmlPackage.DOCUMENT_ROOT__DONEDATA:
            return getDonedata();
        case ScxmlPackage.DOCUMENT_ROOT__ELSE:
            return getElse();
        case ScxmlPackage.DOCUMENT_ROOT__ELSEIF:
            return getElseif();
        case ScxmlPackage.DOCUMENT_ROOT__FINAL:
            return getFinal();
        case ScxmlPackage.DOCUMENT_ROOT__FINALIZE:
            return getFinalize();
        case ScxmlPackage.DOCUMENT_ROOT__FOREACH:
            return getForeach();
        case ScxmlPackage.DOCUMENT_ROOT__HISTORY:
            return getHistory();
        case ScxmlPackage.DOCUMENT_ROOT__IF:
            return getIf();
        case ScxmlPackage.DOCUMENT_ROOT__INITIAL:
            return getInitial();
        case ScxmlPackage.DOCUMENT_ROOT__INVOKE:
            return getInvoke();
        case ScxmlPackage.DOCUMENT_ROOT__LOG:
            return getLog();
        case ScxmlPackage.DOCUMENT_ROOT__ONENTRY:
            return getOnentry();
        case ScxmlPackage.DOCUMENT_ROOT__ONEXIT:
            return getOnexit();
        case ScxmlPackage.DOCUMENT_ROOT__PARALLEL:
            return getParallel();
        case ScxmlPackage.DOCUMENT_ROOT__PARAM:
            return getParam();
        case ScxmlPackage.DOCUMENT_ROOT__RAISE:
            return getRaise();
        case ScxmlPackage.DOCUMENT_ROOT__SCRIPT:
            return getScript();
        case ScxmlPackage.DOCUMENT_ROOT__SCXML:
            return getScxml();
        case ScxmlPackage.DOCUMENT_ROOT__SEND:
            return getSend();
        case ScxmlPackage.DOCUMENT_ROOT__STATE:
            return getState();
        case ScxmlPackage.DOCUMENT_ROOT__TRANSITION:
            return getTransition();
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
        case ScxmlPackage.DOCUMENT_ROOT__MIXED:
            ((FeatureMap.Internal) getMixed()).set(newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            ((EStructuralFeature.Setting) getXMLNSPrefixMap()).set(newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            ((EStructuralFeature.Setting) getXSISchemaLocation()).set(newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__ASSIGN:
            setAssign((ScxmlAssignType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__CANCEL:
            setCancel((ScxmlCancelType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__CONTENT:
            setContent((ScxmlContentType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__DATA:
            setData((ScxmlDataType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__DATAMODEL:
            setDatamodel((ScxmlDatamodelType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__DONEDATA:
            setDonedata((ScxmlDonedataType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__ELSE:
            setElse((ScxmlElseType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__ELSEIF:
            setElseif((ScxmlElseifType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__FINAL:
            setFinal((ScxmlFinalType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__FINALIZE:
            setFinalize((ScxmlFinalizeType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__FOREACH:
            setForeach((ScxmlForeachType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__HISTORY:
            setHistory((ScxmlHistoryType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__IF:
            setIf((ScxmlIfType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__INITIAL:
            setInitial((ScxmlInitialType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__INVOKE:
            setInvoke((ScxmlInvokeType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__LOG:
            setLog((ScxmlLogType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__ONENTRY:
            setOnentry((ScxmlOnentryType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__ONEXIT:
            setOnexit((ScxmlOnexitType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__PARALLEL:
            setParallel((ScxmlParallelType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__PARAM:
            setParam((ScxmlParamType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__RAISE:
            setRaise((ScxmlRaiseType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__SCRIPT:
            setScript((ScxmlScriptType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__SCXML:
            setScxml((ScxmlScxmlType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__SEND:
            setSend((ScxmlSendType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__STATE:
            setState((ScxmlStateType) newValue);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__TRANSITION:
            setTransition((ScxmlTransitionType) newValue);
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
        case ScxmlPackage.DOCUMENT_ROOT__MIXED:
            getMixed().clear();
            return;
        case ScxmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            getXMLNSPrefixMap().clear();
            return;
        case ScxmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            getXSISchemaLocation().clear();
            return;
        case ScxmlPackage.DOCUMENT_ROOT__ASSIGN:
            setAssign((ScxmlAssignType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__CANCEL:
            setCancel((ScxmlCancelType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__CONTENT:
            setContent((ScxmlContentType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__DATA:
            setData((ScxmlDataType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__DATAMODEL:
            setDatamodel((ScxmlDatamodelType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__DONEDATA:
            setDonedata((ScxmlDonedataType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__ELSE:
            setElse((ScxmlElseType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__ELSEIF:
            setElseif((ScxmlElseifType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__FINAL:
            setFinal((ScxmlFinalType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__FINALIZE:
            setFinalize((ScxmlFinalizeType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__FOREACH:
            setForeach((ScxmlForeachType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__HISTORY:
            setHistory((ScxmlHistoryType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__IF:
            setIf((ScxmlIfType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__INITIAL:
            setInitial((ScxmlInitialType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__INVOKE:
            setInvoke((ScxmlInvokeType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__LOG:
            setLog((ScxmlLogType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__ONENTRY:
            setOnentry((ScxmlOnentryType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__ONEXIT:
            setOnexit((ScxmlOnexitType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__PARALLEL:
            setParallel((ScxmlParallelType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__PARAM:
            setParam((ScxmlParamType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__RAISE:
            setRaise((ScxmlRaiseType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__SCRIPT:
            setScript((ScxmlScriptType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__SCXML:
            setScxml((ScxmlScxmlType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__SEND:
            setSend((ScxmlSendType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__STATE:
            setState((ScxmlStateType) null);
            return;
        case ScxmlPackage.DOCUMENT_ROOT__TRANSITION:
            setTransition((ScxmlTransitionType) null);
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
        case ScxmlPackage.DOCUMENT_ROOT__MIXED:
            return mixed != null && !mixed.isEmpty();
        case ScxmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
        case ScxmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
        case ScxmlPackage.DOCUMENT_ROOT__ASSIGN:
            return getAssign() != null;
        case ScxmlPackage.DOCUMENT_ROOT__CANCEL:
            return getCancel() != null;
        case ScxmlPackage.DOCUMENT_ROOT__CONTENT:
            return getContent() != null;
        case ScxmlPackage.DOCUMENT_ROOT__DATA:
            return getData() != null;
        case ScxmlPackage.DOCUMENT_ROOT__DATAMODEL:
            return getDatamodel() != null;
        case ScxmlPackage.DOCUMENT_ROOT__DONEDATA:
            return getDonedata() != null;
        case ScxmlPackage.DOCUMENT_ROOT__ELSE:
            return getElse() != null;
        case ScxmlPackage.DOCUMENT_ROOT__ELSEIF:
            return getElseif() != null;
        case ScxmlPackage.DOCUMENT_ROOT__FINAL:
            return getFinal() != null;
        case ScxmlPackage.DOCUMENT_ROOT__FINALIZE:
            return getFinalize() != null;
        case ScxmlPackage.DOCUMENT_ROOT__FOREACH:
            return getForeach() != null;
        case ScxmlPackage.DOCUMENT_ROOT__HISTORY:
            return getHistory() != null;
        case ScxmlPackage.DOCUMENT_ROOT__IF:
            return getIf() != null;
        case ScxmlPackage.DOCUMENT_ROOT__INITIAL:
            return getInitial() != null;
        case ScxmlPackage.DOCUMENT_ROOT__INVOKE:
            return getInvoke() != null;
        case ScxmlPackage.DOCUMENT_ROOT__LOG:
            return getLog() != null;
        case ScxmlPackage.DOCUMENT_ROOT__ONENTRY:
            return getOnentry() != null;
        case ScxmlPackage.DOCUMENT_ROOT__ONEXIT:
            return getOnexit() != null;
        case ScxmlPackage.DOCUMENT_ROOT__PARALLEL:
            return getParallel() != null;
        case ScxmlPackage.DOCUMENT_ROOT__PARAM:
            return getParam() != null;
        case ScxmlPackage.DOCUMENT_ROOT__RAISE:
            return getRaise() != null;
        case ScxmlPackage.DOCUMENT_ROOT__SCRIPT:
            return getScript() != null;
        case ScxmlPackage.DOCUMENT_ROOT__SCXML:
            return getScxml() != null;
        case ScxmlPackage.DOCUMENT_ROOT__SEND:
            return getSend() != null;
        case ScxmlPackage.DOCUMENT_ROOT__STATE:
            return getState() != null;
        case ScxmlPackage.DOCUMENT_ROOT__TRANSITION:
            return getTransition() != null;
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
        result.append(" (mixed: "); //$NON-NLS-1$
        result.append(mixed);
        result.append(')');
        return result.toString();
    }

} // DocumentRootImpl
