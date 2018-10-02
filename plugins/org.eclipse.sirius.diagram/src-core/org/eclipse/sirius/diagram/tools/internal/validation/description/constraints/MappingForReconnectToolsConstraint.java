/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectionKind;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Checks that a {@link ReconnectEdgeDescription} tool does not reference Region
 * mapping.
 * 
 * @author mporhel
 */
public class MappingForReconnectToolsConstraint extends AbstractModelConstraint {

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus validate(IValidationContext ctx) {
        EObject eObj = ctx.getTarget();
        EMFEventType eventType = ctx.getEventType();
        // In the case of batch mode.

        if (eventType == EMFEventType.NULL) {
            Resource eObjResource = eObj.eResource();
            if (eObjResource != null && eObjResource.getResourceSet() != null && eObj instanceof ReconnectEdgeDescription) {
                ReconnectEdgeDescription red = (ReconnectEdgeDescription) eObj;
                Collection<String> edgeToRegions = getReconnectOnRegions(red);
                if (!edgeToRegions.isEmpty()) {
                    return ctx.createFailureStatus(new Object[] { new IdentifiedElementQuery(red).getLabel(), edgeToRegions });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    private Collection<String> getReconnectOnRegions(ReconnectEdgeDescription tool) {
        Predicate<ContainerMapping> isRegionMapping = new Predicate<ContainerMapping>() {
            @Override
            public boolean apply(ContainerMapping input) {
                return new ContainerMappingQuery(input).isRegion();
            }
        };

        Collection<EdgeMapping> edgeToRegions = new LinkedHashSet<>();
        for (EdgeMapping em : tool.getMappings()) {
            if (tool.getReconnectionKind() == ReconnectionKind.RECONNECT_SOURCE_LITERAL || tool.getReconnectionKind() == ReconnectionKind.RECONNECT_BOTH_LITERAL) {
                if (Iterables.any(Iterables.filter(em.getSourceMapping(), ContainerMapping.class), isRegionMapping)) {
                    edgeToRegions.add(em);
                }
            }

            if (tool.getReconnectionKind() == ReconnectionKind.RECONNECT_TARGET_LITERAL || tool.getReconnectionKind() == ReconnectionKind.RECONNECT_BOTH_LITERAL) {
                if (Iterables.any(Iterables.filter(em.getTargetMapping(), ContainerMapping.class), isRegionMapping)) {
                    edgeToRegions.add(em);
                }
            }
        }

        Collection<String> mappingsLabel = new ArrayList<>();
        for (EdgeMapping mapping : edgeToRegions) {
            mappingsLabel.add(new IdentifiedElementQuery(mapping).getLabel());
        }
        return mappingsLabel;
    }
}
