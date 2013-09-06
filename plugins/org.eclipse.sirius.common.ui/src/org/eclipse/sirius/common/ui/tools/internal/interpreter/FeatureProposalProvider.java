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
import org.eclipse.emf.ecore.EObject;
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

    private static final String SEPARATOR_1 = ":";

    private static final String SEPARATOR_2 = " - ";

    private static final String SEPARATOR_3 = "[0..*]";

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
            String eObjectName = EObject.class.getSimpleName();

            String eContainerFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[0];
            String displayedName = eContainerFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_2 + eObjectName;
            proposals.add(new ContentProposal(eContainerFeatureName, displayedName, displayedName));

            String eContentsFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[1];
            displayedName = eContentsFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_3 + SEPARATOR_2 + eObjectName;
            proposals.add(new ContentProposal(eContentsFeatureName, displayedName, displayedName));

            String eAllContentsFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[2];
            displayedName = eAllContentsFeatureName + SEPARATOR_1 + "TreeIterator" + SEPARATOR_2 + eObjectName;
            proposals.add(new ContentProposal(eAllContentsFeatureName, displayedName, displayedName));

            String eClassFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[3];
            displayedName = eClassFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_3 + SEPARATOR_2 + EClass.class.getSimpleName();
            proposals.add(new ContentProposal(eClassFeatureName, displayedName, displayedName));

            String eReferencesFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[4];
            displayedName = eReferencesFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_3 + SEPARATOR_2 + eObjectName;
            proposals.add(new ContentProposal(eReferencesFeatureName, displayedName, displayedName));

            EClass currentElementType = featureInterpreter.getCurrentElementType(interpreterContext);
            if (currentElementType != null) {
                for (EStructuralFeature eStructuralFeature : currentElementType.getEAllStructuralFeatures()) {
                    displayedName = eStructuralFeature.getName()
                            + (eStructuralFeature.isMany() ? "[" + eStructuralFeature.getLowerBound() + ".." + (eStructuralFeature.getUpperBound() == -1 ? "*" : eStructuralFeature.getUpperBound())
                                    + "]" : "") + (eStructuralFeature.getEType() != null ? SEPARATOR_1 + eStructuralFeature.getEType().getName() : "") + SEPARATOR_2
                            + eStructuralFeature.getEContainingClass().getName();
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
            String eObjectName = EObject.class.getSimpleName();

            String eContainerFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[0];
            String displayedName = eContainerFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_2 + eObjectName;
            proposals.add(new ContentProposal(eContainerFeatureName, displayedName, displayedName));

            String eContentsFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[1];
            displayedName = eContentsFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_3 + SEPARATOR_2 + eObjectName;
            proposals.add(new ContentProposal(eContentsFeatureName, displayedName, displayedName));

            String eAllContentsFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[2];
            displayedName = eAllContentsFeatureName + SEPARATOR_1 + "TreeIterator" + SEPARATOR_2 + eObjectName;
            proposals.add(new ContentProposal(eAllContentsFeatureName, displayedName, displayedName));

            String eClassFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[3];
            displayedName = eClassFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_3 + SEPARATOR_2 + EClass.class.getSimpleName();
            proposals.add(new ContentProposal(eClassFeatureName, displayedName, displayedName));

            String eReferencesFeatureName = FeatureInterpreter.DEFAULT_FEATURE_NAMES[4];
            displayedName = eReferencesFeatureName + SEPARATOR_1 + eObjectName + SEPARATOR_3 + SEPARATOR_2 + eObjectName;
            proposals.add(new ContentProposal(eReferencesFeatureName, displayedName, displayedName));

            EClass currentElementType = context.getCurrentSelected().eClass();
            if (currentElementType != null) {
                for (EStructuralFeature eStructuralFeature : currentElementType.getEAllStructuralFeatures()) {
                    displayedName = eStructuralFeature.getName()
                            + (eStructuralFeature.isMany() ? "[" + eStructuralFeature.getLowerBound() + ".." + (eStructuralFeature.getUpperBound() == -1 ? "*" : eStructuralFeature.getUpperBound())
                                    + "]" : "") + (eStructuralFeature.getEType() != null ? SEPARATOR_1 + eStructuralFeature.getEType().getName() : "") + SEPARATOR_2
                            + eStructuralFeature.getEContainingClass().getName();
                    proposals.add(new ContentProposal(eStructuralFeature.getName(), displayedName, displayedName));
                }
            }
        }
        return proposals;
    }

}
