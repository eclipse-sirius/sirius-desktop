/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Felix Dorner <felix.dorner@gmail.com> -  initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.ui.PartInitException;

/**
 * Test the representation link tool in a fragmented model. More representation link tests can be found in
 * {@link NoteCreationTest}.
 */
public class LinkNoteFragmentModelCreationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String LINK_NOTE_TOOL = "Representation Link";

    private static final String REPRESENTATION_INSTANCE_NAME = "root package entities";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String REPRESENTATION_FRAGMENT_NAME = "p1 package entities";

    private static final String[] FILES = new String[] { "representations.aird", "My_p1.aird", "My.ecore", "My_p1.ecore" };

    private static final String DATA_UNIT_DIR = "data/unit/tools/creation/linkNote/";

    private static final String FILE_DIR = "/";

    private static final String MY_NOTE = "My Note";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, FILES);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, FILES[0]);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * This test creates a representation link from the main diagram to the fragment diagram, and verifies that the
     * diagram in the fragment can be opened by doubleclick on the representation link.
     * 
     * @throws Exception
     *             any test exception
     */
    public void testNavigateToDiagramInFragment() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        executeTool(5, 5, getDiagramInFragment());
        validateLinkDoubleclick(getDiagramInFragment());
    }

    /**
     * This test creates a representation link from the fragment diagram to the main diagram and verifies that the main
     * diagram can be opened by doubleclick on the representation link.
     * 
     * @throws Exception
     *             any test exception
     */
    public void testCreateLinkNoteInDiagramInFragment() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_FRAGMENT_NAME, DDiagram.class);
        executeTool(5, 5, getDiagramInMain());
        validateLinkDoubleclick(getDiagramInMain());
    }

    private void executeTool(int x, int y, DRepresentationDescriptor link) {
        editor.activateTool(LINK_NOTE_TOOL);
        editor.click(x, y);
        selectTargetRepresentation(link);
        SWTBotUtils.pressKeyboardKey(editor.getWidget(), SWT.F2);
        editor.directEditType(MY_NOTE);
    }

    // find the representation to link in the selection dialog
    private void selectTargetRepresentation(DRepresentationDescriptor link) {
        Viewpoint vp = (Viewpoint) link.getDescription().eContainer();
        int size = DialectManager.INSTANCE.getRepresentations(link.getDescription(), localSession.getOpenedSession()).size();
        SWTBot dialogBot = SWTBotSiriusHelper.getShellBot("Select representation");
        dialogBot.tree().expandNode(vp.getName(), link.getDescription().getName() + " (" + size + ")").getNode(link.getName()).select();
        dialogBot.button("OK").click();
    }

    // doubleclick should open the editor for the targeted representation
    private void validateLinkDoubleclick(DRepresentationDescriptor link) throws PartInitException {
        editor.doubleClick(editor.selectedEditParts().get(0));
        SessionEditorInput seip = (SessionEditorInput) bot.activeEditor().getReference().getEditorInput();
        assertEquals(EcoreUtil.getURI(link), seip.getRepDescUri());
        bot.activeEditor().close();
    }

    private DRepresentationDescriptor getDiagramInFragment() {
        return getRepresentationDescriptorWithName(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_FRAGMENT_NAME);
    }

    private DRepresentationDescriptor getDiagramInMain() {
        return getRepresentationDescriptorWithName(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);
    }

}
