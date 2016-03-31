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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.tool.DeleteHook;
import org.eclipse.sirius.diagram.description.tool.DeleteHookParameter;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Delete Hook</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DeleteHookImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.DeleteHookImpl#getParameters
 * <em>Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeleteHookImpl extends MinimalEObjectImpl.Container implements DeleteHook {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = DeleteHookImpl.ID_EDEFAULT;

    /**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected EList<DeleteHookParameter> parameters;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DeleteHookImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.DELETE_HOOK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DELETE_HOOK__ID, oldId, id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DeleteHookParameter> getParameters() {
        if (parameters == null) {
            parameters = new EObjectContainmentEList.Resolving<DeleteHookParameter>(DeleteHookParameter.class, this, ToolPackage.DELETE_HOOK__PARAMETERS);
        }
        return parameters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.DELETE_HOOK__PARAMETERS:
            return ((InternalEList<?>) getParameters()).basicRemove(otherEnd, msgs);
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
        case ToolPackage.DELETE_HOOK__ID:
            return getId();
        case ToolPackage.DELETE_HOOK__PARAMETERS:
            return getParameters();
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
        case ToolPackage.DELETE_HOOK__ID:
            setId((String) newValue);
            return;
        case ToolPackage.DELETE_HOOK__PARAMETERS:
            getParameters().clear();
            getParameters().addAll((Collection<? extends DeleteHookParameter>) newValue);
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
        case ToolPackage.DELETE_HOOK__ID:
            setId(DeleteHookImpl.ID_EDEFAULT);
            return;
        case ToolPackage.DELETE_HOOK__PARAMETERS:
            getParameters().clear();
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
        case ToolPackage.DELETE_HOOK__ID:
            return DeleteHookImpl.ID_EDEFAULT == null ? id != null : !DeleteHookImpl.ID_EDEFAULT.equals(id);
        case ToolPackage.DELETE_HOOK__PARAMETERS:
            return parameters != null && !parameters.isEmpty();
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
        result.append(" (id: "); //$NON-NLS-1$
        result.append(id);
        result.append(')');
        return result.toString();
    }

} // DeleteHookImpl
