/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.internal.command.reconnect;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.tools.internal.Messages;

/**
 * Specific command to set the actual mapping of an edge.
 * 
 * @author mporhel
 */
public final class SetEdgeActualMappingCommand extends RecordingCommand {

    private final DEdge edge;

    private final IEdgeMapping newEdgeMapping;

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
    public SetEdgeActualMappingCommand(TransactionalEditingDomain domain, DEdge edge, IEdgeMapping newEdgeMapping) {
        super(domain, Messages.SetEdgeActualMappingCommand_commandLabel);
        this.edge = edge;
        if (newEdgeMapping instanceof EdgeMappingImportWrapper) {
            this.newEdgeMapping = ((EdgeMappingImportWrapper) newEdgeMapping).getWrappedEdgeMappingImport();
        } else {
            this.newEdgeMapping = newEdgeMapping;
        }
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
