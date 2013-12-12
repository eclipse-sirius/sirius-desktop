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
package org.eclipse.sirius.viewpoint.description.validation;

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
 * @see org.eclipse.sirius.viewpoint.description.validation.ValidationFactory
 * @model kind="package"
 * @generated
 */
public interface ValidationPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "validation";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/description/validation/1.1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "validation";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ValidationPackage eINSTANCE = org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationSetImpl
     * <em>Set</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationSetImpl
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getValidationSet()
     * @generated
     */
    int VALIDATION_SET = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_SET__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Owned Rules</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_SET__OWNED_RULES = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_SET__NAME = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Reused Rules</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_SET__REUSED_RULES = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>All Rules</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_SET__ALL_RULES = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Set</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_SET_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl
     * <em>Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getValidationRule()
     * @generated
     */
    int VALIDATION_RULE = 1;

    /**
     * The feature id for the '<em><b>Level</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_RULE__LEVEL = 0;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_RULE__MESSAGE = 1;

    /**
     * The feature id for the '<em><b>Audits</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_RULE__AUDITS = 2;

    /**
     * The feature id for the '<em><b>Fixes</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_RULE__FIXES = 3;

    /**
     * The number of structural features of the '<em>Rule</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_RULE_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.validation.impl.SemanticValidationRuleImpl
     * <em>Semantic Validation Rule</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.SemanticValidationRuleImpl
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getSemanticValidationRule()
     * @generated
     */
    int SEMANTIC_VALIDATION_RULE = 2;

    /**
     * The feature id for the '<em><b>Level</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_VALIDATION_RULE__LEVEL = VALIDATION_RULE__LEVEL;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_VALIDATION_RULE__MESSAGE = VALIDATION_RULE__MESSAGE;

    /**
     * The feature id for the '<em><b>Audits</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_VALIDATION_RULE__AUDITS = VALIDATION_RULE__AUDITS;

    /**
     * The feature id for the '<em><b>Fixes</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_VALIDATION_RULE__FIXES = VALIDATION_RULE__FIXES;

    /**
     * The feature id for the '<em><b>Target Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_VALIDATION_RULE__TARGET_CLASS = VALIDATION_RULE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Semantic Validation Rule</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEMANTIC_VALIDATION_RULE_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ViewValidationRuleImpl
     * <em>View Validation Rule</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ViewValidationRuleImpl
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getViewValidationRule()
     * @generated
     */
    int VIEW_VALIDATION_RULE = 3;

    /**
     * The feature id for the '<em><b>Level</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VALIDATION_RULE__LEVEL = VALIDATION_RULE__LEVEL;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VALIDATION_RULE__MESSAGE = VALIDATION_RULE__MESSAGE;

    /**
     * The feature id for the '<em><b>Audits</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VALIDATION_RULE__AUDITS = VALIDATION_RULE__AUDITS;

    /**
     * The feature id for the '<em><b>Fixes</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VALIDATION_RULE__FIXES = VALIDATION_RULE__FIXES;

    /**
     * The feature id for the '<em><b>Targets</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VALIDATION_RULE__TARGETS = VALIDATION_RULE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>View Validation Rule</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VALIDATION_RULE_FEATURE_COUNT = VALIDATION_RULE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.validation.impl.RuleAuditImpl
     * <em>Rule Audit</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.RuleAuditImpl
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getRuleAudit()
     * @generated
     */
    int RULE_AUDIT = 4;

    /**
     * The feature id for the '<em><b>Audit Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RULE_AUDIT__AUDIT_EXPRESSION = 0;

    /**
     * The number of structural features of the '<em>Rule Audit</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RULE_AUDIT_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationFixImpl
     * <em>Fix</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationFixImpl
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getValidationFix()
     * @generated
     */
    int VALIDATION_FIX = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_FIX__NAME = 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_FIX__INITIAL_OPERATION = 1;

    /**
     * The number of structural features of the '<em>Fix</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VALIDATION_FIX_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL
     * <em>ERROR LEVEL</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getERROR_LEVEL()
     * @generated
     */
    int ERROR_LEVEL = 6;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet
     * <em>Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Set</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationSet
     * @generated
     */
    EClass getValidationSet();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getOwnedRules
     * <em>Owned Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Rules</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getOwnedRules()
     * @see #getValidationSet()
     * @generated
     */
    EReference getValidationSet_OwnedRules();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getName()
     * @see #getValidationSet()
     * @generated
     */
    EAttribute getValidationSet_Name();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getReusedRules
     * <em>Reused Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Reused Rules</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getReusedRules()
     * @see #getValidationSet()
     * @generated
     */
    EReference getValidationSet_ReusedRules();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getAllRules
     * <em>All Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>All Rules</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getAllRules()
     * @see #getValidationSet()
     * @generated
     */
    EReference getValidationSet_AllRules();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule
     * <em>Rule</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Rule</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationRule
     * @generated
     */
    EClass getValidationRule();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getLevel
     * <em>Level</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Level</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getLevel()
     * @see #getValidationRule()
     * @generated
     */
    EAttribute getValidationRule_Level();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getMessage
     * <em>Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Message</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getMessage()
     * @see #getValidationRule()
     * @generated
     */
    EAttribute getValidationRule_Message();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getAudits
     * <em>Audits</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Audits</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getAudits()
     * @see #getValidationRule()
     * @generated
     */
    EReference getValidationRule_Audits();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getFixes
     * <em>Fixes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Fixes</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getFixes()
     * @see #getValidationRule()
     * @generated
     */
    EReference getValidationRule_Fixes();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule
     * <em>Semantic Validation Rule</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Semantic Validation Rule</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule
     * @generated
     */
    EClass getSemanticValidationRule();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule#getTargetClass
     * <em>Target Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Class</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule#getTargetClass()
     * @see #getSemanticValidationRule()
     * @generated
     */
    EAttribute getSemanticValidationRule_TargetClass();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule
     * <em>View Validation Rule</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>View Validation Rule</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule
     * @generated
     */
    EClass getViewValidationRule();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule#getTargets
     * <em>Targets</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Targets</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule#getTargets()
     * @see #getViewValidationRule()
     * @generated
     */
    EReference getViewValidationRule_Targets();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.validation.RuleAudit
     * <em>Rule Audit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Rule Audit</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.RuleAudit
     * @generated
     */
    EClass getRuleAudit();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.validation.RuleAudit#getAuditExpression
     * <em>Audit Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Audit Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.RuleAudit#getAuditExpression()
     * @see #getRuleAudit()
     * @generated
     */
    EAttribute getRuleAudit_AuditExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationFix
     * <em>Fix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Fix</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationFix
     * @generated
     */
    EClass getValidationFix();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationFix#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationFix#getName()
     * @see #getValidationFix()
     * @generated
     */
    EAttribute getValidationFix_Name();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationFix#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationFix#getInitialOperation()
     * @see #getValidationFix()
     * @generated
     */
    EReference getValidationFix_InitialOperation();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL
     * <em>ERROR LEVEL</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>ERROR LEVEL</em>'.
     * @see org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL
     * @generated
     */
    EEnum getERROR_LEVEL();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ValidationFactory getValidationFactory();

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
         * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationSetImpl
         * <em>Set</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationSetImpl
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getValidationSet()
         * @generated
         */
        EClass VALIDATION_SET = eINSTANCE.getValidationSet();

        /**
         * The meta object literal for the '<em><b>Owned Rules</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VALIDATION_SET__OWNED_RULES = eINSTANCE.getValidationSet_OwnedRules();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VALIDATION_SET__NAME = eINSTANCE.getValidationSet_Name();

        /**
         * The meta object literal for the '<em><b>Reused Rules</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VALIDATION_SET__REUSED_RULES = eINSTANCE.getValidationSet_ReusedRules();

        /**
         * The meta object literal for the '<em><b>All Rules</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VALIDATION_SET__ALL_RULES = eINSTANCE.getValidationSet_AllRules();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl
         * <em>Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getValidationRule()
         * @generated
         */
        EClass VALIDATION_RULE = eINSTANCE.getValidationRule();

        /**
         * The meta object literal for the '<em><b>Level</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VALIDATION_RULE__LEVEL = eINSTANCE.getValidationRule_Level();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VALIDATION_RULE__MESSAGE = eINSTANCE.getValidationRule_Message();

        /**
         * The meta object literal for the '<em><b>Audits</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VALIDATION_RULE__AUDITS = eINSTANCE.getValidationRule_Audits();

        /**
         * The meta object literal for the '<em><b>Fixes</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VALIDATION_RULE__FIXES = eINSTANCE.getValidationRule_Fixes();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.validation.impl.SemanticValidationRuleImpl
         * <em>Semantic Validation Rule</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.SemanticValidationRuleImpl
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getSemanticValidationRule()
         * @generated
         */
        EClass SEMANTIC_VALIDATION_RULE = eINSTANCE.getSemanticValidationRule();

        /**
         * The meta object literal for the '<em><b>Target Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SEMANTIC_VALIDATION_RULE__TARGET_CLASS = eINSTANCE.getSemanticValidationRule_TargetClass();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ViewValidationRuleImpl
         * <em>View Validation Rule</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ViewValidationRuleImpl
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getViewValidationRule()
         * @generated
         */
        EClass VIEW_VALIDATION_RULE = eINSTANCE.getViewValidationRule();

        /**
         * The meta object literal for the '<em><b>Targets</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEW_VALIDATION_RULE__TARGETS = eINSTANCE.getViewValidationRule_Targets();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.validation.impl.RuleAuditImpl
         * <em>Rule Audit</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.RuleAuditImpl
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getRuleAudit()
         * @generated
         */
        EClass RULE_AUDIT = eINSTANCE.getRuleAudit();

        /**
         * The meta object literal for the '<em><b>Audit Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RULE_AUDIT__AUDIT_EXPRESSION = eINSTANCE.getRuleAudit_AuditExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationFixImpl
         * <em>Fix</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationFixImpl
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getValidationFix()
         * @generated
         */
        EClass VALIDATION_FIX = eINSTANCE.getValidationFix();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VALIDATION_FIX__NAME = eINSTANCE.getValidationFix_Name();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VALIDATION_FIX__INITIAL_OPERATION = eINSTANCE.getValidationFix_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL
         * <em>ERROR LEVEL</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL
         * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl#getERROR_LEVEL()
         * @generated
         */
        EEnum ERROR_LEVEL = eINSTANCE.getERROR_LEVEL();

    }

} // ValidationPackage
