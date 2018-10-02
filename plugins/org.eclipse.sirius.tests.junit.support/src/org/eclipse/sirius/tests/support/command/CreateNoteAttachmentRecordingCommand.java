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
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;

/**
 * A Recording Command to call GMFNotationHelper.createNoteAttachment.
 * 
 * @author smonnier
 */
public class CreateNoteAttachmentRecordingCommand extends RecordingCommand {

    private final Node note;

    private final Node gmfNode;

    /**
     * Constructor.
     * 
     * @param domain
     *            my domain
     * @param note
     *            a list of nodes corresponding to notes.
     * @param gmfNode
     *            the node which has as element the diagram element given as
     *            parameter or null if any
     */
    public CreateNoteAttachmentRecordingCommand(TransactionalEditingDomain domain, Node note, Node gmfNode) {
        super(domain);
        this.note = note;
        this.gmfNode = gmfNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        GMFNotationHelper.createNoteAttachment(note, gmfNode);
    }

}
