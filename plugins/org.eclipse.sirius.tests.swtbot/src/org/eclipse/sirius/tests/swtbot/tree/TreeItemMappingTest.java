/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.tree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;

/**
 * Test tree item mapping. Test automatic refresh and manual refresh. Test undo/redo after each test type Test opening
 * and closing model or editor to verify that all changes are effective
 * 
 * @author jdupont
 */
public class TreeItemMappingTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /**
     * Sirius Specific Model.
     */
    private static final String VSM = "ecore.odesign";

    /**
     * 
     */
    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    /**
     * Test repository.
     */
    private static final String DATA_UNIT_DIR = "data/unit/tree/";

    /**
     * Session file.
     */
    private static final String SESSION_FILE = "tree.aird";

    /**
     * UML File.
     */
    private static final String ECORE_FILE = "tree.ecore";

    /**
     * File directory.
     */
    private static final String FILE_DIR = "/";

    /**
     * Sirius Group.
     */
    private static final String GROUP = "Ecore Editing Workbench V4.6";

    /**
     * Properties view tab General.
     */
    private static final String GENERAL = "General";

    /**
     * Properties view tab Import.
     */
    private static final String IMPORT = "Import";

    /**
     * Properties view tab Behavior.
     */
    private static final String BEHAVIOR = "Behavior";

    /**
     * Properties view tab Advanced.
     */
    private static final String ADVANCED = "Advanced";

    /**
     * Sirius name.
     */
    private static final String VIEWPOINT_NAME = "Design";

    /**
     * Representation name.
     */
    private static final String REPRESENTATION_NAME = "Tree";

    /**
     * Semantic model instance.
     */
    private static final String REPRESENTATION_INSTANCE_NAME = "new Tree";

    /**
     * Contextual menu undo.
     */
    private static final String UNDO = "Undo Set";

    /**
     * Contextual menu redo.
     */
    private static final String REDO = "Redo Set";

    /**
     * Current diagram.
     */
    protected UITreeRepresentation treeRepresentation;

    /**
     * Current editor.
     */
    protected SWTBotEditor editor;

    /**
     * Session.
     */
    private UIResource sessionAirdResource;

    /**
     * Local Session.
     */
    private UILocalSession localSession;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM, SESSION_FILE, ECORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

    }

    /**
     * Test manual refresh.
     */
    @Test
    public void testStyleDescriptionManualRefresh() {
        disableAutoRefresh();

        // open Odesign
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);

        // Modify Viewpoint Specification Model
        modifyVSM(odesignEditor);

        SWTBotUtils.waitAllUiEvents();
        // Open editor
        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);

        editor = treeRepresentation.getEditor();

        editor.save();

        editor.setFocus();

        // Manual refreh with click context menu
        editor.bot().toolbarButtonWithTooltip("Force a refresh of the tree").click();

        refreshEditorTest();

        localSession.save();
        // closeAndSaveAll(VSM, odesignEditor, localSession);
        localSession.closeNoDirty();

    }

    /**
     * Test automatic refresh when session is open before modify odesign.
     */
    @Test
    public void _testAutomaticRefreshWithSessionOpen() {
        disableAutoRefresh();
        enableAutoRefresh();

        // the session is already open and opening odesign
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);

        // Modify VSM
        modifyVSM(odesignEditor);

        // Open editor
        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);

        // Test editor refresh
        refreshEditorTest();

        // Close editor
        editor.close();

        // Open editor
        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);

        // Test editor refresh
        refreshEditorTest();

        // close session
        if (localSession.isDirty()) {
            localSession.close(true);
        } else {
            localSession.closeNoDirty();
        }

        // Open session
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // open editor
        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);

        // Test editor refresh
        refreshEditorTest();

        // close session
        localSession.closeNoDirty();
        // closeAndSaveAll(VSM, odesignEditor, localSession);
    }

    /**
     * Test automatic refresh when session is close during modify odesign.
     */
    @Test
    public void _testAutomaticRefreshWithSessionClose() {
        disableAutoRefresh();
        enableAutoRefresh();

        // the session is already open and opening odesign
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);

        // the session is close and the odesign is open
        if (localSession.isDirty()) {
            localSession.close(true);
        } else {
            localSession.closeNoDirty();
        }

        // Modify VSM
        modifyVSM(odesignEditor);

        // Wait for the refresh of the vsm is over.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Open session
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Open editor
        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);

        // Test editor refresh
        refreshEditorTest();

        localSession.closeNoDirty();
        // closeAndSaveAll(VSM, odesignEditor, localSession);

    }

    /**
     * Test automatic refresh when tree editor is open when modify odesign.
     */
    @Test
    public void testAutomaticRefreshWithEditorOpen() {
        disableAutoRefresh();
        enableAutoRefresh();

        // Open editor
        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);

        // the session is already open and opening odesign
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);

        modifyVSM(odesignEditor);

        bot.sleep(500);

        // Test editor refresh
        refreshEditorTest();

    }

    /**
     * Modify all fields in properties view of tree item style description in viewpoint specific model (.odesign).
     * 
     * @param odesignEditor
     *            the odesign editor.
     */
    private void modifyVSM(SWTBotVSMEditor odesignEditor) {
        // expand the tree : Tree Item Style Description 8
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("Design").expandNode("Tree").expandNode("Enum").select();

        // accesses to property view
        SWTBotView propertiesView = bot.viewByTitle(PROPERTIES);
        propertiesView.setFocus();

        // accesses to tab General
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL, propertiesView.bot());
        changeAndTestPropertyTabGeneral("EDataType", "DataTypeId", "DataType", "[eContents()->select(not oclIsKindOf(EObject) or oclIsKindOf(EDataType))/]");

        // accesses to tab import
        SWTBotSiriusHelper.selectPropertyTabItem(IMPORT, propertiesView.bot());
        // changeAndTestPropertyTabImport("");

        // accesses to tab Behaviour
        SWTBotSiriusHelper.selectPropertyTabItem(BEHAVIOR, propertiesView.bot());
        // changeAndTestPropertyTabBehavior("", "");

        // accesses to tab Advanced
        SWTBotSiriusHelper.selectPropertyTabItem(ADVANCED, propertiesView.bot());
        changeAndTestPropertyTabAdvanced("[eContents()->select(not oclIsKindOf(EObject) or oclIsKindOf(EAttribute))/]", "[name.size() > 4/]");

        // Save odesign
        saveViewpointSpecificationModel(VSM);

    }

    /**
     * Verify that editor changes made in the odesign.
     */
    private void refreshEditorTest() {

        editor = treeRepresentation.getEditor();

        editor.save();

        TreeItem widgetDataType = null;

        if (editor.bot().tree().getTreeItem("datatype").widget instanceof TreeItem) {
            widgetDataType = editor.bot().tree().getTreeItem("datatype").widget;
        }

        String semanticElement = null;
        String domainClass = null;
        String id = null;
        String label = null;
        String preconditionExpression = null;
        String semanticCandidatesExpression = null;
        EList<TreeItemMapping> reusedTreeItemMappings = null;
        EList<RepresentationCreationDescription> detailDescription = null;
        EList<RepresentationNavigationDescription> navigationDescription = null;

        // Retrieve show icon, label size, label expression, label alignment,
        // label format, color background, label color of 'new EClass 1'
        if (widgetDataType != null) {
            semanticElement = getSemanticElement(widgetDataType);
            domainClass = getDomainClass(widgetDataType);
            id = getId(widgetDataType);
            label = getLabel(widgetDataType);
            preconditionExpression = getPreconditionExpression(widgetDataType);
            semanticCandidatesExpression = getSemanticCandidatesExpression(widgetDataType);
            reusedTreeItemMappings = getReusedTreeItemMappings(widgetDataType);
            detailDescription = getDetailDescriptions(widgetDataType);
            navigationDescription = getNavigationDescriptions(widgetDataType);
        }

        assertThat(semanticElement, equalTo("[eContents()->select(not oclIsKindOf(EObject) or oclIsKindOf(EAttribute))/]"));
        assertThat(domainClass, equalTo("EDataType"));
        assertThat(id, equalTo("DataTypeId"));
        assertThat(label, equalTo("DataType"));
        assertThat(preconditionExpression, equalTo("[name.size() > 4/]"));
        assertThat(semanticCandidatesExpression, equalTo("[eContents()->select(not oclIsKindOf(EObject) or oclIsKindOf(EDataType))/]"));
        assertThat(reusedTreeItemMappings.size(), equalTo(0));

        if (editor.bot().tree().getAllItems().length > 3) {
            fail("the precondition expression is not taken into account");
        }
    }

    /**
     * Retrieve, test, change and test all fields in General tab.
     * 
     * @param newValueDomainClass
     *            new value for domain class field
     * @param newValueId
     *            new value for id field
     * @param newValueLabel
     *            new value for label field
     * @param newValueSemanticCandidateExpression
     *            new value for semantic candidate expression
     */
    private void changeAndTestPropertyTabGeneral(String newValueDomainClass, String newValueId, String newValueLabel, String newValueSemanticCandidateExpression) {
        // Change Domain class
        bot.viewByTitle(PROPERTIES).bot().text("EEnum").setFocus();
        bot.viewByTitle(PROPERTIES).bot().text("EEnum").setText(newValueDomainClass);
        assertThat(bot.viewByTitle(PROPERTIES).bot().text("EDataType").getText(), equalTo(newValueDomainClass));
        // Change id
        bot.viewByTitle(PROPERTIES).bot().text("EnumId").setFocus();
        bot.viewByTitle(PROPERTIES).bot().text("EnumId").setText(newValueId);
        assertThat(bot.viewByTitle(PROPERTIES).bot().text("DataTypeId").getText(), equalTo(newValueId));
        // Change label
        bot.viewByTitle(PROPERTIES).bot().text("Enum").setFocus();
        bot.viewByTitle(PROPERTIES).bot().text("Enum").setText(newValueLabel);
        assertThat(bot.viewByTitle(PROPERTIES).bot().text("DataType").getText(), equalTo(newValueLabel));
        // Change Semantic Candidate Expression
        bot.viewByTitle(PROPERTIES).bot().text("[eContents()->select(not oclIsKindOf(EObject) or oclIsKindOf(EEnum))/]").setFocus();
        bot.viewByTitle(PROPERTIES).bot().text("[eContents()->select(not oclIsKindOf(EObject) or oclIsKindOf(EEnum))/]").setText(newValueSemanticCandidateExpression);
        assertThat(bot.viewByTitle(PROPERTIES).bot().text("[eContents()->select(not oclIsKindOf(EObject) or oclIsKindOf(EDataType))/]").getText(), equalTo(newValueSemanticCandidateExpression));
    }

    /**
     * Retrieve, test, change and test all fields in Advanced tab.
     * 
     * @param newValueSemanticElement
     *            label for semantic element field
     * @param newValuePreConditionExpression
     *            label for precondition expression field
     */
    private void changeAndTestPropertyTabAdvanced(String newValueSemanticElement, String newValuePreConditionExpression) {
        SWTBotText firstField = bot.viewByTitle(PROPERTIES).bot().text(0);
        SWTBotText secondField = bot.viewByTitle(PROPERTIES).bot().text(1);
        if (firstField.getToolTipText().startsWith("Precondition")) {
            // Change Precondition Expression
            firstField.setFocus();
            firstField.setText(newValuePreConditionExpression);
            assertThat(firstField.getText(), equalTo(newValuePreConditionExpression));
            // Change label Semantic elements
            secondField.setFocus();
            secondField.setText(newValueSemanticElement);
            assertThat(secondField.getText(), equalTo(newValueSemanticElement));
        } else if (secondField.getToolTipText().startsWith("Precondition")) {
            // Change Precondition Expression
            secondField.setFocus();
            secondField.setText(newValuePreConditionExpression);
            assertThat(secondField.getText(), equalTo(newValuePreConditionExpression));
            // Change label Semantic elements
            firstField.setFocus();
            firstField.setText(newValueSemanticElement);
            assertThat(firstField.getText(), equalTo(newValueSemanticElement));
        } else {
            fail("The tooltip of fields of Advanced folder of Properties view have been probably changed.");
        }
    }

}
