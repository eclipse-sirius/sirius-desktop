/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.services.diagram.internal.actions;

import org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramService;

/**
 * Used to handle an {@link AbstractSiriusDiagramAction}.
 *
 * @author sbegaudeau
 */
public interface ISiriusDiagramActionHandler {
    /**
     * Indicates if the handler can handler the given {@link Action}.
     *
     * @param diagramService
     *            The diagram service
     * @param action
     *            The action
     * @return <code>true</code> if the action can be handled,
     *         <code>false</code> otherwise
     */
    boolean canHandle(SiriusDiagramService diagramService, AbstractSiriusDiagramAction action);

    /**
     * Handles the given {@link Action}.
     *
     * @param diagramService
     *            The diagram service
     * @param action
     *            The action
     */
    void handle(SiriusDiagramService diagramService, AbstractSiriusDiagramAction action);
}
