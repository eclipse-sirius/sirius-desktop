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
 *
 * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsFactory
 * @model kind="package"
 * @generated
 */
public interface PropertiestestsPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "propertiestests";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/tests/properties/1.0.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "propertiestests";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    PropertiestestsPackage eINSTANCE = org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestRootImpl
     * <em>Test Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestRootImpl
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl#getTestRoot()
     * @generated
     */
    int TEST_ROOT = 0;

    /**
     * The feature id for the '<em><b>Elements</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
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
     * The feature id for the '<em><b>String Attributes</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__STRING_ATTRIBUTES = 1;

    /**
     * The feature id for the '<em><b>Int Attribute</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__INT_ATTRIBUTE = 2;

    /**
     * The feature id for the '<em><b>Int Attributes</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__INT_ATTRIBUTES = 3;

    /**
     * The feature id for the '<em><b>Boolean Attribute</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__BOOLEAN_ATTRIBUTE = 4;

    /**
     * The feature id for the '<em><b>Boolean Attributes</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__BOOLEAN_ATTRIBUTES = 5;

    /**
     * The feature id for the '<em><b>Enum Attribute</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__ENUM_ATTRIBUTE = 6;

    /**
     * The feature id for the '<em><b>Enum Attributes</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__ENUM_ATTRIBUTES = 7;

    /**
     * The feature id for the '<em><b>Containment Reference</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__CONTAINMENT_REFERENCE = 8;

    /**
     * The feature id for the '<em><b>Containment References</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__CONTAINMENT_REFERENCES = 9;

    /**
     * The feature id for the '<em><b>Reference</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__REFERENCE = 10;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT__REFERENCES = 11;

    /**
     * The number of structural features of the '<em>Test Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT_FEATURE_COUNT = 12;

    /**
     * The number of operations of the '<em>Test Element</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
     * <em>Test Enum</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl#getTestEnum()
     * @generated
     */
    int TEST_ENUM = 2;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot
     * <em>Test Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Test Root</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot
     * @generated
     */
    EClass getTestRoot();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot#getElements
     * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Elements</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot#getElements()
     * @see #getTestRoot()
     * @generated
     */
    EReference getTestRoot_Elements();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement
     * <em>Test Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
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
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttribute
     * <em>Int Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Int Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_IntAttribute();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttributes
     * <em>Int Attributes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Int Attributes</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getIntAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_IntAttributes();

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
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttribute
     * <em>Enum Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Enum Attribute</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttribute()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_EnumAttribute();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttributes
     * <em>Enum Attributes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Enum Attributes</em>
     *         '.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getEnumAttributes()
     * @see #getTestElement()
     * @generated
     */
    EAttribute getTestElement_EnumAttributes();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReference
     * <em>Containment Reference</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Containment Reference</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReference()
     * @see #getTestElement()
     * @generated
     */
    EReference getTestElement_ContainmentReference();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReferences
     * <em>Containment References</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Containment References</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getContainmentReferences()
     * @see #getTestElement()
     * @generated
     */
    EReference getTestElement_ContainmentReferences();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReference
     * <em>Reference</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Reference</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReference()
     * @see #getTestElement()
     * @generated
     */
    EReference getTestElement_Reference();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReferences
     * <em>References</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>References</em>'.
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement#getReferences()
     * @see #getTestElement()
     * @generated
     */
    EReference getTestElement_References();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum
     * <em>Test Enum</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
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
     *
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
        EClass TEST_ROOT = PropertiestestsPackage.eINSTANCE.getTestRoot();

        /**
         * The meta object literal for the '<em><b>Elements</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference TEST_ROOT__ELEMENTS = PropertiestestsPackage.eINSTANCE.getTestRoot_Elements();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl
         * <em>Test Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl
         * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.PropertiestestsPackageImpl#getTestElement()
         * @generated
         */
        EClass TEST_ELEMENT = PropertiestestsPackage.eINSTANCE.getTestElement();

        /**
         * The meta object literal for the '<em><b>String Attribute</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEST_ELEMENT__STRING_ATTRIBUTE = PropertiestestsPackage.eINSTANCE.getTestElement_StringAttribute();

        /**
         * The meta object literal for the '<em><b>String Attributes</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEST_ELEMENT__STRING_ATTRIBUTES = PropertiestestsPackage.eINSTANCE.getTestElement_StringAttributes();

        /**
         * The meta object literal for the '<em><b>Int Attribute</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEST_ELEMENT__INT_ATTRIBUTE = PropertiestestsPackage.eINSTANCE.getTestElement_IntAttribute();

        /**
         * The meta object literal for the '<em><b>Int Attributes</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEST_ELEMENT__INT_ATTRIBUTES = PropertiestestsPackage.eINSTANCE.getTestElement_IntAttributes();

        /**
         * The meta object literal for the '<em><b>Boolean Attribute</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEST_ELEMENT__BOOLEAN_ATTRIBUTE = PropertiestestsPackage.eINSTANCE.getTestElement_BooleanAttribute();

        /**
         * The meta object literal for the '<em><b>Boolean Attributes</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEST_ELEMENT__BOOLEAN_ATTRIBUTES = PropertiestestsPackage.eINSTANCE.getTestElement_BooleanAttributes();

        /**
         * The meta object literal for the '<em><b>Enum Attribute</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEST_ELEMENT__ENUM_ATTRIBUTE = PropertiestestsPackage.eINSTANCE.getTestElement_EnumAttribute();

        /**
         * The meta object literal for the '<em><b>Enum Attributes</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEST_ELEMENT__ENUM_ATTRIBUTES = PropertiestestsPackage.eINSTANCE.getTestElement_EnumAttributes();

        /**
         * The meta object literal for the '
         * <em><b>Containment Reference</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEST_ELEMENT__CONTAINMENT_REFERENCE = PropertiestestsPackage.eINSTANCE.getTestElement_ContainmentReference();

        /**
         * The meta object literal for the '
         * <em><b>Containment References</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEST_ELEMENT__CONTAINMENT_REFERENCES = PropertiestestsPackage.eINSTANCE.getTestElement_ContainmentReferences();

        /**
         * The meta object literal for the '<em><b>Reference</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEST_ELEMENT__REFERENCE = PropertiestestsPackage.eINSTANCE.getTestElement_Reference();

        /**
         * The meta object literal for the '<em><b>References</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEST_ELEMENT__REFERENCES = PropertiestestsPackage.eINSTANCE.getTestElement_References();

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
        EEnum TEST_ENUM = PropertiestestsPackage.eINSTANCE.getTestEnum();

    }

} // PropertiestestsPackage
