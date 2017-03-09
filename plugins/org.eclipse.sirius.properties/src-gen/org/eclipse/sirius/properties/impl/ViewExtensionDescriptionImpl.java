/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>View Extension Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getMetamodels <em>Metamodels</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getCategories <em>Categories</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ViewExtensionDescriptionImpl extends MinimalEObjectImpl.Container implements ViewExtensionDescription {
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
    protected String name = ViewExtensionDescriptionImpl.NAME_EDEFAULT;

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
    protected String label = ViewExtensionDescriptionImpl.LABEL_EDEFAULT;

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
    protected String documentation = ViewExtensionDescriptionImpl.DOCUMENTATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getMetamodels() <em>Metamodels</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getMetamodels()
     * @generated
     * @ordered
     */
    protected EList<EPackage> metamodels;

    /**
     * The cached value of the '{@link #getCategories() <em>Categories</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCategories()
     * @generated
     * @ordered
     */
    protected EList<Category> categories;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ViewExtensionDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.VIEW_EXTENSION_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL, oldLabel, label));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DOCUMENTATION, oldDocumentation, documentation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EPackage> getMetamodels() {
        if (metamodels == null) {
            metamodels = new EObjectResolvingEList<EPackage>(EPackage.class, this, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS);
        }
        return metamodels;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Category> getCategories() {
        if (categories == null) {
            categories = new EObjectContainmentEList<Category>(Category.class, this, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__CATEGORIES);
        }
        return categories;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__CATEGORIES:
            return ((InternalEList<?>) getCategories()).basicRemove(otherEnd, msgs);
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
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__NAME:
            return getName();
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL:
            return getLabel();
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DOCUMENTATION:
            return getDocumentation();
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS:
            return getMetamodels();
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__CATEGORIES:
            return getCategories();
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
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__NAME:
            setName((String) newValue);
            return;
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL:
            setLabel((String) newValue);
            return;
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS:
            getMetamodels().clear();
            getMetamodels().addAll((Collection<? extends EPackage>) newValue);
            return;
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__CATEGORIES:
            getCategories().clear();
            getCategories().addAll((Collection<? extends Category>) newValue);
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
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__NAME:
            setName(ViewExtensionDescriptionImpl.NAME_EDEFAULT);
            return;
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL:
            setLabel(ViewExtensionDescriptionImpl.LABEL_EDEFAULT);
            return;
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DOCUMENTATION:
            setDocumentation(ViewExtensionDescriptionImpl.DOCUMENTATION_EDEFAULT);
            return;
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS:
            getMetamodels().clear();
            return;
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__CATEGORIES:
            getCategories().clear();
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
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__NAME:
            return ViewExtensionDescriptionImpl.NAME_EDEFAULT == null ? name != null : !ViewExtensionDescriptionImpl.NAME_EDEFAULT.equals(name);
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL:
            return ViewExtensionDescriptionImpl.LABEL_EDEFAULT == null ? label != null : !ViewExtensionDescriptionImpl.LABEL_EDEFAULT.equals(label);
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DOCUMENTATION:
            return ViewExtensionDescriptionImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !ViewExtensionDescriptionImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS:
            return metamodels != null && !metamodels.isEmpty();
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__CATEGORIES:
            return categories != null && !categories.isEmpty();
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
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__NAME:
                return DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL:
                return DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == DocumentedElement.class) {
            switch (derivedFeatureID) {
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DOCUMENTATION:
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
                return PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__NAME;
            case DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == DocumentedElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION:
                return PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DOCUMENTATION;
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

} // ViewExtensionDescriptionImpl
