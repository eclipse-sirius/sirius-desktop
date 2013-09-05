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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.internal.interpreter.FeatureInterpreter;

/**
 * A {@link IProposalProvider} to provide completion for the feature
 * interpreter.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class FeatureProposalProvider implements IProposalProvider {

    private static final String SEPARATOR = ".";

    /**
     * {@inheritDoc}
     */
    public ContentProposal getNewEmtpyExpression() {
        return new ContentProposal(FeatureInterpreter.PREFIX, FeatureInterpreter.PREFIX, "New variable expression.", 1);
    }

    /**
     * {@inheritDoc}
     */
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof FeatureInterpreter)) {
            proposals = Collections.emptyList();
        } else if (context.getContents() == null || context.getContents().length() == 0) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            FeatureInterpreter featureInterpreter = (FeatureInterpreter) interpreter;
            IInterpreterContext interpreterContext = context.getInterpreterContext();
            proposals = new ArrayList<ContentProposal>();
            for (String defaultFeatureName : FeatureInterpreter.DEFAULT_FEATURE_NAMES) {
                proposals.add(new ContentProposal(defaultFeatureName, defaultFeatureName, defaultFeatureName));
            }

            EClass currentElementType = featureInterpreter.getCurrentElementType(interpreterContext);
            if (currentElementType != null) {
                for (EStructuralFeature eStructuralFeature : currentElementType.getEAllStructuralFeatures()) {
                    String displayedName = eStructuralFeature.getEContainingClass().getName()
                            + SEPARATOR
                            + eStructuralFeature.getName()
                            + (eStructuralFeature.isMany() ? "[" + eStructuralFeature.getLowerBound() + ".." + (eStructuralFeature.getUpperBound() == -1 ? "*" : eStructuralFeature.getUpperBound())
                                    + "]" : "") + (eStructuralFeature.getEType() != null ? ":" + eStructuralFeature.getEType().getName() : "");
                    proposals.add(new ContentProposal(eStructuralFeature.getName(), displayedName, displayedName));
                }
            }
        }
        return proposals;
    }

    /**
     * {@inheritDoc}
     */
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof FeatureInterpreter)) {
            proposals = Collections.emptyList();
        } else if (context.getCurrentSelected() == null) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            proposals = new ArrayList<ContentProposal>();
            for (String defaultFeatureName : FeatureInterpreter.DEFAULT_FEATURE_NAMES) {
                proposals.add(new ContentProposal(defaultFeatureName, defaultFeatureName, defaultFeatureName));
            }
            for (EStructuralFeature eStructuralFeature : context.getCurrentSelected().eClass().getEAllStructuralFeatures()) {
                String displayedName = eStructuralFeature.getEContainingClass().getName()
                        + SEPARATOR
                        + eStructuralFeature.getName()
                        + (eStructuralFeature.isMany() ? "[" + eStructuralFeature.getLowerBound() + ".." + (eStructuralFeature.getUpperBound() == -1 ? "*" : eStructuralFeature.getUpperBound()) + "]"
                                : "") + (eStructuralFeature.getEType() != null ? ":" + eStructuralFeature.getEType().getName() : "");
                proposals.add(new ContentProposal(eStructuralFeature.getName(), displayedName, displayedName));
            }
        }
        return proposals;
    }

}
