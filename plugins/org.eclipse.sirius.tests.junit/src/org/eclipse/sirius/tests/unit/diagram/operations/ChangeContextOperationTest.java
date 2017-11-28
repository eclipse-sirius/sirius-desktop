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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.business.internal.helper.task.operations.ChangeContextTask;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EcoreIntrinsicExtender;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

/**
 * Test elementary operations.
 * 
 * @author cnotot
 * 
 */
public class ChangeContextOperationTest extends TestCase {

    private InterpreterRegistry iRegistry;

    private ModelAccessor accessor;

    private CommandContext rootContext = new CommandContext(EcorePackage.eINSTANCE, null);

    private class Case {
        protected EObject root;

        protected AbstractOperationTask task;

        void check() throws Exception {
            root = task.getContext().getNextPush();
            task.execute();
        }
    }

    private class Nominal extends Case {
        public void check() throws Exception {
            task = changeContextTask(rootContext, "aql:self.eContents()->first()");
            super.check();
            EObject contextAfter = rootContext.getNextPush();
            EObject firstElement = iRegistry.getInterpreter(root).evaluateEObject(root, "aql:self.eContents()->first()");
            assertEquals("The change context operation did not push the right context.", contextAfter, firstElement);
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
    }

    private AbstractOperationTask changeContextTask(CommandContext context, String browseExpression) {
        final ChangeContext changeContextOp = ToolFactory.eINSTANCE.createChangeContext();
        changeContextOp.setBrowseExpression(browseExpression);
        return new ChangeContextTask(context, accessor, changeContextOp, iRegistry.getInterpreter(changeContextOp));
    }

    /**
     * Check nominal use case.
     * 
     * @throws Exception
     */
    public void testNominal() throws Exception {
        new Nominal().check();
    }

}
