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

import java.util.Optional;

import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.server.diagram.internal.actions.ISiriusActionHandler;
import org.eclipse.sirius.server.diagram.internal.actions.SiriusOptionalActionHandlerSwitch;
import org.eclipse.sprotty.Action;
import org.eclipse.sprotty.ActionMessage;
import org.eclipse.sprotty.DefaultDiagramServer;
import org.eclipse.sprotty.ILayoutEngine;
import org.eclipse.sprotty.SModelRoot;

/**
 * The {@link SiriusDiagramServer} is used to communicate with clients.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramServer extends DefaultDiagramServer {

	/**
	 * The Sirius session.
	 */
	private Session session;

	/**
	 * The representation.
	 */
	private DDiagram dDiagram;

	/**
	 * The listener used to update the model when a modification occurs in Sirius.
	 */
	private ResourceSetListener resourceSetListener;

	private ResourceSetListener preCommitListener;

	public SiriusDiagramServer(Session session, DDiagram dDiagram, String clientId) {
		this.session = session;
		this.dDiagram = dDiagram;
		this.setClientId(clientId);

		this.resourceSetListener = new SiriusResourceSetListener(this);
		// FIXME We are never removing this listener, this is a memory leak!
		this.session.getTransactionalEditingDomain().addResourceSetListener(this.resourceSetListener);

		this.preCommitListener = new SiriusDiagramPreCommitListener(this);
		// FIXME We are never removing this listener, this is a memory leak!
		this.session.getTransactionalEditingDomain().addResourceSetListener(this.preCommitListener);
	}

	@Override
	public void accept(ActionMessage message) {
		Action action = message.getAction();
		Optional<ISiriusActionHandler> optionalHandler = new SiriusOptionalActionHandlerSwitch().doSwitch(action);
		if (optionalHandler.isPresent()) {
			// @formatter:off
			optionalHandler.filter(handler -> handler.canHandle(this, action))
				.ifPresent(handler -> handler.handle(this, action));
			// @formatter:on
		} else {
			super.accept(message);
		}
	}

	@Override
	protected boolean needsClientLayout(SModelRoot root) {
		/*
		 * The client layout is necessary in order to have Sprotty compute some information which are necessary for ELK
		 * to to its job. As an example, the internal layout of a node needs to be computed by Sprotty in order to know
		 * the real size of the various elements after the use of CSS. Sprotty can then communicate this information to
		 * ELK which can thus start to compute a meaningful layout which will be then returned to Sprotty.
		 **/
		return true;
	}

	@Override
	protected ILayoutEngine getLayoutEngine() {
		return new SiriusLayoutEngine();
	}

	/**
	 * Return the session.
	 *
	 * @return the session
	 */
	public Session getSession() {
		return this.session;
	}

	/**
	 * Return the DDiagram.
	 *
	 * @return the DDiagram
	 */
	public DDiagram getDDiagram() {
		return this.dDiagram;
	}
}
