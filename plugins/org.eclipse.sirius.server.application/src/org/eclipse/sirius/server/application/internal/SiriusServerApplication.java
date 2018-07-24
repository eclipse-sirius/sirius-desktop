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

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

/**
 * The entry point of the Sirius Server application.
 *
 * @author sbegaudeau
 */
public class SiriusServerApplication implements IApplication {

    /**
     * The identifier of the bundler containing the Sirius server.
     */
    private static final String SIRIUS_SERVER_PLUGIN_ID = "org.eclipse.sirius.server"; //$NON-NLS-1$

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

        Bundle siriusServerBundle = Platform.getBundle(SIRIUS_SERVER_PLUGIN_ID);
        if (siriusServerBundle != null && siriusServerBundle.getState() != Bundle.ACTIVE) {
            try {
                siriusServerBundle.start(Bundle.START_TRANSIENT);
            } catch (BundleException exception) {
                // @CHECKSTYLE:OFF
                exception.printStackTrace();
                // @CHECKSTYLE:ON
            }
        }
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
