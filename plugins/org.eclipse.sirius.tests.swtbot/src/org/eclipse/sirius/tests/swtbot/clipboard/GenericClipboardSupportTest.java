/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.clipboard;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test Copy, cut and paste generic behavior on diagram representation. Test
 * VP-1894. Test that menu copy, cut paste is accessible. Test that menu cut,
 * not delete elements if a deletion tool with no delete action is defined.
 * 
 * @author jdupont
 */
public class GenericClipboardSupportTest extends AbstractClipboardSupportTest {
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
     * Contextual menu is not well updated, and the only action to test is to
     * click. This test is here to test contextual menu sync with global "Edit"
     * menu.
     * 
     * Other tests can only check edit menu.
     */
    public void testCopyPasteMenusSync() {
        clearSiriusClipboard();

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);

        Session session = localSession.getOpenedSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());

        // Select diagram
        editor.getEditPart("Class2").parent().parent().select();
        sideEffectAssertCopyCutPasteActivation(editor, true, false, false);

        assertEquals(SessionStatus.SYNC, session.getStatus());

        // Select Class 2
        editor.getEditPart("Class2").parent().select();
        sideEffectAssertCopyCutPasteActivation(editor, true, true, false);

        assertEquals(SessionStatus.DIRTY, session.getStatus());
    }

    /**
     * Check viewpoint contents.
     */
    public void testCopyEPackageSiriusClipboardContent() {
        clearSiriusClipboard();

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);

        Session session = localSession.getOpenedSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());

        checkSiriusClipboardContent();

        // Select diagram
        SWTBotGefEditPart class2PartBot = editor.getEditPart("Class2").parent();
        SWTBotGefEditPart diagramPartBot = class2PartBot.parent();
        diagramPartBot.select();
        assertCopyCutPasteToolBarActivation(true, false, false, editor);
        copySelection(editor);

        // Re-select diagram to force menu status refresh
        diagramPartBot.select();

        // Copy should not modify model.
        assertEquals(SessionStatus.SYNC, session.getStatus());

        // Check ClipboardContent
        checkSiriusClipboardContent(diagramPartBot);

        // Check menus : paste became enabled :
        // an EPackage can be pasted in an other EPackage.
        assertCopyCutPasteToolBarActivation(true, false, true, editor);

        // Select Class 2
        class2PartBot.select();
        // Check menus : copy and cut are still enabled, paste became disabled :
        // an
        // EPackage can be pasted in an EClass
        assertCopyCutPasteToolBarActivation(true, true, false, editor);

        assertEquals(SessionStatus.SYNC, session.getStatus());

        // Select diagram
        diagramPartBot.select();
        // Check menus : copy is still enabled, cut became disabled, paste
        // became enabled :
        // an EPackage can be pasted in an other EPackage.
        assertCopyCutPasteToolBarActivation(true, false, true, editor);

        pasteInSelection(editor);

        assertEquals(SessionStatus.DIRTY, session.getStatus());

    }

    /**
     * Check viewpoint contents.
     */
    public void testCopyEClassSiriusClipboardContent() {
        clearSiriusClipboard();

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);

        Session session = localSession.getOpenedSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());

        checkSiriusClipboardContent();

        // Select Class 2
        SWTBotGefEditPart class2PartBot = editor.getEditPart("Class2").parent();
        class2PartBot.select();
        assertCopyCutPasteToolBarActivation(true, true, false, editor);
        copySelection(editor);

        // Re-select Class 2 to force menu status refresh
        class2PartBot.select();

        // Copy should not modify model.
        assertEquals(SessionStatus.SYNC, session.getStatus());

        // Check ClipboardContent
        checkSiriusClipboardContent(class2PartBot);

        // Check menus : copy and cut are still enabled, paste is still disabled
        // : an EClass can not be pasted in an other EClass
        assertCopyCutPasteToolBarActivation(true, true, false, editor);

        // Select diagram
        SWTBotGefEditPart diagramPartBot = class2PartBot.parent();
        diagramPartBot.select();

        // Check menus : copy and cut are still enabled, paste became enabled :
        // an EClass can be pasted in a package
        assertCopyCutPasteToolBarActivation(true, false, true, editor);

        assertEquals(SessionStatus.SYNC, session.getStatus());

        pasteInSelection(editor);

        assertEquals(SessionStatus.DIRTY, session.getStatus());
    }

    /**
     * Test copy and paste from menu Edit on diagram targeting an EPackage.
     */
    public void testGenericCopyEClassPasteInPackage() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);
        checkCopyPaste(editor, editor.getEditPart("Class2", AbstractBorderedShapeEditPart.class), editor, true, (SWTBotGefEditPart) null, "Class2", 2);
    }

    /**
     * Test copy and paste from menu Edit on node targeting an EClass.
     */
    public void testGenericCopyEClassPasteInEClass() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);
        checkCopyPaste(editor, editor.getEditPart("Class2", AbstractBorderedShapeEditPart.class), editor, false, editor.getEditPart("Class2", AbstractBorderedShapeEditPart.class), "Class2", 1);
    }

    /**
     * Test copy and paste from menu edit for node from editor to other editor
     * diagram targeting an EPackage.
     */
    public void testGenericCopyEClassPasteInPackageOtherEditor() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE, DDiagram.class);
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);
        checkCopyPaste(editor, editor.getEditPart("Class2", AbstractBorderedShapeEditPart.class), editor2, true, (SWTBotGefEditPart) null, "Class2", 2);
    }

    /**
     * Test copy and paste from menu edit for node from editor to other editor
     * node targeting an EClass.
     */
    public void testGenericCopyEClassPasteInEClassOtherEditor() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE, DDiagram.class);
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);
        checkCopyPaste(editor, editor.getEditPart("Class2", AbstractBorderedShapeEditPart.class), editor2, false, editor2.getEditPart("Class2", AbstractBorderedShapeEditPart.class), "Class2", 1);
    }

    /**
     * Test that cut on menu delete element selected.
     */
    public void testGenericCutOnMenuDeleteElement() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);

        checkCutPaste(editor, "Class2", true);
    }

    /**
     * Test that cut on menu with a delete tools who not delete elements, don't
     * delete selected element.
     */
    public void testGenericCutOnMenuWithNoDeleteTools() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE, DDiagram.class);

        checkCutPaste(editor, "nodelete_Class", false);
    }

    /**
     * Test copy node and no paste super type edge.
     */
    public void testGeneriCopyNoPasteInSuperTypeEdge() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);
        SWTBotGefEditPart edge = editor2.getEditPart("Class1").parent().sourceConnections().get(1);
        checkCopyPaste(editor2, editor2.getEditPart("pastable_Class", AbstractBorderedShapeEditPart.class), editor2, false, edge, "pasted_pastable_Class", 1);
    }

    /**
     * Test copy super type edge and paste node .
     */
    public void testGenericCopySuperTypeEdgePasteNode() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);
        SWTBotGefEditPart edge = editor2.getEditPart("Class1").parent().sourceConnections().get(1);
        checkCopyPaste(editor2, edge, editor2, false, editor2.getEditPart("pastable_Class", AbstractBorderedShapeEditPart.class), "pasted_pastable_Class", 1);
    }

    /**
     * Test copy node and no paste reference.
     */
    public void testCopyNoPasteInReference() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);
        checkCopyPaste(editor2, editor2.getEditPart("pastable_Class", AbstractBorderedShapeEditPart.class), editor2, false, editor2.getEditPart("[0..1] ref1"), "pasted_pastable_Class", 1);
    }

    /**
     * Test copy reference edge and paste node.
     */
    public void testGenericCopyReferenceEdgePasteNode() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);
        checkCopyPaste(editor2, editor2.getEditPart("[0..1] ref1"), editor2, true, editor2.getEditPart("pastable_Class", AbstractBorderedShapeEditPart.class), "[0..1] ref1", 2);
    }

    /**
     * Test copy more elements and paste this elements on one node.
     */
    public void testGenericCopyMoreElementsPasteNode() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);
        List<SWTBotGefEditPart> listElementCopy = new ArrayList<SWTBotGefEditPart>();
        listElementCopy.add(editor2.getEditPart("attributee1"));
        listElementCopy.add(editor2.getEditPart("attributee2"));
        Map<String, Integer> pasteElements = new Hashtable<String, Integer>();
        pasteElements.put("attributee1", 2);
        pasteElements.put("attributee2", 5);
        checkCopyPaste(editor2, listElementCopy, editor2, true, editor2.getEditPart("pastable_Class", AbstractBorderedShapeEditPart.class), pasteElements);
    }

    /**
     * Test copy one element and paste this elements on more elements.
     */
    public void testGenericCopyElementsPasteOnMoreElements() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);
        List<SWTBotGefEditPart> listTargetPaste = new ArrayList<SWTBotGefEditPart>();
        listTargetPaste.add(editor2.getEditPart("pastable_Class", AbstractBorderedShapeEditPart.class));
        listTargetPaste.add(editor2.getEditPart("Class2", AbstractBorderedShapeEditPart.class));
        listTargetPaste.add(editor2.getEditPart("nodelete_Class", AbstractBorderedShapeEditPart.class));
        checkCopyPaste(editor2, editor2.getEditPart("attributee1"), editor2, true, listTargetPaste, "attributee1", 4);
    }
}
