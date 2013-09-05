/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor;

import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IWorkbenchActionConstants;

import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.EditorCreateTreeItemMenuAction;

/**
 * This is a contributor for a DTree editor.
 * 
 * @author cbrun
 */
public class DTreeActionBarContributor extends EditingDomainActionBarContributor {

    /**
     * Constructor.
     */
    public DTreeActionBarContributor() {

    }

    /**
     * Add the create tree item menu to the toolbar.
     * 
     * @param editorCreateLineMenuAction
     *            the menu to add
     */
    public void addCreateTreeItemMenu(final EditorCreateTreeItemMenuAction editorCreateLineMenuAction) {
        final IToolBarManager toolBarManager = getActionBars().getToolBarManager();
        toolBarManager.remove(EditorCreateTreeItemMenuAction.ID);
        toolBarManager.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, editorCreateLineMenuAction); //$NON-NLS-1$
        toolBarManager.update(true);
    }

}
