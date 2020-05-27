/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.sequence.condition;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Check if the new note attachment has been connected.
 * 
 * @author smonnier
 */
public class CheckNoteAttachement extends DefaultCondition {

    private AbstractGraphicalEditPart lifelineEditPart;

    private int attachementNumber;

    private boolean attachmentIsFromNote;

    /**
     * Constructor.
     * 
     */
    public CheckNoteAttachement(AbstractGraphicalEditPart editPart, boolean attachmentIsFromNote) {
        this.lifelineEditPart = editPart;
        this.attachmentIsFromNote = attachmentIsFromNote;
        if (attachmentIsFromNote) {
            this.attachementNumber = editPart.getTargetConnections().size();
        } else {
            this.attachementNumber = editPart.getSourceConnections().size();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        if (attachmentIsFromNote) {
            return attachementNumber + 1 == lifelineEditPart.getTargetConnections().size();
        } else {
            return attachementNumber + 1 == lifelineEditPart.getSourceConnections().size();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return "No new note attachment has been detected before timeout";
    }
}
