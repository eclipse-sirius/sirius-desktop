/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo.
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
package org.eclipse.sirius.server.diagram.internal;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.server.images.api.ISiriusServerImagesConstants;
import org.eclipse.sirius.server.internal.SiriusServerPlugin;
import org.eclipse.sirius.services.diagram.api.ISiriusDiagramImagePathProvider;

/**
 * The image path provider.
 *
 * @author sbegaudeau
 */
@SuppressWarnings("restriction") // We need to access the URI of the server
public class SiriusServerDiagramImagePathProvider implements ISiriusDiagramImagePathProvider {

    /**
     * The separator of the segments of the URI.
     */
    private static final String SLASH = "/"; //$NON-NLS-1$

    /**
     * The platform resource URIs prefix.
     */
    private static final String PLATFORM_RESOURCE = "platform:/resource/"; //$NON-NLS-1$

    /**
     * The separator between the key and value in the query part of the URL.
     */
    private static final String EQUAL = "="; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.api.ISiriusDiagramImagePathProvider#getStaticImagePath(org.eclipse.sirius.diagram.WorkspaceImage)
     */
    @Override
    public Optional<String> getStaticImagePath(WorkspaceImage workspaceImage) {
        URI serverURI = SiriusServerPlugin.getPlugin().getServerURI();

        String workspaceImagePath = workspaceImage.getWorkspacePath();
        if (workspaceImagePath.startsWith(SLASH)) {
            workspaceImagePath = workspaceImagePath.substring(SLASH.length());
        }

        String path = ISiriusServerImagesConstants.IMAGES_PATH + SLASH + workspaceImagePath;
        Optional<String> optionalPath = Optional.empty();
        try {
            URI imageURI = new URI(serverURI.getScheme(), serverURI.getUserInfo(), serverURI.getHost(), serverURI.getPort(), path, null, null);
            optionalPath = Optional.of(imageURI.toString());
        } catch (URISyntaxException e) {
            IStatus status = new Status(IStatus.ERROR, SiriusServerDiagramPlugin.PLUGIN_ID, e.getMessage(), e);
            SiriusServerDiagramPlugin.getPlugin().log(status);
        }
        return optionalPath;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.api.ISiriusDiagramImagePathProvider#getLabelProviderImagePath(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Optional<String> getLabelProviderImagePath(EObject eObject) {
        URI serverURI = SiriusServerPlugin.getPlugin().getServerURI();

        org.eclipse.emf.common.util.URI uri = EcoreUtil.getURI(eObject);
        String resourceURI = uri.trimFragment().toString();
        if (resourceURI.startsWith(PLATFORM_RESOURCE)) {
            resourceURI = resourceURI.substring(PLATFORM_RESOURCE.length());
        }
        String uriFragment = uri.fragment();

        String path = ISiriusServerImagesConstants.IMAGES_PATH + SLASH + resourceURI;
        String fragment = ISiriusServerImagesConstants.FRAGMENT + EQUAL + uriFragment;
        Optional<String> optionalPath = Optional.empty();
        try {
            URI imageURI = new URI(serverURI.getScheme(), serverURI.getUserInfo(), serverURI.getHost(), serverURI.getPort(), path, fragment, null);
            optionalPath = Optional.of(imageURI.toString());
        } catch (URISyntaxException e) {
            IStatus status = new Status(IStatus.ERROR, SiriusServerDiagramPlugin.PLUGIN_ID, e.getMessage(), e);
            SiriusServerDiagramPlugin.getPlugin().log(status);
        }
        return optionalPath;
    }

}
