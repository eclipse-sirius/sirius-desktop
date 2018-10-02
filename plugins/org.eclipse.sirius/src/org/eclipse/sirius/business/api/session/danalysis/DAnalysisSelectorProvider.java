/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.session.danalysis;

/**
 * Provides analysis selectors.
 * 
 * @author ymortier
 */
public interface DAnalysisSelectorProvider {

    /**
     * Returns <code>true</code> if this provider provides a selector for the
     * given session.
     * 
     * @param session
     *            the session.
     * @return <code>true</code> if this provider provides a selector for the
     *         given session.
     */
    boolean provides(DAnalysisSession session);

    /**
     * Returns the selector to use for the given session.
     * 
     * @param session
     *            the session.
     * @return the selector to use for the given session.
     */
    DAnalysisSelector getSelector(DAnalysisSession session);

}
