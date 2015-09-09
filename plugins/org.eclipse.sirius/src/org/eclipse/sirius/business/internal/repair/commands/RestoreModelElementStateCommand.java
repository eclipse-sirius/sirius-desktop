/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.repair.commands;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.sirius.business.api.repair.IRepairParticipant;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Messages;

/**
 * RestoreModelElementStateRecordingCommand.
 * 
 * @author esteban
 */
public class RestoreModelElementStateCommand extends IdentityCommand {

    private DView view;

    private List<IRepairParticipant> repairParticipants;

    private IProgressMonitor monitor;

    /**
     * Default constructor.
     * 
     * @param view
     *            the {@link DView}
     * @param repairParticipants
     *            List of {@link IRepairParticipant}
     * @param monitor
     *            a {@link IProgressMonitor}
     */
    public RestoreModelElementStateCommand(DView view, List<IRepairParticipant> repairParticipants, IProgressMonitor monitor) {
        super();
        this.view = view;
        this.repairParticipants = repairParticipants;
        this.monitor = monitor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        monitor.beginTask(Messages.RestoreModelElementStateCommand_label, repairParticipants.size());
        for (final IRepairParticipant participant : repairParticipants) {
            participant.restoreModelElementState(view, new NullProgressMonitor());
            monitor.worked(1);
        }
        monitor.done();
        repairParticipants = null;
        view = null;
    }

    /**
     * Overridden to avoid the CommandStack to keep a reference to this command.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canUndo() {
        return false;
    }
}
