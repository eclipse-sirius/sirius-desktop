/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.command;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.description.tool.ReconnectionKind;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.edit.helpers.EdgeReconnectionHelper;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;
import org.eclipse.sirius.ext.base.Option;

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
     * Helper used to access the edge after reconnection.
     */
    private EdgeReconnectionHelper reconnectingEdgeHelper;

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
        this.reconnectingEdgeHelper = new EdgeReconnectionHelper(reconnectionTarget, reconnectionTargetEdges, reconnectionKind);
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

            Edge edge = reconnectingEdgeHelper.getReconnectedEdge();

            // The reconnect tool did not necessary reconnect the edge with the
            // expected new source or target. In the case where we did not find
            // out the
            // reconnected edge, there is nothing to do here.
            if (edge != null) {

                // If there is tree brothers on the new source, we must use the
                // existing sourceAnchor instead of the
                // <code>newSourceTerminal</code> parameter
                EdgeQuery edgeQuery = new EdgeQuery(edge);
                if (edgeQuery.isEdgeOnTreeOnSourceSide()) {
                    Option<IdentityAnchor> optionalSourceBortherAnchor = edgeQuery.getSourceAnchorOfFirstBrotherWithSameSource();
                    if (optionalSourceBortherAnchor.some()) {
                        setNewSourceTerminal(optionalSourceBortherAnchor.get().getId());
                    }
                }
                updateSourceAnchor(edge);
                // If there is tree brothers on the new target, we must use the
                // existing targetAnchor instead of the
                // <code>newTargetTerminal</code> parameter
                if (edgeQuery.isEdgeOnTreeOnTargetSide()) {
                    Option<IdentityAnchor> optionalTargetBortherAnchor = edgeQuery.getTargetAnchorOfFirstBrotherWithSameTarget();
                    if (optionalTargetBortherAnchor.some()) {
                        setNewTargetTerminal(optionalTargetBortherAnchor.get().getId());
                    }
                }
                updateTargetAnchor(edge);

                CenterEdgeEndModelChangeOperation centerEdgeEndModelChangeOperation = new CenterEdgeEndModelChangeOperation(edge);
                centerEdgeEndModelChangeOperation.execute();
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
}
