/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.concern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.concern.ConcernFactory
 * @model kind="package"
 * @generated
 */
public interface ConcernPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "concern"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/description/concern/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "concern"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    ConcernPackage eINSTANCE = org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.concern.impl.ConcernSetImpl
     * <em>Set</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.concern.impl.ConcernSetImpl
     * @see org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl#getConcernSet()
     * @generated
     */
    int CONCERN_SET = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONCERN_SET__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Owned Concern Descriptions</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONCERN_SET__OWNED_CONCERN_DESCRIPTIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Set</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONCERN_SET_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.concern.impl.ConcernDescriptionImpl
     * <em>Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.concern.impl.ConcernDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl#getConcernDescription()
     * @generated
     */
    int CONCERN_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONCERN_DESCRIPTION__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONCERN_DESCRIPTION__NAME = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONCERN_DESCRIPTION__LABEL = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filters</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONCERN_DESCRIPTION__FILTERS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Rules</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONCERN_DESCRIPTION__RULES = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Behaviors</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONCERN_DESCRIPTION__BEHAVIORS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Description</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONCERN_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.concern.ConcernSet
     * <em>Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Set</em>'.
     * @see org.eclipse.sirius.diagram.description.concern.ConcernSet
     * @generated
     */
    EClass getConcernSet();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.concern.ConcernSet#getOwnedConcernDescriptions <em>Owned Concern
     * Descriptions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Concern Descriptions</em>'.
     * @see org.eclipse.sirius.diagram.description.concern.ConcernSet#getOwnedConcernDescriptions()
     * @see #getConcernSet()
     * @generated
     */
    EReference getConcernSet_OwnedConcernDescriptions();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.concern.ConcernDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Description</em>'.
     * @see org.eclipse.sirius.diagram.description.concern.ConcernDescription
     * @generated
     */
    EClass getConcernDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.concern.ConcernDescription#getFilters <em>Filters</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Filters</em>'.
     * @see org.eclipse.sirius.diagram.description.concern.ConcernDescription#getFilters()
     * @see #getConcernDescription()
     * @generated
     */
    EReference getConcernDescription_Filters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.concern.ConcernDescription#getRules <em>Rules</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Rules</em>'.
     * @see org.eclipse.sirius.diagram.description.concern.ConcernDescription#getRules()
     * @see #getConcernDescription()
     * @generated
     */
    EReference getConcernDescription_Rules();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.concern.ConcernDescription#getBehaviors <em>Behaviors</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Behaviors</em>'.
     * @see org.eclipse.sirius.diagram.description.concern.ConcernDescription#getBehaviors()
     * @see #getConcernDescription()
     * @generated
     */
    EReference getConcernDescription_Behaviors();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ConcernFactory getConcernFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
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
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.concern.impl.ConcernSetImpl
         * <em>Set</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.concern.impl.ConcernSetImpl
         * @see org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl#getConcernSet()
         * @generated
         */
        EClass CONCERN_SET = ConcernPackage.eINSTANCE.getConcernSet();

        /**
         * The meta object literal for the '<em><b>Owned Concern Descriptions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONCERN_SET__OWNED_CONCERN_DESCRIPTIONS = ConcernPackage.eINSTANCE.getConcernSet_OwnedConcernDescriptions();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.concern.impl.ConcernDescriptionImpl <em>Description</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.concern.impl.ConcernDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl#getConcernDescription()
         * @generated
         */
        EClass CONCERN_DESCRIPTION = ConcernPackage.eINSTANCE.getConcernDescription();

        /**
         * The meta object literal for the '<em><b>Filters</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONCERN_DESCRIPTION__FILTERS = ConcernPackage.eINSTANCE.getConcernDescription_Filters();

        /**
         * The meta object literal for the '<em><b>Rules</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CONCERN_DESCRIPTION__RULES = ConcernPackage.eINSTANCE.getConcernDescription_Rules();

        /**
         * The meta object literal for the '<em><b>Behaviors</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONCERN_DESCRIPTION__BEHAVIORS = ConcernPackage.eINSTANCE.getConcernDescription_Behaviors();

    }

} // ConcernPackage
