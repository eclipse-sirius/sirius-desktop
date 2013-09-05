/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.commands;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.DDiagram;

/**
 * Specific command to change synchronized diagram status.
 * 
 * @author mporhel
 */
public class ChangeSynchronizedDagramStatusCommand extends RecordingCommand {

    private final DDiagram diagram;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param diagram
     *            the diagram to update.
     */
    public ChangeSynchronizedDagramStatusCommand(TransactionalEditingDomain domain, DDiagram diagram) {
        super(domain, "Change Synchronized status");
        this.diagram = diagram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (diagram == null) {
            return;
        }

        diagram.setSynchronized(!diagram.isSynchronized());
    }
}
