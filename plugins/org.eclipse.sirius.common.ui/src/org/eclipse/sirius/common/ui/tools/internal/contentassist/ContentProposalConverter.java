/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.internal.assist.ContentContextHelper;

/**
 * This class create a content proposal object.
 * 
 * @author mporhel
 * @author lfasani
 */
public class ContentProposalConverter {

    private String contents;

    private int currentPosition;

    /**
     * Constructor.
     * 
     * @param contents
     *            the full content.
     * @param position
     *            the current position of the cursor.
     */
    public ContentProposalConverter(String contents, int position) {
        this.contents = contents;
        this.currentPosition = position;

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
    private IContentProposal convertToJFaceContentProposal(final ContentProposal arg) {
        String prefix = CompoundInterpreter.INSTANCE.getVariablePrefix(contents);
        String proposalStart = new ContentContextHelper(contents, currentPosition, prefix).getProposalStart();

        // As the content proposal mode is set to
        // ContentProposalAdapter.PROPOSAL_REPLACE
        // the full expression content is proposed including proposal
        String proposal = arg.getProposal();
        StringBuilder targetProposalBuilder = new StringBuilder(contents);
        targetProposalBuilder.delete(currentPosition - proposalStart.length(), currentPosition);
        targetProposalBuilder.insert(currentPosition - proposalStart.length(), proposal);

        // put the cursor after the proposal
        int targetPosition = currentPosition - proposalStart.length() + arg.getCursorPosition();

        return new DefaultContentProposal(targetProposalBuilder.toString(), arg.getInformation(), arg.getDisplay(), targetPosition);
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
        @Override
        public String getContent() {
            return proposal;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
         */
        @Override
        public int getCursorPosition() {
            return cursorPosition;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
         */
        @Override
        public String getDescription() {
            return description;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
         */
        @Override
        public String getLabel() {
            return label;
        }
    }

}
