/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.resourcelistener.ISessionFileLoadingListener;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.BundleContext;

public class SessionEditorPlugin extends EMFPlugin {
    /**
     * Keep track of the singleton.
     */
    public static final SessionEditorPlugin INSTANCE = new SessionEditorPlugin();

    /**
     * Keep track of the singleton.
     */
    private static Implementation plugin;

    /**
     * Create the instance.
     */
    public SessionEditorPlugin() {
        super(new ResourceLocator[0]);
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     */
    public static class Implementation extends EclipseUIPlugin {
        /**
         * Listener used to open the session's editor when the modeling project
         * has been expanded whereas it's session was not loaded.
         */
        private ISessionFileLoadingListener modelingProjectExpansionListener;

        /**
         * Creates an instance.
         */
        public Implementation() {
            plugin = this;
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
            modelingProjectExpansionListener = new ISessionFileLoadingListener() {

                @Override
                public void notifySessionLoadedFromModelingProjectExpansion(Session session) {
                    URI uri = session.getSessionResource().getURI();

                    PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
                        try {
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new URIEditorInput(uri), SessionEditor.EDITOR_ID);
                        } catch (PartInitException e) {
                            error("An error occurred while opening the session's editor.", e); //$NON-NLS-1$
                        }

                    });

                }
            };
            SiriusEditPlugin.getPlugin().addSessionFileLoadingListener(modelingProjectExpansionListener);
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            SiriusEditPlugin.getPlugin().removeSessionFileLoadingListener(modelingProjectExpansionListener);
            modelingProjectExpansionListener = null;
            super.stop(context);
        }

        /**
         * Logs an error in the error log.
         * 
         * @param message
         *            the message to log (optional).
         * @param e
         *            the exception (optional).
         */
        public void error(final String message, final Exception e) {
            String msgToDisplay = message;
            if (message == null && e != null) {
                msgToDisplay = e.getMessage();
            }
            if (e instanceof CoreException) {
                this.getLog().log(((CoreException) e).getStatus());
            } else {
                final IStatus status = new Status(IStatus.ERROR, this.getBundle().getSymbolicName(), msgToDisplay, e);
                this.getLog().log(status);
            }
        }
    }
}
