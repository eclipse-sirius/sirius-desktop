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
 * <em><b>Assign Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getMixed
 * <em>Mixed</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getAny <em>
 * Any</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getAttr <em>
 * Attr</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getExpr <em>
 * Expr</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getLocation
 * <em>Location</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getType <em>
 * Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlAssignType()
 * @model extendedMetaData="name='scxml.assign.type' kind='mixed'"
 * @generated
 */
public interface ScxmlAssignType extends EObject {
    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list. The list
     * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mixed</em>' attribute list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlAssignType_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
    FeatureMap getMixed();

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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlAssignType_Any()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData=
     *        "kind='elementWildcard' wildcards='##any' name=':1' processing='lax'"
     * @generated
     */
    FeatureMap getAny();

    /**
     * Returns the value of the '<em><b>Attr</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attr</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Attr</em>' attribute.
     * @see #setAttr(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlAssignType_Attr()
     * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
     *        extendedMetaData="kind='attribute' name='attr'"
     * @generated
     */
    String getAttr();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getAttr
     * <em>Attr</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Attr</em>' attribute.
     * @see #getAttr()
     * @generated
     */
    void setAttr(String value);

    /**
     * Returns the value of the '<em><b>Expr</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expr</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Expr</em>' attribute.
     * @see #setExpr(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlAssignType_Expr()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.ValueLangDatatype"
     *        extendedMetaData="kind='attribute' name='expr'"
     * @generated
     */
    String getExpr();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getExpr
     * <em>Expr</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Expr</em>' attribute.
     * @see #getExpr()
     * @generated
     */
    void setExpr(String value);

    /**
     * Returns the value of the '<em><b>Location</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Location</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Location</em>' attribute.
     * @see #setLocation(String)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlAssignType_Location()
     * @model dataType="org.eclipse.sirius.tests.sample.scxml.LocLangDatatype"
     *        required="true"
     *        extendedMetaData="kind='attribute' name='location'"
     * @generated
     */
    String getLocation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getLocation
     * <em>Location</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Location</em>' attribute.
     * @see #getLocation()
     * @generated
     */
    void setLocation(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. The default
     * value is <code>"replacechildren"</code>. The literals are from the
     * enumeration
     * {@link org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
     * @see #isSetType()
     * @see #unsetType()
     * @see #setType(AssignTypeDatatype)
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlAssignType_Type()
     * @model default="replacechildren" unsettable="true"
     *        extendedMetaData="kind='attribute' name='type'"
     * @generated
     */
    AssignTypeDatatype getType();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getType
     * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Type</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype
     * @see #isSetType()
     * @see #unsetType()
     * @see #getType()
     * @generated
     */
    void setType(AssignTypeDatatype value);

    /**
     * Unsets the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getType
     * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isSetType()
     * @see #getType()
     * @see #setType(AssignTypeDatatype)
     * @generated
     */
    void unsetType();

    /**
     * Returns whether the value of the '
     * {@link org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType#getType
     * <em>Type</em>}' attribute is set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return whether the value of the '<em>Type</em>' attribute is set.
     * @see #unsetType()
     * @see #getType()
     * @see #setType(AssignTypeDatatype)
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
     * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getScxmlAssignType_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
     *        many="true" extendedMetaData=
     *        "kind='attributeWildcard' wildcards='##other' name=':6' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // ScxmlAssignType
