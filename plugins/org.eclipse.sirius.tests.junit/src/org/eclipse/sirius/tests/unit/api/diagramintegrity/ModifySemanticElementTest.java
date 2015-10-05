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

public class ModifySemanticElementTest extends DiagramIntegrityTestCase {

    /*
     * Check that the corresponding label changes if a semantic element is
     * renamed.
     */
    public void testDiagramIntegrityWhenRenamingSemanticElement() throws Exception {
        String eltName = null;
        String newName = "chapter name";

        // create a diagram based on my semantic model (simple.docbook)
        myRepresentation = createRepresentation("evoluate view");

        // refresh the current representation.
        refreshRepresentation();

        // check that there is Two DNodeContainer representing the 2 chapters in
        // the diagram.
        try {
            eltName = INTERPRETER.evaluateString(myRepresentation, "aql:self.eAllContents(diagram::DNode)->first().name").toString();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The diagram is not correctly initialized.", "chap0", eltName);

        // delete the chapter.
        renameChapter(newName);

        // refresh the current representation.
        refreshRepresentation();

        // check that there is one DNodeContainer left in the diagram.
        try {
            eltName = INTERPRETER.evaluateString(myRepresentation, "aql:self.eAllContents(diagram::DNode)->first().name").toString();
        } catch (final EvaluationException e) {
            fail("Exception while trying to get the integer value.");
            e.printStackTrace();
        }
        assertEquals("The element has not been renamed.", newName, eltName);

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
