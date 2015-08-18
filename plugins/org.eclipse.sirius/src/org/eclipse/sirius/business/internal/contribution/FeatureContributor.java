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

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.description.contribution.AddFeatureContribution;
import org.eclipse.sirius.description.contribution.ClearFeatureContribution;
import org.eclipse.sirius.description.contribution.FeatureContribution;
import org.eclipse.sirius.description.contribution.IgnoreFeatureContribution;
import org.eclipse.sirius.description.contribution.RemoveFeatureContribution;
import org.eclipse.sirius.description.contribution.ResetFeatureContribution;
import org.eclipse.sirius.description.contribution.SetFeatureContribution;
import org.eclipse.sirius.description.contribution.util.ContributionSwitch;
import org.eclipse.sirius.ext.emf.EStructuralFeatureQuery;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

/**
 * A feature contribution can modify the value of a single feature in a target
 * object, using a value from a type-compatible feature in a source object. How
 * exactly the value of target feature is combined with the initial value of the
 * source feature depends on the contribution mode.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class FeatureContributor {
    private static final String TARGET_OBJECT = "Target object";

    private static final String SOURCE_OBJECT = "Source object";

    private final FeatureContribution contribution;

    private Set<EObject> additions;

    /**
     * Constructor.
     * 
     * @param contribution
     *            the definition of the contribution to apply.
     */
    public FeatureContributor(FeatureContribution contribution) {
        this.contribution = Preconditions.checkNotNull(contribution);
        // Preconditions.checkArgument(contribution.getSourceFeature() != null,
        // "No source feature specified.");
        Preconditions.checkArgument(contribution.getTargetFeature() != null, "No target feature specified.");
        Preconditions.checkArgument(contribution.getTargetFeature().isChangeable(), "Target feature not modifiable.");
        Preconditions.checkArgument(new EStructuralFeatureQuery(contribution.getTargetFeature()).isAssignableFrom(contribution.getSourceFeature()), "Incompatible source and target features.");
    }

    /**
     * Apply the contribution using the specified source and target objects.
     * 
     * @param target
     *            the object in which to contribute.
     * @param source
     *            the object from which to obtain the values to contribute.
     * @return the set of root elements added inside the target object of the
     *         last application.
     */
    public Set<EObject> apply(final EObject target, final EObject source) {
        Preconditions.checkNotNull(source, "No source object specified.");
        Preconditions.checkNotNull(target, "No target object specified.");
        this.additions = Collections.emptySet();
        ContributionSwitch<Void> contributionSwitch = new ContributionSwitch<Void>() {
            @Override
            public Void caseIgnoreFeatureContribution(IgnoreFeatureContribution object) {
                // Ignore has nothing to do.
                return null;
            };

            @Override
            public Void caseSetFeatureContribution(SetFeatureContribution sfc) {
                doSet(target, source, sfc);
                return null;
            }

            @Override
            public Void caseAddFeatureContribution(AddFeatureContribution afc) {
                doAdd(target, source, afc);
                return null;
            }

            @Override
            public Void caseRemoveFeatureContribution(RemoveFeatureContribution afc) {
                doRemove(target, source, afc);
                return null;
            }

            @Override
            public Void caseClearFeatureContribution(ClearFeatureContribution cfc) {
                doClear(target, cfc);
                return null;
            }

            @Override
            public Void caseResetFeatureContribution(ResetFeatureContribution rfc) {
                doReset(target, rfc);
                return null;
            }
        };
        contributionSwitch.doSwitch(contribution);
        return additions;
    }

    private void doSet(EObject target, EObject source, SetFeatureContribution sfc) {
        EStructuralFeature sourceFeature = sfc.getSourceFeature();
        EStructuralFeature targetFeature = sfc.getTargetFeature();

        Object value = sourceFeature != null ? source.eGet(sourceFeature) : source;
        target.eSet(targetFeature, value);

        if (isAddition()) {
            additions = Collections.singleton((EObject) value);
        }
    }

    private void doAdd(final EObject target, final EObject source, AddFeatureContribution afc) {
        EStructuralFeature sourceFeature = afc.getSourceFeature();
        if (sourceFeature != null) {
            Preconditions.checkArgument(sourceFeature.isMany());
            checkFeatureIsPresent(source, sourceFeature, SOURCE_OBJECT);
        }

        EStructuralFeature targetFeature = afc.getTargetFeature();
        Preconditions.checkArgument(targetFeature.isMany());
        checkFeatureIsPresent(target, targetFeature, TARGET_OBJECT);

        Collection<Object> targetValues = getMany(target, targetFeature);
        Collection<Object> sourceValues;
        if (sourceFeature != null) {
            sourceValues = getMany(source, sourceFeature);
        } else {
            sourceValues = Collections.<Object> singletonList(source);
        }

        if (isAddition()) {
            // Get to objects from sourceValues before they are removed when
            // added to the source.
            additions = ImmutableSet.copyOf(Iterables.filter(sourceValues, EObject.class));
        }

        targetValues.addAll(sourceValues);
    }

    private void doRemove(final EObject target, final EObject source, RemoveFeatureContribution afc) {
        EStructuralFeature sourceFeature = afc.getSourceFeature();
        if (sourceFeature != null) {
            Preconditions.checkArgument(sourceFeature.isMany());
            checkFeatureIsPresent(source, sourceFeature, SOURCE_OBJECT);
        }

        EStructuralFeature targetFeature = afc.getTargetFeature();
        Preconditions.checkArgument(targetFeature.isMany());
        checkFeatureIsPresent(target, targetFeature, TARGET_OBJECT);

        Collection<Object> targetValues = getMany(target, targetFeature);
        Collection<Object> sourceValues;
        if (sourceFeature != null) {
            sourceValues = getMany(source, sourceFeature);
        } else {
            sourceValues = Collections.<Object> singletonList(source);
        }
        targetValues.removeAll(sourceValues);
    }

    private void doClear(EObject target, ClearFeatureContribution cfc) {
        EStructuralFeature targetFeature = cfc.getTargetFeature();
        Preconditions.checkArgument(targetFeature.isMany());
        checkFeatureIsPresent(target, targetFeature, TARGET_OBJECT);

        Collection<Object> targetValues = getMany(target, targetFeature);
        targetValues.clear();
    }

    private void doReset(EObject target, ResetFeatureContribution rfc) {
        EStructuralFeature targetFeature = rfc.getTargetFeature();
        checkFeatureIsPresent(target, targetFeature, TARGET_OBJECT);
        target.eUnset(targetFeature);
    }

    /**
     * Get the values of a many-valued feature as a collection.
     */
    @SuppressWarnings("unchecked")
    private Collection<Object> getMany(EObject target, EStructuralFeature targetFeature) {
        Object rawValue = target.eGet(targetFeature);
        if (rawValue != null && !(rawValue instanceof Collection<?>)) {
            throw new RuntimeException(MessageFormat.format("Expected a collection from many-valued feature {0} but got a {1}", featureString(targetFeature), rawValue.getClass()));
        }
        return (Collection<Object>) rawValue;
    }

    private String featureString(EStructuralFeature feature) {
        return feature.getContainerClass().getName() + "." + feature.getName(); //$NON-NLS-1$
    }

    private void checkFeatureIsPresent(EObject obj, EStructuralFeature feature, String name) {
        boolean present = new EStructuralFeatureQuery(feature).existsIn(obj);
        Preconditions.checkArgument(present, MessageFormat.format("{0} ({1}) does not have feature {2}", name, obj, featureString(feature)));
    }

    private boolean isAddition() {
        return contribution.getTargetFeature() instanceof EReference && ((EReference) contribution.getTargetFeature()).isContainment();
    }
}
