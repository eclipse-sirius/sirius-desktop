/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * A condition waiting for a viewpoints selection.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class ViewpointSelectionCondition extends DefaultCondition {

    private final Session session;

    private final String viewpointName;

    /**
     * Constructor.
     * 
     * @param session
     *            the current {@link Session}
     * @param viewpointName
     *            the name of the viewpoint we are waiting for its selection
     */
    public ViewpointSelectionCondition(final Session session, final String viewpointName) {
        this.session = session;
        this.viewpointName = viewpointName;
    }

    @Override
    public String getFailureMessage() {
        return "The expected viewpoint has not been selected";
    }

    @Override
    public boolean test() throws Exception {
        for (Viewpoint viewpoint : session.getSelectedViewpoints(false)) {
            if (viewpoint.getName().equals(viewpointName)) {
                return true;
            }
        }
        return false;
    }

}
