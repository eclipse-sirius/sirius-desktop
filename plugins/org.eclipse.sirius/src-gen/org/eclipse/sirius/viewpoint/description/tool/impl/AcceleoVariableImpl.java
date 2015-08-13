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
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable;
import org.eclipse.sirius.viewpoint.description.tool.SubVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Acceleo Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AcceleoVariableImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AcceleoVariableImpl#getComputationExpression
 * <em>Computation Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AcceleoVariableImpl extends VariableContainerImpl implements AcceleoVariable {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = AcceleoVariableImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getComputationExpression()
     * <em>Computation Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String COMPUTATION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getComputationExpression()
     * <em>Computation Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getComputationExpression()
     * @generated
     * @ordered
     */
    protected String computationExpression = AcceleoVariableImpl.COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected AcceleoVariableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.ACCELEO_VARIABLE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.ACCELEO_VARIABLE__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getComputationExpression() {
        return computationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setComputationExpression(String newComputationExpression) {
        String oldComputationExpression = computationExpression;
        computationExpression = newComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.ACCELEO_VARIABLE__COMPUTATION_EXPRESSION, oldComputationExpression, computationExpression));
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
        case ToolPackage.ACCELEO_VARIABLE__NAME:
            return getName();
        case ToolPackage.ACCELEO_VARIABLE__COMPUTATION_EXPRESSION:
            return getComputationExpression();
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
        case ToolPackage.ACCELEO_VARIABLE__NAME:
            setName((String) newValue);
            return;
        case ToolPackage.ACCELEO_VARIABLE__COMPUTATION_EXPRESSION:
            setComputationExpression((String) newValue);
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
        case ToolPackage.ACCELEO_VARIABLE__NAME:
            setName(AcceleoVariableImpl.NAME_EDEFAULT);
            return;
        case ToolPackage.ACCELEO_VARIABLE__COMPUTATION_EXPRESSION:
            setComputationExpression(AcceleoVariableImpl.COMPUTATION_EXPRESSION_EDEFAULT);
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
        case ToolPackage.ACCELEO_VARIABLE__NAME:
            return AcceleoVariableImpl.NAME_EDEFAULT == null ? name != null : !AcceleoVariableImpl.NAME_EDEFAULT.equals(name);
        case ToolPackage.ACCELEO_VARIABLE__COMPUTATION_EXPRESSION:
            return AcceleoVariableImpl.COMPUTATION_EXPRESSION_EDEFAULT == null ? computationExpression != null : !AcceleoVariableImpl.COMPUTATION_EXPRESSION_EDEFAULT.equals(computationExpression);
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
            case ToolPackage.ACCELEO_VARIABLE__NAME:
                return ToolPackage.ABSTRACT_VARIABLE__NAME;
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
            case ToolPackage.ABSTRACT_VARIABLE__NAME:
                return ToolPackage.ACCELEO_VARIABLE__NAME;
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

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", computationExpression: "); //$NON-NLS-1$
        result.append(computationExpression);
        result.append(')');
        return result.toString();
    }

} // AcceleoVariableImpl
