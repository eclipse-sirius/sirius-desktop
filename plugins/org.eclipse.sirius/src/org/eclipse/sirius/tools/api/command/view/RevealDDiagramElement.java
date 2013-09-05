/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.command.view;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.business.api.helper.graphicalfilters.HideFilterHelper;

/**
 * A simple command to reveal a diagram element.
 * 
 * @author Mariot Chauvin (mchauvin)
 * @deprecated Use {@link RevealDDiagramElements} instead.
 */
@Deprecated
public class RevealDDiagramElement extends RecordingCommand {

    /** The viewpoint element to reveal. */
    private final DDiagramElement dde;

    /**
     * Create a new {@link RevealDDiagramElement}.
     * 
     * @param domain
     *            the editing domain.
     * @param dde
     *            the diagram element.
     */
    public RevealDDiagramElement(final TransactionalEditingDomain domain, final DDiagramElement dde) {
        super(domain);
        this.dde = dde;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        HideFilterHelper.INSTANCE.reveal(dde);
    }
}
