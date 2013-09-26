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
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * This action shows the Eclipse properties View.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ShowPropertiesViewAction extends Action {
    private static final String DEFAULT_NAME = "Show Properties View";

    /**
     * Default constructor.
     * 
     */
    public ShowPropertiesViewAction() {
        super(DEFAULT_NAME, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.SHOW_PROPERTIES_VIEW));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.ui.views.PropertySheet");
        } catch (final PartInitException e) {
            SiriusPlugin.getDefault().error("Error while showing the Property View.", e);
        }
    }
}
