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

import java.util.Collection;

import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.model.business.internal.query.DRepresentationInternalQuery;
import org.eclipse.sirius.model.business.internal.query.DRepresentationInternalQueryHelper;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A class aggregating all the queries (read-only!) having a {@link DRepresentation} as a starting point and a potential
 * session.
 * 
 * @author mporhel
 * 
 */
public class DRepresentationWithSessionInternalQueryHelper extends DRepresentationInternalQueryHelper {

    @Override
    public boolean hasSession(DRepresentationInternalQuery query) {
        return getSession(query) != null;
    }

    private Session getSession(DRepresentationInternalQuery query) {
        Session session = null;

        DRepresentation rep = query.getRepresentation();
        if (query instanceof DRepresentationWithSessionInternalQuery) {
            session = ((DRepresentationWithSessionInternalQuery) query).getSession();
        }
        if (session == null && rep instanceof DSemanticDecorator) {
            session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) rep).getTarget());
            if (query instanceof DRepresentationWithSessionInternalQuery) {
                ((DRepresentationWithSessionInternalQuery) query).setSession(session);
            }
        }
        return session;
    }

    @Override
    public Collection<DAnalysis> getAllAnalyses(DRepresentationInternalQuery query) {
        Session session = getSession(query);
        if (session != null) {
            return ((DAnalysisSessionImpl) session).allAnalyses();
        }
        return super.getAllAnalyses(query);
    }

    @Override
    public ECrossReferenceAdapter getCrossReferencer(DRepresentationInternalQuery query) {
        Session session = getSession(query);
        if (session != null) {
            session.getSemanticCrossReferencer();
        }
        return super.getCrossReferencer(query);
    }

}
