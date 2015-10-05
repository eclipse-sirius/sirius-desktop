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
package org.eclipse.sirius.tests.unit.api.tools;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;
import org.eclipse.sirius.tools.api.command.DCommand;

/**
 * Test drag and drop.
 * 
 * @author fmorel
 */
public class DragAndDropTest extends DocbookTestCase {

    private static final String QUERY_E_CONTENTS_E_CLASS_NAME_D_NODE_TARGET_E_CLASS_NAME_PARA_N_SIZE = "aql:self.eContents()->select( e | e.eClass().name = 'DNode').target->select(e | e.eClass().name = 'Para')->size()";
    private static final String QUERY_TARGET_E_ALL_CONTENTS_CHAPTER_N_LAST_E_ALL_CONTENTS_PARA_N_SIZE = "aql:self.target.eAllContents(docbook::Chapter)->last().eAllContents(docbook::Para)->size()";
    private static final String QUERY_TARGET_E_ALL_CONTENTS_CHAPTER_N_FIRST_E_ALL_CONTENTS_PARA_N_SIZE = "aql:self.target.eAllContents(docbook::Chapter)->first().eAllContents(docbook::Para)->size()";
    private static final String QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_1_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_PARA_N_SIZE = "aql:self.eAllContents(diagram::DNodeContainer)->select( e | e.target.eClass().name = 'Chapter')->at(2).eAllContents(diagram::DNode)->select( e | e.target.eClass().name = 'Para')->size()";
    private static final String QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_PARA_N_SIZE = "aql:self.eAllContents(diagram::DNodeContainer)->select( e | e.target.eClass().name = 'Chapter')->at(1).eAllContents(diagram::DNode)->select(e | e.target.eClass().name = 'Para')->size()";
    private static final String QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_1_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_SIZE = "aql:self.eAllContents(diagram::DNodeContainer)->select(e | e.target.eClass().name = 'Chapter')->at(2).eAllContents(diagram::DNode)->select(e | e.target.eClass().name = 'Sect2')->size()";
    private static final String QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_SIZE = "aql:self.eAllContents(diagram::DNodeContainer)->select(e | e.target.eClass().name = 'Chapter')->at(1).eAllContents(diagram::DNode)->select(e | e.target.eClass().name = 'Sect2')->size()";
    private static final String QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_SECT1_N_SIZE = "aql:self.eAllContents(diagram::DNodeContainer)->select(e | e.target.eClass().name = 'Chapter')->at(1).eAllContents(diagram::DNodeContainer)->select(e| e.target.eClass().name = 'Sect1')->size()";

    /**
     * test drag and drop of a container in a container.
     */
    public void testDragAndDropContainerInContainer() {
        final DCommand command;
        final DragAndDropTarget target = createChapter();
        final DDiagramElement element = createBigSection();

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram,
                    QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_SECT1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong DNodeContainer count inside the considered DNodeContainer.", 0, targetCount);

        command = (DCommand) dragAndDropContainerInsideContainer(obviousDiagram, target, element);
        assertTrue("Could not drag & drop a container in a container", execute(command));

        // check that the big section belongs now to the first chapter.
        // to do so, we check that one big section is inside the first chapter
        // and
        // that no big section is inside the second chapter.

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram,
                    QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_SECT1_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong DNodeContainer count inside the considered DNodeContainer.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram,
                    "aql:self.eAllContents(diagram::DNodeContainer)->select(e | e.target.eClass().name = 'Chapter')->at(2).eAllContents(diagram::DNodeContainer)->select(n | n.target.eClass().name = 'Sect1')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong DNodeContainer count inside the considered DNodeContainer.", 0, targetCount);
    }

    /**
     * test drag and drop of a node in a container.
     */
    public void testDragAndDropNodeInContainer() {
        final DCommand command;
        final DragAndDropTarget target = createBigSection();
        final DDiagramElement element = createMediumSection();

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram,
                    QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong DNode count inside the considered DNodeContainer.", 0, targetCount);

        command = (DCommand) dragAndDropNodeInsideContainer(obviousDiagram, target, element);
        assertTrue("Could not drag & drop a node in a container", execute(command));

        // check that the medium section belongs now to the first chapter.
        // to do so, we check that one medium section is inside the first
        // chapter and
        // that no medium section is inside the second chapter.

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram,
                    QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong DNode count inside the considered DNodeContainer.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram,
                    QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_1_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_SECT2_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong DNode count inside the considered DNodeContainer.", 0, targetCount);
    }

    /**
     * test drag and drop of a bordered node in a container.
     */
    public void testDragAndDropBorderedNodeInContainer() {
        final DCommand command;
        final DragAndDropTarget target = createChapter();
        List<EObject> createdElts = createChapterNote();
        final DDiagramElement element = (DDiagramElement) createdElts.get(0);

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram,
                    QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_PARA_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong bordered node count inside the considered DNodeContainer.", 0, targetCount);

        command = (DCommand) dragAndDropBorderedNodeInsideContainer(obviousDiagram, target, element);
        assertTrue("Could not drag & drop a node in a container", execute(command));

        // check that the medium section belongs now to the first chapter.
        // to do so, we check that one medium section is inside the first
        // chapter and
        // that no medium section is inside the second chapter.

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram,
                    QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_0_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_PARA_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong bordered node count inside the considered DNodeContainer.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram,
                    QUERY_E_ALL_CONTENTS_D_NODE_CONTAINER_TARGET_E_CLASS_NAME_CHAPTER_N_GET_1_E_ALL_CONTENTS_D_NODE_TARGET_E_CLASS_NAME_PARA_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong bordered node count inside the considered DNodeContainer.", 0, targetCount);
    }

    /**
     * test drag and drop of a bordered node in a node.
     */
    public void testDragAndDropBorderedNodeInNode() {
        final DCommand command;
        final DragAndDropTarget target = createTinySection();
        final DDiagramElement element = createTinyNote();

        int targetCount = -1;

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, QUERY_TARGET_E_ALL_CONTENTS_CHAPTER_N_FIRST_E_ALL_CONTENTS_PARA_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong bordered node count inside the considered DNode.", 0, targetCount);

        command = (DCommand) dragAndDropBorderedNodeInsideNode(obviousDiagram, target, element);
        assertTrue("Could not drag & drop a node in a container", execute(command));

        // check that the medium section belongs now to the first chapter.
        // to do so, we check that one medium section is inside the first
        // chapter and
        // that no medium section is inside the second chapter.

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, QUERY_TARGET_E_ALL_CONTENTS_CHAPTER_N_FIRST_E_ALL_CONTENTS_PARA_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong bordered node count inside the considered DNode.", 1, targetCount);

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, QUERY_TARGET_E_ALL_CONTENTS_CHAPTER_N_LAST_E_ALL_CONTENTS_PARA_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong bordered node count inside the considered DNode.", 0, targetCount);
    }

    /**
     * test drag and drop of a bordered node in the diagram.
     */
    public void testDragAndDropBorderedNodeInDiagram() {
        final DCommand command;
        final DDiagramElement element = createMediumSectionAndChapterNote();
        createChapterNote();

        int targetCount = -1;

        // check that no "Para" type element exist directly in the diagram
        // before command execution.

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, QUERY_E_CONTENTS_E_CLASS_NAME_D_NODE_TARGET_E_CLASS_NAME_PARA_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong bordered node count inside the considered DNode.", 0, targetCount);

        command = (DCommand) dragAndDropBorderedNodeInsideDiagram(obviousDiagram, obviousDiagram, element);
        assertTrue("Could not drag & drop a node in a container", execute(command));

        // check that one and only one "Para" type element exist now directly in
        // the diagram.

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, QUERY_E_CONTENTS_E_CLASS_NAME_D_NODE_TARGET_E_CLASS_NAME_PARA_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong bordered node count inside the considered DNode.", 1, targetCount);
    }
}
