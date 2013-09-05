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
 * A simple command to reveal a view point element.
 * 
 * @author Mariot Chauvin (mchauvin)
 * @deprecated
 * @see RevealDDiagramElements
 */
@Deprecated
public class RevealSiriusElement extends RecordingCommand {

    /** The viewpoint element to reveal. */
    private DDiagramElement vpe;

    /**
     * Create a new {@link RevealSiriusElement}.
     * 
     * @param domain
     *            the editing domain.
     * @param vpe
     *            the viewpoint element.
     */
    public RevealSiriusElement(final TransactionalEditingDomain domain, final DDiagramElement vpe) {
        super(domain);
        this.vpe = vpe;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        HideFilterHelper.INSTANCE.reveal(vpe);
    }
}
