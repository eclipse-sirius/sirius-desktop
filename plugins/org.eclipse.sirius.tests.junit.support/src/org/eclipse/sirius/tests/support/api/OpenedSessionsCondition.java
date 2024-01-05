/*******************************************************************************
 * Copyright (c) 2016, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.support.api;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

/**
 * A condition to wait until the {@link SessionManager} return the expected
 * number of sessions.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 */
public class OpenedSessionsCondition implements ICondition {

    private final int expectedNumber;

    /**
     * Construct a condition to wait until a session is closed.
     * 
     * @param expectedNumber
     *            the expected number of session
     */
    public OpenedSessionsCondition(int expectedNumber) {
        this.expectedNumber = expectedNumber;
    }

    @Override
    public boolean test() throws Exception {
        return SessionManager.INSTANCE.getSessions().stream().filter(Session::isOpen).count() == expectedNumber;
    }

    @Override
    public String getFailureMessage() {
        return "The expected number of sessions (" + expectedNumber + ") was not reached.";
    }
}