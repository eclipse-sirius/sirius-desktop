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
package org.eclipse.sirius.server.internal;

import java.net.InetSocketAddress;
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
	 * The VM argument used to customize the jetty {@link Server} port.
	 */
	private static final String PORT_VMARG = "SIRIUS_SERVER_PORT"; //$NON-NLS-1$

	/**
	 * The default hostname.
	 */
	private static final String HOSTNAME = "localhost"; //$NON-NLS-1$

	/**
	 * The default port.
	 */
	private static final String PORT = "8080"; //$NON-NLS-1$

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
			int port = Integer.valueOf(System.getProperty(PORT_VMARG, PORT)).intValue();
			InetSocketAddress address = new InetSocketAddress(HOSTNAME, port);
			this.server = new Server(address);
			this.server.setHandler(new HandlerCollection());

			SiriusServerPlugin.getPlugin().getSiriusServerConfigurators().forEach(configurator -> configurator.configure(server));

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
}
