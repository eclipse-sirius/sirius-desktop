/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common.command;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * A RecordingCommand to add an EClassifier to an {@link EPackage}.
 * 
 * @author smonnier
 */
public class EPackageEClassifiersAppenderRecordingCommand extends RecordingCommand {

    private EPackage ePackage;

    private EClass eClass;

    /**
     * Default constructor.
     * 
     * @param domain
     *            my domain
     * @param ePackage
     *            current {@link EPackage}
     * @param eClass
     *            {@link EClass} to add to ePackage
     */
    public EPackageEClassifiersAppenderRecordingCommand(TransactionalEditingDomain domain, EPackage ePackage, EClass eClass) {
        super(domain);
        this.ePackage = ePackage;
        this.eClass = eClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        ePackage.getEClassifiers().add(eClass);
    }

}
