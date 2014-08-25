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
package org.eclipse.sirius.tests.swtbot.clipboard;

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionCondition;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import org.eclipse.sirius.tests.swtbot.Activator;

/**
 * Test Copy, cut and paste on diagram representation. Test VP-1894. Test
 * multi-session clipboard support.
 * 
 * @author jdupont
 */
public class MultiSessionCopyPasteTest extends AbstractClipboardSupportTest {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, MODEL_BIS, SESSION_FILE, SESSION_FILE_BIS, VSM_FILE);
    }

    /**
     * 
     * {@inheritDoc}
     */
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
        diagram2 = localSessionBis.getLocalSessionBrowser().perCategory().selectViewpoint(GENERIC_VIEWPOINT_NAME).selectRepresentation(GENERIC_DESCRIPTION)
                .selectRepresentationInstance(REPRESENTATION_WITH_GENERIC_PASTE_BIS, UIDiagramRepresentation.class).open();
        editor2 = diagram2.getEditor();

        diagram1 = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(GENERIC_VIEWPOINT_NAME).selectRepresentation(GENERIC_DESCRIPTION)
                .selectRepresentationInstance(REPRESENTATION_WITH_GENERIC_PASTE, UIDiagramRepresentation.class).open();
        editor = diagram1.getEditor();

        checkCopyPaste(editor, editor.getEditPart("Class2"), editor2, false, editor2.getEditPart("ClassBis2"), "Class2", 1);
    }

    /**
     * Test copy and paste from menu edit for node from editor to other editor
     * from other session.
     * 
     * Test VP-2100 : refresh opened editors was not domain safe.
     */
    public void testGenericCopyEClassPasteInEPackageOtherSession() {
        diagram2 = localSessionBis.getLocalSessionBrowser().perCategory().selectViewpoint(GENERIC_VIEWPOINT_NAME).selectRepresentation(GENERIC_DESCRIPTION)
                .selectRepresentationInstance(REPRESENTATION_WITH_GENERIC_PASTE_BIS, UIDiagramRepresentation.class).open();
        editor2 = diagram2.getEditor();
        diagram1 = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(GENERIC_VIEWPOINT_NAME).selectRepresentation(GENERIC_DESCRIPTION)
                .selectRepresentationInstance(REPRESENTATION_WITH_GENERIC_PASTE, UIDiagramRepresentation.class).open();
        editor = diagram1.getEditor();

        checkCopyPaste(editor, editor.getEditPart("Class2"), editor2, true, (SWTBotGefEditPart) null, "Class2", 1);
    }

}
