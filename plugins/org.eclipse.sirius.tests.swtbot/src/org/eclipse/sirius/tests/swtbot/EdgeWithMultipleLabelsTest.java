/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES.
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

import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEdgeLabelVisibility;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.swt.SWTException;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.Assert;

import junit.framework.AssertionFailedError;

/**
 * Tests for edges with multiple labels.
 * 
 * @author smonnier
 */
@SuppressWarnings("restriction")
public class EdgeWithMultipleLabelsTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String MODEL_FILE = "doremi_1551.ecore";

    private static final String SESSION_FILE = "doremi_1551.aird";

    private static final String VSM_FILE = "doremi_1551.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeWithMultipleLabels/";

    private static final String REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME = "doremi_1551 Relation Based Straight";

    private static final String REPRESENTATION_DESC_1551_EB_STRAIGHT_NAME = "doremi_1551 Element Based Straight";

    private static final String REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME = "doremi_1551 Relation Based Manhattan";

    private static final String REPRESENTATION_DESC_1551_EB_MANHATTAN_NAME = "doremi_1551 Element Based Manhattan";

    private static final String REPRESENTATION_DESC_1551_RB_TREE_NAME = "doremi_1551 Relation Based Tree";

    private static final String REPRESENTATION_DESC_1551_EB_TREE_NAME = "doremi_1551 Element Based Tree";

    private static final String EDGE_RB_CREATION_TOOL_NAME = "Create EReference RB";

    private static final String EDGE_EB_CREATION_TOOL_NAME = "Create EReference EB";

    private SWTBotSiriusDiagramEditor editor;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private ILogListener listener;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

    }

    private void openDiagram(String name) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), name, name, DDiagram.class);
    }

    private boolean error;

    private synchronized boolean doesASpecificErrorOccurs() {
        return error;
    }

    private synchronized void errorOccurs() {
        error = true;
    }

    /**
     * 
     * Test to create a relation based edge and validated that there are multiple labels.
     * 
     */
    public void testCreateRelationBasedEdgeStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        validateCreateRelationBasedEdge();
    }

    /**
     * 
     * Test to create a relation based edge and validated that there are multiple labels.
     * 
     */
    public void testCreateRelationBasedEdgeManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        validateCreateRelationBasedEdge();
    }

    /**
     * 
     * Test to create a relation based edge and validated that there are multiple labels.
     * 
     */
    public void testCreateRelationBasedEdgeTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        validateCreateRelationBasedEdge();
    }

    private void validateCreateRelationBasedEdge() {
        addWarningListener();

        // Creates the edge
        createReference("EC2", DNodeEditPart.class, "EC4", DNodeEditPart.class, EDGE_RB_CREATION_TOOL_NAME);

        getSingleDEdgeTo("EC4");

        // Checks multiple label existence
        Assert.assertNotNull(editor.getEditPart("EC2 begin"));
        Assert.assertNotNull(editor.getEditPart("EC2 center"));
        Assert.assertNotNull(editor.getEditPart("EC2 end"));

        // Validates undo

        undo(EDGE_RB_CREATION_TOOL_NAME);

        try {
            getSingleDEdgeTo("EC4");
            fail("The edge creation should have been undone");
        } catch (AssertionFailedError e) {
            // Nothing, it has to fail in the try block
        }

        redo(EDGE_RB_CREATION_TOOL_NAME);

        getSingleDEdgeTo("EC4");

        // Checks multiple label existence
        Assert.assertNotNull(editor.getEditPart("EC2 begin"));
        Assert.assertNotNull(editor.getEditPart("EC2 center"));
        Assert.assertNotNull(editor.getEditPart("EC2 end"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to create an element based edge and validated that there are multiple labels.
     * 
     */
    public void testCreateElementBasedEdgeStraight() {
        openDiagram(REPRESENTATION_DESC_1551_EB_STRAIGHT_NAME);

        validateCreateElementBasedEdge();
    }

    /**
     * 
     * Test to create an element based edge and validated that there are multiple labels.
     * 
     */
    public void testCreateElementBasedEdgeManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_EB_MANHATTAN_NAME);

        validateCreateElementBasedEdge();
    }

    /**
     * 
     * Test to create an element based edge and validated that there are multiple labels.
     * 
     */
    public void testCreateElementBasedEdgeTree() {
        openDiagram(REPRESENTATION_DESC_1551_EB_TREE_NAME);

        validateCreateElementBasedEdge();
    }

    private void validateCreateElementBasedEdge() {
        addWarningListener();

        // Create the edge
        createReference("EC1", DNodeEditPart.class, "EC3", DNodeEditPart.class, EDGE_EB_CREATION_TOOL_NAME);

        getSingleDEdgeTo("EC3");

        // Checks multiple label existence
        Assert.assertNotNull(editor.getEditPart("newEReference2 begin"));
        Assert.assertNotNull(editor.getEditPart("newEReference2 center"));
        Assert.assertNotNull(editor.getEditPart("newEReference2 end"));

        // Validates undo

        undo(EDGE_EB_CREATION_TOOL_NAME);

        try {
            getSingleDEdgeTo("EC3");
            fail("The edge creation should have been undone");
        } catch (AssertionFailedError e) {
            // Nothing, it has to fail in the try block
        }

        redo(EDGE_EB_CREATION_TOOL_NAME);

        getSingleDEdgeTo("EC3");

        // Checks multiple label existence
        Assert.assertNotNull(editor.getEditPart("newEReference2 begin"));
        Assert.assertNotNull(editor.getEditPart("newEReference2 center"));
        Assert.assertNotNull(editor.getEditPart("newEReference2 end"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to delete a relation based edge and validated that there are multiple labels.
     * 
     */
    public void testDeleteRelationBasedEdgeStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        validateDeleteRelationBasedEdge();
    }

    /**
     * 
     * Test to delete a relation based edge and validated that there are multiple labels.
     * 
     */
    public void testDeleteRelationBasedEdgeManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        validateDeleteRelationBasedEdge();
    }

    /**
     * 
     * Test to delete a relation based edge and validated that there are multiple labels.
     * 
     */
    public void testDeleteRelationBasedEdgeTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        validateDeleteRelationBasedEdge();
    }

    private void validateDeleteRelationBasedEdge() {
        addWarningListener();

        editor.select(getSingleDEdgeFrom("EC1"));

        deleteSelectedElement();

        try {
            getSingleDEdgeFrom("EC1");
            fail("No edge should be found after beeing deleted");
        } catch (AssertionFailedError e) {
            // Nothing, it has to fail in the try block
        }

        // Validates undo

        undo("Delete");

        getSingleDEdgeFrom("EC1");

        // Checks multiple label existence
        Assert.assertNotNull(editor.getEditPart("EC1 begin"));
        Assert.assertNotNull(editor.getEditPart("EC1 center"));
        Assert.assertNotNull(editor.getEditPart("EC1 end"));

        redo("Delete");

        try {
            getSingleDEdgeFrom("EC1");
            fail("No edge should be found after beeing deleted");
        } catch (AssertionFailedError e) {
            // Nothing, it has to fail in the try block
        }

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to delete an element based edge and validated that there are multiple labels.
     * 
     */
    public void testDeleteElementBasedEdgeStraight() {
        openDiagram(REPRESENTATION_DESC_1551_EB_STRAIGHT_NAME);

        validateDeleteElementBasedEdge();
    }

    /**
     * 
     * Test to delete an element based edge and validated that there are multiple labels.
     * 
     */
    public void testDeleteElementBasedEdgeManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_EB_MANHATTAN_NAME);

        validateDeleteElementBasedEdge();
    }

    /**
     * 
     * Test to delete an element based edge and validated that there are multiple labels.
     * 
     */
    public void testDeleteElementBasedEdgeTree() {
        openDiagram(REPRESENTATION_DESC_1551_EB_TREE_NAME);

        validateDeleteElementBasedEdge();
    }

    private void validateDeleteElementBasedEdge() {
        addWarningListener();

        editor.select(getSingleDEdgeFrom("EC1"));

        deleteSelectedElement();

        try {
            getSingleDEdgeFrom("EC1");
            fail("No edge should be found after beeing deleted");
        } catch (AssertionFailedError e) {
            // Nothing, it has to fail in the try block
        }

        // Validates undo

        undo("Delete");

        getSingleDEdgeFrom("EC1");

        // Checks multiple label existence
        Assert.assertNotNull(editor.getEditPart("ec2 begin"));
        Assert.assertNotNull(editor.getEditPart("ec2 center"));
        Assert.assertNotNull(editor.getEditPart("ec2 end"));

        redo("Delete");

        try {
            getSingleDEdgeFrom("EC1");
            fail("No edge should be found after beeing deleted");
        } catch (AssertionFailedError e) {
            // Nothing, it has to fail in the try block
        }

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to hide and show a relation based edge labels.
     * 
     */
    public void testShowHideRelationBasedEdgeLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        validateShowHideRelationBasedEdgeLabel();
    }

    /**
     * 
     * Test to hide and show a relation based edge labels.
     * 
     */
    public void testShowHideRelationBasedEdgeLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        validateShowHideRelationBasedEdgeLabel();
    }

    /**
     * 
     * Test to hide and show a relation based edge labels.
     * 
     */
    public void testShowHideRelationBasedEdgeLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        validateShowHideRelationBasedEdgeLabel();
    }

    private void validateShowHideRelationBasedEdgeLabel() {
        addWarningListener();

        // Hide the label
        editor.bot().toolbarButtonWithTooltip("Show/Hide").click();
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Diagram elements visibility");
        wizardBot.text().setText("*label");
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 begin label").select().toggleCheck();
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 center label").select().toggleCheck();
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 end label").select().toggleCheck();

        wizardBot.button("OK").click();

        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 begin", false));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 center", false));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 end", false));

        // Show the label
        editor.bot().toolbarButtonWithTooltip("Show/Hide").click();

        wizardBot = SWTBotSiriusHelper.getShellBot("Diagram elements visibility");
        wizardBot.text().setText("*label");
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 begin label").select().toggleCheck();
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 center label").select().toggleCheck();
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 end label").select().toggleCheck();

        wizardBot.button("OK").click();

        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 begin", true));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 center", true));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 end", true));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to hide and show an element based edge labels.
     * 
     */
    public void testShowHideElementBasedEdgeLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_EB_STRAIGHT_NAME);

        validateShowHideElementBasedEdgeLabel();
    }

    /**
     * 
     * Test to hide and show an element based edge labels.
     * 
     */
    public void testShowHideElementBasedEdgeLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_EB_MANHATTAN_NAME);

        validateShowHideElementBasedEdgeLabel();
    }

    /**
     * 
     * Test to hide and show an element based edge labels.
     * 
     */
    public void testShowHideElementBasedEdgeLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_EB_TREE_NAME);

        validateShowHideElementBasedEdgeLabel();
    }

    private void validateShowHideElementBasedEdgeLabel() {
        addWarningListener();

        // Hide the label
        editor.bot().toolbarButtonWithTooltip("Show/Hide").click();
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Diagram elements visibility");
        wizardBot.text().setText("*label");
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 begin label").select().toggleCheck();
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 center label").select().toggleCheck();
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 end label").select().toggleCheck();

        wizardBot.button("OK").click();

        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 begin", false));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 center", false));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 end", false));

        // Show the label
        editor.bot().toolbarButtonWithTooltip("Show/Hide").click();
        wizardBot = SWTBotSiriusHelper.getShellBot("Diagram elements visibility");
        wizardBot.text().setText("*label");
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 begin label").select().toggleCheck();
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 center label").select().toggleCheck();
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 end label").select().toggleCheck();

        wizardBot.button("OK").click();

        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 begin", true));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 center", true));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 end", true));

        removeWarningListenerAndCheckWarning();
    }

    // ////////////////////////////////////////////////////////////////////////////
    // Version where the 3 labels are displayed in the Show/Hide dialog allowing
    // to hide label individually

    /**
     * 
     * Test to hide and show a relation based edge labels.
     * 
     */
    public void _testShowHideRelationBasedEdge3LabelsStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        validateShowHideRelationBasedEdge3Labels();
    }

    /**
     * 
     * Test to hide and show a relation based edge labels.
     * 
     */
    public void _testShowHideRelationBasedEdge3LabelsManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        validateShowHideRelationBasedEdge3Labels();
    }

    /**
     * 
     * Test to hide and show a relation based edge labels.
     * 
     */
    public void _testShowHideRelationBasedEdge3LabelsTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        validateShowHideRelationBasedEdge3Labels();
    }

    private void validateShowHideRelationBasedEdge3Labels() {
        addWarningListener();

        // Hide the label begin and center
        editor.bot().toolbarButtonWithTooltip("Show/Hide").click();
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Diagram elements visibility");
        wizardBot.text().setText("*label");
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 begin label").select().toggleCheck();
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 center label").select().toggleCheck();
        // bot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 end
        // label").select().toggleCheck();

        wizardBot.button("OK").click();

        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 begin", false));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 center", false));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 end", true));

        // Toggle visibility of all labels
        editor.bot().toolbarButtonWithTooltip("Show/Hide").click();
        wizardBot = SWTBotSiriusHelper.getShellBot("Diagram elements visibility");
        wizardBot.text().setText("*label");
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 begin label").select().toggleCheck();
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 center label").select().toggleCheck();
        wizardBot.tree().getTreeItem("EC1 -> EC3").getNode("EC1 end label").select().toggleCheck();

        wizardBot.button("OK").click();

        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 begin", true));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 center", true));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "EC1 end", false));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to hide and show an element based edge labels.
     * 
     */
    public void _testShowHideElementBasedEdge3LabelsStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        validateShowHideElementBasedEdge3Labels();
    }

    /**
     * 
     * Test to hide and show an element based edge labels.
     * 
     */
    public void _testShowHideElementBasedEdge3LabelsManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        validateShowHideElementBasedEdge3Labels();
    }

    /**
     * 
     * Test to hide and show an element based edge labels.
     * 
     */
    public void _testShowHideElementBasedEdge3LabelsTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        validateShowHideElementBasedEdge3Labels();
    }

    private void validateShowHideElementBasedEdge3Labels() {
        addWarningListener();

        // Hide the label begin and center
        editor.bot().toolbarButtonWithTooltip("Show/Hide").click();
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Diagram elements visibility");
        wizardBot.text().setText("*label");
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 begin label").select().toggleCheck();
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 center label").select().toggleCheck();
        // bot.tree().getTreeItem("ec2 : EC2").getNode("ec2 end
        // label").select().toggleCheck();

        wizardBot.button("OK").click();

        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 begin", false));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 center", false));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 end", true));

        // Toggle visibility of all labels
        editor.bot().toolbarButtonWithTooltip("Show/Hide").click();
        wizardBot = SWTBotSiriusHelper.getShellBot("Diagram elements visibility");
        wizardBot.text().setText("*label");
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 begin label").select().toggleCheck();
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 center label").select().toggleCheck();
        wizardBot.tree().getTreeItem("ec2 : EC2").getNode("ec2 end label").select().toggleCheck();

        wizardBot.button("OK").click();

        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 begin", true));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 center", true));
        bot.waitUntil(new CheckEdgeLabelVisibility(editor, "ec2 end", false));

        removeWarningListenerAndCheckWarning();
    }

    // ////////////////////////////////////////////////////////////////////////////

    /**
     * 
     * Test to select a relation based edge begin label.
     * 
     */
    public void testSelectRelationBasedEdgeBeginLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        validateSelectRelationBasedEdgeBeginLabel();
    }

    /**
     * 
     * Test to select a relation based edge begin label.
     * 
     */
    public void testSelectRelationBasedEdgeBeginLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        validateSelectRelationBasedEdgeBeginLabel();
    }

    /**
     * 
     * Test to select a relation based edge begin label.
     * 
     */
    public void testSelectRelationBasedEdgeBeginLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        validateSelectRelationBasedEdgeBeginLabel();
    }

    private void validateSelectRelationBasedEdgeBeginLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("EC1 begin"));
        validateSelectedLabel("EC1 begin", DEdgeBeginNameEditPart.class);
        editor.select(editor.getSelectableEditPart("EC2"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to select a relation based edge center label.
     * 
     */
    public void testSelectRelationBasedEdgeCenterLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        validateSelectRelationBasedEdgeCenterLabel();
    }

    /**
     * 
     * Test to select a relation based edge center label.
     * 
     */
    public void testSelectRelationBasedEdgeCenterLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        validateSelectRelationBasedEdgeCenterLabel();
    }

    /**
     * 
     * Test to select a relation based edge center label.
     * 
     */
    public void testSelectRelationBasedEdgeCenterLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        validateSelectRelationBasedEdgeCenterLabel();
    }

    private void validateSelectRelationBasedEdgeCenterLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("EC1 center"));
        validateSelectedLabel("EC1 center", DEdgeNameEditPart.class);
        editor.select(editor.getSelectableEditPart("EC2"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to select a relation based edge end label.
     * 
     */
    public void testSelectRelationBasedEdgeEndLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        validateSelectRelationBasedEdgeEndLabel();
    }

    /**
     * 
     * Test to select a relation based edge end label.
     * 
     */
    public void testSelectRelationBasedEdgeEndLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        validateSelectRelationBasedEdgeEndLabel();
    }

    /**
     * 
     * Test to select a relation based edge end label.
     * 
     */
    public void testSelectRelationBasedEdgeEndLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        validateSelectRelationBasedEdgeEndLabel();
    }

    private void validateSelectRelationBasedEdgeEndLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("EC1 end"));
        validateSelectedLabel("EC1 end", DEdgeEndNameEditPart.class);
        editor.select(editor.getSelectableEditPart("EC2"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to select an element based edge begin label.
     * 
     */
    public void testSelectElementBasedEdgeBeginLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_EB_STRAIGHT_NAME);

        validateSelectElementBasedEdgeBeginLabel();
    }

    /**
     * 
     * Test to select an element based edge begin label.
     * 
     */
    public void testSelectElementBasedEdgeBeginLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_EB_MANHATTAN_NAME);

        validateSelectElementBasedEdgeBeginLabel();
    }

    /**
     * 
     * Test to select an element based edge begin label.
     * 
     */
    public void testSelectElementBasedEdgeBeginLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_EB_TREE_NAME);

        validateSelectElementBasedEdgeBeginLabel();
    }

    private void validateSelectElementBasedEdgeBeginLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("ec2 begin"));
        validateSelectedLabel("ec2 begin", DEdgeBeginNameEditPart.class);
        editor.select(editor.getSelectableEditPart("EC2"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to select an element based edge center label.
     * 
     */
    public void testSelectElementBasedEdgeCenterLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_EB_STRAIGHT_NAME);

        validateSelectElementBasedEdgeCenterLabel();
    }

    /**
     * 
     * Test to select an element based edge center label.
     * 
     */
    public void testSelectElementBasedEdgeCenterLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_EB_MANHATTAN_NAME);

        validateSelectElementBasedEdgeCenterLabel();
    }

    /**
     * 
     * Test to select an element based edge center label.
     * 
     */
    public void testSelectElementBasedEdgeCenterLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_EB_TREE_NAME);

        validateSelectElementBasedEdgeCenterLabel();
    }

    private void validateSelectElementBasedEdgeCenterLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("ec2 center"));
        validateSelectedLabel("ec2 center", DEdgeNameEditPart.class);
        editor.select(editor.getSelectableEditPart("EC2"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test to select an element based edge end label.
     * 
     */
    public void testSelectElementBasedEdgeEndLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_EB_STRAIGHT_NAME);

        validateSelectElementBasedEdgeEndLabel();
    }

    /**
     * 
     * Test to select an element based edge end label.
     * 
     */
    public void testSelectElementBasedEdgeEndLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_EB_MANHATTAN_NAME);

        validateSelectElementBasedEdgeEndLabel();
    }

    /**
     * 
     * Test to select an element based edge end label.
     * 
     */
    public void testSelectElementBasedEdgeEndLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_EB_TREE_NAME);

        validateSelectElementBasedEdgeEndLabel();
    }

    private void validateSelectElementBasedEdgeEndLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("ec2 end"));
        validateSelectedLabel("ec2 end", DEdgeEndNameEditPart.class);
        editor.select(editor.getSelectableEditPart("EC2"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test direct edit on a relation based edge begin label.
     * 
     */
    public void testDirectEditRelationBasedEdgeBeginLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditRelationBasedEdgeBeginLabel();
        setErrorCatchActive(true);
    }

    /**
     * 
     * Test direct edit on a relation based edge begin label.
     * 
     */
    public void testDirectEditRelationBasedEdgeBeginLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditRelationBasedEdgeBeginLabel();
        setErrorCatchActive(true);
    }

    /**
     * 
     * Test direct edit on a relation based edge begin label.
     * 
     */
    public void testDirectEditRelationBasedEdgeBeginLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditRelationBasedEdgeBeginLabel();
        setErrorCatchActive(true);
    }

    /**
     * The direct edit on begin label is not allowed, so check that it fails. The default SWTBot timeOut is reduced to
     * consume less time.
     */
    private void validateDirectEditRelationBasedEdgeBeginLabel() {

        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("EC1 begin"));
        boolean directEdit = true;
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            directEdit = editor.directEdgeEditTypeBeginLabel("EC1", "EC3", "EClass1");
        } catch (SWTException e) {
            directEdit = false;
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
            Assert.assertFalse("The direct edit is only available on center label.", directEdit);
        }
        validateSelectedLabel("EC1 begin", DEdgeBeginNameEditPart.class);
        Assert.assertNotNull(editor.getEditPart("EC1 center"));
        Assert.assertNotNull(editor.getEditPart("EC1 end"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test direct edit on a relation based edge begin label.
     * 
     */
    public void testDirectEditRelationBasedEdgeCenterLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        validateDirectEditRelationBasedEdgeCenterLabel();
    }

    /**
     * 
     * Test direct edit on a relation based edge begin label.
     * 
     */
    public void testDirectEditRelationBasedEdgeCenterLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        validateDirectEditRelationBasedEdgeCenterLabel();
    }

    /**
     * 
     * Test direct edit on a relation based edge begin label.
     * 
     */
    public void testDirectEditRelationBasedEdgeCenterLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        validateDirectEditRelationBasedEdgeCenterLabel();
    }

    private void validateDirectEditRelationBasedEdgeCenterLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("EC1 center"));
        editor.directEdgeEditTypeCenterLabel("EC1", "EC3", "EClass1");
        Assert.assertNotNull(editor.getEditPart("EClass1 begin"));
        validateSelectedLabel("EClass1 center", DEdgeNameEditPart.class);
        Assert.assertNotNull(editor.getEditPart("EClass1 end"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test direct edit on a relation based edge begin label.
     * 
     */
    public void testDirectEditRelationBasedEdgeEndLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_RB_STRAIGHT_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditRelationBasedEdgeEndLabel();
        setErrorCatchActive(true);
    }

    /**
     * 
     * Test direct edit on a relation based edge begin label.
     * 
     */
    public void testDirectEditRelationBasedEdgeEndLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_RB_MANHATTAN_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditRelationBasedEdgeEndLabel();
        setErrorCatchActive(true);
    }

    /**
     * 
     * Test direct edit on a relation based edge begin label.
     * 
     */
    public void testDirectEditRelationBasedEdgeEndLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_RB_TREE_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditRelationBasedEdgeEndLabel();
        setErrorCatchActive(true);
    }

    private void validateDirectEditRelationBasedEdgeEndLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("EC1 end"));
        boolean directEdit = true;
        try {
            directEdit = editor.directEdgeEditTypeEndLabel("EC1", "EC3", "EClass1");
        } catch (SWTException e) {
            directEdit = false;
        } finally {
            Assert.assertFalse("The direct edit is only available on center label.", directEdit);
        }
        Assert.assertNotNull(editor.getEditPart("EC1 begin"));
        Assert.assertNotNull(editor.getEditPart("EC1 center"));
        validateSelectedLabel("EC1 end", DEdgeEndNameEditPart.class);

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test direct edit on an element based edge begin label.
     * 
     */
    public void testDirectEditElementBasedEdgeBeginLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_EB_STRAIGHT_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditElementBasedEdgeBeginLabel();
        setErrorCatchActive(true);
    }

    /**
     * 
     * Test direct edit on an element based edge begin label.
     * 
     */
    public void testDirectEditElementBasedEdgeBeginLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_EB_MANHATTAN_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditElementBasedEdgeBeginLabel();
        setErrorCatchActive(true);
    }

    /**
     * 
     * Test direct edit on an element based edge begin label.
     * 
     */
    public void testDirectEditElementBasedEdgeBeginLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_EB_TREE_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditElementBasedEdgeBeginLabel();
        setErrorCatchActive(true);
    }

    private void validateDirectEditElementBasedEdgeBeginLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("ec2 begin"));
        boolean directEdit = true;
        try {
            directEdit = editor.directEdgeEditTypeBeginLabel("EC1", "EC2", "eclass2");
        } catch (SWTException e) {
            directEdit = false;
        } finally {
            Assert.assertFalse("The direct edit is only available on center label.", directEdit);
        }
        validateSelectedLabel("ec2 begin", DEdgeBeginNameEditPart.class);
        Assert.assertNotNull(editor.getEditPart("ec2 center"));
        Assert.assertNotNull(editor.getEditPart("ec2 end"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test direct edit on an element based edge center label.
     * 
     */
    public void testDirectEditElementBasedEdgeCenterLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_EB_STRAIGHT_NAME);

        validateDirectEditElementBasedEdgeCenterLabel();
    }

    /**
     * 
     * Test direct edit on an element based edge center label.
     * 
     */
    public void testDirectEditElementBasedEdgeCenterLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_EB_MANHATTAN_NAME);

        validateDirectEditElementBasedEdgeCenterLabel();
    }

    /**
     * 
     * Test direct edit on an element based edge center label.
     * 
     */
    public void testDirectEditElementBasedEdgeCenterLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_EB_TREE_NAME);

        validateDirectEditElementBasedEdgeCenterLabel();
    }

    private void validateDirectEditElementBasedEdgeCenterLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("ec2 center"));
        editor.directEdgeEditTypeCenterLabel("EC1", "EC2", "eclass2");
        Assert.assertNotNull(editor.getEditPart("eclass2 begin"));
        validateSelectedLabel("eclass2 center", DEdgeNameEditPart.class);
        Assert.assertNotNull(editor.getEditPart("eclass2 end"));

        removeWarningListenerAndCheckWarning();
    }

    /**
     * 
     * Test direct edit on an element based edge end label.
     * 
     */
    public void testDirectEditElementBasedEdgeEndLabelStraight() {
        openDiagram(REPRESENTATION_DESC_1551_EB_STRAIGHT_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditElementBasedEdgeEndLabel();
        setErrorCatchActive(true);
    }

    /**
     * 
     * Test direct edit on an element based edge end label.
     * 
     */
    public void testDirectEditElementBasedEdgeEndLabelManhattan() {
        openDiagram(REPRESENTATION_DESC_1551_EB_MANHATTAN_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditElementBasedEdgeEndLabel();
        setErrorCatchActive(true);
    }

    /**
     * 
     * Test direct edit on an element based edge end label.
     * 
     */
    public void testDirectEditElementBasedEdgeEndLabelTree() {
        openDiagram(REPRESENTATION_DESC_1551_EB_TREE_NAME);

        // Disables the "Error Log" view because if the test is ok we have an
        // error
        setErrorCatchActive(false);
        validateDirectEditElementBasedEdgeEndLabel();
        setErrorCatchActive(true);
    }

    /**
     * The direct edit on end label is not allowed, so check that it fails. The default SWTBot timeOut is reduced to
     * consume less time.
     */
    private void validateDirectEditElementBasedEdgeEndLabel() {
        addWarningListener();

        // Select the begin label and focus something else
        editor.select(editor.getEditPart("ec2 end"));
        boolean directEdit = true;
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            directEdit = editor.directEdgeEditTypeEndLabel("EC1", "EC2", "eclass2");
        } catch (SWTException e) {
            directEdit = false;
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
            Assert.assertFalse("The direct edit is only available on center label.", directEdit);
        }
        Assert.assertNotNull(editor.getEditPart("ec2 begin"));
        Assert.assertNotNull(editor.getEditPart("ec2 center"));
        validateSelectedLabel("ec2 end", DEdgeEndNameEditPart.class);

        removeWarningListenerAndCheckWarning();
    }

    private void validateSelectedLabel(String label, Class<? extends EditPart> labelClass) {
        StructuredSelection selection = (StructuredSelection) editor.getSelection();
        Assert.assertEquals(1, selection.size());
        Assert.assertTrue(labelClass.isInstance(selection.getFirstElement()));
        Assert.assertEquals(label, ((AbstractDEdgeNameEditPart) selection.getFirstElement()).getEditText());
    }

    private void removeWarningListenerAndCheckWarning() {
        // Remove the log listener
        InternalPlatform.getDefault().removeLogListener(listener);

        // Check if there is no warning message
        if (doesASpecificErrorOccurs())
            fail("A warning concerning the MapModeUtil was log during this test.");
    }

    private void addWarningListener() {
        listener = new ILogListener() {

            @Override
            public void logging(IStatus status, String plugin) {
                if (status.getSeverity() == IStatus.WARNING && status.getMessage().indexOf("MapModeUtil") != -1)
                    errorOccurs();
            }
        };
        // Add a log listener to catch an eventual warning message during the
        // edge creation
        InternalPlatform.getDefault().addLogListener(listener);
    }

    private void createReference(String sourceName, Class<? extends EditPart> expectedSourceClass, String targetName, Class<? extends EditPart> expectedTargetClass, String edgeCreationToolName) {
        SWTBotGefEditPart source = editor.getEditPart(sourceName, expectedSourceClass);
        SWTBotGefEditPart target = editor.getEditPart(targetName, expectedTargetClass);
        Rectangle sourceBounds = editor.getBounds(source);
        Rectangle targetBounds = editor.getBounds(target);
        createReference((IGraphicalEditPart) source.part(), sourceBounds.getCenter(), (IGraphicalEditPart) target.part(), targetBounds.getCenter(), edgeCreationToolName);
    }

    private void createReference(IGraphicalEditPart source, Point sourcePosition, IGraphicalEditPart target, Point targetPosition, String edgeCreationToolName) {

        editor.activateTool(edgeCreationToolName);
        editor.click(sourcePosition);
        editor.click(targetPosition);
    }

    private SWTBotGefConnectionEditPart getSingleDEdgeFrom(String sourceName) {
        SWTBotGefEditPart sourcePart = editor.getEditPart(sourceName).parent();
        assertEquals("Bad number of edge", 1, sourcePart.sourceConnections().size());
        SWTBotGefConnectionEditPart edge = sourcePart.sourceConnections().get(0);
        return edge;
    }

    private DEdgeEditPart getSingleDEdgeTo(String targetName) {
        SWTBotGefEditPart targetPart = editor.getEditPart(targetName).parent();
        assertEquals("Bad number of edge", 1, targetPart.targetConnections().size());
        SWTBotGefConnectionEditPart edge = targetPart.targetConnections().get(0);
        assertTrue(edge.part() instanceof DEdgeEditPart);
        return (DEdgeEditPart) edge.part();
    }

    /**
     * Delete the selected element if possible.
     */
    @Override
    protected void deleteSelectedElement() {
        SWTBotMenu deleteMenu = bot.menu("Edit").menu("Delete");
        if (deleteMenu.isEnabled()) {
            deleteMenu.click();
        }
    }
}
