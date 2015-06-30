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
package org.eclipse.sirius.tests.unit.table.unit.tools;

import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.command.TableCommandFactoryService;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.AbstractToolDescriptionTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

import com.google.common.collect.Lists;

/**
 * Check selection in table after tool execution.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SelectionInTableAfterToolExecutionTest extends AbstractToolDescriptionTestCase {

    private static final String PATH = "/data/unit/tools/selection/";

    private static final String SEMANTIC_RESOURCE_NAME = "testVSMForSelection.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "testVSMForSelection.aird";

    private static final String MODELER_RESOURCE_NAME = "VSMForSelection.odesign";

    private DTable tableClasses;

    private DTableEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME, MODELER_RESOURCE_NAME);
        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME), Lists.newArrayList(TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME), TEMPORARY_PROJECT_NAME
                + "/" + REPRESENTATIONS_RESOURCE_NAME);

        tableClasses = (DTable) getRepresentations("Classes", semanticModel).iterator().next();
        editor = (DTableEditor) DialectUIManager.INSTANCE.openEditor(session, tableClasses, new NullProgressMonitor());

        TestsUtil.emptyEventsFromUIThread();
    }

    /**
     * Test selection after CreateLine tool and check that</br>
     * <ul>
     * <li>nothing is selected by default</li>
     * <li>selection corresponds to ElementsToSelect expression query</li>
     * </ul>
     */
    public void testSelectionAfterCreateLineTool() {
        DLine dLine = tableClasses.getLines().get(0);

        final String tool_Name = "Create Class";
        final AbstractToolDescription tool = getTool(tool_Name);

        changeSelectionExpression(tool, "[instance/]", false);
        TestsUtil.synchronizationWithUIThread();
        applyCreateLineTool(tool_Name, dLine.getContainer(), dLine.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection
        checkExpectedElementsInSelection(editor, Lists.newArrayList("NewEClass"), 1, true);

        changeSelectionExpression(tool, "", false);
        TestsUtil.synchronizationWithUIThread();
        applyCreateLineTool(tool_Name, dLine.getContainer(), dLine.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection is the newly created element by default
        checkExpectedElementsInSelection(editor, Lists.newArrayList("NewEClass"), 1, true);

        changeSelectionExpression(tool, "[/]", false);
        TestsUtil.synchronizationWithUIThread();
        applyCreateLineTool(tool_Name, dLine.getContainer(), dLine.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection is empty
        checkExpectedElementsInSelection(editor, null, 0);

        changeSelectionExpression(tool, "service:stdEmptyCollection", false);
        TestsUtil.synchronizationWithUIThread();
        applyCreateLineTool(tool_Name, dLine.getContainer(), dLine.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection is empty
        checkExpectedElementsInSelection(editor, null, 0);
    }

    void applyCreateLineTool(String toolName, final LineContainer lineContainer, final EObject semanticCurrentElement) {
        final CreateTool tool = (CreateTool) getTool(toolName);

        Command cmd = ((ITableCommandFactory) getCommandFactory()).buildCreateLineCommandFromTool(lineContainer, semanticCurrentElement, tool);
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
    }

    private AbstractToolDescription getTool(String name) {
        TreeIterator<EObject> allTableDescriptionContents = tableClasses.getDescription().eAllContents();
        while (allTableDescriptionContents.hasNext()) {
            EObject eObject = allTableDescriptionContents.next();
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
        DLine dLine = tableClasses.getLines().get(0);

        final String tool_Name = "Create Class";
        final AbstractToolDescription tool = getTool(tool_Name);

        changeSelectionExpression(tool, "[container->including(element)->including(root)/]", false);
        applyCreateLineTool(tool_Name, dLine.getContainer(), dLine.getTarget());

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", doesAnErrorOccurs());
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        ITableCommandFactory commandFactory = TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setModelAccessor(SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(session.getSemanticResources().iterator().next().getContents().get(0)));
        return commandFactory;
    }

}
