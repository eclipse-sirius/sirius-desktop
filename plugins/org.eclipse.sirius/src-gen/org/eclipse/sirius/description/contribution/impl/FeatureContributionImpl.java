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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.FeatureContribution;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Feature Contribution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.FeatureContributionImpl#getSourceFeature
 * <em>Source Feature</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.FeatureContributionImpl#getTargetFeature
 * <em>Target Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FeatureContributionImpl extends EObjectImpl implements FeatureContribution {
    /**
     * The cached value of the '{@link #getSourceFeature()
     * <em>Source Feature</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getSourceFeature()
     * @generated
     * @ordered
     */
    protected EStructuralFeature sourceFeature;

    /**
     * The cached value of the '{@link #getTargetFeature()
     * <em>Target Feature</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTargetFeature()
     * @generated
     * @ordered
     */
    protected EStructuralFeature targetFeature;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected FeatureContributionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContributionPackage.Literals.FEATURE_CONTRIBUTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EStructuralFeature getSourceFeature() {
        if (sourceFeature != null && sourceFeature.eIsProxy()) {
            InternalEObject oldSourceFeature = (InternalEObject) sourceFeature;
            sourceFeature = (EStructuralFeature) eResolveProxy(oldSourceFeature);
            if (sourceFeature != oldSourceFeature) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE, oldSourceFeature, sourceFeature));
                }
            }
        }
        return sourceFeature;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EStructuralFeature basicGetSourceFeature() {
        return sourceFeature;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSourceFeature(EStructuralFeature newSourceFeature) {
        EStructuralFeature oldSourceFeature = sourceFeature;
        sourceFeature = newSourceFeature;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE, oldSourceFeature, sourceFeature));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EStructuralFeature getTargetFeature() {
        if (targetFeature != null && targetFeature.eIsProxy()) {
            InternalEObject oldTargetFeature = (InternalEObject) targetFeature;
            targetFeature = (EStructuralFeature) eResolveProxy(oldTargetFeature);
            if (targetFeature != oldTargetFeature) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE, oldTargetFeature, targetFeature));
                }
            }
        }
        return targetFeature;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EStructuralFeature basicGetTargetFeature() {
        return targetFeature;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTargetFeature(EStructuralFeature newTargetFeature) {
        EStructuralFeature oldTargetFeature = targetFeature;
        targetFeature = newTargetFeature;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE, oldTargetFeature, targetFeature));
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
        case ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE:
            if (resolve) {
                return getSourceFeature();
            }
            return basicGetSourceFeature();
        case ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE:
            if (resolve) {
                return getTargetFeature();
            }
            return basicGetTargetFeature();
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
        case ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE:
            setSourceFeature((EStructuralFeature) newValue);
            return;
        case ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE:
            setTargetFeature((EStructuralFeature) newValue);
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
        case ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE:
            setSourceFeature((EStructuralFeature) null);
            return;
        case ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE:
            setTargetFeature((EStructuralFeature) null);
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
        case ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE:
            return sourceFeature != null;
        case ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE:
            return targetFeature != null;
        }
        return super.eIsSet(featureID);
    }

} // FeatureContributionImpl
