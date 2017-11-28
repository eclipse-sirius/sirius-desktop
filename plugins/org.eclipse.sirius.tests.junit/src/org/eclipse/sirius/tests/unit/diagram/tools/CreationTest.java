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
package org.eclipse.sirius.tests.unit.diagram.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.tools.internal.command.builders.AbstractDiagramCommandBuilder;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Test object creation.
 * 
 * @author fmorel
 */
public class CreationTest extends DocbookTestCase {

    public void testAvailableVariablesOnPrecondition() {
        TestCommandBuilder testBuilder = new TestCommandBuilder();
        AbstractToolDescription tool = ToolFactory.eINSTANCE.createNodeCreationDescription();
        tool.setPrecondition("aql:" +  IInterpreterSiriusVariables.CONTAINER + "<> null");
        assertTrue(testBuilder.testPrecondition(obviousDiagram, tool));

        tool.setPrecondition("aql:" + IInterpreterSiriusVariables.CONTAINER_VIEW + "<> null");
        assertTrue(testBuilder.testPrecondition(obviousDiagram, tool));
    }

    private class TestCommandBuilder extends AbstractDiagramCommandBuilder {

        public Command buildCommand() {
            return null;
        }

        public boolean testPrecondition(final DDiagram diagram, final AbstractToolDescription AbstractToolDescription) {
            return checkPrecondition(diagram, AbstractToolDescription);
        }

        protected String getEnclosingCommandLabel() {
            return "";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Option<DDiagram> getDDiagram() {
            return Options.newNone();
        }
    }

    /**
     * Check that chapter creation creates one and only one DNodeContainer.
     */
    public void testCreateContainerInDiagram() {
        createChapter();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eAllContents(diagram::DNodeContainer)");
        } catch (final EvaluationException e) {
            fail("Exception while trying to fetch the created container nodes.");
        }
        containers.addAll(nodes);
        assertEquals("wrong number of DNodeContainer created", 1, containers.size());
    }

    /**
     * check that big section creation creates one and only one DNodeContainer
     * in a DNodeContainer.
     */
    public void testCreateContainerInContainer() {
        createBigSection();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eAllContents(diagram::DNodeContainer)->select( e | e.target.eClass().name ='Chapter').eContents()->select(e | e.eClass().name='DNodeContainer')");
        } catch (final EvaluationException e) {
            fail("Exception while trying to fetch the created container nodes.");
        }
        containers.addAll(nodes);
        assertEquals("wrong number of DNodeContainer created in a DNodeContainer", 1, containers.size());
    }

    /**
     * check that medium section creation creates one and only one DNode in
     * DNodeContainer.
     */
    public void testCreateNodeInContainer() {
        createMediumSection();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eAllContents(diagram::DNodeContainer).ownedDiagramElements->select( e | e.eClass().name='DNode')");
        } catch (final EvaluationException e) {
            fail(EXCEPTION_FETCH_NODE);
        }
        containers.addAll(nodes);
        assertEquals("wrong number of DNode created in a DNodeContainer", 1, containers.size());
    }

    /**
     * check that tiny section creation creates one and only one DNode in
     * Diagram.
     */
    public void testCreateNodeInDiagram() {
        createTinySection();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eContents()->select(e |e.eClass().name='DNode')");
        } catch (final EvaluationException e) {
            fail(EXCEPTION_FETCH_NODE);
        }
        containers.addAll(nodes);
        assertEquals("wrong number of DNode created in Diagram", 1, containers.size());
    }

    /**
     * Check that tiny note creation creates one and only one border Node(DNode)
     * in a DNode.
     */
    public void testCreateBorderNodeOnNode() {
        createTinyNote();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eAllContents(diagram::DNode).eAllContents(diagram::DNode)");
        } catch (final EvaluationException e) {
            fail(EXCEPTION_FETCH_NODE);
        }
        containers.addAll(nodes);
        assertEquals("wrong number of bordered Nodes created in Nodes", 1, containers.size());
    }

    /**
     * Check that chapter creation creates one and only one border Node (DNode)
     * on a DNodeContainer.
     */
    public void testCreateBorderNodeOnContainer() {
        createChapter();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eAllContents(diagram::DNodeContainer).ownedBorderedNodes->select(e | e.eClass().name='DNode')");
        } catch (final EvaluationException e) {
            fail(EXCEPTION_FETCH_NODE);
        }
        containers.addAll(nodes);
        assertEquals("wrong number of bordered node (DNode) created in a DNodeContainer", 1, containers.size());
    }

    /**
     * check that tiny section creation creates two and only two Edges between
     * two nodes.
     */
    public void testCreateEdgeBetweenTwoNodes() {
        createTinySection();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eAllContents(diagram::DEdge)->select(e | (e.sourceNode.eClass().name = 'DNode') and (e.sourceNode.eContainer().eClass().name <> 'DNode') and "
                    + "((e.sourceNode.eContainer().eClass().name <> 'DNodeContainer') or not(e.sourceNode.eContainer().ownedBorderedNodes->includes(e.sourceNode))) and "
                    + "(e.targetNode.eClass().name = 'DNode') and (e.targetNode.eContainer().eClass().name <> 'DNode') and "
                    + "((e.targetNode.eContainer().eClass().name <> 'DNodeContainer') or not(e.targetNode.eContainer().ownedBorderedNodes->includes(e.targetNode))))");
        } catch (final EvaluationException e) {
            fail(EXCEPTION_FETCH_EDGE);
        }
        containers.addAll(nodes);
        assertEquals("wrong number of DEdge between two nodes created in Diagram", 2, containers.size());
    }

    /**
     * check that chapter creation creates one and only one Edge between a
     * border node and a container. the title border node and the edge are
     * automatically created on chapter creation
     */
    public void testCreateEdgeBetweenBorderNodeAndContainer() {
        createChapter();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eAllContents(diagram::DEdge)->select( e | ((e.sourceNode.eClass().name = 'DNode') and ((e.sourceNode.eContainer().eClass().name = 'DNode') or "
                    + "(e.sourceNode.eContainer().ownedBorderedNodes->includes(e.sourceNode))) and (e.targetNode.eClass().name = 'DNodeContainer')) or "
                    + "((e.targetNode.eClass().name = 'DNode') and ((e.targetNode.eContainer().eClass().name = 'DNode') or "
                    + "(e.targetNode.eContainer().ownedBorderedNodes->includes(e.targetNode))) and (e.sourceNode.eClass().name = 'DNodeContainer')))");
        } catch (final EvaluationException e) {
            fail(EXCEPTION_FETCH_EDGE);
        }
        containers.addAll(nodes);
        assertEquals("wrong number of DEdge between border and container created in Diagram", 1, containers.size());
    }

    /**
     * check that creation of two chapters create one and only one Edge between
     * two containers. the edge is automatically created on chapters creation.
     */
    public void testCreateEdgeBetweenTwoContainers() {
        createTwoChapters();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eAllContents(diagram::DEdge)->select(e | (e.sourceNode.eClass().name = 'DNodeContainer') and (e.targetNode.eClass().name = 'DNodeContainer'))");
        } catch (final EvaluationException e) {
            fail(EXCEPTION_FETCH_EDGE);
        }
        containers.addAll(nodes);
        assertEquals("wrong number of DEdge between two containers", 1, containers.size());
    }

    /**
     * Check that creation of two tiny notes creates one and only one Edge
     * between two broder node. The edge is automatically created on tiny note
     * creation if a tiny note already exist.
     */
    public void testCreateEdgeBetweenTwoBorderNodes() {
        createTwoTinyNotes();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eAllContents(diagram::DEdge)->select( e | (e.sourceNode.eClass().name = 'DNode') and ((e.sourceNode.eContainer().eClass().name = 'DNode') or "
                    + "((e.sourceNode.eContainer().eClass().name = 'DNodeContainer') and (e.sourceNode.eContainer().ownedBorderedNodes->includes(e.sourceNode)))) and "
                    + "(e.targetNode.eClass().name = 'DNode') and ((e.targetNode.eContainer().eClass().name = 'DNode') or "
                    + "((e.targetNode.eContainer().eClass().name = 'DNodeContainer') and (e.targetNode.eContainer().ownedBorderedNodes->includes(e.targetNode)))))");
        } catch (final EvaluationException e) {
            fail(EXCEPTION_FETCH_EDGE);
        }
        containers.addAll(nodes);
        assertEquals("wrong number of DEdge between two border nodes created in Diagram", 1, containers.size());
    }

    /**
     * Check that Note creation creates one and only one Edge between a border
     * node and a node. The Edge is automatically created on chapter note
     * creation.
     */
    public void testCreateEdgeBetweenBorderNodeAndNode() {
        createNote();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER
                    .evaluateCollection(
                            obviousDiagram,

                            "aql:self.eAllContents(diagram::DEdge)->select( e | (((e.sourceNode.eClass().name = 'DNode' and e.sourceNode.eContainer().eClass().name = 'DNode') or (e.sourceNode.eContainer().eClass().name = 'DNodeContainer' and e.sourceNode.eContainer().ownedBorderedNodes->includes(e.sourceNode))) and e.targetNode.eClass().name = 'DNode' and e.targetNode.eContainer().eClass().name <> 'DNode' and (e.targetNode.eContainer().eClass().name <> 'DNodeContainer' or not (e.targetNode.eContainer().ownedBorderedNodes->includes(e.targetNode)))) or (((e.targetNode.eClass().name = 'DNode' and e.targetNode.eContainer().eClass().name = 'DNode') or (e.targetNode.eContainer().eClass().name = 'DNodeContainer' and e.targetNode.eContainer().ownedBorderedNodes->includes(e.targetNode))) and e.sourceNode.eClass().name = 'DNode' and e.sourceNode.eContainer().eClass().name <> 'DNode' and (e.sourceNode.eContainer().eClass().name <> 'DNodeContainer' or not(e.sourceNode.eContainer().ownedBorderedNodes->includes(e.sourceNode)))))");
        } catch (final EvaluationException e) {
            fail(EXCEPTION_FETCH_EDGE);
        }
        containers.addAll(nodes);
        assertEquals("wrong number of DEdge between nodes and border node created in Diagram", 1, containers.size());
    }

    /**
     * Check that medium section creation creates two and only two Edge between
     * a node and a node container. The Edges are automatically created on
     * medium section creation.
     */
    public void testCreateEdgeBetweenNodeAndContainer() {
        createMediumSection();
        final List<EObject> containers = new ArrayList<>();
        Collection<EObject> nodes = null;
        try {
            nodes = INTERPRETER.evaluateCollection(obviousDiagram, "aql:self.eAllContents(diagram::DEdge)->select( e | (((e.sourceNode.eClass().name = 'DNode') and not(e.sourceNode.eContainer().eClass().name = 'DNode') and "
                    + "(not(e.sourceNode.eContainer().eClass().name = 'DNodeContainer') or not(e.sourceNode.eContainer().ownedBorderedNodes->includes(e.sourceNode)))) and "
                    + "(e.targetNode.eClass().name = 'DNodeContainer')) or (((e.targetNode.eClass().name = 'DNode') and not(e.targetNode.eContainer().eClass().name = 'DNode') "
                    + "and (not(e.targetNode.eContainer().eClass().name = 'DNodeContainer') or not(e.targetNode.eContainer().ownedBorderedNodes->includes(e.targetNode)))) and "
                    + "(e.sourceNode.eClass().name = 'DNodeContainer')))");
        } catch (final EvaluationException e) {
            fail(EXCEPTION_FETCH_EDGE);
        }
        containers.addAll(nodes);
        assertEquals("wrong number of DEdge between nodes and border node created in Diagram", 2, containers.size());
    }


}
