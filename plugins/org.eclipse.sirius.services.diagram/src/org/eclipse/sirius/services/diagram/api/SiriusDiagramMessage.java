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

/**
 * The message used to describe the action to perform.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramMessage {
    /**
     * The clientIdentifier.
     */
    private String clientIdentifier;

    /**
     * The action.
     */
    private AbstractSiriusDiagramAction action;

    /**
     * Return the clientIdentifier.
     *
     * @return the clientIdentifier
     */
    public String getClientIdentifier() {
        return this.clientIdentifier;
    }

    /**
     * Return the action.
     *
     * @return the action
     */
    public AbstractSiriusDiagramAction getAction() {
        return this.action;
    }

    /**
     * Sets the action.
     *
     * @param action
     *            the action to set
     */
    public void setAction(AbstractSiriusDiagramAction action) {
        this.action = action;
    }
}
