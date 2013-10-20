/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.session;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Helper to manage sessions with GMF diagram.
 * 
 * @author ymortier
 */
public final class DiagramSessionHelper {

    /**
     * Avoid instantiation.
     */
    private DiagramSessionHelper() {
        // empty
    }

    /**
     * Try to find the session associated with the given GMF view. Returns
     * <code>null</code> if no session is found.
     * 
     * @param view
     *            a GMF view.
     * @return the found session or null if no session is available.
     */
    public static Session findSession(final View view) {
        Session result = null;
        final EObject eObject = ViewUtil.resolveSemanticElement(view);
        if (eObject instanceof DSemanticDecorator) {
            final DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) eObject;
            result = SessionManager.INSTANCE.getSession(dSemanticDecorator.getTarget());
        } else if (view.eContainer() instanceof View) {
            result = DiagramSessionHelper.findSession((View) view.eContainer());
        }
        return result;
    }

}
