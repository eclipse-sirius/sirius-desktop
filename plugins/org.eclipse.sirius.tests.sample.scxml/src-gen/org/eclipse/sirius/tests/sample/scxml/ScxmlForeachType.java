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
 * <em><b>Foreach Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getScxmlCoreExecutablecontent
 * <em>Scxml Core Executablecontent</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getAny <em>
 * Any</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getRaise
 * <em>Raise</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getIf <em>
 * If</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getForeach
 * <em>Foreach</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getSend
 * <em>Send</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getScript
 * <em>Script</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getAssign
 * <em>Assign</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getLog <em>
 * Log</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getCancel
 * <em>Cancel</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getArray
 * <em>Array</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getIndex
 * <em>Index</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getItem
 * <em>Item</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType()
 * @model extendedMetaData="name='scxml.foreach.type' kind='elementOnly'"
 * @generated
 */
public interface ScxmlForeachType extends EObject {
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_ScxmlCoreExecutablecontent()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Any()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Raise()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_If()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Foreach()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Send()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Script()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Assign()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Log()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Cancel()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='cancel' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    EList<ScxmlCancelType> getCancel();

    /**
     * Returns the value of the '<em><b>Array</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Array</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Array</em>' attribute.
     * @see #setArray(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Array()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.ValueLangDatatype"
     *        required="true" extendedMetaData="kind='attribute' name='array'"
     * @generated
     */
    String getArray();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getArray
     * <em>Array</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Array</em>' attribute.
     * @see #getArray()
     * @generated
     */
    void setArray(String value);

    /**
     * Returns the value of the '<em><b>Index</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Index</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Index</em>' attribute.
     * @see #setIndex(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Index()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='index'"
     * @generated
     */
    String getIndex();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getIndex
     * <em>Index</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Index</em>' attribute.
     * @see #getIndex()
     * @generated
     */
    void setIndex(String value);

    /**
     * Returns the value of the '<em><b>Item</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Item</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Item</em>' attribute.
     * @see #setItem(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_Item()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='item'"
     * @generated
     */
    String getItem();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType#getItem
     * <em>Item</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Item</em>' attribute.
     * @see #getItem()
     * @generated
     */
    void setItem(String value);

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlForeachType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':13' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlForeachType
