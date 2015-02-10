/*******************************************************************************
 * Copyright (c) 2008, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor;

import java.lang.reflect.InvocationTargetException;
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.table.business.internal.refresh.DTableElementSynchronizerSpec;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementSynchronizer;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactoryProvider;
import org.eclipse.sirius.table.tools.api.command.TableCommandFactoryService;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.commands.EMFCommandFactoryUI;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTableEditorUtil;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
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
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Provides generic support for DTable editors. <BR>
 * Clients may extend this class.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public abstract class AbstractDTableEditor extends AbstractDTreeEditor implements DTableEditor {

    /** The contributor ID */
    private static final String CONTRIBUTOR_ID = "org.eclipse.sirius.table.ui.EditorID";

    /** the context ID. */
    private static final String CONTEXT_ID = CONTRIBUTOR_ID + ".tableContext";

    /**
     * This is the one adapter factory used for providing views of the model (as
     * in EcoreEditor).
     */
    protected AdapterFactory adapterFactory;

    /** This is the one adapter factory used for providing views of the model */
    private DTable tableModel;

    private IPartListener refreshAtOpeningActivator;

    private IInterpreter getInterpreter() {
        return SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(getTableModel().getTarget());
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

                /*
                 * 1GEUSSR: ITPUI:ALL - User should never loose changes made in
                 * the editors. Changed Behavior to make sure that if called
                 * inside a regular save (because of deletion of input element)
                 * there is a way to report back to the caller.
                 */
                performSaveAs(progressMonitor);

            } else {

                final Shell shell = getSite().getShell();
                final String title = Messages.dTableEditor_ErrorSaveDeletedTitle;
                final String msg = Messages.dTableEditor_ErrorSaveDeletedMessage;
                MessageDialog.openError(shell, title, msg);
            }

        } else {
            // Update the readonly state
            // updateState(getEditorInput());
            // Valide the state
            // validateState(getEditorInput());
            performSave(false, progressMonitor);
        }
        // Indicates that the editor is saved
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
        if (isDeleted(input) && original != null) {
            final String message = NLS.bind(Messages.dTableEditor_SavingDeletedFile, original.getName());
            dialog.setErrorMessage(null);
            dialog.setMessage(message, IMessageProvider.WARNING);
        }
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
                    MessageDialog.openWarning(shell, Messages.dTableEditor_SaveAsErrorTitle, Messages.dTableEditor_SaveAsErrorMessage);
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
            try {
                new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, new WorkspaceModifyOperation() {

                    @Override
                    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                        performSaveAs(monitor);
                    }
                });

            } catch (final InterruptedException e) {
                IStatus status = new Status(IStatus.ERROR, TableUIPlugin.ID, e.getLocalizedMessage(), e);
                TableUIPlugin.getPlugin().getLog().log(status);
            } catch (final InvocationTargetException e) {
                IStatus status = new Status(IStatus.ERROR, TableUIPlugin.ID, e.getLocalizedMessage(), e.getTargetException());
                TableUIPlugin.getPlugin().getLog().log(status);
            }
        }
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
            SessionEditorInput sessionEditorInput = (SessionEditorInput) input;
            final URI uri = sessionEditorInput.getURI();
            this.session = sessionEditorInput.getSession();
            setTableModel(getDTable(uri, false));
        }

        setInput(input);

        if (session != null) {
            session.addListener(this);
        }

        configureCommandFactoryProviders();

        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(this.session);
        uiSession.open();
        uiSession.attachEditor(this);
        setAccessor(SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(getTableModel()));

        if (getTableModel() != null) {
            /*
             * let's activate the model listening
             */
            final DTableElementSynchronizer sync = new DTableElementSynchronizerSpec(accessor, getInterpreter());
            getTableModel().activate(sync);

            /* Update title. Semantic table could have been renamed */
            notify(PROP_TITLE);

            // Launch the refresh if needed
            if (DialectUIManager.INSTANCE.isRefreshActivatedOnRepresentationOpening()) {
                launchRefresh();
            }

            initialTitleImage = getTitleImage();

            // In case of shared table representation, we notify the use of tree
            // representation locking
            initCollaborativeIPermissionAuthority(getTableModel());
        }
    }

    private void configureCommandFactoryProviders() {
        /* get IEMFCommandFactories */
        emfCommandFactory = TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(getEditingDomain());

        /* We add a callback for UI stuffs */
        emfCommandFactory.setUserInterfaceCallBack(new EMFCommandFactoryUI());
    }

    /**
     * Get the DTable corresponding to this URI
     * 
     * @param uri
     *            the URI to resolve.
     * @param loadOnDemand
     *            whether to create and load the resource, if it doesn't already
     *            exists.
     * @return the DTable resource resolved by the URI, or <code>null</code> if
     *         there isn't one and it's not being demand loaded.
     */
    private DTable getDTable(final URI uri, final boolean loadOnDemand) {
        DTable result = null;
        final Resource resource = getEditingDomain().getResourceSet().getResource(uri.trimFragment(), loadOnDemand);
        if (resource != null && resource.isLoaded()) {
            if (uri.fragment() != null) {
                final EObject rootElement = resource.getEObject(uri.fragment());
                if (rootElement instanceof DTable) {
                    result = (DTable) rootElement;
                }
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        super.createPartControl(parent);

        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CREATE_TABLE_KEY);
        if (getTableModel() == null) {
            /* eclipse was closed with an editor opened and not saved */
            final Label errorLabel = new Label(parent, SWT.CENTER);
            errorLabel.setText("This table was not saved. You can close the editor");
            return;
        }
        treeViewerManager = new DTableViewerManager(parent, getTableModel(), getEditingDomain(), accessor, (ITableCommandFactory) emfCommandFactory, this);
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CREATE_TABLE_KEY);
        getSite().setSelectionProvider(treeViewerManager.getTreeViewer());
        /* initialize Java Service. */
        EObject semantic = null;
        if (tableModel != null) {
            semantic = tableModel.getTarget();
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

        refreshAtOpeningActivator = new RefreshAtOpeningActivator(session, this);
        getSite().getPage().addPartListener(refreshAtOpeningActivator);

        // Activate the context for this site.
        IContextService contextService = (IContextService) getSite().getService(IContextService.class);
        contextService.activateContext(CONTEXT_ID);
    }

    /**
     * Overridden to update the UI part when the {@link DTable} model is changed
     * outside of a EMF Command (which notify DTableContentAdapter) in case of
     * collab model.
     * 
     * {@inheritDoc}
     */
    @Override
    public void setInput(IEditorInput input) {
        super.setInput(input);

        // Make sure that the viewer columns are up-to-date
        if (treeViewerManager != null) {
            enablePropertiesUpdate(false);
            DTableEditorUtil.updateViewerColumns(this.treeViewerManager, this.getTableModel());
            enablePropertiesUpdate(true);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor#getFrozenRepresentationImage()
     */
    @Override
    public Image getFrozenRepresentationImage() {
        if (frozenRepresentationImage == null || frozenRepresentationImage.isDisposed()) {
            Image refreshImage = TableUIPlugin.Implementation.getImage(TableUIPlugin.Implementation.getBundledImageDescriptor("icons/" + DTableViewerManager.REFRESH_IMG + ".gif"));
            List<Object> images = new ArrayList<Object>(2);
            images.add(refreshImage);
            Image lockByOtherOverlayIamge = SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied_overlay.gif"));
            images.add(lockByOtherOverlayIamge);
            ImageDescriptor composedImageDescriptor = new ComposedImageDescriptor(new ComposedImage(images));
            frozenRepresentationImage = SiriusEditPlugin.getPlugin().getImage(composedImageDescriptor);
        }
        return frozenRepresentationImage;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor#getControl()
     */
    public Control getControl() {
        TreeViewer treeViewer = this.getTableViewer().getTreeViewer();
        return treeViewer.getTree();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        if (treeViewerManager != null) {
            super.setFocus();
            checkSemanticAssociation();
        }
    }

    private void checkSemanticAssociation() {
        if (tableModel == null || tableModel.eResource() == null || tableModel.getTarget() == null || tableModel.getTarget().eResource() == null) {
            /*
             * The element has been deleted, we should close the editor
             */
            myDialogFactory.editorWillBeClosedInformationDialog(getSite().getShell());
            DialectUIManager.INSTANCE.closeEditor(this, false);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor#launchRefresh()
     */
    @Override
    protected void launchRefresh() {
        getEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(getEditingDomain(), new NullProgressMonitor(), getTableModel()));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
     */
    public String getContributorId() {
        return AbstractDTableEditor.CONTRIBUTOR_ID;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectEditor#getRepresentation()
     */
    public DRepresentation getRepresentation() {
        return tableModel;
    }

    /**
     * Get the tableModel.
     * 
     * @return the tableModel
     */
    public DTable getTableModel() {
        return tableModel;
    }

    /**
     * Set the tableModel.
     * 
     * @param tableModel
     *            the tableModel to set
     */
    protected void setTableModel(final DTable tableModel) {
        this.tableModel = tableModel;
    }

    /**
     * {@inheritDoc}
     */
    public void validateRepresentation() {
        // TODO implement validation for Table Editor.
    }

    /**
     * @param accessor
     *            the accessor to set
     */
    private void setAccessor(final ModelAccessor accessor) {
        this.accessor = accessor;
        ((ITableCommandFactory) emfCommandFactory).setModelAccessor(this.accessor);
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
        final String tableURI = marker.getAttribute(TraceabilityMarkerNavigationProvider.REPRESENTATION_URI, null);
        final String elementId = marker.getAttribute(TraceabilityMarkerNavigationProvider.REPRESENTATION_ELEMENT_ID, null);

        if (tableURI == null || elementId == null) {
            return;
        }

        final URI markerTableURI = URI.createURI(tableURI);
        final DTable markerTable = (DTable) this.getTableModel().eResource().getEObject(markerTableURI.fragment());

        if (markerTable != null) {
            EObject searchedElement = markerTable.eResource().getEObject(elementId);
            TreeItem contains = contains(this.getTableViewer().getTreeViewer().getTree().getItems(), searchedElement);
            if (contains != null) {
                ISelection selection = new TreeSelection(getTreePathFromItem(contains));
                // FIXME this always throws a Runtime Exception because Tree is
                // busy. However, tree is correctly expanded
                this.getTableViewer().getTreeViewer().setSelection(selection);
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
            if (type == ITableCommandFactoryProvider.class) {
                result = emfCommandFactory;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor#getAdapterFactory()
     */
    public AdapterFactory getAdapterFactory() {
        if (adapterFactory == null) {
            // Create an adapter factory that yields item providers.
            adapterFactory = TableUIPlugin.getPlugin().createAdapterFactory();
        }
        return adapterFactory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (getAdapterFactory() instanceof IDisposable) {
            ((IDisposable) getAdapterFactory()).dispose();
        }
        if (refreshAtOpeningActivator != null) {
            getSite().getPage().removePartListener(refreshAtOpeningActivator);
            refreshAtOpeningActivator = null;
        }
    }
}
