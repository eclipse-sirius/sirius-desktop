/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.table.unit.vsm.interpreted.expression.variables;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

/**
 * 
 * Test Ensuring that there is no error when creating columns for which the
 * corresponding tool uses variables (such as "root", "element" and
 * "container").
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class VariableOnTableCreationToolsTest extends TableTestCase {
    private static final String TEST_DIR = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/bugzilla-424662/";

    private static final String SEMANTIC_MODEL_PATH = TEST_DIR + "424662.ecore";

    private static final String SESSION_NAME = "424662.aird";

    private static final String SESSION_PATH = TEST_DIR + SESSION_NAME;

    private static final String MODELER_PATH = TEST_DIR + "424662.odesign";

    private DialectEditor tableEditor;

    private DTable tableRepresentation;

    private EPackage subPackage1;

    private CrossTableDescription tableDescription;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);

        getCommandFactory().setModelAccessor(session.getModelAccessor());
        tableDescription = (CrossTableDescription) session.getSelectedViewpoints(true).iterator().next().getOwnedRepresentations().get(0);
        tableRepresentation = (DTable) new DViewQuery(session.getOwnedViews().iterator().next()).getLoadedRepresentations().iterator().next();
        subPackage1 = ((EPackage) session.getSemanticResources().iterator().next().getContents().get(0)).getESubpackages().iterator().next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

    /**
     * Ensures that there is no error when creating cross columns for which the
     * corresponding tool uses variables (such as "root", "element" and
     * "container").
     */
    public void testCrossColumnCreationWithToolUsingVariables() {
        CreateTool createCrossColumnTool = tableDescription.getCreateColumn().get(0);
        Command createCrossColumnCommand = getCommandFactory().buildCreateColumnCommandFromTool(tableRepresentation, subPackage1, createCrossColumnTool);

        doTestColumnCreationWithToolUsingVariables(createCrossColumnCommand, subPackage1, "EClass of p1");
    }

    /**
     * Ensures that there is no error when creating columns for which the
     * corresponding tool uses variables (such as "root", "element" and
     * "container").
     */
    public void testColumnCreationWithToolUsingVariables() {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                subPackage1.getEClassifiers().add(EcoreFactory.eINSTANCE.createEClass());
            }
        });
        CreateTool createColumnTool = tableDescription.getOwnedColumnMappings().get(0).getCreate().get(0);
        Command createColumnCommand = getCommandFactory().buildCreateColumnCommandFromTool(tableRepresentation, createColumnTool);

        doTestColumnCreationWithToolUsingVariables(createColumnCommand, (EPackage) subPackage1.eContainer(), "EClass2 of p1");
    }

    /**
     * Ensures that, when executing the given command (which is a create column
     * command) has the expected effect on subPackage1's label.
     * 
     * @param createColumnCommand
     *            the command to execute
     * @param expectedTarget
     * @param expectedLabel
     *            the expected label for subPackage1
     */
    public void doTestColumnCreationWithToolUsingVariables(Command createColumnCommand, EPackage expectedTarget, String expectedLabel) {
        // Step 1: open the table
        DialectUIManager.INSTANCE.openEditor(session, tableRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        int previousNumberOfClassifiers = expectedTarget.getEClassifiers().size();

        // Step 2: apply the tool
        session.getTransactionalEditingDomain().getCommandStack().execute(createColumnCommand);

        // Step 3: tool should be applied
        assertEquals("Tool was not correctly applied", previousNumberOfClassifiers + 1, expectedTarget.getEClassifiers().size());
        // => created element should have the expected label
        assertEquals("Tool was not correctly applied", expectedLabel, expectedTarget.getEClassifiers().get(0).getName());
    }
}
