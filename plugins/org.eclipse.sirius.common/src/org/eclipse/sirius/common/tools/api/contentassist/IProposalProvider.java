/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.api.contentassist;

import java.util.List;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;

/**
 * The content assist interface.
 * 
 * @author ggebhart
 */
public interface IProposalProvider {

    /**
     * Indicates how an accepted proposal should affect the string being completed. These enum corresponds to
     * ContentProposalAdapter.PROPOSAL_INSERT and ContentProposalAdapter.PROPOSAL_REPLACE. Default value is
     * PROPOSAL_INSERT.
     * 
     * @author lfasani
     *
     */
    enum ProposalAcceptanceStyle {

        /**
         * If the proposal is to be inserted in a string.</br>
         * Corresponds to ContentProposalAdapter.PROPOSAL_INSERT
         */
        PROPOSAL_INSERT,

        /**
         * If the proposal is to be replacing another string in the global string.</br>
         * Corresponds to ContentProposalAdapter.PROPOSAL_REPLACE
         */
        PROPOSAL_REPLACE
    }

    /**
     * Evaluates the content proposals for a given expression and returns the
     * result as a list.
     * 
     * @param interpreter
     *            The interpreter this proposal provider extends.
     * @param context
     *            the context.
     * @return content proposal list.
     */
    List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context);

    /**
     * Evaluates the content proposals for a given expression and returns the
     * result as a list.
     * 
     * @param interpreter
     *            The interpreter this proposal provider extends.
     * @param context
     *            the context.
     * @return content proposal list.
     */
    List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context);

    /**
     * Get a proposal to start a new expression handled by the current
     * interpreter.
     * 
     * @return the proposal if there is one or <code>null</code> if none.
     * @since 0.9.0
     */
    ContentProposal getNewEmtpyExpression();

}
