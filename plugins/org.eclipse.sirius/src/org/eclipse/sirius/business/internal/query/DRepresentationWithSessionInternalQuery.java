/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.query;

import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.model.business.internal.query.DRepresentationInternalQuery;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A class aggregating all the queries (read-only!) having a {@link DRepresentation} as a starting point and a potential
 * session.
 * 
 * @author mporhel
 * 
 */
public class DRepresentationWithSessionInternalQuery extends DRepresentationInternalQuery {

    private Session session;

    /**
     * Create a new query.
     * 
     * @param representation
     *            the element to query.
     */
    public DRepresentationWithSessionInternalQuery(DRepresentation representation) {
        super(representation);
    }

    /**
     * Create a new query.
     * 
     * @param representation
     *            the element to query.
     * @param session
     *            the session containing the representation.
     */
    public DRepresentationWithSessionInternalQuery(DRepresentation representation, Session session) {
        this(representation);
        this.session = session;
    }

    /**
     * Check if the current representation is a dangling representation, ie if its target element is null or if it does
     * not belong to any session.
     * 
     * @return true if the current representation is orphan.
     */
    public boolean isDanglingRepresentation() {
        if (representation instanceof DSemanticDecorator) {
            DSemanticDecorator semDecRep = (DSemanticDecorator) representation;
            if (session == null) {
                session = SessionManager.INSTANCE.getSession(semDecRep.getTarget());
            }
            return semDecRep.getTarget() == null || session == null;
        }
        return false;
    }

    /**
     * Gives the value of the preference {@link SiriusPreferencesKeys}.PREF_AUTO_REFRESH for this representation.
     * 
     * @return the value
     */
    public boolean isAutoRefresh() {
        if (session == null) {
            session = Session.of(representation).orElse(null);
        }
        if (session != null) {
            return session.getSiriusPreferences().isAutoRefresh();
        }
        return false;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
