/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.diagram.internal;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramMessage;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramService;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramSetModelAction;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagram;
import org.eclipse.sirius.services.diagram.internal.converter.SiriusDiagramConverter;

/**
 * The resource set listener used to emit messages indicating that the diagram
 * has been modified.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramResourceSetListener extends ResourceSetListenerImpl {
    /**
     * The diagram service.
     */
    private SiriusDiagramService diagramService;

    /**
     * The constructor.
     *
     * @param diagramService
     *            The diagram service
     */
    public SiriusDiagramResourceSetListener(SiriusDiagramService diagramService) {
        this.diagramService = diagramService;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        super.resourceSetChanged(event);

        SiriusDiagramConverter converter = new SiriusDiagramConverter(this.diagramService.getDDiagram());
        SiriusDiagram diagram = converter.convert();
        SiriusDiagramSetModelAction action = new SiriusDiagramSetModelAction(diagram);

        SiriusDiagramMessage diagramMessage = new SiriusDiagramMessage();
        diagramMessage.setAction(action);
        this.diagramService.dispatch(diagramMessage);
    }
}
