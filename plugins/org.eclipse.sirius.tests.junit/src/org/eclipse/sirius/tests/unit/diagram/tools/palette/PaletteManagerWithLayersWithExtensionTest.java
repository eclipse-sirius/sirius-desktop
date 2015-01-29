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
package org.eclipse.sirius.tests.unit.diagram.tools.palette;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.PaletteManagerImpl;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Test class for {@link PaletteManagerImpl}.
 * 
 * Tests with layers.
 * 
 * @author nlepine
 */
public class PaletteManagerWithLayersWithExtensionTest extends AbstractPaletteManagerTest {

    private static final String ROOT_EXTENSION = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "extension/"; //$NON-NLS-1$ $NON-NLS-2$

    static final String MODEL_PATH = ROOT_EXTENSION + "toolSection.ecore"; //$NON-NLS-1$

    private static final String VSM_PATH = ROOT_EXTENSION + "toolSection.odesign"; //$NON-NLS-1$

    private static final String SESSION_PATH = ROOT_EXTENSION + "toolSection.aird";

    private static final String MODELER_EXTENSION_PATH = ROOT_EXTENSION + "toolSection_extension.odesign"; //$NON-NLS-1$

    private static final String ANOTHER_VSM_PATH = ROOT_EXTENSION + "another_vsm.odesign"; //$NON-NLS-1$

    private static final String REPRESENTATION_DESC_NAME = "toolSectionLayers"; //$NON-NLS-1$

    private static final String ANOTHER_VIEWPOINT_NAME = "zzzLastSirius"; //$NON-NLS-1$

    private static final String TOOL_SECTION_EXTENSION_VIEWPOINT_NAME = "toolSectionExtension"; //$NON-NLS-1$

    private static final SortedSet<Entry> EXPECTED_ENTRIES_STD_PALETTE = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "ToolL2-A-1")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L3_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "PBSWDL3-A-2", "ToolL2-A-1", "ToolL3-A-1")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L2_HIDDEN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L2_HIDDEN_L3_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "PBSWDL3-A-2", "ToolL3-A-1")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L2_L4_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "ToolL4-A-1", "ToolL2-A-1")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L2_L4_L5_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "ToolL4-A-1", "ToolL2-A-1", "PBSWDL5-A-2", "ToolL5-A-1")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L2_L4_L5_L6_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "ToolL4-A-1", "ToolL2-A-1", "PBSWDL5-A-2", "ToolL5-A-1"),
            //
            createNewEntry("L6Section", "ToolL6")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L2_L5_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "ToolL2-A-1", "PBSWDL5-A-2", "ToolL5-A-1")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L4_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "ToolL4-A-1")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L5_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "PBSWDL5-A-2", "ToolL5-A-1", "ToolL4-A-1")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L6_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("L6Section", "ToolL6")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L2_L3_L4_L5_L6_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "ToolL4-A-1", "ToolL2-A-1", "PBSWDL5-A-2", "ToolL5-A-1", "PBSWDL3-A-2", "ToolL3-A-1"),
            //
            createNewEntry("L6Section", "ToolL6")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L3_L5_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "PBSWDL5-A-2", "ToolL5-A-1", "PBSWDL3-A-2", "ToolL3-A-1")
    //
            ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L3_L4_SHOWN = Sets.newTreeSet(Arrays.asList(
    //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "ToolL4-A-1", "PBSWDL3-A-2", "ToolL3-A-1")
    //
            ));

    private Layer layerL3;

    private Layer layerL2;

    private Layer layerL4;

    private Layer layerL5;

    private Layer layerL6;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        final List<String> modelerPathes = new ArrayList<String>();
        modelerPathes.add(VSM_PATH);
        modelerPathes.add(MODELER_EXTENSION_PATH);
        modelerPathes.add(ANOTHER_VSM_PATH);
        genericSetUp(Collections.singleton(MODEL_PATH), modelerPathes, SESSION_PATH);
        initViewpoint(TOOL_SECTION_EXTENSION_VIEWPOINT_NAME);
        initViewpoint(ANOTHER_VIEWPOINT_NAME);

        Collection<DRepresentation> representations = getRepresentations(getRepresentationDescriptionName());
        for (DRepresentation repr : representations) {
            if (repr.getName().equals(getRepresentationDescriptionInstanceName())) {
                dDiagram = (DDiagram) repr;
                break;
            }
        }

        diagram = (Diagram) Iterables.get(session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, dDiagram), 0);

        // Layer L3 to show
        layerL3 = getLayer(dDiagram, "L3 disabled");

        // Layer L2 to hide
        layerL2 = getLayer(dDiagram, "L2 enabled");

        // Layer L4 to show
        layerL4 = getLayer(dDiagram, "L4 enabled");

        // Layer L5 to show
        layerL5 = getLayer(dDiagram, "L5 disabled");

        // Layer L6 to show
        layerL6 = getLayer(dDiagram, "L6");

        deactivateLayer(dDiagram, "L4 enabled");
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
        return "new toolSectionLayers";
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCreatePalette() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        doContentPaletteTest(EXPECTED_ENTRIES_STD_PALETTE);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testShowLayer() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.showLayer(layerL3);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L3_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideLayer() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.hideLayer(layerL2);
        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L2_HIDDEN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowLayers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.showLayer(layerL3);
        paletteManager.hideLayer(layerL2);
        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L2_HIDDEN_L3_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowL2L4Layers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.showLayer(layerL4);
        paletteManager.showLayer(layerL2);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L2_L4_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowL2L4L5Layers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.showLayer(layerL4);
        paletteManager.showLayer(layerL2);
        paletteManager.showLayer(layerL5);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L2_L4_L5_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowL2L4L5L6Layers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.showLayer(layerL4);
        paletteManager.showLayer(layerL2);
        paletteManager.showLayer(layerL5);
        paletteManager.showLayer(layerL6);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L2_L4_L5_L6_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowL2L5Layers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.showLayer(layerL2);
        paletteManager.showLayer(layerL5);
        paletteManager.hideLayer(layerL4);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L2_L5_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowL4Layers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.hideLayer(layerL2);
        paletteManager.showLayer(layerL4);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L4_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowL5Layers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.hideLayer(layerL2);
        paletteManager.showLayer(layerL4);
        paletteManager.showLayer(layerL5);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L5_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowL2L3L4L5L6Layers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.showLayer(layerL2);
        paletteManager.showLayer(layerL4);
        paletteManager.showLayer(layerL5);
        paletteManager.showLayer(layerL3);
        paletteManager.showLayer(layerL6);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L2_L3_L4_L5_L6_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowL3L5Layers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.hideLayer(layerL2);
        paletteManager.hideLayer(layerL4);
        paletteManager.showLayer(layerL5);
        paletteManager.showLayer(layerL3);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L3_L5_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowL3L4Layers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.hideLayer(layerL2);
        paletteManager.showLayer(layerL4);
        paletteManager.showLayer(layerL3);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L3_L4_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowL6Layers() throws Exception {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(diagram);
        paletteManager.hideLayer(layerL2);
        paletteManager.hideLayer(layerL4);
        paletteManager.showLayer(layerL6);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L6_SHOWN);
    }
}
