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
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

/**
 * Tests for the popup menu tool.
 * 
 * @author cnotot
 */
public class PopupMenuTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new vp939";

    private static final String REPRESENTATION_NAME = "vp939";

    private static final String MODEL = "vp939.ecore";

    private static final String SESSION_FILE = "vp939.aird";

    private static final String VSM_FILE = "vp939.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/popupMenu/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private SWTBotSiriusDiagramEditor editor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu1OnPackage() throws Exception {

        // Select a diagram element named "ref"
        editor.click(editor.getEditPart("sub package"));
        editor.select(editor.getSelectableEditPart("sub package"));
        try {
            editor.clickContextMenu("myAction1.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        assertFalse("The menu should not exist", true);
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu2OnPackage() throws Exception {

        // Select a diagram element named "ref"
        editor.click(editor.getEditPart("sub package"));
        editor.select(editor.getSelectableEditPart("sub package"));
        try {
            editor.clickContextMenu("myAction2.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        assertFalse("The menu should not exist", true);
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu3OnPackage() throws Exception {

        editor.click(editor.getEditPart("sub package"));
        editor.select(editor.getSelectableEditPart("sub package"));
        try {
            editor.clickContextMenu("myAction3.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        assertFalse("The menu should not exist", true);
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu1OnClass() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));
        try {
            editor.clickContextMenu("myAction1.1");
        } catch (WidgetNotFoundException e) {
            assertFalse("The action myAction1.1 of the menu myMenu1 should exist", true);
        }
        try {
            editor.clickContextMenu("myAction1.2");
        } catch (WidgetNotFoundException e) {
            return;
        }
        assertFalse("The action myAction1.2 of the menu myMenu1 should not exist", true);
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu2OnClass() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));
        try {
            editor.clickContextMenu("myAction2.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        assertFalse("The menu should not exist", true);
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu3OnClass() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));
        try {
            editor.clickContextMenu("myAction3.2");
            assertFalse("The action myAction3.2 of the menu myMenu3 should not exist", true);
        } catch (WidgetNotFoundException e) {
        }
        try {
            editor.clickContextMenu("myAction3.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        assertFalse("The action myAction3.1 of the menu myMenu3 should not exist", true);
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu1OnAttribute() throws Exception {
        editor.click(editor.getEditPart("att"));
        editor.select(editor.getEditPart("att"));
        try {
            editor.clickContextMenu("myAction1.2");
        } catch (WidgetNotFoundException e) {
            assertFalse("The action myAction1.2 of the menu myMenu1 should exist", true);
        }
        try {
            editor.clickContextMenu("myAction1.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        assertFalse("The action myAction1.1 of the menu myMenu1 should not exist", true);
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu2OnAttribute() throws Exception {

        editor.click(editor.getEditPart("att"));
        editor.select(editor.getEditPart("att"));
        try {
            editor.clickContextMenu("myAction2.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        assertFalse("The menu should not exist", true);
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu3OnAttribute() throws Exception {

        editor.click(editor.getEditPart("att"));
        editor.select(editor.getEditPart("att"));
        try {
            editor.clickContextMenu("myAction3.1");
            assertFalse("The action myAction3.1 of the menu myMenu3 should not exist", true);
        } catch (WidgetNotFoundException e) {
        }
        try {
            editor.clickContextMenu("myAction3.2");
        } catch (WidgetNotFoundException e) {
            return;
        }
        assertFalse("The action myAction3.2 of the menu myMenu3 should not exist", true);
    }

    /**
     * Test if the menu or actions are accessible with a precondition Also test
     * if the variable $views is accessible only from Operation action and not
     * in external action or action call.
     * 
     * @throws Exception
     *             e
     */
    public void testMenuWithWrongVariable() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));

        // the popup menu has a precondition with $views
        try {
            editor.clickContextMenu("myMenuWithWrongVariable");
            assertFalse("The action myActionWithViews of the menu myMenu3 should not exist", true);
        } catch (WidgetNotFoundException e) {
        }

        // the ExternalJavaAction has a precondition with $views
        try {
            editor.clickContextMenu("myExternalJavaActionWithWrongVariableAction");
            assertFalse("The action myExternalJavaActionWithWrongVariableAction should not exist", true);
        } catch (WidgetNotFoundException e) {
        }

        // the ExternalJavaActionCall has a precondition with $views
        try {
            editor.clickContextMenu("myExternalJavaActionCallWithWrongVariableAction");
            assertFalse("The action myExternalJavaActionCallWithWrongVariableAction should not exist", true);
        } catch (WidgetNotFoundException e) {
        }

    }

    /**
     * Test if the menu or actions are accessible with a precondition in the
     * operation action Also test if the variable $views is accessible only from
     * Operation action.
     * 
     * @throws Exception
     *             e
     */
    public void testMenu4() throws Exception {
        SWTBotGefEditPart editPart = editor.getEditPart("Class");
        SWTBotGefEditPart editPart2 = editor.getEditPart("att");

        // the action is visible if there are more than one edit part selected
        testMenuWith2Selections(editPart, editPart2);
        testMenuWith1Selection(editPart);
        testMenuWith2Selections(editPart, editPart2);
        testMenuWith1Selection(editPart);
    }

    /**
     * With one edit part selected the action is not visible.
     * 
     * @param editPart
     */
    private void testMenuWith1Selection(SWTBotGefEditPart editPart) {
        editor.select(editPart);
        try {
            editor.clickContextMenu("actionMenu4");
            assertFalse("The action actionMenu4 of the menu4 should not exist", true);
        } catch (WidgetNotFoundException e) {
        }
    }

    /**
     * With two edit parts selected the action is visible.
     * 
     * @param editPart
     * @param editPart2
     */
    private void testMenuWith2Selections(SWTBotGefEditPart editPart, SWTBotGefEditPart editPart2) {
        editor.select(editPart, editPart2);

        try {
            editor.clickContextMenu("actionMenu4");
        } catch (WidgetNotFoundException e) {
            assertTrue("The action actionMenu4 of the menu myMenu4 should exist", true);
        }
    }

    /**
     * Test undo/redo of the PopupMenu action.
     * 
     */
    public void testUndoRedoMenuAction() {
        SWTBotGefEditPart editPart = editor.getEditPart("Class");
        SWTBotGefEditPart editPart2 = editor.getEditPart("att");
        editor.select(editPart, editPart2);
        try {
            editor.clickContextMenu("actionMenu4");
        } catch (WidgetNotFoundException e) {
            assertTrue("The action actionMenu4 of the menu myMenu4 should exist", true);
        }
        try {
            undo("actionMenu4");
        } catch (WidgetNotFoundException e) {
            assertTrue("The action actionMenu4 of the menu myMenu4 should be canceled", true);
        }
        try {
            redo("actionMenu4");
        } catch (WidgetNotFoundException e) {
            assertTrue("The action actionMenu4 of the menu myMenu4 should be restored", true);
        }
    }
}
