/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.business.internal.helper.task.operations.ChangeContextTask;
import org.eclipse.sirius.business.internal.helper.task.operations.CreateInstanceTask;
import org.eclipse.sirius.business.internal.helper.task.operations.ForTask;
import org.eclipse.sirius.business.internal.helper.task.operations.IfTask;
import org.eclipse.sirius.business.internal.helper.task.operations.LetTask;
import org.eclipse.sirius.business.internal.helper.task.operations.MoveElementTask;
import org.eclipse.sirius.business.internal.helper.task.operations.RemoveElementTask;
import org.eclipse.sirius.business.internal.helper.task.operations.SetValueTask;
import org.eclipse.sirius.business.internal.helper.task.operations.SwitchTask;
import org.eclipse.sirius.business.internal.helper.task.operations.UnsetTask;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.helper.task.operations.CreateViewTask;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.CreateEdgeView;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tests.sample.docbook.DocbookFactory;
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.viewpoint.description.tool.Case;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.Default;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.If;
import org.eclipse.sirius.viewpoint.description.tool.Let;
import org.eclipse.sirius.viewpoint.description.tool.MoveElement;
import org.eclipse.sirius.viewpoint.description.tool.RemoveElement;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.Unset;

/**
 * Test elementary operations.
 * 
 * @author fmorel
 * 
 */
public class OperationTest extends DocbookTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Iterator<Resource> semanticResources = session.getSemanticResources().iterator();
        semanticResources.next();
        semanticModel = semanticResources.next().getContents().get(0);
    }

    public AbstractOperationTask createSect1InstanceTask(CommandContext context) {
        final CreateInstance createOp = ToolFactory.eINSTANCE.createCreateInstance();

        // definition and execution of the create instance operation.
        createOp.setReferenceName("sect1");
        createOp.setTypeName("Sect1");
        return new CreateInstanceTask(context, accessor, createOp, INTERPRETER);
    }

    public AbstractOperationTask createChapterInstanceTask() {
        final CreateInstance createOp = ToolFactory.eINSTANCE.createCreateInstance();
        EObject book = null;

        // definition of a new context with Book as first and last element.
        try {
            book = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject book.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", book);
        CommandContext context = new CommandContext(book, null);

        // definition and execution of the create instance operation.
        createOp.setReferenceName("chapter");
        createOp.setTypeName("Chapter");
        return new CreateInstanceTask(context, accessor, createOp, INTERPRETER);
    }

    /**
     * Check that createInstance operation creates the wanted instance in the semantic model.
     * 
     * @throws Exception
     * @throws FeatureNotFoundException
     */
    public void testCreateInstanceOperation() {
        int instanceCount = -1;
        EObject chapter = null;

        // definition of a new context with chapter as first and last element.
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);
        CommandContext context = new CommandContext(chapter, null);

        final AbstractOperationTask task = createSect1InstanceTask(context);

        // check that there is no instance of sect1 in the model.

        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Sect1)->size()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element before the create instance execution.", 0, instanceCount);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    task.execute();
                } catch (MetaClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FeatureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // check that one instance of sect1 as been created.

        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Sect1)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Create instance task failed to create an element.", 1, instanceCount);
    }

    /**
     * Check that the changeContextOperation effectively push the wanted element into the current context.
     */
    public void testChangeContextOperation() {
        final ChangeContext op = ToolFactory.eINSTANCE.createChangeContext();
        EObject book = null;

        // definition of a new context with book as first and last element.
        try {
            book = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject book.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model root element.", book);
        CommandContext context = new CommandContext(book, null);

        // definition and execution of the change context operation.
        String browseChapter = "aql:self.eContents()->first()";
        op.setBrowseExpression(browseChapter);
        final AbstractOperationTask task = new ChangeContextTask(context, accessor, op, INTERPRETER);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    task.execute();
                } catch (MetaClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FeatureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        EObject chapter = null;
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }

        EObject popElement = context.getCurrentTarget();

        // verification that the pushed element is a chapter.
        assertEquals("the change context operation did not push the right context.", chapter, popElement);
    }

    /**
     * Check that the SetValue operation set the selected feature of the current semantic element to the wanted value.
     */
    public void testSetValueOperation() {
        final SetValue op = ToolFactory.eINSTANCE.createSetValue();
        EObject chapter = null;
        int instanceCount = -1;
        op.setFeatureName("id");
        op.setValueExpression("newChapterID");

        // definition of a new context with chapter as first and last element.
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);
        CommandContext context = new CommandContext(chapter, null);

        final AbstractOperationTask task = new SetValueTask(context, accessor, op, session.getInterpreter());

        // check that there is no instance of chapter with an id equal to
        // "newChapterID" in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->select(c | c.id='newChapterID')->size()")
                    .intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element before the set value execution.", 0, instanceCount);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    task.execute();
                } catch (MetaClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FeatureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // check that there is one instance of chapter with an id equal to
        // "newChapterID" in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->select(c | c.id='newChapterID')->size()")
                    .intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element having the wanted value.", 1, instanceCount);
    }

    /**
     * Check that the SetObject operation adds an instance of wanted element in the current semantic element.
     */
    public void testSetObjectOperation() {
        final SetObject op = ToolFactory.eINSTANCE.createSetObject();
        final EObject chapter = DocbookFactory.eINSTANCE.createChapter();
        EObject book = null;
        int instanceCount = -1;
        op.setFeatureName("chapter");
        op.setObject(chapter);

        // definition of a new context with book as first and last element.
        try {
            book = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject book.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model root element.", book);
        CommandContext context = new CommandContext(book, null);

        final AbstractOperationTask task = new SetValueTask(context, accessor, op, session.getInterpreter());

        // check that there is only one instance of chapter in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element before the set value execution.", 1, instanceCount);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    task.execute();
                } catch (MetaClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FeatureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // check that there is now two instances of chapter in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element before the set value execution.", 2, instanceCount);
    }

    /**
     * Check that the Unset operation remove the the wanted reference from the selected element.
     */
    public void testUnsetOperation() {
        final Unset op = ToolFactory.eINSTANCE.createUnset();
        EObject book = null;
        int instanceCount = -1;
        op.setFeatureName("chapter");
        op.setElementExpression("aql:self.eContents()->first()");

        // definition of a new context with book as first and last element.
        try {
            book = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject book.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model root element.", book);
        CommandContext context = new CommandContext(book, null);

        final AbstractOperationTask task = new UnsetTask(context, accessor, op, session.getInterpreter());

        // check that there is one instance of chapter in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element before the set value execution.", 1, instanceCount);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    task.execute();
                } catch (MetaClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FeatureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // check that there is now no instance of chapter in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element having the wanted value.", 0, instanceCount);
    }

    /**
     * Check that the remove operation remove the the wanted reference from the model.
     */
    public void testRemoveOperation() {
        final RemoveElement op = ToolFactory.eINSTANCE.createRemoveElement();
        EObject chapter = null;
        int instanceCount = -1;

        // definition of a new context with chapter as first and last element.
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);
        CommandContext context = new CommandContext(chapter, null);

        final AbstractOperationTask task = new RemoveElementTask(context, accessor, op, session.getInterpreter());

        // check that there is one instance of chapter in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element before the set value execution.", 1, instanceCount);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    task.execute();
                } catch (MetaClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FeatureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // check that there is now no instance of chapter in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element having the wanted value.", 0, instanceCount);
    }

    /**
     * Check that the move operation move effectively an element from a reference to another reference.
     */
    public void testMoveOperation() {
        int instanceCount = -1;
        EObject chapter = null;

        // definition of a new context with chapter as first and last element.
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);
        CommandContext context = new CommandContext(chapter, null);

        final AbstractOperationTask sect1task = createSect1InstanceTask(context);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    sect1task.execute();
                } catch (MetaClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FeatureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        final AbstractOperationTask chapterTask = createChapterInstanceTask();
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    chapterTask.execute();
                } catch (MetaClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FeatureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // check that there is one sect1 in my book.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Sect1)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject sect1.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of sect1.", 1, instanceCount);

        // check that there are two chapters in my book.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of chapter.", 2, instanceCount);

        // check that there is no sect1 referenced under the second chapter.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->last().eContents()->filter(docbook::Sect1)->size()")
                    .intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of sect1 under the last chapter.", 0, instanceCount);

        EObject sect1 = null;

        // definition of a new context with sect1 as first and last element.
        try {
            sect1 = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Sect1)->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject sect1.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the wanted semantic model element.", sect1);
        CommandContext sect1context = new CommandContext(sect1, null);

        final MoveElement op = ToolFactory.eINSTANCE.createMoveElement();
        op.setFeatureName("sect1");
        op.setNewContainerExpression("aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->last()");

        final AbstractOperationTask task = new MoveElementTask(sect1context, accessor, op, session.getInterpreter());

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    task.execute();
                } catch (MetaClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FeatureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // check that there is now one sect1 referenced under the second
        // chapter.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->last().eContents()->filter(docbook::Sect1)->size()")
                    .intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of sect1 under the last chapter.", 1, instanceCount);
    }

    /**
     * Check that createView operation is able to create a DEdge view based on the wanted mapping in a diagram.
     */
    public void testCreateEdgeView() {
        EObject chapter = null;
        int instanceCount = -1;
        final DiagramElementMapping edgeMapping = getEdgeMappingFromName(obviousDiagram, "chapter");

        final DNodeContainer chapterView = createChapter();

        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);

        final EObject chap = chapter;

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                chapterView.setTarget(chap);
            }
        });

        final CreateEdgeView op = org.eclipse.sirius.diagram.description.tool.ToolFactory.eINSTANCE.createCreateEdgeView();
        String acceleoExp = "aql:self.eAllContents(diagram::DNodeContainer)->first().target";
        op.setMapping(edgeMapping);
        op.setSourceExpression(acceleoExp);
        op.setTargetExpression(acceleoExp);
        op.setContainerViewExpression("aql:self");

        assertNotNull("Not possible to catch the diagram.", obviousDiagram);
        CommandContext context = new CommandContext(obviousDiagram, null);

        final AbstractOperationTask task = new CreateViewTask(context, accessor, op, interpreter);

        // check that there is only one edge view before executing the
        // CreateViewTask.
        try {
            instanceCount = INTERPRETER.evaluateInteger(obviousDiagram, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the edge.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of DEdge in the obvious diagram.", 1, instanceCount);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    task.execute();
                } catch (MetaClassNotFoundException e) {
                    e.printStackTrace();
                } catch (FeatureNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // check that there is now two edge views.
        try {
            instanceCount = INTERPRETER.evaluateInteger(obviousDiagram, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the edge.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of DEdge in the obvious diagram.", 2, instanceCount);
    }

    /**
     * Check that a task under an if operation is executed if and only if the condition of the if is true.
     */
    public void testIFOperation() {
        testIf("aql:self.eClass().name = 'Chapter'", 0, 1);
    }

    /**
     * Check that an if condition expression is wrong an error is raised in Error Log view.
     */
    public void testIFOperationConditionError() {
        platformProblemsListener.setErrorCatchActive(true);
        testIf("var:ifConditionWithError", 0, 0);
        assertTrue(platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Check that a case under an switch operation is executed if and only if the condition of the case is true. Check
     * too, if there is 2 task with true condition expression, only the first check true is executed.
     */
    public void testSwitchOperation() {
        testSwitch("aql:self.eClass().name = 'Chapter'", 0, 1, 0, 0);
    }

    /**
     * Check that if never case match true condition, it's the default case that is executed.
     */
    public void testSwitchOperationDefault() {
        testSwitch("aql:self.eClass().name = 'UnexistingClassName'", 0, 0, 0, 1);
    }

    /**
     * Check that a condition expression in case is wrong, an error is raised in Error Log view.
     */
    public void testSwitchOperationConditionError() {
        platformProblemsListener.setErrorCatchActive(true);
        testSwitch("var:switchConditionWithError", 0, 0, 0, 1);
        assertTrue(platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Check that a condtion expression in case is null, it's default case that is executed.
     */
    public void testSwitchOperationConditionNull() {
        platformProblemsListener.setErrorCatchActive(true);
        testSwitch(null, 0, 0, 0, 1);
    }

    /**
     * Check that a new variable is properly created by the Let operation.
     */
    public void testLetOperation() {
        final Let let = ToolFactory.eINSTANCE.createLet();
        let.setVariableName("id");
        let.setValueExpression("aql:'IDENTIFIER'");

        final SetValue set = ToolFactory.eINSTANCE.createSetValue();
        set.setFeatureName("id");
        set.setValueExpression("var:id");

        let.getSubModelOperations().add(set);

        EObject chapter = null;

        // definition of a new context with chapter as first and last element.
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);

        // Check that the id of the chapter DOES NOT have the expected value yet
        String chapterName = "";
        try {
            chapterName = INTERPRETER.evaluateString(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first().id");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("chap0", chapterName);

        final CommandContext chapContext = new CommandContext(chapter, null);
        final AbstractOperationTask letTask = new LetTask(chapContext, accessor, let, session.getInterpreter());
        final AbstractOperationTask setTask = new SetValueTask(chapContext, accessor, set, session.getInterpreter());
        letTask.getChildrenTasks().add(setTask);
        SiriusCommand command = new SiriusCommand(session.getTransactionalEditingDomain());
        command.getTasks().add(letTask);

        assertTrue("Could not execute the command.", execute(command));

        // Check that the id of the chapter has been modified to the expected
        // value
        chapterName = "";
        try {
            chapterName = INTERPRETER.evaluateString(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first().id");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("IDENTIFIER", chapterName);
    }

    /**
     * Check that an existing variable (in this case, self) can be overridden by the Let operation.
     */
    public void testLetOperationOverrideSelf() {
        final Let let = ToolFactory.eINSTANCE.createLet();
        let.setVariableName("self");
        let.setValueExpression("aql:self.eContainer()");

        final SetValue set = ToolFactory.eINSTANCE.createSetValue();
        set.setFeatureName("id");
        set.setValueExpression("aql:'IDENTIFIER'");

        let.getSubModelOperations().add(set);

        EObject chapter = null;

        // definition of a new context with chapter as first and last element.
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);

        // Check that the id of the book DOES NOT have the expected value yet
        String chapterName = "";
        try {
            chapterName = INTERPRETER.evaluateString(semanticModel, "aql:self.eResource().getContents()->first().id");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("livre premier", chapterName);

        final CommandContext chapContext = new CommandContext(chapter, null);
        final AbstractOperationTask letTask = new LetTask(chapContext, accessor, let, session.getInterpreter());
        final AbstractOperationTask setTask = new SetValueTask(chapContext, accessor, set, session.getInterpreter());
        letTask.getChildrenTasks().add(setTask);
        SiriusCommand command = new SiriusCommand(session.getTransactionalEditingDomain());
        command.getTasks().add(letTask);

        assertTrue("Could not execute the command.", execute(command));

        // Check that the id of the chapter has NOT been modified
        chapterName = "";
        try {
            chapterName = INTERPRETER.evaluateString(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first().id");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("chap0", chapterName);

        // Check that the id of the book has been modified to the expected
        // value
        chapterName = "";
        try {
            chapterName = INTERPRETER.evaluateString(semanticModel, "aql:self.eResource().getContents()->first().id");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("IDENTIFIER", chapterName);
    }

    /**
     * Check that an existing variable can be overriden by a Let operation.
     */
    public void testLetOperationOverrideExistingVariable() {
        final Let let = ToolFactory.eINSTANCE.createLet();
        let.setVariableName("id");
        let.setValueExpression("aql:'IDENTIFIER'");

        final Let subLet = ToolFactory.eINSTANCE.createLet();
        subLet.setVariableName("id");
        subLet.setValueExpression("aql:'NEW_IDENTIFIER'");

        final SetValue set = ToolFactory.eINSTANCE.createSetValue();
        set.setFeatureName("id");
        set.setValueExpression("var:id");

        let.getSubModelOperations().add(subLet);
        subLet.getSubModelOperations().add(set);

        EObject chapter = null;

        // definition of a new context with chapter as first and last element.
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);

        // Check that the id of the chapter DOES NOT have the expected value yet
        String chapterName = "";
        try {
            chapterName = INTERPRETER.evaluateString(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first().id");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("chap0", chapterName);

        final CommandContext chapContext = new CommandContext(chapter, null);
        final AbstractOperationTask letTask = new LetTask(chapContext, accessor, let, session.getInterpreter());
        final AbstractOperationTask subLetTask = new LetTask(chapContext, accessor, subLet, session.getInterpreter());
        final AbstractOperationTask setTask = new SetValueTask(chapContext, accessor, set, session.getInterpreter());
        letTask.getChildrenTasks().add(subLetTask);
        subLetTask.getChildrenTasks().add(setTask);
        SiriusCommand command = new SiriusCommand(session.getTransactionalEditingDomain());
        command.getTasks().add(letTask);

        assertTrue("Could not execute the command.", execute(command));

        // Check that the id of the chapter has been modified to the expected
        // value
        chapterName = "";
        try {
            chapterName = INTERPRETER.evaluateString(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first().id");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("NEW_IDENTIFIER", chapterName);
    }

    public void testForOperation() {
        final For forop = ToolFactory.eINSTANCE.createFor();
        final SetValue setop = ToolFactory.eINSTANCE.createSetValue();
        EObject chapter = null;
        int instanceCount = -1;

        setop.setFeatureName("id");
        setop.setValueExpression("new id");
        forop.setExpression("aql:self.eContents()->filter(docbook::Sect1)");
        forop.setIteratorName("it");
        forop.getSubModelOperations().add(setop);

        // definition of a new context with chapter as first and last element.
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);

        DCommand cmd = new SiriusCommand(session.getTransactionalEditingDomain());

        for (int i = 0; i < 3; i++) {
            final CommandContext context = new CommandContext(chapter, null);
            final AbstractOperationTask task = createSect1InstanceTask(context);
            cmd.getTasks().add(task);
        }
        execute(cmd);

        // check that there is no instance of sect1 with an id equal to "new id"
        // in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Sect1)->select( s | s.id='new id')->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element having the wanted value.", 0, instanceCount);

        final CommandContext chapContext = new CommandContext(chapter, null);
        final AbstractOperationTask fortask = new ForTask(chapContext, accessor, forop, session.getInterpreter(), null);
        // final AbstractOperationTask settask = new SetValueTask(accessor,
        // chapContext, setop,
        // AcceleoUtil.getInterpreter(chapContext.getCurrentTarget()));
        SiriusCommand command = new SiriusCommand(session.getTransactionalEditingDomain());
        command.getTasks().add(fortask);

        // check that there is now 3 instances of sect1 in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Sect1)->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element.", 3, instanceCount);

        assertTrue("Could not execute the command.", execute(command));

        // check that there is now 3 instances of sect1 with an id equal to "new
        // id" in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Sect1)->select( s | s.id='new id')->size()").intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element having the wanted value.", 3, instanceCount);
    }

    /**
     * Test switch with parameters.
     * 
     * @param condition
     *            the condition for case.
     * @param valueBegin
     *            value (0 or 1) to know if switch child executed
     * @param valueCase1
     *            value (0 or 1) to know if case 1 executed
     * @param valueCase2
     *            value (0 or 1) to know if case 2 executed
     * @param valueDefault
     *            value (0 or 1) to know if default case executed
     */
    private void testIf(String condition, int valueBegin, int valueIf) {
        final SetValue op = ToolFactory.eINSTANCE.createSetValue();
        final If ifop = ToolFactory.eINSTANCE.createIf();
        int instanceCount = -1;

        op.setFeatureName("id");
        op.setValueExpression("newChapterID");

        EObject chapter = null;
        ifop.setConditionExpression(condition);

        // definition of a new context with chapter as first and last element.
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);
        CommandContext context = new CommandContext(chapter, null);

        AbstractOperationTask iftask = new IfTask(context, accessor, ifop, session.getInterpreter());
        final AbstractOperationTask task = new SetValueTask(context, accessor, op, session.getInterpreter());
        final AbstractOperationTask shallNotBeExecutedTask = new AbstractOperationTask(context, accessor, session.getInterpreter()) {

            @Override
            public void execute() {
                assertTrue(false);
            }

            @Override
            public String getLabel() {
                return null;
            }

        };

        iftask.getChildrenTasks().add(task);

        // check that there is no instance of chapter with an id equal to
        // "newChapterID" in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->select(c | c.id='newChapterID')->size()")
                    .intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element before the set value execution.", valueBegin, instanceCount);

        DCommand command = new SiriusCommand(session.getTransactionalEditingDomain());
        command.getTasks().add(iftask);
        assertTrue("Could not execute the command.", execute(command));

        // check that there is one instance of chapter with an id equal to
        // "newChapterID" in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->select(c | c.id='newChapterID')->size()")
                    .intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element having the wanted value.", valueIf, instanceCount);

        ifop.setConditionExpression("aql:self.eClass().name <> 'Chapter'");
        iftask = new IfTask(context, accessor, ifop, session.getInterpreter());
        iftask.getChildrenTasks().add(shallNotBeExecutedTask);
        command.getTasks().clear();
        command.getTasks().add(iftask);
        assertTrue("Could not execute the command.", execute(command));
    }

    /**
     * Test switch with parameters.
     * 
     * @param condition
     *            the condition for case.
     * @param valueBegin
     *            value (0 or 1) to know if switch child executed
     * @param valueCase1
     *            value (0 or 1) to know if case 1 executed
     * @param valueCase2
     *            value (0 or 1) to know if case 2 executed
     * @param valueDefault
     *            value (0 or 1) to know if default case executed
     */
    private void testSwitch(String condition, int valueBegin, int valueCase1, int valueCase2, int valueDefault) {

        String labelId1 = "newChapterID";

        String labelId2 = "newChapterID2";

        String labelIdDefault = "defaultChapterID";

        // The 2 model operation put in case.
        final SetValue op = ToolFactory.eINSTANCE.createSetValue();
        final SetValue op2 = ToolFactory.eINSTANCE.createSetValue();

        // The model operation put in default case.
        final SetValue opDefault = ToolFactory.eINSTANCE.createSetValue();

        final Switch switchOp = ToolFactory.eINSTANCE.createSwitch();
        final Case caseOp = ToolFactory.eINSTANCE.createCase();
        final Case caseOp2 = ToolFactory.eINSTANCE.createCase();
        final Default defaultOp = ToolFactory.eINSTANCE.createDefault();

        int instanceCount = -1;

        op.setFeatureName("id");
        op.setValueExpression(labelId1);

        op2.setFeatureName("id");
        op2.setValueExpression(labelId2);

        opDefault.setFeatureName("id");
        opDefault.setValueExpression(labelIdDefault);

        EObject chapter = null;
        caseOp.getSubModelOperations().add(op);
        // This condition is true.
        caseOp.setConditionExpression(condition);
        caseOp2.getSubModelOperations().add(op2);
        // This condition is true too.
        caseOp2.setConditionExpression(condition);
        defaultOp.getSubModelOperations().add(opDefault);
        switchOp.getCases().add(caseOp);
        switchOp.getCases().add(caseOp2);
        switchOp.setDefault(defaultOp);

        // definition of a new context with chapter as first and last element.
        try {
            chapter = INTERPRETER.evaluateEObject(semanticModel, "aql:self.eResource().getContents()->first().eContents()->first()");
        } catch (EvaluationException e) {
            fail("Exception while trying to get the eObject chapter.");
            e.printStackTrace();
        }
        assertNotNull("Not possible to catch the semantic model element.", chapter);
        CommandContext context = new CommandContext(chapter, null);

        AbstractOperationTask switchtask = new SwitchTask(context, accessor, switchOp, session, new NoUICallback());
        // check that there is no instance of chapter with an id equal to
        // "newChapterID" in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->select(c | c.id='" + labelId1 + "')->size()")
                    .intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element before the set value execution.", valueBegin, instanceCount);

        DCommand command = new SiriusCommand(session.getTransactionalEditingDomain());
        command.getTasks().add(switchtask);
        assertTrue("Could not execute the command.", execute(command));

        // check that there is one instance of chapter with an id equal to
        // "newChapterID" in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->select(c | c.id='" + labelId1 + "')->size()")
                    .intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element having the wanted value.", valueCase1, instanceCount);

        // check that there is no instance of chapter with an id equal to
        // "newChapterID2" in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->select(c | c.id='" + labelId2 + "')->size()")
                    .intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element having the wanted value.", valueCase2, instanceCount);

        // check that there is no instance of chapter with an id equal to
        // "defaultChapterID" in the model.
        try {
            instanceCount = INTERPRETER.evaluateInteger(semanticModel, "aql:self.eResource().getContents()->first().eAllContents(docbook::Chapter)->select(c | c.id='" + labelIdDefault + "')->size()")
                    .intValue();
        } catch (EvaluationException e) {
            fail("Exception while trying to get the instance count.");
            e.printStackTrace();
        }
        assertEquals("Wrong count of element having the wanted value.", valueDefault, instanceCount);
    }

}
