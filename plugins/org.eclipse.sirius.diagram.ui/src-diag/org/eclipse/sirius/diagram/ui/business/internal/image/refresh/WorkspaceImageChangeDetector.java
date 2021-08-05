/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.internal.image.refresh;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.ext.base.Option;

/**
 * Remove images from cache when required.
 * 
 * @author jdupont
 */
public class WorkspaceImageChangeDetector implements IResourceDeltaVisitor {

    private boolean workspaceImageChangeDetected;

    /**
     * Overridden to detect a support image change in the workspace.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean visit(IResourceDelta delta) throws CoreException {
        boolean continueToVisit = true;
        boolean isWorkspaceImageChange = false;
        IResource resource = delta.getResource();
        if (isWorkspaceImage(resource)) {
            switch (delta.getKind()) {
            case IResourceDelta.ADDED:
                isWorkspaceImageChange = true;
                break;
            case IResourceDelta.CHANGED:
            case IResourceDelta.REMOVED:
                isWorkspaceImageChange = needClearCache(delta.getResource());
                break;
            default:
                break;
            }
            workspaceImageChangeDetected = workspaceImageChangeDetected || isWorkspaceImageChange;
            continueToVisit = !workspaceImageChangeDetected;
        }
        return continueToVisit;
    }

    /**
     * Tells if the specified resource correspond to a supported workspace image.
     * 
     * @param resource
     *            the specified resource
     * @return true if the specified resource correspond to a supported workspace image, false else
     */
    private boolean isWorkspaceImage(IResource resource) {
        boolean isWorkspaceImage = resource != null && resource.getType() == IResource.FILE && resource.getFileExtension() != null && isSupportedImageFormat(resource.getFileExtension());
        return isWorkspaceImage;
    }

    /**
     * Tell if the specified fileExtension is one of a supported image format.
     * 
     * @param fileExtension
     *            the file extension of a image file.
     * @return true the specified file extension if one of a supported image format.
     */
    private boolean isSupportedImageFormat(final String fileExtension) {
        boolean isSupportedImageFormat = false;
        for (ImageFileFormat element : ImageFileFormat.VALUES) {
            if (element.getName().toLowerCase().equals(fileExtension.toLowerCase())) {
                isSupportedImageFormat = true;
                break;
            }
        }
        return isSupportedImageFormat;
    }

    /**
     * Return true if at least one editor need refresh.
     * 
     * @return boolean
     */
    public boolean isAtLeastOneEditorToRefresh() {
        return workspaceImageChangeDetected;
    }

    /**
     * Clear cache if necessary and return true if a refresh of figure of opened editors must be done, false otherwise.
     * 
     * @param delta
     *            , resource delta
     * 
     * @return boolean
     */
    private boolean needClearCache(IResource resource) {
        boolean cacheUpdated = false;
        String resourceExtension = resource.getFileExtension();
        if (WorkspaceImageFigure.isSvgImage(resourceExtension)) {
            String svgUri = resource.getFullPath().toString();
            Option<String> removed = SVGWorkspaceImageFigure.removeFromCache(svgUri);
            if (removed.some()) {
                cacheUpdated = true;
            }
        } else {
            URL url;
            try {
                url = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().append(resource.getFullPath()).toOSString()).toURI().toURL();
            } catch (MalformedURLException e) {
                DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.WorkspaceImageChangeDetector_invalidUri, e.getMessage()));
                return false;
            }
            ImageDescriptor bundledImageDescriptor = ImageDescriptor.createFromURL(url);
            boolean removed = DiagramUIPlugin.getPlugin().removeCacheImage(bundledImageDescriptor);
            // If a removed cache action is do, a refresh opened editors
            // is
            // required
            if (removed) {
                cacheUpdated = true;
            }
        }
        return cacheUpdated;
    }
}
