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
package org.eclipse.sirius.viewpoint.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.SytemColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserColorsPalette;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Group</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.GroupImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.GroupImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.GroupImpl#getOwnedViewpoints
 * <em>Owned Viewpoints</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.GroupImpl#getSystemColorsPalette
 * <em>System Colors Palette</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.GroupImpl#getUserColorsPalettes
 * <em>User Colors Palettes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.GroupImpl#getVersion
 * <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupImpl extends DModelElementImpl implements Group {
    /**
     * The default value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = GroupImpl.DOCUMENTATION_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = GroupImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwnedViewpoints()
     * <em>Owned Viewpoints</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedViewpoints()
     * @generated
     * @ordered
     */
    protected EList<Viewpoint> ownedViewpoints;

    /**
     * The cached value of the '{@link #getSystemColorsPalette()
     * <em>System Colors Palette</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSystemColorsPalette()
     * @generated
     * @ordered
     */
    protected SytemColorsPalette systemColorsPalette;

    /**
     * The cached value of the '{@link #getUserColorsPalettes()
     * <em>User Colors Palettes</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getUserColorsPalettes()
     * @generated
     * @ordered
     */
    protected EList<UserColorsPalette> userColorsPalettes;

    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final String VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected String version = GroupImpl.VERSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected GroupImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.GROUP;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.GROUP__DOCUMENTATION, oldDocumentation, documentation));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.GROUP__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Viewpoint> getOwnedViewpoints() {
        if (ownedViewpoints == null) {
            ownedViewpoints = new EObjectContainmentEList.Resolving<Viewpoint>(Viewpoint.class, this, DescriptionPackage.GROUP__OWNED_VIEWPOINTS);
        }
        return ownedViewpoints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SytemColorsPalette getSystemColorsPalette() {
        if (systemColorsPalette != null && systemColorsPalette.eIsProxy()) {
            InternalEObject oldSystemColorsPalette = (InternalEObject) systemColorsPalette;
            systemColorsPalette = (SytemColorsPalette) eResolveProxy(oldSystemColorsPalette);
            if (systemColorsPalette != oldSystemColorsPalette) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.GROUP__SYSTEM_COLORS_PALETTE, oldSystemColorsPalette, systemColorsPalette));
                }
            }
        }
        return systemColorsPalette;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SytemColorsPalette basicGetSystemColorsPalette() {
        return systemColorsPalette;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSystemColorsPalette(SytemColorsPalette newSystemColorsPalette) {
        SytemColorsPalette oldSystemColorsPalette = systemColorsPalette;
        systemColorsPalette = newSystemColorsPalette;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.GROUP__SYSTEM_COLORS_PALETTE, oldSystemColorsPalette, systemColorsPalette));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<UserColorsPalette> getUserColorsPalettes() {
        if (userColorsPalettes == null) {
            userColorsPalettes = new EObjectContainmentEList.Resolving<UserColorsPalette>(UserColorsPalette.class, this, DescriptionPackage.GROUP__USER_COLORS_PALETTES);
        }
        return userColorsPalettes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.GROUP__VERSION, oldVersion, version));
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
        case DescriptionPackage.GROUP__OWNED_VIEWPOINTS:
            return ((InternalEList<?>) getOwnedViewpoints()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.GROUP__USER_COLORS_PALETTES:
            return ((InternalEList<?>) getUserColorsPalettes()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.GROUP__DOCUMENTATION:
            return getDocumentation();
        case DescriptionPackage.GROUP__NAME:
            return getName();
        case DescriptionPackage.GROUP__OWNED_VIEWPOINTS:
            return getOwnedViewpoints();
        case DescriptionPackage.GROUP__SYSTEM_COLORS_PALETTE:
            if (resolve) {
                return getSystemColorsPalette();
            }
            return basicGetSystemColorsPalette();
        case DescriptionPackage.GROUP__USER_COLORS_PALETTES:
            return getUserColorsPalettes();
        case DescriptionPackage.GROUP__VERSION:
            return getVersion();
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
        case DescriptionPackage.GROUP__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case DescriptionPackage.GROUP__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.GROUP__OWNED_VIEWPOINTS:
            getOwnedViewpoints().clear();
            getOwnedViewpoints().addAll((Collection<? extends Viewpoint>) newValue);
            return;
        case DescriptionPackage.GROUP__SYSTEM_COLORS_PALETTE:
            setSystemColorsPalette((SytemColorsPalette) newValue);
            return;
        case DescriptionPackage.GROUP__USER_COLORS_PALETTES:
            getUserColorsPalettes().clear();
            getUserColorsPalettes().addAll((Collection<? extends UserColorsPalette>) newValue);
            return;
        case DescriptionPackage.GROUP__VERSION:
            setVersion((String) newValue);
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
        case DescriptionPackage.GROUP__DOCUMENTATION:
            setDocumentation(GroupImpl.DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.GROUP__NAME:
            setName(GroupImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.GROUP__OWNED_VIEWPOINTS:
            getOwnedViewpoints().clear();
            return;
        case DescriptionPackage.GROUP__SYSTEM_COLORS_PALETTE:
            setSystemColorsPalette((SytemColorsPalette) null);
            return;
        case DescriptionPackage.GROUP__USER_COLORS_PALETTES:
            getUserColorsPalettes().clear();
            return;
        case DescriptionPackage.GROUP__VERSION:
            setVersion(GroupImpl.VERSION_EDEFAULT);
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
        case DescriptionPackage.GROUP__DOCUMENTATION:
            return GroupImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !GroupImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case DescriptionPackage.GROUP__NAME:
            return GroupImpl.NAME_EDEFAULT == null ? name != null : !GroupImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.GROUP__OWNED_VIEWPOINTS:
            return ownedViewpoints != null && !ownedViewpoints.isEmpty();
        case DescriptionPackage.GROUP__SYSTEM_COLORS_PALETTE:
            return systemColorsPalette != null;
        case DescriptionPackage.GROUP__USER_COLORS_PALETTES:
            return userColorsPalettes != null && !userColorsPalettes.isEmpty();
        case DescriptionPackage.GROUP__VERSION:
            return GroupImpl.VERSION_EDEFAULT == null ? version != null : !GroupImpl.VERSION_EDEFAULT.equals(version);
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
            case DescriptionPackage.GROUP__DOCUMENTATION:
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
                return DescriptionPackage.GROUP__DOCUMENTATION;
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
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", version: "); //$NON-NLS-1$
        result.append(version);
        result.append(')');
        return result.toString();
    }

} // GroupImpl
