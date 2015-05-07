/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.eef.listeners;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.ResourceSetAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManagerListener.Stub;

/**
 * {@link SessionManagerListener} managing the EEF adapters.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class EEFSessionManagerListener extends Stub {

    @Override
    public void notifyRemoveSession(Session removedSession) {
        ResourceSet resourceSet = removedSession.getTransactionalEditingDomain().getResourceSet();
        Adapter existingAdapter = EcoreUtil.getExistingAdapter(resourceSet, ResourceSetAdapter.class);
        if (existingAdapter != null) {
            resourceSet.eAdapters().remove(existingAdapter);
        }
    }

}
