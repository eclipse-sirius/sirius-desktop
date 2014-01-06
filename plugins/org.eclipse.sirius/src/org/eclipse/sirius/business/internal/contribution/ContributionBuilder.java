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

import java.util.Iterator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.description.contribution.ComputedEObjectReference;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionFactory;
import org.eclipse.sirius.description.contribution.DirectEObjectReference;
import org.eclipse.sirius.description.contribution.FeatureContribution;
import org.eclipse.sirius.ext.emf.EStructuralFeatureQuery;

import com.google.common.base.Preconditions;

/**
 * Builder class to facilitate the creation of complex <code>Contribution</code>
 * .
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ContributionBuilder {
    private static ContributionFactory factory = ContributionFactory.eINSTANCE;

    private EClass sourceType;

    private EClass targetType;

    private Contribution contribution;

    /**
     * Constructor.
     */
    public ContributionBuilder() {
        contribution = factory.createContribution();
    }

    /**
     * Returns the contribution built.
     * 
     * @return the contribution built.
     */
    public Contribution build() {
        return contribution;
    }

    /**
     * Sets the source of the contribution to a direct reference to the
     * specified object.
     * 
     * @param source
     *            the object to use as source for the contribution.
     * @return the builder itself.
     */
    public ContributionBuilder from(EObject source) {
        this.sourceType = source.eClass();
        DirectEObjectReference ref = factory.createDirectEObjectReference();
        ref.setValue(source);
        contribution.setSource(ref);
        return this;
    }

    /**
     * Sets the source of the contribution to a computed reference using the
     * specified expression.
     * 
     * @param expression
     *            the expression to use to find the contribution source.
     * @param srcType
     *            the expected type of the resolved contribution source.
     * @return the builder itself.
     */
    public ContributionBuilder from(String expression, EClass srcType) {
        this.sourceType = srcType;
        ComputedEObjectReference ref = factory.createComputedEObjectReference();
        ref.setValueExpression(expression);
        contribution.setSource(ref);
        return this;
    }

    /**
     * Sets the target of the contribution to a direct reference to the
     * specified object.
     * 
     * @param target
     *            the object to use as target for the contribution.
     * @return the builder itself.
     */
    public ContributionBuilder to(EObject target) {
        this.targetType = target.eClass();
        DirectEObjectReference ref = factory.createDirectEObjectReference();
        ref.setValue(target);
        contribution.setTarget(ref);
        return this;
    }

    /**
     * Sets the target of the contribution to a computed reference using the
     * specified expression.
     * 
     * @param expression
     *            the expression to use to find the contribution target.
     * @param tgtType
     *            the expected type of the resolved contribution target.
     * @return the builder itself.
     */
    public ContributionBuilder to(String expression, EClass tgtType) {
        this.targetType = tgtType;
        ComputedEObjectReference ref = factory.createComputedEObjectReference();
        ref.setValueExpression(expression);
        contribution.setTarget(ref);
        return this;
    }

    // CHECKSTYLE:OFF

    public ContributionBuilder keep(EStructuralFeature targetFeature) {
        removeFeatureContributionsTo(targetFeature);
        return this;
    }

    public ContributionBuilder keep(String targetFeature) {
        return keep(targetType.getEStructuralFeature(targetFeature));
    }

    public ContributionBuilder set(EStructuralFeature targetFeature, EStructuralFeature sourceFeature) {
        FeatureContribution fc = factory.createSetFeatureContribution();
        configureFeatureContribution(fc, targetFeature, sourceFeature);
        return this;
    }

    public ContributionBuilder set(EStructuralFeature feature) {
        return set(feature, feature);
    }

    public ContributionBuilder set(String targetFeature, String sourceFeature) {
        return set(targetType.getEStructuralFeature(targetFeature), sourceType.getEStructuralFeature(sourceFeature));
    }

    public ContributionBuilder set(String feature) {
        return set(feature, feature);
    }

    public ContributionBuilder setAllAttributes() {
        for (EAttribute attr : targetType.getEAllAttributes()) {
            if (attr.isChangeable() && !attr.isDerived()) {
                set(attr);
            }
        }
        return this;
    }

    public ContributionBuilder addAllAttributes() {
        for (EAttribute attr : targetType.getEAllAttributes()) {
            if (attr.isChangeable() && !attr.isDerived() && attr.isMany()) {
                add(attr);
            }
        }
        return this;
    }

    public ContributionBuilder add(EStructuralFeature targetFeature, EStructuralFeature sourceFeature) {
        FeatureContribution fc = factory.createAddFeatureContribution();
        configureFeatureContribution(fc, targetFeature, sourceFeature);
        return this;
    }

    public ContributionBuilder add(EStructuralFeature feature) {
        return add(feature, feature);
    }

    public ContributionBuilder add(String targetFeature, String sourceFeature) {
        return add(targetType.getEStructuralFeature(targetFeature), sourceType.getEStructuralFeature(sourceFeature));
    }

    public ContributionBuilder add(String feature) {
        return add(feature, feature);
    }

    public ContributionBuilder remove(EStructuralFeature targetFeature, EStructuralFeature sourceFeature) {
        FeatureContribution fc = factory.createRemoveFeatureContribution();
        configureFeatureContribution(fc, targetFeature, sourceFeature);
        return this;
    }

    public ContributionBuilder remove(EStructuralFeature feature) {
        return remove(feature, feature);
    }

    public ContributionBuilder remove(String targetFeature, String sourceFeature) {
        return remove(targetType.getEStructuralFeature(targetFeature), sourceType.getEStructuralFeature(sourceFeature));
    }

    public ContributionBuilder remove(String feature) {
        return remove(feature, feature);
    }

    private void configureFeatureContribution(FeatureContribution fc, EStructuralFeature targetFeature, EStructuralFeature sourceFeature) {
        Preconditions.checkArgument(new EStructuralFeatureQuery(sourceFeature).isAssignableFrom(targetFeature));
        fc.setSourceFeature(sourceFeature);
        fc.setTargetFeature(targetFeature);
        addFeatureContribution(fc);
    }

    private void addFeatureContribution(FeatureContribution fc) {
        removeFeatureContributionsTo(fc.getTargetFeature());
        contribution.getFeatureMask().add(fc);
    }

    private void removeFeatureContributionsTo(EStructuralFeature targetFeature) {
        for (Iterator<FeatureContribution> iter = contribution.getFeatureMask().iterator(); iter.hasNext();) {
            FeatureContribution existingFc = iter.next();
            if (existingFc.getTargetFeature().equals(targetFeature)) {
                iter.remove();
            }
        }
    }
    // CHECKSTYLE:ON

}
