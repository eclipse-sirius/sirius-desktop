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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.TreeTestCase;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.ui.business.api.helper.TreeUIHelper;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;

/**
 * Test refresh on tree.
 * 
 * @author jdupont
 */
public class TreeItemRefreshTest extends TreeTestCase {

    /**
     * Tree description id.
     */
    private String TREE_DESCRIPTION_ID = "Tree";

    /**
     * Number elements of first level in tree representation.
     */
    protected static final int ELEMENTS_NUMBER_IN_TREE = 12;

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/tree/unit/refresh/tree.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/tree/unit/refresh/tree.odesign";

    private static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/tree/unit/refresh/tree.aird";

    private static final String NEWECLASS = "NewECLass";

    private DTreeEditor treeEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
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
     * Test the case where all element are the same name in the semantic model.
     * 
     * @throws Exception
     */
    public void testRefreshElement() throws Exception {
        final TreeDescription treeDescription = find(TREE_DESCRIPTION_ID);
        Assert.assertNotNull(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, treeDescription);

        Assert.assertEquals(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, treeDescription.getSubItemMappings().size());
        // TreeItemMapping treeItemMapping =
        // treeDescription.getSubItemMappings().get(1);

        DTree newTree = (DTree) getRepresentations(TREE_DESCRIPTION_ID).toArray()[0];

        final TreeDescription desc = newTree.getDescription();

        // final DTreeElement treeElement = (DTreeElement)
        // newTree.getOwnedTreeItems().get(1);

        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        treeEditor = (DTreeEditor) openedEditor;

        Tree tree = (Tree) treeEditor.getControl();

        String currentHtml = TreeUIHelper.toContentHTMl(tree);

        Assert.assertTrue("The editor has not the good number element", TreeItemRefreshTest.getModelHtml().length() == currentHtml.length());

        int instanceCount = -1;

        Assert.assertNotNull("Unit test data is not correct", desc);

        // Check that there is all elements in tree (13)
        Assert.assertEquals("We have 13 elements in ecore model, so we should have 13 elements in tree.", ELEMENTS_NUMBER_IN_TREE, newTree.getOwnedTreeItems().size());

        instanceCount = countNamedEClasses(semanticModel, "NewEClass");

        Assert.assertEquals("Wrong count of element having the wanted value.", 4, instanceCount);

        applyRefresh(newTree);

        instanceCount = countNamedEClasses(semanticModel, "NewEClass");

        Assert.assertEquals("Wrong count of elementhaving the wanted value.", 4, instanceCount);
        // Check there is an element more
        Assert.assertEquals("We have 13 elements in ecore model, so we should have 13 elements in tree.", ELEMENTS_NUMBER_IN_TREE + 1, newTree.getOwnedTreeItems().size());

        currentHtml = TreeUIHelper.toContentHTMl(tree);

        // Check that the deletion is effective visually
        Assert.assertTrue("The refesh is not effetive in editor", TreeItemRefreshTest.getModelHtml().length() == currentHtml.length());

    }

    /**
     * Verify that tree is refresh after semantic model modification.
     * 
     * @throws Exception
     */
    public void testRefreshTreeAfterSemanticModelModification() throws Exception {
        final TreeDescription treeDescription = find(TREE_DESCRIPTION_ID);
        Assert.assertNotNull(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, treeDescription);

        Assert.assertEquals(TreeTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, treeDescription.getSubItemMappings().size());

        DTree newTree = (DTree) getRepresentations(TREE_DESCRIPTION_ID).toArray()[0];

        treeEditor = (DTreeEditor) DialectUIManager.INSTANCE.openEditor(session, newTree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        Tree tree = (Tree) treeEditor.getControl();

        String currentHtml = TreeUIHelper.toContentHTMl(tree);

        // TODO : There is an issue at refresh. When we open the representation
        // it recreates an instance of the class that exists 4 times.
        Assert.assertTrue("The editor has not the good number element", TreeItemRefreshTest.getModelHtml().length() == currentHtml.length());

        int instanceCount = -1;

        instanceCount = countNamedEClasses(semanticModel, "EClass1");

        Assert.assertEquals("Wrong count of element having the wanted value.", 1, instanceCount);

        final EPackage semanticPackage = (EPackage) newTree.getTarget();

        session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                semanticPackage.getEClassifier("EClass1").setName("EClass21");
            };

        });

        instanceCount = countNamedEClasses(semanticModel, "EClass21");

        applyRefresh(newTree);

        currentHtml = TreeUIHelper.toContentHTMl(tree);

        Assert.assertTrue("The tree representation must be contain the new EClass name. The refresh doesn't perform.", currentHtml.contains("EClass21"));

    }

    private static String getModelHtml() {
        List<String> expected = new ArrayList<String>();
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Enum"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("new package 1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("Enum2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("new package 2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("EClass2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("new package 3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("new EClass 3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf(NEWECLASS));
        TreeUIHelper.addLineToTree(expected, String.valueOf(NEWECLASS));
        TreeUIHelper.addLineToTree(expected, String.valueOf(NEWECLASS));
        TreeUIHelper.addLineToTree(expected, String.valueOf(NEWECLASS));
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
