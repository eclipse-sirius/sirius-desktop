/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.AcceleoMTLInterpreter;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.AcceleoMTLInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.sample.interactions.InteractionsFactory;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

import com.google.common.collect.Iterables;

import junit.framework.TestCase;

/**
 * Tests for the {@link AcceleoMTLInterpreter} utility class.
 * 
 * @author mporhel
 * 
 */
public class AcceleoMTLInterpreterTests extends TestCase {
    private static final String IMPORT = "org::eclipse::sirius::tests::unit::common::interpreter::acceleo::mtl::AcceleoMtlInterpreterTestModule";

    /**
     * Tests that it is possible to create an empty range.
     */
    public void testAcceleoMTLProvider() {
        IInterpreterProvider provider = new AcceleoMTLInterpreterProvider();

        assertFalse("The expression is not a mtl expression.", provider.provides("string"));
        assertFalse("The expression is an AQL expression, not a mtl expression.", provider.provides("aql:'incomplete' + '[' + 'expression'+ ']'"));
        assertTrue("The expression looks like a mtl expression.", provider.provides("[expression/]"));

        assertTrue("The expression looks like a mtl expression.", provider.provides("['an' + expression/]"));
    }

    /**
     * Tests that it is possible to create an empty range.
     */
    public void _testAcceleoMTLProviderWithSurroundedExpression() {
        IInterpreterProvider provider = new AcceleoMTLInterpreterProvider();
        assertTrue("The expression looks like a mtl expression.", provider.provides("[expression /] "));

        assertTrue("The expression looks like a mtl expression.", provider.provides("an[expression/]"));
        assertTrue("The expression looks like a mtl expression.", provider.provides("an [expression /] "));
    }

    /**
     * Tests prefix.
     */
    public void testAcceleoMTLPrefix() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();
        assertEquals("[", interpreter.getPrefix());
    }

    /**
     * Tests variable prefix.
     */
    public void testAcceleoMTLVariablePrefix() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();
        assertNull(interpreter.getVariablePrefix());
    }

    /**
     * Tests that it is possible to create an Acceleo 3 interpreter.
     */
    public void testAcceleoMTLInterpreterCreation() {
        IInterpreterProvider provider = new AcceleoMTLInterpreterProvider();
        IInterpreter createdInterpreter = provider.createInterpreter();

        assertTrue(createdInterpreter instanceof AcceleoMTLInterpreter);

        createdInterpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a String.
     */
    public void testAcceleoMtlStringEvaluation() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        evaluateString(interpreter, eClass, "[name/]", eClass.getName());
        evaluateString(interpreter, eClass, "['t' + self.name + ' t'/]", "t" + eClass.getName() + " t");

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a String.
     */
    public void _testAcceleoMtlStringEvaluationWithSurroundedExpression() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        evaluateString(interpreter, eClass, "t[self.name/] t", "t" + eClass.getName() + " t");

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a String on a single element of a
     * hierarchy.
     */
    public void testAcceleoMtlHierarchyStringEvaluation() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p1");

        evaluateString(interpreter, p, "[name/]", p.getName());

        EPackage p2 = EcoreFactory.eINSTANCE.createEPackage();
        p2.setName("p2");

        evaluateString(interpreter, p2, "[name/]", p2.getName());
        evaluateString(interpreter, p, "[name/]", p.getName());

        p.getESubpackages().add(p2);

        // Acceleo is used to generate/evaluate the dynamic template only on the
        // current element.
        // It should not iterate over the input contents.
        evaluateString(interpreter, p2, "[name/]", p2.getName());
        evaluateString(interpreter, p, "[name/]", p.getName());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a boolean.
     */
    public void testAcceleoMtlBooleanEvaluation() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();

        evaluateBoolean(interpreter, eClass, "[abstract/]", eClass.isAbstract());

        eClass.setAbstract(true);

        evaluateBoolean(interpreter, eClass, "[abstract/]", eClass.isAbstract());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate an Integer.
     */
    public void testAcceleoMtlIntegerEvaluation() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        EReference ref = EcoreFactory.eINSTANCE.createEReference();

        int result = 0;
        try {
            result = interpreter.evaluateInteger(eClass, "[eReferences->size()/]");
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("Evaluation result do not match", eClass.getEReferences().size(), result);

        eClass.getEStructuralFeatures().add(ref);
        try {
            result = interpreter.evaluateInteger(eClass, "[eReferences->size()/]");
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("Evaluation result do not match", eClass.getEStructuralFeatures().size(), result);

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate an EObject.
     */
    public void testAcceleoMtlEObjectEvaluation() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p1");

        EPackage p2 = EcoreFactory.eINSTANCE.createEPackage();
        p2.setName("p2");

        p.getESubpackages().add(p2);

        evaluateEObject(interpreter, p, "[self/]", p);
        evaluateEObject(interpreter, p2, "[self.eContainer()/]", p);

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate an Object.
     */
    public void testAcceleoMtlObjectEvaluation() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p1");

        EPackage p2 = EcoreFactory.eINSTANCE.createEPackage();
        p2.setName("p2");

        p.getESubpackages().add(p2);

        evaluateObject(interpreter, p2, "[self.eContainer()/]", p);

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a Collection.
     */
    public void testAcceleoMtlCollectionEvaluation() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p1");

        EPackage p2 = EcoreFactory.eINSTANCE.createEPackage();
        p2.setName("p2");

        p.getESubpackages().add(p2);

        evaluateCollection(interpreter, p, "[self.eSubpackages/]", p.getESubpackages());
        evaluateCollection(interpreter, p, "[self.eAllContents()/]", p.getESubpackages());
        evaluateCollection(interpreter, p, "[self/]", Collections.singletonList(p));

        interpreter.dispose();
    }

    private void evaluateString(IInterpreter interpreter, EObject eObj, String expression, String expectedResult) {
        String result = "";
        try {
            result = interpreter.evaluateString(eObj, expression);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("Evaluation result do not match", expectedResult, result);
    }

    private void evaluateBoolean(IInterpreter interpreter, EObject eObj, String expression, boolean expectedResult) {
        boolean result = false;
        try {
            result = interpreter.evaluateBoolean(eObj, expression);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("Evaluation result do not match", expectedResult, result);
    }

    private void evaluateObject(IInterpreter interpreter, EObject eObj, String expression, Object expectedResult) {
        Object result = null;
        try {
            result = interpreter.evaluate(eObj, expression);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("Evaluation result do not match", expectedResult, result);
    }

    private void evaluateEObject(IInterpreter interpreter, EObject eObj, String expression, EObject expectedResult) {
        EObject result = null;
        try {
            result = interpreter.evaluateEObject(eObj, expression);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("Evaluation result do not match", expectedResult, result);
    }

    private void evaluateCollection(IInterpreter interpreter, EObject eObj, String expression, Collection<? extends EObject> expectedResult) {
        Collection<EObject> result = null;
        try {
            result = interpreter.evaluateCollection(eObj, expression);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("Evaluation result do not match", expectedResult.size(), result.size());
        assertTrue("Evaluation result do not match", Iterables.elementsEqual(expectedResult, result));
    }

    /**
     * Tests that it is possible to have access to variables.
     */
    public void testAcceleoMtlVariableAccess() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        String varName = "maClass";
        interpreter.setVariable(varName, eClass);

        evaluateString(interpreter, eClass, "[" + varName + ".name/]", eClass.getName());
        evaluateBoolean(interpreter, eClass, "[" + varName + ".abstract/]", eClass.isAbstract());
        evaluateCollection(interpreter, eClass, "[" + varName + ".eContents()/]", Collections.<EObject> emptyList());
        evaluateEObject(interpreter, eClass, "[" + varName + ".eContainer()/]", null);
        evaluateObject(interpreter, eClass, "[" + varName + ".eContainer()/]", null);

        // Check evaluation shortcut
        evaluateEObject(interpreter, eClass, "[" + varName + "/]", eClass);
        evaluateObject(interpreter, eClass, "[" + varName + "/]", eClass);

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to have access to self.
     */
    public void testAcceleoMtlSelfAccess() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        EPackage pkg = EcoreFactory.eINSTANCE.createEPackage();
        pkg.getEClassifiers().add(eClass);

        String varName = "maClass";
        interpreter.setVariable(varName, eClass);

        evaluateString(interpreter, eClass, "[self.name/]", eClass.getName());
        evaluateBoolean(interpreter, eClass, "[self.abstract/]", eClass.isAbstract());
        evaluateCollection(interpreter, eClass, "self.eContents()/]", Collections.<EObject> emptyList());
        evaluateEObject(interpreter, eClass, "[self.eContainer()/]", pkg);
        evaluateObject(interpreter, eClass, "[self.eContainer()/]", pkg);

        // Check evaluation shortcut
        evaluateEObject(interpreter, eClass, "[self/]", eClass);
        evaluateObject(interpreter, eClass, "[self/]", eClass);

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to have access to thisEObject which is the
     * receiver argument of the generated queries.
     */
    public void testAcceleoMtlThisEObjectAccess() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        EPackage pkg = EcoreFactory.eINSTANCE.createEPackage();
        pkg.getEClassifiers().add(eClass);

        String varName = "maClass";
        interpreter.setVariable(varName, eClass);

        evaluateString(interpreter, eClass, "[thisEObject.name/]", eClass.getName());
        evaluateBoolean(interpreter, eClass, "[thisEObject.abstract/]", eClass.isAbstract());
        evaluateCollection(interpreter, eClass, "thisEObject.eContents()/]", Collections.<EObject> emptyList());
        evaluateEObject(interpreter, eClass, "[thisEObject.eContainer()/]", pkg);
        evaluateObject(interpreter, eClass, "[thisEObject.eContainer()/]", pkg);

        // Check evaluation shortcut
        evaluateEObject(interpreter, eClass, "[thisEObject/]", eClass);
        evaluateObject(interpreter, eClass, "[thisEObject/]", eClass);

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to call an imported script.
     */
    public void testAcceleoMtlImportedScriptAccess() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(IMPORT);

        evaluateString(interpreter, eClass, "[getName()/]", eClass.getName());
        evaluateString(interpreter, eClass, "[self.getNameQuery()/]", eClass.getName());
        // evaluateString(interpreter, eClass, "[self.getNameQuery()/] c",
        // eClass.getName() + " c");
        evaluateString(interpreter, eClass, "[getImportedName()/]", eClass.getName());
        // evaluateString(interpreter, eClass, "c [getImportedQueryName()/] c",
        // "c " + eClass.getName() + " c");

        evaluateBoolean(interpreter, eClass, "[self.isAbstractQuery()/]", eClass.isAbstract());
        evaluateCollection(interpreter, eClass, "[self.eContentsQuery()/]", eClass.eContents());
        evaluateEObject(interpreter, eClass, "[self.selfQuery()/]", eClass);
        evaluateObject(interpreter, eClass, "[self.selfImportedQuery()/]", eClass);

        interpreter.dispose();
    }

    /**
     * Tests that result change.
     */
    public void testNoWrongCache() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(IMPORT);

        evaluateString(interpreter, eClass, "[self.getNameQuery()/]", eClass.getName());

        eClass.setName("testName2");
        evaluateString(interpreter, eClass, "[self.getNameQuery()/]", eClass.getName());

        eClass.setName("testName3");
        evaluateString(interpreter, eClass, "[self.getNameQuery()/]", eClass.getName());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a String.
     */
    public void testAcceleoMtlAddImports() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(IMPORT);
        interpreter.addImport("org.eclipse.sirius.common.test");
        interpreter.addImport("org::eclipse::sirius::common::test");

        assertEquals(1, interpreter.getImports().size());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a String.
     */
    public void testAcceleoMtlRemoveImports() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(IMPORT);
        interpreter.addImport("org.eclipse.sirius.common.test");
        interpreter.addImport("org::eclipse::sirius::common::test");

        assertEquals(1, interpreter.getImports().size());

        interpreter.removeImport(IMPORT);

        assertEquals(0, interpreter.getImports().size());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a String.
     */
    public void testAcceleoMtlClearImports() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(IMPORT);
        interpreter.addImport("org.eclipse.sirius.common.test");
        interpreter.addImport("org::eclipse::sirius::common::test");

        assertEquals(1, interpreter.getImports().size());

        interpreter.clearImports();

        assertEquals(0, interpreter.getImports().size());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a String.
     */
    public void testAcceleoMtlNoFilesProperties() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);

        interpreter.addImport(IMPORT);
        interpreter.addImport("org.eclipse.sirius.common.test");
        interpreter.addImport("org::eclipse::sirius::common::test");

        assertEquals(0, interpreter.getImports().size());

        // Register vsm
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(IMPORT);
        interpreter.addImport("org.eclipse.sirius.common.test");
        interpreter.addImport("org::eclipse::sirius::common::test");

        assertEquals(1, interpreter.getImports().size());

        interpreter.clearImports();

        assertEquals(0, interpreter.getImports().size());

        // Unregister vsm
        interpreter.setProperty(IInterpreter.FILES, null);
        interpreter.addImport(IMPORT);
        interpreter.addImport("org.eclipse.sirius.common.test");
        interpreter.addImport("org::eclipse::sirius::common::test");

        assertEquals(0, interpreter.getImports().size());

        interpreter.dispose();
    }

    /**
     * Tests arguments and variables order is valid.
     */
    public void testAcceleoMtlVariablesValues() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p1");

        EPackage p2 = EcoreFactory.eINSTANCE.createEPackage();
        p2.setName("p2");

        p.getESubpackages().add(p2);

        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("c1");
        p.getEClassifiers().add(c);

        EClass c2 = EcoreFactory.eINSTANCE.createEClass();
        c2.setName("c2");
        p2.getEClassifiers().add(c2);

        Map<String, Object> variables = new LinkedHashMap<>();

        variables.put("c1", c);
        variables.put("p1", p);
        variables.put("c2", c2);
        variables.put("p2", p2);

        for (Entry<String, ?> var : variables.entrySet()) {
            interpreter.setVariable(var.getKey(), var.getValue());
        }

        checkRegisteredVariablesConsistency(variables, interpreter);

        // Ovverrides p2
        variables.put("p2", p);
        interpreter.setVariable("p2", p);
        checkRegisteredVariablesConsistency(variables, interpreter);

        // Go back to old value
        variables.put("p2", p2);
        interpreter.unSetVariable("p2");
        checkRegisteredVariablesConsistency(variables, interpreter);

        // Unset p1
        interpreter.unSetVariable("p2");
        variables.remove("p2");
        checkRegisteredVariablesConsistency(variables, interpreter);

        interpreter.dispose();
    }

    private void checkRegisteredVariablesConsistency(Map<String, Object> expectedVariables, IInterpreter interpreter) {
        Map<String, ?> registeredVariables = interpreter.getVariables();
        assertEquals(expectedVariables.size(), registeredVariables.size());

        for (String varName : expectedVariables.keySet()) {
            assertEquals("Wrong value for " + varName, expectedVariables.get(varName), interpreter.getVariable(varName));
        }
    }

    /**
     * Tests that it is possible to dispose the interpreter.
     */
    public void testAcceleoMTLInterpreterDisposeSuccess() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(IMPORT);

        interpreter.setVariable("c", eClass);

        assertEquals(1, interpreter.getImports().size());
        assertEquals(1, interpreter.getVariables().size());

        interpreter.dispose();

        assertEquals(0, interpreter.getImports().size());
        assertEquals(0, interpreter.getVariables().size());
    }

    /**
     * Tests that interpreter have correct stack values.
     */
    public void testAcceleoMTLVariableStacks() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("c1");

        EClass eClass2 = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("c2");

        final String varName = "c";
        final List<Object> varVals = new ArrayList<>();
        varVals.add(eClass);
        interpreter.setVariable(varName, eClass);
        assertEquals(eClass, interpreter.getVariable(varName));

        varVals.add(eClass2);
        interpreter.setVariable(varName, eClass2);
        assertEquals(eClass2, interpreter.getVariable(varName));

        varVals.remove(eClass2);
        interpreter.unSetVariable(varName);
        assertEquals(eClass, interpreter.getVariable(varName));

        varVals.remove(eClass);
        interpreter.unSetVariable(varName);
        assertNull(interpreter.getVariable(varName));

        varVals.add(eClass);
        interpreter.setVariable(varName, eClass);
        assertEquals(eClass, interpreter.getVariable(varName));

        varVals.clear();
        interpreter.clearVariables();
        assertNull(interpreter.getVariable(varName));

        interpreter.setVariable(varName, eClass);
        // test will fail if listener was not removed : valVals is not sync with
        // declared variables.

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate, add variables and then reevaluate.
     */
    public void testAcceleoMtlVariableTypeChange() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();
        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p");
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("c");

        evaluateString(interpreter, eClass, "[name/]", eClass.getName());

        interpreter.setVariable("var", eClass);

        evaluateString(interpreter, eClass, "[var.name/]", eClass.getName());
        evaluateString(interpreter, eClass, "[name/]", eClass.getName());

        interpreter.setVariable("var", p);

        evaluateString(interpreter, eClass, "[var.name/]", p.getName());
        evaluateString(interpreter, eClass, "[name/]", eClass.getName());

        interpreter.unSetVariable("var");

        evaluateString(interpreter, eClass, "[var.name/]", eClass.getName());
        evaluateString(interpreter, eClass, "[name/]", eClass.getName());

        interpreter.unSetVariable("var");

        evaluateString(interpreter, eClass, "[name/]", eClass.getName());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate, add variables and then reevaluate.
     */
    public void testAcceleoMtlMultiMMEvaluation() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("c");

        Participant p = InteractionsFactory.eINSTANCE.createParticipant();
        p.setType(eClass);

        evaluateEObject(interpreter, p, "[self.type/]", eClass);

        interpreter.dispose();
    }

    public void testAcceleoMtlCollectionEvaluationOnArrayValues() {
        IInterpreter interpreter = new AcceleoMTLInterpreter();

        // Setup
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("a1");
        String varExampleName = "varWithArray";
        String[] arrayValue = new String[] { eAttribute.getName() };
        interpreter.setVariable(varExampleName, arrayValue);

        // Test 1: evaluate returns the String array.
        String expression = "[" + varExampleName + "/]";
        evaluateObject(interpreter, eAttribute, expression, arrayValue);

        // Test 2: evaluateCollection returns an empty
        // Collection<EObject>the String array.
        evaluateCollection(interpreter, eAttribute, expression, Collections.<EObject> emptyList());

        // Change the value
        EObject[] eobjectArrayValue = new EObject[] { eAttribute };
        interpreter.setVariable(varExampleName, eobjectArrayValue);

        // Test 3: evaluate returns the EObject array.
        evaluateObject(interpreter, eAttribute, expression, eobjectArrayValue);

        // Test 4: evaluateCollection returns a Collection<EObject> with the
        // array content
        evaluateCollection(interpreter, eAttribute, expression, new ArrayList<EObject>(Arrays.asList(eobjectArrayValue)));

        interpreter.dispose();
    }

}
