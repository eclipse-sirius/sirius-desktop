/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.api.interpreter;

import java.util.concurrent.TimeUnit;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * Tests on editing domain based on entities diagram of ecore modeler.
 * 
 * @author pcdavid
 */
public class InterpreterPerformanceTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String NAME = "ecore modeler test";
    
    private static final String VAR_NAME = "testVar";
    
    private static final String VAR_VALUE = "varValue";

    private static final int ITER = 1000 * 1000;

    private EAttribute testAttr;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        interpreter = session.getInterpreter();
        interpreter.setVariable(VAR_NAME, VAR_VALUE);
        final EPackage root = (EPackage) semanticModel;
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                EClass klass = EcoreFactory.eINSTANCE.createEClass();
                klass.setName("A");
                root.getEClassifiers().add(klass);
                testAttr = EcoreFactory.eINSTANCE.createEAttribute();
                testAttr.setName("foo");
                testAttr.setLowerBound(1);
                testAttr.setUpperBound(1);
                testAttr.setEType(EcorePackage.Literals.ESTRING);
                klass.getEStructuralFeatures().add(testAttr);
            }
        });
        TestsUtil.emptyEventsFromUIThread();
        Thread.sleep(3000);
    }

    public void testEvaluateNameWithAQL() throws EvaluationException {
        measure("AQL", semanticModel, "aql:self.name", NAME, ITER);
    }

    public void testEvaluateNameWithAcceleo3() throws EvaluationException {
        measure("Acceleo 3", semanticModel, "aql:self.name", NAME, ITER);
    }

    public void testEvaluateNameWithFeature() throws EvaluationException {
        measure("feature:", semanticModel, "feature:name", NAME, ITER);
    }

    public void testEvaluateVarWithAQL() throws EvaluationException {
        measure("AQL", semanticModel, "aql:testVar", VAR_VALUE, ITER);
    }

    public void testEvaluateVarWithAcceleo3() throws EvaluationException {
        measure("Acceleo 3", semanticModel, "[testVar/]", VAR_VALUE, ITER);
    }

    public void testEvaluateVarWithFeature() throws EvaluationException {
        measure("var:", semanticModel, "var:testVar", VAR_VALUE, ITER);
    }

    public void _testEvaluateServiceWithAQL() throws EvaluationException {
        measure("AQL", testAttr, "aql:self.render()", "foo : EString", ITER);
    }

    public void _testEvaluateServiceWithAcceleo3() throws EvaluationException {
        measure("Acceleo 3", testAttr, "[self.render()/]", "foo : EString", ITER);
    }

    public void _testEvaluateServiceWithFeature() throws EvaluationException {
        measure("service:", testAttr, "service:render", "foo : EString", ITER);
    }

    private void measure(String interpreterName, EObject self, String expression, String expected, int times) throws EvaluationException {
        String name = null;
        long start = System.nanoTime();
        for (int i = 0; i < times; i++) {
            name = interpreter.evaluateString(self, expression);
        }
        long duration = System.nanoTime() - start;
        assertEquals(expected, name);
        String totalMs = TimeUnit.NANOSECONDS.toMillis(duration) + "ms";
        String meanNs = Math.round(((double) duration / (double) times)) + "ns";
        System.out.println(interpreterName + " (" + expression + ", N = " + times + "): total = " + totalMs + ", mean = " + meanNs);
    }

}
