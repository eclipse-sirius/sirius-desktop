/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.tests.rcptt.properties.propertiestests;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsFactory
 * @model kind="package"
 * @generated
 */
public interface PropertiestestsPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "propertiestests";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/tests/properties/1.0.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "propertiestests";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    PropertiestestsPackage eINSTANCE = org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestRootImpl <em>Test Root</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestRootImpl
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl#getTestRoot()
     * @generated
     */
    int TEST_ROOT = 0;

    /**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ROOT__ELEMENTS = 0;

    /**
     * The number of structural features of the '<em>Test Root</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ROOT_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Test Root</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ROOT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl
     * <em>Test Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl#getTestElement()
     * @generated
     */
    int TEST_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>String Attribute</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__STRING_ATTRIBUTE = 0;

    /**
     * The feature id for the '<em><b>String Attributes</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__STRING_ATTRIBUTES = 1;

    /**
     * The feature id for the '<em><b>String Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__STRING_ATTRIBUTE_MANDATORY = 2;

    /**
     * The feature id for the '<em><b>String Attribute Multiline</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE = 3;

    /**
     * The feature id for the '<em><b>String Attribute Multiline Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE_MANDATORY = 4;

    /**
     * The feature id for the '<em><b>Int Attribute</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__INT_ATTRIBUTE = 5;

    /**
     * The feature id for the '<em><b>Int Attributes</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__INT_ATTRIBUTES = 6;

    /**
     * The feature id for the '<em><b>Int Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__INT_ATTRIBUTE_MANDATORY = 7;

    /**
     * The feature id for the '<em><b>Boolean Attribute</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__BOOLEAN_ATTRIBUTE = 8;

    /**
     * The feature id for the '<em><b>Boolean Attributes</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__BOOLEAN_ATTRIBUTES = 9;

    /**
     * The feature id for the '<em><b>Boolean Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__BOOLEAN_ATTRIBUTE_MANDATORY = 10;

    /**
     * The feature id for the '<em><b>Enum Attribute</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__ENUM_ATTRIBUTE = 11;

    /**
     * The feature id for the '<em><b>Enum Attributes</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__ENUM_ATTRIBUTES = 12;

    /**
     * The feature id for the '<em><b>Enum Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__ENUM_ATTRIBUTE_MANDATORY = 13;

    /**
     * The feature id for the '<em><b>Containment Reference</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__CONTAINMENT_REFERENCE = 14;

    /**
     * The feature id for the '<em><b>Containment References</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__CONTAINMENT_REFERENCES = 15;

    /**
     * The feature id for the '<em><b>Containment Reference Mandatory</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY = 16;

    /**
     * The feature id for the '<em><b>Reference</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__REFERENCE = 17;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__REFERENCES = 18;

    /**
     * The feature id for the '<em><b>Reference Mandatory</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__REFERENCE_MANDATORY = 19;

    /**
     * The feature id for the '<em><b>Char Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__CHAR_ATTRIBUTE = 20;

    /**
     * The feature id for the '<em><b>Char Attributes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__CHAR_ATTRIBUTES = 21;

    /**
     * The feature id for the '<em><b>Char Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__CHAR_ATTRIBUTE_MANDATORY = 22;

    /**
     * The feature id for the '<em><b>Date Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__DATE_ATTRIBUTE = 23;

    /**
     * The feature id for the '<em><b>Date Attributes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__DATE_ATTRIBUTES = 24;

    /**
     * The feature id for the '<em><b>Date Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__DATE_ATTRIBUTE_MANDATORY = 25;

    /**
     * The feature id for the '<em><b>Double Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__DOUBLE_ATTRIBUTE = 26;

    /**
     * The feature id for the '<em><b>Double Attributes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__DOUBLE_ATTRIBUTES = 27;

    /**
     * The feature id for the '<em><b>Double Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__DOUBLE_ATTRIBUTE_MANDATORY = 28;

    /**
     * The feature id for the '<em><b>Float Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__FLOAT_ATTRIBUTE = 29;

    /**
     * The feature id for the '<em><b>Float Attributes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__FLOAT_ATTRIBUTES = 30;

    /**
     * The feature id for the '<em><b>Float Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__FLOAT_ATTRIBUTE_MANDATORY = 31;

    /**
     * The feature id for the '<em><b>Long Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__LONG_ATTRIBUTE = 32;

    /**
     * The feature id for the '<em><b>Long Attributes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__LONG_ATTRIBUTES = 33;

    /**
     * The feature id for the '<em><b>Long Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__LONG_ATTRIBUTE_MANDATORY = 34;

    /**
     * The feature id for the '<em><b>Short Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__SHORT_ATTRIBUTE = 35;

    /**
     * The feature id for the '<em><b>Short Attributes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__SHORT_ATTRIBUTES = 36;

    /**
     * The feature id for the '<em><b>Short Attribute Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__SHORT_ATTRIBUTE_MANDATORY = 37;

    /**
     * The feature id for the '<em><b>Derived Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__DERIVED_ATTRIBUTE = 38;

    /**
     * The feature id for the '<em><b>Transient Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__TRANSIENT_ATTRIBUTE = 39;

    /**
     * The feature id for the '<em><b>Non Changeable Attribute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__NON_CHANGEABLE_ATTRIBUTE = 40;

    /**
     * The feature id for the '<em><b>Optional Feature</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__OPTIONAL_FEATURE = 41;

    /**
     * The number of structural features of the '<em>Test Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_ELEMENT_FEATURE_COUNT = 42;

    /**
     * The number of operations of the '<em>Test Element</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum <em>Test Enum</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl#getTestEnum()
     * @generated
     */
    int TEST_ENUM = 2;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot <em>Test Root</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Test Root</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot
     * @generated
     */
    EClass getTestRoot();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot#getElements <em>Elements</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Elements</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot#getElements()
     * @see #getTestRoot()
     * @generated
     */
    EReference getTestRoot_Elements();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement <em>Test Element</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Test Element</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement
     * @generated
     */
    EClass getTestElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttribute
     * <em>String Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>String Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_StringAttribute();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributes
     * <em>String Attributes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>String Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_StringAttributes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMandatory <em>String Attribute Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>String Attribute Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_StringAttributeMandatory();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMultiline <em>String Attribute Multiline</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>String Attribute Multiline</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMultiline()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_StringAttributeMultiline();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMultilineMandatory <em>String Attribute Multiline Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>String Attribute Multiline Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getStringAttributeMultilineMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_StringAttributeMultilineMandatory();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttribute <em>Int Attribute</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Int Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_IntAttribute();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttributes <em>Int Attributes</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Int Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_IntAttributes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttributeMandatory <em>Int Attribute Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Int Attribute Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttributeMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_IntAttributeMandatory();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#isBooleanAttribute
     * <em>Boolean Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Boolean Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#isBooleanAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_BooleanAttribute();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getBooleanAttributes
     * <em>Boolean Attributes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>Boolean Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getBooleanAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_BooleanAttributes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#isBooleanAttributeMandatory <em>Boolean Attribute Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Boolean Attribute Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#isBooleanAttributeMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_BooleanAttributeMandatory();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttribute <em>Enum Attribute</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enum Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_EnumAttribute();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttributes <em>Enum Attributes</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Enum Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_EnumAttributes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttributeMandatory <em>Enum Attribute Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enum Attribute Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttributeMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_EnumAttributeMandatory();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReference <em>Containment Reference</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference '<em>Containment Reference</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReference()
     * @see #getTestElement()
     * @generated
     */
    EReference getTestElement_ContainmentReference();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReferences <em>Containment References</em>}'.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Containment References</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReferences()
     * @see #getTestElement()
     * @generated
     */
    EReference getTestElement_ContainmentReferences();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReferenceMandatory <em>Containment Reference Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Containment Reference Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReferenceMandatory()
     * @see #getTestElement()
     * @generated
     */
    EReference getTestElement_ContainmentReferenceMandatory();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReference <em>Reference</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Reference</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReference()
     * @see #getTestElement()
     * @generated
     */
    EReference getTestElement_Reference();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReferences <em>References</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>References</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReferences()
     * @see #getTestElement()
     * @generated
     */
    EReference getTestElement_References();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReferenceMandatory <em>Reference Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Reference Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReferenceMandatory()
     * @see #getTestElement()
     * @generated
     */
    EReference getTestElement_ReferenceMandatory();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttribute <em>Char Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Char Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_CharAttribute();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttributes <em>Char Attributes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Char Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_CharAttributes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttributeMandatory <em>Char Attribute Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Char Attribute Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getCharAttributeMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_CharAttributeMandatory();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttribute <em>Date Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Date Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_DateAttribute();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttributes <em>Date Attributes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Date Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_DateAttributes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttributeMandatory <em>Date Attribute Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Date Attribute Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDateAttributeMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_DateAttributeMandatory();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttribute <em>Double Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Double Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_DoubleAttribute();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttributes <em>Double Attributes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Double Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_DoubleAttributes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttributeMandatory <em>Double Attribute Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Double Attribute Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDoubleAttributeMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_DoubleAttributeMandatory();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttribute <em>Float Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Float Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_FloatAttribute();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttributes <em>Float Attributes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Float Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_FloatAttributes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttributeMandatory <em>Float Attribute Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Float Attribute Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getFloatAttributeMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_FloatAttributeMandatory();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttribute <em>Long Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Long Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_LongAttribute();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttributes <em>Long Attributes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Long Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_LongAttributes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttributeMandatory <em>Long Attribute Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Long Attribute Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getLongAttributeMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_LongAttributeMandatory();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttribute <em>Short Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Short Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_ShortAttribute();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttributes <em>Short Attributes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Short Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_ShortAttributes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttributeMandatory <em>Short Attribute Mandatory</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Short Attribute Mandatory</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getShortAttributeMandatory()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_ShortAttributeMandatory();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDerivedAttribute <em>Derived Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Derived Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getDerivedAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_DerivedAttribute();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getTransientAttribute <em>Transient Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Transient Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getTransientAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_TransientAttribute();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getNonChangeableAttribute <em>Non Changeable Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Non Changeable Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getNonChangeableAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_NonChangeableAttribute();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getOptionalFeature <em>Optional Feature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Optional Feature</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getOptionalFeature()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_OptionalFeature();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum <em>Test Enum</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Test Enum</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
     * @generated
     */
    EEnum getTestEnum();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PropertiestestsFactory getPropertiestestsFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestRootImpl
         * <em>Test Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestRootImpl
         * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl#getTestRoot()
         * @generated
         */
        EClass TEST_ROOT = eINSTANCE.getTestRoot();

        /**
         * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference TEST_ROOT__ELEMENTS = eINSTANCE.getTestRoot_Elements();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl <em>Test Element</em>}' class.
         * <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl
         * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl#getTestElement()
         * @generated
         */
        EClass TEST_ELEMENT = eINSTANCE.getTestElement();

        /**
         * The meta object literal for the '<em><b>String Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__STRING_ATTRIBUTE = eINSTANCE.getTestElement_StringAttribute();

        /**
         * The meta object literal for the '<em><b>String Attributes</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__STRING_ATTRIBUTES = eINSTANCE.getTestElement_StringAttributes();

        /**
         * The meta object literal for the '<em><b>String Attribute Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__STRING_ATTRIBUTE_MANDATORY = eINSTANCE.getTestElement_StringAttributeMandatory();

        /**
         * The meta object literal for the '<em><b>String Attribute Multiline</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE = eINSTANCE.getTestElement_StringAttributeMultiline();

        /**
         * The meta object literal for the '<em><b>String Attribute Multiline Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE_MANDATORY = eINSTANCE.getTestElement_StringAttributeMultilineMandatory();

        /**
         * The meta object literal for the '<em><b>Int Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__INT_ATTRIBUTE = eINSTANCE.getTestElement_IntAttribute();

        /**
         * The meta object literal for the '<em><b>Int Attributes</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__INT_ATTRIBUTES = eINSTANCE.getTestElement_IntAttributes();

        /**
         * The meta object literal for the '<em><b>Int Attribute Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__INT_ATTRIBUTE_MANDATORY = eINSTANCE.getTestElement_IntAttributeMandatory();

        /**
         * The meta object literal for the '<em><b>Boolean Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__BOOLEAN_ATTRIBUTE = eINSTANCE.getTestElement_BooleanAttribute();

        /**
         * The meta object literal for the '<em><b>Boolean Attributes</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__BOOLEAN_ATTRIBUTES = eINSTANCE.getTestElement_BooleanAttributes();

        /**
         * The meta object literal for the '<em><b>Boolean Attribute Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__BOOLEAN_ATTRIBUTE_MANDATORY = eINSTANCE.getTestElement_BooleanAttributeMandatory();

        /**
         * The meta object literal for the '<em><b>Enum Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__ENUM_ATTRIBUTE = eINSTANCE.getTestElement_EnumAttribute();

        /**
         * The meta object literal for the '<em><b>Enum Attributes</b></em>' attribute list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__ENUM_ATTRIBUTES = eINSTANCE.getTestElement_EnumAttributes();

        /**
         * The meta object literal for the '<em><b>Enum Attribute Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__ENUM_ATTRIBUTE_MANDATORY = eINSTANCE.getTestElement_EnumAttributeMandatory();

        /**
         * The meta object literal for the '<em><b>Containment Reference</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference TEST_ELEMENT__CONTAINMENT_REFERENCE = eINSTANCE.getTestElement_ContainmentReference();

        /**
         * The meta object literal for the '<em><b>Containment References</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference TEST_ELEMENT__CONTAINMENT_REFERENCES = eINSTANCE.getTestElement_ContainmentReferences();

        /**
         * The meta object literal for the '<em><b>Containment Reference Mandatory</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY = eINSTANCE.getTestElement_ContainmentReferenceMandatory();

        /**
         * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference TEST_ELEMENT__REFERENCE = eINSTANCE.getTestElement_Reference();

        /**
         * The meta object literal for the '<em><b>References</b></em>' reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference TEST_ELEMENT__REFERENCES = eINSTANCE.getTestElement_References();

        /**
         * The meta object literal for the '<em><b>Reference Mandatory</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TEST_ELEMENT__REFERENCE_MANDATORY = eINSTANCE.getTestElement_ReferenceMandatory();

        /**
         * The meta object literal for the '<em><b>Char Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__CHAR_ATTRIBUTE = eINSTANCE.getTestElement_CharAttribute();

        /**
         * The meta object literal for the '<em><b>Char Attributes</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__CHAR_ATTRIBUTES = eINSTANCE.getTestElement_CharAttributes();

        /**
         * The meta object literal for the '<em><b>Char Attribute Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__CHAR_ATTRIBUTE_MANDATORY = eINSTANCE.getTestElement_CharAttributeMandatory();

        /**
         * The meta object literal for the '<em><b>Date Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__DATE_ATTRIBUTE = eINSTANCE.getTestElement_DateAttribute();

        /**
         * The meta object literal for the '<em><b>Date Attributes</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__DATE_ATTRIBUTES = eINSTANCE.getTestElement_DateAttributes();

        /**
         * The meta object literal for the '<em><b>Date Attribute Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__DATE_ATTRIBUTE_MANDATORY = eINSTANCE.getTestElement_DateAttributeMandatory();

        /**
         * The meta object literal for the '<em><b>Double Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__DOUBLE_ATTRIBUTE = eINSTANCE.getTestElement_DoubleAttribute();

        /**
         * The meta object literal for the '<em><b>Double Attributes</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__DOUBLE_ATTRIBUTES = eINSTANCE.getTestElement_DoubleAttributes();

        /**
         * The meta object literal for the '<em><b>Double Attribute Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__DOUBLE_ATTRIBUTE_MANDATORY = eINSTANCE.getTestElement_DoubleAttributeMandatory();

        /**
         * The meta object literal for the '<em><b>Float Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__FLOAT_ATTRIBUTE = eINSTANCE.getTestElement_FloatAttribute();

        /**
         * The meta object literal for the '<em><b>Float Attributes</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__FLOAT_ATTRIBUTES = eINSTANCE.getTestElement_FloatAttributes();

        /**
         * The meta object literal for the '<em><b>Float Attribute Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__FLOAT_ATTRIBUTE_MANDATORY = eINSTANCE.getTestElement_FloatAttributeMandatory();

        /**
         * The meta object literal for the '<em><b>Long Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__LONG_ATTRIBUTE = eINSTANCE.getTestElement_LongAttribute();

        /**
         * The meta object literal for the '<em><b>Long Attributes</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__LONG_ATTRIBUTES = eINSTANCE.getTestElement_LongAttributes();

        /**
         * The meta object literal for the '<em><b>Long Attribute Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__LONG_ATTRIBUTE_MANDATORY = eINSTANCE.getTestElement_LongAttributeMandatory();

        /**
         * The meta object literal for the '<em><b>Short Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__SHORT_ATTRIBUTE = eINSTANCE.getTestElement_ShortAttribute();

        /**
         * The meta object literal for the '<em><b>Short Attributes</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__SHORT_ATTRIBUTES = eINSTANCE.getTestElement_ShortAttributes();

        /**
         * The meta object literal for the '<em><b>Short Attribute Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__SHORT_ATTRIBUTE_MANDATORY = eINSTANCE.getTestElement_ShortAttributeMandatory();

        /**
         * The meta object literal for the '<em><b>Derived Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__DERIVED_ATTRIBUTE = eINSTANCE.getTestElement_DerivedAttribute();

        /**
         * The meta object literal for the '<em><b>Transient Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__TRANSIENT_ATTRIBUTE = eINSTANCE.getTestElement_TransientAttribute();

        /**
         * The meta object literal for the '<em><b>Non Changeable Attribute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__NON_CHANGEABLE_ATTRIBUTE = eINSTANCE.getTestElement_NonChangeableAttribute();

        /**
         * The meta object literal for the '<em><b>Optional Feature</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_ELEMENT__OPTIONAL_FEATURE = eINSTANCE.getTestElement_OptionalFeature();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
         * <em>Test Enum</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
         * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl#getTestEnum()
         * @generated
         */
        EEnum TEST_ENUM = eINSTANCE.getTestEnum();

    }

} // PropertiestestsPackage
