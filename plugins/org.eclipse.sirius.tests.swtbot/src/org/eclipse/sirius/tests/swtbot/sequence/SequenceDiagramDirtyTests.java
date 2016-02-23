/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;

/**
 * 
 * @author pcdavid
 */
public class SequenceDiagramDirtyTests extends AbstractSequenceDiagramTestCase {
    @Override
    protected String getPath() {
        return "data/unit/sequence/navigation/";
    }

    @Override
    protected String getSemanticModel() {
        return "fixture.interactions";
    }

    @Override
    protected String getTypesSemanticModel() {
        return "types.ecore";
    }

    @Override
    protected String getSessionModel() {
        return "fixture.aird";
    }

    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome("Complex");
    }

    /**
     * Opening a an existing sequence diagram for the first time in a session
     * should not make the session dirty if the semantic model has not changed
     * since the last opening.
     */
    public void test_session_clean_after_diagram_opened() {
        Session session = localSession.getOpenedSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());
    }

    /**
     * After saving a sequence diagram and closing the editor, re-opening the
     * editor should not make the session dirty.
     */
    public void test_reopen_saved_sequence_diagram_in_same_session() {
        Session session = localSession.getOpenedSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());
        // Delete an execution => the session becomes DIRTY
        editor.click(new Point(320, 280));
        deleteSelectedElement();
        assertEquals(SessionStatus.DIRTY, session.getStatus());
        // Save and close => the session is clean
        editor.saveAndClose();
        assertEquals(SessionStatus.SYNC, session.getStatus());
        // Reopen the editor => the session should still be clean
        editor = openDiagram(localSession.getOpenedSession(), getRepresentationId(), getDRepresentationName().get(), DDiagram.class);
        assertEquals(SessionStatus.SYNC, session.getStatus());
    }

}
