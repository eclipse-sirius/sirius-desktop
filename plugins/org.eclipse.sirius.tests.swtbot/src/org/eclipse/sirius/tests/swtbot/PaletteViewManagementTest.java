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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

/**
 * Test the Palette View Mangement. - Test the view Palette with representation
 * opened.
 * 
 * @author jdupont
 * 
 */
public class PaletteViewManagementTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String DATA_UNIT_DIR = "data/unit/paletteViewManagement/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String REPRESENTATION_NAME = "root package entities";

    private static final String REPRESENTATION_NAME_2 = "root package entities Archetype";

    private static final String VIEW_NAME_PALETTE = "Palette";

    private SWTBotView paletteView;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        localSession = null;
        super.tearDown();
    }

    /**
     * Test with Open Diagram and Open Palette:
     * <P>
     * - Check the palette view is closed
     * <P>
     * - Open a diagram
     * <P>
     * - Open the palette view
     * <P>
     * - Check the palette view contains the same tools
     */
    public void testOpenDiagramOpenPalette() {
        // Check the palette view is closed
        assertEquals(true, notFindingView(VIEW_NAME_PALETTE));
        // Open the diagram
        openDiagram(REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME);
        // Open the palette view
        openView("General", VIEW_NAME_PALETTE);
        try {
            // Check the palette view containment
            checkPaletteViewContainmentAndAddElement("Class", "NewEClass3", 150, 200, REPRESENTATION_NAME);
        } finally {
            // Close the view
            closeView(VIEW_NAME_PALETTE);
        }
    }

    /**
     * Test with Open Palette and Open Diagram:
     * <P>
     * - Open the palette view
     * <P>
     * - Open the diagram
     * <P>
     * - Check the palette contains the expected tools
     * <P>
     * - Close the view
     * <P>
     * - Check the palette (editor's palette) is reappeared and contains the
     * same tools
     */
    public void testOpenPaletteOpenDiagram() {
        // Open the palette View
        openView("General", VIEW_NAME_PALETTE);
        try {
            // Open the diagram
            openDiagram(REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME);
            // Check the palette view containment
            checkPaletteViewContainmentAndAddElement("Class", "NewEClass3", 150, 200, REPRESENTATION_NAME);
        } finally {
            // Close the view
            closeView(VIEW_NAME_PALETTE);
        }
        // Check the palette (editor palette) is reappeared and contains the
        // same tools
        checkPaletteViewContainmentAndAddElement("Class", "NewEClass4", 300, 200, REPRESENTATION_NAME);
    }

    /**
     * Test with Open Palette and with 2 Diagrams opened:
     * <P>
     * - Open the palette view
     * <P>
     * - Open two editors
     * <P>
     * - Check that changing the focus/the active editor, change the content of
     * the palette view
     */
    public void testOpenPaletteOpenTwoDiagram() {
        // Open the palette view
        openView("General", VIEW_NAME_PALETTE);
        try {
            // Open two editors
            openDiagram(REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME);
            // Check that changing the focus/the active editor, change the
            // content
            // of the palette view
            checkPaletteViewContainmentAndAddElement("Class", "NewEClass3", 150, 200, REPRESENTATION_NAME);
            openDiagram(REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_2);
            // Check that changing the focus/the active editor, change the
            // content
            // of the palette view
            checkPaletteViewContainmentAndAddElement("Role", "newRole4", 150, 200, REPRESENTATION_NAME_2);
        } finally {
            // Close the view
            closeView(VIEW_NAME_PALETTE);
        }
    }

    /**
     * Check if the view is not opened.
     * 
     * @param viewName
     *            the view name
     * @return boolean true if the view is not found.
     */
    private boolean notFindingView(String viewName) {
        boolean viewNotFind = false;
        try {
            bot.viewByTitle(viewName);
        } catch (WidgetNotFoundException expected) {
            viewNotFind = true;
            return viewNotFind;
        }
        fail("The view should not be opened");
        return viewNotFind;
    }

    /**
     * Opened Diagram representation.
     * 
     * @param representationDescriptionName
     *            the name of description
     * @param representationName
     *            the representation name
     * @return the editor opened
     */
    private SWTBotSiriusDiagramEditor openDiagram(String representationDescriptionName, String representationName) {
        SWTBotUtils.waitAllUiEvents();
        SWTBotSiriusDiagramEditor swtBotEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), representationDescriptionName, representationName, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();
        return swtBotEditor;
    }

    /**
     * Open the view.
     * 
     * @param category
     *            the category of window named "show View"
     * @param viewName
     *            the view name to opened
     */
    private void openView(String category, String viewName) {
        SWTBotUtils.waitAllUiEvents();
        paletteView = designerViews.openViewByAPI("org.eclipse.gef.ui.palette_view", VIEW_NAME_PALETTE);
    }

    /**
     * Close the view.
     * 
     * @param viewName
     *            the view name
     */
    private void closeView(String viewName) {
        paletteView.close();
    }

    /**
     * Check palette view containment. For this use a tool from the palette.
     * 
     * @param toolName
     *            the tool's name
     * @param newElement
     *            the name of the new Element created by the method
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     */
    private void checkPaletteViewContainmentAndAddElement(String toolName, String newElement, int x, int y, String representationName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);
        editor.activateTool(toolName);
        editor.click(x, y);
        bot.waitUntil(new CheckSelectedCondition(editor, newElement));
    }
}
