/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.analysis;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.semantic.RemoveSemanticResourceCommand;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.provider.Messages;

import com.google.common.collect.Iterables;

/**
 * Action to remove semantic resources from a session.
 *
 * @author ymortier
 */
public class RemoveSemanticResourceAction extends Action {

    /** The session to change. */
    private Session session;

    /** The resources to remove. */
    private Collection<Resource> toRemove;

    /**
     * Creates a new {@link RemoveSemanticResourceAction}.
     *
     * @param selection
     *            the resources to remove.
     * @param session
     *            the session to change.
     */
    public RemoveSemanticResourceAction(final Collection<Resource> selection, final Session session) {
        super(Messages.RemoveSemanticResourceAction_name);
        this.session = session;
        this.toRemove = new ArrayList<Resource>(selection);
    }

    @Override
    public boolean isEnabled() {
        boolean res = super.isEnabled();
        res = res && this.session != null;
        res = res && !this.toRemove.isEmpty();
        res = res && this.checkResources();
        return res;
    }

    @Override
    public void run() {
        if (!isEnabled() || !checkResources()) {
            return;
        }
        final TransactionalEditingDomain transDomain = session.getTransactionalEditingDomain();
        CompoundCommand compoundCommand = new CompoundCommand();
        for (Resource semanticResourceToRemove : toRemove) {
            compoundCommand.append(new RemoveSemanticResourceCommand(session, semanticResourceToRemove, new NullProgressMonitor(), true));
        }
        transDomain.getCommandStack().execute(compoundCommand);
    }

    private boolean checkResources() {
        boolean okForRemove = true;
        if (session instanceof DAnalysisSessionEObject) {
            // Controlled resource should be removed with uncontrol command
            okForRemove = !Iterables.removeAll(toRemove, ((DAnalysisSessionEObject) session).getControlledResources());
        }

        if (okForRemove) {
            for (final DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
                if (representation instanceof DSemanticDecorator) {
                    final DSemanticDecorator decorator = (DSemanticDecorator) representation;
                    if (decorator.getTarget() != null && this.toRemove.contains(decorator.getTarget().eResource())) {
                        okForRemove = false;
                        break;
                    }
                }
            }
        }
        return okForRemove;
    }
}
