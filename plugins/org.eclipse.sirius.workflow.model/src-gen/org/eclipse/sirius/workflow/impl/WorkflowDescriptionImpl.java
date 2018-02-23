/**
 *  Copyright (c) 2018 Obeo.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  Contributors:
 *     Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.workflow.impl;

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
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.workflow.PageDescription;
import org.eclipse.sirius.workflow.WorkflowDescription;
import org.eclipse.sirius.workflow.WorkflowPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.workflow.impl.WorkflowDescriptionImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.workflow.impl.WorkflowDescriptionImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.workflow.impl.WorkflowDescriptionImpl#getDocumentation <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.sirius.workflow.impl.WorkflowDescriptionImpl#getPages <em>Pages</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkflowDescriptionImpl extends MinimalEObjectImpl.Container implements WorkflowDescription {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = WorkflowDescriptionImpl.NAME_EDEFAULT;

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
    protected String label = WorkflowDescriptionImpl.LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = WorkflowDescriptionImpl.DOCUMENTATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getPages() <em>Pages</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getPages()
     * @generated
     * @ordered
     */
    protected EList<PageDescription> pages;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected WorkflowDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return WorkflowPackage.Literals.WORKFLOW_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.WORKFLOW_DESCRIPTION__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.WORKFLOW_DESCRIPTION__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDocumentation() {
        return documentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, WorkflowPackage.WORKFLOW_DESCRIPTION__DOCUMENTATION, oldDocumentation, documentation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<PageDescription> getPages() {
        if (pages == null) {
            pages = new EObjectContainmentEList.Resolving<PageDescription>(PageDescription.class, this, WorkflowPackage.WORKFLOW_DESCRIPTION__PAGES);
        }
        return pages;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case WorkflowPackage.WORKFLOW_DESCRIPTION__PAGES:
            return ((InternalEList<?>) getPages()).basicRemove(otherEnd, msgs);
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
        case WorkflowPackage.WORKFLOW_DESCRIPTION__NAME:
            return getName();
        case WorkflowPackage.WORKFLOW_DESCRIPTION__LABEL:
            return getLabel();
        case WorkflowPackage.WORKFLOW_DESCRIPTION__DOCUMENTATION:
            return getDocumentation();
        case WorkflowPackage.WORKFLOW_DESCRIPTION__PAGES:
            return getPages();
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
        case WorkflowPackage.WORKFLOW_DESCRIPTION__NAME:
            setName((String) newValue);
            return;
        case WorkflowPackage.WORKFLOW_DESCRIPTION__LABEL:
            setLabel((String) newValue);
            return;
        case WorkflowPackage.WORKFLOW_DESCRIPTION__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case WorkflowPackage.WORKFLOW_DESCRIPTION__PAGES:
            getPages().clear();
            getPages().addAll((Collection<? extends PageDescription>) newValue);
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
        case WorkflowPackage.WORKFLOW_DESCRIPTION__NAME:
            setName(WorkflowDescriptionImpl.NAME_EDEFAULT);
            return;
        case WorkflowPackage.WORKFLOW_DESCRIPTION__LABEL:
            setLabel(WorkflowDescriptionImpl.LABEL_EDEFAULT);
            return;
        case WorkflowPackage.WORKFLOW_DESCRIPTION__DOCUMENTATION:
            setDocumentation(WorkflowDescriptionImpl.DOCUMENTATION_EDEFAULT);
            return;
        case WorkflowPackage.WORKFLOW_DESCRIPTION__PAGES:
            getPages().clear();
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
        case WorkflowPackage.WORKFLOW_DESCRIPTION__NAME:
            return WorkflowDescriptionImpl.NAME_EDEFAULT == null ? name != null : !WorkflowDescriptionImpl.NAME_EDEFAULT.equals(name);
        case WorkflowPackage.WORKFLOW_DESCRIPTION__LABEL:
            return WorkflowDescriptionImpl.LABEL_EDEFAULT == null ? label != null : !WorkflowDescriptionImpl.LABEL_EDEFAULT.equals(label);
        case WorkflowPackage.WORKFLOW_DESCRIPTION__DOCUMENTATION:
            return WorkflowDescriptionImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !WorkflowDescriptionImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case WorkflowPackage.WORKFLOW_DESCRIPTION__PAGES:
            return pages != null && !pages.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case WorkflowPackage.WORKFLOW_DESCRIPTION__NAME:
                return DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case WorkflowPackage.WORKFLOW_DESCRIPTION__LABEL:
                return DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == DocumentedElement.class) {
            switch (derivedFeatureID) {
            case WorkflowPackage.WORKFLOW_DESCRIPTION__DOCUMENTATION:
                return DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return WorkflowPackage.WORKFLOW_DESCRIPTION__NAME;
            case DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return WorkflowPackage.WORKFLOW_DESCRIPTION__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == DocumentedElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION:
                return WorkflowPackage.WORKFLOW_DESCRIPTION__DOCUMENTATION;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(')');
        return result.toString();
    }

} // WorkflowDescriptionImpl
