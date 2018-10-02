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
package org.eclipse.sirius.tree.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeCreationDescription;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Tree Creation Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.impl.TreeCreationDescriptionImpl#getTreeDescription <em>Tree
 * Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TreeCreationDescriptionImpl extends RepresentationCreationDescriptionImpl implements TreeCreationDescription {
    /**
     * The cached value of the '{@link #getTreeDescription() <em>Tree Description</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getTreeDescription()
     * @generated
     * @ordered
     */
    protected TreeDescription treeDescription;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TreeCreationDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TREE_CREATION_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TreeDescription getTreeDescription() {
        if (treeDescription != null && treeDescription.eIsProxy()) {
            InternalEObject oldTreeDescription = (InternalEObject) treeDescription;
            treeDescription = (TreeDescription) eResolveProxy(oldTreeDescription);
            if (treeDescription != oldTreeDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.TREE_CREATION_DESCRIPTION__TREE_DESCRIPTION, oldTreeDescription, treeDescription));
                }
            }
        }
        return treeDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TreeDescription basicGetTreeDescription() {
        return treeDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTreeDescription(TreeDescription newTreeDescription) {
        TreeDescription oldTreeDescription = treeDescription;
        treeDescription = newTreeDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_CREATION_DESCRIPTION__TREE_DESCRIPTION, oldTreeDescription, treeDescription));
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
        case DescriptionPackage.TREE_CREATION_DESCRIPTION__TREE_DESCRIPTION:
            if (resolve) {
                return getTreeDescription();
            }
            return basicGetTreeDescription();
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
        case DescriptionPackage.TREE_CREATION_DESCRIPTION__TREE_DESCRIPTION:
            setTreeDescription((TreeDescription) newValue);
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
        case DescriptionPackage.TREE_CREATION_DESCRIPTION__TREE_DESCRIPTION:
            setTreeDescription((TreeDescription) null);
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
        case DescriptionPackage.TREE_CREATION_DESCRIPTION__TREE_DESCRIPTION:
            return treeDescription != null;
        }
        return super.eIsSet(featureID);
    }

} // TreeCreationDescriptionImpl
