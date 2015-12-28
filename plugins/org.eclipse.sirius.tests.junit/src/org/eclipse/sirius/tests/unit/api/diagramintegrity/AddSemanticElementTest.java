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

public class AddSemanticElementTest extends DiagramIntegrityTestCase {

    /*
     * Check that the corresponding container is added if it's target is added
     * in the semantic model.
     */
    public void testAddingSemanticElementAddsContainer() {
        int eltCount = -1;

        // create a diagram based on my semantic model (simple.docbook)
        myRepresentation = createRepresentation("obviousDiagram");

        // refresh the current representation.
        refreshRepresentation();

        // check that there is one container representing the chapter in the
        // diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNodeContainer)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 1, eltCount);

        // check that there is no edges in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The element has not been removed.", 0, eltCount);

        // add a new chapter.
        addChapter();

        // refresh the current representation.
        refreshRepresentation();

        // check that there is 2 containers representing the 2 chapters in the
        // diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNodeContainer)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The element has not been removed.", 2, eltCount);

        // check that there is 1 edge between both chapters in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The element has not been removed.", 1, eltCount);
    }

    /*
     * Check that the corresponding node is added if it's target is added in the
     * semantic model.
     */
    public void testAddingSemanticElementAddsNode() {
        int eltCount = -1;

        // create a diagram based on my semantic model (simple.docbook)
        myRepresentation = createRepresentation("evoluate view");

        // refresh the current representation.
        refreshRepresentation();

        // check that there is one node representing the chapter in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNode)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 1, eltCount);

        // add a new chapter.
        addChapter();

        // refresh the current representation.
        refreshRepresentation();

        // check that there are 2 nodes representing the 2 chapters in the
        // diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNode)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The node has not been added.", 2, eltCount);
    }

    /*
     * Check that the corresponding edge is added if it's target is added in the
     * semantic model.
     */
    public void testAddingSemanticElementAddsEdge() {
        int eltCount = -1;

        // add a chapter in the semantic model.
        addTinySection();

        activateViewpoint("docbook1");
        // create a diagram based on my semantic model (simple.docbook)
        myRepresentation = createRepresentation("evoluate view");

        // refresh the current representation.
        refreshRepresentation();

        // check that there are 4 nodes in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNode)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 4, eltCount);

        // check that there are 3 edges in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 3, eltCount);

        // add a note chapter.
        addNote();

        // refresh the current representation.
        refreshRepresentation();

        // check that there are 4 edges in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The edge has not been added.", 4, eltCount);

        // check that there are 5 nodes in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNode)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The node has not been added.", 5, eltCount);
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
