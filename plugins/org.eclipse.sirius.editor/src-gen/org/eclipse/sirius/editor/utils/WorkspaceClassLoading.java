/*******************************************************************************
 * Copyright (c) 2013, 2016 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.osgi.service.resolver.BaseDescription;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.osgi.service.resolver.ExportPackageDescription;
import org.eclipse.osgi.service.resolver.HostSpecification;
import org.eclipse.osgi.service.resolver.ImportPackageSpecification;
import org.eclipse.osgi.service.resolver.VersionConstraint;
import org.eclipse.pde.core.plugin.IPluginAttribute;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginObject;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.sirius.common.tools.api.interpreter.ClasspathChangeCallback;
import org.eclipse.sirius.common.tools.api.interpreter.EPackageLoadingCallback;
import org.eclipse.sirius.common.tools.api.interpreter.EPackageLoadingCallback.EPackageDeclaration;
import org.eclipse.sirius.common.tools.internal.interpreter.BundleClassLoading;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Limitations :
 * <ul>
 * <li>if you have in your workspace some projects which are actually used by Sirius and which might pass instances to
 * the query implementation, things *will* go wrong as this utility will search first in the workspace.</li>
 * <li>you can't use the generated model interfaces</li>
 * </ul>
 *
 * @author cedric
 */
public class WorkspaceClassLoading extends BundleClassLoading {

    private IResourceChangeListener workspaceListener = new IResourceChangeListener() {

        @Override
        public void resourceChanged(IResourceChangeEvent event) {
            /*
             * we are only interested in change which have been completed on the workspace.
             */
            if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
                return;
            }

            final Set<String> changed = new LinkedHashSet<>();

            IResourceDeltaVisitor projectClassesInvalidator = new IResourceDeltaVisitor() {

                @Override
                public boolean visit(IResourceDelta delta) throws CoreException {
                    if (delta.getKind() != IResourceDelta.CHANGED) {
                        return true;
                    }
                    // only interested in content changes
                    if ((delta.getFlags() & IResourceDelta.CONTENT) == 0) {
                        return true;
                    }
                    IResource resource = delta.getResource();
                    // only interested in files with the "txt" extension
                    if (resource.getType() == IResource.FILE
                            && ("class".equalsIgnoreCase(resource.getFileExtension()) || "ecore".equalsIgnoreCase(resource.getFileExtension()) || "MANIFEST.MF".equalsIgnoreCase(resource.getName()))
                            && resource.getProject() != null && resource.getProject().getName() != null) {
                        changed.add(resource.getProject().getName());
                    }
                    return true;
                }

            };
            try {
                event.getDelta().accept(projectClassesInvalidator);
            } catch (CoreException e) {
                /*
                 * We can get errors when inspecting the changes if, for instance, the workspace was not completely
                 * refreshed.
                 */
                SiriusEditorPlugin.INSTANCE.log(e);
            }

            if (changed.size() > 0) {
                for (String projectName : changed) {
                    URLClassLoader old = projectsToClassLoader.get(projectName);
                    if (old != null) {
                        closeClassLoader(old);
                    }
                    projectsToClassLoader.remove(projectName);
                }
                if (callback != null) {
                    callback.classpathChanged(changed);
                }
            }
        }

    };

    private Set<String> doNotLoadFromWorkspace;

    public WorkspaceClassLoading() {
        doNotLoadFromWorkspace = new LinkedHashSet<>();
        for (String siriusBundle : SiriusEditorPlugin.getSiriusRuntimeBundles()) {
            doNotLoadFromWorkspace.add(siriusBundle);
            doNotLoadFromWorkspace.addAll(super.getBundleDependencies(siriusBundle));
        }
    }

    protected Collection<Object> findCallees(IPluginModelBase model) {
        BundleDescription desc = model.getBundleDescription();
        if (desc == null) {
            return Collections.<Object> emptyList();
        }
        BundleDescription fFragmentDescription = null;
        HostSpecification spec = desc.getHost();
        if (spec != null) {
            fFragmentDescription = desc;
            List<Object> fragmentDependencies = new ArrayList<Object>(getDependencies(desc, fFragmentDescription));
            BaseDescription host = spec.getSupplier();
            if (host instanceof BundleDescription) {
                BundleDescription hostDesc = (BundleDescription) host;
                /*
                 * check to see if the host is already included as a dependency. If so, we don't need to include the
                 * host manually.
                 */
                for (Object object : fragmentDependencies) {
                    BundleDescription dependency = null;
                    if (object instanceof BundleSpecification) {
                        dependency = ((BundleSpecification) object).getBundle();
                    } else if (object instanceof ImportPackageSpecification) {
                        ExportPackageDescription epd = (ExportPackageDescription) ((ImportPackageSpecification) object).getSupplier();
                        if (epd != null) {
                            dependency = epd.getSupplier();
                        }
                    }
                    if (dependency != null && dependency.equals(hostDesc)) {
                        return fragmentDependencies;
                    }
                }

                // host not included as dependency, include it manually.
                fragmentDependencies.add(0, hostDesc);
                return fragmentDependencies;
            }
            return fragmentDependencies;
        }
        return getDependencies(desc, fFragmentDescription);
    }

    private Collection<Object> getDependencies(BundleDescription desc, BundleDescription fFragmentDescription) {
        /*
         * use map to store dependencies so if Import-Package is supplied by same BundleDescription as supplier of
         * Require-Bundle, it only shows up once Also, have to use BundleSpecficiation instead of BundleDescroption to
         * show re-exported icon on re-exported Required-Bundles Have to use ImportPackageSpecification to determine if
         * an import is optional and should be filtered.
         */
        HashMap<Object, Object> dependencies = new HashMap<Object, Object>();
        BundleSpecification[] requiredBundles = desc.getRequiredBundles();
        for (BundleSpecification requiredBundle : requiredBundles) {
            BaseDescription bd = requiredBundle.getSupplier();
            if (bd != null) {
                dependencies.put(bd, requiredBundle);
            } else {
                dependencies.put(requiredBundle, requiredBundle);
            }
        }
        ImportPackageSpecification[] importedPkgs = desc.getImportPackages();
        for (int i = 0; i < importedPkgs.length; i++) {
            BaseDescription bd = importedPkgs[i].getSupplier();
            if (bd instanceof ExportPackageDescription) {
                BundleDescription exporter = ((ExportPackageDescription) bd).getExporter();
                if (exporter != null) {
                    Object obj = dependencies.get(exporter);
                    if (obj == null) {
                        dependencies.put(exporter, importedPkgs[i]);
                    } else if (!Constants.RESOLUTION_OPTIONAL.equals(importedPkgs[i].getDirective(Constants.RESOLUTION_DIRECTIVE)) && obj instanceof ImportPackageSpecification
                            && Constants.RESOLUTION_OPTIONAL.equals(((ImportPackageSpecification) obj).getDirective(Constants.RESOLUTION_DIRECTIVE))) {
                        /*
                         * if we have a non-optional Import-Package dependency on a bundle which we already depend on,
                         * check to make sure our current dependency is not optional. If it is, replace the optional
                         * dependency with the non-optional one
                         */
                        dependencies.put(exporter, importedPkgs[i]);
                    }
                }
            }
            /*
             * ignore unresolved packages
             */
        }
        /*
         * include fragments which are "linked" to this bundle
         */
        BundleDescription frags[] = desc.getFragments();
        for (int i = 0; i < frags.length; i++) {
            if (!frags[i].equals(fFragmentDescription)) {
                dependencies.put(frags[i], frags[i]);
            }
        }
        return dependencies.values();
    }

    private void computeURLs(IProject project, List<URL> uRLs) {
        final IJavaProject javaProject = JavaCore.create(project);
        try {
            for (IClasspathEntry entry : javaProject.getResolvedClasspath(true)) {
                if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
                    final IPath output = entry.getOutputLocation();
                    if (output != null) {
                        IFile reference = ResourcesPlugin.getWorkspace().getRoot().getFile(output);
                        if (reference.exists()) {
                            URL url;
                            try {
                                url = reference.getLocation().toFile().toURI().toURL();
                                uRLs.add(url);
                            } catch (MalformedURLException e) {
                                /*
                                 * We don't know how to handle this class path entry.
                                 */
                            }
                        }

                    }
                }
            }
            /*
             * Add the default output location to the classpath anyway since source folders are not required to have
             * their own
             */
            final IPath output = javaProject.getOutputLocation();
            if (output != null) {
                IFolder reference = ResourcesPlugin.getWorkspace().getRoot().getFolder(output);
                if (reference.exists() && reference.getLocation() != null) {
                    URL url;
                    File file = reference.getLocation().toFile();
                    try {
                        if (file != null && file.exists()) {
                            url = file.toURI().toURL();
                            uRLs.add(url);
                        }
                    } catch (MalformedURLException e) {
                        /*
                         * the given path does not map to a file which can actually be mapped to an url, ignore it.
                         */
                    }
                }

            }
        } catch (JavaModelException e) {
        }
    }

    private Map<String, URLClassLoader> projectsToClassLoader = new HashMap<>();

    private ClasspathChangeCallback callback;

    @Override
    public Class findClass(Set<String> viewpointProjects, Set<String> plugins, String qualifiedName) {
        Class result = null;
        IWorkspaceRoot root = EcorePlugin.getWorkspaceRoot();
        if (root != null) {
            Iterator<String> it = viewpointProjects.iterator();
            while (result == null && it.hasNext()) {
                String projectName = it.next();
                ClassLoader loader = getOrCreateClassLoader(projectName, root);
                if (loader != null) {
                    try {
                        result = loader.loadClass(qualifiedName);
                    } catch (ClassNotFoundException e) {
                        /*
                         * nothing to report,we would not find the class.
                         */
                    } catch (NoClassDefFoundError e) {
                        /*
                         * nothing to report,we would not find the class.
                         */
                    }
                }
            }
        }
        Iterator<String> it = plugins.iterator();
        while (result == null && it.hasNext()) {
            Bundle bnd = Platform.getBundle(it.next());
            if (bnd != null) {
                try {
                    result = bnd.loadClass(qualifiedName);
                } catch (Throwable e) {
                    /*
                     * the class we are searching wasn't there.
                     */
                }
            }
        }
        return result;
    }

    private ClassLoader getOrCreateClassLoader(String projectName, IWorkspaceRoot root) {
        URLClassLoader existing = projectsToClassLoader.get(projectName);
        if (existing == null) {
            existing = createClassLoader(projectName, root);
            if (existing != null) {
                projectsToClassLoader.put(projectName, existing);
            }
        }
        return existing;
    }

    @Override
    protected Collection<EPackageLoadingCallback.EPackageDeclarationSource> getEPackagesDeclaredInBundles(Collection<String> bundles) {
        Collection<EPackageLoadingCallback.EPackageDeclarationSource> result = new ArrayList<>();
        IWorkspaceRoot root = EcorePlugin.getWorkspaceRoot();
        Set<String> bundlesToSearchInPlatform = new LinkedHashSet<>();
        if (root != null) {
            Map<String, IPluginModelBase> symbolicNamestoModels = getBundlesInWorkspace();
            for (String bundleID : bundles) {
                IPluginModelBase workspaceVersion = symbolicNamestoModels.get(bundleID);
                if (workspaceVersion != null) {
                    for (IPluginExtension extensions : workspaceVersion.getExtensions().getExtensions()) {
                        if (BundleClassLoading.EMF_GENERATED_PACKAGE_EXTENSIONPOINT.equals(extensions.getPoint())) {
                            String symbolicNameForProject = workspaceVersion.getBundleDescription().getSymbolicName();
                            List<EPackageLoadingCallback.EPackageDeclaration> declarations = new ArrayList<>();

                            for (IPluginObject object : extensions.getChildren()) {
                                if (object instanceof IPluginElement) {
                                    IPluginElement element = (IPluginElement) object;
                                    String nsURI = getAttributeValue(element, "uri");
                                    String className = getAttributeValue(element, "class");
                                    String genModel = getAttributeValue(element, "genModel");
                                    if (nsURI != null && className != null) {
                                        declarations.add(new EPackageDeclaration(nsURI, className, genModel));
                                    } else {
                                        IStatus status = new Status(IStatus.WARNING, SiriusEditorPlugin.PLUGIN_ID,
                                                "An EPackage declaration in project " + symbolicNameForProject + " has been ignored because of missing informations.");
                                        SiriusEditorPlugin.INSTANCE.log(status);;
                                    }
                                }
                            }
                            if (declarations.size() > 0) {
                                result.add(new EPackageLoadingCallback.EPackageDeclarationSource(symbolicNameForProject, declarations, false));
                            }
                        }
                    }
                } else {
                    bundlesToSearchInPlatform.add(bundleID);
                }
            }
            result.addAll(super.getEPackagesDeclaredInBundles(bundlesToSearchInPlatform));
        } else {
            result = super.getEPackagesDeclaredInBundles(bundles);
        }
        return result;
    }

    @Override
    protected Set<String> getBundleDependencies(String currentBundleID) {
        Set<String> dependenciesRetrievedThroughPlatform = super.getBundleDependencies(currentBundleID);
        IPluginModelBase pdeModel = PluginRegistry.findModel(currentBundleID);
        if (pdeModel != null && pdeModel.getUnderlyingResource() instanceof IFile) {
            Set<String> dependenciesRetrievedThroughPDE = getDependenciesFromPDE(pdeModel);
            return dependenciesRetrievedThroughPDE;
        } else {
            return dependenciesRetrievedThroughPlatform;
        }

    }

    protected Set<String> getDependenciesFromPDE(IPluginModelBase pdeModel) {
        Set<String> dependenciesRetrievedThroughPDE = new LinkedHashSet<>();
        /*
         * we have this bundle in the workspace.
         */
        Collection<Object> dependencies = Sets.newLinkedHashSet(findCallees(pdeModel));
        for (VersionConstraint requireBundleOrImportPackage : Iterables.filter(dependencies, VersionConstraint.class)) {
            BaseDescription supplier = requireBundleOrImportPackage.getSupplier();
            if (requireBundleOrImportPackage instanceof ImportPackageSpecification && supplier instanceof ExportPackageDescription) {
                supplier = ((ExportPackageDescription) supplier).getSupplier();
            }
            if (supplier instanceof BundleDescription && ((BundleDescription) supplier).getSymbolicName() != null) {
                dependenciesRetrievedThroughPDE.add(((BundleDescription) supplier).getSymbolicName());
            }
        }
        return dependenciesRetrievedThroughPDE;
    }

    private String getAttributeValue(IPluginElement element, String key) {
        IPluginAttribute attr = element.getAttribute(key);
        if (attr != null) {
            return attr.getValue();
        }
        return null;
    }

    private URLClassLoader createClassLoader(String projectName, IWorkspaceRoot root) {
        Map<String, IPluginModelBase> symbolicNamestoModels = getBundlesInWorkspace();

        final Collection<IPluginModelBase> workspaceDependencies = new ArrayList<>();
        final Collection<Bundle> installedDependencies = new ArrayList<>();

        collectAllClassPath(projectName, root, symbolicNamestoModels, workspaceDependencies, installedDependencies);

        List<URL> urls = new ArrayList<URL>();
        /*
         * declare the URLs for the workspace dependencies
         */
        for (IPluginModelBase wksDep : workspaceDependencies) {
            IResource res = wksDep.getUnderlyingResource();
            if (res != null) {
                computeURLs(res.getProject(), urls);
            }
        }

        URLClassLoader loader = new URLClassLoader(urls.toArray(new URL[urls.size()])) {

            @Override
            public Class findClass(String name) throws ClassNotFoundException {
                Class found = null;
                /*
                 * search in the workspace projects first...
                 */
                try {
                    found = super.findClass(name);
                } catch (Throwable e) {
                    /*
                     * the class we are searching wasn't found in the URLs
                     */
                }
                Iterator<Bundle> it = installedDependencies.iterator();
                while (found == null && it.hasNext()) {
                    try {
                        found = it.next().loadClass(name);
                    } catch (Throwable e) {
                        /*
                         * the class we are searching wasn't there.
                         */
                    }

                }
                return found;
            }
        };

        return loader;
    }

    protected void collectAllClassPath(String projectName, IWorkspaceRoot root, Map<String, IPluginModelBase> symbolicNamestoModels, final Collection<IPluginModelBase> workspaceDependencies,
            final Collection<Bundle> installedDependencies) {
        IPluginModelBase pdeModel = PluginRegistry.findModel(projectName);
        if (pdeModel != null) {
            Collection<String> symbolicNames = getDependenciesFromPDE(pdeModel);
            for (String requirementSymbolicName : symbolicNames) {
                IPluginModelBase requiredDep = symbolicNamestoModels.get(requirementSymbolicName);
                if (requiredDep != null && !doNotLoadFromWorkspace.contains(requirementSymbolicName)) {
                    workspaceDependencies.add(requiredDep);
                } else {
                    Bundle bnd = Platform.getBundle(requirementSymbolicName);
                    if (bnd != null) {
                        installedDependencies.add(bnd);
                    }

                }

            }
        }
        IPluginModelBase currentProject = symbolicNamestoModels.get(projectName);
        if (currentProject != null && !doNotLoadFromWorkspace.contains(projectName)) {
            workspaceDependencies.add(currentProject);
        } else {
            Bundle bnd = Platform.getBundle(projectName);
            if (bnd != null) {
                installedDependencies.add(bnd);
            }
        }
    }

    private Map<String, IPluginModelBase> getBundlesInWorkspace() {
        Map<String, IPluginModelBase> symbolicNamestoModels = new HashMap<>();
        IPluginModelBase[] wrkspcesModels = PluginRegistry.getWorkspaceModels();
        for (IPluginModelBase iPluginModelBase : wrkspcesModels) {
            if (iPluginModelBase.getBundleDescription() != null && iPluginModelBase.getBundleDescription().getSymbolicName() != null) {
                symbolicNamestoModels.put(iPluginModelBase.getBundleDescription().getSymbolicName(), iPluginModelBase);
            }
        }
        return symbolicNamestoModels;
    }

    @Override
    public void dispose() {
        this.projectsToClassLoader.clear();
        IWorkspaceRoot root = EcorePlugin.getWorkspaceRoot();
        if (this.callback != null && root != null) {
            ResourcesPlugin.getWorkspace().removeResourceChangeListener(workspaceListener);
        }
        for (URLClassLoader loader : projectsToClassLoader.values()) {
            closeClassLoader(loader);
        }
        projectsToClassLoader.clear();
    }

    private void closeClassLoader(URLClassLoader old) {
        try {
            old.close();
        } catch (IllegalArgumentException | IOException e) {
            SiriusEditorPlugin.INSTANCE.log(e.getCause());
        }
    }

    @Override
    public void setClasspathChangeCallback(ClasspathChangeCallback listener) {
        IWorkspaceRoot root = EcorePlugin.getWorkspaceRoot();
        if (this.callback != null && listener == null && root != null) {
            ResourcesPlugin.getWorkspace().removeResourceChangeListener(workspaceListener);
        }
        this.callback = listener;
        if (root != null) {
            ResourcesPlugin.getWorkspace().addResourceChangeListener(workspaceListener);
        }

    }
}
