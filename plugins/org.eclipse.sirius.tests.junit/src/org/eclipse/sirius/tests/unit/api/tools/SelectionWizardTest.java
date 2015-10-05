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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

public class SelectionWizardTest extends DocbookTestCase {

    private static final String QUERY_E_ALL_CONTENTS_TARGET_DATA_DATA_VALUE_N_SIZE = "aql:self.eAllContents(viewpoint::DSemanticDecorator)->select( e | e.target.data = 'data value')->size()";

    /**
     * Test that a multiple selection is possible only if allowed. Test that a
     * single selection is always possible.
     */
    public void testSelectionWizardSingleSelection() {
        Command command = null;
        int targetCount = -1;
        List<EObject> selectedElements = Collections.emptyList();
        // Collection<EObject> selectedElements = null;
        createTitleInEvoluateView();

        /* retrieve elements from the diagram. */
        try {
            selectedElements = new ArrayList<EObject>(INTERPRETER.evaluateCollection(evoluateDiagram, "aql:self.eAllContents(viewpoint::DSemanticDecorator)->select(e | e.target.data->size() = 0)"));
        } catch (final EvaluationException e) {
            fail("Exception while trying to fetch the created chapter.");
        }

        if (evoluateDiagram instanceof DSemanticDecorator) {
            command = getSingleSelectionWizardCommand(evoluateDiagram, (DSemanticDecorator) evoluateDiagram, selectedElements);
        }
        assertTrue("Could not execute Selection wizard command.", execute(command));

        // check that the selection wizard tool apply just on the first element.

        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, QUERY_E_ALL_CONTENTS_TARGET_DATA_DATA_VALUE_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong element count.", 2, targetCount);

    }

    /**
     * Test that a selection wizard can be call on an edge. <BR>
     * Ticket #1617
     */
    public void testSelectionWizardOnEdge() {
        Command command = null;
        int targetCount = -1;
        List<EObject> selectedElements = Collections.emptyList();
        // Collection<EObject> selectedElements = null;
        createTitleInEvoluateView();

        /* retrieve elements from the diagram. */
        try {
            selectedElements = new ArrayList<EObject>(INTERPRETER.evaluateCollection(evoluateDiagram, "aql:self.eAllContents(viewpoint::DSemanticDecorator)->select(e | e.target.data->size() = 0)"));
        } catch (final EvaluationException e) {
            fail("Exception while trying to fetch the created chapter.");
        }

        EObject sect1 = null;
        try {
            sect1 = INTERPRETER.evaluateEObject(evoluateDiagram, "aql:self.eAllContents(diagram::DNode)->select(n | n.target.eClass().name='Sect1')->last()");
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject big section : " + e.getMessage());
        }

        if (sect1 instanceof DSemanticDecorator) {
            command = getSingleSelectionWizardCommand(evoluateDiagram, (DSemanticDecorator) sect1, selectedElements);
        }
        assertTrue("Could not execute Selection wizard command.", execute(command));

        // check that the selection wizard tool apply just on the first element.

        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, QUERY_E_ALL_CONTENTS_TARGET_DATA_DATA_VALUE_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong element count.", 2, targetCount);

    }

    /**
     * Test that possible choices match with expected result of acceleo
     * expression and that their type may be DNode, DNodeContainer or DEdge.
     */
    public void testSelectionWizardMultipleSelection() {
        Command command = null;
        int targetCount = -1;
        List<EObject> selectedElements = Collections.emptyList();
        createTitleInEvoluateView();

        /* retrieve elements from the diagram. */
        try {
            selectedElements = new ArrayList<EObject>(INTERPRETER.evaluateCollection(evoluateDiagram, "aql:self.eAllContents(viewpoint::DSemanticDecorator)->select(e | e.target.data->size() = 0)"));
        } catch (final EvaluationException e) {
            fail("Exception while trying to fetch the created chapter.");
        }

        if (evoluateDiagram instanceof DSemanticDecorator) {
            command = getMultipleSelectionWizardCommand(evoluateDiagram, (DSemanticDecorator) evoluateDiagram, selectedElements);
        }
        assertTrue("Could not execute Selection wizard command.", execute(command));

        /* check that the selection wizard tool apply on every elements. */

        try {
            targetCount = INTERPRETER.evaluateInteger(evoluateDiagram, QUERY_E_ALL_CONTENTS_TARGET_DATA_DATA_VALUE_N_SIZE).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }

        assertEquals("Wrong element count.", 3, targetCount);
    }

}
