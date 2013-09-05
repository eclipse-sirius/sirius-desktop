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

/**
 * Contract of a proposal for an expression to evaluate.
 * 
 * @author ymortier
 */
public interface IExpressionProposal {

    /**
     * Returns the proposal.
     * 
     * @return the proposal.
     */
    String getProposal();

    /**
     * Returns the description.
     * 
     * @return the description.
     */
    String getDescription();

}
