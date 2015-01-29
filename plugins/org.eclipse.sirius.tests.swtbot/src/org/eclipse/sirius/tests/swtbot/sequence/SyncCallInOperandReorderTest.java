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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Tests reorder tool
 * 
 * @author smonnier
 */
public class SyncCallInOperandReorderTest extends AbstractDefaultModelSequenceTests {

    private static final String REP_1 = "SyncCallReorderMessageBeforeExecution";

    private static final String REP_2 = "SyncCallReorderMessageAfterExecution";

    private static final String REP_3 = "ReflexifSyncCallReorder";

    private static final String MODEL = "My.interactions";

    private static final String SESSION_FILE = "My.aird";

    private static final String DATA_UNIT_DIR = "data/unit/sequence/reorder/VP-4473/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Sync_Call_Reorder_Message_Before_Execution() throws Exception {
        moveCombinedFragment(REP_1);
    }

    /**
     * @param rep1
     */
    private void moveCombinedFragment(String representationName) {
        prepareEditorForTest(representationName);

        SWTBotGefEditPart sequenceDiagramBot = editor.mainEditPart();

        SWTBotGefEditPart combinedFragmentBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0);
        Rectangle combinedFragmentBounds = editor.getBounds(combinedFragmentBot);

        List<SWTBotGefEditPart> execs = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class));
        Collections.sort(execs, new Comparator<SWTBotGefEditPart>() {
            public int compare(SWTBotGefEditPart o1, SWTBotGefEditPart o2) {
                return editor.getBounds(o1).y - editor.getBounds(o2).y;
            }

            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        });

        SWTBotGefEditPart syncCallExecBot = execs.get(0);
        Rectangle syncCallExecBounds = editor.getBounds(syncCallExecBot);

        SWTBotGefEditPart execBot = execs.get(1);
        Rectangle execBounds = editor.getBounds(execBot);

        int firstMessageStartY = getSequenceMessageFirstBendpointScreenPosition(getSequenceMessageEditPart(FIRST_MESSAGE)).y;
        int returnMessageEndY = getSequenceMessageLastBendpointScreenPosition(getSequenceMessageEditPart(RETURN_MESSAGE)).y;

        int dy = execBounds.getCenter().y - combinedFragmentBounds.y;
        ICondition done = new OperationDoneCondition();
        editor.drag(combinedFragmentBot, combinedFragmentBounds.getCenter().x, combinedFragmentBounds.y + dy);
        bot.waitUntil(done);

        // A reparent might occurs for sync call (common test on several use
        // case)
        syncCallExecBot = Iterables.filter(sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)), Predicates.not(Predicates.equalTo(execBot))).iterator().next();
        // No x test, all tests data will not produce a reparent.
        Rectangle expectedSyncCallExecBounds = syncCallExecBounds.getTranslated(0, dy);

        assertEquals("Combined Fragment insertion should not move the execution.", execBounds.y, editor.getBounds(execBot).y);
        assertEquals("Combined fragment should starts from the drop location.", execBounds.getCenter().y, editor.getBounds(combinedFragmentBot).y);
        assertEquals("Combined fragment height should not change.", combinedFragmentBounds.height, editor.getBounds(combinedFragmentBot).height);
        assertEquals("Auto expand should resize the execution to allow the combined fragment insertion.", execBounds.height + combinedFragmentBounds.height, editor.getBounds(execBot).height);

        assertExecutionHasValidLogicalBounds((ExecutionEditPart) execBot.part(), execBounds.getResized(0, combinedFragmentBounds.height), false);
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) syncCallExecBot.part(), expectedSyncCallExecBounds, false);
        Rectangle rectangle = new Rectangle(0, firstMessageStartY + dy, 0, syncCallExecBounds.y - firstMessageStartY);
        assertMessageHasValidScreenBounds(getSequenceMessageEditPart(FIRST_MESSAGE), rectangle, true, false);
        Rectangle rectangle2 = new Rectangle(0, expectedSyncCallExecBounds.bottom(), 0, returnMessageEndY - syncCallExecBounds.bottom());
        assertMessageHasValidScreenBounds(getSequenceMessageEditPart(RETURN_MESSAGE), rectangle2, true, false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Sync_Call_Reorder_Message_After_Execution() throws Exception {
        moveCombinedFragment(REP_2);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Reflexif_Sync_Call_Reorder() throws Exception {
        moveCombinedFragment(REP_3);
    }

    /**
     * @param rep1
     */
    private void prepareEditorForTest(String repName) {
        editor = openDiagram(localSession.getOpenedSession(), getRepresentationId(), repName, DDiagram.class);

        editor.mainEditPart().part().getViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, Boolean.FALSE);
        editor.mainEditPart().part().getViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, Boolean.FALSE);

        maximizeEditor(editor);
        assertFalse("Session should be SYNC after diagram opening.", localSession.isDirty());
    }
}
