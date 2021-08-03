/**
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Custom Layout Configuration</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.CustomLayoutConfigurationImpl#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.CustomLayoutConfigurationImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.CustomLayoutConfigurationImpl#getDescription
 * <em>Description</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.CustomLayoutConfigurationImpl#getLayoutOptions <em>Layout
 * Options</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomLayoutConfigurationImpl extends DocumentedElementImpl implements CustomLayoutConfiguration {
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
    protected String id = CustomLayoutConfigurationImpl.ID_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = CustomLayoutConfigurationImpl.LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = CustomLayoutConfigurationImpl.DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getLayoutOptions() <em>Layout Options</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLayoutOptions()
     * @generated
     * @ordered
     */
    protected EList<LayoutOption> layoutOptions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CustomLayoutConfigurationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.CUSTOM_LAYOUT_CONFIGURATION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__ID, oldId, id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__DESCRIPTION, oldDescription, description));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<LayoutOption> getLayoutOptions() {
        if (layoutOptions == null) {
            layoutOptions = new EObjectContainmentEList.Resolving<>(LayoutOption.class, this, DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LAYOUT_OPTIONS);
        }
        return layoutOptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LAYOUT_OPTIONS:
            return ((InternalEList<?>) getLayoutOptions()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__ID:
            return getId();
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LABEL:
            return getLabel();
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__DESCRIPTION:
            return getDescription();
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LAYOUT_OPTIONS:
            return getLayoutOptions();
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
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__ID:
            setId((String) newValue);
            return;
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LABEL:
            setLabel((String) newValue);
            return;
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__DESCRIPTION:
            setDescription((String) newValue);
            return;
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LAYOUT_OPTIONS:
            getLayoutOptions().clear();
            getLayoutOptions().addAll((Collection<? extends LayoutOption>) newValue);
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
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__ID:
            setId(CustomLayoutConfigurationImpl.ID_EDEFAULT);
            return;
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LABEL:
            setLabel(CustomLayoutConfigurationImpl.LABEL_EDEFAULT);
            return;
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__DESCRIPTION:
            setDescription(CustomLayoutConfigurationImpl.DESCRIPTION_EDEFAULT);
            return;
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LAYOUT_OPTIONS:
            getLayoutOptions().clear();
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
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__ID:
            return CustomLayoutConfigurationImpl.ID_EDEFAULT == null ? id != null : !CustomLayoutConfigurationImpl.ID_EDEFAULT.equals(id);
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LABEL:
            return CustomLayoutConfigurationImpl.LABEL_EDEFAULT == null ? label != null : !CustomLayoutConfigurationImpl.LABEL_EDEFAULT.equals(label);
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__DESCRIPTION:
            return CustomLayoutConfigurationImpl.DESCRIPTION_EDEFAULT == null ? description != null : !CustomLayoutConfigurationImpl.DESCRIPTION_EDEFAULT.equals(description);
        case DescriptionPackage.CUSTOM_LAYOUT_CONFIGURATION__LAYOUT_OPTIONS:
            return layoutOptions != null && !layoutOptions.isEmpty();
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
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", description: "); //$NON-NLS-1$
        result.append(description);
        result.append(')');
        return result.toString();
    }

} // CustomLayoutConfigurationImpl
