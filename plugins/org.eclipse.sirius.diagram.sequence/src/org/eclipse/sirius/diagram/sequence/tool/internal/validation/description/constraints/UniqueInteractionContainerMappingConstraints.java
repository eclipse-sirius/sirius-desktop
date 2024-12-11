/*******************************************************************************
 * Copyright (c) 2024 CEA.
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
package org.eclipse.sirius.diagram.sequence.tool.internal.validation.description.constraints;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.sequence.description.InteractionContainerMapping;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;

/**
 * Validation rule checking that only one Interaction Container Mapping is present in a VSM.
 * 
 * @author smonnier
 */
public class UniqueInteractionContainerMappingConstraints extends AbstractModelConstraint {

    /**
     * Sequence children presentation rule id.
     */
    public static final String UNIQUE_INTERACTION_CONTAINER_MAPPING_ID = "org.eclipse.sirius.diagram.sequence.UniqueInteractionContainerMapping"; //$NON-NLS-1$

    @Override
    public IStatus validate(IValidationContext ctx) {
        IStatus result = ctx.createSuccessStatus();
        final EObject eObj = ctx.getTarget();
        if (UNIQUE_INTERACTION_CONTAINER_MAPPING_ID.equals(ctx.getCurrentConstraintId())) {
            result = validateUniqueInteractionContainerMapping(ctx, eObj);
        }
        return result;
    }

    private IStatus validateUniqueInteractionContainerMapping(IValidationContext ctx, EObject eObj) {
        Collection<Layer> layersToInspect = new LinkedHashSet<>();

        if (eObj instanceof SequenceDiagramDescription sequenceDiagramDescription) {
            layersToInspect.add(sequenceDiagramDescription.getDefaultLayer());
            layersToInspect.addAll(sequenceDiagramDescription.getAdditionalLayers());
        } else if (eObj instanceof Layer layer) {
            layersToInspect.add(layer);
        }

        Stream<InteractionContainerMapping> interactionMappings = layersToInspect.stream() //
                .flatMap(layer -> layer.eContents().stream()) //
                .filter(InteractionContainerMapping.class::isInstance) //
                .map(InteractionContainerMapping.class::cast);
        Collection<InteractionContainerMapping> interactionContainerMappingList = interactionMappings.toList();

        // Only one InteractionContainerMapping is allowed
        if (interactionContainerMappingList.size() > 1) {
            // Compile the names of the detected InteractionContainerMappings for the error message
            String interactionContainerMappingNames = interactionContainerMappingList.stream().map(icm -> icm.getName()).collect(Collectors.joining(", ")); //$NON-NLS-1$
            return ctx.createFailureStatus(interactionContainerMappingNames);
        }
        return ctx.createSuccessStatus();
    }
}
