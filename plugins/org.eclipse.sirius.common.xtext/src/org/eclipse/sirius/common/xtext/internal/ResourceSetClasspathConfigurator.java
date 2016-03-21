/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.xtext.internal;

import static com.google.common.collect.Maps.newHashMap;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.util.JdtClasspathUriResolver;
import org.osgi.framework.Constants;

/**
 * Configures an Xtext ResourceSet for Java integration if we are inside a Java
 * project.
 */
public class ResourceSetClasspathConfigurator {
    /**
     * Configures an Xtext ResourceSet for Java integration if we are inside a
     * Java project.
     */
    public ResourceSet configure(XtextResourceSet set, IProject project) {
        IJavaProject javaProject = JavaCore.create(project);
        if (javaProject != null && javaProject.exists()) {
            set.getURIConverter().getURIMap().putAll(computePlatformURIMap(javaProject));
            set.setClasspathURIContext(javaProject);
            set.setClasspathUriResolver(new JdtClasspathUriResolver());
        }
        return set;
    }

    private Map<URI, URI> computePlatformURIMap(IJavaProject javaProject) {
        HashMap<URI, URI> hashMap = newHashMap(EcorePlugin.computePlatformURIMap());
        try {
            if (!javaProject.exists())
                return hashMap;
            IClasspathEntry[] classpath = javaProject.getResolvedClasspath(true);
            for (IClasspathEntry classPathEntry : classpath) {
                IPath path = classPathEntry.getPath();
                if (path != null && "jar".equals(path.getFileExtension())) { //$NON-NLS-1$
                    try {
                        final File file = path.toFile();
                        if (file != null && file.exists()) {
                            try (JarFile jarFile = new JarFile(file)) {
                                Manifest manifest = jarFile.getManifest();
                                if (manifest != null) {
                                    handleManifest(hashMap, file, manifest);
                                }
                            }
                        }
                    } catch (IOException e) {
                        DslCommonPlugin.getDefault().error(e.getMessage(), e);
                    }
                }
            }
        } catch (JavaModelException e) {
            DslCommonPlugin.getDefault().error(e.getMessage(), e);
        }
        return hashMap;
    }

    private void handleManifest(HashMap<URI, URI> hashMap, final File file, Manifest manifest) {
        String name = manifest.getMainAttributes().getValue(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE);
        if (name != null) {
            final int indexOf = name.indexOf(';');
            if (indexOf > 0)
                name = name.substring(0, indexOf);
            if (!EcorePlugin.getPlatformResourceMap().containsKey(name)) {
                String p = "archive:" + file.toURI() + "!/"; //$NON-NLS-1$ //$NON-NLS-2$
                URI uri = URI.createURI(p);
                final URI platformResourceKey = URI.createPlatformResourceURI(name + "/", false); //$NON-NLS-1$
                final URI platformPluginKey = URI.createPlatformPluginURI(name + "/", false); //$NON-NLS-1$
                hashMap.put(platformResourceKey, uri);
                hashMap.put(platformPluginKey, uri);
            }
        }
    }
}
