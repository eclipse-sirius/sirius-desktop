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
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.VerticalPositionFunction;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.LayoutEditPartConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckMessageEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberExecutionOnLifeline;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckResize;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Test class for syncCall
 * 
 * @author smonnier, edugueperoux
 */
public class SyncCallTest extends AbstractDefaultModelSequenceTests {

    // InstanceRoles
    private SWTBotGefEditPart instanceRoleABot;

    private SWTBotGefEditPart instanceRoleBBot;

    private SWTBotGefEditPart instanceRoleCBot;

    private Rectangle instanceRoleABounds;

    private Rectangle instanceRoleBBounds;

    private Rectangle instanceRoleCBounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);

        maximizeEditor(editor);
        editor.reveal(LIFELINE_A);
        arrangeAll();

        // InstanceRoles
        instanceRoleABot = editor.getEditPart(LIFELINE_A);
        instanceRoleBBot = editor.getEditPart(LIFELINE_B);
        instanceRoleCBot = editor.getEditPart(LIFELINE_C);

        instanceRoleABounds = editor.getBounds(instanceRoleABot);
        instanceRoleBBounds = editor.getBounds(instanceRoleBBot);
        instanceRoleCBounds = editor.getBounds(instanceRoleCBot);

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Resize_Zoom() throws Exception {
        try {
            ZoomLevel zoom50 = ZoomLevel.ZOOM_50;
            editor.zoom(ZoomLevel.ZOOM_50);
            editor.restore();

            // Click on the diagram to unfocus the created element
            editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);
            // Arrange All
            arrangeAll();

            // Calculate the X position of the center of lifelines A and B
            int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
            int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;
            int lifelineCPosition = getScreenPosition(LIFELINE_C).x + getScreenSize(LIFELINE_C).width / 2;

            // Creation of a Sync Call
            editor.activateTool("Sync Call");
            editor.click(lifelineAPosition, 75);
            editor.click(lifelineBPosition, 75);
            editor.activateTool("Sync Call");
            editor.click(lifelineBPosition, 80);
            editor.click(lifelineCPosition, 80);

            // Creation of a Sync Call
            editor.activateTool("Sync Call");
            editor.click(lifelineAPosition, 150);
            editor.click(lifelineBPosition, 150);

            Point firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0);
            Dimension firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0);
            Point secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0);
            Dimension secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0);
            Point thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1);
            Dimension thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 1);

            // Select the first message to be able to move it then
            editor.click(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL));
            editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL), getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL).x - 10,
                    getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL).y - 10);

            validateMessagesOnEdgeOfExecution(zoom50.getAmount());
            validateOrdering(12);

            // Validate execution vertical position
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y - 10,
                    getExecutionScreenPosition(LIFELINE_B, 0).y);
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_C, 0).y);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

            assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 1).y);
            assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

            firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0);
            firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0);
            secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0);
            secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0);
            thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1);
            thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 1);

            // Move down the return message of the second execution of Lifeline
            // B
            editor.click(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 1)));
            editor.drag(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 1)), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 1)).x + 50,
                    getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 1)).y + 50);

            validateMessagesOnEdgeOfExecution(zoom50.getAmount());
            validateOrdering(12);

            // Validate execution vertical position
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 0).y);
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_C, 0).y);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

            assertExecutionHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(0, thirdExecutionScreenPosition.y, 0, thirdExecutionDimension.height + 50), false);

            firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0);
            firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0);
            secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0);
            secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0);
            thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1);
            thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 1);

            // Move down message m5 of the second execution of Lifeline B
            editor.click(getSequenceMessageScreenCenteredPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
            editor.drag(getSequenceMessageScreenCenteredPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B), getSequenceMessageScreenCenteredPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x + 50,
                    getSequenceMessageScreenCenteredPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).y + 50);

            validateMessagesOnEdgeOfExecution(zoom50.getAmount());
            validateOrdering(12);

            // Validate execution vertical position
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 0).y);
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_C, 0).y);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

            assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + 50,
                    getExecutionScreenPosition(LIFELINE_B, 1).y);
            assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

            firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0);
            firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0);
            secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0);
            secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0);
            thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1);
            thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 1);

            // Move down the return message of the first execution of Lifeline B
            editor.click(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)));
            editor.drag(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)).x + 50,
                    getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)).y + 50);

            validateMessagesOnEdgeOfExecution(zoom50.getAmount());
            validateOrdering(12);

            // Validate execution vertical position
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 0).y);
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_C, 0).y);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height
                    + 50, getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height, 1);

            assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 1).y);
            assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

            firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0);
            firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0);
            secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0);
            secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0);
            thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1);
            thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 1);

            // Move down the return message of the first execution of Lifeline C
            int delta = 40;
            editor.click(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)));
            editor.drag(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)).x + delta,
                    getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)).y + delta);

            validateMessagesOnEdgeOfExecution(zoom50.getAmount());
            validateOrdering(12);

            // Validate execution vertical position
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 0).y, 1);
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_C, 0).y, 1);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height
                    + delta, getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height, 1);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height, 1);

            assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 1).y, 1);
            assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height, 1);

            firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0);
            firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0);
            secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0);
            secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0);
            thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1);
            thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 1);

            // Move down message m3 of the first execution of Lifeline C
            editor.click(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
            editor.drag(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C), getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x + 50,
                    getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).y + 50);

            validateMessagesOnEdgeOfExecution(zoom50.getAmount());
            validateOrdering(12);

            // Validate execution vertical position
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 0).y);
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + 50,
                    getExecutionScreenPosition(LIFELINE_C, 0).y);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

            assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 1).y);
            assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

            firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0);
            firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0);
            secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0);
            secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0);
            thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1);
            thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 1);

            // Validation of forbidden move

            // Validate it is not possible to move up message m1 before the
            // begining of the lifeline
            editor.click(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL));
            editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL), getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL).x - 50,
                    getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL).y - 50);

            validateMessagesOnEdgeOfExecution(zoom50.getAmount());
            validateOrdering(12);

            // Validate execution vertical position
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 0).y);
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_C, 0).y);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

            assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 1).y);
            assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

            // Validate it is not possible to move down message m3 after the
            // return message of the same execution
            editor.click(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
            editor.drag(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)).x + 10,
                    getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)).y + 10);

            validateMessagesOnEdgeOfExecution(zoom50.getAmount());
            validateOrdering(12);

            // Validate execution vertical position
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 0).y);
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_C, 0).y);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

            assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 1).y);
            assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

            // Validate it is not possible to move up message m3 before m1
            editor.click(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
            editor.drag(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)).x - 10,
                    getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)).y - 1);

            validateMessagesOnEdgeOfExecution(zoom50.getAmount());
            validateOrdering(12);

            // Validate execution vertical position
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 0).y);
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_C, 0).y);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

            assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 1).y);
            assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

            // Validate it is not possible to move up message m1 after m3
            editor.click(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL));
            editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL), getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x + 10,
                    getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).y + 10);

            validateMessagesOnEdgeOfExecution(zoom50.getAmount());
            validateOrdering(12);

            // Validate execution vertical position
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 0).y);
            assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_C, 0).y);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
            assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

            assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                    getExecutionScreenPosition(LIFELINE_B, 1).y);
            assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                    getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

        } finally {
            // Set zoom to default
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Creation_Deletion() throws Exception {
        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;
        int lifelineCPosition = getScreenPosition(LIFELINE_C).x + getScreenSize(LIFELINE_C).width / 2;

        // Creation of a Sync Call
        int firstMsgsyncCallY = 200;
        editor.activateTool("Sync Call");
        editor.click(lifelineAPosition, firstMsgsyncCallY);
        editor.click(lifelineBPosition, firstMsgsyncCallY);

        Point executionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0);
        getExecutionScreenDimension(LIFELINE_B, 0);

        // Validates the position of the execution and its messages
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstMsgsyncCallY, executionScreenPosition.y);
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, firstMsgsyncCallY);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        // Creation of a Sync Call
        int thirdMsgsyncCallY = executionScreenPosition.y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP / 2;
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, thirdMsgsyncCallY);
        editor.click(lifelineCPosition, thirdMsgsyncCallY);

        // Validates the position of the executions and their messages
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionScreenPosition(LIFELINE_C, 0));
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", thirdMsgsyncCallY, getExecutionScreenPosition(LIFELINE_C, 0).y);

        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, firstMsgsyncCallY);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        // Deletion of FIRST_MESSAGE_SYNC_CALL
        editor.click(getExecutionScreenPosition(LIFELINE_B, 0).x - 50, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        bot.menu("Edit").menu("Delete").click();

        try {
            getSequenceMessageEditPart(FIRST_MESSAGE_SYNC_CALL);
            fail(WidgetNotFoundException.class + " expected");

        } catch (final WidgetNotFoundException e) {
            // Expected, the edit part must not be found
        }

        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionScreenPosition(LIFELINE_C, 0));

        // Deletion of FIRST_MESSAGE_SYNC_CALL
        editor.click(getExecutionScreenPosition(LIFELINE_C, 0).x - 50, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
        bot.menu("Edit").menu("Delete").click();

        try {
            getSequenceMessageEditPart(FIRST_MESSAGE_SYNC_CALL);
            getSequenceMessageEditPart(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C);
            fail(WidgetNotFoundException.class + " expected");

        } catch (final WidgetNotFoundException e) {
            // Expected, the edit part must not be found
        }

        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionScreenPosition(LIFELINE_C, 0));

        // Deletion of the first return message
        editor.click(getExecutionScreenPosition(LIFELINE_B, 0).x - 50, getReturnSyncCallScreenPosition(LIFELINE_B, 0));
        bot.menu("Edit").menu("Delete").click();

        try {
            getSequenceMessageEditPart(FIRST_MESSAGE_SYNC_CALL);
            getSequenceMessageEditPart(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C);
            getReturnSyncCallScreenPosition(LIFELINE_B, 0);
            fail(WidgetNotFoundException.class + " expected");

        } catch (final WidgetNotFoundException e) {
            // Expected, the edit part must not be found
        }

        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionScreenPosition(LIFELINE_C, 0));

        // Deletion of the second return message
        editor.click(getExecutionScreenPosition(LIFELINE_C, 0).x - 50, getReturnSyncCallScreenPosition(LIFELINE_C, 0));
        bot.menu("Edit").menu("Delete").click();

        try {
            getSequenceMessageEditPart(FIRST_MESSAGE_SYNC_CALL);
            getSequenceMessageEditPart(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C);
            getReturnSyncCallScreenPosition(LIFELINE_B, 0);
            getReturnSyncCallScreenPosition(LIFELINE_C, 0);
            fail(WidgetNotFoundException.class + " expected");

        } catch (final WidgetNotFoundException e) {
            // Expected, the edit part must not be found
        }

        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionScreenPosition(LIFELINE_C, 0));

        // Deletion of the executions
        editor.click(getExecutionScreenPosition(LIFELINE_B, 0).x, getExecutionScreenPosition(LIFELINE_B, 0).y);
        bot.menu("Edit").menu("Delete").click();

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);
        manualRefresh();

        assertExecutionDoesNotExist(LIFELINE_B, 0);

        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionEditPart(LIFELINE_C, 0));

        // Deletion of the executions
        editor.click(getExecutionScreenPosition(LIFELINE_C, 0).x, getExecutionScreenPosition(LIFELINE_C, 0).y);
        bot.menu("Edit").menu("Delete").click();

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);
        manualRefresh();

        assertNull(getExecutionEditPart(LIFELINE_C, 0));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Ordering() throws Exception {

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;

        // Creation of an syncCall
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, 200);
        editor.click(lifelineAPosition, 200);

        Point executionScreenPosition = getExecutionScreenPosition(LIFELINE_A, 0);
        Dimension executionDimension = getExecutionScreenDimension(LIFELINE_A, 0);

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_A + " is not at the expected vertical position", 200, executionScreenPosition.y);

        int yPosition = executionScreenPosition.y + executionDimension.height / 2;
        // Creation of an syncCall
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, yPosition);
        editor.click(lifelineAPosition, yPosition);

        // Validates the position of the second execution
        assertEquals("The execution index 1 on lifeline " + LIFELINE_A + " is not at the expected vertical position", yPosition, getExecutionScreenPosition(LIFELINE_A, 1).y);

        validateOrdering(8);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Ordering2() throws Exception {

        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;
        int lifelineCPosition = getScreenPosition(LIFELINE_C).x + getScreenSize(LIFELINE_C).width / 2;

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineAPosition, 200);
        editor.click(lifelineBPosition, 200);

        Point executionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0);
        getExecutionScreenDimension(LIFELINE_B, 0);

        // Validates the position of the execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));
        // assertEquals("The execution index 0 on lifeline " + LIFELINE_B +
        // " is not at the expected vertical position", 200 -
        // IBorderItemOffsets.DEFAULT_OFFSET.height, executionScreenPosition.y);

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, executionScreenPosition.y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP / 2);
        editor.click(lifelineCPosition, executionScreenPosition.y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP / 2);

        // Validates the position of the second execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionScreenPosition(LIFELINE_C, 0));
        // assertEquals("The execution index 0 on lifeline " + LIFELINE_C +
        // " is not at the expected vertical position",
        // executionScreenPosition.y + executionDimension.height / 2 -
        // IBorderItemOffsets.DEFAULT_OFFSET.height,
        // getExecutionScreenPosition(LIFELINE_C, 0).y);

        SequenceDiagramEditPart sequenceDiagramEditPart = Iterables.getOnlyElement(Iterables.filter(editor.rootEditPart().part().getChildren(), SequenceDiagramEditPart.class));
        assertNotNull("No SequenceDiagramEditPart found", sequenceDiagramEditPart);

        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();

        // Validates the semantic ordering equals the graphical ordering
        assertEquals("The number of event in semantic ordering is not as expected", 8, sequenceDDiagram.getSemanticOrdering().getEventEnds().size());
        assertEquals("The number of event in graphical ordering is not as expected", 8, sequenceDDiagram.getGraphicalOrdering().getEventEnds().size());
        assertTrue("The semantic ordering does not match the graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Move() throws Exception {

        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;
        int lifelineCPosition = getScreenPosition(LIFELINE_C).x + getScreenSize(LIFELINE_C).width / 2;

        // Creation of a Sync Call
        Point start = new Point(lifelineAPosition, 200);
        Point end = new Point(lifelineBPosition, 200);
        createSyncCall(start, end);

        // Validates the position of the execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));

        // Creation of a Sync Call
        start = new Point(lifelineBPosition, 300);
        end = new Point(lifelineCPosition, 300);
        createSyncCall(start, end);

        // Validates the position of the second execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionScreenPosition(LIFELINE_C, 0));

        Point firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        Dimension firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        Point secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        Dimension secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();

        editor.drag(firstExecutionScreenPosition, new Point(firstExecutionScreenPosition.x + 200, firstExecutionScreenPosition.y + 200));
        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + 200, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 0).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        validateOrdering(8);

        firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();

        // Resize up
        editor.click(secondExecutionScreenPosition.x, secondExecutionScreenPosition.y);
        CheckResize cR = new CheckResize(LIFELINE_C, 0, new Point(0, -50), editor);
        editor.drag(new Point(secondExecutionScreenPosition.x + secondExecutionDimension.width / 2, secondExecutionScreenPosition.y), new Point(secondExecutionScreenPosition.x - 50,
                secondExecutionScreenPosition.y - 50));
        bot.waitUntil(cR);

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y - 50, getExecutionScreenPosition(LIFELINE_C, 0).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height + 50,
                getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        validateOrdering(8);

        firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();

        // Move up the sync call on lifeline B on forbidden location (both end
        // of sync call on the same vertical)
        editor.drag(firstExecutionScreenPosition, new Point(firstExecutionScreenPosition.x - 100, firstExecutionScreenPosition.y - 100));

        // effect
        firstExecutionScreenPosition.y = firstExecutionScreenPosition.y - 100;
        secondExecutionDimension.height += firstExecutionDimension.height;

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 0).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        validateOrdering(8);

        undo();

        // Move up the sync call on lifeline B
        firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();

        // Unfocus
        editor.click(0, 0);
        editor.drag(firstExecutionScreenPosition, new Point(firstExecutionScreenPosition.x, secondExecutionScreenPosition.y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP));
        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", secondExecutionScreenPosition.y
                + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 0).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        validateOrdering(8);

        // then move out back
        firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();

        // Unfocus
        editor.click(0, 0);
        editor.drag(firstExecutionScreenPosition, new Point(firstExecutionScreenPosition.x + 120, firstExecutionScreenPosition.y + 120));
        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + 120, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 0).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        // then move the sync call on lifeline C over the one on lifeline B
        firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();

        // Unfocus
        editor.click(0, 0);
        editor.drag(secondExecutionScreenPosition, new Point(secondExecutionScreenPosition.x + 120, secondExecutionScreenPosition.y + 120));
        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + 120,
                getExecutionScreenPosition(LIFELINE_C, 0).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        validateOrdering(8);

        // Creation of a Sync Call
        start = new Point(lifelineAPosition, 200);
        end = new Point(lifelineBPosition, 200);
        createSyncCall(start, end);
        // Validate we can move a sync call over another one

        firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1).getCopy();
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 1).getCopy();

        // Resize up a sync call
        editor.click(secondExecutionScreenPosition.x, secondExecutionScreenPosition.y);
        cR = new CheckResize(LIFELINE_B, 1, new Point(0, -LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 2), editor);
        editor.drag(secondExecutionScreenPosition.x + secondExecutionDimension.width / 2, secondExecutionScreenPosition.y, secondExecutionScreenPosition.x, secondExecutionScreenPosition.y
                - LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 2);
        bot.waitUntil(cR);

        // Move the other sync call over the resized sync call
        editor.drag(firstExecutionScreenPosition, secondExecutionScreenPosition.x, secondExecutionScreenPosition.y - LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP);
        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", secondExecutionScreenPosition.y
                - LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 2, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x + firstExecutionDimension.width
                - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width, getExecutionScreenPosition(LIFELINE_B, 1).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", secondExecutionScreenPosition.y
                - LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, getExecutionScreenPosition(LIFELINE_B, 1).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 1).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected height", secondExecutionDimension.height + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 2,
                getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 1).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);

        // Validate the positions of the messages

        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 1), getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        validateOrdering(12);

    }

    /**
     * Test method.
     * 
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_ArrangeAll() throws Exception {
        test_SyncCall_Move();

        arrangeAll();

        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Validate vertical position after arrange all
        Rectangle eB0ExpectedBounds = new Rectangle();
        Rectangle eB1ExpectedBounds = new Rectangle();
        Rectangle eC0ExpectedBounds = new Rectangle();

        eB0ExpectedBounds.y = getScreenPosition(LIFELINE_B).y + getScreenSize(LIFELINE_B).height + LayoutConstants.TIME_START_OFFSET;
        eB1ExpectedBounds.y = eB0ExpectedBounds.y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;
        eB1ExpectedBounds.height = LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT;
        eB0ExpectedBounds.height = eB1ExpectedBounds.bottom() + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP - eB0ExpectedBounds.y;
        eC0ExpectedBounds.y = eB0ExpectedBounds.bottom() + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;
        eC0ExpectedBounds.height = LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT;

        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, eB0ExpectedBounds, false);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, eB1ExpectedBounds, false);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 0, eC0ExpectedBounds, false);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 1), getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
    }

    /**
     * Test move second syncCall top of the first syncCall having feedback of
     * callMessage of second syncCall above the callMessage of the first
     * syncCall and the returnMessage of the second syncCall between the
     * callMessage and returnMessage of the first syncCall.
     */
    public void test_SyncCall_Move2() {
        Point startOfFirstSyncCall = instanceRoleBBounds.getCenter().translate(0, 100);
        Point endOfFirstSyncCall = instanceRoleCBounds.getCenter().translate(0, 100);
        createSyncCall(startOfFirstSyncCall, endOfFirstSyncCall);

        Point startOfSecondSyncCall = instanceRoleCBounds.getCenter().translate(0, 200);
        Point endOfSecondSyncCall = instanceRoleBBounds.getCenter().translate(0, 200);
        createSyncCall(startOfSecondSyncCall, endOfSecondSyncCall);

        // Select the diagram itself for the future drag
        editor.select(editor.mainEditPart());

        SWTBotGefEditPart executionEditPartOfBBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        SWTBotGefEditPart executionEditPartOfCBot = instanceRoleCBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);

        SWTBotGefConnectionEditPart callMessageOnE2Bot = executionEditPartOfBBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart callMessageOnE1Bot = executionEditPartOfCBot.targetConnections().get(0);

        SWTBotGefConnectionEditPart returnMessageOfE2Bot = executionEditPartOfBBot.sourceConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageOfE1Bot = executionEditPartOfCBot.sourceConnections().get(0);

        Rectangle executionOfBBounds = editor.getBounds(executionEditPartOfBBot);
        Rectangle executionOfCBounds = editor.getBounds(executionEditPartOfCBot);

        // Checks newly created syncCall bounds
        // Checks execs bounds
        Rectangle expectedExecutionOfBBounds = new Rectangle(instanceRoleBBounds.getCenter().x - LayoutConstants.DEFAULT_EXECUTION_WIDTH / 2, startOfSecondSyncCall.y,
                LayoutConstants.DEFAULT_EXECUTION_WIDTH, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT);
        assertEquals(expectedExecutionOfBBounds, executionOfBBounds);
        Rectangle expectedExecutionOfCBounds = new Rectangle(instanceRoleCBounds.getCenter().x - LayoutConstants.DEFAULT_EXECUTION_WIDTH / 2, startOfFirstSyncCall.y,
                LayoutConstants.DEFAULT_EXECUTION_WIDTH, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT);
        assertEquals(expectedExecutionOfCBounds, executionOfCBounds);

        // Checks messages bounds
        int messageXDelta = 2;
        Rectangle expectedCallMessageOnE1Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE1Bot.part(),
                new Rectangle(startOfFirstSyncCall.getTranslated(messageXDelta, 0), endOfFirstSyncCall.getTranslated(-LayoutConstants.DEFAULT_EXECUTION_WIDTH / 2, 0)), true, true);
        Rectangle expectedCallMessageOnE2Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE2Bot.part(),
                new Rectangle(endOfSecondSyncCall.getTranslated(LayoutConstants.DEFAULT_EXECUTION_WIDTH / 2, 0), startOfSecondSyncCall.getTranslated(-messageXDelta, 0)), false, true);

        Rectangle expectedReturnMessageOfE1Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE1Bot.part(),
                expectedCallMessageOnE1Bounds.getTranslated(0, executionOfCBounds.height), false, true);
        Rectangle expectedReturnMessageOfE2Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE2Bot.part(),
                expectedCallMessageOnE2Bounds.getTranslated(0, executionOfBBounds.height), true, true);

        // Test
        Point dragPoint = instanceRoleBBounds.getCenter().translate(0, 80);
        int dy = dragPoint.y - expectedExecutionOfBBounds.y;
        ICondition condition = new CheckEditPartMoved(executionEditPartOfBBot);
        editor.drag(executionEditPartOfBBot, dragPoint.x, dragPoint.y);
        bot.waitUntil(condition);

        // Checks bounds after move
        // Checks that the second syncCall is correctly moved before the first
        // syncCall
        assertEquals(expectedExecutionOfBBounds.getTranslated(0, dy), editor.getBounds(executionEditPartOfBBot));
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE2Bot.part(), expectedCallMessageOnE2Bounds.getTranslated(0, dy), false, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE2Bot.part(), expectedReturnMessageOfE2Bounds.getTranslated(0, dy), true, true);

        // Checks that the second syncCall is shifted down
        int yShiftDelta = (expectedExecutionOfBBounds.getTranslated(0, dy).getBottom().y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP) - executionOfCBounds.y;
        assertEquals(expectedExecutionOfCBounds.getTranslated(0, yShiftDelta), editor.getBounds(executionEditPartOfCBot));
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE1Bot.part(), expectedCallMessageOnE1Bounds.getTranslated(0, yShiftDelta), true, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE1Bot.part(), expectedReturnMessageOfE1Bounds.getTranslated(0, yShiftDelta), false, true);

        // Undo
        undo();

        executionOfBBounds = editor.getBounds(executionEditPartOfBBot);
        executionOfCBounds = editor.getBounds(executionEditPartOfCBot);

        assertEquals(expectedExecutionOfBBounds, executionOfBBounds);
        assertEquals(expectedExecutionOfCBounds, executionOfCBounds);

        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE1Bot.part(), expectedCallMessageOnE1Bounds, true, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE1Bot.part(), expectedReturnMessageOfE1Bounds, false, true);

        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE2Bot.part(), expectedCallMessageOnE2Bounds, false, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE2Bot.part(), expectedReturnMessageOfE2Bounds, true, true);

        // Redo
        redo();

        executionOfBBounds = editor.getBounds(executionEditPartOfBBot);
        executionOfCBounds = editor.getBounds(executionEditPartOfCBot);

        assertEquals(expectedExecutionOfBBounds.getTranslated(0, dy), editor.getBounds(executionEditPartOfBBot));
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE2Bot.part(), expectedCallMessageOnE2Bounds.getTranslated(0, dy), false, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE2Bot.part(), expectedReturnMessageOfE2Bounds.getTranslated(0, dy), true, true);

        assertEquals(expectedExecutionOfCBounds.getTranslated(0, yShiftDelta), editor.getBounds(executionEditPartOfCBot));
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE1Bot.part(), expectedCallMessageOnE1Bounds.getTranslated(0, yShiftDelta), true, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE1Bot.part(), expectedReturnMessageOfE1Bounds.getTranslated(0, yShiftDelta), false, true);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Move_Group() throws Exception {

        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Calculate the X position of the center of lifelines A and B
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;
        ArrayList<Integer> clics = new ArrayList<Integer>();

        // Creation of a Sync Call
        int currentY = 450;
        createMessage("Sync Call", LIFELINE_A, 450, LIFELINE_B, 450);
        clics.add(currentY - currentY);

        // Creation of a Sync Call
        int firstSynCallY = getExecutionScreenPosition(LIFELINE_B, 0).y + 10;
        createMessage("Sync Call", LIFELINE_B, firstSynCallY, LIFELINE_C, firstSynCallY);
        clics.add(firstSynCallY - currentY);
        currentY = firstSynCallY;

        // Creation of a participant
        ICondition done = new OperationDoneCondition();
        createParticipant(getScreenPosition(LIFELINE_C).x + getScreenSize(LIFELINE_C).width + LayoutConstants.LIFELINES_MIN_X_GAP, 100);
        bot.waitUntil(done);

        // Creation of a Sync Call
        int secondSyncCallY = getExecutionScreenPosition(LIFELINE_C, 0).y + 10;
        createMessage("Sync Call", LIFELINE_C, secondSyncCallY, NEW_LIFELINE, secondSyncCallY);
        clics.add(secondSyncCallY - currentY);
        currentY = secondSyncCallY;

        // Add 2 executions on the one on lifelne B
        int execB1Y = getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height - 1;
        createExecution(lifelineBPosition, execB1Y);
        clics.add(execB1Y - currentY);
        currentY = execB1Y;
        int execB2Y = getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height - 1;
        createExecution(lifelineBPosition, execB2Y);
        clics.add(execB2Y - currentY);
        currentY = execB2Y;

        // Creation of a Sync Call
        int thirdSyncCall = getExecutionScreenPosition(LIFELINE_B, 2).y + 10;
        createMessage("Sync Call", getExecutionScreenPosition(LIFELINE_B, 2).x + 10, thirdSyncCall, LIFELINE_C, thirdSyncCall);
        clics.add(thirdSyncCall - currentY);
        currentY = thirdSyncCall;

        validateGroupStability(450, 20, clics);

        // slightly move up the group to check this is invalidated by linked
        // executions
        int moveVerticalPosition = getExecutionScreenPosition(LIFELINE_B, 0).y - LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 2;
        editor.drag(getExecutionScreenPosition(LIFELINE_B, 0), lifelineBPosition, moveVerticalPosition);

        validateGroupStability(moveVerticalPosition, 20, clics);

        // unfocus
        editor.click(0, 0);

        // move up the group to the begining of the lifeline
        int toYPosition = getScreenPosition(LIFELINE_B).y + getScreenSize(LIFELINE_B).height + LayoutConstants.TIME_START_OFFSET;
        editor.drag(getExecutionScreenPosition(LIFELINE_B, 0), lifelineBPosition, toYPosition);

        validateGroupStability(toYPosition, 20, clics);

        // add a message after the group
        createMessage("Read", LIFELINE_A, getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP,
                LIFELINE_B, getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP);

        // unfocus
        editor.click(0, 0);
        // move the group after this message
        editor.drag(getExecutionScreenPosition(LIFELINE_B, 0), lifelineBPosition, getSequenceMessageScreenVerticalPosition(NINETH_MESSAGE) + 5);

        validateGroupStability(getSequenceMessageScreenVerticalPosition(NINETH_MESSAGE) + 5, 22, clics);

        // unfocus
        editor.click(0, 0);
        // Arrange All
        arrangeAll();
        validateGroupStability(getSequenceMessageFirstBendpointScreenPosition(NINETH_MESSAGE).y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, 22, clics, 1, true);
    }

    /**
     * Test the move of a multiple selection of Execution.
     */
    public void test_SyncCall_Move_Multiple_Selection() {

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);

        // Creation of the first SyncCall
        int y1 = 200;
        Point startFirstSyncCall = new Point(instanceRoleABounds.getCenter().x, y1);
        Point endFirstSyncCall = new Point(instanceRoleBBounds.getCenter().x, y1);
        createSyncCall(startFirstSyncCall, endFirstSyncCall);

        // check positions
        Rectangle execB0 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(0, y1, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, execB0.y);
        Rectangle returnB0 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(0, execB0.bottom(), 0, 0), false, false);

        // Creation of the second SyncCall
        int vGap = 10;
        int y2 = y1 + vGap;
        Point startSecondSyncCall = new Point(instanceRoleBBounds.getCenter().x, y2);
        Point endSecondSyncCall = new Point(instanceRoleCBounds.getCenter().x, y2);
        createSyncCall(startSecondSyncCall, endSecondSyncCall);

        // Expected effect
        execB0.height += vGap + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        returnB0.y += vGap + LayoutConstants.EXECUTION_CHILDREN_MARGIN;

        // check positions and stability
        execB0 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, execB0);
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, execB0.y);
        returnB0 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, returnB0, false);

        Rectangle execC0 = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, new Rectangle(0, y2, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);
        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C, execC0.y);
        Rectangle returnC0 = assertReturnMessageHasValidScreenBounds(LIFELINE_C, 0, new Rectangle(0, execC0.bottom(), 0, 0), false, false);

        // Creation of a Sync Call
        int y3 = 300;
        Point startThirdSyncCall = new Point(instanceRoleABounds.getCenter().x, y3);
        Point endThirdSyncCall = new Point(instanceRoleBBounds.getCenter().x, y3);
        createSyncCall(startThirdSyncCall, endThirdSyncCall);

        // check positions and stability
        execB0 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, execB0);
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, execB0.y);
        returnB0 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, returnB0, false);

        execC0 = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, execC0);
        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C, execC0.y);
        returnC0 = assertReturnMessageHasValidScreenBounds(LIFELINE_C, 0, returnC0, false);

        Rectangle execB1 = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(0, y3, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);
        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, execB1.y);
        Rectangle returnB1 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(0, execB1.bottom(), 0, 0), false, false);

        int lengthA = getLifelineLength(LIFELINE_A);
        int lengthB = getLifelineLength(LIFELINE_B);
        int lengthC = getLifelineLength(LIFELINE_C);

        assertEquals(lengthA, lengthB);
        assertEquals(lengthA, lengthC);

        // Resize lifelines
        int lifelineDeltaH = 200;
        resizeLifeline(LIFELINE_A, lifelineDeltaH);

        // Check lifelines size
        checkLifelineSize(LIFELINE_A, 40, lengthA + lifelineDeltaH);
        checkLifelineSize(LIFELINE_B, 40, lengthB + lifelineDeltaH);
        checkLifelineSize(LIFELINE_C, 40, lengthC + lifelineDeltaH);

        // Check positions and stability
        execB0 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, execB0);
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, execB0.y);
        returnB0 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, returnB0, false);

        execC0 = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, execC0);
        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C, execC0.y);
        returnC0 = assertReturnMessageHasValidScreenBounds(LIFELINE_C, 0, returnC0, false);

        execB1 = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, execB1, false);
        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, execB1.y);
        returnB1 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, returnB1, false);

        // Selection of both executions by dragging over both executions
        selectEditParts(getExecutionEditPart(LIFELINE_B, 1), getExecutionEditPart(LIFELINE_B, 0));

        // Move the selection behind the second execution
        int deltaY = execB1.getBottom().y + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP - execB0.getLocation().y;
        editor.drag(execB0.getLocation(), execB0.getLocation().getCopy().getTranslated(new Dimension(0, deltaY)));

        // Expected effect
        execB0.y += deltaY;
        execB1.y += deltaY;
        execC0.y += deltaY;

        returnB0.y = execB0.bottom();
        returnB1.y = execB1.bottom();
        returnC0.y = execC0.bottom();

        // Check positions
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, execB0);
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, execB0.y);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, returnB0, false);

        assertExecutionHasValidScreenBounds(LIFELINE_C, 0, execC0);
        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C, execC0.y);
        assertReturnMessageHasValidScreenBounds(LIFELINE_C, 0, returnC0, false);

        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, execB1, false);
        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, execB1.y);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, returnB1, false);

        // Revert the precedent move
        deltaY = -deltaY;
        editor.drag(execB0.getLocation(), execB0.getLocation().getCopy().getTranslated(new Dimension(0, deltaY)));

        // Expected effect
        execB0.y += deltaY;
        execB1.y += deltaY;
        execC0.y += deltaY;

        returnB0.y = execB0.bottom();
        returnB1.y = execB1.bottom();
        returnC0.y = execC0.bottom();

        // Check positions
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, execB0);
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, execB0.y);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, returnB0, false);

        assertExecutionHasValidScreenBounds(LIFELINE_C, 0, execC0);
        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C, execC0.y);
        assertReturnMessageHasValidScreenBounds(LIFELINE_C, 0, returnC0, false);

        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, execB1, false);
        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, execB1.y);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, returnB1, false);

        // Move the group of executions to have the first execution where the
        // second execution was
        deltaY = execB1.getLocation().y - execB0.getLocation().y;
        editor.drag(execB0.getLocation(), execB1.getLocation());

        // Expected effect
        execB0.y += deltaY;
        execB1.y += deltaY;
        execC0.y += deltaY;
    }

    /**
     * Test Reconnect by dropping a sync call inside the range of another sync
     * call. The messages end of the dropped sync call will reconnect on the
     * unmoved sync call.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Move_Reconnect() throws Exception {
        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;
        int lifelineCPosition = getScreenPosition(LIFELINE_C).x + getScreenSize(LIFELINE_C).width / 2;

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineAPosition, 200);
        editor.click(lifelineBPosition, 200);

        getExecutionScreenPosition(LIFELINE_B, 0);
        getExecutionScreenDimension(LIFELINE_B, 0);

        // Validates the position of the execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, 300);
        editor.click(lifelineCPosition, 300);

        // Validates the position of the second execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionScreenPosition(LIFELINE_C, 0));

        Point firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        Dimension firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        Point secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        Dimension secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();

        // focus on the sync call on lifeline B to resize it
        editor.click(firstExecutionScreenPosition.x, firstExecutionScreenPosition.y);
        // resize up
        CheckResize cR = new CheckResize(LIFELINE_B, 0, new Point(0, -50), editor);
        editor.drag(new Point(firstExecutionScreenPosition.x + firstExecutionDimension.width / 2, firstExecutionScreenPosition.y), new Point(firstExecutionScreenPosition.x - 50,
                firstExecutionScreenPosition.y - 50));
        bot.waitUntil(cR);

        editor.click(0, 0);
        manualRefresh();

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y - 50, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 0).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height + 50,
                getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        validateOrdering(8);

        // move the sync call of lifeline C inside the one on lifeline B

        firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();

        // resize up
        editor.drag(secondExecutionScreenPosition, new Point(secondExecutionScreenPosition.x - 125, secondExecutionScreenPosition.y - 125));
        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y - 125,
                getExecutionScreenPosition(LIFELINE_C, 0).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        assertEquals("The first bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition((THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C)).x);
        assertEquals("The last bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 0).x,
                getSequenceMessageLastBendpointScreenPosition((THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C)).x);

        assertEquals("The first bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 0).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);

        validateOrdering(8);
    }

    /**
     * Test Reconnect by dropping a sync call on the message end of other sync
     * call. These messages end will reconnect on the dropped sync call.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Move_Reconnect2() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        resizeLifeline(LIFELINE_B, 200);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;
        int lifelineCPosition = getScreenPosition(LIFELINE_C).x + getScreenSize(LIFELINE_C).width / 2;

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineAPosition, 150);
        editor.click(lifelineBPosition, 150);

        getExecutionScreenPosition(LIFELINE_B, 0);
        getExecutionScreenDimension(LIFELINE_B, 0);

        // Validates the position of the execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, 160);
        editor.click(lifelineCPosition, 160);

        // Validates the position of the second execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionScreenPosition(LIFELINE_C, 0));

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, 500);
        editor.click(lifelineCPosition, 500);

        // Creation of a Read
        editor.activateTool("Read");
        editor.click(lifelineBPosition, 450);
        editor.click(lifelineCPosition, 450);

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, 600);
        editor.click(lifelineCPosition, 600);

        Rectangle firstExecutionScreenBounds = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        Point secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        Dimension secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();
        Point thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 1).getCopy();
        Dimension thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 1).getCopy();
        Point fourthExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 2).getCopy();
        Dimension fourthExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 2).getCopy();

        // focus on the sync call on lifeline B to resize it
        editor.click(firstExecutionScreenBounds.getTop());
        // resize up
        Dimension expectedResize = new Dimension(0, 200);
        CheckResize cR = new CheckResize(LIFELINE_B, 0, expectedResize, editor);
        editor.drag(firstExecutionScreenBounds.getBottom(), firstExecutionScreenBounds.getBottom().getTranslated(expectedResize));
        bot.waitUntil(cR);

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenBounds.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenBounds.y, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " is not at the expected vertical position", thirdExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 1).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " is not at the expected vertical position", thirdExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 1).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " is not at the expected vertical position", fourthExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 2).x);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " is not at the expected vertical position", fourthExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 2).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionScreenBounds.height + 200,
                getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected height", thirdExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 1).getCopy().height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " has not the expected height", fourthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 2).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionScreenBounds.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected width", thirdExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 1).getCopy().width);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " has not the expected width", fourthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 2).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        assertMessageVerticalPosition(SEVENTH_MESSAGE_ON_LIFELINE_C, 450);

        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 1).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 1), getExecutionScreenPosition(LIFELINE_C, 1).y + getExecutionScreenDimension(LIFELINE_C, 1).height);

        assertMessageVerticalPosition(EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C, getExecutionScreenPosition(LIFELINE_C, 2).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 2), getExecutionScreenPosition(LIFELINE_C, 2).y + getExecutionScreenDimension(LIFELINE_C, 2).height);

        assertEquals("The first bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageLastBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The first bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);

        assertEquals("The first bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 0).x,
                getSequenceMessageLastBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The first bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 0).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);

        assertEquals("The first bendpoint of the message named " + SEVENTH_MESSAGE_ON_LIFELINE_C + " is not at the expected horizontal position", lifelineBPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(SEVENTH_MESSAGE_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + SEVENTH_MESSAGE_ON_LIFELINE_C + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(SEVENTH_MESSAGE_ON_LIFELINE_C).x);

        assertEquals("The first bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineBPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 1).x,
                getSequenceMessageLastBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL).x);
        assertEquals("The first bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 1).x,
                getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 1)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineBPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 1)).x);

        assertEquals("The first bendpoint of the message named " + EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", lifelineBPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 2).x,
                getSequenceMessageLastBendpointScreenPosition(EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The first bendpoint of the return message linked to " + EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 2).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 2)).x);
        assertEquals("The last bendpoint of the return message linked to " + EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", lifelineBPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 2)).x);

        validateOrdering(18);

        firstExecutionScreenBounds = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();
        thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 1).getCopy();
        thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 1).getCopy();
        fourthExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 2).getCopy();
        fourthExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 2).getCopy();

        resizeLifeline(LIFELINE_A, 50);
        editor.click(0, 0);

        int dragPositionTarget = getSequenceMessageScreenVerticalPosition(SEVENTH_MESSAGE_ON_LIFELINE_C) - LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP - 10;

        // move the sync call of lifeline B to include all message on lifeline C
        editor.drag(firstExecutionScreenBounds.getLocation(), new Point(firstExecutionScreenBounds.x, dragPositionTarget));

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenBounds.x, getExecutionLogicalPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", dragPositionTarget, getExecutionLogicalPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionLogicalPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y - firstExecutionScreenBounds.y,
                getExecutionScreenPosition(LIFELINE_C, 0).y - getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " is not at the expected vertical position", thirdExecutionScreenPosition.x, getExecutionLogicalPosition(LIFELINE_C, 1).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " is not at the expected vertical position", thirdExecutionScreenPosition.y, getExecutionLogicalPosition(LIFELINE_C, 1).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " is not at the expected vertical position", fourthExecutionScreenPosition.x, getExecutionLogicalPosition(LIFELINE_C, 2).x);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " is not at the expected vertical position", fourthExecutionScreenPosition.y, getExecutionLogicalPosition(LIFELINE_C, 2).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionScreenBounds.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected height", thirdExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 1).getCopy().height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " has not the expected height", fourthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 2).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionScreenBounds.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected width", thirdExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 1).getCopy().width);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " has not the expected width", fourthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 2).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        assertMessageVerticalPosition(SEVENTH_MESSAGE_ON_LIFELINE_C, 450 - GraphicalHelper.getScrollSize(((IGraphicalEditPart) editor.mainEditPart().part())).y());

        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 1).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 1), getExecutionScreenPosition(LIFELINE_C, 1).y + getExecutionScreenDimension(LIFELINE_C, 1).height);

        assertMessageVerticalPosition(EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C, getExecutionScreenPosition(LIFELINE_C, 2).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 2), getExecutionScreenPosition(LIFELINE_C, 2).y + getExecutionScreenDimension(LIFELINE_C, 2).height);

        assertEquals("The first bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageLastBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The first bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);

        assertEquals("The first bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 0).x,
                getSequenceMessageLastBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The first bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 0).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);

        assertEquals("The first bendpoint of the message named " + SEVENTH_MESSAGE_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(SEVENTH_MESSAGE_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + SEVENTH_MESSAGE_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 0).x,
                getSequenceMessageLastBendpointScreenPosition(SEVENTH_MESSAGE_ON_LIFELINE_C).x);

        assertEquals("The first bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 1).x,
                getSequenceMessageLastBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL).x);
        assertEquals("The first bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 1).x,
                getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 1)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 1)).x);

        assertEquals("The first bendpoint of the message named " + EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 2).x,
                getSequenceMessageLastBendpointScreenPosition(EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The first bendpoint of the return message linked to " + EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 2).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 2)).x);
        assertEquals("The last bendpoint of the return message linked to " + EIGHTH_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 2)).x);

        validateOrdering(18);

    }

    /**
     * Test Reconnect by dropping a sync call, with linked sync call, on the
     * message ends of other sync calls. These message ends will reconnect on
     * the dropped sync call.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Move_Reconnect3() throws Exception {
        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        resizeLifeline(LIFELINE_B, 200);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;
        int lifelineCPosition = getScreenPosition(LIFELINE_C).x + getScreenSize(LIFELINE_C).width / 2;

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineAPosition, 150);
        editor.click(lifelineBPosition, 150);

        getExecutionScreenPosition(LIFELINE_B, 0);
        getExecutionScreenDimension(LIFELINE_B, 0);

        // Validates the position of the execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, 160);
        editor.click(lifelineCPosition, 160);

        // Validates the position of the second execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_C + " has not been found", getExecutionScreenPosition(LIFELINE_C, 0));

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineCPosition, 400);
        editor.click(lifelineAPosition, 400);

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, 500);
        editor.click(lifelineCPosition, 500);

        Point firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        Dimension firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        Point secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        Dimension secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();
        Point thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_A, 0).getCopy();
        Dimension thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_A, 0).getCopy();
        Point fourthExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 1).getCopy();
        Dimension fourthExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 1).getCopy();

        // focus on the sync call on lifeline B to resize it
        editor.click(firstExecutionScreenPosition.x, firstExecutionScreenPosition.y);
        // resize up
        Dimension expectedResize = new Dimension(0, 150);
        CheckResize cR = new CheckResize(LIFELINE_B, 0, expectedResize, editor);
        editor.drag(new Point(firstExecutionScreenPosition.x + firstExecutionDimension.width / 2, firstExecutionScreenPosition.y + firstExecutionDimension.height), new Point(
                firstExecutionScreenPosition.x - 50, firstExecutionScreenPosition.y + firstExecutionDimension.height + 150));
        bot.waitUntil(cR);

        // focus on the sync call on lifeline C to resize it
        editor.click(secondExecutionScreenPosition.x, secondExecutionScreenPosition.y);
        // resize up
        cR = new CheckResize(LIFELINE_C, 0, new Dimension(0, 50), editor);
        editor.drag(new Point(secondExecutionScreenPosition.x + secondExecutionDimension.width / 2, secondExecutionScreenPosition.y + secondExecutionDimension.height), new Point(
                secondExecutionScreenPosition.x - 50, secondExecutionScreenPosition.y + secondExecutionDimension.height + 50));
        bot.waitUntil(cR);

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " is not at the expected vertical position", thirdExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_A, 0).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " is not at the expected vertical position", thirdExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_A, 0).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " is not at the expected vertical position", fourthExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 1).x);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " is not at the expected vertical position", fourthExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 1).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height + 150,
                getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height + 50,
                getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected height", thirdExecutionDimension.height, getExecutionScreenDimension(LIFELINE_A, 0).getCopy().height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " has not the expected height", fourthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 1).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected width", thirdExecutionDimension.width, getExecutionScreenDimension(LIFELINE_A, 0).getCopy().width);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " has not the expected width", fourthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 1).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A, getExecutionScreenPosition(LIFELINE_A, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_A, 0), getExecutionScreenPosition(LIFELINE_A, 0).y + getExecutionScreenDimension(LIFELINE_A, 0).height);

        assertMessageVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, getExecutionScreenPosition(LIFELINE_C, 1).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 1), getExecutionScreenPosition(LIFELINE_C, 1).y + getExecutionScreenDimension(LIFELINE_C, 1).height);

        assertEquals("The first bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageLastBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The first bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);

        assertEquals("The first bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 0).x,
                getSequenceMessageLastBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The first bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 0).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);

        assertEquals("The first bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A).x);
        assertEquals("The last bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_A, 0).x
                + getExecutionScreenDimension(LIFELINE_A, 0).width, getSequenceMessageLastBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A).x);
        assertEquals("The first bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_A, 0).x + getExecutionScreenDimension(LIFELINE_A, 0).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_A, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_A, 0)).x);

        assertEquals("The first bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position", lifelineBPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE).x);
        assertEquals("The last bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 1).x, getSequenceMessageLastBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE).x);
        assertEquals("The first bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 1).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 1)).x);
        assertEquals("The last bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position", lifelineBPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 1)).x);

        validateOrdering(16);

        firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();
        thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_A, 0).getCopy();
        thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_A, 0).getCopy();
        fourthExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 1).getCopy();
        fourthExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 1).getCopy();

        int dragPositionTarget = getSequenceMessageScreenVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A) - LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP - 30;

        // move the sync call of lifeline B to include all message on lifeline C
        editor.drag(firstExecutionScreenPosition, new Point(firstExecutionScreenPosition.x, dragPositionTarget));
        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", dragPositionTarget, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y - firstExecutionScreenPosition.y,
                getExecutionScreenPosition(LIFELINE_C, 0).y - getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " is not at the expected vertical position", thirdExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_A, 0).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " is not at the expected vertical position", thirdExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_A, 0).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " is not at the expected vertical position", fourthExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 1).x);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " is not at the expected vertical position", fourthExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 1).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected height", thirdExecutionDimension.height, getExecutionScreenDimension(LIFELINE_A, 0).getCopy().height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " has not the expected height", fourthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 1).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected width", thirdExecutionDimension.width, getExecutionScreenDimension(LIFELINE_A, 0).getCopy().width);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " has not the expected width", fourthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 1).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A, getExecutionScreenPosition(LIFELINE_A, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_A, 0), getExecutionScreenPosition(LIFELINE_A, 0).y + getExecutionScreenDimension(LIFELINE_A, 0).height);

        assertMessageVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, getExecutionScreenPosition(LIFELINE_C, 1).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 1), getExecutionScreenPosition(LIFELINE_C, 1).y + getExecutionScreenDimension(LIFELINE_C, 1).height);

        assertEquals("The first bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageLastBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The first bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);

        assertEquals("The first bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 0).x,
                getSequenceMessageLastBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The first bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 0).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);

        assertEquals("The first bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 0).x,
                getSequenceMessageFirstBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A).x);
        assertEquals("The last bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_A, 0).x
                + getExecutionScreenDimension(LIFELINE_A, 0).width, getSequenceMessageLastBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A).x);
        assertEquals("The first bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_A, 0).x + getExecutionScreenDimension(LIFELINE_A, 0).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_A, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 0).x, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_A, 0)).x);

        assertEquals("The first bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width,
                getSequenceMessageFirstBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE).x);
        assertEquals("The last bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 1).x, getSequenceMessageLastBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE).x);
        assertEquals("The first bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 1).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 1)).x);
        assertEquals("The last bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 1)).x);

        validateOrdering(16);

        firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0).getCopy();
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0).getCopy();
        thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_A, 0).getCopy();
        thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_A, 0).getCopy();
        fourthExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 1).getCopy();
        fourthExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 1).getCopy();

        dragPositionTarget = 150;

        // move the sync call on lifeline A to the top
        editor.drag(thirdExecutionScreenPosition, new Point(thirdExecutionScreenPosition.x, dragPositionTarget));
        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " is not at the expected vertical position", thirdExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_A, 0).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " is not at the expected vertical position", dragPositionTarget, getExecutionScreenPosition(LIFELINE_A, 0).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " is not at the expected vertical position", fourthExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_C, 1).x);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " is not at the expected vertical position", fourthExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_C, 1).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected height", thirdExecutionDimension.height, getExecutionScreenDimension(LIFELINE_A, 0).getCopy().height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " has not the expected height", fourthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_C, 1).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 0).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected width", thirdExecutionDimension.width, getExecutionScreenDimension(LIFELINE_A, 0).getCopy().width);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_C + " has not the expected width", fourthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_C, 1).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);

        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A, getExecutionScreenPosition(LIFELINE_A, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_A, 0), getExecutionScreenPosition(LIFELINE_A, 0).y + getExecutionScreenDimension(LIFELINE_A, 0).height);

        assertMessageVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, getExecutionScreenPosition(LIFELINE_C, 1).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 1), getExecutionScreenPosition(LIFELINE_C, 1).y + getExecutionScreenDimension(LIFELINE_C, 1).height);

        assertEquals("The first bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageLastBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The first bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);

        assertEquals("The first bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_C, 0).x,
                getSequenceMessageLastBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x);
        assertEquals("The first bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 0).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 0)).x);

        assertEquals("The first bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A).x);
        assertEquals("The last bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_A, 0).x
                + getExecutionScreenDimension(LIFELINE_A, 0).width, getSequenceMessageLastBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A).x);
        assertEquals("The first bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_A, 0).x + getExecutionScreenDimension(LIFELINE_A, 0).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_A, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_A, 0)).x);

        assertEquals("The first bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width,
                getSequenceMessageFirstBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE).x);
        assertEquals("The last bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 1).x, getSequenceMessageLastBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE).x);
        assertEquals("The first bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_C, 1).x, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 1)).x);
        assertEquals("The last bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_C, 1)).x);

        validateOrdering(16);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Resize() throws Exception {
        do_test_SyncCall_Resize(false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_CollapsedSyncCall_Resize() throws Exception {
        do_test_SyncCall_Resize(true);
    }

    private void do_test_SyncCall_Resize(boolean collapse) throws Exception {

        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;
        int lifelineCPosition = getScreenPosition(LIFELINE_C).x + getScreenSize(LIFELINE_C).width / 2;

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineAPosition, 150);
        editor.click(lifelineBPosition, 150);
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, 160);
        editor.click(lifelineCPosition, 160);

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineAPosition, 300);
        editor.click(lifelineBPosition, 300);

        if (collapse) {
            // Switch outline to filters section
            bot.viewByTitle("Outline").setFocus();
            bot.viewByTitle("Outline").toolbarToggleButton("Filters").click();

            changeCollapseFilterActivation();

            editor.restore();
            editor.maximize();
        }

        Point firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0);
        Dimension firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0);
        Point secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0);
        Dimension secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0);
        Point thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1);
        Dimension thirdExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 1);

        // Select the first message to be able to move it then
        editor.click(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL));
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL), getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL).x - 20,
                getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL).y - 20);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Validate execution vertical position
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y - 20,
                getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

        // Move down the return message of the second execution of Lifeline B
        editor.click(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 1)));
        editor.drag(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 1)), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 1)).x + 50,
                getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 1)).y + 50);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Validate execution vertical position
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y - 20,
                getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height + 50,
                getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height, 1);

        // Move down message m5 of the second execution of Lifeline B
        editor.click(getSequenceMessageScreenCenteredPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        editor.drag(getSequenceMessageScreenCenteredPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B), getSequenceMessageScreenCenteredPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x + 50,
                getSequenceMessageScreenCenteredPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).y + 50);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Validate execution vertical position
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y - 20,
                getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + 50,
                getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height + 50,
                getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height, 1);

        // Move down the return message of the first execution of Lifeline B
        editor.click(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)));
        editor.drag(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)).x + 50,
                getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)).y + 50);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Validate execution vertical position
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y - 20,
                getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height + 50,
                getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + 50,
                getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height + 50,
                getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height, 1);

        // Move down the return message of the first execution of Lifeline C
        int delta = 40;
        thirdExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1);
        editor.click(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)));
        editor.drag(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)).x + delta,
                getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)).y + delta);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Validate execution vertical position
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y - 20,
                getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y,
                getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height
                + delta, getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height, 1);

        assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height, 1);

        // Move down message m3 of the first execution of Lifeline C
        secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_C, 0);
        secondExecutionDimension = getExecutionScreenDimension(LIFELINE_C, 0);
        editor.click(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
        editor.drag(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C), getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x + 50,
                getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).y + 50);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Validate execution vertical position
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y - 20,
                getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The beginning of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + 50,
                getExecutionScreenPosition(LIFELINE_C, 0).y);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_C + " is not at the expected vertical position", secondExecutionScreenPosition.y + secondExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_C, 0).y + getExecutionScreenDimension(LIFELINE_C, 0).height);
        assertEquals("The end of the execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y + firstExecutionDimension.height + 50,
                getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);

        assertEquals("The beginning of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y,
                getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The end of the execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y + thirdExecutionDimension.height,
                getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height, 1);

        // Validation of forbidden move

        // Validate it is not possible to move up message m1 before the begining
        // of the lifeline
        editor.click(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL));
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL), getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL).x - 50,
                getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL).y - 50);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Validate execution vertical position
        Rectangle b0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(0, firstExecutionScreenPosition.y - 20, 0, firstExecutionDimension.height + 70), false);
        Rectangle c0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, new Rectangle(0, secondExecutionScreenPosition.y + 50, 0, secondExecutionDimension.height - 50), false);
        Rectangle b1Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(thirdExecutionScreenPosition, thirdExecutionDimension), false);

        // Validate it is not possible to move down message m3 after the return
        // message of the same execution
        editor.click(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
        editor.drag(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)).x + 10,
                getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_C, 0)).y + 10);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Validate execution vertical position
        b0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, b0Bounds, false);
        c0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, c0Bounds, false);
        b1Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, b1Bounds, false);

        // Validate it is not possible to move up message m3 before m1
        editor.click(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C));
        editor.drag(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)).x - 10,
                getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)).y - 2);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Validate execution vertical position
        b0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, b0Bounds, false);
        c0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, c0Bounds, false);
        b1Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, b1Bounds, false);

        // Validate it is not possible to move up message m1 after m3
        editor.click(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL));
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL), getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).x + 10,
                getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C).y + 10);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Validate execution vertical position
        b0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, b0Bounds, false);
        c0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, c0Bounds, false);
        b1Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, b1Bounds, false);

        // Validate that moving down the return message of the
        // first execution on lifeline B after m5 causes auto-expand
        int overlap = 10;
        int toYPosition = getSequenceMessageScreenCenteredPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).y + overlap;
        editor.click(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)));
        editor.drag(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)), getSequenceMessageScreenCenteredPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x + 10, toYPosition);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Expected effect
        int prevH = b0Bounds.height;
        b0Bounds.height = toYPosition - b0Bounds.y;
        b1Bounds.y += LayoutConstants.EXECUTION_CHILDREN_MARGIN + overlap;

        // Validate execution vertical position
        b0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, b0Bounds, false);
        c0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, c0Bounds, false);
        b1Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, b1Bounds, false);

        // Undo
        undo();
        b0Bounds.height = prevH;
        b1Bounds.y -= LayoutConstants.EXECUTION_CHILDREN_MARGIN + overlap;

        // Validate execution vertical position
        b0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, b0Bounds, false);
        c0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, c0Bounds, false);
        b1Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, b1Bounds, false);

        // Validate it is possible to include m5 + exec + return
        int enclosing = b1Bounds.bottom() + 20 - b0Bounds.bottom();
        editor.click(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)));
        editor.drag(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 0)), getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 1)).x + 200, b0Bounds.bottom()
                + enclosing);

        validateMessagesOnEdgeOfExecution();
        validateOrdering(12);

        // Expected effect
        b0Bounds.height += enclosing;

        // Validate execution vertical position
        b0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, b0Bounds, false);
        c0Bounds = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, c0Bounds, false);
        b1Bounds = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, b1Bounds, false);

    }

    /**
     * Test Reconnect by dropping a sync call inside the range of another sync
     * call but without enough vertical space. The targeted sync call must
     * expand itself to reconnect the dropped sync call.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Move_Reconnect_With_ExpansionZone() throws Exception {

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);
        int lifelineCPosition = getLifelineScreenX(LIFELINE_C);

        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineAPosition, 150, lifelineBPosition, 150);

        Rectangle boundsB0 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(0, 150, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);
        Rectangle boundsM1 = assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, new Rectangle(new Point(0, 150), new Point(0, 150)), true, false);
        Rectangle boundsM2 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(new Point(0, 150 + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), new Point(0,
                150 + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT)), false, false);

        int m3verticalTarget = boundsB0.y + boundsB0.height + 10;

        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineBPosition, m3verticalTarget, lifelineCPosition, m3verticalTarget);
        // Select the diagram itself for the future drag
        editor.select(editor.mainEditPart());

        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);
        Rectangle boundsC0 = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, new Rectangle(0, m3verticalTarget, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, true);
        Rectangle boundsM3 = assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C, new Rectangle(new Point(0, m3verticalTarget), new Point(0, m3verticalTarget)), true, false);
        Rectangle boundsM4 = assertReturnMessageHasValidScreenBounds(LIFELINE_C, 0, new Rectangle(new Point(0, m3verticalTarget + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT),
                new Point(0, m3verticalTarget + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT)), false, false);

        Point dragTarget = boundsB0.getBottom().getCopy().getTranslated(new Point(0, -10));
        // Move the first sync call of lifeline C to have its invocation message
        // in the range of the execution on lifeline B, but not its return
        // message
        // The execution on lifeline B must expand itself
        editor.drag(boundsC0.getTopLeft(), dragTarget);

        // Expected bounds after the drag
        boundsC0.setLocation(new Point(boundsC0.x, dragTarget.y));
        boundsB0.resize(0, boundsC0.height);
        boundsM2.setLocation(new Point(boundsM2.x, boundsB0.getBottom().y));
        boundsM3.setBounds(new Rectangle(new Point(boundsB0.getRight().x, boundsC0.getTop().y), boundsC0.getTopLeft()));
        boundsM4.setBounds(new Rectangle(new Point(boundsB0.getRight().x, boundsC0.getBottom().y), boundsC0.getBottomLeft()));

        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 0, boundsC0);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, true);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C, boundsM3, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_C, 0, boundsM4, false, true);
        validateOrdering(8);
    }

    /**
     * Tests that when resizing a sync call, we can not overlap branches of
     * reflective messages which are around us.
     */
    public void test_sync_call_resize_with_reflective_branches_around() {
        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;

        // m1 from A to B
        editor.activateTool("Sync Call");
        editor.click(lifelineAPosition, 150);
        editor.click(lifelineBPosition, 150);

        // m2 below m1 on B (reflective)
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, 250);
        editor.click(lifelineBPosition, 260);

        // m3 below m2 from A to B
        editor.activateTool("Sync Call");
        editor.click(lifelineAPosition, 350);
        editor.click(lifelineBPosition, 350);
        bot.waitUntil(new CheckNumberExecutionOnLifeline(LIFELINE_B, 3, editor));

        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(instanceRoleABot.part());
        Map<EObject, Integer> initialPositions = readVerticalPositions(sequenceDiagram);

        // Try to resize e3 so that its bottom is right in the middle of e2.
        // This should be forbidden.
        resizeExecutionFromTop(LIFELINE_B, 2, getExecutionScreenBounds(LIFELINE_B, 1).getCenter().y - getExecutionScreenBounds(LIFELINE_B, 2).y);
        assertPositions(initialPositions, readVerticalPositions(sequenceDiagram));

        // Try to resize e3 so that its bottom is right in the middle of the
        // call branch of e2. This should be forbidden.
        resizeExecutionFromTop(LIFELINE_B, 2, getExecutionScreenBounds(LIFELINE_B, 1).y - 10 - getExecutionScreenBounds(LIFELINE_B, 2).y);
        assertPositions(initialPositions, readVerticalPositions(sequenceDiagram));

        // Try to resize e3 so that its bottom is right in the middle of the
        // return branch of e2. This should be forbidden.
        resizeExecutionFromTop(LIFELINE_B, 2, getExecutionScreenBounds(LIFELINE_B, 1).bottom() + 10 - getExecutionScreenBounds(LIFELINE_B, 2).y);
        assertPositions(initialPositions, readVerticalPositions(sequenceDiagram));

    }

    /**
     * Tests that execution filter hide executions like on eclipse 3.3 and that
     * messages are displayed as directly connected on lifelines.
     */
    public void test_sync_call_with_execution_filter() {
        // Creation of a first message between lifeline A and B
        createMessage("Sync Call", LIFELINE_A, 200, LIFELINE_B, 200);
        CheckMessageEditPartIsDisplayed check = new CheckMessageEditPartIsDisplayed(FIRST_MESSAGE, editor);
        bot.waitUntil(check);

        int firstMessageVerticalPosition = getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE);
        // Creation of a second message between lifeline C and B inside the
        // first message
        createMessage("Sync Call", LIFELINE_C, firstMessageVerticalPosition + 10, LIFELINE_B, firstMessageVerticalPosition + 10);
        check = new CheckMessageEditPartIsDisplayed(THIRD_MESSAGE, editor);
        bot.waitUntil(check);

        // Save bounds of executions and messages before applying execution
        // collapse filter
        Rectangle boundsE1 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(0, 200, 0, 65), false);
        Rectangle boundsE2 = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(0, 210, 0, 50), false);
        Rectangle boundsM1 = assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE, new Rectangle(new Point(0, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE)), new Point(0,
                getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE))), true, false);
        Rectangle boundsM2 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(new Point(0, getReturnSyncCallScreenPosition(LIFELINE_B, 0)), new Point(0,
                getReturnSyncCallScreenPosition(LIFELINE_B, 0))), false, false);
        Rectangle boundsM3 = assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE, new Rectangle(new Point(0, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE)), new Point(0,
                getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE))), true, false);
        Rectangle boundsM4 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(new Point(0, getReturnSyncCallScreenPosition(LIFELINE_B, 1)), new Point(0,
                getReturnSyncCallScreenPosition(LIFELINE_B, 1))), false, false);
        Dimension firstExecutionBounds = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();

        // Collapsing the execution will resize the message to be connected to
        // the lifeline
        boundsM1.resize(firstExecutionBounds.width / 2, 0);
        boundsM2.resize(firstExecutionBounds.width / 2, 0);
        int resizeDelta = boundsM3.x - getLifelineScreenX(LIFELINE_B);
        boundsM3.translate(-resizeDelta, 0);
        boundsM3.resize(resizeDelta, 0);
        boundsM4.translate(-resizeDelta, 0);
        boundsM4.resize(resizeDelta, 0);

        // Execution will be reduced to a width of 0 and aligned on the lifeline
        boundsE1.x = getLifelineScreenX(LIFELINE_B);
        boundsE1.width = 0;
        boundsE2.x = getLifelineScreenX(LIFELINE_B);
        boundsE2.width = 0;

        // toggle maximization of the editor
        editor.restore();

        // Switch outline to filters section
        bot.viewByTitle("Outline").setFocus();
        bot.viewByTitle("Outline").toolbarToggleButton("Filters").click();

        // Activate the first filter (CollapseExec)
        changeCollapseFilterActivation();

        // Validate bounds of Executions and Messages
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE1);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, true);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE, boundsM3, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, true, true);
    }

    /**
     * Tests size and bounds of exectuion after collapse and uncollapse.
     */
    public void test_sync_call_with_execution_filter_unfilter() {
        test_sync_call_with_execution_filter();

        changeCollapseFilterActivation();

        // Creation of an state on lifeline B
        Option<SWTBotGefEditPart> newStateOption = createStateWithResult(getLifelineScreenX(LIFELINE_B), getExecutionScreenBounds(LIFELINE_B, 1).getCenter().y);
        Assert.assertTrue(newStateOption.some());

        changeCollapseFilterActivation();

        // Get parts
        ExecutionEditPart e1 = getExecutionEditPart(LIFELINE_B, 0);
        ExecutionEditPart e2 = getExecutionEditPart(LIFELINE_B, 1);
        StateEditPart s1 = (StateEditPart) newStateOption.get().part();

        // Get correspondign DDiagramElements
        DDiagramElement dde1 = e1.resolveDiagramElement();
        DDiagramElement dde2 = e2.resolveDiagramElement();
        DDiagramElement dde3 = s1.resolveDiagramElement();

        // Check the global collapsed state
        assertTrue(new DDiagramElementQuery(dde1).isIndirectlyCollapsed());
        assertTrue(new DDiagramElementQuery(dde2).isIndirectlyCollapsed());
        assertTrue(new DDiagramElementQuery(dde3).isIndirectlyCollapsed());

        // Check the directly collapsed state
        assertTrue(new DDiagramElementQuery(dde1).isCollapsed());
        assertTrue(new DDiagramElementQuery(dde2).isCollapsed());
        assertFalse(new DDiagramElementQuery(dde3).isCollapsed());

        // Check the indirectly collapsed state : e2 is both way collapsed.
        assertFalse(new DDiagramElementQuery(dde1).isOnlyIndirectlyCollapsed());
        assertTrue(new DDiagramElementQuery(dde2).isOnlyIndirectlyCollapsed());
        assertTrue(new DDiagramElementQuery(dde3).isOnlyIndirectlyCollapsed());

        // Get the displayed figure dimension
        Dimension sizeE1 = getExecutionLogicalBounds(LIFELINE_B, 0).getSize();
        Dimension sizeE2 = getExecutionLogicalBounds(LIFELINE_B, 1).getSize();
        Dimension sizeS1 = getStateScreenBounds(s1).getSize();

        // Get the GMF size
        Size gmfSizeE1 = (Size) ((Node) e1.getNotationView()).getLayoutConstraint();
        Size gmfSizeE2 = (Size) ((Node) e2.getNotationView()).getLayoutConstraint();
        Size gmfSizeS1 = (Size) ((Node) s1.getNotationView()).getLayoutConstraint();

        // Get the flagged expanded sizes.
        CollapseFilter filterE1 = Iterables.filter(dde1.getGraphicalFilters(), CollapseFilter.class).iterator().next();
        Predicate<Object> dde2CollapseFilter = Predicates.and(Predicates.instanceOf(CollapseFilter.class), Predicates.not(Predicates.instanceOf(IndirectlyCollapseFilter.class)));
        CollapseFilter filterE2 = (CollapseFilter) Iterables.filter(dde2.getGraphicalFilters(), dde2CollapseFilter).iterator().next();
        IndirectlyCollapseFilter filterE2_indirect = Iterables.filter(dde2.getGraphicalFilters(), IndirectlyCollapseFilter.class).iterator().next();
        IndirectlyCollapseFilter filterS1 = Iterables.filter(dde3.getGraphicalFilters(), IndirectlyCollapseFilter.class).iterator().next();

        assertEquals(sizeE1.height, filterE1.getHeight());
        assertEquals(sizeE1.height, gmfSizeE1.getHeight());
        assertEquals(sizeE2.height, filterE2.getHeight());
        assertEquals(sizeE2.height, filterE2_indirect.getHeight());
        assertEquals(sizeE2.height, gmfSizeE2.getHeight());
        assertEquals(sizeS1.height, filterS1.getHeight());
        assertEquals(sizeS1.height, gmfSizeS1.getHeight());

        assertEquals(20, filterE1.getWidth());
        assertEquals(20, filterE2.getWidth());
        assertEquals(20, filterE2_indirect.getWidth());
        assertEquals(120, filterS1.getWidth());

        assertEquals(0, gmfSizeE1.getWidth());
        assertEquals(0, gmfSizeE2.getWidth());
        assertEquals(0, gmfSizeS1.getWidth());

        /*
         * Deactivate the collapse filter
         */
        changeCollapseFilterActivation();

        // Check the global collapsed state
        assertFalse(new DDiagramElementQuery(dde1).isIndirectlyCollapsed());
        assertFalse(new DDiagramElementQuery(dde2).isIndirectlyCollapsed());
        assertFalse(new DDiagramElementQuery(dde3).isIndirectlyCollapsed());

        // Check the directly collapsed state
        assertFalse(new DDiagramElementQuery(dde1).isCollapsed());
        assertFalse(new DDiagramElementQuery(dde2).isCollapsed());
        assertFalse(new DDiagramElementQuery(dde3).isCollapsed());

        // Check the indirectly collapsed state : e2 is both way collapsed.
        assertFalse(new DDiagramElementQuery(dde1).isOnlyIndirectlyCollapsed());
        assertFalse(new DDiagramElementQuery(dde2).isOnlyIndirectlyCollapsed());
        assertFalse(new DDiagramElementQuery(dde3).isOnlyIndirectlyCollapsed());

        // Get the displayed figure dimension
        Dimension expandedSizeE1 = getExecutionLogicalBounds(LIFELINE_B, 0).getSize();
        Dimension expandedSizeE2 = getExecutionLogicalBounds(LIFELINE_B, 1).getSize();
        Dimension expandedSizeS1 = getStateScreenBounds(s1).getSize();

        // Get the GMF size
        Size expandedGmfSizeE1 = (Size) ((Node) e1.getNotationView()).getLayoutConstraint();
        Size expandedGmfSizeE2 = (Size) ((Node) e2.getNotationView()).getLayoutConstraint();
        Size expandedGmfSizeS1 = (Size) ((Node) s1.getNotationView()).getLayoutConstraint();

        // Get the flagged expanded sizes.
        assertFalse(Iterables.any(dde1.getGraphicalFilters(), Predicates.instanceOf(CollapseFilter.class)));
        assertFalse(Iterables.any(dde2.getGraphicalFilters(), Predicates.instanceOf(CollapseFilter.class)));
        assertFalse(Iterables.any(dde3.getGraphicalFilters(), Predicates.instanceOf(CollapseFilter.class)));

        assertEquals(sizeE1.height, expandedSizeE1.height);
        assertEquals(sizeE1.height, expandedGmfSizeE1.getHeight());
        assertEquals(sizeE2.height, expandedSizeE2.height);
        assertEquals(sizeE2.height, expandedGmfSizeE2.getHeight());
        assertEquals(sizeS1.height, expandedSizeS1.height);
        assertEquals(sizeS1.height, expandedGmfSizeS1.getHeight());

        assertEquals(20, expandedSizeE1.width);
        assertEquals(20, expandedGmfSizeE1.getWidth());
        assertEquals(20, expandedSizeE2.width);
        assertEquals(20, expandedGmfSizeE2.getWidth());
        assertEquals(120, expandedSizeS1.width);
        assertEquals(120, expandedGmfSizeS1.getWidth());
    }

    private void moveExecution(String lifeline, int executionIndex, int y) {
        Rectangle execScreenBounds = getExecutionScreenBounds(lifeline, executionIndex);
        int deltaY = y - execScreenBounds.y;
        Point center = execScreenBounds.getCenter().getCopy();

        // Select the execution
        editor.click(center);
        bot.sleep(500);
        bot.waitUntil(new CheckSelectedCondition(editor, getExecutionEditPart(lifeline, executionIndex)));
        // Drag the top side by deltaY
        editor.drag(center, center.getCopy().getTranslated(0, deltaY));
    }

    private void resizeExecutionFromTop(String lifeline, int executionIndex, int deltaY) {
        Point executionScreenPosition = getExecutionScreenPosition(lifeline, executionIndex);
        Dimension executionDimension = getExecutionScreenDimension(lifeline, executionIndex);
        int center_x = executionScreenPosition.x + executionDimension.width / 2;
        int top_y = executionScreenPosition.y;
        // Select the execution
        editor.click(center_x, top_y + executionDimension.height / 2);
        bot.sleep(500);
        bot.waitUntil(new CheckSelectedCondition(editor, getExecutionEditPart(lifeline, executionIndex)));
        // Drag the top side by deltaY
        editor.drag(center_x, top_y, center_x, top_y + deltaY);
    }

    private void assertPositions(Map<EObject, Integer> expected, Map<EObject, Integer> actual) {
        assertEquals(expected.keySet(), actual.keySet());
        for (EObject element : expected.keySet()) {
            Integer expectedPosition = expected.get(element);
            assertEquals("Invalid position for semantic element " + element, expectedPosition, actual.get(element));
        }
    }

    private Map<EObject, Integer> readVerticalPositions(SequenceDiagram sequenceDiagram) {
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();
        VerticalPositionFunction vpf = new VerticalPositionFunction(sequenceDDiagram);
        Map<EObject, Integer> positions = new HashMap<>();
        for (EventEnd end : sequenceDDiagram.getGraphicalOrdering().getEventEnds()) {
            positions.put(end.getSemanticEnd(), (int) vpf.apply(end));
        }
        return positions;

    }

    private void validateMessagesOnEdgeOfExecution() {
        // Validate message vertical position
        Rectangle b0Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        assertNotNull(b0Bounds);
        Rectangle c0Bounds = getExecutionScreenBounds(LIFELINE_C, 0);
        assertNotNull(c0Bounds);
        Rectangle e1Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        assertNotNull(e1Bounds);

        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, b0Bounds.y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), b0Bounds.bottom());

        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C, c0Bounds.y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_C, 0), c0Bounds.bottom());

        assertMessageVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, e1Bounds.y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 1), e1Bounds.bottom());
    }

    private void validateMessagesOnEdgeOfExecution(double zoomAmount) {
        // Validate message vertical position
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y,
                getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE_SYNC_CALL), 2);
        assertEquals("The return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y
                + getExecutionScreenDimension(LIFELINE_B, 0).height, getReturnSyncCallScreenPosition(LIFELINE_B, 0), 2);
        assertEquals("The message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 0).y,
                getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C), 2);
        assertEquals("The return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 0).y
                + getExecutionScreenDimension(LIFELINE_C, 0).height, getReturnSyncCallScreenPosition(LIFELINE_C, 0), 2);
        assertEquals("The message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y,
                getSequenceMessageScreenVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B), 2);
        assertEquals("The return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y
                + getExecutionScreenDimension(LIFELINE_B, 1).height, getReturnSyncCallScreenPosition(LIFELINE_B, 1), 2);

    }

    private void validateGroupStability(int verticalBeginingPosition, int endsNumber, ArrayList<Integer> deltaClics) {
        validateGroupStability(verticalBeginingPosition, endsNumber, deltaClics, 0, false);
    }

    private void validateGroupStability(int verticalBeginingPosition, int endsNumber, ArrayList<Integer> deltaClics, int tolerance, boolean packed) {
        // Validate positions after creation

        int expectedY = verticalBeginingPosition + deltaClics.get(0);
        int expectedSize = LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT;

        // e1, e2, e3 positions
        assertExecutionVerticalPosition(LIFELINE_B, 0, expectedY, tolerance);
        assertExecutionVerticalPosition(LIFELINE_C, 0, expectedY += getGapBefore(packed, deltaClics, 1, 0), tolerance);
        assertExecutionVerticalPosition(NEW_LIFELINE, 0, expectedY += getGapBefore(packed, deltaClics, 2, 0), tolerance);

        // e3, e2 sizes
        assertEquals("The execution index 0 on lifeline " + NEW_LIFELINE + " has not the expected height", expectedSize, getExecutionScreenDimension(NEW_LIFELINE, 0).height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_C + " has not the expected height", expectedSize += packed ? LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * 2
                : getGapBefore(packed, deltaClics, 1, 0) + LayoutConstants.EXECUTION_CHILDREN_MARGIN, getExecutionScreenDimension(LIFELINE_C, 0).height);

        // e4
        int realGap = expectedSize - getGapBefore(packed, deltaClics, 2, 0) + (packed ? LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP : LayoutConstants.EXECUTION_CHILDREN_MARGIN);
        int delta = 0;
        if (!packed) {
            assertEquals(realGap, getGapBefore(packed, deltaClics, 3, 0), LayoutConstants.EXECUTION_CHILDREN_MARGIN);
            delta = realGap - getGapBefore(packed, deltaClics, 3, 0);
        } else {
            delta = 0;
        }

        assertExecutionVerticalPosition(LIFELINE_B, 1, expectedY += realGap, tolerance);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected height", packed ? LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT
                : LayoutConstants.DEFAULT_EXECUTION_HEIGHT, getExecutionScreenDimension(LIFELINE_B, 1).height);

        // e5, e6 positions
        realGap = packed ? LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP : LayoutConstants.DEFAULT_EXECUTION_HEIGHT
                + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        if (!packed) {
            assertEquals(realGap, getGapBefore(packed, deltaClics, 4, delta), LayoutConstants.EXECUTION_CHILDREN_MARGIN);
            delta = realGap - getGapBefore(packed, deltaClics, 4, delta);
        } else {
            delta = 0;
        }

        assertExecutionVerticalPosition(LIFELINE_B, 2, expectedY += realGap, tolerance);
        assertExecutionVerticalPosition(LIFELINE_C, 1, expectedY += getGapBefore(packed, deltaClics, 5, delta), tolerance);

        // e6, e5 sizes
        assertEquals("The execution index 1 on lifeline " + LIFELINE_C + " has not the expected height", LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT,
                getExecutionScreenDimension(LIFELINE_C, 1).height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected height", expectedSize, getExecutionScreenDimension(LIFELINE_B, 2).height);

        // e1 size
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", (expectedY += LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT + 2
                * (packed ? LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP : LayoutConstants.EXECUTION_CHILDREN_MARGIN))
                - verticalBeginingPosition, +getExecutionScreenDimension(LIFELINE_B, 0).height);

        // Validate message vertical position
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y,
                getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE_SYNC_CALL), 1);
        assertEquals("The return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y
                + getExecutionScreenDimension(LIFELINE_B, 0).height, getReturnSyncCallScreenPosition(LIFELINE_B, 0), 1);
        assertEquals("The message named " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 0).y,
                getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C), 1);
        assertEquals("The return message linked to " + THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_C + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 0).y
                + getExecutionScreenDimension(LIFELINE_C, 0).height, getReturnSyncCallScreenPosition(LIFELINE_C, 0), 1);
        assertEquals("The message named " + FIFTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected vertical position", getExecutionScreenPosition(NEW_LIFELINE, 0).y,
                getSequenceMessageScreenVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE), 1);
        assertEquals("The return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected vertical position", getExecutionScreenPosition(NEW_LIFELINE, 0).y
                + getExecutionScreenDimension(NEW_LIFELINE, 0).height, getReturnSyncCallScreenPosition(NEW_LIFELINE, 0), 1);
        assertEquals("The message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 1).y,
                getSequenceMessageScreenVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE), 1);
        assertEquals("The return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_C, 1).y
                + getExecutionScreenDimension(LIFELINE_C, 1).height, getReturnSyncCallScreenPosition(LIFELINE_C, 1), 1);

        validateOrdering(endsNumber);
    }

    private int getGapBefore(boolean packed, ArrayList<Integer> deltaClics, int i, int delta) {
        return Math.max(deltaClics.get(i) + delta, packed ? LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP : LayoutConstants.EXECUTION_CHILDREN_MARGIN);
    }

    private void assertExecutionVerticalPosition(String lifelineName, int index, int expected, int tolerance) {
        int actualPosition = getExecutionScreenPosition(lifelineName, index).y;
        String message = "The execution index " + index + " on lifeline " + lifelineName + " is not at the expected vertical position";
        MatcherAssert.assertThat(message, (double) actualPosition, Matchers.closeTo(expected, tolerance));
    }

    /**
     * Tests that when moving a reflective sync call, which surround other
     * events on its lifeline, we can not overlap its fixed branches.
     */
    public void test_sync_call_move_execution_with_reflective_branches_ends() {
        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Calculate the X position of the center of lifelines A and B
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;

        // m1 reflective sync call on B
        editor.activateTool("Sync Call");
        editor.click(lifelineBPosition, 130);
        editor.click(lifelineBPosition, 150);
        bot.waitUntil(new CheckNumberExecutionOnLifeline(LIFELINE_B, 1, editor));

        // m2 from A to B
        createMessage("Read", LIFELINE_A, 135, LIFELINE_B, 250);

        // m3 from A to B between reflective branches
        createMessage("Read", LIFELINE_A, 195, LIFELINE_B, 250);

        arrangeAll();

        int callMessageStartY = getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE);
        int returnMessageEndY = getReturnSyncCall(LIFELINE_B, 0).getISequenceEvent().getVerticalRange().getUpperBound();
        Rectangle execBounds = getExecutionScreenBounds(LIFELINE_B, 0);

        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(instanceRoleABot.part());
        Map<EObject, Integer> initialPositions = readVerticalPositions(sequenceDiagram);

        // Try to move e1 before its start message
        // This should be forbidden.
        moveExecution(LIFELINE_B, 0, callMessageStartY - 10);
        assertPositions(initialPositions, readVerticalPositions(sequenceDiagram));

        // Try to move e1 at the same position than its call message start
        // This should be forbidden.
        moveExecution(LIFELINE_B, 0, callMessageStartY);
        assertPositions(initialPositions, readVerticalPositions(sequenceDiagram));

        // Try to move e1 after its return message start
        // This should be forbidden.
        moveExecution(LIFELINE_B, 0, returnMessageEndY - execBounds.height + 10);
        assertPositions(initialPositions, readVerticalPositions(sequenceDiagram));

        // Try to move e1 at the same position than its return message end.
        // This should be forbidden.
        moveExecution(LIFELINE_B, 0, returnMessageEndY - execBounds.height);
        assertPositions(initialPositions, readVerticalPositions(sequenceDiagram));

        // Try to move e1
        ICondition done = new OperationDoneCondition();
        moveExecution(LIFELINE_B, 0, execBounds.y - 10);
        bot.waitUntil(done);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, execBounds.getCopy().getTranslated(0, -10));
        assertEquals("Start message should not move", callMessageStartY, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertEquals("End message should not move", returnMessageEndY, getReturnSyncCall(LIFELINE_B, 0).getISequenceEvent().getVerticalRange().getUpperBound());
    }

}
