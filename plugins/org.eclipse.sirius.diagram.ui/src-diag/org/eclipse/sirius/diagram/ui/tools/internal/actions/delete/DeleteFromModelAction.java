/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.delete;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * Delete a {@link org.eclipse.sirius.diagram.DDiagramElement}.
 * 
 * @author cnotot
 * 
 */
public class DeleteFromModelAction extends Action {

    /**
     * The action to delegate.
     */
    protected DeleteFromModelWithHookAction action;

    /**
     * Constructor only for tests.
     */
    public DeleteFromModelAction() {
        super(""); //$NON-NLS-1$
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param toolTipText
     *            the tool tip text, or <code>null</code> if none
     * @param id
     *            the action id
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     */
    public DeleteFromModelAction(final String text, final String toolTipText, final String id, final ImageDescriptor image) {
        super(text, image);
        setId(id);
        setToolTipText(toolTipText);
    }

    /**
     * {@inheritDoc} Used from button.
     * 
     * @see org.eclipse.jface.action#run(org.eclipse.jface.action)
     */
    @Override
    public void run() {
        if (action == null) {
            final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            action = new DeleteFromModelWithHookAction(page);
            action.init();
        }

        action.doRun(new NullProgressMonitor());
    }
}
