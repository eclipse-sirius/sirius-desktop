/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.internal;

import java.util.Map;

/**
 * The result of the match performed by the {@link SiriusServerPathMatcher}.
 *
 * @author sbegaudeau
 */
public class SiriusServerMatchResult {

    /**
     * The variables.
     */
    private Map<String, String> variables;

    /**
     * The remaining part.
     */
    private String remainingPart;

    /**
     * Indicates if the match has been successful.
     */
    private boolean hasMatched;

    /**
     * The constructor.
     *
     * @param variables
     *            The variables
     * @param remainingPart
     *            The remaining part
     * @param hasMatched
     *            Indicates if the match has been successful
     */
    public SiriusServerMatchResult(Map<String, String> variables, String remainingPart, boolean hasMatched) {
        this.variables = variables;
        this.remainingPart = remainingPart;
        this.hasMatched = hasMatched;
    }

    /**
     * Indicates if the result is matched.
     *
     * @return <code>true</code> if the matcher has found a (partial) match,
     *         <code>false</code> otherwise
     */
    public boolean hasMatched() {
        return this.hasMatched;
    }

    /**
     * Returns the variables found in the match.
     *
     * @return The variables found in the match
     */
    public Map<String, String> getVariables() {
        return this.variables;
    }

    /**
     * Returns the remaining part of the request.
     *
     * @return The remaining part of the request
     */
    public String getRemainingPart() {
        return this.remainingPart;
    }

}
