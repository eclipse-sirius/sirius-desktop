/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.common.ui.tools.api.view.IExpandSelectionTarget;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.ui.tools.api.views.LockDecorationUpdater;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerTabExtension;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.filter.FilteredCommonTree;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.tab.CommonNavigatorTab;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.tab.ModelExplorerTabDescriptor;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.tab.ModelExplorerTabRegistry;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.tab.ModelExplorerTabRegistryListener;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * An Eclipse view to see the viewpoint resource set content.
 * 
 * @author mporhel
 */
public class ModelExplorerView extends CommonNavigator implements IModelExplorerView, IExpandSelectionTarget, ITabbedPropertySheetPageContributor {

    private CTabFolder tabFolder;

    private String initialSelection = ""; //$NON-NLS-1$

    private Map<CTabItem, TabInfo> tabItems = Maps.newLinkedHashMap();

    private Action deleteActionHandler;

    private Action renameActionHandler;

    /**
     * The updater in charge of refresh this view according to lock
     * notifications send to
     * {@link org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener}
     * .
     */
    private LockDecorationUpdater lockDecorationUpdater = new LockDecorationUpdater();

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(Composite aParent) {
        aParent.setLayout(new FillLayout(SWT.HORIZONTAL));
        Collection<ModelExplorerTabDescriptor> tabDescriptors = ModelExplorerTabRegistry.getRegisteredExtensions();
        if (tabDescriptors.isEmpty()) {
            // No extension : standard view
            super.createPartControl(aParent);
            hookGlobalActions();
        } else {
            // Extension tabs : new tab folder
            this.tabFolder = new CTabFolder(aParent, SWT.BORDER);
            tabFolder.setBorderVisible(false);
            tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

            tabFolder.addSelectionListener(new SelectionAdapter() {

                public void widgetSelected(SelectionEvent e) {
                    selection(e);
                }

                private void selection(SelectionEvent e) {
                    CTabItem tabItem = tabFolder.getSelection();
                    TabInfo tabInfo = tabItems.get(tabItem);
                    IModelExplorerTabExtension tab = tabInfo.tab;
                    if (tab != null) {
                        IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
                        for (final IAction action : tab.getActions()) {
                            mgr.add(action);
                        }
                        mgr.update(true);
                    }
                }

            });

            createTabs(tabDescriptors);
        }
        getCommonViewer().setComparer(new ModelExplorerItemComparer());
    }

    /**
     * {@inheritDoc}
     */
    public Object getAdapter(Class type) {
        if (type == IPropertySheetPage.class) {
            return new TabbedPropertySheetPage(this);
        }
        return super.getAdapter(type);
    }

    /**
     * TODO MCH comment.
     * 
     * @param folder
     *            the tab folder
     */
    public void createNavigatorControl(CTabFolder folder) {
        super.createPartControl(folder);
    }

    /**
     * TODO MCH comment.
     */
    public void refreshNavigatorInput() {
        getCommonViewer().setInput(getInitialInput());
    }

    private void createTabs(Collection<ModelExplorerTabDescriptor> tabDescriptors) {

        Collection<TabInfo> tabs = Sets.newLinkedHashSet();

        final CommonNavigatorTab navigatorTab = new CommonNavigatorTab(this);
        tabs.add(new TabInfo(CommonNavigatorTab.TAB_ID, navigatorTab.getImage(), navigatorTab));

        for (ModelExplorerTabDescriptor tabDescriptor : tabDescriptors) {
            IModelExplorerTabExtension tab = tabDescriptor.getTabExtension();
            Image tabImage = SiriusEditPlugin.getPlugin().getImage(tabDescriptor.getImageDescriptor());
            if (tab != null && tabImage != null) {
                TabInfo tabInfo = new TabInfo(tabDescriptor.getId(), tabImage, tab);
                tabs.add(tabInfo);
            }
        }
        createTabItems(tabs);
    }

    private void createTabItems(Collection<TabInfo> tabs) {
        for (TabInfo tabInfo : tabs) {

            final IModelExplorerTabExtension tab = tabInfo.tab;
            final Image tabImage = tabInfo.image;

            Control tabControl = tab.createTabControl(tabFolder);

            CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
            tabItem.setControl(tabControl);
            tabItem.setToolTipText(tab.getToolTipText());
            tabItem.setImage(tabImage);

            tabItems.put(tabItem, tabInfo);

            /* Try to restore previous selection. */
            if (!StringUtil.isEmpty(tabInfo.id) && tabInfo.id.equals(initialSelection)) {
                tabFolder.setSelection(tabItem);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveState(IMemento aMemento) {
        super.saveState(aMemento);
        if (tabFolder != null) {
            CTabItem selection = tabFolder.getSelection();
            if (selection != null) {
                TabInfo tabInfo = tabItems.get(selection);
                aMemento.putString(ModelExplorerTabRegistryListener.MODEL_EXPLORER_TAB_EXTENSION_POINT, tabInfo.id);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(IViewSite site) throws PartInitException {
        super.init(site);

        for (ModelExplorerTabDescriptor tabDescriptor : ModelExplorerTabRegistry.getRegisteredExtensions()) {
            IModelExplorerTabExtension tab = tabDescriptor.getTabExtension();
            if (tab != null) {
                tab.init(site);
            }
        }
        lockDecorationUpdater.register(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(IViewSite aSite, IMemento aMemento) throws PartInitException {
        super.init(aSite, aMemento);

        initialSelection = CommonNavigatorTab.TAB_ID;
        if (aMemento != null) {
            String selectedTabId = aMemento.getString(ModelExplorerTabRegistryListener.MODEL_EXPLORER_TAB_EXTENSION_POINT);
            if (!StringUtil.isEmpty(selectedTabId)) {
                initialSelection = selectedTabId;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {

        super.dispose();

        if (this.tabFolder != null) {
            this.tabFolder.dispose();
            this.tabFolder = null;
        }

        tabItems.clear();

        for (ModelExplorerTabDescriptor tabDescriptor : ModelExplorerTabRegistry.getRegisteredExtensions()) {
            IModelExplorerTabExtension tab = tabDescriptor.getTabExtension();

            if (tab != null) {
                tab.dispose();
            }
        }

        lockDecorationUpdater.unregister();
        lockDecorationUpdater = null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.view.IExpandSelectionTarget#expand(java.lang.Object)
     */
    public void expand(Object elementOrTreePath) {
        if (getCommonViewer() != null) {
            getCommonViewer().expandToLevel(elementOrTreePath, 1);
        }
    }

    /** TODO MCH comment */
    private class TabInfo {
        final String id;

        final Image image;

        final IModelExplorerTabExtension tab;

        public TabInfo(String id, Image image, IModelExplorerTabExtension tab) {
            this.id = id;
            this.image = image;
            this.tab = tab;
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.navigator.CommonNavigator#createCommonViewerObject(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected CommonViewer createCommonViewerObject(Composite parent) {
        final FilteredCommonTree commonfilteredTree = new FilteredCommonTree(getViewSite().getId(), parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, false);
        // add a dispose listener
        SWTUtil.addDisposeListener(parent, commonfilteredTree);

        final GridData gridData3 = new GridData();
        gridData3.grabExcessHorizontalSpace = true;
        gridData3.horizontalAlignment = GridData.FILL;
        gridData3.verticalAlignment = GridData.FILL;
        gridData3.grabExcessVerticalSpace = true;

        commonfilteredTree.setLayoutData(gridData3);
        /* Set model viewer providers */
        return commonfilteredTree.getViewer();
    }

    @Override
    protected void handleDoubleClick(DoubleClickEvent anEvent) {
        super.handleDoubleClick(anEvent);
        // Just after the restart of Eclipse, the listener of the
        // SiriusCommonContentProvider is not enabled so if the user
        // double-click on a ModelingProject, it is expanded but not loaded. So
        // we must load (if it's not already loaded) the main representation
        // file here.
        IStructuredSelection selection = (IStructuredSelection) anEvent.getSelection();
        Object element = selection.getFirstElement();
        if (element instanceof IProject) {
            Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject((IProject) element);
            if (optionalModelingProject.some()) {
                Option<URI> optionalMainSessionFileURI = optionalModelingProject.get().getMainRepresentationsFileURI(new NullProgressMonitor(), false, false);
                if (optionalMainSessionFileURI.some()) {
                    // Load the main representations file of this modeling
                    // project if it's not already loaded or during loading.
                    ModelingProjectManager.INSTANCE.loadAndOpenRepresentationsFile(optionalMainSessionFileURI.get());
                }
            }
        }
    }

    private void hookGlobalActions() {
        final IActionBars bars = this.getViewSite().getActionBars();
        deleteActionHandler = new DeleteActionHandler(this.getSite().getSelectionProvider());
        bars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteActionHandler);

        renameActionHandler = new RenameActionHandler(this.getSite().getSelectionProvider());
        bars.setGlobalActionHandler(ActionFactory.RENAME.getId(), renameActionHandler);

        this.getCommonViewer().getControl().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                handleKeyReleased(event);
            }
        });

        bars.updateActionBars();
    }

    private void handleKeyReleased(KeyEvent event) {
        if (event.stateMask != 0)
            return;

        int key = event.keyCode;
        if (key == SWT.DEL) {
            if (deleteActionHandler.isEnabled()) {
                deleteActionHandler.run();
            }
        } else if (key == SWT.F2) {
            if (renameActionHandler.isEnabled()) {
                renameActionHandler.run();
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    public String getContributorId() {
        return ID;
    }
}
