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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.TreeTestCase;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.junit.Assert;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Tests for ensuring that the Tree Item Edition Tool works as expected.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TreeItemEditionToolTest extends TreeTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/tree/unit/directedit/vp912/vp912.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/tree/unit/directedit/vp912/vp912.odesign";

    private static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/tree/unit/directedit/vp912/vp912.aird";

    private static final String REPRESENTATION_NAME = "TreeWithDirectEdit";

    private String elementNameP2 = "P2";

    private String elementNameC1 = "C1";

    private String elementNameC2 = "C2";

    private String elementNameC1Renamed = "C1Renamed";

    private String elementNameMyAttRenammed = "myAttRenammed";

    private String elementNameC1CategorieRenamed = "C1 CategorieRenamed";

    private DTree tree;

    private EPackage semanticModel;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
        tree = (DTree) getRepresentations(REPRESENTATION_NAME).toArray()[0];
        DialectUIManager.INSTANCE.openEditor(session, tree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        semanticModel = (EPackage) tree.getTarget();
    }

    /**
     * Ensures that editing a DTreeItem with an edition tool with no behavior
     * (no model operation) does not change the semantic element neither than
     * the treeitem.
     */
    public void testDirectEditWithNoBehavior() {
        // Step 1 : getting the semantic element to edit
        EPackage pkgToEdit = Iterables.filter(semanticModel.getESubpackages(), new Predicate<EPackage>() {

            public boolean apply(EPackage input) {
                return elementNameP2.equals(input.getName());
            }
        }).iterator().next();

        // Step 2 : getting the tree items corresponding to this element
        DTreeItem treeitem = (DTreeItem) getFirstRepresentationElement(tree, pkgToEdit);

        // Step 3 : apply tool on treeItem
        // and ensure that the tool was correctly applied, i.e. nothing has been
        // done
        applyDirectEditTool(tree, treeitem, "P2Renamed");

        Assert.assertEquals(elementNameP2, treeitem.getName());
        Assert.assertEquals(elementNameP2, pkgToEdit.getName());

        // Step 4 : testing undo/Redo
        applyUndo();
        Assert.assertEquals(elementNameP2, treeitem.getName());
        Assert.assertEquals(elementNameP2, pkgToEdit.getName());

        applyRedo();
        Assert.assertEquals(elementNameP2, treeitem.getName());
        Assert.assertEquals(elementNameP2, pkgToEdit.getName());

    }

    /**
     * Tests edition tool with preconditions. The Edition Tool defined has a
     * precondition that will be checked for the first DTreeItem and false for
     * the second.
     */
    public void testDirectEditWithPrecondition() {
        // Step 1 : getting the semantic elements to edit
        EClass class1 = (EClass) semanticModel.getEClassifier(elementNameC1);
        EClass class2 = (EClass) semanticModel.getEClassifier(elementNameC2);

        // Step 2 : getting the tree items corresponding to these elements
        DTreeItem treeitem1 = (DTreeItem) getFirstRepresentationElement(tree, class1);
        DTreeItem treeitem2 = (DTreeItem) getFirstRepresentationElement(tree, class2);

        // Step 3 : apply tool on treeItem 1
        // and ensure that the tool was correctly applied
        applyDirectEditTool(tree, treeitem1, elementNameC1Renamed);

        Assert.assertEquals(elementNameC1Renamed, treeitem1.getName());
        Assert.assertEquals(elementNameC1Renamed, class1.getName());

        // Step 4 : apply tool on treeItem 2
        // and ensure that the tool was not applied
        applyDirectEditTool(tree, treeitem2, "C2Renamed");
        TestsUtil.synchronizationWithUIThread();
        Assert.assertEquals(elementNameC2, treeitem2.getName());
        Assert.assertEquals(elementNameC2, class2.getName());

        // Step 5 : testing undo/redo
        applyUndo();
        Assert.assertEquals(elementNameC1, treeitem1.getName());
        Assert.assertEquals(elementNameC1, class1.getName());
        Assert.assertEquals(elementNameC2, treeitem2.getName());
        Assert.assertEquals(elementNameC2, class2.getName());

        applyRedo();
        Assert.assertEquals(elementNameC1Renamed, treeitem1.getName());
        Assert.assertEquals(elementNameC1Renamed, class1.getName());
        Assert.assertEquals(elementNameC2, treeitem2.getName());
        Assert.assertEquals(elementNameC2, class2.getName());
    }

    /**
     * Ensures that Edition Tool uses correctly the edit Mask.
     */
    public void testDirectEditWithEditMask() {
        // Step 1 : getting the semantic elements to edit
        EPackage pkgToEdit = Iterables.filter(semanticModel.getESubpackages(), new Predicate<EPackage>() {

            public boolean apply(EPackage input) {
                return elementNameP2.equals(input.getName());
            }
        }).iterator().next();
        EAttribute attributeToEdit = ((EClass) pkgToEdit.getEClassifier("C4")).getEAttributes().iterator().next();

        // Step 2 : getting the tree items corresponding to these elements
        DTreeItem treeitem = (DTreeItem) getFirstRepresentationElement(tree, attributeToEdit);

        // Step 3 : apply tool on treeItem 1
        // and ensure that the tool was correctly applied
        applyDirectEditTool(tree, treeitem, "myAttRenammed[4,-1]");

        Assert.assertEquals(elementNameMyAttRenammed, treeitem.getName());
        Assert.assertEquals(elementNameMyAttRenammed, attributeToEdit.getName());
        // We must also ensure that bounds of attributes have been modified
        Assert.assertEquals(4, attributeToEdit.getLowerBound());
        Assert.assertEquals(-1, attributeToEdit.getUpperBound());

        // Step 4 : testing undo/redo
        applyUndo();
        Assert.assertEquals("myAtt", treeitem.getName());
        Assert.assertEquals("myAtt", attributeToEdit.getName());
        Assert.assertEquals(1, attributeToEdit.getLowerBound());
        Assert.assertEquals(1, attributeToEdit.getUpperBound());

        applyRedo();
        Assert.assertEquals(elementNameMyAttRenammed, treeitem.getName());
        Assert.assertEquals(elementNameMyAttRenammed, attributeToEdit.getName());
        Assert.assertEquals(4, attributeToEdit.getLowerBound());
        Assert.assertEquals(-1, attributeToEdit.getUpperBound());
    }

    /**
     * Ensures that direct edit on TreeItem having no associated Description
     * Tool simply renames the TreeItem and not the semantic element.
     */
    public void testDirectEditWithoutEditionTool() {
        // Step 1 : getting the semantic elements to edit
        EClass class1 = (EClass) semanticModel.getEClassifier(elementNameC1);

        // Step 2 : getting the tree items corresponding to these elements
        DTreeItem treeitem1 = (DTreeItem) getRepresentationElementWithName(tree, "C1 Categorie");
        String originalName = treeitem1.getName();

        // Step 3 : apply tool on treeItem 1
        applyDirectEditTool(tree, treeitem1, elementNameC1CategorieRenamed);

        // and ensure that the tool was not applied
        Assert.assertEquals(originalName, treeitem1.getName());
        Assert.assertEquals(elementNameC1, class1.getName());

        // Step 4 : testing undo/redo
        applyUndo();
        Assert.assertEquals("C1 Categorie", treeitem1.getName());
        Assert.assertEquals(elementNameC1, class1.getName());

        applyRedo();
        Assert.assertEquals(originalName, treeitem1.getName());
        Assert.assertEquals(elementNameC1, class1.getName());
    }

    /**
     * Test direct edit with imports.
     */
    public void testDirectEditDefinitionWithImports() {
        // TODO : define this test as soon as TreeItemMapping import will be
        // defined
    }

    @Override
    protected void tearDown() throws Exception {
        tree = null;
        semanticModel = null;
        super.tearDown();
    }

}
