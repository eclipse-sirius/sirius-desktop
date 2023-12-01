/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.table.tests.srs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.ui.business.api.helper.TableUIHelper;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.SortColumnsByLineAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.SortLinesByColumnAction;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;

/**
 * Sirius shall enable to sort and/or filter the lists of LineElements and
 * ColumnElements in a Cross Array according to Attribute values.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class REQ_161_Test extends TableTestCase {

    public void test_REQ_161_NormalSort() throws Exception {
        final TableDescription desc = find("Model Association Cross Table");
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
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class1", "Class2", "Class3", "AbstractClass1", "AbstractClass2", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "_", "class 1 to class 2", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "_", "class 2 to class 3", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "_", "_", "_" });

        assertEquals("The original sort is KO", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(newTable, false));
    }

    public void test_REQ_161_ascendingSortOnSecondColumn() throws Exception {
        final TableDescription desc = find("Model Association Cross Table");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        assertEquals("We have 6 classes so we should get 6 lines", 6, newTable.getLines().size());
        assertEquals("We have 6 classes so we should get 6 columns", 6, newTable.getColumns().size());

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        final SortLinesByColumnAction sortLinesByColumnAction = new SortLinesByColumnAction(domain);
        sortLinesByColumnAction.setColumn(newTable.getColumns().get(1));
        sortLinesByColumnAction.run();

        final List<List<String>> expectedAscendingSort = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedAscendingSort, new String[] { "", "Class1", "Class2", "Class3", "AbstractClass1", "AbstractClass2", "Class4" });
        TableUIHelper.addLineToTable(expectedAscendingSort, new String[] { "Class2", "_", "_", "class 2 to class 3", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedAscendingSort, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedAscendingSort, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedAscendingSort, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedAscendingSort, new String[] { "Class4", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedAscendingSort, new String[] { "Class1", "_", "class 1 to class 2", "_", "_", "_", "_" });

        assertEquals("The ascending sort on name column is KO", TableUIHelper.toHTML(expectedAscendingSort), TableUIHelper.toContentHTMl(newTable, false));
    }

    public void test_REQ_161_descendingSortOnSeconfColumn() throws Exception {
        final TableDescription desc = find("Model Association Cross Table");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        assertEquals("We have 6 classes so we should get 6 lines", 6, newTable.getLines().size());
        assertEquals("We have 6 classes so we should get 6 columns", 6, newTable.getColumns().size());

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        SortLinesByColumnAction sortLinesByColumnAction = new SortLinesByColumnAction(domain);
        sortLinesByColumnAction.setColumn(newTable.getColumns().get(1));
        sortLinesByColumnAction.run();

        sortLinesByColumnAction = new SortLinesByColumnAction(domain);
        sortLinesByColumnAction.setColumn(newTable.getColumns().get(1));
        sortLinesByColumnAction.run();

        final List<List<String>> expectedDescendingSort = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedDescendingSort, new String[] { "", "Class1", "Class2", "Class3", "AbstractClass1", "AbstractClass2", "Class4" });
        TableUIHelper.addLineToTable(expectedDescendingSort, new String[] { "Class1", "_", "class 1 to class 2", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedDescendingSort, new String[] { "Class2", "_", "_", "class 2 to class 3", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedDescendingSort, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedDescendingSort, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedDescendingSort, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedDescendingSort, new String[] { "Class4", "_", "_", "_", "_", "_", "_" });

        assertEquals("The descending sort on name column is KO", TableUIHelper.toHTML(expectedDescendingSort), TableUIHelper.toContentHTMl(newTable, false));
    }

    public void test_REQ_161_ascendingSortOnFirstLine() throws Exception {
        final TableDescription desc = find("Model Association Cross Table");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        assertEquals("We have 6 classes so we should get 6 lines", 6, newTable.getLines().size());
        assertEquals("We have 6 classes so we should get 6 columns", 6, newTable.getColumns().size());

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        final SortColumnsByLineAction sortColumnsByLineAction = new SortColumnsByLineAction(domain);
        sortColumnsByLineAction.setLine(newTable.getLines().get(0));
        sortColumnsByLineAction.run();

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class1", "Class3", "AbstractClass1", "AbstractClass2", "Class4", "Class2" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "_", "_", "_", "_", "_", "class 1 to class 2" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "class 2 to class 3", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "_", "_", "_" });
        assertEquals("The ascending sort on the first line is KO", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(newTable, false));
    }

    public void test_REQ_161_descendingSortOnFirstLine() throws Exception {
        final TableDescription desc = find("Model Association Cross Table");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());
        assertEquals("We have 6 classes so we should get 6 lines", 6, newTable.getLines().size());
        assertEquals("We have 6 classes so we should get 6 columns", 6, newTable.getColumns().size());

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        SortColumnsByLineAction sortColumnsByLineAction = new SortColumnsByLineAction(domain);
        sortColumnsByLineAction.setLine(newTable.getLines().get(0));
        sortColumnsByLineAction.run();

        sortColumnsByLineAction = new SortColumnsByLineAction(domain);
        sortColumnsByLineAction.setLine(newTable.getLines().get(0));
        sortColumnsByLineAction.run();

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class2", "Class1", "Class3", "AbstractClass1", "AbstractClass2", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "class 1 to class 2", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "_", "class 2 to class 3", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "_", "_", "_" });
        assertEquals("The descending sort on the first line is KO", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(newTable, false));
    }
}
