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
 * <em><b>Initial Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getScxmlExtraContent
 * <em>Scxml Extra Content</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getAny <em>
 * Any</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getTransition
 * <em>Transition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getScxmlExtraContent1
 * <em>Scxml Extra Content1</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getAny1
 * <em>Any1</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInitialType()
 * @model extendedMetaData="name='scxml.initial.type' kind='elementOnly'"
 * @generated
 */
public interface ScxmlInitialType extends EObject {
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInitialType_ScxmlExtraContent()
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInitialType_Any()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##other' name=':1' processing='lax' group='#ScxmlExtraContent:0'"
     * @generated
     */
    FeatureMap getAny();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInitialType_Transition()
     * @model containment="true" required="true" extendedMetaData=
     *        "kind='element' name='transition' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlTransitionType getTransition();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType#getTransition
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

    /**
     * Returns the value of the '<em><b>Scxml Extra Content1</b></em>' attribute
     * list. The list contents are of type
     * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Scxml Extra Content1</em>' attribute list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Scxml Extra Content1</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInitialType_ScxmlExtraContent1()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true"
     *        extendedMetaData="kind='group' name='ScxmlExtraContent:3'"
     * @generated
     */
    FeatureMap getScxmlExtraContent1();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInitialType_Any1()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##other' name=':4' processing='lax' group='#ScxmlExtraContent:3'"
     * @generated
     */
    FeatureMap getAny1();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlInitialType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':5' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlInitialType
