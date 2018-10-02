/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.server.internal;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;

/**
 * Utility class used to start and stop the Sirius server.
 *
 * @author sbegaudeau
 */
public class SiriusServerManager {

    /**
     * The system property key used to retrieve the name of the operating
     * system.
     */
    private static final String OS_NAME = "os.name"; //$NON-NLS-1$

    /**
     * The name of the windows operating system.
     */
    private static final String WINDOWS_NAME = "win"; //$NON-NLS-1$

    /**
     * The host used to listen to all interfaces.
     */
    private static final String ALL_INTERFACES_HOST = "0.0.0.0"; //$NON-NLS-1$

    /**
     * The localhost host.
     */
    private static final String LOCALHOST = "localhost"; //$NON-NLS-1$

    /**
     * The Jetty server.
     */
    private Server server;

    /**
     * Starts the HTTP server.
     */
    @SuppressWarnings({ "checkstyle:illegalcatch" })
    public void start() {
        if (this.server == null || !this.server.isRunning()) {
            this.server = new Server();
            this.server.setHandler(new HandlerCollection());

            SiriusServerConfigurator siriusServerConfigurator = new SiriusServerConfigurator();
            siriusServerConfigurator.configure(server);

            SiriusServerPlugin.getPlugin().getSiriusServerConfigurators().forEach(configurator -> configurator.configure(server));

            SiriusServerHeaderConfigurator headerConfigurator = new SiriusServerHeaderConfigurator();
            headerConfigurator.configure(server);

            try {
                this.server.start();
            } catch (Exception exception) {
                IStatus status = new Status(IStatus.ERROR, SiriusServerPlugin.PLUGIN_ID, Messages.SiriusServerManager_cannotStartServer, exception);
                SiriusServerPlugin.getPlugin().log(status);
            }
        }
    }

    /**
     * Stops the HTTP server.
     */
    @SuppressWarnings({ "checkstyle:illegalcatch" })
    public void stop() {
        Optional.ofNullable(this.server).ifPresent(s -> {
            try {
                s.stop();
            } catch (Exception exception) {
                IStatus status = new Status(IStatus.ERROR, SiriusServerPlugin.PLUGIN_ID, Messages.SiriusServerManager_cannotStopServer, exception);
                SiriusServerPlugin.getPlugin().log(status);
            }
        });
    }

    /**
     * Returns the address on which the server is listening.
     *
     * @return the address on which the server is listening.
     */
    public URI getURI() {
        URI uri = this.server.getURI();

        String osName = System.getProperty(OS_NAME).toLowerCase();
        if (osName.indexOf(WINDOWS_NAME) >= 0 && ALL_INTERFACES_HOST.equals(uri.getHost())) {
            try {
                uri = new URI(uri.getScheme(), uri.getUserInfo(), LOCALHOST, uri.getPort(), uri.getPath(), uri.getRawQuery(), uri.getRawFragment());
            } catch (URISyntaxException exception) {
                IStatus status = new Status(IStatus.ERROR, SiriusServerPlugin.PLUGIN_ID, exception.getMessage(), exception);
                SiriusServerPlugin.getPlugin().getLog().log(status);
            }
        }

        return uri;
    }
}
