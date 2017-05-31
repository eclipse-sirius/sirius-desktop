/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer.resourcelistener;

import org.eclipse.sirius.business.api.session.Session;

/**
 * Define a listener with notification methods about the loading of a session from a session's file.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public interface ISessionFileLoadingListener {
    /**
     * Notify the listener that a session has been loaded from a session file.
     * 
     * @param session
     *            the session loaded.
     */
    void notifySessionLoadedFromModelingProjectExpansion(Session session);
}
