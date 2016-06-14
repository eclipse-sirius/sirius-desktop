/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.tools;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;

/**
 * Test object deletion.
 * 
 * @author fmorel
 */
public class DeleteTest extends DocbookTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    }

    public void testDeleteFromRedefinedTool() {
        List<EObject> createdElts = createNoteInEvoluateView();
        DDiagramElement element = (DDiagramElement) createdElts.get(0);

        Command command = createDiagramThrowNodeNavigationLinkCommand(evoluateDiagram, (DNode) createdElts.get(2));
        assertTrue("Could not create diagram throw node navigation link", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        Collection<DView> ownedViews = session.getOwnedViews();
        for (DView dView : ownedViews) {
            List<DRepresentation> loadedRepresentations = new DViewQuery(dView).getLoadedRepresentations();
            for (DRepresentation dRepresentation : loadedRepresentations) {
                refreshRepresentation(dRepresentation);
            }
        }

        // before applying the tool that delete tiny section and every "Para"
        // type element
        // just under tiny section's chapter, check that there are two graphical
        // elements
        // related to the only "Para" type element in the evoluate diagram and
        // one in the
        // obvious diagram.

        checkModel(1, 2, 1);

        command = getCommandFactory().buildDeleteDiagramElement(element);
        assertTrue("Could not delete the node using the re-defined", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        // check that every graphical element associated to the "Para" type
        // element have been removed.
        // See auto refresh and delete tool behavior in
        // org.eclipse.sirius.tools.internal.command.builders.DeletionCommandBuilder
        checkModel(0, 0, 0);
    }

    public void testDeleteFromDefaultTool() {
        List<EObject> createdElts = createNoteInEvoluateView();
        DDiagramElement element = (DDiagramElement) createdElts.get(1);

        Command command = createDiagramThrowNodeNavigationLinkCommand(evoluateDiagram, (DNode) createdElts.get(2));
        assertTrue("Could not create diagram throw node navigation link", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        refreshRepresentation(evoluateDiagram);
        refreshRepresentation(obviousDiagram);

        // before applying the tool that delete the note edge, check that there
        // are
        // two graphical elements related to the only "Para" type element.
        checkModel(1, 2, 1);

        command = getCommandFactory().buildDeleteDiagramElement(element);
        assertTrue("Could not delete the edge using the default tool", command.canExecute());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);

        // check that every graphical element associated to the "Para" type
        // element have been removed in impacted diagram, and that the other
        // diagram was not impacted.
        // See auto refresh and no delete tool behavior in
        // org.eclipse.sirius.tools.internal.command.builders.DeletionCommandBuilder
        checkModel(0, 0, 1);
    }

    private void checkModel(int paraSemanticNb, int evoDiagParaNb, int obviousDiagParaNb) {
        check(evoluateDiagram, "aql:self.target.eAllContents(docbook::Para)->size()", paraSemanticNb);
        check(evoluateDiagram, "aql:self.eAllContents().target->filter(docbook::Para)->size()", evoDiagParaNb);
        check(obviousDiagram, "aql:self.eAllContents(diagram::DNodeContainer).eAllContents().target->filter(docbook::Para)->size()", obviousDiagParaNb);
    }

    private void check(DDiagram context, String expression, int expected) {
        int targetCount = -1;
        try {
            targetCount = INTERPRETER.evaluateInteger(context, expression).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals("Wrong element count having the right type.", expected, targetCount);
    }
}
