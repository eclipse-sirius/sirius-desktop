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
package org.eclipse.sirius.tests.unit.common.migration;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.osgi.framework.Version;

import com.google.common.collect.Iterators;

/**
 * Check that migration is OK for VSM and representations file from Sirius 1.0.0
 * M5 to the current version.
 * 
 * @author lredor
 */
public class MigrationFromSirius1_0_0_M5Test extends AbstractMigrationFromTest {

    private static final Version SIRIUS_1_0_0_M5_VERSION = new Version("8.0.0");

    @Override
    protected Version getExpectedVersion() {
        return SIRIUS_1_0_0_M5_VERSION;
    }

    @Override
    protected String getExpectedVersionToString() {
        return "1.0.0 M5";
    }

    @Override
    protected String getFolderName() {
        return "FromSirius1.0.0M5To";
    }

    @Override
    protected void checkVSM(Group group) {
        super.checkVSM(group);
        // Check that the ContainerDropDescription tool has been correctly
        // migrated (is here).
        EList<ToolEntry> toolEntries = ((DiagramDescription) group.getOwnedViewpoints().get(0).getOwnedRepresentations().get(3)).getDefaultLayer().getToolSections().get(0).getOwnedTools();
        assertEquals("The default layer of the diagram description should contain a ContainerDropDescription tool.", 1,
                Iterators.size(Iterators.filter(toolEntries.iterator(), ContainerDropDescription.class)));
    }
}
