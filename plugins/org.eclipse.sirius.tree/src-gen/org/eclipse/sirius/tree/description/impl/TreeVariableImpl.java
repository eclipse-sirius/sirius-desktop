/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeVariable;
import org.eclipse.sirius.viewpoint.description.tool.SubVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;
import org.eclipse.sirius.viewpoint.description.tool.impl.AbstractVariableImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tree Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeVariableImpl#getSubVariables
 * <em>Sub Variables</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeVariableImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TreeVariableImpl extends AbstractVariableImpl implements TreeVariable {
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
     * The default value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = TreeVariableImpl.DOCUMENTATION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TreeVariableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TREE_VARIABLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<SubVariable> getSubVariables() {
        if (subVariables == null) {
            subVariables = new EObjectContainmentEList.Resolving<SubVariable>(SubVariable.class, this, DescriptionPackage.TREE_VARIABLE__SUB_VARIABLES);
        }
        return subVariables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getDocumentation() {
        return documentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_VARIABLE__DOCUMENTATION, oldDocumentation, documentation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.TREE_VARIABLE__SUB_VARIABLES:
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
        case DescriptionPackage.TREE_VARIABLE__SUB_VARIABLES:
            return getSubVariables();
        case DescriptionPackage.TREE_VARIABLE__DOCUMENTATION:
            return getDocumentation();
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
        case DescriptionPackage.TREE_VARIABLE__SUB_VARIABLES:
            getSubVariables().clear();
            getSubVariables().addAll((Collection<? extends SubVariable>) newValue);
            return;
        case DescriptionPackage.TREE_VARIABLE__DOCUMENTATION:
            setDocumentation((String) newValue);
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
        case DescriptionPackage.TREE_VARIABLE__SUB_VARIABLES:
            getSubVariables().clear();
            return;
        case DescriptionPackage.TREE_VARIABLE__DOCUMENTATION:
            setDocumentation(TreeVariableImpl.DOCUMENTATION_EDEFAULT);
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
        case DescriptionPackage.TREE_VARIABLE__SUB_VARIABLES:
            return subVariables != null && !subVariables.isEmpty();
        case DescriptionPackage.TREE_VARIABLE__DOCUMENTATION:
            return TreeVariableImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !TreeVariableImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
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
            case DescriptionPackage.TREE_VARIABLE__SUB_VARIABLES:
                return ToolPackage.VARIABLE_CONTAINER__SUB_VARIABLES;
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
            case ToolPackage.VARIABLE_CONTAINER__SUB_VARIABLES:
                return DescriptionPackage.TREE_VARIABLE__SUB_VARIABLES;
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
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(')');
        return result.toString();
    }

} // TreeVariableImpl
