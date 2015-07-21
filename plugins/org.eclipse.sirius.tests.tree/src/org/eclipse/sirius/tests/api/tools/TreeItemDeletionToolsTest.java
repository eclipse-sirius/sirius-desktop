/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.TreeTestCase;
import org.eclipse.sirius.tests.unit.common.TreeCommonTest;
import org.eclipse.sirius.tests.unit.common.TreeEcoreModeler;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.description.DescriptionFactory;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.ui.business.api.helper.TreeUIHelper;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;

/**
 * Test Item Deletion tool.
 * 
 * @author jdupont
 */
public class TreeItemDeletionToolsTest extends TreeCommonTest implements EcoreModeler, TreeEcoreModeler {

    private static final String REQUEST = "<%getRootContainer().eAllContents(\"EClass\")[name==\"EClass1\"].nSize()%>";

    private AbstractDTreeEditor treeEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test add a new tree item deletion tool in the VSM.
     * 
     * @throws Exception
     *             throw Exception in problem case
     */
    public void testCreateDeletionTools() throws Exception {
        // Add a create line tool on the first line mapping
        final TreeDescription treeDescription = find(TREE_DESCRIPTION_ID);
        Assert.assertNotNull(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, treeDescription);

        Assert.assertEquals(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, treeDescription.getSubItemMappings().size());
        final TreeItemMapping treeItemMapping = treeDescription.getSubItemMappings().get(0);
        Assert.assertTrue(treeItemMapping.getName().equals("Package"));

        Assert.assertNull(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, treeItemMapping.getDelete());
        final TreeItemDeletionTool treeItemDeletionTool = DescriptionFactory.eINSTANCE.createTreeItemDeletionTool();
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                treeItemMapping.setDelete(treeItemDeletionTool);
            }

        });
        Assert.assertNotNull("The setting of the delete tool failed.", treeItemMapping.getDelete());
    }

    /**
     * Test delete tool on Tree. Test deletion in semantic model. Test deletion
     * in Tree and test that deletion is effective visually. Test undo/Redo
     * after deletion on tree.
     * 
     * @throws Exception
     */
    public void testDeleteUndo() throws Exception {
        DTree newTree = (DTree) getRepresentations(TREE_DESCRIPTION_ID).toArray()[0];

        final TreeDescription desc = newTree.getDescription();

        final DTreeElement treeElement = newTree.getOwnedTreeItems().get(3);

        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        treeEditor = (AbstractDTreeEditor) openedEditor;

        Tree tree = treeEditor.getTableViewer().getTreeViewer().getTree();

        String currentHtml = TreeUIHelper.toContentHTMl(tree);

        Assert.assertEquals("The editor has not the good number element", TreeItemDeletionToolsTest.getModelHtml(), currentHtml);

        int instanceCount = -1;

        Assert.assertNotNull("Unit test data is not correct", desc);

        // Check that there is all elements in tree (8)
        Assert.assertEquals("We have 8 elements in ecore model, so we should have 9 elements in tree.", ELEMENTS_NUMBER_IN_TREE, newTree.getOwnedTreeItems().size());

        instanceCount = interpreter.evaluateInteger(semanticModel, REQUEST).intValue();

        // Check that there is the element that will be removed
        Assert.assertEquals("Wrong count of element having the wanted value.", 1, instanceCount);

        // Deletion element EClass1 in tree.
        applyDeletionTool(treeElement);
        TestsUtil.synchronizationWithUIThread();

        instanceCount = interpreter.evaluateInteger(semanticModel, REQUEST).intValue();

        // Check that the element are removed
        Assert.assertEquals("Wrong count of element having the wanted value.", 0, instanceCount);
        // Check there is an element within
        Assert.assertEquals("We have 7 elements in ecore model, so we should have 7 elements in tree.", ELEMENTS_NUMBER_IN_TREE - 1, newTree.getOwnedTreeItems().size());

        currentHtml = TreeUIHelper.toContentHTMl(tree);

        // Check that the deletion is effective visually
        Assert.assertEquals("The delete is not effetive in editor", TreeItemDeletionToolsTest.getModelHtmlAfterDelete(), currentHtml);

        // Undo deletion.
        applyUndo();
        TestsUtil.synchronizationWithUIThread();

        // Check that there is all elements in tree (8)
        Assert.assertEquals("We have 8 elements in ecore model, so we should have 8 elements in tree.", ELEMENTS_NUMBER_IN_TREE, newTree.getOwnedTreeItems().size());

        instanceCount = interpreter.evaluateInteger(semanticModel, REQUEST).intValue();

        // Check that there is the element removed was restored
        Assert.assertEquals("Wrong count of element having the wanted value.", 1, instanceCount);

        currentHtml = TreeUIHelper.toContentHTMl(tree);

        // Check that undo is effective visually
        Assert.assertEquals("The undo is not effetive visually", TreeItemDeletionToolsTest.getModelHtml(), currentHtml);

        // Redo deletion.
        applyRedo();
        TestsUtil.synchronizationWithUIThread();

        instanceCount = interpreter.evaluateInteger(semanticModel, REQUEST).intValue();

        // Check that the element are removed
        Assert.assertEquals("Wrong count of element having the wanted value.", 0, instanceCount);
        // Check there is an element within
        Assert.assertEquals("We have 8 elements in ecore model, so we should have 8 elements in tree.", ELEMENTS_NUMBER_IN_TREE - 1, newTree.getOwnedTreeItems().size());

        currentHtml = TreeUIHelper.toContentHTMl(tree);

        // Check that the deletion is effective visually
        Assert.assertEquals("The delete is not effetive in editor", TreeItemDeletionToolsTest.getModelHtmlAfterDelete(), currentHtml);

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

    private static String getModelHtmlAfterDelete() {
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
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass2 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass 3 Categorie"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Enum"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Enum2"));
        return TreeUIHelper.toHTML(expected);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(treeEditor, false);
        TestsUtil.synchronizationWithUIThread();

        treeEditor = null;

        super.tearDown();
    }

}
