/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editpolicies.ConnectionEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.command.ViewDeleteCommand;

/**
 * Extends the GMF {@link ConnectionEditPolicy} to avoid creating
 * {@link org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter}.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 * 
 */
@SuppressWarnings("restriction")
public class SiriusConnectionEditPolicy extends ConnectionEditPolicy {

    @Override
    protected Command createDeleteViewCommand(GroupRequest deleteRequest) {
        // Override to use {@link ViewDeleteCommand} instead of GMF {@link
        // DeleteCommand}. The original code comes from GMF {@link
        // ConnectionEditPolicy.createDeleteViewCommand(GroupRequest)}
        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
        return new ICommandProxy(new ViewDeleteCommand(editingDomain, (View) getHost().getModel()));
    }
}
