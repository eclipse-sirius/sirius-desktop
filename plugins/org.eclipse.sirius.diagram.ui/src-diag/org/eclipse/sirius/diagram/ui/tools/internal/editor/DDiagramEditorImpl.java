/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.ObjectUndoContext;
import org.eclipse.core.commands.operations.UndoContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.rulers.RulerComposite;
import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.IObjectActionDelegateWrapper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.tools.api.command.DiagramCommandFactoryService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.tools.api.command.view.HideDDiagramElement;
import org.eclipse.sirius.diagram.tools.api.command.view.HideDDiagramElementLabel;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.command.RefreshDiagramOnOpeningCommand;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.listener.DiagramHeaderPostCommitListener;
import org.eclipse.sirius.diagram.ui.edit.internal.part.listener.VisibilityPostCommitListener;
import org.eclipse.sirius.diagram.ui.internal.refresh.SiriusDiagramSessionEventBroker;
import org.eclipse.sirius.diagram.ui.internal.refresh.SynchronizeGMFModelCommand;
import org.eclipse.sirius.diagram.ui.internal.refresh.layout.SiriusCanonicalLayoutHandler;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.GMFDiagramUpdater;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditorUtil;
import org.eclipse.sirius.diagram.ui.part.ValidateAction;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.ToolFilter;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.api.properties.PropertiesService;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromModelWithHookAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteWithHookAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementLabelAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.RevealOutlineElementsAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.RevealOutlineLabelsAction;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.EMFCommandFactoryUI;
import org.eclipse.sirius.diagram.ui.tools.internal.dnd.DragAndDropWrapper;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.header.DiagramHeaderComposite;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.Tabbar;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.TabbarRefresher;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramRootEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.menu.DiagramEditorContextMenuProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.menu.DiagramMenuUpdater;
import org.eclipse.sirius.diagram.ui.tools.internal.outline.QuickOutlineControl;
import org.eclipse.sirius.diagram.ui.tools.internal.outline.SiriusInformationPresenter;
import org.eclipse.sirius.diagram.ui.tools.internal.outline.SiriusQuickOutlineInformationProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.PaletteManagerImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.SiriusPaletteViewer;
import org.eclipse.sirius.diagram.ui.tools.internal.part.SiriusDiagramGraphicalViewer;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.CustomSiriusDocumentProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.views.outlineview.DiagramOutlineWithBookPages;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline.OutlineComparator;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline.OutlineContentProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline.OutlineLabelProvider;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.EditingDomainUndoContext;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.api.permission.DRepresentationPermissionStatusListener;
import org.eclipse.sirius.tools.api.permission.DRepresentationPermissionStatusQuery;
import org.eclipse.sirius.tools.api.ui.property.IPropertiesProvider;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditorDialogFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.marker.TraceabilityMarkerNavigationProvider;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInputFactory;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.internal.editor.SelectDRepresentationElementsListener;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.ISaveablesSource;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * The diagram editor.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class DDiagramEditorImpl extends SiriusDiagramEditor implements DDiagramEditor, ISelectionListener, SessionListener {

    protected class DDiagramEditorTransferDropTargetListener extends AbstractTransferDropTargetListener {

        /**
         * Constructs a new AbstractTransferDropTargetListener and sets the
         * EditPartViewer and Transfer. The Viewer's Control should be the Drop
         * target.
         * 
         * @param viewer
         *            the EditPartViewer
         * @param xfer
         *            the Transfer
         */
        protected DDiagramEditorTransferDropTargetListener(EditPartViewer viewer, Transfer xfer) {
            super(viewer, xfer);
        }

        @Override
        protected Request createTargetRequest() {
            final ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_DROP);
            final List<EditPart> list = new ArrayList<EditPart>(1);
            if (getCurrentEvent().data instanceof IStructuredSelection) {
                list.add(new DragAndDropWrapper(getCurrentEvent().data));
            } else if (getTransfer() instanceof LocalSelectionTransfer) {
                final LocalSelectionTransfer localSelectionTransfer = (LocalSelectionTransfer) getTransfer();
                if (localSelectionTransfer.getSelection() instanceof IStructuredSelection) {
                    list.add(new DragAndDropWrapper(localSelectionTransfer.getSelection()));
                }
            }
            request.setEditParts(list);
            return request;
        }

        @Override
        public void setCurrentEvent(DropTargetEvent currentEvent) {
            // Look into the DropTargetEvent for a LocalSelectionTransfer
            if (currentEvent != null && currentEvent.getSource() instanceof DropTarget) {
                Transfer[] transferArray = ((DropTarget) currentEvent.getSource()).getTransfer();
                for (Transfer transfer : transferArray) {
                    if (transfer instanceof LocalSelectionTransfer && ((LocalSelectionTransfer) transfer).getSelection() instanceof TreeSelection) {
                        TreeSelection localSelectionTransfer = (TreeSelection) ((LocalSelectionTransfer) transfer).getSelection();
                        // If there is a DSemanticDecorator among the selected
                        // element then the source view is a DRepresentation and
                        // we do not want to modify the performed operation
                        if (Iterables.isEmpty(Iterables.filter(localSelectionTransfer.toList(), DSemanticDecorator.class))) {
                            currentEvent.detail = DND.DROP_COPY;
                        }
                    }
                }
            }
            super.setCurrentEvent(currentEvent);
        }

        @Override
        protected void updateTargetRequest() {
            final Request request = getTargetRequest();
            ((ChangeBoundsRequest) request).setLocation(getDropLocation());
        }
    }

    /**
     * The ID of the editor in the Editor Registry.
     */
    public static final String ID = DDiagramEditor.EDITOR_ID;

    /**
     * My undo context.
     */
    protected IUndoContext undoContext;

    /**
     * This is the one adapter factory used for providing views of the model (as
     * in EcoreEditor).
     */
    protected AdapterFactory adapterFactory;

    private Session session;

    /** The key handler */
    private KeyHandler keyHandler;

    private TemplateTransferDragSourceListener paletteTransferDragSourceListener;

    private SiriusPaletteToolDropTargetListener paletteTransferDropTargetListener;

    /**
     * The abstract transfer drop target listener.
     */
    private AbstractTransferDropTargetListener transferDropTargetListener;

    /** the emf command factory provider */
    private IDiagramCommandFactoryProvider emfCommandFactoryProvider;

    private boolean isClosing;

    private IPermissionAuthority authority;

    private IAuthorityListener dRepresentationLockStatusListener;

    private final SessionManagerListener sessionManagerListener = new SessionManagerListener.Stub() {

        @Override
        public void notifyAddSession(final Session newSession) {

            /* we want to be notified only once */
            final IEditingSession editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(newSession);
            if (!editingSession.isOpen()) {
                editingSession.open();
                editingSession.attachEditor(DDiagramEditorImpl.this);
                /*
                 * need to reinit command factory provider to take the right
                 * model accesor
                 */
                initCommandFactoryProviders();
                /* remove this listener */
                SessionManager.INSTANCE.removeSessionsListener(this);
            }
        }
    };

    private TabbarRefresher tabbarPostCommitListener;

    private DiagramHeaderPostCommitListener diagramHeaderPostCommitListener;

    private VisibilityPostCommitListener visibilityPostCommitListener;

    private GMFDiagramUpdater gmfDiagramUpdater;

    private DialectEditorDialogFactory myDialogFactory = new DiagramDialectEditorDialogFactory(this);

    private final IOperationHistoryListener operationHistoryListener = new DOperationHistoryListener(this);

    private PaletteManager paletteManager;

    private Tabbar tabbar;

    /**
     * A Selection listeners that react to any selection changes by updating the
     * "Diagram" Menu.
     */
    private DiagramMenuUpdater diagramMenuUpdater;

    private ScrollingGraphicalViewer sGViewer;

    private DiagramHeaderComposite diagramHeaderComposite;

    private ToolFilter toolFilterWhenRepresentationIsLocked = new ToolFilterForLockedDRepresentation();

    private Composite parentComposite;

    private SelectDRepresentationElementsListener selectElementsListener;

    private Job refreshJob;

    private int choice = ISaveablePart2.DEFAULT;

    /**
     * Create a new instance.
     */
    public DDiagramEditorImpl() {
        super();
    }

    @Override
    public void close(boolean save) {
        if (refreshJob != null) {
            try {
                refreshJob.join();
            } catch (InterruptedException e) {
                DiagramPlugin.getDefault().getLog().log(new Status(IStatus.INFO, DiagramPlugin.ID, "Refresh job got interrupted", e));
            }
        }
        disposeGraphicalListeners();
        super.close(save);
    }

    /**
     * We have to take care of the case when Eclipse starts up with a session.
     * and diagram already open. {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.part.SiriusDiagramEditor#init(org.eclipse.ui.IEditorSite,
     *      org.eclipse.ui.IEditorInput)
     */
    @Override
    public void init(final IEditorSite site, IEditorInput input) throws PartInitException {
        IEditorInput correctedInput = getCorrectedInput(input);

        if (correctedInput instanceof SessionEditorInput) {
            this.session = ((SessionEditorInput) correctedInput).getSession();
        }

        final Collection<Session> sessions = SessionManager.INSTANCE.getSessions();

        /*
         * we are during eclipse boot, we are not trying to close the editor
         */
        if (sessions.isEmpty() && (!isClosing)) {
            SessionManager.INSTANCE.addSessionsListener(sessionManagerListener);
        }
        isClosing = false;

        try {
            if (getSession() != null) {
                // Initialize the undo context
                initUndoContext();
                // Enable GMF notation model canonical refresh in pre-commit
                // called here to be notified before the DiagramEventBroker
                SiriusDiagramSessionEventBroker.getInstance(getSession());
            }

            super.init(site, correctedInput);

            if (getSession() != null) {
                getSession().addListener(this);

                /* checks the SessionUIManager. */
                final IEditingSession editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(getSession());
                editingSession.open();
                editingSession.attachEditor(this);
            }

            initCommandFactoryProviders();

            /* Update title. Semantic diagram could have been renamed */
            notify(PROP_TITLE);

            initPermissionAuthority();

        } catch (NullPointerException e) {
            DiagramPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, DiagramPlugin.ID, "Error while getting the session.", e));
        }

    }

    private void initUndoContext() {
        if (undoContext == null) {
            final TransactionalEditingDomain domain = getEditingDomain();

            if (domain != null) {
                if (domain.getCommandStack() instanceof IWorkspaceCommandStack) {
                    undoContext = ((IWorkspaceCommandStack) domain.getCommandStack()).getDefaultUndoContext();
                } else {
                    undoContext = new EditingDomainUndoContext(domain);
                }
            } else {
                undoContext = new ObjectUndoContext(this);
            }
        }
    }

    private IEditorInput getCorrectedInput(IEditorInput input) {
        // input is a FileEditorInput in case editor is opened from a marker
        // then we don't have yet uriFragment to gmf Diagram, we will get it
        // when gotoMarker() method will be called
        if (input instanceof FileEditorInput) {
            IFile file = ((FileEditorInput) input).getFile();
            URI analysisURI = URI.createPlatformResourceURI("/" + file.getProject().getName() + "/" + file.getProjectRelativePath(), true); //$NON-NLS-1$ //$NON-NLS-2$
            return new SessionEditorInputFactory().create(analysisURI);
        }
        return input;
    }

    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);

        // Initialize drag'n drop listener from palette
        // PaletteViewer paletteViewer =
        // getPaletteViewerProvider().getEditDomain().getPaletteViewer();
        // paletteTransferDragSourceListener = new
        // TemplateTransferDragSourceListener(paletteViewer);
        // paletteViewer.addDragSourceListener(paletteTransferDragSourceListener);
        //
        // paletteTransferDropTargetListener = new
        // ViewpointPaletteToolDropTargetListener(getGraphicalViewer());
        // getDiagramGraphicalViewer().addDropTargetListener(paletteTransferDropTargetListener);

        tabbarPostCommitListener = new TabbarRefresher(getEditingDomain());
        visibilityPostCommitListener = new VisibilityPostCommitListener(getDiagramEditPart());
        if (isHeaderSectionEnabled()) {
            diagramHeaderPostCommitListener = new DiagramHeaderPostCommitListener(getEditingDomain(), getDiagramHeader());
        }

        // Update palette : should be hidden if diagram is not editable
        if (!getPermissionAuthority().canEditInstance(getRepresentation())) {
            notify(REPRESENTATION_EDITION_PERMISSION_DENIED);
        }
    }

    /**
     * Overridden to instantiate a {@link SiriusPaletteViewer} instead of the
     * standard one, to support creation from the palette with drag'n drop.
     * 
     * {@inheritDoc}
     */
    @Override
    protected PaletteViewer constructPaletteViewer() {
        // return new ViewpointPaletteViewer();
        PaletteViewer pv = new SiriusPaletteViewer();

        // Initialize drag'n drop listener from palette
        paletteTransferDragSourceListener = new TemplateTransferDragSourceListener(pv);
        pv.addDragSourceListener(paletteTransferDragSourceListener);

        // Handle the palette view case.
        if (paletteManager != null) {
            paletteManager.update(getDiagram());
        }

        return pv;
    }

    /**
     * Overridden to change the visibility of the inherited method.
     * 
     * {@inheritDoc}
     */
    @Override
    public void setTitleImage(Image titleImage) {
        super.setTitleImage(titleImage);
    }

    /**
     * Initialize {@link IPermissionAuthority} and the title image if the
     * Diagram is already locked by the current user before opening.
     */
    private void initPermissionAuthority() {
        // This IPermissionAuthority is added only on shared
        // representations.
        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getDiagram().getElement());
        dRepresentationLockStatusListener = new DRepresentationPermissionStatusListener((DSemanticDiagram) getDiagram().getElement(), this);
        permissionAuthority.addAuthorityListener(dRepresentationLockStatusListener);

        if (!permissionAuthority.canEditInstance(this.getRepresentation())) {
            notify(SessionListener.REPRESENTATION_EDITION_PERMISSION_DENIED);
        } else if (LockStatus.LOCKED_BY_ME.equals(permissionAuthority.getLockStatus(getDiagram().getElement()))) {
            notify(SessionListener.REPRESENTATION_EDITION_PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY);
        }
    }

    @Override
    public IDiagramCommandFactoryProvider getEmfCommandFactoryProvider() {
        return emfCommandFactoryProvider;
    }

    @Override
    public void setEmfCommandFactoryProvider(IDiagramCommandFactoryProvider emfCommandFactoryProvider) {
        this.emfCommandFactoryProvider = emfCommandFactoryProvider;
        configureCommandFactoryProviders();
    }

    @Override
    protected KeyHandler getKeyHandler() {
        if (keyHandler == null) {
            keyHandler = super.getKeyHandler();

            /* map our custom delete action */
            final ActionRegistry registry = getActionRegistry();
            final IDisposableAction deleteFromModelAction = new DeleteFromModelWithHookAction(this);
            deleteFromModelAction.init();
            registry.registerAction(deleteFromModelAction);

            final IAction deleteAction = new DeleteWithHookAction(this);
            deleteAction.setText(DiagramUIMessages.DiagramEditor_Delete_from_Diagram);
            registry.registerAction(deleteAction);

            keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));

            keyHandler.put(/* CTRL + D */
            KeyStroke.getPressed((char) 0x4, 100, SWT.CTRL), getActionRegistry().getAction(ActionIds.ACTION_DELETE_FROM_MODEL));
        }
        return keyHandler;
    }

    /**
     * @see org.eclipse.sirius.business.api.componentization.ViewpointRegistryListener2#modelerDesciptionFilesLoaded()
     */
    private void modelerDescriptionFilesLoaded() {
        if (isAutoRefresh()) {
            // reload the palette when a .odesign is reloaded
            // already done be DDiagramEditorSessionListenerDelegate.

            // Run refresh in a job to avoid taking lock on the Workspace on
            // representation change, while the lock is already taken on odesign
            // change
            refreshJob = new Job("Refresh ") {

                @Override
                protected IStatus run(IProgressMonitor monitor) {
                    launchRefresh(false);
                    return Status.OK_STATUS;
                }

            };
            refreshJob.schedule();
        }
    }

    private void initCommandFactoryProviders() {
        /* get IEMFCommandFactories */
        emfCommandFactoryProvider = DiagramCommandFactoryService.getInstance().getNewProvider();
        configureCommandFactoryProviders();
    }

    private void configureCommandFactoryProviders() {
        /*
         * We add a callback for UI stuffs
         */
        IDiagramCommandFactory diagramCommandFactory = emfCommandFactoryProvider.getCommandFactory(getEditingDomain());
        diagramCommandFactory.setUserInterfaceCallBack(new EMFCommandFactoryUI());
    }

    /**
     * Overridden to not create a new {@link TransactionalEditingDomain} but
     * return the {@link Session#getTransactionalEditingDomain()}.
     * 
     * @return the {@link Session#getTransactionalEditingDomain()}
     */
    @Override
    protected TransactionalEditingDomain createEditingDomain() {
        return getSession().getTransactionalEditingDomain();
    }

    /**
     * Called when the diagram is displayed. Updates the Palette.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void hookGraphicalViewer() {
        super.hookGraphicalViewer();
        // Manage palette.
        paletteManager = new PaletteManagerImpl(getEditDomain());
        paletteManager.update(getDiagram());

        // Initialize drag'n drop listener from palette
        paletteTransferDropTargetListener = new SiriusPaletteToolDropTargetListener(getGraphicalViewer());
        getDiagramGraphicalViewer().addDropTargetListener(paletteTransferDropTargetListener);

        if (getSession() != null) {
            final IInterpreter interpreter = getSession().getInterpreter();
            InterpreterRegistry.prepareImportsFromSession(interpreter, getSession());
        }

        // Add a listener to selection
        getSite().getPage().addSelectionListener(this);

        // Add a post commit listener to select newly created diagram elements.
        selectElementsListener = new SelectDRepresentationElementsListener(this, true);
    }

    @Override
    public void dispose() {
        isClosing = true;
        // Dispose the tabbar (to avoid memory leak)
        if (getTabbar() != null) {
            getTabbar().dispose();
            setTabbar(null);
        }
        disposeGraphicalListeners();
        if (getDiagram() != null && getDiagram().eResource() != null) {
            if (dRepresentationLockStatusListener != null) {
                IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getDiagram().getElement());
                permissionAuthority.removeAuthorityListener(dRepresentationLockStatusListener);
            }
        }
        SessionManager.INSTANCE.removeSessionsListener(sessionManagerListener);

        dRepresentationLockStatusListener = null;
        if (getSession() != null) {
            getSession().removeListener(this);
        }
        selectElementsListener.dispose();
        selectElementsListener = null;

        if (getGraphicalViewer() != null) {
            getGraphicalViewer().removeDropTargetListener(transferDropTargetListener);
        }
        /* It is a very good idea to detach the editor from the session */
        if (this.getDiagram() != null) {
            if (getSession() != null) {
                final IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(getSession());
                if (editingSession != null) {
                    editingSession.detachEditor(this, choice == ISaveablePart2.NO);

                }
            }
        }
        if (getSite() != null) {
            getSite().getPage().removeSelectionListener(this);
        }
        getOperationHistory().removeOperationHistoryListener(this.operationHistoryListener);

        if (adapterFactory instanceof IDisposable) {
            ((IDisposable) adapterFactory).dispose();
        }
        PaletteViewer paletteViewer = getPaletteViewerProvider().getEditDomain().getPaletteViewer();
        if (paletteViewer != null) {
            paletteViewer.removeDragSourceListener(paletteTransferDragSourceListener);
            getGraphicalViewer().removeDropTargetListener(paletteTransferDropTargetListener);
        }
        /* dispose the palette manager */
        if (paletteManager != null) {
            paletteManager.dispose();
            paletteManager = null;
        }
        if (sGViewer != null) {
            if (sGViewer.getControl() != null) {
                sGViewer.getControl().dispose();
            }
            sGViewer = null;
        }
        IUndoContext savedUndoContext = getUndoContext();
        // to avoid dispose of current session related IUndoeableOperation
        setUndoContext(new UndoContext());
        super.dispose();
        setUndoContext(savedUndoContext);

        // If possible, remove the diagram event broker for the listening of the
        // transactional editing domain
        stopDiagramEventBrokerListener(getEditingDomain());
    }

    /**
     * Dispose all graphical listeners. This method can be called as soon as the
     * close of the editor is in progress. This avoids that these listeners
     * react to notification whereas the editor will be closed.
     */
    protected void disposeGraphicalListeners() {
        // Dispose post-commit listener
        disposePostCommitListener();

        if (gmfDiagramUpdater != null) {
            gmfDiagramUpdater.dispose();
            gmfDiagramUpdater = null;
        }
    }

    private void disposePostCommitListener() {
        if (tabbarPostCommitListener != null) {
            tabbarPostCommitListener.dispose();
            tabbarPostCommitListener = null;
        }
        if (visibilityPostCommitListener != null) {
            visibilityPostCommitListener.dispose();
            visibilityPostCommitListener = null;
        }
        if (diagramHeaderPostCommitListener != null) {
            diagramHeaderPostCommitListener.dispose();
            diagramHeaderPostCommitListener = null;
        }
    }

    @Override
    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();

        /* register our menu provider to provide our custom delete action */
        final DiagramEditorContextMenuProvider provider = new DiagramEditorContextMenuProvider(this, getDiagramGraphicalViewer());
        getDiagramGraphicalViewer().setContextMenu(provider);
        getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU, provider, getDiagramGraphicalViewer());

        getOperationHistory().addOperationHistoryListener(operationHistoryListener);
        // add the wheel mouse to zoom
        this.getGraphicalViewer().setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.CTRL), MouseWheelZoomHandler.SINGLETON);
    }

    private DiagramOutlinePage initOutline() {
        /** the outline popup menu items and associated actions */
        IObjectActionDelegateWrapper[] outlinePopupMenuActions = new IObjectActionDelegateWrapper[] {
                new IObjectActionDelegateWrapper(new HideDDiagramElementAction(HideDDiagramElement.HIDE_ELEMENT_LABEL), HideDDiagramElement.HIDE_ELEMENT_LABEL) {

                    @Override
                    public ImageDescriptor getImageDescriptor() {
                        return ((IAction) getWrappedAction()).getImageDescriptor();
                    }

                    @Override
                    public boolean isEnabled() {
                        if (currentSelection instanceof IStructuredSelection) {
                            final Object selectedObject = ((IStructuredSelection) currentSelection).getFirstElement();
                            if (selectedObject instanceof DDiagramElement) {
                                boolean isHidden = !new DDiagramElementQuery((DDiagramElement) selectedObject).isHidden();
                                boolean wrappedActionEnabled = ((IAction) getWrappedAction()).isEnabled();
                                return isHidden && wrappedActionEnabled;
                            }
                        }
                        return false;
                    }
                }, new IObjectActionDelegateWrapper(new HideDDiagramElementLabelAction(HideDDiagramElementLabel.HIDE_LABEL), HideDDiagramElementLabel.HIDE_LABEL) {

                    @Override
                    public ImageDescriptor getImageDescriptor() {
                        return ((IAction) getWrappedAction()).getImageDescriptor();
                    }

                    @Override
                    public boolean isEnabled() {
                        boolean result = false;
                        if (currentSelection instanceof IStructuredSelection) {
                            final Object selectedObject = ((IStructuredSelection) currentSelection).getFirstElement();
                            if (selectedObject instanceof DDiagramElement) {
                                DDiagramElementQuery query = new DDiagramElementQuery((DDiagramElement) selectedObject);
                                result = query.canHideLabel() && !query.isLabelHidden();
                            } else if (selectedObject instanceof AbstractDDiagramElementLabelItemProvider) {
                                Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) selectedObject).getDiagramElementTarget();
                                if (optionTarget.some()) {
                                    DDiagramElementQuery query = new DDiagramElementQuery(optionTarget.get());
                                    result = query.canHideLabel() && !query.isLabelHidden();
                                }
                            }
                        }
                        return result;
                    }
                }, new IObjectActionDelegateWrapper(new RevealOutlineElementsAction(), RevealOutlineElementsAction.REVEAL_ELEMENT_LABEL) {

                    @Override
                    public ImageDescriptor getImageDescriptor() {
                        return ((IAction) getWrappedAction()).getImageDescriptor();
                    }

                    @Override
                    public boolean isEnabled() {
                        if (currentSelection instanceof IStructuredSelection) {
                            final Object selectedObject = ((IStructuredSelection) currentSelection).getFirstElement();
                            if (selectedObject instanceof DDiagramElement) {
                                return new DDiagramElementQuery((DDiagramElement) selectedObject).isHidden();
                            }
                        }
                        return false;
                    }
                }, new IObjectActionDelegateWrapper(new RevealOutlineLabelsAction(), RevealOutlineLabelsAction.REVEAL_LABEL_LABEL) {

                    @Override
                    public ImageDescriptor getImageDescriptor() {
                        return ((IAction) getWrappedAction()).getImageDescriptor();
                    }

                    @Override
                    public boolean isEnabled() {
                        boolean result = false;
                        if (currentSelection instanceof IStructuredSelection) {
                            final Object selectedObject = ((IStructuredSelection) currentSelection).getFirstElement();
                            if (selectedObject instanceof DDiagramElement) {
                                DDiagramElementQuery query = new DDiagramElementQuery((DDiagramElement) selectedObject);
                                result = query.isLabelHidden();
                            } else if (selectedObject instanceof AbstractDDiagramElementLabelItemProvider) {
                                Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) selectedObject).getDiagramElementTarget();
                                if (optionTarget.some()) {
                                    DDiagramElementQuery query = new DDiagramElementQuery(optionTarget.get());
                                    result = query.isLabelHidden();
                                }
                            }
                        }
                        return result;
                    }
                }, };
        DiagramOutlinePage outline;
        if (isOldUIEnabled()) {
            outline = new DiagramOutlineWithBookPages(this.getDiagramEditPart().getModel(), getGraphicalViewer(), outlinePopupMenuActions);
        } else {
            outline = new DiagramOutlinePage(this.getDiagramEditPart().getModel(), new OutlineLabelProvider(), new OutlineContentProvider(), new OutlineComparator(), getGraphicalViewer(),
                    outlinePopupMenuActions);
        }
        outline.setDiagramWorkbenchPart(this);
        return outline;
    }

    /**
     * We hook the set focus in order to refresh the root edit part so that the
     * sub diagram decorators will get refreshed.
     */
    @Override
    public void setFocus() {
        super.setFocus();

        final Iterator<EditPart> iterParts = getDiagramGraphicalViewer().getRootEditPart().getChildren().iterator();
        while (iterParts.hasNext()) {
            final EditPart editPart = iterParts.next();
            if (editPart instanceof AbstractDDiagramEditPart && ((GraphicalEditPart) editPart).resolveSemanticElement() instanceof DSemanticDiagram) {
                final DSemanticDiagram semanticElement = (DSemanticDiagram) ((GraphicalEditPart) editPart).resolveSemanticElement();
                if (semanticElement == null || semanticElement.eResource() == null || semanticElement.getTarget() == null || semanticElement.getTarget().eResource() == null) {
                    // The session may not be accessible if the session is using
                    // CDOResources and the server is down
                    if (SessionManager.INSTANCE.getSession(semanticElement.getTarget()) != null) {
                        /*
                         * The element has been deleted, we should close the
                         * editor
                         */
                        myDialogFactory.editorWillBeClosedInformationDialog(getSite().getShell());
                        close(false);
                    }
                    return;
                }
            }
        }

        if (getOperationHistory() != null) {
            // See the javadoc of addOperationHistoryListener. If the listener
            // is already registered, the call has no effect.
            // so we can safely add the listener here.
            getOperationHistory().addOperationHistoryListener(this.operationHistoryListener);
        }

        setEclipseWindowTitle();
    }

    private void setEclipseWindowTitle() {
        // Updates the title of the eclipse window.
        // Removes the xmi id if the selected element and replace it with the
        // name of the tab.
        String title = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getText();
        final int end = title.lastIndexOf(".aird#"); //$NON-NLS-1$
        final int end2 = title.lastIndexOf(" - "); //$NON-NLS-1$
        if (end > -1) {
            title = title.substring(0, end + 5) + this.getPartName() + title.substring(end2);
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().setText(title);
        }
    }

    @Override
    protected ScrollingGraphicalViewer createScrollingGraphicalViewer() {
        return new SiriusDiagramGraphicalViewer();
    }

    @Override
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        super.selectionChanged(part, selection);
        if (getTabbar() != null) {
            getTabbar().selectionChanged(part, selection);
        }

        // we inform the DiagramMenuUpdater that the selection has changed
        if (this.diagramMenuUpdater != null) {
            this.diagramMenuUpdater.selectionChanged(part, selection);
        }

        if (this == part) {
            return;
        }

        // We want to change the diagram selection only on Outline page
        // selection changes.
        if (isDiagramOutlinePage(part) && selection instanceof IStructuredSelection) {

            final List<?> selected = ((IStructuredSelection) selection).toList();
            final List<IGraphicalEditPart> result = new ArrayList<IGraphicalEditPart>(selected.size());

            final IDiagramGraphicalViewer viewer = getDiagramGraphicalViewer();
            if (viewer != null) {
                for (final Object object : selected) {
                    if (object instanceof EObject) {
                        try {
                            // TODO remove this try/catch once the
                            // offline mode will be supported
                            String elementID = EMFCoreUtil.getProxyID((EObject) object);
                            final List<IGraphicalEditPart> concernedEditParts = viewer.findEditPartsForElement(elementID, IGraphicalEditPart.class);
                            result.addAll(concernedEditParts);
                        } catch (IllegalStateException e) {
                            // An issue has been encountered while connecting to
                            // remote CDO server
                            if (DiagramUIPlugin.getPlugin().isDebugging()) {
                                DiagramUIPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, DiagramUIPlugin.ID, "Error while connecting to remote CDO server"));
                            }
                        }
                    } else {
                        if (object instanceof AbstractDDiagramElementLabelItemProvider) {
                            Option<DDiagramElement> diagramElementTarget = ((AbstractDDiagramElementLabelItemProvider) object).getDiagramElementTarget();
                            if (diagramElementTarget.some()) {
                                String elementID = EMFCoreUtil.getProxyID(diagramElementTarget.get());
                                final List<IGraphicalEditPart> concernedEditParts = viewer.findEditPartsForElement(elementID, IGraphicalEditPart.class);
                                result.addAll(Sets.newHashSet(Iterables.filter(concernedEditParts, AbstractDiagramNameEditPart.class)));
                            }
                        }
                    }
                }

                if (!result.isEmpty()) {
                    try {
                        getDiagramGraphicalViewer().reveal(result.get(0));
                        getDiagramGraphicalViewer().setSelection(new StructuredSelection(result));
                    } catch (IllegalArgumentException e) {
                        // This can happen when selected a CDOObject
                        // that has been deleted. Simply do not update selection
                        // in this case
                    } catch (NullPointerException e) {
                        // This can happen when selected a CDOObject
                        // that has been deleted. Simply do not update selection
                        // in this case
                    }
                }

            }
        }
    }

    private boolean isDiagramOutlinePage(IWorkbenchPart part) {
        if (part instanceof ContentOutline) {
            IPage page = ((ContentOutline) part).getCurrentPage();
            if (page instanceof DiagramOutlinePage) {
                GraphicalViewer graphicalViewer = getGraphicalViewer();
                if (graphicalViewer != null) {
                    Control control = ((DiagramOutlinePage) page).getEditor();
                    return control == graphicalViewer.getControl();
                }
            }
        }
        return false;
    }

    @Override
    public boolean isSaveOnCloseNeeded() {
        /*
         * No call to editingSession.needToBeSavedOnClose(this)
         */
        return isDirty();
    }

    @Override
    public Session getSession() {
        if (session == null) {
            session = new EObjectQuery(getDiagram()).getSession();
        }
        return session;

    }

    @Override
    protected void setDocumentProvider(final IEditorInput input) {
        if (getSession() != null/*
                                 * && (input instanceof IFileEditorInput ||
                                 * input instanceof URIEditorInput)
                                 */) {
            setDocumentProvider(DiagramUIPlugin.getPlugin().getDocumentProvider(getSession().getTransactionalEditingDomain()));
        } else {
            // super.setDocumentProvider(input);
            setDocumentProvider(new CustomSiriusDocumentProvider());
        }
    }

    @Override
    protected IDocumentProvider getDocumentProvider(final IEditorInput input) {
        if (input instanceof IFileEditorInput || input instanceof URIEditorInput) {
            return DiagramUIPlugin.getPlugin().getDocumentProvider(getSession().getTransactionalEditingDomain());
        }
        return super.getDocumentProvider(input);
    }

    @Override
    public IPermissionAuthority getPermissionAuthority() {
        if (authority == null) {
            authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getEditingDomain().getResourceSet());
        }
        return authority;
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

    @Override
    public void validateRepresentation() {
        ValidateAction.runValidation(getDiagramEditPart(), getDiagram());
    }

    /**
     * Overridden to delegate {@link SessionListener} events to
     * {@link DDiagramEditorSessionListenerDelegate}.
     * 
     * {@inheritDoc}
     * 
     * @since 0.9.0
     */
    @Override
    public void notify(final int changeKind) {
        DDiagramEditorSessionListenerDelegate dDiagramEditorSessionListenerDelegate = new DDiagramEditorSessionListenerDelegate(this, toolFilterWhenRepresentationIsLocked, changeKind);
        if (Display.getCurrent() == null) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(dDiagramEditorSessionListenerDelegate);
        } else {
            dDiagramEditorSessionListenerDelegate.run();
        }
        switch (changeKind) {
        case VSM_UPDATED:
            modelerDescriptionFilesLoaded();
            break;
        default:
            break;
        }

    }

    /**
     * Method to update some ui parts according to events.
     * 
     * @param notificationKind
     *            the event to update ui parts.
     */
    public void firePropertyChangeInUIThread(final int notificationKind) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (notificationKind == PROP_TITLE) {
                    if ((getDiagram() != null) && (getDiagram().getElement() != null) && (getDiagram().getElement() instanceof DSemanticDiagram)) {
                        final String editorName = DialectUIManager.INSTANCE.getEditorName((DSemanticDiagram) getDiagram().getElement());
                        setPartName(editorName);
                    }
                }
                firePropertyChange(notificationKind);
            }
        };
        if (Display.getCurrent() == null) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(runnable);
        } else {
            runnable.run();
        }
    }

    private boolean isAutoRefresh() {
        boolean autoRefresh = false;
        try {
            autoRefresh = PropertiesService.getInstance().getPropertiesProvider().getProperty(IPropertiesProvider.KEY_AUTO_REFRESH);
        } catch (final IllegalArgumentException e) {
            DiagramPlugin.getDefault().logError(e.getMessage());
        }
        return autoRefresh;
    }

    /**
     * @param opening
     *            True if this refresh is launch during the opening of the
     *            editor, false otherwise.
     */
    private void launchRefresh(boolean opening) {
        Diagram gmfDiag = getDiagram();
        if (gmfDiag != null) {
            EObject eObject = gmfDiag.getElement();

            Command command = null;
            if (eObject instanceof DSemanticDiagram) {
                DSemanticDiagram dDiagram = (DSemanticDiagram) eObject;

                if (opening) {
                    CompoundCommand compoundCommand = new CompoundCommand();

                    Command refreshOnOpeningCmd = new RefreshDiagramOnOpeningCommand(getEditingDomain(), dDiagram);
                    compoundCommand.append(refreshOnOpeningCmd);

                    compoundCommand.setLabel(refreshOnOpeningCmd.getLabel());

                    // We are during the opening, the diagramEditPart is not
                    // already
                    // available, but we synchronize the GMF diag
                    CanonicalSynchronizer canonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(gmfDiag);
                    Command synchronizeGMFModel = new SynchronizeGMFModelCommand(getEditingDomain(), canonicalSynchronizer);
                    compoundCommand.append(synchronizeGMFModel);

                    command = compoundCommand;
                } else {
                    command = new RefreshRepresentationsCommand(getEditingDomain(), new NullProgressMonitor(), dDiagram);
                }
                getEditingDomain().getCommandStack().execute(command);
            }
        }
    }

    @Override
    protected void handleEditorInputChanged() {
        super.handleEditorInputChanged();
        if (isAutoRefresh()) {
            launchRefresh(false);
        }
    }

    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") final Class type) {
        Object adapter = null;
        if (type == IDiagramCommandFactoryProvider.class) {
            adapter = this.emfCommandFactoryProvider;
        } else if (type == IContentOutlinePage.class) {
            adapter = initOutline();
        } else if (type == EditingDomain.class || type == TransactionalEditingDomain.class) {
            adapter = this.getEditingDomain();
        } else if (type == IDiagramWorkbenchPart.class) {
            adapter = this;
        }
        return adapter != null ? adapter : super.getAdapter(type);
    }

    @Override
    public TransactionalEditingDomain getEditingDomain() {
        if (getSession() != null) {
            return getSession().getTransactionalEditingDomain();
        }
        return null;
    }

    @Override
    protected String getEditingDomainID() {
        if (getSession() != null) {
            return getSession().getTransactionalEditingDomain().getID();
        } else {
            return null;
        }

    }

    @Override
    protected void createGraphicalViewer(final Composite parent) {
        this.diagramMenuUpdater = new DiagramMenuUpdater(this);
        parentComposite = createParentComposite(parent);
        if (!isOldUIEnabled()) {
            setTabbar(new Tabbar(parentComposite, this));
        }
        createHeaderSection(parentComposite);
        createMainDiagramSection(parentComposite);
    }

    private boolean isOldUIEnabled() {
        return Platform.getPreferencesService().getBoolean(DiagramUIPlugin.ID, SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false, null);
    }

    private void createMainDiagramSection(Composite composite) {
        Composite mainSection = new Composite(composite, SWT.None);
        mainSection.setLayoutData(new GridData(GridData.FILL_BOTH));
        mainSection.setLayout(new FillLayout());
        createOriginalGraphicalViewer(mainSection);

    }

    private void createHeaderSection(Composite composite) {
        if (isHeaderSectionEnabled()) {
            diagramHeaderComposite = new DiagramHeaderComposite(composite, this);
        }

    }

    private Composite createParentComposite(Composite parent) {
        this.diagramMenuUpdater = new DiagramMenuUpdater(this);
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout(1, true);
        layout.verticalSpacing = 0;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        composite.setLayout(layout);
        return composite;
    }

    /**
     * Return true if the header must be enabled, false otherwise.
     * 
     * @return true if the header must be enabled, false otherwise.
     */
    public boolean isHeaderSectionEnabled() {
        boolean result = false;
        DRepresentation representation = getRepresentation();
        if (representation instanceof DDiagram) {
            result = new DDiagramGraphicalQuery((DDiagram) representation).isHeaderSectionEnabled();
        }
        return result;
    }

    @Override
    protected Control getGraphicalControl() {
        return parentComposite;

    }

    /**
     * Get the tab bar.
     * 
     * @return Returns the tab bar
     */
    public Tabbar getTabbar() {
        return tabbar;
    }

    /**
     * Set the tab bar.
     * 
     * @param tabbar
     *            the tab bar to set .
     */
    protected void setTabbar(Tabbar tabbar) {
        this.tabbar = tabbar;
    }

    /**
     * Create classic diagram graphical viewer.
     * 
     * @param parent
     *            the parent composite
     */
    protected void createOriginalGraphicalViewer(final Composite parent) {
        setRulerComposite(new RulerComposite(parent, SWT.NONE));
        sGViewer = createScrollingGraphicalViewer();
        sGViewer.createControl(getRulerComposite());
        setGraphicalViewer(sGViewer);
        hookGraphicalViewer();
        configureGraphicalViewer();
        initializeGraphicalViewer();
        getRulerComposite().setGraphicalViewer((ScrollingGraphicalViewer) getGraphicalViewer());

        if (sGViewer.getRootEditPart() instanceof DDiagramRootEditPart) {
            DDiagramRootEditPart rootEditPart = (DDiagramRootEditPart) sGViewer.getRootEditPart();
            rootEditPart.getZoomManager().setZoomLevelContributions(Arrays.asList(ZoomManager.FIT_ALL, ZoomManager.FIT_HEIGHT, ZoomManager.FIT_WIDTH));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @not-generated : we're making sure the property is set as soon as
     *                possible.
     */
    @Override
    protected void setGraphicalViewer(GraphicalViewer viewer) {
        viewer.setProperty(EDITOR_ID, this);
        super.setGraphicalViewer(viewer);
    }

    @Override
    protected void initializeGraphicalViewer() {

        /*
         * Refresh diagram if needed. Must be done before that
         */
        if (DialectUIManager.INSTANCE.isRefreshActivatedOnRepresentationOpening()) {
            launchRefresh(true);
        }

        super.initializeGraphicalViewer();

        // After EditPart instantiation call arrange command on views marked as
        // to be arranged
        final DiagramEditPart diagramEditPart = getDiagramEditPart();
        if (diagramEditPart != null) {
            SiriusCanonicalLayoutHandler.launchArrangeCommand(diagramEditPart);
        }

        transferDropTargetListener = new DDiagramEditorTransferDropTargetListener(getGraphicalViewer(), LocalSelectionTransfer.getTransfer());

        getGraphicalViewer().addDropTargetListener(transferDropTargetListener);

        // Initialize and rebuild the header composite
        if (getDiagramHeader() != null) {
            getDiagramHeader().setGraphicalViewer((ScrollingGraphicalViewer) getGraphicalViewer());
            getDiagramHeader().rebuildHeaderSection();
        }
    }

    /**
     * Configures my diagram edit domain with its command stack.
     */
    @Override
    protected void configureDiagramEditDomain() {
        final DefaultEditDomain editDomain = getEditDomain();

        if (editDomain != null) {
            final CommandStack stack = editDomain.getCommandStack();

            if (stack != null) {
                // dispose the old stack
                stack.dispose();
            }

            // create and assign the new stack
            final DiagramCommandStack diagramStack = new DDiagramCommandStack(getDiagramEditDomain());
            diagramStack.setOperationHistory(getOperationHistory());

            // changes made on the stack can be undone from this editor
            diagramStack.setUndoContext(getUndoContext());

            editDomain.setCommandStack(diagramStack);
        }
    }

    @Override
    public DRepresentation getRepresentation() {
        if (getDiagram() != null && getDiagram().eResource() != null && getDiagram().getElement() instanceof DRepresentation) {
            return (DRepresentation) getDiagram().getElement();
        }
        return null;
    }

    @Override
    public Diagram getDiagram() {
        if (getDocumentProvider() != null) {
            IDiagramDocument document = (IDiagramDocument) getDocumentProvider().getDocument(getEditorInput());
            if (document != null) {
                return document.getDiagram();
            }
        }
        return super.getDiagram();
    }

    @Override
    protected IUndoContext getUndoContext() {
        return undoContext;
    }

    /**
     * Sets my undo context.
     * 
     * @param context
     *            the undo context
     */
    @Override
    protected void setUndoContext(final IUndoContext context) {
        this.undoContext = context;
    }

    /**
     * Override to not dispose undo context. We dispose
     * {@link org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl#defaultUndoContext}
     * only at {@link Session#close(org.eclipse.core.runtime.IProgressMonitor)}
     * call.
     */
    @Override
    protected void stopListening() {
        if (getDocumentProvider() != null && getEditingDomain() == null) {
            /*
             * if editing domain is null, so undo context is not yet activated
             * => we should not create one with getUndoContext()
             */
            super.stopListening();
        } else {
            // Stop listening but set the undo context to null before, to avoid
            // the dispose of this one. The undo context will be dispose during
            // the close of the session.
            final IUndoContext savUndoContext = getUndoContext();
            setUndoContext(null);
            super.stopListening();
            setUndoContext(savUndoContext);
        }
    }

    /**
     * This will create the quick outline presenter and install it on this
     * editor.
     * 
     * @return The quick outline presenter.
     */
    public SiriusInformationPresenter getQuickOutlinePresenter() {
        SiriusInformationPresenter informationPresenter = new SiriusInformationPresenter(new IInformationControlCreator() {
            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.jface.text.IInformationControlCreator#createInformationControl(org.eclipse.swt.widgets.Shell)
             */
            @Override
            public IInformationControl createInformationControl(Shell parent) {
                return new QuickOutlineControl(parent, SWT.RESIZE, DDiagramEditorImpl.this);
            }
        });

        informationPresenter.install(getGraphicalControl());
        IInformationProvider provider = new SiriusQuickOutlineInformationProvider(this);
        informationPresenter.setInformationProvider(provider, IDocument.DEFAULT_CONTENT_TYPE);
        final int minimalWidth = 50;
        final int minimalHeight = 30;
        informationPresenter.setSizeConstraints(minimalWidth, minimalHeight, true, false);
        informationPresenter.setAnchor(AbstractInformationControlManager.ANCHOR_GLOBAL);
        return informationPresenter;
    }

    @Override
    public void setDialogFactory(DialectEditorDialogFactory dialogFactory) {
        myDialogFactory = dialogFactory;
    }

    @Override
    public PaletteManager getPaletteManager() {
        return paletteManager;
    }

    @Override
    public IToolBarManager getTabBarManager() {
        if (getTabbar() != null) {
            return getTabbar().getToolBarManager();
        }
        return null;
    }

    private void gogo(IMarker marker) {
        /* If the marker is a marker supported by the MarkerNavigationService */
        if (marker.getAttribute(EValidator.URI_ATTRIBUTE, null) == null) {
            super.gotoMarker(marker);
        } else {

            /*
             * Step 1 : getting the view corresponding to the given
             * representation element
             */
            final View targetView = getTargetView(marker);

            if (targetView != null) {
                /* Step 2 : getting the edit part from this view */
                Map<?, ?> editPartRegistry = this.getDiagramGraphicalViewer().getEditPartRegistry();
                EditPart targetEditPart = (EditPart) editPartRegistry.get(targetView);

                /* Step 3 : selecting the correct editPart */
                if (targetEditPart != null) {
                    SiriusDiagramEditorUtil.selectElementsInDiagram(this, Arrays.asList(new EditPart[] { targetEditPart }));
                }
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(this);
            }

        }
    }

    private View getTargetView(IMarker marker) {

        final String representationURI = marker.getAttribute(TraceabilityMarkerNavigationProvider.REPRESENTATION_URI, null);
        final String representationElementId = marker.getAttribute(TraceabilityMarkerNavigationProvider.REPRESENTATION_ELEMENT_ID, null);

        if ((representationElementId != null) && (representationURI != null)) {
            final URI markerDDiagramURI = URI.createURI(representationURI);
            if (markerDDiagramURI != null) {
                EObject semanticDiagram = this.getDiagram().eResource().getResourceSet().getEObject(markerDDiagramURI, true);
                if (semanticDiagram != null) {
                    final DRepresentationElement representationElement = (DRepresentationElement) semanticDiagram.eResource().getEObject(representationElementId);
                    if (representationElement != null) {
                        return SiriusGMFHelper.getGmfView((DDiagramElement) representationElement);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void gotoMarker(IMarker marker) {
        if (TraceabilityMarkerNavigationProvider.isTraceabilityMarker(marker)) {
            new TraceabilityMarkerNavigationProvider(this).gotoMarker(marker);
        } else {
            gogo(marker);
        }
    }

    /**
     * Remove the diagram event broker for the listening of the transactional
     * editing domain <B>if possible</B>. GMF never remove its
     * DiagramEventBroker ant this can let think to memoryLeak if the
     * weakHashMap used bu DiagramEventBroker is not clean.<BR>
     * But if there is no more GMF editor, using this transactional editing
     * domain, opened when can remove the diagram event broker to be more clear.
     * 
     * @param ted
     *            The transactional editing domain used by the current editor.
     */
    protected void stopDiagramEventBrokerListener(TransactionalEditingDomain ted) {
        // Try to remove the diagram event broker if it's no more needed to
        // avoir memory leak
        int nbGMFDiagramEditorsOfSameTED = 0;
        if (getSite() != null && getSite().getPage() != null) {
            for (IEditorReference editorRef : getSite().getPage().getEditorReferences()) {
                IEditorPart editorPart = editorRef.getEditor(false);
                if (editorPart instanceof DiagramEditor && !editorPart.equals(this)) {
                    // Check that the same ted is used.
                    if (ted != null && ted.equals(((DiagramEditor) editorPart).getEditingDomain())) {
                        nbGMFDiagramEditorsOfSameTED++;
                    }
                }
            }
        }
        if (nbGMFDiagramEditorsOfSameTED == 0 && ted != null) {
            // Remove the diagram event broker because there is no more opened
            // diagram
            DiagramEventBroker.stopListening(ted);
        }
    }

    @Override
    public AdapterFactory getAdapterFactory() {
        if (adapterFactory == null) {
            // Create an adapter factory that yields item providers.
            adapterFactory = DiagramUIPlugin.getPlugin().getNewAdapterFactory();
        }
        return adapterFactory;
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden for two reason: <br/>
     * - to update the given input in case the URI is a DDiagram instead of a
     * GMF Diagram <br/>
     * - to have the title image stable during super.setInput(input) because the
     * title image can change depending on the representation is editable status
     * (permission authority).
     */
    @Override
    public void setInput(IEditorInput input) {
        // In case the input is based on the DDiagram, we need to updated it to
        // use the GMF diagram
        IEditorInput updatedEditorInput = input;
        if (input instanceof URIEditorInput) {
            URI uri = ((URIEditorInput) input).getURI();
            if (uri != null && !StringUtil.isEmpty(uri.fragment())) {
                EObject eObject = session.getTransactionalEditingDomain().getResourceSet().getEObject(uri, false);
                if (eObject instanceof DDiagram) {
                    DDiagram dDiagram = (DDiagram) eObject;
                    final DiagramCreationUtil util = new DiagramCreationUtil(dDiagram);
                    if (!util.findAssociatedGMFDiagram()) {
                        DiagramPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, DiagramPlugin.ID, "The gmf diagram is expected to be created before calling setInput() on the editor"));
                    }
                    final Diagram gmfDiag = util.getAssociatedGMFDiagram();
                    updatedEditorInput = new SessionEditorInput(EcoreUtil.getURI(gmfDiag), dDiagram.getName(), session);

                }
            }
        }
        super.setInput(updatedEditorInput);
        if (getGraphicalViewer() != null) {
            getGraphicalViewer().setSelection(new StructuredSelection());
        }
        // Update the tab's icon according to LockStatus
        DRepresentation representation = getRepresentation();
        if (representation instanceof DSemanticDecorator) {
            DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) representation;
            DRepresentationPermissionStatusQuery dRepresentationPermissionStatusQuery = new DRepresentationPermissionStatusQuery(dSemanticDecorator);
            IPermissionAuthority permissionAuthority = getPermissionAuthority();
            LockStatus lockStatus = LockStatus.NOT_LOCKED;
            LockStatus dSemanticDecoratorLockStatus = permissionAuthority.getLockStatus(dSemanticDecorator);
            if (dSemanticDecoratorLockStatus != LockStatus.NOT_LOCKED) {
                lockStatus = dSemanticDecoratorLockStatus;
            }
            int associatedSessionListenerEvent = dRepresentationPermissionStatusQuery.getAssociatedSessionListenerEvent(lockStatus);
            notify(associatedSessionListenerEvent);
        }
        if (diagramHeaderComposite != null && !diagramHeaderComposite.isDisposed()) {
            diagramHeaderComposite.rebuildHeaderSection();
        }

        // The input has changed, replace the existing gmfDiagramUpdater
        if (gmfDiagramUpdater != null) {
            gmfDiagramUpdater.dispose();
            gmfDiagramUpdater = null;
        }
        if (representation instanceof DDiagram) {
            gmfDiagramUpdater = new GMFDiagramUpdater(getSession(), (DDiagram) representation);
        }
    }

    @Override
    public DialectEditorDialogFactory getDialogFactory() {
        return myDialogFactory;
    }

    @Override
    public Saveable[] getSaveables() {
        if (session != null && session.isOpen()) {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
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

    /**
     * Return the diagram header composite.
     * 
     * @return the diagram header composite
     */
    public DiagramHeaderComposite getDiagramHeader() {
        return diagramHeaderComposite;
    }
}
