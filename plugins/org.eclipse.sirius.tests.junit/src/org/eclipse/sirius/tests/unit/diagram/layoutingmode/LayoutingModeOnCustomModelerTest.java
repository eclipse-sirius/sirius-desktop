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
package org.eclipse.sirius.tests.unit.diagram.layoutingmode;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.util.SessionCallBackWithUI;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

/**
 * <p>
 * Ensures that Layouting mode forbids the execution of excepted tools. Also
 * checks Layouting mode lifecycle.
 * </p>
 * <p>
 * Tested tools :
 * <ul>
 * <li>Drag'n'Drop (on Bordered Nodes)</li>
 * <li>Double-click
 * <li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class LayoutingModeOnCustomModelerTest extends AbstractLayoutingModeTest {

    private static final String FOLDER_PATH = "/data/unit/layoutingMode/";

    private static final String SEMANTIC_MODEL_NAME = "vp2120.ecore";

    private static final String MODELER_NAME = "vp2120.odesign";

    private static final String VIEWPOINT_NAME = "LayoutingMode";

    private static final String REPRESENTATION_DECRIPTION_NAME = "LayoutingMode Diagram";

    @Override
    protected void setUp() throws Exception {
        // Step 1 : generic setup
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, FOLDER_PATH, SEMANTIC_MODEL_NAME, MODELER_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_NAME);
        initViewpoint(VIEWPOINT_NAME);

        // Step 2 : opening editor
        EPackage rootEPackage = (EPackage) semanticModel;
        EPackage newEPackage1 = rootEPackage.getESubpackages().get(0);
        EPackage newEPackage2 = rootEPackage.getESubpackages().get(1);
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DECRIPTION_NAME, rootEPackage);
        createRepresentation(REPRESENTATION_DECRIPTION_NAME, newEPackage1);
        createRepresentation(REPRESENTATION_DECRIPTION_NAME, newEPackage2);
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : setting a new Callback (so that NavigationTools can open
        // representations).
        this.getCommandFactory().setUserInterfaceCallBack(new SessionCallBackWithUI());
    }

    /**
     * Ensures that Layouting mode forbids edge DnD tools applying on bordered
     * nodes.
     */
    public void testLayoutingModeOnDragOnDropWithBorderedNodes() {
        // Step 1 : getting tool and diagram elements
        ContainerDropDescription dndTool = (ContainerDropDescription) getTool(diagram, "drop brodered");
        DDiagramElement eClass4 = getDiagramElementsFromLabel(diagram, "new EClass 4").iterator().next();
        DragAndDropTarget container = (DragAndDropTarget) getDiagramElementsFromLabel(diagram, "new Package 1").iterator().next();
        // Step 2 : applying tool
        Command command = getCommandFactory().buildDropInContainerCommandFromTool(container, eClass4, dndTool);
        executeCommand(command);
        // -> tool should have been applied
        assertToolHasBeenApplied(true);

        // Step 3 : save session and activating layoutingMode
        setLayoutingMode(true);
        session.save(new NullProgressMonitor());

        // Step 4 : applying tool
        command = getCommandFactory().buildDropInContainerCommandFromTool(container, eClass4, dndTool);
        executeCommand(command);

        // -> tool should not have been applied
        assertToolHasBeenApplied(false);

        // Step 5 : reopening diagram and applying tool
        reopenEditor();
        command = getCommandFactory().buildDropInContainerCommandFromTool(container, eClass4, dndTool);
        executeCommand(command);

        // -> tool should have been applied
        assertToolHasBeenApplied(true);
    }

    /**
     * Ensures that Layouting mode allows Double Click Tools applying <b> if
     * they contain only NavigationDescriptions</b>.
     * 
     * @throws InterruptedException
     */
    public void testLayoutingModeOnDoubleClickToolsWithNavigationDescription() throws InterruptedException {
        // Step 1 : getting tool and diagram elements
        DoubleClickDescription doubleClickTool = (DoubleClickDescription) getTool(diagram, "DoubleClick on Package");
        DDiagramElement package1 = getDiagramElementsFromLabel(diagram, "new Package 1").iterator().next();

        // Step 2 : applying tool
        Command command = getCommandFactory().buildDoubleClickOnElementCommandFromTool(package1, doubleClickTool);
        executeCommand(command);
        // -> tool should have been applied (a new editor should have been
        // opened
        assertNavigationToolHasBeenApplied();

        // Step 3 : save session and activating layoutingMode
        session.save(new NullProgressMonitor());
        closeAllEditorsExceptOriginal();
        setLayoutingMode(true);

        // Step 4 : applying tool
        command = getCommandFactory().buildDoubleClickOnElementCommandFromTool(package1, doubleClickTool);
        executeCommand(command);

        // -> tool should not have been applied
        assertNavigationToolHasBeenApplied();
        closeAllEditorsExceptOriginal();

        // Step 5 : reopening diagram and applying tool
        reopenEditor();
        command = getCommandFactory().buildDoubleClickOnElementCommandFromTool(package1, doubleClickTool);
        executeCommand(command);

        // -> tool should have been applied
        assertNavigationToolHasBeenApplied();
        closeAllEditorsExceptOriginal();

    }

    /**
     * Ensures that Layouting forbids Double Click Tools applying <b> if they
     * don't contain only NavigationDescriptions</b>.
     */
    public void testLayoutingModeOnDoubleClickToolsWithoutNavigationDescription() {
        // Step 1 : getting tool and diagram elements
        DoubleClickDescription doubleClickTool = (DoubleClickDescription) getTool(diagram, "DoubleClick On Class");
        DDiagramElement eClass4 = getDiagramElementsFromLabel(diagram, "new EClass 4").iterator().next();

        // Step 2 : applying tool
        Command command = getCommandFactory().buildDoubleClickOnElementCommandFromTool(eClass4, doubleClickTool);
        executeCommand(command);
        // -> tool should have been applied
        assertToolHasBeenApplied(true);

        // Step 3 : save session and activating layoutingMode
        setLayoutingMode(true);
        session.save(new NullProgressMonitor());

        // Step 4 : applying tool
        command = getCommandFactory().buildDoubleClickOnElementCommandFromTool(eClass4, doubleClickTool);
        executeCommand(command);

        // -> tool should not have been applied
        assertToolHasBeenApplied(false);

        // Step 5 : reopening diagram and applying tool
        reopenEditor();
        command = getCommandFactory().buildDoubleClickOnElementCommandFromTool(eClass4, doubleClickTool);
        executeCommand(command);

        // -> tool should have been applied
        assertToolHasBeenApplied(true);
    }

    /**
     * Ensures that the Navigation Tool has been applied by checking that a new
     * editor has been opened.
     * 
     * @throws InterruptedException
     */
    private void assertNavigationToolHasBeenApplied() throws InterruptedException {
        TestsUtil.emptyEventsFromUIThread();
        Thread.sleep(200);
        assertTrue("A new editor should have been opened when applying double-click tool", !(EclipseUIUtil.getActiveEditor().equals(editor)));
    }

    private void closeAllEditorsExceptOriginal() {
        for (IEditorReference editorRef : EclipseUIUtil.getActivePage().getEditorReferences()) {
            try {
                if (!(editorRef.getEditorInput().equals(editor.getEditorInput()))) {
                    DialectUIManager.INSTANCE.closeEditor(editorRef.getEditor(false), false);
                    TestsUtil.synchronizationWithUIThread();
                }
            } catch (PartInitException e) {
                // Nothing to do
            }
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        // step 1 : restoring previous Callback
        this.getCommandFactory().setUserInterfaceCallBack(new NoUICallback());

        // Step 2 : closing editor and generic teardown
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
