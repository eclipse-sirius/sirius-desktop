/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.validation.description.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.filter.MappingFilter;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;

/**
 * Container children presentation related validation rules.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class ContainerMappingChildrenPresentationConstraints extends AbstractConstraint {

    /**
     * Compartment containment rule ID.
     */
    public static final String COMPARTMENT_CONTAINMENT_RULE_ID = "org.eclipse.sirius.diagram.constraints.CompartmentContainmentConstraint"; //$NON-NLS-1$

    /**
     * Reuse containers in ContainerList rule ID.
     */
    public static final String REUSE_CONTAINERS_IN_CONTAINER_LIST_RULE_ID = "org.eclipse.sirius.diagram.constraints.ReuseContainersInContainerListConstraint"; //$NON-NLS-1$

    /**
     * Containers in ContainerList rule ID.
     */
    public static final String CONTAINERS_IN_CONTAINER_LIST_RULE_ID = "org.eclipse.sirius.diagram.constraints.ContainersInContainerListConstraint"; //$NON-NLS-1$

    /**
     * Reused Nodes in RegionContainer rule ID.
     */
    public static final String REUSE_NODES_IN_REGION_CONTAINER_RULE_ID = "org.eclipse.sirius.diagram.constraints.NoReusedNodesInRegionContainerConstraint"; //$NON-NLS-1$

    /**
     * Reused BorderedNodes in Region rule ID.
     */
    public static final String REUSE_BORDERED_NODES_IN_REGION_CONTAINER_RULE_ID = "org.eclipse.sirius.diagram.constraints.NoReusedBorderedNodesInRegion"; //$NON-NLS-1$

    /**
     * Nodes in RegionContainer rule ID.
     */
    public static final String NODES_IN_REGION_CONTAINER_RULE_ID = "org.eclipse.sirius.diagram.constraints.NoSubNodesInRegionContainerConstraint"; //$NON-NLS-1$

    /**
     * BorderedNodes in RegionContainer rule ID.
     */
    public static final String BORDERED_NODES_IN_REGION_CONTAINER_RULE_ID = "org.eclipse.sirius.diagram.constraints.NoBorderedNodesInRegion"; //$NON-NLS-1$

    /**
     * Mapping filter on region mapping rule ID.
     */
    public static final String MAPPING_FILTER_ON_REGION_MAPPING_RULE_ID = "org.eclipse.sirius.diagram.constraints.NoMappingFilterOnRegionsConstraint"; //$NON-NLS-1$

    /**
     * Style hierarchy rule ID.
     */
    public static final String REGION_REGION_CONTAINER_MAPPING_STYLE_RULE_ID = "org.eclipse.sirius.diagram.constraints.RegionStyleConstraint"; //$NON-NLS-1$

    /**
     * Region mapping cardinality rule ID.
     */
    public static final String REGION_MAPPING_CARDINALITY_RULE_ID = "org.eclipse.sirius.diagram.constraints.RegionMappingCardinalityConstraint"; //$NON-NLS-1$

    @Override
    public IStatus validate(IValidationContext ctx) {
        IStatus result = ctx.createSuccessStatus();
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof ContainerMapping) {
            ContainerMapping containerMapping = (ContainerMapping) eObj;
            if (REGION_MAPPING_CARDINALITY_RULE_ID.equals(ctx.getCurrentConstraintId())) {
                result = validateRegionMappingCardinality(ctx, containerMapping);
            } else if (COMPARTMENT_CONTAINMENT_RULE_ID.equals(ctx.getCurrentConstraintId())) {
                result = validateCompartmentContainment(ctx, containerMapping);
            } else if (REUSE_CONTAINERS_IN_CONTAINER_LIST_RULE_ID.equals(ctx.getCurrentConstraintId())) {
                result = validateReusedContainersInContainerList(ctx, containerMapping);
            } else if (CONTAINERS_IN_CONTAINER_LIST_RULE_ID.equals(ctx.getCurrentConstraintId())) {
                result = validateContainersInContainerList(ctx, containerMapping);
            } else if (REUSE_NODES_IN_REGION_CONTAINER_RULE_ID.equals(ctx.getCurrentConstraintId())) {
                result = validateReusedSubNodesInRegionContainer(ctx, containerMapping);
            } else if (REUSE_BORDERED_NODES_IN_REGION_CONTAINER_RULE_ID.equals(ctx.getCurrentConstraintId())) {
                result = validateReusedBorderedNodesInRegion(ctx, containerMapping);
            }
        } else if (eObj instanceof NodeMapping) {
            NodeMapping currentMapping = (NodeMapping) eObj;
            if (currentMapping.eContainer() instanceof ContainerMapping) {
                ContainerMapping parentMapping = (ContainerMapping) currentMapping.eContainer();
                EStructuralFeature eContainingFeature = currentMapping.eContainingFeature();
                if (DescriptionPackage.eINSTANCE.getContainerMapping_SubNodeMappings().equals(eContainingFeature) && NODES_IN_REGION_CONTAINER_RULE_ID.equals(ctx.getCurrentConstraintId())) {
                    result = validateSubNodesInRegionContainer(ctx, currentMapping, parentMapping);
                } else if (DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings().equals(eContainingFeature)
                        && BORDERED_NODES_IN_REGION_CONTAINER_RULE_ID.equals(ctx.getCurrentConstraintId())) {
                    result = validateBorderedNodesInRegion(ctx, currentMapping, parentMapping);
                }
            }
        } else if (eObj instanceof MappingFilter && MAPPING_FILTER_ON_REGION_MAPPING_RULE_ID.equals(ctx.getCurrentConstraintId())) {
            result = validateMappingFilterOnRegions(ctx, (MappingFilter) eObj);
        } else if (eObj instanceof ContainerStyleDescription) {
            Option<EObject> parentContainerMapping = new EObjectQuery(eObj).getFirstAncestorOfType(DescriptionPackage.eINSTANCE.getContainerMapping());
            if (REGION_REGION_CONTAINER_MAPPING_STYLE_RULE_ID.equals(ctx.getCurrentConstraintId()) && parentContainerMapping.some()) {
                result = validateStyle(ctx, (ContainerStyleDescription) eObj, (ContainerMapping) parentContainerMapping.get());
            }
        }
        return result;
    }

    /**
     * A RegionContainer mapping must defined at least one Region mapping.
     */
    private IStatus validateRegionMappingCardinality(IValidationContext ctx, ContainerMapping containerMapping) {
        if (new ContainerMappingQuery(containerMapping).isRegionContainer() && containerMapping.getAllContainerMappings().isEmpty()) {
            return ctx.createFailureStatus(new Object[] { containerMapping });
        }
        return ctx.createSuccessStatus();
    }

    /**
     * Compartment mapping recursion validation rules.
     */
    private IStatus validateCompartmentContainment(IValidationContext ctx, ContainerMapping containerMapping) {
        ContainerMappingQuery query = new ContainerMappingQuery(containerMapping);
        if (query.isRegionContainer() && query.isRegion()) {
            return ctx.createFailureStatus(new Object[] { containerMapping });
        }
        return ctx.createSuccessStatus();
    }

    /**
     * Reused Containers in Container List validation rules.
     */
    private IStatus validateReusedContainersInContainerList(IValidationContext ctx, ContainerMapping containerMapping) {
        if (new ContainerMappingQuery(containerMapping).isListContainer()) {
            if (!containerMapping.getReusedContainerMappings().isEmpty()) {
                return ctx.createFailureStatus(containerMapping.getReusedContainerMappings());
            }
        }
        return ctx.createSuccessStatus();
    }

    /**
     * Containers in Container List validation rules.
     */
    private IStatus validateContainersInContainerList(IValidationContext ctx, ContainerMapping containerMapping) {
        EObject parentMapping = containerMapping.eContainer();
        if (parentMapping instanceof ContainerMapping) {
            if (new ContainerMappingQuery((ContainerMapping) parentMapping).isListContainer()) {
                return ctx.createFailureStatus(new Object[] { containerMapping });
            }
        }
        return ctx.createSuccessStatus();
    }

    /**
     * Reused Nodes in RegionContainer validation rules.
     */
    private IStatus validateReusedSubNodesInRegionContainer(IValidationContext ctx, ContainerMapping containerMapping) {
        if (new ContainerMappingQuery(containerMapping).isRegionContainer()) {
            if (!containerMapping.getReusedNodeMappings().isEmpty()) {
                return ctx.createFailureStatus(new Object[] { containerMapping });
            }
        }
        return ctx.createSuccessStatus();
    }

    /**
     * Reused BorderedNodes for Region validation rules.
     */
    private IStatus validateReusedBorderedNodesInRegion(IValidationContext ctx, ContainerMapping containerMapping) {
        if (new ContainerMappingQuery(containerMapping).isRegion()) {
            if (!containerMapping.getReusedBorderedNodeMappings().isEmpty()) {
                return ctx.createFailureStatus(containerMapping.getReusedBorderedNodeMappings());
            }
        }
        return ctx.createSuccessStatus();
    }

    /**
     * Nodes in RegionContainer validation rules.
     */
    private IStatus validateSubNodesInRegionContainer(IValidationContext ctx, NodeMapping nodeMapping, ContainerMapping parentMapping) {
        if (new ContainerMappingQuery(parentMapping).isRegionContainer()) {
            return ctx.createFailureStatus(new Object[] { nodeMapping });
        }
        return ctx.createSuccessStatus();
    }

    /**
     * BorderedNodes In Region validation rules.
     */
    private IStatus validateBorderedNodesInRegion(IValidationContext ctx, NodeMapping nodeMapping, ContainerMapping parentMapping) {
        if (new ContainerMappingQuery(parentMapping).isRegion()) {
            return ctx.createFailureStatus(new Object[] { nodeMapping });
        }
        return ctx.createSuccessStatus();
    }

    /**
     * Mapping filter nodes on Regions validation rules.
     */
    private IStatus validateMappingFilterOnRegions(IValidationContext ctx, MappingFilter filter) {
        for (DiagramElementMapping mapping : filter.getMappings()) {
            if (mapping instanceof ContainerMapping && new ContainerMappingQuery((ContainerMapping) mapping.eContainer()).isRegionContainer()) {
                return ctx.createFailureStatus(new Object[] { filter });
            }
        }
        return ctx.createSuccessStatus();
    }

    /**
     * RegionContainer and Region mappings only support the Gradient style.
     */
    private IStatus validateStyle(IValidationContext ctx, ContainerStyleDescription style, ContainerMapping containerMapping) {
        ContainerMappingQuery query = new ContainerMappingQuery(containerMapping);
        if (!(style instanceof FlatContainerStyleDescription) && (query.isRegionContainer() || query.isRegion())) {
            return ctx.createFailureStatus(new Object[] { style, containerMapping });
        }
        return ctx.createSuccessStatus();
    }

}
