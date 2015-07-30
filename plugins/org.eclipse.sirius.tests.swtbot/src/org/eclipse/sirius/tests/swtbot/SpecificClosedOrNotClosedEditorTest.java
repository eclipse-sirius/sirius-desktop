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
package org.eclipse.sirius.tests.swtbot;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.ui.business.api.dialect.DefaultDialectEditorDialogFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.google.common.collect.Lists;

/**
 * Test that if deleted target element present in other representation opened,
 * the other representation is closed. Testing VP-1853 and VP-1854.
 * 
 * @author jdupont
 */
public class SpecificClosedOrNotClosedEditorTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME_1854_1 = "p1 package entities";

    private static final String REPRESENTATION_INSTANCE_NAME_1854_2 = "new VP-1854";

    private static final String REPRESENTATION_INSTANCE_NAME1 = "new diag1";

    private static final String REPRESENTATION_INSTANCE_NAME2 = "new diag2";

    private static final String REPRESENTATION_INSTANCE_NAME3 = "new tree";

    private static final String REPRESENTATION_INSTANCE_NAME4 = "new editionTable";

    private static final String REPRESENTATION_INSTANCE_NAME5 = "new crossTable";

    private static final String REPRESENTATION_NAME_1854_1 = "Entities";

    private static final String REPRESENTATION_NAME_1854_2 = "VP-1854";

    private static final String REPRESENTATION_NAME1 = "diag1";

    private static final String REPRESENTATION_NAME2 = "diag2";

    private static final String REPRESENTATION_NAME3 = "tree";

    private static final String REPRESENTATION_NAME4 = "editionTable";

    private static final String REPRESENTATION_NAME5 = "crossTable";

    private static final String MODEL = "My.ecore";

    private static final String VSM = "My.odesign";

    private static final String SESSION_FILE = "My.aird";

    private static final String MODEL_1854 = "1854.ecore";

    private static final String VSM_1854 = "vp-1854.odesign";

    private static final String SESSION_FILE_1854 = "1854.aird";

    private static final String DATA_UNIT_DIR = "data/unit/closeEditorSpecificTest/";

    private static final String FILE_DIR = "/";

    private SWTBotSiriusDiagramEditor editor;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, MODEL_1854, SESSION_FILE, SESSION_FILE_1854, VSM, VSM_1854);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Test the deleted no-target element on representation, not closed other
     * editor.
     */
    public void testSpecificNotCloseEditor() {
        // Open the 2 representations
        openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME2, REPRESENTATION_INSTANCE_NAME2, DDiagram.class);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME1, REPRESENTATION_INSTANCE_NAME1, DDiagram.class);

        // Selected the edge to remove
        SWTBotGefEditPart selectedElement = selectAndCheckEditPart("", AbstractDiagramEdgeEditPart.class);
        DEdgeEditPart edgeSelected = (DEdgeEditPart) selectedElement.part();
        assertEquals("The selected edge is not correct", (((DNodeEditPart) ((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().get(0)).getSourceConnections().get(0)),
                edgeSelected);
        editor.select(selectedElement);
        // Remove the edge
        editor.clickContextMenu("Delete from Model");
        // Check that edge was removed.
        assertTrue("The edge is not deleted", ((DNodeEditPart) ((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().get(0)).getSourceConnections().size() == 0);
        // Check that the second editor is always open.
        assertEquals("The second editor was closed", REPRESENTATION_INSTANCE_NAME2, ((SWTBotEditor) bot.editors().get(0)).getReference().getPartName());
    }

    /**
     * Test the deleted target element on representation, not closed other
     * editor.
     */
    public void testSpecificCloseEditor() {
        // Open the 2 representations
        openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME2, REPRESENTATION_INSTANCE_NAME2, DDiagram.class);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME1, REPRESENTATION_INSTANCE_NAME1, DDiagram.class);
        // Selected the node to remove
        SWTBotGefEditPart selectedElement = selectAndCheckEditPart("AAA", DNodeEditPart.class);
        DNodeEditPart nodeSelected = (DNodeEditPart) selectedElement.part();
        assertEquals("The selected node is not correct", (((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().get(0)), nodeSelected);
        editor.select(selectedElement);
        // Remove the node
        editor.clickContextMenu("Delete from Model");
        // Check that node was removed.
        assertTrue("The node is not deleted", ((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().size() == 1);
        // Check and close the popup.
        bot.waitUntil(Conditions.shellIsActive(DefaultDialectEditorDialogFactory.ELEMENT_HAS_BEEN_DELETED_TITLE));
        SWTBotShell editorWillClosePopup = bot.activeShell();
        bot.button("OK").click();
        bot.waitUntil(Conditions.shellCloses(editorWillClosePopup));
        // Check that the second editor is closed.
        assertTrue("The second editor was not closed", bot.editors().size() == 1);
    }

    /**
     * Test the deleted target element on representation, not closed other
     * editor.
     */
    public void testSpecificCloseEditorsFromSemanticDeleteAfterReload() throws Exception {
        doTestSpecificCloseEditorsAfterReload(true);
    }

    /**
     * Test the deleted target element on representation, not closed other
     * editor.
     */
    public void testSpecificCloseEditorsFromRepresentationDeleteAfterReload() throws Exception {
        doTestSpecificCloseEditorsAfterReload(false);
    }

    /**
     * Open several representations, reload the aird and then delete them
     * programmatically or delete their common semantic target to test the
     * dialect editor closer.
     * 
     * @param deleteSemantic
     *            true to delete the semantic element, false to delete each
     *            representation.
     */
    private void doTestSpecificCloseEditorsAfterReload(boolean deleteSemantic) throws Exception {
        boolean oldPref = ResourcesPlugin.getPlugin().getPluginPreferences().getBoolean(ResourcesPlugin.PREF_LIGHTWEIGHT_AUTO_REFRESH);
        ResourcesPlugin.getPlugin().getPluginPreferences().setValue(ResourcesPlugin.PREF_LIGHTWEIGHT_AUTO_REFRESH, true);
        try {
            // Open the 2 representations
            openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME2, REPRESENTATION_INSTANCE_NAME2, DDiagram.class);
            openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME3, REPRESENTATION_INSTANCE_NAME3, DTree.class);
            openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME4, REPRESENTATION_INSTANCE_NAME4, DTable.class);
            openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME5, REPRESENTATION_INSTANCE_NAME5, DTable.class);
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME1, REPRESENTATION_INSTANCE_NAME1, DDiagram.class);
            localSession.save();

            final EditorWillBeClosedInformationDialogCallerDetector editorWillCloseDetector = new EditorWillBeClosedInformationDialogCallerDetector();
            final Collection<DRepresentation> openedRepresentations = Lists.newArrayList();
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(localSession.getOpenedSession());
            for (DialectEditor ed : uiSession.getEditors()) {
                DRepresentation representation = ed.getRepresentation();
                openedRepresentations.add(representation);
                assertFalse(representation.eIsProxy());
                ed.setDialogFactory(editorWillCloseDetector);
            }

            // Reload the aird
            forceReloadFromExternalFileModification(localSession.getOpenedSession().getSessionResource());

            // Check that all previous DRepresentation objects have been
            // proxified.
            for (DRepresentation representation : openedRepresentations) {
                assertTrue(representation.eIsProxy());
            }
            openedRepresentations.clear();

            // Check that editors are able to resolve their proxy model element.
            for (DialectEditor ed : Lists.newArrayList(uiSession.getEditors())) {
                SWTBotSiriusHelper.getSiriusEditor(ed.getTitle()).show();
                DRepresentation representation = ed.getRepresentation();
                assertFalse(representation.eIsProxy());
                openedRepresentations.add(representation);
            }

            // Show the editor shown before the editor checks.
            editor.show();

            if (deleteSemantic) {
                // Selected the node to remove
                SWTBotGefEditPart selectedElement = selectAndCheckEditPart("AAA", DNodeEditPart.class);
                DNodeEditPart nodeSelected = (DNodeEditPart) selectedElement.part();
                assertEquals("The selected node is not correct", (((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().get(0)), nodeSelected);
                editor.select(selectedElement);
                // Remove the node
                editor.clickContextMenu("Delete from Model");
                // Check that node was removed.
                assertTrue("The node is not deleted", ((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().size() == 1);
            } else {
                openedRepresentations.remove(editor.getDiagramEditPart().resolveSemanticElement());
                final ModelAccessor modelAccessor = localSession.getOpenedSession().getModelAccessor();
                TransactionalEditingDomain transactionalEditingDomain = localSession.getOpenedSession().getTransactionalEditingDomain();
                transactionalEditingDomain.getCommandStack().execute(new RecordingCommand(transactionalEditingDomain) {
                    @Override
                    protected void doExecute() {
                        for (DRepresentation rep : openedRepresentations) {
                            modelAccessor.eDelete(rep, null);
                        }
                    }
                });
            }

            // FIXME after reload the dialect editor closer of diagrams still
            // point
            // to the previous element which is a proxy and is not notified any
            // more.
            final int dialectEditorCloserExpectedDetections = deleteSemantic ? 4 : 3;
            // Check and close the popups.
            bot.waitUntil(new DefaultCondition() {
                @Override
                public boolean test() throws Exception {
                    return dialectEditorCloserExpectedDetections == editorWillCloseDetector.getNotifiedEditors();
                }

                @Override
                public String getFailureMessage() {
                    return "Some editors were not asked to infor the user that they will be closed due to root element deletion.";
                }
            });
            // Check that the other editors are closed.
            // FIXME after reload the dialect editor closer of diagrams still
            // point
            // to the previous element which is a proxy and is not notified any
            // more.
            assertEquals("Some editors should have been closed.", 5 - dialectEditorCloserExpectedDetections, bot.editors().size());
        } finally {
            ResourcesPlugin.getPlugin().getPluginPreferences().setValue(ResourcesPlugin.PREF_LIGHTWEIGHT_AUTO_REFRESH, oldPref);
        }
    }

    /**
     * Edit the semantic model and save resource.
     */
    private void forceReloadFromExternalFileModification(final Resource res) throws Exception {
        final EObject rootToReload = res.getContents().get(0);
        ICondition reload = new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return rootToReload.eIsProxy();
            }

            @Override
            public String getFailureMessage() {
                return "The resource has not been reloaded";
            }
        };
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                try {
                    final Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
                    new ProgressMonitorDialog(activeShell).run(false, false, new WorkspaceModifyOperation() {
                        @Override
                        public void execute(IProgressMonitor monitor) {
                            ResourceSet set = new ResourceSetImpl();
                            try {
                                final EObject root = ModelUtils.load(res.getURI(), set);
                                root.eResource().save(Collections.EMPTY_MAP);
                            } catch (IOException e) {
                                fail("Problem when saving the resource in another resourceSet : " + e.getMessage());
                            }
                        }
                    });
                } catch (InvocationTargetException e) {
                    fail("Cannot launch control/uncontrol");
                } catch (InterruptedException e) {
                    fail("Cannot launch control/uncontrol");
                }
            }
        });
        bot.waitUntil(reload);
    }

    /**
     * Test that UnGroup packages working properly and that the second editor is
     * closed because the package p1 was deleted.
     */
    public void testUnGroupPackages() {
        localSession.close(false);
        UIResource sessionAirdResource1854 = new UIResource(designerProject, FILE_DIR, SESSION_FILE_1854);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource1854, true);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // Open the 2 representations
        openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_1854_1, REPRESENTATION_INSTANCE_NAME_1854_1, DDiagram.class);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_1854_2, REPRESENTATION_INSTANCE_NAME_1854_2, DDiagram.class);
        // Selected the package
        SWTBotGefEditPart selectedElement = selectAndCheckEditPart("p1", DNodeContainerEditPart.class);
        DNodeContainerEditPart containerSelected = (DNodeContainerEditPart) selectedElement.part();
        assertEquals("The selected container is not correct", (((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().get(2)), containerSelected);
        editor.select(selectedElement);
        // UnGroup the package (move class in p1 to root and remove p1)
        ICondition done = new OperationDoneCondition();
        editor.clickContextMenu("Ungroup classes");
        bot.waitUntil(done);
        // Check that package was removed.
        assertTrue("The package is not deleted", ((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().size() == 4);
        // Check and close the popup.
        bot.waitUntil(Conditions.shellIsActive(DefaultDialectEditorDialogFactory.ELEMENT_HAS_BEEN_DELETED_TITLE));
        SWTBotShell editorWillClosePopup = bot.activeShell();
        bot.button("OK").click();
        bot.waitUntil(Conditions.shellCloses(editorWillClosePopup));
        // Check that the second editor is closed.
        assertEquals("The second editor is always opened", 1, bot.editors().size());
    }

    /**
     * Return the selected edit part.
     * 
     * @param name
     * @param type
     * @return the selected edit part
     */
    private SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = editor.getEditPart(name, type);

        assertNotNull("The requested edit part should not be null", botPart);
        botPart.select();

        return botPart;
    }

    // Specific dialect editor dialog factory to detect the close without root
    // element detected popup.
    private class EditorWillBeClosedInformationDialogCallerDetector extends DefaultDialectEditorDialogFactory {

        int notifiedEditors = 0;

        @Override
        public void editorWillBeClosedInformationDialog(Shell parent) {
            notifiedEditors++;
        }

        /**
         * @return the notifiedEditors
         */
        public int getNotifiedEditors() {
            return notifiedEditors;
        }

    }

}
