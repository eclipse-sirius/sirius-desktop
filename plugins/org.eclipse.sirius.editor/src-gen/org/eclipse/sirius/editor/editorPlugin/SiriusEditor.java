/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.editor.editorPlugin;

// Start of user code imports

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.ui.ViewerPane;
import org.eclipse.emf.common.ui.editor.ProblemEditorPart;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.utils.SelectionTreeTextEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionsUIManager;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.audit.provider.AuditItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.style.provider.StyleItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.tool.provider.ToolItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.validation.provider.ValidationItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.provider.ViewpointItemProviderAdapterFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * This is an example of a Viewpoint model editor.
 */
public class SiriusEditor extends MultiPageEditorPart
implements IAdapterFactoryProvider, IEditingDomainProvider, ISelectionProvider, IMenuListener, IViewerProvider, ITabbedPropertySheetPageContributor, IGotoMarker {

    /**
     * A Color registry for the current editor.
     */
    private static ColorRegistry colorRegistry;

    /**
     * A Font registry for the current editor.
     */
    private static FontRegistry fontRegistry;

    /**
     * An Image registry for the current editor.
     */
    private static ImageRegistry imageRegistry;

    /**
     * This keeps track of the editing domain that is used to track all changes to the model.
     */
    protected AdapterFactoryEditingDomain editingDomain;

    /**
     * This is the one adapter factory used for providing views of the model.
     */
    protected ComposedAdapterFactory adapterFactory;

    /**
     * This is the content outline page.
     */
    protected IContentOutlinePage contentOutlinePage;

    /**
     * This is a kludge...
     */
    protected IStatusLineManager contentOutlineStatusLineManager;

    /**
     * This is the content outline page's viewer.
     */
    protected TreeViewer contentOutlineViewer;

    /**
     * This is the property sheet page.
     */
    protected ViewpointPropertySheetPage propertySheetPage;

    /**
     * This is the viewer that shadows the selection in the content outline. The parent relation must be correctly
     * defined for this to work.
     */
    protected TreeViewer selectionViewer;

    /**
     * This keeps track of the active viewer pane, in the book.
     */
    protected ViewerPane currentViewerPane;

    /**
     * This keeps track of the active content viewer, which may be either one of the viewers in the pages or the content
     * outline viewer.
     */
    protected Viewer currentViewer;

    /**
     * This listens to which ever viewer is active.
     */
    protected ISelectionChangedListener selectionChangedListener;

    /**
     * This keeps track of all the {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are listening to
     * this editor.
     */
    protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();

    /**
     * This keeps track of the selection of the editor as a whole.
     */
    protected ISelection editorSelection = StructuredSelection.EMPTY;

    /**
     * The MarkerHelper is responsible for creating workspace resource markers presented in Eclipse's Problems View.
     */
    protected MarkerHelper markerHelper = new EditUIMarkerHelper();

    /**
     * This listens for when the outline becomes active
     */
    protected IPartListener partListener = new IPartListener() {
        @Override
        public void partActivated(IWorkbenchPart p) {
            if (p instanceof ContentOutline) {
                if (((ContentOutline) p).getCurrentPage() == contentOutlinePage) {
                    getActionBarContributor().setActiveEditor(SiriusEditor.this);

                    setCurrentViewer(contentOutlineViewer);
                }
            } else if (p instanceof PropertySheet) {
                if (((PropertySheet) p).getCurrentPage() == propertySheetPage) {
                    getActionBarContributor().setActiveEditor(SiriusEditor.this);
                    handleActivate();
                }
            } else if (p == SiriusEditor.this) {
                handleActivate();
            }
        }

        @Override
        public void partBroughtToTop(IWorkbenchPart p) {
        }

        @Override
        public void partClosed(IWorkbenchPart p) {
        }

        @Override
        public void partDeactivated(IWorkbenchPart p) {
        }

        @Override
        public void partOpened(IWorkbenchPart p) {
        }
    };

    /**
     * A Job used to refresh the viewer.
     */
    private Job refreshJob;

    /**
     * Resources that have been removed since last activation.
     */
    protected Collection<Resource> removedResources = new ArrayList<Resource>();

    /**
     * Resources that have been changed since last activation.
     */
    protected Collection<Resource> changedResources = new ArrayList<Resource>();

    /**
     * Resources which markers have been updated since last activation.
     */
    protected Collection<Resource> markerUpdatedResources = new ArrayList<Resource>();

    /**
     * Resources that have been saved.
     */
    protected Collection<Resource> savedResources = new ArrayList<Resource>();

    /**
     * Map to store the diagnostic associated with a resource.
     */
    protected Map<Resource, Diagnostic> resourceToDiagnosticMap = new LinkedHashMap<Resource, Diagnostic>();

    /**
     * Controls whether the problem indication should be updated.
     */
    protected boolean updateProblemIndication = true;

    /**
     * Adapter used to update the problem indication when resources are demanded loaded.
     */
    protected EContentAdapter problemIndicationAdapter = new EContentAdapter() {
        @Override
        public void notifyChanged(Notification notification) {
            if (notification.getNotifier() instanceof Resource) {
                switch (notification.getFeatureID(Resource.class)) {
                case Resource.RESOURCE__IS_LOADED:
                case Resource.RESOURCE__ERRORS:
                case Resource.RESOURCE__WARNINGS: {
                    Resource resource = (Resource) notification.getNotifier();
                    Diagnostic diagnostic = analyzeResourceProblems((Resource) notification.getNotifier(), null);
                    if (diagnostic.getSeverity() != Diagnostic.OK) {
                        resourceToDiagnosticMap.put(resource, diagnostic);
                    } else {
                        resourceToDiagnosticMap.remove(resource);
                    }

                    if (updateProblemIndication) {
                        getSite().getShell().getDisplay().asyncExec(new Runnable() {
                            @Override
                            public void run() {
                                updateProblemIndication();
                            }
                        });
                    }
                }
                }
            } else {
                super.notifyChanged(notification);
            }
        }

        @Override
        protected void setTarget(Resource target) {
            basicSetTarget(target);
        }

        @Override
        protected void unsetTarget(Resource target) {
            basicUnsetTarget(target);
        }
    };

    /**
     * This listens for workspace changes.
     */
    protected IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {
        @Override
        public void resourceChanged(IResourceChangeEvent event) {
            // Only listening to these.
            // if (event.getType() == IResourceDelta.POST_CHANGE)
            {
                IResourceDelta delta = event.getDelta();
                try {
                    class ResourceDeltaVisitor implements IResourceDeltaVisitor {
                        protected ResourceSet resourceSet = editingDomain.getResourceSet();

                        protected Collection<Resource> changedResources = new ArrayList<Resource>();

                        protected Collection<Resource> removedResources = new ArrayList<Resource>();

                        protected Collection<Resource> markerUpdatedResources = new ArrayList<Resource>();

                        @Override
                        public boolean visit(IResourceDelta delta) {
                            if (delta.getResource().getType() == IResource.FILE) {
                                if ((delta.getKind() & (IResourceDelta.CHANGED | IResourceDelta.REMOVED)) != 0) {
                                    Resource resource = resourceSet.getResource(URI.createURI(delta.getFullPath().toString()), false);
                                    if (resource != null) {
                                        if (delta.getFlags() == IResourceDelta.MARKERS) {
                                            markerUpdatedResources.add(resource);
                                        } else if ((delta.getKind() & IResourceDelta.REMOVED) != 0) {
                                            removedResources.add(resource);
                                        } else if (!savedResources.remove(resource)) {
                                            changedResources.add(resource);
                                        }
                                    }
                                }
                            }

                            return true;
                        }

                        public Collection<Resource> getChangedResources() {
                            return changedResources;
                        }

                        public Collection<Resource> getRemovedResources() {
                            return removedResources;
                        }

                        public Collection<Resource> getMarkerUpdatedResources() {
                            return markerUpdatedResources;
                        }
                    }

                    ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
                    delta.accept(visitor);

                    if (!visitor.getRemovedResources().isEmpty()) {
                        removedResources.addAll(visitor.getRemovedResources());
                        if (!isDirty()) {
                            getSite().getShell().getDisplay().asyncExec(() -> getSite().getPage().closeEditor(SiriusEditor.this, false));
                        }
                    }

                    if (!visitor.getChangedResources().isEmpty()) {
                        changedResources.addAll(visitor.getChangedResources());
                        if (getSite().getPage().getActiveEditor() == SiriusEditor.this) {
                            getSite().getShell().getDisplay().asyncExec(new Runnable() {
                                @Override
                                public void run() {
                                    handleActivate();
                                }
                            });
                        }
                    }

                    if (!visitor.getMarkerUpdatedResources().isEmpty()) {
                        markerUpdatedResources.addAll(visitor.getMarkerUpdatedResources());
                        getSite().getShell().getDisplay().asyncExec(new Runnable() {
                            @Override
                            public void run() {
                                refreshViewer();
                            }
                        });
                    }
                } catch (CoreException exception) {
                    SiriusEditorPlugin.INSTANCE.log(exception);
                }
            }
        }
    };

    private void refreshViewer() {
        if (refreshJob != null) {
            refreshJob.cancel();
        }
        refreshJob = new Job("Refreshing " + this.getEditorInput().getName() + " editor") {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
                // As this job is scheduled, viewer can have been disposed
                if (currentViewer != null && !currentViewer.getControl().isDisposed()) {
                    // We simply refresh the editor ; interpreter errors
                    // decorations will be refreshed
                    currentViewer.getControl().getDisplay().asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            // As this code is launched asynchronously, viewer
                            // can have been disposed
                            if (currentViewer != null && !currentViewer.getControl().isDisposed()) {
                                currentViewer.refresh();
                            }
                        }
                    });
                }
                return Status.OK_STATUS;
            }

        };
        refreshJob.schedule(200);
    }

    /**
     * Handles activation of the editor or it's associated views.
     */
    protected void handleActivate() {
        // Recompute the read only state.
        //
        if (editingDomain.getResourceToReadOnlyMap() != null) {
            editingDomain.getResourceToReadOnlyMap().clear();

            // Refresh any actions that may become enabled or disabled.
            //
            setSelection(getSelection());
        }

        if (!removedResources.isEmpty()) {
            if (handleDirtyConflict()) {
                getSite().getPage().closeEditor(SiriusEditor.this, false);
                SiriusEditor.this.dispose();
            } else {
                removedResources.clear();
                changedResources.clear();
                savedResources.clear();
            }
        } else if (!changedResources.isEmpty()) {
            changedResources.removeAll(savedResources);
            handleChangedResources();
            changedResources.clear();
            savedResources.clear();
        }
    }

    /**
     * Handles what to do with changed resources on activation.
     */
    protected void handleChangedResources() {
        if (!changedResources.isEmpty() && (!isDirty() || handleDirtyConflict())) {
            editingDomain.getCommandStack().flush();

            updateProblemIndication = false;
            for (Resource resource : changedResources) {
                if (resource.isLoaded()) {
                    resource.unload();
                    beforeReload(resource);
                    try {
                        resource.load(Collections.EMPTY_MAP);
                    } catch (IOException exception) {
                        if (!resourceToDiagnosticMap.containsKey(resource)) {
                            resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
                        }
                    }
                }
            }
            updateProblemIndication = true;
            updateProblemIndication();
        }
    }

    /**
     * The changed resource has just been unloaded and will be loaded after this call. This method allows to analyze and
     * update the resource (default load/save options for example) before the reload.
     *
     * @param changedResource
     *            the changed resource.
     */
    protected void beforeReload(Resource changedResource) {
        // Nothing to do per default.
    }

    /**
     * Updates the problems indication with the information described in the specified diagnostic.
     */
    protected void updateProblemIndication() {
        if (updateProblemIndication) {
            BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.OK, "org.eclipse.sirius.editor", 0, null, new Object[] { editingDomain.getResourceSet() });
            for (Diagnostic childDiagnostic : resourceToDiagnosticMap.values()) {
                if (childDiagnostic.getSeverity() != Diagnostic.OK) {
                    diagnostic.add(childDiagnostic);
                }
            }

            int lastEditorPage = getPageCount() - 1;
            if (lastEditorPage >= 0 && getEditor(lastEditorPage) instanceof ProblemEditorPart) {
                ((ProblemEditorPart) getEditor(lastEditorPage)).setDiagnostic(diagnostic);
                if (diagnostic.getSeverity() != Diagnostic.OK) {
                    setActivePage(lastEditorPage);
                }
            } else if (diagnostic.getSeverity() != Diagnostic.OK) {
                ProblemEditorPart problemEditorPart = new ProblemEditorPart();
                problemEditorPart.setDiagnostic(diagnostic);
                problemEditorPart.setMarkerHelper(markerHelper);
                try {
                    addPage(++lastEditorPage, problemEditorPart, getEditorInput());
                    setPageText(lastEditorPage, problemEditorPart.getPartName());
                    setActivePage(lastEditorPage);
                    showTabs();
                } catch (PartInitException exception) {
                    SiriusEditorPlugin.INSTANCE.log(exception);
                }
            }

            if (markerHelper.hasMarkers(editingDomain.getResourceSet())) {
                markerHelper.deleteMarkers(editingDomain.getResourceSet());
                if (diagnostic.getSeverity() != Diagnostic.OK) {
                    try {
                        markerHelper.createMarkers(diagnostic);
                    } catch (CoreException exception) {
                        SiriusEditorPlugin.INSTANCE.log(exception);
                    }
                }
            }
        }
    }

    /**
     * Shows a dialog that asks if conflicting changes should be discarded.
     */
    protected boolean handleDirtyConflict() {
        return MessageDialog.openQuestion(getSite().getShell(), SiriusEditor.getString("_UI_FileConflict_label"), SiriusEditor.getString("_WARN_FileConflict"));
    }

    /**
     * This creates a model editor.
     */
    public SiriusEditor() {
        super();

        // Create an adapter factory that yields item providers.
        //
        List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        // Start of user code put your specific adapter factories
        factories.add(DialectUIManager.INSTANCE.createAdapterFactory());
        factories.add(FeatureExtensionsUIManager.INSTANCE.createAdapterFactory());
        factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        // End of user code put your specific adapter factories
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new ViewpointItemProviderAdapterFactory());
        factories.add(new DescriptionItemProviderAdapterFactory());
        factories.add(new StyleItemProviderAdapterFactory());
        factories.add(new ToolItemProviderAdapterFactory());
        factories.add(new ValidationItemProviderAdapterFactory());
        factories.add(new AuditItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());

        adapterFactory = new ComposedAdapterFactory(factories);

        // Create the command stack that will notify this editor as commands are executed.
        //
        BasicCommandStack commandStack = new BasicCommandStack();

        // Add a listener to set the most recent command's affected objects to be the selection of the viewer with
        // focus.
        //
        commandStack.addCommandStackListener(new CommandStackListener() {
            @Override
            public void commandStackChanged(final EventObject event) {
                getContainer().getDisplay().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        firePropertyChange(IEditorPart.PROP_DIRTY);

                        // Try to select the affected objects.
                        //
                        Command mostRecentCommand = ((CommandStack) event.getSource()).getMostRecentCommand();
                        if (mostRecentCommand != null) {
                            setSelectionToViewer(mostRecentCommand.getAffectedObjects());
                        }
                        if (propertySheetPage != null && !propertySheetPage.getControl().isDisposed() && propertySheetPage.getCurrentTab() != null) {
                            propertySheetPage.refresh();
                        }
                    }
                });
            }
        });

        // Create the editing domain with a special command stack.
        //
        editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
    }

    /**
     * This is here for the listener to be able to call it.
     */
    @Override
    protected void firePropertyChange(int action) {
        super.firePropertyChange(action);
    }

    /**
     * This sets the selection into whichever viewer is active.
     */
    public void setSelectionToViewer(Collection<?> collection) {
        final Collection<?> theSelection = collection;
        // Make sure it's okay.
        //
        if (theSelection != null && !theSelection.isEmpty()) {
            // I don't know if this should be run this deferred
            // because we might have to give the editor a chance to process the viewer update events
            // and hence to update the views first.
            //
            //
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // Try to select the items in the current content viewer of the editor.
                    //
                    if (currentViewer != null) {
                        currentViewer.setSelection(new StructuredSelection(theSelection.toArray()), true);
                    }
                }
            };
            runnable.run();
        }
    }

    /**
     * This returns the editing domain as required by the {@link IEditingDomainProvider} interface. This is important
     * for implementing the static methods of {@link AdapterFactoryEditingDomain} and for supporting
     * {@link org.eclipse.emf.edit.ui.action.CommandAction}.
     */
    @Override
    public EditingDomain getEditingDomain() {
        return editingDomain;
    }

    public void setCurrentViewerPane(ViewerPane viewerPane) {
        if (currentViewerPane != viewerPane) {
            if (currentViewerPane != null) {
                currentViewerPane.showFocus(false);
            }
            currentViewerPane = viewerPane;
        }
        setCurrentViewer(currentViewerPane.getViewer());
    }

    /**
     * This makes sure that one content viewer, either for the current page or the outline view, if it has focus, is the
     * current one.
     */
    public void setCurrentViewer(Viewer viewer) {
        // If it is changing...
        //
        if (currentViewer != viewer) {
            if (selectionChangedListener == null) {
                // Create the listener on demand.
                //
                selectionChangedListener = new ISelectionChangedListener() {
                    // This just notifies those things that are affected by the section.
                    //
                    @Override
                    public void selectionChanged(SelectionChangedEvent selectionChangedEvent) {
                        setSelection(selectionChangedEvent.getSelection());
                    }
                };
            }

            // Stop listening to the old one.
            //
            if (currentViewer != null) {
                currentViewer.removeSelectionChangedListener(selectionChangedListener);
            }

            // Start listening to the new one.
            //
            if (viewer != null) {
                viewer.addSelectionChangedListener(selectionChangedListener);
            }

            // Remember it.
            //
            currentViewer = viewer;

            // Set the editors selection based on the current viewer's selection.
            //
            setSelection(currentViewer == null ? StructuredSelection.EMPTY : currentViewer.getSelection());
        }
    }

    /**
     * This returns the viewer as required by the {@link IViewerProvider} interface.
     */
    @Override
    public Viewer getViewer() {
        return currentViewer;
    }

    /**
     * This creates a context menu for the viewer and adds a listener as well registering the menu for extension.
     */
    protected void createContextMenuFor(StructuredViewer viewer) {
        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add(new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener(this);
        Menu menu = contextMenu.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
        getSite().registerContextMenu(contextMenu, viewer);

        int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
        Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
        viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
        viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(editingDomain, viewer));
    }

    /**
     * This is the method called to load a resource into the editing domain's resource set based on the editor's input.
     */
    public void createModel() {
        // Assumes that the input is a file object.
        //
        IFileEditorInput modelFile = (IFileEditorInput) getEditorInput();
        URI resourceURI = URI.createPlatformResourceURI(modelFile.getFile().getFullPath().toString(), false);
        Exception exception = null;
        Resource resource = null;
        try {
            // Load the resource through the editing domain.
            //
            resource = editingDomain.getResourceSet().getResource(resourceURI, true);
        } catch (Exception e) {
            exception = e;
            resource = editingDomain.getResourceSet().getResource(resourceURI, false);
        }

        Diagnostic diagnostic = analyzeResourceProblems(resource, exception);
        if (diagnostic.getSeverity() != Diagnostic.OK) {
            resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
        }
        editingDomain.getResourceSet().eAdapters().add(problemIndicationAdapter);
    }

    /**
     * Returns a diagnostic describing the errors and warnings listed in the resource and the specified exception (if
     * any).
     */
    public Diagnostic analyzeResourceProblems(Resource resource, Exception exception) {
        if (!resource.getErrors().isEmpty() || !resource.getWarnings().isEmpty()) {
            BasicDiagnostic basicDiagnostic = new BasicDiagnostic(Diagnostic.ERROR, "org.eclipse.sirius.editor", 0, SiriusEditor.getString("_UI_CreateModelError_message", resource.getURI()),
                    new Object[] { exception == null ? (Object) resource : exception });
            basicDiagnostic.merge(EcoreUtil.computeDiagnostic(resource, true));
            return basicDiagnostic;
        } else if (exception != null) {
            return new BasicDiagnostic(Diagnostic.ERROR, "org.eclipse.sirius.editor", 0, SiriusEditor.getString("_UI_CreateModelError_message", resource.getURI()), new Object[] { exception });
        } else {
            return Diagnostic.OK_INSTANCE;
        }
    }

    /**
     * This is the method used by the framework to install your own controls.
     */
    @Override
    public void createPages() {
        // Creates the model from the editor input
        //
        createModel();

        // Only creates the other pages if there is something that can be edited
        //
        if (!getEditingDomain().getResourceSet().getResources().isEmpty() && !getEditingDomain().getResourceSet().getResources().get(0).getContents().isEmpty()) {
            // Create a page for the selection tree view.
            //
            {
                ViewerPane viewerPane = new ViewerPane(getSite().getPage(), SiriusEditor.this) {
                    @Override
                    public Viewer createViewer(Composite composite) {
                        Tree tree = new Tree(composite, SWT.MULTI);
                        TreeViewer newTreeViewer = new TreeViewer(tree);
                        return newTreeViewer;
                    }

                    @Override
                    public void requestActivation() {
                        super.requestActivation();
                        setCurrentViewerPane(this);
                    }
                };
                viewerPane.createControl(getContainer());

                selectionViewer = (TreeViewer) viewerPane.getViewer();
                selectionViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));

                selectionViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
                selectionViewer.setInput(editingDomain.getResourceSet());
                viewerPane.setTitle("Sirius Specification Editor", new AdapterFactoryLabelProvider(adapterFactory).getImage(DescriptionFactory.eINSTANCE.createViewpoint()));

                new SelectionTreeTextEditor(editingDomain, selectionViewer.getTree(), adapterFactory);

                createContextMenuFor(selectionViewer);
                int pageIndex = addPage(viewerPane.getControl());
                setPageText(pageIndex, SiriusEditor.getString("_UI_SelectionPage_label"));

                setCurrentViewerPane(viewerPane);

            }

            setActivePage(0);
        }

        // Ensures that this editor will only display the page's tab
        // area if there are more than one page
        //
        getContainer().addControlListener(new ControlAdapter() {
            boolean guard = false;

            @Override
            public void controlResized(ControlEvent event) {
                if (!guard) {
                    guard = true;
                    hideTabs();
                    guard = false;
                }
            }
        });

        updateProblemIndication();
    }

    /**
     * If there is just one page in the multi-page editor part, this hides the single tab at the bottom.
     */
    protected void hideTabs() {
        if (getPageCount() <= 1) {
            setPageText(0, "");
            if (getContainer() instanceof CTabFolder) {
                ((CTabFolder) getContainer()).setTabHeight(1);
                Point point = getContainer().getSize();
                getContainer().setSize(point.x, point.y + 6);
            }
        }
    }

    /**
     * If there is more than one page in the multi-page editor part, this shows the tabs at the bottom.
     */
    protected void showTabs() {
        if (getPageCount() > 1) {
            setPageText(0, SiriusEditor.getString("_UI_SelectionPage_label"));
            if (getContainer() instanceof CTabFolder) {
                ((CTabFolder) getContainer()).setTabHeight(SWT.DEFAULT);
                Point point = getContainer().getSize();
                getContainer().setSize(point.x, point.y - 6);
            }
        }
    }

    /**
     * This is used to track the active viewer.
     */
    @Override
    protected void pageChange(int pageIndex) {
        super.pageChange(pageIndex);

        if (contentOutlinePage != null) {
            handleContentOutlineSelection(contentOutlinePage.getSelection());
        }
    }

    /**
     * This is how the framework determines which interfaces we implement.
     */
    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") Class key) {
        if (key.equals(IContentOutlinePage.class)) {
            return showOutlineView() ? getContentOutlinePage() : null;
        } else if (key.equals(IPropertySheetPage.class)) {
            return getPropertySheetPage();
        } else if (key.equals(IGotoMarker.class)) {
            return this;
        } else {
            return super.getAdapter(key);
        }
    }

    /**
     * This accesses a cached version of the content outliner.
     */
    public IContentOutlinePage getContentOutlinePage() {
        if (contentOutlinePage == null) {
            // The content outline is just a tree.
            //
            class MyContentOutlinePage extends ContentOutlinePage {
                @Override
                public void createControl(Composite parent) {
                    super.createControl(parent);
                    contentOutlineViewer = getTreeViewer();
                    contentOutlineViewer.addSelectionChangedListener(this);

                    // Set up the tree viewer.
                    //
                    contentOutlineViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
                    contentOutlineViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
                    contentOutlineViewer.setInput(editingDomain.getResourceSet());

                    // Make sure our popups work.
                    //
                    createContextMenuFor(contentOutlineViewer);

                    if (!editingDomain.getResourceSet().getResources().isEmpty()) {
                        // Select the root object in the view.
                        //
                        ArrayList<Resource> selection = new ArrayList<Resource>();
                        selection.add(editingDomain.getResourceSet().getResources().get(0));
                        contentOutlineViewer.setSelection(new StructuredSelection(selection), true);
                    }
                }

                @Override
                public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager) {
                    super.makeContributions(menuManager, toolBarManager, statusLineManager);
                    contentOutlineStatusLineManager = statusLineManager;
                }

                @Override
                public void setActionBars(IActionBars actionBars) {
                    super.setActionBars(actionBars);
                    getActionBarContributor().shareGlobalActions(this, actionBars);
                }
            }

            contentOutlinePage = new MyContentOutlinePage();

            // Listen to selection so that we can handle it is a special way.
            //
            contentOutlinePage.addSelectionChangedListener(new ISelectionChangedListener() {
                // This ensures that we handle selections correctly.
                //
                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    handleContentOutlineSelection(event.getSelection());
                }
            });
        }

        return contentOutlinePage;
    }

    /**
     * This accesses a cached version of the property sheet.
     */
    public TabbedPropertySheetPage getPropertySheetPage() {
        if (propertySheetPage == null || propertySheetPage.getControl() == null || propertySheetPage.getControl().isDisposed()) {
            propertySheetPage = new ViewpointPropertySheetPage(this);
        }

        return propertySheetPage;
    }

    /**
     * This deals with how we want selection in the outliner to affect the other views.
     */
    public void handleContentOutlineSelection(ISelection selection) {
        if (currentViewerPane != null && !selection.isEmpty() && selection instanceof IStructuredSelection) {
            Iterator<?> selectedElements = ((IStructuredSelection) selection).iterator();
            if (selectedElements.hasNext()) {
                // Get the first selected element.
                //
                Object selectedElement = selectedElements.next();

                // If it's the selection viewer, then we want it to select the same selection as this selection.
                //
                if (currentViewerPane.getViewer() == selectionViewer) {
                    ArrayList<Object> selectionList = new ArrayList<Object>();
                    selectionList.add(selectedElement);
                    while (selectedElements.hasNext()) {
                        selectionList.add(selectedElements.next());
                    }

                    // Set the selection to the widget.
                    //
                    selectionViewer.setSelection(new StructuredSelection(selectionList));
                } else {
                    // Set the input to the widget.
                    //
                    if (currentViewerPane.getViewer().getInput() != selectedElement) {
                        currentViewerPane.getViewer().setInput(selectedElement);
                        currentViewerPane.setTitle(selectedElement);
                    }
                }
            }
        }
    }

    /**
     * This is for implementing {@link IEditorPart} and simply tests the command stack.
     */
    @Override
    public boolean isDirty() {
        return ((BasicCommandStack) editingDomain.getCommandStack()).isSaveNeeded();
    }

    /**
     * This is for implementing {@link IEditorPart} and simply saves the model file.
     */
    @Override
    public void doSave(IProgressMonitor progressMonitor) {
        // Do the work within an operation because this is a long running activity that modifies the workbench.
        //
        WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
            // This is the method that gets invoked when the operation runs.
            //
            @Override
            public void execute(IProgressMonitor monitor) {
                // Save the resources to the file system.
                //
                boolean first = true;
                for (Resource resource : editingDomain.getResourceSet().getResources()) {
                    if ((first || !resource.getContents().isEmpty() || isPersisted(resource)) && !editingDomain.isReadOnly(resource)) {
                        try {
                            savedResources.add(resource);
                            resource.save(Collections.EMPTY_MAP);
                        } catch (Exception exception) {
                            resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
                        }
                        first = false;
                    }
                }
            }
        };

        updateProblemIndication = false;
        try {
            // This runs the options, and shows progress.
            //
            new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);

            // Refresh the necessary state.
            //
            ((BasicCommandStack) editingDomain.getCommandStack()).saveIsDone();
            firePropertyChange(IEditorPart.PROP_DIRTY);
        } catch (Exception exception) {
            // Something went wrong that shouldn't.
            //
            SiriusEditorPlugin.INSTANCE.log(exception);
        }
        updateProblemIndication = true;
        updateProblemIndication();
    }

    /**
     * This returns whether something has been persisted to the URI of the specified resource. The implementation uses
     * the URI converter from the editor's resource set to try to open an input stream.
     */
    protected boolean isPersisted(Resource resource) {
        boolean result = false;
        try {
            InputStream stream = editingDomain.getResourceSet().getURIConverter().createInputStream(resource.getURI());
            if (stream != null) {
                result = true;
                stream.close();
            }
        } catch (IOException e) {
        }
        return result;
    }

    /**
     * This always returns true because it is not currently supported.
     */
    @Override
    public boolean isSaveAsAllowed() {
        return true;
    }

    /**
     * This also changes the editor's input.
     */
    @Override
    public void doSaveAs() {
        SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
        saveAsDialog.open();
        IPath path = saveAsDialog.getResult();
        if (path != null) {
            IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
            if (file != null) {
                doSaveAs(URI.createPlatformResourceURI(file.getFullPath().toString(), false), new FileEditorInput(file));
            }
        }
    }

    protected void doSaveAs(URI uri, IEditorInput editorInput) {
        editingDomain.getResourceSet().getResources().get(0).setURI(uri);
        setInputWithNotify(editorInput);
        setPartName(editorInput.getName());
        IProgressMonitor progressMonitor = getActionBars().getStatusLineManager() != null ? getActionBars().getStatusLineManager().getProgressMonitor() : new NullProgressMonitor();
        doSave(progressMonitor);
    }

    @Override
    public void gotoMarker(IMarker marker) {
        try {
            if (marker.getType().equals(EValidator.MARKER)) {
                String uriAttribute = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
                if (uriAttribute != null) {
                    URI uri = URI.createURI(uriAttribute);
                    EObject eObject = editingDomain.getResourceSet().getEObject(uri, true);
                    if (eObject != null) {
                        setSelectionToViewer(Collections.singleton(editingDomain.getWrapper(eObject)));
                    }
                }
            }
        } catch (CoreException exception) {
            SiriusEditorPlugin.INSTANCE.log(exception);
        }
    }

    /**
     * This is called during startup.
     */
    @Override
    public void init(IEditorSite site, IEditorInput editorInput) {
        setSite(site);
        setInputWithNotify(editorInput);
        setPartName(editorInput.getName());
        site.setSelectionProvider(this);
        site.getPage().addPartListener(partListener);
        ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener, IResourceChangeEvent.POST_CHANGE);
    }

    @Override
    public void setFocus() {
        if (currentViewerPane != null) {
            currentViewerPane.setFocus();
        } else {
            getControl(getActivePage()).setFocus();
        }
    }

    /**
     * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
     */
    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        selectionChangedListeners.add(listener);
    }

    /**
     * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
     */
    @Override
    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        selectionChangedListeners.remove(listener);
    }

    /**
     * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to return this editor's overall selection.
     */
    @Override
    public ISelection getSelection() {
        return editorSelection;
    }

    /**
     * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to set this editor's overall selection.
     * Calling this result will notify the listeners.
     */
    @Override
    public void setSelection(ISelection selection) {
        editorSelection = selection;

        if (selection instanceof TreeSelection) {
            TreeSelection tree = (TreeSelection) selection;
            if (tree.getFirstElement() instanceof EObject) {
                EObject selectedObject = (EObject) tree.getFirstElement();
                PlatformUI.getWorkbench().getHelpSystem().setHelp(getContainer(), "org.eclipse.sirius." + selectedObject.eClass().getName());
            }
        }

        for (ISelectionChangedListener listener : selectionChangedListeners) {
            listener.selectionChanged(new SelectionChangedEvent(this, selection));
        }
        setStatusLineManager(selection);
    }

    public void setStatusLineManager(ISelection selection) {
        IStatusLineManager statusLineManager = currentViewer != null && currentViewer == contentOutlineViewer ? contentOutlineStatusLineManager : getActionBars().getStatusLineManager();

        if (statusLineManager != null) {
            if (selection instanceof IStructuredSelection) {
                Collection<?> collection = ((IStructuredSelection) selection).toList();
                switch (collection.size()) {
                case 0: {
                    statusLineManager.setMessage(SiriusEditor.getString("_UI_NoObjectSelected"));
                    break;
                }
                case 1: {
                    String text = new AdapterFactoryItemDelegator(adapterFactory).getText(collection.iterator().next());
                    statusLineManager.setMessage(SiriusEditor.getString("_UI_SingleObjectSelected", text));
                    break;
                }
                default: {
                    statusLineManager.setMessage(SiriusEditor.getString("_UI_MultiObjectSelected", Integer.toString(collection.size())));
                    break;
                }
                }
            } else {
                statusLineManager.setMessage("");
            }
        }
    }

    /**
     * This looks up a string in the plugin's plugin.properties file.
     */
    private static String getString(String key) {
        return SiriusEditorPlugin.INSTANCE.getString(key);
    }

    /**
     * This looks up a string in plugin.properties, making a substitution.
     */
    private static String getString(String key, Object s1) {
        return SiriusEditorPlugin.INSTANCE.getString(key, new Object[] { s1 });
    }

    /**
     * This implements {@link org.eclipse.jface.action.IMenuListener} to help fill the context menus with contributions
     * from the Edit menu.
     */
    @Override
    public void menuAboutToShow(IMenuManager menuManager) {
        ((IMenuListener) getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
    }

    public EditingDomainActionBarContributor getActionBarContributor() {
        return (EditingDomainActionBarContributor) getEditorSite().getActionBarContributor();
    }

    public IActionBars getActionBars() {
        return getActionBarContributor().getActionBars();
    }

    @Override
    public AdapterFactory getAdapterFactory() {
        return adapterFactory;
    }

    @Override
    public void dispose() {
        updateProblemIndication = false;

        ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);

        Optional.ofNullable(getSite()).map(IWorkbenchSite::getPage).ifPresent(page -> page.removePartListener(partListener));
        adapterFactory.dispose();

        if (getActionBarContributor().getActiveEditor() == this) {
            getActionBarContributor().setActiveEditor(null);
        }

        if (propertySheetPage != null) {
            propertySheetPage.dispose();
        }

        if (contentOutlinePage != null) {
            contentOutlinePage.dispose();
        }

        super.dispose();
    }

    /**
     * Returns whether the outline view should be presented to the user.
     */
    protected boolean showOutlineView() {
        return true;
    }

    /**
     * The contributor id for this configuration
     */
    @Override
    public String getContributorId() {
        return "org.eclipse.sirius.editor.editorPlugin.SiriusEditorContributor";
    }

    /**
     *
     * @return a color registry initialized with basic colors.
     */
    public static ColorRegistry getColorRegistry() {
        if (SiriusEditor.colorRegistry == null) {
            SiriusEditor.colorRegistry = new ColorRegistry();
            SiriusEditor.colorRegistry.put("blue", new RGB(204, 249, 255));
            SiriusEditor.colorRegistry.put("red", new RGB(246, 139, 139));
            SiriusEditor.colorRegistry.put("green", new RGB(204, 242, 166));
            SiriusEditor.colorRegistry.put("yellow", new RGB(255, 245, 181));
            SiriusEditor.colorRegistry.put("purple", new RGB(217, 196, 215));
            SiriusEditor.colorRegistry.put("orange", new RGB(253, 206, 137));
            SiriusEditor.colorRegistry.put("chocolate", new RGB(238, 201, 142));
            SiriusEditor.colorRegistry.put("gray", new RGB(209, 210, 208));
            SiriusEditor.colorRegistry.put("blue", new RGB(194, 239, 255));
            SiriusEditor.colorRegistry.put("red", new RGB(246, 139, 139));
            SiriusEditor.colorRegistry.put("green", new RGB(204, 242, 166));
            SiriusEditor.colorRegistry.put("yellow", new RGB(255, 245, 181));
            SiriusEditor.colorRegistry.put("purple", new RGB(217, 196, 215));
            SiriusEditor.colorRegistry.put("orange", new RGB(253, 206, 137));
            SiriusEditor.colorRegistry.put("chocolate", new RGB(238, 201, 142));
            SiriusEditor.colorRegistry.put("gray", new RGB(209, 210, 208));
            SiriusEditor.colorRegistry.put("light_blue", new RGB(212, 229, 247));
            // Start of user code put your own colors here
            SiriusEditor.colorRegistry.put("lightgreen", new RGB(227, 249, 204));
            SiriusEditor.colorRegistry.put("lightgrey", new RGB(242, 242, 242));
            // End of user code put your own colors here

        }
        return SiriusEditor.colorRegistry;
    }

    public static FontRegistry getFontRegistry() {
        if (SiriusEditor.fontRegistry == null) {
            SiriusEditor.fontRegistry = new FontRegistry();

            FontDescriptor defaultFontDescriptor = SiriusEditor.fontRegistry.defaultFontDescriptor();
            if (defaultFontDescriptor != null && defaultFontDescriptor.getFontData().length > 0) {
                FontData defaultFontData = defaultFontDescriptor.getFontData()[0];

                FontData required = new FontData(defaultFontData.toString());
                required.setStyle(SWT.BOLD);
                SiriusEditor.fontRegistry.put("required", new FontData[] { required });
            } else {
                SiriusEditor.fontRegistry.put("required", new FontData[] { new FontData("Arial", 8, SWT.BOLD) });
            }

            // Start of user code put your own fonts here

            // End of user code put your own fonts here
        }
        return SiriusEditor.fontRegistry;
    }

    public static ImageRegistry getImageRegistry() {
        if (SiriusEditor.imageRegistry == null) {
            SiriusEditor.imageRegistry = SiriusEditor.createImageRegistry();
            SiriusEditor.addImageToRegistry(SiriusEditorPlugin.ICONS_PREFERENCES_HELP);
            // Start of user code put your own images here

            // End of user code put your own images here
        }
        return SiriusEditor.imageRegistry;
    }

    private static void addImageToRegistry(final String path) {
        SiriusEditor.imageRegistry.put(path, SiriusEditor.getImageDescriptor(path));
    }

    private static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditorPlugin.PLUGIN_ID, path);
    }

    /**
     * Returns a new image registry for this plug-in. The registry will be used to manage images which are frequently
     * used by the plug-in.
     * <p>
     * The default implementation of this method creates an empty registry. Subclasses may override this method if
     * needed.
     * </p>
     *
     * @return ImageRegistry the resulting registry.
     * @see #getImageRegistry
     */
    protected static ImageRegistry createImageRegistry() {

        // If we are in the UI Thread use that
        if (Display.getCurrent() != null) {
            return new ImageRegistry(Display.getCurrent());
        }

        if (PlatformUI.isWorkbenchRunning()) {
            return new ImageRegistry(PlatformUI.getWorkbench().getDisplay());
        }

        // Invalid thread access if it is not the UI Thread
        // and the workbench is not created.
        throw new SWTError(SWT.ERROR_THREAD_INVALID_ACCESS);
    }

}
