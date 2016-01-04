/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.TreeTestCase;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.ui.business.api.helper.TreeUIHelper;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;

/**
 * Test that the tool have effects within do a refresh. Test the deletion tools.
 * Test an operation action that add new element Test the edit tool. Correspond
 * to ticket VP-3779 and VP-3780.
 * 
 * @author jdupont
 * 
 */
public class TreeItemRefreshWithToolsTest extends TreeTestCase {

    /**
     * Number elements of first level in tree representation.
     */
    protected static final int ELEMENTS_NUMBER_IN_TREE = 3;

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/tree/unit/refreshtools/test.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/tree/unit/refreshtools/test.odesign";

    private static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/tree/unit/refreshtools/representations.aird";

    private static final String REPRESENTATION_NAME = "tree";

    private static Tree tree;

    private AbstractDTreeEditor treeEditor;

    private DTree dTree;

    private TreeDescription desc;

    private DTreeElement treeElement;


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
        dTree = (DTree) getRepresentations(REPRESENTATION_NAME).toArray()[0];
        desc = dTree.getDescription();
        treeElement = dTree.getOwnedTreeItems().get(0);
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, dTree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        treeEditor = (AbstractDTreeEditor) openedEditor;
        tree = treeEditor.getTableViewer().getTreeViewer().getTree();
    }
    
    private int countNamedEClasses(EObject semanticModel, String name) {
        int count = 0;
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
     * Test when using the default delete tool the element is deleted from
     * representation without refresh.
     */
    public void testDeleteFromTool() {
        checkInitialization();
        // Deletion element NewEClass1 in tree.
        applyDeletionTool(treeElement);
        TestsUtil.synchronizationWithUIThread();

        // Counts number of NewEClass1
        int instanceCount = countNamedEClasses(semanticModel, "NewEClass1");

        // Check that the element is removed
        Assert.assertEquals("Wrong count of element having the wanted value.", 0, instanceCount);

        // Check there is an element within
        int expected = ELEMENTS_NUMBER_IN_TREE - 1;
        Assert.assertEquals("We have " + expected + " elements in ecore model, so we should have " + expected + " elements in tree.", expected, dTree.getOwnedTreeItems().size());

        // Check that the deletion is effective visually
        Assert.assertEquals("The delete is not effetive in editor", TreeItemRefreshWithToolsTest.getModelHtmlAfterDelete(), getCurrentHtml());
    }

    /**
     * Test when using the Edit tool, the element is rename from representation
     * without refresh.
     */
    public void testEditTool() {
        checkInitialization();

        EClass class1 = (EClass) ((EPackage) dTree.getTarget()).getEClassifier("NewEClass1");
        DTreeItem treeItem = (DTreeItem) getFirstRepresentationElement(dTree, class1);

        // Edit element EClass1 To ECLass1Rename.
        applyDirectEditTool(dTree, treeItem, "NewEClass1Rename");
        TestsUtil.synchronizationWithUIThread();

        // Check that the renaming is effective visually
        Assert.assertEquals("The rename is not effetive in editor", TreeItemRefreshWithToolsTest.getModelHtmlAfterRename(), getCurrentHtml());
    }

    /**
     * Test when using the tool "Create Sibling Class", the element is created
     * from representation without refresh.
     */
    public void testToolCreateSiblingClass() {
        checkInitialization();

        // Check that the OperationAction named 'Create Sibling Class' is
        // present in tree
        Assert.assertEquals("No creation tool named Create Sibling Class is defined on the tree representation.", "Create Sibling Class", ((OperationAction) (desc.getSubItemMappings().get(0)
                .getPopupMenus().get(0).getMenuItemDescriptions().get(0))).getName());

        OperationAction operationAction = ((OperationAction) (desc.getSubItemMappings().get(0).getPopupMenus().get(0).getMenuItemDescriptions().get(0)));

        EClass class1 = (EClass) ((EPackage) dTree.getTarget()).getEClassifier("NewEClass1");
        DTreeItem treeItem = (DTreeItem) getFirstRepresentationElement(dTree, class1);

        // Creation of element from OperationAction named 'Create Sibling
        // Class'.
        applyOperationAction(operationAction, treeItem);
        TestsUtil.synchronizationWithUIThread();

        // Check there is an element more
        int expected = ELEMENTS_NUMBER_IN_TREE + 1;
        Assert.assertEquals("We have " + expected + " elements in ecore model, so we should have " + expected + " elements in tree.", expected, dTree.getOwnedTreeItems().size());

        // Check that the creation is effective visually
        Assert.assertEquals("The creation is not effetive in editor", TreeItemRefreshWithToolsTest.getModelHtmlAfterOperationAction(), getCurrentHtml());
    }

    /**
     * Check that the test case correspond to what is expected.
     */
    private void checkInitialization() {
        // Retrieve the visual result.
        String currentHtml = TreeUIHelper.toContentHTMl(tree);

        Assert.assertEquals("The editor has not the good number element", TreeItemRefreshWithToolsTest.getModelHtml(), currentHtml);

        Assert.assertNotNull("Unit test data is not correct", desc);

        // Check that there is all elements in tree (3)
        Assert.assertEquals("We have " + ELEMENTS_NUMBER_IN_TREE + " elements in ecore model, so we should have " + ELEMENTS_NUMBER_IN_TREE + " elements in tree.", ELEMENTS_NUMBER_IN_TREE, dTree
                .getOwnedTreeItems().size());

        // Counts number of NewEClass1
        int instanceCount = countNamedEClasses(semanticModel, "NewEClass1");

        // Check that there is the element NewEclass1
        Assert.assertEquals("Wrong count of element having the wanted value.", 1, instanceCount);
    }

    /**
     * Retrieve the visual result.
     * 
     * @return the String visual result.
     */
    private static String getCurrentHtml() {
        return TreeUIHelper.toContentHTMl(tree);
    }

    /**
     * The visual result expected.
     * 
     * @return the String visual result.
     */
    private static String getModelHtml() {
        List<String> expected = new ArrayList<String>();
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass3"));
        return TreeUIHelper.toHTML(expected);
    }

    /**
     * The visual result expected after delete NewEClass1.
     * 
     * @return the String visual result.
     */
    private static String getModelHtmlAfterDelete() {
        List<String> expected = new ArrayList<String>();
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass3"));
        return TreeUIHelper.toHTML(expected);
    }

    /**
     * The visual result expected after rename NewEClass1 to NewEClass1Rename.
     * 
     * @return the String visual result.
     */
    private static String getModelHtmlAfterRename() {
        List<String> expected = new ArrayList<String>();
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass1Rename"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass3"));
        return TreeUIHelper.toHTML(expected);
    }

    /**
     * The visual result expected after add new element.
     * 
     * @return the String visual result.
     */
    private static String getModelHtmlAfterOperationAction() {
        List<String> expected = new ArrayList<String>();
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("NewEClass3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf(""));
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
