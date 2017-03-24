/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.internal.interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.internal.interpreter.FeatureInterpreter;
import org.eclipse.sirius.common.ui.Messages;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * All instances of this abstract {@link IProposalProvider} provide feature proposals of ECLass for feature interpreter.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class FeatureProposalProvider implements IProposalProvider {

    /**
     * Separator used to construct proposals.
     */
    protected static final String SEPARATOR_1 = ":"; //$NON-NLS-1$

    /**
     * Separator used to construct proposals.
     */
    protected static final String SEPARATOR_2 = " - "; //$NON-NLS-1$

    @Override
    public ContentProposal getNewEmtpyExpression() {
        return new ContentProposal(FeatureInterpreter.PREFIX, FeatureInterpreter.PREFIX, Messages.FeatureProposalProvider_newFeatureExpression, FeatureInterpreter.PREFIX.length());
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        final List<ContentProposal> proposals;
        if (context == null || (!(interpreter instanceof FeatureInterpreter)) && interpreter != null) {
            proposals = Collections.emptyList();
        } else if ((context.getContents() == null || context.getContents().length() == 0) && isPrefixUsed()) {
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

    /**
     * True if the provider use a prefix. False otherwise.
     * 
     * @return True if the provider use a prefix. False otherwise.
     */
    protected boolean isPrefixUsed() {
        return false;
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
     * Evaluates the content proposals for a given expression and returns the result as a list.
     * 
     * @param writtenExpression
     *            The complete expression with feature: prefix.
     * @param cursorPosition
     *            The current cursor position to consider only characters before it.
     * @param currentElementType
     *            The current element type in the context.
     * @return content proposal list.
     */
    private List<ContentProposal> getProposals(String writtenExpression, int cursorPosition, EClass currentElementType) {
        final List<ContentProposal> proposals = new ArrayList<ContentProposal>();

        // Keep only characters before cursor
        String featureNameWithPrefix = writtenExpression.substring(0, cursorPosition);
        // Remove not needed space characters.
        featureNameWithPrefix = featureNameWithPrefix.trim();
        // Remove "feature:" prefix if the cursor position is after the prefix
        // If the cursor position is before the prefix, there is no proposal
        // returned.
        String userInput = getContentWithoutPrefix(featureNameWithPrefix);
        if (userInput != null) {
            List<ContentProposal> newComputedFeatures = addComputedFeatures(userInput);
            if (newComputedFeatures != null) {
                proposals.addAll(newComputedFeatures);
            }

            if (currentElementType != null) {
                for (EStructuralFeature eStructuralFeature : currentElementType.getEAllStructuralFeatures()) {
                    if (eStructuralFeature.getName().startsWith(userInput)) {
                        String displayedName = eStructuralFeature.getName()
                                + (eStructuralFeature.isMany() ? "[" + eStructuralFeature.getLowerBound() + ".." //$NON-NLS-1$//$NON-NLS-2$
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

    /**
     * Return the content of the given string without the prefix handled by this provider.
     * 
     * @param featureNameWithPrefix
     *            the current user input that can contains the prefix handled by this provider.
     * @return the content of the given string without the prefix handled by this provider. Null if no proposal can
     */
    protected String getContentWithoutPrefix(String featureNameWithPrefix) {
        // this provider is used without prefix so we return it as we got it.
        return featureNameWithPrefix;
    }

    /**
     * Adds some additional features handled by this provider that can be different from the one retrieved by method
     * org.eclipse.emf.ecore.EClass.getEAllStructuralFeatures().
     * 
     * @param userInput
     *            the user input from which some additional features can be computed and added to the proposals.
     * @return some additional proposals regarding the given input. If no such element exists, return null.
     */
    protected List<ContentProposal> addComputedFeatures(String userInput) {
        return new ArrayList<ContentProposal>(0);
    }

}
