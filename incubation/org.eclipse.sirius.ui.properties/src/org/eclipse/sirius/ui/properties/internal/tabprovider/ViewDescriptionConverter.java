/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal.tabprovider;

import java.util.List;

import org.eclipse.eef.EEFButtonDescription;
import org.eclipse.eef.EEFCheckboxDescription;
import org.eclipse.eef.EEFContainerDescription;
import org.eclipse.eef.EEFDynamicMappingFor;
import org.eclipse.eef.EEFDynamicMappingIf;
import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFLabelDescription;
import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFRadioDescription;
import org.eclipse.eef.EEFSelectDescription;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.DynamicMappingFor;
import org.eclipse.sirius.properties.DynamicMappingIf;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * Interprets the high-level property views description defined in a Sirius VSM
 * into a lower-level EEFViewDescription suitable for the EEF runtime.
 * 
 * @author pcdavid
 */
public class ViewDescriptionConverter {
    private final List<PageDescription> pageDescriptions;

    /**
     * The contrsuctor.
     * 
     * @param pageDescriptions
     *            The description of the pages to convert
     */
    public ViewDescriptionConverter(List<PageDescription> pageDescriptions) {
        this.pageDescriptions = pageDescriptions;
    }

    /**
     * Use the description of the pages provided in order to create an
     * {@link EEFViewDescription}.
     * 
     * @return The {@link EEFViewDescription} computed
     */
    public EEFViewDescription convert() {
        EEFViewDescription view = EefFactory.eINSTANCE.createEEFViewDescription();
        for (PageDescription pageDescription : pageDescriptions) {
            createPage(pageDescription, view);
        }

        return view;
    }

    /**
     * Creates a concrete page instance bound to a specific semantic target.
     */
    private void createPage(PageDescription pageDescription, EEFViewDescription view) {
        EEFPageDescription page = EefFactory.eINSTANCE.createEEFPageDescription();
        page.setIdentifier(pageDescription.getIdentifier());
        page.setLabelExpression(pageDescription.getLabelExpression());
        page.setDomainClass(pageDescription.getDomainClass());
        page.setSemanticCandidateExpression(pageDescription.getSemanticCandidateExpression());

        if (page.getIdentifier() == null || page.getIdentifier().trim().length() == 0) {
            page.setIdentifier(EcoreUtil.getURI(pageDescription).toString());
        }

        for (GroupDescription groupDescription : pageDescription.getGroups()) {
            createGroup(groupDescription, page, view);
        }

        view.getPages().add(page);
    }

    /**
     * Creates a concrete group instance bound to a specific semantic target.
     */
    private void createGroup(GroupDescription groupDescription, EEFPageDescription page, EEFViewDescription view) {
        EEFGroupDescription group = EefFactory.eINSTANCE.createEEFGroupDescription();
        group.setIdentifier(groupDescription.getIdentifier());
        group.setLabelExpression(groupDescription.getLabelExpression());
        group.setDomainClass(groupDescription.getDomainClass());
        group.setSemanticCandidateExpression(groupDescription.getSemanticCandidateExpression());

        if (group.getIdentifier() == null || group.getIdentifier().trim().length() == 0) {
            group.setIdentifier(EcoreUtil.getURI(groupDescription).toString());
        }

        convertGroupContents(groupDescription, group);

        page.getGroups().add(group);
        view.getGroups().add(group);
    }

    private void convertGroupContents(GroupDescription groupDescription, EEFGroupDescription group) {
        EEFContainerDescription containerDesc = EefFactory.eINSTANCE.createEEFContainerDescription();

        if (groupDescription.getContainer() != null) {
            for (WidgetDescription widgetDescription : groupDescription.getContainer().getWidgets()) {
                EEFWidgetDescription description = this.createEEFWidgetDescription(widgetDescription);
                if (description != null) {
                    containerDesc.getWidgets().add(description);
                }
            }
            for (DynamicMappingFor dynamicMappingFor : groupDescription.getContainer().getDynamicMappings()) {
                EEFDynamicMappingFor eefDynamicMappingFor = EefFactory.eINSTANCE.createEEFDynamicMappingFor();
                eefDynamicMappingFor.setDomainClassExpression(dynamicMappingFor.getDomainClassExpression());
                eefDynamicMappingFor.setIterator(dynamicMappingFor.getIterator());

                List<DynamicMappingIf> dynamicMappingIfs = dynamicMappingFor.getIfs();
                for (DynamicMappingIf dynamicMappingIf : dynamicMappingIfs) {
                    EEFDynamicMappingIf eefDynamicMappingIf = EefFactory.eINSTANCE.createEEFDynamicMappingIf();
                    eefDynamicMappingIf.setPredicateExpression(dynamicMappingIf.getPredicateExpression());

                    EEFWidgetDescription eefWidgetDescription = this.createEEFWidgetDescription(dynamicMappingIf.getWidget());
                    eefDynamicMappingIf.setWidget(eefWidgetDescription);

                    eefDynamicMappingFor.getIfs().add(eefDynamicMappingIf);
                }

                containerDesc.getDynamicMappings().add(eefDynamicMappingFor);
            }
            group.setContainer(containerDesc);
        }
    }

    private EEFWidgetDescription createEEFWidgetDescription(WidgetDescription widgetDescription) {
        EEFWidgetDescription description = null;
        if (widgetDescription instanceof TextAreaDescription) {
            description = createEEFTextDescription((TextAreaDescription) widgetDescription);
        } else if (widgetDescription instanceof TextDescription) {
            description = createEEFTextDescription((TextDescription) widgetDescription);
        } else if (widgetDescription instanceof LabelDescription) {
            description = createEEFLabelDescription((LabelDescription) widgetDescription);
        } else if (widgetDescription instanceof CheckboxDescription) {
            description = createEEFCheckboxDescription((CheckboxDescription) widgetDescription);
        } else if (widgetDescription instanceof SelectDescription) {
            description = createEEFSelectDescription((SelectDescription) widgetDescription);
        } else if (widgetDescription instanceof ButtonDescription) {
            description = createEEFButtonDescription((ButtonDescription) widgetDescription);
        } else if (widgetDescription instanceof RadioDescription) {
            description = createEEFRadioDescription((RadioDescription) widgetDescription);
        }

        if (description != null) {
            description.setHelpExpression(widgetDescription.getHelpExpression());
            description.setLabelExpression(widgetDescription.getLabelExpression());
        }

        return description;
    }

    private EEFTextDescription createEEFTextDescription(TextDescription textDescription) {
        EEFTextDescription eefTextDescription = EefFactory.eINSTANCE.createEEFTextDescription();

        eefTextDescription.setIdentifier(textDescription.getIdentifier());
        eefTextDescription.setValueExpression(textDescription.getValueExpression());

        InitialOperation initialOperation = textDescription.getInitialOperation();
        eefTextDescription.setEditExpression(getExpressionForOperation(initialOperation));
        return eefTextDescription;
    }

    private String getExpressionForOperation(InitialOperation initialOperation) {
        if (initialOperation != null) {
            return "aql:self.executeOperation('" + EcoreUtil.getURI(initialOperation).toString() + "')";
        }
        return null;
    }

    private EEFTextDescription createEEFTextDescription(TextAreaDescription textDescription) {
        EEFTextDescription eefTextDescription = EefFactory.eINSTANCE.createEEFTextDescription();

        eefTextDescription.setIdentifier(textDescription.getIdentifier());
        eefTextDescription.setValueExpression(textDescription.getValueExpression());
        eefTextDescription.setLineCount(textDescription.getLineCount());

        InitialOperation initialOperation = textDescription.getInitialOperation();
        eefTextDescription.setEditExpression(getExpressionForOperation(initialOperation));
        return eefTextDescription;
    }

    private EEFLabelDescription createEEFLabelDescription(LabelDescription labelDescription) {
        EEFLabelDescription eefLabelDescription = EefFactory.eINSTANCE.createEEFLabelDescription();

        eefLabelDescription.setIdentifier(labelDescription.getIdentifier());
        eefLabelDescription.setBodyExpression(labelDescription.getBodyExpression());
        return eefLabelDescription;
    }

    private EEFButtonDescription createEEFButtonDescription(ButtonDescription buttonDescription) {
        EEFButtonDescription eefButtonDescription = EefFactory.eINSTANCE.createEEFButtonDescription();

        eefButtonDescription.setIdentifier(buttonDescription.getIdentifier());
        eefButtonDescription.setButtonLabelExpression(buttonDescription.getButtonLabelExpression());
        InitialOperation initialOperation = buttonDescription.getInitialOperation();
        eefButtonDescription.setPushExpression(getExpressionForOperation(initialOperation));
        return eefButtonDescription;
    }

    private EEFCheckboxDescription createEEFCheckboxDescription(CheckboxDescription checkboxDescription) {
        EEFCheckboxDescription eefCheckboxDescription = EefFactory.eINSTANCE.createEEFCheckboxDescription();

        eefCheckboxDescription.setIdentifier(checkboxDescription.getIdentifier());
        eefCheckboxDescription.setValueExpression(checkboxDescription.getValueExpression());
        return eefCheckboxDescription;
    }

    private EEFSelectDescription createEEFSelectDescription(SelectDescription selectDescription) {
        EEFSelectDescription eefSelectDescription = EefFactory.eINSTANCE.createEEFSelectDescription();

        eefSelectDescription.setIdentifier(selectDescription.getIdentifier());
        eefSelectDescription.setValueExpression(selectDescription.getValueExpression());
        eefSelectDescription.setCandidatesExpression(selectDescription.getCandidatesExpression());
        eefSelectDescription.setCandidateDisplayExpression(selectDescription.getCandidateDisplayExpression());
        return eefSelectDescription;
    }

    private EEFRadioDescription createEEFRadioDescription(RadioDescription radioDescription) {
        EEFRadioDescription eefRadioDescription = EefFactory.eINSTANCE.createEEFRadioDescription();

        eefRadioDescription.setIdentifier(radioDescription.getIdentifier());
        eefRadioDescription.setValueExpression(radioDescription.getValueExpression());
        eefRadioDescription.setCandidatesExpression(radioDescription.getCandidatesExpression());
        eefRadioDescription.setCandidateDisplayExpression(radioDescription.getCandidateDisplayExpression());
        return eefRadioDescription;
    }

}
