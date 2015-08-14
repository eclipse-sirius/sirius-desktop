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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.description.contribution.AddFeatureContribution;
import org.eclipse.sirius.description.contribution.ClearFeatureContribution;
import org.eclipse.sirius.description.contribution.ComputedEObjectReference;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionFactory;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.ContributionPoint;
import org.eclipse.sirius.description.contribution.DirectEObjectReference;
import org.eclipse.sirius.description.contribution.IgnoreFeatureContribution;
import org.eclipse.sirius.description.contribution.RemoveFeatureContribution;
import org.eclipse.sirius.description.contribution.ResetFeatureContribution;
import org.eclipse.sirius.description.contribution.SetFeatureContribution;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class ContributionFactoryImpl extends EFactoryImpl implements ContributionFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static ContributionFactory init() {
        try {
            ContributionFactory theContributionFactory = (ContributionFactory) EPackage.Registry.INSTANCE.getEFactory(ContributionPackage.eNS_URI);
            if (theContributionFactory != null) {
                return theContributionFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ContributionFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public ContributionFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case ContributionPackage.IGNORE_FEATURE_CONTRIBUTION:
            return createIgnoreFeatureContribution();
        case ContributionPackage.SET_FEATURE_CONTRIBUTION:
            return createSetFeatureContribution();
        case ContributionPackage.ADD_FEATURE_CONTRIBUTION:
            return createAddFeatureContribution();
        case ContributionPackage.REMOVE_FEATURE_CONTRIBUTION:
            return createRemoveFeatureContribution();
        case ContributionPackage.CLEAR_FEATURE_CONTRIBUTION:
            return createClearFeatureContribution();
        case ContributionPackage.RESET_FEATURE_CONTRIBUTION:
            return createResetFeatureContribution();
        case ContributionPackage.DIRECT_EOBJECT_REFERENCE:
            return createDirectEObjectReference();
        case ContributionPackage.COMPUTED_EOBJECT_REFERENCE:
            return createComputedEObjectReference();
        case ContributionPackage.CONTRIBUTION:
            return createContribution();
        case ContributionPackage.CONTRIBUTION_POINT:
            return createContributionPoint();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public IgnoreFeatureContribution createIgnoreFeatureContribution() {
        IgnoreFeatureContributionImpl ignoreFeatureContribution = new IgnoreFeatureContributionImpl();
        return ignoreFeatureContribution;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SetFeatureContribution createSetFeatureContribution() {
        SetFeatureContributionImpl setFeatureContribution = new SetFeatureContributionImpl();
        return setFeatureContribution;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AddFeatureContribution createAddFeatureContribution() {
        AddFeatureContributionImpl addFeatureContribution = new AddFeatureContributionImpl();
        return addFeatureContribution;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RemoveFeatureContribution createRemoveFeatureContribution() {
        RemoveFeatureContributionImpl removeFeatureContribution = new RemoveFeatureContributionImpl();
        return removeFeatureContribution;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ClearFeatureContribution createClearFeatureContribution() {
        ClearFeatureContributionImpl clearFeatureContribution = new ClearFeatureContributionImpl();
        return clearFeatureContribution;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResetFeatureContribution createResetFeatureContribution() {
        ResetFeatureContributionImpl resetFeatureContribution = new ResetFeatureContributionImpl();
        return resetFeatureContribution;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DirectEObjectReference createDirectEObjectReference() {
        DirectEObjectReferenceImpl directEObjectReference = new DirectEObjectReferenceImpl();
        return directEObjectReference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ComputedEObjectReference createComputedEObjectReference() {
        ComputedEObjectReferenceImpl computedEObjectReference = new ComputedEObjectReferenceImpl();
        return computedEObjectReference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Contribution createContribution() {
        ContributionImpl contribution = new ContributionImpl();
        return contribution;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContributionPoint createContributionPoint() {
        ContributionPointImpl contributionPoint = new ContributionPointImpl();
        return contributionPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContributionPackage getContributionPackage() {
        return (ContributionPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ContributionPackage getPackage() {
        return ContributionPackage.eINSTANCE;
    }

} // ContributionFactoryImpl
