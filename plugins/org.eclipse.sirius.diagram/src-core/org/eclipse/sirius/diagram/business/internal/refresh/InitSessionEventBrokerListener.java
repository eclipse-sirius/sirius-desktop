/*******************************************************************************
* Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.refresh;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * {@link SessionManagerListener} initializing an instance of {@link SiriusDiagramSessionEventBroker} for each opened
 * session to always have Sirius model synchronized with the GMF one. Necessary when doing a refresh while activating a
 * viewpoint without any editor opened for the session. See 543651.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class InitSessionEventBrokerListener implements SessionManagerListener {

    /**
     * Default constructor.
     */
    public InitSessionEventBrokerListener() {
        System.out.println();
    }

    @Override
    public void notifyAddSession(Session newSession) {
        SiriusDiagramSessionEventBroker.getInstance(newSession);

    }

    @Override
    public void notifyRemoveSession(Session removedSession) {
    }

    @Override
    public void viewpointSelected(Viewpoint selectedSirius) {
    }

    @Override
    public void viewpointDeselected(Viewpoint deselectedSirius) {
    }

    @Override
    public void notify(Session updated, int notification) {
    }

}
