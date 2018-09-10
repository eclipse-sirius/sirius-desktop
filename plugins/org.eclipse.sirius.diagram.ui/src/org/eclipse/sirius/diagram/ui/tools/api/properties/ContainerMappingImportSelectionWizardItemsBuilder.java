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
package org.eclipse.sirius.diagram.ui.tools.api.properties;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContentHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A class which is able to build the items tree to select container mappings to
 * import.
 * 
 * @author mchauvin
 * @see AbstractMappingImportSelectionWizardBuilder
 */
public class ContainerMappingImportSelectionWizardItemsBuilder extends AbstractMappingImportSelectionWizardBuilder {

    /**
     * Builder for container.
     * 
     * @param containerMapping
     *            a container mapping
     * @param availableViewpoints
     *            the available viewpoints
     */
    public ContainerMappingImportSelectionWizardItemsBuilder(final ContainerMapping containerMapping, final Collection<Viewpoint> availableViewpoints) {
        super(containerMapping, availableViewpoints);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.AbstractMappingImportSelectionWizardBuilder#addMappings(org.eclipse.sirius.common.tools.api.util.TreeItemWrapper,
     *      org.eclipse.sirius.viewpoint.description.DiagramDescription)
     */
    @Override
    protected void addMappings(final TreeItemWrapper parentItem, final DiagramDescription diagramDescription) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.AbstractMappingImportSelectionWizardBuilder#checkImportType(org.eclipse.sirius.viewpoint.description.AbstractNodeMapping)
     */
    @Override
    protected boolean checkImportType(final AbstractNodeMapping mapping) {
        if (mapping instanceof ContainerMappingImport) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.AbstractMappingImportSelectionWizardBuilder#getAllMappings(org.eclipse.sirius.viewpoint.description.DiagramDescription)
     */
    @Override
    protected <T extends AbstractNodeMapping> Collection<T> getAllMappings(final DiagramDescription diagramDescription) {
        return (Collection<T>) ContentHelper.getAllContainerMappings(diagramDescription, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.AbstractMappingImportSelectionWizardBuilder#getImportedMapping(org.eclipse.sirius.viewpoint.description.AbstractMappingImport)
     */
    @Override
    protected AbstractNodeMapping getImportedMapping(final AbstractMappingImport mappingImport) {
        if (mappingImport instanceof ContainerMappingImport) {
            return ((ContainerMappingImport) mappingImport).getImportedMapping();
        }
        throw new IllegalArgumentException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.properties.AbstractMappingImportSelectionWizardBuilder#addMappingItems(org.eclipse.sirius.viewpoint.description.DiagramDescription,
     *      org.eclipse.sirius.common.tools.api.util.TreeItemWrapper)
     */
    @Override
    protected void addMappingItems(final DiagramDescription diagramDescription, final TreeItemWrapper diagramDescriptionItem) {
        final List<ContainerMapping> candidates = ContentHelper.getAllContainerMappings(diagramDescription, false);
        addMappingItems(diagramDescriptionItem, candidates);
    }

    private void addMappingItems(final TreeItemWrapper parentItem, final List<ContainerMapping> candidates) {
        for (ContainerMapping mapping : candidates) {
            if (!EcoreUtil.equals(currentMapping, mapping) && safeMappingCandidate(mapping)) {
                final TreeItemWrapper treeItem = new TreeItemWrapper(mapping, parentItem);
                parentItem.getChildren().add(treeItem);
                addMappingItems(treeItem, mapping.getSubContainerMappings());
            } else {
                addMappingItems(parentItem, mapping.getSubContainerMappings());
            }

        }
    }

}
