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

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Test session is correctly opened.
 * 
 * @author jdupont
 * 
 */
public class SessionCondition extends DefaultCondition {

    private final UILocalSession session;

    private final String viewpointName;

    private final String representationName;

    private final String representationInstanceName;

    /**
     * Constructor for SessionCondition.
     * 
     * @param session
     *            the UILocalSession.
     * @param viewpointName
     *            the viewpoint name.
     * @param representationName
     *            the name of representation.
     * @param representationInstanceName
     *            the name of representation instance.
     * 
     */
    public SessionCondition(UILocalSession session, String viewpointName, String representationName, String representationInstanceName) {
        this.session = session;
        this.viewpointName = viewpointName;
        this.representationName = representationName;
        this.representationInstanceName = representationInstanceName;
    }

    @Override
    public String getFailureMessage() {
        return "The session " + session + "is not correctly opened";
    }

    @Override
    public boolean test() throws Exception {
        boolean sessionOpenedCorectly;
        try {
            session.getLocalSessionBrowser().perCategory().selectViewpoint(viewpointName).selectRepresentation(representationName)
                    .selectRepresentationInstance(representationInstanceName, UIDiagramRepresentation.class).open();
            sessionOpenedCorectly = true;
        } catch (WidgetNotFoundException wnfe) {
            sessionOpenedCorectly = false;
        }
        return sessionOpenedCorectly;
    }

}
