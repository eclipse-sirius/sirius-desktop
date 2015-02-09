/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;

import com.google.common.collect.Lists;

/**
 * An {@link IResourceChangeListener} that removes the resource from a cache of
 * files if existing in it.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class WorkspaceFileResourceChangeListener implements IResourceChangeListener {

    /**
     * Map associating a {@link File} uri, as a Sring, to the {@link File}.
     */
    private Map<String, File> uriToFileMap = new HashMap<String, File>();

    /**
     * Map associating a {@link File} to its read status (file != null &&
     * file.exists() && file.canRead()).
     */
    private Map<File, Boolean> readStatusFileMap = new HashMap<File, Boolean>();

    /**
     * Map associating a {@link File} to its {@link URL}, in order to avoid recomputing it
     * multiple times.
     */
    private Map<File, URL> fileURLMap = new HashMap<File, URL>();

    /**
     * Returns the shared instance.
     * 
     * @return the {@link WorkspaceFileResourceChangeListener} instance.
     */
    public static WorkspaceFileResourceChangeListener getInstance() {
        return WorkspaceResourceChangeListenerHolder.instance;
    }

    private static class WorkspaceResourceChangeListenerHolder {
        private static WorkspaceFileResourceChangeListener instance;
        static {
            instance = new WorkspaceResourceChangeListenerImpl();
            instance.init();
        }

    }

    /**
     * Init the {@link IResourceChangeListener}.
     * 
     */
    public void init() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        // Do nothing.
    }

    /**
     * Returns the {@link ResourceDeltaVisitor} to use for visiting
     * changes.
     * 
     * @return the {@link ResourceDeltaVisitor} to use for visiting changes
     */
    protected ResourceDeltaVisitor getResourceDeltaVisitor() {
        return new ResourceDeltaVisitor();
    }

    protected class ResourceDeltaVisitor implements IResourceDeltaVisitor {

        List<String> changedFilesURI = Lists.<String> newArrayList();

        public ResourceDeltaVisitor() {
        }

        /**
         * {@inheritDoc}
         * 
         * This method has been overridden to check if the file is contained in
         * a cache of files.
         */
        protected boolean visitFile(IResourceDelta delta, IFile file) {
            String uri = file.getFullPath().makeRelative().toString();
            if (hasURIOfFile(uri)) {
                changedFilesURI.add(uri);
            }
            return false;
        }

        @Override
        public boolean visit(IResourceDelta delta) throws CoreException {
            boolean visitChildren = true;
            IResource res = delta.getResource();
            if (res.getType() == IResource.FILE) {
                visitChildren = visitFile(delta, (IFile) res);
            }
            return visitChildren;
        }

    }

    /**
     * Finds a {@link File} from a file URI using a cache. If it is not found,
     * the {@link File} is loaded and added to the cache.
     * 
     * @param uri
     *            the path of the file
     * @return the cached {@link File}.
     */
    public File getFileFromURI(String uri) {
        if (uriToFileMap.containsKey(uri)) {
            return uriToFileMap.get(uri);
        } else {
            final File file = FileProvider.getDefault().getFile(new Path(uri));
            uriToFileMap.put(uri, file);
            return file;
        }
    }

    /**
     * Checks if an uri exists in the cache of files.
     * 
     * @param uri
     *            the uri of the file to check.
     * @return if an uri exists in the cache of files.
     */
    public boolean hasURIOfFile(String uri) {
        return uriToFileMap.containsKey(uri);
    }

    /**
     * 
     * Finds the read status (exists() && canRead()) of a {@link File} using a
     * cache. If it is not found, the status is computed and added to the cache.
     * 
     * @param file
     *            {@link File} to request its read status.
     * @return the read status of the {@link File}.
     */
    public boolean getReadStatusOfFile(File file) {
        if (readStatusFileMap.containsKey(file)) {
            return readStatusFileMap.get(file);
        } else {
            boolean fileStatus = file != null && file.exists() && file.canRead();
            readStatusFileMap.put(file, fileStatus);
            try {
                // Addition to the file to url cache as well
                fileURLMap.put(file, file.toURI().toURL());
            } catch (MalformedURLException e) {
                DiagramUIPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, DiagramUIPlugin.ID, e.getMessage()));
            }
            return fileStatus;
        }
    }

    /**
     * Clears the files caches.
     */
    public void dispose() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        workspace.removeResourceChangeListener(this);
        uriToFileMap.clear();
        readStatusFileMap.clear();
        fileURLMap.clear();
    }

    /**
     * Removes a files from the caches.
     * 
     * @param uri
     *            of the {@link File} to remove from the caches.
     */
    public void removeFileStatusAndURIFromMaps(String uri) {
        readStatusFileMap.remove(uriToFileMap.get(uri));
        fileURLMap.remove(uriToFileMap.get(uri));
        uriToFileMap.remove(uri);
    }

    /**
     * Returns an image descriptor for the given image file. <br/>
     * Note that this method uses a cache of {@link File} to {@link URL}, that
     * can improve performances on diagrams with a lot of instance of (the same)
     * image.
     * 
     * @param file
     *            the image file.
     * @return the image descriptor.
     */
    public ImageDescriptor findImageDescriptor(File file) throws MalformedURLException {
        if (fileURLMap.containsKey(file)) {
            return DiagramUIPlugin.Implementation.getURLImageDescriptor(fileURLMap.get(file));
        }
        return DiagramUIPlugin.Implementation.getURLImageDescriptor(file.toURI().toURL());
    }

}
