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
package org.eclipse.sirius.ui.tools.internal.editor;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.permission.DRepresentationPermissionStatusListener;
import org.eclipse.sirius.tools.api.permission.DRepresentationPermissionStatusQuery;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditorDialogFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
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
import org.eclipse.ui.IWorkbenchPage;
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
 */
public abstract class AbstractDTreeEditor extends EditorPart implements DialectEditor, IViewerProvider, ITabbedPropertySheetPageContributor, IEditingDomainProvider, IReusableEditor, SessionListener,
        ISaveablesSource, IPageListener {

    /** The PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY icon descriptor. */
    private static final ImageDescriptor LOCK_BY_ME_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation
            .getBundledImageDescriptor("icons/full/decorator/permission_granted_to_current_user_exclusively.gif"); //$NON-NLS-1$

    /** The PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY icon descriptor. */
    private static final ImageDescriptor LOCK_BY_OTHER_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied.gif"); //$NON-NLS-1$

    private static final ImageDescriptor NO_WRITE_PERMISSION_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_no_write.gif");; //$NON-NLS-1$

    /**
     * This is the one adapter factory used for providing views of the model.
     */
    protected AdapterFactory adapterFactory;

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
     * SessionManagerListener.
     */
    protected final SessionManagerListener sessionManagerListener = new SessionManagerListener.Stub() {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.session.SessionManagerListener.Stub#notifyAddSession(org.eclipse.sirius.business.api.session.Session)
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

    /** The {@link IAuthorityListener}. */
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

    private int choice = ISaveablePart2.DEFAULT;

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
     * Lazily gets the image when there is no write permission of the
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

    @Override
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
            setRepresentation(uri, false);
        } else if (input instanceof URIEditorInput) {
            /* This happens when Eclipse is launched with an open tree editor */
            final URI uri = ((URIEditorInput) input).getURI();
            setRepresentation(uri, true);
        }

        setInput(input);

        if (session != null) {
            session.addListener(this);
        }

        configureCommandFactoryProviders();

        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(this.session);
        uiSession.open();
        uiSession.attachEditor(this);
        setAccessor(SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(getRepresentation()));

        DRepresentation representation = getRepresentation();
        if (representation != null) {
            /* Update title. Semantic table could have been renamed */
            notify(PROP_TITLE);

            initialTitleImage = getTitleImage();
        }
    }

    /**
     * Configure and set the command factory.
     */
    protected abstract void configureCommandFactoryProviders();

    /**
     * Initialize {@link IPermissionAuthority} and the title image if the Table
     * is already locked by the current user before opening.
     * 
     * @param representation
     *            the {@link DSemanticDecorator} that is opening.
     */
    protected void initPermissionAuthority(DSemanticDecorator representation) {
        // This IPermissionAuthority is added only on shared
        // representations.
        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(representation.getTarget());
        dRepresentationLockStatusListener = new DRepresentationPermissionStatusListener(representation, this);
        permissionAuthority.addAuthorityListener(dRepresentationLockStatusListener);

        if (!permissionAuthority.canEditInstance(this.getRepresentation())) {
            notify(SessionListener.REPRESENTATION_EDITION_PERMISSION_DENIED);
        } else if (LockStatus.LOCKED_BY_ME.equals(permissionAuthority.getLockStatus(representation))) {
            notify(SessionListener.REPRESENTATION_EDITION_PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY);
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

    @Override
    public boolean isDirty() {
        final boolean dirty = this.session.getStatus() == SessionStatus.DIRTY;
        return dirty;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public void setFocus() {
        if (treeViewerManager != null) {
            /*
             * A regression in Kepler can cause an NPE inside the
             * treeViewerManager.getControl().setFocus() below. The guard
             * condition is a workaround, which seems to fix the problem (or at
             * least the symptom) in our tests. See
             * https://bugs.eclipse.org/bugs/show_bug.cgi?id=378846#c16
             */
            AbstractDTreeViewer viewer = treeViewerManager.getTreeViewer();
            if (viewer != null && viewer.getTree() != null && viewer.getTree().getTopItem() != null) {
                treeViewerManager.getControl().setFocus();
            }
            setEclipseWindowTitle();

            // Resolve proxy model after aird reload.
            DRepresentation representation = getRepresentation();
            if (representation != null && representation.eIsProxy() && session != null) {
                IEditorInput editorInput = getEditorInput();
                if (editorInput instanceof URIEditorInput) {
                    URIEditorInput sessionEditorInput = (URIEditorInput) editorInput;
                    final URI uri = sessionEditorInput.getURI();
                    setRepresentation(uri, false);
                    IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);

                    // Get the new representation
                    representation = getRepresentation();
                    if (uiSession != null && representation != null) {
                        // Reinit dialect editor closer and other
                        // IEditingSession mechanisms.
                        uiSession.detachEditor(this);
                        uiSession.attachEditor(this);
                    }

                    // Reinit the lock status listener.
                    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(representation);
                    if (representation instanceof DSemanticDecorator && permissionAuthority != null && dRepresentationLockStatusListener != null) {
                        permissionAuthority.removeAuthorityListener(dRepresentationLockStatusListener);
                        initPermissionAuthority((DSemanticDecorator) representation);
                    }
                }
            }

            checkSemanticAssociation();
        }
    }

    /**
     * Retrieve and set the representation from the given URI.
     * 
     * @param uri
     *            the URI to resolve.
     * @param loadOnDemand
     *            whether to create and load the resource, if it doesn't already
     *            exists.
     */
    protected abstract void setRepresentation(URI uri, boolean loadOnDemand);

    private void checkSemanticAssociation() {
        DRepresentation representation = getRepresentation();
        boolean shouldClose = representation == null || representation.eResource() == null;
        if (!shouldClose && representation instanceof DSemanticDecorator) {
            EObject semanticTarget = ((DSemanticDecorator) representation).getTarget();
            shouldClose = semanticTarget == null || semanticTarget.eResource() == null;
        }

        if (shouldClose) {
            /*
             * The element has been deleted, we should close the editor
             */
            myDialogFactory.editorWillBeClosedInformationDialog(getSite().getShell());
            DialectUIManager.INSTANCE.closeEditor(this, false);
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
        final int end = title.lastIndexOf(".aird#"); //$NON-NLS-1$
        if (end > -1) {
            title = title.substring(0, end + 6) + this.getPartName() + " - Eclipse Platform";
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setText(title);
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

    @Override
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

    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") final Class type) {
        Object result = super.getAdapter(type);
        if (result == null) {
            if (type == IPropertySheetPage.class) {
                currentPropertySheetpage = new DTablePropertySheetpage(this);
                result = currentPropertySheetpage;
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

    @Override
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

    @Override
    public void dispose() {

        if (dRepresentationLockStatusListener != null) {
            IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getRepresentation());
            permissionAuthority.removeAuthorityListener(dRepresentationLockStatusListener);
        }

        isClosing = true;
        SessionManager.INSTANCE.removeSessionsListener(sessionManagerListener);

        // Disposing the UndoredoActionHandler
        if (this.undoRedoActionHandler != null) {
            this.undoRedoActionHandler.dispose();
        }
        super.dispose();
        if (getTableViewer() != null) {
            getTableViewer().dispose();
        }
        // We need to perform the detachEditor after having disposed the viewer
        // and the editor input to avoid a refresh. A refresh can occurs in the
        // case where the detach triggers the reload of the modified resources
        // (if choice == ISaveablePart2.NO)
        if (session != null) {
            session.removeListener(this);
            final IEditingSession sess = SessionUIManager.INSTANCE.getUISession(session);
            if (sess != null) {
                sess.detachEditor(this, choice == ISaveablePart2.NO);
            }
        }

    }

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

    @Override
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
            autoRefresh = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), autoRefresh, null);
        } catch (final IllegalArgumentException e) {
            SiriusTransPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusTransPlugin.PLUGIN_ID, IStatus.OK, e.getMessage(), e));
        }
        return autoRefresh;
    }

    @Override
    public void setDialogFactory(DialectEditorDialogFactory dialogFactory) {
        myDialogFactory = dialogFactory;
    }

    @Override
    public Saveable[] getSaveables() {
        if (session != null && session.isOpen()) {
            IEditingSession uiSession = getUISession();
            if (uiSession instanceof ISaveablesSource) {
                return ((ISaveablesSource) uiSession).getSaveables();
            }
        }
        return new Saveable[0];
    }

    @Override
    public Saveable[] getActiveSaveables() {
        return getSaveables();
    }

    @Override
    public int promptToSaveOnClose() {
        choice = ISaveablePart2.DEFAULT;
        if (session != null && session.isOpen()) {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            // Close all && Still open elsewhere detection.
            if (uiSession != null && uiSession.needToBeSavedOnClose(this)) {
                choice = uiSession.promptToSaveOnClose();
            }
        }

        return choice;
    }

    @Override
    public void createPartControl(Composite parent) {
        // setting up a UndoActionHandler
        // and a RedoActionHandler to avoid
        // issues with Undo and Redo
        setUpUndoRedoActionHandler();
    }

    @Override
    public void pageOpened(IWorkbenchPage page) {

    }

    @Override
    public void pageActivated(IWorkbenchPage page) {
        // As the page has been activated, we now can create the
        // UndoRedoActionHandler
        setUpUndoRedoActionHandler();
    }

    @Override
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

    @Override
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
                    return Status.OK_STATUS;
                }

            };
            refreshJob.schedule();
        }
    }

    /**
     * Set the accessor.
     * 
     * @param accessor
     *            the accessor to set
     */
    protected void setAccessor(ModelAccessor accessor) {
        this.accessor = accessor;
    }
}
