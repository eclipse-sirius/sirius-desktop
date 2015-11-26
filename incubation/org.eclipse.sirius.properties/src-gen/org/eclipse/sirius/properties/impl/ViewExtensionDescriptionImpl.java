/**
 * Copyright (c) 2015 Obeo.
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

import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ViewExtensionDescription;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View Extension Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getMetamodels <em>Metamodels</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getPages <em>Pages</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getDefaultPage <em>Default Page</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getLabelExpression <em>Label Expression</em>}</li>
 *   <li>{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl#getGroups <em>Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewExtensionDescriptionImpl extends MinimalEObjectImpl.Container implements ViewExtensionDescription {
    /**
     * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected static final String IDENTIFIER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected String identifier = IDENTIFIER_EDEFAULT;

    /**
     * The cached value of the '{@link #getMetamodels() <em>Metamodels</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMetamodels()
     * @generated
     * @ordered
     */
    protected EList<EPackage> metamodels;

    /**
     * The cached value of the '{@link #getPages() <em>Pages</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPages()
     * @generated
     * @ordered
     */
    protected EList<PageDescription> pages;

    /**
     * The cached value of the '{@link #getDefaultPage() <em>Default Page</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultPage()
     * @generated
     * @ordered
     */
    protected PageDescription defaultPage;

    /**
     * The default value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected String labelExpression = LABEL_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getGroups() <em>Groups</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroups()
     * @generated
     * @ordered
     */
    protected EList<GroupDescription> groups;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ViewExtensionDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.VIEW_EXTENSION_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIdentifier(String newIdentifier) {
        String oldIdentifier = identifier;
        identifier = newIdentifier;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__IDENTIFIER, oldIdentifier, identifier));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<EPackage> getMetamodels() {
        if (metamodels == null) {
            metamodels = new EObjectResolvingEList<EPackage>(EPackage.class, this, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS);
        }
        return metamodels;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<PageDescription> getPages() {
        if (pages == null) {
            pages = new EObjectContainmentEList<PageDescription>(PageDescription.class, this, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__PAGES);
        }
        return pages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PageDescription getDefaultPage() {
        if (defaultPage != null && defaultPage.eIsProxy()) {
            InternalEObject oldDefaultPage = (InternalEObject)defaultPage;
            defaultPage = (PageDescription)eResolveProxy(oldDefaultPage);
            if (defaultPage != oldDefaultPage) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE, oldDefaultPage, defaultPage));
            }
        }
        return defaultPage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PageDescription basicGetDefaultPage() {
        return defaultPage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefaultPage(PageDescription newDefaultPage) {
        PageDescription oldDefaultPage = defaultPage;
        defaultPage = newDefaultPage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE, oldDefaultPage, defaultPage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLabelExpression() {
        return labelExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabelExpression(String newLabelExpression) {
        String oldLabelExpression = labelExpression;
        labelExpression = newLabelExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<GroupDescription> getGroups() {
        if (groups == null) {
            groups = new EObjectContainmentEList<GroupDescription>(GroupDescription.class, this, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__GROUPS);
        }
        return groups;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__PAGES:
                return ((InternalEList<?>)getPages()).basicRemove(otherEnd, msgs);
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__GROUPS:
                return ((InternalEList<?>)getGroups()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__IDENTIFIER:
                return getIdentifier();
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS:
                return getMetamodels();
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__PAGES:
                return getPages();
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE:
                if (resolve) return getDefaultPage();
                return basicGetDefaultPage();
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION:
                return getLabelExpression();
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__GROUPS:
                return getGroups();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__IDENTIFIER:
                setIdentifier((String)newValue);
                return;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS:
                getMetamodels().clear();
                getMetamodels().addAll((Collection<? extends EPackage>)newValue);
                return;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__PAGES:
                getPages().clear();
                getPages().addAll((Collection<? extends PageDescription>)newValue);
                return;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE:
                setDefaultPage((PageDescription)newValue);
                return;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION:
                setLabelExpression((String)newValue);
                return;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__GROUPS:
                getGroups().clear();
                getGroups().addAll((Collection<? extends GroupDescription>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__IDENTIFIER:
                setIdentifier(IDENTIFIER_EDEFAULT);
                return;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS:
                getMetamodels().clear();
                return;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__PAGES:
                getPages().clear();
                return;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE:
                setDefaultPage((PageDescription)null);
                return;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION:
                setLabelExpression(LABEL_EXPRESSION_EDEFAULT);
                return;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__GROUPS:
                getGroups().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__IDENTIFIER:
                return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS:
                return metamodels != null && !metamodels.isEmpty();
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__PAGES:
                return pages != null && !pages.isEmpty();
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE:
                return defaultPage != null;
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION:
                return LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
            case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__GROUPS:
                return groups != null && !groups.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (identifier: ");
        result.append(identifier);
        result.append(", labelExpression: ");
        result.append(labelExpression);
        result.append(')');
        return result.toString();
    }

} //ViewExtensionDescriptionImpl
