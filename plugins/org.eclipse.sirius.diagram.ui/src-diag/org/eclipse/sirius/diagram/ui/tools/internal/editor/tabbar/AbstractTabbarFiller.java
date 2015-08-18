/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.menus.IMenuService;

/**
 * Default filler to subclass.
 * 
 * @author mchauvin
 */
public abstract class AbstractTabbarFiller implements TabbarFiller {

    /** the tool bar manager. */
    protected ToolBarManager manager;

    /** the workbench page. */
    protected IWorkbenchPage page;

    /** the diagram workbench part. */
    protected IDiagramWorkbenchPart part;

    private boolean disposed;

    /**
     * Construct a new instance.
     * 
     * @param manager
     *            the toolbar manager
     * @param page
     *            the workbench page
     */
    public AbstractTabbarFiller(ToolBarManager manager, IWorkbenchPage page) {
        this.manager = manager;
        this.page = page;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.TabbarFiller#setPart(org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart)
     */
    public void setPart(IDiagramWorkbenchPart workbenchPart) {
        this.part = workbenchPart;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.TabbarFiller#fill()
     */
    public void fill() {
        if (!isDisposed()) {
            doFill();
            manager.update(true);
        }
    }

    /**
     * Subclass should implement this method to fill the manager with their
     * contributions.
     */
    protected abstract void doFill();

    /**
     * Add a separator to the tab bar.
     */
    protected void addSeparator() {
        manager.add(new Separator());
    }

    /**
     * Add a separator to the tab bar.
     * 
     * @param groupName
     *            the group name of the separator
     */
    protected void addSeparator(String groupName) {
        if (StringUtil.isEmpty(groupName)) {
            addSeparator();
        } else {
            manager.add(new Separator(groupName));
        }
    }

    /**
     * Add the additions group and ask the menu service to populate the tabbar
     * with contributions regarding the toolbar scheme with
     * {@link Tabbar#TABBAR_ID} id.
     * 
     * DO NOT forget to call releaseTabbarContributions in dispose method.
     */
    protected void addTabbarContributions() {
        addSeparator("additions"); //$NON-NLS-1$
        ((ToolBarContextService) IToolBarContextService.INSTANCE).setMenuManager(manager);
        ((ToolBarContextService) IToolBarContextService.INSTANCE).setPage(page);
        ((ToolBarContextService) IToolBarContextService.INSTANCE).setPart(part);

        // Get the menu service corresponding to the current site (and not the
        // current workbench window).
        IMenuService menuService = (IMenuService) part.getSite().getService(IMenuService.class);
        menuService.populateContributionManager(manager, "toolbar:" + Tabbar.TABBAR_ID); //$NON-NLS-1$

        IToolBarContextService.INSTANCE.dispose();
    }

    /**
     * Release the contributions.
     * 
     * @see AbstractTabbarFiller#addTabbarContributions().
     */
    protected void releaseTabbarContributions() {
        // Get the menu service corresponding to the current site (and not the
        // current workbench window).
        IMenuService menuService = (IMenuService) part.getSite().getService(IMenuService.class);
        menuService.releaseContributions(manager);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.TabbarFiller#dispose()
     */
    public void dispose() {
        this.manager = null;
        this.page = null;
        this.part = null;
        this.disposed = true;
    }

    /**
     * Returns <code>true</code> if the tabbarFiller has been disposed, and
     * <code>false</code> otherwise.
     * <p>
     * This method gets the dispose state for the widget. When a widget has been
     * disposed, it is an error to invoke any other method (except
     * {@link #dispose()}) using the widget.
     * </p>
     * 
     * @return <code>true</code> when the tabbarFiller is disposed and
     *         <code>false</code> otherwise
     */
    public boolean isDisposed() {
        return disposed;
    }
}
