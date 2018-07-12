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
package org.eclipse.sirius.server.ui.autostart.internal;

import java.util.Optional;

import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.ui.intro.IIntroPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

/**
 * Early startup class in charge of activating the Sirius server plugin.
 *
 * @author sbegaudeau
 */
public class SiriusServerStartup implements IStartup {

	/**
	 * The identifier of the bundler containing the Sirius server.
	 */
	private static final String SIRIUS_SERVER_PLUGIN_ID = "org.eclipse.sirius.server"; //$NON-NLS-1$

	/**
	 * The description of the job.
	 */
	private static final String DESCRIPTION = "Starting Sirius Server"; //$NON-NLS-1$

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	@Override
	public void earlyStartup() {
		Display display = Display.getDefault();

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				IWorkbench workbench = PlatformUI.getWorkbench();
				SiriusServerStartup.this.closeIntro(workbench);

				IExtensionTracker extensionTracker = workbench.getExtensionTracker();
				if (extensionTracker == null || workbench.getWorkbenchWindowCount() == 0) {
					display.timerExec(1000, this);
				} else {
					Job job = SiriusServerStartup.this.getStartupJob();
					job.setPriority(Job.DECORATE);
					job.schedule(1000);
				}
			}
		};

		display.asyncExec(runnable);
	}

	/**
	 * Closes the welcome page displayed at the startup of the product.
	 *
	 * @param workbench
	 *            The workbench
	 */
	private void closeIntro(IWorkbench workbench) {
		IIntroManager introManager = workbench.getIntroManager();
		boolean hasIntro = introManager.hasIntro();
		if (hasIntro) {
			Optional<IIntroPart> optionalIntroPart = Optional.ofNullable(introManager.getIntro());
			optionalIntroPart.ifPresent(introPart -> introManager.closeIntro(introPart));
		}
	}

	/**
	 * Returns the startup job.
	 *
	 * @return The startup job
	 */
	private Job getStartupJob() {
		Job job = Job.create(DESCRIPTION, (ICoreRunnable) monitor -> {
			Display.getDefault().asyncExec(() -> {
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
			});
		});
		return job;
	}

}
