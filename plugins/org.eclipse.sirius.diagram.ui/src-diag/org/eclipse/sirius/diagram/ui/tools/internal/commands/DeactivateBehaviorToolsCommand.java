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
import org.eclipse.sirius.diagram.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * Specific command to update activated behaviors.
 *
 * @author mporhel
 */
public final class DeactivateBehaviorToolsCommand extends RecordingCommand {

    private final Collection<BehaviorTool> oldElements;

    private final DDiagram diagram;

    /**
     * Constructor.
     *
     * @param domain
     *            the editing domain.
     * @param oldElements
     *            elements to remove
     * @param dDiagram
     *            the current diagram.
     */
    public DeactivateBehaviorToolsCommand(TransactionalEditingDomain domain, DDiagram dDiagram, Collection<BehaviorTool> oldElements) {
        super(domain, Messages.DeactivateBehaviorToolsCommand_label);
        this.diagram = dDiagram;
        this.oldElements = oldElements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (diagram == null || oldElements == null) {
            return;
        }

        for (BehaviorTool tool : oldElements) {
            diagram.getActivateBehaviors().remove(tool);
        }
        ConcernService.resetCurrentConcern(diagram);
    }
}
