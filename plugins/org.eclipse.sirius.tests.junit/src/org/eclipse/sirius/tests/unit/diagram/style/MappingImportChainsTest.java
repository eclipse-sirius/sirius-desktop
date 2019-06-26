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
package org.eclipse.sirius.tests.unit.diagram.style;

import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.graphics.Color;

/**
 * Tests conditional styles look up in mapping import chains.
 * 
 * @author mporhel
 */
public class MappingImportChainsTest extends SiriusDiagramTestCase {
    private static final String DATA_PATH = "/data/unit/style/vp-3790/";

    private static final String SEMANTIC_FILE_NAME = "VP-3790.ecore";

    private static final String MODELER_FILE_NAME = "VP-3790.odesign";

    private static final String SESSION_FILE_NAME = "VP-3790.aird";

    private static final String VP_3790 = "VP-3790";

    private static final String BLACK_BASE_STYLE = "Black - Base style";

    private static final String ORANGE_LEVEL_1_STYLE = "Orange - Level 1 style";

    private static final String REPRESENTATION_DESC_NAME = "VP-3790";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_PATH, SEMANTIC_FILE_NAME, MODELER_FILE_NAME, SESSION_FILE_NAME);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_FILE_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_FILE_NAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_FILE_NAME);
    }

    /**
     * Test that the layer activation/deactivation triggers the selection of a
     * style provided by the most specific mapping of a hierarchy, this mapping
     * belongs to an activated layer. The VSM is designed to allow to make a
     * simple check on the color.
     */
    public void testSimpleImportedStyleSelectionOnLayerActivation() {
        DDiagram vp_3790 = getDiagramnFromName(VP_3790);
        DialectUIManager.INSTANCE.openEditor(session, vp_3790, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Deactivate all optional layers.
        deactivateLayer(vp_3790, "Level 1 : red");
        deactivateLayer(vp_3790, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(vp_3790, "gray");

        // Activate layer 1: all styles should become red.
        activateLayer(vp_3790, "Level 1 : red");
        TestsUtil.synchronizationWithUIThread();

        checkColors(vp_3790, "red");

        // Deactivate layer 1: all styles should return to gray.
        deactivateLayer(vp_3790, "Level 1 : red");
        TestsUtil.synchronizationWithUIThread();

        checkColors(vp_3790, "gray");

        // Activate layer 2: all styles should become green.
        activateLayer(vp_3790, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(vp_3790, "green");

        // Deactivate layer 2: all styles should return to gray.
        deactivateLayer(vp_3790, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(vp_3790, "gray");

        // Activate layers 1 and 2: all styles should become green (most
        // specific mapping's style).
        activateLayer(vp_3790, "Level 1 : red");
        activateLayer(vp_3790, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(vp_3790, "green");

        // Deactivate layer 2: all styles should become red.
        deactivateLayer(vp_3790, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(vp_3790, "red");

        // Reactivate layer 2: all styles should become green (most specific
        // mapping's style).
        activateLayer(vp_3790, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(vp_3790, "green");

        // Deactivate layer 1: all styles should stay green (most specific
        // mapping's style).
        deactivateLayer(vp_3790, "Level 1 : red");
        TestsUtil.synchronizationWithUIThread();

        checkColors(vp_3790, "green");

        // Deactivate layer 2: all styles should become gray.
        deactivateLayer(vp_3790, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(vp_3790, "gray");
    }

    private void checkColors(DDiagram diagram, String expectedColorName) {
        Color colorFromName = VisualBindingManager.getDefault().getColorFromName(expectedColorName);

        for (DDiagramElement dde : diagram.getOwnedDiagramElements()) {
            Color colorFromRGBValues = null;
            if (dde instanceof DNodeContainer) {
                FlatContainerStyle ownedStyle = (FlatContainerStyle) ((DNodeContainer) dde).getOwnedStyle();
                colorFromRGBValues = VisualBindingManager.getDefault().getColorFromRGBValues(ownedStyle.getForegroundColor());
            } else if (dde instanceof DNode) {
                Square ownedStyle = (Square) ((DNode) dde).getOwnedStyle();
                colorFromRGBValues = VisualBindingManager.getDefault().getColorFromRGBValues(ownedStyle.getColor());
            } else if (dde instanceof DEdge) {
                EdgeStyle ownedStyle = ((DEdge) dde).getOwnedStyle();
                colorFromRGBValues = VisualBindingManager.getDefault().getColorFromRGBValues(ownedStyle.getStrokeColor());
            }
            assertEquals(colorFromName, colorFromRGBValues);
        }
    }

    private DDiagram getDiagramnFromName(String representationName) {
        for (DRepresentationDescriptor rep : getRepresentationDescriptors(REPRESENTATION_DESC_NAME).stream().filter(repDesc -> repDesc.getRepresentation() instanceof DDiagram)
                .collect(Collectors.toList())) {
            if (representationName.equals(rep.getName())) {
                return (DDiagram) rep.getRepresentation();
            }
        }
        return null;
    }

    /**
     * Test the conditional style look up is recursive. On the
     * "Black - Base style" diagram, the model and the odesign are designed to
     * get a Black style from each root mapping (Container, Node, Edge).
     * 
     * The layer still get the red and green names.
     */
    public void testConditionalStyleRecursiveLookUpinMAppingImportChains() {
        DDiagram blackDiagram = getDiagramnFromName(BLACK_BASE_STYLE);
        DialectUIManager.INSTANCE.openEditor(session, blackDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        checkColors(blackDiagram, "black");

        // Activate the first layer : check the look up in a one level
        // hierarchy.
        activateLayer(blackDiagram, "Level 1 : red");
        TestsUtil.synchronizationWithUIThread();

        checkColors(blackDiagram, "black");

        // Deactivate the first layer : check the recomputation of the mapping
        // table.
        deactivateLayer(blackDiagram, "Level 1 : red");
        TestsUtil.synchronizationWithUIThread();

        checkColors(blackDiagram, "black");

        // Activate the second layer : check the look up in a two level
        // hierarchy, with one unactive level.
        activateLayer(blackDiagram, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(blackDiagram, "black");

        // Deactivate the second layer : check the recomputation of the mapping
        // table.
        deactivateLayer(blackDiagram, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(blackDiagram, "black");

        // Activate the two layers : check the look up in a two level hierarchy.
        activateLayer(blackDiagram, "Level 1 : red");
        activateLayer(blackDiagram, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(blackDiagram, "black");

        // Deactivate all optional layers : check the recomputation of the
        // mapping table.
        deactivateLayer(blackDiagram, "Level 1 : red");
        deactivateLayer(blackDiagram, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(blackDiagram, "black");
    }

    /**
     * Test the conditional style look up is consistent for Node, Container and
     * Edge regarding the provided styles which are not is activated layers.
     * 
     * On the "Orange - Level 1 style" diagram, the model and the odesign are
     * designed to get an Orange from the level 1 mappings for every activated
     * optional layers.
     * 
     * The layer still get the red and green names.
     */
    public void testConditionalSSimpleImportedStyleSelectionOnLayerActivation() {
        DDiagram orangeDiagram = getDiagramnFromName(ORANGE_LEVEL_1_STYLE);
        DialectUIManager.INSTANCE.openEditor(session, orangeDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        checkColors(orangeDiagram, "gray");

        // Activate the layer 1: test that the layer which provide the orange
        // style make all styles orange when activated.
        activateLayer(orangeDiagram, "Level 1 : red");
        TestsUtil.synchronizationWithUIThread();

        checkColors(orangeDiagram, "orange");

        // Deactivate the layer 1 to return in the initial state.
        deactivateLayer(orangeDiagram, "Level 1 : red");
        TestsUtil.synchronizationWithUIThread();

        checkColors(orangeDiagram, "gray");

        // Activate the layer 2: test that a layer which does not provide the
        // orange style, but mapping which inherits from this mapping make all
        // styles orange when activated and when the providing mapping is not in
        // activated layers.
        activateLayer(orangeDiagram, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(orangeDiagram, "orange");

        // Activate the layer 1: all layers are active : test that the styles
        // are stil orange.
        activateLayer(orangeDiagram, "Level 1 : red");
        TestsUtil.synchronizationWithUIThread();

        checkColors(orangeDiagram, "orange");

        // Deactivate all layers: check that the diagram is in its initial color
        // state.
        deactivateLayer(orangeDiagram, "Level 1 : red");
        deactivateLayer(orangeDiagram, "Level 2 : green");
        TestsUtil.synchronizationWithUIThread();

        checkColors(orangeDiagram, "gray");
    }
}
