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
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolGroupExtension;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Group Extension</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ToolGroupExtensionImpl#getGroup
 * <em>Group</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ToolGroupExtensionImpl#getTools
 * <em>Tools</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ToolGroupExtensionImpl extends MinimalEObjectImpl.Container implements ToolGroupExtension {
    /**
     * The cached value of the '{@link #getGroup() <em>Group</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getGroup()
     * @generated
     * @ordered
     */
    protected ToolGroup group;

    /**
     * The cached value of the '{@link #getTools() <em>Tools</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTools()
     * @generated
     * @ordered
     */
    protected EList<AbstractToolDescription> tools;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ToolGroupExtensionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.TOOL_GROUP_EXTENSION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ToolGroup getGroup() {
        if (group != null && group.eIsProxy()) {
            InternalEObject oldGroup = (InternalEObject) group;
            group = (ToolGroup) eResolveProxy(oldGroup);
            if (group != oldGroup) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.TOOL_GROUP_EXTENSION__GROUP, oldGroup, group));
                }
            }
        }
        return group;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ToolGroup basicGetGroup() {
        return group;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setGroup(ToolGroup newGroup) {
        ToolGroup oldGroup = group;
        group = newGroup;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.TOOL_GROUP_EXTENSION__GROUP, oldGroup, group));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractToolDescription> getTools() {
        if (tools == null) {
            tools = new EObjectContainmentEList.Resolving<AbstractToolDescription>(AbstractToolDescription.class, this, ToolPackage.TOOL_GROUP_EXTENSION__TOOLS);
        }
        return tools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.TOOL_GROUP_EXTENSION__TOOLS:
            return ((InternalEList<?>) getTools()).basicRemove(otherEnd, msgs);
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
        case ToolPackage.TOOL_GROUP_EXTENSION__GROUP:
            if (resolve) {
                return getGroup();
            }
            return basicGetGroup();
        case ToolPackage.TOOL_GROUP_EXTENSION__TOOLS:
            return getTools();
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
        case ToolPackage.TOOL_GROUP_EXTENSION__GROUP:
            setGroup((ToolGroup) newValue);
            return;
        case ToolPackage.TOOL_GROUP_EXTENSION__TOOLS:
            getTools().clear();
            getTools().addAll((Collection<? extends AbstractToolDescription>) newValue);
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
        case ToolPackage.TOOL_GROUP_EXTENSION__GROUP:
            setGroup((ToolGroup) null);
            return;
        case ToolPackage.TOOL_GROUP_EXTENSION__TOOLS:
            getTools().clear();
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
        case ToolPackage.TOOL_GROUP_EXTENSION__GROUP:
            return group != null;
        case ToolPackage.TOOL_GROUP_EXTENSION__TOOLS:
            return tools != null && !tools.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ToolGroupExtensionImpl
