/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.api.diagramintegrity;

import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.internal.session.danalysis.DViewHelper;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.tools.api.command.semantic.RemoveDanglingReferences;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;

public class DeleteSemanticElementTest extends DiagramIntegrityTestCase {

    /*
     * Check that the corresponding container is removed from the representation if its target is removed from the
     * semantic model.
     */
    public void testSemanticElementDeletionRemovesContainer() {
        int eltCount = -1;

        // create a diagram based on my semantic model (simple.docbook)
        myRepresentation = createRepresentation("obviousDiagram");
        addChapter();

        // refresh the current representation.
        refreshRepresentation();

        // check that there is Two DNodeContainer representing the 2 chapters in
        // the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNodeContainer)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 2, eltCount);

        // check that there is one DEdge between chapters in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 1, eltCount);

        // delete the chapter.
        deleteChapter();

        // refresh the current representation.
        refreshRepresentation();

        // check that there is one DNodeContainer left in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNodeContainer)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The element has not been removed.", 1, eltCount);

        // check that there are no more Edges in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The element has not been removed.", 0, eltCount);
    }

    /*
     * Check that the corresponding node is removed from the representation if its target is removed from the semantic
     * model.
     */
    public void testSemanticElementDeletionRemovesdNode() {
        int eltCount = -1;

        // create a diagram based on my semantic model (simple.docbook)
        myRepresentation = createRepresentation("evoluate view");

        // refresh the current representation.
        refreshRepresentation();

        // check that there is one DNode representing the 2 chapters in the
        // diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNode)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 1, eltCount);

        // delete the chapter.
        deleteChapter();

        // refresh the current representation.
        refreshRepresentation();

        // check that there are no DNode left in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(sessionModel, "aql:self.eAllContents(diagram::DSemanticDiagram)->select( e | e.name = 'evoluate view').eAllContents(diagram::DNode)->size()")
                    .intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The element has not been removed.", 0, eltCount);
    }

    /*
     * Check that the corresponding edge is removed from the representation if its target is removed from the semantic
     * model.
     */
    public void testSemanticElementDeletionRemovesEdge() {
        int eltCount = -1;

        // add a chapter in the semantic model.
        addTinySection();
        addNote();
        activateViewpoint("docbook1");
        // create a diagram based on my semantic model (simple.docbook)
        myRepresentation = createRepresentation("evoluate view");

        // refresh the current representation.
        refreshRepresentation();

        // check that there is 4 Edges in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 4, eltCount);

        // check that there is 5 nodes in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNode)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 5, eltCount);

        // add a note chapter.
        deleteNote();

        // refresh the current representation.
        refreshRepresentation();

        // check that there is 3 Edges left in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DEdge)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The element has not been removed.", 3, eltCount);

        // check that there is is 4 nodes in the diagram.
        try {
            eltCount = INTERPRETER.evaluateInteger(myRepresentation, "aql:self.eAllContents(diagram::DNode)->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", 4, eltCount);
    }

    /*
     * Check that the corresponding diagram is removed if it's target is removed from the semantic model.
     */
    public void testSemanticElementDeletionRemovesDiagram() {
        myRepresentation = createRepresentation("chapterDiagram", semanticModel.eContents().get(0));
        refreshRepresentation();

        int eltCount = 0;

        for (DView view : ((DAnalysis) sessionModel).getOwnedViews()) {
            List<DRepresentationDescriptor> loadedRepresentationDescriptors = view.getOwnedRepresentationDescriptors();
            for (DRepresentationDescriptor representationDescriptor : loadedRepresentationDescriptors) {
                if ("chapterDiagram" == representationDescriptor.getName()) {
                    eltCount++;
                }
            }
        }
        assertEquals("The diagram is not correctly initialized.", 1, eltCount);

        // delete the chapter.
        deleteChapter();

        this.session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(this.session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                RemoveDanglingReferences.removeDanglingReferences(semanticModel.eResource().getResourceSet());

            }
        });

        for (DView view : ((DAnalysis) sessionModel).getOwnedViews()) {
            this.session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(this.session.getTransactionalEditingDomain()) {
                @Override
                protected void doExecute() {
                    DViewHelper.refreshViewContents(view);
                }
            });
        }

        // refresh the current representation.
        // refreshRepresentation();

        try {
            eltCount = INTERPRETER.evaluateInteger(sessionModel, "aql:self.eAllContents(viewpoint::DRepresentation)->select(r | r.name = 'chapterDiagram')->size()").intValue();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram has not been removed.", 0, eltCount);
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
