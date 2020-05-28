/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.support.api.GraphicTestsSupportHelp;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests for the popup menu tool that creates element by grouping several elements (grouping two EClass in a new
 * EPackage). Validate that the created EPackage is located in the middle of the canvas avoiding overlap.
 * 
 * See VP-1859.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class GroupElementsInOneOtherTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/tools/creation/popupMenu/VP-1859/";

    private static final String SEMANTIC_RESOURCE_NAME = "vp-1859.ecore";

    private static final String SESSION_RESOURCE_NAME = "vp-1859.aird";

    private static final String MODELER_RESOURCE_NAME = "vp-1859.odesign";

    private static final String FIRST_REPRESENTATION_NAME = "VP-1859-RealCase";

    private static final String FIRST_REPRESENTATION_INSTANCE_NAME = "new " + FIRST_REPRESENTATION_NAME;

    private static final String GROUP_TOOL_NAME = "Group in new Package";

    private static final String CREATION_TOOL_NAME = "Create 2 packages outside the selected element";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /** Bot of the DiagramEditPart */
    protected SWTBotGefEditPart diagramEditPartBot;

    private SWTBotGefEditPart class1ChildOfDiagramBot;

    private SWTBotGefEditPart class3ChildOfDiagramBot;

    private SWTBotGefEditPart class4ChildOfDiagramBot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    @Override
    protected boolean getAutoRefreshMode() {
        return true;
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        closeOutline();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), FIRST_REPRESENTATION_NAME, FIRST_REPRESENTATION_INSTANCE_NAME, DDiagram.class, true, true);

        editor.scrollTo(0, 0);

        diagramEditPartBot = editor.rootEditPart().children().get(0);

        class1ChildOfDiagramBot = diagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeEditPart.class)).get(0);
        class3ChildOfDiagramBot = diagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeEditPart.class)).get(2);
        class4ChildOfDiagramBot = diagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeEditPart.class)).get(3);
    }

    /**
     * Test that grouping two EClass into a new Package (without scroll), locates this new Package at the center of the
     * visible part of the canvas without overlapping.<BR>
     * Currently there is no overlapping for the top-left corner. A specific issue VP-2399 must be fix to check that
     * there is no overlap of all the figure.
     */
    public void testGroupActionInDiagramEditPartWithoutScroll() {
        // WARNING : This test need to be ran in a specific resolution (@see
        // Xephyr conf)

        // Select Class1 & Class3
        editor.select(class1ChildOfDiagramBot, class3ChildOfDiagramBot);
        // Get the center of the visible part of the editor before the group
        // action (in logical coordinates, not in screen coordinates)
        Point absoluteEditorCenter = getEditorVisiblePart(class4ChildOfDiagramBot.part()).getCenter();
        GraphicalHelper.screen2logical(absoluteEditorCenter, (IGraphicalEditPart) class4ChildOfDiagramBot.part());
        // Launch the group action
        editor.clickContextMenu(GROUP_TOOL_NAME);

        // Check that the top-left corner does not overlap another figure at the
        // same level (brother edit parts).
        SWTBotGefEditPart package5Bot = editor.getEditPart("Package5", AbstractDiagramContainerEditPart.class);
        Point package5AbsoluteLocation = editor.getAbsoluteLocation("Package5", AbstractDiagramContainerEditPart.class);
        assertFalse("The location (top-left corner) of the new figure should not overlaped an existing figure",
                isThereFigureAtLocation(((GraphicalEditPart) editor.mainEditPart().part()).getContentPane(), package5AbsoluteLocation,
                        Collections.singleton(((org.eclipse.gef.GraphicalEditPart) package5Bot.part()).getFigure())));

        // Assert that the created EPackage is in the center of the screen
        // Get the absolute location of p4 package (in logical coordinates, not
        // in screen coordinates)
        if (GraphicalHelper.getZoom(package5Bot.part()) != ZoomLevel.ZOOM_200.getAmount()) {
            GraphicTestsSupportHelp.assertEquals("The newly created Package5 should be at the center of the visible part of the diagram", absoluteEditorCenter, package5AbsoluteLocation, 5, 5);
        } else {
            // We don't make the same test with zoom at 200% because the middle
            // of the visible
            // part of the diagram is on another container so the new container
            // is shifted.
            Point expectedPoint = new Point(300, 125);
            if (!package5AbsoluteLocation.equals(expectedPoint)) {
                // Check the screen resolution to fail only when it is as expected
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                if (screenSize.width == 1440 || screenSize.height == 900) {
                    fail("The newly created Package5 should be at the center of the visible part of the diagram. expected:<" + expectedPoint + "> but was:<" + package5AbsoluteLocation + ">");
                }
            }
        }
    }

    /**
     * Test that creating two elements outside the current selection, does not overlapped this new packages
     * (VP-2609).<BR>
     * Currently there is no overlapping for the top-left corner. A specific issue VP-2399 must be fix to check that
     * there is no overlap of all the figure.
     */
    public void testMultiCreationOutsideCurrentSelection() {
        testMultiCreationOutsideCurrentSelection(false);
    }

    /**
     * Test that creating two elements outside the current selection (with snapToGrid enabled), does not overlapped this
     * new packages (VP-2609).<BR>
     * Currently there is no overlapping for the top-left corner. A specific issue VP-2399 must be fix to check that
     * there is no overlap of all the figure.
     */
    public void testMultiCreationOutsideCurrentSelectionWithSnapToGridEnabled() {
        testMultiCreationOutsideCurrentSelection(true);
    }

    /**
     * Test that creating two elements outside the current selection (with snapToGrid enabled), does not overlapped this
     * new packages (VP-2609).<BR>
     * Currently there is no overlapping for the top-left corner. A specific issue VP-2399 must be fix to check that
     * there is no overlap of all the figure.
     */
    private void testMultiCreationOutsideCurrentSelection(boolean isSnapToGridEnabled) {
        if (isSnapToGridEnabled) {
            editor.setSnapToGrid(true, 50, 2);
        }
        try {
            // Select Class1 & Class3
            editor.select(class1ChildOfDiagramBot, class3ChildOfDiagramBot);
            // Launch the action that create 2 packages outside the current
            // selected
            // element
            editor.clickContextMenu(CREATION_TOOL_NAME);

            // Check that the two new packages top-left corner does not overlap
            // each
            // other.
            Point package5AbsoluteLocation = editor.getAbsoluteLocation("Package5", AbstractDiagramContainerEditPart.class);
            Point package6AbsoluteLocation = editor.getAbsoluteLocation("Package6", AbstractDiagramContainerEditPart.class);

            assertFalse("The location (top-left corner) of the first created figure should not overlaped the location (top-left corner) of the second created figure.",
                    package5AbsoluteLocation.equals(package6AbsoluteLocation));
            int padding = SiriusLayoutDataManager.PADDING;
            if (isSnapToGridEnabled) {
                padding = 50;

            }
            assertEquals("The x coordinate of Package6 should have a delta of " + padding + " pixels with the x coordinate of the Package5.", package5AbsoluteLocation.x + padding,
                    package6AbsoluteLocation.x);
            assertEquals("The y coordinate of Package6 should have a delta of " + padding + " pixels with the y coordinate of the Package5.", package5AbsoluteLocation.y + padding,
                    package6AbsoluteLocation.y);
            if (isSnapToGridEnabled) {
                checkLocationAlignOnGrid(package5AbsoluteLocation, "Package5", padding);
                checkLocationAlignOnGrid(package6AbsoluteLocation, "Package6", padding);
            }
        } finally {
            editor.setSnapToGrid(false);
        }
    }

    /**
     * Get a rectangle representing the visible part in screen coordinates.
     * 
     * @param part
     *            a part from the diagram.
     * @return A rectangle representing the visible part in screen coordinates.
     */
    public static Rectangle getEditorVisiblePart(EditPart part) {
        Rectangle result = new Rectangle();
        Control control = part.getViewer().getControl();
        if (control instanceof FigureCanvas) {
            result = ((FigureCanvas) part.getViewer().getControl()).getViewport().getBounds();
        }
        return result;
    }

    /**
     * Check if there is a figure (children of parent) at the supplied <code>location</code>.
     * 
     * @param parent
     *            The parent figure
     * @param location
     *            the location being tested.
     * @param figuresToIgnore
     *            {@link IFigure} to ignore
     * 
     * @return true if there is a figure at this location, false otherwise.
     */
    protected boolean isThereFigureAtLocation(IFigure parent, Point location, Set<IFigure> figuresToIgnore) {
        ListIterator<IFigure> listIterator = parent.getChildren().listIterator();
        while (listIterator.hasNext()) {
            IFigure child = listIterator.next();
            if (!figuresToIgnore.contains(child)) {
                Rectangle cBounds = child.getBounds();
                if (cBounds.contains(location)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession = null;
        editor = null;
        diagramEditPartBot = null;
        class1ChildOfDiagramBot = null;
        class3ChildOfDiagramBot = null;
        class4ChildOfDiagramBot = null;
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Check that a diagram element is aligned on the grid.
     * 
     * @param location
     *            location of the diagram element element to check
     * @param elementNameToDisplay
     *            The name of the element displayed in case of error
     * @param gridSpacing
     *            The current grid spacing
     */
    private void checkLocationAlignOnGrid(Point location, String elementNameToDisplay, int gridSpacing) {
        boolean locationIsOK = (location.x % gridSpacing) == 0 || (location.y % gridSpacing) == 0;
        if (!locationIsOK) {
            fail("For " + elementNameToDisplay + ", the x or y coordinate of the top left corner should be on the grid (grid spacing = " + gridSpacing + "), but was: " + location + ".");
        }
    }
}
