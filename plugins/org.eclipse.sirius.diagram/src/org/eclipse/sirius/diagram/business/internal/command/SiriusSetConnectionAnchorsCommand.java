/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.description.tool.ReconnectionKind;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A custom SetConnectionAnchorsCommand used when edge reconnection is applied
 * by a reconnection tool (now executed in precommit).
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 * 
 * @since 0.9.0
 */
public class SiriusSetConnectionAnchorsCommand extends SetConnectionAnchorsCommand {

    /**
     * List of incoming/outgoing edges on the targeted edge before reconnection
     */
    private List<Edge> reconnectionTargetEdges;

    /**
     * Reconnection target
     */
    private View reconnectionTarget;

    /**
     * Reuse ReconnectionKind to save if reconnecting the source or the target
     * of the edge.
     */
    private ReconnectionKind reconnectionKind;

    /**
     * SiriusSetConnectionAnchorsCommand constructor.
     * 
     * @param editingDomain
     *            the current editing domain
     * @param label
     *            command label
     * @param reconnectionTarget
     *            the edge on which we are reconnecting
     * @param reconnectionTargetEdges
     *            the incoming(reconnectingSource as
     *            false)/outgoing(reconnectingSource as true) edges of the edge
     *            on which we are reconnecting
     * @param reconnectionKind
     *            indicates if it is a reconnection of the
     *            source(ReconnectionKind.RECONNECT_SOURCE) or the
     *            target(ReconnectionKind.RECONNECT_TARGET)
     */
    public SiriusSetConnectionAnchorsCommand(TransactionalEditingDomain editingDomain, String label, View reconnectionTarget, List<Edge> reconnectionTargetEdges, ReconnectionKind reconnectionKind) {
        super(editingDomain, label);
        this.reconnectionTarget = reconnectionTarget;
        this.reconnectionTargetEdges = new ArrayList<Edge>(reconnectionTargetEdges);
        assert reconnectionKind == ReconnectionKind.RECONNECT_SOURCE_LITERAL || reconnectionKind == ReconnectionKind.RECONNECT_TARGET_LITERAL : "reconnectionKind must be ReconnectionKind.RECONNECT_SOURCE or ReconnectionKind.RECONNECT_TARGET";
        this.reconnectionKind = reconnectionKind;
    }

    /**
     * Overridden because the tool execution is now done in precommit.
     * Therefore, we can not use the getEdgeAdaptor() anymore.
     * 
     * {@inheritDoc}
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
        CommandResult commandResult = CommandResult.newOKCommandResult();
        if (getEdgeAdaptor() != null) {
            commandResult = super.doExecuteWithResult(progressMonitor, info);
        } else {

            Edge edge = getEdge();

            assert null != edge : "Null edge in SetConnectionAnchorsCommand"; //$NON-NLS-1$   

            // In case the reconnectTool has not updated correctly the semantic
            // to
            // do the reconnect, the Edge can be null
            if (edge == null) {
                String message = "The semantic model was not correctly updated by the reconnect tool, the diagram part of the reconnect cannot be done";
                commandResult = CommandResult.newErrorCommandResult(message);
                SiriusDiagramEditorPlugin.getInstance().logWarning(message);
            } else {

                // If there is tree brothers on the new source, we must use the
                // existing
                // sourceAnchor instead of the <code>newSourceTerminal</code>
                // parameter
                Option<IdentityAnchor> optionalSourceBortherAnchor = new EdgeQuery(edge).getSourceAnchorOfFirstBrotherWithSameSource();
                if (optionalSourceBortherAnchor.some()) {
                    setNewSourceTerminal(optionalSourceBortherAnchor.get().getId());
                }
                updateSourceAnchor(edge);
                // If there is tree brothers on the new target, we must use the
                // existing
                // targetAnchor instead of the <code>newTargetTerminal</code>
                // parameter
                Option<IdentityAnchor> optionalTargetBortherAnchor = new EdgeQuery(edge).getTargetAnchorOfFirstBrotherWithSameTarget();
                if (optionalTargetBortherAnchor.some()) {
                    setNewTargetTerminal(optionalTargetBortherAnchor.get().getId());
                }
                updateTargetAnchor(edge);
            }
        }
        return commandResult;
    }

    private void updateSourceAnchor(Edge edge) {
        if (getNewSourceTerminal() != null) {
            if (getNewSourceTerminal().length() == 0)
                edge.setSourceAnchor(null);
            else {
                IdentityAnchor a = (IdentityAnchor) edge.getSourceAnchor();
                if (a == null)
                    a = NotationFactory.eINSTANCE.createIdentityAnchor();
                a.setId(getNewSourceTerminal());
                edge.setSourceAnchor(a);
            }
        }
    }

    private void updateTargetAnchor(Edge edge) {
        if (getNewTargetTerminal() != null) {
            if (getNewTargetTerminal().length() == 0)
                edge.setTargetAnchor(null);
            else {
                IdentityAnchor a = (IdentityAnchor) edge.getTargetAnchor();
                if (a == null)
                    a = NotationFactory.eINSTANCE.createIdentityAnchor();
                a.setId(getNewTargetTerminal());
                edge.setTargetAnchor(a);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Edge getEdge() {
        Edge edge = null;
        if (reconnectionKind == ReconnectionKind.RECONNECT_SOURCE_LITERAL) {
            final List<Edge> sourceEdges = new ArrayList<Edge>(reconnectionTarget.getSourceEdges());
            Iterables.removeAll(sourceEdges, reconnectionTargetEdges);
            Predicate<Edge> notToReconnectingEdge = new Predicate<Edge>() {

                public boolean apply(Edge input) {
                    return !sourceEdges.contains(input.getTarget());
                }
            };
            // For case with reconnect edge source from borderedNode to
            // borderedNode
            // when borderedNode is mapping with EReference
            if (sourceEdges.isEmpty() && reconnectionTarget.getElement() instanceof AbstractDNode) {
                AbstractDNode abstractDNode = (AbstractDNode) reconnectionTarget.getElement();
                List<DNode> borderedNodes = abstractDNode.getOwnedBorderedNodes();
                if (!borderedNodes.isEmpty()) {
                    DNode borderedNode = borderedNodes.get(borderedNodes.size() - 1);
                    Collection<EObject> inverseReferences = new EObjectQuery(borderedNode).getInverseReferences(NotationPackage.eINSTANCE.getView_Element());
                    Node nodeSource = Iterables.getOnlyElement(Iterables.filter(inverseReferences, Node.class));
                    List<Edge> sourceEdgesOfBorderedNode = new ArrayList<Edge>(nodeSource.getSourceEdges());
                    edge = Iterables.getOnlyElement(Iterables.filter(sourceEdgesOfBorderedNode, notToReconnectingEdge));
                }
            } else {
                edge = Iterables.getOnlyElement(Iterables.filter(sourceEdges, notToReconnectingEdge));
            }
        } else {
            final List<Edge> targetEdges = new ArrayList<Edge>(reconnectionTarget.getTargetEdges());
            Iterables.removeAll(targetEdges, reconnectionTargetEdges);
            Predicate<Edge> notFromReconnectingEdge = new Predicate<Edge>() {

                public boolean apply(Edge input) {
                    return !targetEdges.contains(input.getSource());
                }
            };
            // For case with reconnect edge target from borderedNode to
            // borderedNode
            // when borderedNode is mapping with EReference
            if (targetEdges.isEmpty() && reconnectionTarget.getElement() instanceof AbstractDNode) {
                AbstractDNode abstractDNode = (AbstractDNode) reconnectionTarget.getElement();
                List<DNode> borderedNodes = abstractDNode.getOwnedBorderedNodes();
                if (!borderedNodes.isEmpty()) {
                    DNode borderedNode = borderedNodes.get(borderedNodes.size() - 1);
                    Collection<EObject> inverseReferences = new EObjectQuery(borderedNode).getInverseReferences(NotationPackage.eINSTANCE.getView_Element());
                    Node nodeTarget = Iterables.getOnlyElement(Iterables.filter(inverseReferences, Node.class));
                    List<Edge> targetEdgesOfBorderedNode = new ArrayList<Edge>(nodeTarget.getTargetEdges());
                    edge = Iterables.getOnlyElement(Iterables.filter(targetEdgesOfBorderedNode, notFromReconnectingEdge));
                }
            } else {
                edge = Iterables.getOnlyElement(Iterables.filter(targetEdges, notFromReconnectingEdge));
            }
        }
        return edge;
    }
}
