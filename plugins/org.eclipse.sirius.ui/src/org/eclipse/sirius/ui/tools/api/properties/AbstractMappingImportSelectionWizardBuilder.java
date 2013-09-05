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
package org.eclipse.sirius.ui.tools.api.properties;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.business.api.componentization.SiriusRegistry;
import org.eclipse.sirius.business.api.query.SiriusQuery;
import org.eclipse.sirius.description.AbstractMappingImport;
import org.eclipse.sirius.description.AbstractNodeMapping;
import org.eclipse.sirius.description.DiagramDescription;
import org.eclipse.sirius.description.Group;
import org.eclipse.sirius.description.RepresentationDescription;
import org.eclipse.sirius.description.Sirius;

/**
 * A class which is able to build the items tree to select mappings to import.
 * Use with
 * {@link org.eclipse.sirius.common.ui.tools.api.selection.EObjectSelectionWizard}.
 * 
 * @author mchauvin
 */
public abstract class AbstractMappingImportSelectionWizardBuilder {

    /**
     * The current mapping.
     */
    protected AbstractNodeMapping currentMapping;

    private Collection<AbstractNodeMapping> importers;

    private Collection<Sirius> availableSiriuss;

    /**
     * Create a new instance of selection wizard .
     * 
     * @param nodeMapping
     *            the current nope mapping
     * @param availableSiriuss
     *            the available viewpoints
     */
    public AbstractMappingImportSelectionWizardBuilder(final AbstractNodeMapping nodeMapping, final Collection<Sirius> availableSiriuss) {
        this.currentMapping = nodeMapping;
        this.availableSiriuss = availableSiriuss;
    }

    /**
     * build the items.
     * 
     * @return the root {@link TreeItemWrapper}.
     */
    public TreeItemWrapper buildMappingInput() {

        buildMappingImporters();

        final TreeItemWrapper root = new TreeItemWrapper(null, null);

        for (Sirius viewpoint : availableSiriuss) {
            addSiriusItems(viewpoint, root);
        }
        return root;
    }

    /**
     * Get all the available viewpoints contained in the resource set of the
     * object given as parameter.
     * 
     * @param eObject
     *            the eObject to used to retrieve the resource set
     * @return all the available viewpoints
     */
    public static Collection<Sirius> getAvailableSiriussInResourceSet(final EObject eObject) {
        final Resource eResource = eObject.eResource();
        if (eResource != null) {
            final ResourceSet resourceSet = eResource.getResourceSet();
            final Collection<Sirius> viewpoints = new HashSet<Sirius>();
            for (final Resource resource : resourceSet.getResources()) {
                if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof Group) {
                    final Group group = (Group) resource.getContents().get(0);
                    viewpoints.addAll(group.getOwnedSiriuss());
                }
            }
            return viewpoints;
        }
        return Collections.emptySet();
    }

    private void buildMappingImporters() {
        importers = new HashSet<AbstractNodeMapping>();

        importers.add(currentMapping);
        for (Sirius viewpoint : SiriusRegistry.getInstance().getSiriuss()) {
            for (RepresentationDescription representationDescription : new SiriusQuery(viewpoint).getAllRepresentationDescriptions()) {
                if (representationDescription instanceof DiagramDescription) {
                    final DiagramDescription desc = (DiagramDescription) representationDescription;

                    for (AbstractNodeMapping mapping : getAllMappings(desc)) {
                        if (checkImportType(mapping)) {
                            addMappingImporters((AbstractMappingImport) mapping);
                        }
                    }
                }
            }
        }
    }

    private void addMappingImporters(final AbstractMappingImport mappingImport) {
        final AbstractNodeMapping importedMapping = getImportedMapping(mappingImport);
        if (isANodeMappingImporters(importedMapping)) {
            importers.add((AbstractNodeMapping) mappingImport);
        }
        if (checkImportType(importedMapping)) {
            addMappingImporters((AbstractMappingImport) importedMapping);
        }
    }

    private boolean isANodeMappingImporters(final AbstractNodeMapping importedMapping) {
        if (checkImportType(importedMapping) || EqualityHelper.contains(importers, importedMapping)) {
            return true;
        }
        return false;
    }

    private void addSiriusItems(final Sirius viewpoint, final TreeItemWrapper root) {
        final TreeItemWrapper viewpointItem = new TreeItemWrapper(viewpoint, root);
        for (RepresentationDescription represenationDescription : new SiriusQuery(viewpoint).getAllRepresentationDescriptions()) {
            if (represenationDescription instanceof DiagramDescription) {
                addDiagramDescriptionItems((DiagramDescription) represenationDescription, viewpointItem);
            }
        }
        if (!viewpointItem.getChildren().isEmpty()) {
            root.getChildren().add(viewpointItem);
        }
    }

    private void addDiagramDescriptionItems(final DiagramDescription diagramDescription, final TreeItemWrapper viewpointItem) {
        final TreeItemWrapper diagramDescriptionItem = new TreeItemWrapper(diagramDescription, viewpointItem);

        addMappingItems(diagramDescription, diagramDescriptionItem);

        if (!diagramDescriptionItem.getChildren().isEmpty()) {
            viewpointItem.getChildren().add(diagramDescriptionItem);
        }

    }

    /**
     * .
     * 
     * @param diagramDescription
     *            .
     * @param diagramDescriptionItem
     *            .
     */
    protected void addMappingItems(final DiagramDescription diagramDescription, final TreeItemWrapper diagramDescriptionItem) {
        for (AbstractNodeMapping mapping : getAllMappings(diagramDescription)) {
            if (!EcoreUtil.equals(currentMapping, mapping) && safeMappingCandidate(mapping)) {
                final TreeItemWrapper mappingItem = new TreeItemWrapper(mapping, diagramDescriptionItem);
                diagramDescriptionItem.getChildren().add(mappingItem);
            }
        }
        addMappings(diagramDescriptionItem, diagramDescription);
    }

    /**
     * Test if mapping is safe to avoid cycles in importation of mapping.
     * 
     * @param mapping
     *            the mapping to test
     * @return <code>true</code> if it safe, <code>false</code> otherwise
     */
    protected boolean safeMappingCandidate(final AbstractNodeMapping mapping) {
        if (mapping instanceof AbstractMappingImport) {
            final AbstractMappingImport mappingImport = (AbstractMappingImport) mapping;
            final AbstractNodeMapping importedMapping = getImportedMapping(mappingImport);
            return !(EqualityHelper.contains(importers, importedMapping));
        }
        return true;
    }

    /**
     * Check the type of the given mapping.
     * 
     * @param mapping
     *            the mapping for which to check type
     * @return <code>true</code> if it has the right instance,
     *         <code>false</code> otherwise.
     */
    protected abstract boolean checkImportType(AbstractNodeMapping mapping);

    /**
     * Get the imported mapping.
     * 
     * @param mappingImport
     *            the mapping import
     * @return the imported mapping
     */
    protected abstract AbstractNodeMapping getImportedMapping(AbstractMappingImport mappingImport);

    /**
     * get all mappings directly contained from the diagram description.
     * 
     * @param <T>
     *            .
     * @param diagramDescription
     *            the diagram description
     * @return all mappings directly contained.
     */
    protected abstract <T extends AbstractNodeMapping> Collection<T> getAllMappings(final DiagramDescription diagramDescription);

    /**
     * Add mappings for the current diagram description.
     * 
     * @param parentItem
     *            the parent item
     * @param diagramDescription
     *            the diagram description
     */
    protected abstract void addMappings(final TreeItemWrapper parentItem, final DiagramDescription diagramDescription);
}
