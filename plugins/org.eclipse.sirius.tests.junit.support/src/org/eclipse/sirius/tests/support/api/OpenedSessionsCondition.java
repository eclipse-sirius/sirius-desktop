/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.support.api;

import java.util.Collection;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        Collection<Session> knownSession = SessionManager.INSTANCE.getSessions();

        // Sessions are added to the SessionManager during their opening,
        // but they might not be completelty opened yet.
        Iterable<Session> openedSessions = Iterables.filter(knownSession, new Predicate<Session>() {
            @Override
            public boolean apply(Session input) {
                return input.isOpen();
            }
        });
        return expectedNumber == Iterables.size(openedSessions);
    }

    @Override
    public String getFailureMessage() {
        return "The expected number of session was not reached.";
    }
}