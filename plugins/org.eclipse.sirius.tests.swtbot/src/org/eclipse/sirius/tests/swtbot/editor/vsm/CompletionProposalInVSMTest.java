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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import java.util.Collection;

import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * Tests completion in MTL interpreted expression and domain class property
 * section.
 * 
 * @author mporhel
 */
public class CompletionProposalInVSMTest extends AbstractContentAssistTest {

    private static final String ACCELEO_MTL_INTERPRETER_TEST_MODULE2_MTL = "AcceleoMtlInterpreterTestModule2.mtl";

    private static final String ACCELEO_MTL_INTERPRETER_TEST_MODULE_MTL = "AcceleoMtlInterpreterTestModule.mtl";

    private static final String BASIC_SERVICE_JAVA = "BasicService.java";

    private static final String PATH = "data/unit/editor/vsm/completion/";

    private static final String VSM_PROJECT_NAME = "org.eclipse.sirius.test.design";

    private static final String VSM = "test.odesign";

    private boolean previousAutoBuildValue = true;

    private SWTBotView propertiesBot;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();

        previousAutoBuildValue = ResourcesPlugin.getWorkspace().getDescription().isAutoBuilding();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
    }

    /**
     * Initialize the context by copying new resources and waiting all build
     * process. This method is unreliable...
     * 
     * @exception InterruptedException
     *                if this thread is interrupted while waiting
     * @exception OperationCanceledException
     *                if the progress monitor is canceled while waiting
     * @exception CoreException
     *                In case of problem during setting workspace description to
     *                disable auto build.
     */
    private void initContext() throws InterruptedException, OperationCanceledException, CoreException {
        designerPerspectives.openSiriusPerspective();
        // Wait the end of the current build and/or refresh.
        waitJobsBuildOrRefresh();
        // Disable auto-build for new projects.
        final IWorkspaceDescription description = ResourcesPlugin.getWorkspace().getDescription();
        description.setAutoBuilding(false);
        ResourcesPlugin.getWorkspace().setDescription(description);
        // Wait the end of the current build and/or refresh.
        waitJobsBuildOrRefresh();

        // Copy the sample ecore model for type completion
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, "test.ecore");

        // Create VSM Project.
        ViewpointSpecificationProjectCreationTest.createViewpointSpecificationProject(bot, VSM_PROJECT_NAME, VSM);
        waitJobsBuildOrRefresh();
        // Define the imports in the VSM and add Ecore dependency
        String MANIFEST_MF = "MANIFEST.MF";
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + MANIFEST_MF, VSM_PROJECT_NAME + "/META-INF/" + MANIFEST_MF);
        // Wait the end of the current build and/or refresh.
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + VSM, VSM_PROJECT_NAME + "/description/" + VSM);

        // Copy the Java Service file
        char pathSeparator = '/';
        String package_name = VSM_PROJECT_NAME;
        package_name = package_name.replace('.', pathSeparator);
        String dest = VSM_PROJECT_NAME + "/src/" + package_name + pathSeparator;
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + BASIC_SERVICE_JAVA, dest + BASIC_SERVICE_JAVA);

        // Copy the mtl files
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + ACCELEO_MTL_INTERPRETER_TEST_MODULE_MTL, dest + ACCELEO_MTL_INTERPRETER_TEST_MODULE_MTL);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + ACCELEO_MTL_INTERPRETER_TEST_MODULE2_MTL, dest + ACCELEO_MTL_INTERPRETER_TEST_MODULE2_MTL);

        // Wait the end of the current build and/or refresh.
        waitJobsBuildOrRefresh();

        // Launch a manual build and wait the end of the workspace build
        ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
        // Wait the end of the current build and/or refresh.
        waitJobsBuildOrRefresh();
        
        bot.activeEditor().setFocus();
        bot.activeEditor().bot().tree().expandNode("platform:/resource/" + VSM_PROJECT_NAME + "/description/" + VSM, "test", "VP", "Diag").select();

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("General");
    }

    /**
     * There is problem on linux with this test so we are waiting build or
     * refresh jobs by joining them.
     */
    private void waitJobsBuildOrRefresh() throws InterruptedException, OperationCanceledException {
        Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_REFRESH, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_REFRESH, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, new NullProgressMonitor());
    }

    /**
     * Check that the right Java service appears in the completion proposals.
     * 
     * @exception InterruptedException
     *                In case of problem during context initialization.
     * @exception OperationCanceledException
     *                In case of problem during context initialization.
     * @exception CoreException
     *                In case of problem during context initialization.
     */
    public void test_Java_Service_Completion() throws InterruptedException, OperationCanceledException, CoreException {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        initContext();

        // Set the domain class
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();
        domainClass.setText("EClass");

        // Init the Precondition Expression
        SWTBotText precondition = propertiesBot.bot().text(3);
        precondition.setFocus();
        precondition.setText("[thisEObject./]");

        // Get proposals
        Collection<String> contentAssistProposal = getContentAssistProposal(precondition, 13);
        assertTrue("Proposals on EClass should contain service for EClass.", contentAssistProposal.contains("sampleService() : String"));
        assertTrue("Proposals on EClass should contain service for EClass.", contentAssistProposal.contains("sampleServiceOnEClass() : String"));
        assertFalse("Proposals on EClass should not contain services for EPackage.", contentAssistProposal.contains("sampleServiceOnEPackage() : String"));
    }

    /**
     * Check that the right MTL services appear in the completion proposals.
     * 
     * @exception InterruptedException
     *                In case of problem during context initialization.
     * @exception OperationCanceledException
     *                In case of problem during context initialization.
     * @exception CoreException
     *                In case of problem during context initialization.
     */
    public void test_MTL_Service_Completion() throws InterruptedException, OperationCanceledException, CoreException {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        initContext();

        // Set the domain class
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();
        domainClass.setText("EClass");

        // Init the Precondition Expression
        SWTBotText precondition = propertiesBot.bot().text(3);
        precondition.setFocus();
        precondition.setText("[thisEObject./]");

        // Get proposals
        Collection<String> contentAssistProposal = getContentAssistProposal(precondition, 13);

        // Expected mtl queries.
        Collection<String> mtlServices = Lists.newArrayList("getName() : String", "getNameQuery() : String", "getImportedName() : String", "getImportedQueryName() : String",
                "isAbstractQuery() : Boolean", "eContentsQuery() : Collection(EObject)", "selfQuery() : EObject", "selfImportedQuery() : EObject");

        Collection<String> unfoundServices = Lists.newArrayList();
        for (String mtlService : mtlServices) {
            if (!contentAssistProposal.contains(mtlService)) {
                unfoundServices.add(mtlService);
            }
        }

        assertTrue("Some mtl services defined in VSM are not present in proposals on EClass:" + unfoundServices.toString(), unfoundServices.isEmpty());
    }

    /**
     * Check that type completion with no selected MM is consistent.
     * 
     * @exception InterruptedException
     *                In case of problem during context initialization.
     * @exception OperationCanceledException
     *                In case of problem during context initialization.
     * @exception CoreException
     *                In case of problem during context initialization.
     */
    public void test_Domain_Class_Completion_With_No_Selected_MetaModels() throws InterruptedException, OperationCanceledException, CoreException {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        initContext();

        // Get proposals for domain class.
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();

        // Get proposals
        Collection<String> contentAssistProposal = getContentAssistProposal(domainClass, 0);

        assertTrue("Type proposal should contain types from registered meta models.", contentAssistProposal.contains("ecore.EClass"));
        assertTrue("Type proposal should contain types from registered meta models.", contentAssistProposal.contains("docbook.Book"));
        assertTrue("Type proposal should contain types from registered meta models.", contentAssistProposal.contains("description.DiagramDescription"));
        assertTrue("Type proposal should contain types from registered meta models.", contentAssistProposal.contains("diagram.DNode"));
        assertFalse("Proposal should not contain viewpoint.DNode a it has been moved to the diagram package.", contentAssistProposal.contains("viewpoint.DNode"));
        assertTrue("Type proposal should contain types from meta models in workspace.", contentAssistProposal.contains("test.Test"));

    }

    /**
     * Check that type completion with selected MM is consistent.
     * 
     * @exception InterruptedException
     *                In case of problem during context initialization.
     * @exception OperationCanceledException
     *                In case of problem during context initialization.
     * @exception CoreException
     *                In case of problem during context initialization.
     */
    public void test_Domain_Class_Completion_With_Selected_MetaModels() throws InterruptedException, OperationCanceledException, CoreException {
        initContext();

        // select Sirius metamodels
        Collection<String> expectedMetamodels = Lists.newArrayList(DescriptionPackage.eNS_URI, DiagramPackage.eNS_URI, ViewpointPackage.eNS_URI);
        selectSiriusMetaModels(expectedMetamodels);

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("General");
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();

        // Get proposals
        Collection<String> contentAssistProposal = getContentAssistProposal(domainClass, 0);

        assertFalse("Proposal should only contain types from selected meta models: ecore.EClass found", contentAssistProposal.contains("ecore.EClass"));
        assertFalse("Proposal should only contain types from selected meta models: docBook.Book found", contentAssistProposal.contains("docBook.Book"));
        assertFalse("Proposal should only contain types from selected meta models: test.Test found", contentAssistProposal.contains("test.Test"));

        
        assertTrue("Proposal should contain types from selected meta models. DiagramDescription not found", contentAssistProposal.contains("description.DiagramDescription"));
        assertTrue("Proposal should contain types from selected meta models. DNode not found", contentAssistProposal.contains("diagram.DNode"));
        assertFalse("Proposal should not contain viewpoint.DNode a it has been moved to the diagram package.", contentAssistProposal.contains("viewpoint.DNode"));
    }

    private void selectSiriusMetaModels(final Collection<String> expectedNsURIs) throws InterruptedException {
        // Select a meta model : Sirius
        SWTBotView propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Metamodels");

        SWTBotTable metamodelsTableBot = propertiesBot.bot().table();
        SWTBotButton addFromRegistryButtonBot = propertiesBot.bot().button("Add from registry");
        addFromRegistryButtonBot.click();

        final SWTBot metamodelsSelectionFromRegistryBot = bot.activeShell().bot();
        bot.waitUntil(Conditions.shellIsActive("Metamodel selection"));
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                for (String nsURI : expectedNsURIs) {
                    if (metamodelsSelectionFromRegistryBot.table().indexOf(nsURI) == -1) {
                        return false;
                    }
                }
                return true;
            }
            
            @Override
            public String getFailureMessage() {
                return "Some of the expected metamodels were not found: " + Joiner.on(", ").join(expectedNsURIs);
            }
        });
        metamodelsSelectionFromRegistryBot.text().setText("*sirius*");
        SWTBotTable metamodelsFromRegistryTableBot = metamodelsSelectionFromRegistryBot.table();

        int nbOfMetamodelsFromRegistry = metamodelsFromRegistryTableBot.rowCount();
        int[] selection = new int[nbOfMetamodelsFromRegistry];
        for (int i = 0; i < nbOfMetamodelsFromRegistry; i++) {
            selection[i] = i;
        }
        metamodelsFromRegistryTableBot.select(selection);
        bot.waitUntil(new TableSelectionCondition(metamodelsFromRegistryTableBot, nbOfMetamodelsFromRegistry));

        metamodelsSelectionFromRegistryBot.button("OK").click();
        bot.waitUntil(Conditions.tableHasRows(metamodelsTableBot, nbOfMetamodelsFromRegistry));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        try {
            closeAllEditors();

            super.tearDown();
            this.propertiesBot = null;
        } finally {
            final IWorkspaceDescription description = ResourcesPlugin.getWorkspace().getDescription();
            description.setAutoBuilding(previousAutoBuildValue);
            ResourcesPlugin.getWorkspace().setDescription(description);
        }
    }

    private class TableSelectionCondition extends DefaultCondition {

        private SWTBotTable swtBotTable;

        private int expectedSelectionCound;

        public TableSelectionCondition(SWTBotTable swtBotTable, int expectedSelectionCound) {
            this.swtBotTable = swtBotTable;
            this.expectedSelectionCound = expectedSelectionCound;
        }

        /**
         * {@inheritDoc}
         */
        public boolean test() throws Exception {
            return expectedSelectionCound == swtBotTable.selectionCount();
        }

        /**
         * {@inheritDoc}
         */
        public String getFailureMessage() {
            return "The selection count is not the expected one, expected (" + expectedSelectionCound + "), " + swtBotTable.selectionCount();
        }

    }
}
