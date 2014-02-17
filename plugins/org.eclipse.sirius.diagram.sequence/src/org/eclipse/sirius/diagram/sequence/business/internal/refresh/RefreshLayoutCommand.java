/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.refresh;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshSemanticOrderingsOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SynchronizeGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

/**
 * Command to refresh the graphical layout of the whole diagram.
 * 
 * @author edugueperoux
 */
public class RefreshLayoutCommand extends RecordingCommand {

    private Diagram diagram;

    private boolean refreshDiagram;

    /**
     * Default constructor.
     * 
     * @param diagram
     *            {@link Diagram} to refresh, used also to access
     *            {@link SequenceDDiagram} & {@link SequenceDiagram} to refresh
     * 
     * @param refreshDiagram
     *            <code>true</code> if we should actually update the GMF model
     * 
     *            {@inheritDoc}
     */
    public RefreshLayoutCommand(TransactionalEditingDomain domain, Diagram diagram, boolean refreshDiagram) {
        super(domain, "Refresh graphical layout");
        this.diagram = diagram;
        this.refreshDiagram = refreshDiagram;
    }

    /**
     * Overridden to refresh the sequence layout.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        SequenceDiagram sequenceDiagram = ISequenceElementAccessor.getSequenceDiagram(diagram).get();
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();

        /*
         * Everything has been committed, so we should be in a stable state
         * where it is safe to refresh both orderings.
         */
        AbstractModelChangeOperation<Void> refreshSemanticOrderingOperation = new RefreshSemanticOrderingsOperation(sequenceDDiagram);
        refreshSemanticOrderingOperation.execute();
        AbstractModelChangeOperation<Void> refreshGraphicalOrderingOperation = new RefreshGraphicalOrderingOperation(sequenceDiagram);
        refreshGraphicalOrderingOperation.execute();

        if (refreshDiagram) {
            /*
             * Launch a non-packing layout
             */
            AbstractModelChangeOperation<Void> synchronizeGraphicalOrderingOperation = new SynchronizeGraphicalOrderingOperation(diagram, false);
            synchronizeGraphicalOrderingOperation.execute();
            /*
             * The layout has probably changed graphical positions: re-compute
             * the ordering to make sure it is up-to-date.
             */
            refreshGraphicalOrderingOperation.execute();
        }

    }
}
