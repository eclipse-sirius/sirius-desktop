/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl;

import java.util.Collection;

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
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Test Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getStringAttribute
 * <em>String Attribute</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getStringAttributes
 * <em>String Attributes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getIntAttribute
 * <em>Int Attribute</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getIntAttributes
 * <em>Int Attributes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#isBooleanAttribute
 * <em>Boolean Attribute</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getBooleanAttributes
 * <em>Boolean Attributes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getEnumAttribute
 * <em>Enum Attribute</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getEnumAttributes
 * <em>Enum Attributes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getContainmentReference
 * <em>Containment Reference</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getContainmentReferences
 * <em>Containment References</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getReference
 * <em>Reference</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestElementImpl#getReferences
 * <em>References</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestElementImpl extends MinimalEObjectImpl.Container implements TestElement {
    /**
     * The default value of the '{@link #getStringAttribute()
     * <em>String Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStringAttribute()
     * @generated
     * @ordered
     */
    protected static final String STRING_ATTRIBUTE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStringAttribute()
     * <em>String Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStringAttribute()
     * @generated
     * @ordered
     */
    protected String stringAttribute = TestElementImpl.STRING_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getStringAttributes()
     * <em>String Attributes</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getStringAttributes()
     * @generated
     * @ordered
     */
    protected EList<String> stringAttributes;

    /**
     * The default value of the '{@link #getIntAttribute()
     * <em>Int Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getIntAttribute()
     * @generated
     * @ordered
     */
    protected static final int INT_ATTRIBUTE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getIntAttribute() <em>Int Attribute</em>
     * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIntAttribute()
     * @generated
     * @ordered
     */
    protected int intAttribute = TestElementImpl.INT_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getIntAttributes()
     * <em>Int Attributes</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getIntAttributes()
     * @generated
     * @ordered
     */
    protected EList<Integer> intAttributes;

    /**
     * The default value of the '{@link #isBooleanAttribute()
     * <em>Boolean Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isBooleanAttribute()
     * @generated
     * @ordered
     */
    protected static final boolean BOOLEAN_ATTRIBUTE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isBooleanAttribute()
     * <em>Boolean Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isBooleanAttribute()
     * @generated
     * @ordered
     */
    protected boolean booleanAttribute = TestElementImpl.BOOLEAN_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getBooleanAttributes()
     * <em>Boolean Attributes</em>}' attribute list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getBooleanAttributes()
     * @generated
     * @ordered
     */
    protected EList<Boolean> booleanAttributes;

    /**
     * The default value of the '{@link #getEnumAttribute()
     * <em>Enum Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getEnumAttribute()
     * @generated
     * @ordered
     */
    protected static final TestEnum ENUM_ATTRIBUTE_EDEFAULT = TestEnum.LITERAL1;

    /**
     * The cached value of the '{@link #getEnumAttribute()
     * <em>Enum Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getEnumAttribute()
     * @generated
     * @ordered
     */
    protected TestEnum enumAttribute = TestElementImpl.ENUM_ATTRIBUTE_EDEFAULT;

    /**
     * The cached value of the '{@link #getEnumAttributes()
     * <em>Enum Attributes</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getEnumAttributes()
     * @generated
     * @ordered
     */
    protected EList<TestEnum> enumAttributes;

    /**
     * The cached value of the '{@link #getContainmentReference()
     * <em>Containment Reference</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getContainmentReference()
     * @generated
     * @ordered
     */
    protected TestElement containmentReference;

    /**
     * The cached value of the '{@link #getContainmentReferences()
     * <em>Containment References</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getContainmentReferences()
     * @generated
     * @ordered
     */
    protected EList<TestElement> containmentReferences;

    /**
     * The cached value of the '{@link #getReference() <em>Reference</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getReference()
     * @generated
     * @ordered
     */
    protected TestElement reference;

    /**
     * The cached value of the '{@link #getReferences() <em>References</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getReferences()
     * @generated
     * @ordered
     */
    protected EList<TestElement> references;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TestElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiestestsPackage.Literals.TEST_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getStringAttribute() {
        return stringAttribute;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStringAttribute(String newStringAttribute) {
        String oldStringAttribute = stringAttribute;
        stringAttribute = newStringAttribute;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE, oldStringAttribute, stringAttribute));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<String> getStringAttributes() {
        if (stringAttributes == null) {
            stringAttributes = new EDataTypeUniqueEList<String>(String.class, this, PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES);
        }
        return stringAttributes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getIntAttribute() {
        return intAttribute;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIntAttribute(int newIntAttribute) {
        int oldIntAttribute = intAttribute;
        intAttribute = newIntAttribute;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE, oldIntAttribute, intAttribute));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Integer> getIntAttributes() {
        if (intAttributes == null) {
            intAttributes = new EDataTypeUniqueEList<Integer>(Integer.class, this, PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES);
        }
        return intAttributes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isBooleanAttribute() {
        return booleanAttribute;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBooleanAttribute(boolean newBooleanAttribute) {
        boolean oldBooleanAttribute = booleanAttribute;
        booleanAttribute = newBooleanAttribute;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE, oldBooleanAttribute, booleanAttribute));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Boolean> getBooleanAttributes() {
        if (booleanAttributes == null) {
            booleanAttributes = new EDataTypeUniqueEList<Boolean>(Boolean.class, this, PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES);
        }
        return booleanAttributes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TestEnum getEnumAttribute() {
        return enumAttribute;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEnumAttribute(TestEnum newEnumAttribute) {
        TestEnum oldEnumAttribute = enumAttribute;
        enumAttribute = newEnumAttribute == null ? TestElementImpl.ENUM_ATTRIBUTE_EDEFAULT : newEnumAttribute;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE, oldEnumAttribute, enumAttribute));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<TestEnum> getEnumAttributes() {
        if (enumAttributes == null) {
            enumAttributes = new EDataTypeUniqueEList<TestEnum>(TestEnum.class, this, PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES);
        }
        return enumAttributes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TestElement getContainmentReference() {
        return containmentReference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetContainmentReference(TestElement newContainmentReference, NotificationChain msgs) {
        TestElement oldContainmentReference = containmentReference;
        containmentReference = newContainmentReference;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE, oldContainmentReference,
                    newContainmentReference);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setContainmentReference(TestElement newContainmentReference) {
        if (newContainmentReference != containmentReference) {
            NotificationChain msgs = null;
            if (containmentReference != null) {
                msgs = ((InternalEObject) containmentReference).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE, null, msgs);
            }
            if (newContainmentReference != null) {
                msgs = ((InternalEObject) newContainmentReference).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE, null, msgs);
            }
            msgs = basicSetContainmentReference(newContainmentReference, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE, newContainmentReference, newContainmentReference));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<TestElement> getContainmentReferences() {
        if (containmentReferences == null) {
            containmentReferences = new EObjectContainmentEList<TestElement>(TestElement.class, this, PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES);
        }
        return containmentReferences;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TestElement getReference() {
        if (reference != null && reference.eIsProxy()) {
            InternalEObject oldReference = (InternalEObject) reference;
            reference = (TestElement) eResolveProxy(oldReference);
            if (reference != oldReference) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiestestsPackage.TEST_ELEMENT__REFERENCE, oldReference, reference));
                }
            }
        }
        return reference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TestElement basicGetReference() {
        return reference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setReference(TestElement newReference) {
        TestElement oldReference = reference;
        reference = newReference;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiestestsPackage.TEST_ELEMENT__REFERENCE, oldReference, reference));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<TestElement> getReferences() {
        if (references == null) {
            references = new EObjectResolvingEList<TestElement>(TestElement.class, this, PropertiestestsPackage.TEST_ELEMENT__REFERENCES);
        }
        return references;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
            return basicSetContainmentReference(null, msgs);
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
            return ((InternalEList<?>) getContainmentReferences()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE:
            return getStringAttribute();
        case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES:
            return getStringAttributes();
        case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE:
            return getIntAttribute();
        case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES:
            return getIntAttributes();
        case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE:
            return isBooleanAttribute();
        case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES:
            return getBooleanAttributes();
        case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE:
            return getEnumAttribute();
        case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES:
            return getEnumAttributes();
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
            return getContainmentReference();
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
            return getContainmentReferences();
        case PropertiestestsPackage.TEST_ELEMENT__REFERENCE:
            if (resolve) {
                return getReference();
            }
            return basicGetReference();
        case PropertiestestsPackage.TEST_ELEMENT__REFERENCES:
            return getReferences();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE:
            setStringAttribute((String) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES:
            getStringAttributes().clear();
            getStringAttributes().addAll((Collection<? extends String>) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE:
            setIntAttribute((Integer) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES:
            getIntAttributes().clear();
            getIntAttributes().addAll((Collection<? extends Integer>) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE:
            setBooleanAttribute((Boolean) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES:
            getBooleanAttributes().clear();
            getBooleanAttributes().addAll((Collection<? extends Boolean>) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE:
            setEnumAttribute((TestEnum) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES:
            getEnumAttributes().clear();
            getEnumAttributes().addAll((Collection<? extends TestEnum>) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
            setContainmentReference((TestElement) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
            getContainmentReferences().clear();
            getContainmentReferences().addAll((Collection<? extends TestElement>) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__REFERENCE:
            setReference((TestElement) newValue);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__REFERENCES:
            getReferences().clear();
            getReferences().addAll((Collection<? extends TestElement>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE:
            setStringAttribute(TestElementImpl.STRING_ATTRIBUTE_EDEFAULT);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES:
            getStringAttributes().clear();
            return;
        case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE:
            setIntAttribute(TestElementImpl.INT_ATTRIBUTE_EDEFAULT);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES:
            getIntAttributes().clear();
            return;
        case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE:
            setBooleanAttribute(TestElementImpl.BOOLEAN_ATTRIBUTE_EDEFAULT);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES:
            getBooleanAttributes().clear();
            return;
        case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE:
            setEnumAttribute(TestElementImpl.ENUM_ATTRIBUTE_EDEFAULT);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES:
            getEnumAttributes().clear();
            return;
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
            setContainmentReference((TestElement) null);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
            getContainmentReferences().clear();
            return;
        case PropertiestestsPackage.TEST_ELEMENT__REFERENCE:
            setReference((TestElement) null);
            return;
        case PropertiestestsPackage.TEST_ELEMENT__REFERENCES:
            getReferences().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE:
            return TestElementImpl.STRING_ATTRIBUTE_EDEFAULT == null ? stringAttribute != null : !TestElementImpl.STRING_ATTRIBUTE_EDEFAULT.equals(stringAttribute);
        case PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES:
            return stringAttributes != null && !stringAttributes.isEmpty();
        case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE:
            return intAttribute != TestElementImpl.INT_ATTRIBUTE_EDEFAULT;
        case PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES:
            return intAttributes != null && !intAttributes.isEmpty();
        case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE:
            return booleanAttribute != TestElementImpl.BOOLEAN_ATTRIBUTE_EDEFAULT;
        case PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES:
            return booleanAttributes != null && !booleanAttributes.isEmpty();
        case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE:
            return enumAttribute != TestElementImpl.ENUM_ATTRIBUTE_EDEFAULT;
        case PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES:
            return enumAttributes != null && !enumAttributes.isEmpty();
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE:
            return containmentReference != null;
        case PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES:
            return containmentReferences != null && !containmentReferences.isEmpty();
        case PropertiestestsPackage.TEST_ELEMENT__REFERENCE:
            return reference != null;
        case PropertiestestsPackage.TEST_ELEMENT__REFERENCES:
            return references != null && !references.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (stringAttribute: ");
        result.append(stringAttribute);
        result.append(", stringAttributes: ");
        result.append(stringAttributes);
        result.append(", intAttribute: ");
        result.append(intAttribute);
        result.append(", intAttributes: ");
        result.append(intAttributes);
        result.append(", booleanAttribute: ");
        result.append(booleanAttribute);
        result.append(", booleanAttributes: ");
        result.append(booleanAttributes);
        result.append(", enumAttribute: ");
        result.append(enumAttribute);
        result.append(", enumAttributes: ");
        result.append(enumAttributes);
        result.append(')');
        return result.toString();
    }

} // TestElementImpl
