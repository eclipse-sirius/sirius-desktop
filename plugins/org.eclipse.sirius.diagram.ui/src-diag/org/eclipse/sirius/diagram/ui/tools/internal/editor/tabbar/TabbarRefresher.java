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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * A ResourceSet listener to refresh the tabbar icons . for layer icon . for
 * filter icon . for show/hide icon . for pin/unpin icon
 * 
 * @author nlepine
 */
public class TabbarRefresher extends ResourceSetListenerImpl {
    /**
     * Default constructor.
     * 
     * @param domain
     *            {@link TransactionalEditingDomain}
     */
    public TabbarRefresher(TransactionalEditingDomain domain) {
        super(new TabbarRefresherFilter());
        domain.addResourceSetListener(this);
    }

    @Override
    public boolean isPrecommitOnly() {
        return false;
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    /**
     * Forces a refresh of the toolbar if needed.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        reinitToolbar();
    }

    /**
     * Reinit the toolbar
     */
    private void reinitToolbar() {
        EclipseUIUtil.displayAsyncExec(new Runnable() {
            public void run() {
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null) {
                    IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                    if (activePage != null) {
                        IEditorPart activeEditor = activePage.getActiveEditor();
                        if (activeEditor instanceof DDiagramEditorImpl && ((DDiagramEditorImpl) activeEditor).getTabbar() != null) {
                            ((DDiagramEditorImpl) activeEditor).getTabbar().reinitToolBar(((DDiagramEditorImpl) activeEditor).getDiagramGraphicalViewer().getSelection());
                        }
                    }
                }
            }
        });
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
    }

}
