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

import java.util.Optional;

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction;
import org.eclipse.sirius.services.diagram.api.SiriusDiagramService;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramExecuteNodeCreationToolAction;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * Handler for the {@link SiriusDiagramExecuteNodeCreationToolAction}.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramExecuteNodeCreationToolActionHandler implements ISiriusDiagramActionHandler {

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.actions.ISiriusDiagramActionHandler#canHandle(org.eclipse.sirius.services.diagram.api.SiriusDiagramService,
     *      org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction)
     */
    @Override
    public boolean canHandle(SiriusDiagramService diagramService, AbstractSiriusDiagramAction action) {
        return action instanceof SiriusDiagramExecuteNodeCreationToolAction;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.actions.ISiriusDiagramActionHandler#handle(org.eclipse.sirius.services.diagram.api.SiriusDiagramService,
     *      org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction)
     */
    @Override
    public void handle(SiriusDiagramService diagramService, AbstractSiriusDiagramAction action) {
        if (action instanceof SiriusDiagramExecuteNodeCreationToolAction) {
            SiriusDiagramExecuteNodeCreationToolAction executeNodeCreationToolAction = (SiriusDiagramExecuteNodeCreationToolAction) action;

            // @formatter:off
            DiagramDescription diagramDescription = diagramService.getDDiagram().getDescription();
            Optional<AbstractToolDescription> optionalTool = diagramDescription.getAllTools().stream()
                    .filter(tool -> tool.getName().equals(executeNodeCreationToolAction.getIdentifier()))
                    .findFirst();
            // @formatter:on

            Optional<ModelOperation> optionalModelOperation = optionalTool.flatMap(this::getFirstModelOperation);
            System.out.println("Executing the model operation " + optionalModelOperation.toString()); //$NON-NLS-1$
        }
    }

    /**
     * Returns the model operation of the given tool description if it is a node
     * creation or a container creation description.
     *
     * @param toolDescription
     *            The tool description
     * @return The first model operation of the tool description if it is a node
     *         creation or a container creation description
     */
    private Optional<ModelOperation> getFirstModelOperation(AbstractToolDescription toolDescription) {
        Optional<ModelOperation> optionalModelOperation = Optional.empty();
        if (toolDescription instanceof NodeCreationDescription) {
            NodeCreationDescription nodeCreationDescription = (NodeCreationDescription) toolDescription;
            optionalModelOperation = Optional.ofNullable(nodeCreationDescription.getInitialOperation().getFirstModelOperations());
        } else if (toolDescription instanceof ContainerCreationDescription) {
            ContainerCreationDescription containerCreationDescription = (ContainerCreationDescription) toolDescription;
            optionalModelOperation = Optional.ofNullable(containerCreationDescription.getInitialOperation().getFirstModelOperations());
        }
        return optionalModelOperation;
    }

}
