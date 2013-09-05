/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.graphical.edit.policies;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.internal.editpolicies.ConnectionEditPolicy;
import org.eclipse.gmf.runtime.notation.Edge;

import org.eclipse.sirius.diagram.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.tools.api.command.semantic.RemoveDanglingReferences;

/**
 * The specific Edit Policy.
 * 
 * @author ymortier
 */
public class AirNoteAttachmentEditPolicy extends ConnectionEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.internal.editpolicies.ConnectionEditPolicy#createDeleteViewCommand(org.eclipse.gef.requests.GroupRequest)
     */
    @Override
    protected Command createDeleteViewCommand(final GroupRequest deleteRequest) {

        final CompoundCommand cc = new CompoundCommand();
        final Command res = super.createDeleteViewCommand(deleteRequest);
        cc.add(res);
        if (this.getHost().getModel() instanceof Edge) {
            final Edge edge = (Edge) this.getHost().getModel();
            TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(edge);
            if ("NoteAttachment".equals(edge.getType())) {
                final RemoveDanglingReferences rdf = new RemoveDanglingReferences(transactionalEditingDomain, EcoreUtil.getRootContainer(edge));
                cc.add(new ICommandProxy(new GMFCommandWrapper(transactionalEditingDomain, rdf)));
            }
        }
        return cc;
    }
}
