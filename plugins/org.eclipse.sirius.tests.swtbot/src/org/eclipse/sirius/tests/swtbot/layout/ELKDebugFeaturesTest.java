/*******************************************************************************
 * Copyright (c) 2024 Obeo.
 * All rights reserved.
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.layout;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.elk.DiagramElkPlugin;
import org.eclipse.sirius.diagram.elk.ElkDiagramLayoutTracer;
import org.eclipse.sirius.diagram.elk.debug.Messages;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * Tests to verify the debug capabilities offered by the Sirius ELK bridge.
 * 
 * @author Laurent Redor
 * 
 */
public class ELKDebugFeaturesTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/layout/withELK/";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "SimpleDiagram";

    private static final String REPRESENTATION_FOR_DIAGRAM_NAME = "simpleDiagram";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();
        copyFileToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        editor = null;
        super.tearDown();
    }

    /**
     * Check that the export ELKG action creates an elkg file.
     */
    public void testExportElkgFile() {
        testExportAction("Export diagram as ELK Graph", ".elkg");
    }

    /**
     * Check that the export as text action creates an elkt file.
     */
    public void testExportElktFile() {
        testExportAction("Export diagram as ELK Text", ".elkt");
    }

    /**
     * Check that the export as text action creates an elkt file in the expected folder (set with system property
     * {@value ElkDiagramLayoutTracer#TARGET_FOLDER_PATH_SYSTEM_PROPERTY_KEY}).
     */
    public void testExportElktFileWithSpecificFolder() {
        String previousValue = System.getProperty(ElkDiagramLayoutTracer.TARGET_FOLDER_PATH_SYSTEM_PROPERTY_KEY);
        try {
            System.setProperty(ElkDiagramLayoutTracer.TARGET_FOLDER_PATH_SYSTEM_PROPERTY_KEY, "${project_loc}/exportELK");
            IProject targetProject = ResourcesPlugin.getWorkspace().getRoot().getProject(designerProject.getName());
            testExportAction("Export diagram as ELK Text", ".elkt", Path.of(targetProject.getLocation().toOSString()).resolve("exportELK"));
        } finally {
            if (previousValue == null) {
                System.clearProperty(ElkDiagramLayoutTracer.TARGET_FOLDER_PATH_SYSTEM_PROPERTY_KEY);
            } else {
                System.setProperty(ElkDiagramLayoutTracer.TARGET_FOLDER_PATH_SYSTEM_PROPERTY_KEY, previousValue);
            }
            forceDisposeTracer();
        }
    }

    public void testExportDuringArrangeAll_withoutDebugEnabled() {
        testExportDuringArrangeAll(false, false);
    }

    public void testExportDuringArrangeAll_withDebugEnabledOnElkt() {
        testExportDuringArrangeAll(true, false);
    }

    public void testExportDuringArrangeAll_withDebugEnabledOnElkg() {
        testExportDuringArrangeAll(true, true);
    }

    protected void testExportDuringArrangeAll(boolean debug, boolean exportAsElkg) {
        String expectedExtension = exportAsElkg ? ".elkg" : ".elkt";

        openDiagram(REPRESENTATION_FOR_DIAGRAM_NAME);

        if (debug) {
            // Enable the debugging of diagram.elk plugin
            DiagramElkPlugin.getPlugin().setDebugging(true);
            // It is not possible to change the options so use reflection instead
            if (exportAsElkg) {
                ReflectionHelper.setFieldValueWithoutException(DiagramElkPlugin.getPlugin().getTracer(), "exportAsText", false);
                ReflectionHelper.setFieldValueWithoutException(DiagramElkPlugin.getPlugin().getTracer(), "exportAsXmi", true);
            } else {
                ReflectionHelper.setFieldValueWithoutException(DiagramElkPlugin.getPlugin().getTracer(), "exportAsText", true);
            }
        }
        try {
            // Delete already existing files (from previous tests for example)
            Arrays.stream(getJavaTemporaryFolder().toFile().listFiles((f, p) -> p.endsWith(expectedExtension))).forEach(File::delete);

            // Launch an arrange all
            arrangeAll();

            int expectedNbFiles = debug ? 6 : 0;
            assertEquals("Wrong number of \"" + expectedExtension + "\" files exported during the arrange all in \"" + getJavaTemporaryFolder().toString() + "\".", expectedNbFiles,
                    findFiles(getJavaTemporaryFolder(), expectedExtension).size());
        } catch (IOException e) {
            fail("Impossible to get the \"" + expectedExtension + "\" files exported during the arrange all in \"" + getJavaTemporaryFolder().toString() + "\": " + e.getMessage());
        } finally {
            DiagramElkPlugin.getPlugin().setDebugging(false);
            forceDisposeTracer();
        }
    }


    /**
     * Force the tracer to be recreated at the next call to "DiagramElkPlugin.getPlugin().getTracer()" and so by
     * considering the new property system values.
     */
    private void forceDisposeTracer() {
        ReflectionHelper.setFieldValueWithoutException(DiagramElkPlugin.getPlugin(), "tracer", null);
    }

    /**
     * Check that export action creates a file with the expected extension.
     */
    protected void testExportAction(String actionLabel, String expectedExtension) {
        testExportAction(actionLabel, expectedExtension, getJavaTemporaryFolder());
    }

    /**
     * Check that export action creates a file with the expected extension.
     */
    protected void testExportAction(String actionLabel, String expectedExtension, Path targetFolderPath) {
        openDiagram(REPRESENTATION_FOR_DIAGRAM_NAME);

        editor.clickContextMenu(actionLabel);
        SWTBotUtils.waitAllUiEvents();

        // Wait the message dialog and close it.
        bot.waitUntilWidgetAppears(Conditions.shellIsActive(Messages.ExportToElkGraphHandler_elkExportDialogTitle));
        SWTBotShell wizard = bot.shell(Messages.ExportToElkGraphHandler_elkExportDialogTitle);
        wizard.setFocus();
        SWTBot shellBot = new SWTBot(wizard.widget);
        SWTBotButton button = shellBot.button("OK");
        shellBot.waitUntil(new ItemEnabledCondition(button));
        button.click();
        SWTBotUtils.waitAllUiEvents();

        Path exportedFilePath = targetFolderPath.resolve(REPRESENTATION_FOR_DIAGRAM_NAME + expectedExtension);
        assertTrue("A file with name \"" + REPRESENTATION_FOR_DIAGRAM_NAME + expectedExtension + "\" must be created in \"" + targetFolderPath.toString() + "\".", exportedFilePath.toFile().exists());
    }

    private Path getJavaTemporaryFolder() {
        String path = System.getProperty("java.io.tmpdir");
        return Path.of(path);
    }

    private void openDiagram(String representationName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();
    }

    private static List<String> findFiles(Path path, String fileExtension) throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<String> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk.filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }
        return result;
    }
}
