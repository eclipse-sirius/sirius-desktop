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
package org.eclipse.sirius.common.ui.tools.internal.contentassist;

import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.util.StringUtil;

/**
 * This class create a content proposal object.
 * 
 * @author mporhel
 * 
 */

public class ContentProposalConverter {

    private String proposalStart;

    /**
     * Constructor.
     * 
     * @param proposalStart
     *            the current proposal start.
     * 
     */
    public ContentProposalConverter(String proposalStart) {
        this.proposalStart = proposalStart;
    }

    /**
     * Convert {@link ContentProposal}s to JFace {@link IContentProposal}s.
     * 
     * @param proposals
     *            proposals to convert.
     * @return converted proposals.
     */
    public IContentProposal[] convertToJFaceContentProposals(List<ContentProposal> proposals) {
        if (proposals == null)
            return new IContentProposal[0];

        IContentProposal[] contentProposals = new IContentProposal[proposals.size()];
        for (int i = 0; i < contentProposals.length; i++) {
            contentProposals[i] = convertToJFaceContentProposal(proposals.get(i));
        }
        return contentProposals;
    }

    /**
     * Convert a {@link ContentProposal} to a JFace {@link IContentProposal}.
     * 
     * @param arg
     *            proposal to convert.
     * @return converted proposal.
     * 
     */
    public IContentProposal convertToJFaceContentProposal(final ContentProposal arg) {
        String proposal = arg.getProposal();
        int cursorPosition = arg.getCursorPosition();
        if (!StringUtil.isEmpty(proposalStart) && proposal.startsWith(proposalStart)) {
            proposal = proposal.substring(proposalStart.length());

            // cursorPosition is not always at the end of the proposal, so
            // just move the position.
            cursorPosition -= proposalStart.length();
        }

        return new DefaultContentProposal(proposal, arg.getInformation(), arg.getDisplay(), cursorPosition);
    }

    /**
     * Inner Class. Implementation of IContentProposal interface.
     */
    private static final class DefaultContentProposal implements IContentProposal {

        private final String proposal;

        private final String description;

        private final String label;

        private final int cursorPosition;

        private DefaultContentProposal(final String proposal, final String description, final String label, final int cursorPosition) {
            this.proposal = proposal;
            this.description = description;
            this.label = label;
            this.cursorPosition = cursorPosition;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
         */
        public String getContent() {
            return proposal;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
         */
        public int getCursorPosition() {
            return cursorPosition;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
         */
        public String getDescription() {
            return description;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
         */
        public String getLabel() {
            return label;
        }
    }

}
