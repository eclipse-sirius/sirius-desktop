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
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;

/**
 * Tests reorder tool
 * 
 * @author smonnier
 */
public class SequenceReorderTest extends AbstractDefaultModelSequenceTests {

    private static final String REPRESENTATION_INSTANCE_NAME = "Sequence Diagram on Sample";

    private static final String MODEL = "2256.interactions";

    private static final String SESSION_FILE = "2256.aird";

    private static final String DATA_UNIT_DIR = "data/unit/sequence/reorder/2256/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, getTypesSemanticModel());
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        editor = openDiagram(localSession.getOpenedSession(), getRepresentationId(), REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        editor.mainEditPart().part().getViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, Boolean.FALSE);
        editor.mainEditPart().part().getViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, Boolean.FALSE);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Reorder_Execution() throws Exception {
        // TODO FIXME the same scenario with simple message will not work when
        // reconnecting
        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        Rectangle e2 = getExecutionScreenBounds(LIFELINE_B, 1).getCopy();
        Rectangle e3 = getExecutionScreenBounds(LIFELINE_B, 2).getCopy();
        Rectangle e4 = getExecutionScreenBounds(LIFELINE_C, 0).getCopy();

        // Validates the position of the execution
        int startY = getScreenPosition(LIFELINE_B).y + getScreenSize(LIFELINE_B).height + LayoutConstants.TIME_START_OFFSET;
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", startY, e1.y);

        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", e1.y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, e2.y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected vertical position", e2.y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, e3.y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", e3.bottom() + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, e4.y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT, e4.height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected height", LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT, e3.height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected height", e3.height + e4.height + 3 * LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, e2.height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", e2.height + 2 * LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, e1.height);

        // Validate the positions of the messages
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.bottom(), getReturnSyncCallScreenPosition(LIFELINE_B, 0));
        assertEquals("The message named " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e2.y, getSequenceMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL));
        assertEquals("The return message linked to " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e2.bottom(), getReturnSyncCallScreenPosition(LIFELINE_B, 1));
        assertEquals("The message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", e3.y,
                getSequenceMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        assertEquals("The return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", e3.bottom(), getReturnSyncCallScreenPosition(LIFELINE_B, 2));
        assertEquals("The message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", e4.y,
                getSequenceMessageVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
        assertEquals("The return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", e4.bottom(),
                getReturnSyncCallScreenPosition(LIFELINE_C, 0));

        // Drag the second execution under the first one (Reconnect)
        editor.drag(e2.getLocation(), new Point(e1.x, e1.bottom() + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP));

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", e1.y, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected horizontal position", e1.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position",
                getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP,
                getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected horizontal position", e1.x, getExecutionScreenPosition(LIFELINE_B, 1).x);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y
                + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, getExecutionScreenPosition(LIFELINE_B, 2).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected horizontal position", e2.x, getExecutionScreenPosition(LIFELINE_B, 2).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position",
                getExecutionScreenPosition(LIFELINE_B, 2).y + getExecutionScreenDimension(LIFELINE_B, 2).height + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP,
                getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected horizontal position", e4.x, getExecutionScreenPosition(LIFELINE_C, 0).x);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", getExecutionScreenDimension(LIFELINE_B, 1).height
                + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 2, getExecutionScreenDimension(LIFELINE_B, 0).height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected height", getExecutionScreenDimension(LIFELINE_B, 2).height
                + getExecutionScreenDimension(LIFELINE_C, 0).height + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 3, getExecutionScreenDimension(LIFELINE_B, 1).height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected height", LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT,
                getExecutionScreenDimension(LIFELINE_B, 2).height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT,
                getExecutionScreenDimension(LIFELINE_C, 0).height);

        // Validate the positions of the messages
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y,
                getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y
                + getExecutionScreenDimension(LIFELINE_B, 0).height, getReturnSyncCallScreenPosition(LIFELINE_B, 0));
        assertEquals("The message named " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y,
                getSequenceMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL));
        assertEquals("The return message linked to " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y
                + getExecutionScreenDimension(LIFELINE_B, 1).height, getReturnSyncCallScreenPosition(LIFELINE_B, 1));
        assertEquals("The message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 2).y,
                getSequenceMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        assertEquals("The return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 2).y
                + getExecutionScreenDimension(LIFELINE_B, 2).height, getReturnSyncCallScreenPosition(LIFELINE_B, 2));

        assertEquals("The message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 0).y,
                getSequenceMessageVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
        assertEquals("The return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 0).y
                + getExecutionScreenDimension(LIFELINE_C, 0).height, getReturnSyncCallScreenPosition(LIFELINE_C, 0));

        // Drag the second execution back on the first execution
        editor.drag(getExecutionScreenPosition(LIFELINE_B, 1), new Point(e1.x, e1.y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP));

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", startY, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y
                + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y
                + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, getExecutionScreenPosition(LIFELINE_B, 2).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position",
                getExecutionScreenPosition(LIFELINE_B, 2).y + getExecutionScreenDimension(LIFELINE_B, 2).height + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP,
                getExecutionScreenPosition(LIFELINE_C, 0).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", getExecutionScreenDimension(LIFELINE_B, 1).height
                + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 2, getExecutionScreenDimension(LIFELINE_B, 0).height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected height", getExecutionScreenDimension(LIFELINE_B, 2).height
                + getExecutionScreenDimension(LIFELINE_C, 0).height + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 3, getExecutionScreenDimension(LIFELINE_B, 1).height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected height", LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT,
                getExecutionScreenDimension(LIFELINE_B, 2).height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT,
                getExecutionScreenDimension(LIFELINE_C, 0).height);

        // Validate the positions of the messages
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y,
                getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y
                + getExecutionScreenDimension(LIFELINE_B, 0).height, getReturnSyncCallScreenPosition(LIFELINE_B, 0));
        assertEquals("The message named " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y,
                getSequenceMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL));
        assertEquals("The return message linked to " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y
                + getExecutionScreenDimension(LIFELINE_B, 1).height, getReturnSyncCallScreenPosition(LIFELINE_B, 1));
        assertEquals("The message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 2).y,
                getSequenceMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        assertEquals("The return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 2).y
                + getExecutionScreenDimension(LIFELINE_B, 2).height, getReturnSyncCallScreenPosition(LIFELINE_B, 2));

        assertEquals("The message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 0).y,
                getSequenceMessageVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
        assertEquals("The return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 0).y
                + getExecutionScreenDimension(LIFELINE_C, 0).height, getReturnSyncCallScreenPosition(LIFELINE_C, 0));

        // Arrange All to validate the ordering is stable
        arrangeAll();

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", startY, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y
                + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y
                + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, getExecutionScreenPosition(LIFELINE_B, 2).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position",
                getExecutionScreenPosition(LIFELINE_B, 2).y + getExecutionScreenDimension(LIFELINE_B, 2).height + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP,
                getExecutionScreenPosition(LIFELINE_C, 0).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", getExecutionScreenDimension(LIFELINE_B, 1).height
                + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 2, getExecutionScreenDimension(LIFELINE_B, 0).height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected height", getExecutionScreenDimension(LIFELINE_B, 2).height
                + getExecutionScreenDimension(LIFELINE_C, 0).height + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 3, getExecutionScreenDimension(LIFELINE_B, 1).height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected height", LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT,
                getExecutionScreenDimension(LIFELINE_B, 2).height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT,
                getExecutionScreenDimension(LIFELINE_C, 0).height);

        // Validate the positions of the messages
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y,
                getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y
                + getExecutionScreenDimension(LIFELINE_B, 0).height, getReturnSyncCallScreenPosition(LIFELINE_B, 0));
        assertEquals("The message named " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y,
                getSequenceMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL));
        assertEquals("The return message linked to " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y
                + getExecutionScreenDimension(LIFELINE_B, 1).height, getReturnSyncCallScreenPosition(LIFELINE_B, 1));
        assertEquals("The message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 2).y,
                getSequenceMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        assertEquals("The return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 2).y
                + getExecutionScreenDimension(LIFELINE_B, 2).height, getReturnSyncCallScreenPosition(LIFELINE_B, 2));

        assertEquals("The message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 0).y,
                getSequenceMessageVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
        assertEquals("The return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 0).y
                + getExecutionScreenDimension(LIFELINE_C, 0).height, getReturnSyncCallScreenPosition(LIFELINE_C, 0));
    }
}
