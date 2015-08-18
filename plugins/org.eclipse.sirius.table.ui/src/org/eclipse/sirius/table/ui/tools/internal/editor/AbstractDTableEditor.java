/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.table.metamodel.table.DTable;
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
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
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
    private static final String CONTRIBUTOR_ID = "org.eclipse.sirius.table.ui.EditorID"; //$NON-NLS-1$

    /** the context ID. */
    private static final String CONTEXT_ID = CONTRIBUTOR_ID + ".tableContext"; //$NON-NLS-1$

    /** This DTable model */
    private DTable tableModel;

    private IPartListener refreshAtOpeningActivator;

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

    @Override
    public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
        super.init(site, input);

        if (getTableModel() != null) {
            // Launch the refresh if needed
            if (DialectUIManager.INSTANCE.isRefreshActivatedOnRepresentationOpening()) {
                launchRefresh();
            }

            // In case of shared table representation, we notify the use of
            // table
            // representation locking
            initPermissionAuthority(getTableModel());
        }
    }

    @Override
    protected void configureCommandFactoryProviders() {
        /* get IEMFCommandFactories */
        emfCommandFactory = TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(getEditingDomain());

        /* We add a callback for UI stuffs */
        emfCommandFactory.setUserInterfaceCallBack(new EMFCommandFactoryUI());
    }

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
        /* initialize interpreter. */
        if (session != null) {
            InterpreterRegistry.prepareImportsFromSession(session.getInterpreter(), session);
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

    @Override
    public Image getFrozenRepresentationImage() {
        if (frozenRepresentationImage == null || frozenRepresentationImage.isDisposed()) {
            Image refreshImage = TableUIPlugin.Implementation.getImage(TableUIPlugin.Implementation.getBundledImageDescriptor("icons/" + DTableViewerManager.REFRESH_IMG + ".gif")); //$NON-NLS-1$ //$NON-NLS-2$
            List<Object> images = new ArrayList<Object>(2);
            images.add(refreshImage);
            Image lockByOtherOverlayIamge = SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied_overlay.gif")); //$NON-NLS-1$
            images.add(lockByOtherOverlayIamge);
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

    @Override
    protected void launchRefresh() {
        getEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(getEditingDomain(), new NullProgressMonitor(), getRepresentation()));
    }

    @Override
    public String getContributorId() {
        return CONTRIBUTOR_ID;
    }

    @Override
    protected void setRepresentation(URI uri, boolean loadOnDemand) {
        setTableModel(getDTable(uri, loadOnDemand));
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

    @Override
    public DRepresentation getRepresentation() {
        return tableModel;
    }

    /**
     * Get the table model.
     * 
     * @return the table model
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

    @Override
    public void validateRepresentation() {
        // TODO implement validation for Table Editor.
    }

    @Override
    protected void setAccessor(ModelAccessor accessor) {
        super.setAccessor(accessor);
        ((ITableCommandFactory) emfCommandFactory).setModelAccessor(this.accessor);
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

    @Override
    public AdapterFactory getAdapterFactory() {
        if (adapterFactory == null) {
            // Create an adapter factory that yields item providers.
            adapterFactory = TableUIPlugin.getPlugin().createAdapterFactory();
        }
        return adapterFactory;
    }

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
