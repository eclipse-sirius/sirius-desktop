/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.TreeTestCase;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.ui.business.api.helper.TreeUIHelper;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;

/**
 * Test available variables for TreeItem's semantic candidates expression.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class TreeVariablesTest extends TreeTestCase {

    /**
     * Representation name.
     */
    private static String TREE_REPRESENTATION_NAME = "Tree";

    /**
     * The path for resources test.
     */
    private static String PATH = "/data/tree/unit/variables/";

    /**
     * Semantic model path.
     */
    private static String SEMANTIC_RESOURCE_FILENAME = "My.ecore";

    /**
     * Viewpoint specific model filename.
     */
    private static String MODELER_RESOURCE_FILENAME = "project.odesign";

    /**
     * Session name.
     */
    private static String SESSION_RESOURCE_FILENAME = "representations.aird";

    final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_RESOURCE_FILENAME;

    final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_RESOURCE_FILENAME;

    final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_FILENAME;

    /**
     * the opened editor.
     */
    private IEditorPart openedEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
        DTree tree = (DTree) getRepresentations(TREE_REPRESENTATION_NAME).toArray()[0];
        openedEditor = DialectUIManager.INSTANCE.openEditor(session, tree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Ensure that available variables for TreeItem's semantic candidates
     * expression are correctly executed and all tree elements are represented
     * as expected.
     */
    public void testAvailableVariablesForTreeItem() {
        // Get the HTML format of opened tree
        AbstractDTreeEditor treeEditor = (AbstractDTreeEditor) openedEditor;
        Tree tree = treeEditor.getTableViewer().getTreeViewer().getTree();
        String currentHtml = TreeUIHelper.toContentHTMl(tree);

        // Check that all elements are present in the tree
        assertEquals("Elements are not represented as expected", getModelHtml(), currentHtml);
    }

    private static String getModelHtml() {
        List<String> expected = new ArrayList<String>();
        TreeUIHelper.addLineToTree(expected, String.valueOf("p1"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p4"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p2"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p3"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p5"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p6"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p7"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p7"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p8"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p6"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p7"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p9"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p10"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p11"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p11"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p12"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p10"));
        TreeUIHelper.addLineToTree(expected, String.valueOf("p11"));
        return TreeUIHelper.toHTML(expected);
    }

}
