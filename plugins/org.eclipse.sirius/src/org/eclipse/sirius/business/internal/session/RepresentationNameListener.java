/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.collect.Iterables;

/**
 * Post-commit listener which notifies the session manager when a representation
 * is renamed so that session manager listeners (e.g. views) can react).
 * 
 * @author pcdavid
 */
public class RepresentationNameListener extends ResourceSetListenerImpl {

    private Session session;

    /**
     * Default constructor.
     * 
     * @param session
     *            the {@link Session}
     */
    public RepresentationNameListener(Session session) {
        this.session = session;
        session.getTransactionalEditingDomain().addResourceSetListener(this);
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        for (Notification notif : Iterables.filter(event.getNotifications(), Notification.class)) {
            if (isRepresentationNameChange(notif)) {
                SessionManager.INSTANCE.notifyRepresentationRenamed(session);
            }
        }
    }

    private boolean isRepresentationNameChange(Notification notif) {
        return notif.getNotifier() instanceof DRepresentation && notif.getFeatureID(DRepresentation.class) == ViewpointPackage.DREPRESENTATION__NAME;
    }

    /**
     * Dispose this resource.
     */
    public void dispose() {
        getTarget().removeResourceSetListener(this);
        session = null;
    }
}
