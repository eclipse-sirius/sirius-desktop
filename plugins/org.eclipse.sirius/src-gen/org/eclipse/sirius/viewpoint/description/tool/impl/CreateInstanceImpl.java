/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Create Instance</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.CreateInstanceImpl#getTypeName
 * <em>Type Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.CreateInstanceImpl#getReferenceName
 * <em>Reference Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.CreateInstanceImpl#getVariableName
 * <em>Variable Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CreateInstanceImpl extends ContainerModelOperationImpl implements CreateInstance {
    /**
     * The default value of the '{@link #getTypeName() <em>Type Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTypeName()
     * @generated
     * @ordered
     */
    protected static final String TYPE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTypeName() <em>Type Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTypeName()
     * @generated
     * @ordered
     */
    protected String typeName = CreateInstanceImpl.TYPE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getReferenceName()
     * <em>Reference Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getReferenceName()
     * @generated
     * @ordered
     */
    protected static final String REFERENCE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReferenceName()
     * <em>Reference Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getReferenceName()
     * @generated
     * @ordered
     */
    protected String referenceName = CreateInstanceImpl.REFERENCE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getVariableName()
     * <em>Variable Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getVariableName()
     * @generated
     * @ordered
     */
    protected static final String VARIABLE_NAME_EDEFAULT = "instance"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getVariableName()
     * <em>Variable Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getVariableName()
     * @generated
     * @ordered
     */
    protected String variableName = CreateInstanceImpl.VARIABLE_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected CreateInstanceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.CREATE_INSTANCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getTypeName() {
        return typeName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTypeName(String newTypeName) {
        String oldTypeName = typeName;
        typeName = newTypeName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CREATE_INSTANCE__TYPE_NAME, oldTypeName, typeName));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getReferenceName() {
        return referenceName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setReferenceName(String newReferenceName) {
        String oldReferenceName = referenceName;
        referenceName = newReferenceName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CREATE_INSTANCE__REFERENCE_NAME, oldReferenceName, referenceName));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getVariableName() {
        return variableName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setVariableName(String newVariableName) {
        String oldVariableName = variableName;
        variableName = newVariableName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CREATE_INSTANCE__VARIABLE_NAME, oldVariableName, variableName));
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
        case ToolPackage.CREATE_INSTANCE__TYPE_NAME:
            return getTypeName();
        case ToolPackage.CREATE_INSTANCE__REFERENCE_NAME:
            return getReferenceName();
        case ToolPackage.CREATE_INSTANCE__VARIABLE_NAME:
            return getVariableName();
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
        case ToolPackage.CREATE_INSTANCE__TYPE_NAME:
            setTypeName((String) newValue);
            return;
        case ToolPackage.CREATE_INSTANCE__REFERENCE_NAME:
            setReferenceName((String) newValue);
            return;
        case ToolPackage.CREATE_INSTANCE__VARIABLE_NAME:
            setVariableName((String) newValue);
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
        case ToolPackage.CREATE_INSTANCE__TYPE_NAME:
            setTypeName(CreateInstanceImpl.TYPE_NAME_EDEFAULT);
            return;
        case ToolPackage.CREATE_INSTANCE__REFERENCE_NAME:
            setReferenceName(CreateInstanceImpl.REFERENCE_NAME_EDEFAULT);
            return;
        case ToolPackage.CREATE_INSTANCE__VARIABLE_NAME:
            setVariableName(CreateInstanceImpl.VARIABLE_NAME_EDEFAULT);
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
        case ToolPackage.CREATE_INSTANCE__TYPE_NAME:
            return CreateInstanceImpl.TYPE_NAME_EDEFAULT == null ? typeName != null : !CreateInstanceImpl.TYPE_NAME_EDEFAULT.equals(typeName);
        case ToolPackage.CREATE_INSTANCE__REFERENCE_NAME:
            return CreateInstanceImpl.REFERENCE_NAME_EDEFAULT == null ? referenceName != null : !CreateInstanceImpl.REFERENCE_NAME_EDEFAULT.equals(referenceName);
        case ToolPackage.CREATE_INSTANCE__VARIABLE_NAME:
            return CreateInstanceImpl.VARIABLE_NAME_EDEFAULT == null ? variableName != null : !CreateInstanceImpl.VARIABLE_NAME_EDEFAULT.equals(variableName);
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
        result.append(" (typeName: "); //$NON-NLS-1$
        result.append(typeName);
        result.append(", referenceName: "); //$NON-NLS-1$
        result.append(referenceName);
        result.append(", variableName: "); //$NON-NLS-1$
        result.append(variableName);
        result.append(')');
        return result.toString();
    }

} // CreateInstanceImpl
