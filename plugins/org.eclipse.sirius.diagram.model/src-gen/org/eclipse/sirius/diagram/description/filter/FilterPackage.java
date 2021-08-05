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
package org.eclipse.sirius.diagram.description.filter;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.sirius.diagram.description.filter.FilterFactory
 * @model kind="package"
 * @generated
 */
public interface FilterPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "filter"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/description/filter/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "filter"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    FilterPackage eINSTANCE = org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.filter.impl.FilterDescriptionImpl
     * <em>Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.filter.impl.FilterDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getFilterDescription()
     * @generated
     */
    int FILTER_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER_DESCRIPTION__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER_DESCRIPTION__NAME = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER_DESCRIPTION__LABEL = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Description</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int FILTER_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.filter.impl.FilterImpl
     * <em>Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.filter.impl.FilterImpl
     * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getFilter()
     * @generated
     */
    int FILTER = 1;

    /**
     * The feature id for the '<em><b>Filter Kind</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER__FILTER_KIND = 0;

    /**
     * The number of structural features of the '<em>Filter</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.filter.impl.MappingFilterImpl
     * <em>Mapping Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.filter.impl.MappingFilterImpl
     * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getMappingFilter()
     * @generated
     */
    int MAPPING_FILTER = 2;

    /**
     * The feature id for the '<em><b>Filter Kind</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_FILTER__FILTER_KIND = FilterPackage.FILTER__FILTER_KIND;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_FILTER__MAPPINGS = FilterPackage.FILTER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic Condition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION = FilterPackage.FILTER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>View Condition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_FILTER__VIEW_CONDITION_EXPRESSION = FilterPackage.FILTER_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Mapping Filter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_FILTER_FEATURE_COUNT = FilterPackage.FILTER_FEATURE_COUNT + 3;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.filter.impl.CompositeFilterDescriptionImpl <em>Composite Filter
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.filter.impl.CompositeFilterDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getCompositeFilterDescription()
     * @generated
     */
    int COMPOSITE_FILTER_DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPOSITE_FILTER_DESCRIPTION__DOCUMENTATION = FilterPackage.FILTER_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPOSITE_FILTER_DESCRIPTION__NAME = FilterPackage.FILTER_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPOSITE_FILTER_DESCRIPTION__LABEL = FilterPackage.FILTER_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPOSITE_FILTER_DESCRIPTION__FILTERS = FilterPackage.FILTER_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Composite Filter Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPOSITE_FILTER_DESCRIPTION_FEATURE_COUNT = FilterPackage.FILTER_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.filter.impl.VariableFilterImpl
     * <em>Variable Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.filter.impl.VariableFilterImpl
     * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getVariableFilter()
     * @generated
     */
    int VARIABLE_FILTER = 4;

    /**
     * The feature id for the '<em><b>Filter Kind</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VARIABLE_FILTER__FILTER_KIND = FilterPackage.FILTER__FILTER_KIND;

    /**
     * The feature id for the '<em><b>Owned Variables</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VARIABLE_FILTER__OWNED_VARIABLES = FilterPackage.FILTER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic Condition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION = FilterPackage.FILTER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Variable Filter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VARIABLE_FILTER_FEATURE_COUNT = FilterPackage.FILTER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.filter.FilterKind <em>Kind</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.filter.FilterKind
     * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getFilterKind()
     * @generated
     */
    int FILTER_KIND = 5;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.filter.FilterDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Description</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.FilterDescription
     * @generated
     */
    EClass getFilterDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.filter.Filter <em>Filter</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Filter</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.Filter
     * @generated
     */
    EClass getFilter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.filter.Filter#getFilterKind <em>Filter Kind</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Kind</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.Filter#getFilterKind()
     * @see #getFilter()
     * @generated
     */
    EAttribute getFilter_FilterKind();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.filter.MappingFilter <em>Mapping
     * Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Mapping Filter</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.MappingFilter
     * @generated
     */
    EClass getMappingFilter();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.filter.MappingFilter#getMappings <em>Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.MappingFilter#getMappings()
     * @see #getMappingFilter()
     * @generated
     */
    EReference getMappingFilter_Mappings();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.filter.MappingFilter#getSemanticConditionExpression <em>Semantic
     * Condition Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Semantic Condition Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.MappingFilter#getSemanticConditionExpression()
     * @see #getMappingFilter()
     * @generated
     */
    EAttribute getMappingFilter_SemanticConditionExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.filter.MappingFilter#getViewConditionExpression <em>View Condition
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>View Condition Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.MappingFilter#getViewConditionExpression()
     * @see #getMappingFilter()
     * @generated
     */
    EAttribute getMappingFilter_ViewConditionExpression();

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription <em>Composite Filter
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Composite Filter Description</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription
     * @generated
     */
    EClass getCompositeFilterDescription();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription#getFilters <em>Filters</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Filters</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription#getFilters()
     * @see #getCompositeFilterDescription()
     * @generated
     */
    EReference getCompositeFilterDescription_Filters();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.filter.VariableFilter
     * <em>Variable Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Variable Filter</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.VariableFilter
     * @generated
     */
    EClass getVariableFilter();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.filter.VariableFilter#getOwnedVariables <em>Owned
     * Variables</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Variables</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.VariableFilter#getOwnedVariables()
     * @see #getVariableFilter()
     * @generated
     */
    EReference getVariableFilter_OwnedVariables();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.filter.VariableFilter#getSemanticConditionExpression <em>Semantic
     * Condition Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Semantic Condition Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.VariableFilter#getSemanticConditionExpression()
     * @see #getVariableFilter()
     * @generated
     */
    EAttribute getVariableFilter_SemanticConditionExpression();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.description.filter.FilterKind
     * <em>Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Kind</em>'.
     * @see org.eclipse.sirius.diagram.description.filter.FilterKind
     * @generated
     */
    EEnum getFilterKind();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    FilterFactory getFilterFactory();

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
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.filter.impl.FilterDescriptionImpl <em>Description</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.filter.impl.FilterDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getFilterDescription()
         * @generated
         */
        EClass FILTER_DESCRIPTION = FilterPackage.eINSTANCE.getFilterDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.filter.impl.FilterImpl
         * <em>Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.filter.impl.FilterImpl
         * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getFilter()
         * @generated
         */
        EClass FILTER = FilterPackage.eINSTANCE.getFilter();

        /**
         * The meta object literal for the '<em><b>Filter Kind</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute FILTER__FILTER_KIND = FilterPackage.eINSTANCE.getFilter_FilterKind();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.filter.impl.MappingFilterImpl
         * <em>Mapping Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.filter.impl.MappingFilterImpl
         * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getMappingFilter()
         * @generated
         */
        EClass MAPPING_FILTER = FilterPackage.eINSTANCE.getMappingFilter();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MAPPING_FILTER__MAPPINGS = FilterPackage.eINSTANCE.getMappingFilter_Mappings();

        /**
         * The meta object literal for the '<em><b>Semantic Condition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION = FilterPackage.eINSTANCE.getMappingFilter_SemanticConditionExpression();

        /**
         * The meta object literal for the ' <em><b>View Condition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute MAPPING_FILTER__VIEW_CONDITION_EXPRESSION = FilterPackage.eINSTANCE.getMappingFilter_ViewConditionExpression();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.filter.impl.CompositeFilterDescriptionImpl <em>Composite
         * Filter Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.filter.impl.CompositeFilterDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getCompositeFilterDescription()
         * @generated
         */
        EClass COMPOSITE_FILTER_DESCRIPTION = FilterPackage.eINSTANCE.getCompositeFilterDescription();

        /**
         * The meta object literal for the '<em><b>Filters</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMPOSITE_FILTER_DESCRIPTION__FILTERS = FilterPackage.eINSTANCE.getCompositeFilterDescription_Filters();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.filter.impl.VariableFilterImpl
         * <em>Variable Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.filter.impl.VariableFilterImpl
         * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getVariableFilter()
         * @generated
         */
        EClass VARIABLE_FILTER = FilterPackage.eINSTANCE.getVariableFilter();

        /**
         * The meta object literal for the '<em><b>Owned Variables</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference VARIABLE_FILTER__OWNED_VARIABLES = FilterPackage.eINSTANCE.getVariableFilter_OwnedVariables();

        /**
         * The meta object literal for the '<em><b>Semantic Condition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION = FilterPackage.eINSTANCE.getVariableFilter_SemanticConditionExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.filter.FilterKind
         * <em>Kind</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.filter.FilterKind
         * @see org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl#getFilterKind()
         * @generated
         */
        EEnum FILTER_KIND = FilterPackage.eINSTANCE.getFilterKind();

    }

} // FilterPackage
