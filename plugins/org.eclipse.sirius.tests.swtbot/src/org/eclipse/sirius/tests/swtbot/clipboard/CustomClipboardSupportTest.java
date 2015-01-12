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
package org.eclipse.sirius.tests.swtbot.clipboard;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test Copy, cut and paste behavior with Paste tools on diagram representation.
 * Test VP-1894. Test that menu copy is always available with selection of note
 * or DSemanticDecorator, cut can be disabled if delete command is not
 * available, paste will be disabled if there is a no paste tool on the
 * receiver. Test that menu cut, not delete elements if a deletion tool with no
 * delete action is defined.
 * 
 * @author jdupont
 */
public class CustomClipboardSupportTest extends AbstractClipboardSupportTest {

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        assertNotNull(localSession);
        bot.waitUntil(new SessionCondition(localSession, GENERIC_VIEWPOINT_NAME, GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE));
    }

    /**
     * Test copy and paste for menu on Edit Part without Clipboard.
     */
    public void testCopyPasteFromEditMenuWithNoPasteTool() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE, DDiagram.class);
        checkCopyPaste(editor2, editor2.getSelectableEditPart("Class2"), editor2, false, (SWTBotGefEditPart) null, "Class2", 1);
    }

    /**
     * Test copy and paste for menu on Edit Part without Clipboard.
     */
    public void testCopyPasteFromEditMenuWithAlwaysPasteClassTool() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE, DDiagram.class);
        checkCopyPaste(editor2, editor2.getSelectableEditPart("pastable_Class"), editor2, true, (SWTBotGefEditPart) null, "pasted_pastable_Class", 1);
    }

    /**
     * Test copy and paste for menu on Edit Part without Clipboard.
     */
    public void testCopyPasteInClassFromEditMenuWithAlwaysPasteClassTool() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE, DDiagram.class);
        checkCopyPaste(editor2, editor2.getSelectableEditPart("pastable_Class"), editor2, true, editor2.getSelectableEditPart("Class2"), "pasted_pastable_Class", 1);
    }

    /**
     * Test copy and paste for menu on Edit Part without Clipboard.
     */
    public void testCopyPasteInReferenceFromEditMenuWithAlwaysPasteClassTool() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE, DDiagram.class);
        checkCopyPaste(editor2, editor2.getSelectableEditPart("pastable_Class"), editor2, true, editor2.getEditPart("ref1"), "pasted_pastable_Class", 1);
    }

    /**
     * Test copy and paste for menu on Edit Part without Clipboard.
     */
    public void testCopyPasteInAttributeFromEditMenuWithAlwaysPasteClassTool() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE, DDiagram.class);
        checkCopyPaste(editor2, editor2.getSelectableEditPart("pastable_Class"), editor2, true, editor2.getSelectableEditPart("attributee2"), "pasted_pastable_Class", 1);
    }

    /**
     * Test copy and paste for menu on Edit Part without Clipboard.
     */
    public void testCopyPasteInSuperTypeEdgeFromEditMenuWithAlwaysPasteClassTool() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE, DDiagram.class);

        checkCopyPaste(editor2, editor2.getSelectableEditPart("pastable_Class"), editor2, true, editor2.getEditPart("super type of Class1"), "pasted_pastable_Class", 1);
    }

    // TODO with multi tools (one enabled, many enabled)

}
