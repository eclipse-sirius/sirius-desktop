/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.diagram.internal;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tools.api.ui.RefreshHelper;

/**
 * The pre-commit listener used to detect if the Sirius session needs to be
 * refreshed.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramPrecommitListener extends ResourceSetListenerImpl {
    /**
     * The diagram.
     */
    private DDiagram dDiagram;

    /**
     * The constructor.
     *
     * @param dDiagram
     *            The diagram
     */
    public SiriusDiagramPrecommitListener(DDiagram dDiagram) {
        this.dDiagram = dDiagram;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#isPrecommitOnly()
     */
    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#isAggregatePrecommitListener()
     */
    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        CompoundCommand compoundCommand = new CompoundCommand();
        if (RefreshHelper.isImpactingNotification(event.getNotifications())) {
            compoundCommand.append(new RefreshRepresentationsCommand(event.getEditingDomain(), new NullProgressMonitor(), this.dDiagram));
        }
        return compoundCommand;
    }
}
