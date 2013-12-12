/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.SessionService;

/**
 * Session services.
 * 
 * @author cbrun
 * 
 */
public class SessionServiceImpl implements SessionService {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionService#clearCustomData(java.lang.String,
     *      org.eclipse.emf.ecore.EObject)
     */
    public void clearCustomData(final String key, final EObject associatedInstance) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionService#clearCustomData(org.eclipse.emf.ecore.EObject)
     */
    public void clearCustomData(final EObject associatedInstance) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionService#getCustomData(java.lang.String,
     *      org.eclipse.emf.ecore.EObject)
     */
    public Collection<EObject> getCustomData(final String key, final EObject associatedInstance) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionService#putCustomData(java.lang.String,
     *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    public void putCustomData(final String key, final EObject associatedInstance, final EObject data) {
        // TODO Auto-generated method stub

    }

}
