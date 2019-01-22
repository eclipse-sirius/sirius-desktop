/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.assist;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.editor.tools.api.assist.TypeContentProposalProvider;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.sirius.ui.tools.api.assist.TextContentProposalProvider;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * A {@link TypeContentProposalProvider} specific to the {@link CreateInstance} to restrict the type list according to
 * the "Reference Name" field.
 * 
 * @author fbarbin
 *
 */
public class CreateInstanceTypeContentProposalProvider extends TextContentProposalProvider {

    private final TypeAssistant assistant;

    /**
     * Create a new CreateInstanceTypeContentProposalProvider provider from an assistant.
     * 
     * @param assistant
     *            assistant to use to provide proposals.
     */
    public CreateInstanceTypeContentProposalProvider(final TypeAssistant assistant) {
        this.assistant = assistant;
    }

    @Override
    public IContentProposal[] getProposals(final String contents, final int position) {
        IInterpreterContext interpreterContext = createInterpretedContext();
        final String incompleteText = contents.substring(0, position);
        final List<EClassifier> proposals = assistant.proposal(incompleteText);

        // We retrieve the potential type list of the feature specified in the reference name field.
        Collection<EClass> superTypes = getPotentialSuperTypes(interpreterContext);
        if (!superTypes.isEmpty()) {
            for (Iterator<EClassifier> iterator = proposals.iterator(); iterator.hasNext(); /**/) {
                EClassifier next = iterator.next();
                // For each types (most of the time we will find only one type if only one feature matching the
                // reference name has been found), we check if this type is a super type of the current proposal type.
                Optional<EClassifier> optionalEClassifier = Optional.of(next).filter(EClass.class::isInstance).filter(eClass -> {
                    for (EClass superType : superTypes) {
                        if (superType.isSuperTypeOf((EClass) eClass)) {
                            return true;
                        }
                    }
                    return false;
                });

                // If the type is not a super type of the current proposal type, we remove it from the proposal list.
                if (!optionalEClassifier.isPresent()) {
                    iterator.remove();
                }
            }
        }
        final IContentProposal[] result = new IContentProposal[proposals.size()];
        for (int i = 0; i < proposals.size(); i++) {
            final IContentProposal newProposal = new TypeContentProposal(proposals.get(i), incompleteText);
            result[i] = newProposal;
        }
        return result;
    }

    private IInterpreterContext createInterpretedContext() {
        Object selectedElement = getSelectedElement();
        EStructuralFeature f = ToolPackage.eINSTANCE.getCreateInstance_ReferenceName();
        return SiriusInterpreterContextFactory.createInterpreterContext((EObject) selectedElement, f);
    }

    private Collection<EClass> getPotentialSuperTypes(IInterpreterContext interpreterContext) {
        final String referenceName = ((CreateInstance) interpreterContext.getElement()).getReferenceName();
        if (!StringUtil.isEmpty(referenceName)) {
            return interpreterContext.getTargetType().getPossibleTypes().stream().flatMap(type -> type.search(interpreterContext.getAvailableEPackages()).stream()).filter(EClass.class::isInstance)
                    .flatMap(eClass -> ((EClass) eClass).getEAllStructuralFeatures().stream()).filter(eStructuralFeature -> {
                        return eStructuralFeature.getName().equals(referenceName) && eStructuralFeature.getEType() instanceof EClass;
                    }).map(eStructuralFeature -> (EClass) eStructuralFeature.getEType()).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
