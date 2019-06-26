/*******************************************************************************
 * Copyright (c) 2011, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.api.tools;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.TreeTestCase;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.api.command.DTreeItemExpansionChangeCommand;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemDragTool;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.tree.ui.tools.internal.editor.provider.DTreeItemDropListener;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.junit.Assert;

/**
 * Test class containing 2 tests that cannot pass with Eclipse 3.3 without
 * modifications of the emf transaction fork (see VP-926 comments and VP-1484).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TreeItemDragAndDropToolUsingRecordingCommandCompositionTest extends TreeTestCase implements DnDModel {

    private DTree tree;

    private EObject semanticModel;

    private EStructuralFeature wageFeature;

    private EStructuralFeature nameFeature;

    private DTreeItemDropListener dropListener;

    private DTreeEditor editor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {

        // Step 1 : setting up session and representation
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME_1, SESSION_MODEL_FILENAME, SEMANTIC_META_MODEL_FILENAME, MODELER_MODEL_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_1, TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);

        Iterator<DRepresentationDescriptor> iterator = getRepresentationDescriptors(REPRESENTATION_NAME).iterator();
        while (iterator.hasNext() && tree == null) {
            DRepresentationDescriptor next = iterator.next();
            if (next.getName().equals("dnd1")) {
                tree = (DTree) next.getRepresentation();
            }
        }
        // Step 2 : getting the dropListener associated to the opened Editor
        editor = (DTreeEditor) DialectUIManager.INSTANCE.openEditor(session, tree, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        dropListener = new DTreeItemDropListener(this.editor.getViewer(), session.getTransactionalEditingDomain(), getCommandFactory(), accessor);

        semanticModel = tree.getTarget();
        wageFeature = ((EClass) semanticModel.eClass().getEPackage().getEClassifier("Employee")).getEStructuralFeature("wage");
        nameFeature = ((EClass) semanticModel.eClass().getEPackage().getEClassifier("NamedEntity")).getEStructuralFeature("name");

    }

    /**
     * Ensures that DnD on several DTreeItems inside another DTreeItem works as
     * expected.
     */
    public void testDnDWithTwoElementsSelection() {
        // Dragging :
        // - "employe4" from "Marketing"
        // - "employe1" from "Projects"
        // into "Maintenance"
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject richEmployee1 = semanticModel.eContents().get(1).eContents().get(1);
        EObject richEmployee2 = semanticModel.eContents().get(0).eContents().get(0);

        EObject richDepartment1 = semanticModel.eContents().get(4);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        Assert.assertEquals(2000, richEmployee2.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        DTreeItem itemEmploye1 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee1);
        DTreeItem itemEmploye2 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee2);

        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(itemEmploye1);
        itemSources.add(itemEmploye2);
        // itemSources.add(itemEmploye3);

        DTreeItem itemRichCompany1 = (DTreeItem) getFirstRepresentationElement(tree, richDepartment1);

        // Step 3 : apply the tool on TreeItem
        // and ensure that the tool was correctly applied
        applyDnDTool(this.dropListener, itemSources, itemRichCompany1, true);

        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));
        Assert.assertEquals(2500, richEmployee2.eGet(wageFeature));

        // Step 4 : testing undo/Redo
        // One undo for the expansion in applyDnDTool();
        applyUndo();
        applyUndo();
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        Assert.assertEquals(2000, richEmployee2.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, false);

        // One redo for the expansion in applyDnDTool();
        applyRedo();
        applyRedo();
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));
        Assert.assertEquals(2500, richEmployee2.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
    }

    /**
     * Ensures that a DnD performed from a selection of the model Content View
     * to an other DTreeEditor works correctly (multiple selection).
     * 
     */
    public void testDnDFromModelContentViewToTreeEditorWithMultipleSelection() {
        // Dragging :
        // - "employe4" from "Marketing"
        // - "employe1" from "Projects"
        // into "Maintenance"
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject richEmployee1 = semanticModel.eContents().get(1).eContents().get(1);
        EObject richEmployee2 = semanticModel.eContents().get(0).eContents().get(0);
        EObject richDepartment1 = semanticModel.eContents().get(4);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        Assert.assertEquals(2000, richEmployee2.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        Set<EObject> semanticSources = new LinkedHashSet<>();
        semanticSources.add(richEmployee1);
        semanticSources.add(richEmployee2);

        DTreeItem itemRichCompany1 = (DTreeItem) getFirstRepresentationElement(tree, richDepartment1);

        // Step 3 : apply the tool on TreeItem
        // and ensure that the tool was correctly applied
        applyDnDTool(this.dropListener, semanticSources, itemRichCompany1, true);

        // We now get the created TreeItems from this dragAndDrop
        Set<EObject> itemSources = new LinkedHashSet<>();
        DTreeItem itemEmploye1 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee1);
        DTreeItem itemEmploye2 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee2);
        itemSources.add(itemEmploye1);
        itemSources.add(itemEmploye2);

        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));
        Assert.assertEquals(2500, richEmployee2.eGet(wageFeature));

        // Step 4 : testing undo/Redo
        // One undo for the expansion in applyDnDTool();
        applyUndo();
        applyUndo();
        checkDTreeItemContainment(itemSources, itemRichCompany1, false);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        Assert.assertEquals(2000, richEmployee2.eGet(wageFeature));

        // One redo for the expansion in applyDnDTool();
        applyRedo();
        applyRedo();
        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));
        Assert.assertEquals(2500, richEmployee2.eGet(wageFeature));
    }

    /**
     * Applies the DnD Tool : dragging the given item Sources and dropping them
     * into the given targetContainer.
     * 
     * @param dndListener
     *            the DTreeItemDropListener to use
     * @param itemSources
     *            the sources of the DnD (may be a collection of DTreeItems or
     *            simple EObjects if the drop has been performed from the Model
     *            Content View).
     * @param targetContainer
     *            the target of the DnD
     * @param shouldBeValidDrop
     *            indicates if this drop operation should is valid or not
     */
    private void applyDnDTool(DTreeItemDropListener dndListener, Set<EObject> itemSources, DTreeItemContainer targetContainer, boolean shouldBeValidDrop) {

        // Step 1 : setting the local transfer selection to simulate dnd
        IStructuredSelection selection = new StructuredSelection(itemSources.toArray());
        LocalSelectionTransfer.getTransfer().setSelection(selection);

        // Step 2 : checking that the DnD validation provides the same result as
        // expected
        boolean isValidDrop = dndListener.validateDrop(targetContainer, 0, null);
        Assert.assertEquals("The validation of the DnD operation did not occur as expected ", shouldBeValidDrop, isValidDrop);
        org.eclipse.sirius.tools.api.interpreter.InterpreterUtil.getInterpreter(semanticModel).setModelAccessor(accessor);
        // Step 3 : if the drop operation is valid, perform drop
        if (isValidDrop) {
            dndListener.performDrop(null);
        }

        if (targetContainer instanceof DTreeItem) {
            DTreeItem dTreeItem = (DTreeItem) targetContainer;
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            GlobalContext globalContext = new GlobalContext(session.getModelAccessor(), session.getInterpreter(), session.getSemanticResources());
            Command cmd = new DTreeItemExpansionChangeCommand(globalContext, domain, dTreeItem, true);
            domain.getCommandStack().execute(cmd);
        }
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Ensures that all the given item Sources are contained in the given
     * targetContainer.
     * 
     * @param itemSources
     *            the sources of the DnD
     * @param targetContainer
     *            the target of the DnD
     * @param boolean
     *            indicates if the sources of the DnD should be contained in the
     *            given target Container
     */
    private void checkDTreeItemContainment(Set<EObject> itemSources, DTreeItemContainer targetContainer, boolean shouldBeContained) {

        for (EObject item : itemSources) {
            Assert.assertTrue(item instanceof DTreeItem);
            String targetContainerName = null;
            if (targetContainer instanceof DTreeItem) {
                targetContainerName = ((DTreeItem) targetContainer).getName();
            } else {
                targetContainerName = new DRepresentationQuery(((DTree) targetContainer)).getRepresentationDescriptor().getName();
            }
            DTreeItem oldItem = (DTreeItem) item;
            DTreeItem newItem = (DTreeItem) getRepresentationElementWithName(TreeHelper.getTree(targetContainer), (String) oldItem.getTarget().eGet(this.nameFeature));
            if (shouldBeContained) {
                Assert.assertNotNull((String) oldItem.getTarget().eGet(this.nameFeature) + " should now be part of "
                        + new DRepresentationQuery(TreeHelper.getTree(targetContainer)).getRepresentationDescriptor().getName() + " tree", newItem);
                Assert.assertEquals(oldItem.getName() + " should be contained in " + targetContainerName, targetContainer, newItem.getContainer());
            } else {
                if (newItem != null) {
                    Assert.assertNotSame(oldItem.getName() + " should not be contained in " + targetContainerName, targetContainer, newItem.getContainer());
                }
            }
        }
    }

    /**
     * <p>
     * Returns the {@link TreeItemDragTool} to use if the given dragged TreeItem
     * is dropped on the given newContainer.
     * </p>
     * <b> This method can return null if no tool is applicable </b>
     * 
     * @param dragged
     *            the dragged TreeItem that will be dropped on the given
     *            newContainer
     * @param newContainer
     *            the new Container of the dragged TreeItem
     * @return the {@link TreeItemDragTool} to use if the given dragged TreeItem
     *         is dropped on the given newContainer
     */
    protected TreeItemContainerDropTool getDragToolFromDragOperation(DTreeItem dragged, DTreeItemContainer newContainer) {
        return null;
    }
}
