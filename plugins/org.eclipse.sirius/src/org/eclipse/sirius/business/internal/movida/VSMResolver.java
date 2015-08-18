/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.movida;

import java.text.MessageFormat;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.relations.Relation;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
/**
 * Resolves a set of logical Sirius URIs into the complete set of physical
 * VSM resources required to be loaded to provide all these Viewpoints.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class VSMResolver {
    /**
     * The registry to use for logical to physical URI resolution.
     */
    private final ViewpointRegistry registry;

    /**
     * Constructor.
     * 
     * @param regsitry
     *            the registry to use for logical to physical URI resolution.
     */
    public VSMResolver(ViewpointRegistry regsitry) {
        this.registry = Preconditions.checkNotNull(regsitry);
    }

    /**
     * Resolves a set of logical Sirius URIs into the set of physical URIs of
     * all the resources required to provide these viewpoints, including their
     * dependencies.
     * 
     * @param logicalURIs
     *            the set of logical Sirius URIs to resolve.
     * @return the physical URIs of all the resources which must be loaded to
     *         provide all the specified viewpoints.
     * @throws IllegalArgumentException
     *             if some of the (directly or indirectly) required viewpoints
     *             can not be resolved into a physical resource.
     */
    public Set<URI> resolve(Set<URI> logicalURIs) throws IllegalArgumentException {
        Preconditions.checkNotNull(logicalURIs);
        Set<URI> allLogical = computeAllLogicalRequirements(logicalURIs);
        final Set<URI> missing = Sets.newHashSet();
        Iterable<URI> allPhysical = Iterables.transform(allLogical, new Function<URI, URI>() {
            public URI apply(URI from) {
                Option<URI> provider = registry.getProvider(from);
                if (provider.some()) {
                    return provider.get();
                } else {
                    missing.add(from);
                    return null;
                }
            }
        });
        if (!missing.isEmpty()) {
            String msg = MessageFormat.format("Some of the required logical Sirius URIs could not be resolved to physical resources: {0}.", Joiner.on(", ").join(missing)); //$NON-NLS-2$
            throw new IllegalArgumentException(msg);
        } else {
            return ImmutableSet.copyOf(Iterables.filter(allPhysical, Predicates.notNull()));
        }
    }

    private Set<URI> computeAllLogicalRequirements(Set<URI> logicalURIs) {
        Set<URI> allLogical = Sets.newHashSet();
        Relation<URI> requires = registry.getRelations().getTransitiveRequires();
        for (URI uri : logicalURIs) {
            allLogical.add(uri);
            allLogical.addAll(requires.apply(uri));
        }
        return allLogical;
    }

}
