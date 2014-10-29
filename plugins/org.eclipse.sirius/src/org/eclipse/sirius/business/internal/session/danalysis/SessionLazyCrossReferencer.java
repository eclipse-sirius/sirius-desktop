/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.tools.api.util.LazyCrossReferencer;
import org.eclipse.sirius.viewpoint.DAnalysis;

/**
 * A LazyCrossReferencer for the session.
 * {@link LazyCrossReferencer#initialize()} is overridden in order to only add
 * the adapter at the first use.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 *
 */
public class SessionLazyCrossReferencer extends LazyCrossReferencer {
    private DAnalysisSessionImpl session;

    /**
     * Construct from an opened session.
     * 
     * @param session
     *            an opened session
     */
    public SessionLazyCrossReferencer(DAnalysisSessionImpl session) {
        this.session = session;
    }

    @Override
    protected void initialize() {
        super.initialize();
        for (Resource res : session.getSemanticResources()) {
            List<Adapter> adapters = res.eAdapters();
            // add only if it was not added between creation and
            // initialization
            if (!adapters.contains(this)) {
                adapters.add(this);
            }
        }

        for (DAnalysis analysis : session.allAnalyses()) {
            List<Adapter> adapters = analysis.eResource().eAdapters();
            // add only if it was not added between creation and
            // initialization
            if (!adapters.contains(this)) {
                adapters.add(this);
            }
        }
    }
}
