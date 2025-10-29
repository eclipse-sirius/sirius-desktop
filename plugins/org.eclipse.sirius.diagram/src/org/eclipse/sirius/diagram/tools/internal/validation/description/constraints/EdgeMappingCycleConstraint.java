/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.internal.validation.description.constraints;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.diagram.description.EdgeMapping;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Check that {@link EdgeMapping}s with an {@link EdgeMapping} as source or
 * target does not cause cycles.
 * 
 * @author Steve Monnier <steve.monnier@obeo.fr>
 */
public class EdgeMappingCycleConstraint extends AbstractModelConstraint {

    @Override
    public IStatus validate(IValidationContext ctx) {
        EObject eObj = ctx.getTarget();
        EMFEventType eventType = ctx.getEventType();
        // In the case of batch mode.
        if (eventType == EMFEventType.NULL) {
            Resource eObjResource = eObj.eResource();
            if (eObjResource != null && eObjResource.getResourceSet() != null && eObj instanceof EdgeMapping) {
                EdgeMapping edgeMapping = (EdgeMapping) eObj;
                boolean findCycle = hasCycle(
                        edgeMapping,
                        Sets.<EdgeMapping> newLinkedHashSet(),
                        Sets.<EdgeMapping> newLinkedHashSet(Iterables.concat(Iterables.filter(edgeMapping.getSourceMapping(), EdgeMapping.class),
                                Iterables.filter(edgeMapping.getTargetMapping(), EdgeMapping.class))));
                if (findCycle) {
                    return ctx.createFailureStatus(new Object[] { edgeMapping, edgeMapping.getName() });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    /**
     * Detects cycles from current {@link EdgeMapping} recurcively.
     * 
     * @param edgeMapping
     *            the current {@link EdgeMapping}
     * @param processedEdgeMappings
     *            {@link Set} of already investigated {@link EdgeMapping}
     * @param edgeMappingsToProcess
     *            {@link Set} of {@link EdgeMapping} to investigate
     * 
     * @return if a cycle is detected from the current {@link EdgeMapping}
     */
    private boolean hasCycle(EdgeMapping edgeMapping, Set<EdgeMapping> processedEdgeMappings, Set<EdgeMapping> edgeMappingsToProcess) {
        boolean hasCycle = false;
        if (Iterables.contains(edgeMappingsToProcess, edgeMapping)) {
            // Investigation lead to the first EdgeMapping : A cycle is
            // detected.
            hasCycle = true;
        } else if (edgeMappingsToProcess.isEmpty() || processedEdgeMappings.containsAll(edgeMappingsToProcess)) {
            // No more EdgeMapping to investigate : No cycle detected.
            hasCycle = false;
        } else {
            // Recursively investigate source/target EdgeMapping of the
            // edgeMappingsToProcess Set.
            LinkedHashSet<EdgeMapping> newEdgeMappingsToProcess = Sets.<EdgeMapping> newLinkedHashSet();
            for (EdgeMapping edgeMappingToProcess : edgeMappingsToProcess) {
                newEdgeMappingsToProcess.addAll(Sets.newLinkedHashSet(Iterables.concat(Iterables.filter(edgeMappingToProcess.getSourceMapping(), EdgeMapping.class),
                        Iterables.filter(edgeMappingToProcess.getTargetMapping(), EdgeMapping.class))));
            }
            hasCycle = hasCycle(edgeMapping, Sets.newLinkedHashSet(Iterables.concat(processedEdgeMappings, edgeMappingsToProcess)), newEdgeMappingsToProcess);
        }
        return hasCycle;
    }

}
