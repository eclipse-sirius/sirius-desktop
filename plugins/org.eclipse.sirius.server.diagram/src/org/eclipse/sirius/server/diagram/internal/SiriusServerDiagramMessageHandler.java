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
import com.google.gson.GsonBuilder;

import java.util.Optional;

import javax.websocket.MessageHandler.Whole;

import org.eclipse.sirius.services.diagram.api.SiriusDiagramMessage;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramService;

/**
 * The message handler used to support incoming messages.
 *
 * @author sbegaudeau
 */
public class SiriusServerDiagramMessageHandler implements Whole<String> {

    /**
     * The diagram service.
     */
    private SiriusDiagramService diagramService;

    /**
     * The JSON parser.
     */
    private Gson gson;

    /**
     * The constructor.
     *
     * @param diagramService
     *            The diagram service
     */
    public SiriusServerDiagramMessageHandler(SiriusDiagramService diagramService) {
        this.diagramService = diagramService;

        this.gson = new GsonBuilder().registerTypeAdapterFactory(new SiriusServerDiagramTypeAdapterFactory()).create();
    }

    /**
     * {@inheritDoc}
     *
     * @see javax.websocket.MessageHandler.Whole#onMessage(java.lang.Object)
     */
    @Override
    public void onMessage(String message) {
        Optional<SiriusDiagramMessage> optionalDiagramMessage = Optional.ofNullable(this.gson.fromJson(message, SiriusDiagramMessage.class));
        optionalDiagramMessage.ifPresent(this.diagramService::accept);
    }

}
