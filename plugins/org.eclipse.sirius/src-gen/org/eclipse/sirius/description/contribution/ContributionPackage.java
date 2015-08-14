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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.description.contribution.ContributionFactory
 * @model kind="package"
 * @generated
 */
public interface ContributionPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "contribution"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/description/contribution/1.0.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "contribution"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    ContributionPackage eINSTANCE = org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.FeatureContributionImpl
     * <em>Feature Contribution</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.FeatureContributionImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getFeatureContribution()
     * @generated
     */
    int FEATURE_CONTRIBUTION = 0;

    /**
     * The feature id for the '<em><b>Source Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_CONTRIBUTION__SOURCE_FEATURE = 0;

    /**
     * The feature id for the '<em><b>Target Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_CONTRIBUTION__TARGET_FEATURE = 1;

    /**
     * The number of structural features of the '<em>Feature Contribution</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_CONTRIBUTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.IgnoreFeatureContributionImpl
     * <em>Ignore Feature Contribution</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.IgnoreFeatureContributionImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getIgnoreFeatureContribution()
     * @generated
     */
    int IGNORE_FEATURE_CONTRIBUTION = 1;

    /**
     * The feature id for the '<em><b>Source Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int IGNORE_FEATURE_CONTRIBUTION__SOURCE_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE;

    /**
     * The feature id for the '<em><b>Target Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int IGNORE_FEATURE_CONTRIBUTION__TARGET_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE;

    /**
     * The number of structural features of the '
     * <em>Ignore Feature Contribution</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int IGNORE_FEATURE_CONTRIBUTION_FEATURE_COUNT = ContributionPackage.FEATURE_CONTRIBUTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.SetFeatureContributionImpl
     * <em>Set Feature Contribution</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.SetFeatureContributionImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getSetFeatureContribution()
     * @generated
     */
    int SET_FEATURE_CONTRIBUTION = 2;

    /**
     * The feature id for the '<em><b>Source Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_FEATURE_CONTRIBUTION__SOURCE_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE;

    /**
     * The feature id for the '<em><b>Target Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_FEATURE_CONTRIBUTION__TARGET_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE;

    /**
     * The number of structural features of the '
     * <em>Set Feature Contribution</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_FEATURE_CONTRIBUTION_FEATURE_COUNT = ContributionPackage.FEATURE_CONTRIBUTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.AddFeatureContributionImpl
     * <em>Add Feature Contribution</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.AddFeatureContributionImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getAddFeatureContribution()
     * @generated
     */
    int ADD_FEATURE_CONTRIBUTION = 3;

    /**
     * The feature id for the '<em><b>Source Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADD_FEATURE_CONTRIBUTION__SOURCE_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE;

    /**
     * The feature id for the '<em><b>Target Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADD_FEATURE_CONTRIBUTION__TARGET_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE;

    /**
     * The number of structural features of the '
     * <em>Add Feature Contribution</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADD_FEATURE_CONTRIBUTION_FEATURE_COUNT = ContributionPackage.FEATURE_CONTRIBUTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.RemoveFeatureContributionImpl
     * <em>Remove Feature Contribution</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.RemoveFeatureContributionImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getRemoveFeatureContribution()
     * @generated
     */
    int REMOVE_FEATURE_CONTRIBUTION = 4;

    /**
     * The feature id for the '<em><b>Source Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REMOVE_FEATURE_CONTRIBUTION__SOURCE_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE;

    /**
     * The feature id for the '<em><b>Target Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REMOVE_FEATURE_CONTRIBUTION__TARGET_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE;

    /**
     * The number of structural features of the '
     * <em>Remove Feature Contribution</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REMOVE_FEATURE_CONTRIBUTION_FEATURE_COUNT = ContributionPackage.FEATURE_CONTRIBUTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.ClearFeatureContributionImpl
     * <em>Clear Feature Contribution</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.ClearFeatureContributionImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getClearFeatureContribution()
     * @generated
     */
    int CLEAR_FEATURE_CONTRIBUTION = 5;

    /**
     * The feature id for the '<em><b>Source Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CLEAR_FEATURE_CONTRIBUTION__SOURCE_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE;

    /**
     * The feature id for the '<em><b>Target Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CLEAR_FEATURE_CONTRIBUTION__TARGET_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE;

    /**
     * The number of structural features of the '
     * <em>Clear Feature Contribution</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CLEAR_FEATURE_CONTRIBUTION_FEATURE_COUNT = ContributionPackage.FEATURE_CONTRIBUTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.ResetFeatureContributionImpl
     * <em>Reset Feature Contribution</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.ResetFeatureContributionImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getResetFeatureContribution()
     * @generated
     */
    int RESET_FEATURE_CONTRIBUTION = 6;

    /**
     * The feature id for the '<em><b>Source Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RESET_FEATURE_CONTRIBUTION__SOURCE_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__SOURCE_FEATURE;

    /**
     * The feature id for the '<em><b>Target Feature</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RESET_FEATURE_CONTRIBUTION__TARGET_FEATURE = ContributionPackage.FEATURE_CONTRIBUTION__TARGET_FEATURE;

    /**
     * The number of structural features of the '
     * <em>Reset Feature Contribution</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RESET_FEATURE_CONTRIBUTION_FEATURE_COUNT = ContributionPackage.FEATURE_CONTRIBUTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.EObjectReference
     * <em>EObject Reference</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.EObjectReference
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getEObjectReference()
     * @generated
     */
    int EOBJECT_REFERENCE = 7;

    /**
     * The number of structural features of the '<em>EObject Reference</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EOBJECT_REFERENCE_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.DirectEObjectReferenceImpl
     * <em>Direct EObject Reference</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.DirectEObjectReferenceImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getDirectEObjectReference()
     * @generated
     */
    int DIRECT_EOBJECT_REFERENCE = 8;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EOBJECT_REFERENCE__VALUE = ContributionPackage.EOBJECT_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Direct EObject Reference</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EOBJECT_REFERENCE_FEATURE_COUNT = ContributionPackage.EOBJECT_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.ComputedEObjectReferenceImpl
     * <em>Computed EObject Reference</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.ComputedEObjectReferenceImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getComputedEObjectReference()
     * @generated
     */
    int COMPUTED_EOBJECT_REFERENCE = 9;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPUTED_EOBJECT_REFERENCE__VALUE_EXPRESSION = ContributionPackage.EOBJECT_REFERENCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Computed EObject Reference</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPUTED_EOBJECT_REFERENCE_FEATURE_COUNT = ContributionPackage.EOBJECT_REFERENCE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.ContributionImpl
     * <em>Contribution</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.ContributionImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getContribution()
     * @generated
     */
    int CONTRIBUTION = 10;

    /**
     * The feature id for the '<em><b>Source</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION__SOURCE = 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION__TARGET = 1;

    /**
     * The feature id for the '<em><b>Feature Mask</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION__FEATURE_MASK = 2;

    /**
     * The feature id for the '<em><b>Sub Contributions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION__SUB_CONTRIBUTIONS = 3;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION__DESCRIPTION = 4;

    /**
     * The number of structural features of the '<em>Contribution</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.ContributionProviderImpl
     * <em>Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.ContributionProviderImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getContributionProvider()
     * @generated
     */
    int CONTRIBUTION_PROVIDER = 11;

    /**
     * The feature id for the '<em><b>Contributions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION_PROVIDER__CONTRIBUTIONS = 0;

    /**
     * The number of structural features of the '<em>Provider</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION_PROVIDER_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.contribution.impl.ContributionPointImpl
     * <em>Point</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPointImpl
     * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getContributionPoint()
     * @generated
     */
    int CONTRIBUTION_POINT = 12;

    /**
     * The feature id for the '<em><b>Origin</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION_POINT__ORIGIN = 0;

    /**
     * The feature id for the '<em><b>Contributed</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION_POINT__CONTRIBUTED = 1;

    /**
     * The number of structural features of the '<em>Point</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTRIBUTION_POINT_FEATURE_COUNT = 2;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.FeatureContribution
     * <em>Feature Contribution</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Feature Contribution</em>'.
     * @see org.eclipse.sirius.description.contribution.FeatureContribution
     * @generated
     */
    EClass getFeatureContribution();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.contribution.FeatureContribution#getSourceFeature
     * <em>Source Feature</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Source Feature</em>'.
     * @see org.eclipse.sirius.description.contribution.FeatureContribution#getSourceFeature()
     * @see #getFeatureContribution()
     * @generated
     */
    EReference getFeatureContribution_SourceFeature();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.contribution.FeatureContribution#getTargetFeature
     * <em>Target Feature</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Target Feature</em>'.
     * @see org.eclipse.sirius.description.contribution.FeatureContribution#getTargetFeature()
     * @see #getFeatureContribution()
     * @generated
     */
    EReference getFeatureContribution_TargetFeature();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.IgnoreFeatureContribution
     * <em>Ignore Feature Contribution</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Ignore Feature Contribution</em>'.
     * @see org.eclipse.sirius.description.contribution.IgnoreFeatureContribution
     * @generated
     */
    EClass getIgnoreFeatureContribution();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.SetFeatureContribution
     * <em>Set Feature Contribution</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Set Feature Contribution</em>'.
     * @see org.eclipse.sirius.description.contribution.SetFeatureContribution
     * @generated
     */
    EClass getSetFeatureContribution();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.AddFeatureContribution
     * <em>Add Feature Contribution</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Add Feature Contribution</em>'.
     * @see org.eclipse.sirius.description.contribution.AddFeatureContribution
     * @generated
     */
    EClass getAddFeatureContribution();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.RemoveFeatureContribution
     * <em>Remove Feature Contribution</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Remove Feature Contribution</em>'.
     * @see org.eclipse.sirius.description.contribution.RemoveFeatureContribution
     * @generated
     */
    EClass getRemoveFeatureContribution();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.ClearFeatureContribution
     * <em>Clear Feature Contribution</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Clear Feature Contribution</em>'.
     * @see org.eclipse.sirius.description.contribution.ClearFeatureContribution
     * @generated
     */
    EClass getClearFeatureContribution();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.ResetFeatureContribution
     * <em>Reset Feature Contribution</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Reset Feature Contribution</em>'.
     * @see org.eclipse.sirius.description.contribution.ResetFeatureContribution
     * @generated
     */
    EClass getResetFeatureContribution();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.EObjectReference
     * <em>EObject Reference</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>EObject Reference</em>'.
     * @see org.eclipse.sirius.description.contribution.EObjectReference
     * @generated
     */
    EClass getEObjectReference();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.DirectEObjectReference
     * <em>Direct EObject Reference</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Direct EObject Reference</em>'.
     * @see org.eclipse.sirius.description.contribution.DirectEObjectReference
     * @generated
     */
    EClass getDirectEObjectReference();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.contribution.DirectEObjectReference#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Value</em>'.
     * @see org.eclipse.sirius.description.contribution.DirectEObjectReference#getValue()
     * @see #getDirectEObjectReference()
     * @generated
     */
    EReference getDirectEObjectReference_Value();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.ComputedEObjectReference
     * <em>Computed EObject Reference</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Computed EObject Reference</em>'.
     * @see org.eclipse.sirius.description.contribution.ComputedEObjectReference
     * @generated
     */
    EClass getComputedEObjectReference();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.contribution.ComputedEObjectReference#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.description.contribution.ComputedEObjectReference#getValueExpression()
     * @see #getComputedEObjectReference()
     * @generated
     */
    EAttribute getComputedEObjectReference_ValueExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.Contribution
     * <em>Contribution</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Contribution</em>'.
     * @see org.eclipse.sirius.description.contribution.Contribution
     * @generated
     */
    EClass getContribution();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.contribution.Contribution#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Source</em>'.
     * @see org.eclipse.sirius.description.contribution.Contribution#getSource()
     * @see #getContribution()
     * @generated
     */
    EReference getContribution_Source();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.contribution.Contribution#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Target</em>'.
     * @see org.eclipse.sirius.description.contribution.Contribution#getTarget()
     * @see #getContribution()
     * @generated
     */
    EReference getContribution_Target();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.contribution.Contribution#getFeatureMask
     * <em>Feature Mask</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Feature Mask</em>'.
     * @see org.eclipse.sirius.description.contribution.Contribution#getFeatureMask()
     * @see #getContribution()
     * @generated
     */
    EReference getContribution_FeatureMask();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.contribution.Contribution#getSubContributions
     * <em>Sub Contributions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Sub Contributions</em>'.
     * @see org.eclipse.sirius.description.contribution.Contribution#getSubContributions()
     * @see #getContribution()
     * @generated
     */
    EReference getContribution_SubContributions();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.contribution.Contribution#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.eclipse.sirius.description.contribution.Contribution#getDescription()
     * @see #getContribution()
     * @generated
     */
    EAttribute getContribution_Description();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.ContributionProvider
     * <em>Provider</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Provider</em>'.
     * @see org.eclipse.sirius.description.contribution.ContributionProvider
     * @generated
     */
    EClass getContributionProvider();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.contribution.ContributionProvider#getContributions
     * <em>Contributions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Contributions</em>'.
     * @see org.eclipse.sirius.description.contribution.ContributionProvider#getContributions()
     * @see #getContributionProvider()
     * @generated
     */
    EReference getContributionProvider_Contributions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.contribution.ContributionPoint
     * <em>Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Point</em>'.
     * @see org.eclipse.sirius.description.contribution.ContributionPoint
     * @generated
     */
    EClass getContributionPoint();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.contribution.ContributionPoint#getOrigin
     * <em>Origin</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Origin</em>'.
     * @see org.eclipse.sirius.description.contribution.ContributionPoint#getOrigin()
     * @see #getContributionPoint()
     * @generated
     */
    EAttribute getContributionPoint_Origin();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.contribution.ContributionPoint#getContributed
     * <em>Contributed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Contributed</em>'.
     * @see org.eclipse.sirius.description.contribution.ContributionPoint#getContributed()
     * @see #getContributionPoint()
     * @generated
     */
    EReference getContributionPoint_Contributed();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ContributionFactory getContributionFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.FeatureContributionImpl
         * <em>Feature Contribution</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.FeatureContributionImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getFeatureContribution()
         * @generated
         */
        EClass FEATURE_CONTRIBUTION = ContributionPackage.eINSTANCE.getFeatureContribution();

        /**
         * The meta object literal for the '<em><b>Source Feature</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference FEATURE_CONTRIBUTION__SOURCE_FEATURE = ContributionPackage.eINSTANCE.getFeatureContribution_SourceFeature();

        /**
         * The meta object literal for the '<em><b>Target Feature</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference FEATURE_CONTRIBUTION__TARGET_FEATURE = ContributionPackage.eINSTANCE.getFeatureContribution_TargetFeature();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.IgnoreFeatureContributionImpl
         * <em>Ignore Feature Contribution</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.IgnoreFeatureContributionImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getIgnoreFeatureContribution()
         * @generated
         */
        EClass IGNORE_FEATURE_CONTRIBUTION = ContributionPackage.eINSTANCE.getIgnoreFeatureContribution();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.SetFeatureContributionImpl
         * <em>Set Feature Contribution</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.SetFeatureContributionImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getSetFeatureContribution()
         * @generated
         */
        EClass SET_FEATURE_CONTRIBUTION = ContributionPackage.eINSTANCE.getSetFeatureContribution();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.AddFeatureContributionImpl
         * <em>Add Feature Contribution</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.AddFeatureContributionImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getAddFeatureContribution()
         * @generated
         */
        EClass ADD_FEATURE_CONTRIBUTION = ContributionPackage.eINSTANCE.getAddFeatureContribution();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.RemoveFeatureContributionImpl
         * <em>Remove Feature Contribution</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.RemoveFeatureContributionImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getRemoveFeatureContribution()
         * @generated
         */
        EClass REMOVE_FEATURE_CONTRIBUTION = ContributionPackage.eINSTANCE.getRemoveFeatureContribution();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.ClearFeatureContributionImpl
         * <em>Clear Feature Contribution</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.ClearFeatureContributionImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getClearFeatureContribution()
         * @generated
         */
        EClass CLEAR_FEATURE_CONTRIBUTION = ContributionPackage.eINSTANCE.getClearFeatureContribution();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.ResetFeatureContributionImpl
         * <em>Reset Feature Contribution</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.ResetFeatureContributionImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getResetFeatureContribution()
         * @generated
         */
        EClass RESET_FEATURE_CONTRIBUTION = ContributionPackage.eINSTANCE.getResetFeatureContribution();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.EObjectReference
         * <em>EObject Reference</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.EObjectReference
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getEObjectReference()
         * @generated
         */
        EClass EOBJECT_REFERENCE = ContributionPackage.eINSTANCE.getEObjectReference();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.DirectEObjectReferenceImpl
         * <em>Direct EObject Reference</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.DirectEObjectReferenceImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getDirectEObjectReference()
         * @generated
         */
        EClass DIRECT_EOBJECT_REFERENCE = ContributionPackage.eINSTANCE.getDirectEObjectReference();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIRECT_EOBJECT_REFERENCE__VALUE = ContributionPackage.eINSTANCE.getDirectEObjectReference_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.ComputedEObjectReferenceImpl
         * <em>Computed EObject Reference</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.ComputedEObjectReferenceImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getComputedEObjectReference()
         * @generated
         */
        EClass COMPUTED_EOBJECT_REFERENCE = ContributionPackage.eINSTANCE.getComputedEObjectReference();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute COMPUTED_EOBJECT_REFERENCE__VALUE_EXPRESSION = ContributionPackage.eINSTANCE.getComputedEObjectReference_ValueExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.ContributionImpl
         * <em>Contribution</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.ContributionImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getContribution()
         * @generated
         */
        EClass CONTRIBUTION = ContributionPackage.eINSTANCE.getContribution();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTRIBUTION__SOURCE = ContributionPackage.eINSTANCE.getContribution_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTRIBUTION__TARGET = ContributionPackage.eINSTANCE.getContribution_Target();

        /**
         * The meta object literal for the '<em><b>Feature Mask</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CONTRIBUTION__FEATURE_MASK = ContributionPackage.eINSTANCE.getContribution_FeatureMask();

        /**
         * The meta object literal for the '<em><b>Sub Contributions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CONTRIBUTION__SUB_CONTRIBUTIONS = ContributionPackage.eINSTANCE.getContribution_SubContributions();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CONTRIBUTION__DESCRIPTION = ContributionPackage.eINSTANCE.getContribution_Description();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.ContributionProviderImpl
         * <em>Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.ContributionProviderImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getContributionProvider()
         * @generated
         */
        EClass CONTRIBUTION_PROVIDER = ContributionPackage.eINSTANCE.getContributionProvider();

        /**
         * The meta object literal for the '<em><b>Contributions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CONTRIBUTION_PROVIDER__CONTRIBUTIONS = ContributionPackage.eINSTANCE.getContributionProvider_Contributions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.contribution.impl.ContributionPointImpl
         * <em>Point</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPointImpl
         * @see org.eclipse.sirius.description.contribution.impl.ContributionPackageImpl#getContributionPoint()
         * @generated
         */
        EClass CONTRIBUTION_POINT = ContributionPackage.eINSTANCE.getContributionPoint();

        /**
         * The meta object literal for the '<em><b>Origin</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CONTRIBUTION_POINT__ORIGIN = ContributionPackage.eINSTANCE.getContributionPoint_Origin();

        /**
         * The meta object literal for the '<em><b>Contributed</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTRIBUTION_POINT__CONTRIBUTED = ContributionPackage.eINSTANCE.getContributionPoint_Contributed();

    }

} // ContributionPackage
