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
 * <em><b>Final Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getScxmlFinalMix
 * <em>Scxml Final Mix</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getOnentry
 * <em>Onentry</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getOnexit
 * <em>Onexit</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getDonedata
 * <em>Donedata</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getAny <em>
 * Any</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getId <em>Id
 * </em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlFinalType()
 * @model extendedMetaData="name='scxml.final.type' kind='elementOnly'"
 * @generated
 */
public interface ScxmlFinalType extends EObject {
    /**
     * Returns the value of the '<em><b>Scxml Final Mix</b></em>' attribute
     * list. The list contents are of type
     * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Scxml Final Mix</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Scxml Final Mix</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlFinalType_ScxmlFinalMix()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData="kind='group' name='ScxmlFinalMix:0'"
     * @generated
     */
    FeatureMap getScxmlFinalMix();

    /**
     * Returns the value of the '<em><b>Onentry</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Onentry</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Onentry</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlFinalType_Onentry()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='onentry' namespace='##targetNamespace' group='#ScxmlFinalMix:0'"
     * @generated
     */
    EList<ScxmlOnentryType> getOnentry();

    /**
     * Returns the value of the '<em><b>Onexit</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Onexit</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Onexit</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlFinalType_Onexit()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='onexit' namespace='##targetNamespace' group='#ScxmlFinalMix:0'"
     * @generated
     */
    EList<ScxmlOnexitType> getOnexit();

    /**
     * Returns the value of the '<em><b>Donedata</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Donedata</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Donedata</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlFinalType_Donedata()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='element' name='donedata' namespace='##targetNamespace' group='#ScxmlFinalMix:0'"
     * @generated
     */
    EList<ScxmlDonedataType> getDonedata();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlFinalType_Any()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##other' name=':4' processing='lax' group='#ScxmlFinalMix:0'"
     * @generated
     */
    FeatureMap getAny();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlFinalType_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType#getId
     * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlFinalType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':6' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlFinalType
