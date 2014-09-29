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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * A RecordingCommand to add a structural feature to an {@link EClass}.
 * 
 * @author smonnier
 */
public class EClassEStructuralFeaturesAppenderRecordingCommand extends RecordingCommand {

    private EClass eClass;

    private EAttribute eAttribute;

    /**
     * Default constructor.
     * 
     * @param domain
     *            my domain
     * @param eClass
     *            current {@link EClass}
     * @param eAttribute
     *            {@link EAttribute} to add to eClass
     */
    public EClassEStructuralFeaturesAppenderRecordingCommand(TransactionalEditingDomain domain, EClass eClass, EAttribute eAttribute) {
        super(domain);
        this.eClass = eClass;
        this.eAttribute = eAttribute;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        eClass.getEStructuralFeatures().add(eAttribute);
    }

}
