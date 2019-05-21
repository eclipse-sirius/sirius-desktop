/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.std;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNoOpenedSessionInModelContentView;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

/**
 * Test class for STD 007.
 * 
 * @author nlepine
 */
public class STD007 extends AbstractSiriusSwtBotGefTestCase {

    private static final String ANNOTATION_NAME = "Test-007";

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    private static final String MODEL = "STD-TEST-007.ecore";

    private static final String SESSION_FILE = "STD-TEST-007.aird";

    private static final String DATA_UNIT_DIR = "data/unit/std/007/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM = "RootSTDTestCase package entities";

    private static final String REPRESENTATION_NAME_DIAGRAM = "Entities";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        closeOutline();
    }

    @Override
    protected void tearDown() throws Exception {
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        super.tearDown();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSTD007() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
                org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException: Could not find node with text: STD-TEST-007.ecore
                at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNodes(SWTBotTreeItem.java:334)
                at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNode(SWTBotTreeItem.java:308)
                at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNode(SWTBotTreeItem.java:346)
                at org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.getUIItemFromResource(UIProject.java:152)
                at org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.selectResource(UIProject.java:122)
                at org.eclipse.sirius.tests.swtbot.support.api.business.UIPerspective.openSessionCreationWizardFromSemanticResource(UIPerspective.java:188)
                at org.eclipse.sirius.tests.swtbot.std.STD007.testSTD007(STD007.java:76)
             */
            return;
        }
        final UIResource ecoreEcoreResource = new UIResource(designerProject, FILE_DIR, MODEL);

        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);

        UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);

        // Open the editor on the representation (that is automatically created)
        final SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM,
                DDiagram.class);

        // Adding various items of the palette on the diagram
        // Add a class
        editor.activateTool("Class");
        editor.click(50, 100);

        // Adding various items of the palette on the diagram
        // Add a Note
        editor.activateTool("Note");
        editor.click(150, 100);

        final Request request = new DirectEditRequest();

        /*
         * Workaround for GMF based modelers -> need to be in a SWTBotGMFEditor ->
         * org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants.
         * REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR
         */
        request.getExtendedData().put("directedit_extendeddata_initial_char", 'a');

        final EditPart part = getNoteEditPart(editor);
        ((DirectEditRequest) request).setLocation(((GraphicalEditPart) part).getFigure().getBounds().getLocation());
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                part.performRequest(request);
            }
        });

        editor.directEditType(ANNOTATION_NAME);

        // Move the note
        editor.drag(ANNOTATION_NAME, 150, 150);
        SWTBotUtils.waitAllUiEvents();

        // Close the session and save the diagram
        editor.save();
        SWTBotUtils.waitAllUiEvents();
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        // Close the session and save the diagram
        localSession.close(true);

        bot.waitUntil(new CheckNoOpenedSessionInModelContentView(bot, SESSION_FILE));

        // Re open session
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        // Open the editor on the representation
        SWTBotSiriusDiagramEditor editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM,
                DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        assertNotNull(editor2.getEditPart(ANNOTATION_NAME));

        editor2.close();
        SWTBotUtils.waitAllUiEvents();
        localSession.closeNoDirty();
        SWTBotUtils.waitAllUiEvents();
    }

    private EditPart getNoteEditPart(final SWTBotSiriusDiagramEditor editor) {
        final AbstractEditPart part = (AbstractEditPart) editor.rootEditPart().children().get(0).part();
        for (final Object child : part.getChildren()) {
            if (child instanceof NoteEditPart)
                return (EditPart) child;
        }
        return null;
    }

}
