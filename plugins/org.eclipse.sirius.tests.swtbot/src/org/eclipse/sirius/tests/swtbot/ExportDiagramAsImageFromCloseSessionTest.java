/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Ensure that export as image of a closed session is OK.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ExportDiagramAsImageFromCloseSessionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "/data/sequence/unit/createMessage/";

    private static final String SESSION_MODEL_FILENAME = "semanticModel.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "semanticModel.interactions";

    private static final String TYPES_MODEL_FILENAME = "semanticModelTypes.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + getProjectName() + "/" + SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + TYPES_MODEL_FILENAME, "/" + getProjectName() + "/" + TYPES_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME, "/" + getProjectName() + "/" + SESSION_MODEL_FILENAME);
    }

    /**
     * Test that export of sequence diagram of a closed session is OK.
     */
    public void testExportDiagramFromCloseSession() {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        try {
            int nbFilesBeforeExport = project.members().length;
            // Launch export from the aird file
            SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
            SWTBotTreeItem representationsFile = tree.expandNode(getProjectName()).select(SESSION_MODEL_FILENAME);
            SWTBotUtils.clickContextMenu(representationsFile, Messages.ExportRepresentationsAction_label);
            SWTBot exportBot = SWTBotSiriusHelper.getShellBot(Messages.ExportSeveralRepresentationsAsImagesDialog_dialogTitle);
            exportBot.button("OK").click();
            SWTBotUtils.waitAllUiEvents();
            // Ensure that one image file has been created.
            project.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
            int nbFilesAfterExport = project.members().length;
            assertEquals("Wrong number of images generated.", 1, nbFilesAfterExport - nbFilesBeforeExport);
        } catch (CoreException e) {
            fail("Problem during accessing project: " + e.getLocalizedMessage());
        }
        // Potential errors in Error Log view are automatically detected by
        // superClass.
    }
}
