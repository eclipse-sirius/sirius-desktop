/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckDiagramSelected;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test class for label selection. This test checks that non visible edge label
 * cannot be selected and cannot intercept the selection of the underlying part
 * to select the edge instead (See VP-3826).
 * 
 * @author mporhel
 */
public class NonVisibleLabelSelectionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "LabelTests";

    private static final String REPRESENTATION_NAME = "LabelTests";

    private static final String MODEL = "vp-3826.ecore";

    private static final String SESSION_FILE = "vp-3826.aird";

    private static final String VSM_FILE = "vp-3826.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/labelSelection/";

    private static final String FILE_DIR = "/";

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckLabelSelection() throws Exception {
        // The default and the optional layer should be selected.
        assertEquals(2, ((DDiagramEditPart) editor.mainEditPart().part()).resolveDDiagram().get().getActivatedLayers().size());

        // Required edit parts
        final SWTBotGefEditPart beginLabelEditPart = editor.getEditPart("begin");
        final SWTBotGefEditPart centerLabelEditPart = editor.getEditPart("center");
        final SWTBotGefEditPart endLabelEditPart = editor.getEditPart("end");
        final SWTBotGefEditPart edgeEditPart = beginLabelEditPart.parent();
        final SWTBotGefEditPart containerEditPart = editor.getEditPart("container").parent();

        // Corresponding Draw2D bounds
        final Rectangle beginLabelBounds = editor.getBounds(beginLabelEditPart);
        final Point beginLabelCenter = beginLabelBounds.getCenter();
        final Point centerLabelCenter = editor.getBounds(centerLabelEditPart).getCenter();
        final Point endLabelCenter = editor.getBounds(endLabelEditPart).getCenter();

        checkTestData(beginLabelEditPart, centerLabelEditPart, endLabelEditPart, beginLabelBounds);

        // Click in the center of each label to select the edge.
        checkSelection(beginLabelEditPart, beginLabelCenter);
        checkSelection(centerLabelEditPart, centerLabelCenter);
        checkSelection(endLabelEditPart, endLabelCenter);

        // Hide label and select diagram and check that the edge is not selected
        // when the user clicks on the non visible labels locations
        hideEdgeLabelsAndSelectDiagram(edgeEditPart);
        checkSelection(containerEditPart, beginLabelCenter, centerLabelCenter, endLabelCenter);

        // undo, the edge labels are visible and can be selected.
        undo(this.localSession.getOpenedSession());
        checkSelection(beginLabelEditPart, beginLabelCenter);
        checkSelection(centerLabelEditPart, centerLabelCenter);
        checkSelection(endLabelEditPart, endLabelCenter);

        // Deactivate optional layer and check that the edge is not selected
        // when the user clicks on the non visible labels locations
        changeLayerWithLabelsActivation();
        checkSelection(containerEditPart, beginLabelCenter, endLabelCenter);
        checkSelection(centerLabelEditPart, centerLabelCenter);
    }

    private void changeLayerWithLabelsActivation() {
        ICondition done = new OperationDoneCondition();
        editor.changeLayerActivation("Layer with begin and end labels");
        bot.waitUntil(done);
        SWTBotUtils.waitAllUiEvents();
    }

    private void hideEdgeLabelsAndSelectDiagram(final SWTBotGefEditPart edgeEditPart) {
        ICondition done = new CheckSelectedCondition(editor, edgeEditPart.part());
        edgeEditPart.select();
        bot.waitUntil(done);

        done = new OperationDoneCondition();
        editor.clickContextMenu("Hide label");
        bot.waitUntil(done);

        done = new CheckDiagramSelected(editor);
        editor.select(editor.mainEditPart());
        bot.waitUntil(done);
    }

    private void checkSelection(final SWTBotGefEditPart expectedSelection, final Point... selectionPoints) {
        ICondition done;
        for (Point selectionPoint : selectionPoints) {
            done = new CheckSelectedCondition(editor, expectedSelection.part());
            editor.click(selectionPoint);
            bot.waitUntil(done);
            assertTrue("The part " + expectedSelection + " does not appear in the selection.", editor.selectedEditParts().contains(expectedSelection));

            done = new CheckDiagramSelected(editor);
            editor.select(editor.mainEditPart());
            bot.waitUntil(done);
        }
    }

    private void checkTestData(final SWTBotGefEditPart beginLabelEditPart, final SWTBotGefEditPart centerLabelEditPart, final SWTBotGefEditPart endLabelEditPart, final Rectangle beginLabelBounds) {
        // Corresponding GMF bounds
        final Size beginGmfSize = ((Size) ((Node) beginLabelEditPart.part().getModel()).getLayoutConstraint());
        final Size centerGmfSize = ((Size) ((Node) centerLabelEditPart.part().getModel()).getLayoutConstraint());
        final Size endGmfSize = ((Size) ((Node) endLabelEditPart.part().getModel()).getLayoutConstraint());

        // Check the test data.
        assertEquals("Begin label should have been resized.", beginLabelBounds.height, beginGmfSize.getHeight());
        assertEquals("Begin label should have been resized.", beginLabelBounds.width, beginGmfSize.getWidth());
        assertEquals("Center label should be in autosize (-1,-1).", -1, centerGmfSize.getHeight());
        assertEquals("Center label should be in autosize (-1,-1).", -1, centerGmfSize.getWidth());
        assertEquals("End label should be in autosize (-1,-1).", -1, endGmfSize.getHeight());
        assertEquals("End label should be in autosize (-1,-1).", -1, endGmfSize.getHeight());
    }
}
