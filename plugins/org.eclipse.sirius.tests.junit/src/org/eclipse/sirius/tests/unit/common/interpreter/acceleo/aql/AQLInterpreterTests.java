/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.common.interpreter.acceleo.aql;

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
import org.eclipse.sirius.common.acceleo.aql.business.internal.AQLSiriusInterpreter;
import org.eclipse.sirius.common.acceleo.aql.business.internal.AcceleoAQLInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.JavaExtensionsManager;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.sample.interactions.InteractionsFactory;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

import com.google.common.collect.Iterables;

import junit.framework.TestCase;

/**
 * Tests for the {@link AQLSiriusInterpreter} utility class.
 * 
 * @author mporhel
 * 
 */
public class AQLInterpreterTests extends TestCase {

    private static final String MTL_IMPORT = "org::eclipse::sirius::tests::unit::common::interpreter::acceleo::mtl::AcceleoMtlInterpreterTestModule";

    private static final String SERVICE = "org.eclipse.sirius.tests.unit.common.interpreter.acceleo.aql.BasicService";

    /**
     * Tests that it is possible to create an empty range.
     */
    public void testAQLProvider() {
        IInterpreterProvider provider = new AcceleoAQLInterpreterProvider();

        assertFalse("The expression is not an AQL expression.", provider.provides("string"));
        assertFalse("The expression is not an AQL expression.", provider.provides("string aql:anExpression"));
        assertTrue("The expression is an AQL expression, not a mtl expression.", provider.provides("aql:'incomplete' + '[' + 'expression'+ ']'"));
        assertTrue("The expression looks like an AQL expression.", provider.provides("aql:expression"));
        assertTrue("The expression looks like a AQL expression.", provider.provides("aql:'an' + expression"));
    }

    /**
     * Tests prefix.
     */
    public void testAQLPrefix() {
        IInterpreter interpreter = new AQLSiriusInterpreter();
        assertEquals("aql:", interpreter.getPrefix());
    }

    /**
     * Tests variable prefix.
     */
    public void testAQLVariablePrefix() {
        IInterpreter interpreter = new AQLSiriusInterpreter();
        assertNull(interpreter.getVariablePrefix());
    }

    /**
     * Tests that it is possible to create an AQL interpreter.
     */
    public void testAQLInterpreterCreation() {
        IInterpreterProvider provider = new AcceleoAQLInterpreterProvider();
        IInterpreter createdInterpreter = provider.createInterpreter();

        assertTrue(createdInterpreter instanceof AQLSiriusInterpreter);

        createdInterpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a String.
     */
    public void testAQLStringEvaluation() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        evaluateString(interpreter, eClass, "aql:self.name", eClass.getName());
        evaluateString(interpreter, eClass, "aql:'t' + self.name + ' t'", "t" + eClass.getName() + " t");

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a String on a single element of a hierarchy.
     */
    public void testAQLHierarchyStringEvaluation() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p1");

        evaluateString(interpreter, p, "aql:self.name", p.getName());

        EPackage p2 = EcoreFactory.eINSTANCE.createEPackage();
        p2.setName("p2");

        evaluateString(interpreter, p2, "aql:self.name", p2.getName());
        evaluateString(interpreter, p, "aql:self.name", p.getName());

        p.getESubpackages().add(p2);

        evaluateString(interpreter, p2, "aql:self.name", p2.getName());
        evaluateString(interpreter, p, "aql:self.name", p.getName());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a boolean.
     */
    public void testAQLBooleanEvaluation() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();

        evaluateBoolean(interpreter, eClass, "aql:self.abstract", eClass.isAbstract());

        eClass.setAbstract(true);

        evaluateBoolean(interpreter, eClass, "aql:self.abstract", eClass.isAbstract());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate an Integer.
     */
    public void testAQLIntegerEvaluation() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        EReference ref = EcoreFactory.eINSTANCE.createEReference();

        int result = 0;
        try {
            result = interpreter.evaluateInteger(eClass, "aql:self.eReferences->size()");
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("Evaluation result do not match", eClass.getEReferences().size(), result);

        eClass.getEStructuralFeatures().add(ref);
        try {
            result = interpreter.evaluateInteger(eClass, "aql:self.eReferences->size()");
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("Evaluation result do not match", eClass.getEStructuralFeatures().size(), result);

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate an EObject.
     */
    public void testAQLEObjectEvaluation() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p1");

        EPackage p2 = EcoreFactory.eINSTANCE.createEPackage();
        p2.setName("p2");

        p.getESubpackages().add(p2);

        evaluateEObject(interpreter, p, "aql:self", p);
        evaluateEObject(interpreter, p2, "aql:self.eContainer()", p);

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate an Object.
     */
    public void testAQLObjectEvaluation() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p1");

        EPackage p2 = EcoreFactory.eINSTANCE.createEPackage();
        p2.setName("p2");

        p.getESubpackages().add(p2);

        evaluateObject(interpreter, p2, "aql:self.eContainer()", p);

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate a Collection.
     */
    public void testAQLCollectionEvaluation() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p1");

        EPackage p2 = EcoreFactory.eINSTANCE.createEPackage();
        p2.setName("p2");

        p.getESubpackages().add(p2);

        evaluateCollection(interpreter, p, "aql:self.eSubpackages", p.getESubpackages());
        evaluateCollection(interpreter, p, "aql:self.eAllContents()", p.getESubpackages());
        evaluateCollection(interpreter, p, "aql:self", Collections.singletonList(p));

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
    public void testAQLVariableAccess() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        String varName = "maClass";
        interpreter.setVariable(varName, eClass);

        evaluateString(interpreter, eClass, "aql:" + varName + ".name", eClass.getName());
        evaluateBoolean(interpreter, eClass, "aql:" + varName + ".abstract", eClass.isAbstract());
        evaluateCollection(interpreter, eClass, "aql:" + varName + ".eContents()", Collections.<EObject> emptyList());
        evaluateEObject(interpreter, eClass, "aql:" + varName + ".eContainer()", null);
        evaluateObject(interpreter, eClass, "aql:" + varName + ".eContainer()", null);

        // Check evaluation shortcut
        evaluateEObject(interpreter, eClass, "aql:" + varName, eClass);
        evaluateObject(interpreter, eClass, "aql:" + varName, eClass);

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to have access to self.
     */
    public void testAQLSelfAccess() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        EPackage pkg = EcoreFactory.eINSTANCE.createEPackage();
        pkg.getEClassifiers().add(eClass);

        String varName = "maClass";
        interpreter.setVariable(varName, eClass);

        evaluateString(interpreter, eClass, "aql:self.name", eClass.getName());
        evaluateBoolean(interpreter, eClass, "aql:self.abstract", eClass.isAbstract());
        evaluateCollection(interpreter, eClass, "self.eContents()", Collections.<EObject> emptyList());
        evaluateEObject(interpreter, eClass, "aql:self.eContainer()", pkg);
        evaluateObject(interpreter, eClass, "aql:self.eContainer()", pkg);

        // Check evaluation shortcut
        evaluateEObject(interpreter, eClass, "aql:self", eClass);
        evaluateObject(interpreter, eClass, "aql:self", eClass);

        interpreter.dispose();
    }

    /**
     * Tests that it is not possible to have access to thisEObject (MTL specific variable).
     */
    public void testAQLThisEObjectAccess() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        try {
            interpreter.evaluateString(eClass, "aql:thisEObject.name");
            fail("thisEObject was available in MTL but doesnot  exists in AQL.");
        } catch (Exception e) {
            e.getMessage().contains("Couldn't find the 'thisEObject' variable");
        }

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to call services and imported services.
     */
    public void testAQLScriptAccess() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(SERVICE);

        evaluateString(interpreter, eClass, "aql:self.getName()", eClass.getName());
        evaluateString(interpreter, eClass, "aql:self.getNameQuery()", eClass.getName());
        evaluateString(interpreter, eClass, "aql:self.sampleService()", "sampleService");

        evaluateBoolean(interpreter, eClass, "aql:self.isAbstractQuery()", eClass.isAbstract());
        evaluateCollection(interpreter, eClass, "aql:self.eContentsQuery()", eClass.eContents());
        evaluateEObject(interpreter, eClass, "aql:self.selfQuery()", eClass);

        interpreter.dispose();
    }

    /**
     * Tests that result change.
     */
    public void testNoWrongCache() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(SERVICE);

        evaluateString(interpreter, eClass, "aql:self.getNameQuery()", eClass.getName());

        eClass.setName("testName2");
        evaluateString(interpreter, eClass, "aql:self.getNameQuery()", eClass.getName());

        eClass.setName("testName3");
        evaluateString(interpreter, eClass, "aql:self.getNameQuery()", eClass.getName());

        interpreter.dispose();
    }

    /**
     * Tests that registering a MTL service or a Java package has no effect.
     */
    public void testAQLAddInvalidImports() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(MTL_IMPORT);
        interpreter.addImport("org::eclipse::sirius::common::test");

        assertEquals(0, interpreter.getImports().size());
        interpreter.addImport("org.eclipse.sirius.common.test");

        assertEquals(1, interpreter.getImports().size());

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        evaluateEObject(interpreter, p, "aql:self", p);

        JavaExtensionsManager jem = (JavaExtensionsManager) ReflectionHelper.getFieldValueWithoutException(interpreter, "javaExtensions").get();
        Collection<String> notLoadedExtensions = (Collection<String>) ReflectionHelper.getFieldValueWithoutException(jem, "couldNotBeLoaded").get();

        assertEquals(1, notLoadedExtensions.size());
        assertTrue(notLoadedExtensions.contains("org.eclipse.sirius.common.test"));

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to import a Java Service class.
     */
    public void testAQLAddImports() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(SERVICE);

        assertEquals(1, interpreter.getImports().size());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to remove a Java Service class.
     */
    public void testAQLRemoveImports() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(SERVICE);

        assertEquals(1, interpreter.getImports().size());

        interpreter.removeImport(SERVICE);

        assertEquals(0, interpreter.getImports().size());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to clear Java services.
     */
    public void testAQLClearImports() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(SERVICE);

        assertEquals(1, interpreter.getImports().size());

        interpreter.clearImports();

        assertEquals(0, interpreter.getImports().size());

        interpreter.dispose();
    }

    /**
     * Tests that IInterpreter.FILES has no effect on addImport in AQL Interpreter (it was necessary for MTL).
     */
    public void testAQLNoFilesProperties() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);

        interpreter.addImport(SERVICE);

        assertEquals(1, interpreter.getImports().size());

        interpreter.clearImports();
        
        assertEquals(0, interpreter.getImports().size());

        // Register vsm
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(SERVICE);

        assertEquals(1, interpreter.getImports().size());

        interpreter.clearImports();

        assertEquals(0, interpreter.getImports().size());

        interpreter.dispose();
    }

    /**
     * Tests arguments and variables order is valid.
     */
    public void testAQLVariablesValues() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

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
    public void testAQLInterpreterDisposeSuccess() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("testName");

        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(SERVICE);

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
    public void testAQLVariableStacks() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

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
    public void testAQLVariableTypeChange() {
        IInterpreter interpreter = new AQLSiriusInterpreter();
        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p");
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("c");

        evaluateString(interpreter, eClass, "aql:self.name", eClass.getName());

        interpreter.setVariable("var", eClass);

        evaluateString(interpreter, eClass, "aql:var.name", eClass.getName());
        evaluateString(interpreter, eClass, "aql:self.name", eClass.getName());

        interpreter.setVariable("var", p);

        evaluateString(interpreter, eClass, "aql:var.name", p.getName());
        evaluateString(interpreter, eClass, "aql:self.name", eClass.getName());

        interpreter.unSetVariable("var");

        evaluateString(interpreter, eClass, "aql:var.name", eClass.getName());
        evaluateString(interpreter, eClass, "aql:self.name", eClass.getName());

        interpreter.unSetVariable("var");

        evaluateString(interpreter, eClass, "aql:self.name", eClass.getName());

        interpreter.dispose();
    }

    /**
     * Tests that it is possible to evaluate, add variables and then reevaluate.
     */
    public void testAQLMultiMMEvaluation() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("c");

        Participant p = InteractionsFactory.eINSTANCE.createParticipant();
        p.setType(eClass);

        evaluateEObject(interpreter, p, "aql:self.type", eClass);

        interpreter.dispose();
    }

    public void testAQLCollectionEvaluationOnArrayValues() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        // Setup
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("a1");
        String varExampleName = "varWithArray";
        String[] arrayValue = new String[] { eAttribute.getName() };
        interpreter.setVariable(varExampleName, arrayValue);

        // Test 1: evaluate returns the String array.
        String expression = "aql:" + varExampleName;
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
    
    /**
     * Tests AQL parenthesis management
     * See https://github.com/eclipse-acceleo/acceleo/issues/219
     */
    public void testAQLParenthesisManagement() {
        IInterpreter interpreter = new AQLSiriusInterpreter();

        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("p1");

        evaluateEObject(interpreter, p, "aql:self", p);
        evaluateEObject(interpreter, p, "aql:(self)", p);
        evaluateEObject(interpreter, p, "aql:((self))", p);
        evaluateEObject(interpreter, p, "aql:(((((((self)))))))", p);
        
             
        evaluateString(interpreter, p, "aql:('')", "");
        
        evaluateBoolean(interpreter, p, "aql: false and true or false", false);
        evaluateBoolean(interpreter, p, "aql: false and (true or false)", false);
        evaluateBoolean(interpreter, p, "aql: true and (true or false)", true);
        evaluateBoolean(interpreter, p, "aql: false or true or false", true);
        evaluateBoolean(interpreter, p, "aql: false or (false or not (false))", true);

        
        try {
            interpreter.evaluateEObject(p, "aql:self)");
            fail("AQL should detect the expression as invalid: ");
        } catch (Exception e) {
           assertTrue("", e.getMessage().contains("text remaining after expression \")\""));
        }
        
        try {
            interpreter.evaluateEObject(p, "aql:true and 'false' = '(' or true)");
            fail("AQL should detect the expression as invalid: ");
        } catch (Exception e) {
            assertTrue("", e.getMessage().contains("text remaining after expression \")\""));
        }
        
        
        try {
            interpreter.evaluateEObject(p, "aql:true and 'false' = '\\'(' or true)");
            fail("AQL should detect the expression as invalid: ");
        } catch (Exception e) {
            assertTrue("", e.getMessage().contains("text remaining after expression \")\""));
        }
        
        interpreter.dispose();
    }

}
