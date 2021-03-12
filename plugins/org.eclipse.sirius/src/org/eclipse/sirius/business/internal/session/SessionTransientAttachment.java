/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.session;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.sirius.business.api.session.Session;

/**
 * This adapter might be used as a marker to retrieve a {@link Session} from any
 * EObject. This allows usage of arbitrary model elements within a session
 * without adding it as a session resource.
 * 
 * With this adapter the model elements does not need to be within the
 * resourceset or the editing domain, that said this model can only be used as a
 * read-only data.
 * 
 * A Session Attachment should be associated with EObjects using the eAdapters()
 * method.
 * 
 * @author cbrun
 * 
 */
public class SessionTransientAttachment extends AdapterImpl {

    private final Session session;

    /**
     * Create a new session attachment.
     * 
     * @param session
     *            the session to attach.
     */
    public SessionTransientAttachment(Session session) {
        this.session = Objects.requireNonNull(session);
    }

    /**
     * return the session associated with this model element.
     * 
     * @return the session associated with this model element.
     */
    public Session getSession() {
        return session;
    }

    /**
     * This utility method look through every adapter associated with the given
     * instance to retrieve, if it is there, any instance of
     * {@link SessionTransientAttachment}.
     * 
     * @param eObj
     *            the instance to inspect.
     * @return an optional SessionTransientAttachment.
     */
    public static Optional<SessionTransientAttachment> of(Notifier eObj) {
        return eObj.eAdapters().stream().filter(SessionTransientAttachment.class::isInstance).map(SessionTransientAttachment.class::cast).findFirst();
    }

}
