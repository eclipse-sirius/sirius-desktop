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
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.TypedVariableValue;
import org.eclipse.sirius.viewpoint.description.TypedVariable;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Typed Variable Value</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.TypedVariableValueImpl#getVariableDefinition <em>Variable
 * Definition</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.TypedVariableValueImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TypedVariableValueImpl extends VariableValueImpl implements TypedVariableValue {
    /**
     * The cached value of the '{@link #getVariableDefinition() <em>Variable Definition</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getVariableDefinition()
     * @generated
     * @ordered
     */
    protected TypedVariable variableDefinition;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected String value = TypedVariableValueImpl.VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TypedVariableValueImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.TYPED_VARIABLE_VALUE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TypedVariable getVariableDefinition() {
        if (variableDefinition != null && variableDefinition.eIsProxy()) {
            InternalEObject oldVariableDefinition = (InternalEObject) variableDefinition;
            variableDefinition = (TypedVariable) eResolveProxy(oldVariableDefinition);
            if (variableDefinition != oldVariableDefinition) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.TYPED_VARIABLE_VALUE__VARIABLE_DEFINITION, oldVariableDefinition, variableDefinition));
                }
            }
        }
        return variableDefinition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TypedVariable basicGetVariableDefinition() {
        return variableDefinition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setVariableDefinition(TypedVariable newVariableDefinition) {
        TypedVariable oldVariableDefinition = variableDefinition;
        variableDefinition = newVariableDefinition;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.TYPED_VARIABLE_VALUE__VARIABLE_DEFINITION, oldVariableDefinition, variableDefinition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setValue(String newValue) {
        String oldValue = value;
        value = newValue;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.TYPED_VARIABLE_VALUE__VALUE, oldValue, value));
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
        case DiagramPackage.TYPED_VARIABLE_VALUE__VARIABLE_DEFINITION:
            if (resolve) {
                return getVariableDefinition();
            }
            return basicGetVariableDefinition();
        case DiagramPackage.TYPED_VARIABLE_VALUE__VALUE:
            return getValue();
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
        case DiagramPackage.TYPED_VARIABLE_VALUE__VARIABLE_DEFINITION:
            setVariableDefinition((TypedVariable) newValue);
            return;
        case DiagramPackage.TYPED_VARIABLE_VALUE__VALUE:
            setValue((String) newValue);
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
        case DiagramPackage.TYPED_VARIABLE_VALUE__VARIABLE_DEFINITION:
            setVariableDefinition((TypedVariable) null);
            return;
        case DiagramPackage.TYPED_VARIABLE_VALUE__VALUE:
            setValue(TypedVariableValueImpl.VALUE_EDEFAULT);
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
        case DiagramPackage.TYPED_VARIABLE_VALUE__VARIABLE_DEFINITION:
            return variableDefinition != null;
        case DiagramPackage.TYPED_VARIABLE_VALUE__VALUE:
            return TypedVariableValueImpl.VALUE_EDEFAULT == null ? value != null : !TypedVariableValueImpl.VALUE_EDEFAULT.equals(value);
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (value: "); //$NON-NLS-1$
        result.append(value);
        result.append(')');
        return result.toString();
    }

} // TypedVariableValueImpl
