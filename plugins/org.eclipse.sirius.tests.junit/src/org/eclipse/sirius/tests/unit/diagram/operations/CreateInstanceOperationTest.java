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
package org.eclipse.sirius.tests.unit.diagram.operations;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.business.internal.helper.task.operations.CreateInstanceTask;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EcoreIntrinsicExtender;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

/**
 * Test elementary operations.
 * 
 * @author cnotot
 * 
 */
public class CreateInstanceOperationTest extends TestCase {

    private final static String RIGHT_REFERENCE_NAME = "eClassifiers";

    private final static String RIGHT_TYPE_NAME = "EClass";

    private final static String DEFAULT_VARIABLE_NAME = ToolPackage.Literals.CREATE_INSTANCE__VARIABLE_NAME.getDefaultValueLiteral();

    private InterpreterRegistry iRegistry;

    private ModelAccessor accessor;

    private CommandContext rootContext = new CommandContext(EcorePackage.eINSTANCE, null);

    private String variableName = DEFAULT_VARIABLE_NAME;

    int initialNbElements;

    int initialNbVariables;

    private class Case {
        protected EObject root;

        protected AbstractOperationTask task;

        void check() throws Exception {
            root = task.getContext().getNextPush();
            task.execute();
            IInterpreter interpreter = iRegistry.getInterpreter(root);
            assertNotNull("The " + variableName + " variable should be available to reference the created instance", interpreter.evaluateEObject(root, "aql:" + variableName));
        }
    }

    private class Nominal extends Case {
        public void check() throws Exception {
            task = createInstanceTask(rootContext, RIGHT_REFERENCE_NAME, RIGHT_TYPE_NAME, DEFAULT_VARIABLE_NAME);
            super.check();
            EObject contextAfter = rootContext.getNextPush();
            assertEquals("Create instance task failed to create an element.", initialNbElements + 1, accessor.eAllContents(root, "EObject").size());
            assertTrue("The EClass has to be created under the eClassifiers reference of the EPackage.", ((EPackage) root).getEClassifiers().contains(contextAfter));
            assertTrue("The context has to change to the created element.", contextAfter instanceof EClass);
        }
    }

    private class NoFeatureName extends Case {
        public void check(String featureName) throws Exception {
            try {
                task = createInstanceTask(rootContext, featureName, RIGHT_TYPE_NAME, DEFAULT_VARIABLE_NAME);
                super.check();
                EObject contextAfter = rootContext.getNextPush();
                assertEquals("No element should be created.", 0, accessor.eAllContents(root, "EObject").size());
                assertTrue("The context has not to change", contextAfter instanceof EPackage);
            } catch (FeatureNotFoundException e) {
            }
        }
    }

    private class NoTypeName extends Case {
        public void check(String typeName) throws Exception {
            try {
                task = createInstanceTask(rootContext, RIGHT_REFERENCE_NAME, typeName, DEFAULT_VARIABLE_NAME);
                super.check();
                EObject contextAfter = rootContext.getNextPush();
                assertEquals("No element should be created.", 0, accessor.eAllContents(root, "EObject").size());
                assertTrue("The context has not to change", contextAfter instanceof EPackage);
            } catch (MetaClassNotFoundException e) {
            }
        }
    }

    private class NoTypeNameNull extends NoTypeName {
        public void check() throws Exception {
            try {
                super.check(null);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    private class VariableName extends Case {
        public void check(String variableName) throws Exception {
            task = createInstanceTask(rootContext, RIGHT_REFERENCE_NAME, RIGHT_TYPE_NAME, variableName);
            CreateInstanceOperationTest.this.variableName = variableName;
            super.check();
            EObject contextAfter = rootContext.getNextPush();
            Object variable = iRegistry.getInterpreter(rootContext.getCurrentTarget()).getVariable(variableName);
            assertEquals("The variable " + variableName + " should contain an EClass", variable, contextAfter);
            assertEquals("Create instance task failed to create an element.", initialNbElements + 1, accessor.eAllContents(root, "EObject").size());
            assertTrue("The EClass has to be created under the eClassifiers reference of the EPackage.", ((EPackage) root).getEClassifiers().contains(variable));
            assertTrue("The context has to change to the created element.", contextAfter instanceof EClass);
        }
    }

    private class NoVariableName extends Case {
        public void check(String variableName) throws Exception {
            task = createInstanceTask(rootContext, RIGHT_REFERENCE_NAME, RIGHT_TYPE_NAME, variableName);
            super.check();
            int nbVariablesAfter = iRegistry.getInterpreter(rootContext.getCurrentTarget()).getVariables().size();
            EObject contextAfter = rootContext.getNextPush();
            assertEquals("No additional variable should be exist", initialNbVariables, nbVariablesAfter);
            assertEquals("Create instance task failed to create an element.", initialNbElements + 1, accessor.eAllContents(root, "EObject").size());
            assertTrue("The EClass has to be created under the eClassifiers reference of the EPackage.", ((EPackage) root).getEClassifiers().contains(contextAfter));
            assertTrue("The context has to change to the created element.", contextAfter instanceof EClass);
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        accessor = new ModelAccessor();
        accessor.addExtender(new EcoreIntrinsicExtender(), ExtenderConstants.HIGH_PRIORITY);
        accessor.activateMetamodels(Lists.newArrayList(new EcoreMetamodelDescriptor(EcorePackage.eINSTANCE)));

        iRegistry = new InterpreterRegistry() {

            @Override
            public IInterpreter getInterpreter(EObject modelElement) {
                return CompoundInterpreter.INSTANCE;
            }
        };

        initialNbElements = accessor.eAllContents(EcorePackage.eINSTANCE, "EObject").size();
        initialNbVariables = iRegistry.getInterpreter(rootContext.getCurrentTarget()).getVariables().size();
    }

    @Override
    protected void tearDown() throws Exception {
        // Cleanup anonymous classifiers which were added to Ecore during the
        // tests
        Iterator<EClassifier> iter = EcorePackage.eINSTANCE.getEClassifiers().iterator();
        while (iter.hasNext()) {
            EClassifier obj = iter.next();
            if (obj.getName() == null) {
                iter.remove();
            }
        }
        super.tearDown();
    }

    private AbstractOperationTask createInstanceTask(CommandContext context, String referenceName, String typeName, String variableName) {
        final CreateInstance createOp = ToolFactory.eINSTANCE.createCreateInstance();

        createOp.setReferenceName(referenceName);
        createOp.setTypeName(typeName);
        if (!createOp.getVariableName().equals(variableName)) {
            createOp.setVariableName(variableName);
        }

        return new CreateInstanceTask(context, accessor, createOp, iRegistry.getInterpreter(context.getCurrentTarget()));
    }

    /**
     * Check nominal use case.
     * 
     * @throws Exception
     */
    public void testNominal() throws Exception {
        new Nominal().check();
    }

    /**
     * Check when the feature name doesn't exist.
     * 
     * @throws Exception
     */
    public void testNoFeatureName() throws Exception {
        new NoFeatureName().check(RIGHT_REFERENCE_NAME + "W");
    }

    /**
     * Check when the feature name is not set.
     * 
     * @throws Exception
     */
    public void testNoFeatureName2() throws Exception {
        new NoFeatureName().check("");
    }

    /**
     * Check when the feature name is not set.
     * 
     * @throws Exception
     */
    public void testNoFeatureName3() throws Exception {
        new NoFeatureName().check(null);
    }

    /**
     * Check when the feature name is not set.
     * 
     * @throws Exception
     */
    public void testNoFeatureName4() throws Exception {
        new NoFeatureName().check(" ");
    }

    /**
     * Check when the type name doesn't exist.
     * 
     * @throws Exception
     */
    public void testNoTypeName() throws Exception {
        new NoTypeName().check(RIGHT_TYPE_NAME + "W");
    }

    /**
     * Check when the type name doesn't exist.
     * 
     * @throws Exception
     */
    public void testNoTypeName2() throws Exception {
        new NoTypeName().check("");
    }

    /**
     * Check when the type name doesn't exist.
     * 
     * @throws Exception
     */
    public void testNoTypeName3() throws Exception {
        // new NoTypeName().check(null);
        // TODO VP-873
        // The test case should be return a MetaClassNotFoundException instead
        // of IllegalArgumentException

        new NoTypeNameNull().check();
    }

    /**
     * Check when the type name doesn't exist.
     * 
     * @throws Exception
     */
    public void testNoTypeName4() throws Exception {
        new NoTypeName().check(" ");
    }

    /**
     * Check when the variable name doesn't exist.
     * 
     * @throws Exception
     */
    public void testVariableName1() throws Exception {
        new VariableName().check(DEFAULT_VARIABLE_NAME);
    }

    /**
     * Check when the variable name doesn't exist.
     * 
     * @throws Exception
     */
    public void testVariableName2() throws Exception {
        new VariableName().check("myOwnVariable");
    }

    /**
     * Check when the variable name is not set.
     * 
     * @throws Exception
     */
    public void testNoVariableName() throws Exception {
        new NoVariableName().check("");
    }

    /**
     * Check when the variable name is not set.
     * 
     * @throws Exception
     */
    public void testNoVariableName2() throws Exception {
        new NoVariableName().check(null);
    }

    /**
     * Check when the variable name is not set.
     * 
     * @throws Exception
     */
    public void testNoVariableName3() throws Exception {
        new NoVariableName().check(" ");
    }

}
