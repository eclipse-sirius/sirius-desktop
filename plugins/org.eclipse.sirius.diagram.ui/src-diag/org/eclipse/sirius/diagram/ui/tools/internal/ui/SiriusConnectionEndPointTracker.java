/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.ui;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.tools.ConnectionEndpointTracker;

/**
 * Override ConnectionEndPointTracker to add a call to updateAutoexposeHelper()
 * for have the scroll diagram on the reconnect.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class SiriusConnectionEndPointTracker extends ConnectionEndpointTracker {

    /**
     * Default constructor.
     * 
     * @param cep
     *            connection edit part.
     */
    public SiriusConnectionEndPointTracker(ConnectionEditPart cep) {
        super(cep);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.tools.ConnectionEndpointTracker#handleDragInProgress()
     */
    @Override
    protected boolean handleDragInProgress() {
        super.handleDragInProgress();
        updateAutoexposeHelper();
        return true;
    }

}
