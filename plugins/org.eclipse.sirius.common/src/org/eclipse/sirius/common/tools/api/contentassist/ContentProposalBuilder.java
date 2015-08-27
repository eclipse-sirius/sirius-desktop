/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.contentassist;

import org.eclipse.sirius.common.tools.api.contentassist.ContentProposalWithReplacement.ImageKind;

/**
 * Utility class used to start building content proposals.
 * 
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public final class ContentProposalBuilder {

    /**
     * The constructor.
     */
    private ContentProposalBuilder() {
        // Prevent instantiation
    }

    /**
     * Prepare the creation of a content proposal.
     * 
     * @param proposal
     *            The text of the proposal
     * @param display
     *            The value of the proposal displayed to the user
     * @param information
     *            The documentation of the proposal
     * @return The proposal builder used to add new options to the proposal
     */
    public static ProposalBuilder proposal(String proposal, String display, String information) {
        return ContentProposalBuilder.proposal(proposal, display, information, proposal.length());
    }
    
    /**
     * Prepare the creation of a content proposal.
     * 
     * @param proposal
     *            The text of the proposal
     * @param display
     *            The value of the proposal displayed to the user
     * @param information
     *            The documentation of the proposal
     * @param cursor
     *            The position of the cursor once the proposal has been accepted
     * @return The proposal builder used to add new options to the proposal
     */
    public static ProposalBuilder proposal(String proposal, String display, String information, int cursor) {
        return new ProposalBuilder(proposal, display, information, cursor);
    }

    /**
     * Utility class used to populate the options of the proposal to create.
     * 
     * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
     */
    public static final class ProposalBuilder {
        /**
         * The text of the proposal.
         */
        private String proposal;
        
        /**
         * The value of the proposal displayed to the user.
         */
        private String display;
        
        /**
         * The documentation of the proposal.
         */
        private String information;
        
        /**
         * The position of the cursor once the proposal has been accepted.
         */
        private int cursor;
        
        /**
         * Indicates if we should build a {@link ContentProposalWithReplacement} or not.
         */
        private boolean shouldBuildAProposalWithReplacement;

        /**
         * The replacement offset.
         */
        private int replacementOffset;

        /**
         * The replacement length.
         */
        private int replacementLength;

        /**
         * The image of the proposal.
         */
        private Object proposalImage;

        /**
         * The kind of image used in the proposal.
         */
        private ImageKind kind;
        
        /**
         * The constructor. This constructor is private in order to prevent anyone from
         * using it except thanks to the {@link ContentProposalBuilder}.
         * 
         * @param proposal
         *            The text of the proposal
         * @param display
         *            The value of the proposal displayed to the user
         * @param information
         *            The documentation of the proposal
         * @param cursor
         *            The position of the cursor once the proposal has been
         *            accepted
         */
        private ProposalBuilder(String proposal, String display, String information, int cursor) {
            this.proposal = proposal;
            this.display = display;
            this.information = information;
            this.cursor = cursor;
        }

        /**
         * Adds the replacement information.
         * 
         * @param offset
         *            The replacement offset
         * @param length
         *            The replacement length
         * @return The proposal builder
         */
        public ProposalBuilder withReplacement(int offset, int length) {
            this.shouldBuildAProposalWithReplacement = true;
            this.replacementOffset = offset;
            this.replacementLength = length;
            return this;
        }

        /**
         * Adds an image to the proposal.
         * 
         * @param image
         *            The object of the image
         * @param imageKind
         *            The kind of the image
         * @return The proposal builder
         */
        public ProposalBuilder withImage(Object image, ImageKind imageKind) {
            this.shouldBuildAProposalWithReplacement = true;
            this.proposalImage = image;
            this.kind = imageKind;
            return this;
        }

        /**
         * Builds the {@link ContentProposal}.
         * 
         * @return The content proposal
         */
        public ContentProposal build() {
            if (this.shouldBuildAProposalWithReplacement) {
                return new ContentProposalWithReplacement(this.proposal, this.display, this.information, this.cursor, this.replacementOffset, this.replacementLength, this.proposalImage, this.kind);
            }
            
            return new ContentProposal(this.proposal, this.display, this.information, this.cursor);
        }
    }
}
