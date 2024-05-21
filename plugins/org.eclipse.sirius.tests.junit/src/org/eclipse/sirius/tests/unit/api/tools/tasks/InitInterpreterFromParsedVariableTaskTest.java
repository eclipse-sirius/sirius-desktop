/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.tools.tasks;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.business.api.helper.task.label.InitInterpreterFromParsedVariableTask;
import org.eclipse.sirius.business.api.helper.task.label.InitInterpreterFromParsedVariableTask2;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;

import junit.framework.TestCase;

/**
 * Test the parsing of mask variables and the variable duplication for integer variables names.
 * 
 * This is done by two tasks : InitInterpreterFromParsedVariableTask and InitInterpreterFromParsedVariableTask2.
 * 
 * This test checks the parsing and the int name variable duplication for different masks and values.
 * 
 * The duplication consists in creating an argX named variable for each X variable: 0 and arg0 for example. It is
 * required by some interpreters which cannot have int variables like Acceleo3. Acceleo2 was supporting it thanks to its
 * prefix $.
 * 
 * This test also checks that the dedicated task correctly cleans the interpreter.
 * 
 * @author mporhel
 * 
 */
public class InitInterpreterFromParsedVariableTaskTest extends TestCase {

    private IInterpreter interpreter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interpreter = CompoundInterpreter.createGenericInterpreter();
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Empty_Mask_And_New_Empty_Value() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "");

        doTestParsedVariableTask("", "", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Default_Mask_And_New_Empty_Value() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "");

        doTestParsedVariableTask("{0}", "", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Default_Mask() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "i");

        doTestParsedVariableTask("{0}", "i", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Simple_Mask_And_Empty_Message() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "");

        doTestParsedVariableTask("{0} {1} {2}", "", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Simple_Mask() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "i");
        expectedParsedVariables.put(1, "j");
        expectedParsedVariables.put(2, "k");

        doTestParsedVariableTask("{0} {1} {2}", "i j k", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Complex_Mask_And_Empty_Message() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "");

        doTestParsedVariableTask("{0} : {1} ( {2} )", "", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Complex_Mask() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "i");
        expectedParsedVariables.put(1, "j");
        expectedParsedVariables.put(2, "k");

        doTestParsedVariableTask("{0} : {1} ({2})", "i : j (k)", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Empty_Mask_And_New_Empty_Value2() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "");

        doTestParsedVariableTask2("", "", expectedParsedVariables);
    }
    
    /**
     * Test.
     */
    public void test_VariableDuplication_With_Default_Mask2() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "i");

        doTestParsedVariableTask2("{0}", "i", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Default_Mask_And_New_Empty_Value2() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "");

        doTestParsedVariableTask2("{0}", "", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Simple_Mask_And_Empty_Message_2() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "");

        doTestParsedVariableTask2("{0} {1} {2}", "", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Simple_Mask_2() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "i");
        expectedParsedVariables.put(1, "j");
        expectedParsedVariables.put(2, "k");

        doTestParsedVariableTask2("{0} {1} {2}", "i j k", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Complex_Mask_And_Empty_Message_2() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "");

        doTestParsedVariableTask2("{0} : {1} ( {2} )", "", expectedParsedVariables);
    }

    /**
     * Test.
     */
    public void test_VariableDuplication_With_Complex_Mask_2() {
        Map<Integer, String> expectedParsedVariables = new HashMap<>();
        expectedParsedVariables.put(0, "i");
        expectedParsedVariables.put(1, "j");
        expectedParsedVariables.put(2, "k");

        doTestParsedVariableTask2("{0} : {1} ({2})", "i : j (k)", expectedParsedVariables);
    }

    private void doTestParsedVariableTask(String mask, String message, Map<Integer, String> expectedVariableValues) {
        assertEquals("The interpreter should not have any variables.", 0, interpreter.getVariables().size());

        // Parse the mask, the message, and create the variables.
        new InitInterpreterFromParsedVariableTask(interpreter, mask, message).execute();

        doTestVariables(expectedVariableValues);

        // Call the task that should clean the interpreter
        new InitInterpreterFromParsedVariableTask(interpreter, mask, message, true).execute();
        doTestVariables(expectedVariableValues, true);
    }

    private void doTestParsedVariableTask2(String mask, String message, Map<Integer, String> expectedVariableValues) {
        assertEquals("The interpreter should not have any variables.", 0, interpreter.getVariables().size());

        // Parse the mask, the message, and create the variables.
        new InitInterpreterFromParsedVariableTask2(interpreter, mask, message).execute();

        doTestVariables(expectedVariableValues);

        // Call the task that should clean the interpreter
        new InitInterpreterFromParsedVariableTask2(interpreter, mask, message, true).execute();
        doTestVariables(expectedVariableValues, true);
    }

    private void doTestVariables(Map<Integer, String> expectedVariableValues) {
        doTestVariables(expectedVariableValues, false);
    }

    private void doTestVariables(Map<Integer, String> expectedVariableValues, boolean clean) {

        // Check the global variable number, we should have duplication for all
        // int named variables.
        if (clean) {
            assertEquals("No variable should be contains in the interpreter after direct edit.", 0, interpreter.getVariables().size());
        } else {
            assertEquals("The int named variables should be duplicated, example : 0 and arg0.", expectedVariableValues.size() * 2, interpreter.getVariables().size());

            for (Integer varIntName : expectedVariableValues.keySet()) {
                String intKey = varIntName.toString();
                String argKey = "arg" + intKey;

                assertTrue("The variable named " + intKey + " is not present in the variable list.", interpreter.getVariables().containsKey(intKey));
                assertTrue("The variable named " + argKey + " is not present in the variable list.", interpreter.getVariables().containsKey(argKey));

                // The test deals with String value.
                String intKeyValue = (String) interpreter.getVariable(intKey);
                String argKeyValue = (String) interpreter.getVariable(argKey);

                String expectedValue = expectedVariableValues.get(varIntName);
                assertEquals("The variable named " + intKey + " does not have the expected value.", intKeyValue, expectedValue);
                assertEquals("The variable named " + argKeyValue + " does not have the expected value.", argKeyValue, expectedValue);
            }
        }
    }

    @Override
    protected void tearDown() throws Exception {
        interpreter = null;
        super.tearDown();
    }
}
