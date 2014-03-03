/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

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

    private Session session;

    /**
     * Create a new session attachment.
     * 
     * @param session
     *            the session to attach.
     */
    public SessionTransientAttachment(Session session) {
        this.session = session;
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
    public static Option<SessionTransientAttachment> getSessionTransientAttachement(Notifier eObj) {
        Iterator<SessionTransientAttachment> it = Iterators.filter(Sets.newLinkedHashSet(eObj.eAdapters()).iterator(), SessionTransientAttachment.class);
        if (it.hasNext()) {
            return Options.newSome(it.next());
        }
        return Options.newNone();
    }

}
