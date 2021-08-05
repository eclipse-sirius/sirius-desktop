/*******************************************************************************
 * Copyright (c) 2018, 2021 Obeo.
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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;

/**
 * Helper operations on {@link DView}.
 * 
 * @author pcdavid
 */
public final class DViewHelper {
    private DViewHelper() {
        // Prevents instantiation.
    }

    /**
     * Refresh all the (loaded) representations inside the view and delete the obsolete ones.
     * 
     * @param self
     *            the view to refresh.
     */
    public static void refreshViewContents(DView self) {
        final Set<DRepresentation> representationsToDelete = new HashSet<DRepresentation>();
        Iterator<DRepresentation> it = new DViewQuery(self).getLoadedRepresentations().iterator();
        while (it.hasNext()) {
            final DRepresentation representation = it.next();
            /*
             * detect dangling representations.
             */
            if (representation instanceof DSemanticDecorator && ((DSemanticDecorator) representation).getTarget() == null) {
                representationsToDelete.add(representation);
            }
            if (!representationsToDelete.contains(representation)) {
                DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
            }
        }
        /*
         * delete dangling representations
         */
        it = representationsToDelete.iterator();
        while (it.hasNext()) {
            final EObject next = it.next();
            final Session session;
            if (next instanceof DSemanticDecorator) {
                session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) next).getTarget());
            } else {
                session = SessionManager.INSTANCE.getSession(next);
            }
            SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(next).eDelete(next, session != null ? session.getSemanticCrossReferencer() : null);
        }
    }
}
