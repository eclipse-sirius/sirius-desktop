/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.contentassist;

import org.eclipse.sirius.common.tools.api.util.StringUtil;

/**
 * This class create a content proposal object.
 * 
 * @author ggebhart
 * 
 */

public class ContentProposal implements Comparable<ContentProposal> {

    private String proposal;

    private String display;

    private String information;

    private int cursorPosition;

    /**
     * Constructor.
     *
     * Cursor will be placed after the content of the current
     * proposal.
     * 
     * @param proposal
     *            the assist content proposal.
     * @param display
     *            the display of the content proposal.
     * @param information
     *            the informations about the proposal.
     * 
     * @since 0.9.0
     */
    public ContentProposal(final String proposal, final String display, final String information) {
        this.proposal = proposal;
        this.display = display;
        this.information = information;
        this.cursorPosition = proposal.length();
    }

    /**
     * Constructor.
     * 
     * @param proposal
     *            the assist content proposal.
     * @param display
     *            the display of the content proposal.
     * @param information
     *            the informations about the proposal.
     * @param cursorPosition
     *            the zero-based index position within the contents where the
     *            cursor should be placed after the proposal is accepted.
     */
    public ContentProposal(final String proposal, final String display, final String information, final int cursorPosition) {
        this.proposal = proposal;
        this.display = display;
        this.information = information;
        this.cursorPosition = cursorPosition;
    }

    /**
     * Add a prefix on proposal and display elements.
     * 
     * @param prefix
     *            the prefix to add
     */
    public void addPrefix(final String prefix) {
        if (!StringUtil.isEmpty(prefix)) {
            this.proposal = prefix + this.proposal;
            this.display = prefix + this.display;
            this.cursorPosition += prefix.length();
        }
    }

    public String getProposal() {
        return proposal;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public String getDisplay() {
        return display;
    }

    public String getInformation() {
        return information;
    }

    /**
     * Compare this object with the specified object for order.
     * 
     * @param other
     *            the specified ContentProposal object.
     * @return the result of the comparison.
     */
    public int compareTo(final ContentProposal other) {
        final String nextValue = other.getProposal();
        final String currentValue = this.getProposal();
        return currentValue.compareToIgnoreCase(nextValue);

    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param other
     *            the object with which to compare.
     * @return true if this object is the same as the obj argument; false
     *         otherwise.
     */
    @Override
    public boolean equals(final Object other) {
    	boolean equal = false;
    	if (this == other) {
    		equal = true;
    	} else if (other instanceof ContentProposal) {
    		final String nextValue = ((ContentProposal) other).getProposal();
    		final String currentValue = this.getProposal();
    		equal = currentValue.equalsIgnoreCase(nextValue);
    	}

        return equal;
    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.getProposal().hashCode();

    }
}
