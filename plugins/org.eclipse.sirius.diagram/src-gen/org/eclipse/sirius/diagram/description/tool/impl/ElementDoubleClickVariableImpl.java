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
package org.eclipse.sirius.diagram.description.tool.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.SubVariable;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;
import org.eclipse.sirius.viewpoint.description.tool.impl.AbstractVariableImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Element Double Click Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ElementDoubleClickVariableImpl#getSubVariables
 * <em>Sub Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementDoubleClickVariableImpl extends AbstractVariableImpl implements ElementDoubleClickVariable {
    /**
     * The cached value of the '{@link #getSubVariables()
     * <em>Sub Variables</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSubVariables()
     * @generated
     * @ordered
     */
    protected EList<SubVariable> subVariables;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ElementDoubleClickVariableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.ELEMENT_DOUBLE_CLICK_VARIABLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<SubVariable> getSubVariables() {
        if (subVariables == null) {
            subVariables = new EObjectContainmentEList.Resolving<SubVariable>(SubVariable.class, this, ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE__SUB_VARIABLES);
        }
        return subVariables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE__SUB_VARIABLES:
            return ((InternalEList<?>) getSubVariables()).basicRemove(otherEnd, msgs);
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
        case ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE__SUB_VARIABLES:
            return getSubVariables();
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
        case ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE__SUB_VARIABLES:
            getSubVariables().clear();
            getSubVariables().addAll((Collection<? extends SubVariable>) newValue);
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
        case ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE__SUB_VARIABLES:
            getSubVariables().clear();
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
        case ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE__SUB_VARIABLES:
            return subVariables != null && !subVariables.isEmpty();
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
        if (baseClass == VariableContainer.class) {
            switch (derivedFeatureID) {
            case ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE__SUB_VARIABLES:
                return org.eclipse.sirius.viewpoint.description.tool.ToolPackage.VARIABLE_CONTAINER__SUB_VARIABLES;
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
        if (baseClass == VariableContainer.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.tool.ToolPackage.VARIABLE_CONTAINER__SUB_VARIABLES:
                return ToolPackage.ELEMENT_DOUBLE_CLICK_VARIABLE__SUB_VARIABLES;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} // ElementDoubleClickVariableImpl
