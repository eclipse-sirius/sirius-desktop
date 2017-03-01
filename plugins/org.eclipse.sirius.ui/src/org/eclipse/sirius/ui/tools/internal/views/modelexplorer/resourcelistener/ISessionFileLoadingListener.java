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
