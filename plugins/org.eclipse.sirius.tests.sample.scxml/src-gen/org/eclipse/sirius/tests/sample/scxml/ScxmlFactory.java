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
package org.eclipse.sirius.tests.sample.scxml;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage
 * @generated
 */
public interface ScxmlFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    ScxmlFactory eINSTANCE = org.eclipse.sirius.tests.sample.scxml.impl.ScxmlFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Document Root</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>Assign Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Assign Type</em>'.
     * @generated
     */
    ScxmlAssignType createScxmlAssignType();

    /**
     * Returns a new object of class '<em>Cancel Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Cancel Type</em>'.
     * @generated
     */
    ScxmlCancelType createScxmlCancelType();

    /**
     * Returns a new object of class '<em>Content Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Content Type</em>'.
     * @generated
     */
    ScxmlContentType createScxmlContentType();

    /**
     * Returns a new object of class '<em>Datamodel Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Datamodel Type</em>'.
     * @generated
     */
    ScxmlDatamodelType createScxmlDatamodelType();

    /**
     * Returns a new object of class '<em>Data Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Data Type</em>'.
     * @generated
     */
    ScxmlDataType createScxmlDataType();

    /**
     * Returns a new object of class '<em>Donedata Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Donedata Type</em>'.
     * @generated
     */
    ScxmlDonedataType createScxmlDonedataType();

    /**
     * Returns a new object of class '<em>Elseif Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Elseif Type</em>'.
     * @generated
     */
    ScxmlElseifType createScxmlElseifType();

    /**
     * Returns a new object of class '<em>Else Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Else Type</em>'.
     * @generated
     */
    ScxmlElseType createScxmlElseType();

    /**
     * Returns a new object of class '<em>Finalize Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Finalize Type</em>'.
     * @generated
     */
    ScxmlFinalizeType createScxmlFinalizeType();

    /**
     * Returns a new object of class '<em>Final Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Final Type</em>'.
     * @generated
     */
    ScxmlFinalType createScxmlFinalType();

    /**
     * Returns a new object of class '<em>Foreach Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Foreach Type</em>'.
     * @generated
     */
    ScxmlForeachType createScxmlForeachType();

    /**
     * Returns a new object of class '<em>History Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>History Type</em>'.
     * @generated
     */
    ScxmlHistoryType createScxmlHistoryType();

    /**
     * Returns a new object of class '<em>If Type</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>If Type</em>'.
     * @generated
     */
    ScxmlIfType createScxmlIfType();

    /**
     * Returns a new object of class '<em>Initial Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Initial Type</em>'.
     * @generated
     */
    ScxmlInitialType createScxmlInitialType();

    /**
     * Returns a new object of class '<em>Invoke Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Invoke Type</em>'.
     * @generated
     */
    ScxmlInvokeType createScxmlInvokeType();

    /**
     * Returns a new object of class '<em>Log Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Log Type</em>'.
     * @generated
     */
    ScxmlLogType createScxmlLogType();

    /**
     * Returns a new object of class '<em>Onentry Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Onentry Type</em>'.
     * @generated
     */
    ScxmlOnentryType createScxmlOnentryType();

    /**
     * Returns a new object of class '<em>Onexit Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Onexit Type</em>'.
     * @generated
     */
    ScxmlOnexitType createScxmlOnexitType();

    /**
     * Returns a new object of class '<em>Parallel Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Parallel Type</em>'.
     * @generated
     */
    ScxmlParallelType createScxmlParallelType();

    /**
     * Returns a new object of class '<em>Param Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Param Type</em>'.
     * @generated
     */
    ScxmlParamType createScxmlParamType();

    /**
     * Returns a new object of class '<em>Raise Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Raise Type</em>'.
     * @generated
     */
    ScxmlRaiseType createScxmlRaiseType();

    /**
     * Returns a new object of class '<em>Script Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Script Type</em>'.
     * @generated
     */
    ScxmlScriptType createScxmlScriptType();

    /**
     * Returns a new object of class '<em>Scxml Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Scxml Type</em>'.
     * @generated
     */
    ScxmlScxmlType createScxmlScxmlType();

    /**
     * Returns a new object of class '<em>Send Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Send Type</em>'.
     * @generated
     */
    ScxmlSendType createScxmlSendType();

    /**
     * Returns a new object of class '<em>State Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>State Type</em>'.
     * @generated
     */
    ScxmlStateType createScxmlStateType();

    /**
     * Returns a new object of class '<em>Transition Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Transition Type</em>'.
     * @generated
     */
    ScxmlTransitionType createScxmlTransitionType();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    ScxmlPackage getScxmlPackage();

} // ScxmlFactory
