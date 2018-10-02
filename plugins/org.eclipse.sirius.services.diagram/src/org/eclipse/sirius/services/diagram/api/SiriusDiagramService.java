/*******************************************************************************
 * Copyright (c) 2018 TypeFox and others.
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
package org.eclipse.sirius.services.diagram.api;

import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.services.diagram.internal.SiriusDiagramPrecommitListener;
import org.eclipse.sirius.services.diagram.internal.SiriusDiagramResourceSetListener;
import org.eclipse.sirius.services.diagram.internal.actions.ISiriusDiagramActionHandler;
import org.eclipse.sirius.services.diagram.internal.actions.SiriusDiagramActionHandlerSwitch;

/**
 * The service used to manipulate Sirius diagrams.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramService {

    /**
     * The session.
     */
    private Session session;

    /**
     * The diagram.
     */
    private DDiagram dDiagram;

    /**
     * The callback used to emit messages.
     */
    private Consumer<SiriusDiagramMessage> callback;

    /**
     * The precommit listener used to refresh the diagram when a modification
     * has been made.
     */
    private SiriusDiagramPrecommitListener precommitListener;

    /**
     * The resource set listener used to send actions when the diagram is
     * refreshed.
     */
    private SiriusDiagramResourceSetListener resourceSetListener;

    /**
     * The constructor.
     *
     * @param session
     *            The session
     * @param dDiagram
     *            The diagram
     * @param callback
     *            The callback used to handle some messages
     */
    public SiriusDiagramService(Session session, DDiagram dDiagram, Consumer<SiriusDiagramMessage> callback) {
        this.session = session;
        this.dDiagram = dDiagram;
        this.callback = callback;

        this.precommitListener = new SiriusDiagramPrecommitListener(this.dDiagram);
        this.resourceSetListener = new SiriusDiagramResourceSetListener(this);
    }

    /**
     * Initializes the service.
     */
    public void initialize() {
        this.session.getTransactionalEditingDomain().addResourceSetListener(this.precommitListener);
        this.session.getTransactionalEditingDomain().addResourceSetListener(this.resourceSetListener);
    }

    /**
     * Accept a message to be handled.
     *
     * @param message
     *            The message
     */
    public void accept(SiriusDiagramMessage message) {
        AbstractSiriusDiagramAction action = message.getAction();
        Optional<ISiriusDiagramActionHandler> optionalActionHandler = new SiriusDiagramActionHandlerSwitch().doSwitch(action);
        optionalActionHandler.filter(handler -> handler.canHandle(this, action)).ifPresent(handler -> handler.handle(this, action));
    }

    /**
     * Dispatch a message to be handled by the callback.
     *
     * @param message
     *            The message
     */
    public void dispatch(SiriusDiagramMessage message) {
        this.callback.accept(message);
    }

    /**
     * Disposes the service.
     */
    public void dispose() {
        this.session.getTransactionalEditingDomain().removeResourceSetListener(this.precommitListener);
        this.session.getTransactionalEditingDomain().removeResourceSetListener(this.resourceSetListener);
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
     * Return the dDiagram.
     *
     * @return the dDiagram
     */
    public DDiagram getDDiagram() {
        return this.dDiagram;
    }
}
