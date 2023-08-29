/*******************************************************************************
 * Copyright (c) 2015, 2023 THALES GLOBAL SERVICES.
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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.tree.TreeUtils;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.ui.tools.api.control.SiriusControlHandler;
import org.eclipse.sirius.ui.tools.api.control.SiriusUncontrolHandler;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.junit.Assert;

/**
 * All tests for control/uncontrol
 * 
 * @author <a href="steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class ControlUncontrolWithOpenedRepresentationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String CONTROL_SHELL_NAME = "Control";

    private static final String MODEL = "460351.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "460351.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/control/tc460351/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        designerProject.convertToModelingProject();
    }

    /**
     * Open a diagram located under a semantic element to control. Once controlled, we validate that the diagram is
     * still editable. Next the semantic element is uncontrolled and we validate again that the diagram is still
     * editable.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckOpenedDiagramBehaviourOnControlUncontrol() throws Exception {
        processCheckOpenedDiagramBehaviourOnControlUncontrol("460351Diagram1", "p2");
    }

    /**
     * Open a diagram located under a sub element of a semantic element to control. Once controlled, we validate that
     * the diagram is still editable. Next the semantic element is uncontrolled and we validate again that the diagram
     * is still editable.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckOpenedSubDiagramBehaviourOnControlUncontrol() throws Exception {
        processCheckOpenedDiagramBehaviourOnControlUncontrol("460351Diagram2", "p3");
    }

    /**
     * Open a table located under a semantic element to control. Once controlled, we validate that the table is still
     * editable. Next the semantic element is uncontrolled and we validate again that the table is still editable.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckOpenedTableBehaviourOnControlUncontrol() throws Exception {
        processCheckOpenedTableBehaviourOnControlUncontrol("460351Table1");
    }

    /**
     * Open a table located under a sub element of a semantic element to control. Once controlled, we validate that the
     * table is still editable. Next the semantic element is uncontrolled and we validate again that the table is still
     * editable.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckOpenedSubTableBehaviourOnControlUncontrol() throws Exception {
        processCheckOpenedTableBehaviourOnControlUncontrol("460351Table2");
    }

    /**
     * Open a tree located under a semantic element to control. Once controlled, we validate that the tree is still
     * editable. Next the semantic element is uncontrolled and we validate again that the tree is still editable.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckOpenedTreeBehaviourOnControlUncontrol() throws Exception {
        processCheckOpenedTreeBehaviourOnControlUncontrol("460351Tree1");
    }

    /**
     * Open a tree located under a sub element of a semantic element to control. Once controlled, we validate that the
     * tree is still editable. Next the semantic element is uncontrolled and we validate again that the tree is still
     * editable.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckOpenedSubTreeBehaviourOnControlUncontrol() throws Exception {
        processCheckOpenedTreeBehaviourOnControlUncontrol("460351Tree2");
    }

    private void processCheckOpenedDiagramBehaviourOnControlUncontrol(String diagramName, String packageName) {
        final SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "460351Diagram", diagramName, DDiagram.class);

        // Execute control
        launchControlUncontrolUsingHandler(true);
        checkDiagramIsEditable(editor, true, packageName);

        // Save session to be sync again
        localSession.getOpenedSession().save(new NullProgressMonitor());

        // Execute uncontrol
        launchControlUncontrolUsingHandler(false);
        checkDiagramIsEditable(editor, false, packageName);

        // Save session to be sync again
        localSession.getOpenedSession().save(new NullProgressMonitor());
    }

    private void processCheckOpenedTableBehaviourOnControlUncontrol(String tableName) {
        final SWTBotEditor tableEditorBot = openRepresentation(localSession.getOpenedSession(), "460351Table", tableName, DTable.class);

        // Execute control
        launchControlUncontrolUsingHandler(true);
        checkTableIsEditable(tableEditorBot, true);

        // Save session to be sync again
        localSession.getOpenedSession().save(new NullProgressMonitor());

        // Execute uncontrol
        launchControlUncontrolUsingHandler(false);
        checkTableIsEditable(tableEditorBot, false);

        // Save session to be sync again
        localSession.getOpenedSession().save(new NullProgressMonitor());
    }

    private void processCheckOpenedTreeBehaviourOnControlUncontrol(String treeName) {
        final SWTBotEditor treeEditorBot = openRepresentation(localSession.getOpenedSession(), "460351Tree", treeName, DTree.class);

        // Execute control
        launchControlUncontrolUsingHandler(true);
        checkTreeIsEditable(treeEditorBot, true);

        // Save session to be sync again
        localSession.getOpenedSession().save(new NullProgressMonitor());

        // Execute uncontrol
        launchControlUncontrolUsingHandler(false);
        checkTreeIsEditable(treeEditorBot, false);

        // Save session to be sync again
        localSession.getOpenedSession().save(new NullProgressMonitor());
    }

    /**
     * This method is used to bypass a current issue where the "Control" or "Uncontrol" actions are unexpectedly
     * disabled. Therefore SWTBotUtils.clickContextMenu can't be used. <br/>
     * Note that the first step is to access the package p1 and then to control/uncontrol it.
     * 
     * @param shouldControl
     *            true if we should control, false if we should uncontrol
     */
    private void launchControlUncontrolUsingHandler(final boolean shouldControl) {
        // Explore the semantic model to find the package "p1" that will be
        // control/uncontrol
        Resource semanticResource = localSession.getOpenedSession().getSemanticResources().iterator().next();
        Assert.assertTrue("The semantic resource is not the expected one", semanticResource.getURI().toString().endsWith(MODEL));
        EObject rootPackage = semanticResource.getContents().iterator().next();
        Assert.assertTrue("The element is not an EPackage as expected", rootPackage instanceof EPackage);
        Assert.assertEquals("The element is not the EPackage expected", "460351", ((EPackage) rootPackage).getName());
        final EObject eObject = rootPackage.eContents().iterator().next();
        Assert.assertTrue("The element is not an EPackage as expected", eObject instanceof EPackage);
        Assert.assertEquals("The element is not the EPackage expected", "p1", ((EPackage) eObject).getName());
        final EPackage p1 = (EPackage) eObject;

        UIThreadRunnable.asyncExec(new VoidResult() {

            @Override
            public void run() {
                try {
                    new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, new WorkspaceModifyOperation() {

                        @Override
                        protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                            Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
                            if (shouldControl) {
                                new SiriusControlHandler().performControl(activeShell, p1, new NullProgressMonitor());
                            } else {
                                new SiriusUncontrolHandler().performUncontrol(activeShell, p1, new NullProgressMonitor());
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
    }

    private void checkDiagramIsEditable(final SWTBotSiriusDiagramEditor editor, boolean isControlling, String elementToMoveName) {
        executeControlUncontrol(isControlling);

        // Move an element to validate that the diagram is not read only
        SWTBotGefEditPart elementToMove = editor.getEditPart(elementToMoveName);
        Point modifiedLocation = editor.getBounds(elementToMove).getTranslated(50, 50).getLocation();
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(elementToMove);
        editor.drag(elementToMove, modifiedLocation.x, modifiedLocation.y);
        bot.waitUntil(checkEditPartMoved);

        elementToMove = editor.getEditPart(elementToMoveName);
        Point newLocation = editor.getBounds(elementToMove).getLocation();

        // Validation that the session is dirty and the element has been moved
        Assert.assertEquals("The session is expected to be dirty", SessionStatus.DIRTY, localSession.getOpenedSession().getStatus());
        // There is a strange delta but this is irrelevant to the current test,
        // we just need to validate that the diagram can be edited
        Assert.assertEquals(elementToMoveName + " is not at the expected horizontal position", modifiedLocation.x, newLocation.x, 2);
        Assert.assertEquals(elementToMoveName + " is not at the expected vertical position", modifiedLocation.y, newLocation.y, 2);
    }

    private void checkTableIsEditable(final SWTBotEditor tableEditorBot, boolean isControlling) {
        executeControlUncontrol(isControlling);

        DTableEditor dTableEditor = (DTableEditor) tableEditorBot.getReference().getEditor(false);
        DTable dTable = (DTable) dTableEditor.getRepresentation();

        DLine firstDLine = dTable.getLines().get(0);
        if (isControlling) {
            TreeUtils.collapseTreeItem(tableEditorBot, firstDLine);
        } else {
            TreeUtils.expandTreeItem(tableEditorBot, firstDLine);
        }

        // Validation that the session is dirty and the element has been moved
        Assert.assertEquals("The session is expected to be dirty", SessionStatus.DIRTY, localSession.getOpenedSession().getStatus());
    }

    private void checkTreeIsEditable(final SWTBotEditor treeEditorBot, boolean isControlling) {
        executeControlUncontrol(isControlling);

        DTreeEditor dTreeEditor = (DTreeEditor) treeEditorBot.getReference().getEditor(false);
        DTree dTree = (DTree) dTreeEditor.getRepresentation();

        DTreeItem firstTreeItem = dTree.getOwnedTreeItems().get(0);
        if (isControlling) {
            TreeUtils.collapseTreeItem(treeEditorBot, firstTreeItem);
        } else {
            TreeUtils.expandTreeItem(treeEditorBot, firstTreeItem);
        }

        // Validation that the session is dirty and the element has been moved
        Assert.assertEquals("The session is expected to be dirty", SessionStatus.DIRTY, localSession.getOpenedSession().getStatus());
    }

    private void executeControlUncontrol(boolean isControlling) {
        if (isControlling) {
            bot.waitUntilWidgetAppears(Conditions.shellIsActive(CONTROL_SHELL_NAME));
            final SWTBotShell controlShell = bot.shell(CONTROL_SHELL_NAME);
            controlShell.activate();

            final SWTBotButton ok = bot.button("OK");
            bot.waitUntilWidgetAppears(new ItemEnabledCondition(ok));
            ok.click();

            bot.waitUntilWidgetAppears(Conditions.shellIsActive("Wizard of representations selection"));
            final SWTBotShell wizardShell = bot.shell("Wizard of representations selection");
            wizardShell.activate();

            final SWTBotButton finish = bot.button("Finish");
            bot.waitUntilWidgetAppears(new ItemEnabledCondition(finish));
            finish.click();
        } else {
            bot.waitUntilWidgetAppears(Conditions.shellIsActive("Uncontrol representations?"));
            final SWTBotShell wizardShell = bot.shell("Uncontrol representations?");
            wizardShell.activate();

            final SWTBotButton yes = bot.button("Yes");
            bot.waitUntilWidgetAppears(new ItemEnabledCondition(yes));
            yes.click();
        }
    }
}
