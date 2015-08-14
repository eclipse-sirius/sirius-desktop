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
package org.eclipse.sirius.tests.sample.scxml.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
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
import org.eclipse.sirius.tests.sample.scxml.ScxmlFactory;
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
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class ScxmlFactoryImpl extends EFactoryImpl implements ScxmlFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static ScxmlFactory init() {
        try {
            ScxmlFactory theScxmlFactory = (ScxmlFactory) EPackage.Registry.INSTANCE.getEFactory(ScxmlPackage.eNS_URI);
            if (theScxmlFactory != null) {
                return theScxmlFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ScxmlFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public ScxmlFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case ScxmlPackage.DOCUMENT_ROOT:
            return createDocumentRoot();
        case ScxmlPackage.SCXML_ASSIGN_TYPE:
            return createScxmlAssignType();
        case ScxmlPackage.SCXML_CANCEL_TYPE:
            return createScxmlCancelType();
        case ScxmlPackage.SCXML_CONTENT_TYPE:
            return createScxmlContentType();
        case ScxmlPackage.SCXML_DATAMODEL_TYPE:
            return createScxmlDatamodelType();
        case ScxmlPackage.SCXML_DATA_TYPE:
            return createScxmlDataType();
        case ScxmlPackage.SCXML_DONEDATA_TYPE:
            return createScxmlDonedataType();
        case ScxmlPackage.SCXML_ELSEIF_TYPE:
            return createScxmlElseifType();
        case ScxmlPackage.SCXML_ELSE_TYPE:
            return createScxmlElseType();
        case ScxmlPackage.SCXML_FINALIZE_TYPE:
            return createScxmlFinalizeType();
        case ScxmlPackage.SCXML_FINAL_TYPE:
            return createScxmlFinalType();
        case ScxmlPackage.SCXML_FOREACH_TYPE:
            return createScxmlForeachType();
        case ScxmlPackage.SCXML_HISTORY_TYPE:
            return createScxmlHistoryType();
        case ScxmlPackage.SCXML_IF_TYPE:
            return createScxmlIfType();
        case ScxmlPackage.SCXML_INITIAL_TYPE:
            return createScxmlInitialType();
        case ScxmlPackage.SCXML_INVOKE_TYPE:
            return createScxmlInvokeType();
        case ScxmlPackage.SCXML_LOG_TYPE:
            return createScxmlLogType();
        case ScxmlPackage.SCXML_ONENTRY_TYPE:
            return createScxmlOnentryType();
        case ScxmlPackage.SCXML_ONEXIT_TYPE:
            return createScxmlOnexitType();
        case ScxmlPackage.SCXML_PARALLEL_TYPE:
            return createScxmlParallelType();
        case ScxmlPackage.SCXML_PARAM_TYPE:
            return createScxmlParamType();
        case ScxmlPackage.SCXML_RAISE_TYPE:
            return createScxmlRaiseType();
        case ScxmlPackage.SCXML_SCRIPT_TYPE:
            return createScxmlScriptType();
        case ScxmlPackage.SCXML_SCXML_TYPE:
            return createScxmlScxmlType();
        case ScxmlPackage.SCXML_SEND_TYPE:
            return createScxmlSendType();
        case ScxmlPackage.SCXML_STATE_TYPE:
            return createScxmlStateType();
        case ScxmlPackage.SCXML_TRANSITION_TYPE:
            return createScxmlTransitionType();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case ScxmlPackage.ASSIGN_TYPE_DATATYPE:
            return createAssignTypeDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.BINDING_DATATYPE:
            return createBindingDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.BOOLEAN_DATATYPE:
            return createBooleanDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.EXMODE_DATATYPE:
            return createExmodeDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.HISTORY_TYPE_DATATYPE:
            return createHistoryTypeDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.TRANSITION_TYPE_DATATYPE:
            return createTransitionTypeDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.ASSIGN_TYPE_DATATYPE_OBJECT:
            return createAssignTypeDatatypeObjectFromString(eDataType, initialValue);
        case ScxmlPackage.BINDING_DATATYPE_OBJECT:
            return createBindingDatatypeObjectFromString(eDataType, initialValue);
        case ScxmlPackage.BOOLEAN_DATATYPE_OBJECT:
            return createBooleanDatatypeObjectFromString(eDataType, initialValue);
        case ScxmlPackage.COND_LANG_DATATYPE:
            return createCondLangDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.DURATION_DATATYPE:
            return createDurationDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.EVENT_TYPE_DATATYPE:
            return createEventTypeDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.EVENT_TYPES_DATATYPE:
            return createEventTypesDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.EXMODE_DATATYPE_OBJECT:
            return createExmodeDatatypeObjectFromString(eDataType, initialValue);
        case ScxmlPackage.HISTORY_TYPE_DATATYPE_OBJECT:
            return createHistoryTypeDatatypeObjectFromString(eDataType, initialValue);
        case ScxmlPackage.INTEGER_DATATYPE:
            return createIntegerDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.LOC_LANG_DATATYPE:
            return createLocLangDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.TRANSITION_TYPE_DATATYPE_OBJECT:
            return createTransitionTypeDatatypeObjectFromString(eDataType, initialValue);
        case ScxmlPackage.URI_DATATYPE:
            return createURIDatatypeFromString(eDataType, initialValue);
        case ScxmlPackage.VALUE_LANG_DATATYPE:
            return createValueLangDatatypeFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case ScxmlPackage.ASSIGN_TYPE_DATATYPE:
            return convertAssignTypeDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.BINDING_DATATYPE:
            return convertBindingDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.BOOLEAN_DATATYPE:
            return convertBooleanDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.EXMODE_DATATYPE:
            return convertExmodeDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.HISTORY_TYPE_DATATYPE:
            return convertHistoryTypeDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.TRANSITION_TYPE_DATATYPE:
            return convertTransitionTypeDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.ASSIGN_TYPE_DATATYPE_OBJECT:
            return convertAssignTypeDatatypeObjectToString(eDataType, instanceValue);
        case ScxmlPackage.BINDING_DATATYPE_OBJECT:
            return convertBindingDatatypeObjectToString(eDataType, instanceValue);
        case ScxmlPackage.BOOLEAN_DATATYPE_OBJECT:
            return convertBooleanDatatypeObjectToString(eDataType, instanceValue);
        case ScxmlPackage.COND_LANG_DATATYPE:
            return convertCondLangDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.DURATION_DATATYPE:
            return convertDurationDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.EVENT_TYPE_DATATYPE:
            return convertEventTypeDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.EVENT_TYPES_DATATYPE:
            return convertEventTypesDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.EXMODE_DATATYPE_OBJECT:
            return convertExmodeDatatypeObjectToString(eDataType, instanceValue);
        case ScxmlPackage.HISTORY_TYPE_DATATYPE_OBJECT:
            return convertHistoryTypeDatatypeObjectToString(eDataType, instanceValue);
        case ScxmlPackage.INTEGER_DATATYPE:
            return convertIntegerDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.LOC_LANG_DATATYPE:
            return convertLocLangDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.TRANSITION_TYPE_DATATYPE_OBJECT:
            return convertTransitionTypeDatatypeObjectToString(eDataType, instanceValue);
        case ScxmlPackage.URI_DATATYPE:
            return convertURIDatatypeToString(eDataType, instanceValue);
        case ScxmlPackage.VALUE_LANG_DATATYPE:
            return convertValueLangDatatypeToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlAssignType createScxmlAssignType() {
        ScxmlAssignTypeImpl scxmlAssignType = new ScxmlAssignTypeImpl();
        return scxmlAssignType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlCancelType createScxmlCancelType() {
        ScxmlCancelTypeImpl scxmlCancelType = new ScxmlCancelTypeImpl();
        return scxmlCancelType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlContentType createScxmlContentType() {
        ScxmlContentTypeImpl scxmlContentType = new ScxmlContentTypeImpl();
        return scxmlContentType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlDatamodelType createScxmlDatamodelType() {
        ScxmlDatamodelTypeImpl scxmlDatamodelType = new ScxmlDatamodelTypeImpl();
        return scxmlDatamodelType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlDataType createScxmlDataType() {
        ScxmlDataTypeImpl scxmlDataType = new ScxmlDataTypeImpl();
        return scxmlDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlDonedataType createScxmlDonedataType() {
        ScxmlDonedataTypeImpl scxmlDonedataType = new ScxmlDonedataTypeImpl();
        return scxmlDonedataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlElseifType createScxmlElseifType() {
        ScxmlElseifTypeImpl scxmlElseifType = new ScxmlElseifTypeImpl();
        return scxmlElseifType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlElseType createScxmlElseType() {
        ScxmlElseTypeImpl scxmlElseType = new ScxmlElseTypeImpl();
        return scxmlElseType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlFinalizeType createScxmlFinalizeType() {
        ScxmlFinalizeTypeImpl scxmlFinalizeType = new ScxmlFinalizeTypeImpl();
        return scxmlFinalizeType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlFinalType createScxmlFinalType() {
        ScxmlFinalTypeImpl scxmlFinalType = new ScxmlFinalTypeImpl();
        return scxmlFinalType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlForeachType createScxmlForeachType() {
        ScxmlForeachTypeImpl scxmlForeachType = new ScxmlForeachTypeImpl();
        return scxmlForeachType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlHistoryType createScxmlHistoryType() {
        ScxmlHistoryTypeImpl scxmlHistoryType = new ScxmlHistoryTypeImpl();
        return scxmlHistoryType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlIfType createScxmlIfType() {
        ScxmlIfTypeImpl scxmlIfType = new ScxmlIfTypeImpl();
        return scxmlIfType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlInitialType createScxmlInitialType() {
        ScxmlInitialTypeImpl scxmlInitialType = new ScxmlInitialTypeImpl();
        return scxmlInitialType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlInvokeType createScxmlInvokeType() {
        ScxmlInvokeTypeImpl scxmlInvokeType = new ScxmlInvokeTypeImpl();
        return scxmlInvokeType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlLogType createScxmlLogType() {
        ScxmlLogTypeImpl scxmlLogType = new ScxmlLogTypeImpl();
        return scxmlLogType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlOnentryType createScxmlOnentryType() {
        ScxmlOnentryTypeImpl scxmlOnentryType = new ScxmlOnentryTypeImpl();
        return scxmlOnentryType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlOnexitType createScxmlOnexitType() {
        ScxmlOnexitTypeImpl scxmlOnexitType = new ScxmlOnexitTypeImpl();
        return scxmlOnexitType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlParallelType createScxmlParallelType() {
        ScxmlParallelTypeImpl scxmlParallelType = new ScxmlParallelTypeImpl();
        return scxmlParallelType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlParamType createScxmlParamType() {
        ScxmlParamTypeImpl scxmlParamType = new ScxmlParamTypeImpl();
        return scxmlParamType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlRaiseType createScxmlRaiseType() {
        ScxmlRaiseTypeImpl scxmlRaiseType = new ScxmlRaiseTypeImpl();
        return scxmlRaiseType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlScriptType createScxmlScriptType() {
        ScxmlScriptTypeImpl scxmlScriptType = new ScxmlScriptTypeImpl();
        return scxmlScriptType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlScxmlType createScxmlScxmlType() {
        ScxmlScxmlTypeImpl scxmlScxmlType = new ScxmlScxmlTypeImpl();
        return scxmlScxmlType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlSendType createScxmlSendType() {
        ScxmlSendTypeImpl scxmlSendType = new ScxmlSendTypeImpl();
        return scxmlSendType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlStateType createScxmlStateType() {
        ScxmlStateTypeImpl scxmlStateType = new ScxmlStateTypeImpl();
        return scxmlStateType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlTransitionType createScxmlTransitionType() {
        ScxmlTransitionTypeImpl scxmlTransitionType = new ScxmlTransitionTypeImpl();
        return scxmlTransitionType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AssignTypeDatatype createAssignTypeDatatypeFromString(EDataType eDataType, String initialValue) {
        AssignTypeDatatype result = AssignTypeDatatype.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertAssignTypeDatatypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BindingDatatype createBindingDatatypeFromString(EDataType eDataType, String initialValue) {
        BindingDatatype result = BindingDatatype.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertBindingDatatypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BooleanDatatype createBooleanDatatypeFromString(EDataType eDataType, String initialValue) {
        BooleanDatatype result = BooleanDatatype.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertBooleanDatatypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ExmodeDatatype createExmodeDatatypeFromString(EDataType eDataType, String initialValue) {
        ExmodeDatatype result = ExmodeDatatype.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertExmodeDatatypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public HistoryTypeDatatype createHistoryTypeDatatypeFromString(EDataType eDataType, String initialValue) {
        HistoryTypeDatatype result = HistoryTypeDatatype.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertHistoryTypeDatatypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TransitionTypeDatatype createTransitionTypeDatatypeFromString(EDataType eDataType, String initialValue) {
        TransitionTypeDatatype result = TransitionTypeDatatype.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertTransitionTypeDatatypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AssignTypeDatatype createAssignTypeDatatypeObjectFromString(EDataType eDataType, String initialValue) {
        return createAssignTypeDatatypeFromString(ScxmlPackage.Literals.ASSIGN_TYPE_DATATYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertAssignTypeDatatypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertAssignTypeDatatypeToString(ScxmlPackage.Literals.ASSIGN_TYPE_DATATYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BindingDatatype createBindingDatatypeObjectFromString(EDataType eDataType, String initialValue) {
        return createBindingDatatypeFromString(ScxmlPackage.Literals.BINDING_DATATYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertBindingDatatypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertBindingDatatypeToString(ScxmlPackage.Literals.BINDING_DATATYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BooleanDatatype createBooleanDatatypeObjectFromString(EDataType eDataType, String initialValue) {
        return createBooleanDatatypeFromString(ScxmlPackage.Literals.BOOLEAN_DATATYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertBooleanDatatypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertBooleanDatatypeToString(ScxmlPackage.Literals.BOOLEAN_DATATYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String createCondLangDatatypeFromString(EDataType eDataType, String initialValue) {
        return (String) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertCondLangDatatypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String createDurationDatatypeFromString(EDataType eDataType, String initialValue) {
        return (String) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertDurationDatatypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String createEventTypeDatatypeFromString(EDataType eDataType, String initialValue) {
        return (String) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertEventTypeDatatypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String createEventTypesDatatypeFromString(EDataType eDataType, String initialValue) {
        return (String) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertEventTypesDatatypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ExmodeDatatype createExmodeDatatypeObjectFromString(EDataType eDataType, String initialValue) {
        return createExmodeDatatypeFromString(ScxmlPackage.Literals.EXMODE_DATATYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertExmodeDatatypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertExmodeDatatypeToString(ScxmlPackage.Literals.EXMODE_DATATYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public HistoryTypeDatatype createHistoryTypeDatatypeObjectFromString(EDataType eDataType, String initialValue) {
        return createHistoryTypeDatatypeFromString(ScxmlPackage.Literals.HISTORY_TYPE_DATATYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertHistoryTypeDatatypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertHistoryTypeDatatypeToString(ScxmlPackage.Literals.HISTORY_TYPE_DATATYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BigInteger createIntegerDatatypeFromString(EDataType eDataType, String initialValue) {
        return (BigInteger) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertIntegerDatatypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String createLocLangDatatypeFromString(EDataType eDataType, String initialValue) {
        return (String) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertLocLangDatatypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TransitionTypeDatatype createTransitionTypeDatatypeObjectFromString(EDataType eDataType, String initialValue) {
        return createTransitionTypeDatatypeFromString(ScxmlPackage.Literals.TRANSITION_TYPE_DATATYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertTransitionTypeDatatypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertTransitionTypeDatatypeToString(ScxmlPackage.Literals.TRANSITION_TYPE_DATATYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String createURIDatatypeFromString(EDataType eDataType, String initialValue) {
        return (String) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.ANY_URI, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertURIDatatypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.ANY_URI, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String createValueLangDatatypeFromString(EDataType eDataType, String initialValue) {
        return (String) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertValueLangDatatypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ScxmlPackage getScxmlPackage() {
        return (ScxmlPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ScxmlPackage getPackage() {
        return ScxmlPackage.eINSTANCE;
    }

} // ScxmlFactoryImpl
