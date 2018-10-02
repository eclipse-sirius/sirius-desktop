/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.api.assist;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.ext.swt.TextChangeListener;

/**
 * Client for the content proposal extension point.
 * 
 * @author cbrun
 * 
 */
public interface ContentProposalClient {
    /**
     * return the feature.
     * 
     * @return the feature.
     */
    EAttribute getFeature();

    /**
     * return the text change listener.
     * 
     * @return the text change listener.
     */
    TextChangeListener getListener();

}
