/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor;

import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * IPartListener to allows update of the DTree model after tree dialect editor
 * opening. Done here because it is call even with IMemento.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RefreshAtOpeningActivator implements IPartListener {

    private DTreeEditor dTreeEditor;

    /**
     * Default constructor.
     * 
     * @param dTreeEditor
     *            the {@link DTreeEditor}
     */
    public RefreshAtOpeningActivator(DTreeEditor dTreeEditor) {
        this.dTreeEditor = dTreeEditor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see IPartListener#partActivated(IWorkbenchPart)
     */
    public void partActivated(IWorkbenchPart part) {

    }

    /**
     * {@inheritDoc}
     * 
     * @see IPartListener#partBroughtToTop(IWorkbenchPart)
     */
    public void partBroughtToTop(IWorkbenchPart part) {

    }

    /**
     * {@inheritDoc}
     * 
     * @see IPartListener#partClosed(IWorkbenchPart)
     */
    public void partClosed(IWorkbenchPart part) {

    }

    /**
     * {@inheritDoc}
     * 
     * @see IPartListener#partDeactivated(IWorkbenchPart)
     */
    public void partDeactivated(IWorkbenchPart part) {

    }

    /**
     * {@inheritDoc}
     * 
     * @see IPartListener#partOpened(IWorkbenchPart)
     */
    public void partOpened(IWorkbenchPart part) {
        // Activation of the refresh of the DTree property page
        dTreeEditor.enablePropertiesUpdate(true);
        dTreeEditor.forceRefreshProperties();
    }

}
