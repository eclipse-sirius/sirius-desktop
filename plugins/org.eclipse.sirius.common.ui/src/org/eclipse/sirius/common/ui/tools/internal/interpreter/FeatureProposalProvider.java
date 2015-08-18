/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.internal.interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.internal.interpreter.FeatureInterpreter;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A {@link IProposalProvider} to provide completion for the feature
 * interpreter.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class FeatureProposalProvider implements IProposalProvider {

    private static final String SEPARATOR_1 = ":"; //$NON-NLS-1$

    private static final String SEPARATOR_2 = " - "; //$NON-NLS-1$

    private static final String SEPARATOR_3 = "[0..*]"; //$NON-NLS-1$

    @Override
    public ContentProposal getNewEmtpyExpression() {
        return new ContentProposal(FeatureInterpreter.PREFIX, FeatureInterpreter.PREFIX, "New feature access expression.", FeatureInterpreter.PREFIX.length());
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof FeatureInterpreter)) {
            proposals = Collections.emptyList();
        } else if (context.getContents() == null || context.getContents().length() == 0) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            Set<ContentProposal> intersectingProposals = null;
            IInterpreterContext interpreterContext = context.getInterpreterContext();
            for (TypeName type : interpreterContext.getTargetType().getPossibleTypes()) {
                for (EClass possibleEClass : Iterables.filter(type.search(interpreterContext.getAvailableEPackages()), EClass.class)) {
                    Set<ContentProposal> proposalsForThisType = Sets.newLinkedHashSet(getProposals(context.getContents(), context.getPosition(), possibleEClass));
                    if (intersectingProposals == null) {
                        intersectingProposals = proposalsForThisType;
                    } else {
                        intersectingProposals = Sets.intersection(intersectingProposals, proposalsForThisType);
                    }
                }
            }

            if (intersectingProposals != null) {
                proposals = Lists.newArrayList(intersectingProposals);
            } else {
                proposals = Collections.<ContentProposal> emptyList();
            }
        }
        return proposals;
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof FeatureInterpreter)) {
            proposals = Collections.emptyList();
        } else if (context.getCurrentSelected() == null) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            proposals = getProposals(context.getTextSoFar(), context.getCursorPosition(), context.getCurrentSelected().eClass());
        }
        return proposals;
    }

    /**
     * Evaluates the content proposals for a given expression and returns the
     * result as a list.
     * 
     * @param writtenExpression
     *            The complete expression with feature: prefix.
     * @param cursorPosition
     *            The current cursor position to consider only characters before
     *            it.
     * @param currentElementType
     *            The current element type in the context.
     * @return content proposal list.
     */
    private List<ContentProposal> getProposals(String writtenExpression, int cursorPosition, EClass currentElementType) {
        final List<ContentProposal> proposals = new ArrayList<ContentProposal>();

        // Keep only characters before cursor
        String featureNamePrefix = writtenExpression.substring(0, cursorPosition);
        // Remove not needed space characters.
        featureNamePrefix = featureNamePrefix.trim();
        // Remove "feature:" prefix if the cursor position is after the prefix
        // If the cursor position is before the prefix, there is no proposal
        // returned.
        if (featureNamePrefix.length() >= FeatureInterpreter.PREFIX.length()) {
            featureNamePrefix = featureNamePrefix.trim().substring(FeatureInterpreter.PREFIX.length());

            String eObjectName = EObject.class.getSimpleName();

            String eContainerFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[0];
            if (eContainerFeatureName.startsWith(featureNamePrefix)) {
                String displayedName = eContainerFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_2 + eObjectName;
                proposals.add(new ContentProposal(eContainerFeatureName, displayedName, displayedName));
            }

            String eContentsFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[1];
            if (eContentsFeatureName.startsWith(featureNamePrefix)) {
                String displayedName = eContentsFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_3 + SEPARATOR_2 + eObjectName;
                proposals.add(new ContentProposal(eContentsFeatureName, displayedName, displayedName));
            }

            String eAllContentsFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[2];
            if (eAllContentsFeatureName.startsWith(featureNamePrefix)) {
                String displayedName = eAllContentsFeatureName + SEPARATOR_1 + "TreeIterator" + SEPARATOR_2 + eObjectName; //$NON-NLS-1$
                proposals.add(new ContentProposal(eAllContentsFeatureName, displayedName, displayedName));
            }

            String eClassFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[3];
            if (eClassFeatureName.startsWith(featureNamePrefix)) {
                String displayedName = eClassFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_3 + SEPARATOR_2 + EClass.class.getSimpleName();
                proposals.add(new ContentProposal(eClassFeatureName, displayedName, displayedName));
            }

            String eReferencesFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[4];
            if (eReferencesFeatureName.startsWith(featureNamePrefix)) {
                String displayedName = eReferencesFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_3 + SEPARATOR_2 + eObjectName;
                proposals.add(new ContentProposal(eReferencesFeatureName, displayedName, displayedName));
            }

            if (currentElementType != null) {
                for (EStructuralFeature eStructuralFeature : currentElementType.getEAllStructuralFeatures()) {
                    if (eStructuralFeature.getName().startsWith(featureNamePrefix)) {
                        String displayedName = eStructuralFeature.getName()
                                + (eStructuralFeature.isMany() ? "[" + eStructuralFeature.getLowerBound() + ".."  //$NON-NLS-1$//$NON-NLS-2$
                                        + (eStructuralFeature.getUpperBound() == -1 ? "*" : eStructuralFeature.getUpperBound()) + "]" : "") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                                + (eStructuralFeature.getEType() != null ? SEPARATOR_1 + eStructuralFeature.getEType().getName() : "") + SEPARATOR_2 //$NON-NLS-1$
                                + eStructuralFeature.getEContainingClass().getName();
                        proposals.add(new ContentProposal(eStructuralFeature.getName(), displayedName, displayedName));
                    }
                }
            }
        }
        return proposals;
    }

}
