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
package org.eclipse.sirius.ui.business.api.action;

import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * A listener that will be notified any time a user executes a Refresh action.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public interface IRefreshActionListener {

    /**
     * This method is called whenever a user is about to execute a Refresh
     * action on a given representation.
     * 
     * @param refreshedRepresentation
     *            the Representation that is about to be refreshed
     */
    void notifyRepresentationIsAboutToBeRefreshed(DRepresentation refreshedRepresentation);
}
