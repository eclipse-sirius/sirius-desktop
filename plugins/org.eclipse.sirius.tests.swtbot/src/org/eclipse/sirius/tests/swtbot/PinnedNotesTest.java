/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.ui.IEditorPart;

/**
 * Tests for the "pin-unPin" on notes.
 * 
 * @author jdupont
 */
public class PinnedNotesTest extends AbstractPinnedElementsTest {

    /**
     * Sirius diagram name.
     */
    protected static final String VIEWPOINT_NAME = "pinnedNoteTest";

    private static final String MODEL = "model/pinUnpinNote.ecore";

    private static final String SESSION_FILE = "model/pinUnpinNote.aird";

    private static final String VSM_FILE = "description/pinUnpinNote.odesign";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, "pinUnpinNote.aird");
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Attach note to a pinned element, launch arrange all and check that the notes does not move.
     * 
     * @throws Exception
     *             if an error occurred.
     */
    public void testAttachNoteToPinnedElement() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTO_PIN_ON_MOVE.name(), true);
        openDiagram(VIEWPOINT_NAME, "PinUnpinNoteDiag", "PinUnpinNoteDiag");

        final DiagramEditPart gmfDiagramEditPart = getGmfEditor().getDiagramEditPart();
        final IDiagramElementEditPart p1 = (IDiagramElementEditPart) editor.getEditPart("p1", IDiagramContainerEditPart.class).part();
        final IDiagramElementEditPart p2 = (IDiagramElementEditPart) editor.getEditPart("p2", IDiagramContainerEditPart.class).part();
        final IDiagramEdgeEditPart edgeC1ToC2 = (IDiagramEdgeEditPart) editor.getEditPart("c1ToC2", IDiagramEdgeEditPart.class).part();
        final Rectangle boundsP1 = p1.getFigure().getBounds().getCopy();
        final Rectangle boundsP2 = p2.getFigure().getBounds().getCopy();
        EList<Node> notes = new BasicEList<Node>();
        Node noteLinkToNode = null;
        Node noteLinkToEdge = null;
        Node noteUnLink = null;

        notes = getNotes();

        for (Node note : notes) {
            if (note.getSourceEdges().size() > 0) {
                if (((Edge) note.getSourceEdges().get(0)).getTarget() instanceof Edge) {
                    noteLinkToEdge = note;
                } else {
                    noteLinkToNode = note;
                }
            } else {
                noteUnLink = note;
            }
        }

        final IGraphicalEditPart editPartNoteLinkToNode = (IGraphicalEditPart) gmfDiagramEditPart.getRoot().getViewer().getEditPartRegistry().get(noteLinkToNode);
        final IGraphicalEditPart editPartNoteLinkToEdge = (IGraphicalEditPart) gmfDiagramEditPart.getRoot().getViewer().getEditPartRegistry().get(noteLinkToEdge);
        final IGraphicalEditPart editPartNoteUnLink = (IGraphicalEditPart) gmfDiagramEditPart.getRoot().getViewer().getEditPartRegistry().get(noteUnLink);

        Rectangle boundsNoteLinkToNode = editPartNoteLinkToNode.getFigure().getBounds();
        Rectangle boundsNoteLinkToEdge = editPartNoteLinkToEdge.getFigure().getBounds();
        Rectangle boundsNoteUnLink = editPartNoteUnLink.getFigure().getBounds();

        assertThat(p1, not(isPinnedMatcher()));
        assertThat(p2, not(isPinnedMatcher()));
        assertThat(edgeC1ToC2, not(isPinnedMatcher()));

        editor.getEditPart("p1", IDiagramContainerEditPart.class).select();
        SWTBotUtils.waitAllUiEvents();
        editor.clickContextMenu(Messages.PinElementsEclipseAction_text);
        bot.waitUntil(waitForPinned(p1));
        editor.getEditPart("c1ToC2", IDiagramEdgeEditPart.class).select();
        SWTBotUtils.waitAllUiEvents();
        editor.clickContextMenu(Messages.PinElementsEclipseAction_text);
        bot.waitUntil(waitForPinned(p1));
        assertThat(p1, isPinnedMatcher());
        assertThat(p2, not(isPinnedMatcher()));
        assertThat(edgeC1ToC2, isPinnedMatcher());
        editor.click(200, 200);
        arrangeAll();
        final Rectangle boundsP1AfterLayout = p1.getFigure().getBounds().getCopy();
        final Rectangle boundsP2AfterLayout = p2.getFigure().getBounds().getCopy();
        final Rectangle boundsNoteLinkToNodeAfterLayout = editPartNoteLinkToNode.getFigure().getBounds().getCopy();
        final Rectangle boundsNoteLinkToEdgeAfterLayout = editPartNoteLinkToEdge.getFigure().getBounds().getCopy();
        final Rectangle boundsNoteUnLinkAfterLayout = editPartNoteUnLink.getFigure().getBounds().getCopy();

        assertTrue("The node P1 has been moved despite it was pinned.", boundsP1.equals(boundsP1AfterLayout));
        assertFalse("The node P2 should have been moved.", boundsP2.equals(boundsP2AfterLayout));
        // The edge bounds is not checked because the source has been moved, so edge too.
        assertTrue("The linked note should not have been moved.", boundsNoteLinkToNode.equals(boundsNoteLinkToNodeAfterLayout));
        assertTrue("The linked note should not have been moved.", boundsNoteLinkToEdge.equals(boundsNoteLinkToEdgeAfterLayout));
        assertTrue("The linked note should not have been moved.", boundsNoteUnLink.equals(boundsNoteUnLinkAfterLayout));

    }

    /**
     * Set the preference "Move unlinked notes during layout" to true, launch arrange all and check that the note
     * unpined has moved
     */
    public void testPreferenceEnableMoveToTrue() {
        changeDiagramPreference(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), true);

        openDiagram(VIEWPOINT_NAME, "PinUnpinNoteDiag", "PinUnpinNoteDiag");

        final DiagramEditPart gmfDiagramEditPart = getGmfEditor().getDiagramEditPart();
        final IDiagramElementEditPart p1 = (IDiagramElementEditPart) editor.getEditPart("p1", IDiagramContainerEditPart.class).part();
        final IDiagramElementEditPart p2 = (IDiagramElementEditPart) editor.getEditPart("p2", IDiagramContainerEditPart.class).part();
        final IDiagramEdgeEditPart edgeC1ToC2 = (IDiagramEdgeEditPart) editor.getEditPart("c1ToC2", IDiagramEdgeEditPart.class).part();
        final Rectangle boundsP1 = p1.getFigure().getBounds().getCopy();
        final Rectangle boundsP2 = p2.getFigure().getBounds().getCopy();
        EList<Node> notes = new BasicEList<Node>();
        Node noteLinkToNode = null;
        Node noteLinkToEdge = null;
        Node noteUnLink = null;

        notes = getNotes();

        for (Node note : notes) {
            if (note.getSourceEdges().size() > 0) {
                if (((Edge) note.getSourceEdges().get(0)).getTarget() instanceof Edge) {
                    noteLinkToEdge = note;
                } else {
                    noteLinkToNode = note;
                }
            } else {
                noteUnLink = note;
            }
        }

        final IGraphicalEditPart editPartNoteLinkToNode = (IGraphicalEditPart) gmfDiagramEditPart.getRoot().getViewer().getEditPartRegistry().get(noteLinkToNode);
        final IGraphicalEditPart editPartNoteLinkToEdge = (IGraphicalEditPart) gmfDiagramEditPart.getRoot().getViewer().getEditPartRegistry().get(noteLinkToEdge);
        final IGraphicalEditPart editPartNoteUnLink = (IGraphicalEditPart) gmfDiagramEditPart.getRoot().getViewer().getEditPartRegistry().get(noteUnLink);

        Rectangle boundsNoteLinkToNode = editPartNoteLinkToNode.getFigure().getBounds();
        Rectangle boundsNoteLinkToEdge = editPartNoteLinkToEdge.getFigure().getBounds();

        final Rectangle boundsNoteUnLink = editPartNoteUnLink.getFigure().getBounds().getCopy();

        assertThat(p1, not(isPinnedMatcher()));
        assertThat(p2, not(isPinnedMatcher()));
        assertThat(edgeC1ToC2, not(isPinnedMatcher()));

        editor.getEditPart("p1", IDiagramContainerEditPart.class).select();
        SWTBotUtils.waitAllUiEvents();
        editor.clickContextMenu(Messages.PinElementsEclipseAction_text);
        bot.waitUntil(waitForPinned(p1));
        editor.getEditPart("c1ToC2", IDiagramEdgeEditPart.class).select();
        SWTBotUtils.waitAllUiEvents();
        editor.clickContextMenu(Messages.PinElementsEclipseAction_text);
        bot.waitUntil(waitForPinned(p1));
        assertThat(p1, isPinnedMatcher());
        assertThat(p2, not(isPinnedMatcher()));
        assertThat(edgeC1ToC2, isPinnedMatcher());
        editor.click(200, 200);
        arrangeAll();
        final Rectangle boundsP1AfterLayout = p1.getFigure().getBounds().getCopy();
        final Rectangle boundsP2AfterLayout = p2.getFigure().getBounds().getCopy();
        final Rectangle boundsNoteLinkToNodeAfterLayout = editPartNoteLinkToNode.getFigure().getBounds().getCopy();
        final Rectangle boundsNoteLinkToEdgeAfterLayout = editPartNoteLinkToEdge.getFigure().getBounds().getCopy();
        final Rectangle boundsNoteUnLinkAfterLayout = editPartNoteUnLink.getFigure().getBounds().getCopy();

        assertTrue("The node P1 has been moved despite it was pinned.", boundsP1.equals(boundsP1AfterLayout));
        assertFalse("The node P2 should have been moved.", boundsP2.equals(boundsP2AfterLayout));
        // The edge bounds is not checked because the source has been moved, so edge too.
        assertTrue("The linked note should not have been moved.", boundsNoteLinkToNode.equals(boundsNoteLinkToNodeAfterLayout));
        assertTrue("The linked note should not have been moved.", boundsNoteLinkToEdge.equals(boundsNoteLinkToEdgeAfterLayout));
        assertFalse("The linked note should have been moved.", boundsNoteUnLink.equals(boundsNoteUnLinkAfterLayout));
    }

    /**
     * Attach note to a pinned element and to an unpined element, launch arrange all and check that the note does not
     * move.
     */
    public void testAttachNoteToPinnedElementAndToUnpinnedElement() {
        changeDiagramPreference(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), true);

        openDiagram(VIEWPOINT_NAME, "PinUnpinNoteDiag", "PinUnpinNoteDiag");
        final DiagramEditPart gmfDiagramEditPart = getGmfEditor().getDiagramEditPart();
        final IDiagramElementEditPart p1 = (IDiagramElementEditPart) editor.getEditPart("p1", IDiagramContainerEditPart.class).part();
        final IDiagramElementEditPart p2 = (IDiagramElementEditPart) editor.getEditPart("p2", IDiagramContainerEditPart.class).part();
        final IDiagramEdgeEditPart edgeC1ToC2 = (IDiagramEdgeEditPart) editor.getEditPart("c1ToC2", IDiagramEdgeEditPart.class).part();
        final Rectangle boundsP1 = p1.getFigure().getBounds().getCopy();
        final Rectangle boundsP2 = p2.getFigure().getBounds().getCopy();
        EList<Node> notes = new BasicEList<Node>();
        Node noteLinkToNode = null;
        Node noteLinkToEdge = null;
        Node noteUnLink = null;

        notes = getNotes();

        for (Node note : notes) {
            if (note.getSourceEdges().size() > 0) {
                if (((Edge) note.getSourceEdges().get(0)).getTarget() instanceof Edge) {
                    noteLinkToEdge = note;
                } else {
                    noteLinkToNode = note;
                }
            } else {
                noteUnLink = note;
            }
        }

        final IGraphicalEditPart editPartNoteLinkToNode = (IGraphicalEditPart) gmfDiagramEditPart.getRoot().getViewer().getEditPartRegistry().get(noteLinkToNode);
        final IGraphicalEditPart editPartNoteLinkToEdge = (IGraphicalEditPart) gmfDiagramEditPart.getRoot().getViewer().getEditPartRegistry().get(noteLinkToEdge);
        final IGraphicalEditPart editPartNoteUnLink = (IGraphicalEditPart) gmfDiagramEditPart.getRoot().getViewer().getEditPartRegistry().get(noteUnLink);

        final Rectangle boundsNoteLinkToNode = editPartNoteLinkToNode.getFigure().getBounds();
        final Rectangle boundsNoteLinkToEdge = editPartNoteLinkToEdge.getFigure().getBounds();
        final Rectangle boundsNoteUnLink = editPartNoteUnLink.getFigure().getBounds().getCopy();

        assertThat(p1, not(isPinnedMatcher()));
        assertThat(p2, not(isPinnedMatcher()));
        assertThat(edgeC1ToC2, not(isPinnedMatcher()));

        editor.getEditPart("p1", IDiagramContainerEditPart.class).select();
        SWTBotUtils.waitAllUiEvents();
        editor.clickContextMenu(Messages.PinElementsEclipseAction_text);
        bot.waitUntil(waitForPinned(p1));
        editor.getEditPart("c1ToC2", IDiagramEdgeEditPart.class).select();
        SWTBotUtils.waitAllUiEvents();
        editor.clickContextMenu(Messages.PinElementsEclipseAction_text);
        bot.waitUntil(waitForPinned(p1));
        assertThat(p1, isPinnedMatcher());
        assertThat(p2, not(isPinnedMatcher()));
        assertThat(edgeC1ToC2, isPinnedMatcher());

        editor.activateTool("Note Attachment");

        editor.click(boundsNoteLinkToNode.getCenter());
        editor.click(boundsP2.getCenter());

        editor.click(200, 200);

        arrangeAll();
        final Rectangle boundsP1AfterLayout = p1.getFigure().getBounds().getCopy();
        final Rectangle boundsP2AfterLayout = p2.getFigure().getBounds().getCopy();
        final Rectangle boundsNoteLinkToNodeAfterLayout = editPartNoteLinkToNode.getFigure().getBounds().getCopy();
        final Rectangle boundsNoteLinkToEdgeAfterLayout = editPartNoteLinkToEdge.getFigure().getBounds().getCopy();
        final Rectangle boundsNoteUnLinkAfterLayout = editPartNoteUnLink.getFigure().getBounds().getCopy();

        assertTrue("The node P1 has been moved despite it was pinned.", boundsP1.equals(boundsP1AfterLayout));
        assertFalse("The node P2 should have been moved.", boundsP2.equals(boundsP2AfterLayout));
        // The edge bounds is not check because the source has been moved, so edge too.
        assertTrue("The linked note should not have been moved.", boundsNoteLinkToNode.equals(boundsNoteLinkToNodeAfterLayout));
        assertTrue("The linked note should not have been moved.", boundsNoteLinkToEdge.equals(boundsNoteLinkToEdgeAfterLayout));
        assertFalse("The linked note should have been moved.", boundsNoteUnLink.equals(boundsNoteUnLinkAfterLayout));
    }

    /**
     * Test preferences modification from API.
     */
    public void testChangePreferencesFromApi() {
        IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(DiagramPlugin.ID);
        final boolean oldPreference = prefs.getBoolean(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), false);

        // Check preference page.
        checkSiriusDiagramPreferencePage(oldPreference);

        // Change preference.
        prefs.putBoolean(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), !oldPreference);

        // Check preference page.
        checkSiriusDiagramPreferencePage(!oldPreference);

        // Reset preference
        prefs.putBoolean(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), oldPreference);

        // Check preference page.
        checkSiriusDiagramPreferencePage(oldPreference);
    }

    /**
     * Test preferences modification from API.
     */
    public void testChangePreferencesFromPreferencePage() {
        IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(DiagramPlugin.ID);
        final boolean oldPreference = prefs.getBoolean(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), false);

        // Check preference page consistency.
        checkSiriusDiagramPreferencePage(oldPreference);

        // Change preference.
        changeMoveNoteDuringLayoutInPreferencePage(!oldPreference);

        assertEquals(!oldPreference, prefs.getBoolean(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), false));

        // Reset preference
        changeMoveNoteDuringLayoutInPreferencePage(oldPreference);

        // Check preference page.
        assertEquals(oldPreference, prefs.getBoolean(SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), false));

    }

    private void changeMoveNoteDuringLayoutInPreferencePage(boolean enable) {
        bot.menu("Window").menu("Preferences...").click();
        bot.waitUntil(Conditions.shellIsActive("Preferences"));
        bot.tree().getTreeItem("Sirius").expand().select().getNode("Sirius Diagram").select();
        SWTBotCheckBox checkBox = bot.checkBox("Move unlinked notes during layout");
        if (enable) {
            checkBox.select();
        } else {
            checkBox.deselect();
        }
        bot.button(TestsUtil.isOxygenPlatform() ? JFaceResources.getString("PreferencesDialog.okButtonLabel") : IDialogConstants.OK_LABEL).click();
    }

    private void checkSiriusDiagramPreferencePage(boolean expectedValue) {
        bot.menu("Window").menu("Preferences...").click();
        bot.waitUntil(Conditions.shellIsActive("Preferences"));
        bot.tree().getTreeItem("Sirius").expand().select().getNode("Sirius Diagram").select();
        assertEquals(expectedValue, bot.checkBox("Move unlinked notes during layout").isChecked());
        bot.button(TestsUtil.isOxygenPlatform() ? JFaceResources.getString("PreferencesDialog.okButtonLabel") : IDialogConstants.OK_LABEL).click();

    }

    /**
     * get gmf editor.
     * 
     * @return {@link IDiagramWorkbenchPart}
     */
    private IDiagramWorkbenchPart getGmfEditor() {
        DRepresentation dDiagram = getRepresentationDescriptorWithName(localSession.getOpenedSession(), null, "PinUnpinNoteDiag").getRepresentation();

        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(localSession.getOpenedSession(), dDiagram, new NullProgressMonitor());

        return (IDiagramWorkbenchPart) editorPart;
    }

    /**
     * Return notes in Diagram.
     * 
     * @return Elist<Node> notes in diagram
     */
    private EList<Node> getNotes() {
        EList<Node> notes = new BasicEList<Node>();

        final IDiagramWorkbenchPart gmfEditor = getGmfEditor();
        final Diagram gmfDiagram = gmfEditor.getDiagram();

        for (Object child : gmfDiagram.getChildren()) {
            if (child instanceof Node node && node.getType().equals("Note")) {
                notes.add((Node) child);
            }
        }

        return notes;
    }
}
