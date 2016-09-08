/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.tests.rcptt.properties.propertiestests;

import java.util.Date;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Test Element</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttribute <em>String Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributes <em>String Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMandatory <em>String Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMultiline <em>String Attribute Multiline</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMultilineMandatory <em>String Attribute Multiline Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttribute <em>Int Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttributes <em>Int Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttributeMandatory <em>Int Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#isBooleanAttribute <em>Boolean Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getBooleanAttributes <em>Boolean Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#isBooleanAttributeMandatory <em>Boolean Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttribute <em>Enum Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttributes <em>Enum Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttributeMandatory <em>Enum Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReference <em>Containment Reference</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReferences <em>Containment References</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReferenceMandatory <em>Containment Reference Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReferences <em>References</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReferenceMandatory <em>Reference Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttribute <em>Char Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttributes <em>Char Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttributeMandatory <em>Char Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttribute <em>Date Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttributes <em>Date Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttributeMandatory <em>Date Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttribute <em>Double Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttributes <em>Double Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttributeMandatory <em>Double Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttribute <em>Float Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttributes <em>Float Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttributeMandatory <em>Float Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttribute <em>Long Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttributes <em>Long Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttributeMandatory <em>Long Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttribute <em>Short Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttributes <em>Short Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttributeMandatory <em>Short Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDerivedAttribute <em>Derived Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getTransientAttribute <em>Transient Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getNonChangeableAttribute <em>Non Changeable Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getOptionalFeature <em>Optional Feature</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement()
 * @model
 * @generated
 */
public interface TestElement extends EObject {
    /**
     * Returns the value of the '<em><b>String Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>String Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>String Attribute</em>' attribute.
     * @see #setStringAttribute(String)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_StringAttribute()
     * @model
     * @generated
     */
    String getStringAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttribute <em>String Attribute</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>String Attribute</em>' attribute.
     * @see #getStringAttribute()
     * @generated
     */
    void setStringAttribute(String value);

    /**
     * Returns the value of the '<em><b>String Attributes</b></em>' attribute
     * list. The list contents are of type {@link java.lang.String}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>String Attributes</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>String Attributes</em>' attribute list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_StringAttributes()
     * @model
     * @generated
     */
    EList<String> getStringAttributes();

    /**
     * Returns the value of the '<em><b>String Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>String Attribute Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>String Attribute Mandatory</em>' attribute.
     * @see #setStringAttributeMandatory(String)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_StringAttributeMandatory()
     * @model required="true"
     * @generated
     */
    String getStringAttributeMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMandatory <em>String Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>String Attribute Mandatory</em>' attribute.
     * @see #getStringAttributeMandatory()
     * @generated
     */
    void setStringAttributeMandatory(String value);

    /**
     * Returns the value of the '<em><b>String Attribute Multiline</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>String Attribute Multiline</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>String Attribute Multiline</em>' attribute.
     * @see #setStringAttributeMultiline(String)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_StringAttributeMultiline()
     * @model
     * @generated
     */
    String getStringAttributeMultiline();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMultiline <em>String Attribute Multiline</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>String Attribute Multiline</em>' attribute.
     * @see #getStringAttributeMultiline()
     * @generated
     */
    void setStringAttributeMultiline(String value);

    /**
     * Returns the value of the '<em><b>String Attribute Multiline Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>String Attribute Multiline Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>String Attribute Multiline Mandatory</em>' attribute.
     * @see #setStringAttributeMultilineMandatory(String)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_StringAttributeMultilineMandatory()
     * @model required="true"
     * @generated
     */
    String getStringAttributeMultilineMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMultilineMandatory <em>String Attribute Multiline Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>String Attribute Multiline Mandatory</em>' attribute.
     * @see #getStringAttributeMultilineMandatory()
     * @generated
     */
    void setStringAttributeMultilineMandatory(String value);

    /**
     * Returns the value of the '<em><b>Int Attribute</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Int Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Int Attribute</em>' attribute.
     * @see #setIntAttribute(int)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_IntAttribute()
     * @model
     * @generated
     */
    int getIntAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttribute <em>Int Attribute</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Int Attribute</em>' attribute.
     * @see #getIntAttribute()
     * @generated
     */
    void setIntAttribute(int value);

    /**
     * Returns the value of the '<em><b>Int Attributes</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Integer}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Int Attributes</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Int Attributes</em>' attribute list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_IntAttributes()
     * @model
     * @generated
     */
    EList<Integer> getIntAttributes();

    /**
     * Returns the value of the '<em><b>Int Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Int Attribute Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Int Attribute Mandatory</em>' attribute.
     * @see #setIntAttributeMandatory(int)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_IntAttributeMandatory()
     * @model required="true"
     * @generated
     */
    int getIntAttributeMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttributeMandatory <em>Int Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Int Attribute Mandatory</em>' attribute.
     * @see #getIntAttributeMandatory()
     * @generated
     */
    void setIntAttributeMandatory(int value);

    /**
     * Returns the value of the '<em><b>Boolean Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Boolean Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Boolean Attribute</em>' attribute.
     * @see #setBooleanAttribute(boolean)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_BooleanAttribute()
     * @model
     * @generated
     */
    boolean isBooleanAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#isBooleanAttribute <em>Boolean Attribute</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Boolean Attribute</em>' attribute.
     * @see #isBooleanAttribute()
     * @generated
     */
    void setBooleanAttribute(boolean value);

    /**
     * Returns the value of the '<em><b>Boolean Attributes</b></em>' attribute
     * list. The list contents are of type {@link java.lang.Boolean}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Boolean Attributes</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Boolean Attributes</em>' attribute list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_BooleanAttributes()
     * @model
     * @generated
     */
    EList<Boolean> getBooleanAttributes();

    /**
     * Returns the value of the '<em><b>Boolean Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Boolean Attribute Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Boolean Attribute Mandatory</em>' attribute.
     * @see #setBooleanAttributeMandatory(boolean)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_BooleanAttributeMandatory()
     * @model required="true"
     * @generated
     */
    boolean isBooleanAttributeMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#isBooleanAttributeMandatory <em>Boolean Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Boolean Attribute Mandatory</em>' attribute.
     * @see #isBooleanAttributeMandatory()
     * @generated
     */
    void setBooleanAttributeMandatory(boolean value);

    /**
     * Returns the value of the '<em><b>Enum Attribute</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Enum Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Enum Attribute</em>' attribute.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
     * @see #setEnumAttribute(TestEnum)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_EnumAttribute()
     * @model
     * @generated
     */
    TestEnum getEnumAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttribute <em>Enum Attribute</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Enum Attribute</em>' attribute.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
     * @see #getEnumAttribute()
     * @generated
     */
    void setEnumAttribute(TestEnum value);

    /**
     * Returns the value of the '<em><b>Enum Attributes</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum}.
     * The literals are from the enumeration {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Enum Attributes</em>' attribute list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Enum Attributes</em>' attribute list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_EnumAttributes()
     * @model
     * @generated
     */
    EList<TestEnum> getEnumAttributes();

    /**
     * Returns the value of the '<em><b>Enum Attribute Mandatory</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Enum Attribute Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Enum Attribute Mandatory</em>' attribute.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
     * @see #setEnumAttributeMandatory(TestEnum)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_EnumAttributeMandatory()
     * @model required="true"
     * @generated
     */
    TestEnum getEnumAttributeMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttributeMandatory <em>Enum Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Enum Attribute Mandatory</em>' attribute.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
     * @see #getEnumAttributeMandatory()
     * @generated
     */
    void setEnumAttributeMandatory(TestEnum value);

    /**
     * Returns the value of the '<em><b>Containment Reference</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Containment Reference</em>' containment
     * reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Containment Reference</em>' containment reference.
     * @see #setContainmentReference(TestElement)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_ContainmentReference()
     * @model containment="true"
     * @generated
     */
    TestElement getContainmentReference();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReference
     * <em>Containment Reference</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Containment Reference</em>'
     *            containment reference.
     * @see #getContainmentReference()
     * @generated
     */
    void setContainmentReference(TestElement value);

    /**
     * Returns the value of the '<em><b>Containment References</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Containment References</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Containment References</em>' containment reference list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_ContainmentReferences()
     * @model containment="true"
     * @generated
     */
    EList<TestElement> getContainmentReferences();

    /**
     * Returns the value of the '<em><b>Containment Reference Mandatory</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Containment Reference Mandatory</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Containment Reference Mandatory</em>' containment reference.
     * @see #setContainmentReferenceMandatory(TestElement)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_ContainmentReferenceMandatory()
     * @model containment="true" required="true"
     * @generated
     */
    TestElement getContainmentReferenceMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReferenceMandatory <em>Containment Reference Mandatory</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Containment Reference Mandatory</em>' containment reference.
     * @see #getContainmentReferenceMandatory()
     * @generated
     */
    void setContainmentReferenceMandatory(TestElement value);

    /**
     * Returns the value of the '<em><b>Reference</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reference</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reference</em>' reference.
     * @see #setReference(TestElement)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_Reference()
     * @model
     * @generated
     */
    TestElement getReference();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReference
     * <em>Reference</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Reference</em>' reference.
     * @see #getReference()
     * @generated
     */
    void setReference(TestElement value);

    /**
     * Returns the value of the '<em><b>References</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>References</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>References</em>' reference list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_References()
     * @model
     * @generated
     */
    EList<TestElement> getReferences();

    /**
     * Returns the value of the '<em><b>Reference Mandatory</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reference Mandatory</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reference Mandatory</em>' reference.
     * @see #setReferenceMandatory(TestElement)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_ReferenceMandatory()
     * @model required="true"
     * @generated
     */
    TestElement getReferenceMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReferenceMandatory <em>Reference Mandatory</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reference Mandatory</em>' reference.
     * @see #getReferenceMandatory()
     * @generated
     */
    void setReferenceMandatory(TestElement value);

    /**
     * Returns the value of the '<em><b>Char Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Char Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Char Attribute</em>' attribute.
     * @see #setCharAttribute(char)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_CharAttribute()
     * @model
     * @generated
     */
    char getCharAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttribute <em>Char Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Char Attribute</em>' attribute.
     * @see #getCharAttribute()
     * @generated
     */
    void setCharAttribute(char value);

    /**
     * Returns the value of the '<em><b>Char Attributes</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Character}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Char Attributes</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Char Attributes</em>' attribute list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_CharAttributes()
     * @model
     * @generated
     */
    EList<Character> getCharAttributes();

    /**
     * Returns the value of the '<em><b>Char Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Char Attribute Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Char Attribute Mandatory</em>' attribute.
     * @see #setCharAttributeMandatory(char)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_CharAttributeMandatory()
     * @model required="true"
     * @generated
     */
    char getCharAttributeMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttributeMandatory <em>Char Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Char Attribute Mandatory</em>' attribute.
     * @see #getCharAttributeMandatory()
     * @generated
     */
    void setCharAttributeMandatory(char value);

    /**
     * Returns the value of the '<em><b>Date Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Date Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Date Attribute</em>' attribute.
     * @see #setDateAttribute(Date)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_DateAttribute()
     * @model
     * @generated
     */
    Date getDateAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttribute <em>Date Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Date Attribute</em>' attribute.
     * @see #getDateAttribute()
     * @generated
     */
    void setDateAttribute(Date value);

    /**
     * Returns the value of the '<em><b>Date Attributes</b></em>' attribute list.
     * The list contents are of type {@link java.util.Date}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Date Attributes</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Date Attributes</em>' attribute list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_DateAttributes()
     * @model
     * @generated
     */
    EList<Date> getDateAttributes();

    /**
     * Returns the value of the '<em><b>Date Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Date Attribute Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Date Attribute Mandatory</em>' attribute.
     * @see #setDateAttributeMandatory(Date)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_DateAttributeMandatory()
     * @model required="true"
     * @generated
     */
    Date getDateAttributeMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttributeMandatory <em>Date Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Date Attribute Mandatory</em>' attribute.
     * @see #getDateAttributeMandatory()
     * @generated
     */
    void setDateAttributeMandatory(Date value);

    /**
     * Returns the value of the '<em><b>Double Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Double Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Double Attribute</em>' attribute.
     * @see #setDoubleAttribute(double)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_DoubleAttribute()
     * @model
     * @generated
     */
    double getDoubleAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttribute <em>Double Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Double Attribute</em>' attribute.
     * @see #getDoubleAttribute()
     * @generated
     */
    void setDoubleAttribute(double value);

    /**
     * Returns the value of the '<em><b>Double Attributes</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Double}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Double Attributes</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Double Attributes</em>' attribute list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_DoubleAttributes()
     * @model
     * @generated
     */
    EList<Double> getDoubleAttributes();

    /**
     * Returns the value of the '<em><b>Double Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Double Attribute Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Double Attribute Mandatory</em>' attribute.
     * @see #setDoubleAttributeMandatory(double)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_DoubleAttributeMandatory()
     * @model required="true"
     * @generated
     */
    double getDoubleAttributeMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttributeMandatory <em>Double Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Double Attribute Mandatory</em>' attribute.
     * @see #getDoubleAttributeMandatory()
     * @generated
     */
    void setDoubleAttributeMandatory(double value);

    /**
     * Returns the value of the '<em><b>Float Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Float Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Float Attribute</em>' attribute.
     * @see #setFloatAttribute(float)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_FloatAttribute()
     * @model
     * @generated
     */
    float getFloatAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttribute <em>Float Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Float Attribute</em>' attribute.
     * @see #getFloatAttribute()
     * @generated
     */
    void setFloatAttribute(float value);

    /**
     * Returns the value of the '<em><b>Float Attributes</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Float}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Float Attributes</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Float Attributes</em>' attribute list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_FloatAttributes()
     * @model
     * @generated
     */
    EList<Float> getFloatAttributes();

    /**
     * Returns the value of the '<em><b>Float Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Float Attribute Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Float Attribute Mandatory</em>' attribute.
     * @see #setFloatAttributeMandatory(float)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_FloatAttributeMandatory()
     * @model required="true"
     * @generated
     */
    float getFloatAttributeMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttributeMandatory <em>Float Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Float Attribute Mandatory</em>' attribute.
     * @see #getFloatAttributeMandatory()
     * @generated
     */
    void setFloatAttributeMandatory(float value);

    /**
     * Returns the value of the '<em><b>Long Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Long Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Long Attribute</em>' attribute.
     * @see #setLongAttribute(long)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_LongAttribute()
     * @model
     * @generated
     */
    long getLongAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttribute <em>Long Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Long Attribute</em>' attribute.
     * @see #getLongAttribute()
     * @generated
     */
    void setLongAttribute(long value);

    /**
     * Returns the value of the '<em><b>Long Attributes</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Long}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Long Attributes</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Long Attributes</em>' attribute list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_LongAttributes()
     * @model
     * @generated
     */
    EList<Long> getLongAttributes();

    /**
     * Returns the value of the '<em><b>Long Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Long Attribute Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Long Attribute Mandatory</em>' attribute.
     * @see #setLongAttributeMandatory(long)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_LongAttributeMandatory()
     * @model required="true"
     * @generated
     */
    long getLongAttributeMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttributeMandatory <em>Long Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Long Attribute Mandatory</em>' attribute.
     * @see #getLongAttributeMandatory()
     * @generated
     */
    void setLongAttributeMandatory(long value);

    /**
     * Returns the value of the '<em><b>Short Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Short Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Short Attribute</em>' attribute.
     * @see #setShortAttribute(short)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_ShortAttribute()
     * @model
     * @generated
     */
    short getShortAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttribute <em>Short Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Short Attribute</em>' attribute.
     * @see #getShortAttribute()
     * @generated
     */
    void setShortAttribute(short value);

    /**
     * Returns the value of the '<em><b>Short Attributes</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Short}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Short Attributes</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Short Attributes</em>' attribute list.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_ShortAttributes()
     * @model
     * @generated
     */
    EList<Short> getShortAttributes();

    /**
     * Returns the value of the '<em><b>Short Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Short Attribute Mandatory</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Short Attribute Mandatory</em>' attribute.
     * @see #setShortAttributeMandatory(short)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_ShortAttributeMandatory()
     * @model required="true"
     * @generated
     */
    short getShortAttributeMandatory();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttributeMandatory <em>Short Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Short Attribute Mandatory</em>' attribute.
     * @see #getShortAttributeMandatory()
     * @generated
     */
    void setShortAttributeMandatory(short value);

    /**
     * Returns the value of the '<em><b>Derived Attribute</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Derived Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Derived Attribute</em>' attribute.
     * @see #setDerivedAttribute(String)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_DerivedAttribute()
     * @model default="" transient="true" volatile="true" derived="true"
     * @generated
     */
    String getDerivedAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDerivedAttribute <em>Derived Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Derived Attribute</em>' attribute.
     * @see #getDerivedAttribute()
     * @generated
     */
    void setDerivedAttribute(String value);

    /**
     * Returns the value of the '<em><b>Transient Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transient Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transient Attribute</em>' attribute.
     * @see #setTransientAttribute(String)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_TransientAttribute()
     * @model transient="true"
     * @generated
     */
    String getTransientAttribute();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getTransientAttribute <em>Transient Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transient Attribute</em>' attribute.
     * @see #getTransientAttribute()
     * @generated
     */
    void setTransientAttribute(String value);

    /**
     * Returns the value of the '<em><b>Non Changeable Attribute</b></em>' attribute.
     * The default value is <code>"NonChangeable"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Non Changeable Attribute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Non Changeable Attribute</em>' attribute.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_NonChangeableAttribute()
     * @model default="NonChangeable" changeable="false"
     * @generated
     */
    String getNonChangeableAttribute();

    /**
     * Returns the value of the '<em><b>Optional Feature</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Optional Feature</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Optional Feature</em>' attribute.
     * @see #setOptionalFeature(String)
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#getTestElement_OptionalFeature()
     * @model
     * @generated
     */
    String getOptionalFeature();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getOptionalFeature <em>Optional Feature</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Optional Feature</em>' attribute.
     * @see #getOptionalFeature()
     * @generated
     */
    void setOptionalFeature(String value);

} // TestElement
