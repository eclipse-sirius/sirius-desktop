/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.tools.palette;

import java.util.Arrays;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.management.ToolFilter;
import org.eclipse.sirius.diagram.tools.api.management.ToolManagement;
import org.eclipse.sirius.diagram.tools.internal.management.ToolManagementRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.PaletteManagerImpl;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

import com.google.common.collect.Iterables;

/**
 * Test class for {@link PaletteManagerImpl}.
 * 
 * Tests with filters.
 * 
 * @author mchauvin
 */
public class PaletteManagerWithFiltersTest extends AbstractPaletteManagerTest {

    static final String SEMANTIC_MODEL_FILENAME = "toolSection.ecore";

    static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_MODEL_FILENAME;

    static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "toolFilters.odesign";

    static final String SESSION_MODEL_FILENAME = "toolFilters.aird";

    static final String SESSION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_MODEL_FILENAME;

    static final String REPRESENTATION_DESC_NAME = "toolFiltersLayers";

    static final String REPRESENTATION_NAME = "new toolFiltersLayers";

    private static final SortedSet<Entry> EXPECTED_ENTRIES_STD_PALETTE = new TreeSet<Entry>(Arrays.asList(createNewEntry("never deleted"), createNewEntry("section1", "tool2")));

    private static final SortedSet<Entry> NO_ENTRIES = new TreeSet<Entry>(Arrays.asList(createNewEntry("never deleted")));

    private static final ToolFilter hideAll = new ToolFilter() {
        @Override
        public boolean filter(DDiagram diagram, AbstractToolDescription tool) {
            return true;
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_MODEL_PATH);
        SessionUIManager.INSTANCE.createUISession(session);

        Collection<DRepresentationDescriptor> representationDescriptors = getRepresentationDescriptors(getRepresentationDescriptionName());
        for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {
            if (representationDescriptor.getName().equals(getRepresentationDescriptionInstanceName())) {
                dDiagram = (DDiagram) representationDescriptor.getRepresentation();
                break;
            }
        }

        diagram = (Diagram) Iterables.get(session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, dDiagram), 0);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return REPRESENTATION_DESC_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionInstanceName() {
        return "new toolFiltersLayers";
    }

    public void testCreatePalette() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        updateTools(diagram);
        addLikeGmfANeverDeletedEntry();
        paletteManager.update(dDiagram);
        doContentPaletteTest(EXPECTED_ENTRIES_STD_PALETTE);
    }

    public void testBasicDynamicFilter() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        ToolManagement toolManagement = DiagramPlugin.getPlugin().getToolManagement(diagram);
        addLikeGmfANeverDeletedEntry();
        updateTools(diagram);
        paletteManager.update(dDiagram);
        doContentPaletteTest(EXPECTED_ENTRIES_STD_PALETTE);

        toolManagement.addToolFilter(hideAll);
        updateTools(diagram);
        paletteManager.update(dDiagram);
        doContentPaletteTest(NO_ENTRIES);
        toolManagement.removeToolFilter(hideAll);
        updateTools(diagram);
        paletteManager.update(dDiagram);
        doContentPaletteTest(EXPECTED_ENTRIES_STD_PALETTE);
    }

    public void testBasicDynamicRegistryFilter() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        addLikeGmfANeverDeletedEntry();
        updateTools(diagram);
        paletteManager.update(dDiagram);
        doContentPaletteTest(EXPECTED_ENTRIES_STD_PALETTE);
        ToolManagementRegistry.getInstance().addToolFilter(hideAll);
        updateTools(diagram);
        paletteManager.update(dDiagram);
        doContentPaletteTest(NO_ENTRIES);
        ToolManagementRegistry.getInstance().removeToolFilter(hideAll);
        updateTools(diagram);
        paletteManager.update(dDiagram);
        doContentPaletteTest(EXPECTED_ENTRIES_STD_PALETTE);
    }

    private void addLikeGmfANeverDeletedEntry() {
        final PaletteRoot paletteRoot = editDomain.getPaletteViewer().getPaletteRoot();
        paletteRoot.getChildren().add(new PaletteEntry("never deleted", ""));
    }

}
