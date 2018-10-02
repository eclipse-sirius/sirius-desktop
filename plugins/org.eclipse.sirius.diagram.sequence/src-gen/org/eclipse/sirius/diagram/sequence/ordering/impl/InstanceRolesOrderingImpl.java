/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ordering.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Instance Roles Ordering</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.ordering.impl.InstanceRolesOrderingImpl#getSemanticInstanceRoles
 * <em>Semantic Instance Roles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InstanceRolesOrderingImpl extends MinimalEObjectImpl.Container implements InstanceRolesOrdering {
    /**
     * The cached value of the '{@link #getSemanticInstanceRoles() <em>Semantic Instance Roles</em>}' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSemanticInstanceRoles()
     * @generated
     * @ordered
     */
    protected EList<EObject> semanticInstanceRoles;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InstanceRolesOrderingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OrderingPackage.Literals.INSTANCE_ROLES_ORDERING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EObject> getSemanticInstanceRoles() {
        if (semanticInstanceRoles == null) {
            semanticInstanceRoles = new EObjectResolvingEList<EObject>(EObject.class, this, OrderingPackage.INSTANCE_ROLES_ORDERING__SEMANTIC_INSTANCE_ROLES);
        }
        return semanticInstanceRoles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case OrderingPackage.INSTANCE_ROLES_ORDERING__SEMANTIC_INSTANCE_ROLES:
            return getSemanticInstanceRoles();
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
        case OrderingPackage.INSTANCE_ROLES_ORDERING__SEMANTIC_INSTANCE_ROLES:
            getSemanticInstanceRoles().clear();
            getSemanticInstanceRoles().addAll((Collection<? extends EObject>) newValue);
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
        case OrderingPackage.INSTANCE_ROLES_ORDERING__SEMANTIC_INSTANCE_ROLES:
            getSemanticInstanceRoles().clear();
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
        case OrderingPackage.INSTANCE_ROLES_ORDERING__SEMANTIC_INSTANCE_ROLES:
            return semanticInstanceRoles != null && !semanticInstanceRoles.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // InstanceRolesOrderingImpl
