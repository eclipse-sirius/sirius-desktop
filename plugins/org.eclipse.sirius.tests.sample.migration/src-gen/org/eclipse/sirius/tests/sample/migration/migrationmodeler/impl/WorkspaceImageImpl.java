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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Workspace Image</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.WorkspaceImageImpl#getWorkspacePath
 * <em>Workspace Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkspaceImageImpl extends NodeStyleImpl implements WorkspaceImage {
    /**
     * The default value of the '{@link #getWorkspacePath()
     * <em>Workspace Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getWorkspacePath()
     * @generated
     * @ordered
     */
    protected static final String WORKSPACE_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWorkspacePath()
     * <em>Workspace Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getWorkspacePath()
     * @generated
     * @ordered
     */
    protected String workspacePath = WorkspaceImageImpl.WORKSPACE_PATH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected WorkspaceImageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.WORKSPACE_IMAGE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getWorkspacePath() {
        return workspacePath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWorkspacePath(String newWorkspacePath) {
        String oldWorkspacePath = workspacePath;
        workspacePath = newWorkspacePath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.WORKSPACE_IMAGE__WORKSPACE_PATH, oldWorkspacePath, workspacePath));
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
        case MigrationmodelerPackage.WORKSPACE_IMAGE__WORKSPACE_PATH:
            return getWorkspacePath();
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
        case MigrationmodelerPackage.WORKSPACE_IMAGE__WORKSPACE_PATH:
            setWorkspacePath((String) newValue);
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
        case MigrationmodelerPackage.WORKSPACE_IMAGE__WORKSPACE_PATH:
            setWorkspacePath(WorkspaceImageImpl.WORKSPACE_PATH_EDEFAULT);
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
        case MigrationmodelerPackage.WORKSPACE_IMAGE__WORKSPACE_PATH:
            return WorkspaceImageImpl.WORKSPACE_PATH_EDEFAULT == null ? workspacePath != null : !WorkspaceImageImpl.WORKSPACE_PATH_EDEFAULT.equals(workspacePath);
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
        result.append(" (workspacePath: "); //$NON-NLS-1$
        result.append(workspacePath);
        result.append(')');
        return result.toString();
    }

} // WorkspaceImageImpl
