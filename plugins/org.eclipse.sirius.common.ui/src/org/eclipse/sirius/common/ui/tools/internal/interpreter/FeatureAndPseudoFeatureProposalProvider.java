/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.internal.interpreter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.internal.interpreter.FeatureInterpreter;

/**
 * Provides completion for the feature interpreter.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 */
public class FeatureAndPseudoFeatureProposalProvider extends FeatureProposalProvider {
    private static final String SEPARATOR_3 = "[0..*]"; //$NON-NLS-1$

    @Override
    protected List<ContentProposal> addComputedFeatures(String featureNamePrefix) {
        List<ContentProposal> proposals = new ArrayList<ContentProposal>();
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
        return proposals;
    }

    @Override
    protected String getContentWithoutPrefix(String featureNamePrefix) {
        // We remove the feature: prefix
        if (featureNamePrefix.length() >= FeatureInterpreter.PREFIX.length()) {
            return featureNamePrefix.trim().substring(FeatureInterpreter.PREFIX.length());
        }
        return null;
    }

    @Override
    protected boolean isPrefixUsed() {
        return true;
    }

}
