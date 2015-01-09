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

import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test Copy, cut and paste on diagram representation. Test VP-1894. Test
 * multi-session clipboard support.
 * 
 * @author jdupont
 */
public class MultiSessionCopyPasteTest extends AbstractClipboardSupportTest {
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, MODEL_BIS, SESSION_FILE, SESSION_FILE_BIS, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        sessionAirdResourceBis = new UIResource(designerProject, FILE_DIR, SESSION_FILE_BIS);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        assertNotNull(localSession);
        bot.waitUntil(new SessionCondition(localSession, GENERIC_VIEWPOINT_NAME, GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE));
        localSessionBis = designerPerspective.openSessionFromFile(sessionAirdResourceBis);
        assertNotNull(localSessionBis);
        bot.waitUntil(new SessionCondition(localSessionBis, GENERIC_VIEWPOINT_NAME, GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE_BIS));
    }

    /**
     * Test copy and paste from menu edit for node from editor to other editor
     * from other session.
     * 
     * Test VP-2100 : refresh opened editors was not domain safe.
     */
    public void testGenericCopyEClassPasteInEClassOtherSession() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSessionBis.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE_BIS, DDiagram.class);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);

        checkCopyPaste(editor, editor.getEditPart("Class2", AbstractBorderedShapeEditPart.class), editor2, false, editor2.getEditPart("ClassBis2", AbstractBorderedShapeEditPart.class), "Class2", 1);
    }

    /**
     * Test copy and paste from menu edit for node from editor to other editor
     * from other session.
     * 
     * Test VP-2100 : refresh opened editors was not domain safe.
     */
    public void testGenericCopyEClassPasteInEPackageOtherSession() {
        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSessionBis.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE_BIS, DDiagram.class);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), GENERIC_DESCRIPTION, REPRESENTATION_WITH_GENERIC_PASTE, DDiagram.class);

        checkCopyPaste(editor, editor.getEditPart("Class2", AbstractBorderedShapeEditPart.class), editor2, true, (SWTBotGefEditPart) null, "Class2", 1);
    }

}
