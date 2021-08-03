/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.IAction;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.LayerModelHelper;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.internal.management.UpdateToolRecordingCommand;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.LayersActivationAction;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.PaletteManagerImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.PaletteToolChangeListener;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.layers.LayersActivationAdapter;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Test class for {@link PaletteManagerImpl}.
 * 
 * Tests with layers.
 * 
 * @author dlecan
 */
public class PaletteManagerWithLayersTest extends AbstractPaletteManagerSectionTest {

    static final String REPRESENTATION_DESC_NAME = "toolSectionLayers";

    private static final SortedSet<Entry> EXPECTED_ENTRIES_STD_PALETTE = new TreeSet<Entry>(Arrays.asList(
            //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("L5", "ToolL5"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "ToolL2-A-1")
    //
    ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L3_SHOWN = new TreeSet<Entry>(Arrays.asList(
            //
            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("L5", "ToolL5"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "PBSWDL3-A-2", "ToolL2-A-1", "ToolL3-A-1")
    //
    ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L4_SHOWN = new TreeSet<Entry>(
            Arrays.asList(createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2", "Tool9-1"),
                    //
                    createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
                    //
                    createNewEntry("L1NotEmptySection5", "NCD5-1"),
                    //
                    createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
                    //
                    createNewEntry("L4", "ToolL4"),
                    //
                    createNewEntry("L5", "ToolL5"),
                    //
                    createNewEntry("SectionSharedWithOtherLayersA", "ToolL2-A-1")));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L2_HIDDEN = new TreeSet<Entry>(Arrays.asList(
            //

            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("L5", "ToolL5")
    //
    ));

    private static final SortedSet<Entry> EXPECTED_ENTRIES_LAYER_L2_HIDDEN_L3_SHOWN = new TreeSet<Entry>(Arrays.asList(
            //

            createNewEntry("L1NotEmptySection1", "Tool1-1", "Tool1-2-1", "Tool1-4-1", "ECD1-2-2"),
            //
            createNewEntry("L1NotEmptySection4", "Tool1-2-1"),
            //
            createNewEntry("L1NotEmptySection5", "NCD5-1"),
            //
            createNewEntry("L1EmptySectionButWithReuseTool7", "Tool1-2-1"),
            //
            createNewEntry("L5", "ToolL5"),
            //
            createNewEntry("SectionSharedWithOtherLayersA", "PBSWDL3-A-2", "ToolL3-A-1")
    //
    ));

    private Layer layerL4Transient;

    private IEditorPart editorPart;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Open the editor before testing to be sure to have mandatory transient
        // layer enabled (done in
        // org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl.activateTransientLayers())
        editorPart = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue("Impossible to open editor part, wrong type: " + editorPart.getClass(), editorPart instanceof DiagramDocumentEditor);

        // Layer L4 (transient layer)
        layerL4Transient = getLayer("L4");
    }

    @Override
    protected void tearDown() throws Exception {
        if (editorPart != null) {
            try {
                DialectUIManager.INSTANCE.closeEditor(editorPart, false);
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                // Nothing
            }
        }
        super.tearDown();
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
        paletteManager.update(dDiagram);
        doContentPaletteTest(EXPECTED_ENTRIES_STD_PALETTE);
    }

    /**
     * Check that layer that is activated and not transient in previous Sirius version is now not activated at the
     * diagram opening.
     */
    public void testCreatePalettWithTransientLayerActivatedInPreviousSiriusVersion() {
        // Close the previous editor to open a new one
        if (editorPart != null) {
            DialectUIManager.INSTANCE.closeEditor(editorPart, false);
            TestsUtil.synchronizationWithUIThread();
        }
        diagram = getDiagram(getRepresentationDescriptionName(), getRepresentationDescriptionInstanceName() + "2");
        editorPart = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue("Impossible to open editor part, wrong type: " + editorPart.getClass(), editorPart instanceof DiagramDocumentEditor);
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        paletteManager.update(dDiagram);
        // Even if L4 was previously selected in previous Sirius version, it
        // must not be visible now.
        doContentPaletteTest(EXPECTED_ENTRIES_STD_PALETTE);
    }

    private PaletteManager initPaletteManager() {
        PaletteManager paletteManager = new PaletteManagerImpl(editDomain);
        DiagramPlugin.getDefault().getToolManagement(diagram).addToolChangeListener(new PaletteToolChangeListener(paletteManager, diagram));
        UpdateToolRecordingCommand updateToolRecordingCommand = new UpdateToolRecordingCommand(session.getTransactionalEditingDomain(), dDiagram, true);
        session.getTransactionalEditingDomain().getCommandStack().execute(updateToolRecordingCommand);
        paletteManager.update(dDiagram);
        return paletteManager;
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testShowLayer() throws Exception {
        initPaletteManager();
        activateLayer(dDiagram, "L3 disabled");
        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L3_SHOWN);
        deactivateLayer(dDiagram, "L3 disabled");

        // Check transient layer activation directly with PaletteManager
        activateLayer(dDiagram, "L4");
        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L4_SHOWN);
        deactivateLayer(dDiagram, "L4");

        // Check transient layer activation with action (more like end-user)
        // Add an adapter like it is done when menu is shown
        LayersActivationAdapter adapter = new LayersActivationAdapter();
        dDiagram.eAdapters().add(adapter);
        // Launch the action like with menu
        IAction action = new LayersActivationAction("L4", IAction.AS_CHECK_BOX, dDiagram, layerL4Transient);
        action.run();
        TestsUtil.synchronizationWithUIThread();
        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L4_SHOWN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideLayer() throws Exception {
        initPaletteManager();
        deactivateLayer(dDiagram, "L2 enabled");

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L2_HIDDEN);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowLayers() throws Exception {
        initPaletteManager();
        activateLayer(dDiagram, "L3 disabled");
        deactivateLayer(dDiagram, "L2 enabled");

        doContentPaletteTest(EXPECTED_ENTRIES_LAYER_L2_HIDDEN_L3_SHOWN);
    }

    private Layer getLayer(String partialId) {
        Layer result = null;
        EList<Layer> allLayers = LayerModelHelper.getAllLayers(dDiagram.getDescription());
        for (Layer layer : allLayers) {
            if (layer.getName().contains(partialId)) {
                result = layer;
                break;
            }
        }
        return result;
    }
}
