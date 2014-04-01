/*******************************************************************************
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gmf.runtime.diagram.ui.internal.l10n.DiagramUIPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarZoomAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarZoomInAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarZoomOutAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions.DDiagramTabbarExpression;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

/**
 * 
 * ExtensionContributionFactory responsible for Zoom actions tabbar item
 * creation.
 * 
 * @author fbarbin
 */
public class ZoomExtensionContributionFactory extends SiriusTabbarExtensionContributionFactory {

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {

        super.createContributionItems(serviceLocator, additions);

        addZoomInZoomOut(additions);

        ZoomComboContributionItem zoomItem = new ZoomComboContributionItem(getPage()) {

            @Override
            public void fill(ToolBar parent, int index) {
                super.fill(parent, index);
                ToolItem addedItem = parent.getItem(parent.getItemCount() - 1);
                addedItem.setToolTipText(DiagramUIMessages.ZoomActionMenu_ZoomLabel);
            }

        };
        additions.addContributionItem(zoomItem, new DDiagramTabbarExpression());
    }

    private void addZoomInZoomOut(IContributionRoot additions) {

        TabbarZoomAction zoomIn = new TabbarZoomInAction();
        zoomIn.setImageDescriptor(DiagramUIPluginImages.DESC_ZOOM_IN);
        zoomIn.setText(DiagramUIMessages.ZoomAction_ZoomIn);
        TabbarZoomAction zoomOut = new TabbarZoomOutAction();
        zoomOut.setImageDescriptor(DiagramUIPluginImages.DESC_ZOOM_OUT);
        zoomIn.setText(DiagramUIMessages.ZoomAction_ZoomOut);

        additions.addContributionItem(new ZoomContributionItem(zoomIn, getPage()), new DDiagramTabbarExpression());
        additions.addContributionItem(new ZoomContributionItem(zoomOut, getPage()), new DDiagramTabbarExpression());

    }

    /**
     * ActionContributionItem implementation for zoom actions. Create a part
     * listener to set zoom manager.
     * 
     * @author fbarbin
     * 
     */
    public static class ZoomContributionItem extends ActionContributionItem {
        private IPartListener listener;

        private IWorkbenchPage page;

        /**
         * Constructor.
         * 
         * @param action
         *            The action to wrap
         * @param page
         *            the workbench page
         */
        public ZoomContributionItem(final IAction action, IWorkbenchPage page) {
            super(action);
            this.page = page;
            listener = new IPartListener() {
                public void partActivated(IWorkbenchPart part) {
                    if (part instanceof DDiagramEditor) {
                        final ZoomManager zoomManager = (ZoomManager) part.getAdapter(ZoomManager.class);
                        if (action instanceof TabbarZoomAction) {
                            ((TabbarZoomAction) action).setZoomManager(zoomManager);
                        }
                    }
                }

                public void partBroughtToTop(IWorkbenchPart p) {
                }

                public void partClosed(IWorkbenchPart p) {
                }

                public void partDeactivated(IWorkbenchPart p) {
                }

                public void partOpened(IWorkbenchPart p) {
                }
            };
            page.addPartListener(listener);
        }

        @Override
        public void dispose() {
            super.dispose();
            if (page != null) {
                page.removePartListener(listener);
                page = null;
            }
            listener = null;
        }
    }

}
