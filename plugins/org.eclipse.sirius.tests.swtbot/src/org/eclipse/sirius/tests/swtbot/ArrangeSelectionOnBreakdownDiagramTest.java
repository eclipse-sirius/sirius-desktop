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

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;

/**
 * 
 * @author smonnier
 */
public class ArrangeSelectionOnBreakdownDiagramTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "TC 1975";

    private static final String REPRESENTATION_INSTANCE_NAME_ODT = "TC 1975 ODT";

    private static final String REPRESENTATION_NAME = "Package Hierarchy With Nodes Source";

    private static final String REPRESENTATION_NAME_ODT = "Package Hierarchy With Nodes Source And Ordered Tree Layout";

    private static final String MODEL = "tc1852.ecore";

    private static final String SESSION_FILE = "tc1975.aird";

    private static final String VSM_FILE = "tc1852.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/TreeFolding/";

    private static final String FILE_DIR = "/";

    private static final String[] NODE_SELECTION_TO_ARRANGE = { "P1A1A", "P1A1", "P1A", "P1" };

    private static final String[] NODE_TO_STAY_FIXED = { "P1A1B", "P1A2", "P1A3", "P1B1", "P1B2", "P1B", "P2", "P3" };

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

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
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Test to validate arrange selection stability on breakdown diagram.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeSelectionStability() throws Exception {

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        validateArrangeSelection(true);
    }

    /**
     * Test to validate arrange selection on breakdown diagram with ordered tree
     * layout. As any selection in an ordered tree layout should not be
     * arrangeable, it checks that the "Arrange Selection" Action cannot be
     * called.
     * 
     * @throws Exception
     *             Test error.
     */
    // VP-1126
    public void testArrangeSelectionStability_OrderedTreeLayout() throws Exception {

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_ODT, REPRESENTATION_INSTANCE_NAME_ODT, DDiagram.class);

        validateArrangeSelection(false);
    }

    /**
     * If the selection should be arrangeable, checks that the Arrange Selection
     * Action is correct ; otherwise, ensure that this Action is not enabled.
     * 
     * @param selectionShouldBeArrangeable
     * @throws Exception
     */
    private void validateArrangeSelection(final boolean selectionShouldBeArrangeable) throws Exception {

        HashMap<String, Point> nodeToStayFixedLocation = new HashMap<String, Point>();
        for (int i = 0; i < NODE_TO_STAY_FIXED.length; i++) {
            nodeToStayFixedLocation.put(NODE_TO_STAY_FIXED[i], editor.getLocation(NODE_TO_STAY_FIXED[i], AbstractDiagramNodeEditPart.class));
        }

        // Select node for Arrange Selection
        ArrayList<SWTBotGefEditPart> editPartToSelect = new ArrayList<>();
        for (int i = 0; i < NODE_SELECTION_TO_ARRANGE.length; i++) {
            editPartToSelect.add(editor.getEditPart(NODE_SELECTION_TO_ARRANGE[i], AbstractDiagramNodeEditPart.class));
        }
        editor.select(editPartToSelect);

        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();
        // VP-1126 : we first check if the Arrange Selection action is enabled
        final SWTBotToolbarDropDownButton arrangeSelectionDropDownButton = bot.toolbarDropDownButtonWithTooltip("Arrange Selection");

        // FIXME : we simply should call
        // arrangeSelectionDropDownButton.isEnable(), but
        // this always return true even if the button is disable.
        // We access directly to the widget to fix this issue, but we should use
        // the standard way as soon as SWTBot will allow it
        arrangeSelectionDropDownButton.display.asyncExec(new Runnable() {
            public void run() {
                ToolItem widget = arrangeSelectionDropDownButton.widget;
                boolean widgetIsEnabled = !selectionShouldBeArrangeable;
                Object data = widget.getData();
                if (data instanceof ActionMenuManager) {
                    IAction arrangeSelectionAction = ((ActionMenuManager) data).getDefaultAction();
                    widgetIsEnabled = arrangeSelectionAction.isEnabled();
                }
                assertEquals(selectionShouldBeArrangeable, widgetIsEnabled);
            }
        });

        // If the Arrange Selection action is enabled, we check that it was
        // successful
        if (selectionShouldBeArrangeable) {
            // Arrange Selection
            bot.toolbarDropDownButtonWithTooltip("Arrange Selection").click();

            // Assert that nodes to stay fixed have not been moved
            for (int i = 0; i < NODE_TO_STAY_FIXED.length; i++) {
                Point expectedLocation = nodeToStayFixedLocation.get(NODE_TO_STAY_FIXED[i]);
                Point location = editor.getLocation(NODE_TO_STAY_FIXED[i], AbstractDiagramNodeEditPart.class);
                assertEquals("The x position of " + NODE_TO_STAY_FIXED[i] + " is not as expected", expectedLocation.x, location.x);
                assertEquals("The y position of " + NODE_TO_STAY_FIXED[i] + " is not as expected", expectedLocation.y, location.y);
            }

            // Assert that selected nodes does not overlap other node
            for (int i = 0; i < NODE_SELECTION_TO_ARRANGE.length; i++) {
                Point location = editor.getLocation(NODE_SELECTION_TO_ARRANGE[i], AbstractDiagramNodeEditPart.class);
                for (int j = 0; j < NODE_TO_STAY_FIXED.length; j++) {
                    Point otherNodelocation = editor.getLocation(NODE_TO_STAY_FIXED[j], AbstractDiagramNodeEditPart.class);
                    assertFalse(NODE_SELECTION_TO_ARRANGE[i] + " should not overlap " + NODE_TO_STAY_FIXED[j], location.x == otherNodelocation.x && location.y == otherNodelocation.y);
                }
                for (int j = 0; j < NODE_SELECTION_TO_ARRANGE.length; j++) {
                    if (i != j) {
                        Point otherNodelocation = editor.getLocation(NODE_SELECTION_TO_ARRANGE[j], AbstractDiagramNodeEditPart.class);
                        assertFalse(NODE_SELECTION_TO_ARRANGE[i] + " should not overlap " + NODE_SELECTION_TO_ARRANGE[j], location.x == otherNodelocation.x && location.y == otherNodelocation.y);
                    }
                }
            }
        }
    }
}
