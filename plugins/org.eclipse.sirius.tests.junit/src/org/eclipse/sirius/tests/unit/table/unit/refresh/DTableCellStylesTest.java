/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.table.unit.refresh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.color.AbstractColorUpdater;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.SystemColors;

/**
 * Test the different styles applicable for a cell :
 * <UL>
 * <LI>Default background color</LI>
 * <LI>Conditional background color</LI>
 * <LI>Default foreground color</LI>
 * <LI>Conditional foreground color</LI>
 * <LI>Default foreground font</LI>
 * <LI>Conditional foreground font</LI>
 * <LI>Default foreground size</LI>
 * <LI>Conditional foreground size</LI>
 * </UL>
 * 
 * JUnit Test for Trac #1090 Table font sur Column & Intersection
 * 
 * TODO : Do the same tests for the ElementColumnMapping (there is the Cell
 * intersectionMapping style more to test).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class DTableCellStylesTest extends TableTestCase {
    private static final String COLUMN_LABEL_IS_ABSTARCT = "isAbstract ?";

    private static final String COLUMN_LABEL_IS_ACTIVE = "Active ?";

    public void testFeatureColumnBackgroundStyle() throws Exception {
        final TableDescription desc = find("Colored Classes Table");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        // Get the isAbstract column
        DColumn isAbstractColumn = null;
        try {
            isAbstractColumn = (DColumn) interpreter.evaluateEObject(newTable, "aql:self.columns->select(c | c.label = '" + COLUMN_LABEL_IS_ABSTARCT + "' )->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the isAbstract column.");
            e.printStackTrace();
        }
        assertNotNull("We should have a column labeled " + COLUMN_LABEL_IS_ABSTARCT + ".", isAbstractColumn);

        // Get the line corresponding to the Class Class1
        DLine class1Line = null;
        String acceleoRequestForClass1Line = "self.lines->select( l | l.label = 'Class : Class1')->first()";
        try {
            class1Line = (DLine) interpreter.evaluateEObject(newTable, "aql:" + acceleoRequestForClass1Line);
        } catch (EvaluationException e) {
            fail("Exception while trying to get the line corresponding to the Class Class1.");
            e.printStackTrace();
        }
        assertNotNull("We should have a line corresponding to the Class Class1.", class1Line);

        // Get the background color of the cell of the isAbstract column of
        // Class1 Line
        DCell cell = null;
        try {
            cell = (DCell) interpreter.evaluateEObject(newTable, "aql:" + acceleoRequestForClass1Line + ".cells->select(c | c.column.label = '" + COLUMN_LABEL_IS_ABSTARCT + "')->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the isAbstract cell of the Class1 line.");
            e.printStackTrace();
        }
        assertNotNull("We should have an isAbstract cell for the Class1 line.", cell);
        Option<DTableElementStyle> optionalBackgroundStyleToApply = new DCellQuery(cell).getBackgroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalBackgroundStyleToApply.some());
        RGBValues color = optionalBackgroundStyleToApply.get().getBackgroundColor();
        assertTrue("We should have a red background color (the conditonal background color of the column mapping) for the cell",
                AbstractColorUpdater.areEquals(VisualBindingManager.getDefault().getRGBValuesFor(SystemColors.RED_LITERAL), color));
        //
        /*
         * Let's change the isAbstract value of the Class1 (use for conditional
         * background color) and see whether the cells updates or not.
         */
        assertTrue("The target of the line should be a Class", class1Line.getTarget() instanceof org.eclipse.uml2.uml.Class);
        final DLine tempLine = class1Line;
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            protected void doExecute() {
                ((org.eclipse.uml2.uml.Class) tempLine.getTarget()).setIsAbstract(false);
            }
        });

        sync.refresh(new NullProgressMonitor());

        optionalBackgroundStyleToApply = new DCellQuery(cell).getBackgroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalBackgroundStyleToApply.some());
        color = optionalBackgroundStyleToApply.get().getBackgroundColor();
        assertEquals("We should have white as default background color", RGBValues.create(255, 255, 255), color);
    }

    public void testFeatureColumnForegroundColorStyle() throws Exception {
        final TableDescription desc = find("Colored Classes Table");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        // Get the isActive column
        DColumn isActiveColumn = null;
        try {
            isActiveColumn = (DColumn) interpreter.evaluateEObject(newTable, "aql:self.columns->select( c |c.label = '" + COLUMN_LABEL_IS_ACTIVE + "')->first()");

        } catch (EvaluationException e) {
            fail("Exception while trying to get the isActive column.");
            e.printStackTrace();
        }
        assertNotNull("We should have a column labeled \"Active ?\".", isActiveColumn);

        // Get the line corresponding to the Class Class1
        DLine class1Line = null;
        String acceleoRequestForClass1Line = "self.lines->select( l | l.label = 'Class : Class1')->first()";
        try {
            class1Line = (DLine) interpreter.evaluateEObject(newTable, "aql:" + acceleoRequestForClass1Line);
        } catch (EvaluationException e) {
            fail("Exception while trying to get the line corresponding to the Class Class1.");
            e.printStackTrace();
        }
        assertNotNull("We should have a line corresponding to the Class Class1.", class1Line);

        // Get the foreground style of the cell of the isActive column of Class1
        DCell cellForClasse1 = null;
        try {
            cellForClasse1 = (DCell) interpreter.evaluateEObject(newTable, "aql:" + acceleoRequestForClass1Line + ".cells->select(c | c.column.label = '" + COLUMN_LABEL_IS_ACTIVE + "')->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the isActive cell of the Class1 line.");
            e.printStackTrace();
        }
        assertNotNull("We should have an isActive cell for the Class1 line.", cellForClasse1);
        DCellQuery class1CellQuery = new DCellQuery(cellForClasse1);
        Option<DTableElementStyle> optionalForegroundStyleToApply = class1CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        RGBValues color = optionalForegroundStyleToApply.get().getForegroundColor();
        assertTrue("We should have a purple foreground color (the conditional foreground color of the line mapping) for the cell",
                AbstractColorUpdater.areEquals(VisualBindingManager.getDefault().getRGBValuesFor(SystemColors.PURPLE_LITERAL), color));

        // Get the line corresponding to the Class Class2
        DLine class2Line = null;
        String acceleoRequestForClass2Line = "lines->select(l |l.label = 'Class : Class2')->first()";
        try {
            class2Line = (DLine) interpreter.evaluateEObject(newTable, "aql:self." + acceleoRequestForClass2Line );
        } catch (EvaluationException e) {
            fail("Exception while trying to get the line corresponding to the Class Class2.");
            e.printStackTrace();
        }
        assertNotNull("We should have a line corresponding to the Class Class2.", class2Line);

        // Get the foreground style of the cell of the isActive column of Class2
        DCell cellForClasse2 = null;
        try {
            cellForClasse2 = (DCell) interpreter.evaluateEObject(newTable, "aql:self." + acceleoRequestForClass2Line + ".cells->select(c | c.column.label = '" + COLUMN_LABEL_IS_ACTIVE + "')->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the isActive cell of the Class2 line.");
            e.printStackTrace();
        }
        assertNotNull("We should have an isActive cell for the Class2 line.", cellForClasse2);
        DCellQuery class2CellQuery = new DCellQuery(cellForClasse2);
        optionalForegroundStyleToApply = class2CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        color = optionalForegroundStyleToApply.get().getForegroundColor();
        assertTrue("We should have a dark_blue foreground color (the default foreground color of the line mapping) for the cell",
                AbstractColorUpdater.areEquals(VisualBindingManager.getDefault().getRGBValuesFor(SystemColors.DARK_BLUE_LITERAL), color));

        /*
         * Let's change the isActive value of the Class1 and Class2 (use for
         * conditional foreground color) and see whether the cells updates or
         * not.
         */
        assertTrue("The target of the line should be a Class", class1Line.getTarget() instanceof org.eclipse.uml2.uml.Class);
        assertTrue("The target of the line should be a Class", class2Line.getTarget() instanceof org.eclipse.uml2.uml.Class);
        final DLine tempLineClass1 = class1Line;
        final DLine tempLineClass2 = class2Line;
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            protected void doExecute() {
                ((org.eclipse.uml2.uml.Class) tempLineClass1.getTarget()).setIsActive(true);
                ((org.eclipse.uml2.uml.Class) tempLineClass2.getTarget()).setIsActive(true);
            }
        });

        sync.refresh(new NullProgressMonitor());

        optionalForegroundStyleToApply = class1CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        color = optionalForegroundStyleToApply.get().getForegroundColor();
        assertTrue("We should have a purple foreground color (the conditional foreground color of the line mapping) for the cell and not " + color + ".",
                AbstractColorUpdater.areEquals(VisualBindingManager.getDefault().getRGBValuesFor(SystemColors.PURPLE_LITERAL), color));

        optionalForegroundStyleToApply = class2CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        color = optionalForegroundStyleToApply.get().getForegroundColor();
        assertTrue("We should have a red foreground color (the conditional foreground color of the column mapping) for the cell",
                AbstractColorUpdater.areEquals(VisualBindingManager.getDefault().getRGBValuesFor(SystemColors.RED_LITERAL), color));
    }

    public void testFeatureColumnForegroundSizeStyle() throws Exception {
        final TableDescription desc = find("Colored Classes Table");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        // Get the isActive column
        DColumn isActiveColumn = null;
        try {
            isActiveColumn = (DColumn) interpreter.evaluateEObject(newTable, "aql:self.columns->select( c |c.label = '" + COLUMN_LABEL_IS_ACTIVE + "')->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the isActive column.");
            e.printStackTrace();
        }
        assertNotNull("We should have a column labeled \"Active ?\".", isActiveColumn);

        // Get the line corresponding to the Class Class1
        DLine class1Line = null;
        String acceleoRequestForClass1Line = "self.lines->select( l | l.label = 'Class : Class1')->first()";
        try {
            class1Line = (DLine) interpreter.evaluateEObject(newTable, "aql:" + acceleoRequestForClass1Line);
        } catch (EvaluationException e) {
            fail("Exception while trying to get the line corresponding to the Class Class1.");
            e.printStackTrace();
        }
        assertNotNull("We should have a line corresponding to the Class Class1.", class1Line);

        // Get the foreground style of the cell of the isActive column of Class1
        DCell cellForClasse1 = null;
        try {
            cellForClasse1 = (DCell) interpreter.evaluateEObject(newTable, "aql:" + acceleoRequestForClass1Line + ".cells->select(c | c.column.label = '" + COLUMN_LABEL_IS_ACTIVE + "')->first()"); 
        } catch (EvaluationException e) {
            fail("Exception while trying to get the isActive cell of the Class1 line.");
            e.printStackTrace();
        }
        assertNotNull("We should have an isActive cell for the Class1 line.", cellForClasse1);
        DCellQuery class1CellQuery = new DCellQuery(cellForClasse1);
        Option<DTableElementStyle> optionalForegroundStyleToApply = class1CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        assertEquals("We should have a font size of 9 (the conditional foreground size of the line mapping).", 9, optionalForegroundStyleToApply.get().getLabelSize());

        // Get the line corresponding to the Class Class2
        DLine class2Line = null;
        String acceleoRequestForClass2Line = "lines->select(l |l.label = 'Class : Class2')->first()";
        try {
            class2Line = (DLine) interpreter.evaluateEObject(newTable, "aql:self." + acceleoRequestForClass2Line + "");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the line corresponding to the Class Class2.");
            e.printStackTrace();
        }
        assertNotNull("We should have a line corresponding to the Class Class2.", class2Line);

        // Get the foreground style of the cell of the isActive column of Class2
        DCell cellForClasse2 = null;
        try {
            cellForClasse2 = (DCell) interpreter.evaluateEObject(newTable, "aql:self." + acceleoRequestForClass2Line + ".cells->select( c | c.column.label ='" + COLUMN_LABEL_IS_ACTIVE + "')->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the isActive cell of the Class2 line.");
            e.printStackTrace();
        }

        assertNotNull("We should have an isActive cell for the Class2 line.", cellForClasse2);
        DCellQuery class2CellQuery = new DCellQuery(cellForClasse2);
        optionalForegroundStyleToApply = class2CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        assertEquals("We should have a font size of 12 (the default foreground size of the line mapping).", 12, optionalForegroundStyleToApply.get().getLabelSize());

        /*
         * Let's change the isActive value of the Class1 (use for conditional
         * foreground color) and see whether the cells updates or not.
         */
        assertTrue("The target of the line should be a Class", class1Line.getTarget() instanceof org.eclipse.uml2.uml.Class);
        assertTrue("The target of the line should be a Class", class2Line.getTarget() instanceof org.eclipse.uml2.uml.Class);
        final DLine tempLineClass1 = class1Line;
        final DLine tempLineClass2 = class2Line;
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            protected void doExecute() {
                ((org.eclipse.uml2.uml.Class) tempLineClass1.getTarget()).setIsActive(true);
                ((org.eclipse.uml2.uml.Class) tempLineClass2.getTarget()).setIsActive(true);
            }
        });

        sync.refresh(new NullProgressMonitor());

        optionalForegroundStyleToApply = class1CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        assertEquals("We should have a font size of 9 (the conditional foreground size of the line mapping).", 9, optionalForegroundStyleToApply.get().getLabelSize());

        optionalForegroundStyleToApply = class2CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        assertEquals("We should have a font size of 14 (the conditional foreground size of the column mapping).", 14, optionalForegroundStyleToApply.get().getLabelSize());
    }

    public void testFeatureColumnForegroundFontStyle() throws Exception {
        final TableDescription desc = find("Colored Classes Table");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        // Get the isActive column
        DColumn isActiveColumn = null;
        try {
            isActiveColumn = (DColumn) interpreter.evaluateEObject(newTable, "aql:self.columns->select( c |c.label = '" + COLUMN_LABEL_IS_ACTIVE + "')->first()");

        } catch (EvaluationException e) {
            fail("Exception while trying to get the isActive column.");
            e.printStackTrace();
        }
        assertNotNull("We should have a column labeled \"Active ?\".", isActiveColumn);

        // Get the line corresponding to the Class Class1
        DLine class1Line = null;
        String acceleoRequestForClass1Line = "self.lines->select( l | l.label = 'Class : Class1')->first()";
        try {
            class1Line = (DLine) interpreter.evaluateEObject(newTable, "aql:" + acceleoRequestForClass1Line);
        } catch (EvaluationException e) {
            fail("Exception while trying to get the line corresponding to the Class Class1.");
            e.printStackTrace();
        }
        assertNotNull("We should have a line corresponding to the Class Class1.", class1Line);

        // Get the foreground style of the cell of the isActive column of Class1
        DCell cellForClasse1 = null;
        try {
            cellForClasse1 = (DCell) interpreter.evaluateEObject(newTable, "aql:" + acceleoRequestForClass1Line + ".cells->select(c | c.column.label = '" + COLUMN_LABEL_IS_ACTIVE + "')->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the isActive cell of the Class1 line.");
            e.printStackTrace();
        }
        assertNotNull("We should have an isActive cell for the Class1 line.", cellForClasse1);
        DCellQuery class1CellQuery = new DCellQuery(cellForClasse1);
        Option<DTableElementStyle> optionalForegroundStyleToApply = class1CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        assertNotNull("We should have a label format for the cell.", optionalForegroundStyleToApply.get().getLabelFormat());
        assertEquals("The label format should be normal (the conditional foreground label format of the line mapping).", Collections.emptyList(), optionalForegroundStyleToApply.get().getLabelFormat());

        // Get the line corresponding to the Class Class2
        DLine class2Line = null;
        String acceleoRequestForClass2Line = "lines->select(l |l.label = 'Class : Class2')->first()";
        try {
            class2Line = (DLine) interpreter.evaluateEObject(newTable, "aql:self." + acceleoRequestForClass2Line);
        } catch (EvaluationException e) {
            fail("Exception while trying to get the line corresponding to the Class Class2.");
            e.printStackTrace();
        }
        assertNotNull("We should have a line corresponding to the Class Class2.", class2Line);

        // Get the foreground style of the cell of the isActive column of Class2
        DCell cellForClasse2 = null;
        try {
            cellForClasse2 = (DCell) interpreter.evaluateEObject(newTable, "aql:self." + acceleoRequestForClass2Line + ".cells->select( c | c.column.label = '" + COLUMN_LABEL_IS_ACTIVE + "')->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the isActive cell of the Class2 line.");
            e.printStackTrace();
        }

        assertNotNull("We should have an isActive cell for the Class2 line.", cellForClasse2);
        DCellQuery class2CellQuery = new DCellQuery(cellForClasse2);
        optionalForegroundStyleToApply = class2CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        assertNotNull("We should have a label format for the cell.", optionalForegroundStyleToApply.get().getLabelFormat());
        assertEquals("The label format should be normal (the default foreground label format of the line mapping).", Collections.emptyList(), optionalForegroundStyleToApply.get().getLabelFormat());

        /*
         * Let's change the isActive value of the Class1 (use for conditional
         * foreground color) and see whether the cells updates or not.
         */
        assertTrue("The target of the line should be a Class", class1Line.getTarget() instanceof org.eclipse.uml2.uml.Class);
        assertTrue("The target of the line should be a Class", class2Line.getTarget() instanceof org.eclipse.uml2.uml.Class);
        final DLine tempLineClass1 = class1Line;
        final DLine tempLineClass2 = class2Line;
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            protected void doExecute() {
                ((org.eclipse.uml2.uml.Class) tempLineClass1.getTarget()).setIsActive(true);
                ((org.eclipse.uml2.uml.Class) tempLineClass2.getTarget()).setIsActive(true);
            }
        });

        sync.refresh(new NullProgressMonitor());

        optionalForegroundStyleToApply = class1CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        assertNotNull("We should have a label format for the cell.", optionalForegroundStyleToApply.get().getLabelFormat());
        assertEquals("The label format should be normal (the conditional foreground label format of the line mapping).", Collections.emptyList(), optionalForegroundStyleToApply.get().getLabelFormat());

        optionalForegroundStyleToApply = class2CellQuery.getForegroundStyleToApply();
        assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
        assertNotNull("We should have a label format for the cell.", optionalForegroundStyleToApply.get().getLabelFormat());
        List<FontFormat> fontFormat = new ArrayList<FontFormat>();
        fontFormat.add(FontFormat.ITALIC_LITERAL);
        assertEquals("The label format should be italic (the conditional foreground label format of the column mapping).", fontFormat, optionalForegroundStyleToApply.get().getLabelFormat());
    }
}
