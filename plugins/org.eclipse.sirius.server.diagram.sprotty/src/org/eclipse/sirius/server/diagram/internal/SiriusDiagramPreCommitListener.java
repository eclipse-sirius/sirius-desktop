/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo
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
package org.eclipse.sirius.server.diagram.internal;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.tools.api.ui.RefreshHelper;

/**
 * The pre-commit listener used to detect if the sirius session needs to be refresh.
 *
 * @author gcoutable
 *
 */
public class SiriusDiagramPreCommitListener extends ResourceSetListenerImpl {

	/**
	 * The diagram server.
	 */
	private SiriusDiagramServer diagramServer;

	/**
	 * Constructor.
	 *
	 * @param diagramServer
	 *            The sirius diagram server
	 */
	public SiriusDiagramPreCommitListener(SiriusDiagramServer diagramServer) {
		this.diagramServer = diagramServer;
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
			RefreshRepresentationsCommand command = new RefreshRepresentationsCommand(event.getEditingDomain(), new NullProgressMonitor(),
					this.diagramServer.getDDiagram());
			// FIXME: will work only in our context !!!!!!!!
			compoundCommand.append(command);
		}
		return compoundCommand;
	}

}
