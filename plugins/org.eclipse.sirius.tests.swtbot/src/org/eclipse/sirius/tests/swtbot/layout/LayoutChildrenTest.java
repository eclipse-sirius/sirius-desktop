/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.layout;

import static org.junit.Assert.assertNotEquals;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Tests for layout children
 * 
 * @author scosta
 */
@SuppressWarnings("nls")
public class LayoutChildrenTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/layout/children/";

    private static final String MODEL = "simple.ecore";

    private static final String VSM = "layouts.odesign";

    private static final String REPRESENTATION = "representations.aird";

    /**
     * The local session.
     */
    protected UILocalSession localSession;

    protected SWTBotTreeItem rootEPackage;

    protected SWTBotTreeItem mainEPackage;

    private UIResource semanticModel;

    /**
     * True if snapToGrid should be activated on editor, false otherwise.
     */
    protected boolean snapToGrid;

    // ---- BEGIN SETUP FUNCTIONS ----
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        if (!snapToGrid) {
            changeDiagramUIPreference(IPreferenceConstants.PREF_SNAP_TO_GRID, false);
        }
    }

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, VSM, REPRESENTATION);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        semanticModel = new UIResource(designerProject, MODEL);
        sessionAirdResource = new UIResource(designerProject, REPRESENTATION);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(semanticModel);
        rootEPackage = semanticResourceNode.expandNode("Root");
        mainEPackage = rootEPackage.expandNode("Main");
    }
    // ---- END SETUP FUNCTIONS ----

    // ---- BEGIN UTIL FUNCTIONS ----
    private class MoveChecker {
        Map<String, Point> initialPositions;

        private Map<String, Point> getPositions(Collection<String> editPartNames) {
            return editPartNames.stream().collect(Collectors.toMap(name -> name, name -> {
                IGraphicalEditPart editPart = (IGraphicalEditPart) editor.getEditPart(name).part().getParent();
                return editPart.getFigure().getBounds().getTopLeft().getCopy();
            }));
        }

        public void recordInitialPositions(List<String> editPartNames) {
            initialPositions = getPositions(editPartNames);
        }

        public void assertMoved(List<String> moveExpected) {
            Set<String> partNamesToCheck = initialPositions.keySet();
            Map<String, Point> finalPositions = getPositions(partNamesToCheck);
            
            for (String name : partNamesToCheck) {
                Point initialPosition = initialPositions.get(name);
                Point finalPosition = finalPositions.get(name);
                boolean shouldMove = moveExpected.contains(name);
                if (shouldMove) {
                    assertNotEquals(name + " should have moved", initialPosition, finalPosition);
                } else {
                    assertEquals(name + " should not have moved", initialPosition, finalPosition);
                }
            }
        }
    }

    private enum Orientation {
        Vertical, Horizontal,
    }

    private void assertCenterAboveOf(final String message, final IGraphicalEditPart a, final IGraphicalEditPart b) {
        Point aAbsoluteCenter = a.getFigure().getBounds().getCenter().getCopy();
        Point bAbsoluteCenter = b.getFigure().getBounds().getCenter().getCopy();
        a.getFigure().translateToAbsolute(aAbsoluteCenter);
        b.getFigure().translateToAbsolute(bAbsoluteCenter);
        if (aAbsoluteCenter.y >= bAbsoluteCenter.y) {
            fail(message + " first figure has y=" + aAbsoluteCenter.y + " and second one has y=" + bAbsoluteCenter.y);
        }
    }

    private void assertCenterLeftOf(final String message, final IGraphicalEditPart a, final IGraphicalEditPart b) {
        Point aAbsoluteCenter = a.getFigure().getBounds().getCenter().getCopy();
        Point bAbsoluteCenter = b.getFigure().getBounds().getCenter().getCopy();
        a.getFigure().translateToAbsolute(aAbsoluteCenter);
        b.getFigure().translateToAbsolute(bAbsoluteCenter);
        if (aAbsoluteCenter.x >= bAbsoluteCenter.x) {
            fail(message + " first figure has x=" + aAbsoluteCenter + " and second one has x=" + bAbsoluteCenter);
        }
    }

    private void assertCenterBeforeOf(final String aName, final String bName, final IGraphicalEditPart a, final IGraphicalEditPart b, Orientation orientation) {
        switch (orientation) {
        case Vertical -> {
            String msg = aName + " should be below " + bName;
            assertCenterAboveOf(msg, a, b);
        }
        case Horizontal -> {
            String msg = aName + " should to the left of " + bName;
            assertCenterLeftOf(msg, a, b);
        }
        }
    }

    private List<IGraphicalEditPart> getGraphicalEditParts(List<String> names) {
        return names.stream() //
                .map(editor::getEditPart) //
                .map(SWTBotGefEditPart::part) //
                .map(IGraphicalEditPart.class::cast) //
                .toList();
    }

    private void checkNodeOrder(List<String> nodesName, Orientation orientation) {
        List<IGraphicalEditPart> nodesEditPart = getGraphicalEditParts(nodesName);

        for (int i = 1; i < nodesEditPart.size(); ++i) {
            assertCenterBeforeOf( //
                    nodesName.get(i - 1), nodesName.get(i), //
                    nodesEditPart.get(i - 1), nodesEditPart.get(i), //
                    orientation //
            );
        }
    }

    private void checkNodeOrderGroup(List<List<String>> nodesName, Orientation orientation) {
        List<List<IGraphicalEditPart>> nodesEditPart = nodesName.stream() //
                .map(this::getGraphicalEditParts) //
                .toList();

        for (int i = 1; i < nodesEditPart.size(); ++i) {
            List<IGraphicalEditPart> group1 = nodesEditPart.get(i - 1);
            List<IGraphicalEditPart> group2 = nodesEditPart.get(i);

            // for (IGraphicalEditPart elem1 : group1), but we need indices
            for (int j = 0; j < group1.size(); ++j) {
                IGraphicalEditPart elem1 = group1.get(j);

                // for (IGraphicalEditPart elem2 : group2), but we need indices
                for (int k = 0; k < group2.size(); ++k) {
                    IGraphicalEditPart elem2 = group2.get(k);

                    String elem1name = nodesName.get(i - 1).get(j);
                    String elem2name = nodesName.get(i).get(k);
                    assertCenterBeforeOf(elem1name, elem2name, elem1, elem2, orientation);
                }
            }
        }
    }
    // ---- END UTIL FUNCTIONS ----

    // ---- BEGIN TESTS FUNCTIONS ----
    public void testSimpleComposite() {
        final UIDiagramRepresentation diagramRepresentation = new UIDiagramRepresentation(rootEPackage.getNode("new Bottom/Top MainLikeDiagram"), "new Bottom/Top MainLikeDiagram");
        editor = diagramRepresentation.open().getEditor();

        // select diagram background
        editor.setFocus();
        editor.select(editor.mainEditPart());

        layoutChildren();

        checkNodeOrder(List.of("LF1", "LF5"), Orientation.Vertical);
        checkNodeOrder(List.of( //
                "LF3", "LF2", "LF4", "LF6", "LF7", //
                "LF7_1", "LF7_2", "LF8", "LF9", //
                "LF10", "LF11", "LF12", "LF13", "LF14"), Orientation.Vertical);
        
        checkNodeOrderGroup(List.of( //
                List.of("LF1", "LF5"), //
                List.of("LF3", "LF2", "LF4", "LF6", "LF7", //
                        "LF7_1", "LF7_2", "LF8", "LF9", //
                        "LF10", "LF11", "LF12", "LF13", "LF14") //
                ), Orientation.Horizontal);
    }

    public void testSimpleTree() {
        final UIDiagramRepresentation diagramRepresentation = new UIDiagramRepresentation(rootEPackage.getNode("new TreeOrdering"), "new TreeOrdering");
        editor = diagramRepresentation.open().getEditor();

        // select diagram background
        editor.setFocus();
        editor.select(editor.mainEditPart());

        layoutChildren();

        checkNodeOrderGroup(List.of(List.of("LF1"), List.of("LF2", "LF3", "LF4")), Orientation.Vertical);

        checkNodeOrderGroup(List.of( //
                List.of("LF8"), //
                List.of("LF1", "LF2", "LF3", "LF4"), //
                List.of("LF5", "LF6", "LF7", //
                        "LF7_1", "LF7_2", "LF9", //
                        "LF10", "LF11", "LF12", "LF13", "LF14") //
        ), Orientation.Horizontal);
    }

    public void testRecursiveComposite() {
        final UIDiagramRepresentation diagramRepresentation = new UIDiagramRepresentation(mainEPackage.getNode("new Top/Bottom Container Ports And Edges"), "new Top/Bottom Container Ports And Edges");
        editor = diagramRepresentation.open().getEditor();

        // select LF6
        SWTBotGefEditPart selection = editor.getEditPart("LF6", DNodeContainer2EditPart.class);
        editor.select(selection);

        var moveChecker = new MoveChecker();
        moveChecker.recordInitialPositions(List.of("LF1", "LF2", "LF3", "LF4", "LF5", "LF6", "LF7", "LF7_1", "LF7_2", "LF9", "LF10", "LF11", "LF12", "LF13", "LF14"));

        layoutChildren();

        // Check only LF7, LF10, LF13 move
        moveChecker.assertMoved(List.of("LF7", "LF10", "LF13"));
    }
    // ---- END TESTS FUNCTIONS ----
}
