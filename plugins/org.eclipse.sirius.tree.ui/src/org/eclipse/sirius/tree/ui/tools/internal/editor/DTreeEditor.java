/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
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
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactoryProvider;
import org.eclipse.sirius.tree.business.api.command.TreeCommandFactoryService;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.tree.ui.tools.internal.commands.EMFCommandFactoryUI;
import org.eclipse.sirius.ui.business.api.descriptor.ComposedImageDescriptor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.marker.TraceabilityMarkerNavigationProvider;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
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
 */
public class DTreeEditor extends AbstractDTreeEditor implements org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor {

    /**
     * The DTreeEditor ID.
     */
    public static final String ID = "org.eclipse.sirius.tree.ui.EditorID"; //$NON-NLS-1$

    /** The context ID. */
    private static final String CONTEXT_ID = ID + ".treeContext"; //$NON-NLS-1$

    /** Initial title image descriptor **/
    private static final ImageDescriptor INITIAL_TITLE_IMAGE_DESCRIPTOR = TreeUIPlugin.getBundledImageDescriptor("icons/full/obj16/TreeDescription.gif"); //$NON-NLS-1$

    /** This DTree model */
    private DTree treeModel;

    private IPartListener refreshAtOpeningActivator;

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

    @Override
    public void doSaveAs() {
        if (isSaveAsAllowed()) {
            performSaveAs(new NullProgressMonitor());
        }
    }

    @Override
    public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
        super.init(site, input);

        if (getTreeModel() != null) {
            // Launch the refresh if needed
            if (DialectUIManager.INSTANCE.isRefreshActivatedOnRepresentationOpening()) {
                launchRefresh(true);
            }

            // In case of shared tree representation, we notify the use of tree
            // representation locking
            initPermissionAuthority(getTreeModel());
        }
    }

    @Override
    protected void configureCommandFactoryProviders() {
        /* get IEMFCommandFactories */
        emfCommandFactory = TreeCommandFactoryService.getInstance().getNewProvider().getCommandFactory(getEditingDomain());

        /* We add a callback for UI stuffs */
        emfCommandFactory.setUserInterfaceCallBack(new EMFCommandFactoryUI());
    }

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

        /* initialize interpreter. */
        if (session != null) {
            InterpreterRegistry.prepareImportsFromSession(session.getInterpreter(), session);
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

    @Override
    public Image getFrozenRepresentationImage() {
        if (frozenRepresentationImage == null || frozenRepresentationImage.isDisposed()) {
            Image refreshImage = TreeUIPlugin.getImage(TreeUIPlugin.getBundledImageDescriptor("icons/" + DTreeViewerManager.REFRESH_IMG + ".gif")); //$NON-NLS-1$ //$NON-NLS-2$
            List<Object> images = new ArrayList<Object>(2);
            images.add(refreshImage);
            Image lockByOtherOverlayImage = SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied_overlay.gif")); //$NON-NLS-1$
            images.add(lockByOtherOverlayImage);
            ImageDescriptor composedImageDescriptor = new ComposedImageDescriptor(new ComposedImage(images));
            frozenRepresentationImage = SiriusEditPlugin.getPlugin().getImage(composedImageDescriptor);
        }
        return frozenRepresentationImage;
    }

    @Override
    public Control getControl() {
        TreeViewer treeViewer = this.getTableViewer().getTreeViewer();
        return treeViewer.getTree();
    }

    private void launchRefresh(boolean loading) {
        getEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(getEditingDomain(), new NullProgressMonitor(), getTreeModel()));
        if (!loading) {
            getViewer().refresh();
        }
    }

    @Override
    protected void launchRefresh() {
        launchRefresh(false);
    }

    @Override
    public String getContributorId() {
        return ID;
    }

    @Override
    protected void setRepresentation(URI uri, boolean loadOnDemand) {
        setTreeModel(getDTree(uri, loadOnDemand));
    }

    /**
     * Get the DTree corresponding to this URI
     * 
     * @param uri
     *            the URI to resolve.
     * @param loadOnDemand
     *            whether to create and load the resource, if it doesn't already
     *            exists.
     * @return the DTree resource resolved by the URI, or <code>null</code> if
     *         there isn't one and it's not being demand loaded.
     */
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

    @Override
    public DRepresentation getRepresentation() {
        return treeModel;
    }

    /**
     * Get the tree model.
     * 
     * @return the tree model
     */
    public DTree getTreeModel() {
        return this.treeModel;
    }

    private void setTreeModel(final DTree rootElement) {
        this.treeModel = rootElement;
    }

    @Override
    public void validateRepresentation() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void setAccessor(ModelAccessor accessor) {
        super.setAccessor(accessor);
        ((ITreeCommandFactory) emfCommandFactory).setModelAccessor(this.accessor);
    }

    @Override
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

    @Override
    public AdapterFactory getAdapterFactory() {
        if (adapterFactory == null) {
            // Create an adapter factory that yields item providers.
            adapterFactory = TreeUIPlugin.getPlugin().createAdapterFactory();
        }
        return adapterFactory;
    }

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
     * Closes this DTreeEditor.
     * 
     * @param save
     *            indicates whether the modifications should save or not
     */
    public void close(final boolean save) {
        Display display = getSite().getShell().getDisplay();
        display.asyncExec(new Runnable() {
            @Override
            public void run() {
                if (treeViewerManager != null) {
                    getSite().getPage().closeEditor(DTreeEditor.this, save);
                }
            }
        });

    }
    
    @Override
    public Image getInitialImage() {
        if (initialTitleImage == null || initialTitleImage.isDisposed()) {
            initialTitleImage = SiriusEditPlugin.getPlugin().getImage(INITIAL_TITLE_IMAGE_DESCRIPTOR);
        }
        return initialTitleImage;
    }
}
