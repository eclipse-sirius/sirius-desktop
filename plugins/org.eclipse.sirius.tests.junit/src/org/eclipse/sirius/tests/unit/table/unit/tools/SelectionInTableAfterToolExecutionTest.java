/*******************************************************************************
 * Copyright (c) 2015, 2022 Obeo.
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
package org.eclipse.sirius.tests.unit.table.unit.tools;

import java.util.Arrays;
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
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

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
        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME), Arrays.asList(TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME),
                TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);

        tableClasses = (DTable) getRepresentations("Classes", semanticModel).iterator().next();
    }

    /**
     * Test selection after CreateLine tool and check that</br>
     * <ul>
     * <li>nothing is selected by default</li>
     * <li>selection corresponds to ElementsToSelect expression query</li>
     * </ul>
     */
    public void testSelectionAfterCreateLineTool() {
        editor = (DTableEditor) DialectUIManager.INSTANCE.openEditor(session, tableClasses, new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();

        DLine dLine = tableClasses.getLines().get(0);

        final String tool_Name = "Create Class";
        final AbstractToolDescription tool = getTool(tool_Name);

        changeSelectionExpression(tool, "[instance/]", false);
        TestsUtil.synchronizationWithUIThread();
        applyCreateLineTool(tool_Name, dLine, dLine.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection
        checkExpectedElementsInSelection(editor, Arrays.asList("NewEClass"), 1, true);

        changeSelectionExpression(tool, "", false);
        TestsUtil.synchronizationWithUIThread();
        applyCreateLineTool(tool_Name, dLine, dLine.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection is the newly created element by default
        checkExpectedElementsInSelection(editor, Arrays.asList("NewEClass"), 1, true);

        changeSelectionExpression(tool, "[/]", false);
        TestsUtil.synchronizationWithUIThread();
        applyCreateLineTool(tool_Name, dLine, dLine.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection is empty
        checkExpectedElementsInSelection(editor, null, 0);

        changeSelectionExpression(tool, "service:stdEmptyCollection", false);
        TestsUtil.synchronizationWithUIThread();
        applyCreateLineTool(tool_Name, dLine, dLine.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection is empty
        checkExpectedElementsInSelection(editor, null, 0);

        changeSelectionExpression(tool, "var:element", false);
        TestsUtil.synchronizationWithUIThread();
        applyCreateLineTool(tool_Name, dLine, dLine.getTarget());
        TestsUtil.synchronizationWithUIThread();
        // check the selection is empty, because the system property
        // "org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener" has its default value, and
        // the existing DLine corresponding to variable element (ie class A) is not in the list of created
        // elements during the tool execution
        checkExpectedElementsInSelection(editor, null, 0);
    }

    /**
     * Test selection after CreateLine tool with the system property
     * "org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener" set to false and check that the
     * behavior is not the same when the "elements to select" expression does not contain the created elements. The
     * scenario is the same than the last of {@link #testSelectionAfterCreateLineTool()}, but with a different expected
     * result.
     */
    public void testSelectionAfterCreateLineTool_withConstraintPropertySystemSetToFalse() {
        System.setProperty("org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener", "false"); //$NON-NLS-1$ //$NON-NLS-2$
        try {
            editor = (DTableEditor) DialectUIManager.INSTANCE.openEditor(session, tableClasses, new NullProgressMonitor());
            TestsUtil.emptyEventsFromUIThread();

            DLine dLine = tableClasses.getLines().get(0);

            final String tool_Name = "Create Class";
            final AbstractToolDescription tool = getTool(tool_Name);

            changeSelectionExpression(tool, "var:element", false);
            TestsUtil.synchronizationWithUIThread();
            applyCreateLineTool(tool_Name, dLine, dLine.getTarget());
            TestsUtil.synchronizationWithUIThread();
            // check the selection is the existing DLine corresponding to variable element (ie class A). New
            // behavior according to system property set to false and used in SelectDRepresentationElementsListener.
            checkExpectedElementsInSelection(editor, Collections.singletonList(dLine.getName()), 1);
        } finally {
            System.setProperty("org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener", "true"); //$NON-NLS-1$ //$NON-NLS-2$
        }

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
     * Check that variables are recognized during expression run time computation for CreateLineTool
     */
    public void testRunTimeVariableAfterCreateLineTool() {
        editor = (DTableEditor) DialectUIManager.INSTANCE.openEditor(session, tableClasses, new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();

        DLine dLine = tableClasses.getLines().get(0);

        final String tool_Name = "Create Class";
        final AbstractToolDescription tool = getTool(tool_Name);

        changeSelectionExpression(tool, "[container->including(element)->including(root)/]", false);
        applyCreateLineTool(tool_Name, dLine, dLine.getTarget());

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", doesAnErrorOccurs());
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        ITableCommandFactory commandFactory = TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setModelAccessor(SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(session.getSemanticResources().iterator().next().getContents().get(0)));
        return commandFactory;
    }

}
