/**
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES
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

import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * A condition to wait until the {@link SessionManager} return the expected
 * number of sessions.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 */
public class OpenedSessionCondition extends DefaultCondition implements ICondition {

    private final int expectedNumber;

    /**
     * Construct a condition to wait until a session is closed.
     * 
     * @param expectedNumber
     *            the expected number of session
     */
    public OpenedSessionCondition(int expectedNumber) {
        this.expectedNumber = expectedNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        return expectedNumber == SessionManager.INSTANCE.getSessions().size();
    }

    @Override
    public String getFailureMessage() {
        return "The expected number of session was not reached.";
    }

}
