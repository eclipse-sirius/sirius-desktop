/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMVersionSAXParser;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.osgi.framework.Version;

/**
 * Tests that Sirius Specification Project is created properly.
 * 
 * @author mporhel
 */
public class ViewpointSpecificationProjectCreationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VSP_SHOULD_CONTAIN = "The created VSM project should contain ";

    private static final String VSM_PROJECT_NAME = "org.eclipse.sirius.test.design";

    private static final String VSM = "test.odesign";

    private static final String VIEWPOINT_NAME = "MyViewpoint";

    private static final String JAVA_EXTENSION_QUALIFIED_NAME = VSM_PROJECT_NAME + ".Services";

    private static final String WIZARD_FILE = "File";

    private static final String WIZARD_FINISH = "Finish";

    private static final String WIZARD_NEXT = "Next >";

    private static final String WIZARD_NEW = "New";

    private static final String WIZARD_CANCEL = "Cancel";

    private static final String WIZARD_PROJECT_NAME = "Project name:";

    private static final String WIZARD_VIEWPOINT_SPECIFICATION_MODEL_NAME = "Viewpoint Specification Model name:";

    private static final String WIZARD_VIEWPOINT_SPECIFICATION_PROJECT = "Viewpoint Specification Project";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        designerPerspectives.openSiriusPerspective();
    }

    /**
     * Check that the VSM project is properly created.
     * @throws IOException 
     */
    public void test_ViewpointViewpoint_Specification_Project_Creation() throws IOException {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        IProject project = createViewpointSpecificationProject(bot, VSM_PROJECT_NAME, VSM);

        bot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {
                IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(VSM_PROJECT_NAME);
                return project != null && project.exists() && project.isOpen();
            }

            public String getFailureMessage() {
                return "A project named " + VSM_PROJECT_NAME + " should be created";
            }
        });
        SWTBotUtils.waitAllUiEvents();

        // Get project
        assertTrue("The created VSM project should exist.", project.exists());
        assertTrue("The created VSM project should be open.", project.isOpen());

        checkNatures(project);

        // Check the created folders
        assertNotNull(VSP_SHOULD_CONTAIN + "a folder named src.", project.getFolder("src"));
        assertNotNull(VSP_SHOULD_CONTAIN + "a folder named description.", project.getFolder("description"));
        assertNotNull(VSP_SHOULD_CONTAIN + "a folder named META-INF", project.getFolder("META-INF"));

        // Check the created files
        IFile vsm = project.getFile("description/" + VSM);
        assertNotNull(VSP_SHOULD_CONTAIN + "an odesign in the description folder. The odesign name should be " + VSM, vsm);
        assertNotNull(VSP_SHOULD_CONTAIN + "a plugin.xml file." + VSM, project.getFile("plugin.xml"));
        assertNotNull(VSP_SHOULD_CONTAIN + "a build.properties file." + VSM, project.getFile("build.properties"));
        assertNotNull(VSP_SHOULD_CONTAIN + "a plugin.properties file." + VSM, project.getFile("plugin.properties"));
        assertNotNull(VSP_SHOULD_CONTAIN + ".classpath file.", project.getFile(".classpath"));
        assertNotNull(VSP_SHOULD_CONTAIN + ".project file.", project.getFile(".project"));
        assertNotNull(VSP_SHOULD_CONTAIN + "MANIFEST.MF file.", project.getFile("META-INF/MANIFEST.MF"));
        assertNotNull(VSP_SHOULD_CONTAIN + "Services.java file.", project.getFile("src/" + project.getName() + "/Services.java"));
        
        // Check that the created odesign does not need migration (version tag
        // must be initialized)
        URI createPlatformResourceURI = URI.createPlatformResourceURI(vsm.getFullPath().toOSString());
        VSMVersionSAXParser parser = new VSMVersionSAXParser(createPlatformResourceURI);
        String loadedVersion = parser.getVersion(new NullProgressMonitor());
        assertNotNull("The VSM Group version must be initialized at creation.", loadedVersion);
        assertFalse("The migration must be required on just created VSM.",
                VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(loadedVersion)));
        // Check that the created odesign contains a default viewpoint with one java extension.
        ResourceSet set = new ResourceSetImpl();
        Group modeler = (Group) ModelUtils.load(createPlatformResourceURI, set);
        EList<Viewpoint> ownedViewpoints = modeler.getOwnedViewpoints();
        assertTrue("The VSM Group must contains a default viewpoint.", ownedViewpoints.size() == 1);
        Viewpoint viewpoint = ownedViewpoints.get(0);
        assertTrue("The VSM Group must contains a default viewpoint named " + VIEWPOINT_NAME + ".",
                VIEWPOINT_NAME.equals(viewpoint.getName()));
        EList<JavaExtension> ownedJavaExtensions = viewpoint.getOwnedJavaExtensions();
        assertTrue("The default viewpoint of the VSM should contain a default java extension.",
                ownedJavaExtensions.size() == 1);
        JavaExtension javaExtension = ownedJavaExtensions.get(0);
        assertTrue("The default Java extension name in Viewpoint should be " + JAVA_EXTENSION_QUALIFIED_NAME + ".",
                JAVA_EXTENSION_QUALIFIED_NAME.equals(javaExtension.getQualifiedClassName()));
        // delete project
        closeAllEditors();
        try {
            project.delete(true, new NullProgressMonitor());
        } catch (CoreException e) {
            fail("Cannot delete the VSM Project");
        }

    }

    /**
     * Ensure that it is not possible to create a modeling project when another
     * existing project has the same name in different case on Windows and Mac.
     */
    public void testCreatingProjectWithExistingName() {
        if (isMacOrWindowsOS()) {
            // Create a VSP
            IProject project = createViewpointSpecificationProject(bot, VSM_PROJECT_NAME, VSM);
            assertTrue("The created VSM project should exist.", ResourcesPlugin.getWorkspace().getRoot().getProject(VSM_PROJECT_NAME).exists());
            assertTrue("The created VSM project should be open.", ResourcesPlugin.getWorkspace().getRoot().getProject(VSM_PROJECT_NAME).isOpen());

            // Try to create an other project with the same existing name in
            // different case into the workspace and check the error message
            tryToCreateProjectWithExistingProjectName("A project with " + VSM_PROJECT_NAME + " name should exist in workspace and the Next button should not be enabled");

            // Delete the project only from workspace
            closeAllEditors();
            try {
                project.delete(false, true, new NullProgressMonitor());
            } catch (CoreException e) {
                fail("Cannot delete the VSM Project");
            }
            SWTBotUtils.waitAllUiEvents();

            // Try to create an other project with the same existing name in
            // different case on disk and check the error message
            tryToCreateProjectWithExistingProjectName("A project with " + VSM_PROJECT_NAME + " name should exist on the disk and the Next button should not be enabled");
        }
    }

    private void tryToCreateProjectWithExistingProjectName(String msgIfCreationIsPossible) {
        bot.viewById(IModelExplorerView.ID).setFocus();
        bot.menu(WIZARD_FILE).menu(WIZARD_NEW).menu(WIZARD_VIEWPOINT_SPECIFICATION_PROJECT).click();
        bot.waitUntilWidgetAppears(Conditions.shellIsActive(WIZARD_NEW + " " + WIZARD_VIEWPOINT_SPECIFICATION_PROJECT));
        bot.textWithLabel(WIZARD_PROJECT_NAME).setText(VSM_PROJECT_NAME.toUpperCase());
        assertEquals(msgIfCreationIsPossible, false, bot.button(WIZARD_NEXT).isEnabled());
        bot.button(WIZARD_CANCEL).click();
    }

    private void checkNatures(IProject project) {
        // Check the natures
        IProjectNature nature = null;
        try {
            nature = project.getNature("org.eclipse.acceleo.ide.ui.acceleoNature");
        } catch (CoreException e1) {
            fail("Cannot get the acceleo nature.");
        }
        assertNull("The VSM project should not have the Acceleo nature.", nature);

        nature = null;
        try {
            nature = project.getNature("org.eclipse.pde.PluginNature");
        } catch (CoreException e1) {
            fail("Cannot get the plugin nature.");
        }
        assertNotNull("The VSM project should have a Plugin nature.", nature);

        nature = null;
        try {
            nature = project.getNature("org.eclipse.jdt.core.javanature");
        } catch (CoreException e1) {
            fail("Cannot get the java nature.");
        }
        assertNotNull("The VSM project should be a Java Project.", nature);
    }


    /**
     * Create a Sirius Specification Project and wait until the creation is
     * done.
     * 
     * @param bot
     *            the editor
     * 
     * @param vsmProjectName
     *            VSP name
     * @param vsmFileName
     *            VSM file name
     * 
     * @return the created project.
     */
    public static IProject createViewpointSpecificationProject(SWTGefBot bot, final String vsmProjectName, String vsmFileName) {
        bot.menu(WIZARD_FILE).menu(WIZARD_NEW).menu(WIZARD_VIEWPOINT_SPECIFICATION_PROJECT).click();
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot(WIZARD_NEW + " " + WIZARD_VIEWPOINT_SPECIFICATION_PROJECT);

        wizardBot.textWithLabel(WIZARD_PROJECT_NAME).setText(vsmProjectName);

        SWTBotButton nextButton = wizardBot.button(WIZARD_NEXT);
        wizardBot.waitUntil(new ItemEnabledCondition(nextButton));
        nextButton.click();

        // Check that the initialized name of the odesign corresponds to the
        // name of the project
        assertEquals("Initial model name should be the name of the project.", "test.odesign", wizardBot.textWithLabel(WIZARD_VIEWPOINT_SPECIFICATION_MODEL_NAME).getText());

        wizardBot.textWithLabel(WIZARD_VIEWPOINT_SPECIFICATION_MODEL_NAME).setText("test.design");
        assertFalse("The wizard should not accept other file extension than odesign.", wizardBot.button("Finish").isEnabled());

        wizardBot.textWithLabel(WIZARD_VIEWPOINT_SPECIFICATION_MODEL_NAME).setText(vsmFileName);
        SWTBotButton finishButton = wizardBot.button(WIZARD_FINISH);
        wizardBot.waitUntil(new ItemEnabledCondition(finishButton));
        finishButton.click();

        SWTBotUtils.waitAllUiEvents();

        bot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {
                IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(VSM_PROJECT_NAME);
                return project != null && project.exists() && project.isOpen();
            }

            public String getFailureMessage() {
                return "A project named " + vsmProjectName + " should be created";
            }
        });

        return ResourcesPlugin.getWorkspace().getRoot().getProject(vsmProjectName);
    }

    private static boolean isMacOrWindowsOS() {
        return System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("Mac");
    }

}
