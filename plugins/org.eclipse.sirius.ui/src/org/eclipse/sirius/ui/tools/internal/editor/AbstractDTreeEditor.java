/*******************************************************************************
 * Copyright (c) 2008, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;


import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.preferences.DesignerPreferencesKeys;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener2;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyWrapperPermissionAuthority;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.tools.api.permission.DRepresentationPermissionStatusListener;
import org.eclipse.sirius.tools.api.permission.DRepresentationPermissionStatusQuery;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditorDialogFactory;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.business.internal.dialect.TreeEditorDialogFactory;
import org.eclipse.sirius.ui.tools.api.properties.DTablePropertySheetpage;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.ISaveablesSource;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * Provides generic support for DTable and DTree editors. <BR>
 * Clients may extend this class.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public abstract class AbstractDTreeEditor extends EditorPart implements ISelectionListener, DialectEditor, IViewerProvider, ITabbedPropertySheetPageContributor, IEditingDomainProvider,
        IReusableEditor, SessionListener, ISaveablesSource, IPageListener {

    /** The PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY icon descriptor. */
    private static final ImageDescriptor LOCK_BY_ME_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation
            .getBundledImageDescriptor("icons/full/decorator/permission_granted_to_current_user_exclusively.gif");

    /** The PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY icon descriptor. */
    private static final ImageDescriptor LOCK_BY_OTHER_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied.gif");

    private static final ImageDescriptor NO_WRITE_PERMISSION_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_no_write.gif");;

    /**
     * This is the one adapter factory used for providing views of the model.
     */
    protected ComposedAdapterFactory adapterFactory;

    /**
     * Model accessor.
     */
    protected ModelAccessor accessor;

    /**
     * The tree viewer manager.
     */
    protected AbstractDTableViewerManager treeViewerManager;

    /**
     * The session.
     */
    protected Session session;

    /**
     * To increase performance we stop the update of the corresponding
     * propertySheetPage during the refresh of the DTable.
     */
    protected boolean propertiesUpdateEnabled;

    /**
     * .
     */
    protected boolean isClosing;

    /**
     * Property sheet page.
     */
    protected DTablePropertySheetpage currentPropertySheetpage;

    /** the EMF command factory provider. */
    protected ICommandFactory emfCommandFactory;

    /**
     * SessionManagerListener2.
     */
    protected final SessionManagerListener2 sessionManagerListener = new SessionManagerListener2.Stub() {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.session.SessionManagerListener2.Stub#notifyAddSession(org.eclipse.sirius.business.api.session.Session)
         */
        @Override
        public void notifyAddSession(final Session newSession) {

            /* we want to be notified only once */
            final IEditingSession editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(newSession);
            if (!editingSession.isOpen()) {
                editingSession.open();
                editingSession.attachEditor(AbstractDTreeEditor.this);
                /* remove this listener */
                SessionManager.INSTANCE.removeSessionsListener(this);
            }

        }
    };

    /**
     * The DialectEditorDialogFactory.
     */
    protected DialectEditorDialogFactory myDialogFactory = new TreeEditorDialogFactory(this);

    /**
     * The {@link UndoRedoActionHandler} used to provide appropriate undo and
     * redo Action Handlers to this editor.
     */
    protected UndoRedoActionHandler undoRedoActionHandler;

    /** Singleton instance of the initial Image for the NO_LOCK status. */
    protected Image initialTitleImage;

    /** Singleton instance of the Image for the REPRESENTATION_FROZEN status. */
    protected Image frozenRepresentationImage;

    /** CDO specific {@link IAuthorityListener}. */
    private IAuthorityListener dRepresentationLockStatusListener;

    /** Singleton instance of the Image for the LOCK_BY_ME status */
    private Image lockByMeImage;

    /** Singleton instance of the Image for the LOCK_BY_OTHER status */
    private Image lockByOtherImage;

    /**
     * Singleton instance of the image when DRepresentation has no write
     * permission
     */
    private Image noWritePermissionImage;

    /**
     * Default constructor.
     */
    public AbstractDTreeEditor() {
        super();
        emfCommandFactory = null;
    }

    /**
     * Editor picture.
     * 
     * @return initial image editor
     */
    public abstract Image getInitialImage();

    /**
     * Get lazily the Image for the LOCK_BY_ME status.
     * 
     * @return the Image for the LOCK_BY_ME status
     */
    protected Image getLockByMeImage() {
        if (lockByMeImage == null || lockByMeImage.isDisposed()) {
            lockByMeImage = SiriusEditPlugin.getPlugin().getImage(LOCK_BY_ME_IMAGE_DESCRIPTOR);
        }
        return lockByMeImage;
    }

    /**
     * Get lazily the Image for the LOCK_BY_OTHER status.
     * 
     * @return the Image for the LOCK_BY_OTHER status
     */
    protected Image getLockByOtherImage() {
        if (lockByOtherImage == null || lockByOtherImage.isDisposed()) {
            lockByOtherImage = SiriusEditPlugin.getPlugin().getImage(LOCK_BY_OTHER_IMAGE_DESCRIPTOR);
        }
        return lockByOtherImage;
    }

    /**
     * Lasily gets the image when there is no write permission of the
     * DRepresentation.
     * 
     * @return the image when there is no write permission of the
     *         DRepresentation.
     */
    protected Image getNoWritePermissionImage() {
        if (noWritePermissionImage == null || noWritePermissionImage.isDisposed()) {
            noWritePermissionImage = SiriusEditPlugin.getPlugin().getImage(NO_WRITE_PERMISSION_IMAGE_DESCRIPTOR);
        }
        return noWritePermissionImage;
    }

    /**
     * Get lazily the Image for the REPRESENTATION_FROZEN state.
     * 
     * @return the Image for the REPRESENTATION_FROZEN state
     */
    public abstract Image getFrozenRepresentationImage();

    public TransactionalEditingDomain getEditingDomain() {
        return session.getTransactionalEditingDomain();
    }

    public IActionBars getActionBars() {
        return ((EditingDomainActionBarContributor) getEditorSite().getActionBarContributor()).getActionBars();
    }

    /**
     * Performs the save and handles errors appropriately.
     * 
     * @param overwrite
     *            indicates whether or not overwriting is allowed
     * @param progressMonitor
     *            the monitor in which to run the operation
     * 
     */
    protected void performSave(final boolean overwrite, final IProgressMonitor progressMonitor) {
        session.save(progressMonitor);
    }

    /**
     * Tells if the {@link Resource} associated to the specified
     * <code>editorInput</code> exists or not.
     * 
     * @param editorInput
     *            the specifed {@link IEditorInput}
     * @return false if the {@link Resource} associated to the specified
     *         <code>editorInput</code> exists, true else
     */
    protected boolean isDeleted(final IEditorInput editorInput) {
        boolean isDeleted = false;
        if (getRepresentation() != null) {
            final Resource sessionResource = getRepresentation().eResource();
            if (sessionResource != null) {
                URI sessionResourceURI = sessionResource.getURI();
                boolean sessionResourceExists = sessionResource.getResourceSet().getURIConverter().exists(sessionResourceURI, Collections.emptyMap());
                // WORKAROUND with collab plugins
                if (!sessionResourceExists && new URIQuery(sessionResourceURI).isInMemoryURI()) {
                    isDeleted = false;
                } else {
                    isDeleted = !sessionResourceExists;
                }
            }
        }
        return isDeleted;
    }

    /**
     * We have to take care of the case when Eclipse starts up with a session
     * and diagram already open. see bug #1217
     * 
     * {@inheritDoc}
     */
    @Override
    public abstract void init(final IEditorSite site, final IEditorInput input) throws PartInitException;

    /**
     * Initialize CDO {@link IPermissionAuthority} and the title image if the
     * Table is already locked by the current user before opening.
     * 
     * @param representation
     *            the {@link DSemanticDecorator} that is opening.
     */
    protected void initCollaborativeIPermissionAuthority(DSemanticDecorator representation) {
        // This IPermissionAuthority is added only on shared
        // representations.
        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(representation.getTarget());
        dRepresentationLockStatusListener = new DRepresentationPermissionStatusListener(representation, this);
        permissionAuthority.addAuthorityListener(dRepresentationLockStatusListener);

        if (!permissionAuthority.canEditInstance(this.getRepresentation())) {
            notify(SessionListener.REPRESENTATION_EDITION_PERMISSION_DENIED);
        } else if (permissionAuthority instanceof ReadOnlyWrapperPermissionAuthority) {
            // Find the CDOLockBasedPermissionAuthority and investigate by
            // introspection if the diagram is "locked by me" in order to
            // display the proper title image
            ReadOnlyWrapperPermissionAuthority readOnlyWrapperPermissionAuthority = (ReadOnlyWrapperPermissionAuthority) permissionAuthority;
            IPermissionAuthority wrappedAuthority = readOnlyWrapperPermissionAuthority.getWrappedAuthority();
            if ("CDOLockBasedPermissionAuthority".equals(wrappedAuthority.getClass().getSimpleName())) {
                if (LockStatus.LOCKED_BY_ME.equals(wrappedAuthority.getLockStatus(representation))) {
                    notify(SessionListener.REPRESENTATION_EDITION_PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY);
                }
            }
        }
    }

    /**
     * Open the session.
     * 
     * @param rootElement
     *            the representation
     */
    protected void getSession(final DSemanticDecorator rootElement) {
        session = SessionManager.INSTANCE.getSession(rootElement.getTarget());
        if (session == null) {
            SiriusTransPlugin.getPlugin().error("a DSemanticDecorator must be owned by a Session" + rootElement, null);
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isDirty() {
        final boolean dirty = this.session.getStatus() == SessionStatus.DIRTY;
        return dirty;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        if (treeViewerManager != null) {
            treeViewerManager.getControl().setFocus();
            setEclipseWindowTitle();
        }
    }

    /**
     * Updates the title of the eclipse window.
     */
    protected void setEclipseWindowTitle() {
        // Updates the title of the eclipse window.
        // Removes the xmi id if the selected element and replace it with the
        // name of the tab.
        String title = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getText();
        final int end = title.lastIndexOf(".aird#");
        if (end > -1) {
            title = title.substring(0, end + 6) + this.getPartName() + " - Eclipse Platform";
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setText(title);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        // If not the active editor, ignore selection changed.
        if (this.equals(getSite().getPage().getActiveEditor())) {
            // Do something for the new selections?
            if (!part.equals(this)) {
                // Try to reveal the selection if this selection is made from
                // another part
                // Warning, we get the semanticElements in selection and not the
                // DLine ...
                // search the DLine with the semanticElements ...
                // viewer.setSelection(selection, true);
            }
        }
    }

    /**
     * Retrieves the descriptor for this editor.
     * 
     * @return the editor descriptor
     */
    protected final IEditorDescriptor getEditorDescriptor() {
        final IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();
        final IEditorDescriptor editorDesc = editorRegistry.findEditor(getSite().getId());
        return editorDesc;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectEditor#needsRefresh(int)
     */
    public boolean needsRefresh(int propId) {
        boolean result = false;
        if (propId == DialectEditor.PROP_REFRESH) {
            if (isAutoRefresh()) {
                result = true;
            }
        } else if (propId == DialectEditor.PROP_FORCE_REFRESH) {
            result = true;
        }
        return result;
    }

    /**
     * Launch refresh on representation.
     */
    protected abstract void launchRefresh();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
     */
    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes")
    final Class type) {
        Object result = super.getAdapter(type);
        if (result == null) {
            if (type == IPropertySheetPage.class) {
                currentPropertySheetpage = new DTablePropertySheetpage(this);
                result = currentPropertySheetpage;
            } else if (type == IDiagramCommandFactoryProvider.class) {
                result = emfCommandFactory;
            } else if (type == EditingDomain.class) {
                result = getEditingDomain();
            }
        }
        return result;
    }

    /**
     * Do a refresh of the properties view if it's activated.
     */
    public void forceRefreshProperties() {
        if (currentPropertySheetpage != null && currentPropertySheetpage.getControl() != null && !currentPropertySheetpage.getControl().isDisposed()) {
            currentPropertySheetpage.refresh();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.ui.viewer.IViewerProvider#getViewer()
     */
    public Viewer getViewer() {
        Viewer viewer = null;
        if (treeViewerManager != null) {
            viewer = treeViewerManager.getTreeViewer();
        }
        return viewer;
    }

    public AbstractDTableViewerManager getTableViewer() {
        return treeViewerManager;
    }

    /**
     * return accessor.
     * 
     * @return the accessor
     */
    public ModelAccessor getAccessor() {
        return accessor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#dispose()
     */
    @Override
    public void dispose() {

        if (dRepresentationLockStatusListener != null) {
            IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getRepresentation());
            permissionAuthority.removeAuthorityListener(dRepresentationLockStatusListener);
        }

        isClosing = true;
        SessionManager.INSTANCE.removeSessionsListener(sessionManagerListener);

        // Remove a listener to selection
        getSite().getPage().removeSelectionListener(this);

        // Disposing the UndoredoActionHandler
        if (this.undoRedoActionHandler != null) {
            this.undoRedoActionHandler.dispose();
        }

        if (session != null) {
            session.removeListener(this);
            final IEditingSession sess = SessionUIManager.INSTANCE.getUISession(session);
            if (sess != null) {
                sess.detachEditor(this);
            }
        }
        super.dispose();
        if (getTableViewer() != null) {
            getTableViewer().dispose();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
     */
    @Override
    public void setInput(final IEditorInput input) {
        super.setInput(input);
        // Set the context for the command stack (See
        // org.eclipse.gmf.runtime.diagram.ui.partsDiagramEditor.setInput for
        // details)
        // configureTableEditDomain();

        Viewer viewer = getViewer();
        if (viewer != null) {
            viewer.refresh();
        }

        // Update the tab's icon according to LockStatus
        DRepresentation representation = getRepresentation();
        if (representation instanceof DSemanticDecorator) {
            DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) representation;
            DRepresentationPermissionStatusQuery dRepresentationPermissionStatusQuery = new DRepresentationPermissionStatusQuery(dSemanticDecorator);
            IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dSemanticDecorator);
            LockStatus lockStatus = LockStatus.NOT_LOCKED;
            LockStatus dSemanticDecoratorLockStatus = permissionAuthority.getLockStatus(dSemanticDecorator);
            if (dSemanticDecoratorLockStatus != LockStatus.NOT_LOCKED) {
                lockStatus = dSemanticDecoratorLockStatus;
            }
            int associatedSessionListenerEvent = dRepresentationPermissionStatusQuery.getAssociatedSessionListenerEvent(lockStatus);
            notify(associatedSessionListenerEvent);
        }

    }

    public AdapterFactory getAdapterFactory() {
        return adapterFactory;
    }

    /**
     * Set the update status.
     * 
     * @param enabled
     *            the updateEnabled to set
     */
    public void enablePropertiesUpdate(final boolean enabled) {
        propertiesUpdateEnabled = enabled;
    }

    /**
     * Get the update status.
     * 
     * @return the update status
     */
    public boolean isPropertiesUpdateEnabled() {
        return propertiesUpdateEnabled;
    }

    /**
     * {@inheritDoc}
     */
    public void notify(final int changeKind) {
        AbstractDTreeEditorSessionListenerDelegate abstractDTreeEditorSessionListenerDelegate = new AbstractDTreeEditorSessionListenerDelegate(this, changeKind);
        if (Display.getCurrent() == null) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(abstractDTreeEditorSessionListenerDelegate);
        } else {
            abstractDTreeEditorSessionListenerDelegate.run();
        }
    }

    /**
     * Overridden to change the visibility of the inherited method.
     * 
     * {@inheritDoc}
     */
    @Override
    public void setTitleImage(Image titleImage) {
        if (!getTitleImage().equals(titleImage)) {
            super.setTitleImage(titleImage);
        }
    }

    /**
     * Update a property.
     * 
     * @param notificationKind
     *            the kind of property changed
     */
    protected void firePropertyChangeInUIThread(final int notificationKind) {
        if (notificationKind == PROP_TITLE) {
            setPartName(getRepresentation().getName());
        }
        firePropertyChange(notificationKind);
    }

    protected IEditingSession getUISession() {
        return SessionUIManager.INSTANCE.getUISession(session);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.EditorPart#isSaveOnCloseNeeded()
     */
    @Override
    public boolean isSaveOnCloseNeeded() {
        /*
         * No call to editingSession.needToBeSavedOnClose(this) here to allow
         * close all detection.
         */
        return isDirty();
    }

    /**
     * .
     * 
     * @return if is auto refresh
     */
    protected boolean isAutoRefresh() {
        boolean autoRefresh = false;
        try {
            autoRefresh = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, DesignerPreferencesKeys.PREF_AUTO_REFRESH.name(), autoRefresh, null);
        } catch (final IllegalArgumentException e) {
            SiriusTransPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusTransPlugin.PLUGIN_ID, IStatus.OK, e.getMessage(), e));
        }
        return autoRefresh;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectEditor#setDialogFactory(org.eclipse.sirius.ui.business.api.dialect.DialectEditorDialogFactory)
     */
    public void setDialogFactory(DialectEditorDialogFactory dialogFactory) {
        myDialogFactory = dialogFactory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.ISaveablesSource#getSaveables()
     */
    public Saveable[] getSaveables() {
        if (session != null && session.isOpen()) {
            IEditingSession uiSession = getUISession();
            if (uiSession instanceof ISaveablesSource) {
                return ((ISaveablesSource) uiSession).getSaveables();
            }
        }
        return new Saveable[0];
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.ISaveablesSource#getActiveSaveables()
     */
    public Saveable[] getActiveSaveables() {
        return getSaveables();
    }

    /**
     * {@inheritDoc}
     * 
     * @see ISaveablePart2#promptToSaveOnClose()
     */
    public int promptToSaveOnClose() {
        int choice = ISaveablePart2.DEFAULT;
        if (session != null && session.isOpen()) {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            // Close all && Still open elsewhere detection.
            if (uiSession != null && uiSession.needToBeSavedOnClose(this)) {
                choice = uiSession.promptToSaveOnClose();
            }
        }

        return choice;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        // setting up a UndoActionHandler
        // and a RedoActionHandler to avoid
        // issues with Undo and Redo
        setUpUndoRedoActionHandler();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPageListener#pageOpened(org.eclipse.ui.IWorkbenchPage)
     */
    public void pageOpened(IWorkbenchPage page) {

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPageListener#pageActivated(org.eclipse.ui.IWorkbenchPage)
     */
    public void pageActivated(IWorkbenchPage page) {
        // As the page has been activated, we now can create the
        // UndoRedoActionHandler
        setUpUndoRedoActionHandler();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPageListener#pageClosed(org.eclipse.ui.IWorkbenchPage)
     */
    public void pageClosed(IWorkbenchPage page) {

    }

    /**
     * Initializes an UndoRedoActionHandler, which purpose is to provide
     * appropriate undo and redo Action Handlers to this editor.
     */
    protected void setUpUndoRedoActionHandler() {
        // Undo/Redo Issues with Trees and Tables
        IWorkbenchWindow workbenchWindow = getEditorSite().getWorkbenchWindow();
        if (workbenchWindow != null) {
            IWorkbenchPage part = workbenchWindow.getActivePage();
            if (part != null) {
                undoRedoActionHandler = new UndoRedoActionHandler(this.getEditingDomain(), getEditorSite());
            }
        }
        // If the UndoRedoActionHandler was not create
        if (undoRedoActionHandler == null) {
            // We register the current editor as listening the page opening
            // The UndoRedoActionHandler will be created whenever the page will
            // open
            getEditorSite().getWorkbenchWindow().addPageListener(this);
        }
    }

    /**
     * Indicates if the given TreeItem list contains (at any level) the given
     * element. If it's the case, returns the TreeItem containing this element.
     * 
     * @param items
     *            the TreeItems to look into
     * @param element
     *            the searched element
     * @return the TreeItem containing the searched element if one found, null
     *         otherwise
     */
    protected TreeItem contains(TreeItem[] items, Object element) {
        TreeItem searchedTreeItem = null;
        for (int i = 0; i < items.length && (searchedTreeItem == null); i++) {
            if ((items[i].getData() != null) && items[i].getData().equals(element)) {
                searchedTreeItem = items[i];
            }
            if (searchedTreeItem == null) {
                searchedTreeItem = contains(items[i].getItems(), element);
            }
        }
        return searchedTreeItem;
    }

    public DialectEditorDialogFactory getDialogFactory() {
        return myDialogFactory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.componentization.ViewpointRegistryListener2#modelerDesciptionFilesLoaded()
     */
    protected void modelerDescriptionFilesLoaded() {
        if (isAutoRefresh()) {
            Job refreshJob = new Job("Refresh ") {

                @Override
                protected IStatus run(IProgressMonitor monitor) {
                    getEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(getEditingDomain(), monitor, getRepresentation()));
                    Display.getDefault().asyncExec(new Runnable() {
                        public void run() {
                            getViewer().refresh();
                        }
                    });
                    return Status.OK_STATUS;
                }

            };
            refreshJob.schedule();
        }
    }
}
