/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.diagram.operations;

import java.util.Arrays;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.internal.helper.task.operations.CreateViewTask;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EcoreIntrinsicExtender;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tests.unit.diagram.tools.data.GroupCreateViewTask;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.session.UserSession;
import org.eclipse.sirius.viewpoint.description.Group;

import junit.framework.TestCase;

/**
 * Test elementary operations.
 * 
 * @author cnotot
 */
public class CreateViewOperationTest extends TestCase {

    private Session session;

    private ModelAccessor accessor;

    private CommandContext rootContext;

    private CreateViewOperationData oDesign;

    private DDiagram diagram;

    private class Case {
        protected AbstractOperationTask task;

        void check() throws Exception {
            TransactionalEditingDomain domain = CreateViewOperationTest.this.session.getTransactionalEditingDomain();
            SiriusCommand viewpointCommand = new SiriusCommand(domain);
            viewpointCommand.getTasks().add(task);
            domain.getCommandStack().execute(viewpointCommand);
            IInterpreter interpreter = session.getInterpreter();
            assertNotNull("The variable" + ToolPackage.Literals.CREATE_VIEW__VARIABLE_NAME.getDefaultValueLiteral() + " should be available to reference the created view",
                    interpreter.evaluateEObject(rootContext.getCurrentTarget(), "aql:" + ToolPackage.Literals.CREATE_VIEW__VARIABLE_NAME.getDefaultValueLiteral()));
            assertNotNull("The variable" + ToolPackage.Literals.CREATE_VIEW__VARIABLE_NAME.getDefaultValueLiteral() + " should be available to reference the created view",
                    interpreter.getVariable(ToolPackage.Literals.CREATE_VIEW__VARIABLE_NAME.getDefaultValueLiteral()));
        }
    }

    private class Nominal extends Case {
        @Override
        public void check() throws Exception {

            assertEquals("The diagram should contain no node", 0, diagram.getDiagramElements().size());

            DiagramElementMapping mapping = oDesign.group().viewpoint1().diagram1().mydefaultlayer().nodemapping1().object();

            session.getInterpreter().setVariable("elementView", diagram);

            task = createViewTask(rootContext, mapping, "aql:elementView.eContainerOrSelf(diagram::DDiagram)");

            super.check();
            rootContext.getNextPush();

            assertEquals("The diagram should contain 1 node", 1, diagram.getDiagramElements().size());

            // TODO VP-874
            // check that the context has changed
            // assertTrue("The context has to change to the created element",
            // contextAfter instanceof DNode);
        }
    }

    private class NoContainerViewExpression extends Case {
        public void check(String containerViewExpression) throws Exception {

            DiagramElementMapping mapping = oDesign.group().viewpoint1().diagram1().mydefaultlayer().nodemapping1().object();

            task = createViewTask(rootContext, mapping, containerViewExpression);
            TransactionalEditingDomain domain = CreateViewOperationTest.this.session.getTransactionalEditingDomain();
            SiriusCommand viewpointCommand = new SiriusCommand(domain);
            viewpointCommand.getTasks().add(task);
            domain.getCommandStack().execute(viewpointCommand);

        }
    }

    private class NoContainerViewExpressionNull extends NoContainerViewExpression {
        @Override
        public void check() throws Exception {
            try {
                super.check(null);
            } catch (ClassCastException e) {

            }
        }
    }

    private class NoContainerViewExpressionEmpty extends NoContainerViewExpression {
        @Override
        public void check() throws Exception {
            try {
                super.check("");
            } catch (ClassCastException e) {

            }
        }
    }

    private class NoMapping extends Case {
        public void check(DiagramElementMapping mapping) throws Exception {
            task = createViewTask(rootContext, mapping, "aql:elementView.eContainerOrSelf(diagram::DDiagram)");
            task.execute();

            // TODO VP-874 check we have the requested business exception
            // assertTrue("We should have an exception", false);
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        accessor = new ModelAccessor();
        accessor.addExtender(new EcoreIntrinsicExtender(), ExtenderConstants.HIGH_PRIORITY);
        accessor.activateMetamodels(Arrays.asList(new EcoreMetamodelDescriptor(EcorePackage.eINSTANCE)));

        oDesign = new CreateViewOperationData();
        oDesign.load();

        URI transientSessionResourceURI = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/Project/test.aird");
        URI transientSemanticResourceURI = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/Project/test.ecore");
        Resource semanticResource = new ResourceSetImpl().createResource(transientSemanticResourceURI);
        EPackage semantic = EcoreFactory.eINSTANCE.createEPackage();
        semantic.getEClassifiers().add(EcoreFactory.eINSTANCE.createEClass());
        semanticResource.getContents().add(semantic);
        semanticResource.save(Collections.emptyMap());
        session = SessionManager.INSTANCE.getSession(transientSessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, transientSemanticResourceURI, new NullProgressMonitor());
        domain.getCommandStack().execute(addSemanticResourceCmd);
        semantic = (EPackage) session.getSemanticResources().iterator().next().getContents().get(0);
        rootContext = new CommandContext(semantic.getEClassifiers().get(0), null);

        UserSession.from(session).selectOnlyViewpoint(EcoreModeler.DESIGN_VIEWPOINT_NAME);

        diagram = DiagramFactory.eINSTANCE.createDSemanticDiagram();
        diagram.setDescription(oDesign.group().viewpoint1().diagram1().object());

        Command addDiagramCmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                session.getSessionResource().getContents().add(diagram);
            }
        };
        domain.getCommandStack().execute(addDiagramCmd);
    }

    private AbstractOperationTask createViewTask(CommandContext context, DiagramElementMapping mapping, String container) {
        final CreateView createViewOp = ToolFactory.eINSTANCE.createCreateView();
        createViewOp.setContainerViewExpression(container);
        createViewOp.setMapping(mapping);
        return new CreateViewTask(context, accessor, createViewOp, SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(rootContext.getCurrentTarget()));
    }

    /**
     * Check nominal use case.
     * 
     * @throws Exception
     */
    public void testNominal() throws Exception {
        new Nominal().check(); // The context should change
    }

    /**
     * Check no container view expression use case.
     * 
     * @throws Exception
     */
    public void testNoContainerViewExpression() throws Exception {
        // new NoContainerViewExpression().check(null);
        // TODO VP-874
        // A business exception should be returned instead of a
        // ClassCastException
        new NoContainerViewExpressionNull().check();
    }

    /**
     * Check no container view expression use case.
     * 
     * @throws Exception
     */
    public void testNoContainerViewExpression2() throws Exception {
        // new NoContainerViewExpression().check("");
        // TODO VP-874
        // A business exception should be returned instead of a
        // ClassCastException
        new NoContainerViewExpressionEmpty().check();
    }

    /**
     * Check no container view expression use case.
     * 
     * @throws Exception
     */
    public void testNoContainerViewExpression3() throws Exception {
        try {
            new NoContainerViewExpression().check(" ");
        } catch (ClassCastException e) {
            // TODO VP-874 : A business exception should be returned instead of
            // a
            // ClassCastException
        }
    }

    /**
     * Check no container view expression use case.
     * 
     * @throws Exception
     */
    public void testBadContainerViewExpression() throws Exception {
        try {
            new NoContainerViewExpression().check("aql:self.eContainerOrSelf(ecore::EPackage)");
        } catch (ClassCastException e) {
            // TODO VP-874 : A business exception should be returned instead of
            // a
            // ClassCastException
        }
    }

    /**
     * Check no container view expression use case.
     * 
     * @throws Exception
     */
    public void testBadContainerViewExpression2() throws Exception {
        new NoContainerViewExpression().check("aql:self.eContainerOrSelf(ecore::EPackage)");
    }

    /**
     * Check no mapping use case.
     * 
     * @throws Exception
     */
    public void testNoMapping() throws Exception {
        new NoMapping().check(null);
    }

    @Override
    protected void tearDown() throws Exception {
        // session.getSemanticResources().iterator().next().delete(Collections.emptyMap());
        // session.getSessionResource().delete(Collections.emptyMap());
        session.close(new NullProgressMonitor());
        session = null;
        accessor = null;
        rootContext = null;
        oDesign = null;
        diagram = null;
        super.tearDown();
    }

}

class CreateViewOperationData {
    private Group oDesign;

    public void load() {
        ResourceSet set = new ResourceSetImpl();
        oDesign = (Group) set.getResource(URI.createPlatformPluginURI("/org.eclipse.sirius.tests.junit/data/unit/operations/createView.odesign", true), true).getContents().get(0);
    }

    public GroupCreateViewTask group() {
        return new GroupCreateViewTask(oDesign);
    }

}
