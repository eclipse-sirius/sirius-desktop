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

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Scxml Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getScxmlScxmlMix
 * <em>Scxml Scxml Mix</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getState <em>
 * State</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getParallel
 * <em>Parallel</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getFinal <em>
 * Final</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getDatamodel
 * <em>Datamodel</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getScript
 * <em>Script</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getAny <em>
 * Any</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getBinding
 * <em>Binding</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getDatamodel1
 * <em>Datamodel1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getExmode
 * <em>Exmode</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getInitial
 * <em>Initial</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getName <em>
 * Name</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getVersion
 * <em>Version</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType()
 * @model extendedMetaData="name='scxml.scxml.type' kind='elementOnly'"
 * @generated
 */
public interface ScxmlScxmlType extends EObject {
    /**
     * Returns the value of the '<em><b>Scxml Scxml Mix</b></em>' attribute
     * list. The list contents are of type
     * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Scxml Scxml Mix</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Scxml Scxml Mix</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_ScxmlScxmlMix()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData="kind='group' name='ScxmlScxmlMix:0'"
     * @generated
     */
    FeatureMap getScxmlScxmlMix();

    /**
     * Returns the value of the '<em><b>State</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlStateType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>State</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>State</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_State()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='state' namespace='##targetNamespace' group='#ScxmlScxmlMix:0'"
     * @generated
     */
    EList<ScxmlStateType> getState();

    /**
     * Returns the value of the '<em><b>Parallel</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parallel</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Parallel</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Parallel()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='parallel' namespace='##targetNamespace' group='#ScxmlScxmlMix:0'"
     * @generated
     */
    EList<ScxmlParallelType> getParallel();

    /**
     * Returns the value of the '<em><b>Final</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Final</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Final</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Final()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='final' namespace='##targetNamespace' group='#ScxmlScxmlMix:0'"
     * @generated
     */
    EList<ScxmlFinalType> getFinal();

    /**
     * Returns the value of the '<em><b>Datamodel</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Datamodel</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Datamodel</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Datamodel()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='datamodel' namespace='##targetNamespace' group='#ScxmlScxmlMix:0'"
     * @generated
     */
    EList<ScxmlDatamodelType> getDatamodel();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Script()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='script' namespace='##targetNamespace' group='#ScxmlScxmlMix:0'"
     * @generated
     */
    EList<ScxmlScriptType> getScript();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Any()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##other' name=':6' processing='lax' group='#ScxmlScxmlMix:0'"
     * @generated
     */
    FeatureMap getAny();

    /**
     * Returns the value of the '<em><b>Binding</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.eclipse.sirius.tests.sample.scxml.BindingDatatype}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Binding</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Binding</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.scxml.BindingDatatype
     * @see #isSetBinding()
     * @see #unsetBinding()
     * @see #setBinding(BindingDatatype)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Binding()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='binding'"
     * @generated
     */
    BindingDatatype getBinding();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getBinding
     * <em>Binding</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Binding</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.scxml.BindingDatatype
     * @see #isSetBinding()
     * @see #unsetBinding()
     * @see #getBinding()
     * @generated
     */
    void setBinding(BindingDatatype value);

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getBinding
     * <em>Binding</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #isSetBinding()
     * @see #getBinding()
     * @see #setBinding(BindingDatatype)
     * @generated
     */
    void unsetBinding();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getBinding
     * <em>Binding</em>}' attribute is set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return whether the value of the '<em>Binding</em>' attribute is set.
     * @see #unsetBinding()
     * @see #getBinding()
     * @see #setBinding(BindingDatatype)
     * @generated
     */
    boolean isSetBinding();

    /**
     * Returns the value of the '<em><b>Datamodel1</b></em>' attribute. The
     * default value is <code>"null"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Datamodel1</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Datamodel1</em>' attribute.
     * @see #isSetDatamodel1()
     * @see #unsetDatamodel1()
     * @see #setDatamodel1(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Datamodel1()
     * @model default="null" unsettable="true"
     *        dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
     *        extendedMetaData="kind='attribute' name='datamodel'"
     * @generated
     */
    String getDatamodel1();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getDatamodel1
     * <em>Datamodel1</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Datamodel1</em>' attribute.
     * @see #isSetDatamodel1()
     * @see #unsetDatamodel1()
     * @see #getDatamodel1()
     * @generated
     */
    void setDatamodel1(String value);

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getDatamodel1
     * <em>Datamodel1</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isSetDatamodel1()
     * @see #getDatamodel1()
     * @see #setDatamodel1(String)
     * @generated
     */
    void unsetDatamodel1();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getDatamodel1
     * <em>Datamodel1</em>}' attribute is set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return whether the value of the '<em>Datamodel1</em>' attribute is set.
     * @see #unsetDatamodel1()
     * @see #getDatamodel1()
     * @see #setDatamodel1(String)
     * @generated
     */
    boolean isSetDatamodel1();

    /**
     * Returns the value of the '<em><b>Exmode</b></em>' attribute. The literals
     * are from the enumeration
     * {@link org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Exmode</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Exmode</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
     * @see #isSetExmode()
     * @see #unsetExmode()
     * @see #setExmode(ExmodeDatatype)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Exmode()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='exmode'"
     * @generated
     */
    ExmodeDatatype getExmode();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getExmode
     * <em>Exmode</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Exmode</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype
     * @see #isSetExmode()
     * @see #unsetExmode()
     * @see #getExmode()
     * @generated
     */
    void setExmode(ExmodeDatatype value);

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getExmode
     * <em>Exmode</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #isSetExmode()
     * @see #getExmode()
     * @see #setExmode(ExmodeDatatype)
     * @generated
     */
    void unsetExmode();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getExmode
     * <em>Exmode</em>}' attribute is set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return whether the value of the '<em>Exmode</em>' attribute is set.
     * @see #unsetExmode()
     * @see #getExmode()
     * @see #setExmode(ExmodeDatatype)
     * @generated
     */
    boolean isSetExmode();

    /**
     * Returns the value of the '<em><b>Initial</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Initial</em>' attribute.
     * @see #setInitial(List)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Initial()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREFS" many="false"
     *        extendedMetaData="kind='attribute' name='initial'"
     * @generated
     */
    List<String> getInitial();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getInitial
     * <em>Initial</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Initial</em>' attribute.
     * @see #getInitial()
     * @generated
     */
    void setInitial(List<String> value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute. The default
     * value is <code>"1.0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Version</em>' attribute.
     * @see #isSetVersion()
     * @see #unsetVersion()
     * @see #setVersion(BigDecimal)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_Version()
     * @model default="1.0" unsettable="true"
     *        dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
     *        extendedMetaData="kind='attribute' name='version'"
     * @generated
     */
    BigDecimal getVersion();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getVersion
     * <em>Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Version</em>' attribute.
     * @see #isSetVersion()
     * @see #unsetVersion()
     * @see #getVersion()
     * @generated
     */
    void setVersion(BigDecimal value);

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getVersion
     * <em>Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #isSetVersion()
     * @see #getVersion()
     * @see #setVersion(BigDecimal)
     * @generated
     */
    void unsetVersion();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType#getVersion
     * <em>Version</em>}' attribute is set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return whether the value of the '<em>Version</em>' attribute is set.
     * @see #unsetVersion()
     * @see #getVersion()
     * @see #setVersion(BigDecimal)
     * @generated
     */
    boolean isSetVersion();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlScxmlType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':13' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlScxmlType
