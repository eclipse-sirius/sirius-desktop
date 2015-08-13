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
package org.eclipse.sirius.description.contribution;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.description.contribution.ContributionPackage
 * @generated
 */
public interface ContributionFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    ContributionFactory eINSTANCE = org.eclipse.sirius.description.contribution.impl.ContributionFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Ignore Feature Contribution</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Ignore Feature Contribution</em>'.
     * @generated
     */
    IgnoreFeatureContribution createIgnoreFeatureContribution();

    /**
     * Returns a new object of class '<em>Set Feature Contribution</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Set Feature Contribution</em>'.
     * @generated
     */
    SetFeatureContribution createSetFeatureContribution();

    /**
     * Returns a new object of class '<em>Add Feature Contribution</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Add Feature Contribution</em>'.
     * @generated
     */
    AddFeatureContribution createAddFeatureContribution();

    /**
     * Returns a new object of class '<em>Remove Feature Contribution</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Remove Feature Contribution</em>'.
     * @generated
     */
    RemoveFeatureContribution createRemoveFeatureContribution();

    /**
     * Returns a new object of class '<em>Clear Feature Contribution</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Clear Feature Contribution</em>'.
     * @generated
     */
    ClearFeatureContribution createClearFeatureContribution();

    /**
     * Returns a new object of class '<em>Reset Feature Contribution</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Reset Feature Contribution</em>'.
     * @generated
     */
    ResetFeatureContribution createResetFeatureContribution();

    /**
     * Returns a new object of class '<em>Direct EObject Reference</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Direct EObject Reference</em>'.
     * @generated
     */
    DirectEObjectReference createDirectEObjectReference();

    /**
     * Returns a new object of class '<em>Computed EObject Reference</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Computed EObject Reference</em>'.
     * @generated
     */
    ComputedEObjectReference createComputedEObjectReference();

    /**
     * Returns a new object of class '<em>Contribution</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Contribution</em>'.
     * @generated
     */
    Contribution createContribution();

    /**
     * Returns a new object of class '<em>Point</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Point</em>'.
     * @generated
     */
    ContributionPoint createContributionPoint();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    ContributionPackage getContributionPackage();

} // ContributionFactory
