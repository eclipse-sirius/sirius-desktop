/*******************************************************************************
 * Copyright (c) 2018 Obeo
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
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRootMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.junit.Test;

/**
 * Tests session editor behavior when project containing it are removed.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class SessionEditorProjectRemovalTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DIAGRAM_REPRESENTATION_NAME = "deleteFromModelDiagram";

    private static final String DIAGRAM_DESCRIPTION_NAME = "EdgeLabelRefreshPb";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String VSM_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/airdEditor/";

    private static final String FILE_DIR = "/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, VSM_FILE, SESSION_FILE);

    }

    /**
     * Tests that no problem occurs when: The aird editor is opened as well as a diagram editor and the command stack is
     * dirty because of an undo from diagram editor.
     * 
     * @throws Exception
     *             if a problem occurs.
     */
    @Test
    public void testProjectRemovalWithDirtyCommandStack() throws Exception {
        // open aird editor
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);

        RunnableWithResult<IEditorPart> result = new RunnableWithResult<IEditorPart>() {
            private IEditorPart resultEditor;

            @Override
            public void run() {
                try {
                    final IFile fileToOpen = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(sessionAirdResource.getFullPath()));
                    resultEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new FileEditorInput(fileToOpen), SessionEditor.EDITOR_ID);
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
        Session session = ((SessionEditor) result.getResult()).getSession();
        openRepresentation(session, DIAGRAM_DESCRIPTION_NAME, DIAGRAM_REPRESENTATION_NAME, DDiagram.class);
        session.getTransactionalEditingDomain().getCommandStack().undo();

        SWTBotView modelExplorerView = bot.viewById("org.eclipse.sirius.ui.tools.views.model.explorer");
        SWTBotTree tree = modelExplorerView.bot().tree(0);
        SWTBotTreeItem treeItem = tree.getTreeItem("DesignerTestProject");
        SWTBotRootMenu contextMenu = treeItem.contextMenu();
        SWTBotMenu menuDelete = contextMenu.menu("Delete");
        menuDelete.click();

        SWTBot shellBot = SWTBotSiriusHelper.getShellBot("Delete Resources");
        SWTBotButton button = shellBot.button("OK");
        button.click();

    }
}
