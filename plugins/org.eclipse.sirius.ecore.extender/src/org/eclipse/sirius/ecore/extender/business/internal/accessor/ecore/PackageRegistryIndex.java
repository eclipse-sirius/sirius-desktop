/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

/**
 * Index for all EClasses defined in a package registry.
 * 
 * @author pcdavid
 */
public class PackageRegistryIndex {
    private static final String SEPARATOR = "."; //$NON-NLS-1$

    private static final String AQL_SEPARATOR = "::"; //$NON-NLS-1$

    private final EPackage.Registry runtimeTypeRegistry;

    private final Predicate<EPackage> packageFilter;

    private final Map<String, List<EClass>> index = new HashMap<>();

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
        Collection<EClass> result = index.getOrDefault(name, List.of());
        if (Boolean.valueOf(System.getProperty("org.eclipse.sirius.enableUnsafeOptimisations", "false")) && result.isEmpty() && !initializedDescriptors) { //$NON-NLS-1$ //$NON-NLS-2$
            indexTypesFromRegistry(true);
            initializedDescriptors = true;
            result = index.getOrDefault(name, List.of());
        }
        return result;
    }

    private void indexTypesFrom(EPackage value) {
        if (packageFilter.test(value)) {
            for (EClassifier classifier : value.getEClassifiers()) {
                if (classifier instanceof EClass cur) {
                    addToIndex(cur.getName(), cur);
                    addToIndex(value.getName() + SEPARATOR + cur.getName(), cur);
                    addToIndex(value.getName() + AQL_SEPARATOR + cur.getName(), cur);
                }
            }
        }
    }
    
    private void addToIndex(String key, EClass eClass) {
        index.putIfAbsent(key, new ArrayList<>());
        index.get(key).add(eClass);
    }

}
