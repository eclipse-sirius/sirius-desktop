/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
     * Notify the listener that a session has been loaded from a session file from a modeling project.
     * 
     * @param session
     *            the session loaded.
     */
    void notifySessionLoadedFromModelingProject(Session session);
}
