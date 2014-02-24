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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;

/**
 * Specific command to update activated behaviors.
 * 
 * @author mporhel
 */
public final class ActivateBehaviorToolsCommand extends RecordingCommand {

    private final Collection<BehaviorTool> newElements;

    private final DDiagram diagram;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param newElements
     *            elements to add
     * @param dDiagram
     *            the current diagram.
     */
    public ActivateBehaviorToolsCommand(TransactionalEditingDomain domain, DDiagram dDiagram, Collection<BehaviorTool> newElements) {
        super(domain, "Activate behavior tools");
        this.diagram = dDiagram;
        this.newElements = newElements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (diagram == null || newElements == null) {
            return;
        }

        for (BehaviorTool tool : newElements) {
            diagram.getActivateBehaviors().add(tool);
        }
        ConcernService.resetCurrentConcern(diagram);
    }
}
