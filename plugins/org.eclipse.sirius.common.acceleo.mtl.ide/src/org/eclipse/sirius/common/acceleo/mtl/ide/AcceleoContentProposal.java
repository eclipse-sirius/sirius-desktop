/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl.ide;

import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

/**
 * Prevents viewpoint from losing completion information.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class AcceleoContentProposal extends ContentProposal {
    /** Keeps the original proposal along with all its information. */
    private ICompletionProposal original;

    public AcceleoContentProposal(String proposal, String display, String information, ICompletionProposal original) {
        super(proposal, display, information);
        this.original = original;
    }

    public ICompletionProposal getOriginal() {
        return original;
    }
}
