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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILSCategoryBrowser;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILSResourceBrowser;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILocalSessionBrowser;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemTextCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test representation renaming from Model Content view
 * 
 * @author edugueperoux
 */
public class RepresentationRenamingTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL_001 = "representationRenaming.ecore";

    private static final String SESSION_FILE_001 = "representationRenaming.aird";

    private static final String VIEWPOINT_NAME_001 = "Design";

    private static final String SEMANTIC_ROOT_PACKAGE_NAME = "RootSTDTestCase";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM = "RootSTDTestCase package entities";

    private static final String REPRESENTATION_INSTANCE_NAME_TABLE = "new Classes";

    private static final String REPRESENTATION_NAME_DIAGRAM_001 = "Entities";

    private static final String REPRESENTATION_NAME_TABLE_001 = "Classes";

    private static final String DATA_UNIT_DIR = "data/unit/session/representationRenaming/";

    private static final String FILE_DIR = "/";

    private UILocalSession localSession;

    private UILSResourceBrowser uiLSResourceBrowser;

    private UILSCategoryBrowser uiLSCategoryBrowser;

    private SWTBotTreeItem semanticSWTBotTreeItem;

    private UIDiagramRepresentation representationFromCategory;

    /**
     * Test if representation names in resource/category/semantic tree items and
     * in open editors titles are identicals
     */
    public void testSameDiagramName() {
        doTestSameRepresentationName(REPRESENTATION_INSTANCE_NAME_DIAGRAM, REPRESENTATION_NAME_DIAGRAM_001);
    }

    /**
     * Test if after representation renaming from resource tree the related
     * opened representation editors titles and same representation from
     * category and semantic trees have changed.
     * 
     */
    public void _testDiagramRenamingFromResourceResourceBrowser() {
        doTestRepresentationRenamingFromResourceResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_DIAGRAM, REPRESENTATION_NAME_DIAGRAM_001, "(EPackage " + SEMANTIC_ROOT_PACKAGE_NAME + ")", true);
    }

    /**
     * Test if representation names in resource/category/semantic tree items and
     * in open editors titles are identicals
     */
    public void testDiagramRenamingFromCategoryResourceBrowser() {
        doTestRepresentationRenamingFromCategoryResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_DIAGRAM, REPRESENTATION_NAME_DIAGRAM_001, "(EPackage " + SEMANTIC_ROOT_PACKAGE_NAME + ")", true);
    }

    /**
     * Test if after representation renaming from resource tree the related
     * opened representation editors titles and same representation from
     * category and semantic trees have changed.
     * 
     */
    public void testDiagramRenamingFromSemanticResourceBrowser() {
        doTestRepresentationRenamingFromSemanticResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_DIAGRAM, REPRESENTATION_NAME_DIAGRAM_001, "(EPackage " + SEMANTIC_ROOT_PACKAGE_NAME + ")", true);
    }

    /**
     * Test if representation names in resource/category/semantic tree items and
     * in open editors titles are identicals
     */
    public void testSameTableName() {
        doTestSameRepresentationName(REPRESENTATION_INSTANCE_NAME_TABLE, REPRESENTATION_NAME_TABLE_001);
    }

    /**
     * Test if after representation renaming from resource tree the related
     * opened representation editors titles and same representation from
     * category and semantic trees have changed.
     * 
     */
    public void _testTableRenamingFromResourceResourceBrowser() {
        doTestRepresentationRenamingFromResourceResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_TABLE, REPRESENTATION_NAME_TABLE_001, "", true);
    }

    /**
     * Test if representation names in resource/category/semantic tree items and
     * in open editors titles are identical
     */
    public void testTableRenamingFromCategoryResourceBrowser() {
        doTestRepresentationRenamingFromCategoryResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_TABLE, REPRESENTATION_NAME_TABLE_001, "", true);
    }

    /**
     * Test if after representation renaming from resource tree the related
     * opened representation editors titles and same representation from
     * category and semantic trees have changed.
     */
    public void testTableRenamingFromSemanticResourceBrowser() {
        doTestRepresentationRenamingFromSemanticResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_TABLE, REPRESENTATION_NAME_TABLE_001, "", true);
    }

    /**
     */
    public void _testDiagramRenamingFromResourceResourceBrowser_Closed() {
        doTestRepresentationRenamingFromResourceResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_DIAGRAM, REPRESENTATION_NAME_DIAGRAM_001, "(EPackage " + SEMANTIC_ROOT_PACKAGE_NAME + ")", false);
    }

    /**
     */
    public void testDiagramRenamingFromCategoryResourceBrowser_Closed() {
        doTestRepresentationRenamingFromCategoryResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_DIAGRAM, REPRESENTATION_NAME_DIAGRAM_001, "(EPackage " + SEMANTIC_ROOT_PACKAGE_NAME + ")", false);
    }

    /**
     */
    public void testDiagramRenamingFromSemanticResourceBrowser_Closed() {
        doTestRepresentationRenamingFromSemanticResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_DIAGRAM, REPRESENTATION_NAME_DIAGRAM_001, "(EPackage " + SEMANTIC_ROOT_PACKAGE_NAME + ")", false);
    }

    /**
     */
    public void _testTableRenamingFromResourceResourceBrowser_Closed() {
        doTestRepresentationRenamingFromResourceResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_TABLE, REPRESENTATION_NAME_TABLE_001, "", false);
    }

    /**
     */
    public void testTableRenamingFromCategoryResourceBrowser_Closed() {
        doTestRepresentationRenamingFromCategoryResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_TABLE, REPRESENTATION_NAME_TABLE_001, "", false);
    }

    /**
     */
    public void testTableRenamingFromSemanticResourceBrowser_Closed() {
        doTestRepresentationRenamingFromSemanticResourceBrowser("Plop", REPRESENTATION_INSTANCE_NAME_TABLE, REPRESENTATION_NAME_TABLE_001, "", false);
    }

    /**
     * Return files used in the current test.
     */
    String[] getFilesUsedForTest() {
        return new String[] { MODEL_001, SESSION_FILE_001 };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, getFilesUsedForTest());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_001);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        UILocalSessionBrowser uiLocalSessionBrowser = localSession.getLocalSessionBrowser();
        // uiLSResourceBrowser = uiLocalSessionBrowser.perResource();
        uiLSCategoryBrowser = uiLocalSessionBrowser.perCategory();
        semanticSWTBotTreeItem = uiLocalSessionBrowser.perSemantic();
    }

    private void openDiagram(String descriptionName, String representationName) {
        representationFromCategory = uiLSCategoryBrowser.selectViewpoint(VIEWPOINT_NAME_001).selectRepresentation(descriptionName)
                .selectRepresentationInstance(representationName, UIDiagramRepresentation.class);
        representationFromCategory.open();
    }

    private void doTestSameRepresentationName(String representationName, String descriptionName) {
        openDiagram(descriptionName, representationName);
        assertEditorHasSameRepresentationName(representationName, descriptionName);
    }

    private void doTestRepresentationRenamingFromResourceResourceBrowser(String newName, String representationName, String descriptionName, String editorSuffixe, boolean openEditor) {
        selectRepresentation(descriptionName, representationName, openEditor);
        renameRepresentation(getExpandedRepresentationTreeItemFromResourceBrowser(descriptionName), newName);
        if (openEditor) {
            assertEditorHasSameRepresentationName(newName, descriptionName);
        }
        assertEquals(newName, getExpandedRepresentationTreeItemFromResourceBrowser(descriptionName).getText());
        undoRename();
        String originalName = descriptionName.equals(REPRESENTATION_NAME_TABLE_001) ? REPRESENTATION_INSTANCE_NAME_TABLE : REPRESENTATION_INSTANCE_NAME_DIAGRAM;
        assertEquals(originalName, getExpandedRepresentationTreeItemFromResourceBrowser(descriptionName).getText());
        if (openEditor) {
            assertEditorHasSameRepresentationName(originalName, descriptionName);
        }
    }

    private void doTestRepresentationRenamingFromCategoryResourceBrowser(String newName, String representationName, String descriptionName, String editorSuffixe, boolean openEditor) {
        selectRepresentation(descriptionName, representationName, openEditor);
        renameRepresentation(getExpandedRepresentationTreeItemFromCategoryBrowser(descriptionName), newName);
        if (openEditor) {
            assertEditorHasSameRepresentationName(newName, descriptionName);
        }
        assertEquals(newName, getExpandedRepresentationTreeItemFromCategoryBrowser(descriptionName).getText());

        // TODO MPO
        // undoRename();
        // String originalName =
        // descriptionName.equals(REPRESENTATION_NAME_TABLE_001) ?
        // REPRESENTATION_INSTANCE_NAME_TABLE :
        // REPRESENTATION_INSTANCE_NAME_DIAGRAM;
        // assertEquals(originalName,
        // getExpandedRepresentationTreeItemFromCategoryBrowser(descriptionName).getText());
        // if (openEditor) {
        // assertEditorHasSameRepresentationName(originalName, descriptionName,
        // editorSuffixe);
        // }
    }

    private void doTestRepresentationRenamingFromSemanticResourceBrowser(String newName, String representationName, String descriptionName, String editorSuffixe, boolean openEditor) {
        selectRepresentation(descriptionName, representationName, openEditor);
        renameRepresentation(getExpandedRepresentationTreeItemFromSemanticBrowser(representationName), newName);
        if (openEditor) {
            assertEditorHasSameRepresentationName(newName, descriptionName);
        }
        assertNotNull(getExpandedRepresentationTreeItemFromSemanticBrowser(newName));

        // TODO MPO
        // undoRename();
        // String originalName =
        // descriptionName.equals(REPRESENTATION_NAME_TABLE_001) ?
        // REPRESENTATION_INSTANCE_NAME_TABLE :
        // REPRESENTATION_INSTANCE_NAME_DIAGRAM;
        // assertEquals(originalName,
        // getExpandedRepresentationTreeItemFromSemanticBrowser(representationName).getText());
        // if (openEditor) {
        // assertEditorHasSameRepresentationName(originalName, descriptionName,
        // editorSuffixe);
        // }
    }

    private void undoRename() {
        bot.menu("Edit").menu("Undo " + "Rename representation").click();
    }

    private void renameRepresentation(final SWTBotTreeItem swtBotTreeItem, final String newName) {
        SWTBotUtils.clickContextMenu(swtBotTreeItem, "Rename");
        SWTBotShell shell = bot.shell("Rename representation");
        shell.activate();

        bot.textWithLabel("Enter new name (case sensitive):").setText(newName);
        bot.button("OK").click();

        bot.waitUntil(new TreeItemTextCondition(swtBotTreeItem, newName));
    }

    private void selectRepresentation(String descriptionName, String representationName, boolean openEditor) {
        if (openEditor) {
            openDiagram(descriptionName, representationName);
        } else {
            representationFromCategory = uiLSCategoryBrowser.selectViewpoint(VIEWPOINT_NAME_001).selectRepresentation(descriptionName)
                    .selectRepresentationInstance(representationName, UIDiagramRepresentation.class);
            representationFromCategory.getTreeItem().select().select();
        }
    }

    private SWTBotTreeItem getExpandedRepresentationTreeItemFromResourceBrowser(String descriptionName) {
        uiLSResourceBrowser = localSession.getLocalSessionBrowser().perResource();
        SWTBotTreeItem resourceSWTBotTreeItem = uiLSResourceBrowser.getTreeItem();
        resourceSWTBotTreeItem.setFocus();
        SWTBotTreeItem subResourceSWTBotTreeItem = resourceSWTBotTreeItem.getItems()[0];
        subResourceSWTBotTreeItem.expand();
        SWTBotTreeItem subSubResourceSWTBotTreeItem = subResourceSWTBotTreeItem.getItems()[0];
        subSubResourceSWTBotTreeItem.expand();
        SWTBotTreeItem subSubSubResourceSWTBotTreeItem = subSubResourceSWTBotTreeItem.getNode(descriptionName);
        subSubSubResourceSWTBotTreeItem.expand();
        return subSubSubResourceSWTBotTreeItem.getNode(0);
    }

    private String getRepresentationNameFromCategoryBrowser(String descriptionName) {
        String representationNameFromCategoryBrowser = getExpandedRepresentationTreeItemFromCategoryBrowser(descriptionName).getText();
        return representationNameFromCategoryBrowser;
    }

    private SWTBotTreeItem getExpandedRepresentationTreeItemFromCategoryBrowser(String descriptionName) {
        uiLSCategoryBrowser = localSession.getLocalSessionBrowser().perCategory();
        return uiLSCategoryBrowser.selectViewpoint(VIEWPOINT_NAME_001).selectRepresentation(descriptionName).getTreeItem().getItems()[0];
    }

    private SWTBotTreeItem getExpandedRepresentationTreeItemFromSemanticBrowser(String representationName) {
        semanticSWTBotTreeItem = localSession.getLocalSessionBrowser().perSemantic();
        SWTBotTreeItem subSemanticSWTBotTreeItem = semanticSWTBotTreeItem.getItems()[0];
        subSemanticSWTBotTreeItem.expand();
        return subSemanticSWTBotTreeItem.getNode(representationName);
    }

    private String getRepresentationNameFromOpenedEditor(String descriptionName, String representationName) {
        representationFromCategory = uiLSCategoryBrowser.selectViewpoint(VIEWPOINT_NAME_001).selectRepresentation(descriptionName)
                .selectRepresentationInstance(representationName, UIDiagramRepresentation.class);
        return bot.activeEditor().getTitle();
    }

    private void assertEditorHasSameRepresentationName(String representationName, String descriptionName) {
        // assertEquals(representationName,
        // getRepresentationNameFromResourceBrowser(descriptionName));
        assertEquals(representationName, getRepresentationNameFromCategoryBrowser(descriptionName));
        assertNotNull(getExpandedRepresentationTreeItemFromSemanticBrowser(representationName));

        if (!REPRESENTATION_NAME_TABLE_001.equals(descriptionName)) {
            assertEquals(representationName, getRepresentationNameFromOpenedEditor(descriptionName, representationName));
        }
    }
}
