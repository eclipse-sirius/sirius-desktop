/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Event containing information about a viewpoint that should change its activation status in the current session.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ViewpointStateChangeEvent {
    /**
     * The {@link Viewpoint} subject to the status change.
     */
    private Viewpoint viewpoint;

    /**
     * True if the viewpoint should be activated in the associated session. False if it should be deactivated.
     */
    private boolean shouldBeActivated;

    /**
     * Constructor.
     * 
     * @param viewpoint
     *            The {@link Viewpoint} subject to the status change.
     * @param shouldBeActivated
     *            True if the viewpoint should be activated in the associated session. False if it should be
     *            deactivated.
     */
    public ViewpointStateChangeEvent(Viewpoint viewpoint, boolean shouldBeActivated) {
        super();
        this.viewpoint = viewpoint;
        this.shouldBeActivated = shouldBeActivated;
    }

    /**
     * True if the viewpoint should be activated in the current session. False if it should be deactivated.
     * 
     * @return true if the viewpoint should be activated in the current session. False if it should be deactivated.
     */
    public boolean shouldBeActivated() {
        return shouldBeActivated;
    }

    /**
     * Returns the {@link Viewpoint} subject to the status change.
     * 
     * @return the {@link Viewpoint} subject to the status change.
     */
    public Viewpoint getViewpoint() {
        return viewpoint;
    }
}
