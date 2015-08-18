/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPage;

import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableCrossEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.EditorCreateLineMenuAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.EditorCreateTargetColumnMenuAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.PrintAction;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTableViewerManager;

/**
 * This is a contributor for an DTable editor.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DTableActionBarContributor extends EditingDomainActionBarContributor {

    /* ISharedImages.IMG_ETOOL_PRINT_EDIT only since 3.4 */
    private static final String IMG_ETOOL_PRINT_EDIT = "IMG_ETOOL_PRINT_EDIT"; //$NON-NLS-1$

    private PrintAction printAction;

    @Override
    public void init(final IActionBars actionBars) {
        super.init(actionBars);
        final ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
        printAction = new PrintAction();
        printAction.setImageDescriptor(sharedImages.getImageDescriptor(IMG_ETOOL_PRINT_EDIT));
        actionBars.setGlobalActionHandler(ActionFactory.PRINT.getId(), printAction);
    }

    @Override
    public void shareGlobalActions(final IPage page, final IActionBars actionBars) {
        super.shareGlobalActions(page, actionBars);
        actionBars.setGlobalActionHandler(ActionFactory.PRINT.getId(), printAction);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        printAction.setEditor(null);
    }

    @Override
    public void activate() {
        super.activate();
        printAction.setEditor(activeEditor);
    }

    @Override
    public void contributeToToolBar(final IToolBarManager toolBarManager) {
        super.contributeToToolBar(toolBarManager);
    }

    @Override
    public void setActiveEditor(IEditorPart part) {
        boolean updateCreateMenus = part != activeEditor && part != null;
        super.setActiveEditor(part);
        if (updateCreateMenus && activeEditor instanceof AbstractDTableEditor) {
            AbstractDTableViewerManager tableViewer = ((AbstractDTableEditor) activeEditor).getTableViewer();
            if (tableViewer instanceof DTableViewerManager) {
                // Add the CreateLine menu of the toolbar
                addCreateLineMenu(((DTableViewerManager) tableViewer).getCreateLineMenu());
            }
        }
        if (activeEditor instanceof DTableCrossEditor) {
            DTableCrossEditor tableCrossEditor = (DTableCrossEditor) activeEditor;
            // Add the CreateTargetColumn menu of the toolbar
            IEditorActionBarContributor actionBarContributor = tableCrossEditor.getEditorSite().getActionBarContributor();
            if (actionBarContributor instanceof DTableActionBarContributor) {
                DTableActionBarContributor dTableActionBarContributor = (DTableActionBarContributor) actionBarContributor;
                AbstractDTableViewerManager tableViewer = tableCrossEditor.getTableViewer();
                if (tableViewer instanceof DTableViewerManager) {
                    DTableViewerManager dTableViewerManager = (DTableViewerManager) tableViewer;
                    dTableActionBarContributor.addCreateTargetColumnMenu(dTableViewerManager.getCreateTargetColumnMenu());
                }
            }
        }
    }

    /**
     * Add the create line menu to the toolbar.
     * 
     * @param editorCreateLineMenuAction
     *            the menu to add
     */
    public void addCreateLineMenu(final EditorCreateLineMenuAction editorCreateLineMenuAction) {
        final IToolBarManager toolBarManager = getActionBars().getToolBarManager();
        toolBarManager.remove(EditorCreateLineMenuAction.ID);
        toolBarManager.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, editorCreateLineMenuAction);
        toolBarManager.update(true);
    }

    /**
     * Add the create target column menu to the toolbar.
     * 
     * @param editorCreateTargetColumnMenuAction
     *            the menu to add
     */
    public void addCreateTargetColumnMenu(final EditorCreateTargetColumnMenuAction editorCreateTargetColumnMenuAction) {
        final IToolBarManager toolBarManager = getActionBars().getToolBarManager();
        toolBarManager.remove(EditorCreateTargetColumnMenuAction.ID);
        toolBarManager.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, editorCreateTargetColumnMenuAction);
        toolBarManager.update(true);
    }
}
