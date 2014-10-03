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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.WithSemantic;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

/**
 * Test class which checks correct GMF refresh after undo of a deletion from a
 * GenericTool. See VP-1966
 * 
 * @author edugueperoux
 */
public class RefreshAfterUndoDeletionFromGenericToolTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/refresh/VP-1966/";

    private static final String REPRESENTATION_INSTANCE_NAME = "new VP_1966_Diagram";

    private static final String REPRESENTATION_NAME = "VP_1966_Diagram";

    private static final String SEMANTIC_RESOURCE_FILENAME = "VP-1966.ecore";

    private static final String SESSION_RESOURCE_FILENAME = "VP-1966.aird";

    private static final String MODELER_RESOURCE_FILENAME = "VP-1966.odesign";

    private static final String DELETION_TOOL_ID = "Delete Tool";

    private UIDiagramRepresentation diagram;

    private SWTBotGefEditPart diagramEditPartBot;

    private SWTBotGefEditPart node1Bot;

    private SWTBotGefEditPart nodes2Bot;

    private SWTBotGefEditPart nodes3Bot;

    private SWTBotGefEditPart edgeBot;

    private SWTBotGefEditPart nodeWithAttributeAsBorderedNodes1Bot;

    private SWTBotGefEditPart att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot;

    private SWTBotGefEditPart att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot;

    private SWTBotGefEditPart dataTypeOfAtt1AsBorderedNodeBot;

    private SWTBotGefEditPart dataTypeOfAtt2AsBorderedNodeBot;

    private DSemanticDiagram dSemanticDiagram;

    private EPackage rootEPackage;

    private EClass nodeWithAttributeAsBorderedNodes1;

    private EAttribute att1;

    private EAttribute att2;

    private EDataType myDataType;

    private Rectangle node1Bounds;

    private Rectangle nodes2Bounds;

    private Rectangle nodes3Bounds;

    private Rectangle edgeBounds;

    private Rectangle nodeWithAttributeAsBorderedNodes1Bounds;

    private Rectangle att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bounds;

    private Rectangle att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bounds;

    private Rectangle dataTypeOfAtt1AsBorderedNodeBounds;

    private Rectangle dataTypeOfAtt2AsBorderedNodeBounds;

    private int initialOwnedDiagramElementsNb;

    private int initialEClassifiersOfRootEPackageNb;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_RESOURCE_FILENAME, SESSION_RESOURCE_FILENAME, MODELER_RESOURCE_FILENAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILENAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        initEditor();

        diagramEditPartBot = editor.rootEditPart().children().get(0);
        DDiagramEditPart dDiagramEditPart = (DDiagramEditPart) diagramEditPartBot.part();
        dSemanticDiagram = (DSemanticDiagram) dDiagramEditPart.resolveSemanticElement();
        rootEPackage = (EPackage) dSemanticDiagram.getTarget();
        nodeWithAttributeAsBorderedNodes1 = (EClass) rootEPackage.getEClassifier("NodeWithAttributeAsBorderedNodes1");
        att1 = nodeWithAttributeAsBorderedNodes1.getEAttributes().get(0);
        att2 = nodeWithAttributeAsBorderedNodes1.getEAttributes().get(1);
        myDataType = (EDataType) rootEPackage.getEClassifier("MyDataType");

        node1Bot = editor.getEditPart("Node1").parent();
        nodes2Bot = editor.getEditPart("Nodes2").parent();
        nodes3Bot = editor.getEditPart("Nodes3").parent();
        edgeBot = editor.getEditPart("node2").parent();

        nodeWithAttributeAsBorderedNodes1Bot = diagramEditPartBot.descendants(WithSemantic.withSemantic(nodeWithAttributeAsBorderedNodes1)).get(1);
        att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot = nodeWithAttributeAsBorderedNodes1Bot.descendants(WithSemantic.withSemantic(att1)).get(0);
        att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot = nodeWithAttributeAsBorderedNodes1Bot.descendants(WithSemantic.withSemantic(att2)).get(0);
        dataTypeOfAtt1AsBorderedNodeBot = att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot.descendants(WithSemantic.withSemantic(myDataType)).get(0);
        dataTypeOfAtt2AsBorderedNodeBot = att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot.descendants(WithSemantic.withSemantic(myDataType)).get(0);

        node1Bounds = editor.getBounds(node1Bot);
        nodes2Bounds = editor.getBounds(nodes2Bot);
        nodes3Bounds = editor.getBounds(nodes3Bot);
        edgeBounds = editor.getBounds(edgeBot);

        nodeWithAttributeAsBorderedNodes1Bounds = editor.getBounds(nodeWithAttributeAsBorderedNodes1Bot);
        att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bounds = editor.getBounds(att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot);
        att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bounds = editor.getBounds(att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot);
        dataTypeOfAtt1AsBorderedNodeBounds = editor.getBounds(dataTypeOfAtt1AsBorderedNodeBot);
        dataTypeOfAtt2AsBorderedNodeBounds = editor.getBounds(dataTypeOfAtt2AsBorderedNodeBot);

        initialOwnedDiagramElementsNb = dSemanticDiagram.getOwnedDiagramElements().size();
        initialEClassifiersOfRootEPackageNb = rootEPackage.getEClassifiers().size();
    }

    private void initEditor() {
        if (diagram != null) {
            editor = diagram.getEditor();

            editor.setSnapToGrid(false);

            editor.setFocus();
        }
    }

    /**
     * Test a undo of a deletion simple Node according to the first scenario of
     * ticket Doremi-2495.
     * 
     */
    public void _test_Deletion_Undo_Of_Simple_Node() {

        // Delete only representation of Node3 through a custom tool
        editor.activateTool(DELETION_TOOL_ID);
        ICondition condition = new OperationDoneCondition();
        nodes3Bot.click();
        bot.waitUntil(condition);

        // Checks that semantic elements haven't changed & representation have
        // been deleted
        Assert.assertEquals(initialOwnedDiagramElementsNb - 1, dSemanticDiagram.getOwnedDiagramElements().size());
        // includes the EDataType not being represented
        Assert.assertEquals(initialEClassifiersOfRootEPackageNb, rootEPackage.getEClassifiers().size());
        Assert.assertFalse(diagramEditPartBot.children().contains(nodes3Bot));
        Assert.assertEquals(nodes2Bounds, editor.getBounds(nodes2Bot));
        Assert.assertEquals(nodes3Bounds, editor.getBounds(nodes3Bot));
        Assert.assertEquals(edgeBounds, editor.getBounds(edgeBot));

        // Undo
        undo("Delete Tool");

        Assert.assertEquals(initialOwnedDiagramElementsNb, dSemanticDiagram.getOwnedDiagramElements().size());
        // includes the EDataType not being represented
        Assert.assertEquals(initialEClassifiersOfRootEPackageNb, rootEPackage.getEClassifiers().size());

        nodes3Bot = editor.getEditPart("Nodes3").parent();
        Assert.assertEquals(node1Bounds, editor.getBounds(node1Bot));
        Assert.assertEquals(nodes2Bounds, editor.getBounds(nodes2Bot));
        Assert.assertEquals(nodes3Bounds, editor.getBounds(nodes3Bot));
        Assert.assertEquals(edgeBounds, editor.getBounds(edgeBot));

        // Redo
        redo("Delete Tool");

        // Checks that semantic elements haven't changed & representation have
        // been deleted
        Assert.assertEquals(initialOwnedDiagramElementsNb - 1, dSemanticDiagram.getOwnedDiagramElements().size());
        // includes the EDataType not being represented
        Assert.assertEquals(initialEClassifiersOfRootEPackageNb, rootEPackage.getEClassifiers().size());
        Assert.assertFalse(diagramEditPartBot.children().contains(nodes3Bot));
        Assert.assertEquals(nodes2Bounds, editor.getBounds(nodes2Bot));
        Assert.assertEquals(nodes3Bounds, editor.getBounds(nodes3Bot));
        Assert.assertEquals(edgeBounds, editor.getBounds(edgeBot));

    }

    /**
     * Test a undo of a deletion of a Node connected with a Edge according to
     * the second scenario of ticket Doremi-2495.
     * 
     */
    public void _test_Deletion_Undo_Of_Node_Connected_With_Edge() {
        // Delete only representation of Node1 through a custom tool
        editor.activateTool(DELETION_TOOL_ID);
        ICondition condition = new OperationDoneCondition();
        node1Bot.click();
        bot.waitUntil(condition);
        // Checks that semantic elements haven't changed & representation have
        // been deleted

        // the Delete Tool doesn't delete the DEdge
        Assert.assertEquals(initialOwnedDiagramElementsNb - 1, dSemanticDiagram.getOwnedDiagramElements().size());
        // includes the EDataType not being represented
        Assert.assertEquals(initialEClassifiersOfRootEPackageNb, rootEPackage.getEClassifiers().size());
        Assert.assertFalse(diagramEditPartBot.children().contains(node1Bot));
        Assert.assertFalse(diagramEditPartBot.children().contains(edgeBot));
        Assert.assertEquals(nodes2Bounds, editor.getBounds(nodes2Bot));
        Assert.assertEquals(nodes3Bounds, editor.getBounds(nodes3Bot));

        // Undo
        undo("Delete Tool");

        Assert.assertEquals(initialOwnedDiagramElementsNb, dSemanticDiagram.getOwnedDiagramElements().size());
        // includes the EDataType not being represented
        Assert.assertEquals(initialEClassifiersOfRootEPackageNb, rootEPackage.getEClassifiers().size());

        nodeWithAttributeAsBorderedNodes1Bounds = editor.getBounds(nodeWithAttributeAsBorderedNodes1Bot);
        att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bounds = editor.getBounds(att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot);
        att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bounds = editor.getBounds(att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot);
        dataTypeOfAtt1AsBorderedNodeBounds = editor.getBounds(dataTypeOfAtt1AsBorderedNodeBot);
        dataTypeOfAtt2AsBorderedNodeBounds = editor.getBounds(dataTypeOfAtt2AsBorderedNodeBot);

        node1Bot = editor.getEditPart("Node1").parent();
        edgeBot = editor.getEditPart("node2").parent();
        // FIXME : we have +7 in height
        // Assert.assertEquals(node1Bounds, editor.getBounds(node1Bot));
        Assert.assertEquals(nodes2Bounds, editor.getBounds(nodes2Bot));
        Assert.assertEquals(nodes3Bounds, editor.getBounds(nodes3Bot));
        // FIXME : we have (82, 83, 129, 269) instead of (84,90,127,262)
        // Assert.assertEquals(edgeBounds, editor.getBounds(edgeBot));

        // Redo
        redo("Delete Tool");

        Assert.assertEquals(initialOwnedDiagramElementsNb - 1, dSemanticDiagram.getOwnedDiagramElements().size());
        // includes the EDataType not being represented
        Assert.assertEquals(initialEClassifiersOfRootEPackageNb, rootEPackage.getEClassifiers().size());
        Assert.assertFalse(diagramEditPartBot.children().contains(node1Bot));
        Assert.assertFalse(diagramEditPartBot.children().contains(edgeBot));
        Assert.assertEquals(nodes2Bounds, editor.getBounds(nodes2Bot));
        Assert.assertEquals(nodes3Bounds, editor.getBounds(nodes3Bot));
    }

    /**
     * Test a undo of a deletion of a Node (NodeWithAttributeAsBorderedNodes1)
     * with BorderedNodes, and these last BorderedNodes having also
     * BorderedNodes.
     * 
     */
    public void test_Deletion_Undo_Of_Node_With_BorderedNodes() {
        // Delete only representation of NodeWithAttributeAsBorderedNodes1
        // through a custom tool
        editor.activateTool(DELETION_TOOL_ID);
        ICondition condition = new OperationDoneCondition();
        nodeWithAttributeAsBorderedNodes1Bot.click(nodeWithAttributeAsBorderedNodes1Bounds.getCenter());
        bot.waitUntil(condition);
        // Checks that semantic elements haven't changed & representation have
        // been deleted

        // the Delete Tool doesn't delete the DEdge
        Assert.assertEquals(initialOwnedDiagramElementsNb - 1, dSemanticDiagram.getOwnedDiagramElements().size());
        // includes the EDataType not being represented
        Assert.assertEquals(initialEClassifiersOfRootEPackageNb, rootEPackage.getEClassifiers().size());
        Assert.assertFalse(diagramEditPartBot.children().contains(nodeWithAttributeAsBorderedNodes1Bot));

        // Undo
        undo("Delete Tool");

        Assert.assertEquals(initialOwnedDiagramElementsNb, dSemanticDiagram.getOwnedDiagramElements().size());
        // includes the EDataType not being represented
        Assert.assertEquals(initialEClassifiersOfRootEPackageNb, rootEPackage.getEClassifiers().size());

        nodeWithAttributeAsBorderedNodes1Bot = diagramEditPartBot.descendants(WithSemantic.withSemantic(nodeWithAttributeAsBorderedNodes1)).get(1);
        att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot = nodeWithAttributeAsBorderedNodes1Bot.descendants(WithSemantic.withSemantic(att1)).get(0);
        att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot = nodeWithAttributeAsBorderedNodes1Bot.descendants(WithSemantic.withSemantic(att2)).get(0);
        dataTypeOfAtt1AsBorderedNodeBot = att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot.descendants(WithSemantic.withSemantic(myDataType)).get(0);
        dataTypeOfAtt2AsBorderedNodeBot = att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot.descendants(WithSemantic.withSemantic(myDataType)).get(0);

        Assert.assertEquals(nodeWithAttributeAsBorderedNodes1Bounds, editor.getBounds(nodeWithAttributeAsBorderedNodes1Bot));
        Assert.assertEquals(att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bounds, editor.getBounds(att1AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot));
        Assert.assertEquals(att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bounds, editor.getBounds(att2AsBorderedNodeOfNodeWithAttributeAsBorderedNodes1Bot));
        Assert.assertEquals(dataTypeOfAtt1AsBorderedNodeBounds, editor.getBounds(dataTypeOfAtt1AsBorderedNodeBot));
        Assert.assertEquals(dataTypeOfAtt2AsBorderedNodeBounds, editor.getBounds(dataTypeOfAtt2AsBorderedNodeBot));

        // Redo
        redo("Delete Tool");

        Assert.assertEquals(initialOwnedDiagramElementsNb - 1, dSemanticDiagram.getOwnedDiagramElements().size());
        // includes the EDataType not being represented
        Assert.assertEquals(initialEClassifiersOfRootEPackageNb, rootEPackage.getEClassifiers().size());
        Assert.assertFalse(diagramEditPartBot.children().contains(nodeWithAttributeAsBorderedNodes1Bot));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        diagram = null;
        dSemanticDiagram = null;

        diagramEditPartBot = null;
        node1Bot = null;
        nodes2Bot = null;
        edgeBot = null;
        nodeWithAttributeAsBorderedNodes1Bot = null;

        node1Bounds = null;
        nodes2Bounds = null;
        edgeBounds = null;
        nodeWithAttributeAsBorderedNodes1Bounds = null;

        super.tearDown();
    }

}
