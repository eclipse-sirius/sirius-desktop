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
package org.eclipse.sirius.business.internal.movida.registry;

import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.ext.base.relations.Relation;
import org.eclipse.sirius.ext.base.relations.TransitiveClosure;
import org.eclipse.sirius.ext.base.relations.UnionRelation;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
/**
 * Exposes the various relations which can exist between viewpoints are
 * {@link Relation}s.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ViewpointRelations {
    /**
     * Base class to handle declared viewpoints dependencies as Relations.
     * 
     * @author pierre-charles.david@obeo.fr
     */
    private abstract class AbstractViewpointDependency implements Relation<URI> {
        /**
         * {@inheritDoc}
         */
        public Set<URI> apply(URI from) {
            Viewpoint vp = registry.getViewpoint(from);
            if (vp != null) {
                EList<URI> declared = getDependencies(vp);
                return ImmutableSet.copyOf(Iterables.filter(declared, Predicates.notNull()));
            }
            return Collections.emptySet();
        }

        protected abstract EList<URI> getDependencies(Viewpoint vp);
    }

    /**
     * The registry to use to resolve the URIs used for declared dependencies.
     */
    private final ViewpointRegistry registry;

    private final Relation<URI> reuse = new AbstractViewpointDependency() {
        @Override
        protected EList<URI> getDependencies(Viewpoint vp) {
            return vp.getReuses();
        }
    };

    private final Relation<URI> customize = new AbstractViewpointDependency() {
        @Override
        protected EList<URI> getDependencies(Viewpoint vp) {
            return vp.getCustomizes();
        }
    };

    private final Relation<URI> conflicts = new AbstractViewpointDependency() {
        @Override
        protected EList<URI> getDependencies(Viewpoint vp) {
            return vp.getConflicts();
        }
    };

    @SuppressWarnings("unchecked")
    private final Relation<URI> transitiveRequires = new TransitiveClosure<URI>(new UnionRelation<URI>(reuse, customize));

    /**
     * Constructor.
     * 
     * @param registry
     *            the registry to use to resolve the URIs used for declared
     *            dependencies.
     */
    public ViewpointRelations(ViewpointRegistry registry) {
        this.registry = Preconditions.checkNotNull(registry);
    }

    /**
     * Return a relation representing the direct reuse between viewpoints.
     * 
     * @return a relation representing the direct reuse between viewpoints.
     */
    public Relation<URI> getReuse() {
        return reuse;
    }

    /**
     * Return a relation representing the direct conflict between viewpoints.
     * 
     * @return a relation representing the direct conflict between viewpoints.
     */
    public Relation<URI> getConflicts() {
        return conflicts;
    }

    /**
     * Return a relation representing the transitive reuse between viewpoints.
     * 
     * @return a relation representing the transitive reuse between viewpoints.
     */
    public Relation<URI> getTransitiveRequires() {
        return transitiveRequires;
    }

    /**
     * Return a relation representing the direct customization between
     * viewpoints.
     * 
     * @return a relation representing the direct customization between
     *         viewpoints.
     */
    public Relation<URI> getCustomize() {
        return customize;
    }

    /**
     * Returns a relation representing the direct requirements between
     * viewpoints.
     * 
     * @return a relation representing the direct requirements between
     *         viewpoints.
     */
    public Relation<URI> getRequires() {
        return new UnionRelation<URI>(customize, reuse);
    }
}
