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
package org.eclipse.sirius.diagram.description.filter.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.concern.ConcernPackage;
import org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterFactory;
import org.eclipse.sirius.diagram.description.filter.FilterKind;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.MappingFilter;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.style.impl.StylePackageImpl;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.diagram.impl.DiagramPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class FilterPackageImpl extends EPackageImpl implements FilterPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass filterDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass filterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass mappingFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass compositeFilterDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass variableFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum filterKindEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private FilterPackageImpl() {
        super(FilterPackage.eNS_URI, FilterFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link FilterPackage#eINSTANCE} when that field is accessed. Clients should not
     * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static FilterPackage init() {
        if (FilterPackageImpl.isInited) {
            return (FilterPackage) EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI);
        }

        // Obtain or create and register package
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (EPackage.Registry.INSTANCE.get(FilterPackage.eNS_URI) instanceof FilterPackageImpl
                ? EPackage.Registry.INSTANCE.get(FilterPackage.eNS_URI)
                : new FilterPackageImpl());

        FilterPackageImpl.isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();
        ViewpointPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        DiagramPackageImpl theDiagramPackage = (DiagramPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI) instanceof DiagramPackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI)
                : DiagramPackage.eINSTANCE);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI)
                : DescriptionPackage.eINSTANCE);
        StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI) instanceof StylePackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI)
                : StylePackage.eINSTANCE);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI)
                : ToolPackage.eINSTANCE);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI) instanceof ConcernPackageImpl
                ? EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI)
                : ConcernPackage.eINSTANCE);

        // Create package meta-data objects
        theFilterPackage.createPackageContents();
        theDiagramPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theToolPackage.createPackageContents();
        theConcernPackage.createPackageContents();

        // Initialize created meta-data
        theFilterPackage.initializePackageContents();
        theDiagramPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theConcernPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theFilterPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(FilterPackage.eNS_URI, theFilterPackage);
        return theFilterPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFilterDescription() {
        return filterDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFilter() {
        return filterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getFilter_FilterKind() {
        return (EAttribute) filterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMappingFilter() {
        return mappingFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMappingFilter_Mappings() {
        return (EReference) mappingFilterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMappingFilter_SemanticConditionExpression() {
        return (EAttribute) mappingFilterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMappingFilter_ViewConditionExpression() {
        return (EAttribute) mappingFilterEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCompositeFilterDescription() {
        return compositeFilterDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCompositeFilterDescription_Filters() {
        return (EReference) compositeFilterDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getVariableFilter() {
        return variableFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getVariableFilter_OwnedVariables() {
        return (EReference) variableFilterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getVariableFilter_SemanticConditionExpression() {
        return (EAttribute) variableFilterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getFilterKind() {
        return filterKindEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FilterFactory getFilterFactory() {
        return (FilterFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
     * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        filterDescriptionEClass = createEClass(FilterPackage.FILTER_DESCRIPTION);

        filterEClass = createEClass(FilterPackage.FILTER);
        createEAttribute(filterEClass, FilterPackage.FILTER__FILTER_KIND);

        mappingFilterEClass = createEClass(FilterPackage.MAPPING_FILTER);
        createEReference(mappingFilterEClass, FilterPackage.MAPPING_FILTER__MAPPINGS);
        createEAttribute(mappingFilterEClass, FilterPackage.MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION);
        createEAttribute(mappingFilterEClass, FilterPackage.MAPPING_FILTER__VIEW_CONDITION_EXPRESSION);

        compositeFilterDescriptionEClass = createEClass(FilterPackage.COMPOSITE_FILTER_DESCRIPTION);
        createEReference(compositeFilterDescriptionEClass, FilterPackage.COMPOSITE_FILTER_DESCRIPTION__FILTERS);

        variableFilterEClass = createEClass(FilterPackage.VARIABLE_FILTER);
        createEReference(variableFilterEClass, FilterPackage.VARIABLE_FILTER__OWNED_VARIABLES);
        createEAttribute(variableFilterEClass, FilterPackage.VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION);

        // Create enums
        filterKindEEnum = createEEnum(FilterPackage.FILTER_KIND);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
     * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(FilterPackage.eNAME);
        setNsPrefix(FilterPackage.eNS_PREFIX);
        setNsURI(FilterPackage.eNS_URI);

        // Obtain other dependent packages
        org.eclipse.sirius.viewpoint.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.viewpoint.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        DiagramPackage theDiagramPackage = (DiagramPackage) EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        filterDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getDocumentedElement());
        filterDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getIdentifiedElement());
        mappingFilterEClass.getESuperTypes().add(this.getFilter());
        compositeFilterDescriptionEClass.getESuperTypes().add(this.getFilterDescription());
        variableFilterEClass.getESuperTypes().add(this.getFilter());

        // Initialize classes and features; add operations and parameters
        initEClass(filterDescriptionEClass, FilterDescription.class, "FilterDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        EOperation op = addEOperation(filterDescriptionEClass, theEcorePackage.getEBoolean(), "isVisible", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theDiagramPackage.getDDiagramElement(), "element", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(filterEClass, Filter.class, "Filter", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFilter_FilterKind(), this.getFilterKind(), "filterKind", "HIDE", 0, 1, Filter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(mappingFilterEClass, MappingFilter.class, "MappingFilter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getMappingFilter_Mappings(), theDescriptionPackage.getDiagramElementMapping(), null, "mappings", null, 0, -1, MappingFilter.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getMappingFilter_SemanticConditionExpression(), theDescriptionPackage_1.getInterpretedExpression(), "semanticConditionExpression", null, 0, 1, MappingFilter.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getMappingFilter_ViewConditionExpression(), theDescriptionPackage_1.getInterpretedExpression(), "viewConditionExpression", null, 0, 1, MappingFilter.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(compositeFilterDescriptionEClass, CompositeFilterDescription.class, "CompositeFilterDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompositeFilterDescription_Filters(), this.getFilter(), null, "filters", null, 1, -1, CompositeFilterDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(variableFilterEClass, VariableFilter.class, "VariableFilter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getVariableFilter_OwnedVariables(), theDescriptionPackage_1.getInteractiveVariableDescription(), null, "ownedVariables", null, 0, -1, VariableFilter.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getVariableFilter_SemanticConditionExpression(), theDescriptionPackage_1.getInterpretedExpression(), "semanticConditionExpression", "", 0, 1, VariableFilter.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(filterKindEEnum, FilterKind.class, "FilterKind"); //$NON-NLS-1$
        addEEnumLiteral(filterKindEEnum, FilterKind.HIDE_LITERAL);
        addEEnumLiteral(filterKindEEnum, FilterKind.COLLAPSE_LITERAL);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getMappingFilter_SemanticConditionExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getMappingFilter_ViewConditionExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getVariableFilter_SemanticConditionExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables"; //$NON-NLS-1$
        addAnnotation(getMappingFilter_SemanticConditionExpression(), source, new String[] {});
        addAnnotation(getMappingFilter_ViewConditionExpression(), source, new String[] {});
        addAnnotation(getVariableFilter_SemanticConditionExpression(), source, new String[] {});
    }

} // FilterPackageImpl
