/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;

import junit.framework.TestCase;

/**
 * Tests the use of variables with interpreter.
 * 
 * @author cbrun
 */
public class InterpreterVariablesTestCase extends TestCase {

    /**
     * 
     */
    private static final String ELEMENT_VARIABLE = "aql:element";

    /**
     * 
     */
    private static final String THE_RETURNED_VALUE_IS_NOT_THE_VARIABLE = "The returned value is not the variable";

    private IInterpreter interpreter = CompoundInterpreter.INSTANCE;

    private EClass claz;

    private EPackage pak;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        claz = EcoreFactory.eINSTANCE.createEClass();
        claz.setName("An EClass");
        pak = EcoreFactory.eINSTANCE.createEPackage();
    }

    /**
     * Tests the setting of a variable.
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testSettingVariable() throws Exception {
        interpreter.setVariable(IInterpreterSiriusVariables.SOURCE, claz);
        EObject result = interpreter.evaluateEObject(pak, "aql:source");
        assertSame(THE_RETURNED_VALUE_IS_NOT_THE_VARIABLE, claz, result);
    }

    /**
     * Test to set multiple variables.
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testSettingMultipleVariables() throws Exception {
        interpreter.setVariable(IInterpreterSiriusVariables.SOURCE, claz);
        interpreter.setVariable(IInterpreterSiriusVariables.TARGET, claz);
        boolean result = interpreter.evaluateBoolean(pak, "aql:source = target");
        assertTrue("The expresion using variable has not been correctly evaluated", result);
    }

    /**
     * Test to unset a variable.
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testSetUnsetVariable() throws Exception {
        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, claz);
        EObject result = interpreter.evaluateEObject(pak, ELEMENT_VARIABLE);
        assertSame(THE_RETURNED_VALUE_IS_NOT_THE_VARIABLE, claz, result);
        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, pak);
        result = interpreter.evaluateEObject(pak, ELEMENT_VARIABLE);
        assertSame(THE_RETURNED_VALUE_IS_NOT_THE_VARIABLE, pak, result);
        interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
        result = interpreter.evaluateEObject(pak, ELEMENT_VARIABLE);
        assertSame(THE_RETURNED_VALUE_IS_NOT_THE_VARIABLE, claz, result);
    }

    /**
     * Test to use a variable.
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testVariableUsage() throws Exception {
        interpreter.setVariable(IInterpreterSiriusVariables.SOURCE, claz);
        boolean result = interpreter.evaluateBoolean(claz, "aql:source = self");
        assertEquals("The expresion using variable has not been correctly evaluated", true, result);
    }

    /**
     * Tests to use a non declared variable.
     */
    public void testNonExistingVariable() {
        try {
            interpreter.evaluateBoolean(claz, "var:dummy");
            fail("No exception has been thrown when accessing a non existing variable");
        } catch (final EvaluationException e) {
            // Here, The test is OK.
        }
    }

}
