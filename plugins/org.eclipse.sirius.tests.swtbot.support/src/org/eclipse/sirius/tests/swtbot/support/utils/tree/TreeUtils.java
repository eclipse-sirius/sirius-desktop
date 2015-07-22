/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.utils.tree;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.utils.TreeColumnWidthQuery;
import org.eclipse.sirius.table.ui.tools.internal.editor.utils.TreeColumnWidthSetter;
import org.eclipse.sirius.tests.support.api.TreeItemImageQuery;
import org.eclipse.sirius.tests.support.api.TreeItemLabelFontFormatQuery;
import org.eclipse.sirius.tests.swtbot.support.api.widget.TreeItemBackgroundColorQuery;
import org.eclipse.sirius.tests.swtbot.support.api.widget.TreeItemExpandedQuery;
import org.eclipse.sirius.tests.swtbot.support.api.widget.TreeItemExpander;
import org.eclipse.sirius.tests.swtbot.support.api.widget.TreeItemLabelColorQuery;
import org.eclipse.sirius.tests.swtbot.support.api.widget.TreeItemLabelSizeQuery;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.ui.tools.internal.util.ItemSearcher;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.junit.Assert;

/**
 * A Utility for {@link TreeItem} according to {@link DTreeItem}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class TreeUtils {

    private static final String COLLAPSE = "collapse";

    private static final String EXPANDED = "expanded";

    private TreeUtils() {
        // To not instanciate
    }

    /**
     * Change the {@link TablePackage#DLINE__COLLAPSED} feature of a
     * {@link DLine}.
     * 
     * @param dLine
     *            the {@link DLine}
     * @param collapse
     *            the boolean to set for the feature
     */
    public static void changeDLineCollapse(DLine dLine, boolean collapse) {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(dLine);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command expandCarDTreeItemCmd = SetCommand.create(transactionalEditingDomain, dLine, TablePackage.Literals.DLINE__COLLAPSED, collapse);
        commandStack.execute(expandCarDTreeItemCmd);
    }

    /**
     * Change the {@link TreePackage#DTREE_ITEM__EXPANDED} feature of a
     * {@link DLine}.
     * 
     * @param dTreeItem
     *            the {@link DTreeItem}
     * @param expand
     *            the boolean to set for the feature
     */
    public static void changeDTreeItemExpansion(DTreeItem dTreeItem, boolean expand) {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(dTreeItem);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command expandCarDTreeItemCmd = SetCommand.create(transactionalEditingDomain, dTreeItem, TreePackage.Literals.DTREE_ITEM__EXPANDED, expand);
        commandStack.execute(expandCarDTreeItemCmd);
    }

    /**
     * Collapse a swt {@link TreeItem} corresponding to the specified
     * {@link DTreeItem}.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the {@link TreeItem} to collapse
     *            referencing the specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     */
    public static void collapseTreeItem(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem) {
        TreeUtils.changeExpansionTreeItem(swtBotTreeEditor, dTreeItem, false);
    }

    /**
     * Expand a swt {@link TreeItem} corresponding to the specified
     * {@link DTreeItem}.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the {@link TreeItem} to expand
     *            referencing the specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     */
    public static void expandTreeItem(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem) {
        TreeUtils.changeExpansionTreeItem(swtBotTreeEditor, dTreeItem, true);
    }

    /**
     * Change expansion state of a swt {@link TreeItem} corresponding to the
     * specified {@link DTreeItem}.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the {@link TreeItem} to change expansion
     *            referencing the specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     * @param expansion
     *            true to expand, false to collapse
     */
    public static void changeExpansionTreeItem(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem, boolean expansion) {
        DTreeEditor dTreeEditor = (DTreeEditor) swtBotTreeEditor.getReference().getEditor(false);
        Tree tree = (Tree) dTreeEditor.getControl();
        ItemSearcher itemSearcher = new ItemSearcher(tree, dTreeItem);
        Display.getDefault().syncExec(itemSearcher);
        TreeItem treeItem = (TreeItem) itemSearcher.getResult();
        TreeItemExpander treeItemExpander = new TreeItemExpander(treeItem, expansion);
        Display.getDefault().syncExec(treeItemExpander);
        SWTBotUtils.waitProgressMonitorClose("Progress Information");
    }

    /**
     * Collapse a swt {@link TreeItem} corresponding to the specified
     * {@link DLine}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the {@link TreeItem} to collapse
     *            referencing the specified {@link DLine}
     * @param dLine
     *            the {@link DLine}
     */
    public static void collapseTreeItem(SWTBotEditor swtBotTableEditor, DLine dLine) {
        TreeUtils.changeExpansionTreeItem(swtBotTableEditor, dLine, false);
    }

    /**
     * Expand a swt {@link TreeItem} corresponding to the specified
     * {@link DLine}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the {@link TreeItem} to expand
     *            referencing the specified {@link DLine}
     * @param dLine
     *            the {@link DLine}
     */
    public static void expandTreeItem(SWTBotEditor swtBotTableEditor, DLine dLine) {
        TreeUtils.changeExpansionTreeItem(swtBotTableEditor, dLine, true);
    }

    /**
     * Change expansion state of a swt {@link TreeItem} corresponding to the
     * specified {@link DLine}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the {@link TreeItem} to change expansion
     *            referencing the specified {@link DLine}
     * @param dLine
     *            the {@link DLine}
     * @param expansion
     *            true to expand, false to collapse
     */
    public static void changeExpansionTreeItem(SWTBotEditor swtBotTableEditor, DLine dLine, boolean expansion) {
        DTableEditor dTableEditor = (DTableEditor) swtBotTableEditor.getReference().getEditor(false);
        Tree tree = (Tree) dTableEditor.getControl();
        ItemSearcher itemSearcher = new ItemSearcher(tree, dLine);
        Display.getDefault().syncExec(itemSearcher);
        TreeItem treeItem = (TreeItem) itemSearcher.getResult();
        TreeItemExpander treeItemExpander = new TreeItemExpander(treeItem, expansion);
        Display.getDefault().syncExec(treeItemExpander);
    }

    /**
     * Checks that the swt {@link TreeItem} expansion corresponds to the
     * specified {@link DLine} expansion.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the {@link TreeItem} referencing the
     *            specified {@link DLine}
     * @param dLine
     *            the {@link DLine}
     */
    public static void checkTreeItemExpansion(SWTBotEditor swtBotTableEditor, DLine dLine) {
        boolean collapsed = dLine.isCollapsed();
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTableEditor, dLine);
        boolean widgetExpansion = TreeUtils.getWidgetExpansion(treeItem);
        Assert.assertEquals("As the DLine is " + (collapsed ? TreeUtils.COLLAPSE : TreeUtils.EXPANDED) + ", its TreeItem should be also " + (collapsed ? TreeUtils.COLLAPSE : TreeUtils.EXPANDED),
                !collapsed, widgetExpansion);
    }

    /**
     * Checks that the swt {@link TreeItem} expansion corresponds to the
     * specified {@link DTreeItem} expansion.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the {@link TreeItem} referencing the
     *            specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     */
    public static void checkTreeItemCollapse(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem) {
        boolean expanded = dTreeItem.isExpanded();
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTreeEditor, dTreeItem);
        boolean widgetExpansion = TreeUtils.getWidgetExpansion(treeItem);
        Assert.assertEquals("As the DTreeitem is " + (expanded ? TreeUtils.COLLAPSE : TreeUtils.EXPANDED) + ", its TreeItem should be also " + (expanded ? TreeUtils.COLLAPSE : TreeUtils.EXPANDED),
                expanded, widgetExpansion);
    }

    /**
     * Change the {@link TablePackage#DLINE__COLLAPSED} feature of a
     * {@link DLine}.
     * 
     * @param dTreeItem
     *            the {@link DTreeItem}
     * @param newBackgroundColor
     *            the new background color to use
     */
    public static void changeDTreeItemBackgroundColor(DTreeItem dTreeItem, RGBValues newBackgroundColor) {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(dTreeItem);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTreeItemBackgroundColorCmd = SetCommand
                .create(transactionalEditingDomain, dTreeItem.getOwnedStyle(), TreePackage.Literals.TREE_ITEM_STYLE__BACKGROUND_COLOR, newBackgroundColor);
        commandStack.execute(changeDTreeItemBackgroundColorCmd);
    }

    /**
     * Checks that the swt {@link TreeItem} background color corresponds to the
     * specified {@link DTreeItem} background color.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the {@link TreeItem} referencing the
     *            specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     */
    public static void checkTreeItemBackgroundColor(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem) {
        RGBValues backgroundColor = dTreeItem.getOwnedStyle().getBackgroundColor();
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTreeEditor, dTreeItem);
        RGBValues widgetBackgroundColor = TreeUtils.getWidgetBackgroundColor(treeItem);
        // CHECKSTYLE:OFF
        String backgroundColorMessage = "\t DTreeItem.backgroundColor : (" + backgroundColor.getRed() + "," + backgroundColor.getGreen() + "," + backgroundColor.getBlue() + ")\n";
        String widgetBackgroundColorMessage = "\t SWTTreeItem.backgroundColor : (" + widgetBackgroundColor.getRed() + "," + widgetBackgroundColor.getGreen() + "," + widgetBackgroundColor.getBlue()
                + ")\n";
        String failureMessage = "The background color from the DTreeItem and this from the swt widget differs : \n" + backgroundColorMessage + widgetBackgroundColorMessage;
        // CHECKSTYLE:ON
        Assert.assertEquals(failureMessage, backgroundColor.getRed(), widgetBackgroundColor.getRed());
        Assert.assertEquals(failureMessage, backgroundColor.getGreen(), widgetBackgroundColor.getGreen());
        Assert.assertEquals(failureMessage, backgroundColor.getBlue(), widgetBackgroundColor.getBlue());
    }

    /**
     * Checks that the swt {@link TreeItem} background color corresponds to the
     * specified {@link DCell} background color.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the {@link TreeItem} referencing the
     *            specified {@link DCell}
     * @param dCell
     *            the {@link DCell}
     */
    public static void checkTreeItemBackgroundColor(SWTBotEditor swtBotTableEditor, DCell dCell) {
        Option<DTableElementStyle> optionalBackgroundStyleToApply = new DCellQuery(dCell).getBackgroundStyleToApply();
        Assert.assertTrue("We should have a background style for the cell.", optionalBackgroundStyleToApply.some());
        RGBValues backgroundColor = optionalBackgroundStyleToApply.get().getBackgroundColor();
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTableEditor, dCell.getLine());
        int dCellIndex = new DCellQuery(dCell).getColumnIndex();
        RGBValues widgetBackgroundColor = TreeUtils.getWidgetBackgroundColor(treeItem, dCellIndex + 1);
        // CHECKSTYLE:OFF
        String backgroundColorMessage = "\t DCellStyle.backgroundColor : (" + backgroundColor.getRed() + "," + backgroundColor.getGreen() + "," + backgroundColor.getBlue() + ")\n";
        // CHECKSTYLE:ON
        String widgetBackgroundColorMessage = "\t SWTTreeItem.backgroundColor : (" + widgetBackgroundColor.getRed() + "," + widgetBackgroundColor.getGreen() + "," + widgetBackgroundColor.getBlue()
                + ")\n";
        String failureMessage = "The background color from the DCellStyle and this from the swt widget differs : \n" + backgroundColorMessage + widgetBackgroundColorMessage;

        Assert.assertEquals(failureMessage, backgroundColor.getRed(), widgetBackgroundColor.getRed());
        Assert.assertEquals(failureMessage, backgroundColor.getGreen(), widgetBackgroundColor.getGreen());
        Assert.assertEquals(failureMessage, backgroundColor.getBlue(), widgetBackgroundColor.getBlue());
    }

    /**
     * Checks the swt Column width for a specified {@link DColumn}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the swt column referencing the specified
     *            {@link DColumn}
     * @param dColumn
     *            the {@link DColumn}
     */
    public static void checkColumnWidth(SWTBotEditor swtBotTableEditor, DColumn dColumn) {
        int dColumnWidh = dColumn.getWidth();
        int treeColumnWidth = TreeUtils.getColumnWidth(swtBotTableEditor, dColumn);
        Assert.assertEquals("The TreeColumn width should corresponds to its DColumn.width", dColumnWidh, treeColumnWidth);
    }

    /**
     * Checks the swt {@link TreeItem#getFont()}'s label size for a specified
     * {@link DTreeItem}.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     */
    public static void checkTreeItemLabelSize(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem) {
        int labelSize = dTreeItem.getOwnedStyle().getLabelSize();
        checkTreeItemLabelSize(swtBotTreeEditor, dTreeItem, labelSize);
    }

    /**
     * Checks the swt {@link TreeItem#getFont()}'s label size for a specified
     * {@link DTreeItem}.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     * @param expectedLabelSize
     *            expected size of the label
     */
    public static void checkTreeItemLabelSize(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem, int expectedLabelSize) {
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTreeEditor, dTreeItem);
        int treeItemLabelSize = TreeUtils.getWidgetLabelSize(treeItem);
        Assert.assertEquals("The TreeItem label size should corresponds to its DTreeItem.ownedStyle.labelSize", expectedLabelSize, treeItemLabelSize);
    }

    /**
     * Checks the swt {@link TreeItem#getFont()}'s label size for a specified
     * {@link DCell}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DCell}
     * @param dCell
     *            the {@link DCell}
     */
    public static void checkTreeItemLabelSize(SWTBotEditor swtBotTableEditor, DCell dCell) {
        Option<DTableElementStyle> optionalForegroundStyleToApply = new DCellQuery(dCell).getForegroundStyleToApply();
        Assert.assertTrue("We should have a foreground Style for the cell.", optionalForegroundStyleToApply.some());
        int labelSize = optionalForegroundStyleToApply.get().getLabelSize();
        checkTreeItemLabelSize(swtBotTableEditor, dCell, labelSize);
    }

    /**
     * Checks the swt {@link TreeItem#getFont()}'s label size for a specified
     * {@link DCell}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DCell}
     * @param dCell
     *            the {@link DCell}
     * @param expectedLabelSize
     *            expected size of the label
     */
    public static void checkTreeItemLabelSize(SWTBotEditor swtBotTableEditor, DCell dCell, int expectedLabelSize) {
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTableEditor, dCell.getLine());
        int dCellIndex = new DCellQuery(dCell).getColumnIndex();
        int treeItemLabelSize = TreeUtils.getWidgetLabelSize(treeItem, dCellIndex + 1);
        Assert.assertEquals("The TreeItem label size should corresponds to its DCell.currentStyle.labelSize", expectedLabelSize, treeItemLabelSize);
    }

    /**
     * Checks the swt {@link TreeItem#getFont()}'s label format for a specified
     * {@link DTreeItem}.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     */
    public static void checkTreeItemLabelFormat(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem) {
        List<FontFormat> fontFormat = dTreeItem.getOwnedStyle().getLabelFormat();
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTreeEditor, dTreeItem);
        List<FontFormat> widgetFontFormat = TreeUtils.getWidgetLabelFormat(treeItem);
        Assert.assertEquals("The TreeItem label font format should corresponds to its DTreeItem.ownedStyle.fontFormat", fontFormat, widgetFontFormat);
    }

    /**
     * Checks the swt {@link TreeItem#getFont()}'s label format for a specified
     * {@link DCell}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DCell}
     * @param dCell
     *            the {@link DCell}
     */
    public static void checkTreeItemLabelFormat(SWTBotEditor swtBotTableEditor, DCell dCell) {
        Option<DTableElementStyle> optionalForegroundStyleToApply = new DCellQuery(dCell).getForegroundStyleToApply();
        Assert.assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        List<FontFormat> fontFormat = optionalForegroundStyleToApply.get().getLabelFormat();
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTableEditor, dCell.getLine());
        int dCellIndex = new DCellQuery(dCell).getColumnIndex();
        List<FontFormat> widgetFontFormat = TreeUtils.getWidgetLabelFormat(treeItem, dCellIndex + 1);
        Assert.assertEquals("The TreeItem label font format should corresponds to its DCell.currentStyle.fontFormat", fontFormat, widgetFontFormat);
    }

    /**
     * Checks the swt {@link TreeItem#getImage()} is not null for a specified
     * {@link DTreeItem}.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     */
    public static void checkTreeItemShowIcon(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem) {
        boolean showIcon = dTreeItem.getOwnedStyle().isShowIcon();
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTreeEditor, dTreeItem);
        boolean widgetShowIcon = TreeUtils.hasWidgetWithIcon(treeItem);
        Assert.assertEquals("The TreeItem.image nullity should corresponds to its DTreeItem.ownedStyle.showIcon", showIcon, widgetShowIcon);
    }

    /**
     * Checks the swt {@link TreeItem#getFont()}'s label color for a specified
     * {@link DTreeItem}.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     */
    public static void checkTreeItemLabelColor(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem) {
        RGBValues labelColor = dTreeItem.getOwnedStyle().getLabelColor();
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTreeEditor, dTreeItem);
        RGBValues widgetLabelColor = TreeUtils.getWidgetLabelColor(treeItem);
        // CHECKSTYLE:OFF
        String backgroundColorMessage = "\t DTreeItem.ownedStyle.labelColor : (" + labelColor.getRed() + "," + labelColor.getGreen() + "," + labelColor.getBlue() + ")\n";
        // CHECKSTYLE:ON
        String widgetBackgroundColorMessage = "\t SWTTreeItem.backgroundColor : (" + widgetLabelColor.getRed() + "," + widgetLabelColor.getGreen() + "," + widgetLabelColor.getBlue() + ")\n";
        String failureMessage = "The label color from the DTreeItem and this from the swt widget differs : \n" + backgroundColorMessage + widgetBackgroundColorMessage;

        Assert.assertEquals(failureMessage, labelColor.getRed(), widgetLabelColor.getRed());
        Assert.assertEquals(failureMessage, labelColor.getGreen(), widgetLabelColor.getGreen());
        Assert.assertEquals(failureMessage, labelColor.getBlue(), widgetLabelColor.getBlue());
    }

    /**
     * Checks the swt {@link TreeItem#getForeground()}'s label color for a
     * specified {@link DCell}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DCell}
     * @param dCell
     *            the {@link DCell}
     */
    public static void checkTreeItemLabelColor(SWTBotEditor swtBotTableEditor, DCell dCell) {
        Option<DTableElementStyle> optionalForegroundStyleToApply = new DCellQuery(dCell).getForegroundStyleToApply();
        Assert.assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        RGBValues foregroundColor = optionalForegroundStyleToApply.get().getForegroundColor();
        TreeItem treeItem = TreeUtils.getTreeItem(swtBotTableEditor, dCell.getLine());
        int dCellIndex = new DCellQuery(dCell).getColumnIndex();
        RGBValues widgetForegroundColor = TreeUtils.getWidgetLabelColor(treeItem, dCellIndex + 1);
        // CHECKSTYLE:OFF
        String backgroundColorMessage = "\t DCellStyle.foregroundColor : (" + foregroundColor.getRed() + "," + foregroundColor.getGreen() + "," + foregroundColor.getBlue() + ")\n";
        // CHECKSTYLE:ON
        String widgetBackgroundColorMessage = "\t SWTTreeItem.oregroundColor : (" + widgetForegroundColor.getRed() + "," + widgetForegroundColor.getGreen() + "," + widgetForegroundColor.getBlue()
                + ")\n";
        String failureMessage = "The label color from the DCellStyle and this from the swt widget differs : \n" + backgroundColorMessage + widgetBackgroundColorMessage;

        Assert.assertEquals(failureMessage, foregroundColor.getRed(), widgetForegroundColor.getRed());
        Assert.assertEquals(failureMessage, foregroundColor.getGreen(), widgetForegroundColor.getGreen());
        Assert.assertEquals(failureMessage, foregroundColor.getBlue(), widgetForegroundColor.getBlue());
    }

    /**
     * Resize the header column with the specified new width on the specified
     * editor.
     * 
     * @param swtBotTableEditor
     *            the specified bot of the table editor
     * @param dTable
     *            the {@link DTable} for which to resize the header column
     * @param newWidth
     *            the new width
     */
    public static void resizeTreeHeaderColumnWidth(SWTBotEditor swtBotTableEditor, DTable dTable, int newWidth) {
        TreeUtils.resizeTreeColumnWidth(swtBotTableEditor, null, newWidth);
    }

    /**
     * Resize the column with the specified new width on the specified editor.
     * 
     * @param swtBotTableEditor
     *            the specified bot of the table editor
     * @param dColumn
     *            the {@link DColumn} for which to resize the TreeColumn
     * @param newWidth
     *            the new width
     */
    public static void resizeTreeColumnWidth(SWTBotEditor swtBotTableEditor, DColumn dColumn, int newWidth) {
        DTableEditor dTableEditor = (DTableEditor) swtBotTableEditor.getReference().getEditor(false);
        Tree tree = (Tree) dTableEditor.getControl();
        ItemSearcher itemSearcher = new ItemSearcher(tree, null);
        Display.getDefault().syncExec(itemSearcher);
        Item item = itemSearcher.getResult();
        Assert.assertTrue("item should be a TreeColumn", item instanceof TreeColumn);
        TreeColumn treeColumn = (TreeColumn) item;
        TreeColumnWidthSetter treeColumnWidthSetter = new TreeColumnWidthSetter(treeColumn, newWidth);
        Display.getDefault().syncExec(treeColumnWidthSetter);
    }

    /**
     * Checks the swt Tree.columns[0]'s width for a specified {@link DTable}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DTable}
     * @param dTable
     *            the {@link DTable}
     */
    public static void checkTreeHeaderColumnWidth(SWTBotEditor swtBotTableEditor, DTable dTable) {
        int widgetHeaderColumnWidth = TreeUtils.getColumnWidth(swtBotTableEditor, null);
        int modelHeaderColumnWidth = dTable.getHeaderColumnWidth();
        Assert.assertEquals("DTable.headerColumnWidth should corresponds to the Tree.treeColumn[0].width", modelHeaderColumnWidth, widgetHeaderColumnWidth);
    }

    /**
     * Checks the swt TreeColumn's width for a specified {@link DColumn}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the swt {@link TreeItem} referencing the
     *            specified {@link DColumn}
     * @param dColumn
     *            the {@link DColumn}
     */
    public static void checkTreeColumnWidth(SWTBotEditor swtBotTableEditor, DColumn dColumn) {
        int widgetHeaderColumnWidth = TreeUtils.getColumnWidth(swtBotTableEditor, dColumn);
        int modelColumnWidth = dColumn.getWidth();
        Assert.assertEquals("DColumn.width should corresponds to the TreeColumn.width", modelColumnWidth, widgetHeaderColumnWidth);
    }

    private static TreeItem getTreeItem(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem) {
        TreeItem treeItem = null;
        DTreeEditor dTreeEditor = (DTreeEditor) swtBotTreeEditor.getReference().getEditor(false);
        Tree tree = (Tree) dTreeEditor.getControl();
        ItemSearcher itemSearcher = new ItemSearcher(tree, dTreeItem);
        Display.getDefault().syncExec(itemSearcher);
        treeItem = (TreeItem) itemSearcher.getResult();
        return treeItem;
    }

    private static TreeItem getTreeItem(SWTBotEditor swtBotTableEditor, DLine dLine) {
        TreeItem treeItem = null;
        DTableEditor dTableEditor = (DTableEditor) swtBotTableEditor.getReference().getEditor(false);
        Tree tree = (Tree) dTableEditor.getControl();
        ItemSearcher itemSearcher = new ItemSearcher(tree, dLine);
        Display.getDefault().syncExec(itemSearcher);
        treeItem = (TreeItem) itemSearcher.getResult();
        return treeItem;
    }

    /**
     * Get the swt {@link TreeItem} expansion state for a specified
     * {@link DLine}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the {@link TreeItem} referencing the
     *            specified {@link DLine}
     * @param dLine
     *            the {@link DLine}
     * 
     * @return the swt {@link TreeItem} expansion state
     */
    public static boolean getWidgetExpansion(SWTBotEditor swtBotTableEditor, DLine dLine) {
        boolean widgetExpansion = false;
        DTableEditor dTableEditor = (DTableEditor) swtBotTableEditor.getReference().getEditor(false);
        Tree tree = (Tree) dTableEditor.getControl();
        ItemSearcher itemSearcher = new ItemSearcher(tree, dLine);
        Display.getDefault().syncExec(itemSearcher);
        TreeItem treeItem = (TreeItem) itemSearcher.getResult();
        widgetExpansion = TreeUtils.getWidgetExpansion(treeItem);
        return widgetExpansion;
    }

    /**
     * Get the swt {@link TreeItem} expansion state for a specified
     * {@link DTreeItem}.
     * 
     * @param swtBotTreeEditor
     *            the editor bot owning the {@link TreeItem} referencing the
     *            specified {@link DTreeItem}
     * @param dTreeItem
     *            the {@link DTreeItem}
     * @return the swt {@link TreeItem} expansion state
     */
    public static boolean getWidgetExpansion(SWTBotEditor swtBotTreeEditor, DTreeItem dTreeItem) {
        boolean widgetExpansion = false;
        DTreeEditor dTreeEditor = (DTreeEditor) swtBotTreeEditor.getReference().getEditor(false);
        Tree tree = (Tree) dTreeEditor.getControl();
        ItemSearcher itemSearcher = new ItemSearcher(tree, dTreeItem);
        Display.getDefault().syncExec(itemSearcher);
        TreeItem treeItem = (TreeItem) itemSearcher.getResult();
        widgetExpansion = TreeUtils.getWidgetExpansion(treeItem);
        return widgetExpansion;
    }

    /**
     * Get the swt Column width for a specified {@link DColumn}.
     * 
     * @param swtBotTableEditor
     *            the editor bot owning the swt column} referencing the
     *            specified {@link DColumn}
     * @param dColumn
     *            the {@link DColumn}
     * @return the swt Column widthfor a specified {@link DColumn}
     */
    public static int getColumnWidth(SWTBotEditor swtBotTableEditor, DColumn dColumn) {
        int columnWidth = -1;
        DTableEditor dTableEditor = (DTableEditor) swtBotTableEditor.getReference().getEditor(false);
        Tree tree = (Tree) dTableEditor.getControl();
        ItemSearcher itemSearcher = new ItemSearcher(tree, dColumn);
        Display.getDefault().syncExec(itemSearcher);
        Item item = itemSearcher.getResult();
        Assert.assertTrue("item should be a TreeColumn", item instanceof TreeColumn);
        TreeColumn treeColumn = (TreeColumn) item;
        TreeColumnWidthQuery treeColumnWidthQuery = new TreeColumnWidthQuery(treeColumn);
        Display.getDefault().syncExec(treeColumnWidthQuery);
        columnWidth = treeColumnWidthQuery.getResult();
        return columnWidth;
    }

    /**
     * Get the swt {@link TreeItem} expansion state for a specified
     * {@link TreeItem}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @return the swt {@link TreeItem} expansion state
     */
    public static boolean getWidgetExpansion(TreeItem treeItem) {
        boolean widgetExpansion = false;
        TreeItemExpandedQuery treeItemExpandedQuery = new TreeItemExpandedQuery(treeItem);
        Display.getDefault().syncExec(treeItemExpandedQuery);
        widgetExpansion = treeItemExpandedQuery.getResult();
        return widgetExpansion;
    }

    /**
     * Get the swt {@link TreeItem} background color of a specified
     * {@link TreeItem}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @return the swt {@link TreeItem} background color of a specified
     *         {@link TreeItem}
     */
    private static RGBValues getWidgetBackgroundColor(TreeItem treeItem) {
        RGBValues widgetBackgroundColor = TreeUtils.getWidgetBackgroundColor(treeItem, 0);
        return widgetBackgroundColor;
    }

    /**
     * Get the swt {@link TreeItem} background color of a specified
     * {@link TreeItem}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @param index
     *            the index in the {@link TreeItem} for which to get the
     *            background color
     * @return the swt {@link TreeItem} background color of a specified
     *         {@link TreeItem}
     */
    private static RGBValues getWidgetBackgroundColor(TreeItem treeItem, int index) {
        RGBValues widgetBackgroundColor = null;
        TreeItemBackgroundColorQuery treeItemBackgroundColorQuery = new TreeItemBackgroundColorQuery(treeItem, index);
        Display.getDefault().syncExec(treeItemBackgroundColorQuery);
        widgetBackgroundColor = treeItemBackgroundColorQuery.getResult();
        return widgetBackgroundColor;
    }

    /**
     * Get the swt {@link TreeItem} label size of a specified {@link TreeItem}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @return the swt {@link TreeItem} label size a specified {@link TreeItem}
     */
    private static int getWidgetLabelSize(TreeItem treeItem) {
        int widgetLabelSize = TreeUtils.getWidgetLabelSize(treeItem, 0);
        return widgetLabelSize;
    }

    /**
     * Get the swt {@link TreeItem} label size of a specified {@link TreeItem}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @param index
     *            the index in the {@link TreeItem} for which to get the label
     *            size
     * @return the swt {@link TreeItem} label size a specified {@link TreeItem}
     */
    private static int getWidgetLabelSize(TreeItem treeItem, int index) {
        int widgetLabelSize;
        TreeItemLabelSizeQuery treeItemBackgroundColorQuery = new TreeItemLabelSizeQuery(treeItem, index);
        Display.getDefault().syncExec(treeItemBackgroundColorQuery);
        widgetLabelSize = treeItemBackgroundColorQuery.getResult();
        return widgetLabelSize;
    }

    /**
     * Get the swt {@link TreeItem} label font format of a specified
     * {@link TreeItem}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @return the swt {@link TreeItem} label font format a specified
     *         {@link TreeItem}
     */
    private static List<FontFormat> getWidgetLabelFormat(TreeItem treeItem) {
        List<FontFormat> widgetLabelFormat = TreeUtils.getWidgetLabelFormat(treeItem, 0);
        return widgetLabelFormat;
    }

    /**
     * Get the swt {@link TreeItem} label font format of a specified
     * {@link TreeItem}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @param index
     *            the index in the {@link TreeItem} for which to get the label
     *            format
     * @return the swt {@link TreeItem} label font format a specified
     *         {@link TreeItem}
     */
    private static List<FontFormat> getWidgetLabelFormat(TreeItem treeItem, int index) {
        TreeItemLabelFontFormatQuery treeItemBackgroundColorQuery = new TreeItemLabelFontFormatQuery(treeItem, index);
        Display.getDefault().syncExec(treeItemBackgroundColorQuery);
        return treeItemBackgroundColorQuery.getResult();
    }

    /**
     * Tells if the swt {@link TreeItem} has a associated {@link Image}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @return true if the swt {@link TreeItem} has a associated {@link Image},
     *         false else
     */
    private static boolean hasWidgetWithIcon(TreeItem treeItem) {
        boolean hasWidgetWithIcon = false;
        TreeItemImageQuery treeItemImageQuery = new TreeItemImageQuery(treeItem);
        Display.getDefault().syncExec(treeItemImageQuery);
        Image result = treeItemImageQuery.getResult();
        hasWidgetWithIcon = result != null;
        return hasWidgetWithIcon;
    }

    /**
     * Get the swt {@link TreeItem} label color of a specified {@link TreeItem}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @return the swt {@link TreeItem} label color of a specified
     *         {@link TreeItem}
     */
    private static RGBValues getWidgetLabelColor(TreeItem treeItem) {
        RGBValues widgetLabelColor = TreeUtils.getWidgetLabelColor(treeItem, 0);
        return widgetLabelColor;
    }

    /**
     * Get the swt {@link TreeItem} label color of a specified {@link TreeItem}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @param index
     *            the index in the {@link TreeItem} for which to get the
     *            background color
     * @return the swt {@link TreeItem} label color of a specified
     *         {@link TreeItem}
     */
    private static RGBValues getWidgetLabelColor(TreeItem treeItem, int index) {
        RGBValues widgetLabelColor = null;
        TreeItemLabelColorQuery treeItemLabelColorQuery = new TreeItemLabelColorQuery(treeItem, index);
        Display.getDefault().syncExec(treeItemLabelColorQuery);
        widgetLabelColor = treeItemLabelColorQuery.getResult();
        return widgetLabelColor;
    }

}
