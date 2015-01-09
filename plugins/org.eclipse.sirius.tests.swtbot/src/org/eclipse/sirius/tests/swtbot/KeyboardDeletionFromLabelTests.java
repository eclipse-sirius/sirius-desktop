/*******************************************************************************
 * Copyright (c) 2010-2015 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartIsNotDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import com.google.common.base.Function;

/**
 * This class aims to tests deletion from model via key board when a label is
 * selected.
 * 
 * @author mporhel
 */
public class KeyboardDeletionFromLabelTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/labelDeletion/";

    private static final String FILE_DIR = "/";

    private static final String SESSION_FILE = "vp-3009.aird";

    private static final String VSM_FILE = "vp-3009.odesign";

    private static final String MODEL = "vp-3009.ecore";

    private static final String REPRESENTATION_NAME = "vp-3009";

    private static final String REPRESENTATION_INSTANCE_NAME = "vp-3009";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDeleteFromLabelWithStandardTool() throws Exception {
        // Those elements will trigger the standard delete tool behavior : it
        // will delete/remove the semantic element.
        testDeleteFromLabel(true, "node_c", "border_a", "list_element_a", "begin_c", "center_c", "end_c", "container_node_ref", "container_border_a", "list_c");

        assertEquals("Deletion tests should be added for the label of a container edit part (" + "container_c" + ").", false, editor.getEditPart("container_c").part().isSelectable());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDeleteSemanticElementWithNoDeleteTool() throws Exception {
        // Those elements will trigger the no delete tool behavior : it will do
        // nothing.
        testDeleteFromLabel(false, "node_c_1", "border_a_1", "list_element_a_1", "begin_c_1", "center_c_1", "end_c_1", "container_node_ref_1", "container_border_a_1", "list_c_1");

        assertEquals("Deletion tests should be added for the label of a container edit part (" + "container_c_1" + ").", false, editor.getEditPart("container_c_1").part().isSelectable());

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDeleteSemanticElementWithDeactivatedDeleteTool() throws Exception {
        // Those elements will trigger the deactivated delete tool : it will do
        // nothing.
        testDeleteFromLabel(false, "node_c_2", "border_a_2", "list_element_a_2", "begin_c_2", "center_c_2", "end_c_2", "container_node_ref_2", "container_border_a_2", "list_c_2");

        assertEquals("Deletion tests should be added for the label of a container edit part (" + "container_c_2" + ").", false, editor.getEditPart("container_c_2").part().isSelectable());
    }

    private void testDeleteFromLabel(boolean shouldDelete, String... labels) throws Exception {
        for (String label : labels) {
            // Test deletion with CTRL + D
            deleteCheckDeletionAndRevert(label, deleteFromKeyboard_CtrlD, shouldDelete);

            // Test deletion with Suppr
            deleteCheckDeletionAndRevert(label, deleteFromKeyboard_Suppr, shouldDelete);
        }
    }

    private void deleteCheckDeletionAndRevert(String label, SelectAndDeleteFunction deleteFunction, boolean shouldDelete) {
        // check session sync status
        assertFalse("The session should not be dirty before deletion.", localSession.isDirty());
        editor.setFocus();
        SWTBotGefEditPart part = editor.getEditPart(label, IDiagramNameEditPart.class);

        if (!(part.part() instanceof DNodeListElementEditPart)) {
            part = part.parent();
        }

        EditPart editPart = part.part();
        ICondition done = new CheckSelectedCondition(editor, editPart);
        editor.select(part);
        bot.waitUntil(done);

        View notationView = (View) editPart.getModel();

        DDiagramElement dde = (DDiagramElement) notationView.getElement();
        EObject viewContainer = notationView.eContainer();
        EObject ddeContainer = dde.eContainer();

        // select and call delete shortcut
        ICondition cond = new CheckEditPartIsNotDisplayed(label, editor);
        SWTBotGefEditPart toDelete = deleteFunction.apply(part);

        if (shouldDelete) {
            bot.waitUntil(cond);
            // CheckDeletion Occurs
            assertTrue("The session should be dirty : delete should occurs on " + label + ".", localSession.isDirty());
            assertFalse(toDelete.part().isActive());
            assertEquals("The session should be dirty : delete should occurs on " + label + ".", null, dde.eContainer());
            assertEquals("The session should be dirty : delete should occurs on " + label + ".", null, notationView.eContainer());

            // Revert delete
            undo();
            localSession.save();
            assertFalse("The session should not be dirty after deletion of " + label + ", undo and save.", localSession.isDirty());
        } else {
            // check session sync status
            assertEquals("The no delete tool should have no effect on " + label + ".", viewContainer, notationView.eContainer());
            assertEquals("The no delete tool should have no effect on " + label + ".", ddeContainer, dde.eContainer());
        }

        assertNotNull("This test step should have no effect on the test data.", editor.getEditPart(label));
        assertFalse("This test step should have no effect on the test data." + label + ".", localSession.isDirty());
        editor.select(editor.mainEditPart());
    }

    /**
     * Delete from model with shortcut CTRL+D.
     */
    private SelectAndDeleteFunction deleteFromKeyboard_CtrlD = new SelectAndDeleteFunction() {
        @Override
        protected void pressDeleteShortCut() {
            String savedKeyboardLayout = SWTBotPreferences.KEYBOARD_LAYOUT;
            SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
            editor.getCanvas().pressShortcut(SWT.CTRL, 'd');
            SWTBotPreferences.KEYBOARD_LAYOUT = savedKeyboardLayout;
        }
    };

    /**
     * Delete from model with shortcut Suppr.
     */
    private SelectAndDeleteFunction deleteFromKeyboard_Suppr = new SelectAndDeleteFunction() {
        @Override
        protected void pressDeleteShortCut() {
            String savedKeyboardLayout = SWTBotPreferences.KEYBOARD_LAYOUT;
            SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
            editor.getCanvas().pressShortcut(Keystrokes.DELETE);
            SWTBotPreferences.KEYBOARD_LAYOUT = savedKeyboardLayout;
        }
    };

    private abstract class SelectAndDeleteFunction implements Function<SWTBotGefEditPart, SWTBotGefEditPart> {

        @Override
        public SWTBotGefEditPart apply(SWTBotGefEditPart toDelete) {
            assertTrue(editor != null);

            List<?> selection = ((IStructuredSelection) editor.getSelection()).toList();

            if (!(selection.size() == 1 && selection.contains(toDelete.part()))) {
                selectLabelPart(toDelete);
            }

            pressDeleteShortCut();

            return toDelete;
        }

        protected abstract void pressDeleteShortCut();
    }

    private SWTBotGefEditPart selectLabelPart(SWTBotGefEditPart toSelect) {
        assertTrue(editor != null);

        ICondition slection = new CheckSelectedCondition(editor, toSelect.part());
        toSelect.select();

        bot.waitUntil(slection);
        return toSelect;
    }
}
