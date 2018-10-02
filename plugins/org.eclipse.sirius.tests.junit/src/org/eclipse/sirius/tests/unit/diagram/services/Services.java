/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.services;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.business.internal.logger.OCERuntimeLoggerSpy;

/**
 * The services class used by VSM of the current test.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class Services {

    /**
     * Service that is intended to create one new EClass and to throw an
     * OperationCanceledExceptionan. It is to test the behavior of each
     * interpreter when calling such kind of service in a tool. In theory, it
     * should rollback all the tool.
     * 
     * @param container
     *            The container of the new classes
     * @return a the first new EClass
     */
    public EClass createNewClassService(EPackage container) {
        return createNewClass(container, "service");
    }

    /**
     * Service that is intended to create one new EClass and to throw an
     * OperationCanceledExceptionan. It is to test the behavior of each
     * interpreter when calling such kind of service in a tool. In theory, it
     * should rollback all the tool.
     * 
     * @param container
     *            The container of the new classes
     * @return a the first new EClass
     */
    public EClass createNewClassServiceSpecificMessage(EPackage container) {
        return createNewClass(container, OCERuntimeLoggerSpy.RE_THROW_STATUS_MESSAGE_KEY, "service");
    }

    /**
     * Service that is intended to create one new EClass and to throw an
     * OperationCanceledExceptionan. It is to test the behavior of each
     * interpreter when calling such kind of service in a tool. In theory, it
     * should rollback all the tool.
     * 
     * @param container
     *            The container of the new classes
     * @param suffix
     *            The suffix for the class name
     * @return a the first new EClass
     */
    public EClass createNewClass(EPackage container, String suffix) {
        return createNewClass(container, "", suffix);
    }

    /**
     * Service that is intended to create one new EClass and to throw an
     * OperationCanceledExceptionan. It is to test the behavior of each
     * interpreter when calling such kind of service in a tool. In theory, it
     * should rollback all the tool.
     * 
     * @param container
     *            The container of the new classes
     * @param suffix
     *            The suffix for the class name
     * @return a the first new EClass
     */
    public EClass createNewClassSpecificMessage(EPackage container, String suffix) {
        return createNewClass(container, OCERuntimeLoggerSpy.RE_THROW_STATUS_MESSAGE_KEY, suffix);
    }

    /**
     * Service that is intended to create one new EClass and to throw an
     * OperationCanceledExceptionan. It is to test the behavior of each
     * interpreter when calling such kind of service in a tool. In theory, it
     * should rollback all the tool.
     * 
     * @param container
     *            The container of the new classes
     * @param operationCanceledExceptionMessagePrefix
     *            The prefix to use for the OperationCanceledException message
     * @param classNameSuffix
     *            The suffix for the class name
     * @return a the first new EClass
     */
    private EClass createNewClass(EPackage container, String operationCanceledExceptionMessagePrefix, String classNameSuffix) {
        EClass firstEClass = EcoreFactory.eINSTANCE.createEClass();
        firstEClass.setName("EClass1_" + classNameSuffix);
        container.getEClassifiers().add(firstEClass);
        // Behind is code to simulate a service throwing an
        // OperationCanceledException. So the pseudo dead code is expected.
        if (true) {
            throw new OperationCanceledException(operationCanceledExceptionMessagePrefix + "Creation of new EClass is not possible.");
        }
        EClass secondEClass = EcoreFactory.eINSTANCE.createEClass();
        secondEClass.setName("EClass2_" + classNameSuffix);
        container.getEClassifiers().add(secondEClass);
        return firstEClass;
    }

}
