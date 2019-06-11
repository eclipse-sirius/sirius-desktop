/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.palette.PaletteToolEntry;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.SectionPaletteDrawer;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.SiriusPaletteViewer;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIProject;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;

import com.google.common.collect.Sets;

/**
 * Test class for diagram creation description.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class SiriusInternationalizationTest extends AbstractSiriusSwtBotGefTestCase {

    // Labels coming from the plugin_en.properties file
    private static final String TOOL_SELECTED_VALUES_LABEL_ENG = "This is the selected values!";

    private static final String TOOL_CHOICE_OF_VALUES_LABEL_ENG = "This is the choice of values!";

    private static final String TOOL_MESSAGE_LABEL_ENG = "This is the message!";

    private static final String TOOL_TITLE_LABEL_ENG = "This is the title!";

    private static final String TOOL_SECTION_LABEL_ENG = "Bugzilla 459993 Tools";

    private static final String EPACKAGE_CREATION_TOOL_LABEL_ENG = "Add EPackage";

    private static final String ECLASS_CREATION_TOOL_LABEL_ENG = "Add EClass";

    private static final String VIEWPOINT_LABEL_ENG = "Bugzilla 459993";

    private static final String DIAGRAM_DESCRIPTION_LABEL_ENG = "Bugzilla 459993 Diagram";

    private static final String TREE_DESCRIPTION_LABEL_ENG = "Bugzilla 459993 Tree";

    private static final String EDITION_TABLE_DESCRIPTION_LABEL_ENG = "Bugzilla 459993 Table";

    private static final String CROSS_TABLE_DESCRIPTION_LABEL_ENG = "Bugzilla 459993 Cross Table";

    private static final String POPUP_MENU_LABEL_ENG = "Bugzilla 459993 Popup menu";

    private static final String OPERATION_ACTION1_LABEL_ENG = "Bugzilla 459993 Operation 1";

    private static final String OPERATION_ACTION2_LABEL_ENG = "Bugzilla 459993 Operation 2";

    private static final String TOOL_FILTERS_LABEL_ENG = "This is a filter";

    private static final String TOOL_LAYERS_LABEL_ENG = "This is a layer";

    // Labels coming from the plugin_fr.properties file
    private static final String TOOL_SELECTED_VALUES_LABEL_FR = "Voici le choix des valeurs!";

    private static final String TOOL_CHOICE_OF_VALUES_LABEL_FR = "Voici les valeurs sélectionnées!";

    private static final String TOOL_MESSAGE_LABEL_FR = "Voici le message!";

    private static final String TOOL_TITLE_LABEL_FR = "Voici le titre!";

    private static final String TOOL_SECTION_LABEL_FR = "Outils Bugzilla 459993";

    private static final String EPACKAGE_CREATION_TOOL_LABEL_FR = "Ajouter EPackage";

    private static final String ECLASS_CREATION_TOOL_LABEL_FR = "Ajouter EClass";

    private static final String VIEWPOINT_LABEL_FR = "Bugzilla 459993 FR";

    private static final String DIAGRAM_DESCRIPTION_LABEL_FR = "Diagramme Bugzilla 459993";

    private static final String TREE_DESCRIPTION_LABEL_FR = "Arbre Bugzilla 459993";

    private static final String EDITION_TABLE_DESCRIPTION_LABEL_FR = "Table Bugzilla 459993";

    private static final String CROSS_TABLE_DESCRIPTION_LABEL_FR = "Table croisée Bugzilla 459993";

    private static final String POPUP_MENU_LABEL_FR = "Bugzilla 459993 Menu popup";

    private static final String OPERATION_ACTION1_LABEL_FR = "Bugzilla 459993 Opération 1";

    private static final String OPERATION_ACTION2_LABEL_FR = "Bugzilla 459993 Opération 2";

    private static final String VIEWPOINT_ID = "bugzilla459993";

    private static final String TOOL_FILTERS_LABEL_FR = "Voici un filtre";

    private static final String TOOL_LAYERS_LABEL_FR = "Voici un layer";

    // Id of tools as set in the odesign
    private static final String TOOL2_LABEL = "%SelectionWizard";

    private static final String TOOL3_LABEL = "PaneBasedSelectionWizard";

    // Files
    private static final String MODEL = "bugzilla459993.ecore";

    private static final String SESSION_FILE_VIEWPOINT = "representations.aird";

    private static final String DATA_UNIT_DIR = "data/unit/internationalization/";

    private static final String FILE_DIR = "/";

    private static final String FILTERS = "Filters";

    private static final String LAYERS = "Layers";

    private static final String TOOL_DOCUMENTATION_TOOLTIP_ENG = "Tool documentation";

    private static final String TOOL_DOCUMENTATION_TOOLTIP_FR = "Documentation de l'outil";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private String selected_language = Locale.ENGLISH.getLanguage();

    private Locale default_language = Locale.getDefault();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        if (editor != null) {
            editor.close();
        }
        Locale.setDefault(default_language);
        sessionAirdResource = null;
        localSession = null;
        editor = null;
        super.tearDown();
    }

    /**
     * Test the scenario using French localization.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDiagramInternationalization_FR() throws Exception {
        doTestDiagramInternationalization(Locale.FRENCH);
    }

    /**
     * Test the scenario using English localization.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDiagramInternationalization_ENG() throws Exception {
        doTestDiagramInternationalization(Locale.ENGLISH);
    }

    /**
     * Test the scenario on a tree, using French localization.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTreeInternationalization_FR() throws Exception {
        doTestTreeInternationalization(Locale.FRENCH);
    }

    /**
     * Test the scenario on a tree, using English localization.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTreeInternationalization_ENG() throws Exception {
        doTestTreeInternationalization(Locale.ENGLISH);
    }

    /**
     * Test the scenario on a tree, using French localization.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTableInternationalization_FR() throws Exception {
        doTestEditionTableInternationalization(Locale.FRENCH);
    }

    /**
     * Test the scenario on a tree, using English localization.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTableInternationalization_ENG() throws Exception {
        doTestEditionTableInternationalization(Locale.ENGLISH);
    }

    /**
     * Test the scenario on a tree, using French localization.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCrossTableInternationalization_FR() throws Exception {
        doTestEditionTableInternationalization(Locale.FRENCH);
    }

    /**
     * Test the scenario on a tree, using English localization.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCrossTableInternationalization_ENG() throws Exception {
        doTestEditionTableInternationalization(Locale.ENGLISH);
    }

    /**
     * The scenario uses a custom odesign and the plugin.properties in order to test the localization of VSM texts on a
     * Diagram.
     * 
     * @param locale
     *            the {@link Locale} to use in the scenario
     * @throws Exception
     *             Test error.
     */
    public void doTestDiagramInternationalization(Locale locale) throws Exception {
        initializedModelingProject(locale);

        // Validation of the RepresentationDescription label
        final RepresentationDescription representationDescription = localSession.getOpenedSession().getSelectedViewpoints(false).iterator().next().getOwnedRepresentations().iterator().next();
        Assert.assertEquals("The label of the representation description is not as expected.", getDiagramDescriptionLabel(),
                MessageTranslator.INSTANCE.getMessage(representationDescription, representationDescription.getLabel()));

        // Create a new diagram using the Model Explorer contextual menu
        UIProject uiProject = new UIProject(getProjectName());
        SWTBotTreeItem p1Node = uiProject.getProjectTreeItem().expand().getNode(MODEL).expand().getNode("p1").select();

        // Validate that the representation description label in the contextual
        // menu is translated
        localSession.newDiagramRepresentation("new " + getDiagramDescriptionLabel(), getDiagramDescriptionLabel()).on(p1Node);
        // Check popup title is translated too
        SWTBotShell shell = SWTBotUtils.checkForShellWithTitle("New " + getDiagramDescriptionLabel());
        shell.bot().button("OK").click();

        editor = SWTBotSiriusHelper.getSiriusDiagramEditor("new " + getDiagramDescriptionLabel());

        // Check that labels in the palette are properly displayed, especially
        // the ones with localization
        List<String> labelsToCheck = new ArrayList<>();
        labelsToCheck.add(getToolSectionLabel());
        labelsToCheck.add(getEPackageCreationToolLabel());
        labelsToCheck.add(TOOL2_LABEL);
        labelsToCheck.add(TOOL3_LABEL);
        SWTBotUtils.checkLabelsInPalette(editor, labelsToCheck);

        // Check that labels in the filters tool are properly displayed and internationalized
        editor.click(100, 100);
        labelsToCheck = new ArrayList<>();
        labelsToCheck.add(getToolFilterLabel());
        SWTBotUtils.checkLabelsInDiagramToolBar(editor, FILTERS, labelsToCheck);

        // Check SelectionWizard label and use tool
        editor.activateTool(TOOL2_LABEL);
        editor.click(100, 100);

        shell = SWTBotUtils.checkForShellWithTitle(getToolTitleLabel());

        // Check that TOOL_MESSAGE_LABEL is properly displayed
        SWTBotUtils.checkLabelInShell(getToolMessageLabel(), shell);

        shell.bot().label(getToolMessageLabel());
        shell.bot().button("Cancel").click();

        // Check PaneBasedSelectionWizard label and use tool
        editor.activateTool(TOOL3_LABEL);
        editor.click(100, 100);

        // Validate that TOOL_TITLE_LABEL is properly displayed
        shell = SWTBotUtils.checkForShellWithTitle(getToolTitleLabel());

        // Check that TOOL_MESSAGE_LABEL, TOOL_CHOICE_OF_VALUES_LABEL and
        // TOOL_SELECTED_VALUES_LABEL are properly displayed
        SWTBotUtils.checkLabelInShell(getToolMessageLabel(), shell);
        SWTBotUtils.checkLabelInShell(getToolChoiceOfValuesLabel(), shell);
        SWTBotUtils.checkLabelInShell(getToolSelectedValuesLabel(), shell);
        shell.bot().button("Cancel").click();

        editor.getEditPart("p11").click();
        bot.waitUntil(new CheckSelectedCondition(editor, "p11"));

        // Check that the popup menu and 2 operation actions are properly
        // translated
        // checkContextualMenus();
        List<String> contextMenusToCheck = new ArrayList<>();
        contextMenusToCheck.add(getPopupMenuLabel());
        contextMenusToCheck.add(getOperationAction1Label());
        contextMenusToCheck.add(getOperationAction2Label());
        contextMenusToCheck.add(getDiagramDescriptionLabel());
        SWTBotUtils.checkContextualMenus(bot.getDisplay(), editor.getDiagramEditPart().getViewer().getControl(), contextMenusToCheck);

        checkLabelsInModelExplorerView(getDiagramDescriptionLabel());

        // Check that labels in the layers tool are properly displayed and internationalized
        editor.click(100, 100);
        labelsToCheck = new ArrayList<>();
        labelsToCheck.add(getToolLayerLabel());
        SWTBotUtils.checkLabelsInDiagramToolBar(editor, LAYERS, labelsToCheck);

        // Bug 522368 test that there is no duplicated entries in the palette after an external modification.
        URI ecoreURI = URI.createPlatformResourceURI(getProjectName() + "/" + MODEL, false);
        Resource resource = new ResourceSetImpl().getResource(ecoreURI, true);
        EPackage ePackage = (EPackage) resource.getContents().get(0);
        ePackage.getEAnnotations().add(EcoreFactory.eINSTANCE.createEAnnotation());
        PaletteRoot paletteRoot = ((SiriusPaletteViewer) editor.getPaletteRootEditPartBot().part().getViewer()).getPaletteRoot();
        int before = paletteRoot.getChildren().size();
        resource.save(Collections.emptyMap());
        SWTBotUtils.waitAllUiEvents();
        int after = paletteRoot.getChildren().size();
        assertEquals("The number of elements in the palette should be the same after a refresh following an external modification.", before, after);
        String description = ((PaletteToolEntry) ((SectionPaletteDrawer) paletteRoot.getChildren().get(1)).getChildren().get(0)).getDescription();
        assertEquals("The tool documentation tooltip is not the expected one.", description, getToolDocumentationTooltip());

    }

    /**
     * The scenario uses a custom odesign and the plugin.properties in order to test the localization of VSM texts on a
     * Tree.
     * 
     * @param locale
     *            {@link Locale} to use in the current scenario.
     * @throws Exception
     *             Test error.
     */
    public void doTestTreeInternationalization(Locale locale) throws Exception {
        initializedModelingProject(locale);

        // Create a new diagram using the Model Explorer contextual menu
        UIProject uiProject = new UIProject(getProjectName());
        SWTBotTreeItem p1Node = uiProject.getProjectTreeItem().expand().getNode(MODEL).expand().getNode("p1").select();
        // Validate that the representation description label in the contextual
        // menu is translated
        localSession.newTreeRepresentation("new " + getTreeDescriptionLabel(), getTreeDescriptionLabel()).on(p1Node);
        // Check popup title is translated too
        SWTBotShell shell = SWTBotUtils.checkForShellWithTitle("New " + getTreeDescriptionLabel());
        shell.bot().button("OK").click();
        SWTBotUtils.waitAllUiEvents();

        final SWTGefBot botCopy = bot;
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return !botCopy.editors().isEmpty();
            }

            @Override
            public String getFailureMessage() {
                return "No editor found";
            }
        });

        final SWTBotTree rootTreeItem = bot.activeEditor().bot().tree().select(0);
        List<String> contextMenusToCheck = new ArrayList<>();
        contextMenusToCheck.add(getEPackageCreationToolLabel());
        contextMenusToCheck.add(getPopupMenuLabel());
        contextMenusToCheck.add(getOperationAction1Label());
        SWTBotUtils.checkContextualMenus(bot.getDisplay(), rootTreeItem.widget, contextMenusToCheck);

        checkLabelsInModelExplorerView(getTreeDescriptionLabel());
    }

    /**
     * The scenario uses a custom odesign and the plugin.properties in order to test the localization of VSM texts on an
     * Edition Table.
     * 
     * @param locale
     *            {@link Locale} to use in the current scenario.
     * @throws Exception
     *             Test error.
     */
    public void doTestEditionTableInternationalization(Locale locale) throws Exception {
        initializedModelingProject(locale);

        // Create a new diagram using the Model Explorer contextual menu
        UIProject uiProject = new UIProject(getProjectName());
        SWTBotTreeItem p1Node = uiProject.getProjectTreeItem().expand().getNode(MODEL).expand().getNode("p1").select();
        // Validate that the representation description label in the contextual
        // menu is translated
        localSession.newTableRepresentation("new " + getEditionTableDescriptionLabel(), getEditionTableDescriptionLabel()).on(p1Node);
        // Check popup title is translated too
        SWTBotShell shell = SWTBotUtils.checkForShellWithTitle("New " + getEditionTableDescriptionLabel());
        shell.bot().button("OK").click();
        SWTBotUtils.waitAllUiEvents();

        final SWTGefBot botCopy = bot;
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return !botCopy.editors().isEmpty();
            }

            @Override
            public String getFailureMessage() {
                return "No editor found";
            }
        });

        final SWTBotTree rootTreeItem = bot.activeEditor().bot().tree().select(0);
        List<String> contextMenusToCheck = new ArrayList<>();
        contextMenusToCheck.add(getEPackageCreationToolLabel());
        SWTBotUtils.checkContextualMenus(bot.getDisplay(), rootTreeItem.widget, contextMenusToCheck);

        checkLabelsInModelExplorerView(getEditionTableDescriptionLabel());
    }

    /**
     * The scenario uses a custom odesign and the plugin.properties in order to test the localization of VSM texts on an
     * Cross Table.
     * 
     * @param locale
     *            {@link Locale} to use in the current scenario.
     * @throws Exception
     *             Test error.
     */
    public void doTestCrossTableInternationalization(Locale locale) throws Exception {
        initializedModelingProject(locale);

        // Create a new diagram using the Model Explorer contextual menu
        UIProject uiProject = new UIProject(getProjectName());
        SWTBotTreeItem p1Node = uiProject.getProjectTreeItem().expand().getNode(MODEL).expand().getNode("p1").select();
        // Validate that the representation description label in the contextual
        // menu is translated
        localSession.newTableRepresentation("new " + getCrossTableDescriptionLabel(), getCrossTableDescriptionLabel()).on(p1Node);
        // Check popup title is translated too
        SWTBotShell shell = SWTBotUtils.checkForShellWithTitle("New " + getCrossTableDescriptionLabel());
        shell.bot().button("OK").click();
        SWTBotUtils.waitAllUiEvents();

        final SWTGefBot botCopy = bot;
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return !botCopy.editors().isEmpty();
            }

            @Override
            public String getFailureMessage() {
                return "No editor found";
            }
        });

        final SWTBotTree rootTreeItem = bot.activeEditor().bot().tree().select(0);
        List<String> contextMenusToCheck = new ArrayList<>();
        contextMenusToCheck.add(getEPackageCreationToolLabel());
        contextMenusToCheck.add(getEClassCreationToolLabel());
        SWTBotUtils.checkContextualMenus(bot.getDisplay(), rootTreeItem.widget, contextMenusToCheck);

        checkLabelsInModelExplorerView(getCrossTableDescriptionLabel());
    }

    /**
     * Validate that the Viewpoint and Representation Description are displayed with localization in the Model Explorer
     * view under the representations.aird file
     * 
     * @param representationDescriptionLabel
     *            expected {@link RepresentationDescription} label
     */
    private void checkLabelsInModelExplorerView(String representationDescriptionLabel) {
        //
        SWTBotTreeItem[] viewpointItems = localSession.getRepresentationsFileTreeItem().expand().getItems();
        Assert.assertEquals("There is not the expected number of activated Viewpoint", 1, viewpointItems.length);
        SWTBotTreeItem viewpointTreeItem = viewpointItems[0];
        Assert.assertEquals("There is not the expected number of activated Viewpoint", getViewpointLabel(), viewpointTreeItem.getText());
        SWTBotTreeItem[] representationDescriptionItems = viewpointTreeItem.expand().getItems();
        boolean representationDescriptionFound = false;
        for (SWTBotTreeItem representationDescriptionItem : representationDescriptionItems) {
            if (representationDescriptionLabel.equals(representationDescriptionItem.getText())) {
                representationDescriptionFound = true;
            }
        }
        Assert.assertTrue("The representation description " + representationDescriptionLabel + " has not been found", representationDescriptionFound);
    }

    /**
     * 
     * Initialization of the Modeling Project.
     * 
     * @param locale
     *            {@link Locale} to use in the current scenario.
     */
    private void initializedModelingProject(Locale locale) {
        Locale.setDefault(locale);
        selected_language = locale.getLanguage();
        try {
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
            // Initialization of a new modeling project
            project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
            ModelingProjectManager.INSTANCE.convertToModelingProject(project, new NullProgressMonitor());
            project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
        } catch (CoreException e) {
            fail("Fail during refreshing the new project: " + e.getMessage());
        }
        try {
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        } catch (OperationCanceledException e) {
            fail("Fail during waiting of ResourceSyncClientNotifier job: " + e.getMessage());
        } catch (InterruptedException e) {
            fail("Fail during waiting of ResourceSyncClientNotifier job: " + e.getMessage());
        }

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_VIEWPOINT);
        bot.waitUntil(new DefaultCondition() {
            // This waiting condition is needed otherwise the test fails in run
            // (not in debug)
            @Override
            public boolean test() throws Exception {
                return designerPerspective.getOpenedSession(sessionAirdResource) != null;
            }

            @Override
            public String getFailureMessage() {
                return "No session was found";
            }
        });
        localSession = designerPerspective.getOpenedSession(sessionAirdResource);
        localSession.getOpenedSession().save(new NullProgressMonitor());

        // Activation of a translated viewpoint
        Set<Viewpoint> viewpoints = ViewpointRegistry.getInstance().getViewpoints();
        Viewpoint viewpointToActivate = null;
        for (Viewpoint viewpoint : viewpoints) {
            if (VIEWPOINT_ID.equals(viewpoint.getName())) {
                viewpointToActivate = viewpoint;
            }
        }
        // Validate of the Viewpoint label
        Assert.assertNotNull("The viewpoint " + getViewpointLabel() + " isn't available", viewpointToActivate);
        Assert.assertEquals("The label of the viewpoint is not as expected.", getViewpointLabel(), MessageTranslator.INSTANCE.getMessage(viewpointToActivate, viewpointToActivate.getLabel()));

        // Activate the viewpoint
        Assert.assertEquals("There should be no activated viewpoint", 0, localSession.getOpenedSession().getSelectedViewpoints(false).size());
        localSession.changeViewpointSelection(Collections.singleton(getViewpointLabel()), Sets.<String> newLinkedHashSet());
        Assert.assertEquals("There should be 1 activated viewpoint", 1, localSession.getOpenedSession().getSelectedViewpoints(false).size());
    }

    private String getToolFilterLabel() {
        if ("en".equals(selected_language)) {
            return TOOL_FILTERS_LABEL_ENG;
        }
        return TOOL_FILTERS_LABEL_FR;
    }

    private String getToolLayerLabel() {
        if ("en".equals(selected_language)) {
            return TOOL_LAYERS_LABEL_ENG;
        }
        return TOOL_LAYERS_LABEL_FR;
    }

    private String getToolSelectedValuesLabel() {
        if ("en".equals(selected_language)) {
            return TOOL_SELECTED_VALUES_LABEL_ENG;
        }
        return TOOL_SELECTED_VALUES_LABEL_FR;
    }

    private String getToolChoiceOfValuesLabel() {
        if ("en".equals(selected_language)) {
            return TOOL_CHOICE_OF_VALUES_LABEL_ENG;
        }
        return TOOL_CHOICE_OF_VALUES_LABEL_FR;
    }

    private String getToolMessageLabel() {
        if ("en".equals(selected_language)) {
            return TOOL_MESSAGE_LABEL_ENG;
        }
        return TOOL_MESSAGE_LABEL_FR;
    }

    private String getToolTitleLabel() {
        if ("en".equals(selected_language)) {
            return TOOL_TITLE_LABEL_ENG;
        }
        return TOOL_TITLE_LABEL_FR;
    }

    private String getToolSectionLabel() {
        if ("en".equals(selected_language)) {
            return TOOL_SECTION_LABEL_ENG;
        }
        return TOOL_SECTION_LABEL_FR;
    }

    private String getEPackageCreationToolLabel() {
        if ("en".equals(selected_language)) {
            return EPACKAGE_CREATION_TOOL_LABEL_ENG;
        }
        return EPACKAGE_CREATION_TOOL_LABEL_FR;
    }

    private String getEClassCreationToolLabel() {
        if ("en".equals(selected_language)) {
            return ECLASS_CREATION_TOOL_LABEL_ENG;
        }
        return ECLASS_CREATION_TOOL_LABEL_FR;
    }

    private String getViewpointLabel() {
        if ("en".equals(selected_language)) {
            return VIEWPOINT_LABEL_ENG;
        }
        return VIEWPOINT_LABEL_FR;
    }

    private String getDiagramDescriptionLabel() {
        if ("en".equals(selected_language)) {
            return DIAGRAM_DESCRIPTION_LABEL_ENG;
        }
        return DIAGRAM_DESCRIPTION_LABEL_FR;
    }

    private String getTreeDescriptionLabel() {
        if ("en".equals(selected_language)) {
            return TREE_DESCRIPTION_LABEL_ENG;
        }
        return TREE_DESCRIPTION_LABEL_FR;
    }

    private String getEditionTableDescriptionLabel() {
        if ("en".equals(selected_language)) {
            return EDITION_TABLE_DESCRIPTION_LABEL_ENG;
        }
        return EDITION_TABLE_DESCRIPTION_LABEL_FR;
    }

    private String getCrossTableDescriptionLabel() {
        if ("en".equals(selected_language)) {
            return CROSS_TABLE_DESCRIPTION_LABEL_ENG;
        }
        return CROSS_TABLE_DESCRIPTION_LABEL_FR;
    }

    private String getPopupMenuLabel() {
        if ("en".equals(selected_language)) {
            return POPUP_MENU_LABEL_ENG;
        }
        return POPUP_MENU_LABEL_FR;
    }

    private String getOperationAction1Label() {
        if ("en".equals(selected_language)) {
            return OPERATION_ACTION1_LABEL_ENG;
        }
        return OPERATION_ACTION1_LABEL_FR;
    }

    private String getOperationAction2Label() {
        if ("en".equals(selected_language)) {
            return OPERATION_ACTION2_LABEL_ENG;
        }
        return OPERATION_ACTION2_LABEL_FR;
    }

    private String getToolDocumentationTooltip() {
        if ("en".equals(selected_language)) {
            return TOOL_DOCUMENTATION_TOOLTIP_ENG;
        }
        return TOOL_DOCUMENTATION_TOOLTIP_FR;
    }
}
