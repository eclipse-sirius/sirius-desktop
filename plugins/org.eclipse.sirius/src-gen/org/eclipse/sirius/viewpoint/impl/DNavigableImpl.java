/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.DNavigable;
import org.eclipse.sirius.viewpoint.DNavigationLink;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DNavigable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DNavigableImpl#getOwnedNavigationLinks
 * <em>Owned Navigation Links</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class DNavigableImpl extends EObjectImpl implements DNavigable {
    /**
     * The cached value of the '{@link #getOwnedNavigationLinks()
     * <em>Owned Navigation Links</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedNavigationLinks()
     * @generated
     * @ordered
     */
    protected EList<DNavigationLink> ownedNavigationLinks;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DNavigableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DNAVIGABLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DNavigationLink> getOwnedNavigationLinks() {
        if (ownedNavigationLinks == null) {
            ownedNavigationLinks = new EObjectContainmentEList.Resolving<DNavigationLink>(DNavigationLink.class, this, ViewpointPackage.DNAVIGABLE__OWNED_NAVIGATION_LINKS);
        }
        return ownedNavigationLinks;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.DNAVIGABLE__OWNED_NAVIGATION_LINKS:
            return ((InternalEList<?>) getOwnedNavigationLinks()).basicRemove(otherEnd, msgs);
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
        case ViewpointPackage.DNAVIGABLE__OWNED_NAVIGATION_LINKS:
            return getOwnedNavigationLinks();
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
        case ViewpointPackage.DNAVIGABLE__OWNED_NAVIGATION_LINKS:
            getOwnedNavigationLinks().clear();
            getOwnedNavigationLinks().addAll((Collection<? extends DNavigationLink>) newValue);
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
        case ViewpointPackage.DNAVIGABLE__OWNED_NAVIGATION_LINKS:
            getOwnedNavigationLinks().clear();
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
        case ViewpointPackage.DNAVIGABLE__OWNED_NAVIGATION_LINKS:
            return ownedNavigationLinks != null && !ownedNavigationLinks.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // DNavigableImpl
