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
package org.eclipse.sirius.tests.swtbot.sequence.condition;

import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Check if the new note attachment has been connected.
 * 
 * @author smonnier
 */
public class CheckNoteAttachement extends DefaultCondition {

    private LifelineEditPart lifelineEditPart;

    private int attachementNumber;

    private boolean attachmentIsFromLifeline;

    /**
     * Constructor.
     * 
     */
    public CheckNoteAttachement(LifelineEditPart lifelineEditPart, boolean attachmentIsFromLifeline) {
        this.lifelineEditPart = lifelineEditPart;
        this.attachmentIsFromLifeline = attachmentIsFromLifeline;
        if (attachmentIsFromLifeline) {
            this.attachementNumber = lifelineEditPart.getSourceConnections().size();
        } else {
            this.attachementNumber = lifelineEditPart.getTargetConnections().size();
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean test() throws Exception {
        if (attachmentIsFromLifeline) {
            return attachementNumber + 1 == lifelineEditPart.getSourceConnections().size();
        } else {
            return attachementNumber + 1 == lifelineEditPart.getTargetConnections().size();
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getFailureMessage() {
        return "No new note attachment has been detected before timeout";
    }
}
