/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.api.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.DeselectAllTracker;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.query.CompositeFilterDescriptionQuery;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.business.api.query.DMappingBasedQuery;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusPopupBarEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.AbstractEdgeViewOrdering;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrdering;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrderingHint;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrderingProvider;
import org.eclipse.sirius.diagram.ui.tools.api.permission.EditPartAuthorityListener;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.InitializeHiddenElementsCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.ordering.ViewOrderingProviderRegistry;
import org.eclipse.sirius.diagram.ui.tools.internal.providers.decorators.SubDiagramDecoratorProvider;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gef.editpolicies.SiriusSnapFeedbackPolicy;
import org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools.RubberbandDragTracker;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * 
 * The base edit part.
 * 
 * @author ymortier
 */
public abstract class AbstractDDiagramEditPart extends DiagramEditPart implements IDDiagramEditPart {

    /** The authority listener. */
    protected EditPartAuthorityListener authListener = new EditPartAuthorityListener(this);

    /**
     * Creates a new <code>AbstractDDiagramEditPart</code>.
     * 
     * @param diagramView
     *            the GMF view.
     */
    public AbstractDDiagramEditPart(final View diagramView) {
        super(diagramView);
    }

    /**
     * {@inheritDoc}
     */
    public final Option<DDiagram> resolveDDiagram() {
        final EObject resolveSemanticElement = resolveSemanticElement();

        if (resolveSemanticElement instanceof DDiagram) {
            return Options.newSome((DDiagram) resolveSemanticElement);
        }
        return Options.newNone();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart#createDefaultEditPolicies()
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy(EditPolicyRoles.POPUPBAR_ROLE, new SiriusPopupBarEditPolicy());
        // Replace the feedback policy to have a lighter color for guides
        installEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE, new SiriusSnapFeedbackPolicy());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getModelChildren()
     */
    @Override
    protected List<?> getModelChildren() {
        /* create a new view to avoid to change the super.getModelChildren list. */
        @SuppressWarnings("unchecked")
        final List<?> modelChildren = new ArrayList<Object>(super.getModelChildren());
        DiagramElementEditPartOperation.removeInvisibleElements(modelChildren);
        return modelChildren;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#handleNotificationEvent(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    protected void handleNotificationEvent(final Notification notification) {
        /*
         * This guard is here to prevent node deletion when a diagram is opened
         */
        // if (!(notification.getEventType() == Notification.REMOVE)
        // && (notification.getEventType() == Notification.UNSET &&
        // !((EStructuralFeature)
        // notification.getFeature()).getName().equals("element")))
        super.handleNotificationEvent(notification);

        // Edge Show/Hide failure, saving and closing the diagram
        // If the visibility of an Edge as been modified
        if (NotationPackage.Literals.VIEW__VISIBLE.equals(notification.getFeature())) {
            if (notification.getNotifier() instanceof Edge) {
                // We create an edit part to handle the revealed Edge (if
                // necessary)
                createEditPartForRevealedEdge(notification);
            }
        }

        /*
         * If the notification is a REMOVE of an NoteAttachment, we want to
         * refresh the editpart.
         */
        if (NotationPackage.eINSTANCE.getDiagram_PersistedEdges().equals(notification.getFeature()) || NotationPackage.eINSTANCE.getDiagram_TransientEdges().equals(notification.getFeature())) {
            if (notification.getOldValue() instanceof Edge) {
                final Edge oldValue = (Edge) notification.getOldValue();
                if ("NoteAttachment".equals(oldValue.getType())) { //$NON-NLS-1$
                    refresh();
                }
            }
        }

        /*
         * Special case for activatedFilters
         */
        if (DiagramPackage.eINSTANCE.getDDiagram_ActivatedFilters().equals(notification.getFeature())) {
            refresh();
            refreshSourceAndTargetOfRevealedEdges(notification);
            SubDiagramDecoratorProvider.refreshEditParts(getRoot());
        } else {
            /**
             * If the notification is an ADD we want to refresh the editpart as
             * it may mean that some elements are filtered.
             * 
             * We don't want to refresh on a REMOVE event or it would make the
             * element deletion fail if a diagram is open.
             */
            if (notification.getEventType() == Notification.SET || notification.getEventType() == Notification.UNSET || notification.getEventType() == Notification.ADD) {
                // We don't launch a refresh is the notification is a adding of
                // a layer that has only tools
                if (!(notification.getEventType() == Notification.ADD && notification.getNewValue() instanceof Layer && LayerHelper.containsOnlyTools((Layer) notification.getNewValue()))) {
                    refresh();
                }
            }
        }
    }

    /**
     * Creates an Edit Part for a newly revealed Edge (if necessary), by refresh
     * its source and target.
     * 
     * @param notification
     *            A notification of feature
     *            NotationPackage.Literals.VIEW__VISIBLE
     */
    protected void createEditPartForRevealedEdge(Notification notification) {
        // Step 1 : determine if an edit part needs to be created for this edge
        Option<Edge> edgeToRefresh = Options.newNone();

        // If the visibility of an edge as changed from false to true
        if (Notification.SET == notification.getEventType()) {
            if (!notification.getOldBooleanValue() && notification.getNewBooleanValue()) {
                // Then an Edit part has to be created for this edge
                edgeToRefresh = Options.newSome((Edge) notification.getNotifier());
            }
        }

        // Step 2 : create the edit part for the revealed edge if needed
        if (edgeToRefresh.some()) {
            // To do so, we refresh the source and the target of the revealed
            // edge
            List<IGraphicalEditPart> editPartsToRefresh = new ArrayList<IGraphicalEditPart>();
            Edge edge = edgeToRefresh.get();
            Option<IGraphicalEditPart> sourcePart = getEditPartFor(edge.getSource());
            if (sourcePart.some()) {
                editPartsToRefresh.add(sourcePart.get());
            }
            Option<IGraphicalEditPart> targetPart = getEditPartFor(edge.getTarget());
            if (targetPart.some() && !editPartsToRefresh.contains(targetPart.get())) {
                editPartsToRefresh.add(targetPart.get());
            }
            for (IGraphicalEditPart editPartToRefresh : editPartsToRefresh) {
                editPartToRefresh.refresh();
            }
        }
    }

    /**
     * Launch a refresh on the SourceEditPart and TargetEditPart of all edges
     * revealed with the deactivation of a filter.<BR>
     * This is usefull if the edge is created when the filter is activated and
     * so it has been never created (no corresponding editpart)
     * 
     * @param notification
     *            A notification of feature
     *            DiagramPackage.eINSTANCE.getDDiagram_ActivatedFilters()
     */
    protected void refreshSourceAndTargetOfRevealedEdges(Notification notification) {
        EList<Edge> edgesToRefresh = new BasicEList<Edge>();
        if (Notification.REMOVE == notification.getEventType()) {
            FilterDescription test = (FilterDescription) notification.getOldValue();
            if (test instanceof CompositeFilterDescription) {
                CompositeFilterDescription compositeFilterDescription = (CompositeFilterDescription) test;
                CompositeFilterDescriptionQuery query = new CompositeFilterDescriptionQuery(compositeFilterDescription);
                if (query.isHideCompositeFilter()) {
                    EList<DiagramElementMapping> mappings = query.getHiddenMappings();
                    edgesToRefresh = getEdges(Lists.newArrayList(Iterables.filter(mappings, EdgeMapping.class)));
                }
            }
        }
        // List all edges extremity to refresh
        List<IGraphicalEditPart> editPartsToRefresh = new ArrayList<IGraphicalEditPart>();
        for (Edge edge : edgesToRefresh) {
            Option<IGraphicalEditPart> sourcePart = getEditPartFor(edge.getSource());
            if (sourcePart.some() && !editPartsToRefresh.contains(sourcePart.get())) {
                editPartsToRefresh.add(sourcePart.get());
            }
            Option<IGraphicalEditPart> targetPart = getEditPartFor(edge.getTarget());
            if (targetPart.some() && !editPartsToRefresh.contains(targetPart.get())) {
                editPartsToRefresh.add(targetPart.get());
            }
        }
        for (IGraphicalEditPart editPartToRefresh : editPartsToRefresh) {
            editPartToRefresh.refresh();
        }
    }

    /**
     * Get all GMF edges of this diagram which have a DEdge with mapping
     * contains in <code>edgeMappingsKindOf<code>
     * 
     * @param edgeMappingsKindOf
     *            The edges must have a mapping contained in this list.
     * @return a list of GMF edges.
     */
    private EList<Edge> getEdges(Collection<EdgeMapping> edgeMappingsKindOf) {
        EList<Edge> result = new BasicEList<Edge>();
        Diagram gmfDiagram = this.getDiagramView();
        for (Edge edge : Iterables.filter(gmfDiagram.getEdges(), Edge.class)) {
            EObject element = edge.getElement();
            if (element instanceof DEdge) {
                if (new DMappingBasedQuery((DEdge) element).isFromAnyMapping(edgeMappingsKindOf)) {
                    result.add(edge);
                }
            }
        }
        return result;
    }

    private Option<IGraphicalEditPart> getEditPartFor(View view) {
        Map<?, ?> registry = this.getViewer().getEditPartRegistry();
        if (view != null && registry.containsKey(view) && registry.get(view) instanceof IGraphicalEditPart) {
            return Options.newSome((IGraphicalEditPart) registry.get(view));
        } else {
            return Options.newNone();
        }
    }

    /**
     * {@inheritDoc}
     */
    public EditPartAuthorityListener getEditPartAuthorityListener() {
        return this.authListener;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart#activate()
     */
    @Override
    public void activate() {
        super.activate();
        DDiagram dDiagram = resolveDDiagram().get();
        Session session = new EObjectQuery(dDiagram).getSession();

        // List of hidden diagramElement (or diagramElement from which label is
        // hidden).
        List<DDiagramElement> hiddenElements = new ArrayList<DDiagramElement>();

        DDiagramQuery dDiagramQuery = new DDiagramQuery(dDiagram);
        for (final DDiagramElement diagramElement : dDiagram.getDiagramElements()) {
            if (dDiagramQuery.isHidden(session, diagramElement)) {
                hiddenElements.add(diagramElement);
            } else if (dDiagramQuery.isLabelHidden(session, diagramElement)) {
                hiddenElements.add(diagramElement);
            }
        }

        if (!hiddenElements.isEmpty()) {
            // Execute command if there are hidden elements to add to transient
            // reference hiddenElements
            // Memory leak : do not open a new command if a transaction is being
            // executed
            if ((getEditingDomain() instanceof InternalTransactionalEditingDomain) && ((InternalTransactionalEditingDomain) getEditingDomain()).getActiveTransaction() == null) {
                final org.eclipse.emf.common.command.Command command = new InitializeHiddenElementsCommand(getEditingDomain(), dDiagram, hiddenElements);
                if (command != null) {
                    executeCommand(new ICommandProxy(new GMFCommandWrapper(getEditingDomain(), command)));
                }
            } else {
                dDiagram.getHiddenElements().addAll(hiddenElements);
            }
            // Refresh the tabbar because at least one element is hidden.
            final DDiagramEditorImpl diagramEditor = (DDiagramEditorImpl) this.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
            if (diagramEditor != null && diagramEditor.getTabbar() != null) {
                diagramEditor.getTabbar().reinitToolBar(diagramEditor.getDiagramGraphicalViewer().getSelection());
            }
        }

        final IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getEditingDomain().getResourceSet());
        auth.addAuthorityListener(this.getEditPartAuthorityListener());
        this.getEditPartAuthorityListener().refreshEditMode();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deactivate() {
        if (!isActive()) {
            return;
        }
        final IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getEditingDomain().getResourceSet());
        auth.removeAuthorityListener(this.getEditPartAuthorityListener());
        super.deactivate();
    }

    /**
     * Populate the view ordering. {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        Command originalCommand = super.getCommand(request);
        if (request instanceof ArrangeRequest && originalCommand != null) {
            Command populateViewOrder = new Command(originalCommand.getLabel()) {

                @Override
                public void execute() {
                    populateViewOrderings();
                }

            };
            originalCommand = populateViewOrder.chain(originalCommand);
        }
        return originalCommand;
    }

    /**
     * Populate the {@link ViewOrderingHint} for a future Arrange command.
     */
    protected void populateViewOrderings() {

        final EObject element = this.resolveSemanticElement();
        if (element instanceof DDiagram) {
            final DDiagram dDiagram = (DDiagram) element;
            if (dDiagram.getDescription() != null) {
                final DiagramDescription desc = dDiagram.getDescription();
                final List<DiagramElementMapping> allMappings = new LinkedList<DiagramElementMapping>();

                Session session = null;
                if (dDiagram instanceof DSemanticDiagram) {
                    session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) dDiagram).getTarget());
                }

                allMappings.addAll(new DiagramComponentizationManager().getAllContainerMappings(session.getSelectedViewpoints(false), desc));
                allMappings.addAll(new DiagramComponentizationManager().getAllNodeMappings(session.getSelectedViewpoints(false), desc));
                allMappings.addAll(new DiagramComponentizationManager().getAllEdgeMappings(session.getSelectedViewpoints(false), desc));
                final Iterator<DiagramElementMapping> iterAllMappings = allMappings.iterator();
                while (iterAllMappings.hasNext()) {
                    final DiagramElementMapping diagramElementMapping = iterAllMappings.next();
                    final ViewOrderingProvider provider = ViewOrderingProviderRegistry.getInstance().getProvider(diagramElementMapping);
                    if (provider != null) {
                        final ViewOrdering viewOrdering = provider.getViewOrdering(diagramElementMapping);
                        if (viewOrdering instanceof AbstractEdgeViewOrdering) {
                            ViewOrderingHint.getInstance().putEdgeViewOrdering(getNotationView(), (AbstractEdgeViewOrdering) viewOrdering);
                        } else if (viewOrdering != null) {
                            ViewOrderingHint.getInstance().putViewOrdering(getNotationView(), viewOrdering);
                        }
                    }
                }
            }
        }
        for (IGraphicalEditPart part : Iterables.filter(getChildren(), IGraphicalEditPart.class)) {
            this.populateViewOrderings(part);
        }
    }

    /**
     * Recursively populate orderings.
     * 
     * @param editPart
     *            the current edit part.
     */
    private void populateViewOrderings(final IGraphicalEditPart editPart) {

        final EObject element = editPart.resolveSemanticElement();

        if (element instanceof DNodeContainer) {
            final ContainerMapping containerMapping = ((DNodeContainer) element).getActualMapping();
            if (containerMapping != null) {
                final Iterator<DiagramElementMapping> iterAllMappings = containerMapping.getAllMappings().iterator();
                while (iterAllMappings.hasNext()) {
                    final DiagramElementMapping diagramElementMapping = iterAllMappings.next();
                    final ViewOrderingProvider provider = ViewOrderingProviderRegistry.getInstance().getProvider(diagramElementMapping);
                    if (provider != null) {
                        final ViewOrdering viewOrdering = provider.getViewOrdering(diagramElementMapping);
                        if (viewOrdering != null) {
                            ViewOrderingHint.getInstance().putViewOrdering(editPart.getNotationView(), viewOrdering);
                        }
                    }
                }
            }
        }
        for (IGraphicalEditPart part : Iterables.filter(editPart.getChildren(), IGraphicalEditPart.class)) {
            this.populateViewOrderings(part);
        }
    }

    /**
     * {@inheritDoc} Override to use specific MarqueeSelectionTool (GEf name) or
     * RubberbandDragTracker (GMF name) to allow ports selection.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(final Request req) {
        if (req instanceof SelectionRequest && ((SelectionRequest) req).getLastButtonPressed() == 3) {
            return new DeselectAllTracker(this);
        }
        return new RubberbandDragTracker();
    }
}
