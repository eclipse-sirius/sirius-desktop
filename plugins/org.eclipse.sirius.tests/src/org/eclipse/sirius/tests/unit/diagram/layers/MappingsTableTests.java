/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.layers;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager;
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.DiagramDescriptionMappingsManagerImpl;
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.table.CandidateMappingManager;
import org.eclipse.sirius.diagram.business.internal.layers.MappingTableEntry;
import org.eclipse.sirius.diagram.business.internal.layers.MappingsTable;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;

/**
 * Tests of {@link MappingsTable} features
 * 
 * @author mchauvin
 */
public class MappingsTableTests extends AbstractMappingsTableTest {

    private DDiagram diagram;

    private Layer layer;

    MappingsTable<AbstractNodeMapping> table;

    private DiagramDescriptionMappingsManager manager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        diagram = DiagramFactory.eINSTANCE.createDDiagram();
        layer = DescriptionFactory.eINSTANCE.createLayer();
        description.setDefaultLayer(layer);

        table = new MappingsTable<AbstractNodeMapping>();
        manager = new DiagramDescriptionMappingsManagerImpl(description);
    }

    /**
     * trac bug #1938
     */
    public void testBuildWithDirectCycleContainerMappingReuseandImport() {

        AdditionalLayer optionalLayer = DescriptionFactory.eINSTANCE.createAdditionalLayer();
        optionalLayer.setActiveByDefault(true);
        description.getAdditionalLayers().add(optionalLayer);

        diagram.getActivatedLayers().add(layer);
        diagram.getActivatedLayers().add(optionalLayer);

        final ContainerMapping cm = createContainerMapping(layer);
        cm.getReusedContainerMappings().add(cm);
        createContainerMappingImport(optionalLayer, cm, false);

        manager.computeMappings(null);
        CandidateMappingManager candidateMappingsManager = new CandidateMappingManager(manager);
        candidateMappingsManager.build(diagram.getActivatedLayers());
        table.build(candidateMappingsManager.getAvailableCandidateContainerMappings());
    }

    public void testBuildWithIndirectCycleContainerMappingReuseAndImport() {

        diagram.getActivatedLayers().add(layer);

        final ContainerMapping cm = createContainerMapping(layer);

        final ContainerMapping subCm = DescriptionFactory.eINSTANCE.createContainerMapping();
        mappings.add(subCm);

        cm.getSubContainerMappings().add(subCm);
        subCm.getReusedContainerMappings().add(cm);

        /* try to build with only indirect cycle */
        manager.computeMappings(null);
        CandidateMappingManager candidateMappingsManager = new CandidateMappingManager(manager);
        candidateMappingsManager.build(diagram.getActivatedLayers());
        table.build(candidateMappingsManager.getAvailableCandidateContainerMappings());

        /* add a mapping import */
        AdditionalLayer optionalLayer = DescriptionFactory.eINSTANCE.createAdditionalLayer();
        optionalLayer.setActiveByDefault(true);
        description.getAdditionalLayers().add(optionalLayer);

        diagram.getActivatedLayers().add(optionalLayer);
        createContainerMappingImport(optionalLayer, cm, false);

        /* try to build with an import + indirect cycle */
        manager.computeMappings(null);
        candidateMappingsManager = new CandidateMappingManager(manager);
        candidateMappingsManager.build(diagram.getActivatedLayers());
        table.build(candidateMappingsManager.getAvailableCandidateContainerMappings());
    }

    public void testSearch() {

        diagram.getActivatedLayers().add(layer);

        final AbstractNodeMapping mappingB = createNodeMapping(layer);
        final AbstractNodeMapping mappingBI1importB = createNodeMappingImport(layer, (NodeMapping) mappingB, false);
        final AbstractNodeMapping mappingBI2importB = createNodeMappingImport(layer, (NodeMapping) mappingB, false);

        manager.computeMappings(null);
        CandidateMappingManager candidateMappingsManager = new CandidateMappingManager(manager);
        candidateMappingsManager.build(diagram.getActivatedLayers());
        table.build(candidateMappingsManager.getAvailableCandidateNodeMappings());

        MappingTableEntry result = table.searchMappingEntry(mappingB);
        assertNotNull(result);
        assertEquals(mappingB, result.getMapping());

        result = table.searchMappingEntry(mappingBI1importB);
        assertNotNull(result);
        assertEquals(mappingBI1importB, result.getMapping());

        result = table.searchMappingEntry(mappingBI2importB);
        assertNotNull(result);
        assertEquals(mappingBI2importB, result.getMapping());
    }
}
