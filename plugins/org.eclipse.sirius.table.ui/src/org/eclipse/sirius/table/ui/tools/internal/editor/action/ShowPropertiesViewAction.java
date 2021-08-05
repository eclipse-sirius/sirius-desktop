/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import org.eclipse.jface.action.Action;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * This action shows the Eclipse properties View.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ShowPropertiesViewAction extends Action {
    /**
     * Default constructor.
     *
     */
    public ShowPropertiesViewAction() {
        super(Messages.ShowPropertiesViewAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.SHOW_PROPERTIES_VIEW));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.ui.views.PropertySheet"); //$NON-NLS-1$
        } catch (final PartInitException e) {
            SiriusPlugin.getDefault().error(Messages.ShowPropertiesViewAction_error, e);
        }
    }
}
