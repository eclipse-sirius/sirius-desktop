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
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.tree.TreeUtils;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;

/**
 * Tests on ui (swt {@link TreeItem}) update according to {@link DTree} model.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeUIRefreshTests extends AbstractTreeSiriusSWTBotGefTestCase {

    /** Path. */
    private static final String PATH = "data/unit/tree/";

    /** Modeler resource file. */
    private static final String MODELER_RESOURCE_FILE = "ecore.odesign";

    /** Session resource file. */
    private static final String SESSION_RESOURCE_FILE = "tree.aird";

    /** Semantic resource file. */
    private static final String SEMANTIC_RESOURCE_FILE = "tree.ecore";

    private SWTBotEditor treeEditorBot;

    private UITreeRepresentation treeRepresentation;

    private DTree dTree;

    private DTreeItem firstDTreeItem;

    private DTreeItem secondDTreeItem;

    private DTreeItem thirdDTreeItem;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, MODELER_RESOURCE_FILE, SESSION_RESOURCE_FILE, SEMANTIC_RESOURCE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        treeRepresentation = openEditor(localSession, EcoreModeler.DESIGN_VIEWPOINT_NAME, "Tree", "new Tree");
        treeEditorBot = treeRepresentation.getEditor();
        DTreeEditor dTreeEditor = (DTreeEditor) treeEditorBot.getReference().getEditor(false);
        dTree = (DTree) dTreeEditor.getRepresentation();
        firstDTreeItem = dTree.getOwnedTreeItems().get(0);
        secondDTreeItem = dTree.getOwnedTreeItems().get(1);
        thirdDTreeItem = dTree.getOwnedTreeItems().get(2);
    }

    /**
     * Test that changing the feature {@link TreePackage#DTREE_ITEM__EXPANDED}
     * of a {@link DTreeItem}, expand/collapse correctly the corresponding SWT
     * {@link TreeItem}.
     */
    public void testTreeItemExpansion() {
        // Test expansion
        TreeUtils.changeDTreeItemExpansion(firstDTreeItem, false);

        TreeUtils.checkTreeItemCollapse(treeEditorBot, firstDTreeItem);

        undo("Set Expanded");

        TreeUtils.checkTreeItemCollapse(treeEditorBot, firstDTreeItem);

        redo("Set Expanded");

        TreeUtils.checkTreeItemCollapse(treeEditorBot, firstDTreeItem);

        // Test collapse
        TreeUtils.changeDTreeItemExpansion(firstDTreeItem, true);

        TreeUtils.checkTreeItemCollapse(treeEditorBot, firstDTreeItem);

        undo("Set Expanded");

        TreeUtils.checkTreeItemCollapse(treeEditorBot, firstDTreeItem);

        redo("Set Expanded");

        TreeUtils.checkTreeItemCollapse(treeEditorBot, firstDTreeItem);
    }

    /**
     * Test that changing the feature
     * {@link TreePackage#TREE_ITEM_STYLE__BACKGROUND_COLOR} of a
     * {@link DTreeItem}, update correctly the background color of the
     * corresponding SWT {@link TreeItem}.
     */
    public void testTreeItemStyleBackgroundColor() {
        // Test a first color change
        RGBValues backgroundColor = firstDTreeItem.getOwnedStyle().getBackgroundColor();

        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstDTreeItem);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, backgroundColor, ViewpointPackage.Literals.RGB_VALUES__BLUE, 0);
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        undo("Set Blue");

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        redo("Set Blue");

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        // Test a second color change
        changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, backgroundColor, ViewpointPackage.Literals.RGB_VALUES__GREEN, 100);
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        undo("Set Green");

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        redo("Set Green");

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);
    }

    /**
     * Test that changing the feature
     * {@link ViewpointPackage#BASIC_LABEL_STYLE__LABEL_SIZE} of a
     * {@link DTreeItem}, update correctly the text size in swt {@link TreeItem}
     * .
     */
    public void testTreeItemStyleLabelSize() {
        TreeItemStyle treeItemStyle = thirdDTreeItem.getOwnedStyle();
        int labelSize = treeItemStyle.getLabelSize();

        // Test a label size up
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(thirdDTreeItem);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE, labelSize + 20);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelSize(treeEditorBot, thirdDTreeItem);

        undo("Set Label Size");

        TreeUtils.checkTreeItemLabelSize(treeEditorBot, thirdDTreeItem);

        redo("Set Label Size");

        TreeUtils.checkTreeItemLabelSize(treeEditorBot, thirdDTreeItem);

        // Test a label size down
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE, labelSize + 10);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelSize(treeEditorBot, thirdDTreeItem);

        undo("Set Label Size");

        TreeUtils.checkTreeItemLabelSize(treeEditorBot, thirdDTreeItem);

        redo("Set Label Size");

        TreeUtils.checkTreeItemLabelSize(treeEditorBot, thirdDTreeItem);

    }

    /**
     * Test that changing the feature
     * {@link ViewpointPackage#BASIC_LABEL_STYLE__LABEL_FORMAT} of a
     * {@link DTreeItem}, update correctly the text format in swt
     * {@link TreeItem} .
     */
    public void testTreeItemStyleLabelFormat() {
        TreeItemStyle treeItemStyle = secondDTreeItem.getOwnedStyle();

        // Test a the bold font format
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(secondDTreeItem);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT, FontFormat.ITALIC_LITERAL);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        // Test a the italic font format
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT, FontFormat.BOLD_LITERAL);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, thirdDTreeItem);

        // Test a the normal font format
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT, FontFormat.NORMAL_LITERAL);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, thirdDTreeItem);
    }

    /**
     * Test that changing the feature
     * {@link ViewpointPackage#BASIC_LABEL_STYLE__SHOW_ICON} of a
     * {@link DTreeItem}, update correctly the {@link TreeItem}'s icon .
     */
    public void testTreeItemStyleShowIcon() {
        TreeItemStyle treeItemStyle = secondDTreeItem.getOwnedStyle();

        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(secondDTreeItem);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON, false);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemShowIcon(treeEditorBot, secondDTreeItem);

        undo("Set Show Icon");

        TreeUtils.checkTreeItemShowIcon(treeEditorBot, secondDTreeItem);

        redo("Set Show Icon");

        TreeUtils.checkTreeItemShowIcon(treeEditorBot, secondDTreeItem);

        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__SHOW_ICON, true);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemShowIcon(treeEditorBot, secondDTreeItem);

        undo("Set Show Icon");

        TreeUtils.checkTreeItemShowIcon(treeEditorBot, secondDTreeItem);

        redo("Set Show Icon");

        TreeUtils.checkTreeItemShowIcon(treeEditorBot, secondDTreeItem);
    }

    /**
     * Test that changing the feature
     * {@link ViewpointPackage#BASIC_LABEL_STYLE__LABEL_COLOR} of a
     * {@link DTreeItem}, update correctly the {@link TreeItem}'s label color .
     */
    public void testTreeItemStyleLabelColor() {
        // Test a first color change
        RGBValues labelColor = firstDTreeItem.getOwnedStyle().getLabelColor();

        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstDTreeItem);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, labelColor, ViewpointPackage.Literals.RGB_VALUES__BLUE, 0);
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        TreeUtils.checkTreeItemLabelColor(treeEditorBot, firstDTreeItem);

        undo("Set Blue");

        TreeUtils.checkTreeItemLabelColor(treeEditorBot, firstDTreeItem);

        redo("Set Blue");

        TreeUtils.checkTreeItemLabelColor(treeEditorBot, firstDTreeItem);

        // Test a second color change
        changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, labelColor, ViewpointPackage.Literals.RGB_VALUES__GREEN, 100);
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        TreeUtils.checkTreeItemLabelColor(treeEditorBot, firstDTreeItem);

        undo("Set Green");

        TreeUtils.checkTreeItemLabelColor(treeEditorBot, firstDTreeItem);

        redo("Set Green");

        TreeUtils.checkTreeItemLabelColor(treeEditorBot, firstDTreeItem);
    }

    @Override
    protected void tearDown() throws Exception {

        treeEditorBot.close();
        treeEditorBot = null;
        treeRepresentation = null;
        dTree = null;
        firstDTreeItem = null;
        secondDTreeItem = null;
        thirdDTreeItem = null;

        super.tearDown();
    }
}
