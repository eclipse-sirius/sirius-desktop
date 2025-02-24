/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES and others.
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.business.api.query.DTableQuery;
import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.ui.business.api.helper.TableUIHelper;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;

/**
 * Test the table synchronizer.
 * 
 * @author cbrun
 */
public class DTableSynchronizerTest extends TableTestCase {

    public void testSimpleEditionTable() throws Exception {
        final TableDescription desc = find("Colored Classes Table");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());
        assertEquals("We have 6 classes so we should get 6 lines", 6, newTable.getLines().size());

        assertEquals("We have 3 features columns so we should get 3 columns", 3, newTable.getColumns().size());

        final String expectedHtml = TableUIHelper.toContentHTMl(newTable, false);
        final String expectedInverseHtml = TableUIHelper.toContentHTMl(newTable, true);

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());
            final String newHTML = TableUIHelper.toContentHTMl(newTable, false);
            assertEquals("A refresh without semantic changes modified the table.", expectedHtml, newHTML);
            final String newInverseHTML = TableUIHelper.toContentHTMl(newTable, true);
            assertEquals("A refresh without semantic changes modified the table.", expectedInverseHtml, newInverseHTML);
        }
    }

    /**
     * This test checks the behavior of a virtual feature column. It also checks the evaluation of the header label
     * expression of a feature column.
     * 
     * @throws Exception
     */
    public void testVirtualFeatureColumns() throws Exception {
        final TableDescription desc = find("Virtual columns");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "name", "ComputedLabel_featureName*", "MyUMLModel_NonExistingFeature" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "Class1", "ComputedLabel", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "Class2", "ComputedLabel", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "Class3", "ComputedLabel", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "AbstractClass1", "ComputedLabel", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "AbstractClass2", "ComputedLabel", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "Class4", "ComputedLabel", "_" });
        String expectedHtml = TableUIHelper.toHTML(expected);

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());
            assertEquals("We have 6 classes so we should get 6 lines", 6, newTable.getLines().size());
            assertEquals("We have 3 features columns so we should get 3 columns", 3, newTable.getColumns().size());
            assertEquals(expectedHtml, TableUIHelper.toContentHTMl(newTable, false));
        }

    }

    public void testCrossClassWithGeneralization() throws Exception {
        final TableDescription desc = find("Model Generalization Cross Table");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        assertEquals("We have 6 classes so we should get 6 lines", 6, newTable.getLines().size());
        assertEquals("We have 6 classes so we should get 6 columns", 6, newTable.getColumns().size());

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "X", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "X", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "X", "_", "_" });

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());
            assertEquals("the table have changed even if nothing has been modified in the semantic model", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(newTable, false));
        }

        /*
         * Let's remove a generalization and see whether the cells updates or
         * not.
         */
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                final org.eclipse.uml2.uml.Class claz = (Class) ((Model) semanticModel).getPackagedElement("Class1");
                assertNotNull("Unit test data is not correct", claz);
                claz.getGeneralizations().clear();
            }
        });
        final List<List<String>> expectedMissingGeneralization = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedMissingGeneralization, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4" });
        TableUIHelper.addLineToTable(expectedMissingGeneralization, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedMissingGeneralization, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedMissingGeneralization, new String[] { "Class1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedMissingGeneralization, new String[] { "Class2", "_", "X", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedMissingGeneralization, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedMissingGeneralization, new String[] { "Class4", "_", "_", "_", "X", "_", "_" });

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());
            assertEquals("No change in the table even if a generalization has been removed", TableUIHelper.toHTML(expectedMissingGeneralization), TableUIHelper.toContentHTMl(newTable, false));
        }
    }

    public void testCrossClassWithAssociations() throws Exception {
        final TableDescription desc = find("Model Association Cross Table");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class1", "Class2", "Class3", "AbstractClass1", "AbstractClass2", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "_", "class 1 to class 2", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "_", "class 2 to class 3", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "_", "_", "_" });

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());
            assertEquals("We have 6 classes so we should get 6 lines", 6, newTable.getLines().size());
            assertEquals("We have 6 classes so we should get 6 columns", 6, newTable.getColumns().size());
            assertEquals(TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(newTable, false));
        }
    }

    public void testCrossClassForegroundColors() throws Exception {
        final TableDescription desc = find("Cross Table Colors");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class1", "Class2", "Class3", "AbstractClass1", "AbstractClass2", "Class4", "P1" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "P1", "orange", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "extends", "_", "_", "_", "green", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "blue", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "P2", "_", "orange", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "extends", "_", "_", "_", "_", "green", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "red", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "P3", "_", "_", "orange", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "extends", "_", "green", "_", "_", "_", "_", "_" });

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());
            assertEquals(TableUIHelper.toHTML(expected), TableUIHelper.toForegroundStyleHTMl(newTable, false));
        }
    }

    public void testCrossClassBackgroundColors() throws Exception {
        final TableDescription desc = find("Cross Table Colors");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class1", "Class2", "Class3", "AbstractClass1", "AbstractClass2", "Class4", "P1" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_green" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_green" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_green" });
        TableUIHelper.addLineToTable(expected, new String[] { "P1", "yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_green" });
        TableUIHelper.addLineToTable(expected, new String[] { "extends", "light_gray", "light_gray", "light_gray", "light_gray", "light_gray", "light_gray", "light_gray" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_green" });
        TableUIHelper.addLineToTable(expected, new String[] { "P2", "light_yellow", "yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_green" });
        TableUIHelper.addLineToTable(expected, new String[] { "extends", "light_gray", "light_gray", "light_gray", "light_gray", "light_gray", "light_gray", "light_gray" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_green" });
        TableUIHelper.addLineToTable(expected, new String[] { "P3", "light_yellow", "light_yellow", "yellow", "light_yellow", "light_yellow", "light_yellow", "light_green" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_yellow", "light_green" });
        TableUIHelper.addLineToTable(expected, new String[] { "extends", "light_gray", "light_gray", "light_gray", "light_gray", "light_gray", "light_gray", "light_gray" });

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());
            assertEquals(TableUIHelper.toHTML(expected), TableUIHelper.toBackgroundStyleHTMl(newTable, false));
        }
    }

    public void testVariablesForCellInCrossTable() throws Exception {
        final TableDescription desc = find("VariablesAccess");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "Class1 Class1 AbstractClass1 AbstractClass1", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "Class2 Class2 AbstractClass2 AbstractClass2", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "Class4 Class4 Class2 Class2", "_", "_" });

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());
            assertEquals("Access to one of the following variables must be a problem: $line, $column, $lineSemantic, $columnSemantic.", TableUIHelper.toHTML(expected),
                    TableUIHelper.toContentHTMl(newTable, false));
        }
    }

    /**
     * Check that when no semantic element is specified in the field Semantic
     * Elements of LineMapping, the target of this line is used as semantic
     * elements.
     * 
     * @throws Exception
     *             When test failed
     */
    public void testSemanticElementsInEditionTable() throws Exception {
        final TableDescription desc = find("TestColumnWithoutHeaderLabelExpression");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());

            for (DLine dLine : newTable.getLines()) {
                assertEquals("The line " + dLine.getLabel() + " must have only one semantic element", 1, dLine.getSemanticElements().size());
                assertEquals("The line " + dLine.getLabel() + " must have its target has semantic element", dLine.getTarget(), dLine.getSemanticElements().get(0));
            }
        }
    }

    /**
     * Check that when no semantic element is specified in the field Semantic
     * Elements of LineMapping or TargetColumnMapping, the target of this
     * line/column is used as semantic elements.
     * 
     * @throws Exception
     *             When test failed
     */
    public void testSemanticElementsInCrossTable() throws Exception {
        final TableDescription desc = find("VariablesAccess");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());

            for (DLine dLine : newTable.getLines()) {
                assertEquals("The line " + dLine.getLabel() + " must have only one semantic element", 1, dLine.getSemanticElements().size());
                assertEquals("The line " + dLine.getLabel() + " must have its target has semantic element", dLine.getTarget(), dLine.getSemanticElements().get(0));
            }
            for (DColumn dColumn : newTable.getColumns()) {
                assertEquals("The column " + dColumn.getLabel() + " must have only one semantic element", 1, dColumn.getSemanticElements().size());
                assertEquals("The column " + dColumn.getLabel() + " must have its target has semantic element", dColumn.getTarget(), dColumn.getSemanticElements().get(0));
            }
        }
    }

    private IntersectionMapping findMapping(final TableDescription table, final String name) {
        final Iterator<EObject> it = table.eAllContents();
        while (it.hasNext()) {
            final Object next = it.next();
            if (next instanceof IntersectionMapping) {
                if (name.equals(((IntersectionMapping) next).getName()))
                    return (IntersectionMapping) next;
            }
        }
        return null;

    }

    private LineMapping findLineMapping(final TableDescription table, final String name) {
        final Iterator<EObject> it = table.eAllContents();
        while (it.hasNext()) {
            final Object next = it.next();
            if (next instanceof LineMapping) {
                if (name.equals(((LineMapping) next).getName()))
                    return (LineMapping) next;
            }
        }
        return null;

    }

    /**
     * Check that the precondition expression is taken into account in the
     * IntersectionMapping.
     * 
     * @throws Exception
     *             When test failed
     */
    public void testPreconditionInIntersectionMappingNoDomainBased() throws Exception {

        final Map<String, String> expectedMatrix = new HashMap<>();

        final TableDescription desc = find("VariablesAccess");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());

            expectedMatrix.put("Class1", "AbstractClass1");
            expectedMatrix.put("Class2", "AbstractClass2");
            expectedMatrix.put("Class3", null);
            expectedMatrix.put("Class4", "Class2");
            checkPrecondition(newTable, expectedMatrix);
        }

        final IntersectionMapping mapping = findMapping(desc, "Generalization");
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                mapping.setPreconditionExpression("aql:false");
            }

        });

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());

            expectedMatrix.put("Class1", null);
            expectedMatrix.put("Class2", null);
            expectedMatrix.put("Class3", null);
            expectedMatrix.put("Class4", null);
            checkPrecondition(newTable, expectedMatrix);
        }

    }

    /**
     * Check that the precondition expression is taken into account in the
     * IntersectionMapping.
     * 
     * @throws Exception
     *             When test failed
     */
    public void testPreconditionInIntersectionMappingDomainBased() throws Exception {

        final Map<String, String> expectedMatrix = new HashMap<>();

        final TableDescription desc = find("Model Association Cross Table");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());

            expectedMatrix.put("Class1", "Class2");
            expectedMatrix.put("Class2", "Class3");
            expectedMatrix.put("Class3", null);
            expectedMatrix.put("Class4", null);
            checkPrecondition(newTable, expectedMatrix);
        }

        final IntersectionMapping mapping = findMapping(desc, "Associations");
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                mapping.setPreconditionExpression("aql:false");
            }

        });

        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());

            expectedMatrix.put("Class1", null);
            expectedMatrix.put("Class2", null);
            expectedMatrix.put("Class3", null);
            expectedMatrix.put("Class4", null);
            checkPrecondition(newTable, expectedMatrix);
        }

    }

    /**
     * Check that the deletion of a style in the LineMapping is taken into
     * account during the refresh of diagram.
     * 
     * @throws Exception
     *             When test failed
     */
    public void testStyleRefreshEditionTable() {

        checkStyle("All Types and Instance", "All EClasses");

    }

    /**
     * Check that the deletion of a style in the LineMapping is taken into
     * account during the refresh of diagram.
     * 
     * @throws Exception
     *             When test failed
     */
    public void testStyleRefreshCrossTable() {
        checkStyle("Model Association Cross Table", "CT Classes");
    }

    private void checkStyle(String tableDescriptionName, String lineMappingName) {
        final TableDescription desc = find(tableDescriptionName);
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        Option<DCell> optionalCell = new DTableQuery(newTable).getFirstCell();
        if (optionalCell.some()) {
            Option<DTableElementStyle> optionalForegroundStyleToApply = new DCellQuery(optionalCell.get()).getForegroundStyleToApply();
            assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
            checkStyle(optionalForegroundStyleToApply.get(), false, -1, Collections.emptyList());
        }

        final LineMapping mapping = findLineMapping(desc, lineMappingName);

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                ForegroundStyleDescription foregroundStyle = DescriptionFactory.eINSTANCE.createForegroundStyleDescription();
                FontFormatHelper.setFontFormat(foregroundStyle.getLabelFormat(), FontFormat.BOLD_LITERAL);
                foregroundStyle.setLabelSize(14);
                mapping.setDefaultForeground(foregroundStyle);
            }

        });

        sync.refresh(new NullProgressMonitor());

        optionalCell = new DTableQuery(newTable).getFirstCell();
        if (optionalCell.some()) {
            Option<DTableElementStyle> optionalForegroundStyleToApply = new DCellQuery(optionalCell.get()).getForegroundStyleToApply();
            assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
            List<FontFormat> fontFormat = new ArrayList<FontFormat>();
            fontFormat.add(FontFormat.BOLD_LITERAL);
            checkStyle(optionalForegroundStyleToApply.get(), true, 14, fontFormat);
        }

        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                mapping.setDefaultForeground(null);
            }

        });

        sync.refresh(new NullProgressMonitor());

        optionalCell = new DTableQuery(newTable).getFirstCell();
        if (optionalCell.some()) {
            Option<DTableElementStyle> optionalForegroundStyleToApply = new DCellQuery(optionalCell.get()).getForegroundStyleToApply();
            assertTrue("We should have a currentStyle for the cell.", optionalForegroundStyleToApply.some());
            checkStyle(optionalForegroundStyleToApply.get(), false, -1, Collections.emptyList());
        }
    }

    @SuppressWarnings("rawtypes")
    private void checkStyle(DTableElementStyle style, boolean isStyle, int labelSize, List fontFormat) {
        if (!isStyle)
            assertEquals(RGBValues.create(0, 0, 0), style.getForegroundColor());
        assertEquals(labelSize, style.getLabelSize());
        assertEquals(fontFormat, style.getLabelFormat());
    }

    private void checkPrecondition(final DTable newTable, final Map<String, String> expectedResult) {
        for (DLine dLine : newTable.getLines()) {
            String expectedColumnName = expectedResult.get(dLine.getLabel());
            for (DCell dCell : dLine.getCells()) {
                if (dCell.getColumn().getLabel().equals(expectedColumnName)) {
                    assertTrue("Not expected result", dCell.getLabel() != null && dCell.getLabel().trim() != "");
                } else {
                    assertFalse("Not expected result", dCell.getLabel() != null && dCell.getLabel().trim() != "");
                }

            }
        }
    }

    /**
     * Tests that the variables line(DLine) and table(DTable) are handled
     * correctly in the Feature Parent Expression (DescriptionPackage.eINSTANCE.
     * getFeatureColumnMapping_FeatureParentExpression()). The tested expression
     * is : aql:if table<>null then line.target else line.target endif
     * 
     */
    public void testVariablesForCellInSimpleTable() {
        final TableDescription desc = find("SimpleTableVariables");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "______________", "Name__" });
        TableUIHelper.addLineToTable(expected, new String[] { "<Class> Class3", "Class3" });
        for (int i = 0; i < 10; i++) {
            sync.refresh(new NullProgressMonitor());
            assertEquals("Variables table and line seem to not be interpreted correctly.", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(newTable, false));
        }
    }
}
