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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramMessage;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramService;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramRequestModelAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramSetModelAction;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagram;
import org.eclipse.sirius.services.diagram.internal.converter.SiriusDiagramConverter;

/**
 * Handler of the {@link SiriusDiagramRequestModelAction}.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramRequestModelActionHandler implements ISiriusDiagramActionHandler {

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.actions.ISiriusDiagramActionHandler#canHandle(org.eclipse.sirius.services.diagram.api.SiriusDiagramService,
     *      org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction)
     */
    @Override
    public boolean canHandle(SiriusDiagramService diagramService, AbstractSiriusDiagramAction action) {
        return action instanceof SiriusDiagramRequestModelAction;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.actions.ISiriusDiagramActionHandler#handle(org.eclipse.sirius.services.diagram.api.SiriusDiagramService,
     *      org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction)
     */
    @Override
    public void handle(SiriusDiagramService diagramService, AbstractSiriusDiagramAction action) {
        DDiagram dDiagram = diagramService.getDDiagram();

        SiriusDiagramConverter diagramConverter = new SiriusDiagramConverter(dDiagram);
        SiriusDiagram siriusDiagram = diagramConverter.convert();

        SiriusDiagramMessage message = new SiriusDiagramMessage();
        message.setAction(new SiriusDiagramSetModelAction(siriusDiagram));
        diagramService.dispatch(message);
    }

}
