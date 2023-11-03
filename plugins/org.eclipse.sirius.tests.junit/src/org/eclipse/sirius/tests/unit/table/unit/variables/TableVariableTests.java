/*******************************************************************************
 * Copyright (c) 2023 Obeo.
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
package org.eclipse.sirius.tests.unit.table.unit.variables;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableEditionEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Test for table using 'table' variable and annotation.
 * 
 * @author <a href="mailto:nicolas.peransin@obeo.fr">Nicolas Peransin</a>
 */
public class TableVariableTests extends SiriusTestCase {

    private static final String PATH = "/data/table/unit/variables/"; //$NON-NLS-1$
    
    private DTable dTable;

    private DialectEditor tableEditor;
    
    private List<String> FILENAMES = List.of( // Model resources
            "tables.uml", //$NON-NLS-1$
            "representations.aird" //$NON-NLS-1$
    );

    
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        FILENAMES.forEach(filename -> {
            var from = PATH + filename;
            var to = '/' + TEMPORARY_PROJECT_NAME + '/' + filename;
            EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, from, to);
        });
        
        genericSetUp(TEMPORARY_PROJECT_NAME + '/' + FILENAMES.get(0), 
                "/org.eclipse.sirius.tests.junit/data/table/unit/variables/TableVariable.odesign",  //$NON-NLS-1$
                TEMPORARY_PROJECT_NAME + '/' + FILENAMES.get(1));

        
        dTable = (DTable) getRepresentations("VirtualTable").iterator().next(); //$NON-NLS-1$
        tableEditor = (DialectEditor) DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());

        TestsUtil.synchronizationWithUIThread();
    }


    /**
     * Tests that refresh is done with
     * {@link DialectUIManager#refreshEditor(DialectEditor, org.eclipse.core.runtime.IProgressMonitor)}
     * for a {@link DTableEditionEditor}.
     * 
     */
    public void testAddLines() {
        assertTableContent(new String[][] {
            { /*Columns*/ "Path", "name", "visibility", "isAbstract", "isLeaf" },  //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
            { "A", null, null, null, null, null }, //$NON-NLS-1$
            { "AbstractClass1", "MyModel", "AbstractClass1", "public", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "AbstractClass2", "MyModel", "AbstractClass2", "public", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "C", null, null, null, null, null }, //$NON-NLS-1$
            { "Class1", "MyModel", "Class1", "public", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "Class2", "MyModel", "Class2", "public", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "Class3", "MyModel", "Class3", "protected", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "Class4", "MyModel", "Class4", "public", "false", "false" } //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        });
        
        org.eclipse.uml2.uml.Class newEClass = UMLFactory.eINSTANCE.createClass();
        newEClass.setName("Behavior"); //$NON-NLS-1$
        TransactionalEditingDomain edt = session.getTransactionalEditingDomain();
        edt.getCommandStack().execute(AddCommand.create(edt, semanticModel, // UMLModel
                UMLPackage.Literals.PACKAGE__PACKAGED_ELEMENT, newEClass));
        
        // Refresh table.
        DialectUIManager.INSTANCE.refreshEditor(tableEditor, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTableContent(new String[][] {
            { /*Columns*/ "Path", "name", "visibility", "isAbstract", "isLeaf" },  //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
            { "A", null, null, null, null, null }, //$NON-NLS-1$
            { "AbstractClass1", "MyModel", "AbstractClass1", "public", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "AbstractClass2", "MyModel", "AbstractClass2", "public", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "B", null, null, null, null, null }, //$NON-NLS-1$
            { "Behavior", "MyModel", "Behavior", "public", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "C", null, null, null, null, null }, //$NON-NLS-1$
            { "Class1", "MyModel", "Class1", "public", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "Class2", "MyModel", "Class2", "public", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "Class3", "MyModel", "Class3", "protected", "false", "false" }, //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            { "Class4", "MyModel", "Class4", "public", "false", "false" } //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        });
        
    }
    
    private void assertTableContent(String [][] content) {
        List<String> columnNames = dTable.getColumns().stream() // collect names
                .map(it -> it.getLabel()).collect(Collectors.toList());
        assertEquals(Arrays.asList(content[0]), columnNames);
        int lineIndex = 1; // after columns
        for (DLine line : dTable.getLines()) {
            lineIndex = assertLineContent(line, content, lineIndex);
        }
    }
    
    private int assertLineContent(DLine line, String [][] content, int index) {
        String[] values = new String[dTable.getColumns().size() + 1];
        values[0] = line.getLabel();
        for (DCell cell : line.getCells()) {
            int columnIndex = dTable.getColumns().indexOf(cell.getColumn()) + 1;
            values[columnIndex] = cell.getLabel();
        }
        assertEquals(Arrays.asList(content[index]), Arrays.asList(values));

        int newIndex = index + 1;
        for (DLine child : line.getLines()) {
            newIndex = assertLineContent(child, content, newIndex);
        }
        
        return newIndex;
    }
    

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        dTable = null;
        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        tableEditor = null;
        super.tearDown();
    }

}
