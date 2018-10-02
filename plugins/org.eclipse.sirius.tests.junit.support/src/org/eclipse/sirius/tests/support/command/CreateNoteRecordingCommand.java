/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;

/**
 * A Recording Command to call GMFNotationHelper.createNote.
 * 
 * @author smonnier
 */
public class CreateNoteRecordingCommand extends RecordingCommand {

    private final Diagram gmfDiagram;

    private final String noteText;

    /**
     * Constructor.
     * 
     * @param domain
     *            my domain
     * @param gmfDiagram
     *            the view which has as element the diagram element given as
     *            parameter or null if any
     * @param noteText
     *            the note text
     */
    public CreateNoteRecordingCommand(TransactionalEditingDomain domain, Diagram gmfDiagram, String noteText) {
        super(domain);
        this.gmfDiagram = gmfDiagram;
        this.noteText = noteText;
    }

    @Override
    protected void doExecute() {
        GMFNotationHelper.createNote(gmfDiagram, noteText);
    }

}
