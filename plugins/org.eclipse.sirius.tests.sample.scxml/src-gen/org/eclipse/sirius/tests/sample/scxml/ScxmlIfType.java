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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>If Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScxmlCoreExecutablecontent
 * <em>Scxml Core Executablecontent</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAny <em>Any
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getRaise <em>
 * Raise</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getIf <em>If
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getForeach <em>
 * Foreach</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getSend <em>Send
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScript <em>
 * Script</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAssign <em>
 * Assign</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getLog <em>Log
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCancel <em>
 * Cancel</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getElseif <em>
 * Elseif</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScxmlCoreExecutablecontent1
 * <em>Scxml Core Executablecontent1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAny1 <em>Any1
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getRaise1 <em>
 * Raise1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getIf1 <em>If1
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getForeach1 <em>
 * Foreach1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getSend1 <em>
 * Send1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScript1 <em>
 * Script1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAssign1 <em>
 * Assign1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getLog1 <em>Log1
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCancel1 <em>
 * Cancel1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getElse <em>Else
 * </em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScxmlCoreExecutablecontent2
 * <em>Scxml Core Executablecontent2</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAny2 <em>Any2
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getRaise2 <em>
 * Raise2</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getIf2 <em>If2
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getForeach2 <em>
 * Foreach2</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getSend2 <em>
 * Send2</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getScript2 <em>
 * Script2</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAssign2 <em>
 * Assign2</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getLog2 <em>Log2
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCancel2 <em>
 * Cancel2</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCond <em>Cond
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType()
 * @model extendedMetaData="name='scxml.if.type' kind='elementOnly'"
 * @generated
 */
public interface ScxmlIfType extends EObject {
    /**
     * Returns the value of the '<em><b>Scxml Core Executablecontent</b></em>'
     * attribute list. The list contents are of type
     * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Scxml Core Executablecontent</em>' attribute
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Scxml Core Executablecontent</em>'
     *         attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_ScxmlCoreExecutablecontent()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true"
     *        extendedMetaData="kind='group' name='ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    FeatureMap getScxmlCoreExecutablecontent();

    /**
     * Returns the value of the '<em><b>Any</b></em>' attribute list. The list
     * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Any</em>' attribute list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Any</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Any()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##other' name=':1' processing='lax' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    FeatureMap getAny();

    /**
     * Returns the value of the '<em><b>Raise</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Raise</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Raise</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Raise()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='raise' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    EList<ScxmlRaiseType> getRaise();

    /**
     * Returns the value of the '<em><b>If</b></em>' containment reference list.
     * The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>If</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>If</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_If()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='if' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    EList<ScxmlIfType> getIf();

    /**
     * Returns the value of the '<em><b>Foreach</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Foreach</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Foreach</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Foreach()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='foreach' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    EList<ScxmlForeachType> getForeach();

    /**
     * Returns the value of the '<em><b>Send</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Send</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Send</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Send()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='send' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    EList<ScxmlSendType> getSend();

    /**
     * Returns the value of the '<em><b>Script</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Script</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Script</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Script()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='script' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    EList<ScxmlScriptType> getScript();

    /**
     * Returns the value of the '<em><b>Assign</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assign</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Assign</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Assign()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='assign' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    EList<ScxmlAssignType> getAssign();

    /**
     * Returns the value of the '<em><b>Log</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlLogType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Log</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Log</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Log()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='log' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    EList<ScxmlLogType> getLog();

    /**
     * Returns the value of the '<em><b>Cancel</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cancel</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Cancel</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Cancel()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='cancel' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    EList<ScxmlCancelType> getCancel();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Elseif()
     * @model containment="true" extendedMetaData=
     *        "kind='element' name='elseif' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlElseifType getElseif();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getElseif
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
     * Returns the value of the '<em><b>Scxml Core Executablecontent1</b></em>'
     * attribute list. The list contents are of type
     * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Scxml Core Executablecontent1</em>' attribute
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Scxml Core Executablecontent1</em>'
     *         attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_ScxmlCoreExecutablecontent1()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true"
     *        extendedMetaData="kind='group' name='ScxmlCoreExecutablecontent:11'"
     * @generated
     */
    FeatureMap getScxmlCoreExecutablecontent1();

    /**
     * Returns the value of the '<em><b>Any1</b></em>' attribute list. The list
     * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Any1</em>' attribute list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Any1</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Any1()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##other' name=':12' processing='lax' group='#ScxmlCoreExecutablecontent:11'"
     * @generated
     */
    FeatureMap getAny1();

    /**
     * Returns the value of the '<em><b>Raise1</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Raise1</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Raise1</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Raise1()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='raise' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:11'"
     * @generated
     */
    EList<ScxmlRaiseType> getRaise1();

    /**
     * Returns the value of the '<em><b>If1</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>If1</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>If1</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_If1()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='if' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:11'"
     * @generated
     */
    EList<ScxmlIfType> getIf1();

    /**
     * Returns the value of the '<em><b>Foreach1</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Foreach1</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Foreach1</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Foreach1()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='foreach' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:11'"
     * @generated
     */
    EList<ScxmlForeachType> getForeach1();

    /**
     * Returns the value of the '<em><b>Send1</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Send1</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Send1</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Send1()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='send' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:11'"
     * @generated
     */
    EList<ScxmlSendType> getSend1();

    /**
     * Returns the value of the '<em><b>Script1</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Script1</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Script1</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Script1()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='script' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:11'"
     * @generated
     */
    EList<ScxmlScriptType> getScript1();

    /**
     * Returns the value of the '<em><b>Assign1</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assign1</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Assign1</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Assign1()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='assign' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:11'"
     * @generated
     */
    EList<ScxmlAssignType> getAssign1();

    /**
     * Returns the value of the '<em><b>Log1</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlLogType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Log1</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Log1</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Log1()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='log' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:11'"
     * @generated
     */
    EList<ScxmlLogType> getLog1();

    /**
     * Returns the value of the '<em><b>Cancel1</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cancel1</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Cancel1</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Cancel1()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='cancel' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:11'"
     * @generated
     */
    EList<ScxmlCancelType> getCancel1();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Else()
     * @model containment="true" extendedMetaData=
     *        "kind='element' name='else' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlElseType getElse();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getElse
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
     * Returns the value of the '<em><b>Scxml Core Executablecontent2</b></em>'
     * attribute list. The list contents are of type
     * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Scxml Core Executablecontent2</em>' attribute
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Scxml Core Executablecontent2</em>'
     *         attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_ScxmlCoreExecutablecontent2()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true"
     *        extendedMetaData="kind='group' name='ScxmlCoreExecutablecontent:22'"
     * @generated
     */
    FeatureMap getScxmlCoreExecutablecontent2();

    /**
     * Returns the value of the '<em><b>Any2</b></em>' attribute list. The list
     * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Any2</em>' attribute list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Any2</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Any2()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##other' name=':23' processing='lax' group='#ScxmlCoreExecutablecontent:22'"
     * @generated
     */
    FeatureMap getAny2();

    /**
     * Returns the value of the '<em><b>Raise2</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Raise2</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Raise2</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Raise2()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='raise' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:22'"
     * @generated
     */
    EList<ScxmlRaiseType> getRaise2();

    /**
     * Returns the value of the '<em><b>If2</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>If2</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>If2</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_If2()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='if' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:22'"
     * @generated
     */
    EList<ScxmlIfType> getIf2();

    /**
     * Returns the value of the '<em><b>Foreach2</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Foreach2</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Foreach2</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Foreach2()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='foreach' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:22'"
     * @generated
     */
    EList<ScxmlForeachType> getForeach2();

    /**
     * Returns the value of the '<em><b>Send2</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Send2</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Send2</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Send2()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='send' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:22'"
     * @generated
     */
    EList<ScxmlSendType> getSend2();

    /**
     * Returns the value of the '<em><b>Script2</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Script2</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Script2</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Script2()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='script' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:22'"
     * @generated
     */
    EList<ScxmlScriptType> getScript2();

    /**
     * Returns the value of the '<em><b>Assign2</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assign2</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Assign2</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Assign2()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='assign' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:22'"
     * @generated
     */
    EList<ScxmlAssignType> getAssign2();

    /**
     * Returns the value of the '<em><b>Log2</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlLogType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Log2</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Log2</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Log2()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='log' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:22'"
     * @generated
     */
    EList<ScxmlLogType> getLog2();

    /**
     * Returns the value of the '<em><b>Cancel2</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cancel2</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Cancel2</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Cancel2()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='cancel' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:22'"
     * @generated
     */
    EList<ScxmlCancelType> getCancel2();

    /**
     * Returns the value of the '<em><b>Cond</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cond</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Cond</em>' attribute.
     * @see #setCond(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_Cond()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.CondLangDatatype"
     *        required="true" extendedMetaData="kind='attribute' name='cond'"
     * @generated
     */
    String getCond();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlIfType#getCond
     * <em>Cond</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Cond</em>' attribute.
     * @see #getCond()
     * @generated
     */
    void setCond(String value);

    /**
     * Returns the value of the '<em><b>Any Attribute</b></em>' attribute list.
     * The list contents are of type
     * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Any Attribute</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Any Attribute</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlIfType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':33' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlIfType
