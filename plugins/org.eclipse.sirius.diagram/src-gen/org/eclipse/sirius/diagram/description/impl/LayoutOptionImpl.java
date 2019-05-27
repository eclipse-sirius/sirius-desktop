/**
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOptionTarget;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Layout Option</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.LayoutOptionImpl#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.LayoutOptionImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.LayoutOptionImpl#getDescription <em>Description</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.LayoutOptionImpl#getTargets <em>Targets</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class LayoutOptionImpl extends MinimalEObjectImpl.Container implements LayoutOption {
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
    protected String id = LayoutOptionImpl.ID_EDEFAULT;

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
    protected String label = LayoutOptionImpl.LABEL_EDEFAULT;

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
    protected String description = LayoutOptionImpl.DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getTargets() <em>Targets</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTargets()
     * @generated
     * @ordered
     */
    protected EList<LayoutOptionTarget> targets;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected LayoutOptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.LAYOUT_OPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYOUT_OPTION__ID, oldId, id));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYOUT_OPTION__LABEL, oldLabel, label));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYOUT_OPTION__DESCRIPTION, oldDescription, description));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<LayoutOptionTarget> getTargets() {
        if (targets == null) {
            targets = new EDataTypeUniqueEList<LayoutOptionTarget>(LayoutOptionTarget.class, this, DescriptionPackage.LAYOUT_OPTION__TARGETS);
        }
        return targets;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.LAYOUT_OPTION__ID:
            return getId();
        case DescriptionPackage.LAYOUT_OPTION__LABEL:
            return getLabel();
        case DescriptionPackage.LAYOUT_OPTION__DESCRIPTION:
            return getDescription();
        case DescriptionPackage.LAYOUT_OPTION__TARGETS:
            return getTargets();
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
        case DescriptionPackage.LAYOUT_OPTION__ID:
            setId((String) newValue);
            return;
        case DescriptionPackage.LAYOUT_OPTION__LABEL:
            setLabel((String) newValue);
            return;
        case DescriptionPackage.LAYOUT_OPTION__DESCRIPTION:
            setDescription((String) newValue);
            return;
        case DescriptionPackage.LAYOUT_OPTION__TARGETS:
            getTargets().clear();
            getTargets().addAll((Collection<? extends LayoutOptionTarget>) newValue);
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
        case DescriptionPackage.LAYOUT_OPTION__ID:
            setId(LayoutOptionImpl.ID_EDEFAULT);
            return;
        case DescriptionPackage.LAYOUT_OPTION__LABEL:
            setLabel(LayoutOptionImpl.LABEL_EDEFAULT);
            return;
        case DescriptionPackage.LAYOUT_OPTION__DESCRIPTION:
            setDescription(LayoutOptionImpl.DESCRIPTION_EDEFAULT);
            return;
        case DescriptionPackage.LAYOUT_OPTION__TARGETS:
            getTargets().clear();
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
        case DescriptionPackage.LAYOUT_OPTION__ID:
            return LayoutOptionImpl.ID_EDEFAULT == null ? id != null : !LayoutOptionImpl.ID_EDEFAULT.equals(id);
        case DescriptionPackage.LAYOUT_OPTION__LABEL:
            return LayoutOptionImpl.LABEL_EDEFAULT == null ? label != null : !LayoutOptionImpl.LABEL_EDEFAULT.equals(label);
        case DescriptionPackage.LAYOUT_OPTION__DESCRIPTION:
            return LayoutOptionImpl.DESCRIPTION_EDEFAULT == null ? description != null : !LayoutOptionImpl.DESCRIPTION_EDEFAULT.equals(description);
        case DescriptionPackage.LAYOUT_OPTION__TARGETS:
            return targets != null && !targets.isEmpty();
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
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", description: "); //$NON-NLS-1$
        result.append(description);
        result.append(", targets: "); //$NON-NLS-1$
        result.append(targets);
        result.append(')');
        return result.toString();
    }

} // LayoutOptionImpl
