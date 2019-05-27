/*******************************************************************************
 * Copyright (c) 2017, 2018 Obeo.
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

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIProject;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * This class tests various functionalities of the {@link SessionEditor}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SessionEditorTest extends AbstractSiriusSwtBotGefTestCase {
    private final class SessionEditorOpener implements RunnableWithResult<IEditorPart> {
        private IEditorPart resultEditor;

        private UILocalSession localSession;

        public SessionEditorOpener(UILocalSession localSession) {
            this.localSession = localSession;
        }

        @Override
        public void run() {
            URI uri = localSession.getOpenedSession().getSessionResource().getURI();
            try {
                resultEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .openEditor(new FileEditorInput(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)))), SessionEditor.EDITOR_ID);
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
    }

    private static final String TEMP_SECOND_PROJECT_NAME = "DesignerTestProject2";

    private static final String PATH = "/data/unit/layoutingMode/";

    private static final String SEMANTIC_MODEL_FILENAME = "vp2120.ecore";

    private static final String MODELER_MODEL_FILENAME = "vp2120.odesign";

    private static final String SESSION_FILE_NAME = "vp2120.aird";

    private static final String SESSION_FILE_NAME_2 = "vp2120_2.aird";

    private static final String FILE_DIR = "/";

    private SessionEditor sessionEditor;

    private SessionEditor sessionEditor2;

    private UIResource sessionAirdResource2;

    private UILocalSession localSession2;

    private UIProject designerProject2;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, MODELER_MODEL_FILENAME, SESSION_FILE_NAME);

        designerProject2 = designerPerspective.createProject(TEMP_SECOND_PROJECT_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + SESSION_FILE_NAME, TEMP_SECOND_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + SESSION_FILE_NAME_2, TEMP_SECOND_PROJECT_NAME + "/" + SESSION_FILE_NAME_2);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_NAME);
        sessionAirdResource2 = new UIResource(designerProject2, FILE_DIR, SESSION_FILE_NAME_2);

        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2);

        RunnableWithResult<IEditorPart> sessionEditor1Launcher = new SessionEditorOpener(localSession);

        PlatformUI.getWorkbench().getDisplay().syncExec(sessionEditor1Launcher);
        sessionEditor = (SessionEditor) sessionEditor1Launcher.getResult();

        SWTBotEditor activeEditor = bot.activeEditor();
        SWTBotTree tree = activeEditor.bot().tree(1);
        try {
            tree.getTreeItem("LayoutingMode");
        } catch (WidgetNotFoundException e) {
            fail("Test setup is wrong. The activated viewpoint 'LayoutingMode' should be present");
        }

        RunnableWithResult<IEditorPart> sessionEditor2Launcher = new SessionEditorOpener(localSession2);

        PlatformUI.getWorkbench().getDisplay().syncExec(sessionEditor2Launcher);
        sessionEditor2 = (SessionEditor) sessionEditor2Launcher.getResult();
    }

    @Override
    protected void tearDown() throws Exception {
        sessionEditor.close(false);
        sessionEditor = null;
        sessionEditor2 = null;
        sessionAirdResource2 = null;
        localSession2.close(false);
        designerProject2 = null;
        super.tearDown();

    }

    /**
     * Test that changing a viewpoint activation status of a viewpoint in a session editor does not change the
     * activation status of a viewpoint in another opened session editor.
     */
    public void testViewpointActivationStatusChange() {
        SWTBotEditor activeEditor = bot.activeEditor();
        final SWTBotTree secondEditorTree = activeEditor.bot().tree(1);
        SWTBotTreeItem viewpoint = null;
        try {
            viewpoint = secondEditorTree.getTreeItem("LayoutingMode");
        } catch (IndexOutOfBoundsException | WidgetNotFoundException e) {
            fail("Test setup is wrong. The activated viewpoint 'LayoutingMode' should be present");
        }
        viewpoint.click();
        bot.button("Disable").click();
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                SWTBotUtils.waitAllUiEvents();
                try {
                    secondEditorTree.getTreeItem("LayoutingMode (disabled)");
                } catch (IndexOutOfBoundsException | WidgetNotFoundException e) {
                    return false;
                }
                return true;
            }

            @Override
            public String getFailureMessage() {
                return "Viewpoint deactivation failed.";
            }
        });
        activeEditor.close();
        SWTBotUtils.waitAllUiEvents();
        SWTBotEditor firstEditor = bot.editorByTitle(SESSION_FILE_NAME);
        SWTBotTree firstEditorTree = firstEditor.bot().tree(1);
        try {
            firstEditorTree.getTreeItem("LayoutingMode");
        } catch (IndexOutOfBoundsException | WidgetNotFoundException e) {
            fail("The activated viewpoint 'LayoutingMode' should be present in first opened session editor. Something should have activated it wrongly.");
        }

    }

}
