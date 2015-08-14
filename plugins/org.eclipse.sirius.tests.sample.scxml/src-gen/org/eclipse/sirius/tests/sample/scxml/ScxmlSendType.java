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
 * <em><b>Send Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getScxmlSendMix
 * <em>Scxml Send Mix</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getContent
 * <em>Content</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getParam <em>
 * Param</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getAny <em>Any
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getDelay <em>
 * Delay</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getDelayexpr
 * <em>Delayexpr</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getEvent <em>
 * Event</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getEventexpr
 * <em>Eventexpr</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getId <em>Id
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getIdlocation
 * <em>Idlocation</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getNamelist
 * <em>Namelist</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTarget <em>
 * Target</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTargetexpr
 * <em>Targetexpr</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getType <em>
 * Type</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTypeexpr
 * <em>Typeexpr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType()
 * @model extendedMetaData="name='scxml.send.type' kind='elementOnly'"
 * @generated
 */
public interface ScxmlSendType extends EObject {
    /**
     * Returns the value of the '<em><b>Scxml Send Mix</b></em>' attribute list.
     * The list contents are of type
     * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Scxml Send Mix</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Scxml Send Mix</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_ScxmlSendMix()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData="kind='group' name='ScxmlSendMix:0'"
     * @generated
     */
    FeatureMap getScxmlSendMix();

    /**
     * Returns the value of the '<em><b>Content</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlContentType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Content</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Content</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Content()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='content' namespace='##targetNamespace' group='#ScxmlSendMix:0'"
     * @generated
     */
    EList<ScxmlContentType> getContent();

    /**
     * Returns the value of the '<em><b>Param</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParamType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Param</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Param</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Param()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='param' namespace='##targetNamespace' group='#ScxmlSendMix:0'"
     * @generated
     */
    EList<ScxmlParamType> getParam();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Any()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##other' name=':3' processing='lax' group='#ScxmlSendMix:0'"
     * @generated
     */
    FeatureMap getAny();

    /**
     * Returns the value of the '<em><b>Delay</b></em>' attribute. The default
     * value is <code>"0s"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Delay</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Delay</em>' attribute.
     * @see #isSetDelay()
     * @see #unsetDelay()
     * @see #setDelay(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Delay()
     * @model default="0s" unsettable="true"
     *        dataType="org.eclipse.sirius.tests.sample.scxml.DurationDatatype"
     *        extendedMetaData="kind='attribute' name='delay'"
     * @generated
     */
    String getDelay();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getDelay
     * <em>Delay</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Delay</em>' attribute.
     * @see #isSetDelay()
     * @see #unsetDelay()
     * @see #getDelay()
     * @generated
     */
    void setDelay(String value);

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getDelay
     * <em>Delay</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isSetDelay()
     * @see #getDelay()
     * @see #setDelay(String)
     * @generated
     */
    void unsetDelay();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getDelay
     * <em>Delay</em>}' attribute is set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return whether the value of the '<em>Delay</em>' attribute is set.
     * @see #unsetDelay()
     * @see #getDelay()
     * @see #setDelay(String)
     * @generated
     */
    boolean isSetDelay();

    /**
     * Returns the value of the '<em><b>Delayexpr</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Delayexpr</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Delayexpr</em>' attribute.
     * @see #setDelayexpr(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Delayexpr()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.ValueLangDatatype"
     *        extendedMetaData="kind='attribute' name='delayexpr'"
     * @generated
     */
    String getDelayexpr();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getDelayexpr
     * <em>Delayexpr</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Delayexpr</em>' attribute.
     * @see #getDelayexpr()
     * @generated
     */
    void setDelayexpr(String value);

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Event()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.EventTypeDatatype"
     *        extendedMetaData="kind='attribute' name='event'"
     * @generated
     */
    String getEvent();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getEvent
     * <em>Event</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Event</em>' attribute.
     * @see #getEvent()
     * @generated
     */
    void setEvent(String value);

    /**
     * Returns the value of the '<em><b>Eventexpr</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Eventexpr</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Eventexpr</em>' attribute.
     * @see #setEventexpr(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Eventexpr()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.ValueLangDatatype"
     *        extendedMetaData="kind='attribute' name='eventexpr'"
     * @generated
     */
    String getEventexpr();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getEventexpr
     * <em>Eventexpr</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Eventexpr</em>' attribute.
     * @see #getEventexpr()
     * @generated
     */
    void setEventexpr(String value);

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getId
     * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Idlocation</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Idlocation</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Idlocation</em>' attribute.
     * @see #setIdlocation(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Idlocation()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.LocLangDatatype"
     *        extendedMetaData="kind='attribute' name='idlocation'"
     * @generated
     */
    String getIdlocation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getIdlocation
     * <em>Idlocation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Idlocation</em>' attribute.
     * @see #getIdlocation()
     * @generated
     */
    void setIdlocation(String value);

    /**
     * Returns the value of the '<em><b>Namelist</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namelist</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Namelist</em>' attribute.
     * @see #setNamelist(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Namelist()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='namelist'"
     * @generated
     */
    String getNamelist();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getNamelist
     * <em>Namelist</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Namelist</em>' attribute.
     * @see #getNamelist()
     * @generated
     */
    void setNamelist(String value);

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
     * @see #setTarget(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Target()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.URIDatatype"
     *        extendedMetaData="kind='attribute' name='target'"
     * @generated
     */
    String getTarget();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTarget
     * <em>Target</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Target</em>' attribute.
     * @see #getTarget()
     * @generated
     */
    void setTarget(String value);

    /**
     * Returns the value of the '<em><b>Targetexpr</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Targetexpr</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Targetexpr</em>' attribute.
     * @see #setTargetexpr(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Targetexpr()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.ValueLangDatatype"
     *        extendedMetaData="kind='attribute' name='targetexpr'"
     * @generated
     */
    String getTargetexpr();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTargetexpr
     * <em>Targetexpr</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Targetexpr</em>' attribute.
     * @see #getTargetexpr()
     * @generated
     */
    void setTargetexpr(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. The default
     * value is <code>"scxml"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Type</em>' attribute.
     * @see #isSetType()
     * @see #unsetType()
     * @see #setType(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Type()
     * @model default="scxml" unsettable="true"
     *        dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='type'"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getType
     * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Type</em>' attribute.
     * @see #isSetType()
     * @see #unsetType()
     * @see #getType()
     * @generated
     */
    void setType(String value);

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getType
     * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isSetType()
     * @see #getType()
     * @see #setType(String)
     * @generated
     */
    void unsetType();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getType
     * <em>Type</em>}' attribute is set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return whether the value of the '<em>Type</em>' attribute is set.
     * @see #unsetType()
     * @see #getType()
     * @see #setType(String)
     * @generated
     */
    boolean isSetType();

    /**
     * Returns the value of the '<em><b>Typeexpr</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Typeexpr</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Typeexpr</em>' attribute.
     * @see #setTypeexpr(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_Typeexpr()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.ValueLangDatatype"
     *        extendedMetaData="kind='attribute' name='typeexpr'"
     * @generated
     */
    String getTypeexpr();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlSendType#getTypeexpr
     * <em>Typeexpr</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Typeexpr</em>' attribute.
     * @see #getTypeexpr()
     * @generated
     */
    void setTypeexpr(String value);

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlSendType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':15' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlSendType
