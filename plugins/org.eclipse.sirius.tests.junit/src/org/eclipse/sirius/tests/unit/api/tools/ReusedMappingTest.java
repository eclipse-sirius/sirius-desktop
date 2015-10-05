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

import org.eclipse.emf.common.command.Command;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;

/**
 * Test re-use mappings.
 * 
 * @author fmorel
 */
public class ReusedMappingTest extends DocbookTestCase {

    /**
     * Test re-use of a container mapping. The container mapping "Title"
     * directly under the diagram is re-used in the container mapping "info".
     * This test verify that we are able to create an object "title" in a
     * container "info" and that the created "title" appears in the container
     * "info" and in the diagram.
     */
    public void testReuseContainerMapping() {
        final Command command;
        final DNodeContainer container = createInfoInEvoluateView();
        int targetCount = -1;
        command = createTitleCommandInInfoInEvoluateView(evoluateDiagram, container);

        // before executing the command, we check that there is no "title"
        // object in the diagram.
        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "aql:self.eAllContents()->select(e | e.target.eClass().name = 'Title')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong DNodeContainer count in the diagram.", 0, targetCount);

        assertTrue("Could not reuse container mapping in a container mapping", execute(command));

        // we verify that 1 and only one "title" object has been created
        // directly under the diagram.

        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "aql:self.eContents()->select(e | e.target.eClass().name = 'Title')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong DNodeContainer count in the diagram.", 1, targetCount);

        // we verify that 1 and only one "title" object has been created in the
        // "info" container.
        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "aql:self.eContents()->select(e | e.target.eClass().name = 'Info').eContents()->select(e | e.target.eClass().name = 'Title')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong DNodeContainer count in the container.", 1, targetCount);
    }

    /**
     * Test re-use of a node mapping. The node mapping "big sect" directly under
     * the diagram is re-used in the container mapping "info". This test verify
     * that we are able to create an object "big sect" in a container "info" and
     * that the created "big sect" appears in the container "info" and in the
     * diagram.
     */
    public void testReuseNodeMapping() {
        final Command command;
        final DNodeContainer container = createInfoInEvoluateView();
        int targetCount = -1;
        command = createBigSectCommandInInfoInEvoluateView(evoluateDiagram, container);

        // before executing the command, we check that there is no "big sect"
        // object in the diagram.
        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "aql:self.eAllContents()->select(e | e.target.eClass().name = 'Sect1')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong DNode count in the diagram.", 0, targetCount);

        assertTrue("Could not reuse container mapping in a container mapping", execute(command));

        // we verify that 1 and only one "big sect" object has been created
        // directly under the diagram.

        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "aql:self.eContents()->filter(diagram::DNode)->select(n | n.target.eClass().name = 'Sect1')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong DNode count in the diagram.", 1, targetCount);

        // we verify that 1 and only one "big sect" object has been created in
        // the "info" container.
        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "aql:self.eContents()->select(n | n.target.eClass().name = 'Info').eContents()->select(e | e.target.eClass().name= 'Sect1')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong DNode count in the container.", 1, targetCount);
    }

    /**
     * Test re-use of a bordered node mapping. The node mapping "author" defined
     * in border of the "info" container is re-used directly under the diagram.
     * This test verify that we are able to create an object "author" directly
     * in the diagram and that the created "author" appears in the container
     * "info" and in the diagram.
     */
    public void testReuseBorderedNodeMapping() {
        final Command command;
        int targetCount = -1;
        createInfoInEvoluateView();
        command = createAuthorCommandInEvoluateView(evoluateDiagram);

        // before executing the command, we check that there is no "author"
        // object in the diagram.
        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "aql:self.eAllContents()->select(e | e.target.eClass().name = 'Author')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong DNode count in the diagram.", 0, targetCount);

        assertTrue("Could not reuse the bordered node mapping in the diagram", execute(command));

        // we verify that 1 and only one "author" object has been created in
        // border of the "info" container.
        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "aql:self.eContents()->select(e | e.target.eClass().name = 'Info').eContents()->select(n | n.target.eClass().name = 'Author')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong DNode count in the container.", 1, targetCount);

        // we verify that 1 and only one "author" object has been created
        // directly under the diagram.
        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "aql:self.eContents()->filter(diagram::DNode)->select(n | n.target.eClass().name = 'Author')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong DNode count in the diagram.", 1, targetCount);
    }

}
