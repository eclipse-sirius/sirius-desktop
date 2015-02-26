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

import java.util.List;

import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ComponentEditPolicy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.command.ViewDeleteCommand;

import com.google.common.collect.Iterables;

/**
 * Extends the GMF {@link ComponentEditPolicy} to avoid creating
 * {@link org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter}.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 * 
 */
public class SiriusComponentEditPolicy extends ComponentEditPolicy {

    @Override
    protected Command createDeleteViewCommand(GroupRequest deleteRequest) {
        // Override to use {@link ViewDeleteCommand} instead of GMF {@link
        // DeleteCommand}. The original code comes from GMF {@link
        // ComponentEditPolicy.createDeleteViewCommand(GroupRequest)}
        TransactionalEditingDomain editingDomain = getEditingDomain();
        if (editingDomain == null) {
            return null;
        }

        CompositeTransactionalCommand cc = new CompositeTransactionalCommand(getEditingDomain(), StringStatics.BLANK);

        List<?> toDel = deleteRequest.getEditParts();
        if (toDel == null || toDel.isEmpty()) {
            cc.compose(new ViewDeleteCommand(editingDomain, (View) getHost().getModel()));
        } else {
            for (IGraphicalEditPart gep : Iterables.filter(toDel, IGraphicalEditPart.class)) {
                cc.compose(new ViewDeleteCommand(editingDomain, (View) gep.getModel()));
            }
        }
        return new ICommandProxy(cc.reduce());
    }

    private TransactionalEditingDomain getEditingDomain() {
        // This method is a clone of the private super {@link
        // ComponentEditPolicy.getEditingDomain()} method.
        if (getHost() instanceof IGraphicalEditPart) {
            return ((IGraphicalEditPart) getHost()).getEditingDomain();
        } else if (getHost() instanceof IEditingDomainProvider) {
            Object domain = ((IEditingDomainProvider) getHost()).getEditingDomain();
            if (domain instanceof TransactionalEditingDomain) {
                return (TransactionalEditingDomain) domain;
            }
        }
        return null;
    }

}
