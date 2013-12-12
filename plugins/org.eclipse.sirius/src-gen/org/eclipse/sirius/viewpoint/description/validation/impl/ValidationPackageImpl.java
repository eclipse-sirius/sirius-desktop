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
package org.eclipse.sirius.viewpoint.description.validation.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl;
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
import org.eclipse.sirius.viewpoint.description.validation.RuleAudit;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFactory;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationSet;
import org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule;
import org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ValidationPackageImpl extends EPackageImpl implements ValidationPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass validationSetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass validationRuleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass semanticValidationRuleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass viewValidationRuleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass ruleAuditEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass validationFixEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum erroR_LEVELEEnum = null;

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
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ValidationPackageImpl() {
        super(eNS_URI, ValidationFactory.eINSTANCE);
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
     * This method is used to initialize {@link ValidationPackage#eINSTANCE}
     * when that field is accessed. Clients should not invoke it directly.
     * Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ValidationPackage init() {
        if (isInited)
            return (ValidationPackage) EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI);

        // Obtain or create and register package
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ValidationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new ValidationPackageImpl());

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
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI) instanceof AuditPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(AuditPackage.eNS_URI) : AuditPackage.eINSTANCE);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI) instanceof ConcernPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ConcernPackage.eNS_URI) : ConcernPackage.eINSTANCE);
        DiagramPackageImpl theDiagramPackage = (DiagramPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI) instanceof DiagramPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DiagramPackage.eNS_URI) : DiagramPackage.eINSTANCE);
        org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl theDescriptionPackage_1 = (org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI) instanceof org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI) : org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE);
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI) instanceof FilterPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(FilterPackage.eNS_URI) : FilterPackage.eINSTANCE);

        // Create package meta-data objects
        theValidationPackage.createPackageContents();
        theViewpointPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theToolPackage.createPackageContents();
        theAuditPackage.createPackageContents();
        theConcernPackage.createPackageContents();
        theDiagramPackage.createPackageContents();
        theDescriptionPackage_1.createPackageContents();
        theFilterPackage.createPackageContents();

        // Initialize created meta-data
        theValidationPackage.initializePackageContents();
        theViewpointPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();
        theConcernPackage.initializePackageContents();
        theDiagramPackage.initializePackageContents();
        theDescriptionPackage_1.initializePackageContents();
        theFilterPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theValidationPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ValidationPackage.eNS_URI, theValidationPackage);
        return theValidationPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getValidationSet() {
        return validationSetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getValidationSet_OwnedRules() {
        return (EReference) validationSetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getValidationSet_Name() {
        return (EAttribute) validationSetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getValidationSet_ReusedRules() {
        return (EReference) validationSetEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getValidationSet_AllRules() {
        return (EReference) validationSetEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getValidationRule() {
        return validationRuleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getValidationRule_Level() {
        return (EAttribute) validationRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getValidationRule_Message() {
        return (EAttribute) validationRuleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getValidationRule_Audits() {
        return (EReference) validationRuleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getValidationRule_Fixes() {
        return (EReference) validationRuleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSemanticValidationRule() {
        return semanticValidationRuleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSemanticValidationRule_TargetClass() {
        return (EAttribute) semanticValidationRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getViewValidationRule() {
        return viewValidationRuleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getViewValidationRule_Targets() {
        return (EReference) viewValidationRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRuleAudit() {
        return ruleAuditEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRuleAudit_AuditExpression() {
        return (EAttribute) ruleAuditEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getValidationFix() {
        return validationFixEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getValidationFix_Name() {
        return (EAttribute) validationFixEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getValidationFix_InitialOperation() {
        return (EReference) validationFixEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getERROR_LEVEL() {
        return erroR_LEVELEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ValidationFactory getValidationFactory() {
        return (ValidationFactory) getEFactoryInstance();
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
        validationSetEClass = createEClass(VALIDATION_SET);
        createEReference(validationSetEClass, VALIDATION_SET__OWNED_RULES);
        createEAttribute(validationSetEClass, VALIDATION_SET__NAME);
        createEReference(validationSetEClass, VALIDATION_SET__REUSED_RULES);
        createEReference(validationSetEClass, VALIDATION_SET__ALL_RULES);

        validationRuleEClass = createEClass(VALIDATION_RULE);
        createEAttribute(validationRuleEClass, VALIDATION_RULE__LEVEL);
        createEAttribute(validationRuleEClass, VALIDATION_RULE__MESSAGE);
        createEReference(validationRuleEClass, VALIDATION_RULE__AUDITS);
        createEReference(validationRuleEClass, VALIDATION_RULE__FIXES);

        semanticValidationRuleEClass = createEClass(SEMANTIC_VALIDATION_RULE);
        createEAttribute(semanticValidationRuleEClass, SEMANTIC_VALIDATION_RULE__TARGET_CLASS);

        viewValidationRuleEClass = createEClass(VIEW_VALIDATION_RULE);
        createEReference(viewValidationRuleEClass, VIEW_VALIDATION_RULE__TARGETS);

        ruleAuditEClass = createEClass(RULE_AUDIT);
        createEAttribute(ruleAuditEClass, RULE_AUDIT__AUDIT_EXPRESSION);

        validationFixEClass = createEClass(VALIDATION_FIX);
        createEAttribute(validationFixEClass, VALIDATION_FIX__NAME);
        createEReference(validationFixEClass, VALIDATION_FIX__INITIAL_OPERATION);

        // Create enums
        erroR_LEVELEEnum = createEEnum(ERROR_LEVEL);
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
        org.eclipse.sirius.diagram.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.diagram.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        validationSetEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        semanticValidationRuleEClass.getESuperTypes().add(this.getValidationRule());
        viewValidationRuleEClass.getESuperTypes().add(this.getValidationRule());

        // Initialize classes and features; add operations and parameters
        initEClass(validationSetEClass, ValidationSet.class, "ValidationSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValidationSet_OwnedRules(), this.getValidationRule(), null, "ownedRules", null, 0, -1, ValidationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getValidationSet_Name(), ecorePackage.getEString(), "name", "", 0, 1, ValidationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getValidationSet_ReusedRules(), this.getValidationRule(), null, "reusedRules", null, 0, -1, ValidationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValidationSet_AllRules(), this.getValidationRule(), null, "allRules", null, 0, -1, ValidationSet.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(validationRuleEClass, ValidationRule.class, "ValidationRule", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getValidationRule_Level(), this.getERROR_LEVEL(), "level", "INFO", 1, 1, ValidationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getValidationRule_Message(), theDescriptionPackage.getInterpretedExpression(), "message", "The <%name%> element has...", 0, 1, ValidationRule.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValidationRule_Audits(), this.getRuleAudit(), null, "audits", null, 0, -1, ValidationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValidationRule_Fixes(), this.getValidationFix(), null, "fixes", null, 0, -1, ValidationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = addEOperation(validationRuleEClass, theEcorePackage.getEBoolean(), "checkRule", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "eObj", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(validationRuleEClass, theEcorePackage.getEString(), "getMessage", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEObject(), "eObj", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(semanticValidationRuleEClass, SemanticValidationRule.class, "SemanticValidationRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSemanticValidationRule_TargetClass(), theDescriptionPackage.getTypeName(), "targetClass", null, 1, 1, SemanticValidationRule.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(viewValidationRuleEClass, ViewValidationRule.class, "ViewValidationRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getViewValidationRule_Targets(), theDescriptionPackage_1.getDiagramElementMapping(), null, "targets", null, 0, -1, ViewValidationRule.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ruleAuditEClass, RuleAudit.class, "RuleAudit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRuleAudit_AuditExpression(), theDescriptionPackage.getInterpretedExpression(), "auditExpression", null, 1, 1, RuleAudit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(validationFixEClass, ValidationFix.class, "ValidationFix", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getValidationFix_Name(), theEcorePackage.getEString(), "name", null, 1, 1, ValidationFix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getValidationFix_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, ValidationFix.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(erroR_LEVELEEnum, org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL.class, "ERROR_LEVEL");
        addEEnumLiteral(erroR_LEVELEEnum, org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL.INFO_LITERAL);
        addEEnumLiteral(erroR_LEVELEEnum, org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL.WARNING_LITERAL);
        addEEnumLiteral(erroR_LEVELEEnum, org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL.ERROR_LITERAL);

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
        addAnnotation(getValidationRule_Message(), source, new String[] { "returnType", "a boolean." });
        addAnnotation(getRuleAudit_AuditExpression(), source, new String[] { "returnType", "a boolean." });
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
        addAnnotation(getValidationRule_Message(), source, new String[] {});
        addAnnotation(getRuleAudit_AuditExpression(), source, new String[] {});
    }

} // ValidationPackageImpl
