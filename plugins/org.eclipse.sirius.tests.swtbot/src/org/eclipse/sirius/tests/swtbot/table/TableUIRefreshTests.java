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
package org.eclipse.sirius.tests.swtbot.table;

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
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.tree.TreeUtils;
import org.eclipse.sirius.tests.swtbot.tree.AbstractTreeSiriusSWTBotGefTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;

/**
 * Tests on ui (swt {@link TreeItem}) update according to {@link DTable} model.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TableUIRefreshTests extends AbstractTreeSiriusSWTBotGefTestCase {

    /** Path. */
    private static final String PATH = "data/unit/tree/";

    /** Modeler resource file. */
    private static final String MODELER_RESOURCE_FILE = "ecore.odesign";

    /** Session resource file. */
    private static final String SESSION_RESOURCE_FILE = "tree.aird";

    /** Semantic resource file. */
    private static final String SEMANTIC_RESOURCE_FILE = "tree.ecore";

    private SWTBotEditor tableEditorBot;

    private UITableRepresentation tableRepresentation;

    private DTable dTable;

    private DColumn firstDColumn;

    private DLine firstDLine;

    private DLine secondDLine;

    private DCell firstDCellOfFirstDLine;

    private DCell firstDCellOfSecondDLine;

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

        tableRepresentation = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(EcoreModeler.DESIGN_VIEWPOINT_NAME).selectRepresentation(EcoreModeler.TABLES_DESC_NAME)
                .selectRepresentationInstance("new Classes", UITableRepresentation.class).open();

        tableEditorBot = tableRepresentation.getEditor();
        DTableEditor dTableEditor = (DTableEditor) tableEditorBot.getReference().getEditor(false);
        dTable = (DTable) dTableEditor.getRepresentation();

        firstDColumn = dTable.getColumns().get(0);

        firstDLine = dTable.getLines().get(0);
        secondDLine = dTable.getLines().get(1);

        firstDCellOfFirstDLine = firstDLine.getCells().get(0);
        firstDCellOfSecondDLine = secondDLine.getCells().get(0);
    }

    /**
     * Test that changing the feature {@link TablePackage#DLINE__COLLAPSED} of a
     * {@link DLine}, expand/collapse correctly the corresponding SWT
     * {@link TreeItem}.
     */
    public void testTreeItemExpansion() {
        // Test expansion
        TreeUtils.changeDLineCollapse(firstDLine, true);

        TreeUtils.checkTreeItemExpansion(tableEditorBot, firstDLine);

        undo("Set Collapsed");

        TreeUtils.checkTreeItemExpansion(tableEditorBot, firstDLine);

        redo("Set Collapsed");

        TreeUtils.checkTreeItemExpansion(tableEditorBot, firstDLine);

        // Test collapse
        TreeUtils.changeDLineCollapse(firstDLine, false);

        TreeUtils.checkTreeItemExpansion(tableEditorBot, firstDLine);

        undo("Set Collapsed");

        TreeUtils.checkTreeItemExpansion(tableEditorBot, firstDLine);

        redo("Set Collapsed");

        TreeUtils.checkTreeItemExpansion(tableEditorBot, firstDLine);
    }

    /**
     * Test that changing the feature
     * {@link TablePackage#DCELL_STYLE__BACKGROUND_COLOR} of a {@link DCell},
     * update correctly the background color of the corresponding SWT
     * {@link TreeItem}.
     */
    public void testTreeItemStyleBackgroundColor() {
        // Test a first color change
        Option<DTableElementStyle> optionalBackgroundStyleToApply = new DCellQuery(firstDCellOfFirstDLine).getBackgroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalBackgroundStyleToApply.some());
        DTableElementStyle dTableElementStyle = optionalBackgroundStyleToApply.get();
        RGBValues backgroundColor = dTableElementStyle.getBackgroundColor();
        if (backgroundColor == null) {
            backgroundColor = ViewpointFactory.eINSTANCE.createRGBValues();
            TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstDCellOfFirstDLine);
            CommandStack commandStack = transactionalEditingDomain.getCommandStack();
            Command addBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR, backgroundColor);
            commandStack.execute(addBackgroundColorCmd);
        }

        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstDCellOfFirstDLine);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, backgroundColor, ViewpointPackage.Literals.RGB_VALUES__BLUE, 200);
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);

        undo("Set Blue");

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);

        redo("Set Blue");

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);

        // Test a second color change
        changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, backgroundColor, ViewpointPackage.Literals.RGB_VALUES__GREEN, 255);
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);

        undo("Set Green");

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);

        redo("Set Green");

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);
    }

    /**
     * Test that changing the feature
     * {@link TablePackage#DCELL_STYLE__LABEL_SIZE} of a {@link DCell}, update
     * correctly the text size in swt {@link TreeItem} .
     */
    public void testTreeItemStyleLabelSize() {
        Option<DTableElementStyle> optionalForegroundStyleToApply = new DCellQuery(firstDCellOfSecondDLine).getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        DTableElementStyle dTableElementStyle = optionalForegroundStyleToApply.get();
        int labelSize = dTableElementStyle.getLabelSize();

        // Test a label size up
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(dTableElementStyle);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_SIZE, labelSize + 20);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelSize(tableEditorBot, firstDCellOfSecondDLine);

        undo("Set Label Size");

        TreeUtils.checkTreeItemLabelSize(tableEditorBot, firstDCellOfSecondDLine);

        redo("Set Label Size");

        TreeUtils.checkTreeItemLabelSize(tableEditorBot, firstDCellOfSecondDLine);

        // Test a label size down
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_SIZE, labelSize + 10);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelSize(tableEditorBot, firstDCellOfSecondDLine);

        undo("Set Label Size");

        TreeUtils.checkTreeItemLabelSize(tableEditorBot, firstDCellOfSecondDLine);

        redo("Set Label Size");

        TreeUtils.checkTreeItemLabelSize(tableEditorBot, firstDCellOfSecondDLine);

    }

    /**
     * Test that changing the feature
     * {@link TablePackage#DCELL_STYLE__LABEL_FORMAT} of a {@link DCell}, update
     * correctly the text format in swt {@link TreeItem} .
     */
    public void testTreeItemStyleLabelFormat() {
        Option<DTableElementStyle> optionalForegroundStyleToApply = new DCellQuery(firstDCellOfSecondDLine).getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        DTableElementStyle dTableElementStyle = optionalForegroundStyleToApply.get();

        // Test a the bold font format
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstDCellOfSecondDLine);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_FORMAT, FontFormat.BOLD_LITERAL);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        // Test a the italic font format
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_FORMAT, FontFormat.ITALIC_LITERAL);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);
    }

    /**
     * Test that changing the feature
     * {@link TablePackage#DCELL_STYLE__FOREGROUND_COLOR} of a {@link DCell},
     * update correctly the {@link TreeItem}'s label color .
     */
    public void testTreeItemStyleLabelColor() {
        // Test a first color change
        Option<DTableElementStyle> optionalForegroundStyleToApply = new DCellQuery(firstDCellOfFirstDLine).getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        DTableElementStyle dTableElementStyle = optionalForegroundStyleToApply.get();
        RGBValues labelColor = dTableElementStyle.getForegroundColor();
        if (labelColor == null) {
            labelColor = ViewpointFactory.eINSTANCE.createRGBValues();
            TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstDCellOfFirstDLine);
            CommandStack commandStack = transactionalEditingDomain.getCommandStack();
            Command addForegroundColorCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR, labelColor);
            commandStack.execute(addForegroundColorCmd);
        }

        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstDCellOfFirstDLine);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, labelColor, ViewpointPackage.Literals.RGB_VALUES__BLUE, 100);
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        TreeUtils.checkTreeItemLabelColor(tableEditorBot, firstDCellOfFirstDLine);

        undo("Set Blue");

        TreeUtils.checkTreeItemLabelColor(tableEditorBot, firstDCellOfFirstDLine);

        redo("Set Blue");

        TreeUtils.checkTreeItemLabelColor(tableEditorBot, firstDCellOfFirstDLine);

        // Test a second color change
        changeDTreeItemBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, labelColor, ViewpointPackage.Literals.RGB_VALUES__GREEN, 100);
        commandStack.execute(changeDTreeItemBackgroundColorCmd);

        undo("Set Green");

        TreeUtils.checkTreeItemLabelColor(tableEditorBot, firstDCellOfFirstDLine);

        redo("Set Green");

        TreeUtils.checkTreeItemLabelColor(tableEditorBot, firstDCellOfFirstDLine);
    }

    /**
     * Test that changing the feature
     * {@link TablePackage#DTABLE__HEADER_COLUMN_WIDTH} of a {@link DTable},
     * update correctly the ColumnItem's header width.
     */
    public void testColumnItemHeaderWidth() {
        int headerColumnWidth = dTable.getHeaderColumnWidth();

        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(dTable);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTableHeaderColumnWidthCmd = SetCommand.create(transactionalEditingDomain, dTable, TablePackage.Literals.DTABLE__HEADER_COLUMN_WIDTH, headerColumnWidth + 100);
        commandStack.execute(changeDTableHeaderColumnWidthCmd);

        TreeUtils.checkTreeHeaderColumnWidth(tableEditorBot, dTable);

        undo("Set Header Column Width");

        TreeUtils.checkTreeHeaderColumnWidth(tableEditorBot, dTable);

        redo("Set Header Column Width");

        TreeUtils.checkTreeHeaderColumnWidth(tableEditorBot, dTable);

    }

    /**
     * Test that changing the feature {@link TablePackage#DCOLUMN__WIDTH} of a
     * {@link DTable}, update correctly the ColumnItem's width.
     */
    public void testColumnItemWidth() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        int columnWidth = firstDColumn.getWidth();

        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstDColumn);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command changeDTableHeaderColumnWidthCmd = SetCommand.create(transactionalEditingDomain, firstDColumn, TablePackage.Literals.DCOLUMN__WIDTH, columnWidth - 100);
        commandStack.execute(changeDTableHeaderColumnWidthCmd);

        TreeUtils.checkTreeColumnWidth(tableEditorBot, firstDColumn);

        undo("Set Width");

        TreeUtils.checkTreeColumnWidth(tableEditorBot, firstDColumn);

        redo("Set Width");

        TreeUtils.checkTreeColumnWidth(tableEditorBot, firstDColumn);
    }

    @Override
    protected void tearDown() throws Exception {

        tableEditorBot = null;
        tableRepresentation = null;
        dTable = null;
        firstDColumn = null;
        firstDLine = null;
        secondDLine = null;
        firstDCellOfFirstDLine = null;
        firstDCellOfSecondDLine = null;

        super.tearDown();
    }
}
