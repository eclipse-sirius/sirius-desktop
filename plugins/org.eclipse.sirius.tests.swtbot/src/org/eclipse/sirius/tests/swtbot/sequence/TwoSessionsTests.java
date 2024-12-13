/*******************************************************************************
 * Copyright (c) 2024 CEA.
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
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIProject;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.ui.PlatformUI;

/**
 * Test class to reveal the MetaClassNotFoundException problem by using two sessions and a creation of a message.
 * 
 * @author lredor
 */
public class TwoSessionsTests extends AbstractDefaultModelSequenceTests {

    private String secondProjectName = "AnotherProject";

    private SWTBotGefEditPart instanceRoleABot;

    private SWTBotGefEditPart instanceRoleBBot;

    private Rectangle instanceRoleABounds;

    private Rectangle instanceRoleBBounds;

    @Override
    protected void tearDown() throws Exception {
        instanceRoleABot = null;
        instanceRoleBBot = null;

        instanceRoleABounds = null;
        instanceRoleBBounds = null;

        super.tearDown();
    }

    /**
     * Test method.
     */
    public void testToRevealMetaClassNotFoundException() {
        // Create a second project with same data
        UIProject designerProject2 = designerPerspective.createProject(secondProjectName);
        copyFileToProject(Activator.PLUGIN_ID, getPath(), secondProjectName, getTypesSemanticModel());
        copyFileToProject(Activator.PLUGIN_ID, getPath(), secondProjectName, getSemanticModel());
        copyFileToProject(Activator.PLUGIN_ID, getPath(), secondProjectName, getSessionModel());

        UIResource sessionAirdResource2 = new UIResource(designerProject2, FILE_DIR, getSessionModel());
        UILocalSession localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2, true);
        SWTBotUtils.waitAllUiEvents();

        // Open a second editor on the second session
        SWTBotSiriusDiagramEditor editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession2.getOpenedSession(), getRepresentationId(), "Sequence Diagram on LifelinesCopy", DDiagram.class,
                true, true);
        SWTBotUtils.waitAllUiEvents();

        // Close the first session and the first editor
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            localSession.getOpenedSession().close(new NullProgressMonitor());
        });
        SWTBotUtils.waitAllUiEvents();

        // Replace global variable for the tearDown process
        editor = editor2;
        localSession = localSession2;
        initEditor();
        editor.reveal(LIFELINE_A);

        // Arrange All
        arrangeAll();
        maximizeEditor(editor);

        SWTBotUtils.waitAllUiEvents();

        // InstanceRoles
        instanceRoleABot = editor.getEditPart(LIFELINE_A);
        instanceRoleBBot = editor.getEditPart(LIFELINE_B);

        instanceRoleABounds = editor.getBounds(instanceRoleABot);
        instanceRoleBBounds = editor.getBounds(instanceRoleBBot);

        // Creation of a message
        Point start = instanceRoleABounds.getCenter().translate(0, 200);
        Point end = instanceRoleBBounds.getCenter().translate(0, 200);
        createMessage(InteractionsConstants.READ_TOOL_ID, start, end);

        SWTBotGefEditPart lifelineABot = instanceRoleABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        assertEquals("The message has not been created.", 1, lifelineABot.sourceConnections().size());
    }
}
