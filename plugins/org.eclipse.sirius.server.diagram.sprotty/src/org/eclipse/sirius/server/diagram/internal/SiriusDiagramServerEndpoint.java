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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

import org.apache.log4j.Logger;
import org.eclipse.sirius.server.diagram.internal.actions.SiriusActionTypeAdapter;
import org.eclipse.sprotty.ActionMessage;
import org.eclipse.sprotty.server.websocket.DiagramServerEndpoint;

/**
 * The {@link SiriusServerEndpoint} is used to log the various interactions between the client and the server.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramServerEndpoint extends DiagramServerEndpoint {
	/**
	 * The logger.
	 */
	private static Logger LOG = Logger.getLogger(SiriusDiagramServerEndpoint.class);

	/**
	 * The gson object used to deserialize actions.
	 */
	private Gson gson;

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		LOG.info("Opened connection " + session.getId()); //$NON-NLS-1$
		super.onOpen(session, config);
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		LOG.info("Closed connection " + session.getId()); //$NON-NLS-1$
		super.onClose(session, closeReason);
	}

	@Override
	public void accept(ActionMessage message) {
		initializeGson();
		String json = gson.toJson(message, ActionMessage.class);
		getSession().getAsyncRemote().sendText(json);
	}

	@Override
	protected void fireMessageReceived(ActionMessage message) {
		LOG.info("CLIENT " + message); //$NON-NLS-1$
		super.fireMessageReceived(message);
	}

	@Override
	protected void initializeGson() {
		if (gson == null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			SiriusActionTypeAdapter.configureGson(gsonBuilder);
			this.gson = gsonBuilder.create();
			this.setGson(gson);
		}
	}
}
