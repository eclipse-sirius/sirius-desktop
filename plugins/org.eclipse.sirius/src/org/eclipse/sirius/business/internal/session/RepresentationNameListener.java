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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;

import com.google.common.collect.Iterables;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

/**
 * Post-commit listener which notifies the session manager when a representation
 * is renamed so that session manager listeners (e.g. views) can react).
 * 
 * @author pcdavid
 */
public class RepresentationNameListener extends ResourceSetListenerImpl {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        for (Notification notif : Iterables.filter(event.getNotifications(), Notification.class)) {
            if (isRepresentationNameChange(notif)) {
                Option<Session> session = getSessionFromRepresentation((DRepresentation) notif.getNotifier());
                if (session.some()) {
                    SessionManager.INSTANCE.notifyRepresentationRenamed(session.get());
                }
            }
        }
    }

    private Option<Session> getSessionFromRepresentation(DRepresentation representation) {
        EObject semanticElement = null;
        if (representation instanceof DSemanticDecorator) {
            semanticElement = ((DSemanticDecorator) representation).getTarget();
        }
        if (semanticElement != null) {
            Session session = SessionManager.INSTANCE.getSession(semanticElement);
            if (session != null) {
                return Options.newSome(session);
            }
        }
        return Options.newNone();
    }

    private boolean isRepresentationNameChange(Notification notif) {
        return notif.getNotifier() instanceof DRepresentation && notif.getFeatureID(DRepresentation.class) == ViewpointPackage.DREPRESENTATION__NAME;
    }
}
