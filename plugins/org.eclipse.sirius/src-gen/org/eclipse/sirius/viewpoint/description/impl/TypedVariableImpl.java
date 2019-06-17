/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.SubVariable;
import org.eclipse.sirius.viewpoint.description.TypedVariable;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Typed Variable</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.TypedVariableImpl#getUserDocumentation <em>User
 * Documentation</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.TypedVariableImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.TypedVariableImpl#getDefaultValueExpression <em>Default
 * Value Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.TypedVariableImpl#getValueType <em>Value Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TypedVariableImpl extends MinimalEObjectImpl.Container implements TypedVariable {
    /**
     * The default value of the '{@link #getUserDocumentation() <em>User Documentation</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getUserDocumentation()
     * @generated
     * @ordered
     */
    protected static final String USER_DOCUMENTATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserDocumentation() <em>User Documentation</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getUserDocumentation()
     * @generated
     * @ordered
     */
    protected String userDocumentation = TypedVariableImpl.USER_DOCUMENTATION_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = TypedVariableImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDefaultValueExpression() <em>Default Value Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDefaultValueExpression()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_VALUE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefaultValueExpression() <em>Default Value Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDefaultValueExpression()
     * @generated
     * @ordered
     */
    protected String defaultValueExpression = TypedVariableImpl.DEFAULT_VALUE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getValueType() <em>Value Type</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getValueType()
     * @generated
     * @ordered
     */
    protected EDataType valueType;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TypedVariableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TYPED_VARIABLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getUserDocumentation() {
        return userDocumentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setUserDocumentation(String newUserDocumentation) {
        String oldUserDocumentation = userDocumentation;
        userDocumentation = newUserDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TYPED_VARIABLE__USER_DOCUMENTATION, oldUserDocumentation, userDocumentation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TYPED_VARIABLE__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDefaultValueExpression() {
        return defaultValueExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDefaultValueExpression(String newDefaultValueExpression) {
        String oldDefaultValueExpression = defaultValueExpression;
        defaultValueExpression = newDefaultValueExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TYPED_VARIABLE__DEFAULT_VALUE_EXPRESSION, oldDefaultValueExpression, defaultValueExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getValueType() {
        if (valueType != null && valueType.eIsProxy()) {
            InternalEObject oldValueType = (InternalEObject) valueType;
            valueType = (EDataType) eResolveProxy(oldValueType);
            if (valueType != oldValueType) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.TYPED_VARIABLE__VALUE_TYPE, oldValueType, valueType));
                }
            }
        }
        return valueType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EDataType basicGetValueType() {
        return valueType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setValueType(EDataType newValueType) {
        EDataType oldValueType = valueType;
        valueType = newValueType;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TYPED_VARIABLE__VALUE_TYPE, oldValueType, valueType));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.TYPED_VARIABLE__USER_DOCUMENTATION:
            return getUserDocumentation();
        case DescriptionPackage.TYPED_VARIABLE__NAME:
            return getName();
        case DescriptionPackage.TYPED_VARIABLE__DEFAULT_VALUE_EXPRESSION:
            return getDefaultValueExpression();
        case DescriptionPackage.TYPED_VARIABLE__VALUE_TYPE:
            if (resolve) {
                return getValueType();
            }
            return basicGetValueType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case DescriptionPackage.TYPED_VARIABLE__USER_DOCUMENTATION:
            setUserDocumentation((String) newValue);
            return;
        case DescriptionPackage.TYPED_VARIABLE__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.TYPED_VARIABLE__DEFAULT_VALUE_EXPRESSION:
            setDefaultValueExpression((String) newValue);
            return;
        case DescriptionPackage.TYPED_VARIABLE__VALUE_TYPE:
            setValueType((EDataType) newValue);
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
        case DescriptionPackage.TYPED_VARIABLE__USER_DOCUMENTATION:
            setUserDocumentation(TypedVariableImpl.USER_DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.TYPED_VARIABLE__NAME:
            setName(TypedVariableImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.TYPED_VARIABLE__DEFAULT_VALUE_EXPRESSION:
            setDefaultValueExpression(TypedVariableImpl.DEFAULT_VALUE_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.TYPED_VARIABLE__VALUE_TYPE:
            setValueType((EDataType) null);
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
        case DescriptionPackage.TYPED_VARIABLE__USER_DOCUMENTATION:
            return TypedVariableImpl.USER_DOCUMENTATION_EDEFAULT == null ? userDocumentation != null : !TypedVariableImpl.USER_DOCUMENTATION_EDEFAULT.equals(userDocumentation);
        case DescriptionPackage.TYPED_VARIABLE__NAME:
            return TypedVariableImpl.NAME_EDEFAULT == null ? name != null : !TypedVariableImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.TYPED_VARIABLE__DEFAULT_VALUE_EXPRESSION:
            return TypedVariableImpl.DEFAULT_VALUE_EXPRESSION_EDEFAULT == null ? defaultValueExpression != null : !TypedVariableImpl.DEFAULT_VALUE_EXPRESSION_EDEFAULT.equals(defaultValueExpression);
        case DescriptionPackage.TYPED_VARIABLE__VALUE_TYPE:
            return valueType != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractVariable.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TYPED_VARIABLE__NAME:
                return DescriptionPackage.ABSTRACT_VARIABLE__NAME;
            default:
                return -1;
            }
        }
        if (baseClass == SubVariable.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractVariable.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.ABSTRACT_VARIABLE__NAME:
                return DescriptionPackage.TYPED_VARIABLE__NAME;
            default:
                return -1;
            }
        }
        if (baseClass == SubVariable.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (userDocumentation: "); //$NON-NLS-1$
        result.append(userDocumentation);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", defaultValueExpression: "); //$NON-NLS-1$
        result.append(defaultValueExpression);
        result.append(')');
        return result.toString();
    }

} // TypedVariableImpl
