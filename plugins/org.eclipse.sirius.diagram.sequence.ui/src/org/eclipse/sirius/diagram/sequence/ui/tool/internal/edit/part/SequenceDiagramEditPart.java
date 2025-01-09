/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.common.ui.util.DisplayUtils;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.ui.DiagramEventBrokerThreadSafe;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionEventBroker;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.refresh.RefreshLayoutCommand;
import org.eclipse.sirius.diagram.sequence.business.internal.refresh.RefreshLayoutScope;
import org.eclipse.sirius.diagram.sequence.business.internal.refresh.RefreshLayoutTrigger;
import org.eclipse.sirius.diagram.sequence.business.internal.refresh.SequenceCanonicalSynchronizerAdapter;
import org.eclipse.sirius.diagram.sequence.business.internal.refresh.SequenceCanonicalSynchronizerAdapterScope;
import org.eclipse.sirius.diagram.sequence.ui.business.internal.refresh.VisibilityEventHandler;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.command.SequenceEMFCommandFactory;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ExecutionOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceContainerCreationPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceZOrderingRefresher;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.api.query.EditPartQuery;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.ContainerCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.figures.OverlayLabel;
import org.eclipse.sirius.diagram.ui.graphical.figures.OverlayLabelsDrawerFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramRootEditPart;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * The edit part for sequence diagrams themselves.
 * 
 * @author pcdavid
 */
public class SequenceDiagramEditPart extends DDiagramEditPart {
    /**
     * The listener in charge with refreshing the graphical ordering and layout when the model and/or graphics positions
     * change.
     */
    private final VisibilityEventHandler semanticOrderingSynchronizer;

    private IDiagramCommandFactoryProvider previousProvider;

    private ModelChangeTrigger refreshLayout;

    private ModelChangeTrigger sequenceCanonicalSynchronizer;

    private ResourceSetListener zOrderAndInstanceRolePartRefresher = new ZOrderAndInstanceRolePartRefresher();

    private IPropertyChangeListener snapDisabler;

    /**
     * A "virtual" figure added to the {@link DDiagramRootEditPart#OVERLAY_LAYER} to paint all the overlay labels
     * (instance of {@link OverlayLabel}) on top of the rest of the diagram to make sure they are always readable. In
     * sequence diagram overlay labels are labels of {@link OperandEditPart} and of {@link CombinedFragmentEditPart}.
     */
    private IFigure overlayLabelsDrawerFigure;

    /**
     * Constructor.
     * 
     * @param diagramView
     *            the view.
     */
    public SequenceDiagramEditPart(final View diagramView) {
        super(diagramView);
        this.semanticOrderingSynchronizer = new VisibilityEventHandler();
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        ExecutionOperations.replaceEditPolicy(this, EditPolicy.CONTAINER_ROLE, new SequenceContainerCreationPolicy(), ContainerCreationEditPolicy.class);

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    @Override
    public void addNotify() {
        SequenceEditPartsOperations.registerDiagramElement(this, resolveSemanticElement());
        super.addNotify();
        Object property = getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (property instanceof DDiagramEditor) {
            DDiagramEditor diagramEditor = (DDiagramEditor) property;
            setCustomCommandFactoryProvider(diagramEditor);
        }
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        SequenceEditPartsOperations.unregisterDiagramElement(this, resolveSemanticElement());
        Object property = getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (property instanceof DDiagramEditor && this.previousProvider != null) {
            DDiagramEditor diagramEditor = (DDiagramEditor) property;
            diagramEditor.setEmfCommandFactoryProvider(this.previousProvider);
            this.previousProvider = null;
        }
    }

    /**
     * Overridden to do some initialization and add a post-commit listener.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void activate() {
        super.activate();

        final EditPartViewer viewer = getViewer();
        if (viewer instanceof DiagramGraphicalViewer) {
            final IPreferenceStore workspaceViewerPreferenceStore = ((DiagramGraphicalViewer) viewer).getWorkspaceViewerPreferenceStore();
            if (workspaceViewerPreferenceStore != null) {
                workspaceViewerPreferenceStore.setDefault(WorkspaceViewerProperties.SNAPTOGRID, false);
                workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.SNAPTOGRID, false);

                workspaceViewerPreferenceStore.setDefault(WorkspaceViewerProperties.SNAPTOGEOMETRY, false);
                workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.SNAPTOGEOMETRY, false);

                snapDisabler = new IPropertyChangeListener() {

                    @Override
                    public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
                        if (event.getNewValue() instanceof Boolean && ((Boolean) event.getNewValue()).booleanValue()) {
                            if (WorkspaceViewerProperties.SNAPTOGEOMETRY.equals(event.getProperty())) {
                                EclipseUIUtil.displayAsyncExec(new Runnable() {
                                    @Override
                                    public void run() {
                                        workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.SNAPTOGEOMETRY, Boolean.FALSE);
                                    }
                                });
                            } else if (WorkspaceViewerProperties.SNAPTOGRID.equals(event.getProperty())) {
                                EclipseUIUtil.displayAsyncExec(new Runnable() {
                                    @Override
                                    public void run() {
                                        workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.SNAPTOGRID, Boolean.FALSE);
                                    }
                                });
                            }
                        }
                    }
                };

                workspaceViewerPreferenceStore.addPropertyChangeListener(snapDisabler);

            }
        }

        /*
         * Once the diagram (and all its children) is active, refresh the various ordering. This is especially needed
         * when creating/opening a diagram as some commands need a properly initialized graphically ordering to work.
         */
        Diagram diagramView = getDiagramView();

        DDiagram dDiagram = new EditPartQuery(this).getDDiagram().get();

        boolean autoRefresh = new DRepresentationQuery(dDiagram).isAutoRefresh();

        boolean refreshOnOpen = Session.of(dDiagram).get().getSiriusPreferences().isRefreshOnRepresentationOpening();

        getEditingDomain().getCommandStack().execute(new RefreshLayoutCommand(getEditingDomain(), diagramView, autoRefresh || refreshOnOpen));
        getEditingDomain().addResourceSetListener(semanticOrderingSynchronizer);
        getEditingDomain().addResourceSetListener(zOrderAndInstanceRolePartRefresher);

        Option<SessionEventBroker> broker = getSessionBroker();
        if (broker.some()) {
            SessionEventBroker sessionEventBroker = broker.get();

            Predicate<Notification> refreshLayoutScope = new RefreshLayoutScope(diagramView);
            refreshLayout = new RefreshLayoutTrigger(diagramView);
            sessionEventBroker.addLocalTrigger(SessionEventBrokerImpl.asFilter(refreshLayoutScope), refreshLayout);

            Predicate<Notification> sequenceCanonicalSynchronizerLayoutScope = new SequenceCanonicalSynchronizerAdapterScope(diagramView);
            sequenceCanonicalSynchronizer = new SequenceCanonicalSynchronizerAdapter();
            sessionEventBroker.addLocalTrigger(SessionEventBrokerImpl.asFilter(sequenceCanonicalSynchronizerLayoutScope), sequenceCanonicalSynchronizer);
        }

        IFigure overlayLayer = getLayer(DDiagramRootEditPart.OVERLAY_LAYER);
        if (overlayLayer != null) {
            this.overlayLabelsDrawerFigure = new OverlayLabelsDrawerFigure(this.getFigure(), this);
            overlayLayer.add(this.overlayLabelsDrawerFigure);
        }
    }

    private Option<SessionEventBroker> getSessionBroker() {
        DDiagramEditor diagramEditor = (DDiagramEditor) this.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (diagramEditor != null) {
            Session session = diagramEditor.getSession();
            if (session != null && session.isOpen()) {
                return Options.newSome(diagramEditor.getSession().getEventBroker());
            }
        }
        return Options.newNone();
    }

    private void setCustomCommandFactoryProvider(DDiagramEditor diagramEditor) {
        previousProvider = diagramEditor.getEmfCommandFactoryProvider();
        diagramEditor.setEmfCommandFactoryProvider(new IDiagramCommandFactoryProvider() {

            /** the EMF command factory */
            private IDiagramCommandFactory commandFactory;

            @Override
            public IDiagramCommandFactory getCommandFactory(final TransactionalEditingDomain editingDomain) {
                if (commandFactory == null) {
                    commandFactory = new SequenceEMFCommandFactory(SequenceDiagramEditPart.this);
                }
                return commandFactory;
            }
        });
    }

    /**
     * Overridden to remove the post-commit listener and other initializations.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void deactivate() {
        EditPartViewer viewer = getViewer();
        if (snapDisabler != null && viewer instanceof DiagramGraphicalViewer && ((DiagramGraphicalViewer) viewer).getWorkspaceViewerPreferenceStore() != null) {
            ((DiagramGraphicalViewer) viewer).getWorkspaceViewerPreferenceStore().removePropertyChangeListener(snapDisabler);
            snapDisabler = null;
        }
        IFigure overlayLayer = getLayer(DDiagramRootEditPart.OVERLAY_LAYER);
        if (overlayLabelsDrawerFigure != null && overlayLayer != null) {
            overlayLayer.remove(overlayLabelsDrawerFigure);
            overlayLabelsDrawerFigure = null;
        }

        getEditingDomain().removeResourceSetListener(zOrderAndInstanceRolePartRefresher);
        getEditingDomain().removeResourceSetListener(semanticOrderingSynchronizer);
        Option<SessionEventBroker> broker = getSessionBroker();
        if (broker.some()) {
            SessionEventBroker sessionEventBroker = broker.get();
            sessionEventBroker.removeLocalTrigger(refreshLayout);
            sessionEventBroker.removeLocalTrigger(sequenceCanonicalSynchronizer);
        }
        super.deactivate();
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden to be accessible from {@link SequenceZOrderingRefresher}.
     */
    @Override
    public void reorderChild(EditPart child, int index) {
        super.reorderChild(child, index);
    }

    @Override
    protected void refreshChildren() {
        super.refreshChildren();
        refreshConnectionsBendpoints();
        new SequenceZOrderingRefresher(this).run();
        Optional<InteractionContainerEditPart> optionalInteractionContainerEditPart = this.getChildren().stream().filter(InteractionContainerEditPart.class::isInstance)
                .map(InteractionContainerEditPart.class::cast).findFirst();
        if (optionalInteractionContainerEditPart.isPresent()) {
            optionalInteractionContainerEditPart.get().refresh();
        }
    }

    @Override
    protected void reorderEdgesFiguresAccordingToGmfOrder() {
        // Do nothing for sequence diagram. Indeed, the z-order is managed differently in sequence diagram.
    }

    /**
     * Refresh the bendpoints of source & target connections.
     */
    protected void refreshConnectionsBendpoints() {
        for (SequenceMessageEditPart connectionEditPart : Iterables.filter(getConnections(), SequenceMessageEditPart.class)) {
            connectionEditPart.refreshBendpoints();
        }
    }

    /**
     * Returns the underlying sequence diagram element.
     * 
     * @return the underlying sequence diagram element.
     */
    public SequenceDiagram getSequenceDiagram() {
        return ISequenceElementAccessor.getSequenceDiagram(getDiagramView()).get();
    }

    private class ZOrderAndInstanceRolePartRefresher extends ResourceSetListenerImpl {

        @Override
        public boolean isPostcommitOnly() {
            return true;
        }

        @Override
        public void resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent event) {
            refreshInstanceRoleEditPartsOnAbstractNodeEventSetBounds(event);
            new SequenceZOrderingRefresher(SequenceDiagramEditPart.this).run();
            EObject element = ((View) SequenceDiagramEditPart.this.getModel()).getElement();
            if (!PermissionAuthorityRegistry.getDefault().getPermissionAuthority(element).isFrozen(element)) {
                refreshConnectionsBendpoints();
            }
        }

        private void refreshInstanceRoleEditPartsOnAbstractNodeEventSetBounds(ResourceSetChangeEvent event) {
            Collection<View> instanceOfRoleToRefresh = new LinkedHashSet<>();
            for (Notification notification : event.getNotifications()) {
                if (!notification.isTouch() && notification.getEventType() == Notification.SET && notification.getNotifier() instanceof Bounds) {
                    Bounds notifier = (Bounds) notification.getNotifier();
                    EObject eContainer = notifier.eContainer();
                    if (eContainer instanceof View) {
                        Option<AbstractNodeEvent> abstractNodeEvent = ISequenceElementAccessor.getAbstractNodeEvent((View) eContainer);
                        if (abstractNodeEvent.some()) {
                            Option<Lifeline> lifeline = abstractNodeEvent.get().getLifeline();
                            if (lifeline.some()) {
                                instanceOfRoleToRefresh.add(lifeline.get().getInstanceRole().getNotationView());
                            }
                        }
                    }
                }
            }

            if (instanceOfRoleToRefresh.isEmpty()) {
                return;
            }

            Runnable instanceRoleRefreshRunnable = () -> {
                // @formatter:off
                List<? extends GraphicalEditPart> children = new ArrayList<>(SequenceDiagramEditPart.this.getChildren());
                Stream<InstanceRoleEditPart> instanceRoleEditParts = children.stream()
                                                                              .filter(InstanceRoleEditPart.class::isInstance)
                                                                              .map(InstanceRoleEditPart.class::cast);
                // @formatter:on
                instanceRoleEditParts.filter(part -> instanceOfRoleToRefresh.contains(part.getModel())).forEach(EditPart::refresh);
            };

            if (Display.getCurrent() != null) {
                instanceRoleRefreshRunnable.run();
            } else {
                boolean safeSynchroneRefresh = isDefaultSiriusDiagramEventBroker(event.getEditingDomain());
                if (safeSynchroneRefresh) {
                    DisplayUtils.getDisplay().syncExec(event.getEditingDomain().createPrivilegedRunnable(instanceRoleRefreshRunnable));
                } else {
                    // DiagramEventBroker is overridden by some products to do an async refresh of the edit parts
                    // Do the same here.
                    EclipseUIUtil.displayAsyncExec(instanceRoleRefreshRunnable);
                }
            }
        }

        private boolean isDefaultSiriusDiagramEventBroker(TransactionalEditingDomain editingDomain) {
            if (editingDomain != null) {
                // Do not use DiagramEventBroker.getInstance(editingDomain) to avoid the initialization of a broker if
                // there is no broker on the editing domain.
                Optional<?> postCommitListeners = ReflectionHelper.getFieldValueWithoutException(editingDomain, "postListeners"); //$NON-NLS-1$
                if (postCommitListeners.isPresent()) {
                    Optional<DiagramEventBroker> broker = ((Collection<ResourceSetListener>) postCommitListeners.get()).stream().filter(DiagramEventBroker.class::isInstance).findFirst()
                            .map(DiagramEventBroker.class::cast);
                    return broker.isPresent() && DiagramEventBrokerThreadSafe.class.equals(broker.get().getClass());
                }
            }
            return false;
        }
    }
}
