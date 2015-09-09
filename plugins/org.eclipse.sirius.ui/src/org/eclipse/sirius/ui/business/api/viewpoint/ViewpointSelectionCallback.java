/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.viewpoint;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.ViewpointSelector;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;

/**
 * The callback for selection.
 * <p>
 * All methods must be executed in transactional mode.
 * </p>
 *
 * @author mchauvin
 */
public class ViewpointSelectionCallback implements ViewpointSelection.Callback {

    @Override
    public void selectViewpoint(Viewpoint viewpoint, Session session, IProgressMonitor monitor) {
        selectViewpoint(viewpoint, session, true, monitor);
    }

    @Override
    public void selectViewpoint(Viewpoint viewpoint, Session session, boolean createNewRepresentations, IProgressMonitor monitor) {
        try {
            monitor.beginTask(MessageFormat.format(Messages.ViewpointSelectionCallback_selection, viewpoint.getName()), 3);
            new ViewpointSelector(session).selectViewpoint(viewpoint, createNewRepresentations, monitor);
        } finally {
            monitor.done();
        }
    }

    @Override
    public void deselectViewpoint(Viewpoint deselectedViewpoint, Session session, IProgressMonitor monitor) {
        try {
            monitor.beginTask(MessageFormat.format(Messages.ViewpointSelectionCallback_deselection, deselectedViewpoint.getName()), 1);
            new ViewpointSelector(session).deselectViewpoint(deselectedViewpoint, monitor);
        } finally {
            monitor.done();
        }
    }

}
