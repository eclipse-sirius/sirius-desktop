/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.ConnectorStyle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * Specific command to update GMF style.
 *
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class UpdateGMFEdgeStyleCommand extends RecordingCommand {

    private Edge edge;

    private EdgeStyle style;

    /**
     * Constructor.
     *
     * @param domain
     *            the editing domain
     * @param edge
     *            edge with style to update
     * @param style
     *            style to put at edge
     */
    public UpdateGMFEdgeStyleCommand(final TransactionalEditingDomain domain, final Edge edge, EdgeStyle style) {
        super(domain, Messages.UpdateGMFEdgeStyleCommand_label);
        this.edge = edge;
        this.style = style;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (edge != null && edge.getStyles() != null) {
            for (Object notationStyle : edge.getStyles()) {
                if (notationStyle instanceof ConnectorStyle) {
                    ConnectorStyle connectorStyle = (ConnectorStyle) notationStyle;
                    if (EdgeRouting.MANHATTAN_LITERAL == style.getRoutingStyle()) {
                        connectorStyle.setRouting(Routing.RECTILINEAR_LITERAL);
                    } else if (EdgeRouting.STRAIGHT_LITERAL == style.getRoutingStyle()) {
                        connectorStyle.setRouting(Routing.MANUAL_LITERAL);
                    } else if (EdgeRouting.TREE_LITERAL == style.getRoutingStyle()) {
                        connectorStyle.setRouting(Routing.TREE_LITERAL);
                    }
                }
            }
        }
    }
}
