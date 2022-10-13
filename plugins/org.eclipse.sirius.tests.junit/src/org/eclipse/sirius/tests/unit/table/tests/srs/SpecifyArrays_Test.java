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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.ui.business.api.helper.TableUIHelper;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SpecifyArrays_Test extends TableTestCase {

    /**
     * REQ-056 : Sirius shall enable to specify in an ArraySpec the
     * handling of a set of Elements, either organised in a hierarchy or not, to
     * be managed in an Array.
     * 
     * This test checks that we can create a table description in an odesign
     * file.
     * 
     * @throws Exception
     *             if the test has problems not handle
     */
    public void test_REQ_056() throws Exception {
        final EditionTableDescription editionTableDescription = DescriptionFactory.eINSTANCE.createEditionTableDescription();
        final EList<RepresentationDescription> representations = viewpoints.iterator().next().getOwnedRepresentations();

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                representations.add(editionTableDescription);
            }

        });

        assertTrue("The odesign should contains the new table description", representations.contains(editionTableDescription));
    }

    /**
     * REQ-057 : Sirius shall enable to specify Edition Arrays dedicated
     * to the representation and edition of the properties of a set of elements
     * of the same type, based on the following types (naming convention) of
     * MetaElements (Element MetaElement, Attribute MetaElement)
     * 
     * This test checks that an edition table description exist in the sample
     * odesign and that a creation of table for this creation description works
     * with the correct number of lines and columns.
     * 
     * @throws Exception
     *             if the test has problems not handle
     */
    public void test_REQ_057() throws Exception {
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
    }

    /**
     * REQ-058 : Sirius shall represent Edition Array by an array where
     * the Elements are managed in the lines and the Attributes in the columns,
     * the first line being composed of the name of the Attributes and the first
     * column of the name of the Element, and specify the display of the
     * Attribute values.
     * 
     * This test checks that an edition table description exist in the sample
     * odesign and that a creation of table for this creation description works
     * with the correct data in cells.
     * 
     * @throws Exception
     *             if the test has problems not handle
     */
    public void test_REQ_058() throws Exception {
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

        assertEquals("The display of the edition table is KO", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(newTable, false));
    }

    /**
     * REQ-059 : Sirius shall enable to specify the automated actions to
     * be performed when adding, and deleting Elements and when modify
     * Attributes in the Edition Array.
     * 
     * This test checks that we can add create and delete tool on lines.
     * 
     * @throws Exception
     *             if the test has problems not handle
     */
    public void test_REQ_059() throws Exception {
        // Add a create line tool on the first line mapping
        final TableDescription tableDescription = find("Colored Classes Table");
        assertNotNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription);
        final CreateLineTool createLineTool = DescriptionFactory.eINSTANCE.createCreateLineTool();

        assertEquals(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, tableDescription.getAllLineMappings().size());
        final LineMapping lineMapping = tableDescription.getAllLineMappings().get(0);
        final int nbCreateToolsBeforeAdd = lineMapping.getCreate().size();
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                lineMapping.getCreate().add(createLineTool);
            }

        });
        assertEquals("The number of create line tool is KO.", nbCreateToolsBeforeAdd + 1, lineMapping.getCreate().size());

        assertNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, lineMapping.getDelete());
        final DeleteLineTool deleteLineTool = DescriptionFactory.eINSTANCE.createDeleteLineTool();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                lineMapping.setDelete(deleteLineTool);
            }

        });
        assertNotNull("The setting of the delete tool failed.", lineMapping.getDelete());
    }

    /**
     * REQ-060 : Sirius shall enable to specify the automated actions to
     * be performed when modifying an Attribute in an Edition Array.
     * 
     * 
     * This test checks that we can set label edit tool on columns.
     * 
     * @throws Exception
     *             if the test has problems not handle
     */
    public void test_REQ_060() throws Exception {
        // Add a create line tool on the first line mapping
        final TableDescription tableDescription = find("Colored Classes Table");
        assertNotNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription);
        assertTrue(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription instanceof EditionTableDescription);
        final EditionTableDescription editionTableDescription = (EditionTableDescription) tableDescription;

        assertTrue(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, editionTableDescription.getAllColumnMappings().size() > 1);
        final FeatureColumnMapping columnMapping = editionTableDescription.getAllColumnMappings().get(1);
        assertNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, columnMapping.getDirectEdit());

        final LabelEditTool labelEditTool = DescriptionFactory.eINSTANCE.createLabelEditTool();
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                columnMapping.setDirectEdit(labelEditTool);
            }

        });
        assertNotNull("The setting of the label edit tool failed.", columnMapping.getDirectEdit());
    }

    /**
     * REQ-061 : Sirius shall enable to specify Cross Arrays dedicated to
     * the representation and edition of the relationship between elements of
     * two sets of elements, each set being of the same type, based on the
     * following types (naming convention) of MetaElements: (LineElement
     * MetaElement, ColumnElement MetaElement, Relationship MetaElement)
     * 
     * This test checks that a cross table description exist in the sample
     * odesign and that a creation of table for this creation description works
     * with the correct number of lines and columns.
     * 
     * @throws Exception
     *             if the test has problems not handle
     */
    public void test_REQ_061() throws Exception {
        final TableDescription desc = find("Cross Table Colors");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        assertEquals("We have 6 classes so we should get 6 lines", 6, newTable.getLines().size());
        assertEquals("We have 6 classes and n Properties so we should get 7 columns", 7, newTable.getColumns().size());
    }

    /**
     * REQ-062 : Sirius shall represent Cross Array by an array where the
     * LineElements are managed in the lines and the ColumnElements in the
     * columns, the first line being composed of the name of the Attributes and
     * the first column of the name of the Element, and specify the display of
     * the Relationship values at their intersection.
     * 
     * This test checks that an edition table description exist in the sample
     * odesign and that a creation of table for this creation description works
     * with the correct data in cells.
     * 
     * @throws Exception
     *             if the test has problems not handle
     */
    public void test_REQ_062() throws Exception {
        final TableDescription desc = find("Cross Table Colors");
        assertNotNull("Unit test data is not correct", desc);

        final DTableSynchronizer sync = createTableSynchronizer(desc);

        final DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());

        assertEquals("We have 6 classes so we should get 6 lines", 6, newTable.getLines().size());
        assertEquals("We have 6 classes and n Properties so we should get 7 columns", 7, newTable.getColumns().size());

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class1", "Class2", "Class3", "AbstractClass1", "AbstractClass2", "Class4", "P1" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "", "", "", "", "", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "", "", "", "", "", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "", "", "", "", "", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "P1", "", "", "", "", "", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "extends", "", "", "", "extend", "", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "class 1 to class 2", "", "", "", "", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "P2", "", "", "", "", "", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "extends", "", "", "", "", "extend", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "", "class 2 to class 3", "", "", "", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "P3", "", "", "", "", "", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "", "", "", "", "", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "extends", "", "extend", "", "", "", "", "" });

        assertEquals("The display of the edition table is KO", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(newTable, false));
    }

    /**
     * REQ-063 : Sirius shall enable to specify the automated actions to
     * be performed when adding and deleting LineElements and ColumnElements in
     * the Cross Array.
     * 
     * This test checks that we can add create and delete tool on lines, columns
     * and cells.
     * 
     * @throws Exception
     *             if the test has problems not handle
     */
    public void test_REQ_063() throws Exception {
        // Add a create line tool on the first line mapping
        final TableDescription tableDescription = find("Cross Table Colors");
        assertNotNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription);
        assertEquals(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, tableDescription.getAllLineMappings().size());

        final CreateLineTool createLineTool = DescriptionFactory.eINSTANCE.createCreateLineTool();
        final LineMapping lineMapping = tableDescription.getAllLineMappings().get(0);
        final int nbCreateToolsBeforeAdd = lineMapping.getCreate().size();
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                lineMapping.getCreate().add(createLineTool);
            }

        });
        assertEquals("The number of create line tool is KO.", nbCreateToolsBeforeAdd + 1, lineMapping.getCreate().size());
        // Set delete line tool on the first line mapping
        assertNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, lineMapping.getDelete());
        final DeleteLineTool deleteLineTool = DescriptionFactory.eINSTANCE.createDeleteLineTool();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                lineMapping.setDelete(deleteLineTool);
            }

        });
        assertNotNull("The setting of the delete tool failed.", lineMapping.getDelete());
        // Add a create column tool on the first column mapping
        assertTrue(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription instanceof CrossTableDescription);
        final CrossTableDescription crossTableDescription = (CrossTableDescription) tableDescription;

        assertTrue(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, crossTableDescription.getOwnedColumnMappings().size() > 0);
        final ElementColumnMapping columnMapping = crossTableDescription.getOwnedColumnMappings().get(0);

        final CreateColumnTool createColumnTool = DescriptionFactory.eINSTANCE.createCreateColumnTool();
        final int nbCreateColumnToolsBeforeAdd = columnMapping.getCreate().size();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                columnMapping.getCreate().add(createColumnTool);
            }

        });
        assertEquals("The number of create column tool is KO.", nbCreateColumnToolsBeforeAdd + 1, columnMapping.getCreate().size());
        // Set delete column tool on the first column mapping
        assertNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, columnMapping.getDelete());
        final DeleteColumnTool deleteColumnTool = DescriptionFactory.eINSTANCE.createDeleteColumnTool();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                columnMapping.setDelete(deleteColumnTool);
            }

        });
        assertNotNull("The setting of the delete tool failed.", columnMapping.getDelete());
        // Add a create cell tool on the first intersection mapping
        assertTrue(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, crossTableDescription.getIntersection().size() > 0);
        final IntersectionMapping intersectionMapping = crossTableDescription.getIntersection().get(0);

        assertNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, intersectionMapping.getCreateCell());
        final CreateCellTool createCellTool = DescriptionFactory.eINSTANCE.createCreateCellTool();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                intersectionMapping.setCreate(createCellTool);
            }

        });
        assertNotNull("The setting of the create cell tool failed.", intersectionMapping.getCreate());
    }

    /**
     * REQ-064 : Sirius shall enable to specify the automated actions to
     * be performed when modifying a Relationship in the Cross Array.
     * 
     * This test checks that we can set label edit tool on columns.
     * 
     * @throws Exception
     *             if the test has problems not handle
     */
    public void test_REQ_064() throws Exception {
        // Add a create line tool on the first line mapping
        final TableDescription tableDescription = find("Cross Table Colors");
        assertNotNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription);
        assertTrue(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription instanceof CrossTableDescription);
        final CrossTableDescription crossTableDescription = (CrossTableDescription) tableDescription;

        assertTrue(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, crossTableDescription.getIntersection().size() > 0);
        final IntersectionMapping intersectionMapping = crossTableDescription.getIntersection().get(0);

        assertNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, intersectionMapping.getDirectEdit());
        final LabelEditTool labelEditTool = DescriptionFactory.eINSTANCE.createLabelEditTool();
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                intersectionMapping.setDirectEdit(labelEditTool);
            }

        });
        assertNotNull("The setting of the label edit tool failed.", intersectionMapping.getDirectEdit());
    }
}
