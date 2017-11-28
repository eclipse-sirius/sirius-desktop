/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.api.tools;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.common.TreeCommonTest;
import org.eclipse.sirius.tests.unit.common.TreeEcoreModeler;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;

/**
 * Test for navigation description.
 * 
 * @author nlepine
 * 
 */
public class NavigationDescriptionTest extends TreeCommonTest implements EcoreModeler, TreeEcoreModeler {

    private DTree treeRepresentation;

    private AbstractDTreeEditor treeEditor;

    private TreeDescription desc;

    private EPackage semanticModel;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        initTree();
        TestsUtil.synchronizationWithUIThread();
    }

    private void initTree() {
        treeRepresentation = (DTree) getRepresentations(TREE_DESCRIPTION_ID).toArray()[0];
        Assert.assertNotNull("The tree representation is null", treeRepresentation);
        desc = treeRepresentation.getDescription();
        Assert.assertNotNull("The tree description is null", desc);

        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, treeRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        Assert.assertNotNull("The tree editor is not opened", openedEditor);
        Assert.assertTrue("The tree editor is not opened", openedEditor instanceof DTreeEditor);
        treeEditor = (AbstractDTreeEditor) openedEditor;

        Assert.assertNotNull("The tree editor is not opened", openedEditor);
        semanticModel = (EPackage) treeRepresentation.getTarget();

    }

    /**
     * Test creation description tool on Tree.
     * 
     */
    public void testCreationDescription() {

        Assert.assertFalse(desc.getOwnedRepresentationCreationDescriptions().isEmpty());
        RepresentationCreationDescription representationCreationDescription = desc.getOwnedRepresentationCreationDescriptions().get(0);

        // Step 1 : getting the semantic elements to edit
        EClass class1 = (EClass) semanticModel.getEClassifier("EClass1");

        // Step 2 : getting the tree items corresponding to these elements
        DTreeItem treeitem1 = (DTreeItem) getFirstRepresentationElement(treeRepresentation, class1);

        Collection<DRepresentation> representations = getRepresentations("Tree on Class");
        Assert.assertTrue(representations.size() == 1);

        applyCreationDescriptionTool(representationCreationDescription, treeitem1, "new Creation Description For Tree");

        representations = getRepresentations("Tree on Class");
        Assert.assertTrue(representations.size() == 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(treeEditor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }
}
