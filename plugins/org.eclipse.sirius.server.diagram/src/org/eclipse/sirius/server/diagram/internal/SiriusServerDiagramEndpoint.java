/*******************************************************************************
 * Copyright (c) 2018 TypeFox and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.diagram.internal;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

import org.eclipse.sirius.services.diagram.api.SiriusDiagramMessage;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramService;

/**
 * The endpoint of the Sirius server diagram API.
 *
 * @author sbegaudeau
 */
public class SiriusServerDiagramEndpoint extends Endpoint {

    /**
     * The name of the parameter used to retrieve the project name.
     */
    private static final String PROJECT_NAME = "projectName"; //$NON-NLS-1$

    /**
     * The name of the parameter used to retrieve the representation name.
     */
    private static final String REPRESENTATION_NAME = "representationName"; //$NON-NLS-1$

    /**
     * The diagram service manager.
     */
    private SiriusServerDiagramServiceManager diagramServiceManager;

    /**
     * The constructor.
     * 
     * @param diagramServiceManager
     *            The diagram service manager
     */
    public SiriusServerDiagramEndpoint(SiriusServerDiagramServiceManager diagramServiceManager) {
        this.diagramServiceManager = diagramServiceManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see javax.websocket.Endpoint#onOpen(javax.websocket.Session,
     *      javax.websocket.EndpointConfig)
     */
    @Override
    public void onOpen(Session session, EndpointConfig config) {
        Map<String, List<String>> parameters = session.getRequestParameterMap();
        List<String> projectNameValues = parameters.get(PROJECT_NAME);
        List<String> representatioNameValues = parameters.get(REPRESENTATION_NAME);

        if (projectNameValues.size() == 1 && representatioNameValues.size() == 1) {
            String projectName = projectNameValues.get(0);
            String representationName = representatioNameValues.get(0);

            Consumer<SiriusDiagramMessage> callback = message -> {
                String json = new Gson().toJson(message);
                session.getAsyncRemote().sendText(json);
            };

            SiriusDiagramService diagramService = this.diagramServiceManager.acquire(session.getId(), projectName, representationName, callback);
            session.addMessageHandler(new SiriusServerDiagramMessageHandler(diagramService));
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see javax.websocket.Endpoint#onClose(javax.websocket.Session,
     *      javax.websocket.CloseReason)
     */
    @Override
    public void onClose(Session session, CloseReason closeReason) {
        this.diagramServiceManager.release(session.getId());
        super.onClose(session, closeReason);
    }
}
