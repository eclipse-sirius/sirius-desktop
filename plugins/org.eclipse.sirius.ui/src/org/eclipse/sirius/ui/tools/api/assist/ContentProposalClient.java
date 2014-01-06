/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
