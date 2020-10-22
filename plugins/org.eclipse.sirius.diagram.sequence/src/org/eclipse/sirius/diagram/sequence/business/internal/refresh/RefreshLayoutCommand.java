/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.diagram.sequence.Messages;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshSemanticOrderingsOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SynchronizeGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.RefreshOrderingHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ui.tools.api.profiler.SiriusTasks;

/**
 * Command to refresh the graphical layout of the whole diagram.
 * 
 * @author edugueperoux
 */
public class RefreshLayoutCommand extends RecordingCommand {

    private static final ProfilerTask REFRESH_LAYOUT = new ProfilerTask(Messages.RefreshLayoutCommand_profilerTaskCategory, Messages.RefreshLayoutCommand_profilerTaskName,
            SiriusTasks.IMAGES_VIEWPOINT);

    private Diagram diagram;

    private boolean refreshDiagram;

    /**
     * Default constructor.
     * 
     * @param diagram
     *            {@link Diagram} to refresh, used also to access {@link SequenceDDiagram} & {@link SequenceDiagram} to
     *            refresh
     * 
     * @param refreshDiagram
     *            <code>true</code> if we should actually update the GMF model
     * 
     *            {@inheritDoc}
     */
    public RefreshLayoutCommand(TransactionalEditingDomain domain, Diagram diagram, boolean refreshDiagram) {
        super(domain, Messages.RefreshLayoutCommand_commandName);
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
        DslCommonPlugin.PROFILER.startWork(REFRESH_LAYOUT);
        SequenceDiagram sequenceDiagram = ISequenceElementAccessor.getSequenceDiagram(diagram).get();
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();
        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(sequenceDDiagram);
        if (permissionAuthority != null && permissionAuthority.canEditInstance(sequenceDDiagram)) {
            sequenceDiagram.useCache(true);
            CacheHelper.clearCaches();
            CacheHelper.setStructuralCacheEnabled(true);
            CacheHelper.setVerticalRangeCacheEnabled(false);
            try {
                /*
                 * Everything has been committed, so we should be in a stable state where it is safe to refresh both
                 * orderings.
                 */

                // Compute only once (and not three times) the event ends.
                final Iterable<? extends EventEnd> allEventEnds = RefreshOrderingHelper.getAllEventEnds(sequenceDDiagram);

                AbstractModelChangeOperation<Boolean> refreshSemanticOrderingOperation = new RefreshSemanticOrderingsOperation(sequenceDDiagram) {
                    @Override
                    protected Iterable<? extends EventEnd> getAllEventEnds() {
                        return allEventEnds;
                    }
                };
                if (refreshSemanticOrderingOperation.execute()) {
                    sequenceDiagram.clearOrderedCaches();
                }
                AbstractModelChangeOperation<Boolean> refreshGraphicalOrderingOperation = new RefreshGraphicalOrderingOperation(sequenceDiagram) {
                    @Override
                    protected Iterable<? extends EventEnd> getAllEventEnds() {
                        return allEventEnds;
                    }
                };
                if (refreshGraphicalOrderingOperation.execute()) {
                    sequenceDiagram.clearOrderedCaches();
                }

                if (refreshDiagram) {
                    /*
                     * Launch a non-packing layout
                     */
                    AbstractModelChangeOperation<Boolean> synchronizeGraphicalOrderingOperation = new SynchronizeGraphicalOrderingOperation(diagram, false);
                    synchronizeGraphicalOrderingOperation.execute();
                    /*
                     * The layout has probably changed graphical positions: re-compute the ordering to make sure it is
                     * up-to-date.
                     */
                    if (refreshGraphicalOrderingOperation.execute()) {
                        sequenceDiagram.clearOrderedCaches();
                    }
                }
            } finally {
                sequenceDiagram.useCache(false);
                sequenceDiagram.clearAllCaches();

                CacheHelper.setStructuralCacheEnabled(false);
                CacheHelper.setVerticalRangeCacheEnabled(false);
                CacheHelper.clearCaches();
            }
        }
        DslCommonPlugin.PROFILER.stopWork(REFRESH_LAYOUT);
    }
}
