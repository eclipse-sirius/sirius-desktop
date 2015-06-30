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
package org.eclipse.sirius.tests.unit.tree.tools;

import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.AbstractToolDescriptionTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.api.command.TreeCommandFactoryService;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

import com.google.common.collect.Lists;

/**
 * Check selection in tree after tool execution.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SelectionInTreeAfterToolExecutionTest extends AbstractToolDescriptionTestCase {

    private static final String PATH = "/data/unit/tools/selection/";

    private static final String SEMANTIC_RESOURCE_NAME = "testVSMForSelection.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "testVSMForSelection.aird";

    private static final String MODELER_RESOURCE_NAME = "VSMForSelection.odesign";

    private DTree treeClasses;

    private DTreeEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME, MODELER_RESOURCE_NAME);
        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME), Lists.newArrayList(TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME), TEMPORARY_PROJECT_NAME
                + "/" + REPRESENTATIONS_RESOURCE_NAME);

        // Activate auto refresh
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        treeClasses = (DTree) getRepresentations("Tree", semanticModel).iterator().next();
        editor = (DTreeEditor) DialectUIManager.INSTANCE.openEditor(session, treeClasses, new NullProgressMonitor());

        TestsUtil.emptyEventsFromUIThread();
    }

    /**
     * Test selection after TreeItemCreation tool and check that</br>
     * <ul>
     * <li>nothing is selected by default</li>
     * <li>selection corresponds to ElementsToSelect expression query</li>
     * </ul>
     */
    public void testSelectionAfterTreeItemCreationTool() {
        DTreeItem dTreeItem = treeClasses.getOwnedTreeItems().get(0);

        final String tool_Name = "Tree_CreateItem";
        final AbstractToolDescription tool = getTool(tool_Name);

        changeSelectionExpression(tool, "[instance/]", false);
        TestsUtil.synchronizationWithUIThread();
        applyTreeItemCreationTool(tool_Name, dTreeItem, dTreeItem.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection
        // As the father node is collapsed, no sub node is created
        checkExpectedElementsInSelection(editor, null, 0, false);

        // Expand the father node
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(dTreeItem);
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        Command expandCarDTreeItemCmd = SetCommand.create(transactionalEditingDomain, dTreeItem, TreePackage.Literals.DTREE_ITEM__EXPANDED, true);
        commandStack.execute(expandCarDTreeItemCmd);
        refresh(new EObjectQuery(dTreeItem).getRepresentation().get(), true);
        TestsUtil.synchronizationWithUIThread();
        // check the selection
        // As the father node is now expanded, sub nodes are created but outside
        // the tool transaction
        // The selection should then be empty
        checkExpectedElementsInSelection(editor, null, 0, false);

        changeSelectionExpression(tool, "[instance/]", false);
        TestsUtil.synchronizationWithUIThread();
        applyTreeItemCreationTool(tool_Name, dTreeItem, dTreeItem.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection
        // As the father node is expanded, a sub node is created
        checkExpectedElementsInSelection(editor, null, 1, false);

        changeSelectionExpression(tool, "", false);
        TestsUtil.synchronizationWithUIThread();
        applyTreeItemCreationTool(tool_Name, dTreeItem.getContainer(), dTreeItem.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection is NOT empty by default
        checkExpectedElementsInSelection(editor, null, 1);

        changeSelectionExpression(tool, "[/]", false);
        TestsUtil.synchronizationWithUIThread();
        applyTreeItemCreationTool(tool_Name, dTreeItem.getContainer(), dTreeItem.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection is empty by default
        checkExpectedElementsInSelection(editor, null, 0);

        changeSelectionExpression(tool, "service:stdEmptyCollection", false);
        TestsUtil.synchronizationWithUIThread();
        applyTreeItemCreationTool(tool_Name, dTreeItem.getContainer(), dTreeItem.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection is empty by default
        checkExpectedElementsInSelection(editor, null, 0);

    }

    void applyTreeItemCreationTool(String toolName, final DTreeItemContainer lineContainer, final EObject semanticCurrentElement) {
        final TreeItemCreationTool tool = (TreeItemCreationTool) getTool(toolName);

        Command cmd = ((ITreeCommandFactory) getCommandFactory()).buildCreateLineCommandFromTool(lineContainer, semanticCurrentElement, tool);
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
    }

    private AbstractToolDescription getTool(String name) {
        TreeIterator<EObject> allTreeDescriptionContents = treeClasses.getDescription().eAllContents();
        while (allTreeDescriptionContents.hasNext()) {
            EObject eObject = allTreeDescriptionContents.next();
            if (eObject instanceof AbstractToolDescription && ((AbstractToolDescription) eObject).getName().equals(name)) {
                return (AbstractToolDescription) eObject;
            }
        }
        return null;
    }

    /**
     * Check that variables are recognized during expression run time
     * computation for CreateLineTool
     */
    public void testRunTimeVariableAfterCreateLineTool() {
        DTreeItem dTreeItem = treeClasses.getOwnedTreeItems().get(0);

        final String tool_Name = "Tree_CreateItem";
        final AbstractToolDescription tool = getTool(tool_Name);

        changeSelectionExpression(tool, "[container->including(element)->including(root)/]", false);
        applyTreeItemCreationTool(tool_Name, dTreeItem, dTreeItem.getTarget());

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", doesAnErrorOccurs());
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        ITreeCommandFactory commandFactory = TreeCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setModelAccessor(SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(session.getSemanticResources().iterator().next().getContents().get(0)));
        return commandFactory;
    }

}
