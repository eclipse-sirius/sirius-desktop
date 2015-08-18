/*******************************************************************************
 * Copyright (c) 2013, 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EPackageLoadingCallback.EPackageDeclaration;
import org.eclipse.sirius.common.tools.api.interpreter.EPackageLoadingCallback.EPackageDeclarationSource;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.internal.interpreter.BundleClassLoading;
import org.eclipse.sirius.common.tools.internal.interpreter.ClassLoadingService;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * The {@link JavaExtensionsManager} load and maintains {@link Class} instances
 * based on the current search scope (of projects and/or plugins) and the
 * imported qualified names.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 */
public final class JavaExtensionsManager {

    private static final String WORKSPACE_SEPARATOR = "/"; //$NON-NLS-1$

    private static final Set<String> JAVA_SERVICES_BUNDLES_WHITE_LIST = Sets.newHashSet(DslCommonPlugin.PLUGIN_ID);

    /**
     * This will be updated with the list of accessible viewpoint plugins, if
     * any.
     */
    private Set<String> viewpointPlugins = Sets.newLinkedHashSet();

    /**
     * This will be updated with the list of accessible viewpoint projects
     * present in the workspace, if any.
     */
    private Set<String> viewpointProjects = Sets.newLinkedHashSet();

    private final Set<String> imports = new LinkedHashSet<String>();

    /**
     * These are the imports which are registered as
     * "not having been loaded so far", waiting for a change of scope or a
     * recompilation which would make them loadable.
     */
    private final Set<String> couldNotBeLoaded = new LinkedHashSet<String>();

    private final Map<String, Class<?>> loadedClasses = Maps.newLinkedHashMap();

    private ClassLoading classLoading;

    private List<ClassLoadingCallback> callbacks = Lists.newArrayList();

    private List<EPackageLoadingCallback> ePackageCallbacks = Lists.newArrayList();

    private boolean shouldLoadServices = true;

    private boolean shouldLoadEPackages = true;

    private ClasspathChangeCallback onWorkspaceChange = new ClasspathChangeCallback() {

        @Override
        public void classpathChanged(Set<String> updatedProjects) {
            /*
             * we get a notification if something in the classpath we used so
             * far has changed.
             */
            if (viewpointPlugins.size() > 0 || viewpointProjects.size() > 0) {
                reload();
            }
        }
    };

    private Multimap<String, EPackage> lastDeclarerIDsToEPackages = HashMultimap.create();

    /**
     * through this field we keep track fo the EPackage declarers which were
     * identified as bundles, hence don't need to be reloaded if they are still
     * bundles when a reload is requested.
     *
     */
    private Set<String> lastDeclarerIDsInBundles;

    /**
     * Create a new JavaExtensionManager.
     */
    public JavaExtensionsManager() {
        classLoading = new BundleClassLoading();
    }

    /**
     * Set (or replace) a new classloading override. If a previous one was
     * already set, then it will be disposed.
     * 
     * @param override
     *            the instance to override the class loading process.
     */
    public void setClassLoadingOverride(ClassLoading override) {
        if (classLoading != null) {
            classLoading.dispose();
        }
        override.setClasspathChangeCallback(onWorkspaceChange);
        this.classLoading = override;

    }

    /**
     * Add a new callback which, from now one, will be notified when a class has
     * been loaded/unloaded/searched but not found.
     * 
     * @param callback
     *            the callback to register.
     */
    public void addClassLoadingCallBack(ClassLoadingCallback callback) {
        this.callbacks.add(callback);
    }

    /**
     * Remove a previously registered callback.
     * 
     * @param callback
     *            the callback to remove.
     */
    public void removeClassLoadingCallBack(ClassLoadingCallback callback) {
        this.callbacks.remove(callback);
    }

    /**
     * Add a new callback which, from now one, will be notified when a EPackage
     * has been loaded/unloaded/searched but not found.
     * 
     * @param callback
     *            the callback to register.
     */
    public void addEPackageCallBack(EPackageLoadingCallback callback) {
        this.ePackageCallbacks.add(callback);
    }

    /**
     * Remove a previously registered callback.
     * 
     * @param callback
     *            the callback to remove.
     */
    public void removeEPackageCallBack(EPackageLoadingCallback callback) {
        this.ePackageCallbacks.remove(callback);
    }

    /**
     * Clear the resources used by the {@link JavaExtensionsManager}.
     */
    public void dispose() {
        classLoading.dispose();
        clearImports();
        this.viewpointPlugins.clear();
        this.viewpointProjects.clear();
    }

    /**
     * Update the search scope for Classes. Only if the newly passed scope
     * differs to the previous one then a reload of the classes will be
     * triggered.
     * 
     * @param plugins
     *            a set of bundle IDs to search in.
     * @param project
     *            a set of project names to search in.
     */
    public void updateScope(Set<String> plugins, Set<String> project) {
        boolean removedAddedAtLeastOnePlugin = this.viewpointPlugins.retainAll(plugins) || this.viewpointPlugins.addAll(plugins);

        boolean removedAddedAtLeastOnProject = this.viewpointProjects.retainAll(project) || this.viewpointProjects.addAll(project);
        this.viewpointPlugins = plugins;
        this.viewpointProjects = project;
        /*
         * something changed in the scope, we have to reload the Java
         * extensions.
         */
        if (couldNotBeLoaded.size() > 0 || removedAddedAtLeastOnePlugin || removedAddedAtLeastOnProject) {
            if (this.viewpointPlugins.size() > 0 || this.viewpointProjects.size() > 0) {
                reload();
            }
        }

    }

    /*
     * This method is synchronized as we expect the workspace listener to call
     * it when an event requires a reload. There is no guarantee this call is
     * made from the same thread as the others and we don't want several reloads
     * being done concurrently.
     */
    private synchronized void reload() {
        this.shouldLoadEPackages = true;
        this.shouldLoadServices = true;
    }

    /***
     * This will trigger the loading of EPackages or java Classes with the
     * current configuration (search scope and imports).
     */
    public synchronized void reloadIfNeeded() {
        if (this.shouldLoadEPackages) {
            reloadEPackages();
            this.shouldLoadEPackages = false;
        }
        if (this.shouldLoadServices) {
            loadJavaExtensions(this.imports);
            this.shouldLoadServices = false;
        }
    }

    private void reloadEPackages() {
        Multimap<String, EPackage> newDeclarations = HashMultimap.create();
        Set<String> newDeclarersAsBundles = Sets.newLinkedHashSet();
        Collection<EPackageDeclarationSource> ecoreDeclarationSources = this.classLoading.findEcoreDeclarations(this.viewpointProjects, this.viewpointPlugins);
        Collection<EPackageDeclarationSource> workspaceDeclarations = Lists.newArrayList();
        for (EPackageLoadingCallback.EPackageDeclarationSource declarer : ecoreDeclarationSources) {
            if (declarer.isBundle()) {
                newDeclarersAsBundles.add(declarer.getSymbolicName());
                for (EPackageDeclaration ePackageDeclaration : declarer.getEPackageDeclarations()) {
                    /*
                     * the EPackage definition comes from a deployed plugin, we
                     * retrieve the EPackage instance to use by getting it from
                     * the global registry.
                     */
                    EPackage pak = EPackage.Registry.INSTANCE.getEPackage(ePackageDeclaration.getNsURI());
                    if (pak != null) {
                        newDeclarations.put(declarer.getSymbolicName(), pak);
                    }
                }

            } else {
                /*
                 * we keep that for later as we need to initialize a specific
                 * resourceset which will be used by all the subsequent
                 * loadings.
                 */
                workspaceDeclarations.add(declarer);
            }
        }
        if (workspaceDeclarations.size() > 0) {
            /*
             * this resourceset is being used to load the genmodel instances
             * from the workspace. It is setup with uri mappings so that other
             * Ecore residing in the workspace are shadowing the ones from the
             * targetplatform.
             */
            ResourceSetImpl set = new ResourceSetImpl();

            computePlatformURIMap(set);

            /*
             * the EPackage definition comes from a workspace project, right now
             * we don't explicitely and fully support this use case where the
             * Ecore model lives in the workspace next to the .odesign
             * specification. To properly support this use case we would have to
             * load the corresponding genmodel and register it, making sure we
             * clean all the
             */
            for (EPackageDeclarationSource workspaceSource : workspaceDeclarations) {
                Map<String, EPackage> ecorePackages = Maps.newLinkedHashMap();
                /*
                 * a first iteration to populate the map of loaded Ecore
                 * packages.
                 */
                loadAndFindEPackages(set, workspaceSource, ecorePackages);
                /*
                 * a second iteration to declare the EPackages
                 */
                for (EPackageDeclaration declaration : workspaceSource.getEPackageDeclarations()) {
                    String nsURI = declaration.getNsURI();
                    if (!StringUtil.isEmpty(nsURI)) {
                        EPackage loaded = ecorePackages.get(nsURI);
                        if (loaded != null) {
                            newDeclarations.put(nsURI, loaded);
                        }
                    }
                }
            }

        }

        /*
         * cleaning up previously registered EPackage which are not accessible
         * any more.
         */
        boolean firstRun = lastDeclarerIDsInBundles == null;
        if (!firstRun) {
            for (Entry<String, EPackage> entry : lastDeclarerIDsToEPackages.entries()) {
                boolean changedType = lastDeclarerIDsInBundles.contains(entry.getKey()) != newDeclarersAsBundles.contains(entry.getKey());
                if (changedType) {
                    unloadedEPackage(entry.getValue());
                }
            }
        }
        for (Entry<String, EPackage> entry : newDeclarations.entries()) {
            boolean changedType = firstRun || lastDeclarerIDsInBundles.contains(entry.getKey()) != newDeclarersAsBundles.contains(entry.getKey());
            if (changedType) {
                loadedEPackage(entry.getValue());
            }
        }

        this.lastDeclarerIDsToEPackages = newDeclarations;
        this.lastDeclarerIDsInBundles = newDeclarersAsBundles;

    }

    private void computePlatformURIMap(ResourceSetImpl set) {
        Map<URI, URI> result = null;
        /*
         * We invoke computePlatformURIMap by reflection to keep being
         * compatible with EMF 2.8 and still leverage the new capabilities
         * regarding target platforms introduced in EMF 2.9.
         */
        try {
            Method computePlatformURIMap = EcorePlugin.class.getMethod("computePlatformURIMap", Boolean.TYPE); //$NON-NLS-1$
            result = (Map<URI, URI>) computePlatformURIMap.invoke(null, true);
        } catch (NoSuchMethodException e) {
            /*
             * result is still null, we'll call the old method.
             */
        } catch (IllegalAccessException e) {
            /*
             * result is still null, we'll call the old method.
             */
        } catch (IllegalArgumentException e) {
            /*
             * result is still null, we'll call the old method.
             */
        } catch (InvocationTargetException e) {
            /*
             * result is still null, we'll call the old method.
             */
        }
        if (result == null) {
            result = EcorePlugin.computePlatformURIMap();
        }
        if (result != null) {
            set.getURIConverter().getURIMap().putAll(result);
        }
    }

    private void loadAndFindEPackages(ResourceSetImpl set, EPackageDeclarationSource workspaceSource, Map<String, EPackage> ecorePackages) {
        for (EPackageDeclaration declaration : workspaceSource.getEPackageDeclarations()) {
            String genmodelPath = declaration.getGenModelPath();
            if (!StringUtil.isEmpty(genmodelPath)) {
                URI genModelURI = URI.createPlatformResourceURI(WORKSPACE_SEPARATOR + workspaceSource.getSymbolicName() + WORKSPACE_SEPARATOR + genmodelPath, true);
                /*
                 * the uri might have a fragment already, for instance in the
                 * Xcore case, if it is not the case then the genmodel is
                 * supposed to be the root element.
                 */
                if (!genModelURI.hasFragment()) {
                    genModelURI = genModelURI.appendFragment("/"); //$NON-NLS-1$
                }
                EObject genModel = set.getEObject(genModelURI, true);
                if (genModel != null && genModel.eClass().getEPackage() != null && "GenModel".equals(genModel.eClass().getName()) && "genmodel".equals(genModel.eClass().getEPackage().getName())) { //$NON-NLS-1$ //$NON-NLS-2$
                    Collection<EObject> genPackages = (Collection<EObject>) genModel.eGet(genModel.eClass().getEStructuralFeature("genPackages")); //$NON-NLS-1$
                    collectEPackages(ecorePackages, genPackages);
                }

            }
        }
    }

    private void collectEPackages(Map<String, EPackage> ecorePackages, Collection<EObject> genPackages) {
        for (EObject genPackage : genPackages) {
            Object ePak = genPackage.eGet(genPackage.eClass().getEStructuralFeature("ecorePackage")); //$NON-NLS-1$
            if (ePak instanceof EPackage && !StringUtil.isEmpty(((EPackage) ePak).getNsURI())) {
                ecorePackages.put(((EPackage) ePak).getNsURI(), (EPackage) ePak);
            }
            Collection<EObject> subGenPackages = (Collection<EObject>) genPackage.eGet(genPackage.eClass().getEStructuralFeature("nestedGenPackages")); //$NON-NLS-1$
            collectEPackages(ecorePackages, subGenPackages);
        }
    }

    private void unloadedEPackage(EPackage removed) {
        for (EPackageLoadingCallback ePackageCallBack : this.ePackageCallbacks) {
            try {
                ePackageCallBack.unloaded(removed.getNsURI(), removed);
                // CHECKSTYLE:OFF
            } catch (Throwable e) {
                // CHECKSTYLE:ON
                /*
                 * It's the callback responsability to log or manage the errors,
                 * we should not prevent another callback to process the event
                 * if another one failed for some reason.
                 */
            }
        }
    }

    private void loadedEPackage(EPackage ePackage) {
        for (EPackageLoadingCallback ePackageCallBack : this.ePackageCallbacks) {
            try {
                ePackageCallBack.loaded(ePackage.getNsURI(), ePackage);
                // CHECKSTYLE:OFF
            } catch (Throwable e) {
                // CHECKSTYLE:ON
                /*
                 * It's the callback responsability to log or manage the errors,
                 * we should not prevent another callback to process the event
                 * if another one failed for some reason.
                 */
            }
        }
    }

    /**
     * Add a new Java qualified name to consider as an Import.
     * 
     * @param classQualifiedName
     *            the Java qualified name of a class to consider as a Java
     *            Extension.
     */
    public void addImport(String classQualifiedName) {
        if (classQualifiedName != null && classQualifiedName.contains(".")) { //$NON-NLS-1$
            this.imports.add(classQualifiedName);
            this.shouldLoadServices = true;
        }
    }

    /**
     * Remove a JavaExtension in the current manager.
     * 
     * @param classQualifiedName
     *            the Java qualified name of a class to remove as a Java
     *            Extension.
     */
    public void removeImport(String classQualifiedName) {
        if (this.imports.contains(classQualifiedName)) {
            couldNotBeLoaded.remove(classQualifiedName);
            Set<String> removedImport = Sets.newLinkedHashSet();
            removedImport.add(classQualifiedName);
            this.imports.remove(classQualifiedName);
            unloadJavaExtensions(removedImport);
        }
    }

    /**
     * the current list of imported Java Extensions.
     * 
     * @return the current list of class qualified name used as Java Extensions.
     */
    public Collection<String> getImports() {
        return ImmutableList.copyOf(this.imports);
    }

    /**
     * Unload the already known Java Extensions.
     */
    public void clearImports() {
        unloadJavaExtensions(this.imports);
        this.imports.clear();
        this.couldNotBeLoaded.clear();
    }

    private void loadJavaExtensions(Set<String> addedImports) {
        Map<String, Class> toUnload = Maps.newLinkedHashMap();
        Map<String, Class> toLoad = Maps.newLinkedHashMap();
        Set<String> notFound = Sets.newLinkedHashSet();
        for (String qualifiedName : addedImports) {
            Class<?> found = classLoading.findClass(viewpointProjects, viewpointPlugins, qualifiedName);
            if (found == null) {
                // Find services in white list
                found = classLoading.findClass(Collections.<String> emptySet(), JAVA_SERVICES_BUNDLES_WHITE_LIST, qualifiedName);
            }

            if (found != null) {
                this.couldNotBeLoaded.remove(qualifiedName);
                Class<?> alreadyHere = loadedClasses.get(qualifiedName);
                if (alreadyHere != null) {
                    toUnload.put(qualifiedName, alreadyHere);
                }
                loadedClasses.put(qualifiedName, found);
                toLoad.put(qualifiedName, found);
            } else {
                notFound.add(qualifiedName);
                this.couldNotBeLoaded.add(qualifiedName);
            }
        }
        /*
         * We make sure we notify the callbacks with the following orders :
         * always notify an unload first, then the loaded classes, then the
         * qualified names which were not found. This makes it easier for the
         * callbacks to maintain their own data structures in sync regarding the
         * latest versions of the classes.
         */
        for (Map.Entry<String, Class> classToUnload : toUnload.entrySet()) {
            unloaded(classToUnload.getKey(), classToUnload.getValue());
        }
        for (Map.Entry<String, Class> classToLoad : toLoad.entrySet()) {
            loaded(classToLoad.getKey(), classToLoad.getValue());
        }
        for (String qualifiedName : notFound) {
            notFound(qualifiedName);
        }
    }

    private void notFound(String qualifiedName) {
        for (ClassLoadingCallback callback : this.callbacks) {
            try {
                callback.notFound(qualifiedName);
                // CHECKSTYLE:OFF
            } catch (Throwable e) {
                // CHECKSTYLE:ON
                /*
                 * It's the callback responsability to log or manage the errors,
                 * we should not prevent another callback to process the event
                 * if another one failed for some reason.
                 */
            }
        }

    }

    private void loaded(String qualifiedName, Class<?> clazz) {
        for (ClassLoadingCallback callback : this.callbacks) {
            try {
                callback.loaded(qualifiedName, clazz);
                // CHECKSTYLE:OFF
            } catch (Throwable e) {
                // CHECKSTYLE:ON
                /*
                 * It's the callback responsability to log or manage the errors,
                 * we should not prevent another callback to process the event
                 * if another one failed for some reason.
                 */
            }
        }

    }

    private void unloaded(String qualifiedName, Class<?> clazz) {
        for (ClassLoadingCallback callback : this.callbacks) {
            try {
                callback.unloaded(qualifiedName, clazz);
                // CHECKSTYLE:OFF
            } catch (Throwable e) {
                // CHECKSTYLE:ON
                /*
                 * It's the callback responsability to log or manage the errors,
                 * we should not prevent another callback to process the event
                 * if another one failed for some reason.
                 */
            }
        }

    }

    private void unloadJavaExtensions(Set<String> removedImports) {
        for (String qualifiedName : removedImports) {
            Class<?> alreadyHere = loadedClasses.get(qualifiedName);
            if (alreadyHere != null) {
                unloaded(qualifiedName, alreadyHere);
            }
        }
    }

    /**
     * Takes a pure Object as value to update the scope as sent to the
     * {@link IInterpreter} instances through the setProperty() method with
     * IInterpreter.FILES key.
     * 
     * @param value
     *            can be null, or a list of String each being the identifier of
     *            a project which can be in the workspace or not.
     */
    public void updateScope(Collection<String> value) {
        Set<String> prjs = Sets.newLinkedHashSet();
        Set<String> plugins = Sets.newLinkedHashSet();
        if (value != null) {
            for (final String odesignPath : value) {
                final URI workspaceCandidate = URI.createPlatformResourceURI(odesignPath, true);
                final URI pluginCandidate = URI.createPlatformPluginURI(odesignPath, true);
                if (existsInWorkspace(workspaceCandidate.toPlatformString(true))) {
                    prjs.add(workspaceCandidate.segment(1));
                } else if (existsInPlugins(URI.decode(pluginCandidate.toString()))) {
                    plugins.add(pluginCandidate.segment(1));
                }
            }
        }
        updateScope(plugins, prjs);
    }

    /**
     * Checks whether the given path exists in the plugins.
     *
     * @param path
     *            The path we need to check.
     * @return <code>true</code> if <em>path</em> denotes an existing plugin
     *         resource, <code>false</code> otherwise.
     */
    private static boolean existsInPlugins(String path) {
        try {
            URL url = new URL(path);
            return FileLocator.find(url) != null;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    /**
     * Checks whether the given path exists in the workspace.
     *
     * @param path
     *            The path we need to check.
     * @return <code>true</code> if <em>path</em> denotes an existing workspace
     *         resource, <code>false</code> otherwise.
     */
    private static boolean existsInWorkspace(String path) {
        if (path == null || path.length() == 0 || EcorePlugin.getWorkspaceRoot() == null) {
            return false;
        }
        return ResourcesPlugin.getWorkspace().getRoot().exists(new Path(path));
    }

    /**
     * Create and setup a {@link JavaExtensionsManager} which might have been
     * extended with a specific support for workspace class loading.
     * 
     * @return the created {@link JavaExtensionsManager}
     */
    public static JavaExtensionsManager createManagerWithOverride() {
        JavaExtensionsManager result = new JavaExtensionsManager();
        result.setClassLoadingOverride(ClassLoadingService.getClassLoading());
        return result;
    }

}
