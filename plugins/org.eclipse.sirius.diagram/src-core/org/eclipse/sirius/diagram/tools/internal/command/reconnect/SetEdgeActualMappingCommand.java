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
package org.eclipse.sirius.diagram.tools.internal.command.reconnect;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.EdgeMapping;

/**
 * Specific command to set the actual mapping of an edge.
 * 
 * @author mporhel
 */
public final class SetEdgeActualMappingCommand extends RecordingCommand {

    private final DEdge edge;

    private final EdgeMapping newEdgeMapping;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param newEdgeMapping
     *            the new edge mapping
     * @param edge
     *            the DEdge to update.
     */
    public SetEdgeActualMappingCommand(TransactionalEditingDomain domain, DEdge edge, EdgeMapping newEdgeMapping) {
        super(domain, "Set edge actual mapping");
        this.edge = edge;
        this.newEdgeMapping = newEdgeMapping;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (edge == null) {
            return;
        }

        edge.setActualMapping(newEdgeMapping);
    }
}
