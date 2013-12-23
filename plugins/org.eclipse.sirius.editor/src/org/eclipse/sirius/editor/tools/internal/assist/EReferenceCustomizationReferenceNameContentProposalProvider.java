/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.assist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener2;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.sirius.editor.properties.sections.common.ModelViewBinding;
import org.eclipse.sirius.editor.properties.sections.description.ereferencecustomization.EReferenceCustomizationReferenceNamePropertySection;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

/**
 * A {@link IContentProposalProvider} for
 * {@link EReferenceCustomization#getReferenceName()} feature.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EReferenceCustomizationReferenceNameContentProposalProvider implements IContentProposalProvider {

    private EReferenceCustomization eReferenceCustomization;

    /**
     * Default constructor.
     * 
     * @param eReferenceCustomization
     *            the {@link EReferenceCustomization} for which provide
     *            referenceName proposals
     */
    public EReferenceCustomizationReferenceNameContentProposalProvider(EReferenceCustomization eReferenceCustomization) {
        this.eReferenceCustomization = eReferenceCustomization;
    }

    /**
     * {@inheritDoc}
     */
    public IContentProposal[] getProposals(String contents, int position) {
        List<IContentProposal> proposals = new ArrayList<IContentProposal>();
        String incompleteText = contents.substring(0, position);
        List<EObject> appliedOn = eReferenceCustomization.getAppliedOn();
        List<EObject> styleDescriptionsToCustomize = new ArrayList<EObject>();
        for (EObject eObject : appliedOn) {
            if (eObject instanceof StyleDescription || eObject.eContainer() instanceof StyleDescription) {
                styleDescriptionsToCustomize.add(eObject);
            }
        }
        Set<EReference> commonEReferences = getCommonEReferenceNames(styleDescriptionsToCustomize);
        for (EReference commonEReference : commonEReferences) {
            if (commonEReference.getName() != null && commonEReference.getName().startsWith(incompleteText)) {
                proposals.add(new EStructuralFeatureContentProposal(commonEReference, position));
            }
        }
        return proposals.toArray(new IContentProposal[0]);
    }

    /**
     * Get a set of {@link EReference} common to all specified style
     * description, include also different {@link EReference} having the same
     * name.
     * 
     * @param styleDescriptionsToCustomize
     *            the list of style description for which get the set of common
     *            {@link EReference}
     * @return a set of {@link EReference} common to all specified style
     *         description
     */
    private Set<EReference> getCommonEReferenceNames(List<EObject> styleDescriptionsToCustomize) {
        Set<EReference> commonEReferences = new LinkedHashSet<EReference>();
        Iterator<EObject> iterator = styleDescriptionsToCustomize.iterator();
        if (iterator.hasNext()) {
            EObject styleDescription = iterator.next();
            commonEReferences.addAll(styleDescription.eClass().getEAllReferences());
            while (iterator.hasNext()) {
                styleDescription = iterator.next();
                Iterator<EReference> commonEReferencesIterator = commonEReferences.iterator();
                while (commonEReferencesIterator.hasNext()) {
                    EReference eReference = commonEReferencesIterator.next();
                    if (!containsEReferenceOrAnotherWithSameName(styleDescription.eClass().getEAllReferences(), eReference)) {
                        commonEReferencesIterator.remove();
                    }
                }
            }
        }
        return commonEReferences;
    }

    private boolean containsEReferenceOrAnotherWithSameName(List<EReference> eAllAttributes, EReference eAttribute) {
        boolean containsEAttributeOrAnotherWithSameName = eAllAttributes.contains(eAttribute);
        if (!containsEAttributeOrAnotherWithSameName) {
            for (EReference currentEAttribute : eAllAttributes) {
                if (currentEAttribute.getName().equals(eAttribute.getName()) && haveTypeInCommon(currentEAttribute, eAttribute)) {
                    containsEAttributeOrAnotherWithSameName = true;
                    break;
                }
            }
        }
        return containsEAttributeOrAnotherWithSameName;
    }

    private boolean haveTypeInCommon(EReference currentEReference, EReference eReference) {
        boolean haveTypeInCommon = currentEReference.getEType().equals(eReference.getEType()) || currentEReference.getEType() instanceof EClass && eReference.getEType() instanceof EClass
                && haveSuperTypeInCommon((EClass) currentEReference.getEType(), (EClass) eReference.getEType());
        return haveTypeInCommon;
    }

    private boolean haveSuperTypeInCommon(EClass currentEReferenceType, EClass eReferenceType) {
        boolean haveSuperTypeInCommon = currentEReferenceType.getEAllSuperTypes().contains(eReferenceType) || eReferenceType.getEAllSuperTypes().contains(currentEReferenceType);
        return haveSuperTypeInCommon;
    }

    /**
     * Bind a completion processor to a given text element.
     * 
     * @param section
     *            section to give, if it implements {@link ModelViewBinding}
     *            then the section update will be disabled during proposal
     *            settings.
     * @param text
     *            text to bind a completion processor to.
     */
    public static void bindCompletionProcessor(final EReferenceCustomizationReferenceNamePropertySection section, final Text text) {

        final IBindingService bindingService = (IBindingService) PlatformUI.getWorkbench().getService(IBindingService.class);

        TriggerSequence[] contentAssistBindings = bindingService.getActiveBindingsFor(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
        if (contentAssistBindings != null && contentAssistBindings.length > 0) {

            KeyStroke keyStroke = EReferenceCustomizationReferenceNameContentProposalProvider.getKeyStroke(contentAssistBindings[0]);
            final ContentProposalAdapter adapter = new ContentProposalAdapter(text, new TextContentAdapter(), new EReferenceCustomizationReferenceNameContentProposalProvider(
                    section.getEReferenceCustomization()), keyStroke, null);

            adapter.addContentProposalListener(new IContentProposalListener2() {

                public void proposalPopupClosed(final ContentProposalAdapter arg0) {
                    if (section != null) {
                        ((ModelViewBinding) section).enableModelUpdating();
                    }
                }

                public void proposalPopupOpened(final ContentProposalAdapter arg0) {
                    if (section != null) {
                        ((ModelViewBinding) section).disableModelUpdating();
                    }
                }

            }); // close popup
        }
    }

    private static KeyStroke getKeyStroke(TriggerSequence sequence) {
        for (Trigger trigger : sequence.getTriggers()) {
            if (trigger instanceof KeyStroke) {
                return (KeyStroke) trigger;
            }
        }
        return null;
    }
}
