/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.ContextMenuFiller;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionLabelProvider;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

/**
 * Provide viewpoint actions for common navigator.
 * 
 * @author mporhel
 */
public class ManageSessionActionProvider extends CommonActionProvider {

    private ContextMenuFiller contextMenuFiller;

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
     */
    @Override
    public void init(final ICommonActionExtensionSite aSite) {
        super.init(aSite);
        contextMenuFiller = new ContextMenuFiller(aSite.getStructuredViewer(), new SessionLabelProvider(ViewHelper.INSTANCE.createAdapterFactory()));
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    @Override
    public void fillContextMenu(final IMenuManager menu) {
        super.fillContextMenu(menu);
        final ISelection selection = getContext().getSelection();
        if (selection.isEmpty()) {
            return;
        }

        // Wait the end of the loading of the representations file
        try {
            Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
        } catch (InterruptedException e) {
            // Do nothing
        }

        // Differents behavior between win and linux : windows will not
        // display contextual menu if busy cursor while dialog is shown.

        // Fill the context menu
        contextMenuFiller.fillContextMenu(menu, selection);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    @Override
    public void dispose() {
        super.dispose();

        contextMenuFiller.dispose();
        contextMenuFiller = null;
    }
}
