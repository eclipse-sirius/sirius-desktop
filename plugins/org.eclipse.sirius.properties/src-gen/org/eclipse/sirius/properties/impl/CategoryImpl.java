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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.AbstractOverrideDescription;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Category</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.CategoryImpl#getDocumentation <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CategoryImpl#getPages <em>Pages</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CategoryImpl#getGroups <em>Groups</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CategoryImpl#getOverrides <em>Overrides</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CategoryImpl extends IdentifiedElementImpl implements Category {
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
    protected String documentation = CategoryImpl.DOCUMENTATION_EDEFAULT;

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
     * The cached value of the '{@link #getGroups() <em>Groups</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getGroups()
     * @generated
     * @ordered
     */
    protected EList<GroupDescription> groups;

    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected EList<AbstractOverrideDescription> overrides;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CategoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.CATEGORY;
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CATEGORY__DOCUMENTATION, oldDocumentation, documentation));
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
            pages = new EObjectContainmentEList<PageDescription>(PageDescription.class, this, PropertiesPackage.CATEGORY__PAGES);
        }
        return pages;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<GroupDescription> getGroups() {
        if (groups == null) {
            groups = new EObjectContainmentEList<GroupDescription>(GroupDescription.class, this, PropertiesPackage.CATEGORY__GROUPS);
        }
        return groups;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractOverrideDescription> getOverrides() {
        if (overrides == null) {
            overrides = new EObjectContainmentEList<AbstractOverrideDescription>(AbstractOverrideDescription.class, this, PropertiesPackage.CATEGORY__OVERRIDES);
        }
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.CATEGORY__PAGES:
            return ((InternalEList<?>) getPages()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.CATEGORY__GROUPS:
            return ((InternalEList<?>) getGroups()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.CATEGORY__OVERRIDES:
            return ((InternalEList<?>) getOverrides()).basicRemove(otherEnd, msgs);
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
        case PropertiesPackage.CATEGORY__DOCUMENTATION:
            return getDocumentation();
        case PropertiesPackage.CATEGORY__PAGES:
            return getPages();
        case PropertiesPackage.CATEGORY__GROUPS:
            return getGroups();
        case PropertiesPackage.CATEGORY__OVERRIDES:
            return getOverrides();
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
        case PropertiesPackage.CATEGORY__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case PropertiesPackage.CATEGORY__PAGES:
            getPages().clear();
            getPages().addAll((Collection<? extends PageDescription>) newValue);
            return;
        case PropertiesPackage.CATEGORY__GROUPS:
            getGroups().clear();
            getGroups().addAll((Collection<? extends GroupDescription>) newValue);
            return;
        case PropertiesPackage.CATEGORY__OVERRIDES:
            getOverrides().clear();
            getOverrides().addAll((Collection<? extends AbstractOverrideDescription>) newValue);
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
        case PropertiesPackage.CATEGORY__DOCUMENTATION:
            setDocumentation(CategoryImpl.DOCUMENTATION_EDEFAULT);
            return;
        case PropertiesPackage.CATEGORY__PAGES:
            getPages().clear();
            return;
        case PropertiesPackage.CATEGORY__GROUPS:
            getGroups().clear();
            return;
        case PropertiesPackage.CATEGORY__OVERRIDES:
            getOverrides().clear();
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
        case PropertiesPackage.CATEGORY__DOCUMENTATION:
            return CategoryImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !CategoryImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case PropertiesPackage.CATEGORY__PAGES:
            return pages != null && !pages.isEmpty();
        case PropertiesPackage.CATEGORY__GROUPS:
            return groups != null && !groups.isEmpty();
        case PropertiesPackage.CATEGORY__OVERRIDES:
            return overrides != null && !overrides.isEmpty();
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
        if (baseClass == DocumentedElement.class) {
            switch (derivedFeatureID) {
            case PropertiesPackage.CATEGORY__DOCUMENTATION:
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
        if (baseClass == DocumentedElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION:
                return PropertiesPackage.CATEGORY__DOCUMENTATION;
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
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(')');
        return result.toString();
    }

} // CategoryImpl
