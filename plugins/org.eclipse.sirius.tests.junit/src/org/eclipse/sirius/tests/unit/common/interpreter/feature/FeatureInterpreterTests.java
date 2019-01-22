/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common.interpreter.feature;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.internal.interpreter.FeatureInterpreter;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;

import junit.framework.TestCase;

/**
 * A Test case for the {@link FeatureInterpreter}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class FeatureInterpreterTests extends TestCase {

    private IInterpreter interpreter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interpreter = new FeatureInterpreter();
    }

    public void testFeatureInterpreterEvaluationWithNullParameters() {
        try {
            Object result = interpreter.evaluate(null, null);
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown." + e.getMessage());
        }
    }

    public void testFeatureInterpreterEvaluationWithNullTargetAndEmptyExpression() {
        try {
            Object result = interpreter.evaluate(null, "");
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown." + e.getMessage());
        }
    }

    public void testFeatureInterpreterEvaluationWithNullExpression() {
        try {
            EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
            ePackage.setName("p1");
            Object result = interpreter.evaluate(ePackage, null);
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown." + e.getMessage());
        }
    }

    public void testFeatureInterpreterEvaluationWithEmptyExpression() {
        try {
            // Setup
            EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
            ePackage.setName("p1");
            // Test
            Object result = interpreter.evaluate(ePackage, "");
            // Check
            assertNull("The evaluation result must be null because we have not provided context and expression", result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown." + e.getMessage());
        }
    }

    public void testFeatureInterpreterEvaluationWithEContainerExpression() {
        try {
            // Setup
            EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
            ePackage.setName("p1");
            EPackage subEPackage = EcoreFactory.eINSTANCE.createEPackage();
            subEPackage.setName("p11");
            ePackage.getESubpackages().add(subEPackage);
            // Test
            Object result = interpreter.evaluate(subEPackage, FeatureInterpreter.PREFIX + FeatureInterpreter.E_CONTAINER_FEATURE_NAME);
            // Check
            assertEquals("The evaluation result must be the subEPackage", ePackage, result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown. " + e.getMessage());
        }
    }

    public void testFeatureInterpreterEvaluationWithEContentsExpression() {
        try {
            // Setup
            EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
            ePackage.setName("p1");
            EPackage subEPackage = EcoreFactory.eINSTANCE.createEPackage();
            subEPackage.setName("p11");
            ePackage.getESubpackages().add(subEPackage);
            EClass eClass = EcoreFactory.eINSTANCE.createEClass();
            ePackage.getEClassifiers().add(eClass);
            // Test
            Object result = interpreter.evaluate(ePackage, FeatureInterpreter.PREFIX + FeatureInterpreter.E_CONTENTS_FEATURE_NAME);
            // Check
            assertTrue(result instanceof EList<?>);
            @SuppressWarnings("unchecked")
            EList<?> contents = (EList<Object>) result;
            assertEquals(2, contents.size());
            assertEquals("The evaluation result must contains the eClass", eClass, contents.get(0));
            assertEquals("The evaluation result must contains the subEPackage", subEPackage, contents.get(1));
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown." + e.getMessage());
        }
    }

    public void testFeatureInterpreterEvaluationWithEAllContentsExpression() {
        try {
            // Setup
            EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
            ePackage.setName("p1");
            EPackage subEPackage = EcoreFactory.eINSTANCE.createEPackage();
            subEPackage.setName("p11");
            ePackage.getESubpackages().add(subEPackage);
            EClass eClass = EcoreFactory.eINSTANCE.createEClass();
            ePackage.getEClassifiers().add(eClass);
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eClass.getEStructuralFeatures().add(eAttribute);
            // Test
            Object result = interpreter.evaluate(ePackage, FeatureInterpreter.PREFIX + FeatureInterpreter.E_ALL_CONTENTS_FEATURE_NAME);
            // Check
            assertTrue(result instanceof Collection<?>);
            @SuppressWarnings("unchecked")
            Collection<?> allContents = (Collection<Object>) result;
            assertEquals("The eAllContents call should return a collection of 3 elements", 3, allContents.size());
            Iterator<?> iterator = allContents.iterator();
            assertEquals("The evaluation result must contains the eClass", eClass, iterator.next());
            assertEquals("The evaluation result must contains the eAttribute", eAttribute, iterator.next());
            assertEquals("The evaluation result must contains the subEPackage", subEPackage, iterator.next());
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown. " + e.getMessage());
        }
    }

    public void testFeatureInterpreterEvaluationWithEClassExpression() {
        try {
            // Setup
            EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
            ePackage.setName("p1");
            // Test
            Object result = interpreter.evaluate(ePackage, FeatureInterpreter.PREFIX + FeatureInterpreter.E_CLASS_FEATURE_NAME);
            // Check
            assertEquals("The evaluation result must be the EcorePackage.Literals.EPACKAGE", EcorePackage.Literals.EPACKAGE, result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown. " + e.getMessage());
        }
    }

    public void testFeatureInterpreterEvaluationWithECrossReferencesExpression() {
        try {
            // Setup
            EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
            ePackage.setName("p1");
            EPackage subEPackage = EcoreFactory.eINSTANCE.createEPackage();
            subEPackage.setName("p11");
            ePackage.getESubpackages().add(subEPackage);
            EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
            eClass1.setName("eClass1");
            ePackage.getEClassifiers().add(eClass1);
            EClass eClass2 = EcoreFactory.eINSTANCE.createEClass();
            eClass2.setName("eClass2");
            ePackage.getEClassifiers().add(eClass2);
            EReference eReference1 = EcoreFactory.eINSTANCE.createEReference();
            eReference1.setName("eReference1");
            eReference1.setEType(eClass2);
            eClass1.getEStructuralFeatures().add(eReference1);
            EReference eReference2 = EcoreFactory.eINSTANCE.createEReference();
            eReference2.setName("eReference2");
            eReference2.setEType(eClass2);
            eClass1.getEStructuralFeatures().add(eReference2);
            EReference eReference3 = EcoreFactory.eINSTANCE.createEReference();
            eReference3.setName("eReference3");
            eReference3.setEType(eClass2);
            eClass1.getEStructuralFeatures().add(eReference3);
            // Test
            Object result = interpreter.evaluate(eClass1, FeatureInterpreter.PREFIX + FeatureInterpreter.E_CROSS_REFERENCES_FEATURE_NAME);
            // Check
            assertTrue(result instanceof EList<?>);
            @SuppressWarnings("unchecked")
            EList<?> contents = (EList<Object>) result;
            assertEquals(9, contents.size());
            assertEquals("The evaluation result must contains the eReference1", eReference1, contents.get(0));
            assertEquals("The evaluation result must contains the eReference2", eReference2, contents.get(1));
            assertEquals("The evaluation result must contains the eReference3", eReference3, contents.get(2));
            assertEquals("The evaluation result must contains the eReference1", eReference1, contents.get(3));
            assertEquals("The evaluation result must contains the eReference2", eReference2, contents.get(4));
            assertEquals("The evaluation result must contains the eReference3", eReference3, contents.get(5));
            assertEquals("The evaluation result must contains the eReference1", eReference1, contents.get(6));
            assertEquals("The evaluation result must contains the eReference2", eReference2, contents.get(7));
            assertEquals("The evaluation result must contains the eReference3", eReference3, contents.get(8));
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown. " + e.getMessage());
        }
    }

    public void testFeatureInterpreterEvaluationWithSpecificFeatureExpression() {
        try {
            // Setup
            DEdge dEdge = DiagramFactory.eINSTANCE.createDEdge();
            EdgeStyle edgeStyle = DiagramFactory.eINSTANCE.createEdgeStyle();
            dEdge.setOwnedStyle(edgeStyle);
            // Test
            Object result = interpreter.evaluate(dEdge, FeatureInterpreter.PREFIX + DiagramPackage.Literals.DEDGE__OWNED_STYLE.getName());
            // Check
            assertEquals("The evaluation result must be the edgeStyle", edgeStyle, result);
        } catch (EvaluationException e) {
            fail("EvaluationException should not be thrown." + e.getMessage());
        }
    }

    public void testFeatureInterpreterEvaluationWithIncorretFeatureExpression() {
        try {
            // Setup
            DEdge dEdge = DiagramFactory.eINSTANCE.createDEdge();
            EdgeStyle edgeStyle = DiagramFactory.eINSTANCE.createEdgeStyle();
            dEdge.setOwnedStyle(edgeStyle);
            // Test
            interpreter.evaluate(dEdge, FeatureInterpreter.PREFIX + "not_a_feature");
            // Check
            fail("EvaluationException should be thrown");
        } catch (EvaluationException e) {

        }
    }

    public void testFeatureInterpreterValidationOnEContainerFeatureExpression() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        // Test
        Collection<IInterpreterStatus> status = interpreter.validateExpression(context, FeatureInterpreter.PREFIX + FeatureInterpreter.E_CONTAINER_FEATURE_NAME);
        // Check
        assertNotNull(status);
        assertTrue("The validation should be successful", status.isEmpty());
    }

    public void testFeatureInterpreterValidationOnEContentsFeatureExpression() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        // Test
        Collection<IInterpreterStatus> status = interpreter.validateExpression(context, FeatureInterpreter.PREFIX + FeatureInterpreter.E_CONTENTS_FEATURE_NAME);
        // Check
        assertNotNull(status);
        assertTrue("The validation should be successful", status.isEmpty());
    }

    public void testFeatureInterpreterValidationOnEAllContentsFeatureExpression() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        // Test
        Collection<IInterpreterStatus> status = interpreter.validateExpression(context, FeatureInterpreter.PREFIX + FeatureInterpreter.E_ALL_CONTENTS_FEATURE_NAME);
        // Check
        assertNotNull(status);
        assertTrue("The validation should be successful", status.isEmpty());
    }

    public void testFeatureInterpreterValidationOnEClassFeatureExpression() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        // Test
        Collection<IInterpreterStatus> status = interpreter.validateExpression(context, FeatureInterpreter.PREFIX + FeatureInterpreter.E_CLASS_FEATURE_NAME);
        // Check
        assertNotNull(status);
        assertTrue("The validation should be successful", status.isEmpty());
    }

    public void testFeatureInterpreterValidationOnECrossReferencesFeatureExpression() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        // Test
        Collection<IInterpreterStatus> status = interpreter.validateExpression(context, FeatureInterpreter.PREFIX + FeatureInterpreter.E_CROSS_REFERENCES_FEATURE_NAME);
        // Check
        assertNotNull(status);
        assertTrue("The validation should be successful", status.isEmpty());
    }

    public void testFeatureInterpreterValidationOnSpecificFeatureExpression() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        // Test
        Collection<IInterpreterStatus> status = interpreter.validateExpression(context, FeatureInterpreter.PREFIX + EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS.getName());
        // Check
        assertNotNull(status);
        assertTrue("The validation should be successful", status.isEmpty());
    }

    public void testFeatureInterpreterValidationOnIncorrectFeatureExpression() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        // Test
        Collection<IInterpreterStatus> status = interpreter.validateExpression(context, FeatureInterpreter.PREFIX + "not_a_feature");
        // Check
        assertNotNull(status);
        assertEquals("The validation should return a IInterpreterStatus to show the error in the expression", 1, status.size());
        IInterpreterStatus interpreterStatus = status.iterator().next();
        assertEquals(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION, interpreterStatus.getField());
    }

    @Override
    protected void tearDown() throws Exception {
        interpreter = null;
        super.tearDown();
    }
}
