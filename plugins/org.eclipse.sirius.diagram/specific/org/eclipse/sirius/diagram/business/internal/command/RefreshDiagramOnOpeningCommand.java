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
package org.eclipse.sirius.diagram.business.internal.command;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.DSemanticDiagram;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.display.DisplayMode;
import org.eclipse.sirius.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;

/**
 * Specific command to refresh a diagram on opening.
 * 
 * @author mporhel
 */
public final class RefreshDiagramOnOpeningCommand extends RecordingCommand {

    private final DSemanticDiagram diag;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param diag
     *            the diagram to refresh.
     */
    public RefreshDiagramOnOpeningCommand(TransactionalEditingDomain domain, DSemanticDiagram diag) {
        super(domain, "Refresh diagram on opening");
        this.diag = diag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (diag == null) {
            return;
        }

        if (diag.getActivatedFilters().size() != 0) {
            CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(diag);
            builder.computeCompositeFilterApplications();
        }

        if (DisplayMode.NORMAL.equals(DisplayServiceManager.INSTANCE.getMode())) {
            DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility(diag);
        }
        DialectManager.INSTANCE.refresh(diag, new NullProgressMonitor());
    }
}
