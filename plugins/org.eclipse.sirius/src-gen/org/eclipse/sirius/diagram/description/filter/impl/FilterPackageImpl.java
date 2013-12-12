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
package org.eclipse.sirius.diagram.description.filter.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterFactory;
import org.eclipse.sirius.diagram.description.filter.FilterKind;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.FilterVariable;
import org.eclipse.sirius.diagram.description.filter.MappingFilter;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.diagram.impl.DiagramPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl;
import org.eclipse.sirius.viewpoint.description.concern.ConcernPackage;
import org.eclipse.sirius.viewpoint.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl;
import org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
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
    private EClass filterVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum filterKindEEnum = null;

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
     * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private FilterPackageImpl() {
        super(eNS_URI, FilterFactory.eINSTANCE);
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
     * This method is used to initialize {@link FilterPackage#eINSTANCE} when
     * that field is accessed. Clients should not invoke it directly. Instead,
     * they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static FilterPackage init() {
        if (isInited)
            return (FilterPackage) EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI);

        // Obtain or create and register package
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FilterPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new FilterPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        ViewpointPackageImpl theViewpointPackage = (ViewpointPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI) instanceof ViewpointPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ViewpointPackage.eNS_URI) : ViewpointPackage.eINSTANCE);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);
        StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI) instanceof StylePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(StylePackage.eNS_URI) : StylePackage.eINSTANCE);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ToolPackage.eNS_URI) : ToolPackage.eINSTANCE);
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI) instanceof ValidationPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ValidationPackage.eNS_URI) : ValidationPackage.eINSTANCE);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI) instanceof AuditPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(AuditPackage.eNS_URI) : AuditPackage.eINSTANCE);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI) instanceof ConcernPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ConcernPackage.eNS_URI) : ConcernPackage.eINSTANCE);
        DiagramPackageImpl theDiagramPackage = (DiagramPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI) instanceof DiagramPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DiagramPackage.eNS_URI) : DiagramPackage.eINSTANCE);
        org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl theDescriptionPackage_1 = (org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI) instanceof org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI) : org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE);

        // Create package meta-data objects
        theFilterPackage.createPackageContents();
        theViewpointPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theToolPackage.createPackageContents();
        theValidationPackage.createPackageContents();
        theAuditPackage.createPackageContents();
        theConcernPackage.createPackageContents();
        theDiagramPackage.createPackageContents();
        theDescriptionPackage_1.createPackageContents();

        // Initialize created meta-data
        theFilterPackage.initializePackageContents();
        theViewpointPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theValidationPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();
        theConcernPackage.initializePackageContents();
        theDiagramPackage.initializePackageContents();
        theDescriptionPackage_1.initializePackageContents();

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
    public EClass getFilterDescription() {
        return filterDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFilter() {
        return filterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFilter_FilterKind() {
        return (EAttribute) filterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getMappingFilter() {
        return mappingFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getMappingFilter_Mappings() {
        return (EReference) mappingFilterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMappingFilter_SemanticConditionExpression() {
        return (EAttribute) mappingFilterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMappingFilter_ViewConditionExpression() {
        return (EAttribute) mappingFilterEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCompositeFilterDescription() {
        return compositeFilterDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCompositeFilterDescription_Filters() {
        return (EReference) compositeFilterDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getVariableFilter() {
        return variableFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getVariableFilter_OwnedVariables() {
        return (EReference) variableFilterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getVariableFilter_SemanticConditionExpression() {
        return (EAttribute) variableFilterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFilterVariable() {
        return filterVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFilterVariable_Name() {
        return (EAttribute) filterVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getFilterKind() {
        return filterKindEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
        filterDescriptionEClass = createEClass(FILTER_DESCRIPTION);

        filterEClass = createEClass(FILTER);
        createEAttribute(filterEClass, FILTER__FILTER_KIND);

        mappingFilterEClass = createEClass(MAPPING_FILTER);
        createEReference(mappingFilterEClass, MAPPING_FILTER__MAPPINGS);
        createEAttribute(mappingFilterEClass, MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION);
        createEAttribute(mappingFilterEClass, MAPPING_FILTER__VIEW_CONDITION_EXPRESSION);

        compositeFilterDescriptionEClass = createEClass(COMPOSITE_FILTER_DESCRIPTION);
        createEReference(compositeFilterDescriptionEClass, COMPOSITE_FILTER_DESCRIPTION__FILTERS);

        variableFilterEClass = createEClass(VARIABLE_FILTER);
        createEReference(variableFilterEClass, VARIABLE_FILTER__OWNED_VARIABLES);
        createEAttribute(variableFilterEClass, VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION);

        filterVariableEClass = createEClass(FILTER_VARIABLE);
        createEAttribute(filterVariableEClass, FILTER_VARIABLE__NAME);

        // Create enums
        filterKindEEnum = createEEnum(FILTER_KIND);
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
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        DiagramPackage theDiagramPackage = (DiagramPackage) EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);
        org.eclipse.sirius.diagram.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.diagram.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        filterDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        filterDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        mappingFilterEClass.getESuperTypes().add(this.getFilter());
        compositeFilterDescriptionEClass.getESuperTypes().add(this.getFilterDescription());
        variableFilterEClass.getESuperTypes().add(this.getFilter());
        filterVariableEClass.getESuperTypes().add(theDescriptionPackage.getSelectionDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(filterDescriptionEClass, FilterDescription.class, "FilterDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        EOperation op = addEOperation(filterDescriptionEClass, theEcorePackage.getEBoolean(), "isVisible", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theDiagramPackage.getDDiagramElement(), "element", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(filterEClass, Filter.class, "Filter", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFilter_FilterKind(), this.getFilterKind(), "filterKind", "HIDE", 0, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        op = addEOperation(filterEClass, theEcorePackage.getEBoolean(), "isVisible", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theDiagramPackage.getDDiagramElement(), "element", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(mappingFilterEClass, MappingFilter.class, "MappingFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMappingFilter_Mappings(), theDescriptionPackage_1.getDiagramElementMapping(), null, "mappings", null, 0, -1, MappingFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMappingFilter_SemanticConditionExpression(), theDescriptionPackage.getInterpretedExpression(), "semanticConditionExpression", null, 0, 1, MappingFilter.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMappingFilter_ViewConditionExpression(), theDescriptionPackage.getInterpretedExpression(), "viewConditionExpression", null, 0, 1, MappingFilter.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(compositeFilterDescriptionEClass, CompositeFilterDescription.class, "CompositeFilterDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompositeFilterDescription_Filters(), this.getFilter(), null, "filters", null, 1, -1, CompositeFilterDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(variableFilterEClass, VariableFilter.class, "VariableFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVariableFilter_OwnedVariables(), this.getFilterVariable(), null, "ownedVariables", null, 0, -1, VariableFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVariableFilter_SemanticConditionExpression(), theDescriptionPackage.getInterpretedExpression(), "semanticConditionExpression", "", 0, 1, VariableFilter.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(variableFilterEClass, null, "setFilterContext", 0, 1, IS_UNIQUE, IS_ORDERED);
        EGenericType g1 = createEGenericType(theEcorePackage.getEMap());
        EGenericType g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        addEParameter(op, g1, "variables", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(filterVariableEClass, FilterVariable.class, "FilterVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFilterVariable_Name(), theEcorePackage.getEString(), "name", null, 1, 1, FilterVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(filterKindEEnum, FilterKind.class, "FilterKind");
        addEEnumLiteral(filterKindEEnum, FilterKind.HIDE_LITERAL);
        addEEnumLiteral(filterKindEEnum, FilterKind.COLLAPSE_LITERAL);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType";
        addAnnotation(getMappingFilter_SemanticConditionExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getMappingFilter_ViewConditionExpression(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getVariableFilter_SemanticConditionExpression(), source, new String[] { "returnType", "a boolean." });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables";
        addAnnotation(getMappingFilter_SemanticConditionExpression(), source, new String[] {});
        addAnnotation(getMappingFilter_ViewConditionExpression(), source, new String[] {});
        addAnnotation(getVariableFilter_SemanticConditionExpression(), source, new String[] {});
    }

} // FilterPackageImpl
