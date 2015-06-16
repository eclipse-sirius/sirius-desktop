/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.description.style.BracketEdgeStyleDescription;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.BracketBendpointEditPolicy;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test brackets edge. Test edge horizontally resize, test move node, test
 * rotate to TOP/BOTTOM/LEFT/RIGHT, test zoom, test scroll, test same things in
 * container, test change source decoration in Odesign, test bracketEdge on
 * bracketEdge, test bracketEdge on standardEdge, test standard edge on
 * bracketEdge, test change lineStyle.
 * 
 * Test VP-3092
 * 
 * @author jdupont
 */
public class BracketEdgeTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/bracketEdge/";

    private static final String MODELER_RESOURCE_NAME = "VP-3092.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3092.ecore";

    private static final String SESSION_RESOURCE_NAME = "VP-3092.aird";

    private static final String VIEWPOINT_NAME = "VP-3092_Viewpoint";

    private static final String REPRESENTATION_INSTANCE_NAME = "new VP-3092_DiagramDescription";

    private static final String REPRESENTATION_NAME = "VP-3092_DiagramDescription";

    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + MODELER_RESOURCE_NAME;

    private static final String GROUP = "VP-3092";

    private static final String DIAGRAM_DESCRIPTION_NAME = "VP-3092_DiagramDescription";

    private static final String NEW_ECLASS_1 = "new EClass 1";

    private static final String NEW_ECLASS_2 = "new EClass 2";

    private static final String NEW_ECLASS_3 = "new EClass 3";

    private static final String NEW_ECLASS_11 = "new EClass 11";

    private static final String NEW_ECLASS_12 = "new EClass 12";

    private static final String NEW_ECLASS_111 = "new EClass 111";

    private static final String NEW_ECLASS_112 = "new EClass 112";

    private static final String EANNOTATION_1 = "A1";

    private static final String NEW_EREFERENCE_1 = "newEReference1";

    private static final String NEW_EREFERENCE_11 = "newEReference11";

    private static final String NEW_EREFERENCE_111 = "newEReference111";

    private static final String SET_LOCATION_SIZE = "Set Location or Size";

    private static final String SET = "Set";

    private static final String EXTEND = "Extend";

    private static final String PROPERTIES = "Properties";

    private static final String DECORATORS = "Decorators";

    private static final String GENERAL = "General";

    private static final String BRACKET_EDGE_ON_BRACKET_EDGE_CREATION_TOOL = "EAnnot2ERefCreationTool";

    private SWTBotView propertiesBot;

    /** Current diagram. */
    protected UIDiagramRepresentation uiDiagramRepresentation;

    private Point point0;

    private Point point1;

    private Point point2;

    private Point point3;

    private Point point4;

    private Point point5;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class, true);
    }

    /**
     * Test resizing bracket edge with source move. Resizing edge between new
     * EClass 1 and new EClass 2.
     */
    public void testResizeBracketWithMoveSource() {
        SWTBotGefEditPart newEReference1BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_1, BracketEdgeEditPart.class);
        PointList newEReference1PointList = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        SWTBotGefEditPart newEClass1EditPartBot = editor.getEditPart(NEW_ECLASS_1, AbstractDiagramListEditPart.class);
        Point newEClass1Location = editor.getBounds(newEClass1EditPartBot).getLocation();

        // Move new EClass 1
        newEClass1EditPartBot.select();
        int xDelta = 100;
        int yDelta = -100;
        Point dropLocation = newEClass1Location.getTranslated(xDelta, yDelta);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEClass1EditPartBot);
        editor.drag(newEClass1EditPartBot, dropLocation);
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        PointList newEReference1PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterDrop.size());
        assertEquals(newEReference1PointList.getPoint(0).getTranslated(xDelta, yDelta), newEReference1PointListAfterDrop.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1).getTranslated(xDelta, yDelta), newEReference1PointListAfterDrop.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2).getTranslated(xDelta, yDelta), newEReference1PointListAfterDrop.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3).getTranslated(xDelta, 0), newEReference1PointListAfterDrop.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4).getTranslated(xDelta, 0), newEReference1PointListAfterDrop.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterDrop.getPoint(5));

        // Undo
        undo(DiagramUIMessages.SetLocationCommand_Label_Resize);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterUndo.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterUndo.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1), newEReference1PointListAfterUndo.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2), newEReference1PointListAfterUndo.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3), newEReference1PointListAfterUndo.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4), newEReference1PointListAfterUndo.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterUndo.getPoint(5));

        // Redo
        redo(DiagramUIMessages.SetLocationCommand_Label_Resize);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterRedo.size());
        assertEquals(newEReference1PointList.getPoint(0).getTranslated(xDelta, yDelta), newEReference1PointListAfterRedo.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1).getTranslated(xDelta, yDelta), newEReference1PointListAfterRedo.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2).getTranslated(xDelta, yDelta), newEReference1PointListAfterRedo.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3).getTranslated(xDelta, 0), newEReference1PointListAfterRedo.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4).getTranslated(xDelta, 0), newEReference1PointListAfterRedo.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class, true);
        SWTBotUtils.waitAllUiEvents();

        newEReference1BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_1, BracketEdgeEditPart.class);
        PointList newEReference1PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterReopening.size());
        assertEquals(newEReference1PointList.getPoint(0).getTranslated(xDelta, yDelta), newEReference1PointListAfterReopening.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1).getTranslated(xDelta, yDelta), newEReference1PointListAfterReopening.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2).getTranslated(xDelta, yDelta), newEReference1PointListAfterReopening.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3).getTranslated(xDelta, 0), newEReference1PointListAfterReopening.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4).getTranslated(xDelta, 0), newEReference1PointListAfterReopening.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterReopening.getPoint(5));

    }

    /**
     * Test resizing bracket edge with target move. Resizing edge between new
     * EClass 1 and new EClass 2.
     */
    public void testResizeBracketWithMoveTarget() {
        SWTBotGefEditPart newEReference1BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_1, BracketEdgeEditPart.class);
        PointList newEReference1PointList = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        SWTBotGefEditPart newEClass2EditPartBot = editor.getEditPart(NEW_ECLASS_2, AbstractDiagramListEditPart.class);
        Point newEClass2Location = editor.getBounds(newEClass2EditPartBot).getLocation();

        // Move new EClass 2
        newEClass2EditPartBot.select();
        int xDelta = -100;
        int yDelta = 100;
        Point dropLocation = newEClass2Location.getTranslated(xDelta, yDelta);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEClass2EditPartBot);
        editor.drag(newEClass2EditPartBot, dropLocation);
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        PointList newEReference1PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterDrop.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterDrop.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1), newEReference1PointListAfterDrop.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2), newEReference1PointListAfterDrop.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3).getTranslated(0, yDelta), newEReference1PointListAfterDrop.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4).getTranslated(0, yDelta), newEReference1PointListAfterDrop.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5).getTranslated(xDelta, yDelta), newEReference1PointListAfterDrop.getPoint(5));

        // Undo
        undo(DiagramUIMessages.SetLocationCommand_Label_Resize);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterUndo.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterUndo.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1), newEReference1PointListAfterUndo.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2), newEReference1PointListAfterUndo.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3), newEReference1PointListAfterUndo.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4), newEReference1PointListAfterUndo.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterUndo.getPoint(5));

        // Redo
        redo(DiagramUIMessages.SetLocationCommand_Label_Resize);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterRedo.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterRedo.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1), newEReference1PointListAfterRedo.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2), newEReference1PointListAfterRedo.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3).getTranslated(0, yDelta), newEReference1PointListAfterRedo.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4).getTranslated(0, yDelta), newEReference1PointListAfterRedo.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5).getTranslated(xDelta, yDelta), newEReference1PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class, true);
        SWTBotUtils.waitAllUiEvents();

        newEReference1BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_1, BracketEdgeEditPart.class);
        PointList newEReference1PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterReopening.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterReopening.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1), newEReference1PointListAfterReopening.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2), newEReference1PointListAfterReopening.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3).getTranslated(0, yDelta), newEReference1PointListAfterReopening.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4).getTranslated(0, yDelta), newEReference1PointListAfterReopening.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5).getTranslated(xDelta, yDelta), newEReference1PointListAfterReopening.getPoint(5));

    }

    /**
     * Test resizing bracket edge with source move in container. Resizing edge
     * between new EClass 111 and new EClass 112 in container new Package 11
     */
    public void testResizeBracketWithMoveSourceInContainer() {
        SWTBotGefEditPart newEReference111BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_111, BracketEdgeEditPart.class);
        PointList newEReference111PointList = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        SWTBotGefEditPart newEClass111EditPartBot = editor.getEditPart(NEW_ECLASS_111, AbstractDiagramListEditPart.class);
        Point newEClass111Location = editor.getBounds(newEClass111EditPartBot).getLocation();

        // Move new EClass 111
        newEClass111EditPartBot.select();
        int xDelta = -50;
        int yDelta = 50;
        Point dropLocation = newEClass111Location.getTranslated(xDelta, yDelta);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEClass111EditPartBot);
        // editor.drag(newEClass111Location, dropLocation);
        editor.drag(newEClass111Location.getTranslated(3, 2), dropLocation.getTranslated(3, 2));
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        PointList newEReference111PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterDrop.size());
        assertEquals(newEReference111PointList.getPoint(0).getTranslated(xDelta, yDelta), newEReference111PointListAfterDrop.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1).getTranslated(xDelta, yDelta), newEReference111PointListAfterDrop.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2).getTranslated(xDelta, yDelta), newEReference111PointListAfterDrop.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(xDelta, 0), newEReference111PointListAfterDrop.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(xDelta, 0), newEReference111PointListAfterDrop.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterDrop.getPoint(5));

        // Undo
        undo(DiagramUIMessages.SetLocationCommand_Label_Resize);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference111PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterUndo.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterUndo.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1), newEReference111PointListAfterUndo.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2), newEReference111PointListAfterUndo.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3), newEReference111PointListAfterUndo.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4), newEReference111PointListAfterUndo.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterUndo.getPoint(5));

        // Redo
        redo(DiagramUIMessages.SetLocationCommand_Label_Resize);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference111PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterRedo.size());
        assertEquals(newEReference111PointList.getPoint(0).getTranslated(xDelta, yDelta), newEReference111PointListAfterRedo.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1).getTranslated(xDelta, yDelta), newEReference111PointListAfterRedo.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2).getTranslated(xDelta, yDelta), newEReference111PointListAfterRedo.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(xDelta, 0), newEReference111PointListAfterRedo.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(xDelta, 0), newEReference111PointListAfterRedo.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class, true);
        SWTBotUtils.waitAllUiEvents();

        newEReference111BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_111, BracketEdgeEditPart.class);
        PointList newEReference111PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterReopening.size());
        assertEquals(newEReference111PointList.getPoint(0).getTranslated(xDelta, yDelta), newEReference111PointListAfterReopening.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1).getTranslated(xDelta, yDelta), newEReference111PointListAfterReopening.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2).getTranslated(xDelta, yDelta), newEReference111PointListAfterReopening.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(xDelta, 0), newEReference111PointListAfterReopening.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(xDelta, 0), newEReference111PointListAfterReopening.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterReopening.getPoint(5));

    }

    /**
     * Test resizing bracket edge with target move in container. Resizing edge
     * between new EClass 111 and new EClass 112 in container new Package 11
     */
    public void testResizeBracketWithMoveTargetInContainer() {
        SWTBotGefEditPart newEReference111BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_111, BracketEdgeEditPart.class);
        PointList newEReference111PointList = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        SWTBotGefEditPart newEClass112EditPartBot = editor.getEditPart(NEW_ECLASS_112, AbstractDiagramListEditPart.class);
        Point newEClass112Location = editor.getBounds(newEClass112EditPartBot).getCenter();

        // Move new EClass 111
        newEClass112EditPartBot.select();
        int xDelta = 50;
        int yDelta = -50;
        Point dropLocation = newEClass112Location.getTranslated(xDelta, yDelta);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEClass112EditPartBot);
        editor.drag(newEClass112Location.getTranslated(3, 2), dropLocation.getTranslated(3, 2));
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        PointList newEReference111PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterDrop.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterDrop.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1), newEReference111PointListAfterDrop.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2), newEReference111PointListAfterDrop.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(0, yDelta), newEReference111PointListAfterDrop.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(0, yDelta), newEReference111PointListAfterDrop.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5).getTranslated(xDelta, yDelta), newEReference111PointListAfterDrop.getPoint(5));

        // Undo
        undo(DiagramUIMessages.SetLocationCommand_Label_Resize);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference111PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterUndo.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterUndo.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1), newEReference111PointListAfterUndo.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2), newEReference111PointListAfterUndo.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3), newEReference111PointListAfterUndo.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4), newEReference111PointListAfterUndo.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterUndo.getPoint(5));

        // Redo
        redo(DiagramUIMessages.SetLocationCommand_Label_Resize);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference111PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterRedo.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterRedo.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1), newEReference111PointListAfterRedo.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2), newEReference111PointListAfterRedo.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(0, yDelta), newEReference111PointListAfterRedo.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(0, yDelta), newEReference111PointListAfterRedo.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5).getTranslated(xDelta, yDelta), newEReference111PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class, true);
        SWTBotUtils.waitAllUiEvents();

        newEReference111BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_111, BracketEdgeEditPart.class);
        PointList newEReference111PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterReopening.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterReopening.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1), newEReference111PointListAfterReopening.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2), newEReference111PointListAfterReopening.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(0, yDelta), newEReference111PointListAfterReopening.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(0, yDelta), newEReference111PointListAfterReopening.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5).getTranslated(xDelta, yDelta), newEReference111PointListAfterReopening.getPoint(5));

    }

    /**
     * Test resizing bracket edge with handle move. To resizing edge between new
     * EClass 1 and new EClass 2.
     */
    public void testResizeBracketWithHandleMove() {
        SWTBotGefEditPart newEReference1BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_1, BracketEdgeEditPart.class);
        PointList newEReference1PointList = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());

        // Move newEReference1
        newEReference1BracketEdgeEditPartBot.select();
        int xDelta = 50;
        Point dragLocation = newEReference1PointList.getMidpoint();
        Point dropLocation = dragLocation.getTranslated(xDelta, 0);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEReference1BracketEdgeEditPartBot);
        editor.drag(dragLocation, dropLocation);
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        PointList newEReference1PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterDrop.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterDrop.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1).getTranslated(xDelta, 0), newEReference1PointListAfterDrop.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2).getTranslated(xDelta, 0), newEReference1PointListAfterDrop.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3).getTranslated(xDelta, 0), newEReference1PointListAfterDrop.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4).getTranslated(xDelta, 0), newEReference1PointListAfterDrop.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterDrop.getPoint(5));

        // Undo
        undo(BracketBendpointEditPolicy.MOVE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterUndo.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterUndo.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1), newEReference1PointListAfterUndo.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2), newEReference1PointListAfterUndo.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3), newEReference1PointListAfterUndo.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4), newEReference1PointListAfterUndo.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterUndo.getPoint(5));

        // Redo
        redo(BracketBendpointEditPolicy.MOVE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterRedo.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterRedo.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1).getTranslated(xDelta, 0), newEReference1PointListAfterRedo.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2).getTranslated(xDelta, 0), newEReference1PointListAfterRedo.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3).getTranslated(xDelta, 0), newEReference1PointListAfterRedo.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4).getTranslated(xDelta, 0), newEReference1PointListAfterRedo.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        newEReference1BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_1, BracketEdgeEditPart.class);
        PointList newEReference1PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterReopening.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterReopening.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1).getTranslated(xDelta, 0), newEReference1PointListAfterReopening.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2).getTranslated(xDelta, 0), newEReference1PointListAfterReopening.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3).getTranslated(xDelta, 0), newEReference1PointListAfterReopening.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4).getTranslated(xDelta, 0), newEReference1PointListAfterReopening.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterReopening.getPoint(5));

    }

    /**
     * Test resizing bracket edge with handle move. To resizing edge between new
     * EClass 111 and new EClass 112.
     */
    public void testResizeBracketWithHandleMoveInContainer() {
        SWTBotGefEditPart newEReference111BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_111, BracketEdgeEditPart.class);
        PointList newEReference111PointList = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());

        // Move newEReference111
        newEReference111BracketEdgeEditPartBot.select();
        int xDelta = 50;
        Point dragLocation = newEReference111PointList.getMidpoint();
        Point dropLocation = dragLocation.getTranslated(xDelta, 0);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEReference111BracketEdgeEditPartBot);
        editor.drag(dragLocation, dropLocation);
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        PointList newEReference111PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterDrop.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterDrop.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1).getTranslated(xDelta, 0), newEReference111PointListAfterDrop.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2).getTranslated(xDelta, 0), newEReference111PointListAfterDrop.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(xDelta, 0), newEReference111PointListAfterDrop.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(xDelta, 0), newEReference111PointListAfterDrop.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterDrop.getPoint(5));

        // Undo
        undo(BracketBendpointEditPolicy.MOVE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference111PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterUndo.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterUndo.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1), newEReference111PointListAfterUndo.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2), newEReference111PointListAfterUndo.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3), newEReference111PointListAfterUndo.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4), newEReference111PointListAfterUndo.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterUndo.getPoint(5));

        // Redo
        redo(BracketBendpointEditPolicy.MOVE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference111PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterRedo.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterRedo.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1).getTranslated(xDelta, 0), newEReference111PointListAfterRedo.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2).getTranslated(xDelta, 0), newEReference111PointListAfterRedo.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(xDelta, 0), newEReference111PointListAfterRedo.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(xDelta, 0), newEReference111PointListAfterRedo.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        newEReference111BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_111, BracketEdgeEditPart.class);
        PointList newEReference111PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterReopening.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterReopening.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1).getTranslated(xDelta, 0), newEReference111PointListAfterReopening.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2).getTranslated(xDelta, 0), newEReference111PointListAfterReopening.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(xDelta, 0), newEReference111PointListAfterReopening.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(xDelta, 0), newEReference111PointListAfterReopening.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterReopening.getPoint(5));

    }

    /**
     * Test that rotate to top, if y value is too low, passed in to top. Select
     * point 2 of bracket named newEReference1 and drag to right and to top.
     */
    public void testRotateBracketToTop() {
        rotateBracketToTop(NEW_ECLASS_1, NEW_ECLASS_2, NEW_EREFERENCE_1);
    }

    /**
     * Test that rotate to bottom, if y value is too low, passed in to bottom.
     * Select point 4 of bracket named newEReference1 and drag to right and to
     * bottom.
     */
    public void testRotateBracketToBottom() {
        rotateBracketToBottom(NEW_ECLASS_1, NEW_ECLASS_2, NEW_EREFERENCE_1);
    }

    /**
     * Test that rotate to left with y value not big enough to passed in to
     * bottom. Select point 4 of bracket named newEReference1 and drag to left
     * and bottom.
     */
    public void testRotateBracketToLeft() {
        rotateBracketToLeft(NEW_ECLASS_1, NEW_ECLASS_2, NEW_EREFERENCE_1);
    }

    /**
     * Test that rotate to right with y value not big enough to passed in to
     * bottom. Select point 4 of bracket named newEReference1 and drag to right
     * and bottom.
     */
    public void testRotateBracketToRight() {
        rotateBracketToRight(NEW_ECLASS_1, NEW_ECLASS_2, NEW_EREFERENCE_1);
    }

    /**
     * Test that rotate to top in container, if y value is too low, passed in to
     * top. Select point 2 of bracket named newEReference1 and drag to right and
     * to top.
     */
    public void testRotateBracketToTopInContainer() {
        rotateBracketToTop(NEW_ECLASS_111, NEW_ECLASS_112, NEW_EREFERENCE_111);
    }

    /**
     * Test that rotate to bottom in container, if y value is too low, passed in
     * to bottom. Select point 4 of bracket named newEReference1 and drag to
     * right and to bottom.
     */
    public void testRotateBracketToBottomInContainer() {
        rotateBracketToBottom(NEW_ECLASS_111, NEW_ECLASS_112, NEW_EREFERENCE_111);
    }

    /**
     * Test that rotate to left in container with y value not big enough to
     * passed in to bottom. Select point 4 of bracket named newEReference1 and
     * drag to left and bottom.
     */
    public void testRotateBracketToLeftInContainer() {
        rotateBracketToLeft(NEW_ECLASS_111, NEW_ECLASS_112, NEW_EREFERENCE_111);
    }

    /**
     * Test that rotate to right in container with y value not big enough to
     * passed in to bottom. Select point 4 of bracket named newEReference1 and
     * drag to right and bottom.
     */
    public void testRotateBracketToRightInContainer() {
        rotateBracketToRight(NEW_ECLASS_111, NEW_ECLASS_112, NEW_EREFERENCE_111);
    }

    /**
     * Test resize bracket with move source with zoom 200.
     */
    // This not work, can't drag point with zoom
    public void _testResizeBracketWithHandleMoveWithZoom() {
        ZoomLevel zoomLevel = ZoomLevel.ZOOM_200;
        editor.zoom(zoomLevel);

        SWTBotGefEditPart newEReference111BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_111, BracketEdgeEditPart.class);
        PointList newEReference111PointList = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());

        // Move newEReference111
        newEReference111BracketEdgeEditPartBot.select();
        int xDelta = 30;
        Point dragLocation = newEReference111PointList.getMidpoint();
        Point dropLocation = dragLocation.getTranslated(xDelta, 0);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEReference111BracketEdgeEditPartBot);
        // Need to do a logical2screen to dragAndDrop the midPoint
        GraphicalHelper.logical2screen(dragLocation, (IGraphicalEditPart) newEReference111BracketEdgeEditPartBot.part());
        GraphicalHelper.logical2screen(dropLocation, (IGraphicalEditPart) newEReference111BracketEdgeEditPartBot.part());
        editor.drag(dragLocation, dropLocation);
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        xDelta = (int) (xDelta * zoomLevel.getAmount());
        PointList newEReference111PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterDrop.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterDrop.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1).getTranslated(xDelta, 0), newEReference111PointListAfterDrop.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2).getTranslated(xDelta, 0), newEReference111PointListAfterDrop.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(xDelta, 0), newEReference111PointListAfterDrop.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(xDelta, 0), newEReference111PointListAfterDrop.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterDrop.getPoint(5));

        // Undo
        undo(BracketBendpointEditPolicy.MOVE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference111PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterUndo.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterUndo.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1), newEReference111PointListAfterUndo.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2), newEReference111PointListAfterUndo.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3), newEReference111PointListAfterUndo.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4), newEReference111PointListAfterUndo.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterUndo.getPoint(5));

        // Redo
        redo(BracketBendpointEditPolicy.MOVE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference111PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterRedo.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterRedo.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1).getTranslated(xDelta, 0), newEReference111PointListAfterRedo.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2).getTranslated(xDelta, 0), newEReference111PointListAfterRedo.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(xDelta, 0), newEReference111PointListAfterRedo.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(xDelta, 0), newEReference111PointListAfterRedo.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        newEReference111BracketEdgeEditPartBot = editor.getEditPart(NEW_EREFERENCE_111, BracketEdgeEditPart.class);
        PointList newEReference111PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference111BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference111PointList.size(), newEReference111PointListAfterReopening.size());
        assertEquals(newEReference111PointList.getPoint(0), newEReference111PointListAfterReopening.getPoint(0));
        assertEquals(newEReference111PointList.getPoint(1).getTranslated(xDelta, 0), newEReference111PointListAfterReopening.getPoint(1));
        assertEquals(newEReference111PointList.getPoint(2).getTranslated(xDelta, 0), newEReference111PointListAfterReopening.getPoint(2));
        assertEquals(newEReference111PointList.getPoint(3).getTranslated(xDelta, 0), newEReference111PointListAfterReopening.getPoint(3));
        assertEquals(newEReference111PointList.getPoint(4).getTranslated(xDelta, 0), newEReference111PointListAfterReopening.getPoint(4));
        assertEquals(newEReference111PointList.getPoint(5), newEReference111PointListAfterReopening.getPoint(5));

        try {
            // Retrieve Position of 6 points bracketEdge between EClass 1 and
            // EClass
            // 2.
            SWTBotGefEditPart bracket1EditPart = initializeEdgeBracketPositionPoint(NEW_EREFERENCE_1);
            // Retrieve edge point location between new ECLass 1 and new EClass
            // 2.
            checkPositionEdge(editor, NEW_ECLASS_1, NEW_ECLASS_2, point0.x, point0.y, point1.x, point1.y, point2.x, point2.y, point3.x, point3.y, point4.x, point4.y, point5.x, point5.y);
            // Select bracket edge newEReference1 between new EClass 1 and new
            // EClass 2
            bracket1EditPart.select();
            // Select editor for show Zoom + and Zoom -
            editor.click(new Point(1, 1));
            // Zoom to 200%
            pressZoomInButton(editor, 4);
            // Select bracket edge between new source and target.
            bracket1EditPart.select();
            // Drag and drop new bracket newEReference1 to 498 (x value, y value
            // don't move)
            Point location = editor.getAbsoluteLocation((GraphicalEditPart) bracket1EditPart.part());
            int deltaX = 40;
            Point newLocation = location.getTranslated(new Point(deltaX, 0));
            // Point newLocation = pointToDrag.getTranslated(new Point(deltaX,
            // 0));
            editor.drag(bracket1EditPart, newLocation);

            // pointToDrag = new Point(pointToDrag.x + 1, pointToDrag.y);
            // int deltaX = 40;
            // Point newLocation = pointToDrag.getTranslated(new Point(deltaX,
            // 0));
            // editor.drag(pointToDrag, newLocation);
            // Retrieve edge point location
            checkPositionEdge(editor, NEW_ECLASS_1, NEW_ECLASS_2, point0.x, point0.y, point1.x + deltaX, point1.y, point2.x + deltaX, point2.y, point3.x + deltaX, point3.y, point4.x + deltaX,
                    point4.y, point5.x, point5.y);
            // Undo
            undo(SET);
            checkPositionEdge(editor, NEW_ECLASS_1, NEW_ECLASS_2, point0.x, point0.y, point1.x, point1.y, point2.x, point2.y, point3.x, point3.y, point4.x, point4.y, point5.x, point5.y);
            // Redo
            redo(SET);
            checkPositionEdge(editor, NEW_ECLASS_1, NEW_ECLASS_2, point0.x, point0.y, point1.x + deltaX, point1.y, point2.x + deltaX, point2.y, point3.x + deltaX, point3.y, point4.x + deltaX,
                    point4.y, point5.x, point5.y);
            // Save and close representation
            editor.saveAndClose();
            SWTBotUtils.waitAllUiEvents();
            // Open editor
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
            SWTBotUtils.waitAllUiEvents();
            // Retrieve edge point location
            checkPositionEdge(editor, NEW_ECLASS_1, NEW_ECLASS_2, point0.x, point0.y, point1.x + deltaX, point1.y, point2.x + deltaX, point2.y, point3.x + deltaX, point3.y, point4.x + deltaX,
                    point4.y, point5.x, point5.y);
        } finally {
            // Press to default zoom
            pressZoomOutButton(editor, 4);
        }
    }

    /**
     * Test creation bracket edge on bracket edge.
     */
    public void testCreateBracketEdgeOnBracketEdge() {
        createBracketEdgeOnEdge(NEW_ECLASS_1, NEW_ECLASS_2, EANNOTATION_1, NEW_EREFERENCE_1);
    }

    /**
     * Test creation bracket edge on bracket edge in Container.
     */
    public void testCreateBracketEdgeOnBracketEdgeInContainer() {
        createBracketEdgeOnEdge(NEW_ECLASS_111, NEW_ECLASS_112, EANNOTATION_1, NEW_EREFERENCE_111);
    }

    /**
     * Test creation bracket edge on standard edge
     */
    public void testCreateBracketEdgeOnStandardEdge() {
        createBracketEdgeOnEdge(NEW_ECLASS_2, NEW_ECLASS_3, EANNOTATION_1, EXTEND);
    }

    /**
     * Test reconnect BracketEdge
     */
    public void testReconnectBracketEdge() {
        reconnectBracketEdge(NEW_ECLASS_1, NEW_ECLASS_2, NEW_EREFERENCE_1, NEW_ECLASS_3);
    }

    /**
     * Test reconnect BracketEdge in container
     */
    public void testReconnectBracketEdgeInContainer() {
        editor.maximize();
        try {
            reconnectBracketEdge(NEW_ECLASS_11, NEW_ECLASS_12, NEW_EREFERENCE_11, NEW_ECLASS_111);
        } finally {
            editor.restore();
        }
    }

    /**
     * Change decorators style (Start/Finish arrow) in VSM and check that
     * modifications are changed in representation
     */
    public void testChangeDecoratorsInOdesign() {
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }
        changeDecorators(EdgeArrows.INPUT_FILL_CLOSED_ARROW_LITERAL);
        // Open representation
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        // In SWTBOt must do refresh
        editor.refresh();
        // Check that Source and Target Arrow are set correctly
        initializeEdgeBracketPositionPoint(NEW_EREFERENCE_1).select();
        propertiesBot = bot.viewByTitle(PROPERTIES);
        SWTBotSiriusHelper.selectPropertyTabItem("Style");
        SWTBotTree tree = propertiesBot.bot().tree();
        tree.expandNode("Misc").select().getNode("Source Arrow").doubleClick();
        assertEquals("The type of Source Arrow must be Input Fill Closed Arrow", "Input Fill Closed Arrow", propertiesBot.bot().ccomboBox().getText());
        tree.expandNode("Misc").select().getNode("Target Arrow").doubleClick();
        assertEquals("The type of Target Arrow must be Input Fill Closed Arrow", "Input Fill Closed Arrow", propertiesBot.bot().ccomboBox().getText());

        editor.save();
    }

    private void changeDecorators(EdgeArrows edgeArrow) {
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(MODELER_RESOURCE_NAME);
        // expand the tree : EReference Mapping
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(DIAGRAM_DESCRIPTION_NAME).expandNode("Default").expandNode("EReferenceMapping")
                .expandNode("Bracket Edge Style solid").select();
        // accesses to property view
        bot.viewByTitle(PROPERTIES).setFocus();
        // accesses to tab Decorators
        SWTBotSiriusHelper.selectPropertyTabItem(DECORATORS);
        // Change Source arrow decorators for InputFilledClosedArrow
        SWTBotTable listDecoratorsSourceArrow = bot.viewByTitle(PROPERTIES).bot().tableWithLabel("Source Arrow*:");
        assertEquals("The decorators name must be " + edgeArrow.getLiteral(), edgeArrow.getLiteral(), listDecoratorsSourceArrow.getTableItem(6).getText());
        listDecoratorsSourceArrow.getTableItem(6).select();
        SWTBotTable listDecoratorsTargetArrow = bot.viewByTitle(PROPERTIES).bot().table(0);
        assertEquals("The decorators name must be " + edgeArrow.getLiteral(), edgeArrow.getLiteral(), listDecoratorsTargetArrow.getTableItem(6).getText());
        listDecoratorsTargetArrow.getTableItem(6).select();
        odesignEditor.save();
    }

    /**
     * The property routing style must not appears in VSM and must be disabled
     * in representation properties view.
     */
    public void testRoutingStyleNotAppears() {
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(MODELER_RESOURCE_NAME);
        // expand the tree : EReference Mapping
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(DIAGRAM_DESCRIPTION_NAME).expandNode("Default").expandNode("EReferenceMapping")
                .expandNode("Bracket Edge Style solid").select();
        // accesses to property view
        bot.viewByTitle(PROPERTIES).setFocus();
        // accesses to tab Decorators
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL);
        try {
            // Check that routing style is not present
            bot.viewByTitle(PROPERTIES).bot().radio();
            fail("The Routing Style should not be present in VSM for bracket edge");
        } catch (WidgetNotFoundException e) {
            editor.show();
            editor.setFocus();
            // Eclipse 4.x setFocus
            editor.click(0, 0);

            initializeEdgeBracketPositionPoint(NEW_EREFERENCE_1).select();
            propertiesBot = bot.viewByTitle(PROPERTIES);
            SWTBotUtils.waitAllUiEvents();
            propertiesBot.setFocus();
            SWTBotSiriusHelper.selectPropertyTabItem("Style");
            tree = propertiesBot.bot().tree();
            // Test that routing style is not visible in properties view
            try {
                tree.expandNode("Bracket Edge Style false").select().getNode("Routing Style");
                fail("The field Routing Style should not be present in properties view for Bracket Edge");
            } catch (WidgetNotFoundException wnfe) {
                // No things to do
            }
        }

    }

    /**
     * Test that source and target are inner Decoration when segment is too
     * small.
     */
    public void testSourceTargetInnerDecorationWhenSegmentTooSmall() {
        // Retrieve Position of 6 points bracketEdge between EClass 1 and EClass
        // 2.
        SWTBotGefEditPart bracket1EditPart = initializeEdgeBracketPositionPoint(NEW_EREFERENCE_1);
        // Retrieve edge point location between new ECLass 1 and new EClass 2.
        checkPositionEdge(editor, NEW_ECLASS_1, NEW_ECLASS_2, point0.x, point0.y, point1.x, point1.y, point2.x, point2.y, point3.x, point3.y, point4.x, point4.y, point5.x, point5.y);
        // Select node new EClass 1
        editor.getEditPart(NEW_ECLASS_1, AbstractDiagramListEditPart.class).select();
        // Retrieve position of new EClass 1 and new EClass 2
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NEW_ECLASS_1, AbstractDiagramListEditPart.class);
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NEW_ECLASS_2, AbstractDiagramListEditPart.class);
        // Drag and drop new EClass 1 near EClass 2
        Point location = editor.getAbsoluteLocation((GraphicalEditPart) root1EditPart.part());
        int deltaY = location.y + 20;
        Point newLocation = new Point(editor.getAbsoluteLocation((GraphicalEditPart) root2EditPart.part()).x, deltaY);
        editor.drag(root2EditPart, newLocation);
        // Check that sourceDecorator is over vertical line and targetDecorator
        // is under line
        int sourceLineBracket = edgePoints(editor, NEW_ECLASS_1, NEW_ECLASS_2).getFirstPoint().y;
        int targetLineBracket = edgePoints(editor, NEW_ECLASS_1, NEW_ECLASS_2).getLastPoint().y;
        Rectangle sourceDecoratorBounds = ((ViewEdgeFigure) ((GraphicalEditPart) bracket1EditPart.part()).getFigure()).getSourceDecoration().getBounds().getCopy();
        Rectangle targetDecoratorBounds = ((ViewEdgeFigure) ((GraphicalEditPart) bracket1EditPart.part()).getFigure()).getTargetDecoration().getBounds().getCopy();
        assertEquals("The source decorator should be over sourceLineBracket", true, sourceDecoratorBounds.y < sourceLineBracket);
        assertEquals("The target decorator should be under targetLineBracket", true, (targetDecoratorBounds.y + targetDecoratorBounds.height) > targetLineBracket);
        // Undo
        undo(SET_LOCATION_SIZE);
        checkPositionEdge(editor, NEW_ECLASS_1, NEW_ECLASS_2, point0.x, point0.y, point1.x, point1.y, point2.x, point2.y, point3.x, point3.y, point4.x, point4.y, point5.x, point5.y);
        // Redo
        redo(SET_LOCATION_SIZE);
        // Check that sourceDecorator is over vertical line and targetDecorator
        // is under line
        assertEquals("The source decorator should be over sourceLineBracket", true, sourceDecoratorBounds.y < sourceLineBracket);
        assertEquals("The target decorator should be under targetLineBracket", true, (targetDecoratorBounds.y + targetDecoratorBounds.height) > targetLineBracket);
        // // Save and close representation
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        // Open editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        // Check that sourceDecorator is over vertical line and targetDecorator
        // is under line
        assertEquals("The source decorator should be over sourceLineBracket", true, sourceDecoratorBounds.y < sourceLineBracket);
        assertEquals("The target decorator should be under targetLineBracket", true, (targetDecoratorBounds.y + targetDecoratorBounds.height) > targetLineBracket);
    }

    /**
     * Change line style in VSM and check that modifications are changed in
     * representation and in properties view
     */
    public void testChangeLineStyleInOdesign() {
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(MODELER_RESOURCE_NAME);
        // expand the tree : EReference Mapping
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(DIAGRAM_DESCRIPTION_NAME).expandNode("Default").expandNode("EReferenceMapping")
                .expandNode("Bracket Edge Style solid").select();
        // accesses to property view
        bot.viewByTitle(PROPERTIES).setFocus();
        // accesses to tab Decorators
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL);
        // Change Source arrow decorators
        SWTBotCCombo lineStyle = bot.viewByTitle(PROPERTIES).bot().ccomboBox();
        lineStyle.setSelection(2);
        assertEquals("Line style should be dot", "dot", lineStyle.getText());
        odesignEditor.save();
        openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        // In SWTBOt must do refresh
        editor.refresh();
        // Check that line style are set correctly
        initializeEdgeBracketPositionPoint(NEW_EREFERENCE_1).select();
        propertiesBot = bot.viewByTitle(PROPERTIES);
        SWTBotSiriusHelper.selectPropertyTabItem("Style");
        tree = propertiesBot.bot().tree();
        tree.expandNode("General").select().getNode("Line Style").doubleClick();
        assertEquals("The line style should be dot", "dot", propertiesBot.bot().ccomboBox().getText());

    }

    /**
     * Test that {@link BracketEdgeStyleDescription} is allowed in the odesign
     * editor.
     */
    public void testBracketEdgeStyleDescriptionCreation() {
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(MODELER_RESOURCE_NAME);
        // expand the tree : EReference Mapping
        SWTBotTree tree = odesignEditor.bot().tree();
        SWTBotTreeItem mappingTreeItem = tree.expandNode(ODESIGN).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(DIAGRAM_DESCRIPTION_NAME).expandNode("Default")
                .expandNode("EReferenceMapping");
        SWTBotTreeItem bracketEdgeStyleDescriptionTreeItem = mappingTreeItem.expandNode("Bracket Edge Style solid").select();
        SWTBotUtils.clickContextMenu(bracketEdgeStyleDescriptionTreeItem, "Delete");
        SWTBotUtils.waitAllUiEvents();
        assertEquals(0, mappingTreeItem.getItems().length);
        SWTBotUtils.clickContextMenu(mappingTreeItem, "Bracket Edge Style");
        SWTBotUtils.waitAllUiEvents();
        assertEquals("A new Bracket Edge Style should be created", 1, mappingTreeItem.getItems().length);
        bracketEdgeStyleDescriptionTreeItem = mappingTreeItem.expandNode("Bracket Edge Style solid").select();
        SWTBotUtils.waitAllUiEvents();
        SWTBotUtils.clickContextMenu(bracketEdgeStyleDescriptionTreeItem, "Begin Label Style");
        SWTBotUtils.waitAllUiEvents();
        bracketEdgeStyleDescriptionTreeItem.select();
        assertFalse("Center label should already be created.", SWTBotUtils.isContextMenuEnabled(bracketEdgeStyleDescriptionTreeItem, "Center Label Style"));
        SWTBotUtils.waitAllUiEvents();
        bracketEdgeStyleDescriptionTreeItem.select();
        SWTBotUtils.clickContextMenu(bracketEdgeStyleDescriptionTreeItem, "End Label Style");
        SWTBotUtils.waitAllUiEvents();
        bracketEdgeStyleDescriptionTreeItem.select();
        assertEquals("The begin/center/end label style should be created", 3, bracketEdgeStyleDescriptionTreeItem.getItems().length);

        odesignEditor.save();
        editor.setFocus();
        editor.save();
    }

    private void reconnectBracketEdge(String source, String target, String connection, String newSourceNodeReconnect) {
        SWTBotGefEditPart bracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList bracketEdgePointList = getPointList((BracketEdgeEditPart) bracketEdgeEditPartBot.part());

        SWTBotGefEditPart targetEditPartBot = editor.getEditPart(target, AbstractDiagramListEditPart.class);
        Rectangle targetEditPartBounds = editor.getBounds(targetEditPartBot);
        SWTBotGefEditPart newSourceEditPartBot = editor.getEditPart(newSourceNodeReconnect, AbstractDiagramListEditPart.class);
        Rectangle newSourceEditPartBounds = editor.getBounds(newSourceEditPartBot);

        // Move bracketEdge
        bracketEdgeEditPartBot.select();
        Point dragLocation = bracketEdgePointList.getFirstPoint();
        Point dropLocation = newSourceEditPartBounds.getCenter();
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(bracketEdgeEditPartBot);
        editor.drag(dragLocation, dropLocation);
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        bracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList bracketEdgePointListAfterReconnect = getPointList((BracketEdgeEditPart) bracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", bracketEdgePointList.size(), bracketEdgePointListAfterReconnect.size());
        assertEquals(newSourceEditPartBounds.getRight(), bracketEdgePointListAfterReconnect.getPoint(0));
        assertEquals(newSourceEditPartBounds.getRight().getTranslated(BracketConnectionQuery.DEFAULT_OFFSET + BracketConnectionQuery.DECO_OFFSET, 0), bracketEdgePointListAfterReconnect.getPoint(1));
        assertEquals(newSourceEditPartBounds.getRight().getTranslated(BracketConnectionQuery.DEFAULT_OFFSET, 0), bracketEdgePointListAfterReconnect.getPoint(2));
        Point targetPoint = bracketEdgePointListAfterReconnect.getPoint(2);
        targetPoint.y = targetEditPartBounds.getRight().y;
        assertEquals(targetPoint, bracketEdgePointListAfterReconnect.getPoint(3));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(3).getTranslated(BracketConnectionQuery.DECO_OFFSET, 0), bracketEdgePointListAfterReconnect.getPoint(4));
        assertEquals(targetEditPartBounds.getRight(), bracketEdgePointListAfterReconnect.getPoint(5));

        // Undo
        undo(localSession.getOpenedSession());
        SWTBotUtils.waitAllUiEvents();

        // Check
        bracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList bracketEdgePointListAfterUndo = getPointList((BracketEdgeEditPart) bracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", bracketEdgePointList.size(), bracketEdgePointListAfterUndo.size());
        assertEquals(bracketEdgePointList.getPoint(0), bracketEdgePointListAfterUndo.getPoint(0));
        assertEquals(bracketEdgePointList.getPoint(1), bracketEdgePointListAfterUndo.getPoint(1));
        assertEquals(bracketEdgePointList.getPoint(2), bracketEdgePointListAfterUndo.getPoint(2));
        assertEquals(bracketEdgePointList.getPoint(3), bracketEdgePointListAfterUndo.getPoint(3));
        assertEquals(bracketEdgePointList.getPoint(4), bracketEdgePointListAfterUndo.getPoint(4));
        assertEquals(bracketEdgePointList.getPoint(5), bracketEdgePointListAfterUndo.getPoint(5));

        // Redo
        redo(localSession.getOpenedSession());
        SWTBotUtils.waitAllUiEvents();

        // Check
        bracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList bracketEdgePointListAfterRedo = getPointList((BracketEdgeEditPart) bracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", bracketEdgePointListAfterReconnect.size(), bracketEdgePointListAfterRedo.size());
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(0), bracketEdgePointListAfterRedo.getPoint(0));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(1), bracketEdgePointListAfterRedo.getPoint(1));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(2), bracketEdgePointListAfterRedo.getPoint(2));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(3), bracketEdgePointListAfterRedo.getPoint(3));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(4), bracketEdgePointListAfterRedo.getPoint(4));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(5), bracketEdgePointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        bracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList bracketEdgePointListAfterReopening = getPointList((BracketEdgeEditPart) bracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", bracketEdgePointListAfterReconnect.size(), bracketEdgePointListAfterReopening.size());
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(0), bracketEdgePointListAfterReopening.getPoint(0));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(1), bracketEdgePointListAfterReopening.getPoint(1));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(2), bracketEdgePointListAfterReopening.getPoint(2));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(3), bracketEdgePointListAfterReopening.getPoint(3));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(4), bracketEdgePointListAfterReopening.getPoint(4));
        assertEquals(bracketEdgePointListAfterReconnect.getPoint(5), bracketEdgePointListAfterReopening.getPoint(5));

    }

    private void createBracketEdgeOnEdge(String sourceName, String targetName, String eAnnotation, String connection) {
        SWTBotGefEditPart eAnnotationEditPartBot = editor.getEditPart(eAnnotation, AbstractDiagramNodeEditPart.class);
        Rectangle eAnnotationEditPartBounds = editor.getBounds(eAnnotationEditPartBot);
        SWTBotGefEditPart targetEdgeEditPartBot = editor.getEditPart(connection, AbstractDiagramEdgeEditPart.class);
        PointList targetEdgePointList = getPointList((AbstractDiagramEdgeEditPart) targetEdgeEditPartBot.part());

        // Create the bracketEdge on bracketEdge
        editor.activateTool(BRACKET_EDGE_ON_BRACKET_EDGE_CREATION_TOOL);
        Point firstClick = eAnnotationEditPartBounds.getCenter();
        Point lastClick = targetEdgePointList.getMidpoint();
        editor.click(firstClick);
        editor.click(lastClick);

        // Check
        SWTBotGefEditPart bracketEdgeOnBracketEdgeEditPartBot = editor.getEditPart("eAnnotationToBracket", BracketEdgeEditPart.class);
        BracketEdgeEditPart bracketEdgeOnBracketEdgeEditPart = (BracketEdgeEditPart) bracketEdgeOnBracketEdgeEditPartBot.part();
        // return bracketEdgeOnBracketEdgeEditPartBot;
        assertNotNull("A bracket edge should be created between eAnnotation and bracketEdge", bracketEdgeOnBracketEdgeEditPartBot);
        PointList bracketEdgeOnBracketEdgePointList = getPointList(bracketEdgeOnBracketEdgeEditPart);
        assertEquals("The created bracket edge must be placed on the right side of the source EAnnotation", eAnnotationEditPartBounds.getRight(), bracketEdgeOnBracketEdgePointList.getPoint(0));
        assertEquals(eAnnotationEditPartBounds.getRight().getTranslated(BracketConnectionQuery.DEFAULT_OFFSET + BracketConnectionQuery.DECO_OFFSET, 0), bracketEdgeOnBracketEdgePointList.getPoint(1));
        assertEquals(eAnnotationEditPartBounds.getRight().getTranslated(BracketConnectionQuery.DEFAULT_OFFSET, 0), bracketEdgeOnBracketEdgePointList.getPoint(2));
        assertEquals(lastClick.getCopy().setLocation(bracketEdgeOnBracketEdgePointList.getPoint(2).x, lastClick.y), bracketEdgeOnBracketEdgePointList.getPoint(3));
        Point targetDecoPoint = bracketEdgeOnBracketEdgePointList.getPoint(3);
        if (lastClick.x < firstClick.x + BracketConnectionQuery.DEFAULT_OFFSET) {
            targetDecoPoint = targetDecoPoint.getTranslated(BracketConnectionQuery.DECO_OFFSET, 0);
        } else {
            targetDecoPoint = targetDecoPoint.getTranslated(-BracketConnectionQuery.DECO_OFFSET, 0);
        }
        assertEquals(targetDecoPoint, bracketEdgeOnBracketEdgePointList.getPoint(4));
        assertEquals("The created bracket edge last point must be placed on midpoint of target bracket edge " + connection, lastClick, bracketEdgeOnBracketEdgePointList.getPoint(5));

        // Undo
        undo(BRACKET_EDGE_ON_BRACKET_EDGE_CREATION_TOOL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        try {
            bracketEdgeOnBracketEdgeEditPartBot = null;
            bracketEdgeOnBracketEdgeEditPartBot = editor.getEditPart("eAnnotationToBracket", BracketEdgeEditPart.class);
        } catch (Exception e) {

        } finally {
            assertNull("A bracket edge creation should undoed", bracketEdgeOnBracketEdgeEditPartBot);
        }

        // Redo
        redo(BRACKET_EDGE_ON_BRACKET_EDGE_CREATION_TOOL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        bracketEdgeOnBracketEdgeEditPartBot = editor.getEditPart("eAnnotationToBracket", BracketEdgeEditPart.class);
        assertNotNull("A bracket edge should be created between eAnnotation and bracketEdge", bracketEdgeOnBracketEdgeEditPartBot);
        PointList bracketEdgeOnBracketEdgePointListAfterRedo = getPointList((BracketEdgeEditPart) bracketEdgeOnBracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", bracketEdgeOnBracketEdgePointList.size(), bracketEdgeOnBracketEdgePointListAfterRedo.size());
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(0), bracketEdgeOnBracketEdgePointListAfterRedo.getPoint(0));
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(1), bracketEdgeOnBracketEdgePointListAfterRedo.getPoint(1));
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(2), bracketEdgeOnBracketEdgePointListAfterRedo.getPoint(2));
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(3), bracketEdgeOnBracketEdgePointListAfterRedo.getPoint(3));
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(4), bracketEdgeOnBracketEdgePointListAfterRedo.getPoint(4));
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(5), bracketEdgeOnBracketEdgePointListAfterRedo.getPoint(5));

        // Check after reopeningt
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        bracketEdgeOnBracketEdgeEditPartBot = editor.getEditPart("eAnnotationToBracket", BracketEdgeEditPart.class);
        PointList bracketEdgeOnBracketEdgePointListAfterReopening = getPointList((BracketEdgeEditPart) bracketEdgeOnBracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", bracketEdgeOnBracketEdgePointList.size(), bracketEdgeOnBracketEdgePointListAfterReopening.size());
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(0), bracketEdgeOnBracketEdgePointListAfterReopening.getPoint(0));
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(1), bracketEdgeOnBracketEdgePointListAfterReopening.getPoint(1));
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(2), bracketEdgeOnBracketEdgePointListAfterReopening.getPoint(2));
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(3), bracketEdgeOnBracketEdgePointListAfterReopening.getPoint(3));
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(4), bracketEdgeOnBracketEdgePointListAfterReopening.getPoint(4));
        assertEquals(bracketEdgeOnBracketEdgePointList.getPoint(5), bracketEdgeOnBracketEdgePointListAfterReopening.getPoint(5));

    }

    private void rotateBracketToTop(String source, String target, String connection) {
        SWTBotGefEditPart newEClass1EditPartBot = editor.getEditPart(source, AbstractDiagramListEditPart.class);
        Rectangle newEClass1Bounds = editor.getBounds(newEClass1EditPartBot);
        SWTBotGefEditPart newEClass2EditPartBot = editor.getEditPart(target, AbstractDiagramListEditPart.class);
        Rectangle newEClass2Bounds = editor.getBounds(newEClass2EditPartBot);

        SWTBotGefEditPart newEReference1BracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList newEReference1PointList = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());

        // Move newEReference1
        newEReference1BracketEdgeEditPartBot.select();
        int xDelta = -100;
        int yDelta = -100;
        Point dragLocation = newEReference1PointList.getPoint(BracketConnectionQuery.ORIGIN_POINT_INDEX);
        Point dropLocation = dragLocation.getTranslated(xDelta, yDelta);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEReference1BracketEdgeEditPartBot);
        editor.drag(dragLocation, dropLocation);
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        PointList newEReference1PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterDrop.size());
        assertEquals(newEClass1Bounds.getTop(), newEReference1PointListAfterDrop.getPoint(0));
        assertEquals(new Point(newEClass1Bounds.getTop().x, dropLocation.y - BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterDrop.getPoint(1));
        assertEquals(new Point(newEClass1Bounds.getTop().x, dropLocation.y), newEReference1PointListAfterDrop.getPoint(2));
        assertEquals(new Point(newEClass2Bounds.getTop().x, dropLocation.y), newEReference1PointListAfterDrop.getPoint(3));
        assertEquals(new Point(newEClass2Bounds.getTop().x, dropLocation.y - BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterDrop.getPoint(4));
        assertEquals(newEClass2Bounds.getTop(), newEReference1PointListAfterDrop.getPoint(5));

        // Undo
        undo(BracketBendpointEditPolicy.ROTATE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterUndo.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterUndo.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1), newEReference1PointListAfterUndo.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2), newEReference1PointListAfterUndo.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3), newEReference1PointListAfterUndo.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4), newEReference1PointListAfterUndo.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterUndo.getPoint(5));

        // Redo
        redo(BracketBendpointEditPolicy.ROTATE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterRedo.size());
        assertEquals(newEClass1Bounds.getTop(), newEReference1PointListAfterRedo.getPoint(0));
        assertEquals(new Point(newEClass1Bounds.getTop().x, dropLocation.y - BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterRedo.getPoint(1));
        assertEquals(new Point(newEClass1Bounds.getTop().x, dropLocation.y), newEReference1PointListAfterRedo.getPoint(2));
        assertEquals(new Point(newEClass2Bounds.getTop().x, dropLocation.y), newEReference1PointListAfterRedo.getPoint(3));
        assertEquals(new Point(newEClass2Bounds.getTop().x, dropLocation.y - BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterRedo.getPoint(4));
        assertEquals(newEClass2Bounds.getTop(), newEReference1PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        newEReference1BracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList newEReference1PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterReopening.size());
        assertEquals(newEClass1Bounds.getTop(), newEReference1PointListAfterReopening.getPoint(0));
        assertEquals(new Point(newEClass1Bounds.getTop().x, dropLocation.y - BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterReopening.getPoint(1));
        assertEquals(new Point(newEClass1Bounds.getTop().x, dropLocation.y), newEReference1PointListAfterReopening.getPoint(2));
        assertEquals(new Point(newEClass2Bounds.getTop().x, dropLocation.y), newEReference1PointListAfterReopening.getPoint(3));
        assertEquals(new Point(newEClass2Bounds.getTop().x, dropLocation.y - BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterReopening.getPoint(4));
        assertEquals(newEClass2Bounds.getTop(), newEReference1PointListAfterReopening.getPoint(5));

    }

    private void rotateBracketToBottom(String source, String target, String connection) {
        SWTBotGefEditPart newEClass1EditPartBot = editor.getEditPart(source, AbstractDiagramListEditPart.class);
        Rectangle newEClass1Bounds = editor.getBounds(newEClass1EditPartBot);
        SWTBotGefEditPart newEClass2EditPartBot = editor.getEditPart(target, AbstractDiagramListEditPart.class);
        Rectangle newEClass2Bounds = editor.getBounds(newEClass2EditPartBot);

        SWTBotGefEditPart newEReference1BracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList newEReference1PointList = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());

        // Move newEReference1
        newEReference1BracketEdgeEditPartBot.select();
        int xDelta = -100;
        int yDelta = 100;
        Point dragLocation = newEReference1PointList.getPoint(BracketConnectionQuery.TARGET_POINT_INDEX);
        Point dropLocation = dragLocation.getTranslated(xDelta, yDelta);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEReference1BracketEdgeEditPartBot);
        editor.drag(dragLocation, dropLocation);
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        PointList newEReference1PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterDrop.size());
        assertEquals(newEClass1Bounds.getBottom(), newEReference1PointListAfterDrop.getPoint(0));
        assertEquals(new Point(newEClass1Bounds.getBottom().x, dropLocation.y + BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterDrop.getPoint(1));
        assertEquals(new Point(newEClass1Bounds.getBottom().x, dropLocation.y), newEReference1PointListAfterDrop.getPoint(2));
        assertEquals(new Point(newEClass2Bounds.getBottom().x, dropLocation.y), newEReference1PointListAfterDrop.getPoint(3));
        assertEquals(new Point(newEClass2Bounds.getBottom().x, dropLocation.y + BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterDrop.getPoint(4));
        assertEquals(newEClass2Bounds.getBottom(), newEReference1PointListAfterDrop.getPoint(5));

        // Undo
        undo(BracketBendpointEditPolicy.ROTATE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterUndo.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterUndo.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1), newEReference1PointListAfterUndo.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2), newEReference1PointListAfterUndo.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3), newEReference1PointListAfterUndo.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4), newEReference1PointListAfterUndo.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterUndo.getPoint(5));

        // Redo
        redo(BracketBendpointEditPolicy.ROTATE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterRedo.size());
        assertEquals(newEClass1Bounds.getBottom(), newEReference1PointListAfterRedo.getPoint(0));
        assertEquals(new Point(newEClass1Bounds.getBottom().x, dropLocation.y + BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterRedo.getPoint(1));
        assertEquals(new Point(newEClass1Bounds.getBottom().x, dropLocation.y), newEReference1PointListAfterRedo.getPoint(2));
        assertEquals(new Point(newEClass2Bounds.getBottom().x, dropLocation.y), newEReference1PointListAfterRedo.getPoint(3));
        assertEquals(new Point(newEClass2Bounds.getBottom().x, dropLocation.y + BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterRedo.getPoint(4));
        assertEquals(newEClass2Bounds.getBottom(), newEReference1PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        newEReference1BracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList newEReference1PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterReopening.size());
        assertEquals(newEClass1Bounds.getBottom(), newEReference1PointListAfterReopening.getPoint(0));
        assertEquals(new Point(newEClass1Bounds.getBottom().x, dropLocation.y + BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterReopening.getPoint(1));
        assertEquals(new Point(newEClass1Bounds.getBottom().x, dropLocation.y), newEReference1PointListAfterReopening.getPoint(2));
        assertEquals(new Point(newEClass2Bounds.getBottom().x, dropLocation.y), newEReference1PointListAfterReopening.getPoint(3));
        assertEquals(new Point(newEClass2Bounds.getBottom().x, dropLocation.y + BracketConnectionQuery.DECO_OFFSET), newEReference1PointListAfterReopening.getPoint(4));
        assertEquals(newEClass2Bounds.getBottom(), newEReference1PointListAfterReopening.getPoint(5));

    }

    private void rotateBracketToLeft(String source, String target, String connection) {
        SWTBotGefEditPart newEClass1EditPartBot = editor.getEditPart(source, AbstractDiagramListEditPart.class);
        Rectangle newEClass1Bounds = editor.getBounds(newEClass1EditPartBot);
        SWTBotGefEditPart newEClass2EditPartBot = editor.getEditPart(target, AbstractDiagramListEditPart.class);
        Rectangle newEClass2Bounds = editor.getBounds(newEClass2EditPartBot);

        SWTBotGefEditPart newEReference1BracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList newEReference1PointList = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());

        // Move newEReference1
        newEReference1BracketEdgeEditPartBot.select();
        int xDelta = -300;
        Point dragLocation = newEReference1PointList.getPoint(BracketConnectionQuery.TARGET_POINT_INDEX);
        Point dropLocation = dragLocation.getTranslated(xDelta, 0);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEReference1BracketEdgeEditPartBot);
        editor.drag(dragLocation, dropLocation);
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        PointList newEReference1PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterDrop.size());
        assertEquals(newEClass1Bounds.getLeft(), newEReference1PointListAfterDrop.getPoint(0));
        assertEquals(new Point(dropLocation.x - BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(1).y), newEReference1PointListAfterDrop.getPoint(1));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(2).y), newEReference1PointListAfterDrop.getPoint(2));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(3).y), newEReference1PointListAfterDrop.getPoint(3));
        assertEquals(new Point(dropLocation.x - BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(4).y), newEReference1PointListAfterDrop.getPoint(4));
        assertEquals(newEClass2Bounds.getLeft(), newEReference1PointListAfterDrop.getPoint(5));

        // Undo
        undo(BracketBendpointEditPolicy.ROTATE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterUndo.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterUndo.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1), newEReference1PointListAfterUndo.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2), newEReference1PointListAfterUndo.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3), newEReference1PointListAfterUndo.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4), newEReference1PointListAfterUndo.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterUndo.getPoint(5));

        // Redo
        redo(BracketBendpointEditPolicy.ROTATE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterRedo.size());
        assertEquals(newEClass1Bounds.getLeft(), newEReference1PointListAfterRedo.getPoint(0));
        assertEquals(new Point(dropLocation.x - BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(1).y), newEReference1PointListAfterRedo.getPoint(1));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(2).y), newEReference1PointListAfterRedo.getPoint(2));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(3).y), newEReference1PointListAfterRedo.getPoint(3));
        assertEquals(new Point(dropLocation.x - BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(4).y), newEReference1PointListAfterRedo.getPoint(4));
        assertEquals(newEClass2Bounds.getLeft(), newEReference1PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        newEReference1BracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList newEReference1PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterReopening.size());
        assertEquals(newEClass1Bounds.getLeft(), newEReference1PointListAfterReopening.getPoint(0));
        assertEquals(new Point(dropLocation.x - BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(1).y), newEReference1PointListAfterReopening.getPoint(1));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(2).y), newEReference1PointListAfterReopening.getPoint(2));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(3).y), newEReference1PointListAfterReopening.getPoint(3));
        assertEquals(new Point(dropLocation.x - BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(4).y), newEReference1PointListAfterReopening.getPoint(4));
        assertEquals(newEClass2Bounds.getLeft(), newEReference1PointListAfterReopening.getPoint(5));

    }

    private void rotateBracketToRight(String source, String target, String connection) {
        SWTBotGefEditPart newEClass1EditPartBot = editor.getEditPart(source, AbstractDiagramListEditPart.class);
        Rectangle newEClass1Bounds = editor.getBounds(newEClass1EditPartBot);
        SWTBotGefEditPart newEClass2EditPartBot = editor.getEditPart(target, AbstractDiagramListEditPart.class);
        Rectangle newEClass2Bounds = editor.getBounds(newEClass2EditPartBot);

        SWTBotGefEditPart newEReference1BracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList newEReference1PointList = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());

        // Move newEReference1
        newEReference1BracketEdgeEditPartBot.select();
        int xDelta = 50;
        Point dragLocation = newEReference1PointList.getPoint(BracketConnectionQuery.TARGET_POINT_INDEX);
        Point dropLocation = dragLocation.getTranslated(xDelta, 0);
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(newEReference1BracketEdgeEditPartBot);
        editor.drag(dragLocation, dropLocation);
        bot.waitUntil(checkEditPartMoved);
        SWTBotUtils.waitAllUiEvents();

        // Check bracket edge new pointList
        PointList newEReference1PointListAfterDrop = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterDrop.size());
        assertEquals(newEClass1Bounds.getRight(), newEReference1PointListAfterDrop.getPoint(0));
        assertEquals(new Point(dropLocation.x + BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(1).y), newEReference1PointListAfterDrop.getPoint(1));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(2).y), newEReference1PointListAfterDrop.getPoint(2));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(3).y), newEReference1PointListAfterDrop.getPoint(3));
        assertEquals(new Point(dropLocation.x + BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(4).y), newEReference1PointListAfterDrop.getPoint(4));
        assertEquals(newEClass2Bounds.getRight(), newEReference1PointListAfterDrop.getPoint(5));

        // Undo
        undo(BracketBendpointEditPolicy.ROTATE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterUndo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterUndo.size());
        assertEquals(newEReference1PointList.getPoint(0), newEReference1PointListAfterUndo.getPoint(0));
        assertEquals(newEReference1PointList.getPoint(1), newEReference1PointListAfterUndo.getPoint(1));
        assertEquals(newEReference1PointList.getPoint(2), newEReference1PointListAfterUndo.getPoint(2));
        assertEquals(newEReference1PointList.getPoint(3), newEReference1PointListAfterUndo.getPoint(3));
        assertEquals(newEReference1PointList.getPoint(4), newEReference1PointListAfterUndo.getPoint(4));
        assertEquals(newEReference1PointList.getPoint(5), newEReference1PointListAfterUndo.getPoint(5));

        // Redo
        redo(BracketBendpointEditPolicy.ROTATE_COMMAND_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Check
        PointList newEReference1PointListAfterRedo = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterRedo.size());
        assertEquals(newEClass1Bounds.getRight(), newEReference1PointListAfterRedo.getPoint(0));
        assertEquals(new Point(dropLocation.x + BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(1).y), newEReference1PointListAfterRedo.getPoint(1));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(2).y), newEReference1PointListAfterRedo.getPoint(2));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(3).y), newEReference1PointListAfterRedo.getPoint(3));
        assertEquals(new Point(dropLocation.x + BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(4).y), newEReference1PointListAfterRedo.getPoint(4));
        assertEquals(newEClass2Bounds.getRight(), newEReference1PointListAfterRedo.getPoint(5));

        // Check after reopening
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        newEReference1BracketEdgeEditPartBot = editor.getEditPart(connection, BracketEdgeEditPart.class);
        PointList newEReference1PointListAfterReopening = getPointList((BracketEdgeEditPart) newEReference1BracketEdgeEditPartBot.part());
        assertEquals("We should have the same number of points", newEReference1PointList.size(), newEReference1PointListAfterReopening.size());
        assertEquals(newEClass1Bounds.getRight(), newEReference1PointListAfterReopening.getPoint(0));
        assertEquals(new Point(dropLocation.x + BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(1).y), newEReference1PointListAfterReopening.getPoint(1));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(2).y), newEReference1PointListAfterReopening.getPoint(2));
        assertEquals(new Point(dropLocation.x, newEReference1PointList.getPoint(3).y), newEReference1PointListAfterReopening.getPoint(3));
        assertEquals(new Point(dropLocation.x + BracketConnectionQuery.DECO_OFFSET, newEReference1PointList.getPoint(4).y), newEReference1PointListAfterReopening.getPoint(4));
        assertEquals(newEClass2Bounds.getRight(), newEReference1PointListAfterReopening.getPoint(5));

    }

    private void checkPositionEdge(SWTBotSiriusDiagramEditor editor, String source, String target, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5) {
        PointList edgePoints = edgePoints(editor, source, target);
        // This points correspond to edge bendpoints. Point0 correspond to
        // source and point3 correspond to target.
        Point point0 = new Point(x0, y0);
        Point point1 = new Point(x1, y1);
        Point point2 = new Point(x2, y2);
        Point point3 = new Point(x3, y3);
        Point point4 = new Point(x4, y4);
        Point point5 = new Point(x5, y5);
        assertEquals("The point must be have coordinates x :" + point0.x + " and y : " + point0.y, point0, edgePoints.getPoint(0));
        assertEquals("The point must be have coordinates x :" + point1.x + " and y : " + point1.y, point1, edgePoints.getPoint(1));
        assertEquals("The point must be have coordinates x :" + point2.x + " and y : " + point2.y, point2, edgePoints.getPoint(2));
        assertEquals("The point must be have coordinates x :" + point3.x + " and y : " + point3.y, point3, edgePoints.getPoint(3));
        assertEquals("The point must be have coordinates x :" + point4.x + " and y : " + point4.y, point4, edgePoints.getPoint(4));
        assertEquals("The point must be have coordinates x :" + point5.x + " and y : " + point5.y, point5, edgePoints.getPoint(5));
    }

    private PointList edgePoints(SWTBotSiriusDiagramEditor editor, String sourceEditPartName, String targetEditPartName) {
        ConnectionEditPart connectionEditPart = getConnectionEditPart(editor, sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        return ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();

    }

    private ConnectionEditPart getConnectionEditPart(SWTBotSiriusDiagramEditor editor, String sourceEditPartName, String targetEditPartName) {
        assertNotNull("There is no connection between " + sourceEditPartName + " and " + targetEditPartName, getConnectionEditPartList(editor, sourceEditPartName, targetEditPartName));
        assertEquals("There are more or less than 1 connection between " + sourceEditPartName + " and " + targetEditPartName, 1,
                getConnectionEditPartList(editor, sourceEditPartName, targetEditPartName).size());
        return getConnectionEditPartList(editor, sourceEditPartName, targetEditPartName).get(0).part();
    }

    private List<SWTBotGefConnectionEditPart> getConnectionEditPartList(SWTBotSiriusDiagramEditor editor, String sourceEditPart, String targetEditPart) {
        return editor.getConnectionEditPart(editor.getEditPart(sourceEditPart, AbstractDiagramListEditPart.class), editor.getEditPart(targetEditPart, AbstractDiagramListEditPart.class));
    }

    private SWTBotGefEditPart initializeEdgeBracketPositionPoint(String bracketEdgeName) {
        SWTBotGefEditPart bracketEdgeEditPartBot = editor.getEditPart(bracketEdgeName, BracketEdgeEditPart.class);
        BracketEdgeEditPart bracketEdgeEditPart = (BracketEdgeEditPart) bracketEdgeEditPartBot.part();
        Connection connectionFigure = bracketEdgeEditPart.getConnectionFigure();
        PointList pointList = connectionFigure.getPoints();
        point0 = pointList.getPoint(0);
        point1 = pointList.getPoint(1);
        point2 = pointList.getPoint(2);
        point3 = pointList.getPoint(3);
        point4 = pointList.getPoint(4);
        point5 = pointList.getPoint(5);
        return bracketEdgeEditPartBot;
    }

    private PointList getPointList(AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart) {
        PointList pointList = null;
        Connection connectionFigure = abstractDiagramEdgeEditPart.getConnectionFigure();
        pointList = connectionFigure.getPoints();
        return pointList;
    }

    @Override
    protected void tearDown() throws Exception {
        editor.click(0, 0);
        editor.close();
        propertiesBot = null;
        uiDiagramRepresentation = null;
        super.tearDown();
    }
}
