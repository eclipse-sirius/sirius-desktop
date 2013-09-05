/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * RemoveDiagramElementsRecordingCommand.
 * 
 * @author esteban
 */
public class RemoveDiagramElementsCommand extends IdentityCommand {

    private IProgressMonitor monitor;

    private List<EObject> toBeRemoved;

    /**
     * Default constructor.
     * 
     * @param monitor
     *            {@link IProgressMonitor}
     * @param toBeRemoved
     *            toBeRemoved
     */
    public RemoveDiagramElementsCommand(IProgressMonitor monitor, List<EObject> toBeRemoved) {
        super();
        this.monitor = monitor;
        this.toBeRemoved = toBeRemoved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        for (int i = 0; i < toBeRemoved.size(); i++) {
            EcoreUtil.remove(toBeRemoved.get(i));
        }
        monitor.done();
        toBeRemoved = null;
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
