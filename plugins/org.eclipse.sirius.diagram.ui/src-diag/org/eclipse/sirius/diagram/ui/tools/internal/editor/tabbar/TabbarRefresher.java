/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import java.util.Optional;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
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

    /**
     * Reinit the toolbar.
     */
    public static void reinitToolbar() {
        EclipseUIUtil.displayAsyncExec(new Runnable() {
            @Override
            public void run() {
                //@formatter:off
                Optional<DDiagramEditorImpl> optionalDDiagramEditor = Optional.ofNullable(PlatformUI.getWorkbench().getActiveWorkbenchWindow())
                        .map(IWorkbenchWindow::getActivePage)
                        .map(IWorkbenchPage::getActiveEditor)
                        .filter(DDiagramEditorImpl.class::isInstance)
                        .map(DDiagramEditorImpl.class::cast)
                        .filter(dDiagramEditor -> dDiagramEditor.getTabbar() != null)
                        .filter(dDiagramEditor -> {
                            Session session = dDiagramEditor.getSession();
                            return session != null && session.isOpen();
                        });
                //@formatter:on
                if (optionalDDiagramEditor.isPresent()) {
                    optionalDDiagramEditor.get().getTabbar().reinitToolBar(optionalDDiagramEditor.get().getDiagramGraphicalViewer().getSelection());
                }
            }
        });
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
        TabbarRefresher.reinitToolbar();
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
