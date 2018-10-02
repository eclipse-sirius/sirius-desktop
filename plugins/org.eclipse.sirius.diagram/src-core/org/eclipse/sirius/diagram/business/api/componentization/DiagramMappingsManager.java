/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.componentization;

import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingsListVisitor;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Build the mapping table for one diagram. Build is launch on layer
 * modification and when DiagramDescriptionMappingManager is changed. The
 * available mapping depends on the activated layers.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface DiagramMappingsManager {

    /**
     * Compute the available mappings depending on the activated layers.
     * 
     * @param vps
     *            the list of viewpoints to consider.
     * 
     * @param computeDescriptionMappings
     *            if set to <code>true</code>, the available mappings from
     *            diagram description will be recomputed
     */
    void computeMappings(Collection<Viewpoint> vps, boolean computeDescriptionMappings);

    /**
     * Get available node mappings for the diagram.
     * 
     * @return node mappings
     */
    List<NodeMapping> getNodeMappings();

    /**
     * Get available container mappings for the diagram.
     * 
     * @return edge mappings
     */
    List<ContainerMapping> getContainerMappings();

    /**
     * Get available edge mappings for the diagram.
     * 
     * @return edge mappings
     */
    List<EdgeMapping> getEdgeMappings();

    /**
     * Get available container mappings children of the mapping of the given
     * container.
     * 
     * @param container
     *            the container
     * @return container mappings
     */
    List<ContainerMapping> getContainerMappings(DNodeContainer container);

    /**
     * Get {@link ContainerMapping}s valid to create view children of the
     * specified <code>container</code> view. If
     * <code>takeAlsoChildrenOfAllImportedMappings</code> is at true take also
     * all children of mapping import inheritance until one has its
     * hideSubMappings attribute at true.
     * 
     * @param container
     *            {@link ContainerMapping}s valid to create view children of the
     *            specified <code>container</code> view
     * @param takeAlsoChildrenOfAllImportedMappings
     *            if <code>takeAlsoChildrenOfAllImportedMappings</code> is at
     *            true take also all children of mapping import inheritance
     *            until one has its hideSubMappings attribute, else take only
     *            {@link ContainerMapping} children of the actualMapping of the
     *            specified view
     * @return bordered {@link ContainerMapping}s valid to create view children
     *         of the specified <code>node</code> view
     */
    List<ContainerMapping> getContainerMappings(DNodeContainer container, boolean takeAlsoChildrenOfAllImportedMappings);

    /**
     * Get available node mappings children of the mapping of the given
     * container.
     * 
     * @param container
     *            the container
     * @return node mappings
     */
    List<NodeMapping> getNodeMappings(DNodeContainer container);

    /**
     * Get {@link NodeMapping}s valid to create view children of the specified
     * <code>container</code> view. If
     * <code>takeAlsoChildrenOfAllImportedMappings</code> is at true take also
     * all children of mapping import inheritance until one has its
     * hideSubMappings attribute at true.
     * 
     * @param container
     *            {@link NodeMapping}s valid to create view children of the
     *            specified <code>container</code> view
     * @param takeAlsoChildrenOfAllImportedMappings
     *            if <code>takeAlsoChildrenOfAllImportedMappings</code> is at
     *            true take also all children of mapping import inheritance
     *            until one has its hideSubMappings attribute, else take only
     *            {@link NodeMapping} children of the actualMapping of the
     *            specified view
     * @return bordered {@link NodeMapping}s valid to create view children of
     *         the specified <code>node</code> view
     */
    List<NodeMapping> getNodeMappings(DNodeContainer container, boolean takeAlsoChildrenOfAllImportedMappings);

    /**
     * Get available node mappings children of the mapping of the given
     * container.
     * 
     * @param container
     *            the container
     * @return node mappings
     */
    List<NodeMapping> getNodeMappings(DNodeList container);

    /**
     * Get {@link NodeMapping}s valid to create view children of the specified
     * <code>container</code> view. If
     * <code>takeAlsoChildrenOfAllImportedMappings</code> is at true take also
     * all children of mapping import inheritance until one has its
     * hideSubMappings attribute at true.
     * 
     * @param container
     *            {@link NodeMapping}s valid to create view children of the
     *            specified <code>container</code> view
     * @param takeAlsoChildrenOfAllImportedMappings
     *            if <code>takeAlsoChildrenOfAllImportedMappings</code> is at
     *            true take also all children of mapping import inheritance
     *            until one has its hideSubMappings attribute, else take only
     *            {@link NodeMapping} children of the actualMapping of the
     *            specified view
     * @return bordered {@link NodeMapping}s valid to create view children of
     *         the specified <code>node</code> view
     */
    List<NodeMapping> getNodeMappings(DNodeList container, boolean takeAlsoChildrenOfAllImportedMappings);

    /**
     * Get available bordered of the mapping of the given node or container.
     * 
     * @param node
     *            the node or container
     * @return bordered node mappings
     */
    List<NodeMapping> getBorderedNodeMappings(AbstractDNode node);

    /**
     * Get bordered {@link NodeMapping}s valid to create view children of the
     * specified <code>node</code> view. If
     * <code>takeAlsoChildrenOfAllImportedMappings</code> is at true take also
     * all children of mapping import inheritance until one has its
     * hideSubMappings attribute at true.
     * 
     * @param node
     *            bordered {@link NodeMapping}s valid to create view children of
     *            the specified <code>node</code> view
     * @param takeAlsoChildrenOfAllImportedMappings
     *            if <code>takeAlsoChildrenOfAllImportedMappings</code> is at
     *            true take also all children of mapping import inheritance
     *            until one has its hideSubMappings attribute, else take only
     *            {@link NodeMapping} children of the actualMapping of the
     *            specified view
     * @return bordered {@link NodeMapping}s valid to create view children of
     *         the specified <code>node</code> view
     */
    List<NodeMapping> getBorderedNodeMappings(AbstractDNode node, boolean takeAlsoChildrenOfAllImportedMappings);

    /**
     * Get the all the importer mappings which were not selected as primary
     * importer.
     * 
     * @return the other importer mappings, an empty list if we are not in layer
     *         mode
     */
    List<DiagramElementMapping> getOtherImportersMappings();

    /**
     * return all Layers which use this mapping if available.
     * 
     * @param mapping
     *            any {@link DiagramElementMapping} .
     * @return a containing Layer if available.
     */
    Collection<Layer> getActiveParentLayers(DiagramElementMapping mapping);

    /**
     * Iterate over the mappings in the smartest way for layers. The mappings
     * list will be browsed with the following rules : - if a mapping A is
     * defined on layers, which is not active, then it will not appear - if a
     * mapping AA import A, then mapping AA will appear before mapping A. - if a
     * mapping AA imports A, and mapping AA hides submappings, then A will not
     * appear.
     * 
     * @param <T>
     *            .
     * 
     * @param visitor
     *            the iteration callback which will be called for each
     *            appropriate mapping
     * @param container
     *            the viewpoint container
     */
    <T extends AbstractNodeMapping> void iterate(MappingsListVisitor visitor, DragAndDropTarget container);
}
