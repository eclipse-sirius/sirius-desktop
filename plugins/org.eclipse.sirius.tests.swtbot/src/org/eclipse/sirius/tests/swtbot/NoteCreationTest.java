/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.NoteFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusDescriptionCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test class for double click tool and navigation operation.
 * 
 * @author smonnier
 */
public class NoteCreationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "root package entities";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String MODEL = "2083.ecore";

    private static final String SESSION_FILE = "2083.aird";

    private static final String DATA_UNIT_DIR = "data/unit/tools/creation/note/";

    private static final String FILE_DIR = "/";

    private static final String P2_PACKAGE_NAME = "p2";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        bot.viewById("org.eclipse.ui.views.ContentOutline").close();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        editor.setSnapToGrid(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // Restore the default zoom level
        editor.click(1, 1); // Set the focus on the diagram
        editor.zoom(ZoomLevel.ZOOM_100);
        // Go to the origin to avoid scroll bar
        editor.scrollTo(0, 0);
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInDiagramWithoutScroll() throws Exception {
        testNoteCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_100);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInDiagramWithoutScrollAndChangeZoom() throws Exception {
        testNoteCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_50);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInDiagramWithScroll() throws Exception {
        testNoteCreationInDiagramWithScroll(ZoomLevel.ZOOM_100);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInDiagramWithScrollAndChangeZoom() throws Exception {
        testNoteCreationInDiagramWithScroll(ZoomLevel.ZOOM_50);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithoutScroll() throws Exception {
        testNoteCreationInContainer(ZoomLevel.ZOOM_100, "p1");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithoutScrollAndChangeZoom() throws Exception {
        testNoteCreationInContainer(ZoomLevel.ZOOM_50, "p1");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInDiagram() throws Exception {
        testNoteCreationInContainer(ZoomLevel.ZOOM_100, "p2");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInDiagramAndChangeZoom() throws Exception {
        testNoteCreationInContainer(ZoomLevel.ZOOM_50, "p2");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInContainer() throws Exception {
        testNoteCreationInContainer(ZoomLevel.ZOOM_100, "p3", "C31");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInContainerAndChangeZoom() throws Exception {
        testNoteCreationInContainer(ZoomLevel.ZOOM_50, "p3", "C31");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInContainerAndDiagram() throws Exception {
        testNoteCreationInContainer(ZoomLevel.ZOOM_100, "p4", "C41");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoteCreationInContainerWithScrollInContainerAndDiagramAndChangeZoom() throws Exception {
        testNoteCreationInContainer(ZoomLevel.ZOOM_50, "p4", "C41");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    private void testNoteCreationInDiagramWithoutScroll(ZoomLevel zoomLevel) throws Exception {
        editor.zoom(zoomLevel);
        // Get the insertion location for the not
        Point location = new Point(2, 2);
        // Select the Note tool and click in the editor (the coordinates use to
        // click is with the zoom level)
        editor.activateTool("Note");
        editor.click(location.getScaled(zoomLevel.getAmount()).x, location.getScaled(zoomLevel.getAmount()).y);
        // Write a label in the note
        String newLabel = "My note";
        editor.directEditType(newLabel);
        // Check the location of the note
        assertNoteAtLocation(newLabel, location);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    private void testNoteCreationInDiagramWithScroll(ZoomLevel zoomLevel) throws Exception {
        editor.zoom(zoomLevel);
        // Reveal p2 (to scroll in the diagram)
        editor.select(editor.getSelectableEditPart(P2_PACKAGE_NAME));
        editor.reveal(editor.getEditPart(P2_PACKAGE_NAME).part());
        // Get the location of p2 package (relative the part visible on the
        // screen)
        Point p2Location = editor.getLocation(P2_PACKAGE_NAME, DNodeContainerEditPart.class);
        // Get the absolute location of p2 package from origin (0, 0)
        Point p2AbsoluteLocation = editor.getAbsoluteLocation(P2_PACKAGE_NAME, DNodeContainerEditPart.class);
        // Get the insertion location for the note (use the relative coordinate,
        // that's what is send to the request in reality)
        Point delta = new Point(-2, -2);
        Point location = p2Location.getTranslated(delta.getScaled(zoomLevel.getAmount()));
        // Select the Note tool and click in the editor
        editor.activateTool("Note");
        editor.click(location.x, location.y);
        // Write a label in the note
        String newLabel = "My note";
        editor.directEditType(newLabel);
        // Check the location of the note (with the absolute coordinate)
        assertNoteAtLocation(newLabel, p2AbsoluteLocation.getTranslated(delta));
        // Check note's label alignment
        checkNoteLabelAlignment(editor.getEditPart(newLabel));
    }

    /**
     * 
     */
    private void checkNoteLabelAlignment(SWTBotGefEditPart editPart) {
        assertNotNull("The edit part shouldn't be null for the note label", editPart);
        EditPart part = editPart.part();
        assertTrue("The edit part of the note label should an instance of SiriusDescriptionCompartmentEditPart", part instanceof SiriusDescriptionCompartmentEditPart);
        // Retrieve the bounds of the Note
        EditPart parentPart = part.getParent();
        assertTrue("The edit part of the note should an instance of SiriusNoteEditPart", parentPart instanceof SiriusNoteEditPart);
        IFigure noteFigure = ((SiriusNoteEditPart) parentPart).getFigure();
        assertTrue("The figure of the note should an instance of NoteFigure", noteFigure instanceof NoteFigure);
        Rectangle noteFigureBounds = noteFigure.getBounds();
        Border noteFigureBorder = noteFigure.getBorder();
        Insets noteFigureInsets = noteFigureBorder.getInsets(noteFigure);
        // Retrieve the bounds of the label of the Note
        IFigure wrapLabelFigure = ((SiriusDescriptionCompartmentEditPart) part).getFigure();
        assertTrue("The figure of the note label should an instance of WrappingLabel", wrapLabelFigure instanceof WrappingLabel);
        List<?> childrenFigure = wrapLabelFigure.getChildren();
        assertSame("The figure of the note label should have only one child", 1, childrenFigure.size());
        Object flowPageFigure = childrenFigure.get(0);
        assertTrue("The figure of the note label child should be an instance of FlowPage", flowPageFigure instanceof FlowPage);
        Rectangle flowPageBounds = ((FlowPage) flowPageFigure).getBounds();
        // The label should be TOP & CENTER aligned.
        int middleOfTheNoteX = noteFigureBounds.x + ((noteFigureBounds.width + noteFigureInsets.left - noteFigureInsets.right) / 2);
        int middleOfTheLabelX = flowPageBounds.getCenter().x;
        assertEquals("The label should be centered in the note", middleOfTheNoteX, middleOfTheLabelX, 2);
        int middleOfTheNoteY = noteFigureBounds.getCenter().y;
        int beginingOfTheLabelY = flowPageBounds.y;
        int endOfTheLabelY = flowPageBounds.y + flowPageBounds.height;
        assertTrue("The label should be at the top of the note", beginingOfTheLabelY < middleOfTheNoteY);
        assertTrue("The label should be at the top of the note", endOfTheLabelY < middleOfTheNoteY);
        // We do all of this because the following commented lines don't works
        // assertSame("The note label should be centered",
        // PositionConstants.CENTER, ((SiriusDescriptionCompartmentEditPart)
        // part).getLabelDelegate().getTextJustification());
        // assertSame("The note label should be on top", PositionConstants.TOP,
        // ((SiriusDescriptionCompartmentEditPart)
        // part).getLabelDelegate().getAlignment());
    }

    /**
     * Test method.
     * 
     * @param zoomLevel
     *            The zoom factor
     * @param packageNameToReveal
     *            the name of the package to reveal before inserting the note.
     * @throws Exception
     *             Test error.
     */
    private void testNoteCreationInContainer(ZoomLevel zoomLevel, String packageNameToReveal) throws Exception {
        editor.zoom(zoomLevel);
        // Reveal the package (and eventually scroll in the diagram)
        editor.reveal(packageNameToReveal);
        // Get the location of the package (relative the part visible on the
        // screen)
        Point packageLocation = editor.getLocation(packageNameToReveal, DNodeContainerEditPart.class);
        // Get the absolute location of the package from origin (0, 0)
        Point packageAbsoluteLocation = editor.getAbsoluteLocation(packageNameToReveal, DNodeContainerEditPart.class);
        // Get the insertion location for the note (use the relative coordinate,
        // that's what is send to the request in reality)
        Point delta = new Point(30, 30);
        Point location = packageLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()));
        // Select the Note tool and click in the editor
        editor.activateTool("Note");
        editor.click(location.x, location.y);
        // Write a label in the note
        String newLabel = "My note";
        editor.directEditType(newLabel);
        // Check the location of the note (with the absolute coordinate)
        assertNoteAtLocation(newLabel, packageAbsoluteLocation.getTranslated(delta));
    }

    /**
     * Test method.
     * 
     * @param zoomLevel
     *            The zoom factor
     * @param packageNameToReveal
     *            the name of the package to reveal before inserting the note.
     * @throws Exception
     *             Test error.
     */
    private void testNoteCreationInContainer(ZoomLevel zoomLevel, String packageNameToReveal, String classNameToReveal) throws Exception {
        editor.zoom(zoomLevel);
        // Reveal the class (and eventually scroll in the diagram and in the
        // container)
        editor.reveal(classNameToReveal);
        // Get the location of the class (relative the part visible on the
        // screen)
        Point classLocation = editor.getLocation(classNameToReveal, DNodeList2EditPart.class);
        // Get the absolute location of the class from origin (0, 0)
        Point classAbsoluteLocation = editor.getAbsoluteLocation(classNameToReveal, DNodeList2EditPart.class);
        // Get the insertion location for the note (use the relative coordinate,
        // that's what is send to the request in reality)
        Point delta = new Point(-30, -30);
        Point location = classLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()));
        // Select the Note tool and click in the editor
        editor.activateTool("Note");
        editor.click(location.x, location.y);
        // Write a label in the note
        String newLabel = "My note";
        editor.directEditType(newLabel);
        // Check the location of the note (with the absolute coordinate)
        assertNoteAtLocation(newLabel, classAbsoluteLocation.getTranslated(delta));
    }

    /**
     * Check that the note is near the expected location. We don't check
     * precisely the location because the zoom can have round effect on the
     * location.
     * 
     * @param excpectedLocation
     *            the absolute position within the graphical viewer
     */
    private void assertNoteAtLocation(String noteLabel, Point excpectedLocation) {
        Point noteLocation = editor.getAbsoluteLocation(noteLabel, NoteEditPart.class);
        // assertTrue("The note has a bad location. expected:<" +
        // excpectedLocation.toString() + "> but was:<" + noteLocation + ">",
        // excpectedLocation.getDistance(noteLocation) < 2);
        assertEquals("The note has been created at wrong location.", adaptLocation(excpectedLocation), noteLocation);
    }

    /**
     * Possibility to adapt the expected location according to some parameters
     * (snap to grid, ...).
     * 
     * @param expectedLocation
     *            The initial expected location
     * @return The adapted location
     */
    protected Point adaptLocation(Point expectedLocation) {
        return expectedLocation;
    }
}
