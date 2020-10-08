/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.refresh.RefreshLayoutScope;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckToolIsActivated;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test non sequence changes do not trigger sequence layout.
 * 
 * See Bug 455443.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class RefreshLayoutScopeTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "/data/unit/sequence/455443/";

    private static final String ODESIGN_EXT_RESOURCE_NAME = "455443.odesign";

    private static final String SEMANTIC_INTERACTIONS_RESOURCE_NAME = "455443.interactions";

    private static final String AIRD_RESOURCE_NAME = "455443.aird";

    private RefreshLayoutScopeAsserter refreshLayoutScopeAsserter;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        copyFileToTestProject(Activator.PLUGIN_ID, PATH, ODESIGN_EXT_RESOURCE_NAME);
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_INTERACTIONS_RESOURCE_NAME);
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, AIRD_RESOURCE_NAME);

        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);
        changeDiagramPreference(SiriusDiagramPreferencesKeys.PREF_DISPLAY_HEADER_SECTION.name(), false);

        sessionAirdResource = new UIResource(designerProject, "/", AIRD_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Sequence Diagram on Interaction", "Sequence Diagram on 455443", DDiagram.class);

        maximizeEditor(editor);

        refreshLayoutScopeAsserter = new RefreshLayoutScopeAsserter(localSession.getOpenedSession().getTransactionalEditingDomain(), editor.getDiagramEditPart().getDiagramView());

    }

    /**
     * Test that creation of a GMF NoteAttachement on sequence diagram does not trigger sequence refresh layout.
     */
    public void testGMFNoteAttachementOnSequenceDiagramDoesNotTriggerRefreshLayout() {
        Point creationLocation = new Point(700, 300);
        createNode("Note", creationLocation);
        SWTBotGefEditPart instanceRoleEditPartBot = editor.getEditPart("ddddd : C1", InstanceRoleEditPart.class);
        Rectangle instanceRoleEditPartBounds = editor.getBounds(instanceRoleEditPartBot);
        Point firstClickLocation = creationLocation.getTranslated(5, 5);
        Point secondClickLocation = instanceRoleEditPartBounds.getCenter();
        String toolName = "Note Attachment";
        ICondition done = new CheckToolIsActivated(editor, toolName);
        editor.activateTool(toolName);
        bot.waitUntil(done);
        editor.click(firstClickLocation);
        editor.click(secondClickLocation);
        String assertMessagePrefix = "Sequence layout should not be launched on " + toolName + " ";
        assertFalse(assertMessagePrefix + "creation", refreshLayoutScopeAsserter.refreshLayoutScopeApply());
    }

    /**
     * Test that creation and change of a GMF Note on sequence diagram does not trigger sequence refresh layout.
     */
    public void testGMFNoteOnSequenceDiagramDoesNotTriggerRefreshLayout() {
        testNodeOnSequenceDiagramDoesNotTriggerRefreshLayout("Note", NoteEditPart.class);
    }

    /**
     * Test that creation and change of a GMF Text on sequence diagram does not trigger sequence refresh layout.
     */
    public void testGMFTextCreationOnSequenceDiagramDoesNotTriggerRefreshLayout() {
        testNodeOnSequenceDiagramDoesNotTriggerRefreshLayout("Text", TextEditPart.class);
    }

    /**
     * Test that creation and change of a Viewpoint Node on sequence diagram does not trigger sequence refresh layout.
     */
    public void testViewpointNodeCreationOnSequenceDiagramDoesNotTriggerRefreshLayout() {
        testNodeOnSequenceDiagramDoesNotTriggerRefreshLayout("EClass", DNodeEditPart.class);
    }

    /**
     * Test that modification of a Sequence Diagram which triggers the sequence refresh layout of this sequence diagram
     * does not trigger the layout on other sequence diagrams.
     */
    public void testModificationOnSequenceDiagramDoesNotTriggerRefreshLayoutOnOtherSequenceDiagram() {

        SWTBotSiriusDiagramEditor editorOnOtherInyteractionToModify = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Sequence Diagram on Interaction",
                "Sequence Diagram on otherInteraction", DDiagram.class);

        maximizeEditor(editorOnOtherInyteractionToModify);
        RefreshLayoutScopeAsserter diagramOnOtherInteractionRefreshLayoutScopeAsserter = new RefreshLayoutScopeAsserter(localSession.getOpenedSession().getTransactionalEditingDomain(),
                editorOnOtherInyteractionToModify.getDiagramEditPart().getDiagramView());

        localSession.selectView();
        SWTBotTreeItem interactionNode = localSession.getRootSessionTreeItem().select().getNode(1).select().expand().getNode("Interaction 455443");
        interactionNode.select();
        UIDiagramRepresentation uiDiagramRepresentation = localSession.newDiagramRepresentation("Sequence Diagram on 455443", "Sequence Diagram on Interaction").on(interactionNode)
                .withName("Sequence Diagram to modify").ok();

        SWTBotSiriusDiagramEditor editorToModify = uiDiagramRepresentation.getEditor();
        maximizeEditor(editorToModify);
        RefreshLayoutScopeAsserter modifiedDiagramRefreshLayoutScopeAsserter = new RefreshLayoutScopeAsserter(localSession.getOpenedSession().getTransactionalEditingDomain(),
                editorToModify.getDiagramEditPart().getDiagramView());

        ICondition done = new CheckToolIsActivated(editorToModify, InteractionsConstants.EXECUTION_TOOL_ID);
        editorToModify.activateTool(InteractionsConstants.EXECUTION_TOOL_ID);
        bot.waitUntil(done);
        done = new OperationDoneCondition();
        editorToModify.click(770, 230);
        bot.waitUntil(done);

        assertTrue("Layout refresh must be triggered on modified Sequence diagram.", modifiedDiagramRefreshLayoutScopeAsserter.refreshLayoutScopeApply());
        assertTrue("Layout refresh must be triggered on Sequence diagram on the same interaction.", refreshLayoutScopeAsserter.refreshLayoutScopeApply());
        assertFalse("Layout refresh must not be triggered on Sequence diagram which has another interaction as semantic model.",
                diagramOnOtherInteractionRefreshLayoutScopeAsserter.refreshLayoutScopeApply());
    }

    private void testNodeOnSequenceDiagramDoesNotTriggerRefreshLayout(String toolToCreateNode, Class<? extends EditPart> typeOfEditPartToCreate) {
        Point creationLocation = new Point(700, 300);
        createNode(toolToCreateNode, creationLocation);
        String assertMessagePrefix = "Sequence layout should not be launched on " + toolToCreateNode + " ";
        assertFalse(assertMessagePrefix + "creation", refreshLayoutScopeAsserter.refreshLayoutScopeApply());

        SWTBotGefEditPart noteEditPartBot = editor.getEditPart(creationLocation.getTranslated(5, 5), typeOfEditPartToCreate);
        Rectangle noteEditPartBotBounds = editor.getBounds(noteEditPartBot);
        editor.drag(noteEditPartBotBounds.getTopLeft(), noteEditPartBotBounds.getTopLeft().getTranslated(-10, -10));
        assertFalse(assertMessagePrefix + "resize", refreshLayoutScopeAsserter.refreshLayoutScopeApply());

        noteEditPartBot.select();
        deleteSelectedElement();
        assertFalse(assertMessagePrefix + "remove", refreshLayoutScopeAsserter.refreshLayoutScopeApply());
    }

    private void createNode(String toolName, Point creationLocation) {
        ICondition done = new CheckToolIsActivated(editor, toolName);
        editor.activateTool(toolName);
        bot.waitUntil(done);
        editor.click(creationLocation);
    }

    @Override
    protected void tearDown() throws Exception {
        refreshLayoutScopeAsserter.dispose();
        refreshLayoutScopeAsserter = null;
        super.tearDown();
    }

    private static class RefreshLayoutScopeAsserter extends ResourceSetListenerImpl {

        private RefreshLayoutScope refreshLayoutScope;

        private boolean refreshLayoutScopeApply;

        public RefreshLayoutScopeAsserter(TransactionalEditingDomain domain, Diagram diagramView) {
            domain.addResourceSetListener(this);
            refreshLayoutScope = new RefreshLayoutScope(diagramView);
        }

        @Override
        public void resourceSetChanged(ResourceSetChangeEvent event) {
            super.resourceSetChanged(event);
            for (Notification notification : event.getNotifications()) {
                if (refreshLayoutScope.apply(notification)) {
                    refreshLayoutScopeApply = true;
                    break;
                }
            }
        }

        public boolean refreshLayoutScopeApply() {
            return refreshLayoutScopeApply;
        }

        public void dispose() {
            getTarget().removeResourceSetListener(this);
        }
    }

}
