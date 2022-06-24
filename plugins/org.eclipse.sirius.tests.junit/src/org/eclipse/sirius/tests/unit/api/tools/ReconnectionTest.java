/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.api.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.tools.api.interpreter.IInterpreterSiriusDiagramVariables;
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Test edges reconnections.
 * 
 * @author fmorel
 */
public class ReconnectionTest extends DocbookTestCase {

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT1_SOURCE_NODE_NAME_CHAP1_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select(e | e.targetNode.target.oclIsTypeOf(docbook::Sect1) and e.sourceNode.name='chap1')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT1_SOURCE_NODE_NAME_CHAP0_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select(e | e.targetNode.target.oclIsTypeOf(docbook::Sect1) and e.sourceNode.name='chap0')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_NAME_CHAP1_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select(e | e.targetNode.target.oclIsTypeOf(docbook::Sect2) and e.sourceNode.name='chap1')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_NAME_CHAP0_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select(e | e.targetNode.target.oclIsTypeOf(docbook::Sect2) and e.sourceNode.name='chap0')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_TARGET_E_CLASS_NAME_SECT1_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select(e | e.sourceNode.target.oclIsTypeOf(docbook::Chapter) and e.targetNode.target.oclIsTypeOf(docbook::Sect1))->first()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select(e | e.sourceNode.target.oclIsTypeOf(docbook::Sect3) and e.targetNode.target.oclIsTypeOf(docbook::Chapter))->first()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_PARA_SOURCE_NODE_NAME_CHAP1_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select(e | e.targetNode.target.eClass().name = 'Para' and e.sourceNode.name = 'chap1')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_PARA_SOURCE_NODE_NAME_CHAP0_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select(e | e.targetNode.target.eClass().name = 'Para' and e.sourceNode.name = 'chap0')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select(e | e.sourceNode.target.eClass().name = 'Chapter' and e.targetNode.target.eClass().name = 'Sect2')->first()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Sect3' and e.targetNode.target.eClass().name = 'Chapter').targetNode->first()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Sect3' and e.targetNode.target.eClass().name = 'Chapter').targetNode->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Sect3' and e.targetNode.target.eClass().name = 'Sect2').targetNode->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Sect3' and e.targetNode.target.eClass().name = 'Sect2').targetNode->first()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Sect3' and e.targetNode.target.eClass().name = 'Sect2')->first()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT1_TARGET_NODE_NAME_CHAP0_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Sect1' and e.targetNode.name = 'chap0')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_NAME_CHAP1_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Sect2' and e.targetNode.name = 'chap1')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_NAME_CHAP0_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Sect2' and e.targetNode.name = 'chap0')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT1_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select( e | e.targetNode.target.eClass().name = 'Chapter' and e.sourceNode.target.eClass().name = 'Sect1')->first()";

    private static final String E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_12 = "aql:self.eAllContents(diagram::DNode)->select( e | e.target.eClass().name = 'Sect2')->at(2)";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select( e | e.targetNode.target.eClass().name = 'Sect3' and e.sourceNode.target.eClass().name = 'Chapter')->first()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_PARA_TARGET_NODE_NAME_CHAP1_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Para' and e.targetNode.name = 'chap1')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_PARA_TARGET_NODE_NAME_CHAP0_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Para' and e.targetNode.name = 'chap0')->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select( e | e.targetNode.target.eClass().name = 'Chapter' and e.sourceNode.target.eClass().name = 'Sect2')->first()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select( e | e.targetNode.target.eClass().name = 'Sect3' and e.sourceNode.target.eClass().name = 'Chapter').sourceNode->first()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select( e | e.targetNode.target.eClass().name = 'Sect3' and e.sourceNode.target.eClass().name = 'Chapter').sourceNode->size()";

    private static final String E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_1 = "aql:self.eAllContents(diagram::DNodeContainer)->select( e | e.target.eClass().name = 'Chapter')->at(2)";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_N_SIZE = "aql:self.eAllContents(diagram::DEdge)->select( e | e.targetNode.target.eClass().name = 'Sect3' and e.sourceNode.target.eClass().name = 'Sect2').sourceNode->size()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select( e | e.targetNode.target.eClass().name = 'Sect3' and e.sourceNode.target.eClass().name = 'Sect2').sourceNode->first()";

    private static final String E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0 = "aql:self.eAllContents(diagram::DEdge)->select( e | e.targetNode.target.eClass().name = 'Sect3' and e.sourceNode.target.eClass().name = 'Sect2')->first()";

    /**
     * test reconnection of edge source from DNode to DNode.
     */
    public void testReconnectSourceFromDNodeToDNode() {
        final EdgeTarget target = createTinySectionAndExtraMediumSection();
        final Command command;
        EObject edge = null;
        EObject source = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        try {
            source = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        assertTrue("source and target objet don't have expected type", (source instanceof EdgeTarget) && (edge instanceof DEdge));
        if (source instanceof EdgeTarget && edge instanceof DEdge) {
            command = reconnectEdgeSourceToNode(obviousDiagram, (DEdge) edge, (EdgeTarget) source, target);
        } else
            command = null;
        assertTrue("Could not reconnect edge source from node to node", execute(command));

        // check that target is effectively the new edge target element and the
        // only one.
        EObject effectiveTarget = null;
        int targetCount = 0;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong source count for the considered edge.", 1, targetCount);

        try {
            effectiveTarget = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        assertTrue("the edge source is not the expected one", effectiveTarget != null && EcoreUtil.equals(target, effectiveTarget));
    }

    /**
     * test reconnection of edge source from DNode to Container.
     */
    public void testReconnectSourceFromDNodeToContainer() {
        createTinySection();
        createMediumSection();
        final Command command;
        EObject edge = null;
        EObject target = null;
        EObject source = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        try {
            source = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        try {
            target = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_1);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject target.");
            e.printStackTrace();
        }

        assertTrue("source and target objet don't have expected type", (source instanceof EdgeTarget) && (target instanceof EdgeTarget) && (edge instanceof DEdge));
        if (source instanceof EdgeTarget && target instanceof EdgeTarget && edge instanceof DEdge) {
            command = reconnectEdgeSourceToContainer(obviousDiagram, (DEdge) edge, (EdgeTarget) source, (EdgeTarget) target);
        } else
            command = null;
        assertTrue("Could not reconnect edge source from node to container", execute(command));

        // check that target is effectively the new edge target element.
        EObject effectiveTarget = null;
        int targetCount = 0;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_N_SIZE)
                    .intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            effectiveTarget = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        assertTrue("the edge source is not the expected one", effectiveTarget != null && EcoreUtil.equals(target, effectiveTarget));
    }

    /**
     * test reconnection of edge source from DNode to BorderedNode.
     * 
     * Reconnect the source of an edge (mapping : mediumToChapter). The original source of the edge is the mediume
     * section (node in a container). The destination of the edge is a Para (Border NodeMapping chapNote). The new
     * mapping is chapterNoteToChapter.
     * 
     * 
     */
    public void testReconnectSourceFromDNodeToBorderedNode() {
        final Command command;
        final EdgeTarget source = (EdgeTarget) createMediumSection();
        final List<EObject> eltList = createChapterNote();
        final EdgeTarget target = (EdgeTarget) eltList.get(0);
        EObject edge = null;

        //
        // Gets the edge to reconnect.
        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        assertTrue("edge objet don't have expected type", edge instanceof DEdge);
        if (edge instanceof DEdge) {
            command = reconnectEdgeSourceToBorderedNode(obviousDiagram, (DEdge) edge, source, target);
        } else
            command = null;
        assertTrue("Could not reconnect edge source from node to bordered node", execute(command));

        // check that the note belongs now to the first chapter.
        // to do so, we check that one link is now going from note element to
        // the first chapter and
        // that no links are going from note element to the second chapter.

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_PARA_TARGET_NODE_NAME_CHAP0_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_PARA_TARGET_NODE_NAME_CHAP1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 0, targetCount);
    }

    /**
     * test reconnection of edge source from Container to DNode.
     */
    public void testReconnectSourceFromContainerToDNode() {
        createTinySection();
        createMediumSection();
        final Command command;
        EObject edge = null;
        EObject target = null;
        EObject source = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        try {
            source = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        try {
            target = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_12);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject target.");
            e.printStackTrace();
        }

        assertTrue("source and target objet don't have expected type", (source instanceof EdgeTarget) && (target instanceof EdgeTarget) && (edge instanceof DEdge));
        if (source instanceof EdgeTarget && target instanceof EdgeTarget && edge instanceof DEdge) {
            command = reconnectEdgeSourceToNode(obviousDiagram, (DEdge) edge, (EdgeTarget) source, (EdgeTarget) target);
        } else
            command = null;
        assertTrue("Could not reconnect edge source from node to container", execute(command));

        // check that target is effectively the new edge source element.
        EObject effectiveTarget = null;
        int targetCount = 0;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            effectiveTarget = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        assertTrue("the edge source is not the expected one", effectiveTarget != null && EcoreUtil.equals(target, effectiveTarget));
    }

    /**
     * test reconnection of edge source from Container to Container.
     */
    public void testReconnectSourceFromContainerToContainer() {
        createTinySection();
        createMediumSection();
        final Command command;
        EObject edge = null;
        EObject target = null;
        EObject source = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        try {
            source = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        try {
            target = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_1);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject target.");
            e.printStackTrace();
        }

        assertTrue("source and target objet don't have expected type", (source instanceof EdgeTarget) && (target instanceof EdgeTarget) && (edge instanceof DEdge));
        if (source instanceof EdgeTarget && target instanceof EdgeTarget && edge instanceof DEdge) {
            command = reconnectEdgeSourceToContainer(obviousDiagram, (DEdge) edge, (EdgeTarget) source, (EdgeTarget) target);
        } else
            command = null;
        assertTrue("Could not reconnect edge source from node to container", execute(command));

        // check that target is effectively the new edge source element.
        EObject effectiveTarget = null;
        int targetCount = 0;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_N_SIZE)
                    .intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            effectiveTarget = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        assertTrue("the edge source is not the expected one", effectiveTarget != null && EcoreUtil.equals(target, effectiveTarget));
    }

    /**
     * test reconnection of edge source from Container to BorderedNode.
     */
    public void testReconnectSourceFromContainerToBorderedNode() {
        final Command command;
        final DNodeContainer source = createBigSection();
        final List<EObject> eltList = createChapterNote();
        final DNode target = (DNode) eltList.get(0);
        EObject edge = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT1_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        assertTrue("edge objet don't have expected type", edge instanceof DEdge);
        if (edge instanceof DEdge) {
            command = reconnectEdgeSourceToBorderedNode(obviousDiagram, (DEdge) edge, (EdgeTarget) source, (EdgeTarget) target);
        } else
            command = null;
        assertTrue("Could not reconnect edge source from container to bordered node", execute(command));

        // check that the note belongs now to the first chapter.
        // to do so, we check that one link is now going from note element to
        // the first chapter and
        // that no links are going from note element to the second chapter.

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_PARA_TARGET_NODE_NAME_CHAP0_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_PARA_TARGET_NODE_NAME_CHAP1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 0, targetCount);
    }

    /**
     * test reconnection of edge source from borderedNode to DNode.
     */
    public void testReconnectSourceFromBorderedNodeToDNode() {
        Command command;
        final List<EObject> eltList = createChapterNote();
        final DNode target = createMediumSection();
        final DNode source = (DNode) eltList.get(0);
        final DEdge edge = (DEdge) eltList.get(1);

        // add a big section un the first created chapter.
        command = createBigSectionCommand(obviousDiagram, (DNodeContainer) eltList.get(3));
        assertTrue("Could not create container.", execute(command));

        command = reconnectEdgeSourceFromBorderedNodeToDNode(obviousDiagram, edge, source, target);
        assertTrue("Could not reconnect edge source from bordered node to bordered node", execute(command));

        // check that the medium section belongs now to the first chapter.
        // to do so, we check that one link is now going from medium section
        // element to the first container and
        // that no links are going from medium section type element to the
        // second container.

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_NAME_CHAP0_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_NAME_CHAP1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 0, targetCount);
    }

    /**
     * test reconnection of edge source from borderedNode to Container.
     */
    public void testReconnectSourceFromBorderedNodeToContainer() {
        final Command command;
        final List<EObject> eltList = createChapterNote();
        final DNodeContainer target = createBigSection();
        final DNode source = (DNode) eltList.get(0);
        final DEdge edge = (DEdge) eltList.get(1);

        command = reconnectEdgeSourceFromBorderedNodeToContainer(obviousDiagram, edge, source, target);
        assertTrue("Could not reconnect edge source from bordered node to bordered node", execute(command));

        // check that the big section belongs now to the first chapter.
        // to do so, we check that one link is now going from big section
        // element to the first container and
        // that no links are going from big section type element to the second
        // container.

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT1_TARGET_NODE_NAME_CHAP0_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            targetCount = INTERPRETER
                    .evaluateInteger(obviousDiagram, "aql:self.eAllContents(diagram::DEdge)->select( e | e.sourceNode.target.eClass().name = 'Sect1' and e.targetNode.name = 'chap1')->size()")
                    .intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 0, targetCount);
    }

    /**
     * test reconnection of edge source from borderedNode to BorderedNode.
     */
    public void testReconnectSourceFromBorderedNodeToBorderedNode() {
        final Command command;
        final List<EObject> firstList = createChapterNote();
        final List<EObject> secondList = createChapterNote();
        final DNode source = (DNode) firstList.get(0);
        final DEdge edge = (DEdge) firstList.get(1);
        final DNode target = (DNode) secondList.get(0);

        command = reconnectEdgeSourceToBorderedNode(obviousDiagram, edge, source, target);
        assertTrue("Could not reconnect edge source from bordered node to bordered node", execute(command));

        // check that the note belongs now to the first chapter.
        // to do so, we check that two links are now going from Para element to
        // the first container and
        // that no links are going from Para type element to the second
        // container.

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_PARA_TARGET_NODE_NAME_CHAP0_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 2, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_PARA_TARGET_NODE_NAME_CHAP1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 0, targetCount);
    }

    /**
     * test reconnection of edge Target from DNode to DNode.
     */
    public void testReconnectTargetFromDNodeToDNode() {
        final EdgeTarget target = createTinySectionAndExtraMediumSection();
        final Command command;
        EObject edge = null;
        EObject source = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        try {
            source = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        assertTrue("source and target objet don't have expected type", (source instanceof EdgeTarget) && (edge instanceof DEdge));
        if (source instanceof EdgeTarget && edge instanceof DEdge) {
            command = reconnectEdgeTargetToNode(obviousDiagram, (DEdge) edge, (EdgeTarget) source, target);
        } else
            command = null;
        assertTrue("Could not reconnect edge target from node to node", execute(command));

        // check that target is effectively the new edge target element and the
        // only one.
        EObject effectiveTarget = null;
        int targetCount = 0;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            effectiveTarget = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        assertTrue("the edge target is not the expected one", effectiveTarget != null && EcoreUtil.equals(target, effectiveTarget));
    }

    /**
     * test reconnection of edge Target from DNode to Container.
     */
    public void testReconnectTargetFromDNodeToContainer() {
        createTinySection();
        createMediumSection();
        final Command command;
        EObject edge = null;
        EObject target = null;
        EObject source = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        try {
            source = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        try {
            target = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_1);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject target.");
            e.printStackTrace();
        }

        assertTrue("source and target objet don't have expected type", (source instanceof EdgeTarget) && (target instanceof EdgeTarget) && (edge instanceof DEdge));
        if (source instanceof EdgeTarget && target instanceof EdgeTarget && edge instanceof DEdge) {
            command = reconnectEdgeTargetToContainer(obviousDiagram, (DEdge) edge, (EdgeTarget) source, (EdgeTarget) target);
        } else
            command = null;
        assertTrue("Could not reconnect edge target from node to container", execute(command));

        // check that target is effectively the new edge target element.
        EObject effectiveTarget = null;
        int targetCount = 0;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_N_SIZE)
                    .intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            effectiveTarget = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        assertTrue("the edge target is not the expected one", effectiveTarget != null && EcoreUtil.equals(target, effectiveTarget));
    }

    /**
     * test reconnection of edge Target from DNode to BorderedNode.
     */
    public void testReconnectTargetFromDNodeToBorderedNode() {
        final Command command;
        final DNode source = createMediumSection();
        final List<EObject> eltList = createChapterNote();
        final DNode target = (DNode) eltList.get(0);
        EObject edge = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        assertTrue("edge objet don't have expected type", edge instanceof DEdge);
        if (edge instanceof DEdge) {
            command = reconnectEdgeTargetToBorderedNode(obviousDiagram, (DEdge) edge, (EdgeTarget) source, (EdgeTarget) target);
        } else
            command = null;
        assertTrue("Could not reconnect edge source from node to bordered node", execute(command));

        // check that the note belongs now to the first chapter.
        // to do so, we check that one link is now going from note element to
        // the first chapter and
        // that no links are going from note element to the second chapter.

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_PARA_SOURCE_NODE_NAME_CHAP0_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_PARA_SOURCE_NODE_NAME_CHAP1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 0, targetCount);
    }

    /**
     * test reconnection of edge Target from Container to DNode.
     */
    public void testReconnectTargetFromContainerToDNode() {
        createTinySection();
        createMediumSection();
        final Command command;
        EObject edge = null;
        EObject target = null;
        EObject source = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        try {
            source = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        try {
            target = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_12);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject target.");
            e.printStackTrace();
        }

        assertTrue("source and target objet don't have expected type", (source instanceof EdgeTarget) && (target instanceof EdgeTarget) && (edge instanceof DEdge));
        if (source instanceof EdgeTarget && target instanceof EdgeTarget && edge instanceof DEdge) {
            command = reconnectEdgeTargetToNode(obviousDiagram, (DEdge) edge, (EdgeTarget) source, (EdgeTarget) target);
        } else
            command = null;
        assertTrue("Could not reconnect edge target from node to container", execute(command));

        // check that target is effectively the new edge target element.
        EObject effectiveTarget = null;
        int targetCount = 0;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            effectiveTarget = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        assertTrue("the edge target is not the expected one", effectiveTarget != null && EcoreUtil.equals(target, effectiveTarget));
    }

    /**
     * test reconnection of edge Target from Container to Container.
     */
    public void testReconnectTargetFromContainerToContainer() {
        createTinySection();
        createMediumSection();
        final Command command;
        EObject edge = null;
        EObject target = null;
        EObject source = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        try {
            source = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        try {
            target = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_1);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject target.");
            e.printStackTrace();
        }

        assertTrue("source and target objet don't have expected type", (source instanceof EdgeTarget) && (target instanceof EdgeTarget) && (edge instanceof DEdge));
        if (source instanceof EdgeTarget && target instanceof EdgeTarget && edge instanceof DEdge) {
            command = reconnectEdgeTargetToContainer(obviousDiagram, (DEdge) edge, (EdgeTarget) source, (EdgeTarget) target);
        } else
            command = null;
        assertTrue("Could not reconnect edge target from node to container", execute(command));

        // check that target is effectively the new edge target element.
        EObject effectiveTarget = null;
        int targetCount = 0;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_N_SIZE)
                    .intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            effectiveTarget = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject source.");
            e.printStackTrace();
        }

        assertTrue("the edge target is not the expected one", effectiveTarget != null && EcoreUtil.equals(target, effectiveTarget));
    }

    /**
     * test reconnection of edge Target from Container to BorderedNode.
     */
    public void testReconnectTargetFromContainerToBorderedNode() {
        final Command command;
        final DNodeContainer source = createBigSection();
        final List<EObject> eltList = createChapterNote();
        final DNode target = (DNode) eltList.get(0);
        EObject edge = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_CHAPTER_TARGET_NODE_TARGET_E_CLASS_NAME_SECT1_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        assertTrue("edge objet don't have expected type", edge instanceof DEdge);
        if (edge instanceof DEdge) {
            command = reconnectEdgeTargetToBorderedNode(obviousDiagram, (DEdge) edge, (EdgeTarget) source, (EdgeTarget) target);
        } else
            command = null;
        assertTrue("Could not reconnect edge target from container to bordered node", execute(command));

        // check that the note belongs now to the first chapter.
        // to do so, we check that one link is now going from note element to
        // the first chapter and
        // that no links are going from note element to the second chapter.

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_PARA_SOURCE_NODE_NAME_CHAP0_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_PARA_SOURCE_NODE_NAME_CHAP1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 0, targetCount);
    }

    /**
     * test reconnection of edge Target from borderedNode to DNode.
     */
    public void testReconnectTargetFromBorderedNodeToDNode() {
        Command command;
        final List<EObject> eltList = createChapterNote();
        final DNode target = createMediumSection();
        final DNode source = (DNode) eltList.get(0);
        final DEdge edge = (DEdge) eltList.get(2);

        // add a big section un the first created chapter.
        command = createBigSectionCommand(obviousDiagram, (DNodeContainer) eltList.get(3));
        assertTrue("Could not create container.", execute(command));

        command = reconnectEdgeTargetFromBorderedNodeToDNode(obviousDiagram, edge, source, target);
        assertTrue("Could not reconnect edge target from bordered node to bordered node", execute(command));

        // check that the medium section belongs now to the first chapter.
        // to do so, we check that one link is now going toward medium section
        // element to the first container and
        // that no links are going toward medium section type element to the
        // second container.

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_NAME_CHAP0_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_NAME_CHAP1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 0, targetCount);
    }

    /**
     * test reconnection of edge Target from borderedNode to Container.
     */
    public void testReconnectTargetFromBorderedNodeToContainer() {
        final Command command;
        final List<EObject> eltList = createChapterNote();
        final DNodeContainer target = createBigSection();
        final DNode source = (DNode) eltList.get(0);
        final DEdge edge = (DEdge) eltList.get(2);

        command = reconnectEdgeTargetFromBorderedNodeToContainer(obviousDiagram, edge, source, target);
        assertTrue("Could not reconnect edge source from bordered node to bordered node", execute(command));

        // check that the big section belongs now to the first chapter.
        // to do so, we check that one link is now going from big section
        // element to the first container and
        // that no links are going from big section type element to the second
        // container.

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT1_SOURCE_NODE_NAME_CHAP0_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT1_SOURCE_NODE_NAME_CHAP1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 0, targetCount);
    }

    /**
     * test reconnection of edge Target from borderedNode to BorderedNode.
     */
    public void testReconnectTargetFromBorderedNodeToBorderedNode() {
        final Command command;
        final List<EObject> firstList = createChapterNote();
        final List<EObject> secondList = createChapterNote();
        final DNode source = (DNode) firstList.get(0);
        final DEdge edge = (DEdge) firstList.get(2);
        final DNode target = (DNode) secondList.get(0);

        command = reconnectEdgeTargetToBorderedNode(obviousDiagram, edge, source, target);
        assertTrue("Could not reconnect edge target from bordered node to bordered node", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        // check that the note belongs now to the first chapter.
        // to do so, we check that two links are now going toward Para element
        // to the first container and
        // that no links are going toward Para type element to the second
        // container.

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_PARA_SOURCE_NODE_NAME_CHAP0_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 2, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_PARA_SOURCE_NODE_NAME_CHAP1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong target count for the considered edge.", 0, targetCount);
    }

    /**
     * Check that the evaluation of a reconnect tool precondition containing variables edgeView and otherEnd, is
     * evaluated without errors (test done using a new API from TaskHelper used in SiriusGraphicalNodeEditPolicy).
     */
    public void testReconnectPreconditionEvaluation() {
        final EdgeTarget reconnectionTarget = createTinySectionAndExtraMediumSection();
        final Command command;
        EObject edge = null;
        EObject reconnectionSource = null;
        EObject otherEnd = null;

        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge:" + e.getMessage());
        }

        try {
            reconnectionSource = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT3_TARGET_NODE_TARGET_E_CLASS_NAME_SECT2_TARGET_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the existing graphical target of the edge:" + e.getMessage());
        }

        try {
            otherEnd = INTERPRETER.evaluateEObject(obviousDiagram, E_ALL_CONTENTS_D_EDGE_TARGET_NODE_TARGET_E_CLASS_NAME_SECT3_SOURCE_NODE_TARGET_E_CLASS_NAME_SECT2_SOURCE_NODE_N_GET_0);
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the existing graphical source of the edge:" + e.getMessage());
        }

        assertTrue("source and target objet don't have expected type", (reconnectionSource instanceof EdgeTarget) && (edge instanceof DEdge) && (reconnectionSource instanceof DSemanticDecorator));
        Map<String, EObject> variables = new HashMap<>();
        variables.put(IInterpreterSiriusVariables.DIAGRAM, obviousDiagram);
        variables.put(IInterpreterSiriusVariables.SOURCE, ((DSemanticDecorator) reconnectionSource).getTarget());
        variables.put(IInterpreterSiriusVariables.SOURCE_VIEW, reconnectionSource);
        variables.put(IInterpreterSiriusVariables.TARGET, ((DSemanticDecorator) reconnectionTarget).getTarget());
        variables.put(IInterpreterSiriusVariables.TARGET_VIEW, reconnectionTarget);
        variables.put(IInterpreterSiriusVariables.ELEMENT, ((DEdge) edge).getTarget());
        variables.put(IInterpreterSiriusDiagramVariables.EDGE_VIEW_VARIABLE_NAME, edge);
        variables.put(IInterpreterSiriusDiagramVariables.OTHER_END_VARIABLE_NAME, otherEnd);

        final ReconnectEdgeDescription reconnectTool = (ReconnectEdgeDescription) getTool(obviousDiagram, "medium section target reconnection");
        assertNotNull("Could not find the tool to reconnect edge target to a node.", reconnectTool);

        boolean preconditionResult = TaskHelper.checkPrecondition(((DSemanticDecorator) edge).getTarget(), reconnectTool, variables, false, false);
        assertTrue("The precondition must be evaluated to true.", preconditionResult);
    }
}
