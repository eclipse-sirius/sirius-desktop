/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.contentassist;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;

import java.util.List;

/**
 * The content assist interface.
 * 
 * @author ggebhart
 */
public interface IProposalProvider {

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
