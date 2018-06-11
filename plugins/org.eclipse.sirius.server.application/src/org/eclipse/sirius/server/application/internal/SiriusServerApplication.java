/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.application.internal;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * The entry point of the Sirius Server application.
 *
 * @author sbegaudeau
 */
public class SiriusServerApplication implements IApplication {

    /**
     * The application context.
     */
    private IApplicationContext appContext;

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
     */
    @Override
    public Object start(IApplicationContext context) throws Exception {
        this.appContext = context;
        return IApplicationContext.EXIT_ASYNC_RESULT;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.equinox.app.IApplication#stop()
     */
    @Override
    public void stop() {
        appContext.setResult(EXIT_OK, this);
    }

}
