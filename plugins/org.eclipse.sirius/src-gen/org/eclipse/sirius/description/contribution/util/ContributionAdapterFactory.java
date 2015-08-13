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
package org.eclipse.sirius.description.contribution.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.description.contribution.AddFeatureContribution;
import org.eclipse.sirius.description.contribution.ClearFeatureContribution;
import org.eclipse.sirius.description.contribution.ComputedEObjectReference;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.ContributionPoint;
import org.eclipse.sirius.description.contribution.ContributionProvider;
import org.eclipse.sirius.description.contribution.DirectEObjectReference;
import org.eclipse.sirius.description.contribution.EObjectReference;
import org.eclipse.sirius.description.contribution.FeatureContribution;
import org.eclipse.sirius.description.contribution.IgnoreFeatureContribution;
import org.eclipse.sirius.description.contribution.RemoveFeatureContribution;
import org.eclipse.sirius.description.contribution.ResetFeatureContribution;
import org.eclipse.sirius.description.contribution.SetFeatureContribution;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 *
 * @see org.eclipse.sirius.description.contribution.ContributionPackage
 * @generated
 */
public class ContributionAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static ContributionPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public ContributionAdapterFactory() {
        if (ContributionAdapterFactory.modelPackage == null) {
            ContributionAdapterFactory.modelPackage = ContributionPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == ContributionAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == ContributionAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ContributionSwitch<Adapter> modelSwitch = new ContributionSwitch<Adapter>() {
        @Override
        public Adapter caseFeatureContribution(FeatureContribution object) {
            return createFeatureContributionAdapter();
        }

        @Override
        public Adapter caseIgnoreFeatureContribution(IgnoreFeatureContribution object) {
            return createIgnoreFeatureContributionAdapter();
        }

        @Override
        public Adapter caseSetFeatureContribution(SetFeatureContribution object) {
            return createSetFeatureContributionAdapter();
        }

        @Override
        public Adapter caseAddFeatureContribution(AddFeatureContribution object) {
            return createAddFeatureContributionAdapter();
        }

        @Override
        public Adapter caseRemoveFeatureContribution(RemoveFeatureContribution object) {
            return createRemoveFeatureContributionAdapter();
        }

        @Override
        public Adapter caseClearFeatureContribution(ClearFeatureContribution object) {
            return createClearFeatureContributionAdapter();
        }

        @Override
        public Adapter caseResetFeatureContribution(ResetFeatureContribution object) {
            return createResetFeatureContributionAdapter();
        }

        @Override
        public Adapter caseEObjectReference(EObjectReference object) {
            return createEObjectReferenceAdapter();
        }

        @Override
        public Adapter caseDirectEObjectReference(DirectEObjectReference object) {
            return createDirectEObjectReferenceAdapter();
        }

        @Override
        public Adapter caseComputedEObjectReference(ComputedEObjectReference object) {
            return createComputedEObjectReferenceAdapter();
        }

        @Override
        public Adapter caseContribution(Contribution object) {
            return createContributionAdapter();
        }

        @Override
        public Adapter caseContributionProvider(ContributionProvider object) {
            return createContributionProviderAdapter();
        }

        @Override
        public Adapter caseContributionPoint(ContributionPoint object) {
            return createContributionPointAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.FeatureContribution
     * <em>Feature Contribution</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.FeatureContribution
     * @generated
     */
    public Adapter createFeatureContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.IgnoreFeatureContribution
     * <em>Ignore Feature Contribution</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.IgnoreFeatureContribution
     * @generated
     */
    public Adapter createIgnoreFeatureContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.SetFeatureContribution
     * <em>Set Feature Contribution</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.SetFeatureContribution
     * @generated
     */
    public Adapter createSetFeatureContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.AddFeatureContribution
     * <em>Add Feature Contribution</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.AddFeatureContribution
     * @generated
     */
    public Adapter createAddFeatureContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.RemoveFeatureContribution
     * <em>Remove Feature Contribution</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.RemoveFeatureContribution
     * @generated
     */
    public Adapter createRemoveFeatureContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.ClearFeatureContribution
     * <em>Clear Feature Contribution</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.ClearFeatureContribution
     * @generated
     */
    public Adapter createClearFeatureContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.ResetFeatureContribution
     * <em>Reset Feature Contribution</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.ResetFeatureContribution
     * @generated
     */
    public Adapter createResetFeatureContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.EObjectReference
     * <em>EObject Reference</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.EObjectReference
     * @generated
     */
    public Adapter createEObjectReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.DirectEObjectReference
     * <em>Direct EObject Reference</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.DirectEObjectReference
     * @generated
     */
    public Adapter createDirectEObjectReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.ComputedEObjectReference
     * <em>Computed EObject Reference</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.ComputedEObjectReference
     * @generated
     */
    public Adapter createComputedEObjectReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.Contribution
     * <em>Contribution</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.Contribution
     * @generated
     */
    public Adapter createContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.ContributionProvider
     * <em>Provider</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.ContributionProvider
     * @generated
     */
    public Adapter createContributionProviderAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.contribution.ContributionPoint
     * <em>Point</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.description.contribution.ContributionPoint
     * @generated
     */
    public Adapter createContributionPointAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // ContributionAdapterFactory
