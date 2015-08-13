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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.description.contribution.AddFeatureContribution;
import org.eclipse.sirius.description.contribution.ClearFeatureContribution;
import org.eclipse.sirius.description.contribution.ComputedEObjectReference;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionFactory;
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
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class ContributionPackageImpl extends EPackageImpl implements ContributionPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass featureContributionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass ignoreFeatureContributionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass setFeatureContributionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass addFeatureContributionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass removeFeatureContributionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass clearFeatureContributionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass resetFeatureContributionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass eObjectReferenceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass directEObjectReferenceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass computedEObjectReferenceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass contributionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass contributionProviderEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass contributionPointEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.description.contribution.ContributionPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ContributionPackageImpl() {
        super(ContributionPackage.eNS_URI, ContributionFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link ContributionPackage#eINSTANCE}
     * when that field is accessed. Clients should not invoke it directly.
     * Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ContributionPackage init() {
        if (ContributionPackageImpl.isInited) {
            return (ContributionPackage) EPackage.Registry.INSTANCE.getEPackage(ContributionPackage.eNS_URI);
        }

        // Obtain or create and register package
        ContributionPackageImpl theContributionPackage = (ContributionPackageImpl) (EPackage.Registry.INSTANCE.get(ContributionPackage.eNS_URI) instanceof ContributionPackageImpl ? EPackage.Registry.INSTANCE
                .get(ContributionPackage.eNS_URI) : new ContributionPackageImpl());

        ContributionPackageImpl.isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theContributionPackage.createPackageContents();

        // Initialize created meta-data
        theContributionPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theContributionPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ContributionPackage.eNS_URI, theContributionPackage);
        return theContributionPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFeatureContribution() {
        return featureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getFeatureContribution_SourceFeature() {
        return (EReference) featureContributionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getFeatureContribution_TargetFeature() {
        return (EReference) featureContributionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getIgnoreFeatureContribution() {
        return ignoreFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSetFeatureContribution() {
        return setFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAddFeatureContribution() {
        return addFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRemoveFeatureContribution() {
        return removeFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getClearFeatureContribution() {
        return clearFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getResetFeatureContribution() {
        return resetFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEObjectReference() {
        return eObjectReferenceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDirectEObjectReference() {
        return directEObjectReferenceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDirectEObjectReference_Value() {
        return (EReference) directEObjectReferenceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getComputedEObjectReference() {
        return computedEObjectReferenceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getComputedEObjectReference_ValueExpression() {
        return (EAttribute) computedEObjectReferenceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getContribution() {
        return contributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContribution_Source() {
        return (EReference) contributionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContribution_Target() {
        return (EReference) contributionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContribution_FeatureMask() {
        return (EReference) contributionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContribution_SubContributions() {
        return (EReference) contributionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getContribution_Description() {
        return (EAttribute) contributionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getContributionProvider() {
        return contributionProviderEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContributionProvider_Contributions() {
        return (EReference) contributionProviderEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getContributionPoint() {
        return contributionPointEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getContributionPoint_Origin() {
        return (EAttribute) contributionPointEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContributionPoint_Contributed() {
        return (EReference) contributionPointEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContributionFactory getContributionFactory() {
        return (ContributionFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        featureContributionEClass = createEClass(ContributionPackage.FEATURE_CONTRIBUTION);
        createEReference(featureContributionEClass, ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE);
        createEReference(featureContributionEClass, ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE);

        ignoreFeatureContributionEClass = createEClass(ContributionPackage.IGNORE_FEATURE_CONTRIBUTION);

        setFeatureContributionEClass = createEClass(ContributionPackage.SET_FEATURE_CONTRIBUTION);

        addFeatureContributionEClass = createEClass(ContributionPackage.ADD_FEATURE_CONTRIBUTION);

        removeFeatureContributionEClass = createEClass(ContributionPackage.REMOVE_FEATURE_CONTRIBUTION);

        clearFeatureContributionEClass = createEClass(ContributionPackage.CLEAR_FEATURE_CONTRIBUTION);

        resetFeatureContributionEClass = createEClass(ContributionPackage.RESET_FEATURE_CONTRIBUTION);

        eObjectReferenceEClass = createEClass(ContributionPackage.EOBJECT_REFERENCE);

        directEObjectReferenceEClass = createEClass(ContributionPackage.DIRECT_EOBJECT_REFERENCE);
        createEReference(directEObjectReferenceEClass, ContributionPackage.DIRECT_EOBJECT_REFERENCE__VALUE);

        computedEObjectReferenceEClass = createEClass(ContributionPackage.COMPUTED_EOBJECT_REFERENCE);
        createEAttribute(computedEObjectReferenceEClass, ContributionPackage.COMPUTED_EOBJECT_REFERENCE__VALUE_EXPRESSION);

        contributionEClass = createEClass(ContributionPackage.CONTRIBUTION);
        createEReference(contributionEClass, ContributionPackage.CONTRIBUTION__SOURCE);
        createEReference(contributionEClass, ContributionPackage.CONTRIBUTION__TARGET);
        createEReference(contributionEClass, ContributionPackage.CONTRIBUTION__FEATURE_MASK);
        createEReference(contributionEClass, ContributionPackage.CONTRIBUTION__SUB_CONTRIBUTIONS);
        createEAttribute(contributionEClass, ContributionPackage.CONTRIBUTION__DESCRIPTION);

        contributionProviderEClass = createEClass(ContributionPackage.CONTRIBUTION_PROVIDER);
        createEReference(contributionProviderEClass, ContributionPackage.CONTRIBUTION_PROVIDER__CONTRIBUTIONS);

        contributionPointEClass = createEClass(ContributionPackage.CONTRIBUTION_POINT);
        createEAttribute(contributionPointEClass, ContributionPackage.CONTRIBUTION_POINT__ORIGIN);
        createEReference(contributionPointEClass, ContributionPackage.CONTRIBUTION_POINT__CONTRIBUTED);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(ContributionPackage.eNAME);
        setNsPrefix(ContributionPackage.eNS_PREFIX);
        setNsURI(ContributionPackage.eNS_URI);

        // Obtain other dependent packages
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        ignoreFeatureContributionEClass.getESuperTypes().add(this.getFeatureContribution());
        setFeatureContributionEClass.getESuperTypes().add(this.getFeatureContribution());
        addFeatureContributionEClass.getESuperTypes().add(this.getFeatureContribution());
        removeFeatureContributionEClass.getESuperTypes().add(this.getFeatureContribution());
        clearFeatureContributionEClass.getESuperTypes().add(this.getFeatureContribution());
        resetFeatureContributionEClass.getESuperTypes().add(this.getFeatureContribution());
        directEObjectReferenceEClass.getESuperTypes().add(this.getEObjectReference());
        computedEObjectReferenceEClass.getESuperTypes().add(this.getEObjectReference());

        // Initialize classes and features; add operations and parameters
        initEClass(featureContributionEClass, FeatureContribution.class, "FeatureContribution", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getFeatureContribution_SourceFeature(),
                theEcorePackage.getEStructuralFeature(),
                null,
                "sourceFeature", null, 1, 1, FeatureContribution.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getFeatureContribution_TargetFeature(),
                theEcorePackage.getEStructuralFeature(),
                null,
                "targetFeature", null, 1, 1, FeatureContribution.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(ignoreFeatureContributionEClass, IgnoreFeatureContribution.class,
                "IgnoreFeatureContribution", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(setFeatureContributionEClass, SetFeatureContribution.class,
                "SetFeatureContribution", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(addFeatureContributionEClass, AddFeatureContribution.class,
                "AddFeatureContribution", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(removeFeatureContributionEClass, RemoveFeatureContribution.class,
                "RemoveFeatureContribution", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(clearFeatureContributionEClass, ClearFeatureContribution.class,
                "ClearFeatureContribution", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(resetFeatureContributionEClass, ResetFeatureContribution.class,
                "ResetFeatureContribution", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(eObjectReferenceEClass, EObjectReference.class, "EObjectReference", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(directEObjectReferenceEClass, DirectEObjectReference.class,
                "DirectEObjectReference", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getDirectEObjectReference_Value(),
                theEcorePackage.getEObject(),
                null,
                "value", null, 1, 1, DirectEObjectReference.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(computedEObjectReferenceEClass, ComputedEObjectReference.class,
                "ComputedEObjectReference", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getComputedEObjectReference_ValueExpression(),
                theEcorePackage.getEString(),
                "valueExpression", null, 1, 1, ComputedEObjectReference.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(contributionEClass, Contribution.class, "Contribution", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getContribution_Source(),
                this.getEObjectReference(),
                null,
                "source", null, 1, 1, Contribution.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getContribution_Target(),
                this.getEObjectReference(),
                null,
                "target", null, 1, 1, Contribution.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getContribution_FeatureMask(),
                this.getFeatureContribution(),
                null,
                "featureMask", null, 1, -1, Contribution.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, !EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getContribution_SubContributions(),
                this.getContribution(),
                null,
                "subContributions", null, 0, -1, Contribution.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getContribution_Description(),
                theEcorePackage.getEString(),
                "description", null, 0, 1, Contribution.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(contributionProviderEClass, ContributionProvider.class, "ContributionProvider", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getContributionProvider_Contributions(),
                this.getContribution(),
                null,
                "contributions", null, 0, -1, ContributionProvider.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(contributionPointEClass, ContributionPoint.class, "ContributionPoint", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getContributionPoint_Origin(),
                theEcorePackage.getEString(),
                "origin", null, 1, 1, ContributionPoint.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getContributionPoint_Contributed(),
                theEcorePackage.getEObject(),
                null,
                "contributed", null, 1, 1, ContributionPoint.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        // Create resource
        createResource(ContributionPackage.eNS_URI);
    }

} // ContributionPackageImpl
