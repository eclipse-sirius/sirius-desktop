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
import org.eclipse.sirius.table.ui.tools.internal.editor.action.SortLinesByColumnAction;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;

/**
 * Sirius shall enable to sort and/or filter the list of Elements in an Edition
 * Array according to Attribute values.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class REQ_157_Test extends TableTestCase {

    public void test_REQ_157_NormalSort() throws Exception {
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

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "name", "isAbstract ?", "Active ?" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class1", "Class1", "true", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Property", "P1", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class2", "Class2", "false", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Property", "P2", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class3", "Class3", "false", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Property", "P3", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : AbstractClass1", "AbstractClass1", "true", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : AbstractClass2", "AbstractClass2", "true", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class4", "Class4", "true", "false" });

        assertEquals("The original sort is KO", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(newTable, false));
    }

    public void test_REQ_157_ascendingSortOnNameColumn() throws Exception {
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

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        final SortLinesByColumnAction sortLinesByColumnAction = new SortLinesByColumnAction(domain);
        sortLinesByColumnAction.setColumn(newTable.getColumns().get(0));
        sortLinesByColumnAction.run();

        final List<List<String>> expectedAscendingSortOnName = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "", "name", "isAbstract ?", "Active ?" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : AbstractClass1", "AbstractClass1", "true", "false" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : AbstractClass2", "AbstractClass2", "true", "false" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : Class1", "Class1", "true", "false" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Property", "P1", "", "" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : Class2", "Class2", "false", "false" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Property", "P2", "", "" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : Class3", "Class3", "false", "false" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Property", "P3", "", "" });
        TableUIHelper.addLineToTable(expectedAscendingSortOnName, new String[] { "Class : Class4", "Class4", "true", "false" });

        assertEquals("The ascending sort on name column is KO", TableUIHelper.toHTML(expectedAscendingSortOnName), TableUIHelper.toContentHTMl(newTable, false));
    }

    public void test_REQ_157_descendingSortOnNameColumn() throws Exception {
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

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        SortLinesByColumnAction sortLinesByColumnAction = new SortLinesByColumnAction(domain);
        sortLinesByColumnAction.setColumn(newTable.getColumns().get(0));
        sortLinesByColumnAction.run();

        sortLinesByColumnAction = new SortLinesByColumnAction(domain);
        sortLinesByColumnAction.setColumn(newTable.getColumns().get(0));
        sortLinesByColumnAction.run();

        final List<List<String>> expectedDescendingSortOnName = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "", "name", "isAbstract ?", "Active ?" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : Class4", "Class4", "true", "false" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : Class3", "Class3", "false", "false" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Property", "P3", "", "" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : Class2", "Class2", "false", "false" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Property", "P2", "", "" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : Class1", "Class1", "true", "false" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Property", "P1", "", "" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : AbstractClass2", "AbstractClass2", "true", "false" });
        TableUIHelper.addLineToTable(expectedDescendingSortOnName, new String[] { "Class : AbstractClass1", "AbstractClass1", "true", "false" });

        assertEquals("The descending sort on name column is KO", TableUIHelper.toHTML(expectedDescendingSortOnName), TableUIHelper.toContentHTMl(newTable, false));
    }
}
