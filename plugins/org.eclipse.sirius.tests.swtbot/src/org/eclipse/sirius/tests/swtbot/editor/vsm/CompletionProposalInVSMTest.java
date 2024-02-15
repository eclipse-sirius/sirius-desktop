/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistry;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
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
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

import com.google.common.base.Joiner;

/**
 * Tests completion in MTL interpreted expression and domain class property section.
 * 
 * @author mporhel
 */
public class CompletionProposalInVSMTest extends AbstractContentAssistTest {

    private static final String ACCELEO_MTL_INTERPRETER_TEST_MODULE2_MTL = "AcceleoMtlInterpreterTestModule2.mtl";

    private static final String ACCELEO_MTL_INTERPRETER_TEST_MODULE_MTL = "AcceleoMtlInterpreterTestModule.mtl";

    private static final String BASIC_SERVICE_JAVA = "BasicService.java";

    private static final String SERVICE_WITH_DEPENDENCIES_JAVA = "ServicesWithDependencies.java";

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
        platformProblemsListener.setErrorCatchActive(false);

        // Load the target platform, if not already done, to allow compilation
        // of the java services
        TestsUtil.setTargetPlatform(Activator.PLUGIN_ID);
    }

    /**
     * Initialize the context by copying new resources and waiting all build process. This method is unreliable...
     * 
     * @exception InterruptedException
     *                if this thread is interrupted while waiting
     * @exception OperationCanceledException
     *                if the progress monitor is canceled while waiting
     * @exception CoreException
     *                In case of problem during setting workspace description to disable auto build.
     * @throws CommandException
     */
    private void initContext(List<String> nodes) throws InterruptedException, OperationCanceledException, CoreException, CommandException {
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
        // Explicitly add the Acceleo nature, as this is no longer done by
        // default
        addAcceleoNature(ResourcesPlugin.getWorkspace().getRoot().getProject(VSM_PROJECT_NAME));
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
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + SERVICE_WITH_DEPENDENCIES_JAVA, dest + SERVICE_WITH_DEPENDENCIES_JAVA);

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
        SWTBotTreeItem lastExpandNode = bot.activeEditor().bot().tree().expandNode("platform:/resource/" + VSM_PROJECT_NAME + "/description/" + VSM);
        for (String node : nodes) {
            lastExpandNode = lastExpandNode.expandNode(node);
        }
        lastExpandNode.select();

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("General", propertiesBot.bot());
    }

    private void addAcceleoNature(IProject projet) throws CommandException {
        ICommandService commandService = PlatformUI.getWorkbench().getService(ICommandService.class);
        IHandlerService handlerService = PlatformUI.getWorkbench().getService(IHandlerService.class);
        Command addAcceleoNatureCommand = commandService.getCommand("org.eclipse.sirius.common.acceleo.mtl.ide.internal.convert"); //$NON-NLS-1$

        // If the acceleo interpreter is not present, do not configure.
        // the acceleo conversion command is not API yet, so, it
        // is declared by the org.eclipse.sirius.common.acceleo.mtl.ide plugin,
        // to
        // avoid dependencies from viewpoint.ui to Acceleo.
        if (addAcceleoNatureCommand != null && addAcceleoNatureCommand.isDefined()) {
            // Force org.eclipse.sirius.common.acceleo.mtl.ide plugin
            // initialization.
            ProposalProviderRegistry.getAllProviders();
            ParameterizedCommand parmCommand = new ParameterizedCommand(addAcceleoNatureCommand, null);
            EvaluationContext evaluationContext = new EvaluationContext(null, Collections.singletonList(projet));
            handlerService.executeCommandInContext(parmCommand, null, evaluationContext);
        }
    }

    /**
     * There is problem on linux with this test so we are waiting build or refresh jobs by joining them.
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
     * @exception Exception
     *                In case of problem during context initialization.
     */
    public void test_Java_Service_Completion() throws Exception {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        initContext(Arrays.asList("test", "VP", "Diag"));

        // Set the domain class
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();

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
     * Check that the right Java service appears in the completion proposals with Acceleo interpreter.
     * 
     * Warning: To ensure the non regression of the associated bug 500253, the manifest must not have a direct reference
     * to org.eclipse.emf.ecore.
     * 
     * @exception Exception
     *                In case of problem during context initialization.
     */
    public void test_Java_Service_Completion_By_Acceleo() throws Exception {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        initContext(Arrays.asList("test", "VP", "Diag", "Default", "C1"));

        // Set the domain class
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();

        // Init the Precondition Expression
        SWTBotText precondition = propertiesBot.bot().text(3);
        precondition.setFocus();
        precondition.setText("[self./]");

        // Get proposals
        Collection<String> contentAssistProposal = getContentAssistProposal(precondition, 6);
        assertTrue("Proposals should be present.", contentAssistProposal.contains("testInteractionsService() : CallMessage"));
        assertTrue("Proposals on EClass should contain service for EClass.", contentAssistProposal.contains("sampleService() : String"));
        assertTrue("Proposals on EClass should contain service for EClass.", contentAssistProposal.contains("sampleServiceOnEClass() : String"));

    }

    /**
     * Check that the right Java service appears in the completion proposals with service keyword used.
     * 
     * Warning: To ensure the non regression of the associated bug 500253, the manifest must not have a direct reference
     * to org.eclipse.emf.ecore.
     * 
     * @exception Exception
     *                In case of problem during context initialization.
     */
    public void test_Java_Service_Completion_By_Service_Keyword() throws Exception {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        initContext(Arrays.asList("test", "VP", "Diag", "Default", "C1"));

        // Set the domain class
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();

        // Init the Precondition Expression
        SWTBotText precondition = propertiesBot.bot().text(3);
        precondition.setFocus();
        precondition.setText("service:");

        // Get proposals
        Collection<String> contentAssistProposal = getContentAssistProposal(precondition, 8);
        assertTrue("Proposals should be present.", contentAssistProposal.contains("testInteractionsService()"));
        assertTrue("Proposals on EClass should contain service for EClass.", contentAssistProposal.contains("sampleService()"));
        assertTrue("Proposals on EClass should contain service for EClass.", contentAssistProposal.contains("sampleServiceOnEClass()"));

    }

    /**
     * Check that the right Java service appears in the completion proposals with AQL interpreter.
     * 
     * Warning: To ensure the non regression of the associated bug 500253, the manifest must not have a direct reference
     * to org.eclipse.emf.ecore.
     * 
     * @exception Exception
     *                In case of problem during context initialization.
     */
    public void test_Java_Service_Completion_By_AQL() throws Exception {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        // initContext(Arrays.asList("test", "VP", "Diag", "Default",
        // "C1"));
        initContext(Arrays.asList("test", "VP", "Diag", "Default", "C1"));

        // Set the domain class
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();

        // Init the Precondition Expression
        SWTBotText precondition = propertiesBot.bot().text(3);
        precondition.setFocus();
        precondition.setText("aql:self.");

        // Get proposals
        Collection<String> contentAssistProposal = getContentAssistProposal(precondition, 9);
        assertTrue("Proposals should be present.", contentAssistProposal.contains("testInteractionsService()"));
        assertTrue("Proposals on EClass should contain service for EClass.", contentAssistProposal.contains("sampleService()"));
        assertTrue("Proposals on EClass should contain service for EClass.", contentAssistProposal.contains("sampleServiceOnEClass()"));

    }

    /**
     * Check that the right MTL services appear in the completion proposals.
     * 
     * @exception Exception
     *                In case of problem during context initialization.
     */
    public void test_MTL_Service_Completion() throws Exception {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        initContext(Arrays.asList("test", "VP", "Diag"));

        // Set the domain class
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();

        // Init the Precondition Expression
        SWTBotText precondition = propertiesBot.bot().text(3);
        precondition.setFocus();
        precondition.setText("[thisEObject./]");

        // Get proposals
        Collection<String> contentAssistProposal = getContentAssistProposal(precondition, 13);

        // Expected mtl queries.
        Collection<String> mtlServices = Arrays.asList("getName() : String", "getNameQuery() : String", "getImportedName() : String", "getImportedQueryName() : String", "isAbstractQuery() : Boolean",
                "eContentsQuery() : Collection(EObject)", "selfQuery() : EObject", "selfImportedQuery() : EObject");

        Collection<String> unfoundServices = new ArrayList<>();
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
     * @exception Exception
     *                In case of problem during context initialization.
     */
    public void test_Domain_Class_Completion_With_No_Selected_MetaModels() throws Exception {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        initContext(Arrays.asList("test", "VP", "Diag"));

        // Get proposals for domain class.
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();

        // Get proposals
        Collection<String> contentAssistProposal = getContentAssistProposal(domainClass, 0);

        assertTrue("Type proposal should contain types from registered meta models.", contentAssistProposal.contains("ecore::EClass"));
        assertTrue("Type proposal should contain types from registered meta models.", contentAssistProposal.contains("docbook::Book"));
        assertTrue("Type proposal should contain types from registered meta models.", contentAssistProposal.contains("description::DiagramDescription"));
        assertTrue("Type proposal should contain types from registered meta models.", contentAssistProposal.contains("diagram::DNode"));
        assertFalse("Proposal should not contain viewpoint.DNode a it has been moved to the diagram package.", contentAssistProposal.contains("viewpoint::DNode"));
        assertTrue("Type proposal should contain types from meta models in workspace.", contentAssistProposal.contains("test::Test"));

    }

    /**
     * Check that type completion with selected MM is consistent.
     * 
     * @exception Exception
     *                In case of problem during context initialization.
     */
    public void test_Domain_Class_Completion_With_Selected_MetaModels() throws Exception {
        initContext(Arrays.asList("test", "VP", "Diag"));

        // select Sirius metamodels
        Collection<String> expectedMetamodels = Arrays.asList(DescriptionPackage.eNS_URI, DiagramPackage.eNS_URI, ViewpointPackage.eNS_URI, InteractionsPackage.eNS_URI);
        selectSiriusMetaModels(expectedMetamodels);

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("General", propertiesBot.bot());
        SWTBotText domainClass = propertiesBot.bot().text(2);
        domainClass.setFocus();

        // Get proposals
        Collection<String> contentAssistProposal = getContentAssistProposal(domainClass, 0);

        assertFalse("Proposal should only contain types from selected meta models: ecore::EClass found", contentAssistProposal.contains("ecore::EClass"));
        assertFalse("Proposal should only contain types from selected meta models: docBook::Book found", contentAssistProposal.contains("docBook::Book"));
        assertFalse("Proposal should only contain types from selected meta models: test::Test found", contentAssistProposal.contains("test::Test"));

        assertTrue("Proposal should contain types from selected meta models. DiagramDescription not found", contentAssistProposal.contains("description::DiagramDescription"));
        assertTrue("Proposal should contain types from selected meta models. DNode not found", contentAssistProposal.contains("diagram::DNode"));
        assertFalse("Proposal should not contain viewpoint.DNode a it has been moved to the diagram package.", contentAssistProposal.contains("viewpoint::DNode"));
    }

    private void selectSiriusMetaModels(final Collection<String> expectedNsURIs) throws InterruptedException {
        // Select a meta model : Sirius
        SWTBotView propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Metamodels", propertiesBot.bot());

        SWTBotTable metamodelsTableBot = propertiesBot.bot().table();
        SWTBotButton addFromRegistryButtonBot = propertiesBot.bot().button("Add from registry");
        addFromRegistryButtonBot.click();

        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Metamodel selection");
        wizardBot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                for (String nsURI : expectedNsURIs) {
                    if (wizardBot.table().indexOf(nsURI) == -1) {
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
        wizardBot.text().setText("*sirius*");
        SWTBotTable metamodelsFromRegistryTableBot = wizardBot.table();

        int nbOfMetamodelsFromRegistry = metamodelsFromRegistryTableBot.rowCount();
        int[] selection = new int[nbOfMetamodelsFromRegistry];
        for (int i = 0; i < nbOfMetamodelsFromRegistry; i++) {
            selection[i] = i;
        }
        metamodelsFromRegistryTableBot.select(selection);
        wizardBot.waitUntil(new TableSelectionCondition(metamodelsFromRegistryTableBot, nbOfMetamodelsFromRegistry));

        wizardBot.button("OK").click();
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
        @Override
        public boolean test() throws Exception {
            return expectedSelectionCound == swtBotTable.selectionCount();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getFailureMessage() {
            return "The selection count is not the expected one, expected (" + expectedSelectionCound + "), " + swtBotTable.selectionCount();
        }

    }
}
