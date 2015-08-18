/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.tab;

import java.util.Collection;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerTabExtension;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.DeleteActionHandler;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.ModelExplorerView;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.actions.ActionFactory;

import com.google.common.collect.Sets;

/**
 * TODO MCH comment here.
 * 
 * @author mchauvin
 */
public class CommonNavigatorTab implements IModelExplorerTabExtension {

    /** the id of the tab. */
    public static final String TAB_ID = "navigator"; //$NON-NLS-1$

    private static final String MODEL_EXPLORER_STRUCTURE_TAB_NAME = "Navigator";

    private static final String MODEL_EXPLORER_STRUCTURE_TAB_ICON = "icons/full/others/structure.gif"; //$NON-NLS-1$

    private ModelExplorerView view;

    private Action deleteActionHandler;

    /**
     * Create a new instance, this is the single extension for which we will
     * have a constructor with a parameter.
     * 
     * @param view
     *            the main view
     */
    public CommonNavigatorTab(ModelExplorerView view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    public void init(IViewSite site) {
        /* do nothing */
    }

    /**
     * {@inheritDoc}
     */
    public Control createTabControl(CTabFolder tabFolder) {
        view.createNavigatorControl(tabFolder);
        hookGlobalActions();

        // Look for created top control for navigator control
        // (FilteredCommonTree for example)
        Control control = view.getCommonViewer().getControl();
        while (control != null && control.getParent() != tabFolder) {
            control = control.getParent();
        }
        return control;
    }

    private void hookGlobalActions() {
        final IActionBars bars = view.getViewSite().getActionBars();
        deleteActionHandler = new DeleteActionHandler(view.getSite().getSelectionProvider());
        bars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteActionHandler);

        view.getCommonViewer().getControl().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if (event.character == SWT.DEL && event.stateMask == 0 && deleteActionHandler.isEnabled()) {
                    deleteActionHandler.run();
                }
            }
        });

        bars.updateActionBars();
    }

    /**
     * {@inheritDoc}
     */
    public String getToolTipText() {
        return MODEL_EXPLORER_STRUCTURE_TAB_NAME;
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IAction> getActions() {
        Collection<IAction> actions = Sets.newLinkedHashSet();

        // IAction testAction = new Action("Enable project mode",
        // IAction.AS_CHECK_BOX) {
        // public void run() {
        // if
        // (CommonNavigatorTab.MODE_MANAGER.getMode().equals(NavigatorMode.RESOURCE))
        // CommonNavigatorTab.MODE_MANAGER.setMode(NavigatorMode.PROJECT);
        // else
        // CommonNavigatorTab.MODE_MANAGER.setMode(NavigatorMode.RESOURCE);
        // view.refreshNavigatorInput();
        // }
        // };
        // testAction.setImageDescriptor(SiriusEditPlugin.Implementation.getBundledImageDescriptor(PROJECT_MODE_ACTIVATION));
        // actions.add(testAction);

        return actions;
    }

    /**
     * Get the associated image.
     * 
     * @return the tab image
     */
    public Image getImage() {
        return SiriusEditPlugin.getPlugin().getBundledImage(MODEL_EXPLORER_STRUCTURE_TAB_ICON);
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        view = null;
    }

}
