/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A class which is able to build the items tree to select node mappings to
 * import.
 * 
 * @author mchauvin
 * @see AbstractMappingImportSelectionWizardBuilder
 */
public class NodeMappingImportSelectionWizardItemsBuilder extends AbstractMappingImportSelectionWizardBuilder {

    /**
     * .
     * 
     * @param nodeMapping
     *            .
     * @param availableViewpoints
     *            the available viewpoints
     */
    public NodeMappingImportSelectionWizardItemsBuilder(final NodeMapping nodeMapping, final Collection<Viewpoint> availableViewpoints) {
        super(nodeMapping, availableViewpoints);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.AbstractMappingImportSelectionWizardBuilder#checkImportType(org.eclipse.sirius.viewpoint.description.AbstractNodeMapping)
     */
    @Override
    protected boolean checkImportType(final AbstractNodeMapping mapping) {
        if (mapping instanceof NodeMappingImport) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.AbstractMappingImportSelectionWizardBuilder#getImportedMapping(org.eclipse.sirius.viewpoint.description.AbstractMappingImport)
     */
    @Override
    protected AbstractNodeMapping getImportedMapping(final AbstractMappingImport mappingImport) {
        if (mappingImport instanceof NodeMappingImport) {
            return ((NodeMappingImport) mappingImport).getImportedMapping();
        }
        throw new IllegalArgumentException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.AbstractMappingImportSelectionWizardBuilder#getAllMappings(org.eclipse.sirius.viewpoint.description.DiagramDescription)
     */
    @Override
    protected <T extends AbstractNodeMapping> Collection<T> getAllMappings(final DiagramDescription diagramDescription) {
        return (Collection<T>) diagramDescription.getAllNodeMappings();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.AbstractMappingImportSelectionWizardBuilder#addMappings(org.eclipse.sirius.common.tools.api.util.TreeItemWrapper,
     *      org.eclipse.sirius.viewpoint.description.DiagramDescription)
     */
    @Override
    protected void addMappings(final TreeItemWrapper parentItem, final DiagramDescription diagramDescription) {
        final List<ContainerMapping> candidates = diagramDescription.getAllContainerMappings();
        addMappings(parentItem, candidates);
    }

    private void addMappings(final TreeItemWrapper parentItem, final List<ContainerMapping> candidates) {
        for (ContainerMapping mapping : candidates) {
            final ArrayList<NodeMapping> nodeCandidates = new ArrayList<NodeMapping>();
            nodeCandidates.addAll(mapping.getSubNodeMappings());
            nodeCandidates.addAll(mapping.getBorderedNodeMappings());
            for (NodeMapping nodeMapping : nodeCandidates) {
                if (!EcoreUtil.equals(currentMapping, mapping) && safeMappingCandidate(nodeMapping)) {
                    final TreeItemWrapper treeItem = new TreeItemWrapper(nodeMapping, parentItem);
                    parentItem.getChildren().add(treeItem);
                }
            }
            addMappings(parentItem, mapping.getSubContainerMappings());
        }
    }

}
