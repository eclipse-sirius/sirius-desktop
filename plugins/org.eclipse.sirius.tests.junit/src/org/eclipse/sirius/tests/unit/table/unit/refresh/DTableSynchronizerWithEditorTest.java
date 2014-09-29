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
package org.eclipse.sirius.tests.unit.table.unit.refresh;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.business.api.helper.TableUIHelper;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Test the table synchronizer (and check the result in the editor). This tests
 * are the same of DTableSynchronizerTest, but they check that the result in the
 * editor is the expected.
 * 
 * @author lredor
 */
public class DTableSynchronizerWithEditorTest extends SiriusDiagramTestCase implements TableRefreshTestsUMLModeler {

    /**
     * incorrect data.
     */
    public static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    private static final String THE_TABLE_HAVE_CHANGED_EVEN_IF_NOTHING_HAS_BEEN_MODIFIED_IN_THE_SEMANTIC_MODEL = "the table have changed even if nothing has been modified in the semantic model";

    private static final String THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE = "The editor has not the correct type";
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
    }

    public void testSimpleEditionTable() throws Exception {
        final DTable dTable = (DTable) createRepresentation(COLORED_CLASSES_TABLE, semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        List<List<String>> expected = new ArrayList<List<String>>();
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
        final String expectedHtml = TableUIHelper.toHTML(expected);

        for (int i = 0; i < 10; i++) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            final String newHTML = TableUIHelper.toContentHTMl(tree);
            assertEquals("A refresh without semantic changes modified the table.", expectedHtml, newHTML);
        }
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testCrossClassWithGeneralization() throws Exception {
        assertTrue("Unit test data is not correct", semanticModel instanceof Model);
        final Model umlModel = (Model) semanticModel;

        final DTable dTable = (DTable) createRepresentation(MODEL_GENERALIZATION_CROSS_TABLE, semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        final AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "X", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "X", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "X", "_", "_" });
        final String expectedHtml = TableUIHelper.toHTML(expected);

        for (int i = 0; i < 10; i++) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            final String newHTML = TableUIHelper.toContentHTMl(tree);
            assertEquals(THE_TABLE_HAVE_CHANGED_EVEN_IF_NOTHING_HAS_BEEN_MODIFIED_IN_THE_SEMANTIC_MODEL, expectedHtml, newHTML);
        }

        /*
         * Let's remove a generalization and see whether the cells updates or
         * not.
         */
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            protected void doExecute() {
                final org.eclipse.uml2.uml.Class claz = (Class) umlModel.getPackagedElement("Class1");
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
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            TestsUtil.synchronizationWithUIThread();
            assertEquals("No change in the table even if a generalization has been removed", TableUIHelper.toHTML(expectedMissingGeneralization), TableUIHelper.toContentHTMl(tree));
        }

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testCrossClassWithAssociations() throws Exception {
        DTable dTable = (DTable) createRepresentation("Model Association Cross Table", semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Class1", "Class2", "Class3", "AbstractClass1", "AbstractClass2", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "_", "class 1 to class 2", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "_", "class 2 to class 3", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "_", "_", "_" });
        assertEquals(THE_TABLE_HAVE_CHANGED_EVEN_IF_NOTHING_HAS_BEEN_MODIFIED_IN_THE_SEMANTIC_MODEL, TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testCrossClassForegroundColors() throws Exception {
        DTable dTable = (DTable) createRepresentation(CROSS_TABLE_COLORS, semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

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

        assertEquals(TableUIHelper.toHTML(expected), TableUIHelper.toForegroundStyleHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testCrossClassBackgroundColors() throws Exception {
        DTable dTable = (DTable) createRepresentation(CROSS_TABLE_COLORS, semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

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

        assertEquals(TableUIHelper.toHTML(expected), TableUIHelper.toBackgroundStyleHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testVariablesForCellInCrossTable() throws Exception {
        DTable dTable = (DTable) createRepresentation("VariablesAccess", semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "Class1 Class1 AbstractClass1 AbstractClass1", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "Class2 Class2 AbstractClass2 AbstractClass2", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "Class4 Class4 Class2 Class2", "_", "_" });

        assertEquals("Access to one of the following variables must be a problem: $line, $column, $lineSemantic, $columnSemantic.", TableUIHelper.toHTML(expected), TableUIHelper.toContentHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testAddSemanticLineInSimpleEditionTable() throws Exception {
        final DTable dTable = (DTable) createRepresentation(COLORED_CLASSES_TABLE, semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        List<List<String>> expected = new ArrayList<List<String>>();
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
        String expectedHtml = TableUIHelper.toHTML(expected);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
        // Wait the viewer.update run in asyncExec mode
        TestsUtil.synchronizationWithUIThread();
        String newHTML = TableUIHelper.toContentHTMl(tree);
        assertEquals("A refresh without semantic changes modified the table.", expectedHtml, newHTML);

        // Add a class in the model
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                Model model = (Model) semanticModel;
                Class aClass = UMLFactory.eINSTANCE.createClass();
                aClass.setName("newClass");
                model.getPackagedElements().add(aClass);
            }
        });

        expected = new ArrayList<List<String>>();
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
        TableUIHelper.addLineToTable(expected, new String[] { "Class : newClass", "newClass", "false", "false" });
        expectedHtml = TableUIHelper.toHTML(expected);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
        // Wait the viewer.update run in asyncExec mode
        TestsUtil.synchronizationWithUIThread();
        newHTML = TableUIHelper.toContentHTMl(tree);
        assertEquals("A refresh without semantic changes modified the table.", expectedHtml, newHTML);

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testAddSemanticElementInSimpleEditionTableFromOtherDiagram() throws Exception {
        final DTable dTable = (DTable) createRepresentation(COLORED_CLASSES_TABLE, semanticModel);
        IEditorPart openedTableEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedTableEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedTableEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        List<List<String>> expected = new ArrayList<List<String>>();
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
        String expectedHtml = TableUIHelper.toHTML(expected);

        for (int i = 0; i < 10; i++) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            String newHTML = TableUIHelper.toContentHTMl(tree);
            assertEquals("A refresh without semantic changes modified the table.", expectedHtml, newHTML);
        }

        // Open a diagram on the same model
        final DDiagram dDiagram = (DDiagram) createRepresentation("Classes", semanticModel);
        IEditorPart diagramEditor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Add a new class with tool
        Model model = (Model) semanticModel;
        int nbClassBefore = model.getPackagedElements().size();
        applyNodeCreationTool("New class", dDiagram, dDiagram);
        assertEquals("The tool failed : bad number of classes", nbClassBefore + 1, model.getPackagedElements().size());

        expected = new ArrayList<List<String>>();
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
        TableUIHelper.addLineToTable(expected, new String[] { "Class : newClass", "newClass", "false", "false" });
        expectedHtml = TableUIHelper.toHTML(expected);

        for (int i = 0; i < 10; i++) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            String newHTML = TableUIHelper.toContentHTMl(tree);
            assertEquals("A refresh after semantic change in another representation (diagram) don't modified the table.", expectedHtml, newHTML);
        }

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedTableEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testDeleteSemanticElementsInSimpleEditionTableFromOtherDiagram() throws Exception {
        final DTable dTable = (DTable) createRepresentation(COLORED_CLASSES_TABLE, semanticModel);
        IEditorPart openedTableEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedTableEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedTableEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        List<List<String>> expected = new ArrayList<List<String>>();
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
        String expectedHtml = TableUIHelper.toHTML(expected);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
        // Wait the viewer.update run in asyncExec mode
        TestsUtil.synchronizationWithUIThread();
        String newHTML = TableUIHelper.toContentHTMl(tree);
        assertEquals("A refresh without semantic changes modified the table.", expectedHtml, newHTML);

        // Open a diagram on the same model
        final DDiagram dDiagram = (DDiagram) createRepresentation("Classes", semanticModel);
        IEditorPart diagramEditor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Add a new class with tool
        Model model = (Model) semanticModel;
        int nbClassBefore = model.getPackagedElements().size();
        applyNodeCreationTool("New class", dDiagram, dDiagram);
        assertEquals("The tool failed : bad number of classes", nbClassBefore + 1, model.getPackagedElements().size());

        expected = new ArrayList<List<String>>();
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
        TableUIHelper.addLineToTable(expected, new String[] { "Class : newClass", "newClass", "false", "false" });
        expectedHtml = TableUIHelper.toHTML(expected);

        for (int i = 0; i < 10; i++) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            newHTML = TableUIHelper.toContentHTMl(tree);
            assertEquals("A refresh after semantic change (add element) in another representation (diagram) don't modified the table.", expectedHtml, newHTML);
        }

        // Delete the new class
        final Class newClass = (Class) model.getPackagedElements().get(model.getPackagedElements().size() - 1);
        assertEquals("The last create class has not the right name", "newClass", newClass.getName());
        DDiagramElement newClassDiagramElement = getFirstDiagramElement(dDiagram, newClass);
        applyDeletionTool(newClassDiagramElement);
        TestsUtil.synchronizationWithUIThread();

        // Delete the last class
        final Class lastClass = (Class) model.getPackagedElements().get(model.getPackagedElements().size() - 1);
        assertEquals("The last create class has not the right name", "Class4", lastClass.getName());
        DDiagramElement lastClassDiagramElement = getFirstDiagramElement(dDiagram, lastClass);
        applyDeletionTool(lastClassDiagramElement);
        TestsUtil.synchronizationWithUIThread();

        expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "name", "isAbstract ?", "Active ?" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class1", "Class1", "true", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Property", "P1", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class2", "Class2", "false", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Property", "P2", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : Class3", "Class3", "false", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Property", "P3", "", "" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : AbstractClass1", "AbstractClass1", "true", "false" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class : AbstractClass2", "AbstractClass2", "true", "false" });
        expectedHtml = TableUIHelper.toHTML(expected);

        for (int i = 0; i < 10; i++) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));

            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            newHTML = TableUIHelper.toContentHTMl(tree);
            assertEquals("A refresh after semantic change (remove elements) in another representation (diagram) don't modified the table.", expectedHtml, newHTML);
        }

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedTableEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testAddSemanticElementInCrossTableFromOtherDiagram() throws Exception {
        assertTrue("Unit test data is not correct", semanticModel instanceof Model);

        final DTable dTable = (DTable) createRepresentation(MODEL_GENERALIZATION_CROSS_TABLE, semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        final AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "X", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "X", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "X", "_", "_" });
        String expectedHtml = TableUIHelper.toHTML(expected);

        for (int i = 0; i < 10; i++) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            assertEquals(THE_TABLE_HAVE_CHANGED_EVEN_IF_NOTHING_HAS_BEEN_MODIFIED_IN_THE_SEMANTIC_MODEL, expectedHtml, TableUIHelper.toContentHTMl(tree));
        }

        // Open a diagram on the same model
        final DDiagram dDiagram = (DDiagram) createRepresentation("Classes", semanticModel);
        IEditorPart diagramEditor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Add a new class with tool
        Model model = (Model) semanticModel;
        int nbClassBefore = model.getPackagedElements().size();
        applyNodeCreationTool("New class", dDiagram, dDiagram);
        assertEquals("The tool failed : bad number of classes", nbClassBefore + 1, model.getPackagedElements().size());

        // Wait the viewer.update run in asyncExec mode
        TestsUtil.synchronizationWithUIThread();
        final List<List<String>> expectedWithNewClass = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4", "newClass" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class1", "X", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class2", "_", "X", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class3", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class4", "_", "_", "_", "X", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "newClass", "_", "_", "_", "_", "_", "_", "_" });
        expectedHtml = TableUIHelper.toHTML(expectedWithNewClass);

        for (int i = 0; i < 10; i++) {
            // Refresh the table
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            TestsUtil.synchronizationWithUIThread();
            assertEquals("A refresh after semantic change (add element) in another representation (diagram) don't modified the table.", expectedHtml, TableUIHelper.toContentHTMl(tree));
        }

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * @throws Exception
     */
    public void testDeleteSemanticElementsInCrossTableFromOtherDiagram() throws Exception {
        assertTrue("Unit test data is not correct", semanticModel instanceof Model);

        final DTable dTable = (DTable) createRepresentation(MODEL_GENERALIZATION_CROSS_TABLE, semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        final AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "X", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "X", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "X", "_", "_" });
        String expectedHtml = TableUIHelper.toHTML(expected);

        for (int i = 0; i < 10; i++) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            assertEquals(THE_TABLE_HAVE_CHANGED_EVEN_IF_NOTHING_HAS_BEEN_MODIFIED_IN_THE_SEMANTIC_MODEL, expectedHtml, TableUIHelper.toContentHTMl(tree));
        }

        // Open a diagram on the same model
        final DDiagram dDiagram = (DDiagram) createRepresentation("Classes", semanticModel);
        IEditorPart diagramEditor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Add a new class with tool
        Model model = (Model) semanticModel;
        int nbClassBefore = model.getPackagedElements().size();
        applyNodeCreationTool("New class", dDiagram, dDiagram);
        assertEquals("The tool failed to create the new class : bad number of classes", nbClassBefore + 1, model.getPackagedElements().size());

        final List<List<String>> expectedWithNewClass = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4", "newClass" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class1", "X", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class2", "_", "X", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class3", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class4", "_", "_", "_", "X", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "newClass", "_", "_", "_", "_", "_", "_", "_" });
        String expectedWithNewClassHtml = TableUIHelper.toHTML(expectedWithNewClass);

        for (int i = 0; i < 10; i++) {
            // Refresh the table
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            assertEquals("A refresh after semantic change (add element) in another representation (diagram) don't modified the table.", expectedWithNewClassHtml, TableUIHelper.toContentHTMl(tree));
        }

        // Delete the new class
        final Class newClass = (Class) model.getPackagedElements().get(model.getPackagedElements().size() - 1);
        assertEquals("The last create class has not the right name", "newClass", newClass.getName());
        DDiagramElement newClassDiagramElement = getFirstDiagramElement(dDiagram, newClass);
        applyDeletionTool(newClassDiagramElement);
        assertEquals("The tool failed to delete the new class : bad number of classes", nbClassBefore, model.getPackagedElements().size());

        // Delete the last class
        final Class lastClass = (Class) model.getPackagedElements().get(model.getPackagedElements().size() - 1);
        assertEquals("The last create class has not the right name", "Class4", lastClass.getName());
        DDiagramElement lastClassDiagramElement = getFirstDiagramElement(dDiagram, lastClass);
        applyDeletionTool(lastClassDiagramElement);
        assertEquals("The tool failed to delete the last class : bad number of classes", nbClassBefore - 1, model.getPackagedElements().size());

        final List<List<String>> expectedWithTwoClassLess = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3" });
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "AbstractClass1", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "AbstractClass2", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "Class1", "X", "_", "_", "_", "_", });
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "Class2", "_", "X", "_", "_", "_", });
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "Class3", "_", "_", "_", "_", "_", });
        String expectedWithTwoClassLessHtml = TableUIHelper.toHTML(expectedWithTwoClassLess);

        for (int i = 0; i < 10; i++) {
            // Refresh the table
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            assertEquals("A refresh after semantic change (remove elements) in another representation (diagram) don't modified the table.", expectedWithTwoClassLessHtml,
                    TableUIHelper.toContentHTMl(tree));
        }

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testDeleteSemanticElementsInCrossTableFromOtherDiagramInAutoRefreshMode() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        assertTrue("Unit test data is not correct", semanticModel instanceof Model);

        final DTable dTable = (DTable) createRepresentation(MODEL_GENERALIZATION_CROSS_TABLE, semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        final AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        final List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class1", "X", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class2", "_", "X", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class3", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "Class4", "_", "_", "_", "X", "_", "_" });
        String expectedHtml = TableUIHelper.toHTML(expected);

        // Wait the viewer.update run in asyncExec mode
        // TestUtil
        TestsUtil.synchronizationWithUIThread();
        assertEquals(THE_TABLE_HAVE_CHANGED_EVEN_IF_NOTHING_HAS_BEEN_MODIFIED_IN_THE_SEMANTIC_MODEL, expectedHtml, TableUIHelper.toContentHTMl(tree));

        // Open a diagram on the same model
        final DDiagram dDiagram = (DDiagram) createRepresentation("Classes", semanticModel);
        IEditorPart diagramEditor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Add a new class with tool
        Model model = (Model) semanticModel;
        int nbClassBefore = model.getPackagedElements().size();
        applyNodeCreationTool("New class", dDiagram, dDiagram);
        assertEquals("The tool failed to create the new class : bad number of classes", nbClassBefore + 1, model.getPackagedElements().size());

        final List<List<String>> expectedWithNewClass = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3", "Class4", "newClass" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "AbstractClass1", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "AbstractClass2", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class1", "X", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class2", "_", "X", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class3", "_", "_", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "Class4", "_", "_", "_", "X", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithNewClass, new String[] { "newClass", "_", "_", "_", "_", "_", "_", "_" });
        String expectedWithNewClassHtml = TableUIHelper.toHTML(expectedWithNewClass);

        // Wait the viewer.update run in asyncExec mode
        TestsUtil.synchronizationWithUIThread();
        assertEquals("A refresh after semantic change (add element) in another representation (diagram) don't modified the table.", expectedWithNewClassHtml, TableUIHelper.toContentHTMl(tree));

        // Delete the new class
        final Class newClass = (Class) model.getPackagedElements().get(model.getPackagedElements().size() - 1);
        assertEquals("The last create class has not the right name", "newClass", newClass.getName());
        DDiagramElement newClassDiagramElement = getFirstDiagramElement(dDiagram, newClass);
        applyDeletionTool(newClassDiagramElement);
        assertEquals("The tool failed to delete the new class : bad number of classes", nbClassBefore, model.getPackagedElements().size());

        // Delete the last class
        final Class lastClass = (Class) model.getPackagedElements().get(model.getPackagedElements().size() - 1);
        assertEquals("The last create class has not the right name", "Class4", lastClass.getName());
        DDiagramElement lastClassDiagramElement = getFirstDiagramElement(dDiagram, lastClass);
        applyDeletionTool(lastClassDiagramElement);
        assertEquals("The tool failed to delete the last class : bad number of classes", nbClassBefore - 1, model.getPackagedElements().size());

        // Wait the viewer.update run in asyncExec mode
        TestsUtil.synchronizationWithUIThread();
        final List<List<String>> expectedWithTwoClassLess = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "", "AbstractClass1", "AbstractClass2", "Class1", "Class2", "Class3" });
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "AbstractClass1", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "AbstractClass2", "_", "_", "_", "_", "_" });
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "Class1", "X", "_", "_", "_", "_", });
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "Class2", "_", "X", "_", "_", "_", });
        TableUIHelper.addLineToTable(expectedWithTwoClassLess, new String[] { "Class3", "_", "_", "_", "_", "_", });
        String expectedWithTwoClassLessHtml = TableUIHelper.toHTML(expectedWithTwoClassLess);

        assertEquals("A refresh after semantic change (remove elements) in another representation (diagram) don't modified the table.", expectedWithTwoClassLessHtml, TableUIHelper.toContentHTMl(tree));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Check that the initial width define in the VSM is OK during refresh on
     * the DColumn and in the SWT Tree.
     * 
     * TODO : This test failed because the first column initial width is not
     * implemented (the feature .
     */
    public void disabledTestInitialWidth() {
        final DTable dTable = (DTable) createRepresentation("TestInitialWidth", semanticModel);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(THE_EDITOR_HAS_NOT_THE_CORRECT_TYPE, openedEditor instanceof AbstractDTableEditor);
        AbstractDTableEditor tableEditor = (AbstractDTableEditor) openedEditor;
        Tree tree = tableEditor.getTableViewer().getTreeViewer().getTree();

        List<List<String>> expected = new ArrayList<List<String>>();
        TableUIHelper.addLineToTable(expected, new String[] { "", "Name", "isAbstract" });
        TableUIHelper.addLineToTable(expected, new String[] { "200", "250", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "200", "250", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "200", "250", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "200", "250", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "200", "250", "_" });
        TableUIHelper.addLineToTable(expected, new String[] { "200", "250", "_" });
        final String expectedHtml = TableUIHelper.toHTML(expected);

        for (int i = 0; i < 10; i++) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, dTable));
            // Wait the viewer.update run in asyncExec mode
            TestsUtil.synchronizationWithUIThread();
            final String newHTMLFromModel = TableUIHelper.toColumnWidthHTMl(dTable, false);
            assertEquals("A refresh without semantic changes modified the table (in DTable model).", expectedHtml, newHTMLFromModel);

            final String newHTMLFromTree = TableUIHelper.toColumnWidthHTMl(tree);
            assertEquals("A refresh without semantic changes modified the table (in SWT Tree).", expectedHtml, newHTMLFromTree);
        }
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(openedEditor, false);
        TestsUtil.synchronizationWithUIThread();

    }
}
