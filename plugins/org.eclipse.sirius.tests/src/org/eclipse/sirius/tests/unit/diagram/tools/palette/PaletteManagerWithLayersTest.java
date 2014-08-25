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

import java.util.Arrays;
import java.util.SortedSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.PaletteManagerImpl;

import com.google.common.collect.Sets;

/**
 * Test class for {@link PaletteManagerImpl}.
 * 
 * Tests with layers.
 * 
 * @author dlecan
 */
public class PaletteManagerWithLayersTest extends AbstractPaletteManagerSectionTest {

    static final String REPRESENTATION_DESC_NAME = "toolSectionLayers";

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

    private Layer layerToShowL3;

    private Layer layerToHideL2;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Layer L3 to show
        layerToShowL3 = getLayer("L3");

        // Layer L2 to hide
        layerToHideL2 = getLayer("L2");
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
        paletteManager.showLayer(layerToShowL3);

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
        paletteManager.hideLayer(layerToHideL2);

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
        paletteManager.showLayer(layerToShowL3);
        paletteManager.hideLayer(layerToHideL2);

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L2_HIDDEN_L3_SHOWN);
    }

    private Layer getLayer(String partialId) {
        Layer result = null;
        EList<Layer> allLayers = dDiagram.getDescription().getAllLayers();
        for (Layer layer : allLayers) {
            if (layer.getName().contains(partialId)) {
                result = layer;
                break;
            }
        }
        return result;
    }
}
