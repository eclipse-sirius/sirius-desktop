/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.DiagramDescriptionQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramExtensionDescriptionQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContentHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramComponentizationHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * Class dedicated to tools applicability test. Should not be used directly but leveraged through the specific queries.
 * see xxxCreationDescriptionQuery()...
 * 
 * @author cbrun
 * 
 */
public class AbstractNodeMappingApplicabilityTester {

    private Iterable<? extends AbstractNodeMapping> mappingsToCreate;

    /**
     * Create a new tester.
     * 
     * @param mappingsToCreate
     *            mappings of the elements to test the creation.
     */
    public AbstractNodeMappingApplicabilityTester(Iterable<? extends AbstractNodeMapping> mappingsToCreate) {
        this.mappingsToCreate = mappingsToCreate;
    }

    private boolean canCreateIn(final AbstractNodeMapping containerMapping) {
        boolean canCreateIn = false;
        List<DiagramElementMapping> selfAndSuperTypes = createSelfAndSuperType(containerMapping);
        Iterator<DiagramElementMapping> superTypes = selfAndSuperTypes.iterator();
        while (!canCreateIn && superTypes.hasNext()) {
            /*
             * we're pretty sure the super of a container mapping is a container mapping, the cast is safe.
             */

            DiagramElementMapping selfOrSuper = superTypes.next();
            if (selfOrSuper instanceof ContainerMapping) {
                if (checkValidSubMappingsAreSubtypeOf((ContainerMapping) selfOrSuper, mappingsToCreate)) {
                    canCreateIn = true;
                }
            } else if (selfOrSuper instanceof NodeMapping) {
                if (checkValidSubMappingsAreSubtypeOf((NodeMapping) selfOrSuper, mappingsToCreate)) {
                    canCreateIn = true;
                }
            }
        }
        return canCreateIn;
    }

    /**
     * return true if the node could be created on the current container checking the mapping consistency.
     * 
     * @param diagram
     *            any diagram
     * @return true if the node could be created on the current container checking the mapping consistency.
     */
    public boolean canCreateIn(DDiagram diagram) {
        if (diagram.getDescription() != null) {
            return canCreateIn(diagram.getDescription());
        }
        return false;
    }

    private boolean canCreateIn(DiagramDescription description) {
        if (Iterables.isEmpty(mappingsToCreate)) {
            return false;
        }
        boolean canCreateIn = false;
        List<DiagramDescription> selfAndSuperTypes = createSelfAndSuperType(description);
        Iterator<DiagramDescription> superTypes = selfAndSuperTypes.iterator();
        while (!canCreateIn && superTypes.hasNext()) {
            /*
             * we're pretty sure the super of a container mapping is a container mapping, the cast is safe.
             */

            DiagramDescription selfOrSuper = superTypes.next();
            if (checkValidSubMappingsAreSubtypeOf(selfOrSuper, mappingsToCreate)) {
                canCreateIn = true;
            }
            EObject eContainerValue = null;
            if (!Iterables.isEmpty(mappingsToCreate)) {
                eContainerValue = mappingsToCreate.iterator().next();
                while (!(eContainerValue instanceof DiagramExtensionDescription) && eContainerValue.eContainer() != null) {
                    eContainerValue = eContainerValue.eContainer();
                }
            }
            if (eContainerValue instanceof DiagramExtensionDescription) {
                DiagramExtensionDescription diagramExtensionDescription = (DiagramExtensionDescription) eContainerValue;
                Collection<Viewpoint> viewpoints = new ArrayList<Viewpoint>();
                viewpoints.add((Viewpoint) selfOrSuper.eContainer());
                DiagramDescription diagramDescription = DiagramComponentizationHelper.getDiagramDescription(diagramExtensionDescription, viewpoints);
                if (selfOrSuper.equals(diagramDescription)) {
                    if (checkValidSubMappingsAreSubtypeOf(diagramExtensionDescription, mappingsToCreate)) {
                        canCreateIn = true;
                    }
                }
            }

        }
        return canCreateIn;
    }

    private boolean checkValidSubMappingsAreSubtypeOf(DiagramDescription description, Iterable<? extends AbstractNodeMapping> collectToolNodeMappings) {
        for (final AbstractNodeMapping validChild : Iterables.concat(ContentHelper.getAllNodeMappings(description, false), ContentHelper.getAllContainerMappings(description, false))) {
            if (doCheckAtLeastOneIsSubTypeOf(validChild, collectToolNodeMappings)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkValidSubMappingsAreSubtypeOf(DiagramExtensionDescription description, Iterable<? extends AbstractNodeMapping> collectToolNodeMappings) {
        DiagramExtensionDescriptionQuery extensionQuery = new DiagramExtensionDescriptionQuery(description);
        for (AbstractNodeMapping child : Iterables.concat(extensionQuery.getAllNodeMappings(), extensionQuery.getAllContainerMappings())) {
            if (doCheckAtLeastOneIsSubTypeOf(child, collectToolNodeMappings)) {
                return true;
            }
        }
        return false;
    }

    private List<DiagramDescription> createSelfAndSuperType(DiagramDescription description) {
        List<DiagramDescription> selfAndSuperTypes = new ArrayList<>();
        selfAndSuperTypes.add(description);
        Iterators.addAll(selfAndSuperTypes, new DiagramDescriptionQuery(description).superTypes());
        return selfAndSuperTypes;
    }

    /**
     * return true if the node could be created on the current container checking the mapping consistency.
     * 
     * @param container
     *            any container
     * @return true if the node could be created on the current container checking the mapping consistency.
     */
    public boolean canCreateIn(DDiagramElementContainer container) {
        /*
         * here we should check if one of the node mappings is inside the container mapping
         */
        final ContainerMapping containerMapping = container.getActualMapping();
        if (containerMapping != null) {
            return canCreateIn(containerMapping);
        }
        return false;
    }

    /**
     * return true if the node could be created on the current container checking the mapping consistency.
     * 
     * @param node
     *            any container node
     * @return true if the node could be created on the current container checking the mapping consistency.
     */
    public boolean canCreateIn(DNode node) {
        final NodeMapping nodeMapping = node.getActualMapping();
        if (nodeMapping != null) {
            return canCreateIn(nodeMapping);
        }
        return false;

    }

    private boolean checkValidSubMappingsAreSubtypeOf(final ContainerMapping containerMapping, Iterable<? extends AbstractNodeMapping> toolDefinedMappings) {
        for (final AbstractNodeMapping validChild : Iterables.concat(MappingHelper.getAllNodeMappings(containerMapping), MappingHelper.getAllBorderedNodeMappings(containerMapping),
                MappingHelper.getAllContainerMappings(containerMapping))) {
            if (doCheckAtLeastOneIsSubTypeOf(validChild, toolDefinedMappings)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkValidSubMappingsAreSubtypeOf(NodeMapping selfOrSuper, Iterable<? extends AbstractNodeMapping> collectToolNodeMappings) {
        for (final AbstractNodeMapping validChild : MappingHelper.getAllBorderedNodeMappings(selfOrSuper)) {
            if (doCheckAtLeastOneIsSubTypeOf(validChild, collectToolNodeMappings)) {
                return true;
            }
        }
        return false;
    }

    private List<DiagramElementMapping> createSelfAndSuperType(final AbstractNodeMapping containerMapping) {
        List<DiagramElementMapping> selfAndSuperTypes = new ArrayList<>();
        selfAndSuperTypes.add(containerMapping);
        Iterators.addAll(selfAndSuperTypes, new DiagramElementMappingQuery(containerMapping).superTypes());
        return selfAndSuperTypes;
    }

    private boolean doCheckAtLeastOneIsSubTypeOf(AbstractNodeMapping validChild, Iterable<? extends AbstractNodeMapping> toolDefinedMappings) {
        DiagramElementMappingQuery query = new DiagramElementMappingQuery(validChild);
        for (final AbstractNodeMapping toolMap : toolDefinedMappings) {
            if (query.isSuperTypeOf(toolMap)) {
                return true;
            }
        }
        return false;
    }

}
