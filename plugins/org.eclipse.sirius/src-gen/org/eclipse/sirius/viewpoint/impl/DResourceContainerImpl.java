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
package org.eclipse.sirius.viewpoint.impl;

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
import org.eclipse.sirius.viewpoint.DResource;
import org.eclipse.sirius.viewpoint.DResourceContainer;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DResource Container</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl#getName
 * <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl#getPath
 * <em>Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DResourceContainerImpl#getMembers
 * <em>Members</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DResourceContainerImpl extends MinimalEObjectImpl.Container implements DResourceContainer {
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
    protected String name = DResourceContainerImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected static final String PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected String path = DResourceContainerImpl.PATH_EDEFAULT;

    /**
     * The cached value of the '{@link #getMembers() <em>Members</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMembers()
     * @generated
     * @ordered
     */
    protected EList<DResource> members;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DResourceContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DRESOURCE_CONTAINER;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DRESOURCE_CONTAINER__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPath(String newPath) {
        String oldPath = path;
        path = newPath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DRESOURCE_CONTAINER__PATH, oldPath, path));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DResource> getMembers() {
        if (members == null) {
            members = new EObjectContainmentEList.Resolving<DResource>(DResource.class, this, ViewpointPackage.DRESOURCE_CONTAINER__MEMBERS);
        }
        return members;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.DRESOURCE_CONTAINER__MEMBERS:
            return ((InternalEList<?>) getMembers()).basicRemove(otherEnd, msgs);
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
        case ViewpointPackage.DRESOURCE_CONTAINER__NAME:
            return getName();
        case ViewpointPackage.DRESOURCE_CONTAINER__PATH:
            return getPath();
        case ViewpointPackage.DRESOURCE_CONTAINER__MEMBERS:
            return getMembers();
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
        case ViewpointPackage.DRESOURCE_CONTAINER__NAME:
            setName((String) newValue);
            return;
        case ViewpointPackage.DRESOURCE_CONTAINER__PATH:
            setPath((String) newValue);
            return;
        case ViewpointPackage.DRESOURCE_CONTAINER__MEMBERS:
            getMembers().clear();
            getMembers().addAll((Collection<? extends DResource>) newValue);
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
        case ViewpointPackage.DRESOURCE_CONTAINER__NAME:
            setName(DResourceContainerImpl.NAME_EDEFAULT);
            return;
        case ViewpointPackage.DRESOURCE_CONTAINER__PATH:
            setPath(DResourceContainerImpl.PATH_EDEFAULT);
            return;
        case ViewpointPackage.DRESOURCE_CONTAINER__MEMBERS:
            getMembers().clear();
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
        case ViewpointPackage.DRESOURCE_CONTAINER__NAME:
            return DResourceContainerImpl.NAME_EDEFAULT == null ? name != null : !DResourceContainerImpl.NAME_EDEFAULT.equals(name);
        case ViewpointPackage.DRESOURCE_CONTAINER__PATH:
            return DResourceContainerImpl.PATH_EDEFAULT == null ? path != null : !DResourceContainerImpl.PATH_EDEFAULT.equals(path);
        case ViewpointPackage.DRESOURCE_CONTAINER__MEMBERS:
            return members != null && !members.isEmpty();
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", path: "); //$NON-NLS-1$
        result.append(path);
        result.append(')');
        return result.toString();
    }

} // DResourceContainerImpl
