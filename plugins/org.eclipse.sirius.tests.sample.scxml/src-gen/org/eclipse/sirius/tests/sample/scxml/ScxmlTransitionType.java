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

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Transition Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getScxmlCoreExecutablecontent
 * <em>Scxml Core Executablecontent</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getAny
 * <em>Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getRaise
 * <em>Raise</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getIf
 * <em>If</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getForeach
 * <em>Foreach</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getSend
 * <em>Send</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getScript
 * <em>Script</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getAssign
 * <em>Assign</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getLog
 * <em>Log</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getCancel
 * <em>Cancel</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getCond
 * <em>Cond</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getEvent
 * <em>Event</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getTarget
 * <em>Target</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getType
 * <em>Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType()
 * @model extendedMetaData="name='scxml.transition.type' kind='elementOnly'"
 * @generated
 */
public interface ScxmlTransitionType extends EObject {
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_ScxmlCoreExecutablecontent()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Any()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Raise()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_If()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Foreach()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Send()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Script()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Assign()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Log()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Cancel()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='cancel' namespace='##targetNamespace' group='#ScxmlCoreExecutablecontent:0'"
     * @generated
     */
    EList<ScxmlCancelType> getCancel();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Cond()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.CondLangDatatype"
     *        extendedMetaData="kind='attribute' name='cond'"
     * @generated
     */
    String getCond();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getCond
     * <em>Cond</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Cond</em>' attribute.
     * @see #getCond()
     * @generated
     */
    void setCond(String value);

    /**
     * Returns the value of the '<em><b>Event</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Event</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Event</em>' attribute.
     * @see #setEvent(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Event()
     * @model
     *        dataType="org.eclipse.sirius.tests.sample.scxml.EventTypesDatatype"
     *        extendedMetaData="kind='attribute' name='event'"
     * @generated
     */
    String getEvent();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getEvent
     * <em>Event</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Event</em>' attribute.
     * @see #getEvent()
     * @generated
     */
    void setEvent(String value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Target</em>' attribute.
     * @see #setTarget(List)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Target()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREFS" many="false"
     *        extendedMetaData="kind='attribute' name='target'"
     * @generated
     */
    List<String> getTarget();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getTarget
     * <em>Target</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Target</em>' attribute.
     * @see #getTarget()
     * @generated
     */
    void setTarget(List<String> value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. The literals
     * are from the enumeration
     * {@link org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
     * @see #isSetType()
     * @see #unsetType()
     * @see #setType(TransitionTypeDatatype)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_Type()
     * @model unsettable="true" extendedMetaData="kind='attribute' name='type'"
     * @generated
     */
    TransitionTypeDatatype getType();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getType
     * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Type</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype
     * @see #isSetType()
     * @see #unsetType()
     * @see #getType()
     * @generated
     */
    void setType(TransitionTypeDatatype value);

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getType
     * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isSetType()
     * @see #getType()
     * @see #setType(TransitionTypeDatatype)
     * @generated
     */
    void unsetType();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType#getType
     * <em>Type</em>}' attribute is set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return whether the value of the '<em>Type</em>' attribute is set.
     * @see #unsetType()
     * @see #getType()
     * @see #setType(TransitionTypeDatatype)
     * @generated
     */
    boolean isSetType();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlTransitionType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':14' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlTransitionType
