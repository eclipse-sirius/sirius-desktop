/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;

/**
 * Validates that the field SemanticCandidatesExpression is not empty when
 * ContainerMapping is reused recursively.
 * 
 * @author lredor
 */
public class BlankCandidatesExpressionWithRecursiveMappingConstraint extends AbstractConstraint {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    public IStatus validate(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        final EMFEventType eventType = ctx.getEventType();

        // In the case of batch mode.
        if (eventType == EMFEventType.NULL) {
            Object[] result = null;
            if (eObj instanceof ContainerMapping) {
                result = checkError((ContainerMapping) eObj);
            }
            if (result != null) {
                return ctx.createFailureStatus(result);
            }

        }
        return ctx.createSuccessStatus();
    }

    /**
     * @param eObj
     * @return
     */
    private Object[] checkError(ContainerMapping containerMapping) {
        Object[] result = null;
        if (StringUtil.isEmpty(containerMapping.getSemanticCandidatesExpression())) {
            if (containerMapping instanceof ContainerMappingImport) {
                result = checkNoReusedOfItself((ContainerMappingImport) containerMapping);
            } else {
                result = checkNoReusedOfItself(containerMapping);
            }
        }
        return result;
    }

    /**
     * @param containerMappingToCheck
     * @return
     */
    private Object[] checkNoReusedOfItself(ContainerMapping containerMappingToCheck) {
        if (containerMappingToCheck.isCreateElements()) {
            EList<ContainerMapping> allReused = getReusedContainerMapping(containerMappingToCheck, true);
            if (allReused.contains(containerMappingToCheck)) {
                return new Object[] { containerMappingToCheck, allReused };
            }
        }
        return null;
    }

    /**
     * @param containerMappingToCheck
     * @return
     */
    private Object[] checkNoReusedOfItself(ContainerMappingImport containerMappingToCheck) {
        if (containerMappingToCheck.isCreateElements()) {
            ContainerMapping containerMappingImported = containerMappingToCheck.getImportedMapping();
            EList<ContainerMapping> allReused = getReusedContainerMapping(containerMappingImported, true);
            if (allReused.contains(containerMappingImported) || allReused.contains(containerMappingToCheck)) {
                return new Object[] { containerMappingToCheck, allReused };
            }
        }
        return null;
    }

    /**
     * @param containerMapping
     *            the container mapping
     * @param recurse
     *            true to list all reusedContainerMapping of containerMapping
     *            and the reusedContainerMapping of its reusedContainerMapping,
     *            false otherwise
     * @return
     */
    private EList<ContainerMapping> getReusedContainerMapping(ContainerMapping containerMapping, boolean recurse) {
        EList<ContainerMapping> directReused = containerMapping.getReusedContainerMappings();
        if (containerMapping instanceof ContainerMappingImport && ((ContainerMappingImport) containerMapping).getImportedMapping() != null) {
            directReused.addAll(((ContainerMappingImport) containerMapping).getImportedMapping().getReusedContainerMappings());
        }
        EList<ContainerMapping> result = new BasicEList<ContainerMapping>(directReused);
        if (recurse) {
            for (ContainerMapping reusedContainerMapping : directReused) {
                result.addAll(getReusedContainerMapping(reusedContainerMapping, result));
            }
        }
        return result;
    }

    private EList<ContainerMapping> getReusedContainerMapping(ContainerMapping containerMapping, EList<ContainerMapping> alreadyReused) {
        EList<ContainerMapping> directReused = containerMapping.getReusedContainerMappings();
        if (containerMapping instanceof ContainerMappingImport && ((ContainerMappingImport) containerMapping).getImportedMapping() != null) {
            directReused.addAll(((ContainerMappingImport) containerMapping).getImportedMapping().getReusedContainerMappings());
        }
        EList<ContainerMapping> newReusedContainerMapping = new BasicEList<ContainerMapping>();
        for (ContainerMapping reusedContainerMapping : directReused) {
            if (!alreadyReused.contains(reusedContainerMapping) && !newReusedContainerMapping.contains(reusedContainerMapping)) {
                newReusedContainerMapping.add(reusedContainerMapping);
            }
        }
        EList<ContainerMapping> result = new BasicEList<ContainerMapping>(newReusedContainerMapping);
        alreadyReused.addAll(result);
        for (ContainerMapping reusedContainerMapping : newReusedContainerMapping) {
            EList<ContainerMapping> subReusedContainerMapping = getReusedContainerMapping(reusedContainerMapping, alreadyReused);
            result.addAll(subReusedContainerMapping);
            alreadyReused.addAll(subReusedContainerMapping);
        }
        return result;
    }
}
