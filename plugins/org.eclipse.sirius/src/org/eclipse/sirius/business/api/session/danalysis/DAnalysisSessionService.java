/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
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
 * The contrarct for a {@link DAnalysisSessionService}.
 * 
 * @author mchauvin
 */
public interface DAnalysisSessionService {

    /**
     * Set the analysis selector.
     * 
     * @param selector
     *            the selector
     */
    void setAnalysisSelector(DAnalysisSelector selector);

}
