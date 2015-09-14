/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

/**
 * Specific command to update activated rules.
 *
 * @author mporhel
 */
public final class DeactivateRulesCommand extends RecordingCommand {

    private final Collection<ValidationRule> oldElements;

    private final DDiagram diagram;

    /**
     * Constructor.
     *
     * @param domain
     *            the editing domain.
     * @param diagram
     *            the current diagram.
     * @param oldElements
     *            elements to deactivate
     */
    public DeactivateRulesCommand(TransactionalEditingDomain domain, DDiagram diagram, Collection<ValidationRule> oldElements) {
        super(domain, Messages.DeactivateRulesCommand_label);
        this.oldElements = oldElements;
        this.diagram = diagram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (diagram == null || oldElements == null) {
            return;
        }

        for (ValidationRule rule : oldElements) {
            diagram.getActivatedRules().remove(rule);
        }
    }
}
