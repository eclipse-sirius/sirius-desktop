/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.api.command.view;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.danalysis.DViewHelper;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.listener.Notification;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.GaugeCompositeStyle;
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContainerMappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.EdgeMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.BorderedStyleSpecOperation;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.StyleSpecOperations;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.model.business.internal.description.extensions.IContainerMappingExt;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.diagram.util.DiagramSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilter;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Command that is able to refresh a/many viewpoint element(s).
 * 
 * @author cbrun
 */
public class RefreshSiriusElement extends RecordingCommand {

    /** The refreshable. */
    private DRefreshable uniqueRefreshable;

    /** all refreshables. */
    private Collection<?> refreshablesList;

    /** The filters. */
    private Collection<RefreshFilter> filters;

    /**
     * Create a new {@link RefreshSiriusElement}.
     * 
     * @param domain
     *            the editing domain.
     * @param objectToRefresh
     *            the object to refresh.
     */
    public RefreshSiriusElement(final TransactionalEditingDomain domain, final DRefreshable objectToRefresh) {
        super(domain, Messages.RefreshSiriusElement_refreshRepresentationMsg);
        this.uniqueRefreshable = objectToRefresh;
    }

    /**
     * Create a new {@link RefreshSiriusElement}.
     * 
     * @param domain
     *            the editing domain.
     * @param objectsToRefresh
     *            the objects to refresh.
     * @param filters
     *            the filters.
     */
    public RefreshSiriusElement(final TransactionalEditingDomain domain, final Collection<?> objectsToRefresh, final Collection<RefreshFilter> filters) {
        super(domain, Messages.RefreshSiriusElement_refreshRepresentationMsg);
        this.refreshablesList = objectsToRefresh;
        this.filters = filters;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        if (uniqueRefreshable != null) {
            refresh(this.uniqueRefreshable);
            enableVisibilityUpdate(this.uniqueRefreshable);
            forceVisibilityRefresh(this.uniqueRefreshable);
        }
        if (refreshablesList != null) {
            final Iterator<?> it = refreshablesList.iterator();
            while (it.hasNext()) {
                final Object obj = it.next();
                if (obj instanceof DRepresentation) {
                    if (!isFiltered((DRepresentation) obj)) {
                        DialectManager.INSTANCE.refresh((DRepresentation) obj, new NullProgressMonitor());
                        enableVisibilityUpdate((DRefreshable) obj);
                        forceVisibilityRefresh((DRefreshable) obj);
                    }
                } else if (obj instanceof DRefreshable) {
                    refresh((DRefreshable) obj);
                    enableVisibilityUpdate((DRefreshable) obj);
                    forceVisibilityRefresh((DRefreshable) obj);
                }
            }
        }
    }

    private void forceVisibilityRefresh(DRefreshable obj) {
        if (obj instanceof DDiagram) {
            DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility((DDiagram) obj);
        } else if (obj instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) obj;
            DDiagram parentDiagram = dde.getParentDiagram();
            Session session = SessionManager.INSTANCE.getSession(dde.getTarget());
            NotificationUtil.sendNotification(parentDiagram, Notification.Kind.START, Notification.VISIBILITY);
            DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, parentDiagram);
            DisplayServiceManager.INSTANCE.getDisplayService().computeVisibility(mappingManager, parentDiagram, dde);
            NotificationUtil.sendNotification(parentDiagram, Notification.Kind.STOP, Notification.VISIBILITY);
        }
    }

    /**
     * Tells whether a {@link DRepresentation} is filtered by the filters or not.
     * 
     * @param vp
     *            {@link DRepresentation} to test.
     * @return true if we should ignore it, false otherwise.
     */
    private boolean isFiltered(final DRepresentation vp) {
        if (filters != null) {
            final Iterator<RefreshFilter> it = filters.iterator();
            while (it.hasNext()) {
                final RefreshFilter filter = it.next();
                if (!filter.shouldRefresh(vp)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void enableVisibilityUpdate(DRefreshable obj) {
        DDiagram diagram = null;
        if (obj instanceof DDiagramElement) {
            diagram = ((DDiagramElement) obj).getParentDiagram();

        } else if (obj instanceof DDiagram) {
            diagram = (DDiagram) obj;
        }

        if (diagram != null) {
            NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START,
                    org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);
        }
    }

    /**
     * Performs a local refresh on the specified element. The actual behavior depends on the concrete type.
     * 
     * @param refreshable
     *            the element to refresh.
     */
    public static void refresh(DRefreshable refreshable) {
        if (refreshable instanceof DView) {
            DViewHelper.refreshViewContents((DView) refreshable);
        } else {
            new Refresher().doSwitch(refreshable);
        }
    }

    /**
     * Refresh the border nodes of the given {@link AbstractDNode}.
     * 
     * @param adn
     *            the {@link AbstractDNode} whose bordered nodes need to be refreshed.
     */
    private static void refreshBorderNodes(AbstractDNode adn) {
        /*
         * Update bordering nodes
         */
        final Collection<EObjectCouple> managedBorderingNodes = new HashSet<EObjectCouple>();
        DDiagram parentDiagram = adn.getParentDiagram();
        for (DNode n : adn.getOwnedBorderedNodes()) {
            refresh(n);
            managedBorderingNodes.add(new EObjectCouple(n.getTarget(), n.getActualMapping(), RefreshIdsHolder.getOrCreateHolder(parentDiagram)));
        }
        /*
         * create the non managed bordering nodes
         */
        RepresentationElementMapping mapping = adn.getMapping();
        if (mapping instanceof AbstractNodeMapping) {
            AbstractNodeMappingSpecOperations.createBorderingNodes((AbstractNodeMapping) mapping, adn.getTarget(), adn, managedBorderingNodes, parentDiagram);
        }
    }

    /**
     * Helper to dispatch to the concrete call needed to refresh a {@code DRefreshable}. This corresponds to the code
     * which used to live in the various implementations of {@code DRefreshable.refresh()} in {@code *Spec} classes.
     * 
     * @author pcdavid
     */
    private static class Refresher extends DiagramSwitch<DRefreshable> {
        @Override
        public DRefreshable caseDRepresentation(DRepresentation object) {
            DialectManager.INSTANCE.refresh(object, new NullProgressMonitor());
            return object;
        }

        @Override
        public DRefreshable caseDNodeContainer(DNodeContainer object) {
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(object);
            new ContainerMappingWithInterpreterHelper(interpreter).updateContainer((IContainerMappingExt) object.getActualMapping(), object);
            for (DDiagramElement iterElement : object.getOwnedDiagramElements()) {
                doSwitch(iterElement);
            }
            refreshBorderNodes(object);

            return object;
        }

        @Override
        public DRefreshable caseBundledImage(BundledImage object) {
            StyleSpecOperations.refresh(object);
            BorderedStyleSpecOperation.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseCustomStyle(CustomStyle object) {
            StyleSpecOperations.refresh(object);
            BorderedStyleSpecOperation.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseWorkspaceImage(WorkspaceImage object) {
            StyleSpecOperations.refresh(object);
            BorderedStyleSpecOperation.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseSquare(Square object) {
            StyleSpecOperations.refresh(object);
            BorderedStyleSpecOperation.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseShapeContainerStyle(ShapeContainerStyle object) {
            StyleSpecOperations.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseNote(Note object) {
            StyleSpecOperations.refresh(object);
            BorderedStyleSpecOperation.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseLozenge(Lozenge object) {
            StyleSpecOperations.refresh(object);
            BorderedStyleSpecOperation.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            StyleSpecOperations.refresh(object);
            BorderedStyleSpecOperation.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseFlatContainerStyle(FlatContainerStyle object) {
            StyleSpecOperations.refresh(object);
            BorderedStyleSpecOperation.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseEllipse(Ellipse object) {
            StyleSpecOperations.refresh(object);
            BorderedStyleSpecOperation.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseEdgeStyle(EdgeStyle object) {
            StyleSpecOperations.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseDot(Dot object) {
            StyleSpecOperations.refresh(object);
            BorderedStyleSpecOperation.refresh(object);
            return object;
        }

        @Override
        public DRefreshable caseDNode(DNode object) {
            NodeMapping nodeMapping = object.getActualMapping();
            if (nodeMapping != null) {
                IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(object);
                new NodeMappingHelper(interpreter).updateNode(nodeMapping, object);
                refreshBorderNodes(object);
            }
            return object;
        }

        @Override
        public DRefreshable caseDNodeList(DNodeList object) {
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(object);
            new ContainerMappingWithInterpreterHelper(interpreter).updateContainer((IContainerMappingExt) object.getActualMapping(), object);
            for (DNodeListElement iterElement : object.getOwnedElements()) {
                doSwitch(iterElement);
            }

            refreshBorderNodes(object);
            return object;
        }

        @Override
        public DRefreshable caseDNodeListElement(DNodeListElement object) {
            if (object.getActualMapping() != null) {
                IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(object);
                new NodeMappingHelper(interpreter).updateListElement(object.getActualMapping(), object);
            }
            return object;
        }

        @Override
        public DRefreshable caseDEdge(DEdge object) {
            Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(object.getActualMapping()).getEdgeMapping();
            if (edgeMapping.some()) {
                updateEdge(edgeMapping.get(), object);
            }
            return object;
        }

        private void updateEdge(final EdgeMapping edgeMapping, final DEdge dEdge) {
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(dEdge);
            new EdgeMappingHelper(interpreter).updateEdge(edgeMapping, dEdge);
        }
    }
}
