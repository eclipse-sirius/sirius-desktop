/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
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
        Command changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, firstDTreeItem.getOwnedStyle(), TreePackage.Literals.TREE_ITEM_STYLE__BACKGROUND_COLOR,
                RGBValues.create(backgroundColor.getRed(), backgroundColor.getGreen(), 0));
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        // Now that RGBValues is no more an EObject, the corresponding
        // GenFeature children properties are set to false. The
        // SetCommand.create creates a non wrapped SetCommand, during execution
        // EMFCommandOperation does not just take the label of the executed
        // command but improves it.
        String cmdLabel = "Set Background Color";

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        undo(cmdLabel);

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        redo(cmdLabel);

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        // Test a second color change
        changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, firstDTreeItem.getOwnedStyle(), TreePackage.Literals.TREE_ITEM_STYLE__BACKGROUND_COLOR,
                RGBValues.create(backgroundColor.getRed(), 100, 0));
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        undo(cmdLabel);

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        redo(cmdLabel);

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

        // Test a label size down
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE, 0);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        // Validates that even if the tree model element feature label size is
        // 0, the graphical tree item label size is 1
        TreeUtils.checkTreeItemLabelSize(treeEditorBot, thirdDTreeItem, 1);
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
        List<FontFormat> labelFormat = new ArrayList<FontFormat>();
        FontFormatHelper.setFontFormat(labelFormat, FontFormat.ITALIC_LITERAL);
        Command changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT, labelFormat);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        // Test a the italic font format
        labelFormat = new ArrayList<FontFormat>();
        FontFormatHelper.setFontFormat(labelFormat, FontFormat.BOLD_LITERAL);
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT, labelFormat);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, thirdDTreeItem);

        // Test a the normal font format
        labelFormat = new ArrayList<FontFormat>();
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT, labelFormat);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, thirdDTreeItem);

        // Test a the underline font format
        labelFormat = new ArrayList<FontFormat>();
        FontFormatHelper.setFontFormat(labelFormat, FontFormat.UNDERLINE_LITERAL);
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_FORMAT, labelFormat);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        // Test a the strike through font format
        labelFormat = new ArrayList<FontFormat>();
        FontFormatHelper.setFontFormat(labelFormat, FontFormat.STRIKE_THROUGH_LITERAL);
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, treeItemStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_FORMAT, labelFormat);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(treeEditorBot, secondDTreeItem);
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
        Command changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, firstDTreeItem.getOwnedStyle(), ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR,
                RGBValues.create(labelColor.getRed(), labelColor.getGreen(), 0));
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        // Now that RGBValues is no more an EObject, the corresponding
        // GenFeature children properties are set to false. The
        // SetCommand.create creates a non wrapped SetCommand, during execution
        // EMFCommandOperation does not just take the label of the executed
        // command but improves it.
        String cmdLabel = "Set Label Color";

        TreeUtils.checkTreeItemBackgroundColor(treeEditorBot, firstDTreeItem);

        undo(cmdLabel);

        TreeUtils.checkTreeItemLabelColor(treeEditorBot, firstDTreeItem);

        redo(cmdLabel);

        TreeUtils.checkTreeItemLabelColor(treeEditorBot, firstDTreeItem);

        // Test a second color change
        changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, firstDTreeItem.getOwnedStyle(), ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR,
                RGBValues.create(labelColor.getRed(), 100, 0));
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        TreeUtils.checkTreeItemLabelColor(treeEditorBot, firstDTreeItem);

        undo(cmdLabel);

        TreeUtils.checkTreeItemLabelColor(treeEditorBot, firstDTreeItem);

        redo(cmdLabel);

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
