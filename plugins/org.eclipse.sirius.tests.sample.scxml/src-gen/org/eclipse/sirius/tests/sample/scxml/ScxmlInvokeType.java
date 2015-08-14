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
 * <em><b>Invoke Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getScxmlInvokeMix
 * <em>Scxml Invoke Mix</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getContent
 * <em>Content</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getParam
 * <em>Param</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getFinalize
 * <em>Finalize</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAny <em>
 * Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAutoforward
 * <em>Autoforward</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getId <em>Id
 * </em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getIdlocation
 * <em>Idlocation</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getNamelist
 * <em>Namelist</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getSrc <em>
 * Src</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getSrcexpr
 * <em>Srcexpr</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getType <em>
 * Type</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getTypeexpr
 * <em>Typeexpr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType()
 * @model extendedMetaData="name='scxml.invoke.type' kind='elementOnly'"
 * @generated
 */
public interface ScxmlInvokeType extends EObject {
    /**
     * Returns the value of the '<em><b>Scxml Invoke Mix</b></em>' attribute
     * list. The list contents are of type
     * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Scxml Invoke Mix</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Scxml Invoke Mix</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_ScxmlInvokeMix()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true"
     *        extendedMetaData="kind='group' name='ScxmlInvokeMix:0'"
     * @generated
     */
    FeatureMap getScxmlInvokeMix();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Content()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='content' namespace='##targetNamespace' group='#ScxmlInvokeMix:0'"
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Param()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='param' namespace='##targetNamespace' group='#ScxmlInvokeMix:0'"
     * @generated
     */
    EList<ScxmlParamType> getParam();

    /**
     * Returns the value of the '<em><b>Finalize</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Finalize</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Finalize</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Finalize()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='finalize' namespace='##targetNamespace' group='#ScxmlInvokeMix:0'"
     * @generated
     */
    EList<ScxmlFinalizeType> getFinalize();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Any()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##other' name=':4' processing='lax' group='#ScxmlInvokeMix:0'"
     * @generated
     */
    FeatureMap getAny();

    /**
     * Returns the value of the '<em><b>Autoforward</b></em>' attribute. The
     * default value is <code>"false"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.tests.sample.scxml.BooleanDatatype}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Autoforward</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Autoforward</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
     * @see #isSetAutoforward()
     * @see #unsetAutoforward()
     * @see #setAutoforward(BooleanDatatype)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Autoforward()
     * @model default="false" unsettable="true"
     *        extendedMetaData="kind='attribute' name='autoforward'"
     * @generated
     */
    BooleanDatatype getAutoforward();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAutoforward
     * <em>Autoforward</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Autoforward</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.scxml.BooleanDatatype
     * @see #isSetAutoforward()
     * @see #unsetAutoforward()
     * @see #getAutoforward()
     * @generated
     */
    void setAutoforward(BooleanDatatype value);

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAutoforward
     * <em>Autoforward</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isSetAutoforward()
     * @see #getAutoforward()
     * @see #setAutoforward(BooleanDatatype)
     * @generated
     */
    void unsetAutoforward();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getAutoforward
     * <em>Autoforward</em>}' attribute is set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return whether the value of the '<em>Autoforward</em>' attribute is set.
     * @see #unsetAutoforward()
     * @see #getAutoforward()
     * @see #setAutoforward(BooleanDatatype)
     * @generated
     */
    boolean isSetAutoforward();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getId
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Idlocation()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.LocLangDatatype"
     *        extendedMetaData="kind='attribute' name='idlocation'"
     * @generated
     */
    String getIdlocation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getIdlocation
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Namelist()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='namelist'"
     * @generated
     */
    String getNamelist();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getNamelist
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
     * Returns the value of the '<em><b>Src</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Src</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Src</em>' attribute.
     * @see #setSrc(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Src()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.URIDatatype"
     *        extendedMetaData="kind='attribute' name='src'"
     * @generated
     */
    String getSrc();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getSrc
     * <em>Src</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Src</em>' attribute.
     * @see #getSrc()
     * @generated
     */
    void setSrc(String value);

    /**
     * Returns the value of the '<em><b>Srcexpr</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Srcexpr</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Srcexpr</em>' attribute.
     * @see #setSrcexpr(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Srcexpr()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.ValueLangDatatype"
     *        extendedMetaData="kind='attribute' name='srcexpr'"
     * @generated
     */
    String getSrcexpr();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getSrcexpr
     * <em>Srcexpr</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Srcexpr</em>' attribute.
     * @see #getSrcexpr()
     * @generated
     */
    void setSrcexpr(String value);

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Type()
     * @model default="scxml" unsettable="true"
     *        dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='type'"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getType
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
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getType
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
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getType
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_Typeexpr()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.ValueLangDatatype"
     *        extendedMetaData="kind='attribute' name='typeexpr'"
     * @generated
     */
    String getTypeexpr();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType#getTypeexpr
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInvokeType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':13' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlInvokeType
