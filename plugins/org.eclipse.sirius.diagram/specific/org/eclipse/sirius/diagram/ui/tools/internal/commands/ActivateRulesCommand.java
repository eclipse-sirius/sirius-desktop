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
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.Collection;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.description.validation.ValidationRule;

/**
 * Specific command to update activated rules.
 * 
 * @author mporhel
 */
public final class ActivateRulesCommand extends RecordingCommand {

    private final Collection<ValidationRule> newElements;

    private final DDiagram diagram;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param diagram
     *            the current diagram.
     * @param newElements
     *            elements to activate
     */
    public ActivateRulesCommand(TransactionalEditingDomain domain, DDiagram diagram, Collection<ValidationRule> newElements) {
        super(domain, "Activate validation rules");
        this.newElements = newElements;
        this.diagram = diagram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (diagram == null || newElements == null) {
            return;
        }

        for (ValidationRule rule : newElements) {
            diagram.getActivatedRules().add(rule);
        }
    }
}
