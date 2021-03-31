/*******************************************************************************
 * Copyright (c) 2017, 2021 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Class test for the new feature "drag and drop on aird editor". see bug #517532
 * 
 * @author jmallet
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DndWorkspaceToAirdEditorTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "436.ecore";

    private static final String SESSION_FILE = "436.aird";

    private static final String VSM_FILE = "436.odesign";

    private static final String SAMPLE_MODEL_FILE = "file.ecore";

    private static final String DATA_UNIT_DIR = "data/unit/dragAndDrop/tc-436/";

    private static final String FILE_DIR = "/";

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, VSM_FILE, SESSION_FILE, SAMPLE_MODEL_FILE);
    }

    /**
     * Validate drag&drop of a model from the explorer view to the models block of the aird editor.
     * 
     * @throws Exception
     *             if an error occurs.
     */
    @Test
    public void testDragAndDropModelFile() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            // On some IC server this test is KO so we don't launch if the
            // unreliable test must be skipped.
            return;
        }
        // open aird editor
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);

        RunnableWithResult<IEditorPart> result = new RunnableWithResult<IEditorPart>() {
            private IEditorPart resultEditor;

            @Override
            public void run() {
                try {
                    final IFile fileToOpen = ResourcesPlugin.getWorkspace().getRoot()
                            .getFile(new Path(sessionAirdResource.getFullPath()));
                    resultEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                            .openEditor(new FileEditorInput(fileToOpen), SessionEditor.EDITOR_ID);
                } catch (PartInitException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public IEditorPart getResult() {
                return resultEditor;
            }

            @Override
            public void setStatus(IStatus status) {

            }

            @Override
            public IStatus getStatus() {
                return null;
            }
        };
        PlatformUI.getWorkbench().getDisplay().syncExec(result);
        // Get back trees
        SWTBotTree semanticAirdTree = bot.editorById(SessionEditor.EDITOR_ID).bot().tree(0);

        SWTBotTree modelExplorerTree = bot.viewByTitle("Model Explorer").bot().tree();
        SWTBotTreeItem sampleFile = modelExplorerTree.expandNode(designerProject.getName())
                .expandNode(SAMPLE_MODEL_FILE);
        SWTBotUtils.dragAndDropFileToAirdEditor(bot, semanticAirdTree, sampleFile);
        // check item of the semantic aird tree
        boolean isDragAndDropOk = false;
        for (SWTBotTreeItem swtBotTreeItem : semanticAirdTree.getAllItems()) {
            String text = swtBotTreeItem.getText();
            if (text.contains(SAMPLE_MODEL_FILE)) {
                isDragAndDropOk = true;
            }
        }

        assertTrue("Model file file.ecore must be added to semantic model tree.", isDragAndDropOk);

    }
}
