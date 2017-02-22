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

/**
 * This class extends the {@link ContentProposal} with additional information in
 * order to provide better proposals with partial completion, etc.
 * 
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class ContentProposalWithReplacement extends ContentProposal {
    /**
     * The kind of image supported for the proposal.
     * 
     * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
     */
    public enum ImageKind {
        /**
         * A SWT image.
         */
        SWT_IMAGE
    }
    
    /**
     * The replacement offset.
     */
    private int replacementOffset;

    /**
     * The replacement length.
     */
    private int replacementLength;

    /**
     * The image.
     */
    private Object image;

    /**
     * The kind of image.
     */
    private ImageKind imageKind;

    /**
     * The constructor.
     * 
     * @param proposal
     *            The proposal
     * @param display
     *            The text to display to the user
     * @param information
     *            The documentation of the proposal
     * @param cursor
     *            The cursor position after this proposal has been applied
     * @param offset
     *            The replacement offset
     * @param length
     *            The replacement length
     */
    public ContentProposalWithReplacement(String proposal, String display, String information, int cursor, int offset, int length) {
        super(proposal, display, information, cursor);
        this.replacementOffset = offset;
        this.replacementLength = length;
    }
    
    /**
     * The constructor.
     * 
     * @param proposal
     *            The proposal
     * @param display
     *            The text to display to the user
     * @param information
     *            The documentation of the proposal
     * @param cursor
     *            The cursor position after this proposal has been applied
     * @param offset
     *            The replacement offset
     * @param length
     *            The replacement length
     * @param image
     *            The image
     * @param imageKind
     *            The kind of image provided by the proposal
     */
    //CHECKSTYLE:OFF
    public ContentProposalWithReplacement(String proposal, String display, String information, int cursor, int offset, int length, Object image, ImageKind imageKind) {
      //CHECKSTYLE:ON
        super(proposal, display, information, cursor);
        this.replacementOffset = offset;
        this.replacementLength = length;
        this.image = image;
        this.imageKind = imageKind;
    }

    /**
     * Returns the length of the part of the expression to replace.
     * 
     * @return The replacement length
     */
    public int getReplacementLength() {
        return this.replacementLength;
    }

    /**
     * Returns the offset indicating where to start replacing the content of the
     * expression.
     * 
     * @return The replacement offset
     */
    public int getReplacementOffset() {
        return this.replacementOffset;
    }
    
    public Object getImage() {
        return image;
    }
    
    public ImageKind getImageKind() {
        return imageKind;
    }

}
