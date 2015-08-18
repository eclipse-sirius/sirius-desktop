/*******************************************************************************
 * Copyright (c) 2005, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.resource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

import com.google.common.collect.Lists;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.ext.base.cache.LRUCache;

/**
 * To find a module file in the workspace or in the plugins.
 * 
 * @author cbrun
 * 
 */
public class FileProvider {

    /**
     * The sole instance.
     */
    private static FileProvider instance;

    /**
     * Saves the plugin for each file.
     */
    private Map<File, String> file2plugin = new LRUCache<File, String>(10, 50);

    /**
     * Saves the relative path for each file.
     */
    private Map<File, String> file2path = new LRUCache<File, String>(10, 50);

    private Map<String, Map<String, List<URL>>> bundleName2mtPaths = new LRUCache<String, Map<String, List<URL>>>(10, 50);

    private Set<IFileGetter> fileGetters = new LinkedHashSet<IFileGetter>();

    /**
     * Gets the sole instance.
     * 
     * @return the sole instance
     */
    public static FileProvider getDefault() {
        if (FileProvider.instance == null) {
            FileProvider.instance = new FileProvider();
        }
        return FileProvider.instance;
    }

    /**
     * Gets the identifier of the plugin that contains the given file.
     * 
     * @param file
     *            is the file
     * @return the plugin ID
     */
    public String getPluginId(final File file) {
        return file2plugin.get(file);
    }

    /**
     * Gets the relative path of the given file.
     * 
     * @param file
     *            is the file
     * @return the relative path
     */
    public String getRelativePath(final File file) {
        return file2path.get(file);
    }

    /**
     * Gets the file for the full name in the given plugin.
     * 
     * @param pluginId
     *            is the plugin
     * @param fullName
     *            is the full name of the resource in the plugin
     * @param extension
     *            is the extension of the file to search
     * @return the file
     */
    public File getFile(final String pluginId, final String fullName, final String extension) {
        final IPath fullPath = new Path(fullName.replaceAll("\\.", "/")).addFileExtension(extension); //$NON-NLS-1$ //$NON-NLS-2$
        return getFile(pluginId, fullPath);
    }

    /**
     * Gets the file for the given full path in the workspace or in the plugins.
     * 
     * @param fullPath
     *            is the full path of the file
     * @return the file
     */
    public File getFile(final IPath fullPath) {
        File file = null;

        if (fullPath != null && fullPath.segmentCount() > 0) {

            // Step 1 : if path contains a specific URI
            for (IFileGetter getter : fileGetters) {
                file = getter.getFile(fullPath);
                if (file != null) {
                    break;
                }
            }
            if (file == null) {
                final IFile eclipseFile = FileProvider.findFile(fullPath);
                if (eclipseFile != null) {
                    file = eclipseFile.getLocation().toFile();
                } else {
                    final String pluginId = fullPath.segment(0);
                    file = getFile(pluginId, fullPath.removeFirstSegments(1)); // remove
                    // '/Project'
                }
            }

        }
        return file;
    }

    /**
     * Register a {@link IFileGetter}.
     * 
     * @param fileGetter
     *            the {@link IFileGetter} to register
     */
    public void registerFileGetter(IFileGetter fileGetter) {
        this.fileGetters.add(fileGetter);
    }

    /**
     * Gets the file for the relative path in the given plugin.
     * 
     * @param pluginId
     *            is the plugin
     * @param relativePath
     *            is the relative path in the plugin
     * @return the file
     */
    public File getFile(final String pluginId, final IPath relativePath) {
        return getFile(pluginId, relativePath, true);
    }

    /**
     * Gets the file for the relative path in the given plugin.
     * 
     * @param pluginId
     *            is the plugin
     * @param relativePath
     *            is the relative path in the plugin
     * @param requiredSearch
     *            true to search in the required bundles
     * @return the file
     */
    private File getFile(final String pluginId, final IPath relativePath, final boolean requiredSearch) {
        final Bundle bundle = Platform.getBundle(pluginId);
        File file = null;

        if (bundle != null) {
            URL url = bundle.getEntry(relativePath.toString());
            if (url == null && "mt".equals(relativePath.getFileExtension()) && relativePath.segmentCount() > 1) { //$NON-NLS-1$
                url = bundle.getEntry(relativePath.removeFirstSegments(1).toString());
                if (url == null) {
                    url = getRuntimeModeURL(bundle, relativePath);
                }
            }
            if (url != null) {
                file = getFileFromUrl(url, pluginId, relativePath, bundle);
                if (file != null) {
                    return file;
                }
            }

            if (requiredSearch) {
                file = getFileFromBundles(relativePath, bundle);
            }
        }
        return file;
    }

    private File getFileFromUrl(final URL url, final String pluginId, final IPath relativePath, final Bundle bundle) {
        final File file = new File(FileProvider.transformToAbsolutePath(url));
        if (file.exists()) {
            if (!file2plugin.containsKey(file)) {
                file2plugin.put(file, pluginId);
                file2path.put(file, relativePath.toString());
                // Copy the properties in the bundle area
                final Enumeration<?> allProperties = bundle.findEntries(relativePath.removeLastSegments(1).toString(), "*.properties", true); //$NON-NLS-1$
                while (allProperties != null && allProperties.hasMoreElements()) {
                    final URL propertyFileURL = (URL) allProperties.nextElement();
                    if (propertyFileURL != null) {
                        final File propertyFile = new File(FileProvider.transformToAbsolutePath(propertyFileURL));
                        if (propertyFile.exists()) {
                            file2plugin.put(propertyFile, pluginId);
                        }
                    }
                }
            }
            return file;
        }
        return null;
    }

    private File getFileFromBundles(final IPath relativePath, final Bundle bundle) {

        final String requiredBundles = bundle.getHeaders().get(Constants.REQUIRE_BUNDLE);
        if (requiredBundles != null) {
            final StringTokenizer st = new StringTokenizer(requiredBundles, ","); //$NON-NLS-1$
            while (st.hasMoreTokens()) {
                String id = st.nextToken().trim();
                final int iDot = id.indexOf(';');
                if (iDot > -1) {
                    id = id.substring(0, iDot).trim();
                }
                if (id.length() > 0) {
                    final File scriptFile = getFile(id, relativePath, false);
                    if (scriptFile != null) {
                        return scriptFile;
                    }
                }
            }
        }
        return null;
    }

    private URL getRuntimeModeURL(final Bundle bundle, final IPath relativePath) {
        Map<String, List<URL>> mtName2mtURLs = bundleName2mtPaths.get(bundle.getSymbolicName());
        if (mtName2mtURLs == null) {
            mtName2mtURLs = new HashMap<String, List<URL>>();
            bundleName2mtPaths.put(bundle.getSymbolicName(), mtName2mtURLs);
            final Enumeration<?> entries = bundle.findEntries("/", "*.mt", true); //$NON-NLS-1$ //$NON-NLS-2$
            if (entries != null) {
                while (entries.hasMoreElements()) {
                    final URL entry = (URL) entries.nextElement();
                    if (entry != null) {
                        final IPath path = new Path(entry.getPath());
                        if (path.segmentCount() > 0) {
                            final String name = path.lastSegment();
                            final List<URL> mt = getMTs(mtName2mtURLs, name);
                            mt.add(entry);
                        }
                    }
                }
            }
        }
        final List<URL> urls = mtName2mtURLs.get(relativePath.lastSegment());
        if (urls != null) {
            for (final URL url : urls) {
                if (url.getPath().indexOf(relativePath.toString()) > -1) {
                    return url;
                }
            }
        }
        return null;
    }

    /**
     * Returns the MTs.
     * 
     * @param mtName2mtURLs
     *            map the MT name with URLs.
     * @param name
     *            the name.
     * @return the MTs.
     */
    private List<URL> getMTs(final Map<String, List<URL>> mtName2mtURLs, final String name) {
        List<URL> mt = mtName2mtURLs.get(name);
        if (mt == null) {
            mt = Lists.newArrayList();
            mtName2mtURLs.put(name, mt);
        }
        return mt;
    }

    /**
     * Finds and returns the member resource identified by the given path in the
     * workspace, or null if no such resource exists.
     * 
     * @param path
     *            is the path of the desired resource
     * @return the member resource, or null if no such resource exists
     */
    public static IResource findResource(final IPath path) {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        if (workspace.getRoot().exists(path)) {
            return workspace.getRoot().findMember(path);
        } else {
            return null;
        }
    }

    /**
     * Finds and returns the file identified by the given path in the workspace,
     * or null if no such file exists.
     * 
     * @param path
     *            is the path of the desired resource
     * @return the member file, or null if no such resource exists
     */
    public static IFile findFile(final IPath path) {
        final IResource resource = FileProvider.findResource(path);
        if (resource instanceof IFile) {
            return (IFile) resource;
        } else {
            return null;
        }
    }

    /**
     * Creates the absolute path.
     * 
     * @param url
     *            is the relative path
     * @return the absolute file path
     */
    public static String transformToAbsolutePath(final URL url) {
        String absolutePath;
        try {
            final URL transformedUrl = FileLocator.toFileURL(url);
            final File file = new File(transformedUrl.getFile());
            absolutePath = file.getAbsolutePath();
        } catch (final IOException e) {
            absolutePath = ""; //$NON-NLS-1$
            DslCommonPlugin.getDefault().error(e.getMessage(), e);
        }
        return absolutePath;
    }
}
