/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import org.eclipse.sirius.common.tools.Messages;

/**
 * Default implementation of {@link IExpressionProposal}.
 * 
 * @author ymortier
 */
public class DefaultExpressionProposal implements IExpressionProposal {

    /** The description. */
    private String description;

    /** The proposal. */
    private String proposal;

    /**
     * Creates a new {@link DefaultExpressionProposal} with the given proposal.
     * 
     * @param proposal
     *            the proposal (mandatory).
     */
    public DefaultExpressionProposal(final String proposal) {
        this(proposal, null);
    }

    /**
     * Creates a new {@link DefaultExpressionProposal} with the given proposal
     * and description.
     * 
     * @param proposal
     *            the proposal
     * @param description
     *            the description of the proposal.
     */
    public DefaultExpressionProposal(final String proposal, final String description) {
        if (proposal == null) {
            throw new IllegalArgumentException(Messages.DefaultExpressionProposal_nullProposal);
        }
        this.proposal = proposal;
        this.description = description;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IExpressionProposal#getDescription()
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.interpreter.IExpressionProposal#getProposal()
     */
    public String getProposal() {
        return this.proposal;
    }

}
