/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElementSynchronizer;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactoryProvider;
import org.eclipse.sirius.tree.business.api.command.TreeCommandFactoryService;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.business.internal.refresh.DTreeElementSynchronizerSpec;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.tree.ui.tools.internal.command.EMFCommandFactoryUI;
import org.eclipse.sirius.ui.business.api.descriptor.ComposedImageDescriptor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.marker.TraceabilityMarkerNavigationProvider;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Editor for tree representations.
 * 
 * @author nlepine
 * 
 */
public class DTreeEditor extends AbstractDTreeEditor implements org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor {

    /**
     * The DTreeEditor ID.
     */
    public static final String ID = "org.eclipse.sirius.tree.ui.EditorID";

    /** The context ID. */
    private static final String CONTEXT_ID = ID + ".treeContext";

    /** Initial title image descriptor **/
    private static final ImageDescriptor INITIAL_TITLE_IMAGE_DESCRIPTOR = TreeUIPlugin.getBundledImageDescriptor("icons/full/obj16/TreeDescription.gif");

    private IPartListener refreshAtOpeningActivator;

    private DTree treeModel;

    /**
     * {@inheritDoc}
     */
    public Image getInitialImage() {
        if (initialTitleImage == null || initialTitleImage.isDisposed()) {
            initialTitleImage = SiriusEditPlugin.getPlugin().getImage(INITIAL_TITLE_IMAGE_DESCRIPTOR);
        }
        return initialTitleImage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getFrozenRepresentationImage() {
        if (frozenRepresentationImage == null || frozenRepresentationImage.isDisposed()) {
            Image refreshImage = TreeUIPlugin.getImage(TreeUIPlugin.getBundledImageDescriptor("icons/" + DTreeViewerManager.REFRESH_IMG + ".gif"));
            List<Object> images = new ArrayList<Object>(2);
            images.add(refreshImage);
            Image lockByOtherOverlayImage = SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied_overlay.gif"));
            images.add(lockByOtherOverlayImage);
            ImageDescriptor composedImageDescriptor = new ComposedImageDescriptor(new ComposedImage(images));
            frozenRepresentationImage = SiriusEditPlugin.getPlugin().getImage(composedImageDescriptor);
        }
        return frozenRepresentationImage;
    }

    /**
     * We have to take care of the case when Eclipse starts up with a session
     * and diagram already open.
     * 
     * {@inheritDoc}
     */
    @Override
    public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
        setSite(site);

        final Collection<Session> sessions = SessionManager.INSTANCE.getSessions();

        /*
         * we are during eclipse boot, we are not trying to close the editor
         */
        if (sessions.isEmpty() && (!isClosing)) {
            SessionManager.INSTANCE.addSessionsListener(sessionManagerListener);
        }
        isClosing = false;

        if (input instanceof SessionEditorInput) {
            final URI uri = ((SessionEditorInput) input).getURI();
            this.session = ((SessionEditorInput) input).getSession();
            setTreeModel(getDTree(uri, false));
        } else if (input instanceof URIEditorInput) {
            /* This happens when Eclipse is launched with an open tree editor */
            final URI uri = ((URIEditorInput) input).getURI();
            final DTree rootElement = getDTree(uri, true);
            if (rootElement != null) {
                setTreeModel(rootElement);
            }
        }

        setInput(input);

        if (session != null) {
            session.addListener(this);
        }

        initCommandFactoryProviders();

        adapterFactory = new ComposedAdapterFactory(TreeUIPlugin.getPlugin().getItemProvidersAdapterFactory());

        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(this.session);
        uiSession.open();
        uiSession.attachEditor(this);
        setAccessor(SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(getTreeModel()));

        if (getTreeModel() != null) {
            /*
             * let's activate the model listening
             */
            final DTreeElementSynchronizer sync = new DTreeElementSynchronizerSpec(getInterpreter(), getAccessor());
            getTreeModel().activate(sync);

            /* Update title. Semantic tree could have been renamed */
            notify(PROP_TITLE);

            // Launch the refresh if needed
            if (DialectUIManager.INSTANCE.isRefreshActivatedOnRepresentationOpening()) {
                launchRefresh(true);
            }

            initCollaborativeIPermissionAuthority(getTreeModel());
        }
    }

    /**
     * Creates the SWT controls for this workbench part.
     * <p>
     * Subclasses must implement this method. For a detailed description of the
     * requirements see <code>IWorkbenchPart</code>
     * </p>
     * 
     * @param parent
     *            the parent control
     * @see org.eclipse.ui.IWorkbenchPart
     */
    @Override
    public void createPartControl(final Composite parent) {
        super.createPartControl(parent);

        // DslCommonPlugin.PROFILER.startWork(SiriusTasks.CREATE_TREE);
        if (getTreeModel() == null) {
            /* eclipse was closed with an editor opened and not saved */
            final Label errorLabel = new Label(parent, SWT.CENTER);
            errorLabel.setText("This tree was not saved. You can close the editor");
            return;
        }
        treeViewerManager = new DTreeViewerManager(parent, getTreeModel(), getEditingDomain(), accessor, emfCommandFactory, this);
        // DslCommonPlugin.PROFILER.stopWork(SiriusTasks.CREATE_TREE);
        getSite().setSelectionProvider(treeViewerManager.getTreeViewer());

        /* initialize Java Service. */
        EObject semantic = null;
        if (treeModel != null) {
            semantic = treeModel.getTarget();
        }
        if (semantic == null) {
            final TreeIterator<?> allContents = this.getEditingDomain().getResourceSet().getAllContents();
            while (allContents.hasNext() && semantic == null) {
                final Object next = allContents.next();
                if (next instanceof DSemanticDecorator) {
                    semantic = ((DSemanticDecorator) next).getTarget();
                }
            }
        }
        EObject anyEObject = semantic;
        final Iterator<Resource> iterResources = this.getEditingDomain().getResourceSet().getResources().iterator();
        while (iterResources.hasNext() && anyEObject == null) {
            final Resource res = iterResources.next();
            if (!res.getContents().isEmpty()) {
                anyEObject = res.getContents().get(0);
            }
        }
        Resource resource = anyEObject.eResource();
        if (resource.getResourceSet() != getEditingDomain().getResourceSet()) {
            resource.unload();
        }
        if (anyEObject != null) {
            final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(anyEObject);
            InterpreterRegistry.prepareImportsFromSession(interpreter, SessionManager.INSTANCE.getSession(anyEObject));
        }
        // Add the CreateTreeItem menu of the toolbar
        ((DTreeActionBarContributor) getEditorSite().getActionBarContributor()).addCreateTreeItemMenu(((DTreeViewerManager) this.getTableViewer()).getCreateTreeItemMenu());

        refreshAtOpeningActivator = new RefreshAtOpeningActivator(this);
        getSite().getPage().addPartListener(refreshAtOpeningActivator);

        // Activate context
        IContextService contextService = (IContextService) getSite().getService(IContextService.class);
        contextService.activateContext(CONTEXT_ID);
    }

    /**
     * Overridden to update the UI part when the {@link DTree} model is changed
     * outside of a EMF Command (which notify DTreeContentAdapter) in case of
     * collab model.
     * 
     * {@inheritDoc}
     */
    @Override
    public void setInput(IEditorInput input) {
        super.setInput(input);

        // Expands the TreeItem according to the model
        Viewer viewer = getViewer();
        if (viewer instanceof TreeViewer) {
            TreeViewer treeViewer = (TreeViewer) viewer;
            treeViewer.setExpandedElements(TreeHelper.getExpandedItems(getTreeModel()).toArray());
        }
    }

    private DTree getDTree(final URI uri, final boolean loadOnDemand) {
        DTree result = null;
        final Resource resource = getEditingDomain().getResourceSet().getResource(uri.trimFragment(), loadOnDemand);
        if (resource != null && resource.isLoaded()) {
            if (uri.fragment() != null) {
                final EObject rootElement = resource.getEObject(uri.fragment());
                if (rootElement instanceof DTree) {
                    result = (DTree) rootElement;
                }
            }
        }
        return result;
    }

    private void initCommandFactoryProviders() {
        /* get IEMFCommandFactories */
        emfCommandFactory = TreeCommandFactoryService.getInstance().getNewProvider().getCommandFactory(getEditingDomain());

        /* We add a callback for UI stuffs */
        emfCommandFactory.setUserInterfaceCallBack(new EMFCommandFactoryUI());
    }

    private IInterpreter getInterpreter() {
        return SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(getTreeModel().getTarget());
    }

    /**
     * @param accessor
     *            the accessor to set
     */
    private void setAccessor(final ModelAccessor accessor) {
        this.accessor = accessor;
        ((ITreeCommandFactory) emfCommandFactory).setModelAccessor(this.accessor);
    }

    /**
     * {@inheritDoc}
     * */
    protected void launchRefresh(boolean loading) {
        getEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(getEditingDomain(), new NullProgressMonitor(), getTreeModel()));
        if (!loading) {
            getViewer().refresh();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor#launchRefresh()
     */
    @Override
    protected void launchRefresh() {
        launchRefresh(false);
    }

    private void setTreeModel(final DTree rootElement) {
        this.treeModel = rootElement;

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectEditor#validateRepresentation()
     */
    public void validateRepresentation() {
        // TODO Auto-generated method stub
    }

    /**
     * Closes this DTreeEditor.
     * 
     * @param save
     *            indicates whether the modifications should save or not
     */
    public void close(final boolean save) {
        Display display = getSite().getShell().getDisplay();
        display.asyncExec(new Runnable() {
            public void run() {
                if (treeViewerManager != null) {
                    getSite().getPage().closeEditor(DTreeEditor.this, save);
                }
            }
        });

    }

    public DTree getTreeModel() {
        return this.treeModel;
    }

    /**
     * {@inheritDoc}
     */
    public DRepresentation getRepresentation() {
        return getTreeModel();
    }

    public String getContributorId() {
        return ID;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void doSave(final IProgressMonitor progressMonitor) {
        if (isDeleted(getEditorInput())) {
            if (isSaveAsAllowed()) {
                performSaveAs(progressMonitor);
            }
        } else {
            performSave(false, progressMonitor);
        }
    }

    /**
     * Perform the saveAs action.
     * 
     * @param progressMonitor
     *            The progress monitor
     */
    private void performSaveAs(final IProgressMonitor progressMonitor) {
        final Shell shell = getSite().getShell();
        final IEditorInput input = getEditorInput();
        final SaveAsDialog dialog = new SaveAsDialog(shell);
        final IFile original = input instanceof IFileEditorInput ? ((IFileEditorInput) input).getFile() : null;
        if (original != null) {
            dialog.setOriginalFile(original);
        }
        dialog.create();
        if (dialog.open() == Window.CANCEL) {
            if (progressMonitor != null) {
                progressMonitor.setCanceled(true);
            }
        } else {
            final IPath filePath = dialog.getResult();
            if (filePath == null) {
                if (progressMonitor != null) {
                    progressMonitor.setCanceled(true);
                }
                return;
            }
            final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
            final IFile file = workspaceRoot.getFile(filePath);
            final IEditorInput newInput = new FileEditorInput(file);
            // Check if the editor is already open
            final IEditorMatchingStrategy matchingStrategy = getEditorDescriptor().getEditorMatchingStrategy();
            final IEditorReference[] editorRefs = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
            for (IEditorReference editorRef : editorRefs) {
                if (matchingStrategy.matches(editorRef, newInput)) {
                    // MessageDialog.openWarning(shell,
                    // Messages.dTableEditor_SaveAsErrorTitle,
                    // Messages.dTableEditor_SaveAsErrorMessage);
                    return;
                }
            }
            final boolean success = false;
            if (progressMonitor != null) {
                progressMonitor.setCanceled(!success);
            }
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void doSaveAs() {
        if (isSaveAsAllowed()) {
            performSaveAs(new NullProgressMonitor());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.ide.IGotoMarker#gotoMarker(org.eclipse.core.resources.IMarker)
     */
    public void gotoMarker(IMarker marker) {
        if (TraceabilityMarkerNavigationProvider.isTraceabilityMarker(marker)) {
            new TraceabilityMarkerNavigationProvider(this).gotoMarker(marker);
        } else {
            doGoToMarker(marker);
        }
    }

    /**
     * Sets the cursor and selection state for an editor to reveal the position
     * of the given marker.
     * 
     * @param marker
     *            the marker to go to
     */
    // TODO for now on, this implementation only allow to handle Traceability
    // markers
    protected void doGoToMarker(IMarker marker) {
        final String treeURI = marker.getAttribute(TraceabilityMarkerNavigationProvider.REPRESENTATION_URI, null);
        final String elementId = marker.getAttribute(TraceabilityMarkerNavigationProvider.REPRESENTATION_ELEMENT_ID, null);

        if (treeURI == null || elementId == null) {
            return;
        }

        final URI markerTreeURI = URI.createURI(treeURI);
        final DTree markerTree = (DTree) this.getTreeModel().eResource().getEObject(markerTreeURI.fragment());

        if (markerTree != null) {
            EObject searchedElement = markerTree.eResource().getEObject(elementId);
            TreeViewer viewer = this.treeViewerManager.getTreeViewer();

            // FIXME : contrary to tables, getItems() of the Tree returns only
            // expanded elements. So we expand it manually
            Object[] expandedElements = viewer.getExpandedElements();
            ((TreeViewer) this.getViewer()).expandAll();
            TreeItem contains = contains(viewer.getTree().getItems(), searchedElement);
            ((TreeViewer) this.getViewer()).setExpandedElements(expandedElements);

            if (contains != null) {
                ISelection selection = new TreeSelection(getTreePathFromItem(contains));
                ((TreeViewer) this.getViewer()).setSelection(selection, true);
            }

        }
    }

    /**
     * Returns the tree path for the given item.
     * 
     * @param item
     *            the item from which obtain the path
     * @return {@link TreePath}
     * 
     * @since 0.9.0
     */
    protected TreePath getTreePathFromItem(TreeItem item) {
        LinkedList<Object> segments = new LinkedList<Object>();
        TreeItem myItem = item;
        while (myItem != null) {
            Object segment = item.getData();
            Assert.isNotNull(segment);
            segments.addFirst(segment);
            myItem = myItem.getParentItem();
        }
        return new TreePath(segments.toArray());
    }

    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
        Object result = super.getAdapter(type);
        if (result == null) {
            if (type == ITreeCommandFactoryProvider.class) {
                result = emfCommandFactory;
            }
        }
        return result;
    }

    /**
     * Return the adapter factory used for providing views of the model of this
     * editor.
     * 
     * @return the adapter factory used for providing views of the model of this
     *         editor.
     */
    public AdapterFactory getAdapterFactory() {
        if (adapterFactory == null) {
            // Create an adapter factory that yields item providers.
            adapterFactory = TreeUIPlugin.getPlugin().createAdapterFactory();
        }
        return adapterFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        if (refreshAtOpeningActivator != null) {
            getSite().getPage().removePartListener(refreshAtOpeningActivator);
        }
        refreshAtOpeningActivator = null;
        super.dispose();
        if (getAdapterFactory() instanceof IDisposable) {
            ((IDisposable) getAdapterFactory()).dispose();
        }
    }

    /**
     * {@inheritDoc}
     */
    public Control getControl() {
        TreeViewer treeViewer = this.getTableViewer().getTreeViewer();
        return treeViewer.getTree();
    }

}
