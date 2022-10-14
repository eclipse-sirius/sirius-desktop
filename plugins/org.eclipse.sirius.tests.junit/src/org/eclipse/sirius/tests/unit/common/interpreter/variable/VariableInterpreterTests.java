/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.common.interpreter.variable;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.internal.dialect.CompositeInterpretedExpressionQuery;
import org.eclipse.sirius.common.tools.api.interpreter.DefaultInterpreterContextFactory;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.internal.interpreter.VariableInterpreter;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;

import junit.framework.TestCase;

/**
 * A Test case for the {@link VariableInterpreter}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class VariableInterpreterTests extends TestCase {

    private IInterpreter interpreter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interpreter = new VariableInterpreter();
    }

    public void testVariableInterpreterEvaluationWithNullParameters() {
        try {
            Object result = interpreter.evaluate(null, null);
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testVariableInterpreterEvaluationWithNullTargetAndEmptyExpression() {
        try {
            Object result = interpreter.evaluate(null, "");
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testVariableInterpreterEvaluationWithNullExpression() {
        try {
            EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
            ePackage.setName("p1");
            Object result = interpreter.evaluate(ePackage, null);
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testVariableInterpreterEvaluationWithEmptyExpression() {
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

    public void testVariableInterpreterEvaluationWithSpecificServiceExpression() {
        try {
            // Setup
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            String varExampleName = "varExampleName";
            String varExampleValue = "varExampleValue";
            interpreter.setVariable(varExampleName, varExampleValue);
            // Test
            Object result = interpreter.evaluate(eAttribute, VariableInterpreter.PREFIX + varExampleName);
            // Check
            assertEquals("The evaluation result must be the varExampleValue", varExampleValue, result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testVariableInterpreterEvaluationAfterVariableModifiations() {
        try {
            // Setup
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            String varExampleName = "varExampleName";
            String varExampleValue = "varExampleValue";
            interpreter.setVariable(varExampleName, varExampleValue);
            // Test
            Object result = interpreter.evaluate(eAttribute, VariableInterpreter.PREFIX + varExampleName);
            // Check
            assertEquals("The evaluation result must be the varExampleValue", varExampleValue, result);

            // Set a new value
            String varExampleValue2 = "varExampleValue2";
            interpreter.setVariable(varExampleName, varExampleValue2);
            // Test
            result = interpreter.evaluate(eAttribute, VariableInterpreter.PREFIX + varExampleName);
            // Check
            assertEquals("The evaluation result must be the varExampleValue", varExampleValue2, result);

            // Unset the variable, then the next expected evaluation result is
            // the previous value.
            interpreter.unSetVariable(varExampleName);
            // Test
            result = interpreter.evaluate(eAttribute, VariableInterpreter.PREFIX + varExampleName);
            // Check
            assertEquals("The evaluation result must be the varExampleValue", varExampleValue, result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    public void testVariableInterpreterEvaluationWithIncorretServiceExpression() {
        try {
            // Setup
            DEdge dEdge = DiagramFactory.eINSTANCE.createDEdge();
            EdgeStyle edgeStyle = DiagramFactory.eINSTANCE.createEdgeStyle();
            dEdge.setOwnedStyle(edgeStyle);
            // Test
            interpreter.evaluate(dEdge, VariableInterpreter.PREFIX + "not_a_variable");
            // Check
            fail("EvaluationException should be thrown");
        } catch (EvaluationException e) {

        }
    }

    public void testVariableInterpreterValidationOnSpecificVariableExpression() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        String varExampleName = "varExampleName";
        String varExampleValue = "varExampleValue";
        context.getVariables().put(varExampleName, VariableType.fromString(varExampleValue));
        // Test
        Collection<IInterpreterStatus> status = interpreter.validateExpression(context, VariableInterpreter.PREFIX + varExampleName);
        // Check
        assertNotNull(status);
        assertTrue("The validation should be successful", status.isEmpty());
    }

    public void testVariableInterpreterValidationOnIncorrectVariableExpression() {

        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        // Test
        Collection<IInterpreterStatus> status = interpreter.validateExpression(context, VariableInterpreter.PREFIX + "not_a_variable");
        // Check
        assertNotNull(status);
        assertEquals("The validation should return a IInterpreterStatus to show the error in the expression", 1, status.size());
        IInterpreterStatus interpreterStatus = status.iterator().next();
        assertEquals(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION, interpreterStatus.getField());
    }

    public void testVariableInterpreterCollectionEvaluationOnArrayValues() {
        try {
            // Setup
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            String varExampleName = "varWithArray";
            String[] arrayValue = new String[] { eAttribute.getName() };
            interpreter.setVariable(varExampleName, arrayValue);

            // Test 1: evaluate returns the String array.
            Object result = interpreter.evaluate(eAttribute, VariableInterpreter.PREFIX + varExampleName);
            assertEquals("The evaluate method should return the array.", arrayValue, result);

            // Test 2: evaluateCollection returns an empty
            // Collection<EObject>the String array.
            result = interpreter.evaluateCollection(eAttribute, VariableInterpreter.PREFIX + varExampleName);
            assertTrue("The evaluateCollection method should return a collection.", result instanceof Collection);
            assertTrue("The evaluateCollection method should return a en emtpy collection, the array contains only Strings.", ((Collection<?>) result).isEmpty());

            // Change the value
            EObject[] eobjectArrayValue = new EObject[] { eAttribute };
            interpreter.setVariable(varExampleName, eobjectArrayValue);

            // Test 3: evaluate returns the EObject array.
            result = interpreter.evaluate(eAttribute, VariableInterpreter.PREFIX + varExampleName);
            assertEquals("The evaluate method should return the array.", eobjectArrayValue, result);

            // Test 4: evaluateCollection returns a Collection<EObject> with the
            // array content
            result = interpreter.evaluateCollection(eAttribute, VariableInterpreter.PREFIX + varExampleName);
            assertTrue("The evaluateCollection method should return a collection.", result instanceof Collection);
            assertEquals("The evaluateCollection method should return a collection with the content of the array.", eobjectArrayValue[0], ((Collection<?>) result).iterator().next());

        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown");
        }
    }

    /**
     * Tests the validation with a {@link CompositeInterpretedExpressionQuery} in which new variables have been added,
     * and also a variable with at least 2 possible types.
     */
    public void testVariableInterpreterValidationWithCompositeInterpretedExpressionQuery() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());

        String varExampleName1 = "varExampleName1";
        String varExampleValue1 = "varExampleValue1";
        String varExampleName2 = "varExampleName2";
        String varExampleValue2 = "varExampleValue2";
        String varSeveralTypes = "varSeveralTypes";

        IInterpretedExpressionQuery query1 = DialectManager.INSTANCE.createInterpretedExpressionQuery(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        query1.getAvailableVariables().put(varSeveralTypes, VariableType.fromJavaClass(Object.class));
        query1.getAvailableVariables().put(varExampleName1, VariableType.fromString(varExampleValue1));

        IInterpretedExpressionQuery query2 = DialectManager.INSTANCE.createInterpretedExpressionQuery(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        query2.getAvailableVariables().put(varSeveralTypes, VariableType.fromJavaClass(String.class));
        query2.getAvailableVariables().put(varExampleName2, VariableType.fromString(varExampleValue2));

        CompositeInterpretedExpressionQuery compositeQuery = new CompositeInterpretedExpressionQuery();
        compositeQuery.add(query1);
        compositeQuery.add(query2);

        VariableType customizedType = compositeQuery.getAvailableVariables().get(varSeveralTypes);
        assertEquals("There should be 2 possible types", 2, customizedType.getPossibleTypes().size());

        IInterpreterContext customContext = createCustomInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION, compositeQuery);

        // Check validation is successful
        Collection<IInterpreterStatus> status = interpreter.validateExpression(customContext, VariableInterpreter.PREFIX + varExampleName1);
        assertNotNull(status);
        assertTrue("The validation should be successful", status.isEmpty());

        status = interpreter.validateExpression(customContext, VariableInterpreter.PREFIX + varExampleName2);
        assertNotNull(status);
        assertTrue("The validation should be successful", status.isEmpty());

        status = interpreter.validateExpression(customContext, VariableInterpreter.PREFIX + varSeveralTypes);
        assertNotNull(status);
        assertTrue("The validation should be successful", status.isEmpty());
    }

    private static IInterpreterContext createCustomInterpreterContext(EObject element, EStructuralFeature feature, IInterpretedExpressionQuery query) {
        Collection<String> targetDomainClasses = new LinkedHashSet<>();
        Collection<EPackage> avalaiblePackages = new LinkedHashSet<>();
        Collection<String> dependencies = new LinkedHashSet<>();
        Map<String, VariableType> variables = new LinkedHashMap<>();
        boolean requiresTargetType = true;

        Option<Collection<String>> targetDomainClassesOption = query.getTargetDomainClasses();
        if (!targetDomainClassesOption.some()) {
            requiresTargetType = false;
        } else {
            for (String domainClass : targetDomainClassesOption.get()) {
                targetDomainClasses.add(domainClass);
            }
        }
        if (!targetDomainClassesOption.some() || !targetDomainClassesOption.get().isEmpty()) {
            avalaiblePackages = query.getPackagesToImport();
            variables = query.getAvailableVariables();
            dependencies = query.getDependencies();
        }

        IInterpreterContext context = DefaultInterpreterContextFactory.createInterpreterContext(element, requiresTargetType, feature, VariableType.fromStrings(targetDomainClasses), avalaiblePackages,
                variables, dependencies);
        return context;
    }

    @Override
    protected void tearDown() throws Exception {
        interpreter = null;
        super.tearDown();
    }
}
