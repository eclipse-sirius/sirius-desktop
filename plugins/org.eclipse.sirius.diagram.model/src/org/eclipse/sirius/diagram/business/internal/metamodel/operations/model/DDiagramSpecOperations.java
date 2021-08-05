/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.operations.model;

import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.LayerModelHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DView;

/**
 * Implementation of DDiagramImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public final class DDiagramSpecOperations {

    /**
     * Avoid instantiation.
     */
    private DDiagramSpecOperations() {

    }

    /**
     * Return the analysis of the specified viewpoint.
     * 
     * @param diagram
     *            the viewpoint.
     * @return the analysis of the specified viewpoint.
     */
    public static DView basicGetView(final DDiagram diagram) {
        EObject cur = diagram.eContainer();
        DView result = null;
        while (cur != null && result == null) {
            if (cur instanceof DView) {
                result = (DView) cur;
            }
            cur = cur.eContainer();
        }
        return result;
    }

    /**
     * Returns all nodes that are in the specified viewpoint and that have been created from the specified mapping.
     * 
     * @param vp
     *            the viewpoint.
     * @param mapping
     *            the mapping.
     * @return all nodes that are in the specified viewpoint and that have been created from the specified mapping.
     */
    public static EList<DNode> getNodesFromMapping(final DDiagram vp, final NodeMapping mapping) {
        final EList<DNode> result = new BasicEList<DNode>();
        final Iterator<DNode> it = vp.getNodes().iterator();
        while (it.hasNext()) {
            final DNode node = it.next();
            if (node.getActualMapping() == mapping) {
                result.add(node);
            }
        }
        return result;
    }

    /**
     * Returns all edges that are in the specified viewpoint and that have been created from the specified mapping or by
     * an imported mapping of the specified mapping or by a mapping which import the specified mapping.
     * 
     * @param vp
     *            the viewpoint.
     * @param mapping
     *            the mapping.
     * @return all edges that are in the specified viewpoint and that have been created from the specified mapping.
     */
    public static EList<DEdge> getEdgesFromMapping(final DDiagram vp, final EdgeMapping mapping) {
        final EList<DEdge> result = new BasicEList<DEdge>();
        IEdgeMapping mappingToCompare = mapping;
        if (mapping instanceof EdgeMappingImportWrapper) {
            mappingToCompare = ((EdgeMappingImportWrapper) mapping).getWrappedEdgeMappingImport();
        }
        final Iterator<DEdge> it = vp.getEdges().iterator();
        while (it.hasNext()) {
            final DEdge edge = it.next();
            boolean importedOrImport = false;

            IEdgeMapping edgeMapping = edge.getActualMapping();
            if (edgeMapping instanceof EdgeMappingImportWrapper) {
                edgeMapping = ((EdgeMappingImportWrapper) edgeMapping).getWrappedEdgeMappingImport();
            }

            if (mappingToCompare instanceof EdgeMappingImport) {
                importedOrImport = LayerModelHelper.isImported(edgeMapping, (EdgeMappingImport) mappingToCompare);
            }
            if (!importedOrImport && edgeMapping instanceof EdgeMappingImport) {
                importedOrImport = LayerModelHelper.isImported(mappingToCompare, (EdgeMappingImport) edgeMapping);
            }
            if (edgeMapping == mappingToCompare || importedOrImport) {
                result.add(edge);
            }
        }
        return result;
    }

    /**
     * Returns all containers that are in the specified viewpoint and that have been created from the specified mapping.
     * 
     * @param vp
     *            the viewpoint.
     * @param mapping
     *            the mapping.
     * @return all containers that are in the specified viewpoint and that have been created from the specified mapping.
     */
    public static EList<DDiagramElementContainer> getContainersFromMapping(final DDiagram vp, final ContainerMapping mapping) {
        final EList<DDiagramElementContainer> result = new BasicEList<DDiagramElementContainer>();
        final Iterator<DDiagramElementContainer> it = vp.getContainers().iterator();
        while (it.hasNext()) {

            final DDiagramElementContainer container = it.next();
            if (container.getActualMapping() == mapping) {
                result.add(container);
            }
        }
        return result;
    }
}
