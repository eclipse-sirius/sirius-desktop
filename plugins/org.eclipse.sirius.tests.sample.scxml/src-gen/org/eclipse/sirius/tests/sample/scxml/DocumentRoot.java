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
package org.eclipse.sirius.tests.sample.scxml;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Document Root</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getMixed <em>
 * Mixed</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getXMLNSPrefixMap
 * <em>XMLNS Prefix Map</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getXSISchemaLocation
 * <em>XSI Schema Location</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getAssign <em>
 * Assign</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getCancel <em>
 * Cancel</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getContent <em>
 * Content</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getData <em>
 * Data</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getDatamodel
 * <em>Datamodel</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getDonedata
 * <em>Donedata</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getElse <em>
 * Else</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getElseif <em>
 * Elseif</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getFinal <em>
 * Final</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getFinalize
 * <em>Finalize</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getForeach <em>
 * Foreach</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getHistory <em>
 * History</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getIf <em>If
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getInitial <em>
 * Initial</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getInvoke <em>
 * Invoke</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getLog <em>Log
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getOnentry <em>
 * Onentry</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getOnexit <em>
 * Onexit</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getParallel
 * <em>Parallel</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getParam <em>
 * Param</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getRaise <em>
 * Raise</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getScript <em>
 * Script</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getScxml <em>
 * Scxml</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getSend <em>
 * Send</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getState <em>
 * State</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getTransition
 * <em>Transition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list. The list
     * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mixed</em>' attribute list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map. The key
     * is of type {@link java.lang.String}, and the value is of type
     * {@link java.lang.String}, <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>XMLNS Prefix Map</em>' map.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_XMLNSPrefixMap()
     * @model mapType=
     *        "org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
     *        transient="true"
     *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
     * @generated
     */
    EMap<String, String> getXMLNSPrefixMap();

    /**
     * Returns the value of the '<em><b>XSI Schema Location</b></em>' map. The
     * key is of type {@link java.lang.String}, and the value is of type
     * {@link java.lang.String}, <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>XSI Schema Location</em>' map.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_XSISchemaLocation()
     * @model mapType=
     *        "org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
     *        transient="true"
     *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
     * @generated
     */
    EMap<String, String> getXSISchemaLocation();

    /**
     * Returns the value of the '<em><b>Assign</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assign</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Assign</em>' containment reference.
     * @see #setAssign(ScxmlAssignType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Assign()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='assign' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlAssignType getAssign();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getAssign
     * <em>Assign</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Assign</em>' containment reference.
     * @see #getAssign()
     * @generated
     */
    void setAssign(ScxmlAssignType value);

    /**
     * Returns the value of the '<em><b>Cancel</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cancel</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Cancel</em>' containment reference.
     * @see #setCancel(ScxmlCancelType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Cancel()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='cancel' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlCancelType getCancel();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getCancel
     * <em>Cancel</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Cancel</em>' containment reference.
     * @see #getCancel()
     * @generated
     */
    void setCancel(ScxmlCancelType value);

    /**
     * Returns the value of the '<em><b>Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Content</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Content</em>' containment reference.
     * @see #setContent(ScxmlContentType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Content()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='content' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlContentType getContent();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getContent
     * <em>Content</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Content</em>' containment reference.
     * @see #getContent()
     * @generated
     */
    void setContent(ScxmlContentType value);

    /**
     * Returns the value of the '<em><b>Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Data</em>' containment reference.
     * @see #setData(ScxmlDataType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Data()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlDataType getData();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getData
     * <em>Data</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Data</em>' containment reference.
     * @see #getData()
     * @generated
     */
    void setData(ScxmlDataType value);

    /**
     * Returns the value of the '<em><b>Datamodel</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Datamodel</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Datamodel</em>' containment reference.
     * @see #setDatamodel(ScxmlDatamodelType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Datamodel()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='datamodel' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlDatamodelType getDatamodel();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getDatamodel
     * <em>Datamodel</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Datamodel</em>' containment
     *            reference.
     * @see #getDatamodel()
     * @generated
     */
    void setDatamodel(ScxmlDatamodelType value);

    /**
     * Returns the value of the '<em><b>Donedata</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Donedata</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Donedata</em>' containment reference.
     * @see #setDonedata(ScxmlDonedataType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Donedata()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='donedata' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlDonedataType getDonedata();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getDonedata
     * <em>Donedata</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Donedata</em>' containment
     *            reference.
     * @see #getDonedata()
     * @generated
     */
    void setDonedata(ScxmlDonedataType value);

    /**
     * Returns the value of the '<em><b>Else</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Else</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Else</em>' containment reference.
     * @see #setElse(ScxmlElseType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Else()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='else' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlElseType getElse();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getElse
     * <em>Else</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Else</em>' containment reference.
     * @see #getElse()
     * @generated
     */
    void setElse(ScxmlElseType value);

    /**
     * Returns the value of the '<em><b>Elseif</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Elseif</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Elseif</em>' containment reference.
     * @see #setElseif(ScxmlElseifType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Elseif()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='elseif' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlElseifType getElseif();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getElseif
     * <em>Elseif</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Elseif</em>' containment reference.
     * @see #getElseif()
     * @generated
     */
    void setElseif(ScxmlElseifType value);

    /**
     * Returns the value of the '<em><b>Final</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Final</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Final</em>' containment reference.
     * @see #setFinal(ScxmlFinalType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Final()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='final' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlFinalType getFinal();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getFinal
     * <em>Final</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Final</em>' containment reference.
     * @see #getFinal()
     * @generated
     */
    void setFinal(ScxmlFinalType value);

    /**
     * Returns the value of the '<em><b>Finalize</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Finalize</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Finalize</em>' containment reference.
     * @see #setFinalize(ScxmlFinalizeType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Finalize()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='finalize' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlFinalizeType getFinalize();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getFinalize
     * <em>Finalize</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Finalize</em>' containment
     *            reference.
     * @see #getFinalize()
     * @generated
     */
    void setFinalize(ScxmlFinalizeType value);

    /**
     * Returns the value of the '<em><b>Foreach</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Foreach</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Foreach</em>' containment reference.
     * @see #setForeach(ScxmlForeachType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Foreach()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='foreach' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlForeachType getForeach();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getForeach
     * <em>Foreach</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Foreach</em>' containment reference.
     * @see #getForeach()
     * @generated
     */
    void setForeach(ScxmlForeachType value);

    /**
     * Returns the value of the '<em><b>History</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>History</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>History</em>' containment reference.
     * @see #setHistory(ScxmlHistoryType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_History()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='history' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlHistoryType getHistory();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getHistory
     * <em>History</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>History</em>' containment reference.
     * @see #getHistory()
     * @generated
     */
    void setHistory(ScxmlHistoryType value);

    /**
     * Returns the value of the '<em><b>If</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>If</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>If</em>' containment reference.
     * @see #setIf(ScxmlIfType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_If()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='if' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlIfType getIf();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getIf
     * <em>If</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>If</em>' containment reference.
     * @see #getIf()
     * @generated
     */
    void setIf(ScxmlIfType value);

    /**
     * Returns the value of the '<em><b>Initial</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Initial</em>' containment reference.
     * @see #setInitial(ScxmlInitialType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Initial()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='initial' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlInitialType getInitial();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getInitial
     * <em>Initial</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial</em>' containment reference.
     * @see #getInitial()
     * @generated
     */
    void setInitial(ScxmlInitialType value);

    /**
     * Returns the value of the '<em><b>Invoke</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Invoke</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Invoke</em>' containment reference.
     * @see #setInvoke(ScxmlInvokeType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Invoke()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='invoke' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlInvokeType getInvoke();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getInvoke
     * <em>Invoke</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Invoke</em>' containment reference.
     * @see #getInvoke()
     * @generated
     */
    void setInvoke(ScxmlInvokeType value);

    /**
     * Returns the value of the '<em><b>Log</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Log</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Log</em>' containment reference.
     * @see #setLog(ScxmlLogType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Log()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='log' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlLogType getLog();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getLog
     * <em>Log</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Log</em>' containment reference.
     * @see #getLog()
     * @generated
     */
    void setLog(ScxmlLogType value);

    /**
     * Returns the value of the '<em><b>Onentry</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Onentry</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Onentry</em>' containment reference.
     * @see #setOnentry(ScxmlOnentryType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Onentry()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='onentry' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlOnentryType getOnentry();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getOnentry
     * <em>Onentry</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Onentry</em>' containment reference.
     * @see #getOnentry()
     * @generated
     */
    void setOnentry(ScxmlOnentryType value);

    /**
     * Returns the value of the '<em><b>Onexit</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Onexit</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Onexit</em>' containment reference.
     * @see #setOnexit(ScxmlOnexitType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Onexit()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='onexit' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlOnexitType getOnexit();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getOnexit
     * <em>Onexit</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Onexit</em>' containment reference.
     * @see #getOnexit()
     * @generated
     */
    void setOnexit(ScxmlOnexitType value);

    /**
     * Returns the value of the '<em><b>Parallel</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parallel</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Parallel</em>' containment reference.
     * @see #setParallel(ScxmlParallelType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Parallel()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='parallel' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlParallelType getParallel();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getParallel
     * <em>Parallel</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Parallel</em>' containment
     *            reference.
     * @see #getParallel()
     * @generated
     */
    void setParallel(ScxmlParallelType value);

    /**
     * Returns the value of the '<em><b>Param</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Param</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Param</em>' containment reference.
     * @see #setParam(ScxmlParamType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Param()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='param' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlParamType getParam();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getParam
     * <em>Param</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Param</em>' containment reference.
     * @see #getParam()
     * @generated
     */
    void setParam(ScxmlParamType value);

    /**
     * Returns the value of the '<em><b>Raise</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Raise</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Raise</em>' containment reference.
     * @see #setRaise(ScxmlRaiseType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Raise()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='raise' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlRaiseType getRaise();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getRaise
     * <em>Raise</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Raise</em>' containment reference.
     * @see #getRaise()
     * @generated
     */
    void setRaise(ScxmlRaiseType value);

    /**
     * Returns the value of the '<em><b>Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Script</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Script</em>' containment reference.
     * @see #setScript(ScxmlScriptType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Script()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='script' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlScriptType getScript();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getScript
     * <em>Script</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Script</em>' containment reference.
     * @see #getScript()
     * @generated
     */
    void setScript(ScxmlScriptType value);

    /**
     * Returns the value of the '<em><b>Scxml</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scxml</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Scxml</em>' containment reference.
     * @see #setScxml(ScxmlScxmlType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Scxml()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='scxml' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlScxmlType getScxml();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getScxml
     * <em>Scxml</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Scxml</em>' containment reference.
     * @see #getScxml()
     * @generated
     */
    void setScxml(ScxmlScxmlType value);

    /**
     * Returns the value of the '<em><b>Send</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Send</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Send</em>' containment reference.
     * @see #setSend(ScxmlSendType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Send()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='send' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlSendType getSend();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getSend
     * <em>Send</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Send</em>' containment reference.
     * @see #getSend()
     * @generated
     */
    void setSend(ScxmlSendType value);

    /**
     * Returns the value of the '<em><b>State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>State</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>State</em>' containment reference.
     * @see #setState(ScxmlStateType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_State()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='state' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlStateType getState();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getState
     * <em>State</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>State</em>' containment reference.
     * @see #getState()
     * @generated
     */
    void setState(ScxmlStateType value);

    /**
     * Returns the value of the '<em><b>Transition</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transition</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Transition</em>' containment reference.
     * @see #setTransition(ScxmlTransitionType)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getDocumentRoot_Transition()
     * @model containment="true" upper="-2" transient="true" volatile="true"
     *        derived="true" extendedMetaData=
     *        "kind='element' name='transition' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlTransitionType getTransition();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.DocumentRoot#getTransition
     * <em>Transition</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Transition</em>' containment
     *            reference.
     * @see #getTransition()
     * @generated
     */
    void setTransition(ScxmlTransitionType value);

} // DocumentRoot
