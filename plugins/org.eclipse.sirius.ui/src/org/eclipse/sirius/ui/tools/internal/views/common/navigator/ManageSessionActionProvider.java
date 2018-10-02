/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
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
        initFromViewer(aSite.getStructuredViewer());
    }

    /**
     * Initialize the {@link ContextMenuFiller} directly on the given {@link StructuredViewer}.
     * 
     * @param structuredViewer
     *            the viewer target of the {@link ContextMenuFiller} initialization.
     */
    public void initFromViewer(final StructuredViewer structuredViewer) {
        contextMenuFiller = new ContextMenuFiller(structuredViewer, new SessionLabelProvider(ViewHelper.INSTANCE.createAdapterFactory()));
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
