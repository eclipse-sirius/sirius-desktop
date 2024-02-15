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
package org.eclipse.sirius.tests.unit.common.interpreter.service;

import java.util.Collections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.internal.interpreter.ServiceInterpreter;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.ecore.design.service.EcoreSamplePlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;

/**
 * A Test case for the {@link ServiceInterpreter}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ServiceInterpreterTests extends SiriusTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interpreter = new ServiceInterpreter();
    }

    public void testServiceInterpreterEvaluationWithNullParameters() {
        try {
            Object result = interpreter.evaluate(null, null);
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testServiceInterpreterEvaluationWithNullTargetAndEmptyExpression() {
        try {
            Object result = interpreter.evaluate(null, "");
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testServiceInterpreterEvaluationWithNullExpression() {
        try {
            EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
            ePackage.setName("p1");
            Object result = interpreter.evaluate(ePackage, null);
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testServiceInterpreterEvaluationWithEmptyExpression() {
        try {
            // Setup
            EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
            ePackage.setName("p1");
            // Test
            Object result = interpreter.evaluate(ePackage, "");
            // Check
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    private void checkServiceInterpreterEvaluationWithSpecificServiceExpression(boolean endsWithSpace) {
        try {
            // Setup
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            interpreter.setProperty(IInterpreter.FILES, Collections.singletonList("/" + EcoreSamplePlugin.PLUGIN_ID + "/description/ecore.odesign"));
            interpreter.addImport("org.eclipse.sirius.ecore.design.service.EcoreService");
            // Test
            String expression = ServiceInterpreter.PREFIX + "render";
            if (endsWithSpace) {
                expression += " ";
            }
            Object result = interpreter.evaluate(eAttribute, expression);
            // Check
            assertEquals("The evaluation result must be the attribute name", eAttribute.getName(), result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown " + e.getMessage());
        }
    }

    public void testServiceInterpreterEvaluationWithSpecificServiceExpression() {
        checkServiceInterpreterEvaluationWithSpecificServiceExpression(false);
    }

    public void testServiceInterpreterEvaluationWithSpecificServiceExpressionWithSpaceAtEnd() {
        checkServiceInterpreterEvaluationWithSpecificServiceExpression(true);
    }

    public void testServiceInterpreterEvaluationWithSpecificServiceExpressionOnSelf() {
        try {
            // Setup
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            interpreter.setProperty(IInterpreter.FILES, Collections.singletonList("/" + EcoreSamplePlugin.PLUGIN_ID + "/description/ecore.odesign"));
            interpreter.addImport("org.eclipse.sirius.ecore.design.service.EcoreService");
            // Test
            Object result = interpreter.evaluate(eAttribute, ServiceInterpreter.PREFIX + "self.render");
            // Check
            assertEquals("The evaluation result must be the attribute name", eAttribute.getName(), result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testServiceInterpreterEvaluationWithSpecificServiceExpressionOnVariable() {
        try {
            // Setup
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            interpreter.setProperty(IInterpreter.FILES, Collections.singletonList("/" + EcoreSamplePlugin.PLUGIN_ID + "/description/ecore.odesign"));
            interpreter.addImport("org.eclipse.sirius.ecore.design.service.EcoreService");
            interpreter.setVariable("myVar", eAttribute);

            // Test
            Object result = interpreter.evaluate(eAttribute, ServiceInterpreter.PREFIX + "myVar.render");
            // Check
            assertEquals("The evaluation result must be the attribute name", eAttribute.getName(), result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testServiceInterpreterEvaluationAfterVariableModifiations() {
        try {
            // Setup
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            interpreter.setProperty(IInterpreter.FILES, Collections.singletonList("/" + EcoreSamplePlugin.PLUGIN_ID + "/description/ecore.odesign"));
            interpreter.addImport("org.eclipse.sirius.ecore.design.service.EcoreService");
            interpreter.setVariable("myVar", eAttribute);

            // Test
            Object result = interpreter.evaluate(eAttribute, ServiceInterpreter.PREFIX + "myVar.render");
            // Check
            assertEquals("The evaluation result must be the attribute name", eAttribute.getName(), result);

            // Set a new value
            EAttribute eAttribute2 = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute2.setName("a2");
            interpreter.setVariable("myVar", eAttribute2);

            // Test
            result = interpreter.evaluate(eAttribute, ServiceInterpreter.PREFIX + "myVar.render");
            // Check
            assertEquals("The evaluation result must be the attribute name", eAttribute2.getName(), result);

            // Unset the variable, then the next expected evaluation result is
            // the previous value.
            interpreter.unSetVariable("myVar");
            // Test
            result = interpreter.evaluate(eAttribute, ServiceInterpreter.PREFIX + "myVar.render");
            // Check
            assertEquals("The evaluation result must be the attribute name", eAttribute.getName(), result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testServiceInterpreterEvaluationWithSpecificServiceExpressionHavingOneParameter() {
        checkServiceInterpreterEvaluationWithSpecificServiceExpressionHavingOneParameter(false);
    }

    public void checkServiceInterpreterEvaluationWithSpecificServiceExpressionHavingOneParameter(boolean endsWithSpace) {
        try {
            // Setup
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            interpreter.setProperty(IInterpreter.FILES, Collections.singletonList("/" + EcoreSamplePlugin.PLUGIN_ID + "/description/ecore.odesign"));
            interpreter.addImport("org.eclipse.sirius.ecore.design.service.EcoreService");
            String variableName = "test";
            interpreter.setVariable(variableName, variableName);
            // Test
            String expression = ServiceInterpreter.PREFIX + "performEdit(" + variableName + ")";
            if (endsWithSpace) {
                expression = expression + " ";
            }
            Object result = interpreter.evaluate(eAttribute, expression);
            // Check
            assertEquals("The evaluation result must be the attribute itself", eAttribute, result);
            assertEquals("The evaluation should have update the attribute name", variableName, eAttribute.getName());
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown " + e.getMessage());
        }
    }

    public void testServiceInterpreterEvaluationWithSpecificServiceExpressionHavingOneParameterWithSpaceAtEnd() {
        checkServiceInterpreterEvaluationWithSpecificServiceExpressionHavingOneParameter(true);
    }

    public void testServiceInterpreterEvaluationWithSpecificServiceExpressionHavingOneParameterAndSpecificReceiverEObject() {
        try {
            // Setup
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            interpreter.setProperty(IInterpreter.FILES, Collections.singletonList("/" + EcoreSamplePlugin.PLUGIN_ID + "/description/ecore.odesign"));
            interpreter.addImport("org.eclipse.sirius.ecore.design.service.EcoreService");
            String receiverVariableName = "receiverTest";
            interpreter.setVariable(receiverVariableName, eAttribute);
            String variableName = "testVariable";
            String newName = "test";
            interpreter.setVariable(variableName, newName);
            // Test
            Object result = interpreter.evaluate(eAttribute, ServiceInterpreter.PREFIX + receiverVariableName + ".performEdit(" + variableName + ")");
            // Check
            assertEquals("The evaluation result must be the attribute itself", eAttribute, result);
            assertEquals("The evaluation should have update the attribute name", newName, eAttribute.getName());
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testServiceInterpreterEvaluationWithSpecificServiceExpressionHavingOneParameterAndSpecificReceiverNotEObject() {
        try {
            // Setup
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            interpreter.setProperty(IInterpreter.FILES, Collections.singletonList("/" + EcoreSamplePlugin.PLUGIN_ID + "/description/ecore.odesign"));
            interpreter.addImport("org.eclipse.sirius.ecore.design.service.EcoreService");
            String testVariableName = "test";
            interpreter.setVariable(testVariableName, "test");
            // Test
            String newName = "test";
            interpreter.evaluate(eAttribute, ServiceInterpreter.PREFIX + testVariableName + ".performEdit(" + newName + ")");
            fail("EvaluationException should be failed because of a not EObject receiver");
        } catch (EvaluationException e) {
        }
    }

    public void testServiceInterpreterEvaluationWithIncorretServiceExpression() {
        try {
            // Setup
            DEdge dEdge = DiagramFactory.eINSTANCE.createDEdge();
            EdgeStyle edgeStyle = DiagramFactory.eINSTANCE.createEdgeStyle();
            dEdge.setOwnedStyle(edgeStyle);
            // Test
            interpreter.evaluate(dEdge, ServiceInterpreter.PREFIX + "not_a_service");
            // Check
            fail("EvaluationException should be thrown");
        } catch (EvaluationException e) {
        }
    }

    /**
     * Test that when an invalid path of javaExtension is used for mtl file, this path is ignored (not considered as
     * import). The invalid path is a path with ".mtl". It does not respect the documentation
     * (http://www.eclipse.org/sirius/doc/specifier/general/Writing_Queries.html #acceleo).
     */
    public void testAddImportWithInvalidPathContainintPoint() {
        // Initialize error/warning log and uncaught exception handlers
        platformProblemsListener.initLoggers();
        boolean oldIsWarningCatchActive = platformProblemsListener.isWarningCatchActive();
        platformProblemsListener.setWarningCatchActive(true);
        try {
            // Add an invalid mlt path containing a point
            interpreter.addImport("org::eclipse::sirius::test::a3Querries.mtl");
            // Test
            checkServiceInterpreterEvaluationWithSpecificServiceExpression(false);
            assertFalse("Problem concerning Java extension path: " + platformProblemsListener.getWarningLoggersMessage(), platformProblemsListener.doesAWarningOccurs());
        } finally {
            platformProblemsListener.setWarningCatchActive(oldIsWarningCatchActive);
            platformProblemsListener.disposeLoggers();
        }
    }

    /**
     * Test that when a path of javaExtension corresonds to an mtl file, this path is ignored, not considered as import
     * by interpreter.
     */
    public void testAddImportWithPathOfMtlFile() {
        // Initialize error/warning log and uncaught exception handlers
        platformProblemsListener.initLoggers();
        boolean oldIsWarningCatchActive = platformProblemsListener.isWarningCatchActive();
        platformProblemsListener.setWarningCatchActive(true);
        try {
            // Add a path corresponding to an mtl file
            interpreter.addImport("org::eclipse::sirius::test::a3Querries");
            // Test
            checkServiceInterpreterEvaluationWithSpecificServiceExpression(false);
            assertFalse("Problem concerning Java extension path: " + platformProblemsListener.getWarningLoggersMessage(), platformProblemsListener.doesAWarningOccurs());
        } finally {
            platformProblemsListener.setWarningCatchActive(oldIsWarningCatchActive);
            platformProblemsListener.disposeLoggers();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        interpreter = null;
        super.tearDown();
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
