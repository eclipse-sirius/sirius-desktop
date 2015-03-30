/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

public class DirectEditTest extends DocbookTestCase {

    /**
     * test label direct edit for a container.
     */
    public void testDirectEditContainerLabel() {
        int targetCount = -1;
        final Command command;
        final DRepresentationElement repElement = createBigSection();

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, "<%eAllContents(\"DNodeContainer\")[target.eClass.name == \"Sect1\" && target.id == \"new label\"].nSize()%>").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong container count having the right label name.", 0, targetCount);

        command = directEditNodeAndContainerLabel(obviousDiagram, repElement, "new label");
        assertTrue("Could not edit the label of a container", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        // check that the container label has changed to the new one.

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, "<%eAllContents(\"DNodeContainer\")[target.eClass.name == \"Sect1\" && target.id == \"new label\"].nSize()%>").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong container count having the right label name.", 1, targetCount);
    }

    /**
     * test label direct edit for a node.
     */
    public void testDirectEditNodeLabel() {
        int targetCount = -1;
        Command command = null;
        final DRepresentationElement repElement = createMediumSection();

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, "<%eAllContents(\"DNode\")[target.eClass.name == \"Sect2\" && target.id == \"new label\"].nSize()%>").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong node count having the right label name.", 0, targetCount);

        command = directEditNodeAndContainerLabel(obviousDiagram, repElement, "new label");
        assertTrue("Could not edit the label of a container", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        // check that the node label has changed to the new one.

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, "<%eAllContents(\"DNode\")[target.eClass.name == \"Sect2\" && target.id == \"new label\"].nSize()%>").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong node count having the right label name.", 1, targetCount);
    }

    /**
     * test label direct edit for a node.
     */
    public void testDirectEditBorderedNodeLabel() {
        int targetCount = -1;
        Command command = null;
        final DRepresentationElement repElement = createChapterReturnTitle();

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, "<%eAllContents(\"DNode\")[target.eClass.name == \"Title\" && target.data == \"new label\"].nSize()%>").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong node count having the right label name.", 0, targetCount);

        command = directEditBorderedNodeLabel(obviousDiagram, repElement, "new label");
        assertTrue("Could not edit the label of a container", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        // check that the node label has changed to the new one.

        try {
            targetCount = INTERPRETER.evaluateInteger(obviousDiagram, "<%eAllContents(\"DNode\")[target.eClass.name == \"Title\" && target.data == \"new label\"].nSize()%>").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong node count having the right label name.", 1, targetCount);
    }

    /**
     * test label direct edit for an edge.
     */
    public void testDirectEditEdgeLabel() {
        final Command command;
        int targetCount = -1;
        DRepresentationElement repElement = (DRepresentationElement) createNoteInEvoluateView().get(1);

        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "<%eAllContents(\"DEdge\")[target.eClass.name == \"Para\" && target.data == \"new label\"].nSize()%>").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong node count having the right label name.", 0, targetCount);

        command = directEditEdgeLabel(evoluateDiagram, repElement, "new label");
        assertTrue("Could not edit the label of an edge", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        // check that the edge label has changed to the new one.

        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, "<%eAllContents(\"DEdge\")[target.eClass.name == \"Para\" && target.data == \"new label\"].nSize()%>").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong edge count having the right label name.", 1, targetCount);
    }
}
