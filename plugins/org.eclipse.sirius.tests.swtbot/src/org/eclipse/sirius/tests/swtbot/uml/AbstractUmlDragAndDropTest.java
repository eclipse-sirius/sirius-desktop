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
package org.eclipse.sirius.tests.swtbot.uml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.text.MessageFormat;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.tests.support.api.GraphicTestsSupportHelp;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import org.eclipse.sirius.tests.swtbot.Activator;

/**
 * Common class for all Uml drag and drop tests.
 * 
 * @author dlecan
 */
public abstract class AbstractUmlDragAndDropTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * temporary delta.
     */
    // Eyh, developer, raise a warning to the project leader if this delta
    // exceeds 10 pixels
    protected static final int TEMPORARY_DELTA = 0;

    private static final String TEMP_PROJECT_NAME = "DragAndDropTestProject";

    /**
     * The name of the semantic file.
     */
    protected static final String UML2_MODEL = "uml2.uml";

    private static final String SESSION_FILE = "uml2.aird";

    private static final String VSM_FILE = "uml2.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/dragAndDrop/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private String oldFont;

    /**
     * Current editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getProjectName() {
        return TEMP_PROJECT_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, UML2_MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        oldFont = changeDefaultFontName("Comic Sans MS");
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = openAndGetEditor(getRepresentationDescriptionName(), getRepresentationNameToOpen());
        editor.setSnapToGrid(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (oldFont != null) {
            changeDefaultFontName(oldFont);
        }
    }

    /**
     * Get representation name to open in setup.
     * 
     * @return Representation name to open
     */
    protected abstract String getRepresentationNameToOpen();

    /**
     * Get representation description name where to find the representation to
     * open in setup.
     * 
     * @return Representation description name
     */
    protected abstract String getRepresentationDescriptionName();

    /**
     * Open the editor with <code>representationName</code> and
     * <code>representationDescriptionName</code>, and set the zoom to 100%
     * 
     * @param representationDescriptionName
     *            The representation description name to open
     * @param representationName
     *            The representation name to open
     * @return the opened editor.
     */
    protected SWTBotSiriusDiagramEditor openAndGetEditor(final String representationDescriptionName, final String representationName) {
        final SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), representationDescriptionName, representationName, DDiagram.class);
        editor.zoomDefault();
        return editor;
    }

    /**
     * Check position and size.
     * 
     * @param elementNameToDnD
     *            Name of element to drag and drop.
     * @param originalBounds
     *            Original bounds of element.
     * @param newBounds
     *            New bounds of element.
     * @param translation
     *            Translation
     * @param delta
     *            Delta
     */
    protected void checkNewCoordinates(final String elementNameToDnD, final Rectangle originalBounds, final Rectangle newBounds, final Dimension translation, final int delta) {
        checkPositionChanged(elementNameToDnD, originalBounds, newBounds);
        checkNewSize(elementNameToDnD, originalBounds, newBounds);
        checkNewPosition(elementNameToDnD, originalBounds, newBounds, translation, delta);
    }

    /**
     * Check position and size.
     * 
     * @param elementNameToDnD
     *            Name of element to drag and drop.
     * @param originalBounds
     *            Original bounds of element.
     * @param newBounds
     *            New bounds of element.
     * @param translation
     *            Translation
     */
    protected void checkNewCoordinates(final String elementNameToDnD, final Rectangle originalBounds, final Rectangle newBounds, final Dimension translation) {
        // delta = 1 because of int to double conversions, operations, ...
        checkNewCoordinates(elementNameToDnD, originalBounds, newBounds, translation, 1);
    }

    /**
     * Check size.
     * 
     * @param elementNameToDnD
     *            Name of element to drag and drop.
     * @param originalBounds
     *            Original bounds of element.
     * @param newBounds
     *            New bounds of element.
     */
    protected void checkNewSize(final String elementNameToDnD, final Rectangle originalBounds, final Rectangle newBounds) {
        assertThat(elementNameToDnD + " size has changed", newBounds.getSize(), equalTo(originalBounds.getSize()));
    }

    /**
     * Check position has changed.
     * 
     * @param elementNameToDnD
     *            Name of element to drag and drop.
     * @param originalBounds
     *            Original bounds of element.
     * @param newBounds
     *            New bounds of element.
     */
    protected void checkPositionChanged(final String elementNameToDnD, final Rectangle originalBounds, final Rectangle newBounds) {
        assertThat(elementNameToDnD + " position hasn't change", newBounds.getLocation(), not(equalTo(originalBounds.getLocation())));
    }

    /**
     * Check position.
     * 
     * @param elementNameToDnD
     *            Name of element to drag and drop.
     * @param originalBounds
     *            Original bounds of element.
     * @param newBounds
     *            New bounds of element.
     * @param translation
     *            Translation
     * @param delta
     *            Delta
     */
    protected void checkNewPosition(final String elementNameToDnD, final Rectangle originalBounds, final Rectangle newBounds, final Dimension translation, final int delta) {
        final int localDelta = TEMPORARY_DELTA;
        GraphicTestsSupportHelp.assertEquals(String.format("Translation of %s is wrong (delta : %s)", elementNameToDnD, localDelta), originalBounds.getLocation().translate(translation),
                newBounds.getLocation(), localDelta);
    }

    /**
     * Check constant gap between 2 containers.
     * 
     * @param elementName1ToDnD
     *            Element 1 to drag and drop.
     * @param originalTopLeftPoint1
     *            Original top left point of element 1
     * @param newTopLeftPoint1
     *            New top left point of element 1
     * @param elementName2ToDnD
     *            Element 2 to drag and drop.
     * @param originalTopLeftPoint2
     *            Original top left point of element 2
     * @param newTopLeftPoint2
     *            New top left point of element 2
     */
    protected void checkConstantGapBetween(final String elementName1ToDnD, final Point originalTopLeftPoint1, final Point newTopLeftPoint1, final String elementName2ToDnD,
            final Point originalTopLeftPoint2, final Point newTopLeftPoint2) {
        final double originalDistance = originalTopLeftPoint1.getDistance(originalTopLeftPoint2);
        final double newDistance = newTopLeftPoint1.getDistance(newTopLeftPoint2);

        assertEquals("Distance between " + elementName1ToDnD + " and " + elementName2ToDnD + "has changed", newDistance, originalDistance, 1);
    }

    /**
     * Get bounds of specified element.
     * 
     * @param label
     *            Element to find.
     * @return Bounds of element.
     */
    protected Rectangle getEditPartBounds(final String label) {
        return getEditPartBounds(getGraphicalEditPart(label));
    }

    /**
     * Get bounds of specified element.
     * 
     * @param part
     *            Element to find.
     * @return Bounds of element.
     */
    protected Rectangle getEditPartBounds(final GraphicalEditPart part) {
        final Rectangle originalRelativeBounds = part.getFigure().getBounds().getCopy();
        part.getFigure().translateToAbsolute(originalRelativeBounds);
        // Modified by reference, but it works on a copy
        return originalRelativeBounds;
    }

    /**
     * Get edit part of specified element.
     * 
     * @param label
     *            Element to find.
     * @return SWTBot Edit part
     */
    protected SWTBotGefEditPart getUmlEditPart(final String label) {
        final SWTBotGefEditPart editPart = editor.getEditPart(label);
        return editPart.parent();
    }

    /**
     * Get edit part of specified element.
     * 
     * @param label
     *            Element to find.
     * @return SWTBot Edit part
     */
    protected GraphicalEditPart getGraphicalEditPart(final String label) {
        return (GraphicalEditPart) getUmlEditPart(label).part();
    }

    /**
     * Check that the location of the bordered node with <code>name1</code> in
     * the editor <code>editor1</code> has the same location as the bordered
     * node <code>name2</code> in the editor <code>editor2</code>.
     * 
     * @param editor1
     *            The first editor
     * @param name1
     *            The name of the bordered node, in the first editor, to compare
     * @param editor2
     *            The second editor
     * @param name2
     *            The name of the bordered node, in the second editor, to
     *            compare
     * @param message
     *            The error message in case of difference
     */
    protected void assertSameLocation(SWTBotSiriusDiagramEditor editor1, String name1, SWTBotSiriusDiagramEditor editor2, String name2, String message) {
        Point expected = editor1.getAbsoluteLocation(name1, AbstractDiagramBorderNodeEditPart.class);
        Point actual = editor2.getAbsoluteLocation(name2, AbstractDiagramBorderNodeEditPart.class);
        assertEquals(MessageFormat.format(message, name1, name2), expected, actual);
    }
}
