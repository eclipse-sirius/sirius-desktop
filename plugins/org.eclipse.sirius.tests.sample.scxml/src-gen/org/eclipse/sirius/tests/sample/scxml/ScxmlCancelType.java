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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Cancel Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getScxmlExtraContent
 * <em>Scxml Extra Content</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getAny <em>
 * Any</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getSendid
 * <em>Sendid</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getSendidexpr
 * <em>Sendidexpr</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlCancelType()
 * @model extendedMetaData="name='scxml.cancel.type' kind='elementOnly'"
 * @generated
 */
public interface ScxmlCancelType extends EObject {
    /**
     * Returns the value of the '<em><b>Scxml Extra Content</b></em>' attribute
     * list. The list contents are of type
     * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Scxml Extra Content</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Scxml Extra Content</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlCancelType_ScxmlExtraContent()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true"
     *        extendedMetaData="kind='group' name='ScxmlExtraContent:0'"
     * @generated
     */
    FeatureMap getScxmlExtraContent();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlCancelType_Any()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##other' name=':1' processing='lax' group='#ScxmlExtraContent:0'"
     * @generated
     */
    FeatureMap getAny();

    /**
     * Returns the value of the '<em><b>Sendid</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sendid</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sendid</em>' attribute.
     * @see #setSendid(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlCancelType_Sendid()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='attribute' name='sendid'"
     * @generated
     */
    String getSendid();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getSendid
     * <em>Sendid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Sendid</em>' attribute.
     * @see #getSendid()
     * @generated
     */
    void setSendid(String value);

    /**
     * Returns the value of the '<em><b>Sendidexpr</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sendidexpr</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sendidexpr</em>' attribute.
     * @see #setSendidexpr(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlCancelType_Sendidexpr()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.ValueLangDatatype"
     *        extendedMetaData="kind='attribute' name='sendidexpr'"
     * @generated
     */
    String getSendidexpr();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType#getSendidexpr
     * <em>Sendidexpr</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Sendidexpr</em>' attribute.
     * @see #getSendidexpr()
     * @generated
     */
    void setSendidexpr(String value);

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlCancelType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':4' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlCancelType
