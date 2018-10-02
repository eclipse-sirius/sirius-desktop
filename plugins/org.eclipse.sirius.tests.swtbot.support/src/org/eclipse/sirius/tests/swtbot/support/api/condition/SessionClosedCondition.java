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
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * A condition to wait until a session is closed.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SessionClosedCondition extends DefaultCondition implements ICondition {

    private final Session session;

    /**
     * Construct a condition to wait until a session is closed.
     * 
     * @param session
     *            the session on which do the test
     */
    public SessionClosedCondition(Session session) {
        this.session = session;
    }

    @Override
    public boolean test() throws Exception {
        return !session.isOpen();
    }

    @Override
    public String getFailureMessage() {
        return "session not closed";
    }

}
