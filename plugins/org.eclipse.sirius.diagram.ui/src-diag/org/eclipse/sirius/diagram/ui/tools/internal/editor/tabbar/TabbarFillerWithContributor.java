/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.ui.tools.api.editor.tabbar.ITabbarContributor;
import org.eclipse.ui.IWorkbenchPage;

/**
 * A Tabbar Filler that will retrieve contribution items from Tabbar
 * Contributors.
 * 
 * @author Florian Barbin
 *
 */
public class TabbarFillerWithContributor extends AbstractTabbarFiller {

    private ITabbarContributorProvider tabbarContributorProvider;

    /**
     * Default Constructor.
     * 
     * @param manager
     *            the {@link ToolBarManager}.
     * @param page
     *            the current {@link IWorkbenchPage}.
     * @param tabbarContributorProvider
     *            the tabbar contributor provider.
     */
    public TabbarFillerWithContributor(ToolBarManager manager, IWorkbenchPage page, ITabbarContributorProvider tabbarContributorProvider) {
        super(manager, page);
        this.tabbarContributorProvider = tabbarContributorProvider;
    }

    @Override
    protected void doFill() {
        ITabbarContributor contributor = this.tabbarContributorProvider.getContributor();
        if (contributor != null) {
            List<IContributionItem> contributionItems = contributor.getContributionItems(part, manager);
            for (IContributionItem item : contributionItems) {
                manager.add(item);
            }
        }
    }

    /**
     * Updates the tabbar according to the given selection.
     * 
     * @param iSelection
     *            the current selection.
     */
    public void update(ISelection iSelection) {
        ITabbarContributor contributor = this.tabbarContributorProvider.getContributor(iSelection);
        List<IContributionItem> existingItems = Arrays.asList(manager.getItems());
        for (IContributionItem current : existingItems) {
            current.setVisible(false);
        }
        if (contributor != null) {
            List<IContributionItem> contributionItems = contributor.getContributionItems(iSelection, part, manager);
            for (IContributionItem item : contributionItems) {
                if (existingItems.contains(item)) {
                    item.setVisible(true);
                } else {
                    manager.add(item);
                }
            }
        }
        manager.update(true);
    }

    @Override
    public void dispose() {
        tabbarContributorProvider = null;
        manager.dispose();
        super.dispose();
    }

}
