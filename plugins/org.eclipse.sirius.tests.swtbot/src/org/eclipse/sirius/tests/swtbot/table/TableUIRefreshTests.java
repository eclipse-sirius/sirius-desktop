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
package org.eclipse.sirius.tests.swtbot.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
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
    public void testTableElementExpansion() {
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
    public void testTableElementStyleBackgroundColor() {
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
        Command changeDTableElementBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR,
                RGBValues.create(backgroundColor.getRed(), backgroundColor.getGreen(), 200));
        commandStack.execute(changeDTableElementBackgroundColorCmd);

        // Now that RGBValues is no more an EObject, the corresponding
        // GenFeature children properties are set to false. The
        // SetCommand.create creates a non wrapped SetCommand, during execution
        // EMFCommandOperation does not just take the label of the executed
        // command but improves it.
        String cmdLabel = "Set Background Color";

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);

        undo(cmdLabel);

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);

        redo(cmdLabel);

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);

        // Test a second color change
        changeDTableElementBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR,
                RGBValues.create(backgroundColor.getRed(), 255, 200));
        commandStack.execute(changeDTableElementBackgroundColorCmd);

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);

        undo(cmdLabel);

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);

        redo(cmdLabel);

        TreeUtils.checkTreeItemBackgroundColor(tableEditorBot, firstDCellOfFirstDLine);
    }

    /**
     * Test that changing the feature
     * {@link TablePackage#DCELL_STYLE__LABEL_SIZE} of a {@link DCell}, update
     * correctly the text size in swt {@link TreeItem} .
     */
    public void testTableElementStyleLabelSize() {
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

        // Test a label size down
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_SIZE, 0);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        // Validates that even if the table model element feature label size is
        // 0, the graphical table item label size is 1
        TreeUtils.checkTreeItemLabelSize(tableEditorBot, firstDCellOfSecondDLine, 1);
    }

    /**
     * Test that changing the feature
     * {@link TablePackage#DCELL_STYLE__LABEL_FORMAT} of a {@link DCell}, update
     * correctly the text format in swt {@link TreeItem} .
     */
    public void testTableElementStyleLabelFormat() {
        Option<DTableElementStyle> optionalForegroundStyleToApply = new DCellQuery(firstDCellOfSecondDLine).getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        DTableElementStyle dTableElementStyle = optionalForegroundStyleToApply.get();

        // Test a the bold font format
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstDCellOfSecondDLine);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        List<FontFormat> labelFormat = new ArrayList<FontFormat>();
        FontFormatHelper.setFontFormat(labelFormat, FontFormat.BOLD_LITERAL);
        Command changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_FORMAT, labelFormat);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        // Test a the italic font format
        labelFormat = new ArrayList<FontFormat>();
        FontFormatHelper.setFontFormat(labelFormat, FontFormat.ITALIC_LITERAL);
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_FORMAT, labelFormat);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        // Test a the underline font format
        labelFormat = new ArrayList<FontFormat>();
        FontFormatHelper.setFontFormat(labelFormat, FontFormat.UNDERLINE_LITERAL);
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_FORMAT, labelFormat);
        commandStack.execute(changeDTreeItemLabelStyleCmd);

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        undo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        redo("Set Label Format");

        TreeUtils.checkTreeItemLabelFormat(tableEditorBot, firstDCellOfSecondDLine);

        // Test a the strike through font format
        labelFormat = new ArrayList<FontFormat>();
        FontFormatHelper.setFontFormat(labelFormat, FontFormat.STRIKE_THROUGH_LITERAL);
        changeDTreeItemLabelStyleCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_FORMAT, labelFormat);
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
    public void testTableElementStyleLabelColor() {
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
        Command changeDTableElementBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR,
                RGBValues.create(labelColor.getRed(), labelColor.getGreen(), 100));
        commandStack.execute(changeDTableElementBackgroundColorCmd);

        // Now that RGBValues is no more an EObject, the corresponding
        // GenFeature children properties are set to false. The
        // SetCommand.create creates a non wrapped SetCommand, during execution
        // EMFCommandOperation does not just take the label of the executed
        // command but improves it.
        String cmdLabel = "Set Foreground Color";

        TreeUtils.checkTreeItemLabelColor(tableEditorBot, firstDCellOfFirstDLine);

        undo(cmdLabel);

        TreeUtils.checkTreeItemLabelColor(tableEditorBot, firstDCellOfFirstDLine);

        redo(cmdLabel);

        TreeUtils.checkTreeItemLabelColor(tableEditorBot, firstDCellOfFirstDLine);

        // Test a second color change
        changeDTableElementBackgroundColorCmd = SetCommand.create(transactionalEditingDomain, dTableElementStyle, TablePackage.Literals.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR,
                RGBValues.create(labelColor.getRed(), 100, 100));
        commandStack.execute(changeDTableElementBackgroundColorCmd);

        undo(cmdLabel);

        TreeUtils.checkTreeItemLabelColor(tableEditorBot, firstDCellOfFirstDLine);

        redo(cmdLabel);

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
