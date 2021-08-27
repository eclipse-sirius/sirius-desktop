/**
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.ToolGroupInstance;
import org.eclipse.sirius.viewpoint.ToolInstance;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Tool Group Instance</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.ToolGroupInstanceImpl#getTools <em>Tools</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.ToolGroupInstanceImpl#getGroup <em>Group</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ToolGroupInstanceImpl extends ToolInstanceImpl implements ToolGroupInstance {
    /**
     * The cached value of the '{@link #getTools() <em>Tools</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getTools()
     * @generated
     * @ordered
     */
    protected EList<ToolInstance> tools;

    /**
     * The cached value of the '{@link #getGroup() <em>Group</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getGroup()
     * @generated
     * @ordered
     */
    protected EObject group;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ToolGroupInstanceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.TOOL_GROUP_INSTANCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ToolInstance> getTools() {
        if (tools == null) {
            tools = new EObjectContainmentEList.Resolving<>(ToolInstance.class, this, ViewpointPackage.TOOL_GROUP_INSTANCE__TOOLS);
        }
        return tools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject getGroup() {
        if (group != null && group.eIsProxy()) {
            InternalEObject oldGroup = (InternalEObject) group;
            group = eResolveProxy(oldGroup);
            if (group != oldGroup) {
                InternalEObject newGroup = (InternalEObject) group;
                NotificationChain msgs = oldGroup.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP, null, null);
                if (newGroup.eInternalContainer() == null) {
                    msgs = newGroup.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP, oldGroup, group));
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
    public EObject basicGetGroup() {
        return group;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetGroup(EObject newGroup, NotificationChain msgs) {
        EObject oldGroup = group;
        group = newGroup;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP, oldGroup, newGroup);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setGroup(EObject newGroup) {
        if (newGroup != group) {
            NotificationChain msgs = null;
            if (group != null) {
                msgs = ((InternalEObject) group).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP, null, msgs);
            }
            if (newGroup != null) {
                msgs = ((InternalEObject) newGroup).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP, null, msgs);
            }
            msgs = basicSetGroup(newGroup, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP, newGroup, newGroup));
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
        case ViewpointPackage.TOOL_GROUP_INSTANCE__TOOLS:
            return ((InternalEList<?>) getTools()).basicRemove(otherEnd, msgs);
        case ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP:
            return basicSetGroup(null, msgs);
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
        case ViewpointPackage.TOOL_GROUP_INSTANCE__TOOLS:
            return getTools();
        case ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP:
            if (resolve) {
                return getGroup();
            }
            return basicGetGroup();
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
        case ViewpointPackage.TOOL_GROUP_INSTANCE__TOOLS:
            getTools().clear();
            getTools().addAll((Collection<? extends ToolInstance>) newValue);
            return;
        case ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP:
            setGroup((EObject) newValue);
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
        case ViewpointPackage.TOOL_GROUP_INSTANCE__TOOLS:
            getTools().clear();
            return;
        case ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP:
            setGroup((EObject) null);
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
        case ViewpointPackage.TOOL_GROUP_INSTANCE__TOOLS:
            return tools != null && !tools.isEmpty();
        case ViewpointPackage.TOOL_GROUP_INSTANCE__GROUP:
            return group != null;
        }
        return super.eIsSet(featureID);
    }

} // ToolGroupInstanceImpl
