/*******************************************************************************
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES.
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

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.TreeTestCase;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.api.command.DTreeItemExpansionChangeCommand;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.tree.ui.tools.internal.editor.provider.DTreeItemDropListener;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.junit.Assert;

import com.google.common.collect.Iterables;

/**
 * Tests for ensuring that the Tree Item DnD Tool works as expected.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TreeItemDragAndDropToolTest extends TreeTestCase implements DnDModel {

    private DTree tree;

    private EObject semanticModel;

    private EStructuralFeature wageFeature;

    private EStructuralFeature nameFeature;

    private CustomDTreeItemDropListener dropListener;

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
        dropListener = new CustomDTreeItemDropListener(this.editor.getViewer(), session.getTransactionalEditingDomain(), getCommandFactory(), accessor);

        semanticModel = tree.getTarget();
        wageFeature = ((EClass) semanticModel.eClass().getEPackage().getEClassifier("Employee")).getEStructuralFeature("wage");
        nameFeature = ((EClass) semanticModel.eClass().getEPackage().getEClassifier("NamedEntity")).getEStructuralFeature("name");

    }

    /**
     * Ensures that DnD a single DTreeItem inside another DTreeItem works as
     * expected.
     */
    public void testDnDWithSingleSelection() {
        // Dragging "employe4" from "Marketing" to "Projects"
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject richEmployee1 = semanticModel.eContents().get(1).eContents().get(1);
        EObject richDepartment1 = semanticModel.eContents().get(0);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        DTreeItem itemEmploye1 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee1);
        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(itemEmploye1);

        DTreeItem itemRichCompany1 = (DTreeItem) getFirstRepresentationElement(tree, richDepartment1);

        // Step 3 : apply the tool on TreeItem
        // and ensure that the tool was correctly applied
        applyDnDTool(this.dropListener, itemSources, itemRichCompany1, true);

        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));

        // Step 4 : testing undo/Redo
        // One undo for the expansion in applyDnDTool();
        applyUndo();
        applyUndo();
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, false);

        // One redo for the expansion in applyDnDTool();
        applyRedo();
        applyRedo();
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
    }

    /**
     * Ensures that DnD on several DTreeItems inside another DTreeItem works as
     * expected.
     */
    public void testDnDWithMultipleSelection() {
        // Dragging :
        // - "employe4" from "Marketing"
        // - "employe1" from "Projects"
        // - "employe5" from "Projects"
        // into "Maintenance"
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject richEmployee1 = semanticModel.eContents().get(1).eContents().get(1);
        EObject richEmployee2 = semanticModel.eContents().get(0).eContents().get(0);
        EObject richEmployee3 = semanticModel.eContents().get(0).eContents().get(1);
        EObject richDepartment1 = semanticModel.eContents().get(4);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        Assert.assertEquals(2000, richEmployee2.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        DTreeItem itemEmploye1 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee1);
        DTreeItem itemEmploye2 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee2);
        DTreeItem itemEmploye3 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee3);
        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(itemEmploye1);
        itemSources.add(itemEmploye2);
        itemSources.add(itemEmploye3);

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
     * Ensures that a DnD operation which target is the Tree itself performs
     * correctly.
     */
    public void testDnDDirectlyOnTree() {
        // Dragging :
        // "Maintenance"
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject richDepartment1 = semanticModel.eContents().get(4);
        String oldDepartmentName = (String) richDepartment1.eGet(nameFeature);

        // Step 2 : getting the corresponding TreeItems
        DTreeItem itemRichCompany1 = (DTreeItem) getFirstRepresentationElement(tree, richDepartment1);
        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(itemRichCompany1);

        // Step 3 : apply the tool on TreeItem
        // and ensure that the tool was correctly applied
        applyDnDTool(this.dropListener, itemSources, this.tree, true);

        Assert.assertEquals(oldDepartmentName + "_DROPPED", richDepartment1.eGet(nameFeature));

        // Step 4 : testing undo/Redo
        applyUndo();
        Assert.assertEquals(oldDepartmentName, richDepartment1.eGet(nameFeature));

        applyRedo();
        Assert.assertEquals(oldDepartmentName + "_DROPPED", richDepartment1.eGet(nameFeature));

    }

    /**
     * Ensures that a DnD operation involving at least one DnD tool which
     * precondition is false cannot be performed.
     */
    public void testDnDWithFalseToolPrecondition() {
        // Dragging :
        // - "employe3" from "Interships" ("Poor To Poor" dnd tool :
        // precondition false)
        // - "employe4" from "Marketing"
        // into "R&D"
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject poorEmployee1 = semanticModel.eContents().get(3).eContents().get(0);
        EObject richEmployee2 = semanticModel.eContents().get(1).eContents().get(1);
        EObject poorDepartment1 = semanticModel.eContents().get(2);

        Assert.assertEquals(2000, poorEmployee1.eGet(wageFeature));
        Assert.assertEquals(2000, richEmployee2.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        DTreeItem itemEmploye1 = (DTreeItem) getFirstRepresentationElement(tree, poorEmployee1);
        DTreeItem itemEmploye2 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee2);

        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(itemEmploye1);
        itemSources.add(itemEmploye2);

        DTreeItem itemRichCompany1 = (DTreeItem) getFirstRepresentationElement(tree, poorDepartment1);

        // Step 3 : apply the tool on TreeItem :
        // ensure that the tool cannot be applied as dndTool associated to
        // employe3 has a false precondition
        applyDnDTool(this.dropListener, itemSources, itemRichCompany1, false);
    }

    /**
     * Ensures that a DnD operation involving at least one element (graphical or
     * semantic) locked by the Permission Authority cannot be performed.
     */
    public void testDnDWithPermissionAuthority() {
        // Dragging "employe4" from "Marketing" to "Projects"
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject richEmployee1 = semanticModel.eContents().get(1).eContents().get(1);
        EObject richDepartment1 = semanticModel.eContents().get(0);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        DTreeItem itemEmploye1 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee1);
        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(itemEmploye1);

        DTreeItem itemRichCompany1 = (DTreeItem) getFirstRepresentationElement(tree, richDepartment1);

        // Step 3 : activating the read-only permission authority : now semantic
        // elements cannot be edited
        ((ReadOnlyPermissionAuthority) PermissionAuthorityRegistry.getDefault().getPermissionAuthority(itemEmploye1)).activate();

        // Step 4 : apply the tool on TreeItem : as Permission Authority forbids
        // modification, the DnDTool should not be applicable
        applyDnDTool(this.dropListener, itemSources, itemRichCompany1, false);
    }

    /**
     * Ensures that a DnD performed from a DTreeEditor to an other DTreeEditor
     * works correctly.
     * 
     * @throws IOException
     *             if semantic model cannot be loaded
     */
    // FIXME enable this test
    public void _testDnDFromOneEditorToAnOther() throws IOException {
        // Dragging "employe4" from "Marketing" (from Tree Editor 2)
        // to "Juridiction" (from Tree Editor 2)

        // Step 2 : opening an editor on company2.xmi
        DTree tree2 = null;
        Iterator<DRepresentationDescriptor> iterator = getRepresentationDescriptors(REPRESENTATION_NAME).iterator();
        while (iterator.hasNext() && tree2 == null) {
            DRepresentationDescriptor next = iterator.next();
            if (next.getName().equals("dnd2")) {
                tree2 = (DTree) next.getRepresentation();
            }
        }
        DTreeEditor editor2 = (DTreeEditor) DialectUIManager.INSTANCE.openEditor(session, tree2, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        ModelUtils.load(URI.createPlatformPluginURI(SEMANTIC_MODEL_FILENAME_2, true), session.getTransactionalEditingDomain().getResourceSet());

        DTreeItemDropListener dropListener2 = new DTreeItemDropListener(editor2.getViewer(), session.getTransactionalEditingDomain(), getCommandFactory(), accessor);

        // Step 3 : getting the semantic elements to Drag and the new Container
        EObject richEmployee1 = semanticModel.eContents().get(1).eContents().get(1);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));

        // Step 4 : getting the corresponding TreeItems
        DTreeItem itemEmploye1 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee1);
        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(itemEmploye1);

        DTreeItem itemRichCompany1 = (DTreeItem) getRepresentationElementWithName(tree2, "Juridiction");

        // (DTreeItem) getRepresentationElementWithName(tree2, (String)
        // richDepartment1.eGet(nameFeature));

        // Step 5 : apply the tool on TreeItem
        // and ensure that the tool was correctly applied
        applyDnDTool(dropListener2, itemSources, itemRichCompany1, true);

        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));

        // Step 4 : testing undo/Redo
        applyUndo();
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, false);

        applyRedo();
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
    }

    /**
     * Ensures that a DnD performed from a selection of the model Content View
     * to an other DTreeEditor works correctly (single selection).
     * 
     */
    public void testDnDFromModelContentViewToTreeEditorWithSingleSelection() {
        // Dragging "employe4" from "Marketing" to "Projects"
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject richEmployee1 = semanticModel.eContents().get(1).eContents().get(1);
        EObject richDepartment1 = semanticModel.eContents().get(0);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        Set<EObject> semanticSources = new LinkedHashSet<>();
        semanticSources.add(richEmployee1);

        DTreeItem itemRichCompany1 = (DTreeItem) getFirstRepresentationElement(tree, richDepartment1);

        // Step 3 : apply the tool on TreeItem
        // and ensure that the tool was correctly applied
        applyDnDTool(this.dropListener, semanticSources, itemRichCompany1, true);

        // We now get the created TreeItems from this dragAndDrop
        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(getFirstRepresentationElement(tree, richEmployee1));

        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));

        // Step 4 : testing undo/Redo
        // One undo for the expansion in applyDnDTool();
        applyUndo();
        applyUndo();
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, false);

        // One redo for the expansion in applyDnDTool();
        applyRedo();
        applyRedo();
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));
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
        EObject richEmployee3 = semanticModel.eContents().get(0).eContents().get(1);
        EObject richDepartment1 = semanticModel.eContents().get(4);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        Assert.assertEquals(2000, richEmployee2.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        Set<EObject> semanticSources = new LinkedHashSet<>();
        semanticSources.add(richEmployee1);
        semanticSources.add(richEmployee2);
        semanticSources.add(richEmployee3);

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
     * Ensures that Drag and Drop operation performed not directly ON a TreeItem
     * but AFTER a TreeItem is working as expected.
     */
    public void testDnDWithDropAfter() {
        // Dragging "employe1" from "Projects" to "Marketing" (space after
        // employe4)
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject richEmployee1 = semanticModel.eContents().get(0).eContents().get(0);
        EObject richDepartment1 = semanticModel.eContents().get(1);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        DTreeItem itemEmploye1 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee1);
        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(itemEmploye1);

        DTreeItem itemRichCompany1 = (DTreeItem) getFirstRepresentationElement(tree, richDepartment1);

        // Step 3 : checking that the precedingSiblings value is correct
        this.dropListener.setCurrentLocation(ViewerDropAdapter.LOCATION_AFTER);
        checkPrecedingSiblingsValue(this.dropListener, itemSources, itemRichCompany1.getOwnedTreeItems().get(1), itemRichCompany1.getOwnedTreeItems());

        // Step 4 : apply the tool on TreeItem
        // and ensure that the tool was correctly applied
        applyDnDTool(this.dropListener, itemSources, itemRichCompany1.getOwnedTreeItems().get(1), true);

        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));

        // Step 5 : testing undo/Redo
        // One undo for the expansion in applyDnDTool();
        applyUndo();
        applyUndo();
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, false);

        // One redo for the expansion in applyDnDTool();
        applyRedo();
        applyRedo();
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
    }

    /**
     * Ensures that Drag and Drop operation performed not directly ON a TreeItem
     * but BEFORE a TreeItem is working as expected.
     */
    public void testDnDWithDropBefore() {
        // Dragging "employe1" from "Projects" to "Marketing" (space between
        // employe2 and employe4)
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject richEmployee1 = semanticModel.eContents().get(0).eContents().get(0);
        EObject richDepartment1 = semanticModel.eContents().get(1);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        DTreeItem itemEmploye1 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee1);
        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(itemEmploye1);

        DTreeItem itemRichCompany1 = (DTreeItem) getFirstRepresentationElement(tree, richDepartment1);

        // Step 3 : checking that the precedingSiblings value is correct
        this.dropListener.setCurrentLocation(ViewerDropAdapter.LOCATION_BEFORE);
        checkPrecedingSiblingsValue(this.dropListener, itemSources, itemRichCompany1.getOwnedTreeItems().get(1), itemRichCompany1.getOwnedTreeItems().subList(0, 1));

        // Step 4 : apply the tool on TreeItem
        // and ensure that the tool was correctly applied
        applyDnDTool(this.dropListener, itemSources, itemRichCompany1.getOwnedTreeItems().get(1), true);

        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));

        // Step 5 : testing undo/Redo
        // One undo for the expansion in applyDnDTool();
        applyUndo();
        applyUndo();
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, false);

        // One redo for the expansion in applyDnDTool();
        applyRedo();
        applyRedo();
        Assert.assertEquals(2500, richEmployee1.eGet(wageFeature));
        checkDTreeItemContainment(itemSources, itemRichCompany1, true);
    }

    /**
     * Ensures that the preceding siblings Value is correct when dropping a
     * TreeItem on another TreeItem.
     */
    public void testPrecedingSiblingsValues() {
        // Dragging "employe1" from "Projects" to "Marketing"
        // Step 1 : getting the semantic elements to Drag and the new Container
        EObject richEmployee1 = semanticModel.eContents().get(0).eContents().get(0);
        EObject richDepartment1 = semanticModel.eContents().get(1);
        Assert.assertEquals(2000, richEmployee1.eGet(wageFeature));

        // Step 2 : getting the corresponding TreeItems
        DTreeItem itemEmploye1 = (DTreeItem) getFirstRepresentationElement(tree, richEmployee1);
        Set<EObject> itemSources = new LinkedHashSet<>();
        itemSources.add(itemEmploye1);

        DTreeItem itemRichCompany1 = (DTreeItem) getFirstRepresentationElement(tree, richDepartment1);

        // Step 3 : as the item as been dropped directly on "Marketing"
        // the preceding Siblings should be all TreeItems contained by
        // "Marketing"
        checkPrecedingSiblingsValue(this.dropListener, itemSources, itemRichCompany1, itemRichCompany1.getOwnedTreeItems());
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
     * Ensures that the precedingSiblings value calculated by the DropListener
     * has correct value.
     * 
     * @param dndListener
     *            the DTreeItemDropListener to use
     * @param itemSources
     *            the sources of the DnD
     * @param targetContainer
     *            the target of the DnD
     * @param expectedPrecedingSiblings
     *            the expected value of the preceding siblings
     */
    @SuppressWarnings("unchecked")
    private void checkPrecedingSiblingsValue(DTreeItemDropListener dndListener, Set<EObject> itemSources, DTreeItem targetContainer, Collection<DTreeItem> expectedPrecedingSiblings) {
        // Step 1 : setting the local transfer selection to simulate dnd
        IStructuredSelection selection = new StructuredSelection(itemSources.toArray());
        LocalSelectionTransfer.getTransfer().setSelection(selection);

        // Step 2 : checking that the DnD validation provides the same result as
        // expected
        boolean isValidDrop = dndListener.validateDrop(targetContainer, 0, null);
        // Step 3 : if the drop operation is valid, check the preceding Siblings
        // value
        if (isValidDrop) {
            Field precedingSiblingsAttribute;
            try {
                precedingSiblingsAttribute = DTreeItemDropListener.class.getDeclaredField("precedingSiblings");
                precedingSiblingsAttribute.setAccessible(true);
                Collection<DTreeItem> actualPrecedingSiblings = (Collection<DTreeItem>) precedingSiblingsAttribute.get(this.dropListener);

                Assert.assertTrue("Incorrect precedingSiblings value : got " + actualPrecedingSiblings + " expected " + expectedPrecedingSiblings,
                        Iterables.elementsEqual(expectedPrecedingSiblings, actualPrecedingSiblings));
            } catch (SecurityException e) {
                Assert.assertFalse(e.getMessage(), true);
            } catch (NoSuchFieldException e) {
                Assert.assertFalse(e.getMessage(), true);
            } catch (IllegalArgumentException e) {
                Assert.assertFalse(e.getMessage(), true);
            } catch (IllegalAccessException e) {
                Assert.assertFalse(e.getMessage(), true);
            }

        }
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
     * Test class used to simulate the "CurrentLocation" value.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     */
    private class CustomDTreeItemDropListener extends DTreeItemDropListener {

        public CustomDTreeItemDropListener(Viewer viewer, TransactionalEditingDomain domain, ITreeCommandFactory treeCommandFactory, ModelAccessor accessor) {
            super(viewer, domain, treeCommandFactory, accessor);
        }

        public void setCurrentLocation(int i) {
            Field declaredField;
            try {
                declaredField = ViewerDropAdapter.class.getDeclaredField("currentLocation");
                declaredField.setAccessible(true);
                declaredField.set(this, i);
            } catch (SecurityException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        super.tearDown();
    }
}
