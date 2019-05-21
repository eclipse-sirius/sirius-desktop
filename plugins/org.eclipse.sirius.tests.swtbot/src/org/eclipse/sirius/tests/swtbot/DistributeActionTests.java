/**
 * Copyright (c) 2014 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.distribute.DistributeAction;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.swtbot.uml.AbstractUmlDragAndDropTest;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;

/**
 * Distribute actions tests.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DistributeActionTests extends AbstractUmlDragAndDropTest {

    private static final String DIAGRAM_DESCRIPTION_NAME = "Package Diagram";

    private static final String DIAGRAM_NAME = "Distribute Action Package Diagram";

    private static final String HORIZONTALLY_GAPS_LABEL = DistributeAction.getLabel(DistributeAction.GAPS_HORIZONTALLY, false);

    private static final int HORIZONTALLY_GAP_VALUE = 38;

    private static final int SCROLLBAR_HORIZONTALLY_GAP_VALUE = 237;

    private static final String HORIZONTALLY_CENTERS_GAPS_LABEL = DistributeAction.getLabel(DistributeAction.CENTERS_HORIZONTALLY, false);

    private static final int HORIZONTALLY_CENTER_GAP_VALUE = 201;

    private static final int SCROLLBAR_HORIZONTALLY_CENTER_GAP_VALUE = 401;

    private static final String VERTICALLY_GAPS_LABEL = DistributeAction.getLabel(DistributeAction.GAPS_VERTICALLY, false);

    private static final int VERTICALLY_GAP_VALUE = -12;

    private static final int SCROLLBAR_VERTICALLY_GAP_VALUE = 211;

    private static final String VERTICALLY_CENTERS_GAPS_LABEL = DistributeAction.getLabel(DistributeAction.CENTERS_VERTICALLY, false);

    private static final int VERTICALLY_CENTER_GAP_VALUE = 64;

    private static final int SCROLLBAR_VERTICALLY_GAP_CENTER_VALUE = 288;

    private static final Collection<Integer> ALL_DISTRIBUTE_KIND = Arrays.asList(DistributeAction.GAPS_HORIZONTALLY, DistributeAction.CENTERS_HORIZONTALLY, DistributeAction.GAPS_VERTICALLY,
            DistributeAction.CENTERS_VERTICALLY);

    /*
     * (non-Javadoc)
     * @see org.eclipse.sirius.tests.swtbot.uml.AbstractUmlDragAndDropTest#
     * onSetUpAfterOpeningDesignerPerspective()
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        closeOutline();
        super.onSetUpAfterOpeningDesignerPerspective();
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.sirius.tests.swtbot.uml.AbstractUmlDragAndDropTest#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationNameToOpen() {
        return DIAGRAM_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return DIAGRAM_DESCRIPTION_NAME;
    }

    /**
     * Test all distribute actions.
     */
    public void testAllDistributeAction() {
        distributeHorizontallyActionTests(ZoomLevel.ZOOM_100, false, false, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeVerticallyActionTests(ZoomLevel.ZOOM_100, false, false, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
    }

    /**
     * Distribute actions test in various configurations (scrollBar, zoom
     * level).
     */
    public void testDistributeActionInVariousConfigurations() {
        distributeHorizontallyActionTests(ZoomLevel.ZOOM_100, true, false, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeHorizontallyActionTests(ZoomLevel.ZOOM_100, false, true, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeHorizontallyActionTests(ZoomLevel.ZOOM_100, true, true, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeHorizontallyActionTests(ZoomLevel.ZOOM_200, false, false, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeHorizontallyActionTests(ZoomLevel.ZOOM_125, false, false, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeHorizontallyActionTests(ZoomLevel.ZOOM_50, false, false, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");

        distributeVerticallyActionTests(ZoomLevel.ZOOM_100, false, true, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeVerticallyActionTests(ZoomLevel.ZOOM_100, true, false, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeVerticallyActionTests(ZoomLevel.ZOOM_100, true, true, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeVerticallyActionTests(ZoomLevel.ZOOM_200, false, false, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeVerticallyActionTests(ZoomLevel.ZOOM_125, false, false, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        distributeVerticallyActionTests(ZoomLevel.ZOOM_50, false, false, "CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
    }

    /**
     * Test distribute actions when a shape is the first and the last.
     */
    public void testFirstAndLastBothShape() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationDescriptionName(), "Distribute Action Package Diagram 2", DDiagram.class);
        List<SWTBotGefEditPart> editPartsToDistribute = getEditParts("CanvasPackageToDrop1", "RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        List<SWTBotGefEditPart> orderedEditPartsToDistribute = sortEditPartsHorizontally(editPartsToDistribute);
        PointList previousPoints = new PointList();
        for (SWTBotGefEditPart editPart : orderedEditPartsToDistribute) {
            previousPoints.addPoint(editor.getLocation(editPart));
        }
        editor.select(editPartsToDistribute);
        editor.clickContextMenu(HORIZONTALLY_GAPS_LABEL);
        PointList expectedPoints = new PointList();
        for (int i = 0; i < orderedEditPartsToDistribute.size(); i++) {
            expectedPoints.addPoint(editor.getLocation(orderedEditPartsToDistribute.get(i)));
        }
        assertEquals("Distribute Action should not move nodes when the first shape is the last one", previousPoints.getBounds(), expectedPoints.getBounds());
    }

    /**
     * * Test distribute actions when select 2 shapes at same axis.
     */
    public void testShapesAtSameAxis() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationDescriptionName(), "Distribute Action Package Diagram 3", DDiagram.class);
        List<SWTBotGefEditPart> editPartsToDistribute = getEditParts("RootPackage1", "RootPackage2", "CanvasPackageToDrop2", "AAPackage");
        editor.select(editPartsToDistribute);
        for (int distributeKind : ALL_DISTRIBUTE_KIND) {
            switch (distributeKind) {
            case DistributeAction.GAPS_HORIZONTALLY:
                editor.clickContextMenu(HORIZONTALLY_GAPS_LABEL);
                assertNotEquals("Distribute Action should move one of the shapes with same axis", editor.getLocation(getEditParts("RootPackage1").get(0)),
                        editor.getLocation(getEditParts("RootPackage2").get(0)));
                break;
            case DistributeAction.CENTERS_HORIZONTALLY:
                editor.clickContextMenu(HORIZONTALLY_CENTERS_GAPS_LABEL);
                assertNotEquals("Distribute Action should move one of the shapes with same axis", editor.getLocation(getEditParts("RootPackage1").get(0)),
                        editor.getLocation(getEditParts("RootPackage2").get(0)));
                break;
            case DistributeAction.GAPS_VERTICALLY:
                editor.clickContextMenu(VERTICALLY_GAPS_LABEL);
                assertNotEquals("Distribute Action should move one of the shapes with same axis", editor.getLocation(getEditParts("RootPackage1").get(0)),
                        editor.getLocation(getEditParts("RootPackage2").get(0)));
                break;
            case DistributeAction.CENTERS_VERTICALLY:
                editor.clickContextMenu(VERTICALLY_CENTERS_GAPS_LABEL);
                assertNotEquals("Distribute Action should move one of the shapes with same axis", editor.getLocation(getEditParts("RootPackage1").get(0)),
                        editor.getLocation(getEditParts("RootPackage2").get(0)));
                break;
            default:
                break;
            }
        }
    }

    /**
     * Distribute actions disablement checking test when only less than 3 shapes
     * are selected or when selected several shapes not at same level or border
     * nodes not on same side.
     */
    public void testDisablementChecking() {
        final SWTBotMenu menuDistribute = bot.menu("&Diagram").menu("&Distribute");
        testActionsDisablement(menuDistribute, "RootPackage1");
        testActionsDisablement(menuDistribute, "RootPackage1", "CanvasPackageToDrop1");
        testActionsDisablement(menuDistribute, "RootPackage1", "RootPackage2", "ContainerPackageToDrop");
    }

    /**
     * Distribute actions disablement checking test when selected bordered nodes
     * are not on the same side.
     */
    public void testDisablementCheckingBorderedNodes() {
        final SWTBotMenu menuDistribute = bot.menu("&Diagram").menu("&Distribute");
        testActionsDisablementForBorderNodes(menuDistribute, "Port1", "Port4", "Port2");
        testActionsDisablementForBorderNodes(menuDistribute, "Port2", "Port4", "Port9");
    }

    /**
     * Ensures that by launching distribute horizontally gaps action for
     * selected edit parts with the given names, edit parts are distributed with
     * uniforms gaps.
     * 
     * @param editPartsToDistribute
     *            name of the Edit parts to distribute
     * @param zoomLevel
     *            the zoom level
     * 
     */
    private void distributeGapsHorizontally(List<SWTBotGefEditPart> editPartsToDistribute, ZoomLevel zoomLevel, boolean horizontalScrollbar) {
        editor.select(editPartsToDistribute);
        editor.clickContextMenu(HORIZONTALLY_GAPS_LABEL);
        List<SWTBotGefEditPart> orderedEditParts = sortEditPartsHorizontally(editPartsToDistribute);
        boolean uniformGaps = true;
        int expectedGap;
        if (horizontalScrollbar) {
            expectedGap = SCROLLBAR_HORIZONTALLY_GAP_VALUE;
        } else {
            expectedGap = HORIZONTALLY_GAP_VALUE;
        }
        String messsageDetails = " (distribute action=" + HORIZONTALLY_GAPS_LABEL + ", zoomLevel=" + zoomLevel.getLevel() + ", horizontalScrollbar=" + horizontalScrollbar + ")";
        for (int i = 0; i < orderedEditParts.size() - 1 && uniformGaps; i++) {
            int gap = editor.getAbsoluteBounds(orderedEditParts.get(i + 1)).x - editor.getAbsoluteBounds(orderedEditParts.get(i)).getRight().x;
            if (gap != expectedGap) {
                uniformGaps = false;
                if ((i + 1) != (orderedEditParts.size() - 1)) {
                    // Check for all gaps except last gap
                    assertEquals("Not uniform horizontal gap" + messsageDetails, expectedGap, gap);
                } else {
                    // Check for last gap, that can be different of other gap
                    // (because of the rounding the gap)
                    assertEquals("Incorrect horizontal gap between the second to last shape and the last shape" + messsageDetails, gap < expectedGap + (orderedEditParts.size() - 1), gap > expectedGap
                            - (orderedEditParts.size() - 1));
                }
            }
        }
    }

    /**
     * Create an ordered list of the selected edit parts from the leftmost shape
     * (with the minimum x coordinate) to the rightmost one (with the right side
     * with the maximum x coordinate).
     * 
     * @param editPartsToDistribute
     *            name of the Edit parts to distribute
     * @return A new list of ordered edit parts from the leftmost shape (with
     *         the minimum x coordinate) to the rightmost one
     */
    private List<SWTBotGefEditPart> sortEditPartsHorizontally(List<SWTBotGefEditPart> editPartsToDistribute) {
        final List<SWTBotGefEditPart> list = new ArrayList<SWTBotGefEditPart>(editPartsToDistribute);
        Comparator<SWTBotGefEditPart> comparator = new Comparator<SWTBotGefEditPart>() {
            public int compare(SWTBotGefEditPart c1, SWTBotGefEditPart c2) {
                return editor.getLocation(c1).x - editor.getLocation(c2).x;
            }
        };
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * Ensures that by launching distribute centers horizontally gaps action for
     * selected edit parts with the given names, edit parts are distributed with
     * uniforms gaps.
     * 
     * @param editPartsToDistribute
     *            name of the Edit parts to distribute
     * @param zoomLevel
     *            the zoom level
     * 
     */
    private void distributeCentersHorizontally(List<SWTBotGefEditPart> editPartsToDistribute, ZoomLevel zoomLevel, boolean horizontalScrollbar) {
        editor.select(editPartsToDistribute);
        editor.clickContextMenu(HORIZONTALLY_CENTERS_GAPS_LABEL);
        List<SWTBotGefEditPart> orderedEditParts = sortEditPartsCentersHorizontally(editPartsToDistribute);
        boolean uniformGaps = true;
        int expectedGap;
        if (horizontalScrollbar) {
            expectedGap = SCROLLBAR_HORIZONTALLY_CENTER_GAP_VALUE;
        } else {
            expectedGap = HORIZONTALLY_CENTER_GAP_VALUE;
        }
        String messsageDetails = " (distribute action=" + HORIZONTALLY_CENTERS_GAPS_LABEL + ", zoomLevel=" + zoomLevel.getLevel() + ", horizontalScrollbar=" + horizontalScrollbar + ")";
        for (int i = 0; i < orderedEditParts.size() - 1 && uniformGaps; i++) {
            int gap = editor.getAbsoluteBounds(orderedEditParts.get(i + 1)).getCenter().x - editor.getAbsoluteBounds(orderedEditParts.get(i)).getCenter().x;
            if (gap != expectedGap) {
                uniformGaps = false;
                if ((i + 1) != (orderedEditParts.size() - 1)) {
                    // Check for all gaps except last gap
                    assertEquals("Not uniform center horizontal gap" + messsageDetails, expectedGap, gap);
                } else {
                    // Check for last gap, that can be different of other gap
                    // (because of the rounding the gap)
                    assertEquals("Incorrect center horizontal gap between the second to last shape and the last shape" + messsageDetails, gap < expectedGap + (orderedEditParts.size() - 1),
                            gap > expectedGap - (orderedEditParts.size() - 1));
                }
            }
        }
    }

    /**
     * Create an ordered list of the selected edit parts from the leftmost shape
     * (with its center at the minimum x coordinate) to the rightmost one (with
     * its center at the maximum x coordinate).
     * 
     * @param editPartsToDistribute
     *            name of the Edit parts to distribute
     * @return A new list of ordered edit parts from the leftmost shape (with
     *         its center at the minimum x coordinate) to the rightmost one
     */
    private List<SWTBotGefEditPart> sortEditPartsCentersHorizontally(List<SWTBotGefEditPart> editPartsToDistribute) {
        final List<SWTBotGefEditPart> list = new ArrayList<SWTBotGefEditPart>(editPartsToDistribute);
        Comparator<SWTBotGefEditPart> comparator = new Comparator<SWTBotGefEditPart>() {
            public int compare(SWTBotGefEditPart c1, SWTBotGefEditPart c2) {
                return editor.getAbsoluteBounds(c1).getCenter().x - editor.getAbsoluteBounds(c2).getCenter().x;
            }
        };
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * Ensures that by launching distribute vertically gaps action for selected
     * edit parts with the given names, edit parts are distributed with uniforms
     * gaps.
     * 
     * @param editPartsToDistribute
     *            name of the Edit parts to distribute
     * @param verticalScrollBar
     *            indicates if the vertical scrollbar should appear
     * @param zoomLevel
     *            the zoom level
     * 
     */
    private void distributeGapsVertically(List<SWTBotGefEditPart> editPartsToDistribute, ZoomLevel zoomLevel, boolean verticalScrollBar) {
        editor.select(editPartsToDistribute);
        editor.clickContextMenu(VERTICALLY_GAPS_LABEL);
        List<SWTBotGefEditPart> orderedEditParts = sortEditPartsVertically(editPartsToDistribute);
        boolean uniformGaps = true;
        int expectedGap;
        if (verticalScrollBar) {
            expectedGap = SCROLLBAR_VERTICALLY_GAP_VALUE;
        } else {
            expectedGap = VERTICALLY_GAP_VALUE;
        }
        String messsageDetails = " (distribute action=" + VERTICALLY_GAPS_LABEL + ", zoomLevel=" + zoomLevel.getLevel() + ", verticalScrollBar=" + verticalScrollBar + ")";
        for (int i = 0; i < orderedEditParts.size() - 1 && uniformGaps; i++) {
            int gap = editor.getAbsoluteBounds(orderedEditParts.get(i + 1)).y - (editor.getAbsoluteBounds(orderedEditParts.get(i)).y + editor.getAbsoluteBounds(orderedEditParts.get(i)).height);
            if (gap != expectedGap) {
                uniformGaps = false;
                if ((i + 1) != (orderedEditParts.size() - 1)) {
                    // Check for all gaps except last gap
                    assertEquals("Not uniform vertically gap" + messsageDetails, expectedGap, gap);
                } else {
                    // Check for last gap, that can be different of other gap
                    // (because of the rounding the gap)
                    assertEquals("Incorrect vertically gap between the second to last shape and the last shape" + messsageDetails, gap < expectedGap + (orderedEditParts.size() - 1), gap > expectedGap
                            - (orderedEditParts.size() - 1));
                }
            }
        }
    }

    /**
     * Create an ordered list of the selected edit parts from the highest shape
     * (with the minimum y location) to the lowest one (with the bottom side
     * with the maximum y coordinate).
     * 
     * @param editPartsToDistribute
     *            name of the Edit parts to distribute
     * @return A new list of ordered edit parts from the highest shape (with the
     *         minimum y location) to the lowest one
     */
    private List<SWTBotGefEditPart> sortEditPartsVertically(List<SWTBotGefEditPart> editPartsToDistribute) {
        final List<SWTBotGefEditPart> list = new ArrayList<SWTBotGefEditPart>(editPartsToDistribute);
        Comparator<SWTBotGefEditPart> comparator = new Comparator<SWTBotGefEditPart>() {
            public int compare(SWTBotGefEditPart c1, SWTBotGefEditPart c2) {
                return editor.getAbsoluteBounds(c1).y - editor.getAbsoluteBounds(c2).y;
            }
        };
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * Ensures that by launching distribute centers vertically gaps action for
     * selected edit parts with the given names, edit parts are distributed with
     * uniforms gaps.
     * 
     * @param editPartsToDistribute
     *            name of the Edit parts to distribute
     * @param verticalScrollBar
     *            indicates if the vertical scrollbar should appear
     * @param zoomLevel
     *            the zoom level
     * 
     */
    private void distributeCentersVertically(List<SWTBotGefEditPart> editPartsToDistribute, ZoomLevel zoomLevel, boolean verticalScrollBar) {
        editor.select(editPartsToDistribute);
        editor.clickContextMenu(VERTICALLY_CENTERS_GAPS_LABEL);
        List<SWTBotGefEditPart> orderedEditParts = sortEditPartsCentersVertically(editPartsToDistribute);
        boolean uniformGaps = true;
        int expectedGap;
        if (verticalScrollBar) {
            expectedGap = SCROLLBAR_VERTICALLY_GAP_CENTER_VALUE;
        } else {
            expectedGap = VERTICALLY_CENTER_GAP_VALUE;
        }
        String messsageDetails = " (distribute action=" + VERTICALLY_CENTERS_GAPS_LABEL + ", zoomLevel=" + zoomLevel.getLevel() + ", verticalScrollBar=" + verticalScrollBar + ")";
        for (int i = 0; i < orderedEditParts.size() - 1 && uniformGaps; i++) {
            int gap = editor.getAbsoluteBounds(orderedEditParts.get(i + 1)).getCenter().y - (editor.getAbsoluteBounds(orderedEditParts.get(i)).getCenter().y);
            if (gap != expectedGap) {
                uniformGaps = false;
                if ((i + 1) != (orderedEditParts.size() - 1)) {
                    // Check for all gaps except last gap
                    assertEquals("Not uniform center vertical gap" + messsageDetails, expectedGap, gap);
                } else {
                    // Check for last gap, that can be different of other gap
                    // (because of the rounding the gap)
                    assertEquals("Incorrect center vertical gap between the second to last shape and the last shape" + messsageDetails, gap < expectedGap + (orderedEditParts.size() - 1),
                            gap > expectedGap - (orderedEditParts.size() - 1));
                }
            }
        }
    }

    /**
     * Create an ordered list of the selected edit parts from the highest shape
     * (with its center at the minimum y coordinate) to the lowest one (with the
     * bottom side with the maximum y coordinate).
     * 
     * @param editPartsToDistribute
     *            name of the Edit parts to distribute
     * @return A new list of ordered edit parts from the highest shape (with its
     *         center at the minimum y coordinate) to the lowest one
     */
    private List<SWTBotGefEditPart> sortEditPartsCentersVertically(List<SWTBotGefEditPart> editPartsToDistribute) {
        final List<SWTBotGefEditPart> list = new ArrayList<SWTBotGefEditPart>(editPartsToDistribute);
        Comparator<SWTBotGefEditPart> comparator = new Comparator<SWTBotGefEditPart>() {
            public int compare(SWTBotGefEditPart c1, SWTBotGefEditPart c2) {
                return editor.getAbsoluteBounds(c1).getCenter().y - editor.getAbsoluteBounds(c2).getCenter().y;
            }
        };
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * Ensures that by launching distribute horizontally gaps or distribute
     * centers horizontally gaps actions for selected edit parts with the given
     * names in various configurations (zoom level, horizontal scroll bar,
     * vertical scroll bar), edit parts are distributed with uniforms gaps.
     * 
     * @param zoomLevel
     *            the zoom level
     * @param horizontalScrollbar
     *            indicates if the horizontal scrollbar should appear
     * @param verticalScrollBar
     *            indicates if the vertical scrollbar should appear
     * @param nodesToDistribute
     *            name of the Edit parts to distribute
     * 
     */
    private void distributeHorizontallyActionTests(ZoomLevel zoomLevel, boolean horizontalScrollbar, boolean verticalScrollBar, String... nodesToDistribute) {
        List<SWTBotGefEditPart> editPartsToDistribute = getEditParts(nodesToDistribute);
        Collection<Integer> distributeKinds = Arrays.asList(DistributeAction.GAPS_HORIZONTALLY, DistributeAction.CENTERS_HORIZONTALLY);
        for (int distributeKind : distributeKinds) {
            switch (distributeKind) {
            case DistributeAction.GAPS_HORIZONTALLY:
                setUpEditorAccordingToDimensions(zoomLevel, horizontalScrollbar, verticalScrollBar);
                distributeGapsHorizontally(editPartsToDistribute, zoomLevel, horizontalScrollbar);
                try {
                    undo(DistributeAction.getLabel(DistributeAction.GAPS_HORIZONTALLY, true));
                    undo("Set Location or Size");
                } catch (WidgetNotFoundException e) {
                }
                break;
            case DistributeAction.CENTERS_HORIZONTALLY:
                setUpEditorAccordingToDimensions(zoomLevel, horizontalScrollbar, verticalScrollBar);
                distributeCentersHorizontally(editPartsToDistribute, zoomLevel, horizontalScrollbar);
                try {
                    undo(DistributeAction.getLabel(DistributeAction.CENTERS_HORIZONTALLY, true));
                    undo("Set Location or Size");
                } catch (WidgetNotFoundException e) {
                }
                break;
            default:
                break;
            }
        }
    }

    /**
     * Ensures that by launching distribute vertically gaps or distribute
     * centers vertically gaps actions for selected edit parts with the given
     * names in various configurations (zoom level, horizontal scroll bar,
     * vertical scroll bar), edit parts are distributed with uniforms gaps.
     * 
     * @param zoomLevel
     *            the zoom level
     * @param horizontalScrollbar
     *            indicates if the horizontal scrollbar should appear
     * @param verticalScrollBar
     *            indicates if the vertical scrollbar should appear
     * @param nodesToDistribute
     *            name of the Edit parts to distribute
     * 
     */
    private void distributeVerticallyActionTests(ZoomLevel zoomLevel, boolean horizontalScrollbar, boolean verticalScrollBar, String... nodesToDistribute) {
        List<SWTBotGefEditPart> editPartsToDistribute = getEditParts(nodesToDistribute);
        Collection<Integer> distributeKinds = Arrays.asList(DistributeAction.GAPS_VERTICALLY, DistributeAction.CENTERS_VERTICALLY);
        for (int distributeKind : distributeKinds) {
            switch (distributeKind) {
            case DistributeAction.GAPS_VERTICALLY:
                setUpEditorAccordingToDimensions(zoomLevel, horizontalScrollbar, verticalScrollBar);
                distributeGapsVertically(editPartsToDistribute, zoomLevel, verticalScrollBar);
                try {
                    undo(DistributeAction.getLabel(DistributeAction.GAPS_VERTICALLY, true));
                    undo("Set Location or Size");
                } catch (WidgetNotFoundException e) {
                }
                break;
            case DistributeAction.CENTERS_VERTICALLY:
                setUpEditorAccordingToDimensions(zoomLevel, horizontalScrollbar, verticalScrollBar);
                distributeCentersVertically(editPartsToDistribute, zoomLevel, verticalScrollBar);
                try {
                    undo(DistributeAction.getLabel(DistributeAction.CENTERS_VERTICALLY, true));
                    undo("Set Location or Size");
                } catch (WidgetNotFoundException e) {
                }
                break;
            default:
                break;
            }
        }
    }

    /**
     * Ensure that the distribute actions is disabled when only less than 3
     * shapes are selected or when selected several shapes not at same level.
     * 
     * @param menuDistribute
     *            distribute actions menu
     * @param nodesToDistribute
     *            name of the Edit parts to distribute
     */
    private void testActionsDisablement(SWTBotMenu menuDistribute, String... nodesToDistribute) {
        List<SWTBotGefEditPart> editPartsToDistribute = getEditParts(nodesToDistribute);
        editor.select(editPartsToDistribute);
        for (int distributeKind : ALL_DISTRIBUTE_KIND) {
            switch (distributeKind) {
            case DistributeAction.GAPS_HORIZONTALLY:
                final SWTBotMenu menuDistributeHorizontalGaps = menuDistribute.menu(HORIZONTALLY_GAPS_LABEL);
                assertEquals("Distribute Action should be disabled when select 2 shapes or several shapes not at same level", false, menuDistributeHorizontalGaps.isEnabled());
                break;
            case DistributeAction.CENTERS_HORIZONTALLY:
                final SWTBotMenu menuDistributeCentersHorizontalGaps = menuDistribute.menu(HORIZONTALLY_CENTERS_GAPS_LABEL);
                assertEquals("Distribute Action should be disabled when select 2 shapes or several shapes not at same level", false, menuDistributeCentersHorizontalGaps.isEnabled());
                break;
            case DistributeAction.GAPS_VERTICALLY:
                final SWTBotMenu menuDistributeVerticallyGaps = menuDistribute.menu(VERTICALLY_GAPS_LABEL);
                assertEquals("Distribute Action should be disabled when select 2 shapes or several shapes not at same level", false, menuDistributeVerticallyGaps.isEnabled());
                break;
            case DistributeAction.CENTERS_VERTICALLY:
                final SWTBotMenu menuDistributeCentersVerticallyGaps = menuDistribute.menu(VERTICALLY_CENTERS_GAPS_LABEL);
                assertEquals("Distribute Action should be disabled when select 2 shapes or several shapes not at same level", false, menuDistributeCentersVerticallyGaps.isEnabled());
                break;
            default:
                break;
            }
        }
    }

    /**
     * Ensure that the distribute actions is disabled when selecting border
     * nodes not on same side.
     * 
     * @param menuDistribute
     *            distribute actions menu
     * @param nodesToDistribute
     *            name of the Edit parts to distribute
     */
    private void testActionsDisablementForBorderNodes(SWTBotMenu menuDistribute, String... nodesToDistribute) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Component Diagram-MovePortOnContainer", "Distribute Action Bordered Nodes", DDiagram.class);
        List<SWTBotGefEditPart> editPartsToDistribute = getEditParts(nodesToDistribute);
        editor.select(editPartsToDistribute);
        for (int distributeKind : ALL_DISTRIBUTE_KIND) {
            switch (distributeKind) {
            case DistributeAction.GAPS_HORIZONTALLY:
                final SWTBotMenu menuDistributeHorizontalGaps = menuDistribute.menu(HORIZONTALLY_GAPS_LABEL);
                assertEquals("Distribute Action should be disabled when select border nodes not on same side", false, menuDistributeHorizontalGaps.isEnabled());
                break;
            case DistributeAction.CENTERS_HORIZONTALLY:
                final SWTBotMenu menuDistributeCentersHorizontalGaps = menuDistribute.menu(HORIZONTALLY_CENTERS_GAPS_LABEL);
                assertEquals("Distribute Action should be disabled when select border nodes not on same side", false, menuDistributeCentersHorizontalGaps.isEnabled());
                break;
            case DistributeAction.GAPS_VERTICALLY:
                final SWTBotMenu menuDistributeVerticallyGaps = menuDistribute.menu(VERTICALLY_GAPS_LABEL);
                assertEquals("Distribute Action should be disabled when select border nodes not on same side", false, menuDistributeVerticallyGaps.isEnabled());
                break;
            case DistributeAction.CENTERS_VERTICALLY:
                final SWTBotMenu menuDistributeCentersVerticallyGaps = menuDistribute.menu(VERTICALLY_CENTERS_GAPS_LABEL);
                assertEquals("Distribute Action should be disabled when select border nodes not on same side", false, menuDistributeCentersVerticallyGaps.isEnabled());
                break;
            default:
                break;
            }
        }
    }

    /**
     * Returns an editor set up according to the given test dimensions.
     * 
     * @param zoomLevel
     *            the zoom level
     * @param horizontalScrollbar
     *            indicates whether we should drop elements in order to make
     *            horizontal scroll bar appear
     * @param verticalScrollBar
     *            indicates whether we should drop elements in order to make
     *            vertical scroll bar appear
     */
    private void setUpEditorAccordingToDimensions(ZoomLevel zoomLevel, boolean horizontalScrollbar, boolean verticalScrollBar) {
        editor.zoom(zoomLevel);
        SWTBotGefEditPart editPartToMoveForCreatingScroll = editor.getEditPart("CanvasPackageToDrop2", IAbstractDiagramNodeEditPart.class);
        editor.select(editPartToMoveForCreatingScroll);
        Point initialLocation = editor.getBounds(editPartToMoveForCreatingScroll).getCenter();
        int horizontalShift = 0;
        int verticalShift = 0;
        if (horizontalScrollbar) {
            horizontalShift = 1000;
        }
        if (verticalScrollBar) {
            verticalShift = 1000;
        }
        editor.drag(initialLocation, new Point(initialLocation.x + horizontalShift, initialLocation.y + verticalShift));
    }

    /**
     * Get related edit parts to the given names.
     * 
     * @param editPartsToDistribute
     *            name of the Edit parts to distribute
     */
    private List<SWTBotGefEditPart> getEditParts(String... nodesToDistribute) {
        List<SWTBotGefEditPart> editPartsToDistribute = new LinkedList<>();
        for (String nodeToDistribute : nodesToDistribute) {
            SWTBotGefEditPart nodeEditPart = editor.getEditPart(nodeToDistribute, IAbstractDiagramNodeEditPart.class);
            editPartsToDistribute.add(nodeEditPart);
        }
        return editPartsToDistribute;
    }
}
