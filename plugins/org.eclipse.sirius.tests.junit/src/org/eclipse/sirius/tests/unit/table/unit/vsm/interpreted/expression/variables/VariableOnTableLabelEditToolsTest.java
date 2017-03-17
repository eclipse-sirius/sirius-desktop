/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.table.unit.vsm.interpreted.expression.variables;

import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.command.TableCommandFactoryService;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;

/**
 * Tests table's direct edit tools.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class VariableOnTableLabelEditToolsTest extends TableTestCase {
    private static final String TEST_DIR = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/refresh/";

    private static final String SEMANTIC_MODEL_PATH = TEST_DIR + "tables.uml";

    private static final String SESSION_NAME = "tables.aird";

    private static final String SESSION_PATH = TEST_DIR + SESSION_NAME;

    private static final String MODELER_PATH = TEST_DIR + "tables.odesign";

    private DialectEditor tableEditor;

    private DTable tableRepresentation;

    private ITableCommandFactory tableCommandFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
        tableCommandFactory = TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        tableCommandFactory.setModelAccessor(session.getModelAccessor());
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
     * Checks that the given direct edit command does update the semantic
     * element.
     * 
     * @param directEditCommand
     *            the command to test.
     */
    protected void checkVariablesInterpretations(Command directEditCommand) {
        DialectUIManager.INSTANCE.openEditor(session, tableRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        Model model = (Model) semanticModel;

        Optional<Class> testClass = model.getOwnedElements().stream().map(element -> (org.eclipse.uml2.uml.Class) element).filter(element -> "Class3".equals(element.getName())).findFirst();
        assertTrue("Test setup is wrong.", testClass.isPresent());
        assertEquals("Test setup is wrong.", "Class3", testClass.get().getName());

        session.getTransactionalEditingDomain().getCommandStack().execute(directEditCommand);

        assertEquals("Tool was not correctly applied", "Class3working", testClass.get().getName());
    }

    /**
     * Tests that variables line and table are interpreted correctly in the
     * expression of a direct tool of a simple table. The expression is
     * <code>aql:if
     * table<>null then line.target.name+'working' 
     * else
     * line.target.name+'working' endif</code>
     */
    public void testDirectEditVariablesExpressionForSimpleTable() {
        tableRepresentation = (DTable) createRepresentation("SimpleTableVariables");
        // Direct edit command using line and table variables
        Command directEditCommand = tableCommandFactory.buildSetCellValueFromTool(tableRepresentation.getLines().get(0).getCells().get(0), "test");

        checkVariablesInterpretations(directEditCommand);
    }

    /**
     * Tests that variables line and table are interpreted correctly in the
     * expression of a direct tool of a cross table. The expression is
     * <code>aql:if
     * table<>null then line.target.name+'working' 
     * else
     * line.target.name+'working' endif</code>
     */
    public void testDirectEditVariablesExpressionForCrossTable() {
        tableRepresentation = (DTable) createRepresentation("CrossTableVariables");
        // Direct edit command using line and table variables
        Command directEditCommand = tableCommandFactory.buildSetCellValueFromTool(tableRepresentation.getLines().get(0).getCells().get(0), "test");

        checkVariablesInterpretations(directEditCommand);
    }
}
