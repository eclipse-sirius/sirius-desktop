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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.ToolInstance;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Tool Instance</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.ToolInstanceImpl#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.ToolInstanceImpl#isEnabled <em>Enabled</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.ToolInstanceImpl#isVisible <em>Visible</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.ToolInstanceImpl#getToolEntry <em>Tool Entry</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.ToolInstanceImpl#isFiltered <em>Filtered</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ToolInstanceImpl extends MinimalEObjectImpl.Container implements ToolInstance {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ToolInstanceImpl.ID_EDEFAULT;

    /**
     * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isEnabled()
     * @generated
     * @ordered
     */
    protected static final boolean ENABLED_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isEnabled()
     * @generated
     * @ordered
     */
    protected boolean enabled = ToolInstanceImpl.ENABLED_EDEFAULT;

    /**
     * The default value of the '{@link #isVisible() <em>Visible</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isVisible()
     * @generated
     * @ordered
     */
    protected static final boolean VISIBLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isVisible() <em>Visible</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isVisible()
     * @generated
     * @ordered
     */
    protected boolean visible = ToolInstanceImpl.VISIBLE_EDEFAULT;

    /**
     * The cached value of the '{@link #getToolEntry() <em>Tool Entry</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getToolEntry()
     * @generated
     * @ordered
     */
    protected ToolEntry toolEntry;

    /**
     * The default value of the '{@link #isFiltered() <em>Filtered</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isFiltered()
     * @generated
     * @ordered
     */
    protected static final boolean FILTERED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isFiltered() <em>Filtered</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isFiltered()
     * @generated
     * @ordered
     */
    protected boolean filtered = ToolInstanceImpl.FILTERED_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ToolInstanceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.TOOL_INSTANCE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.TOOL_INSTANCE__ID, oldId, id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEnabled(boolean newEnabled) {
        boolean oldEnabled = enabled;
        enabled = newEnabled;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.TOOL_INSTANCE__ENABLED, oldEnabled, enabled));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isVisible() {
        return visible;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setVisible(boolean newVisible) {
        boolean oldVisible = visible;
        visible = newVisible;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.TOOL_INSTANCE__VISIBLE, oldVisible, visible));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ToolEntry getToolEntry() {
        if (toolEntry != null && toolEntry.eIsProxy()) {
            InternalEObject oldToolEntry = (InternalEObject) toolEntry;
            toolEntry = (ToolEntry) eResolveProxy(oldToolEntry);
            if (toolEntry != oldToolEntry) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.TOOL_INSTANCE__TOOL_ENTRY, oldToolEntry, toolEntry));
                }
            }
        }
        return toolEntry;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ToolEntry basicGetToolEntry() {
        return toolEntry;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setToolEntry(ToolEntry newToolEntry) {
        ToolEntry oldToolEntry = toolEntry;
        toolEntry = newToolEntry;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.TOOL_INSTANCE__TOOL_ENTRY, oldToolEntry, toolEntry));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isFiltered() {
        return filtered;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFiltered(boolean newFiltered) {
        boolean oldFiltered = filtered;
        filtered = newFiltered;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.TOOL_INSTANCE__FILTERED, oldFiltered, filtered));
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
        case ViewpointPackage.TOOL_INSTANCE__ID:
            return getId();
        case ViewpointPackage.TOOL_INSTANCE__ENABLED:
            return isEnabled();
        case ViewpointPackage.TOOL_INSTANCE__VISIBLE:
            return isVisible();
        case ViewpointPackage.TOOL_INSTANCE__TOOL_ENTRY:
            if (resolve) {
                return getToolEntry();
            }
            return basicGetToolEntry();
        case ViewpointPackage.TOOL_INSTANCE__FILTERED:
            return isFiltered();
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
        case ViewpointPackage.TOOL_INSTANCE__ID:
            setId((String) newValue);
            return;
        case ViewpointPackage.TOOL_INSTANCE__ENABLED:
            setEnabled((Boolean) newValue);
            return;
        case ViewpointPackage.TOOL_INSTANCE__VISIBLE:
            setVisible((Boolean) newValue);
            return;
        case ViewpointPackage.TOOL_INSTANCE__TOOL_ENTRY:
            setToolEntry((ToolEntry) newValue);
            return;
        case ViewpointPackage.TOOL_INSTANCE__FILTERED:
            setFiltered((Boolean) newValue);
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
        case ViewpointPackage.TOOL_INSTANCE__ID:
            setId(ToolInstanceImpl.ID_EDEFAULT);
            return;
        case ViewpointPackage.TOOL_INSTANCE__ENABLED:
            setEnabled(ToolInstanceImpl.ENABLED_EDEFAULT);
            return;
        case ViewpointPackage.TOOL_INSTANCE__VISIBLE:
            setVisible(ToolInstanceImpl.VISIBLE_EDEFAULT);
            return;
        case ViewpointPackage.TOOL_INSTANCE__TOOL_ENTRY:
            setToolEntry((ToolEntry) null);
            return;
        case ViewpointPackage.TOOL_INSTANCE__FILTERED:
            setFiltered(ToolInstanceImpl.FILTERED_EDEFAULT);
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
        case ViewpointPackage.TOOL_INSTANCE__ID:
            return ToolInstanceImpl.ID_EDEFAULT == null ? id != null : !ToolInstanceImpl.ID_EDEFAULT.equals(id);
        case ViewpointPackage.TOOL_INSTANCE__ENABLED:
            return enabled != ToolInstanceImpl.ENABLED_EDEFAULT;
        case ViewpointPackage.TOOL_INSTANCE__VISIBLE:
            return visible != ToolInstanceImpl.VISIBLE_EDEFAULT;
        case ViewpointPackage.TOOL_INSTANCE__TOOL_ENTRY:
            return toolEntry != null;
        case ViewpointPackage.TOOL_INSTANCE__FILTERED:
            return filtered != ToolInstanceImpl.FILTERED_EDEFAULT;
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (id: "); //$NON-NLS-1$
        result.append(id);
        result.append(", enabled: "); //$NON-NLS-1$
        result.append(enabled);
        result.append(", visible: "); //$NON-NLS-1$
        result.append(visible);
        result.append(", filtered: "); //$NON-NLS-1$
        result.append(filtered);
        result.append(')');
        return result.toString();
    }

} // ToolInstanceImpl
