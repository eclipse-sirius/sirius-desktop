/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.SessionEventBroker;
import org.eclipse.sirius.diagram.graphical.edit.policies.ContainerCreationEditPolicy;
import org.eclipse.sirius.diagram.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
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
import org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.tools.api.properties.PropertiesService;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.tools.api.ui.property.IPropertiesProvider;

/**
 * The edit part for sequence diagrams themselves.
 * 
 * @author pcdavid
 */
public class SequenceDiagramEditPart extends DDiagramEditPart {
    /**
     * The listener in charge with refreshing the graphical ordering and layout
     * when the model and/or graphics positions change.
     */
    private final VisibilityEventHandler semanticOrderingSynchronizer;

    private IDiagramCommandFactoryProvider previousProvider;

    private ModelChangeTrigger refreshLayout;
    
    private ModelChangeTrigger sequenceCanonicalSynchronizer;

    private ResourceSetListener refreshZorder = new ResourceSetListenerImpl() {
        @Override
        public boolean isPostcommitOnly() {
            return true;
        }

        @Override
        public void resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent event) {
            new SequenceZOrderingRefresher(SequenceDiagramEditPart.this).run();
            refreshConnectionsBendpoints();
        }
    };

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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        ExecutionOperations.replaceEditPolicy(this, EditPolicy.CONTAINER_ROLE, new SequenceContainerCreationPolicy(), ContainerCreationEditPolicy.class);

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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
        /*
         * Once the diagram (and all its children) is active, refresh the
         * various ordering. This is especially needed when creating/opening a
         * diagram as some commands need a properly initialized graphically
         * ordering to work.
         */
        boolean autoRefresh = PropertiesService.getInstance().getPropertiesProvider().getProperty(IPropertiesProvider.KEY_AUTO_REFRESH);
        boolean refreshOnOpen = DialectManager.INSTANCE.isRefreshActivatedOnRepresentationOpening();
        getEditingDomain().getCommandStack().execute(new RefreshLayoutCommand(getEditingDomain(), getDiagramView(), autoRefresh || refreshOnOpen));
        getEditingDomain().addResourceSetListener(semanticOrderingSynchronizer);
        getEditingDomain().addResourceSetListener(refreshZorder);

        Option<SessionEventBroker> broker = getSessionBroker();
        if (broker.some()) {
            SessionEventBroker sessionEventBroker = broker.get();
            
            Predicate<Notification> refreshLayoutScope = new RefreshLayoutScope();
            refreshLayout = new RefreshLayoutTrigger(getDiagramView());
            sessionEventBroker.addLocalTrigger(refreshLayoutScope, refreshLayout);
            
            Predicate<Notification> sequenceCanonicalSynchronizerLayoutScope = new SequenceCanonicalSynchronizerAdapterScope();
            sequenceCanonicalSynchronizer = new SequenceCanonicalSynchronizerAdapter();
            sessionEventBroker.addLocalTrigger(sequenceCanonicalSynchronizerLayoutScope, sequenceCanonicalSynchronizer);
        }
    }

    private Option<SessionEventBroker> getSessionBroker() {
        DDiagramEditor diagramEditor = (DDiagramEditor) this.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (diagramEditor != null) {
            return Options.newSome(diagramEditor.getSession().getEventBroker());
        }
        return Options.newNone();
    }

    private void setCustomCommandFactoryProvider(DDiagramEditor diagramEditor) {
        previousProvider = diagramEditor.getEmfCommandFactoryProvider();
        diagramEditor.setEmfCommandFactoryProvider(new IDiagramCommandFactoryProvider() {

            /** the EMF command factory */
            private IDiagramCommandFactory commandFactory;

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.sirius.tools.api.command.IDiagramCommandFactoryProvider#getCommandFactory(org.eclipse.emf.transaction.TransactionalEditingDomain)
             */
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
        getEditingDomain().removeResourceSetListener(refreshZorder);
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void refreshChildren() {
        super.refreshChildren();
        refreshConnectionsBendpoints();
        new SequenceZOrderingRefresher(this).run();
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
}
