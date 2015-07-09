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
 * <em><b>Donedata Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType#getContent
 * <em>Content</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType#getParam
 * <em>Param</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlDonedataType()
 * @model extendedMetaData="name='scxml.donedata.type' kind='elementOnly'"
 * @generated
 */
public interface ScxmlDonedataType extends EObject {
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlDonedataType_Content()
     * @model containment="true" extendedMetaData=
     *        "kind='element' name='content' namespace='##targetNamespace'"
     * @generated
     */
    ScxmlContentType getContent();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType#getContent
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlDonedataType_Param()
     * @model containment="true" extendedMetaData=
     *        "kind='element' name='param' namespace='##targetNamespace'"
     * @generated
     */
    EList<ScxmlParamType> getParam();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlDonedataType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':2' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlDonedataType
