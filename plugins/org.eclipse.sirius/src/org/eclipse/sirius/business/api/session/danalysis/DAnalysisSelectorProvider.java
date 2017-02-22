/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
