/*******************************************************************************
 * Copyright (c) 2008, 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.sessionview;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;

import com.google.common.collect.Lists;

import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener2;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.ui.tools.internal.views.common.ContextMenuFiller;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionLabelProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonContentProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.filter.RepresentationDescriptionWithoutRepresentationCommonFilter;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * An Eclipse view to see the viewpoint resource set content.
 * 
 * @author cbrun
 */
public class DesignerSessionView extends ViewPart implements SessionManagerListener2, SessionListener, ISelectionProvider, ISelectionChangedListener {

    private static final Transfer TRANSFER = LocalSelectionTransfer.getTransfer();

    /** This is the menu that will be displayed on a right click on sessions. */
    protected MenuManager contextMenu;

    private AdapterFactoryLabelProvider labelProvider;

    private Composite top;

    private FilteredTree modelTree;

    private TreeViewer modelViewer;

    private AdapterFactory factory;

    private final Set<ISelectionChangedListener> listeners = new HashSet<ISelectionChangedListener>(1);

    private OperationHistoryActionHandler undoActionHandler;

    private OperationHistoryActionHandler redoActionHandler;

    private Session currentlySelectedSession;

    private Composite parentComposite;

    private ContextMenuFiller contextMenuFiller;

    /**
     * Default constructor.
     */
    public DesignerSessionView() {
        super();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        this.parentComposite = parent;

        final GridData gridData7 = new GridData();
        gridData7.horizontalAlignment = GridData.FILL;
        gridData7.grabExcessHorizontalSpace = true;
        gridData7.horizontalSpan = 2;
        gridData7.verticalAlignment = GridData.CENTER;
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        top = new Composite(parent, SWT.NONE);
        top.setLayout(gridLayout);
        createModelForm(top);

        modelViewer.setInput(Lists.newArrayList(SessionManager.INSTANCE.getSessions()));
        modelViewer.addFilter(new RepresentationDescriptionWithoutRepresentationCommonFilter());
        getSite().setSelectionProvider(modelViewer);

        createContextMenuFor(modelViewer);

        SessionManager.INSTANCE.addSessionsListener(this);
        getSite().setSelectionProvider(this);

        modelViewer.addSelectionChangedListener(this);

        hookGlobalActions();
    }

    public Control getControl() {
        return top;
    }

    private void hookGlobalActions() {
        IActionBars bars = getViewSite().getActionBars();
        undoActionHandler = new UndoActionHandler(getSite(), this);
        redoActionHandler = new RedoActionHandler(getSite(), this);
        bars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoActionHandler);
        bars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoActionHandler);
        bars.updateActionBars();
    }

    private void createContextMenuFor(final StructuredViewer viewer) {
        if (contextMenu != null) {
            safeClearContextMenu();
        }
        contextMenu = new MenuManager("#PopUp");
        contextMenu.setRemoveAllWhenShown(true);
        contextMenuFiller = new ContextMenuFiller(viewer, getLabelProvider());
        contextMenu.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(final IMenuManager mgr) {
                safeClearContextMenu();

                /*
                 * addition group must be added each time
                 * because of contextMenu.setRemoveAllWhenShown(true);
                 */
                contextMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

                contextMenuFiller.fillContextMenu(contextMenu, viewer.getSelection());

            }
        });
        final Menu menu = contextMenu.createContextMenu(viewer.getControl());

        viewer.getControl().setMenu(menu);
        getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));

        /* Configure viewer drag and drop behavior */
        final int ops = DND.DROP_COPY | DND.DROP_MOVE;
        final Transfer[] transfers = new Transfer[] { TRANSFER };
        viewer.addDropSupport(ops, transfers, new ModelDropTargetAdapter(viewer));

        final int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
        // final Transfer[] transfers = new Transfer[] {
        // LocalTransfer.getInstance() };
        viewer.addDragSupport(dndOperations, transfers, new ModelDragTargetAdapter(viewer));
    }

    /**
     * Clear the context menu. Could be called safely from non UI Thread.
     */
    private void safeClearContextMenu() {
        if (Display.getCurrent() != null) {
            contextMenu.removeAll();
            contextMenu.updateAll(true);
        } else {
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    contextMenu.removeAll();
                    contextMenu.updateAll(true);
                }
            });
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        if (this.parentComposite != null) {
            this.parentComposite.setFocus();
        }
    }

    /**
     * This method initializes interpreterForm
     * 
     * @param parent
     * 
     */
    private void createModelForm(final Composite parent) {
        modelTree = SWTUtil.createFilteredTree(parent, SWT.NONE | SWT.BORDER | SWT.MULTI, new PatternFilter());

        final GridData gridData3 = new GridData();
        gridData3.grabExcessHorizontalSpace = true;
        gridData3.horizontalAlignment = GridData.FILL;
        gridData3.verticalAlignment = GridData.FILL;
        gridData3.grabExcessVerticalSpace = true;

        modelTree.setLayoutData(gridData3);
        /* Set model viewer providers */
        modelViewer = modelTree.getViewer();
        modelViewer.setContentProvider(new SiriusCommonContentProvider());
        modelViewer.setLabelProvider(new SiriusCommonLabelProvider());

        /* Set model viewer listeners */
        modelViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(final SelectionChangedEvent event) {
                fireSelectionChanged();
            }
        });
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionListener#notify(int)
     */
    public void notify(final int changeKind) {
        switch (changeKind) {
        case SessionListener.REPRESENTATION_CHANGE:
        case SessionListener.SEMANTIC_CHANGE:
        case SessionListener.SELECTED_VIEWS_CHANGE_KIND:
        case SessionListener.VSM_UPDATED:
        case SessionListener.REPLACED:
            updateViewer();
            break;
        case SessionListener.SYNC:
        case SessionListener.CLOSED:
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    setSyncPartName();
                }

            });
            if (changeKind != SessionListener.SYNC) {
                currentlySelectedSession = null;
            }
            // No update viewer here, because it can cause problem with async
            // exec refresh of the viewer. The updateViewer will be done in
            // notifyRemoveSession
            break;
        case SessionListener.DIRTY:
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    setDirtyPartName();
                }

            });
            break;
        default:
            break;
        }
    }

    private void setSyncPartName() {
        IConfigurationElement configElement = this.getConfigurationElement();
        if (configElement != null) {
            final String name = configElement.getAttribute("name");
            setPartName(name);
        }
    }

    private void setDirtyPartName() {
        IConfigurationElement configElement = this.getConfigurationElement();
        if (configElement != null) {
            final String name = configElement.getAttribute("name");
            setPartName("*" + name);
        }
    }

    private void updateViewer() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new DesignerSessionViewUpdateRunnable(modelViewer));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyAddSession(org.eclipse.sirius.business.api.session.Session)
     */
    public void notifyAddSession(final Session newSession) {
        newSession.addListener(this);
        updateViewer();
        /*
         * Creates UI Session to listen the dirty state.
         */
        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(newSession);
        if (uiSession != null) {
            uiSession.open();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(org.eclipse.sirius.business.api.session.Session)
     */
    public void notifyRemoveSession(final Session removedSession) {
        // FIXME : the close action call insternalClose() by Session.close()
        // call indirectly internalClose by SessionManager notification.
        if (!removedSession.isOpen()) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    // internalClose(removedSession);
                }
            });

            removedSession.removeListener(this);
            currentlySelectedSession = null;
            updateViewer();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyUpdatedSession(org.eclipse.sirius.business.api.session.Session)
     */
    @Deprecated
    public void notifyUpdatedSession(final Session updated) {
        // do nothing as this method is deprecated
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener2#notify(org.eclipse.sirius.business.api.session.Session,
     *      int)
     */
    public void notify(final Session updated, final int notification) {
        switch (notification) {
        case SessionListener.REPRESENTATION_CHANGE:
            updateViewer();
            break;
        default:
            // do nothing as we will be notified in other way
            break;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(org.eclipse.sirius.viewpoint.description.Viewpoint)
     */
    public void viewpointDeselected(final Viewpoint deselectedSirius) {
        // does nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(org.eclipse.sirius.viewpoint.description.Viewpoint)
     */
    public void viewpointSelected(final Viewpoint selectedSirius) {
        // does nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
     */
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        this.listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
     */
    public ISelection getSelection() {
        return this.modelViewer.getSelection();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
     */
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    public void setSelection(final ISelection selection) {
        this.modelViewer.setSelection(selection);
    }

    /**
     * Update the UIs elements according to Session selection :
     * <ol>
     * <li>The Dirty status indication</li>
     * <li>The Undo/Redo command</li>.
     * </ol>
     * 
     * @param event
     *            a {@link SelectionChangedEvent} with the new
     *            {@link ISelection}
     */
    public void selectionChanged(SelectionChangedEvent event) {
        Session newlySelectedSession = null;
        if (event.getSelection() instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            if (selection.getFirstElement() instanceof EObject) {
                EObject eObject = (EObject) selection.getFirstElement();
                if (eObject instanceof Session) {
                    newlySelectedSession = (Session) eObject;
                } else {
                    newlySelectedSession = SessionManager.INSTANCE.getSession(eObject);
                }
            } else if (selection.getFirstElement() instanceof ItemWrapper) {
                ItemWrapper itemWrapper = (ItemWrapper) selection.getFirstElement();
                newlySelectedSession = itemWrapper.getSession().get();
            }
            if (newlySelectedSession != null && currentlySelectedSession != newlySelectedSession && getConfigurationElement() != null) {
                currentlySelectedSession = newlySelectedSession;
                SessionStatus sessionStatus = currentlySelectedSession.getStatus();
                if (sessionStatus == SessionStatus.DIRTY) {
                    setDirtyPartName();
                } else if (sessionStatus == SessionStatus.SYNC) {
                    setSyncPartName();
                }
                undoActionHandler.update();
                redoActionHandler.update();
            }
        }
    }

    private void fireSelectionChanged() {
        final SelectionChangedEvent event = new SelectionChangedEvent(this, getSelection());
        for (final ISelectionChangedListener listener : this.listeners) {
            listener.selectionChanged(event);
        }
    }

    private AdapterFactoryLabelProvider getLabelProvider() {
        if (labelProvider == null) {
            labelProvider = new SessionLabelProvider(getFactory());
        }
        return labelProvider;
    }

    /**
     * Get the current Session selection of this view.
     * 
     * @return the current Session selection of this view
     */
    public Session getCurrentlySelectedSession() {
        return currentlySelectedSession;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#dispose()
     */
    @Override
    public void dispose() {
        listeners.clear();
        top.dispose();
        modelTree.dispose();
        SessionManager.INSTANCE.removeSessionsListener(this);
        if (getFactory() instanceof IDisposable) {
            try {
                ((IDisposable) getFactory()).dispose();
            } catch (NullPointerException e) {
                // Nothing to do, can occur when using CDO (if the view is
                // closed when transactions have been closed
            }
        }

        if (contextMenuFiller != null) {
            contextMenuFiller.dispose();
            contextMenuFiller = null;
        }
    }

    /**
     * Return the factory and create it if null.
     * 
     * @return the factory
     */
    protected AdapterFactory getFactory() {
        if (factory == null) {
            factory = ViewHelper.INSTANCE.createAdapterFactory();
        }
        return factory;
    }
}
