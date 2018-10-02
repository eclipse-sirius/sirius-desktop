/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.MetaModelExtension;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Meta Model Extension</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.MetaModelExtensionImpl#getExtensionGroup <em>Extension Group</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MetaModelExtensionImpl extends IdentifiedElementImpl implements MetaModelExtension {
    /**
     * The cached value of the '{@link #getExtensionGroup() <em>Extension Group</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getExtensionGroup()
     * @generated
     * @ordered
     */
    protected EObject extensionGroup;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MetaModelExtensionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.META_MODEL_EXTENSION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject getExtensionGroup() {
        if (extensionGroup != null && extensionGroup.eIsProxy()) {
            InternalEObject oldExtensionGroup = (InternalEObject) extensionGroup;
            extensionGroup = eResolveProxy(oldExtensionGroup);
            if (extensionGroup != oldExtensionGroup) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.META_MODEL_EXTENSION__EXTENSION_GROUP, oldExtensionGroup, extensionGroup));
                }
            }
        }
        return extensionGroup;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EObject basicGetExtensionGroup() {
        return extensionGroup;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExtensionGroup(EObject newExtensionGroup) {
        EObject oldExtensionGroup = extensionGroup;
        extensionGroup = newExtensionGroup;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.META_MODEL_EXTENSION__EXTENSION_GROUP, oldExtensionGroup, extensionGroup));
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
        case ViewpointPackage.META_MODEL_EXTENSION__EXTENSION_GROUP:
            if (resolve) {
                return getExtensionGroup();
            }
            return basicGetExtensionGroup();
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
        case ViewpointPackage.META_MODEL_EXTENSION__EXTENSION_GROUP:
            setExtensionGroup((EObject) newValue);
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
        case ViewpointPackage.META_MODEL_EXTENSION__EXTENSION_GROUP:
            setExtensionGroup((EObject) null);
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
        case ViewpointPackage.META_MODEL_EXTENSION__EXTENSION_GROUP:
            return extensionGroup != null;
        }
        return super.eIsSet(featureID);
    }

} // MetaModelExtensionImpl
