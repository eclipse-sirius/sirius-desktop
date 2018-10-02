/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common.command;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * A RecordingCommand to add a super type to an {@link EClass}.
 * 
 * @author smonnier
 */
public class EClassSuperTypesAppenderRecordingCommand extends RecordingCommand {

    private EClass eClass;

    private EClass superEClass;

    /**
     * Default constructor.
     * 
     * @param domain
     *            my domain
     * @param eClass
     *            current {@link EClass}
     * @param superEClass
     *            {@link EClass} to add to eClass as super type
     */
    public EClassSuperTypesAppenderRecordingCommand(TransactionalEditingDomain domain, EClass eClass, EClass superEClass) {
        super(domain);
        this.eClass = eClass;
        this.superEClass = superEClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        eClass.getESuperTypes().add(superEClass);
    }

}
