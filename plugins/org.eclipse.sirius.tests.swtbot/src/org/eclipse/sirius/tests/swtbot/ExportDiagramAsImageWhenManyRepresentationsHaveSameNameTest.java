/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.FilenameFilter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.dialog.ExportAsImageHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.hamcrest.Matchers;

/**
 * Asserts that the exported image corresponds to the exported representation
 * contained by a session having many representations with the same name.
 * 
 * Test VP-2711
 * 
 * @author jdupont
 */
public class ExportDiagramAsImageWhenManyRepresentationsHaveSameNameTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/export/vp2711/";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String DATA_UNIT_DIR_FOR_TRANSIENT_SESSION = "data/unit/export/vp2711/sub/";

    private static final String FILE_DIR = "/";

    private static final String MODEL_FOR_TRANSIENT_SESSION = "Pkg1.ecore";

    private static final String MODEL_FOR_TRANSIENT_SESSION2 = "pk2.ecore";

    private static final String TRANSIENT_SESSION = "Pkg1.aird";

    private static final String TRANSIENT_SESSION2 = "pk2.aird";

    private static final String REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION = "package entities";

    private static final int SMALL_SIZE = 7500;

    private static final int MIDDLE_SIZE = 50000;

    private static final int BIG_SIZE = 150000;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        new ExportAsImageHelper().resetDialogSettings();
    }

    /**
     * Validate that that the exported image corresponds to the exported
     * representation when many representations have the same name in fragment
     * session. Check image size correspond to attempt.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfFragmentSessionAsJPGFromTabBar() throws Exception {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);

        // Copy all files necessary for test
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION,
                getProjectName() + "/" + "sub" + "/" + MODEL_FOR_TRANSIENT_SESSION);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION2,
                getProjectName() + "/" + "sub" + "/" + MODEL_FOR_TRANSIENT_SESSION2);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + TRANSIENT_SESSION, getProjectName() + "/" + "sub" + "/" + TRANSIENT_SESSION);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + TRANSIENT_SESSION2, getProjectName() + "/" + "sub" + "/" + TRANSIENT_SESSION2);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

        UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR + "sub" + FILE_DIR, TRANSIENT_SESSION2);
        openDiagram(sessionAirdResource);

        // Export a first representation with small size.
        exportAsImageFromEditorTabBar("JPG");
        valideExportResult("jpg", "sub", SMALL_SIZE, REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR + "sub" + FILE_DIR, TRANSIENT_SESSION);
        openDiagram(sessionAirdResource);

        // Export a second representation with big size.
        exportAsImageFromEditorTabBar("JPG");
        valideExportResult("jpg", "sub", BIG_SIZE, REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        openDiagram(sessionAirdResource);

        // Export a third representation with middle size.
        exportAsImageFromEditorTabBar("JPG");
        valideExportResult("jpg", "sub", MIDDLE_SIZE, REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);

        // Remove error log because errors in error log are normal.
        errors.clear();
    }

    private void exportAsImageFromEditorTabBar(String imageExtension) throws Exception {

        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        bot.activeEditor().bot().toolbarButtonWithTooltip("Export diagram as image").click();

        bot.waitUntil(Conditions.shellIsActive("Export representation as image file"));
        bot.comboBox(1).setSelection(imageExtension);
        bot.button("OK").click();
    }

    private void valideExportResult(final String imageExtension, String newFolderName, int imageSize, final String... expectedFileNames) throws Exception {
        File destinationFolder = new File(ResourcesPlugin.getWorkspace().getRoot().getProject(designerProject.getName()).getLocation().toOSString());
        if (newFolderName != null) {
            destinationFolder = new File(destinationFolder.getAbsolutePath() + File.separator + newFolderName);
        }
        // Check that files exists
        File filesWithExpectedExtension[] = destinationFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                boolean result = false;
                for (String filename : expectedFileNames) {
                    result = result || name.equals(filename + "." + imageExtension);
                }
                return result;
            }
        });
        assertEquals("Wrong number of file created with the \"" + imageExtension + "\" extension.", expectedFileNames.length, filesWithExpectedExtension.length);
        // Compare size of exported image.
        for (String filename : expectedFileNames) {
            IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(getProjectName() + FILE_DIR + filename + "." + imageExtension));
            File file = iFile.getLocation().toFile();
            assertThat(file.length(), Matchers.lessThan((long) imageSize));
        }
    }

    private void openDiagram(UIResource sessionAirdResource) {
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotTreeItem tree = localSession.getRootSessionTreeItem().expand().expandNode("Representations per category");
        tree.select("Design");
        SWTBotUtils.waitAllUiEvents();
        tree.expandNode("Design").expandNode("Entities").expandNode("package entities").doubleClick();
    }
}
