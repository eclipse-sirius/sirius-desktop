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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramMessage;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramService;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramRequestToolsAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramSetToolsAction;
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagramTool;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Handler for the {@link SiriusDiagramRequestToolsAction}.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramRequestToolsActionHandler implements ISiriusDiagramActionHandler {

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.actions.ISiriusDiagramActionHandler#canHandle(org.eclipse.sirius.services.diagram.api.SiriusDiagramService,
     *      org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction)
     */
    @Override
    public boolean canHandle(SiriusDiagramService diagramService, AbstractSiriusDiagramAction action) {
        return action instanceof SiriusDiagramRequestToolsAction;
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

        // @formatter:off
        List<SiriusDiagramTool> tools = dDiagram.getActivatedLayers().stream()
                .map(Layer::getAllTools)
                .flatMap(List::stream)
                .map(this::createDiagramTool)
                .collect(Collectors.toList());
        // @formatter:on

        SiriusDiagramMessage message = new SiriusDiagramMessage();
        message.setAction(new SiriusDiagramSetToolsAction(tools));
        diagramService.dispatch(message);
    }

    /**
     * Creates a diagram tool from the given tool.
     *
     * @param toolDescription
     *            The tool
     * @return A diagram tool from the given tool
     */
    private SiriusDiagramTool createDiagramTool(AbstractToolDescription toolDescription) {
        String type = this.getType(toolDescription);
        return new SiriusDiagramTool(toolDescription.getName(), toolDescription.getLabel(), type);
    }

    /**
     * Returns the type of the given tool.
     *
     * @param toolDescription
     *            The tool
     * @return The type of the given tool
     */
    private String getType(AbstractToolDescription toolDescription) {
        String type = SiriusDiagramTool.NODE_CREATION_TYPE;
        if (toolDescription instanceof ContainerCreationDescription || toolDescription instanceof NodeCreationDescription) {
            type = SiriusDiagramTool.NODE_CREATION_TYPE;
        }
        return type;
    }

}
