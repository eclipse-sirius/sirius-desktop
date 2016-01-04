/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.api.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.TreeTestCase;
import org.eclipse.sirius.tests.unit.common.TreeCommonTest;
import org.eclipse.sirius.tests.unit.common.TreeEcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.description.DescriptionFactory;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.ui.business.api.helper.TreeUIHelper;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;

/**
 * Test Item Creation tool.
 * 
 * @author jdupont
 */
public class TreeItemCreationToolsTest extends TreeCommonTest implements TreeEcoreModeler {

    private TreeItemMapping treeItemMapping;

    private AbstractDTreeEditor treeEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        initViewpoint(TREE_VIEWPOINT_NAME);
        TestsUtil.synchronizationWithUIThread();
    }

    private int countNamedEClasses(EObject semanticModel) {
        int count = 0;
        String name = "NewEClassCreationTool";
        EObject root = semanticModel.eResource().getContents().get(0);
        TreeIterator<EObject> iter = root.eAllContents();
        while (iter.hasNext()) {
            EObject current = iter.next();
            if (current instanceof EClass && name.equals(((EClass) current).getName())) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Test add a new tree item creation tool in the VSM.
     * 
     * @throws Exception
     *             throw exception in problem case
     */
    public void _testCreateCreationTools() throws Exception {
        final TreeItemCreationTool treeItemCreationTool = DescriptionFactory.eINSTANCE.createTreeItemCreationTool();
        // Add a create line tool on the first line mapping
        final TreeDescription treeDescription = find(TREE_DESCRIPTION_ID);
        Assert.assertNotNull(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, treeDescription);

        Assert.assertEquals(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, treeDescription.getSubItemMappings().size());
        treeItemMapping = treeDescription.getSubItemMappings().get(0);
        Assert.assertTrue("Package".equals(treeItemMapping.getName()));

        Assert.assertTrue(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, treeItemMapping.getCreate().size() == 0);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                treeItemMapping.getCreate().add(treeItemCreationTool);
            }

        });
        Assert.assertNotNull("The setting of the creation tool failed.", treeItemMapping.getCreate());
        Assert.assertTrue("The setting of the creation tool failed.", treeItemMapping.getCreate().size() > 0);
    }

    /**
     * Test add a new tree item creation tool in the VSM.
     * 
     * @throws Exception
     *             throw exception in problem case
     */
    public void _testCreateCreationToolsOnRepresentation() throws Exception {
        final TreeItemCreationTool treeItemCreationTool = DescriptionFactory.eINSTANCE.createTreeItemCreationTool();
        // Add a create line tool on the first line mapping
        final TreeDescription treeDescription = find(TREE_DESCRIPTION_ID);
        Assert.assertNotNull(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, treeDescription);

        Assert.assertTrue(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, treeDescription.getCreateTreeItem().size() == 1);

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                treeDescription.getCreateTreeItem().add(treeItemCreationTool);
            }

        });
        Assert.assertNotNull("The setting of the creation tool failed.", treeDescription.getCreateTreeItem());
        Assert.assertTrue("The setting of the creation tool failed.", treeDescription.getCreateTreeItem().size() == 2);
    }

    /**
     * Test create creation tool on Tree. Test creation in semantic model. Test
     * creation in Tree and test that creation is effective visually. Test undo
     * /redo after creation on tree.
     */
    public void _testCreateUndo() {
        final TreeDescription treeDescription = find(TREE_DESCRIPTION_ID);
        Assert.assertNotNull(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, treeDescription);

        Assert.assertEquals(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, treeDescription.getSubItemMappings().size());
        treeItemMapping = treeDescription.getSubItemMappings().get(1);

        DTree newTree = (DTree) getRepresentations(TREE_DESCRIPTION_ID).toArray()[0];

        final TreeDescription desc = newTree.getDescription();
        Assert.assertNotNull("No tree descrition.", desc);

        Assert.assertTrue(newTree.getOwnedTreeItems().size() >= 2);
        final DTreeElement treeElement = newTree.getOwnedTreeItems().get(1);

        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        treeEditor = (AbstractDTreeEditor) openedEditor;

        Tree tree = treeEditor.getTableViewer().getTreeViewer().getTree();

        testTreeContent(tree, newTree);

        Assert.assertNotNull("Unit test data is not correct", desc);

        try {
            testCreationOnMapping(newTree, treeElement, tree);

            testUndo(newTree, tree);

            testRedo(newTree, tree);

        } catch (EvaluationException e) {
            Assert.fail("Exception while trying to get the value.");
            e.printStackTrace();
        }

    }

    private void testCreationOnMapping(DTree newTree, final DTreeElement treeElement, Tree tree) throws EvaluationException {
        int instanceCount = countNamedEClasses(semanticModel);

        // Check that there is the element that will be removed
        Assert.assertEquals("Wrong count of element having the wanted value.", 0, instanceCount);

        // Creation element NewEClassCreationTool in tree.
        applyCreationTool(newTree, treeElement.getTarget(), treeItemMapping.getCreate().get(0));

        instanceCount = countNamedEClasses(semanticModel);

        // Check that the element are created.
        Assert.assertEquals("Wrong count of elementhaving the wanted value.", 1, instanceCount);

        // Check that the creation is effective visually
        String currentHtml = TreeUIHelper.toContentHTMl(tree);
        Assert.assertEquals("The creation is not effetive in editor", TreeItemCreationToolsTest.getModelHtmlAfterCreate(), currentHtml);
    }

    /**
     * Test create creation tool on Tree representation. Test creation in
     * semantic model. Test creation in Tree and test that creation is effective
     * visually. Test undo /redo after creation on tree.
     */
    public void testCreateInRepresentation() {
        final TreeDescription treeDescription = find(TREE_DESCRIPTION_ID);
        Assert.assertNotNull(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, treeDescription);

        DTree newTree = (DTree) getRepresentations(TREE_DESCRIPTION_ID).toArray()[0];

        final TreeDescription desc = newTree.getDescription();
        Assert.assertNotNull("No tree descrition.", desc);

        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        Assert.assertNotNull("The tree editor is not opened", openedEditor);
        Assert.assertTrue("The tree editor is not opened", openedEditor instanceof DTreeEditor);
        treeEditor = (AbstractDTreeEditor) openedEditor;

        Tree tree = treeEditor.getTableViewer().getTreeViewer().getTree();
        Assert.assertNotNull("The tree is null", tree);

        testTreeContent(tree, newTree);

        try {
            testCreationToolOnRepresentation(treeDescription, newTree, tree);

            // Undo creation.
            testUndo(newTree, tree);
            // Redo creation.
            testRedo(newTree, tree);
        } catch (EvaluationException e) {
            Assert.fail("Exception while trying to get the value.");
            e.printStackTrace();
        }

    }

    private void testTreeContent(Tree tree, DTree newTree) {
        String currentHtml = TreeUIHelper.toContentHTMl(tree);
        Assert.assertEquals("The editor has not the good number element", TreeItemCreationToolsTest.getModelHtml(), currentHtml);
        // Check that there is all elements in tree (8)
        Assert.assertEquals("We have 8 elements in ecore model, so we should have 8 elements in tree.", ELEMENTS_NUMBER_IN_TREE, newTree.getOwnedTreeItems().size());
    }

    private void testRedo(DTree newTree, Tree tree) throws EvaluationException {
        String currentHtml;
        int instanceCount;

        // first redo for the full refresh
        applyRedo();
        applyRedo();
        TestsUtil.synchronizationWithUIThread();
        instanceCount = countNamedEClasses(semanticModel);

        // Check that the element are created
        Assert.assertEquals("Wrong count of elementhaving the wanted value.", 1, instanceCount);
        // Check there is an element more
        Assert.assertEquals("We have 10 elements in ecore model, so we should have 10 elements in tree.", ELEMENTS_NUMBER_IN_TREE + 1, newTree.getOwnedTreeItems().size());

        currentHtml = TreeUIHelper.toContentHTMl(tree);

        // Check that the creation is effective visually
        Assert.assertEquals("The creation is not effetive in editor", TreeItemCreationToolsTest.getModelHtmlAfterCreate(), currentHtml);
    }

    private void testUndo(DTree newTree, Tree tree) throws EvaluationException {
        String currentHtml;
        int instanceCount;
        // first undo for the full refresh
        applyUndo();
        applyUndo();

        TestsUtil.synchronizationWithUIThread();

        // Check that there is all elements in tree (9)
        Assert.assertEquals("We have 9 elements in ecore model, so we should have 9 elements in tree.", ELEMENTS_NUMBER_IN_TREE, newTree.getOwnedTreeItems().size());

        instanceCount = countNamedEClasses(semanticModel);

        // Check that there is the element Create was remove
        Assert.assertEquals("Wrong count of element having the wanted value.", 0, instanceCount);

        currentHtml = TreeUIHelper.toContentHTMl(tree);

        // Check that undo is effective visually
        Assert.assertTrue("The undo is not effetive visually", TreeItemCreationToolsTest.getModelHtml().length() == currentHtml.length());
    }

    private void testCreationToolOnRepresentation(final TreeDescription treeDescription, DTree newTree, Tree tree) throws EvaluationException {
        int instanceCount = -1;
        String currentHtml = TreeUIHelper.toContentHTMl(tree);
        instanceCount = countNamedEClasses(semanticModel);

        // Check that there is the element that will be removed
        Assert.assertEquals("Wrong count of element having the wanted value.", 0, instanceCount);

        // Creation element NewEClassCreationTool in tree.
        Assert.assertFalse("No creation tool is defined on the tree representation.", treeDescription.getCreateTreeItem().isEmpty());
        applyCreationTool(newTree, newTree.getTarget(), treeDescription.getCreateTreeItem().get(0));
        TestsUtil.synchronizationWithUIThread();

        instanceCount = countNamedEClasses(semanticModel);

        // Check that the element are created.
        Assert.assertEquals("Wrong count of elementhaving the wanted value.", 1, instanceCount);
        // Check there is an element more
        Assert.assertEquals("We have 10 elements in ecore model, so we should have 9 elements in tree.", ELEMENTS_NUMBER_IN_TREE + 1, newTree.getOwnedTreeItems().size());

        refresh(newTree, true);
        currentHtml = TreeUIHelper.toContentHTMl(tree);

        // Check that the creation is effective visually
        Assert.assertEquals("The creation is not effetive in editor", TreeItemCreationToolsTest.getModelHtmlAfterCreate(), currentHtml);
    }

    private static String getModelHtml() {
        List<String> expected = new ArrayList<String>();
        TreeUIHelper.addLineToTree(expected, String.valueOf("Package 1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P1 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 2 P2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 2 P2 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("sous package 1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P11"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 2 P12"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Package 2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P2 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Package 3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P3 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass1 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("myAttribute"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("myAttribute2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass2 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 3 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Enum"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Enum2"));
        return TreeUIHelper.toHTML(expected);
    }

    private static String getModelHtmlAfterCreate() {
        List<String> expected = new ArrayList<String>();
        TreeUIHelper.addLineToTree(expected, String.valueOf("Package 1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P1 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 2 P2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 2 P2 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("sous package 1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P11"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P11 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 2 P12"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 2 P12 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Package 2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P2 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Package 3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 1 P3 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass1 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("myAttribute"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("myAttribute2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass2 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 3 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClassCreationTool"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClassCreationTool Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Enum"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Enum2"));
        return TreeUIHelper.toHTML(expected);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(treeEditor, false);
        TestsUtil.synchronizationWithUIThread();

        treeItemMapping = null;
        treeEditor = null;

        super.tearDown();
    }

}
