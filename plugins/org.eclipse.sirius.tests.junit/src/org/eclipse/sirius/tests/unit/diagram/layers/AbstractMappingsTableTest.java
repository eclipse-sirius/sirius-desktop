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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;

import junit.framework.TestCase;

public class AbstractMappingsTableTest extends TestCase {

    protected List<AbstractNodeMapping> mappings;

    protected DiagramDescription description;

    @Override
    protected void setUp() throws Exception {

        description = DescriptionFactory.eINSTANCE.createDiagramDescription();
        mappings = new ArrayList<AbstractNodeMapping>();
        super.setUp();
    }

    protected NodeMapping createNodeMapping(final Layer layer) {

        final NodeMapping mapping = DescriptionFactory.eINSTANCE.createNodeMapping();
        mappings.add(mapping);
        if (layer != null)
            layer.getNodeMappings().add(mapping);
        else
            description.getNodeMappings().add(mapping);
        return mapping;
    }

    protected ContainerMapping createContainerMapping(final Layer layer) {

        final ContainerMapping mapping = DescriptionFactory.eINSTANCE.createContainerMapping();
        mappings.add(mapping);
        if (layer != null)
            layer.getContainerMappings().add(mapping);
        else
            description.getContainerMappings().add(mapping);
        return mapping;
    }

    protected NodeMapping createNodeMappingImport(final Layer layer, final NodeMapping importedMapping, final boolean hideSubMappings) {

        final NodeMappingImport mapping = DescriptionFactory.eINSTANCE.createNodeMappingImport();
        mapping.setImportedMapping(importedMapping);
        mappings.add(mapping);
        if (layer != null)
            layer.getNodeMappings().add(mapping);
        else
            description.getNodeMappings().add(mapping);
        if (hideSubMappings)
            mapping.setHideSubMappings(hideSubMappings);
        return mapping;
    }

    protected ContainerMappingImport createContainerMappingImport(final Layer layer, final ContainerMapping importedMapping, final boolean hideSubMappings) {

        final ContainerMappingImport mapping = DescriptionFactory.eINSTANCE.createContainerMappingImport();
        mapping.setImportedMapping(importedMapping);
        mappings.add(mapping);
        if (layer != null)
            layer.getContainerMappings().add(mapping);
        else
            description.getContainerMappings().add(mapping);
        if (hideSubMappings)
            mapping.setHideSubMappings(hideSubMappings);
        return mapping;
    }
}
