/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.xtext.internal;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManagerListener.Stub;

/**
 * A session manager listener which will switch the saving policy of every
 * session which is getting created by one which has special support for Xtext
 * resources.
 * 
 * @author cbrun
 * 
 */
public class XtextSessionManagerListener extends Stub {

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyAddSession(Session newSession) {
        super.notifyAddSession(newSession);
        /*
         * Due to bug 432795 in Xtext, we need to override the default saving
         * options for Xtext resources to avoid hitting false positive
         * validation errors preventing the save. Furthermore when one of this
         * error is thrown the underlying file is emptied which makes this quite
         * critical.
         */
        newSession.setSavingPolicy(new XtextSavingPolicy(newSession.getSavingPolicy()));
    }
}
