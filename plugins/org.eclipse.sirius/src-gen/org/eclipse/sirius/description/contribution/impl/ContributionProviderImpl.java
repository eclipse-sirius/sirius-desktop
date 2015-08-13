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
package org.eclipse.sirius.description.contribution.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.ContributionProvider;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.ContributionProviderImpl#getContributions
 * <em>Contributions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ContributionProviderImpl extends EObjectImpl implements ContributionProvider {
    /**
     * The cached value of the '{@link #getContributions()
     * <em>Contributions</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getContributions()
     * @generated
     * @ordered
     */
    protected EList<Contribution> contributions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ContributionProviderImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContributionPackage.Literals.CONTRIBUTION_PROVIDER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Contribution> getContributions() {
        if (contributions == null) {
            contributions = new EObjectContainmentEList.Resolving<Contribution>(Contribution.class, this, ContributionPackage.CONTRIBUTION_PROVIDER__CONTRIBUTIONS);
        }
        return contributions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ContributionPackage.CONTRIBUTION_PROVIDER__CONTRIBUTIONS:
            return ((InternalEList<?>) getContributions()).basicRemove(otherEnd, msgs);
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
        case ContributionPackage.CONTRIBUTION_PROVIDER__CONTRIBUTIONS:
            return getContributions();
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
        case ContributionPackage.CONTRIBUTION_PROVIDER__CONTRIBUTIONS:
            getContributions().clear();
            getContributions().addAll((Collection<? extends Contribution>) newValue);
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
        case ContributionPackage.CONTRIBUTION_PROVIDER__CONTRIBUTIONS:
            getContributions().clear();
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
        case ContributionPackage.CONTRIBUTION_PROVIDER__CONTRIBUTIONS:
            return contributions != null && !contributions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ContributionProviderImpl
