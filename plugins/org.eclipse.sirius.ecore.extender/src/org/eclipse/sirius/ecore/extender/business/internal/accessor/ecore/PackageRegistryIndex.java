/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import com.google.common.base.Predicate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

/**
 * Index for all EClasses defined in a package registry.
 * 
 * @author pcdavid
 */
public class PackageRegistryIndex {
    private static final String SEPARATOR = "."; //$NON-NLS-1$

    private final EPackage.Registry runtimeTypeRegistry;

    private final Predicate<EPackage> packageFilter;

    private final Multimap<String, EClass> index = HashMultimap.create();

    private boolean initializedDescriptors;

    /**
     * Creates a new platform index and indexes all the already loaded EPackages
     * from the registry.
     * 
     * @param registry
     *            the package registry in which to look for EClasses.
     * @param packageFilter
     *            a predicate which can be used to prevent the indexing of some
     *            package. Only packages for which the predicate returns
     *            <code>true</code> will be indexed.
     */
    public PackageRegistryIndex(EPackage.Registry registry, Predicate<EPackage> packageFilter) {
        this.runtimeTypeRegistry = registry;
        this.packageFilter = packageFilter;
        indexTypesFromRegistry(false);
        if (!Boolean.valueOf(System.getProperty("org.eclipse.sirius.enableUnsafeOptimisations", "false"))) { //$NON-NLS-1$ //$NON-NLS-2$
            indexTypesFromRegistry(true);
        }
    }

    private void indexTypesFromRegistry(boolean descriptors) {
        if (descriptors) {
            // Re-index everything to be sure we do not miss anything and do not
            // index things twice.
            index.clear();
        }

        for (Object value : new ArrayList<Object>(runtimeTypeRegistry.values())) {
            if (!descriptors && (value instanceof EPackage)) {
                indexTypesFrom((EPackage) value);
            } else if (descriptors) {
                if (value instanceof EPackage) {
                    indexTypesFrom((EPackage) value);
                } else if (value instanceof EPackage.Descriptor) {
                    try {
                        indexTypesFrom(((EPackage.Descriptor) value).getEPackage());
                        // CHECKSTYLE:OFF
                    } catch (Throwable e) {
                        /*
                         * anything might happen here depending on the other
                         * Eclipse tools, and we've seen many time tools (like
                         * XText for instance) breaking all the others .
                         */
                        // CHECKSTYLE:ON
                    }
                }
            }
        }
    }

    /**
     * Returns all the indexed classes which match the specified name.
     * 
     * @param name
     *            the name of a class.
     * @return all the indexed classes with a matching name.
     */
    public Collection<EClass> getEClassesFromName(String name) {
        Collection<EClass> result = index.get(name);
        if (Boolean.valueOf(System.getProperty("org.eclipse.sirius.enableUnsafeOptimisations", "false")) && result.isEmpty() && !initializedDescriptors) { //$NON-NLS-1$ //$NON-NLS-2$
            indexTypesFromRegistry(true);
            initializedDescriptors = true;
            result = index.get(name);
        }
        return result;
    }

    private void indexTypesFrom(EPackage value) {
        if (packageFilter.apply(value)) {
            for (EClass cur : Iterables.filter(value.getEClassifiers(), EClass.class)) {
                index.put(cur.getName(), cur);
                index.put(value.getName() + SEPARATOR + cur.getName(), cur);
            }
        }
    }
}
