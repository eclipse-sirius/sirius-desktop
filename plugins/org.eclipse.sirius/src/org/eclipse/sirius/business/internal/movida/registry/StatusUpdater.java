/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.movida.registry;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry.RegistryChange;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Updates or re-computes the status of all the entries in the registry.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class StatusUpdater {
    private static final String DIAGNOSTICS_SOURCE = "Sirius Registry"; //$NON-NLS-1$

    private static final int UNDECLARED_DEPENDENCY = 0;

    private static final int DECLARED_DEPENDENCY_UNAVAILABLE = 1;

    private static final int PHYSICAL_DEPENDENCY_UNAVAILABLE = 2;

    private final ResourceSetImpl resourceSet;

    private final Map<URI, Entry> entries;

    @SuppressWarnings("unused")
    private final RegistryChange change;

    /**
     * Constructor.
     * 
     * @param resourceSet
     *            the resource set in which the Viewpoints are loaded.
     * @param entries
     *            the entries whose status should be updated.
     * @param change
     *            a description of the actual change which triggered an update.
     */
    public StatusUpdater(ResourceSetImpl resourceSet, Map<URI, Entry> entries, RegistryChange change) {
        this.resourceSet = Preconditions.checkNotNull(resourceSet);
        this.entries = Preconditions.checkNotNull(Collections.unmodifiableMap(entries));
        this.change = Preconditions.checkNotNull(change);
    }

    /**
     * Updates the status of all the entries in the registry.
     */
    public void updateStatus() {
        checkAllLogicalDependenciesAreAvailable();
        checkAllActualDependenciesAreDeclared();
        checkAllActualDependenciesAreAvailable();
        resolveCrossResourcesReferences();
        performSemanticValidation();
    }

    private void performSemanticValidation() {
        for (Entry entry : entries.values()) {
            performSemanticValidation(entry);
        }
    }

    private void performSemanticValidation(Entry entry) {
        Diagnostic result = Diagnostician.INSTANCE.validate(entry.getSirius());
        if (result.getSeverity() > Diagnostic.WARNING) {
            entry.setState(ViewpointState.INVALID);
        }
        entry.getDiagnostics().add(result);
    }

    private void resolveCrossResourcesReferences() {
        for (Entry entry : entries.values()) {
            if (entry.getState() != ViewpointState.INVALID) {
                try {
                    EcoreUtil.resolveAll(entry.getSirius());
                    entry.setState(ViewpointState.RESOLVED);
                    // CHECKSTYLE:OFF
                } catch (RuntimeException e) {
                    // CHECKSTYLE:ON
                    entry.setState(ViewpointState.INVALID);
                }
            }
        }
    }

    private void checkAllActualDependenciesAreAvailable() {
        for (Entry entry : entries.values()) {
            checkAllActualDependenciesAreAvailable(entry);
        }
    }

    private void checkAllActualDependenciesAreAvailable(Entry entry) {
        Set<URI> actualPhysical = ImmutableSet.copyOf(Iterables.transform(entry.getActualDependencies(), new Function<URI, URI>() {
            @Override
            public URI apply(URI from) {
                return resourceSet.getURIConverter().normalize(from);
            }
        }));
        Set<URI> availablePhysical = ImmutableSet.copyOf(Iterables.transform(entries.values(), new Function<Entry, URI>() {
            @Override
            public URI apply(Entry from) {
                return from.getResource().getURI();
            };
        }));
        Set<URI> unavailable = Sets.difference(actualPhysical, availablePhysical);
        if (!unavailable.isEmpty()) {
            entry.setState(ViewpointState.INVALID);
            Object[] data = Iterables.toArray(Iterables.transform(unavailable, Functions.toStringFunction()), String.class);
            addDiagnostic(entry, Diagnostic.ERROR, PHYSICAL_DEPENDENCY_UNAVAILABLE, "Sirius definition depends on resources not available.", data); //$NON-NLS-1$
        }
    }

    private void checkAllLogicalDependenciesAreAvailable() {
        for (Entry entry : entries.values()) {
            checkLogicalDependenciesAvailable(entry);
        }
    }

    private void checkLogicalDependenciesAvailable(Entry entry) {
        for (URI dependency : entry.getDeclaredDependencies()) {
            if (!entries.containsKey(dependency)) {
                entry.setState(ViewpointState.INVALID);
                addDiagnostic(entry, Diagnostic.ERROR, DECLARED_DEPENDENCY_UNAVAILABLE, "Invalid dependency declared to unavailable Sirius.", new Object[] { dependency }); //$NON-NLS-1$
            }
        }
    }

    private void checkAllActualDependenciesAreDeclared() {
        for (Entry entry : entries.values()) {
            checkActualDependencies(entry);
        }
    }

    private void checkActualDependencies(Entry entry) {
        Set<URI> declared = Sets.newHashSet(entry.getDeclaredDependencies());
        // Include both the logical URIs and their corresponding physical URIs
        // in the declared set.
        Set<URI> declaredResolved = Sets.newHashSet(Iterables.transform(declared, new Function<URI, URI>() {
            @Override
            public URI apply(URI from) {
                return resourceSet.getURIConverter().normalize(from);
            }
        }));
        declared.addAll(declaredResolved);
        Set<URI> actual = entry.getActualDependencies();
        SetView<URI> undeclared = Sets.difference(actual, declared);
        if (!undeclared.isEmpty()) {
            entry.setState(ViewpointState.INVALID);
            Object[] data = Iterables.toArray(Iterables.transform(undeclared, Functions.toStringFunction()), String.class);
            addDiagnostic(entry, Diagnostic.ERROR, UNDECLARED_DEPENDENCY, "Sirius has undeclared dependencies to other resources.", data); //$NON-NLS-1$
        }
    }

    private void addDiagnostic(Entry entry, int severity, int code, String message, Object[] data) {
        entry.getDiagnostics().add(new BasicDiagnostic(severity, DIAGNOSTICS_SOURCE, code, message, data));
    }
}
