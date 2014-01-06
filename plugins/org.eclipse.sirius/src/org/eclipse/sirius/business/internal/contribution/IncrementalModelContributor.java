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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.FeatureContribution;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 * Similar to a {@link ModelContributor}, but successive operations with
 * different inputs incrementally modify the result of the first one.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class IncrementalModelContributor extends ModelContributor {
    /**
     * The resulting model. <code>null</code> initially, before the first
     * contribution application.
     */
    private EObject model;

    private Map<EObject, Object> modelIds;

    private Function<EObject, Object> idFunction;

    private Map<EObject, Object> contributions;

    private Copier currentCopier;

    private Map<Viewpoint, String> viewpointUris;

    private List<EStructuralFeature> targetsWithEOpposites;

    /**
     * Create a new {@link IncrementalModelContributor}.
     * 
     * @param contributionFinder
     *            the function to use to find all the potentially applicable
     *            contributions from a source model.
     * @param resolver
     *            the resolver to use on the contributions to locate their
     *            actual source and target elements.
     * @param idFunction
     *            the function used to obtain logical identifiers for model
     *            elements, required to perform the incremental update.
     * 
     */
    public IncrementalModelContributor(Function<Iterable<EObject>, Iterable<Contribution>> contributionFinder, ReferenceResolver resolver, Function<EObject, Object> idFunction) {
        super(contributionFinder, resolver);
        this.idFunction = Preconditions.checkNotNull(idFunction);
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
    public EObject apply(EObject targetModel, Collection<? extends EObject> contributionSources) {
        Collection<EObject> referenceInputs = Lists.newArrayList(contributionSources);
        currentCopier = new EcoreUtil.Copier();
        List<EObject> inputs = Lists.newArrayList(currentCopier.copyAll(referenceInputs));
        currentCopier.copyReferences();

        Map<EObject, Object> inputIds = Maps.newHashMap();
        for (EObject root : referenceInputs) {
            for (EObject obj : AllContents.of(root, true)) {
                inputIds.put(currentCopier.get(obj), idFunction.apply(obj));
            }
        }

        viewpointUris = Maps.newHashMap();
        for (Viewpoint originalVP : Iterables.filter(currentCopier.keySet(), Viewpoint.class)) {
            Option<URI> uri = new ViewpointQuery(originalVP).getViewpointURI();
            if (uri.some()) {
                viewpointUris.put((Viewpoint) currentCopier.get(originalVP), uri.get().toString());
            }
        }

        EObject result = super.apply(currentCopier.get(targetModel), inputs);
        postProcess(result);
        contributions = Maps.newHashMap(Maps.filterKeys(inputIds, Predicates.in(additions)));

        if (model == null) {
            model = result;
            modelIds = Maps.newHashMap(Maps.filterKeys(inputIds, new Predicate<EObject>() {
                public boolean apply(EObject input) {
                    return input == model || EcoreUtil.isAncestor(model, input);
                }
            }));
        } else {
            Function<EObject, Object> f = update(result, inputIds);
            Map<EObject, Object> newIds = Maps.newHashMap();
            for (EObject obj : AllContents.of(model, true)) {
                newIds.put(obj, f.apply(obj));
            }
            modelIds = newIds;
        }
        return model;
    }

    /**
     * Allow sub-classes to adjust the resulting model right after the
     * contributions have been applied.
     * 
     * @param result
     *            the result model.
     */
    protected void postProcess(EObject result) {
        // Nothing by default.
    }

    /**
     * Returns the identifier associated with obj, which must be an element of
     * the result model.
     * 
     * @param obj
     *            an element of the result model.
     * @return the identifier of obj.
     */
    public Object getIdentifier(EObject obj) {
        return modelIds.get(obj);
    }

    /**
     * Reset the state of the contributor.
     * 
     * @param newModel
     *            the model.
     * @param ids
     *            the identifiers of every elements of the model.
     */
    public void resetState(EObject newModel, Map<EObject, ? extends Object> ids) {
        this.model = newModel;
        this.modelIds = Maps.newHashMap(ids);
    }

    public Map<EObject, Object> getContributionPoints() {
        return ImmutableMap.copyOf(contributions);
    }

    /**
     * Returns the logical Sirius URI of a copied Sirius instance. The URI
     * can not be retrieved from the copy as it is not inside a resource.
     * 
     * @param copiedSirius
     *            a copied instance of Sirius
     * @return the logical URI of the Sirius, as retrived from the original
     *         instance which lives in a VSM.
     */
    protected String getSiriusURI(Viewpoint copiedSirius) {
        return viewpointUris.get(copiedSirius);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Contribution> findAllContributions(EObject targetModel, Iterable<EObject> sources) {
        /*
         * Return the versions of the Contribution objects from the copied
         * sources, not the original (as those would point to original elements
         * as their source and targets).
         */
        List<Contribution> rawResult = super.findAllContributions(targetModel, sources);
        List<Contribution> result = Lists.newArrayListWithCapacity(rawResult.size());
        for (Contribution contribution : rawResult) {
            if (currentCopier.containsKey(contribution)) {
                EObject copy = currentCopier.get(contribution);
                if (copy instanceof Contribution) {
                    result.add((Contribution) copy);
                }
            } else {
                result.add(contribution);
            }
        }
        targetsWithEOpposites = Lists.newArrayList();
        for (Contribution contribution : result) {
            for (FeatureContribution fc : contribution.getFeatureMask()) {
                if (fc.getTargetFeature() instanceof EReference && ((EReference) fc.getTargetFeature()).getEOpposite() != null) {
                    targetsWithEOpposites.add(((EReference) fc.getTargetFeature()).getEOpposite());
                }
            }
        }
        return result;
    }

    private Function<EObject, Object> update(EObject result, final Map<EObject, Object> inputIds) {
        Function<EObject, Object> idFunction2 = new Function<EObject, Object>() {
            public Object apply(EObject from) {
                if (modelIds.containsKey(from)) {
                    return modelIds.get(from);
                } else {
                    return inputIds.get(from);
                }
            }
        };
        Matcher matcher = new IdentifierBasedMatcher(idFunction2);
        Updater updater = new Updater(matcher, model, result);
        updater.setFeaturesToIgnore(targetsWithEOpposites);
        updater.update();
        return idFunction2;
    }
}
