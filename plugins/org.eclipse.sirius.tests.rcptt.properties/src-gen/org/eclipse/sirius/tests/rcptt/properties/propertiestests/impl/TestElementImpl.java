/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getStringAttribute <em>String Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getStringAttributes <em>String Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getStringAttributeMandatory <em>String Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getStringAttributeMultiline <em>String Attribute Multiline</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getStringAttributeMultilineMandatory <em>String Attribute Multiline Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getIntAttribute <em>Int Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getIntAttributes <em>Int Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getIntAttributeMandatory <em>Int Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#isBooleanAttribute <em>Boolean Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getBooleanAttributes <em>Boolean Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#isBooleanAttributeMandatory <em>Boolean Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getEnumAttribute <em>Enum Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getEnumAttributes <em>Enum Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getEnumAttributeMandatory <em>Enum Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getContainmentReference <em>Containment Reference</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getContainmentReferences <em>Containment References</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getContainmentReferenceMandatory <em>Containment Reference Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getReferences <em>References</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getReferenceMandatory <em>Reference Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getCharAttribute <em>Char Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getCharAttributes <em>Char Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getCharAttributeMandatory <em>Char Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getDateAttribute <em>Date Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getDateAttributes <em>Date Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getDateAttributeMandatory <em>Date Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getDoubleAttribute <em>Double Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getDoubleAttributes <em>Double Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getDoubleAttributeMandatory <em>Double Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getFloatAttribute <em>Float Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getFloatAttributes <em>Float Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getFloatAttributeMandatory <em>Float Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getLongAttribute <em>Long Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getLongAttributes <em>Long Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getLongAttributeMandatory <em>Long Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getShortAttribute <em>Short Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getShortAttributes <em>Short Attributes</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getShortAttributeMandatory <em>Short Attribute Mandatory</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getDerivedAttribute <em>Derived Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getTransientAttribute <em>Transient Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getNonChangeableAttribute <em>Non Changeable Attribute</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getOptionalFeature <em>Optional Feature</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestElementImpl extends MinimalEObjectImpl.Container implements TestElement {
    /**
     * The default value of the '{@link #getStringAttribute() <em>String Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringAttribute()
     * @generated
     * @ordered
     */
    protected static final String STRING_ATTRIBUTE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStringAttribute() <em>String Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringAttribute()
     * @generated
     * @ordered
     */
    protected String stringAttribute = STRING_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getStringAttributes() <em>String Attributes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringAttributes()
     * @generated
     * @ordered
     */
    protected EList<String> stringAttributes;

    /**
     * The default value of the '{@link #getStringAttributeMandatory() <em>String Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringAttributeMandatory()
     * @generated
     * @ordered
     */
    protected static final String STRING_ATTRIBUTE_MANDATORY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStringAttributeMandatory() <em>String Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringAttributeMandatory()
     * @generated
     * @ordered
     */
    protected String stringAttributeMandatory = STRING_ATTRIBUTE_MANDATORY_EDEFAULT;

    /**
     * The default value of the '{@link #getStringAttributeMultiline() <em>String Attribute Multiline</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringAttributeMultiline()
     * @generated
     * @ordered
     */
    protected static final String STRING_ATTRIBUTE_MULTILINE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStringAttributeMultiline() <em>String Attribute Multiline</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringAttributeMultiline()
     * @generated
     * @ordered
     */
    protected String stringAttributeMultiline = STRING_ATTRIBUTE_MULTILINE_EDEFAULT;

    /**
     * The default value of the '{@link #getStringAttributeMultilineMandatory() <em>String Attribute Multiline Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringAttributeMultilineMandatory()
     * @generated
     * @ordered
     */
    protected static final String STRING_ATTRIBUTE_MULTILINE_MANDATORY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStringAttributeMultilineMandatory() <em>String Attribute Multiline Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStringAttributeMultilineMandatory()
     * @generated
     * @ordered
     */
    protected String stringAttributeMultilineMandatory = STRING_ATTRIBUTE_MULTILINE_MANDATORY_EDEFAULT;

    /**
     * The default value of the '{@link #getIntAttribute() <em>Int Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntAttribute()
     * @generated
     * @ordered
     */
    protected static final int INT_ATTRIBUTE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getIntAttribute() <em>Int Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntAttribute()
     * @generated
     * @ordered
     */
    protected int intAttribute = INT_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getIntAttributes() <em>Int Attributes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntAttributes()
     * @generated
     * @ordered
     */
    protected EList<Integer> intAttributes;

    /**
     * The default value of the '{@link #getIntAttributeMandatory() <em>Int Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntAttributeMandatory()
     * @generated
     * @ordered
     */
    protected static final int INT_ATTRIBUTE_MANDATORY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getIntAttributeMandatory() <em>Int Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntAttributeMandatory()
     * @generated
     * @ordered
     */
    protected int intAttributeMandatory = INT_ATTRIBUTE_MANDATORY_EDEFAULT;

    /**
     * The default value of the '{@link #isBooleanAttribute() <em>Boolean Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBooleanAttribute()
     * @generated
     * @ordered
     */
    protected static final boolean BOOLEAN_ATTRIBUTE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isBooleanAttribute() <em>Boolean Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBooleanAttribute()
     * @generated
     * @ordered
     */
    protected boolean booleanAttribute = BOOLEAN_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getBooleanAttributes() <em>Boolean Attributes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBooleanAttributes()
     * @generated
     * @ordered
     */
    protected EList<Boolean> booleanAttributes;

    /**
     * The default value of the '{@link #isBooleanAttributeMandatory() <em>Boolean Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBooleanAttributeMandatory()
     * @generated
     * @ordered
     */
    protected static final boolean BOOLEAN_ATTRIBUTE_MANDATORY_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isBooleanAttributeMandatory() <em>Boolean Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isBooleanAttributeMandatory()
     * @generated
     * @ordered
     */
    protected boolean booleanAttributeMandatory = BOOLEAN_ATTRIBUTE_MANDATORY_EDEFAULT;

    /**
     * The default value of the '{@link #getEnumAttribute() <em>Enum Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEnumAttribute()
     * @generated
     * @ordered
     */
    protected static final TestEnum ENUM_ATTRIBUTE_EDEFAULT = TestEnum.LITERAL1;

    /**
     * The cached value of the '{@link #getEnumAttribute() <em>Enum Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEnumAttribute()
     * @generated
     * @ordered
     */
    protected TestEnum enumAttribute = ENUM_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getEnumAttributes() <em>Enum Attributes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEnumAttributes()
     * @generated
     * @ordered
     */
    protected EList<TestEnum> enumAttributes;

    /**
     * The default value of the '{@link #getEnumAttributeMandatory() <em>Enum Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEnumAttributeMandatory()
     * @generated
     * @ordered
     */
    protected static final TestEnum ENUM_ATTRIBUTE_MANDATORY_EDEFAULT = TestEnum.LITERAL1;

    /**
     * The cached value of the '{@link #getEnumAttributeMandatory() <em>Enum Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEnumAttributeMandatory()
     * @generated
     * @ordered
     */
    protected TestEnum enumAttributeMandatory = ENUM_ATTRIBUTE_MANDATORY_EDEFAULT;

    /**
     * The cached value of the '{@link #getContainmentReference() <em>Containment Reference</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContainmentReference()
     * @generated
     * @ordered
     */
    protected TestElement containmentReference;

    /**
     * The cached value of the '{@link #getContainmentReferences() <em>Containment References</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContainmentReferences()
     * @generated
     * @ordered
     */
    protected EList<TestElement> containmentReferences;

    /**
     * The cached value of the '{@link #getContainmentReferenceMandatory() <em>Containment Reference Mandatory</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContainmentReferenceMandatory()
     * @generated
     * @ordered
     */
    protected TestElement containmentReferenceMandatory;

    /**
     * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReference()
     * @generated
     * @ordered
     */
    protected TestElement reference;

    /**
     * The cached value of the '{@link #getReferences() <em>References</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReferences()
     * @generated
     * @ordered
     */
    protected EList<TestElement> references;

    /**
     * The cached value of the '{@link #getReferenceMandatory() <em>Reference Mandatory</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReferenceMandatory()
     * @generated
     * @ordered
     */
    protected TestElement referenceMandatory;

    /**
     * The default value of the '{@link #getCharAttribute() <em>Char Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCharAttribute()
     * @generated
     * @ordered
     */
    protected static final char CHAR_ATTRIBUTE_EDEFAULT = '\u0000';

    /**
     * The cached value of the '{@link #getCharAttribute() <em>Char Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCharAttribute()
     * @generated
     * @ordered
     */
    protected char charAttribute = CHAR_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getCharAttributes() <em>Char Attributes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCharAttributes()
     * @generated
     * @ordered
     */
    protected EList<Character> charAttributes;

    /**
     * The default value of the '{@link #getCharAttributeMandatory() <em>Char Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCharAttributeMandatory()
     * @generated
     * @ordered
     */
    protected static final char CHAR_ATTRIBUTE_MANDATORY_EDEFAULT = '\u0000';

    /**
     * The cached value of the '{@link #getCharAttributeMandatory() <em>Char Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCharAttributeMandatory()
     * @generated
     * @ordered
     */
    protected char charAttributeMandatory = CHAR_ATTRIBUTE_MANDATORY_EDEFAULT;

    /**
     * The default value of the '{@link #getDateAttribute() <em>Date Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDateAttribute()
     * @generated
     * @ordered
     */
    protected static final Date DATE_ATTRIBUTE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDateAttribute() <em>Date Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDateAttribute()
     * @generated
     * @ordered
     */
    protected Date dateAttribute = DATE_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getDateAttributes() <em>Date Attributes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDateAttributes()
     * @generated
     * @ordered
     */
    protected EList<Date> dateAttributes;

    /**
     * The default value of the '{@link #getDateAttributeMandatory() <em>Date Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDateAttributeMandatory()
     * @generated
     * @ordered
     */
    protected static final Date DATE_ATTRIBUTE_MANDATORY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDateAttributeMandatory() <em>Date Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDateAttributeMandatory()
     * @generated
     * @ordered
     */
    protected Date dateAttributeMandatory = DATE_ATTRIBUTE_MANDATORY_EDEFAULT;

    /**
     * The default value of the '{@link #getDoubleAttribute() <em>Double Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDoubleAttribute()
     * @generated
     * @ordered
     */
    protected static final double DOUBLE_ATTRIBUTE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getDoubleAttribute() <em>Double Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDoubleAttribute()
     * @generated
     * @ordered
     */
    protected double doubleAttribute = DOUBLE_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getDoubleAttributes() <em>Double Attributes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDoubleAttributes()
     * @generated
     * @ordered
     */
    protected EList<Double> doubleAttributes;

    /**
     * The default value of the '{@link #getDoubleAttributeMandatory() <em>Double Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDoubleAttributeMandatory()
     * @generated
     * @ordered
     */
    protected static final double DOUBLE_ATTRIBUTE_MANDATORY_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getDoubleAttributeMandatory() <em>Double Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDoubleAttributeMandatory()
     * @generated
     * @ordered
     */
    protected double doubleAttributeMandatory = DOUBLE_ATTRIBUTE_MANDATORY_EDEFAULT;

    /**
     * The default value of the '{@link #getFloatAttribute() <em>Float Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFloatAttribute()
     * @generated
     * @ordered
     */
    protected static final float FLOAT_ATTRIBUTE_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getFloatAttribute() <em>Float Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFloatAttribute()
     * @generated
     * @ordered
     */
    protected float floatAttribute = FLOAT_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getFloatAttributes() <em>Float Attributes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFloatAttributes()
     * @generated
     * @ordered
     */
    protected EList<Float> floatAttributes;

    /**
     * The default value of the '{@link #getFloatAttributeMandatory() <em>Float Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFloatAttributeMandatory()
     * @generated
     * @ordered
     */
    protected static final float FLOAT_ATTRIBUTE_MANDATORY_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getFloatAttributeMandatory() <em>Float Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFloatAttributeMandatory()
     * @generated
     * @ordered
     */
    protected float floatAttributeMandatory = FLOAT_ATTRIBUTE_MANDATORY_EDEFAULT;

    /**
     * The default value of the '{@link #getLongAttribute() <em>Long Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLongAttribute()
     * @generated
     * @ordered
     */
    protected static final long LONG_ATTRIBUTE_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getLongAttribute() <em>Long Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLongAttribute()
     * @generated
     * @ordered
     */
    protected long longAttribute = LONG_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getLongAttributes() <em>Long Attributes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLongAttributes()
     * @generated
     * @ordered
     */
    protected EList<Long> longAttributes;

    /**
     * The default value of the '{@link #getLongAttributeMandatory() <em>Long Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLongAttributeMandatory()
     * @generated
     * @ordered
     */
    protected static final long LONG_ATTRIBUTE_MANDATORY_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getLongAttributeMandatory() <em>Long Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLongAttributeMandatory()
     * @generated
     * @ordered
     */
    protected long longAttributeMandatory = LONG_ATTRIBUTE_MANDATORY_EDEFAULT;

    /**
     * The default value of the '{@link #getShortAttribute() <em>Short Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShortAttribute()
     * @generated
     * @ordered
     */
    protected static final short SHORT_ATTRIBUTE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getShortAttribute() <em>Short Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShortAttribute()
     * @generated
     * @ordered
     */
    protected short shortAttribute = SHORT_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getShortAttributes() <em>Short Attributes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShortAttributes()
     * @generated
     * @ordered
     */
    protected EList<Short> shortAttributes;

    /**
     * The default value of the '{@link #getShortAttributeMandatory() <em>Short Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShortAttributeMandatory()
     * @generated
     * @ordered
     */
    protected static final short SHORT_ATTRIBUTE_MANDATORY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getShortAttributeMandatory() <em>Short Attribute Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShortAttributeMandatory()
     * @generated
     * @ordered
     */
    protected short shortAttributeMandatory = SHORT_ATTRIBUTE_MANDATORY_EDEFAULT;

    /**
     * The default value of the '{@link #getDerivedAttribute() <em>Derived Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDerivedAttribute()
     * @generated
     * @ordered
     */
    protected static final String DERIVED_ATTRIBUTE_EDEFAULT = "";

    /**
     * The default value of the '{@link #getTransientAttribute() <em>Transient Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransientAttribute()
     * @generated
     * @ordered
     */
    protected static final String TRANSIENT_ATTRIBUTE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTransientAttribute() <em>Transient Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransientAttribute()
     * @generated
     * @ordered
     */
    protected String transientAttribute = TRANSIENT_ATTRIBUTE_EDEFAULT;

    /**
     * The default value of the '{@link #getNonChangeableAttribute() <em>Non Changeable Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNonChangeableAttribute()
     * @generated
     * @ordered
     */
    protected static final String NON_CHANGEABLE_ATTRIBUTE_EDEFAULT = "NonChangeable";

    /**
     * The cached value of the '{@link #getNonChangeableAttribute() <em>Non Changeable Attribute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNonChangeableAttribute()
     * @generated
     * @ordered
     */
    protected String nonChangeableAttribute = NON_CHANGEABLE_ATTRIBUTE_EDEFAULT;

    /**
     * The default value of the '{@link #getOptionalFeature() <em>Optional Feature</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOptionalFeature()
     * @generated
     * @ordered
     */
    protected static final String OPTIONAL_FEATURE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOptionalFeature() <em>Optional Feature</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOptionalFeature()
     * @generated
     * @ordered
     */
    protected String optionalFeature = OPTIONAL_FEATURE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TestElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiestestsPackage.Literals.TEST_ELEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStringAttribute() {
        return stringAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStringAttribute(String newStringAttribute) {
        String oldStringAttribute = stringAttribute;
        stringAttribute = newStringAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE, oldStringAttribute, stringAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getStringAttributes() {
        if (stringAttributes == null) {
            stringAttributes = new EDataTypeUniqueEList<String>(String.class, this, PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES);
        }
        return stringAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStringAttributeMandatory() {
        return stringAttributeMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStringAttributeMandatory(String newStringAttributeMandatory) {
        String oldStringAttributeMandatory = stringAttributeMandatory;
        stringAttributeMandatory = newStringAttributeMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MANDATORY, oldStringAttributeMandatory, stringAttributeMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStringAttributeMultiline() {
        return stringAttributeMultiline;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStringAttributeMultiline(String newStringAttributeMultiline) {
        String oldStringAttributeMultiline = stringAttributeMultiline;
        stringAttributeMultiline = newStringAttributeMultiline;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE, oldStringAttributeMultiline, stringAttributeMultiline));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStringAttributeMultilineMandatory() {
        return stringAttributeMultilineMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStringAttributeMultilineMandatory(String newStringAttributeMultilineMandatory) {
        String oldStringAttributeMultilineMandatory = stringAttributeMultilineMandatory;
        stringAttributeMultilineMandatory = newStringAttributeMultilineMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE_MANDATORY, oldStringAttributeMultilineMandatory, stringAttributeMultilineMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getIntAttribute() {
        return intAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntAttribute(int newIntAttribute) {
        int oldIntAttribute = intAttribute;
        intAttribute = newIntAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE, oldIntAttribute, intAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Integer> getIntAttributes() {
        if (intAttributes == null) {
            intAttributes = new EDataTypeUniqueEList<Integer>(Integer.class, this, PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES);
        }
        return intAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getIntAttributeMandatory() {
        return intAttributeMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntAttributeMandatory(int newIntAttributeMandatory) {
        int oldIntAttributeMandatory = intAttributeMandatory;
        intAttributeMandatory = newIntAttributeMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE_MANDATORY, oldIntAttributeMandatory, intAttributeMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isBooleanAttribute() {
        return booleanAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBooleanAttribute(boolean newBooleanAttribute) {
        boolean oldBooleanAttribute = booleanAttribute;
        booleanAttribute = newBooleanAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE, oldBooleanAttribute, booleanAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Boolean> getBooleanAttributes() {
        if (booleanAttributes == null) {
            booleanAttributes = new EDataTypeUniqueEList<Boolean>(Boolean.class, this, PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES);
        }
        return booleanAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isBooleanAttributeMandatory() {
        return booleanAttributeMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBooleanAttributeMandatory(boolean newBooleanAttributeMandatory) {
        boolean oldBooleanAttributeMandatory = booleanAttributeMandatory;
        booleanAttributeMandatory = newBooleanAttributeMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE_MANDATORY, oldBooleanAttributeMandatory, booleanAttributeMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestEnum getEnumAttribute() {
        return enumAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEnumAttribute(TestEnum newEnumAttribute) {
        TestEnum oldEnumAttribute = enumAttribute;
        enumAttribute = newEnumAttribute == null ? ENUM_ATTRIBUTE_EDEFAULT : newEnumAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE, oldEnumAttribute, enumAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TestEnum> getEnumAttributes() {
        if (enumAttributes == null) {
            enumAttributes = new EDataTypeUniqueEList<TestEnum>(TestEnum.class, this, PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES);
        }
        return enumAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestEnum getEnumAttributeMandatory() {
        return enumAttributeMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEnumAttributeMandatory(TestEnum newEnumAttributeMandatory) {
        TestEnum oldEnumAttributeMandatory = enumAttributeMandatory;
        enumAttributeMandatory = newEnumAttributeMandatory == null ? ENUM_ATTRIBUTE_MANDATORY_EDEFAULT : newEnumAttributeMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE_MANDATORY, oldEnumAttributeMandatory, enumAttributeMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestElement getContainmentReference() {
        return containmentReference;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetContainmentReference(TestElement newContainmentReference, NotificationChain msgs) {
        TestElement oldContainmentReference = containmentReference;
        containmentReference = newContainmentReference;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE, oldContainmentReference, newContainmentReference);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContainmentReference(TestElement newContainmentReference) {
        if (newContainmentReference != containmentReference) {
            NotificationChain msgs = null;
            if (containmentReference != null)
                msgs = ((InternalEObject)containmentReference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE, null, msgs);
            if (newContainmentReference != null)
                msgs = ((InternalEObject)newContainmentReference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE, null, msgs);
            msgs = basicSetContainmentReference(newContainmentReference, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE, newContainmentReference, newContainmentReference));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TestElement> getContainmentReferences() {
        if (containmentReferences == null) {
            containmentReferences = new EObjectContainmentEList<TestElement>(TestElement.class, this, PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES);
        }
        return containmentReferences;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestElement getContainmentReferenceMandatory() {
        return containmentReferenceMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetContainmentReferenceMandatory(TestElement newContainmentReferenceMandatory, NotificationChain msgs) {
        TestElement oldContainmentReferenceMandatory = containmentReferenceMandatory;
        containmentReferenceMandatory = newContainmentReferenceMandatory;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY, oldContainmentReferenceMandatory, newContainmentReferenceMandatory);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContainmentReferenceMandatory(TestElement newContainmentReferenceMandatory) {
        if (newContainmentReferenceMandatory != containmentReferenceMandatory) {
            NotificationChain msgs = null;
            if (containmentReferenceMandatory != null)
                msgs = ((InternalEObject)containmentReferenceMandatory).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY, null, msgs);
            if (newContainmentReferenceMandatory != null)
                msgs = ((InternalEObject)newContainmentReferenceMandatory).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY, null, msgs);
            msgs = basicSetContainmentReferenceMandatory(newContainmentReferenceMandatory, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY, newContainmentReferenceMandatory, newContainmentReferenceMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestElement getReference() {
        if (reference != null && reference.eIsProxy()) {
            InternalEObject oldReference = (InternalEObject)reference;
            reference = (TestElement)eResolveProxy(oldReference);
            if (reference != oldReference) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiestestsPackage.TEST_ELEMENT__REFERENCE, oldReference, reference));
            }
        }
        return reference;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestElement basicGetReference() {
        return reference;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReference(TestElement newReference) {
        TestElement oldReference = reference;
        reference = newReference;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__REFERENCE, oldReference, reference));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TestElement> getReferences() {
        if (references == null) {
            references = new EObjectResolvingEList<TestElement>(TestElement.class, this, PropertiestestsPackage.TEST_ELEMENT__REFERENCES);
        }
        return references;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestElement getReferenceMandatory() {
        if (referenceMandatory != null && referenceMandatory.eIsProxy()) {
            InternalEObject oldReferenceMandatory = (InternalEObject)referenceMandatory;
            referenceMandatory = (TestElement)eResolveProxy(oldReferenceMandatory);
            if (referenceMandatory != oldReferenceMandatory) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiestestsPackage.TEST_ELEMENT__REFERENCE_MANDATORY, oldReferenceMandatory, referenceMandatory));
            }
        }
        return referenceMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestElement basicGetReferenceMandatory() {
        return referenceMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReferenceMandatory(TestElement newReferenceMandatory) {
        TestElement oldReferenceMandatory = referenceMandatory;
        referenceMandatory = newReferenceMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__REFERENCE_MANDATORY, oldReferenceMandatory, referenceMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public char getCharAttribute() {
        return charAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCharAttribute(char newCharAttribute) {
        char oldCharAttribute = charAttribute;
        charAttribute = newCharAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTE, oldCharAttribute, charAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Character> getCharAttributes() {
        if (charAttributes == null) {
            charAttributes = new EDataTypeUniqueEList<Character>(Character.class, this, PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTES);
        }
        return charAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public char getCharAttributeMandatory() {
        return charAttributeMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCharAttributeMandatory(char newCharAttributeMandatory) {
        char oldCharAttributeMandatory = charAttributeMandatory;
        charAttributeMandatory = newCharAttributeMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTE_MANDATORY, oldCharAttributeMandatory, charAttributeMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Date getDateAttribute() {
        return dateAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDateAttribute(Date newDateAttribute) {
        Date oldDateAttribute = dateAttribute;
        dateAttribute = newDateAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTE, oldDateAttribute, dateAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Date> getDateAttributes() {
        if (dateAttributes == null) {
            dateAttributes = new EDataTypeUniqueEList<Date>(Date.class, this, PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTES);
        }
        return dateAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Date getDateAttributeMandatory() {
        return dateAttributeMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDateAttributeMandatory(Date newDateAttributeMandatory) {
        Date oldDateAttributeMandatory = dateAttributeMandatory;
        dateAttributeMandatory = newDateAttributeMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTE_MANDATORY, oldDateAttributeMandatory, dateAttributeMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getDoubleAttribute() {
        return doubleAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDoubleAttribute(double newDoubleAttribute) {
        double oldDoubleAttribute = doubleAttribute;
        doubleAttribute = newDoubleAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTE, oldDoubleAttribute, doubleAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Double> getDoubleAttributes() {
        if (doubleAttributes == null) {
            doubleAttributes = new EDataTypeUniqueEList<Double>(Double.class, this, PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTES);
        }
        return doubleAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getDoubleAttributeMandatory() {
        return doubleAttributeMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDoubleAttributeMandatory(double newDoubleAttributeMandatory) {
        double oldDoubleAttributeMandatory = doubleAttributeMandatory;
        doubleAttributeMandatory = newDoubleAttributeMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTE_MANDATORY, oldDoubleAttributeMandatory, doubleAttributeMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getFloatAttribute() {
        return floatAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFloatAttribute(float newFloatAttribute) {
        float oldFloatAttribute = floatAttribute;
        floatAttribute = newFloatAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTE, oldFloatAttribute, floatAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Float> getFloatAttributes() {
        if (floatAttributes == null) {
            floatAttributes = new EDataTypeUniqueEList<Float>(Float.class, this, PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTES);
        }
        return floatAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getFloatAttributeMandatory() {
        return floatAttributeMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFloatAttributeMandatory(float newFloatAttributeMandatory) {
        float oldFloatAttributeMandatory = floatAttributeMandatory;
        floatAttributeMandatory = newFloatAttributeMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTE_MANDATORY, oldFloatAttributeMandatory, floatAttributeMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public long getLongAttribute() {
        return longAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLongAttribute(long newLongAttribute) {
        long oldLongAttribute = longAttribute;
        longAttribute = newLongAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTE, oldLongAttribute, longAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Long> getLongAttributes() {
        if (longAttributes == null) {
            longAttributes = new EDataTypeUniqueEList<Long>(Long.class, this, PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTES);
        }
        return longAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public long getLongAttributeMandatory() {
        return longAttributeMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLongAttributeMandatory(long newLongAttributeMandatory) {
        long oldLongAttributeMandatory = longAttributeMandatory;
        longAttributeMandatory = newLongAttributeMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTE_MANDATORY, oldLongAttributeMandatory, longAttributeMandatory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public short getShortAttribute() {
        return shortAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setShortAttribute(short newShortAttribute) {
        short oldShortAttribute = shortAttribute;
        shortAttribute = newShortAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTE, oldShortAttribute, shortAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Short> getShortAttributes() {
        if (shortAttributes == null) {
            shortAttributes = new EDataTypeUniqueEList<Short>(Short.class, this, PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTES);
        }
        return shortAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public short getShortAttributeMandatory() {
        return shortAttributeMandatory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setShortAttributeMandatory(short newShortAttributeMandatory) {
        short oldShortAttributeMandatory = shortAttributeMandatory;
        shortAttributeMandatory = newShortAttributeMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTE_MANDATORY, oldShortAttributeMandatory, shortAttributeMandatory));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public String getDerivedAttribute() {
        return stringAttribute + " derived value";
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDerivedAttribute(String newDerivedAttribute) {
        // TODO: implement this method to set the 'Derived Attribute' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public String getTransientAttribute() {
        return stringAttribute.length() + " transient";
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTransientAttribute(String newTransientAttribute) {
        String oldTransientAttribute = transientAttribute;
        transientAttribute = newTransientAttribute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__TRANSIENT_ATTRIBUTE, oldTransientAttribute, transientAttribute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getNonChangeableAttribute() {
        return nonChangeableAttribute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getOptionalFeature() {
        return optionalFeature;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOptionalFeature(String newOptionalFeature) {
        String oldOptionalFeature = optionalFeature;
        optionalFeature = newOptionalFeature;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__OPTIONAL_FEATURE, oldOptionalFeature, optionalFeature));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
                return basicSetContainmentReference(null, msgs);
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
                return ((InternalEList<?>)getContainmentReferences()).basicRemove(otherEnd, msgs);
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY:
                return basicSetContainmentReferenceMandatory(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE:
                return getStringAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES:
                return getStringAttributes();
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MANDATORY:
                return getStringAttributeMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE:
                return getStringAttributeMultiline();
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE_MANDATORY:
                return getStringAttributeMultilineMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE:
                return getIntAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES:
                return getIntAttributes();
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE_MANDATORY:
                return getIntAttributeMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE:
                return isBooleanAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES:
                return getBooleanAttributes();
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE_MANDATORY:
                return isBooleanAttributeMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE:
                return getEnumAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES:
                return getEnumAttributes();
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE_MANDATORY:
                return getEnumAttributeMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
                return getContainmentReference();
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
                return getContainmentReferences();
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY:
                return getContainmentReferenceMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCE:
                if (resolve) return getReference();
                return basicGetReference();
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCES:
                return getReferences();
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCE_MANDATORY:
                if (resolve) return getReferenceMandatory();
                return basicGetReferenceMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTE:
                return getCharAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTES:
                return getCharAttributes();
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTE_MANDATORY:
                return getCharAttributeMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTE:
                return getDateAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTES:
                return getDateAttributes();
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTE_MANDATORY:
                return getDateAttributeMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTE:
                return getDoubleAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTES:
                return getDoubleAttributes();
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTE_MANDATORY:
                return getDoubleAttributeMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTE:
                return getFloatAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTES:
                return getFloatAttributes();
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTE_MANDATORY:
                return getFloatAttributeMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTE:
                return getLongAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTES:
                return getLongAttributes();
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTE_MANDATORY:
                return getLongAttributeMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTE:
                return getShortAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTES:
                return getShortAttributes();
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTE_MANDATORY:
                return getShortAttributeMandatory();
            case PropertiestestsPackage.TEST_ELEMENT__DERIVED_ATTRIBUTE:
                return getDerivedAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__TRANSIENT_ATTRIBUTE:
                return getTransientAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__NON_CHANGEABLE_ATTRIBUTE:
                return getNonChangeableAttribute();
            case PropertiestestsPackage.TEST_ELEMENT__OPTIONAL_FEATURE:
                return getOptionalFeature();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE:
                setStringAttribute((String)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES:
                getStringAttributes().clear();
                getStringAttributes().addAll((Collection<? extends String>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MANDATORY:
                setStringAttributeMandatory((String)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE:
                setStringAttributeMultiline((String)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE_MANDATORY:
                setStringAttributeMultilineMandatory((String)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE:
                setIntAttribute((Integer)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES:
                getIntAttributes().clear();
                getIntAttributes().addAll((Collection<? extends Integer>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE_MANDATORY:
                setIntAttributeMandatory((Integer)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE:
                setBooleanAttribute((Boolean)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES:
                getBooleanAttributes().clear();
                getBooleanAttributes().addAll((Collection<? extends Boolean>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE_MANDATORY:
                setBooleanAttributeMandatory((Boolean)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE:
                setEnumAttribute((TestEnum)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES:
                getEnumAttributes().clear();
                getEnumAttributes().addAll((Collection<? extends TestEnum>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE_MANDATORY:
                setEnumAttributeMandatory((TestEnum)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
                setContainmentReference((TestElement)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
                getContainmentReferences().clear();
                getContainmentReferences().addAll((Collection<? extends TestElement>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY:
                setContainmentReferenceMandatory((TestElement)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCE:
                setReference((TestElement)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCES:
                getReferences().clear();
                getReferences().addAll((Collection<? extends TestElement>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCE_MANDATORY:
                setReferenceMandatory((TestElement)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTE:
                setCharAttribute((Character)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTES:
                getCharAttributes().clear();
                getCharAttributes().addAll((Collection<? extends Character>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTE_MANDATORY:
                setCharAttributeMandatory((Character)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTE:
                setDateAttribute((Date)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTES:
                getDateAttributes().clear();
                getDateAttributes().addAll((Collection<? extends Date>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTE_MANDATORY:
                setDateAttributeMandatory((Date)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTE:
                setDoubleAttribute((Double)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTES:
                getDoubleAttributes().clear();
                getDoubleAttributes().addAll((Collection<? extends Double>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTE_MANDATORY:
                setDoubleAttributeMandatory((Double)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTE:
                setFloatAttribute((Float)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTES:
                getFloatAttributes().clear();
                getFloatAttributes().addAll((Collection<? extends Float>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTE_MANDATORY:
                setFloatAttributeMandatory((Float)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTE:
                setLongAttribute((Long)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTES:
                getLongAttributes().clear();
                getLongAttributes().addAll((Collection<? extends Long>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTE_MANDATORY:
                setLongAttributeMandatory((Long)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTE:
                setShortAttribute((Short)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTES:
                getShortAttributes().clear();
                getShortAttributes().addAll((Collection<? extends Short>)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTE_MANDATORY:
                setShortAttributeMandatory((Short)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DERIVED_ATTRIBUTE:
                setDerivedAttribute((String)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__TRANSIENT_ATTRIBUTE:
                setTransientAttribute((String)newValue);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__OPTIONAL_FEATURE:
                setOptionalFeature((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE:
                setStringAttribute(STRING_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES:
                getStringAttributes().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MANDATORY:
                setStringAttributeMandatory(STRING_ATTRIBUTE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE:
                setStringAttributeMultiline(STRING_ATTRIBUTE_MULTILINE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE_MANDATORY:
                setStringAttributeMultilineMandatory(STRING_ATTRIBUTE_MULTILINE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE:
                setIntAttribute(INT_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES:
                getIntAttributes().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE_MANDATORY:
                setIntAttributeMandatory(INT_ATTRIBUTE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE:
                setBooleanAttribute(BOOLEAN_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES:
                getBooleanAttributes().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE_MANDATORY:
                setBooleanAttributeMandatory(BOOLEAN_ATTRIBUTE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE:
                setEnumAttribute(ENUM_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES:
                getEnumAttributes().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE_MANDATORY:
                setEnumAttributeMandatory(ENUM_ATTRIBUTE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
                setContainmentReference((TestElement)null);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
                getContainmentReferences().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY:
                setContainmentReferenceMandatory((TestElement)null);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCE:
                setReference((TestElement)null);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCES:
                getReferences().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCE_MANDATORY:
                setReferenceMandatory((TestElement)null);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTE:
                setCharAttribute(CHAR_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTES:
                getCharAttributes().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTE_MANDATORY:
                setCharAttributeMandatory(CHAR_ATTRIBUTE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTE:
                setDateAttribute(DATE_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTES:
                getDateAttributes().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTE_MANDATORY:
                setDateAttributeMandatory(DATE_ATTRIBUTE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTE:
                setDoubleAttribute(DOUBLE_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTES:
                getDoubleAttributes().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTE_MANDATORY:
                setDoubleAttributeMandatory(DOUBLE_ATTRIBUTE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTE:
                setFloatAttribute(FLOAT_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTES:
                getFloatAttributes().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTE_MANDATORY:
                setFloatAttributeMandatory(FLOAT_ATTRIBUTE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTE:
                setLongAttribute(LONG_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTES:
                getLongAttributes().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTE_MANDATORY:
                setLongAttributeMandatory(LONG_ATTRIBUTE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTE:
                setShortAttribute(SHORT_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTES:
                getShortAttributes().clear();
                return;
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTE_MANDATORY:
                setShortAttributeMandatory(SHORT_ATTRIBUTE_MANDATORY_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__DERIVED_ATTRIBUTE:
                setDerivedAttribute(DERIVED_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__TRANSIENT_ATTRIBUTE:
                setTransientAttribute(TRANSIENT_ATTRIBUTE_EDEFAULT);
                return;
            case PropertiestestsPackage.TEST_ELEMENT__OPTIONAL_FEATURE:
                setOptionalFeature(OPTIONAL_FEATURE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE:
                return STRING_ATTRIBUTE_EDEFAULT == null ? stringAttribute != null : !STRING_ATTRIBUTE_EDEFAULT.equals(stringAttribute);
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES:
                return stringAttributes != null && !stringAttributes.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MANDATORY:
                return STRING_ATTRIBUTE_MANDATORY_EDEFAULT == null ? stringAttributeMandatory != null : !STRING_ATTRIBUTE_MANDATORY_EDEFAULT.equals(stringAttributeMandatory);
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE:
                return STRING_ATTRIBUTE_MULTILINE_EDEFAULT == null ? stringAttributeMultiline != null : !STRING_ATTRIBUTE_MULTILINE_EDEFAULT.equals(stringAttributeMultiline);
            case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE_MANDATORY:
                return STRING_ATTRIBUTE_MULTILINE_MANDATORY_EDEFAULT == null ? stringAttributeMultilineMandatory != null : !STRING_ATTRIBUTE_MULTILINE_MANDATORY_EDEFAULT.equals(stringAttributeMultilineMandatory);
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE:
                return intAttribute != INT_ATTRIBUTE_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES:
                return intAttributes != null && !intAttributes.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE_MANDATORY:
                return intAttributeMandatory != INT_ATTRIBUTE_MANDATORY_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE:
                return booleanAttribute != BOOLEAN_ATTRIBUTE_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES:
                return booleanAttributes != null && !booleanAttributes.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE_MANDATORY:
                return booleanAttributeMandatory != BOOLEAN_ATTRIBUTE_MANDATORY_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE:
                return enumAttribute != ENUM_ATTRIBUTE_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES:
                return enumAttributes != null && !enumAttributes.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE_MANDATORY:
                return enumAttributeMandatory != ENUM_ATTRIBUTE_MANDATORY_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
                return containmentReference != null;
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
                return containmentReferences != null && !containmentReferences.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY:
                return containmentReferenceMandatory != null;
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCE:
                return reference != null;
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCES:
                return references != null && !references.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__REFERENCE_MANDATORY:
                return referenceMandatory != null;
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTE:
                return charAttribute != CHAR_ATTRIBUTE_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTES:
                return charAttributes != null && !charAttributes.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__CHAR_ATTRIBUTE_MANDATORY:
                return charAttributeMandatory != CHAR_ATTRIBUTE_MANDATORY_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTE:
                return DATE_ATTRIBUTE_EDEFAULT == null ? dateAttribute != null : !DATE_ATTRIBUTE_EDEFAULT.equals(dateAttribute);
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTES:
                return dateAttributes != null && !dateAttributes.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__DATE_ATTRIBUTE_MANDATORY:
                return DATE_ATTRIBUTE_MANDATORY_EDEFAULT == null ? dateAttributeMandatory != null : !DATE_ATTRIBUTE_MANDATORY_EDEFAULT.equals(dateAttributeMandatory);
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTE:
                return doubleAttribute != DOUBLE_ATTRIBUTE_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTES:
                return doubleAttributes != null && !doubleAttributes.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__DOUBLE_ATTRIBUTE_MANDATORY:
                return doubleAttributeMandatory != DOUBLE_ATTRIBUTE_MANDATORY_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTE:
                return floatAttribute != FLOAT_ATTRIBUTE_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTES:
                return floatAttributes != null && !floatAttributes.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__FLOAT_ATTRIBUTE_MANDATORY:
                return floatAttributeMandatory != FLOAT_ATTRIBUTE_MANDATORY_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTE:
                return longAttribute != LONG_ATTRIBUTE_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTES:
                return longAttributes != null && !longAttributes.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__LONG_ATTRIBUTE_MANDATORY:
                return longAttributeMandatory != LONG_ATTRIBUTE_MANDATORY_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTE:
                return shortAttribute != SHORT_ATTRIBUTE_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTES:
                return shortAttributes != null && !shortAttributes.isEmpty();
            case PropertiestestsPackage.TEST_ELEMENT__SHORT_ATTRIBUTE_MANDATORY:
                return shortAttributeMandatory != SHORT_ATTRIBUTE_MANDATORY_EDEFAULT;
            case PropertiestestsPackage.TEST_ELEMENT__DERIVED_ATTRIBUTE:
                return DERIVED_ATTRIBUTE_EDEFAULT == null ? getDerivedAttribute() != null : !DERIVED_ATTRIBUTE_EDEFAULT.equals(getDerivedAttribute());
            case PropertiestestsPackage.TEST_ELEMENT__TRANSIENT_ATTRIBUTE:
                return TRANSIENT_ATTRIBUTE_EDEFAULT == null ? transientAttribute != null : !TRANSIENT_ATTRIBUTE_EDEFAULT.equals(transientAttribute);
            case PropertiestestsPackage.TEST_ELEMENT__NON_CHANGEABLE_ATTRIBUTE:
                return NON_CHANGEABLE_ATTRIBUTE_EDEFAULT == null ? nonChangeableAttribute != null : !NON_CHANGEABLE_ATTRIBUTE_EDEFAULT.equals(nonChangeableAttribute);
            case PropertiestestsPackage.TEST_ELEMENT__OPTIONAL_FEATURE:
                return OPTIONAL_FEATURE_EDEFAULT == null ? optionalFeature != null : !OPTIONAL_FEATURE_EDEFAULT.equals(optionalFeature);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (stringAttribute: ");
        result.append(stringAttribute);
        result.append(", stringAttributes: ");
        result.append(stringAttributes);
        result.append(", stringAttributeMandatory: ");
        result.append(stringAttributeMandatory);
        result.append(", stringAttributeMultiline: ");
        result.append(stringAttributeMultiline);
        result.append(", stringAttributeMultilineMandatory: ");
        result.append(stringAttributeMultilineMandatory);
        result.append(", intAttribute: ");
        result.append(intAttribute);
        result.append(", intAttributes: ");
        result.append(intAttributes);
        result.append(", intAttributeMandatory: ");
        result.append(intAttributeMandatory);
        result.append(", booleanAttribute: ");
        result.append(booleanAttribute);
        result.append(", booleanAttributes: ");
        result.append(booleanAttributes);
        result.append(", booleanAttributeMandatory: ");
        result.append(booleanAttributeMandatory);
        result.append(", enumAttribute: ");
        result.append(enumAttribute);
        result.append(", enumAttributes: ");
        result.append(enumAttributes);
        result.append(", enumAttributeMandatory: ");
        result.append(enumAttributeMandatory);
        result.append(", charAttribute: ");
        result.append(charAttribute);
        result.append(", charAttributes: ");
        result.append(charAttributes);
        result.append(", charAttributeMandatory: ");
        result.append(charAttributeMandatory);
        result.append(", dateAttribute: ");
        result.append(dateAttribute);
        result.append(", dateAttributes: ");
        result.append(dateAttributes);
        result.append(", dateAttributeMandatory: ");
        result.append(dateAttributeMandatory);
        result.append(", doubleAttribute: ");
        result.append(doubleAttribute);
        result.append(", doubleAttributes: ");
        result.append(doubleAttributes);
        result.append(", doubleAttributeMandatory: ");
        result.append(doubleAttributeMandatory);
        result.append(", floatAttribute: ");
        result.append(floatAttribute);
        result.append(", floatAttributes: ");
        result.append(floatAttributes);
        result.append(", floatAttributeMandatory: ");
        result.append(floatAttributeMandatory);
        result.append(", longAttribute: ");
        result.append(longAttribute);
        result.append(", longAttributes: ");
        result.append(longAttributes);
        result.append(", longAttributeMandatory: ");
        result.append(longAttributeMandatory);
        result.append(", shortAttribute: ");
        result.append(shortAttribute);
        result.append(", shortAttributes: ");
        result.append(shortAttributes);
        result.append(", shortAttributeMandatory: ");
        result.append(shortAttributeMandatory);
        result.append(", transientAttribute: ");
        result.append(transientAttribute);
        result.append(", nonChangeableAttribute: ");
        result.append(nonChangeableAttribute);
        result.append(", optionalFeature: ");
        result.append(optionalFeature);
        result.append(')');
        return result.toString();
    }

} //TestElementImpl
