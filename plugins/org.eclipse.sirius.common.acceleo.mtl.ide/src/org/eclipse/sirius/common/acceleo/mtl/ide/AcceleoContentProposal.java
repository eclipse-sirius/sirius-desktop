/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.acceleo.mtl.ide;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;

/**
 * Prevents viewpoint from losing completion information.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class AcceleoContentProposal extends ContentProposal {
    /** Keeps the original proposal along with all its information. */
    private ICompletionProposal original;

    /**
     * Creates a new content proposal.
     * 
     * @param proposal
     *            the assist content proposal.
     * @param display
     *            the display of the content proposal.
     * @param information
     *            the informations about the proposal.
     * @param original
     *            the original proposal.
     * 
     */
    public AcceleoContentProposal(String proposal, String display, String information, ICompletionProposal original) {
        super(proposal, display, information);
        this.original = original;
    }

    public ICompletionProposal getOriginal() {
        return original;
    }
}
