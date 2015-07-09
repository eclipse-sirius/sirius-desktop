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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.sirius.tests.sample.scxml.DocumentRoot;
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

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage
 * @generated
 */
public class ScxmlSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static ScxmlPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public ScxmlSwitch() {
        if (ScxmlSwitch.modelPackage == null) {
            ScxmlSwitch.modelPackage = ScxmlPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == ScxmlSwitch.modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case ScxmlPackage.DOCUMENT_ROOT: {
            DocumentRoot documentRoot = (DocumentRoot) theEObject;
            T result = caseDocumentRoot(documentRoot);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_ASSIGN_TYPE: {
            ScxmlAssignType scxmlAssignType = (ScxmlAssignType) theEObject;
            T result = caseScxmlAssignType(scxmlAssignType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_CANCEL_TYPE: {
            ScxmlCancelType scxmlCancelType = (ScxmlCancelType) theEObject;
            T result = caseScxmlCancelType(scxmlCancelType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_CONTENT_TYPE: {
            ScxmlContentType scxmlContentType = (ScxmlContentType) theEObject;
            T result = caseScxmlContentType(scxmlContentType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_DATAMODEL_TYPE: {
            ScxmlDatamodelType scxmlDatamodelType = (ScxmlDatamodelType) theEObject;
            T result = caseScxmlDatamodelType(scxmlDatamodelType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_DATA_TYPE: {
            ScxmlDataType scxmlDataType = (ScxmlDataType) theEObject;
            T result = caseScxmlDataType(scxmlDataType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_DONEDATA_TYPE: {
            ScxmlDonedataType scxmlDonedataType = (ScxmlDonedataType) theEObject;
            T result = caseScxmlDonedataType(scxmlDonedataType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_ELSEIF_TYPE: {
            ScxmlElseifType scxmlElseifType = (ScxmlElseifType) theEObject;
            T result = caseScxmlElseifType(scxmlElseifType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_ELSE_TYPE: {
            ScxmlElseType scxmlElseType = (ScxmlElseType) theEObject;
            T result = caseScxmlElseType(scxmlElseType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_FINALIZE_TYPE: {
            ScxmlFinalizeType scxmlFinalizeType = (ScxmlFinalizeType) theEObject;
            T result = caseScxmlFinalizeType(scxmlFinalizeType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_FINAL_TYPE: {
            ScxmlFinalType scxmlFinalType = (ScxmlFinalType) theEObject;
            T result = caseScxmlFinalType(scxmlFinalType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_FOREACH_TYPE: {
            ScxmlForeachType scxmlForeachType = (ScxmlForeachType) theEObject;
            T result = caseScxmlForeachType(scxmlForeachType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_HISTORY_TYPE: {
            ScxmlHistoryType scxmlHistoryType = (ScxmlHistoryType) theEObject;
            T result = caseScxmlHistoryType(scxmlHistoryType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_IF_TYPE: {
            ScxmlIfType scxmlIfType = (ScxmlIfType) theEObject;
            T result = caseScxmlIfType(scxmlIfType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_INITIAL_TYPE: {
            ScxmlInitialType scxmlInitialType = (ScxmlInitialType) theEObject;
            T result = caseScxmlInitialType(scxmlInitialType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_INVOKE_TYPE: {
            ScxmlInvokeType scxmlInvokeType = (ScxmlInvokeType) theEObject;
            T result = caseScxmlInvokeType(scxmlInvokeType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_LOG_TYPE: {
            ScxmlLogType scxmlLogType = (ScxmlLogType) theEObject;
            T result = caseScxmlLogType(scxmlLogType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_ONENTRY_TYPE: {
            ScxmlOnentryType scxmlOnentryType = (ScxmlOnentryType) theEObject;
            T result = caseScxmlOnentryType(scxmlOnentryType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_ONEXIT_TYPE: {
            ScxmlOnexitType scxmlOnexitType = (ScxmlOnexitType) theEObject;
            T result = caseScxmlOnexitType(scxmlOnexitType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_PARALLEL_TYPE: {
            ScxmlParallelType scxmlParallelType = (ScxmlParallelType) theEObject;
            T result = caseScxmlParallelType(scxmlParallelType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_PARAM_TYPE: {
            ScxmlParamType scxmlParamType = (ScxmlParamType) theEObject;
            T result = caseScxmlParamType(scxmlParamType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_RAISE_TYPE: {
            ScxmlRaiseType scxmlRaiseType = (ScxmlRaiseType) theEObject;
            T result = caseScxmlRaiseType(scxmlRaiseType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_SCRIPT_TYPE: {
            ScxmlScriptType scxmlScriptType = (ScxmlScriptType) theEObject;
            T result = caseScxmlScriptType(scxmlScriptType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_SCXML_TYPE: {
            ScxmlScxmlType scxmlScxmlType = (ScxmlScxmlType) theEObject;
            T result = caseScxmlScxmlType(scxmlScxmlType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_SEND_TYPE: {
            ScxmlSendType scxmlSendType = (ScxmlSendType) theEObject;
            T result = caseScxmlSendType(scxmlSendType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_STATE_TYPE: {
            ScxmlStateType scxmlStateType = (ScxmlStateType) theEObject;
            T result = caseScxmlStateType(scxmlStateType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ScxmlPackage.SCXML_TRANSITION_TYPE: {
            ScxmlTransitionType scxmlTransitionType = (ScxmlTransitionType) theEObject;
            T result = caseScxmlTransitionType(scxmlTransitionType);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Document Root</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentRoot(DocumentRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Assign Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Assign Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlAssignType(ScxmlAssignType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Cancel Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Cancel Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlCancelType(ScxmlCancelType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Content Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Content Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlContentType(ScxmlContentType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Datamodel Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Datamodel Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlDatamodelType(ScxmlDatamodelType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Data Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Data Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlDataType(ScxmlDataType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Donedata Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Donedata Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlDonedataType(ScxmlDonedataType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Elseif Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Elseif Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlElseifType(ScxmlElseifType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Else Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Else Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlElseType(ScxmlElseType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Finalize Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Finalize Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlFinalizeType(ScxmlFinalizeType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Final Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Final Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlFinalType(ScxmlFinalType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Foreach Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Foreach Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlForeachType(ScxmlForeachType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>History Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>History Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlHistoryType(ScxmlHistoryType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>If Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>If Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlIfType(ScxmlIfType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Initial Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Initial Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlInitialType(ScxmlInitialType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Invoke Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Invoke Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlInvokeType(ScxmlInvokeType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Log Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Log Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlLogType(ScxmlLogType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Onentry Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Onentry Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlOnentryType(ScxmlOnentryType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Onexit Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Onexit Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlOnexitType(ScxmlOnexitType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Parallel Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Parallel Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlParallelType(ScxmlParallelType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Param Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Param Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlParamType(ScxmlParamType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Raise Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Raise Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlRaiseType(ScxmlRaiseType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Script Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Script Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlScriptType(ScxmlScriptType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Scxml Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Scxml Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlScxmlType(ScxmlScxmlType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Send Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Send Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlSendType(ScxmlSendType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>State Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>State Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlStateType(ScxmlStateType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Transition Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Transition Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScxmlTransitionType(ScxmlTransitionType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} // ScxmlSwitch
