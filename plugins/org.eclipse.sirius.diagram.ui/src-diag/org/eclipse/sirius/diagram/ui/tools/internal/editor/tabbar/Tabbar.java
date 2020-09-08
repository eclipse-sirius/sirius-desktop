/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
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

    private AbstractTabbarFiller diagramFiller;

    private IPermissionAuthority permissionAuthority;

    private ITabbarContributorProvider tabbarContributorProvider;

    private Collection<Object> currentSelection;

    private boolean closingInProgress;

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
        this.tabbarContributorProvider = new ExtensionPointTabbarContributorProvider();
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
            Session session = editor.getSession();
            if (session != null) {
                permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(session.getSessionResource().getResourceSet());
                if (permissionAuthority != null) {
                    permissionAuthority.addAuthorityListener(this);
                }
            }
        }
    }

    private void fillForDiagram() {
        if (tabbarContributorProvider.hasContributor()) {
            diagramFiller = new TabbarFillerWithContributor(manager, page, tabbarContributorProvider);
        } else {
            diagramFiller = new TabbarFillerWithContributions(manager, page);
        }
        diagramFiller.setPart(part);
        diagramFiller.fill();
    }

    @Override
    public void selectionChanged(IWorkbenchPart partSelected, ISelection selection) {
        if (partSelected == this.part) {
            if (currentSelection == null || !sameSelection(selection)) {
                if (selection instanceof StructuredSelection) {
                    currentSelection = ((StructuredSelection) selection).toList();
                }
                reinitToolBar(selection);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private boolean sameSelection(ISelection selection) {
        if (selection instanceof StructuredSelection) {
            List newSelection = ((StructuredSelection) selection).toList();
            if (newSelection.size() == currentSelection.size()) {
                return currentSelection.containsAll(newSelection);
            }
        }
        return false;
    }

    /**
     * Reinit the tool bar for diagram.
     * 
     * @param iSelection
     *            the selection
     */
    public void reinitToolBar(ISelection iSelection) {
        if (diagramFiller != null) {
            diagramFiller.update(iSelection);

            updateAllItems();
        }
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
        EclipseUIUtil.displayAsyncExec(new Runnable() {
            @Override
            public void run() {
                if (!isClosingInProgress()) {
                    try {
                        List<IContributionItem> items = Arrays.asList(manager.getItems());
                        for (IContributionItem item : items) {
                            // The enablement update of Diagram actions encapsulated in
                            // DiagramActionContributionItem is directly performed in
                            // their item update.
                            item.update();
                        }
                    } catch (IllegalStateException e) {
                        // Nothing to log here, this can happen if the resource is not accessible anymore (distant
                        // resource).
                    }
                }
            }
        });
    }

    @Override
    public void notifyIsLocked(EObject instance) {
        updateAllItems();

    }

    @Override
    public void notifyIsReleased(EObject instance) {
        updateAllItems();
    }

    @Override
    public void notifyIsLocked(Collection<EObject> instances) {
        updateAllItems();
    }

    @Override
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

    /**
     * This method must be called only when the corresponding editor will be closed. As soon as it is called, the async
     * code of {@link #updateAllItems()} does nothing. It is no longer necessary and worth can cause problem.
     */
    public void closingInProgress() {
        closingInProgress = true;
    }

    /**
     * Check if the closing of the corresponding editor is in progress.
     * 
     * @return true is the closing of the corresponding editor is in progress, false otherwise.
     */
    protected boolean isClosingInProgress() {
        return closingInProgress;
    }

}
