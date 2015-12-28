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
package org.eclipse.sirius.tests.unit.api.diagramintegrity;

import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

public class MoveSemanticElementTest extends DiagramIntegrityTestCase {

    /*
     * Check that the corresponding diagram is properly modified if an element
     * is moved in the semantic model.
     */
    public void testDiagramIntegrityWhenMovingSemanticElement() {
        int eltCount = -1;

        // build the semantic model with an element Para under the chapter.
        addTinySection();
        addNote();

        activateViewpoint("docbook1");
        // create a diagram based on my semantic model (simple.docbook)
        myRepresentation = createRepresentation("evoluate view");

        // refresh the current representation.
        refreshRepresentation();

        // check that there are 5 nodes in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNode)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 5, eltCount);

        // check that there are 4 edges in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 4, eltCount);

        // move the "Para" element under the big section.
        moveNoteUnderBigSection();

        // refresh the current representation.
        refreshRepresentation();

        // check that there are 3 edges in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The edge has not been removed.", 3, eltCount);

        // check that there are 4 nodes in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNode)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The node has not been removed.", 4, eltCount);
    }

    /*
     * check that the corresponding container is moved if it's target is moved
     * in the semantic model.
     */
    public void testMovingSemanticElementMovesContainer() {
        int eltCount = -1;

        // build the semantic model with an element Para under the chapter.
        addTinySection();
        addChapter();

        // create a diagram based on my semantic model (simple.docbook)
        myRepresentation = createRepresentation("obviousDiagram");

        // refresh the current representation.
        refreshRepresentation();

        // check that there are 2 elements under the first chapter.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation,
                    "aql:self.eContents()->filter(diagram::DNodeContainer)->select(e | e.target = e.target.eContainer().chapter->first()).eAllContents(diagram::DNodeContainer)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 1, eltCount);

        // check that there are no elements under the second (last) chapter.
        try {
            eltCount = INTERPRETER
                    .evaluateInteger(myRepresentation, "aql:self.eContents()->filter(diagram::DNodeContainer)->select(e | e.target = e.target.eContainer().chapter->last()).eAllContents(diagram::DNodeContainer)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 0, eltCount);

        // move the "Sect1" element under the last chapter.
        moveBigSectionUnderChapter();

        // refresh the current representation.
        refreshRepresentation();

        // check that there are no elements under the first chapter.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation,
                    "aql:self.eContents()->filter(diagram::DNodeContainer)->select(e | e.target = e.target.eContainer().chapter->first()).eAllContents(diagram::DNodeContainer)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("Wrong container count under the first container.", 0, eltCount);

        // check that there is one element under the second (last) chapter.
        try {
            eltCount = INTERPRETER
                    .evaluateInteger(myRepresentation, "aql:self.eContents()->filter(diagram::DNodeContainer)->select(e | e.target = e.target.eContainer().chapter->last()).eAllContents(diagram::DNodeContainer)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("Wrong container count under the last container.", 1, eltCount);
    }

    /*
     * check that the corresponding node is moved if it's target is moved in the
     * semantic model.
     */
    public void testMovingSemanticElementMovesNodes() {
        int eltCount = -1;

        // build the semantic model with an element Para under the chapter.
        addTinySection();
        addChapterAndBigSection();

        // create a diagram based on my semantic model (simple.docbook)
        myRepresentation = createRepresentation("obviousDiagram");

        // refresh the current representation.
        refreshRepresentation();

        // check that there is 1 element under the first big section.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eContents()->filter(diagram::DNodeContainer)->select(e | e.target = e.target.eContainer().chapter->first()).eAllContents(diagram::DNode)->size()")
                    .intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 1, eltCount);

        // check that there are no elements under the second (last) big section.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eContents()->filter(diagram::DNodeContainer)->select(e | e.target = e.target.eContainer().chapter->last()).eAllContents(diagram::DNode)->size()")
                    .intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 0, eltCount);

        // move the Medium section element under the last big section.
        moveMediumSectionUnderBigSection();

        // refresh the current representation.
        refreshRepresentation();

        // check that there are no elements under the first chapter.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eContents()->filter(diagram::DNodeContainer)->select(e | e.target = e.target.eContainer().chapter->first()).eAllContents(diagram::DNode)->size()")
                    .intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("Wrong node count under the first container.", 0, eltCount);

        // check that there are no elements under the second (last) chapter.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eContents()->filter(diagram::DNodeContainer)->select(e | e.target = e.target.eContainer().chapter->last()).eAllContents(diagram::DNode)->size()")
                    .intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("Wrong node count under the last container.", 1, eltCount);
    }

    /*
     * Refresh the diagram.
     */
    private void refreshRepresentation() {
        if (myRepresentation != null) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, myRepresentation));
        }
    }

}
