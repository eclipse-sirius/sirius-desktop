/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.sample.scxml.util;

import java.math.BigInteger;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;
import org.eclipse.sirius.tests.sample.scxml.AssignTypeDatatype;
import org.eclipse.sirius.tests.sample.scxml.BindingDatatype;
import org.eclipse.sirius.tests.sample.scxml.BooleanDatatype;
import org.eclipse.sirius.tests.sample.scxml.DocumentRoot;
import org.eclipse.sirius.tests.sample.scxml.ExmodeDatatype;
import org.eclipse.sirius.tests.sample.scxml.HistoryTypeDatatype;
import org.eclipse.sirius.tests.sample.scxml.ScxmlAssignType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlCancelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlContentType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDatamodelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlDonedataType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlElseType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlElseifType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlFinalType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlFinalizeType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlForeachType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlHistoryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlIfType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlInitialType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlInvokeType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlLogType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnentryType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlOnexitType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParallelType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlParamType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlRaiseType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScriptType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlSendType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlStateType;
import org.eclipse.sirius.tests.sample.scxml.ScxmlTransitionType;
import org.eclipse.sirius.tests.sample.scxml.TransitionTypeDatatype;

/**
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc
 * -->
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage
 * @generated
 */
public class ScxmlValidator extends EObjectValidator {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final ScxmlValidator INSTANCE = new ScxmlValidator();

    /**
     * A constant for the
     * {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of
     * diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes}
     * from this package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.common.util.Diagnostic#getSource()
     * @see org.eclipse.emf.common.util.Diagnostic#getCode()
     * @generated
     */
    public static final String DIAGNOSTIC_SOURCE = "org.eclipse.sirius.tests.sample.scxml";

    /**
     * A constant with a fixed name that can be used as the base value for
     * additional hand written constants. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

    /**
     * A constant with a fixed name that can be used as the base value for
     * additional hand written constants in a derived class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static final int DIAGNOSTIC_CODE_COUNT = ScxmlValidator.GENERATED_DIAGNOSTIC_CODE_COUNT;

    /**
     * The cached base package validator. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected XMLTypeValidator xmlTypeValidator;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public ScxmlValidator() {
        super();
        xmlTypeValidator = XMLTypeValidator.INSTANCE;
    }

    /**
     * Returns the package of this validator switch. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EPackage getEPackage() {
        return ScxmlPackage.eINSTANCE;
    }

    /**
     * Calls <code>validateXXX</code> for the corresponding classifier of the
     * model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
        switch (classifierID) {
        case ScxmlPackage.DOCUMENT_ROOT:
            return validateDocumentRoot((DocumentRoot) value, diagnostics, context);
        case ScxmlPackage.SCXML_ASSIGN_TYPE:
            return validateScxmlAssignType((ScxmlAssignType) value, diagnostics, context);
        case ScxmlPackage.SCXML_CANCEL_TYPE:
            return validateScxmlCancelType((ScxmlCancelType) value, diagnostics, context);
        case ScxmlPackage.SCXML_CONTENT_TYPE:
            return validateScxmlContentType((ScxmlContentType) value, diagnostics, context);
        case ScxmlPackage.SCXML_DATAMODEL_TYPE:
            return validateScxmlDatamodelType((ScxmlDatamodelType) value, diagnostics, context);
        case ScxmlPackage.SCXML_DATA_TYPE:
            return validateScxmlDataType((ScxmlDataType) value, diagnostics, context);
        case ScxmlPackage.SCXML_DONEDATA_TYPE:
            return validateScxmlDonedataType((ScxmlDonedataType) value, diagnostics, context);
        case ScxmlPackage.SCXML_ELSEIF_TYPE:
            return validateScxmlElseifType((ScxmlElseifType) value, diagnostics, context);
        case ScxmlPackage.SCXML_ELSE_TYPE:
            return validateScxmlElseType((ScxmlElseType) value, diagnostics, context);
        case ScxmlPackage.SCXML_FINALIZE_TYPE:
            return validateScxmlFinalizeType((ScxmlFinalizeType) value, diagnostics, context);
        case ScxmlPackage.SCXML_FINAL_TYPE:
            return validateScxmlFinalType((ScxmlFinalType) value, diagnostics, context);
        case ScxmlPackage.SCXML_FOREACH_TYPE:
            return validateScxmlForeachType((ScxmlForeachType) value, diagnostics, context);
        case ScxmlPackage.SCXML_HISTORY_TYPE:
            return validateScxmlHistoryType((ScxmlHistoryType) value, diagnostics, context);
        case ScxmlPackage.SCXML_IF_TYPE:
            return validateScxmlIfType((ScxmlIfType) value, diagnostics, context);
        case ScxmlPackage.SCXML_INITIAL_TYPE:
            return validateScxmlInitialType((ScxmlInitialType) value, diagnostics, context);
        case ScxmlPackage.SCXML_INVOKE_TYPE:
            return validateScxmlInvokeType((ScxmlInvokeType) value, diagnostics, context);
        case ScxmlPackage.SCXML_LOG_TYPE:
            return validateScxmlLogType((ScxmlLogType) value, diagnostics, context);
        case ScxmlPackage.SCXML_ONENTRY_TYPE:
            return validateScxmlOnentryType((ScxmlOnentryType) value, diagnostics, context);
        case ScxmlPackage.SCXML_ONEXIT_TYPE:
            return validateScxmlOnexitType((ScxmlOnexitType) value, diagnostics, context);
        case ScxmlPackage.SCXML_PARALLEL_TYPE:
            return validateScxmlParallelType((ScxmlParallelType) value, diagnostics, context);
        case ScxmlPackage.SCXML_PARAM_TYPE:
            return validateScxmlParamType((ScxmlParamType) value, diagnostics, context);
        case ScxmlPackage.SCXML_RAISE_TYPE:
            return validateScxmlRaiseType((ScxmlRaiseType) value, diagnostics, context);
        case ScxmlPackage.SCXML_SCRIPT_TYPE:
            return validateScxmlScriptType((ScxmlScriptType) value, diagnostics, context);
        case ScxmlPackage.SCXML_SCXML_TYPE:
            return validateScxmlScxmlType((ScxmlScxmlType) value, diagnostics, context);
        case ScxmlPackage.SCXML_SEND_TYPE:
            return validateScxmlSendType((ScxmlSendType) value, diagnostics, context);
        case ScxmlPackage.SCXML_STATE_TYPE:
            return validateScxmlStateType((ScxmlStateType) value, diagnostics, context);
        case ScxmlPackage.SCXML_TRANSITION_TYPE:
            return validateScxmlTransitionType((ScxmlTransitionType) value, diagnostics, context);
        case ScxmlPackage.ASSIGN_TYPE_DATATYPE:
            return validateAssignTypeDatatype((AssignTypeDatatype) value, diagnostics, context);
        case ScxmlPackage.BINDING_DATATYPE:
            return validateBindingDatatype((BindingDatatype) value, diagnostics, context);
        case ScxmlPackage.BOOLEAN_DATATYPE:
            return validateBooleanDatatype((BooleanDatatype) value, diagnostics, context);
        case ScxmlPackage.EXMODE_DATATYPE:
            return validateExmodeDatatype((ExmodeDatatype) value, diagnostics, context);
        case ScxmlPackage.HISTORY_TYPE_DATATYPE:
            return validateHistoryTypeDatatype((HistoryTypeDatatype) value, diagnostics, context);
        case ScxmlPackage.TRANSITION_TYPE_DATATYPE:
            return validateTransitionTypeDatatype((TransitionTypeDatatype) value, diagnostics, context);
        case ScxmlPackage.ASSIGN_TYPE_DATATYPE_OBJECT:
            return validateAssignTypeDatatypeObject((AssignTypeDatatype) value, diagnostics, context);
        case ScxmlPackage.BINDING_DATATYPE_OBJECT:
            return validateBindingDatatypeObject((BindingDatatype) value, diagnostics, context);
        case ScxmlPackage.BOOLEAN_DATATYPE_OBJECT:
            return validateBooleanDatatypeObject((BooleanDatatype) value, diagnostics, context);
        case ScxmlPackage.COND_LANG_DATATYPE:
            return validateCondLangDatatype((String) value, diagnostics, context);
        case ScxmlPackage.DURATION_DATATYPE:
            return validateDurationDatatype((String) value, diagnostics, context);
        case ScxmlPackage.EVENT_TYPE_DATATYPE:
            return validateEventTypeDatatype((String) value, diagnostics, context);
        case ScxmlPackage.EVENT_TYPES_DATATYPE:
            return validateEventTypesDatatype((String) value, diagnostics, context);
        case ScxmlPackage.EXMODE_DATATYPE_OBJECT:
            return validateExmodeDatatypeObject((ExmodeDatatype) value, diagnostics, context);
        case ScxmlPackage.HISTORY_TYPE_DATATYPE_OBJECT:
            return validateHistoryTypeDatatypeObject((HistoryTypeDatatype) value, diagnostics, context);
        case ScxmlPackage.INTEGER_DATATYPE:
            return validateIntegerDatatype((BigInteger) value, diagnostics, context);
        case ScxmlPackage.LOC_LANG_DATATYPE:
            return validateLocLangDatatype((String) value, diagnostics, context);
        case ScxmlPackage.TRANSITION_TYPE_DATATYPE_OBJECT:
            return validateTransitionTypeDatatypeObject((TransitionTypeDatatype) value, diagnostics, context);
        case ScxmlPackage.URI_DATATYPE:
            return validateURIDatatype((String) value, diagnostics, context);
        case ScxmlPackage.VALUE_LANG_DATATYPE:
            return validateValueLangDatatype((String) value, diagnostics, context);
        default:
            return true;
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlAssignType(ScxmlAssignType scxmlAssignType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlAssignType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlCancelType(ScxmlCancelType scxmlCancelType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlCancelType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlContentType(ScxmlContentType scxmlContentType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlContentType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlDatamodelType(ScxmlDatamodelType scxmlDatamodelType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlDatamodelType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlDataType(ScxmlDataType scxmlDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlDataType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlDonedataType(ScxmlDonedataType scxmlDonedataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlDonedataType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlElseifType(ScxmlElseifType scxmlElseifType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlElseifType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlElseType(ScxmlElseType scxmlElseType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlElseType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlFinalizeType(ScxmlFinalizeType scxmlFinalizeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlFinalizeType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlFinalType(ScxmlFinalType scxmlFinalType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlFinalType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlForeachType(ScxmlForeachType scxmlForeachType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlForeachType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlHistoryType(ScxmlHistoryType scxmlHistoryType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlHistoryType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlIfType(ScxmlIfType scxmlIfType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlIfType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlInitialType(ScxmlInitialType scxmlInitialType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlInitialType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlInvokeType(ScxmlInvokeType scxmlInvokeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlInvokeType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlLogType(ScxmlLogType scxmlLogType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlLogType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlOnentryType(ScxmlOnentryType scxmlOnentryType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlOnentryType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlOnexitType(ScxmlOnexitType scxmlOnexitType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlOnexitType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlParallelType(ScxmlParallelType scxmlParallelType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlParallelType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlParamType(ScxmlParamType scxmlParamType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlParamType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlRaiseType(ScxmlRaiseType scxmlRaiseType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlRaiseType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlScriptType(ScxmlScriptType scxmlScriptType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlScriptType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlScxmlType(ScxmlScxmlType scxmlScxmlType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlScxmlType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlSendType(ScxmlSendType scxmlSendType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlSendType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlStateType(ScxmlStateType scxmlStateType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlStateType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateScxmlTransitionType(ScxmlTransitionType scxmlTransitionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(scxmlTransitionType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateAssignTypeDatatype(AssignTypeDatatype assignTypeDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateBindingDatatype(BindingDatatype bindingDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateBooleanDatatype(BooleanDatatype booleanDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateExmodeDatatype(ExmodeDatatype exmodeDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateHistoryTypeDatatype(HistoryTypeDatatype historyTypeDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateTransitionTypeDatatype(TransitionTypeDatatype transitionTypeDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateAssignTypeDatatypeObject(AssignTypeDatatype assignTypeDatatypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateBindingDatatypeObject(BindingDatatype bindingDatatypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateBooleanDatatypeObject(BooleanDatatype booleanDatatypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateCondLangDatatype(String condLangDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateDurationDatatype(String durationDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        boolean result = validateDurationDatatype_Pattern(durationDatatype, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @see #validateDurationDatatype_Pattern
     */
    public static final PatternMatcher[][] DURATION_DATATYPE__PATTERN__VALUES = new PatternMatcher[][] { new PatternMatcher[] { XMLTypeUtil.createPatternMatcher("\\d*(\\.\\d+)?(ms|s|m|h|d)") } };

    /**
     * Validates the Pattern constraint of '<em>Duration Datatype</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateDurationDatatype_Pattern(String durationDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validatePattern(ScxmlPackage.Literals.DURATION_DATATYPE, durationDatatype, ScxmlValidator.DURATION_DATATYPE__PATTERN__VALUES, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateEventTypeDatatype(String eventTypeDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        boolean result = validateEventTypeDatatype_Pattern(eventTypeDatatype, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @see #validateEventTypeDatatype_Pattern
     */
    public static final PatternMatcher[][] EVENT_TYPE_DATATYPE__PATTERN__VALUES = new PatternMatcher[][] { new PatternMatcher[] { XMLTypeUtil
            .createPatternMatcher("(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*") } };

    /**
     * Validates the Pattern constraint of '<em>Event Type Datatype</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateEventTypeDatatype_Pattern(String eventTypeDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validatePattern(ScxmlPackage.Literals.EVENT_TYPE_DATATYPE, eventTypeDatatype, ScxmlValidator.EVENT_TYPE_DATATYPE__PATTERN__VALUES, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateEventTypesDatatype(String eventTypesDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        boolean result = validateEventTypesDatatype_Pattern(eventTypesDatatype, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @see #validateEventTypesDatatype_Pattern
     */
    public static final PatternMatcher[][] EVENT_TYPES_DATATYPE__PATTERN__VALUES = new PatternMatcher[][] { new PatternMatcher[] { XMLTypeUtil
            .createPatternMatcher("\\.?\\*|(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*(\\.\\*)?(\\s(\\i|\\d|\\-)+(\\.(\\i|\\d|\\-)+)*(\\.\\*)?)*") } };

    /**
     * Validates the Pattern constraint of '<em>Event Types Datatype</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateEventTypesDatatype_Pattern(String eventTypesDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validatePattern(ScxmlPackage.Literals.EVENT_TYPES_DATATYPE, eventTypesDatatype, ScxmlValidator.EVENT_TYPES_DATATYPE__PATTERN__VALUES, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateExmodeDatatypeObject(ExmodeDatatype exmodeDatatypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateHistoryTypeDatatypeObject(HistoryTypeDatatype historyTypeDatatypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateIntegerDatatype(BigInteger integerDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        boolean result = xmlTypeValidator.validateNonNegativeInteger_Min(integerDatatype, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateLocLangDatatype(String locLangDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateTransitionTypeDatatypeObject(TransitionTypeDatatype transitionTypeDatatypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateURIDatatype(String uriDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public boolean validateValueLangDatatype(String valueLangDatatype, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * Returns the resource locator that will be used to fetch messages for this
     * validator's diagnostics. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        // TODO
        // Specialize this to return a resource locator for messages specific to
        // this validator.
        // Ensure that you remove @generated or mark it @generated NOT
        return super.getResourceLocator();
    }

} // ScxmlValidator
