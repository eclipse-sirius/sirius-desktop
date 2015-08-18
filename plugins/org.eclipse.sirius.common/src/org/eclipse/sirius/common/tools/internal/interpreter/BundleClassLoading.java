/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.ClassLoading;
import org.eclipse.sirius.common.tools.api.interpreter.ClasspathChangeCallback;
import org.eclipse.sirius.common.tools.api.interpreter.EPackageLoadingCallback;
import org.eclipse.sirius.common.tools.api.interpreter.EPackageLoadingCallback.EPackageDeclaration;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * A {@link ClassLoading} implementation which look for a class in a list of
 * OSGi bundles.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr
 *
 */
public class BundleClassLoading implements ClassLoading {
    /**
     * The extension point id used to contribute generated EPackages to the EMF
     * runtime.
     */
    protected static final String EMF_GENERATED_PACKAGE_EXTENSIONPOINT = "org.eclipse.emf.ecore.generated_package"; //$NON-NLS-1$

    @Override
    public void setClasspathChangeCallback(ClasspathChangeCallback listener) {
        /*
         * we only consider plugins by default and are not detecting any change
         * in it.
         */

    }

    @Override
    public Class<?> findClass(Set<String> projectsToSearchIn, Set<String> plugins, String qualifiedName) {
        /*
         * By default we don't search in projects, only in plugins.
         */
        Class<?> found = null;
        Iterator<String> it = plugins.iterator();
        while (found == null && it.hasNext()) {
            String bundleID = it.next();
            found = loadClassInBundle(bundleID, qualifiedName);
        }
        return found;
    }

    @Override
    public void dispose() {
        /*
         * no internal state, nothing to dispose of.
         */

    }

    private Class<?> loadClassInBundle(String bundleID, String qualifiedName) {
        Bundle requiredBundle = Platform.getBundle(bundleID);
        if (requiredBundle != null) {
            return loadClassInBundle(requiredBundle, qualifiedName);
        }
        return null;

    }

    private Class<?> loadClassInBundle(Bundle bundle, String qualifiedName) {
        try {
            return bundle.loadClass(qualifiedName);
        } catch (ClassNotFoundException e) {
            /*
             * nothing to report, move along to the next bundle.
             */
        } catch (NoClassDefFoundError e) {
            /*
             * nothing to report, move along to the next bundle.
             */
        }
        return null;
    }

    @Override
    public Collection<EPackageLoadingCallback.EPackageDeclarationSource> findEcoreDeclarations(Set<String> projects, Set<String> plugins) {
        Set<String> analyzed = Sets.newLinkedHashSet();
        Set<String> bundlesIDependOn = Sets.newLinkedHashSet();

        for (String currentBundle : Iterables.concat(plugins, projects)) {
            addDependencies(currentBundle, analyzed, bundlesIDependOn);
        }

        return getEPackagesDeclaredInBundles(bundlesIDependOn);
    }

    /**
     * return the list of EPackage declared in the given list of bundles.
     * 
     * @param bundles
     *            a collection of bundles.
     * @return the list of EPackage declarations made through the plugin.xml of
     *         those bundles.
     */
    protected Collection<EPackageLoadingCallback.EPackageDeclarationSource> getEPackagesDeclaredInBundles(Collection<String> bundles) {
        Collection<EPackageLoadingCallback.EPackageDeclarationSource> result = Lists.newArrayList();
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            final IExtensionRegistry reg = Platform.getExtensionRegistry();
            Multimap<String, EPackageDeclaration> contributions = HashMultimap.create();
            final IExtensionPoint ep = reg.getExtensionPoint(EMF_GENERATED_PACKAGE_EXTENSIONPOINT);
            for (final IExtension ext : ep.getExtensions()) {
                final IConfigurationElement[] ce = ext.getConfigurationElements();
                String contributorName = ext.getContributor().getName();
                if (bundles.contains(contributorName)) {
                    for (IConfigurationElement element : ce) {

                        String nsURI = element.getAttribute("uri"); //$NON-NLS-1$
                        String className = element.getAttribute("class"); //$NON-NLS-1$
                        String genModel = element.getAttribute("genModel"); //$NON-NLS-1$

                        if (nsURI != null && className != null) {
                            contributions.put(contributorName, new EPackageDeclaration(nsURI, className, genModel));
                        } else {
                            DslCommonPlugin.getDefault().warning("An EPackage declaration in project " + contributorName + " has been ignored because of missing informations.",
                                    new IllegalArgumentException());
                        }
                    }

                }
            }

            for (String contributor : contributions.keySet()) {
                Collection<EPackageDeclaration> declarations = contributions.get(contributor);
                if (declarations.size() > 0) {
                    result.add(new EPackageLoadingCallback.EPackageDeclarationSource(contributor, declarations, true));
                }
            }
        }
        return result;
    }

    /**
     * Recursively add all the dependencies
     * 
     * @param bundleSymbolicName
     *            the symbolic name of the bundle to analyze.
     * @param analyzed
     *            the set of bundle which have been analyzed already. This set
     *            is used as a safeguard to avoid infinite cycles and will be
     *            updated by the method.
     * @param collectedDependencies
     *            the set containing the collected dependencies.
     */
    private void addDependencies(String bundleSymbolicName, Set<String> analyzed, Set<String> collectedDependencies) {
        if (!analyzed.contains(bundleSymbolicName)) {
            analyzed.add(bundleSymbolicName);
            Set<String> dependencies = getBundleDependencies(bundleSymbolicName);
            collectedDependencies.addAll(dependencies);
            for (String dependency : dependencies) {
                addDependencies(dependency, analyzed, collectedDependencies);
            }
        }

    }

    /**
     * Retrieve the set of dependencies for a given bundle symbolic name.
     * 
     * 
     * @param bundleSymbolicName
     *            the bundle symbolic name.
     * @return a set of bundle symbolic names which are dependencies. An empty
     *         set if the bundle has not been found.
     */
    protected Set<String> getBundleDependencies(String bundleSymbolicName) {
        Set<String> dependencies = Sets.newLinkedHashSet();
        Bundle currentBundle = Platform.getBundle(bundleSymbolicName);
        if (currentBundle != null) {
            BundleWiring wiring = currentBundle.adapt(BundleWiring.class);
            if (wiring != null) {
                for (BundleWire wire : wiring.getRequiredWires(BundleRevision.BUNDLE_NAMESPACE)) {
                    if (wire.getProvider() != null && wire.getProvider().getBundle() != null) {
                        dependencies.add(wire.getProvider().getBundle().getSymbolicName());
                    }
                }
            }
        }
        return dependencies;
    }

}
