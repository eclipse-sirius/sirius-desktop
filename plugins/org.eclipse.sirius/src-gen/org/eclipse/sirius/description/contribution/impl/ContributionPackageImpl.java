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
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

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
        super(eNS_URI, ContributionFactory.eINSTANCE);
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
        if (isInited)
            return (ContributionPackage) EPackage.Registry.INSTANCE.getEPackage(ContributionPackage.eNS_URI);

        // Obtain or create and register package
        ContributionPackageImpl theContributionPackage = (ContributionPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ContributionPackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new ContributionPackageImpl());

        isInited = true;

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
    public EClass getFeatureContribution() {
        return featureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFeatureContribution_SourceFeature() {
        return (EReference) featureContributionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFeatureContribution_TargetFeature() {
        return (EReference) featureContributionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getIgnoreFeatureContribution() {
        return ignoreFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSetFeatureContribution() {
        return setFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAddFeatureContribution() {
        return addFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRemoveFeatureContribution() {
        return removeFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getClearFeatureContribution() {
        return clearFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getResetFeatureContribution() {
        return resetFeatureContributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEObjectReference() {
        return eObjectReferenceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDirectEObjectReference() {
        return directEObjectReferenceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDirectEObjectReference_Value() {
        return (EReference) directEObjectReferenceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getComputedEObjectReference() {
        return computedEObjectReferenceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getComputedEObjectReference_ValueExpression() {
        return (EAttribute) computedEObjectReferenceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContribution() {
        return contributionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContribution_Source() {
        return (EReference) contributionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContribution_Target() {
        return (EReference) contributionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContribution_FeatureMask() {
        return (EReference) contributionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContribution_SubContributions() {
        return (EReference) contributionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getContribution_Description() {
        return (EAttribute) contributionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContributionProvider() {
        return contributionProviderEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContributionProvider_Contributions() {
        return (EReference) contributionProviderEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContributionPoint() {
        return contributionPointEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getContributionPoint_Origin() {
        return (EAttribute) contributionPointEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContributionPoint_Contributed() {
        return (EReference) contributionPointEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        featureContributionEClass = createEClass(FEATURE_CONTRIBUTION);
        createEReference(featureContributionEClass, FEATURE_CONTRIBUTION__SOURCE_FEATURE);
        createEReference(featureContributionEClass, FEATURE_CONTRIBUTION__TARGET_FEATURE);

        ignoreFeatureContributionEClass = createEClass(IGNORE_FEATURE_CONTRIBUTION);

        setFeatureContributionEClass = createEClass(SET_FEATURE_CONTRIBUTION);

        addFeatureContributionEClass = createEClass(ADD_FEATURE_CONTRIBUTION);

        removeFeatureContributionEClass = createEClass(REMOVE_FEATURE_CONTRIBUTION);

        clearFeatureContributionEClass = createEClass(CLEAR_FEATURE_CONTRIBUTION);

        resetFeatureContributionEClass = createEClass(RESET_FEATURE_CONTRIBUTION);

        eObjectReferenceEClass = createEClass(EOBJECT_REFERENCE);

        directEObjectReferenceEClass = createEClass(DIRECT_EOBJECT_REFERENCE);
        createEReference(directEObjectReferenceEClass, DIRECT_EOBJECT_REFERENCE__VALUE);

        computedEObjectReferenceEClass = createEClass(COMPUTED_EOBJECT_REFERENCE);
        createEAttribute(computedEObjectReferenceEClass, COMPUTED_EOBJECT_REFERENCE__VALUE_EXPRESSION);

        contributionEClass = createEClass(CONTRIBUTION);
        createEReference(contributionEClass, CONTRIBUTION__SOURCE);
        createEReference(contributionEClass, CONTRIBUTION__TARGET);
        createEReference(contributionEClass, CONTRIBUTION__FEATURE_MASK);
        createEReference(contributionEClass, CONTRIBUTION__SUB_CONTRIBUTIONS);
        createEAttribute(contributionEClass, CONTRIBUTION__DESCRIPTION);

        contributionProviderEClass = createEClass(CONTRIBUTION_PROVIDER);
        createEReference(contributionProviderEClass, CONTRIBUTION_PROVIDER__CONTRIBUTIONS);

        contributionPointEClass = createEClass(CONTRIBUTION_POINT);
        createEAttribute(contributionPointEClass, CONTRIBUTION_POINT__ORIGIN);
        createEReference(contributionPointEClass, CONTRIBUTION_POINT__CONTRIBUTED);
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
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

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
        initEClass(featureContributionEClass, FeatureContribution.class, "FeatureContribution", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFeatureContribution_SourceFeature(), theEcorePackage.getEStructuralFeature(), null, "sourceFeature", null, 1, 1, FeatureContribution.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFeatureContribution_TargetFeature(), theEcorePackage.getEStructuralFeature(), null, "targetFeature", null, 1, 1, FeatureContribution.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ignoreFeatureContributionEClass, IgnoreFeatureContribution.class, "IgnoreFeatureContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(setFeatureContributionEClass, SetFeatureContribution.class, "SetFeatureContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(addFeatureContributionEClass, AddFeatureContribution.class, "AddFeatureContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(removeFeatureContributionEClass, RemoveFeatureContribution.class, "RemoveFeatureContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(clearFeatureContributionEClass, ClearFeatureContribution.class, "ClearFeatureContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(resetFeatureContributionEClass, ResetFeatureContribution.class, "ResetFeatureContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(eObjectReferenceEClass, EObjectReference.class, "EObjectReference", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(directEObjectReferenceEClass, DirectEObjectReference.class, "DirectEObjectReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDirectEObjectReference_Value(), theEcorePackage.getEObject(), null, "value", null, 1, 1, DirectEObjectReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(computedEObjectReferenceEClass, ComputedEObjectReference.class, "ComputedEObjectReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getComputedEObjectReference_ValueExpression(), ecorePackage.getEString(), "valueExpression", null, 1, 1, ComputedEObjectReference.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(contributionEClass, Contribution.class, "Contribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContribution_Source(), this.getEObjectReference(), null, "source", null, 1, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContribution_Target(), this.getEObjectReference(), null, "target", null, 1, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContribution_FeatureMask(), this.getFeatureContribution(), null, "featureMask", null, 1, -1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getContribution_SubContributions(), this.getContribution(), null, "subContributions", null, 0, -1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContribution_Description(), ecorePackage.getEString(), "description", null, 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(contributionProviderEClass, ContributionProvider.class, "ContributionProvider", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContributionProvider_Contributions(), this.getContribution(), null, "contributions", null, 0, -1, ContributionProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(contributionPointEClass, ContributionPoint.class, "ContributionPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContributionPoint_Origin(), ecorePackage.getEString(), "origin", null, 1, 1, ContributionPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContributionPoint_Contributed(), theEcorePackage.getEObject(), null, "contributed", null, 1, 1, ContributionPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // ContributionPackageImpl
