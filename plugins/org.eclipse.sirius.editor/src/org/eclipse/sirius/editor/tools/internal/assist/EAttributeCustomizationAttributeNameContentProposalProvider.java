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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener2;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.sirius.editor.properties.sections.common.ModelViewBinding;
import org.eclipse.sirius.editor.properties.sections.description.eattributecustomization.EAttributeCustomizationAttributeNamePropertySection;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

/**
 * A {@link IContentProposalProvider} for
 * {@link EAttributeCustomization#getAttributeName()} feature.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EAttributeCustomizationAttributeNameContentProposalProvider implements IContentProposalProvider {

    private EAttributeCustomization eAttributeCustomization;

    /**
     * Default constructor.
     * 
     * @param eAttributeCustomization
     *            the {@link EAttributeCustomization} for which provide
     *            attributeName proposals
     */
    public EAttributeCustomizationAttributeNameContentProposalProvider(EAttributeCustomization eAttributeCustomization) {
        this.eAttributeCustomization = eAttributeCustomization;
    }

    /**
     * {@inheritDoc}
     */
    public IContentProposal[] getProposals(String contents, int position) {
        List<IContentProposal> proposals = new ArrayList<IContentProposal>();
        String incompleteText = contents.substring(0, position);
        List<EObject> appliedOn = eAttributeCustomization.getAppliedOn();
        List<EObject> styleDescriptionsToCustomize = new ArrayList<EObject>();
        for (EObject eObject : appliedOn) {
            if (eObject instanceof StyleDescription || eObject.eContainer() instanceof StyleDescription) {
                styleDescriptionsToCustomize.add(eObject);
            }
        }
        Set<EAttribute> commonAttributes = getCommonEAttributeNames(styleDescriptionsToCustomize);
        for (EAttribute commonAttribute : commonAttributes) {
            if (commonAttribute.getName() != null && commonAttribute.getName().startsWith(incompleteText)) {
                proposals.add(new EStructuralFeatureContentProposal(commonAttribute, position));
            }
        }
        return proposals.toArray(new IContentProposal[0]);
    }

    /**
     * Get a set of {@link EAttribute} common to all specified style
     * description, include also different {@link EAttribute} having the same
     * name .
     * 
     * @param styleDescriptionsToCustomize
     *            the list of style description for which get the set of common
     *            {@link EAttribute}
     * @return a set of {@link EAttribute} common to all specified style
     *         description
     */
    private Set<EAttribute> getCommonEAttributeNames(List<EObject> styleDescriptionsToCustomize) {
        Set<EAttribute> commonEAttributes = new LinkedHashSet<EAttribute>();
        Iterator<EObject> iterator = styleDescriptionsToCustomize.iterator();
        if (iterator.hasNext()) {
            EObject styleDescription = iterator.next();
            commonEAttributes.addAll(styleDescription.eClass().getEAllAttributes());
            while (iterator.hasNext()) {
                styleDescription = iterator.next();
                Iterator<EAttribute> commonAttributesIterator = commonEAttributes.iterator();
                while (commonAttributesIterator.hasNext()) {
                    EAttribute eAttribute = commonAttributesIterator.next();
                    if (!containsEAttributeOrAnotherWithSameName(styleDescription.eClass().getEAllAttributes(), eAttribute)) {
                        commonAttributesIterator.remove();
                    }
                }
            }
        }
        return commonEAttributes;
    }

    private boolean containsEAttributeOrAnotherWithSameName(List<EAttribute> eAllAttributes, EAttribute eAttribute) {
        boolean containsEAttributeOrAnotherWithSameName = eAllAttributes.contains(eAttribute);
        if (!containsEAttributeOrAnotherWithSameName) {
            for (EAttribute currentEAttribute : eAllAttributes) {
                if (currentEAttribute.getName().equals(eAttribute.getName()) && currentEAttribute.getEType().equals(eAttribute.getEType())) {
                    containsEAttributeOrAnotherWithSameName = true;
                    break;
                }
            }
        }
        return containsEAttributeOrAnotherWithSameName;
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
    public static void bindCompletionProcessor(final EAttributeCustomizationAttributeNamePropertySection section, final Text text) {

        final IBindingService bindingService = (IBindingService) PlatformUI.getWorkbench().getService(IBindingService.class);

        TriggerSequence[] contentAssistBindings = bindingService.getActiveBindingsFor(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
        if (contentAssistBindings != null && contentAssistBindings.length > 0) {

            KeyStroke keyStroke = EAttributeCustomizationAttributeNameContentProposalProvider.getKeyStroke(contentAssistBindings[0]);
            final ContentProposalAdapter adapter = new ContentProposalAdapter(text, new TextContentAdapter(), new EAttributeCustomizationAttributeNameContentProposalProvider(
                    section.getEAttributeCustomization()), keyStroke, null);

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
