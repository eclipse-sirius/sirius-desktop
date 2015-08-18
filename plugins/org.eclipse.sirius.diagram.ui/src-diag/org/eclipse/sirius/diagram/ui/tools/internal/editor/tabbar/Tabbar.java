/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * A beautiful tab bar.
 * 
 * @author mchauvin
 */
public class Tabbar extends Composite implements ISelectionListener, IAuthorityListener {

    /**
     * Tabbar id used to look for extensions through the "org.eclipse.ui.menus"
     * extension point with toolbar scheme.
     */
    public static final String TABBAR_ID = "org.eclipse.sirius.diagram.ui.tabbar"; //$NON-NLS-1$

    private IDiagramWorkbenchPart part;

    private IWorkbenchPage page;

    private ToolBar toolBar;

    private ToolBarManager manager;

    private TabbarFiller diagramFiller;

    private IPermissionAuthority permissionAuthority;

    /**
     * Instantiate a new tab bar.
     * 
     * @param parent
     *            the parent composite
     * @param part
     *            the diagram workbench part
     */
    public Tabbar(Composite parent, final IDiagramWorkbenchPart part) {
        super(parent, SWT.None);
        setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        this.part = part;
        this.page = part.getSite().getPage();
        GridLayout layout = new GridLayout(1, true);
        layout.verticalSpacing = 0;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        this.setLayout(layout);
        createToolBar();
        fillForDiagram();
    }

    /**
     * Get the tool bar manager.
     * 
     * @return the tool bar manager
     */
    public IToolBarManager getToolBarManager() {
        return manager;
    }

    private void createToolBar() {
        toolBar = new ToolBar(this, SWT.FLAT);
        toolBar.setBackgroundMode(SWT.INHERIT_DEFAULT);
        toolBar.setLayoutData(new org.eclipse.swt.layout.GridData(org.eclipse.swt.layout.GridData.FILL_HORIZONTAL));

        manager = new TabbarToolBarManager(toolBar, part);
        setPermissionAuthorityListener();
    }

    private void setPermissionAuthorityListener() {
        if (part instanceof DDiagramEditor) {
            DDiagramEditor editor = (DDiagramEditor) part;
            permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(editor.getSession().getSessionResource().getResourceSet());
            if (permissionAuthority != null) {
                permissionAuthority.addAuthorityListener(this);
            }
        }
    }

    private void fillForDiagram() {
        if (canBeDynamic()) {
            diagramFiller = new TabbarFillerWithContributions(manager, page);
        } else {
            diagramFiller = new TabbarFillerWithoutContributions(manager, page);
        }
        diagramFiller.setPart(part);
        diagramFiller.fill();
    }

    /**
     * Indicates if the tabbar can be dynamic (if the workbench version supports
     * it). Issues exist with visibleWhen and contributions in Juno and Kepler.
     * 
     * @return true if the tabbar can be dynamic.
     */
    public static boolean canBeDynamic() {
        boolean canBeDynamic = false;

        // The check is done on org.eclipse.ui.workbench and not on
        // org.eclipse.core.runtime to be able to differentiate juno3 and juno
        // (both have 3.8 as version on the org.eclipse.core.runtime plugin).
        Bundle uiWorkbenchBundle = Platform.getBundle("org.eclipse.ui.workbench"); //$NON-NLS-1$
        if (uiWorkbenchBundle != null) {
            Version junoStart = Version.parseVersion("3.103"); //$NON-NLS-1$
            Version lunaStart = Version.parseVersion("3.106"); //$NON-NLS-1$
            Version currentVersion = uiWorkbenchBundle.getVersion();

            // Range must not be in [3.103..3.106)
            canBeDynamic = currentVersion.compareTo(junoStart) < 0 || currentVersion.compareTo(lunaStart) >= 0;
        }
        return canBeDynamic;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IWorkbenchPart partSelected, ISelection selection) {
        // nothing to do here. Each item contribution is now responsible for
        // refresh himself when selection change.
    }

    /**
     * Reinit the tool bar for diagram.
     * 
     * @param iSelection
     *            the selection
     */
    public void reinitToolBar(ISelection iSelection) {
        updateAllItems();
    }

    /**
     * Dispose this tab bar.
     */
    @Override
    public void dispose() {
        unSetPermissionAuthorityListener();

        if (diagramFiller != null) {
            diagramFiller.dispose();
            diagramFiller = null;
        }

        // Dispose before remove all, else the contribution items will not be
        // disposed.
        manager.dispose();
        manager.removeAll();

        part = null;
        page = null;
        toolBar.dispose();
        toolBar = null;
    }

    private void unSetPermissionAuthorityListener() {
        permissionAuthority.removeAuthorityListener(this);
        permissionAuthority = null;
    }

    private void updateAllItems() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                List<IContributionItem> items = Arrays.asList(manager.getItems());
                for (IContributionItem item : items) {
                    // The enablement update of Diagram actions encapsulated in
                    // DiagramActionContributionItem is directly performed in
                    // their item update.
                    item.update();
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void notifyIsLocked(EObject instance) {
        updateAllItems();

    }

    /**
     * {@inheritDoc}
     */
    public void notifyIsReleased(EObject instance) {
        updateAllItems();
    }

    /**
     * {@inheritDoc}
     */
    public void notifyIsLocked(Collection<EObject> instances) {
        updateAllItems();
    }

    /**
     * {@inheritDoc}
     */
    public void notifyIsReleased(Collection<EObject> instances) {
        updateAllItems();
    }

    /**
     * The Tabbar should not take the keyboard focus or let one of its
     * contributions take it (Zoom combo during editor part activation for
     * example). The contribution can have the focus when the user explicitly
     * select/enter their control.
     * 
     * @see org.eclipse.swt.widgets.Control#setFocus()
     * @return false the Tabbar will never intercepts the keyboard focus or ask
     *         its contributions to intercepts it.
     */
    @Override
    public boolean setFocus() {
        return false;
    }
}
