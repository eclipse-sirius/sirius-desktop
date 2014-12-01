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

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.business.api.query.AbstractDNodeQuery;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationRedoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationUndoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TransactionClosedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.WithDRepresentationElementType;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test resize kind refresh (VP-2564).
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ResizeKindRefreshTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/refresh/resizeKind/VP-2564/";

    private static final String SEMANTIC_RESOURCE_FILENAME = "VP-2564.ecore";

    private static final String SESSION_RESOURCE_FILENAME = "VP-2564.aird";

    private static final String MODELER_RESOURCE_FILENAME = "VP-2564.odesign";

    private static final String REPRESENTATION_INSTANCE_NAME = "new VP-2564_Diagram";

    private static final String REPRESENTATION_NAME = "VP-2564_Diagram";

    private Resource modelerResource;

    private List<SWTBotGefEditPart> dNodeEditPartBots;

    private Map<SWTBotGefEditPart, Rectangle> initialsBounds;

    /**
     * Initialize the {@link NodeStyleDescription} test fields. The odesign
     * reference in another ResourseSet to simulate odesign editing in the
     * odesign editor.
     */
    private void initializeNodeStyleDescriptions() {
        ResourceSet resourceSet = new ResourceSetImpl();
        Viewpoint viewpoint = localSession.getOpenedSession().getSelectedViewpoints(false).iterator().next();
        URI modelerResourceURI = viewpoint.eResource().getURI();
        modelerResource = resourceSet.getResource(modelerResourceURI, true);
    }

    /**
     * Initialize the SWTBot fields to be used for the resize tests.
     */
    private void initializeEditPartBots() {
        SWTBotGefEditPart rootEditPartBot = editor.rootEditPart();

        dNodeEditPartBots = rootEditPartBot.descendants(new WithDRepresentationElementType<DNode>(DNode.class));
    }

    private void initializeInitialDNodesBounds() {
        initialsBounds = new HashMap<SWTBotGefEditPart, Rectangle>();
        for (SWTBotGefEditPart dNodeEditPartBot : dNodeEditPartBots) {
            Rectangle initialBounds = editor.getAbsoluteBounds(dNodeEditPartBot);
            initialsBounds.put(dNodeEditPartBot, initialBounds);
        }
    }

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + SEMANTIC_RESOURCE_FILENAME, getProjectName() + "/" + SEMANTIC_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + MODELER_RESOURCE_FILENAME, getProjectName() + "/" + MODELER_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + SESSION_RESOURCE_FILENAME, getProjectName() + "/" + SESSION_RESOURCE_FILENAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILENAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        editor.maximize();
        editor.setSnapToGrid(false);
        editor.setFocus();

        initializeNodeStyleDescriptions();
        initializeEditPartBots();
        initializeInitialDNodesBounds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean getAutoRefreshMode() {
        return false;
    }

    /**
     * Test that resize permission like defined in *.aird is correct according
     * to the modeler resizeKind definition for all resizeKind, by changing to
     * NONE, NSEW, NORTH_SOUTH, EAST_WEST, NSEW for all DNode in sequence.
     */
    public void testResizePermission() {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        // Checks just after the diagram opening
        assertResizeKindEquality();

        assertResizeKindEquality(ResizeKind.NSEW_LITERAL);

        checkResizability();

        for (SWTBotGefEditPart dNodeEditPartBot : dNodeEditPartBots) {
            for (ResizeKind resizeKind : ResizeKind.values()) {
                changeResizeKind(dNodeEditPartBot, resizeKind);

                checkInitialBounds(dNodeEditPartBot);

                assertResizeKindEquality(dNodeEditPartBot, resizeKind);

                checkResizability(Collections.singletonList(dNodeEditPartBot));

            }
        }

        changeResizeKind(ResizeKind.NSEW_LITERAL);

        checkInitialsBounds();

        assertResizeKindEquality(ResizeKind.NSEW_LITERAL);

        checkResizability();

    }

    private void checkInitialsBounds() {
        for (SWTBotGefEditPart dNodeEditPartBot : dNodeEditPartBots) {
            checkInitialBounds(dNodeEditPartBot);
        }
    }

    private void checkInitialBounds(SWTBotGefEditPart dNodeEditPartBot) {
        Rectangle currentBounds = editor.getAbsoluteBounds(dNodeEditPartBot);
        Rectangle initialBounds = initialsBounds.get(dNodeEditPartBot);
        DNode dNode = getDNode(dNodeEditPartBot);
        assertEquals("Changing the NodeStyleDescription.resizeKind should not change bounds of existing DNode for : " + dNode, initialBounds, currentBounds);
    }

    /**
     * Assert that resize kind in all {@link DNode}s is equal to their
     * {@link NodeStyleDescription}.
     */
    private void assertResizeKindEquality() {
        for (SWTBotGefEditPart dNodeEditPartBot : dNodeEditPartBots) {
            assertEquals("DNode.resizeKind should be equals to its NodeStyleDescription.resizeKind", getNodeStyleDescriptionResizeKind(dNodeEditPartBot), getDNodeResizeKind(dNodeEditPartBot));
        }
    }

    /**
     * Assert that resize kind in all {@link DNode}s is equal to a specified
     * ResizeKind.
     */
    private void assertResizeKindEquality(ResizeKind resizeKind) {
        for (SWTBotGefEditPart dNodeEditPartBot : dNodeEditPartBots) {
            assertResizeKindEquality(dNodeEditPartBot, resizeKind);
        }
    }

    private void assertResizeKindEquality(SWTBotGefEditPart dNodeEditPartBot, ResizeKind resizeKind) {
        assertEquals("DNode.resizeKind should be equals to  : " + resizeKind, getDNodeResizeKind(dNodeEditPartBot), getDNodeResizeKind(dNodeEditPartBot));
    }

    private ResizeKind getDNodeResizeKind(SWTBotGefEditPart dNodeEditPartBot) {
        DNode dNode = getDNode(dNodeEditPartBot);
        return dNode.getResizeKind();
    }

    private ResizeKind getNodeStyleDescriptionResizeKind(SWTBotGefEditPart dNodeEditPartBot) {
        DNode dNode = getDNode(dNodeEditPartBot);
        Style style = dNode.getStyle();
        StyleDescription styleDescription = style.getDescription();
        assertTrue(styleDescription instanceof NodeStyleDescription);
        NodeStyleDescription nodeStyleDescription = (NodeStyleDescription) styleDescription;
        return nodeStyleDescription.getResizeKind();
    }

    private NodeStyleDescription getNodeStyleDescription(SWTBotGefEditPart dNodeEditPartBot) {
        DNode dNode = getDNode(dNodeEditPartBot);
        Style style = dNode.getStyle();
        StyleDescription styleDescription = style.getDescription();
        assertTrue(styleDescription instanceof NodeStyleDescription);
        NodeStyleDescription nodeStyleDescription = (NodeStyleDescription) styleDescription;
        return nodeStyleDescription;
    }

    private DNode getDNode(SWTBotGefEditPart dNodeEditPartBot) {
        EditPart editPart = dNodeEditPartBot.part();
        Object model = editPart.getModel();
        assertTrue(model instanceof View);
        View view = (View) model;
        EObject element = view.getElement();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("This is not a DNode: " + element, element instanceof DNode);
        DNode dNode = (DNode) element;
        return dNode;
    }

    /**
     * Test resize in the all 8 directions (horizontal, vertical & diagonal),
     * test also move (move should be always allowed for all resizeKind).
     */
    private void checkResizability() {
        SWTBotUtils.waitAllUiEvents();
        // Collection<SWTBotGefEditPart> selection = new
        // ArrayList<SWTBotGefEditPart>(dNodeEditPartBots);
        // int index = dNodeEditPartBots.indexOf(dNodeEditPartBot);
        // for (int i = index + 1; i < dNodeEditPartBots.size(); i++) {
        // selection.remove(dNodeEditPartBots.get(i));
        // }
        for (SWTBotGefEditPart dNodeEditPartBot : dNodeEditPartBots) {

            checkResizability(Collections.singletonList(dNodeEditPartBot));

        }
    }

    /**
     * Test resize in the all 8 directions (horizontal, vertical & diagonal),
     * test also move (move should be always allowed for all resizeKind).
     */
    private void checkResizability(Collection<SWTBotGefEditPart> dNodeEditPartBots) {
        int directions[] = { PositionConstants.NORTH, PositionConstants.SOUTH, PositionConstants.EAST, PositionConstants.WEST, PositionConstants.NORTH_EAST, PositionConstants.NORTH_WEST,
                PositionConstants.SOUTH_EAST, PositionConstants.SOUTH_WEST };

        SWTBotUtils.waitAllUiEvents();
        SWTBotGefEditPart firstDNodeEditPartBot = dNodeEditPartBots.iterator().next();
        IGraphicalEditPart firstDNodeEditPart = (IGraphicalEditPart) firstDNodeEditPartBot.part();
        editor.reveal(firstDNodeEditPart);
        SWTBotUtils.waitAllUiEvents();
        editor.select(dNodeEditPartBots);
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new CheckSelectedCondition(editor, firstDNodeEditPart));

        for (int i = 0; i < directions.length; i++) {
            int direction = directions[i];

            ICondition condition = new TransactionClosedCondition(localSession.getOpenedSession().getTransactionalEditingDomain());
            tryResize(dNodeEditPartBots, direction);
            SWTBotUtils.waitAllUiEvents();
            bot.waitUntil(condition);
            SWTBotUtils.waitAllUiEvents();

            checkBoundsAfterResize(dNodeEditPartBots, direction);
        }
    }

    private void checkBoundsAfterResize(Collection<SWTBotGefEditPart> dNodeEditPartBots, int direction) {
        SWTBotGefEditPart firstDNodeEditPartBot = dNodeEditPartBots.iterator().next();
        DNode dNode = getDNode(firstDNodeEditPartBot);
        SWTBotUtils.waitAllUiEvents();

        boolean isBorderedNode = isBorderedNode(firstDNodeEditPartBot);

        Rectangle initialBounds = initialsBounds.get(firstDNodeEditPartBot);
        Rectangle expectedBounds = getExpectedBounds(firstDNodeEditPartBot, direction);
        Rectangle newBounds = editor.getAbsoluteBounds(firstDNodeEditPartBot);

        assertEquals("The resize error on : " + dNode, expectedBounds, newBounds);

        boolean resized = !newBounds.equals(initialBounds);
        if (resized) {
            String label = "Set Location or Size";
            ICondition condition = new OperationUndoneCondition();
            undo(label);
            bot.waitUntil(condition);
            SWTBotUtils.waitAllUiEvents();
            assertEquals("After a undo we should find the initial bounds for " + dNode, initialBounds, editor.getAbsoluteBounds(firstDNodeEditPartBot));

            condition = new OperationRedoneCondition();
            redo(label);
            bot.waitUntil(condition);
            SWTBotUtils.waitAllUiEvents();
            assertEquals("After a redo we should find the same bounds as for the first resize for " + dNode, expectedBounds, editor.getAbsoluteBounds(firstDNodeEditPartBot));

            condition = new OperationUndoneCondition();
            undo(label);
            bot.waitUntil(condition);
            SWTBotUtils.waitAllUiEvents();
            assertEquals("After a undo we should find the initial bounds for " + dNode, initialBounds, editor.getAbsoluteBounds(firstDNodeEditPartBot));

        }
    }

    private Rectangle getExpectedBounds(SWTBotGefEditPart dNodeEditPartBot, int direction) {
        Rectangle expectedBounds = null;

        switch (direction) {
        case PositionConstants.NORTH:
            expectedBounds = getExpectedBoundsForNorthDirectionResize(dNodeEditPartBot, direction);
            break;
        case PositionConstants.SOUTH:
            expectedBounds = getExpectedBoundsForSouthDirectionResize(dNodeEditPartBot, direction);
            break;
        case PositionConstants.EAST:
            expectedBounds = getExpectedBoundsForEastDirectionResize(dNodeEditPartBot, direction);
            break;
        case PositionConstants.WEST:
            expectedBounds = getExpectedBoundsForWestDirectionResize(dNodeEditPartBot, direction);
            break;
        case PositionConstants.NORTH_EAST:
            expectedBounds = getExpectedBoundsForNorthEastDirectionResize(dNodeEditPartBot, direction);
            break;
        case PositionConstants.NORTH_WEST:
            expectedBounds = getExpectedBoundsForNorthWestDirectionResize(dNodeEditPartBot, direction);
            break;
        case PositionConstants.SOUTH_EAST:
            expectedBounds = getExpectedBoundsForSouthEastDirectionResize(dNodeEditPartBot, direction);
            break;
        case PositionConstants.SOUTH_WEST:
            expectedBounds = getExpectedBoundsForSouthWestDirectionResize(dNodeEditPartBot, direction);
            break;

        default:
            fail("Unknown direction : " + direction);
            break;
        }

        return expectedBounds;
    }

    private Rectangle getExpectedBoundsForNorthDirectionResize(SWTBotGefEditPart dNodeEditPartBot, int direction) {
        Rectangle expectedBounds = null;

        Rectangle initialBounds = initialsBounds.get(dNodeEditPartBot);

        ResizeKind dNodeResizeKind = getDNodeResizeKind(dNodeEditPartBot);

        switch (dNodeResizeKind) {
        case NSEW_LITERAL:
        case NORTH_SOUTH_LITERAL:

            if (isBorderedNode(dNodeEditPartBot)) {
                int side = getSide(dNodeEditPartBot);
                switch (side) {
                case PositionConstants.NORTH:
                    expectedBounds = initialBounds.getResized(0, initialBounds.height).translate(0, -initialBounds.height);
                    break;
                case PositionConstants.EAST:
                    expectedBounds = initialBounds.getResized(0, initialBounds.height).translate(0, -initialBounds.height);
                    break;
                case PositionConstants.WEST:
                    expectedBounds = initialBounds.getResized(0, initialBounds.height).translate(0, -initialBounds.height);
                    break;
                case PositionConstants.SOUTH:
                    expectedBounds = initialBounds.getResized(0, initialBounds.height);
                    break;
                default:
                    fail("Unknown side : " + side);
                    break;
                }
            } else {
                expectedBounds = initialBounds.getResized(0, initialBounds.height).translate(0, -initialBounds.height);
            }

            break;
        case EAST_WEST_LITERAL:
        case NONE_LITERAL:
            expectedBounds = initialBounds;
            break;
        default:
            fail("Unknown direction : " + dNodeResizeKind);
            break;
        }

        return expectedBounds;
    }

    private Rectangle getExpectedBoundsForSouthDirectionResize(SWTBotGefEditPart dNodeEditPartBot, int direction) {
        Rectangle expectedBounds = null;

        Rectangle initialBounds = initialsBounds.get(dNodeEditPartBot);

        ResizeKind dNodeResizeKind = getDNodeResizeKind(dNodeEditPartBot);

        switch (dNodeResizeKind) {
        case NSEW_LITERAL:
        case NORTH_SOUTH_LITERAL:

            if (isBorderedNode(dNodeEditPartBot)) {
                int side = getSide(dNodeEditPartBot);
                switch (side) {
                case PositionConstants.NORTH:
                    expectedBounds = initialBounds.getResized(0, initialBounds.height).translate(0, -initialBounds.height);
                    break;
                case PositionConstants.EAST:
                    expectedBounds = initialBounds.getResized(0, initialBounds.height);
                    break;
                case PositionConstants.WEST:
                    expectedBounds = initialBounds.getResized(0, initialBounds.height);
                    break;
                case PositionConstants.SOUTH:
                    expectedBounds = initialBounds.getResized(0, initialBounds.height);
                    break;
                default:
                    fail("Unknown side : " + side);
                    break;
                }
            } else {
                expectedBounds = initialBounds.getResized(0, initialBounds.height);
            }

            break;
        case EAST_WEST_LITERAL:
        case NONE_LITERAL:
            expectedBounds = initialBounds;
            break;
        default:
            fail("Unknown direction : " + dNodeResizeKind);
            break;
        }

        return expectedBounds;
    }

    private Rectangle getExpectedBoundsForEastDirectionResize(SWTBotGefEditPart dNodeEditPartBot, int direction) {
        Rectangle expectedBounds = null;

        Rectangle initialBounds = initialsBounds.get(dNodeEditPartBot);

        ResizeKind dNodeResizeKind = getDNodeResizeKind(dNodeEditPartBot);

        switch (dNodeResizeKind) {
        case NSEW_LITERAL:
        case EAST_WEST_LITERAL:

            if (isBorderedNode(dNodeEditPartBot)) {
                int side = getSide(dNodeEditPartBot);
                switch (side) {
                case PositionConstants.NORTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, 0).translate(-initialBounds.width, 0);
                    break;
                case PositionConstants.EAST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, 0);
                    break;
                case PositionConstants.WEST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, 0).translate(-initialBounds.width, 0);
                    break;
                case PositionConstants.SOUTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, 0).translate(-initialBounds.width, 0);
                    break;
                default:
                    fail("Unknown side : " + side);
                    break;
                }
            } else {
                expectedBounds = initialBounds.getResized(initialBounds.width, 0).translate(-initialBounds.width, 0);
            }

            break;
        case NORTH_SOUTH_LITERAL:
        case NONE_LITERAL:
            expectedBounds = initialBounds;
            break;
        default:
            fail("Unknown direction : " + dNodeResizeKind);
            break;
        }

        return expectedBounds;
    }

    private Rectangle getExpectedBoundsForWestDirectionResize(SWTBotGefEditPart dNodeEditPartBot, int direction) {
        Rectangle expectedBounds = null;

        Rectangle initialBounds = initialsBounds.get(dNodeEditPartBot);

        ResizeKind dNodeResizeKind = getDNodeResizeKind(dNodeEditPartBot);

        switch (dNodeResizeKind) {
        case NSEW_LITERAL:
        case EAST_WEST_LITERAL:

            if (isBorderedNode(dNodeEditPartBot)) {
                int side = getSide(dNodeEditPartBot);
                switch (side) {
                case PositionConstants.NORTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, 0);
                    break;
                case PositionConstants.EAST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, 0);
                    break;
                case PositionConstants.WEST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, 0).translate(-initialBounds.width, 0);
                    break;
                case PositionConstants.SOUTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, 0);
                    break;
                default:
                    fail("Unknown side : " + side);
                    break;
                }
            } else {
                expectedBounds = initialBounds.getResized(initialBounds.width, 0);
            }

            break;
        case NORTH_SOUTH_LITERAL:
        case NONE_LITERAL:
            expectedBounds = initialBounds;
            break;
        default:
            fail("Unknown direction : " + dNodeResizeKind);
            break;
        }

        return expectedBounds;
    }

    private Rectangle getExpectedBoundsForNorthEastDirectionResize(SWTBotGefEditPart dNodeEditPartBot, int direction) {
        Rectangle expectedBounds = null;

        Rectangle initialBounds = initialsBounds.get(dNodeEditPartBot);

        ResizeKind dNodeResizeKind = getDNodeResizeKind(dNodeEditPartBot);

        switch (dNodeResizeKind) {
        case NSEW_LITERAL:
            if (isBorderedNode(dNodeEditPartBot)) {
                int side = getSide(dNodeEditPartBot);
                switch (side) {
                case PositionConstants.NORTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(-initialBounds.width, -initialBounds.height);
                    break;
                case PositionConstants.EAST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(0, -initialBounds.height);
                    break;
                case PositionConstants.WEST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(-initialBounds.width, -initialBounds.height);
                    break;
                case PositionConstants.SOUTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(-initialBounds.width, 0);
                    break;
                default:
                    fail("Unknown side : " + side);
                    break;
                }
            } else {
                expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(-initialBounds.width, -initialBounds.height);
            }

            break;
        case NORTH_SOUTH_LITERAL:
        case EAST_WEST_LITERAL:
        case NONE_LITERAL:
            expectedBounds = initialBounds;
            break;
        default:
            fail("Unknown direction : " + dNodeResizeKind);
            break;
        }

        return expectedBounds;
    }

    private Rectangle getExpectedBoundsForNorthWestDirectionResize(SWTBotGefEditPart dNodeEditPartBot, int direction) {
        Rectangle expectedBounds = null;

        Rectangle initialBounds = initialsBounds.get(dNodeEditPartBot);

        ResizeKind dNodeResizeKind = getDNodeResizeKind(dNodeEditPartBot);

        switch (dNodeResizeKind) {
        case NSEW_LITERAL:
            if (isBorderedNode(dNodeEditPartBot)) {
                int side = getSide(dNodeEditPartBot);
                switch (side) {
                case PositionConstants.NORTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(0, -initialBounds.height);
                    break;
                case PositionConstants.EAST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(0, -initialBounds.height);
                    break;
                case PositionConstants.WEST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(-initialBounds.width, -initialBounds.height);
                    break;
                case PositionConstants.SOUTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height);
                    break;
                default:
                    fail("Unknown side : " + side);
                    break;
                }
            } else {
                expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(0, -initialBounds.height);
            }

            break;
        case NORTH_SOUTH_LITERAL:
        case EAST_WEST_LITERAL:
        case NONE_LITERAL:
            expectedBounds = initialBounds;
            break;
        default:
            fail("Unknown direction : " + dNodeResizeKind);
            break;
        }

        return expectedBounds;
    }

    private Rectangle getExpectedBoundsForSouthEastDirectionResize(SWTBotGefEditPart dNodeEditPartBot, int direction) {
        Rectangle expectedBounds = null;

        Rectangle initialBounds = initialsBounds.get(dNodeEditPartBot);

        ResizeKind dNodeResizeKind = getDNodeResizeKind(dNodeEditPartBot);

        switch (dNodeResizeKind) {
        case NSEW_LITERAL:
            if (isBorderedNode(dNodeEditPartBot)) {
                int side = getSide(dNodeEditPartBot);
                switch (side) {
                case PositionConstants.NORTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(-initialBounds.width, -initialBounds.height);
                    break;
                case PositionConstants.EAST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height);
                    break;
                case PositionConstants.WEST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(-initialBounds.width, 0);
                    break;
                case PositionConstants.SOUTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(-initialBounds.width, 0);
                    break;
                default:
                    fail("Unknown side : " + side);
                    break;
                }
            } else {
                expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(-initialBounds.width, 0);
            }

            break;
        case NORTH_SOUTH_LITERAL:
        case EAST_WEST_LITERAL:
        case NONE_LITERAL:
            expectedBounds = initialBounds;
            break;
        default:
            fail("Unknown direction : " + dNodeResizeKind);
            break;
        }

        return expectedBounds;
    }

    private Rectangle getExpectedBoundsForSouthWestDirectionResize(SWTBotGefEditPart dNodeEditPartBot, int direction) {
        Rectangle expectedBounds = null;

        Rectangle initialBounds = initialsBounds.get(dNodeEditPartBot);

        ResizeKind dNodeResizeKind = getDNodeResizeKind(dNodeEditPartBot);

        switch (dNodeResizeKind) {
        case NSEW_LITERAL:
            if (isBorderedNode(dNodeEditPartBot)) {
                int side = getSide(dNodeEditPartBot);
                switch (side) {
                case PositionConstants.NORTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(0, -initialBounds.height);
                    break;
                case PositionConstants.EAST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height);
                    break;
                case PositionConstants.WEST:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height).translate(-initialBounds.width, 0);
                    break;
                case PositionConstants.SOUTH:
                    expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height);
                    break;
                default:
                    fail("Unknown side : " + side);
                    break;
                }
            } else {
                expectedBounds = initialBounds.getResized(initialBounds.width, initialBounds.height);
            }

            break;
        case NORTH_SOUTH_LITERAL:
        case EAST_WEST_LITERAL:
        case NONE_LITERAL:
            expectedBounds = initialBounds;
            break;
        default:
            fail("Unknown direction : " + dNodeResizeKind);
            break;
        }

        return expectedBounds;
    }

    private void tryResize(Collection<SWTBotGefEditPart> dNodeEditPartBots, int direction) {
        SWTBotGefEditPart firstDNodeEditPartBot = dNodeEditPartBots.iterator().next();
        Rectangle initialBounds = initialsBounds.get(firstDNodeEditPartBot);

        boolean hasNorthSouthDirection = ((PositionConstants.NORTH | PositionConstants.SOUTH) & direction) != 0;
        boolean hasEastWestDirection = ((PositionConstants.EAST | PositionConstants.WEST) & direction) != 0;
        int heightResize = 0;
        int widthResize = 0;
        if (hasNorthSouthDirection) {
            heightResize = 2 * initialBounds.height;
        }
        if (hasEastWestDirection) {
            widthResize = 2 * initialBounds.width;
        }
        firstDNodeEditPartBot.resize(direction, widthResize, heightResize);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * @param dNodeEditPartBot
     * @return
     */
    private int getSide(SWTBotGefEditPart dNodeEditPartBot) {
        EditPart editPart = dNodeEditPartBot.part();
        assertTrue(editPart instanceof IGraphicalEditPart);
        IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) editPart;
        IFigure dNodeFigure = graphicalEditPart.getFigure();
        Rectangle dNodeFigureBounds = dNodeFigure.getBounds();

        EditPart parentEditPart = graphicalEditPart.getParent();
        assertTrue(parentEditPart instanceof IGraphicalEditPart);
        IGraphicalEditPart parentGraphicalEditPart = (IGraphicalEditPart) parentEditPart;
        IFigure parentDNodeFigure = parentGraphicalEditPart.getFigure();
        Rectangle parentDNodeFigureBounds = parentDNodeFigure.getBounds();

        int side = -1;
        if (dNodeFigureBounds.getCenter().x > parentDNodeFigureBounds.x && dNodeFigureBounds.getCenter().x < parentDNodeFigureBounds.x + parentDNodeFigureBounds.width) {
            if (dNodeFigureBounds.getCenter().y < parentDNodeFigureBounds.y) {
                side = PositionConstants.NORTH;
            } else if (dNodeFigureBounds.getCenter().y > parentDNodeFigureBounds.y + parentDNodeFigureBounds.height) {
                side = PositionConstants.SOUTH;
            }
        } else if (dNodeFigureBounds.getCenter().y > parentDNodeFigureBounds.y && dNodeFigureBounds.getCenter().y < parentDNodeFigureBounds.y + parentDNodeFigureBounds.height) {
            if (dNodeFigureBounds.getCenter().x < parentDNodeFigureBounds.x) {
                side = PositionConstants.WEST;
            } else if (dNodeFigureBounds.getCenter().x > parentDNodeFigureBounds.x + parentDNodeFigureBounds.width) {
                side = PositionConstants.EAST;
            }
        }
        return side;
    }

    private boolean isBorderedNode(SWTBotGefEditPart dNodeEditPartBot) {
        DNode dNode = getDNode(dNodeEditPartBot);
        return new AbstractDNodeQuery(dNode).isBorderedNode();
    }

    private void changeResizeKind(ResizeKind resizeKind) {
        for (SWTBotGefEditPart dNodeEditPartBot : dNodeEditPartBots) {
            NodeStyleDescription nodeStyleDescription = getNodeStyleDescription(dNodeEditPartBot);
            String uriFragment = nodeStyleDescription.eResource().getURIFragment(nodeStyleDescription);
            URI nodeStyleDescriptionURI = modelerResource.getURI().appendFragment(uriFragment);
            nodeStyleDescription = (NodeStyleDescription) modelerResource.getResourceSet().getEObject(nodeStyleDescriptionURI, true);
            nodeStyleDescription.setResizeKind(resizeKind);
        }
        SWTBotUtils.waitAllUiEvents();
        try {
            modelerResource.save(Collections.emptyMap());
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        } finally {
            SWTBotUtils.waitAllUiEvents();
            SWTBotUtils.waitAllUiEvents();
        }
    }

    private void changeResizeKind(SWTBotGefEditPart dNodeEditPartBot, ResizeKind resizeKind) {
        NodeStyleDescription nodeStyleDescription = getNodeStyleDescription(dNodeEditPartBot);
        String uriFragment = nodeStyleDescription.eResource().getURIFragment(nodeStyleDescription);
        URI nodeStyleDescriptionURI = modelerResource.getURI().appendFragment(uriFragment);
        nodeStyleDescription = (NodeStyleDescription) modelerResource.getResourceSet().getEObject(nodeStyleDescriptionURI, true);
        nodeStyleDescription.setResizeKind(resizeKind);
        SWTBotUtils.waitAllUiEvents();
        try {
            modelerResource.save(Collections.emptyMap());
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        } finally {
            bot.sleep(2000);
            SWTBotUtils.waitAllUiEvents();
            SWTBotUtils.waitAllUiEvents();
            SWTBotUtils.waitAllUiEvents();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        editor.restore();
        modelerResource = null;
        dNodeEditPartBots = null;
        initialsBounds = null;
        super.tearDown();
    }

}
