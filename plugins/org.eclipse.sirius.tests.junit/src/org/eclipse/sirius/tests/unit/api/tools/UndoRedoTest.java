/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

public class UndoRedoTest extends DocbookTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    }

    public void testUndoRedoCreation() throws Exception {
        final Command command = createChapterCommand(obviousDiagram);

        // check that before chapter creation, obvious diagram is empty.
        String expression = "aql:self.eContents(diagram::DDiagramElement)->size()";
        String failureMessage = "Wrong element count.";
        check(obviousDiagram, expression, 0, failureMessage);

        assertTrue("Could not create container and edge.", execute(command));

        // check that after chapter creation, there are 2 elements in the
        // diagram.
        check(obviousDiagram, expression, 2, failureMessage);

        // check that after undo command, the diagram gets empty.
        assertTrue("The creation of a container and an edge couldn't be undone.", undo());

        check(obviousDiagram, expression, 0, failureMessage);

        // check that after redo command, the diagram is filled.
        assertTrue("The creation of a container and an edge couldn't be redone.", redo());

        check(obviousDiagram, expression, 2, failureMessage);
    }

    public void testUndoRedoReconnection() throws Exception {
        final Command command;
        final DNodeContainer source = createBigSection();
        final List<EObject> eltList = createChapterNote();
        final DNode target = (DNode) eltList.get(0);
        EObject edge = null;

        // check that before reconnection the note (Para) element is linked with
        // the second chapter (chap1)
        // and not with the first (chap0).
        String after = "aql:self.eAllContents(diagram::DEdge)->select(e | e.targetNode.target.eClass().name = 'Para' and e.sourceNode.name = 'chap0')->size()";
        String before = "aql:self.eAllContents(diagram::DEdge)->select(e | e.targetNode.target.eClass().name = 'Para' and e.sourceNode.name = 'chap1')->size()";

        String failureMessage = "Wrong target count for the considered edge.";
        check(obviousDiagram, after, 0, failureMessage);
        check(obviousDiagram, before, 1, failureMessage);

        // retrieve the edge to reconnect.
        try {
            edge = INTERPRETER.evaluateEObject(obviousDiagram,
                    "aql:self.eAllContents(diagram::DEdge)->select(e | e.sourceNode.target.eClass().name = 'Chapter' and e.targetNode.target.eClass().name = 'Sect1')->first()");
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the EObject edge.");
            e.printStackTrace();
        }

        assertTrue("edge objet don't have expected type", edge instanceof DEdge);
        if (edge instanceof DEdge) {
            command = reconnectEdgeTargetToBorderedNode(obviousDiagram, (DEdge) edge, (EdgeTarget) source, (EdgeTarget) target);
        } else {
            command = null;
        }
        assertTrue("Could not reconnect edge target from container to bordered node", execute(command));

        // check that the note belongs now to the first chapter.
        // to do so, we check that one link is now going from note element to
        // the first chapter and
        // that no links are going from note element to the second chapter.
        check(obviousDiagram, after, 1, failureMessage);
        check(obviousDiagram, before, 0, failureMessage);

        // undo the reconnection command and check that we came back in the
        // initial state.
        assertTrue("The reconnection couldn't be undone.", undo());

        check(obviousDiagram, after, 0, failureMessage);
        check(obviousDiagram, before, 1, failureMessage);

        // redo the reconnection command and check that we came back to the
        // final state.
        assertTrue("The reconnection couldn't be redone.", redo());

        check(obviousDiagram, after, 1, failureMessage);
        check(obviousDiagram, before, 0, failureMessage);
    }

    public void testUndoRedoDragAndDrop() throws Exception {
        final DCommand command;
        final DragAndDropTarget target = createChapter();
        final DDiagramElement element = createBigSection();

        // check that in the initial state, there is no container in the first
        // chapter.
        String after = "aql:self.eAllContents(diagram::DNodeContainer)->select(e | e.target.eClass().name = 'Chapter')->first().eAllContents(diagram::DNodeContainer)->select(n | n.target.eClass().name = 'Sect1')->size()";
        String before = "aql:self.eAllContents(diagram::DNodeContainer)->select(e | e.target.eClass().name = 'Chapter')->at(2).eAllContents(diagram::DNodeContainer)->select( n | n.target.eClass().name = 'Sect1')->size()";
        String failureMessage = "Wrong DNodeContainer count inside the considered DNodeContainer.";
        check(obviousDiagram, after, 0, failureMessage);
        check(obviousDiagram, before, 1, failureMessage);

        command = (DCommand) dragAndDropContainerInsideContainer(obviousDiagram, target, element);
        assertTrue("Could not drag & drop a container in a container", execute(command));

        // check that after the command, the big section belongs to the first
        // chapter.
        // to do so, we check that one big section is inside the first chapter
        // and
        // that no big section is inside the second chapter.
        check(obviousDiagram, after, 1, failureMessage);
        check(obviousDiagram, before, 0, failureMessage);

        // undo the drag and drop command and check that we come back to the
        // initial state.
        assertTrue("The drag and drop couldn't be undone.", undo());

        check(obviousDiagram, after, 0, failureMessage);
        check(obviousDiagram, before, 1, failureMessage);

        // redo the drag and drop command and check that we come back to the
        // final state.
        assertTrue("The drag and drop couldn't be redone.", redo());

        check(obviousDiagram, after, 1, failureMessage);
        check(obviousDiagram, before, 0, failureMessage);
    }

    public void testUndoRedoDirectEdit() throws Exception {
        final Command command;
        final DRepresentationElement repElement = createBigSection();

        // check that in the initial state, there is no "Sect1" element with the
        // label "new label".
        String expression = "aql:self.eAllContents(diagram::DNodeContainer)->select(e | e.target.eClass().name = 'Sect1' and e.target.id = 'new label')->size()";
        String failureMessage = "Wrong container count having the right label name.";
        check(obviousDiagram, expression, 0, failureMessage);

        command = directEditNodeAndContainerLabel(obviousDiagram, repElement, "new label");
        assertTrue("Could not edit the label of a container", execute(command));

        // check that the container label has changed to the new one.
        check(obviousDiagram, expression, 1, failureMessage);

        // undo the direct edit command and check that we come back to the
        // initial state.
        assertTrue("The direct edit couldn't be undone.", undo());

        check(obviousDiagram, expression, 0, failureMessage);

        // redo the direct edit command and check that we come back to the final
        // state.
        assertTrue("The direct edit couldn't be redone.", redo());

        check(obviousDiagram, expression, 1, failureMessage);
    }

    public void testUndoRedoDeleteFromRedefinedTool() throws Exception {
        List<EObject> createdElts = createNoteInEvoluateView();
        DDiagramElement element = (DDiagramElement) createdElts.get(0);

        Command command = createDiagramThrowNodeNavigationLinkCommand(evoluateDiagram, (DNode) createdElts.get(2));
        assertTrue("Could not create diagram throw node navigation link", execute(command));
        refreshRepresentation(obviousDiagram);
        refreshRepresentation(evoluateDiagram);

        // before applying the tool that delete tiny section and every "Para"
        // type element
        // just under tiny section's chapter, check that there are two graphical
        // elements
        // related to the only "Para" type element in the evoluate diagram and
        // one in the
        // obvious diagram.
        checkModel(1, 2, 1);

        command = getCommandFactory().buildDeleteDiagramElement(element);
        assertTrue("Could not delete the node using the re-defined", execute(command));

        // check that every graphical element associated to the "Para" type
        // element
        // have been removed.
        // Delete tool: see
        // org.eclipse.sirius.tools.internal.command.builders.DeletionCommandBuilder.buildDeleteDiagramElementCommandWithoutTool(DCommand,
        // DDiagram)
        checkModel(0, 0, 0);

        // undo the delete command and check that we come back to the initial
        // state.
        assertTrue("The delete couldn't be undone.", undo());

        checkModel(1, 2, 1);

        // redo the delete command and check that we come back to the final
        // state.
        assertTrue("The delete couldn't be redone.", redo());

        // Delete tool: see
        // org.eclipse.sirius.tools.internal.command.builders.DeletionCommandBuilder.buildDeleteDiagramElementCommandWithoutTool(DCommand,
        // DDiagram)
        checkModel(0, 0, 0);
    }

    public void testUndoRedoDeleteFromDefaultTool() throws Exception {
        List<EObject> createdElts = createNoteInEvoluateView();
        DDiagramElement element = (DDiagramElement) createdElts.get(1);

        Command command = createDiagramThrowNodeNavigationLinkCommand(evoluateDiagram, (DNode) createdElts.get(2));
        assertTrue("Could not create diagram throw node navigation link", execute(command));
        refreshRepresentation(obviousDiagram);
        refreshRepresentation(evoluateDiagram);

        // before applying the tool that delete the note edge, check that there
        // are
        // two graphical elements related to the only "Para" type element.
        checkModel(1, 2, 1);

        command = getCommandFactory().buildDeleteDiagramElement(element);
        assertTrue("Could not delete the edge using the default tool", execute(command));

        // check that every graphical element associated to the "Para" type
        // element
        // have been removed.

        // Dangling Ref on DSemanticDecorator.getTarget() are not removed, the
        // RefreshEditorsPRecommitListener will remove them in automatic or
        // forced
        // refresh for opened representations. In manual refresh mode, the next
        // refresh will delete them.
        // Default tool: see
        // org.eclipse.sirius.tools.internal.command.builders.DeletionCommandBuilder.buildDeleteDiagramElementCommandWithoutTool(DCommand,
        // DDiagram)
        // Differences between with and without tool modes: the command tries to
        // delete all related DSemanticDecorator. See
        // DeleteSeveralDDiagramElementsTask
        checkModel(0, 0, 1);

        // undo the delete command and check that we come back to the initial
        // state.
        assertTrue("The delete couldn't be undone.", undo());

        checkModel(1, 2, 1);

        // redo the delete command and check that we come back to the final
        // state.

        assertTrue("The delete couldn't be undone.", redo());

        // See previous comments.
        checkModel(0, 0, 1);
    }

    private void checkModel(int paraSemanticNb, int evoDiagParaNb, int obviousDiagParaNb) {
        String message = "Wrong element count having the right type.";
        check(evoluateDiagram, "aql:self.target.eAllContents(docbook::Para)->size()", paraSemanticNb, message);
        check(evoluateDiagram, "aql:self.eAllContents().target->filter(docbook::Para)->size()", evoDiagParaNb, message);
        check(obviousDiagram, "aql:self.eAllContents(diagram::DNodeContainer).eAllContents().target->filter(docbook::Para)->size()", obviousDiagParaNb, message);
    }

    private void check(DDiagram context, String expression, int expected, String failureMessage) {
        int targetCount = -1;
        try {
            targetCount = INTERPRETER.evaluateInteger(context, expression).intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the Int source.");
            e.printStackTrace();
        }
        assertEquals(failureMessage, expected, targetCount);
    }
}
