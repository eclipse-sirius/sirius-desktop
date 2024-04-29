/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IMenuListener2;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * .
 * 
 * @author mchauvin
 */
public abstract class AbstractMenuContributionItem extends AbstractTabbarContribution {

    /**
     * The key to use to store tooltip data in menu item.
     */
    public static final String TOOLTIP = "Tooltip"; //$NON-NLS-1$

    /**
     * the menu manager.
     */
    protected MenuManager menuManager;

    private MenuContributionItemArmListener listener;

    /**
     * List of tooltips corresponding to the tooltips of all {@link ActionContributionItem} contained in this menu.
     */
    private List<String> tooltips = new ArrayList<>();

    /**
     * Default constructor with an empty ID.
     */
    protected AbstractMenuContributionItem() {
        super();
    }

    /**
     * Default constructor with an ID.
     * 
     * @param id
     *            The id of the TabbarContribution
     */
    protected AbstractMenuContributionItem(String id) {
        super(id);
    }

    @Override
    public void create(final ToolBarManager tb, String groupId) {
        tb.insertAfter(groupId, createContributionItem(tb));
    }

    /**
     * creates a new contribution item.
     * 
     * @param tb
     *            the ToolBarManager where to create contribution item.
     * @return the contribution item.
     */
    public ContributionItem createContributionItem(final ToolBarManager tb) {
        return new TabbarContributionItem(tb);
    }

    /**
     * Get the menu image.
     * 
     * @return the image
     */
    protected abstract Image getMenuImage();

    /**
     * Get the menu label.
     * 
     * @return the label
     */
    protected abstract String getLabel();

    /**
     * add item to show in the menu in this method.
     * 
     * @param manager
     *            the menu manager in which to add menu item
     */
    protected abstract void menuShow(IMenuManager manager);

    private void showMenu(final Control control, int offset) {

        final Diagram gmfDiagram = this.part.getDiagram();
        if (gmfDiagram != null) {
            EObject diagram = gmfDiagram.getElement();
            if (diagram instanceof DDiagram) {
                setDiagram((DDiagram) diagram);
                final Menu menu = getMenuManager().createContextMenu(control);

                final MenuContributionItemArmListener oldListener = listener;
                listener = new MenuContributionItemArmListener(control);

                menu.addListener(SWT.Show, new Listener() {
                    @Override
                    public void handleEvent(Event event) {
                        final Iterator<String> it = tooltips.iterator();
                        for (final MenuItem item : menu.getItems()) {
                            if (oldListener != null) {
                                item.removeArmListener(oldListener);
                            }
                            item.addArmListener(listener);
                            if (it.hasNext()) {
                                item.setData(TOOLTIP, it.next());
                            }
                        }
                    }
                });

                menu.setLocation(control.toDisplay(0 + offset, control.getSize().y));
                menu.setVisible(true);
            }
        }
    }

    private MenuManager getMenuManager() {
        if (menuManager != null) {
            return menuManager;
        }
        menuManager = new MenuManager(null, getId());
        menuManager.setRemoveAllWhenShown(true);

        menuManager.addMenuListener(new IMenuListener2() {
            @Override
            public void menuAboutToHide(IMenuManager manager) {
                /* do nothing */
            }

            @Override
            public void menuAboutToShow(IMenuManager manager) {
                tooltips.clear();
                menuShow(manager);
            }
        });
        return menuManager;
    }

    /**
     * Add a tooltip, should be called after adding an item to the menu.
     * 
     * @param tooltip
     *            the tooltip to add
     */
    protected void addTooltip(String tooltip) {
        tooltips.add(tooltip);
    }

    /**
     * Execute the dispose.
     */
    protected void doDispose() {
        dispose();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractTabbarContribution#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (menuManager != null) {
            menuManager.dispose();
            menuManager = null;
        }
        tooltips = null;
        listener = null;
    }

    /**
     * Common contribution item for filter and layer.
     * 
     * @author fbarbin
     * 
     */
    private class TabbarContributionItem extends ContributionItem {

        private ToolItem menuItem;

        private ToolBarManager toolBarManager;

        TabbarContributionItem(ToolBarManager tb) {
            this.toolBarManager = tb;
        }

        @Override
        public void fill(final ToolBar parent, final int index) {
            menuItem = new ToolItem(parent, SWT.DROP_DOWN, index);
            menuItem.setToolTipText(getLabel());

            computeEnable();

            menuItem.setImage(getMenuImage());
            menuItem.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    final int offset = computeOffset(parent);
                    showMenu(toolBarManager.getControl(), offset);
                }

                private int computeOffset(final ToolBar tb) {
                    int offset = 0;
                    for (int i = 0; i < index; i++) {
                        offset += tb.getItem(i).getWidth();
                    }
                    return offset;
                }
            });
        }

        private void computeEnable() {
            if (part instanceof DDiagramEditor && ((DDiagramEditor) part).getRepresentation() instanceof DDiagram) {
                boolean canEditInstance = true;
                final DDiagramEditor editor = (DDiagramEditor) part;
                final DDiagram editorDiagram = (DDiagram) editor.getRepresentation();
                IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(editor.getSession().getSessionResource().getResourceSet());
                canEditInstance = permissionAuthority.canEditInstance(editorDiagram);
                menuItem.setEnabled(canEditInstance);
            }
        }

        @Override
        public void dispose() {
            doDispose();
            if (menuItem != null) {
                menuItem.dispose();
                menuItem = null;
            }
            toolBarManager = null;
            super.dispose();
        }

        @Override
        public void update() {
            super.update();
            if (menuItem != null && !menuItem.isDisposed()) {
                menuItem.setImage(getMenuImage());
                computeEnable();
            }
        }
    };

}
