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

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.sirius.server.diagram.internal.actions.RequestLayersAction;
import org.eclipse.sirius.server.diagram.internal.actions.RequestToolsAction;
import org.eclipse.sprotty.ActionMessage;
import org.eclipse.sprotty.RequestModelAction;

/**
 * A resource set listener allowing us to refresh the diagram.
 *
 * @author sbegaudeau
 */
public class SiriusResourceSetListener extends ResourceSetListenerImpl {

	/**
	 * The Sirius diagram server.
	 */
	private SiriusDiagramServer siriusDiagramServer;

	/**
	 * The constructor.
	 *
	 */
	public SiriusResourceSetListener(SiriusDiagramServer siriusDiagramServer) {
		this.siriusDiagramServer = siriusDiagramServer;
	}

	@Override
	public void resourceSetChanged(ResourceSetChangeEvent event) {
		super.resourceSetChanged(event);

		ActionMessage requestModelActionMessage = new ActionMessage();
		requestModelActionMessage.setClientId(this.siriusDiagramServer.getClientId());
		requestModelActionMessage.setAction(new RequestModelAction());
		this.siriusDiagramServer.accept(requestModelActionMessage);

		ActionMessage requestToolsActionMessage = new ActionMessage();
		requestToolsActionMessage.setClientId(this.siriusDiagramServer.getClientId());
		requestToolsActionMessage.setAction(new RequestToolsAction());
		this.siriusDiagramServer.accept(requestToolsActionMessage);

		ActionMessage requestLayersActionMessage = new ActionMessage();
		requestLayersActionMessage.setClientId(this.siriusDiagramServer.getClientId());
		requestLayersActionMessage.setAction(new RequestLayersAction());
		this.siriusDiagramServer.accept(requestLayersActionMessage);
	}
}
