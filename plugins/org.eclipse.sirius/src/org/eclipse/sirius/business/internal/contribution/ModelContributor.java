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
package org.eclipse.sirius.business.internal.contribution;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.FeatureContribution;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
/**
 * Applies a set of contributions on a whole target model.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ModelContributor {

    /**
     * The set of root elements added inside the target model during the last
     * application of this contributor.
     */
    protected Set<EObject> additions;

    /**
     * The function to use to find all the potentially applicable contributions
     * from a source model.
     */
    protected Function<Iterable<EObject>, Iterable<Contribution>> contributionFinder;

    /**
     * The resolver to use on the contributions to locate their actual source
     * and target elements.
     */
    private final ReferenceResolver resolver;

    /**
     * Create a new {@link ModelContributor}.
     * 
     * @param contributionFinder
     *            the function to use to find all the potentially applicable
     *            contributions from a source model. They should be returned in
     *            the order the must be applied.
     * @param resolver
     *            the resolver to use on the contributions to locate their
     *            actual source and target elements.
     * 
     */
    public ModelContributor(Function<Iterable<EObject>, Iterable<Contribution>> contributionFinder, ReferenceResolver resolver) {
        this.contributionFinder = Preconditions.checkNotNull(contributionFinder);
        this.resolver = Preconditions.checkNotNull(resolver);
    }

    /**
     * Applies all the applicable contributions found in the sources (including
     * the target model itself) to the target model.
     * 
     * @param targetModel
     *            the model to which the contributions should be applied.
     * @param contributionSources
     *            source models, in addition to the target model itself, which
     *            may provide contributions.
     * @return the modified target model, with the contributions applied.
     */
    public EObject apply(final EObject targetModel, Collection<? extends EObject> contributionSources) {
        Preconditions.checkNotNull(targetModel);
        Preconditions.checkNotNull(contributionSources);

        final List<EObject> allSources = Lists.newArrayList(contributionSources);
        List<Contribution> allContributions = findAllContributions(targetModel, allSources);
        List<ResolvedContribution> resolvedContributions = resolve(allContributions, allSources);

        additions = Sets.newHashSet();
        for (ResolvedContribution rc : resolvedContributions) {
            for (ResolvedFeatureContribution rfc : rc.getResolvedFeatureContributions()) {
                additions.addAll(rfc.apply());
            }
        }

        return targetModel;
    }

    /**
     * Returns the set of root elements added inside the target model during the
     * last application of this contributor.
     * 
     * @return the set of root elements added inside the target model.
     */
    public Set<EObject> getAdditions() {
        return ImmutableSet.copyOf(additions);
    }

    /**
     * Finds all the contributions to apply, in the order they should be
     * applied.
     * 
     * @param targetModel
     *            the target model.
     * @param sources
     *            all the contribution sources.
     * @return all the contributions to apply, in the order they should be
     *         applied.
     */
    protected List<Contribution> findAllContributions(EObject targetModel, Iterable<EObject> sources) {
        return Lists.newArrayList(contributionFinder.apply(sources));
    }

    private List<ResolvedContribution> resolve(Collection<Contribution> contributions, List<EObject> roots) {
        List<ResolvedContribution> result = Lists.newArrayList();
        for (Contribution contribution : contributions) {
            HashMap<String, Object> context = Maps.newHashMap();
            context.put("self", contribution); //$NON-NLS-1$
            context.put("sources", roots); //$NON-NLS-1$
            Option<EObject> source = resolver.resolve(contribution.getSource(), context);
            Option<EObject> target = resolver.resolve(contribution.getTarget(), context);
            if (source.some() && target.some()) {
                result.add(new ResolvedContribution(contribution, source.get(), target.get()));
            }
        }
        return result;
    }

    /**
     * A contribution with its source an target resolved to concrete objects.
     */
    private static class ResolvedContribution {
        final Contribution contribution;

        final EObject sourceObject;

        final EObject targetObject;

        public ResolvedContribution(Contribution contribution, EObject sourceObject, EObject targetObject) {
            this.contribution = contribution;
            this.sourceObject = sourceObject;
            this.targetObject = targetObject;
        }

        public Collection<ResolvedFeatureContribution> getResolvedFeatureContributions() {
            Collection<ResolvedFeatureContribution> result = Lists.newArrayListWithExpectedSize(contribution.getFeatureMask().size());
            for (FeatureContribution fc : contribution.getFeatureMask()) {
                result.add(new ResolvedFeatureContribution(fc, sourceObject, targetObject));
            }
            return result;
        }
    }

    /**
     * A feature contribution with its source an target resolved to concrete
     * objects.
     */
    private static class ResolvedFeatureContribution {
        final FeatureContribution featureContribution;

        final EObject sourceObject;

        final EObject targetObject;

        public ResolvedFeatureContribution(FeatureContribution featureContribution, EObject sourceObject, EObject targetObject) {
            this.featureContribution = featureContribution;
            this.sourceObject = sourceObject;
            this.targetObject = targetObject;
        }

        public Set<EObject> apply() {
            return new FeatureContributor(featureContribution).apply(targetObject, sourceObject);
        }
    }
}
