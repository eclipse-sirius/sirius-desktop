/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.collect.Iterables;

/**
 * Label decorator for files handled by a session.
 * 
 * @author mporhel
 * 
 */
public class SessionLabelDecorator implements ILightweightLabelDecorator {

    /**
     * Id of the decorator.
     */
    public static final String ID = "org.eclipse.sirius.ui.session.decorator";

    /**
     * Default image descriptor for the error overlay.
     */
    public static final ImageDescriptor VIEWPOINT_OVERLAY_DESC = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/ovr16/SessionDecorator.gif"); //$NON-NLS-1$;

    /**
     * {@inheritDoc}
     */
    public void addListener(ILabelProviderListener listener) {
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
    }

    /**
     * {@inheritDoc}
     */
    public boolean isLabelProperty(Object element, String property) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void removeListener(ILabelProviderListener listener) {

    }

    /**
     * {@inheritDoc}
     */
    public void decorate(Object element, IDecoration decoration) {

        if (element instanceof IFile) {
            IFile file = (IFile) element;
            IProject project = ((IFile) element).getProject();

            /*
             * do not decorate if the project has the modeling nature as each
             * model file in the project will be automatically added to the
             * session. We may provide in the future a decorator to indicate a
             * file that was not correcty loaded.
             */
            if (ModelingProject.hasModelingProjectNature(project)) {
                return;
            }

            URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
            for (Session session : SessionManager.INSTANCE.getSessions()) {
                if (session.isOpen() && fileURI != null) {
                    // Do not decorate main aird with image
                    Iterable<Resource> handledResources = Iterables.concat(session.getSemanticResources(), session.getReferencedSessionResources());
                    if (session instanceof DAnalysisSessionEObject) {
                        handledResources = Iterables.concat(handledResources, ((DAnalysisSessionEObject) session).getControlledResources());
                    }

                    for (Resource res : handledResources) {
                        if (res.getURI().equals(fileURI)) {
                            decoration.addOverlay(VIEWPOINT_OVERLAY_DESC);
                        }
                    }
                }
            }
        }
    }
}
