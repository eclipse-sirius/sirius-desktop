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
package org.eclipse.sirius.viewpoint.description.filter;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

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
 * @see org.eclipse.sirius.viewpoint.description.filter.FilterFactory
 * @model kind="package"
 * @generated
 */
public interface FilterPackage extends EPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "filter";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/description/filter/1.1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "filter";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    FilterPackage eINSTANCE = org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.filter.impl.FilterDescriptionImpl
     * <em>Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getFilterDescription()
     * @generated
     */
    int FILTER_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_DESCRIPTION__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_DESCRIPTION__NAME = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_DESCRIPTION__LABEL = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.filter.impl.FilterImpl
     * <em>Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterImpl
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getFilter()
     * @generated
     */
    int FILTER = 1;

    /**
     * The feature id for the '<em><b>Filter Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER__FILTER_KIND = 0;

    /**
     * The number of structural features of the '<em>Filter</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.filter.impl.MappingFilterImpl
     * <em>Mapping Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.MappingFilterImpl
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getMappingFilter()
     * @generated
     */
    int MAPPING_FILTER = 2;

    /**
     * The feature id for the '<em><b>Filter Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_FILTER__FILTER_KIND = FILTER__FILTER_KIND;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_FILTER__MAPPINGS = FILTER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic Condition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION = FILTER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>View Condition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_FILTER__VIEW_CONDITION_EXPRESSION = FILTER_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Mapping Filter</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_FILTER_FEATURE_COUNT = FILTER_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.filter.impl.CompositeFilterDescriptionImpl
     * <em>Composite Filter Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.CompositeFilterDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getCompositeFilterDescription()
     * @generated
     */
    int COMPOSITE_FILTER_DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOSITE_FILTER_DESCRIPTION__DOCUMENTATION = FILTER_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOSITE_FILTER_DESCRIPTION__NAME = FILTER_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOSITE_FILTER_DESCRIPTION__LABEL = FILTER_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOSITE_FILTER_DESCRIPTION__FILTERS = FILTER_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Composite Filter Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPOSITE_FILTER_DESCRIPTION_FEATURE_COUNT = FILTER_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.filter.impl.VariableFilterImpl
     * <em>Variable Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.VariableFilterImpl
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getVariableFilter()
     * @generated
     */
    int VARIABLE_FILTER = 4;

    /**
     * The feature id for the '<em><b>Filter Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABLE_FILTER__FILTER_KIND = FILTER__FILTER_KIND;

    /**
     * The feature id for the '<em><b>Owned Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABLE_FILTER__OWNED_VARIABLES = FILTER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic Condition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION = FILTER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Variable Filter</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABLE_FILTER_FEATURE_COUNT = FILTER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.filter.impl.FilterVariableImpl
     * <em>Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getFilterVariable()
     * @generated
     */
    int FILTER_VARIABLE = 5;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE__CANDIDATES_EXPRESSION = DescriptionPackage.SELECTION_DESCRIPTION__CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Multiple</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE__MULTIPLE = DescriptionPackage.SELECTION_DESCRIPTION__MULTIPLE;

    /**
     * The feature id for the '<em><b>Tree</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE__TREE = DescriptionPackage.SELECTION_DESCRIPTION__TREE;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE__ROOT_EXPRESSION = DescriptionPackage.SELECTION_DESCRIPTION__ROOT_EXPRESSION;

    /**
     * The feature id for the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE__CHILDREN_EXPRESSION = DescriptionPackage.SELECTION_DESCRIPTION__CHILDREN_EXPRESSION;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE__MESSAGE = DescriptionPackage.SELECTION_DESCRIPTION__MESSAGE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE__NAME = DescriptionPackage.SELECTION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Variable</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_FEATURE_COUNT = DescriptionPackage.SELECTION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.filter.FilterKind
     * <em>Kind</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.filter.FilterKind
     * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getFilterKind()
     * @generated
     */
    int FILTER_KIND = 6;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.filter.FilterDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.FilterDescription
     * @generated
     */
    EClass getFilterDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.filter.Filter
     * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.Filter
     * @generated
     */
    EClass getFilter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.filter.Filter#getFilterKind
     * <em>Filter Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Filter Kind</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.Filter#getFilterKind()
     * @see #getFilter()
     * @generated
     */
    EAttribute getFilter_FilterKind();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.filter.MappingFilter
     * <em>Mapping Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Mapping Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.MappingFilter
     * @generated
     */
    EClass getMappingFilter();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getMappings
     * <em>Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Mappings</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getMappings()
     * @see #getMappingFilter()
     * @generated
     */
    EReference getMappingFilter_Mappings();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getSemanticConditionExpression
     * <em>Semantic Condition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Semantic Condition Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getSemanticConditionExpression()
     * @see #getMappingFilter()
     * @generated
     */
    EAttribute getMappingFilter_SemanticConditionExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getViewConditionExpression
     * <em>View Condition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>View Condition Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.MappingFilter#getViewConditionExpression()
     * @see #getMappingFilter()
     * @generated
     */
    EAttribute getMappingFilter_ViewConditionExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.filter.CompositeFilterDescription
     * <em>Composite Filter Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Composite Filter Description</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.filter.CompositeFilterDescription
     * @generated
     */
    EClass getCompositeFilterDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.filter.CompositeFilterDescription#getFilters
     * <em>Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Filters</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.CompositeFilterDescription#getFilters()
     * @see #getCompositeFilterDescription()
     * @generated
     */
    EReference getCompositeFilterDescription_Filters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.filter.VariableFilter
     * <em>Variable Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Variable Filter</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.VariableFilter
     * @generated
     */
    EClass getVariableFilter();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.filter.VariableFilter#getOwnedVariables
     * <em>Owned Variables</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Variables</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.VariableFilter#getOwnedVariables()
     * @see #getVariableFilter()
     * @generated
     */
    EReference getVariableFilter_OwnedVariables();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.filter.VariableFilter#getSemanticConditionExpression
     * <em>Semantic Condition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Semantic Condition Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.VariableFilter#getSemanticConditionExpression()
     * @see #getVariableFilter()
     * @generated
     */
    EAttribute getVariableFilter_SemanticConditionExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.filter.FilterVariable
     * <em>Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.FilterVariable
     * @generated
     */
    EClass getFilterVariable();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.filter.FilterVariable#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.FilterVariable#getName()
     * @see #getFilterVariable()
     * @generated
     */
    EAttribute getFilterVariable_Name();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.description.filter.FilterKind
     * <em>Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Kind</em>'.
     * @see org.eclipse.sirius.viewpoint.description.filter.FilterKind
     * @generated
     */
    EEnum getFilterKind();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    FilterFactory getFilterFactory();

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
         * {@link org.eclipse.sirius.viewpoint.description.filter.impl.FilterDescriptionImpl
         * <em>Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getFilterDescription()
         * @generated
         */
        EClass FILTER_DESCRIPTION = eINSTANCE.getFilterDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.filter.impl.FilterImpl
         * <em>Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterImpl
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getFilter()
         * @generated
         */
        EClass FILTER = eINSTANCE.getFilter();

        /**
         * The meta object literal for the '<em><b>Filter Kind</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FILTER__FILTER_KIND = eINSTANCE.getFilter_FilterKind();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.filter.impl.MappingFilterImpl
         * <em>Mapping Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.MappingFilterImpl
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getMappingFilter()
         * @generated
         */
        EClass MAPPING_FILTER = eINSTANCE.getMappingFilter();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MAPPING_FILTER__MAPPINGS = eINSTANCE.getMappingFilter_Mappings();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Condition Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION = eINSTANCE.getMappingFilter_SemanticConditionExpression();

        /**
         * The meta object literal for the '
         * <em><b>View Condition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MAPPING_FILTER__VIEW_CONDITION_EXPRESSION = eINSTANCE.getMappingFilter_ViewConditionExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.filter.impl.CompositeFilterDescriptionImpl
         * <em>Composite Filter Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.CompositeFilterDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getCompositeFilterDescription()
         * @generated
         */
        EClass COMPOSITE_FILTER_DESCRIPTION = eINSTANCE.getCompositeFilterDescription();

        /**
         * The meta object literal for the '<em><b>Filters</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COMPOSITE_FILTER_DESCRIPTION__FILTERS = eINSTANCE.getCompositeFilterDescription_Filters();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.filter.impl.VariableFilterImpl
         * <em>Variable Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.VariableFilterImpl
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getVariableFilter()
         * @generated
         */
        EClass VARIABLE_FILTER = eINSTANCE.getVariableFilter();

        /**
         * The meta object literal for the '<em><b>Owned Variables</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VARIABLE_FILTER__OWNED_VARIABLES = eINSTANCE.getVariableFilter_OwnedVariables();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Condition Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION = eINSTANCE.getVariableFilter_SemanticConditionExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.filter.impl.FilterVariableImpl
         * <em>Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getFilterVariable()
         * @generated
         */
        EClass FILTER_VARIABLE = eINSTANCE.getFilterVariable();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FILTER_VARIABLE__NAME = eINSTANCE.getFilterVariable_Name();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.filter.FilterKind
         * <em>Kind</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.filter.FilterKind
         * @see org.eclipse.sirius.viewpoint.description.filter.impl.FilterPackageImpl#getFilterKind()
         * @generated
         */
        EEnum FILTER_KIND = eINSTANCE.getFilterKind();

    }

} // FilterPackage
