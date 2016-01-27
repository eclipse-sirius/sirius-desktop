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

import org.eclipse.eef.EEFCheckboxDescription;
import org.eclipse.eef.EEFContainerDescription;
import org.eclipse.eef.EEFDynamicMappingCase;
import org.eclipse.eef.EEFDynamicMappingFor;
import org.eclipse.eef.EEFDynamicMappingSwitch;
import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFLabelDescription;
import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFSelectDescription;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.DynamicMappingCase;
import org.eclipse.sirius.properties.DynamicMappingFor;
import org.eclipse.sirius.properties.DynamicMappingSwitch;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.SelectDescription;
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
        page.setSemanticCandidateExpression(pageDescription.getSemanticCandidateExpression());

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

        convertGroupContents(groupDescription, group);

        page.getGroups().add(group);
        view.getGroups().add(group);
    }

    private void convertGroupContents(GroupDescription groupDescription, EEFGroupDescription group) {
        EEFContainerDescription containerDesc = EefFactory.eINSTANCE.createEEFContainerDescription();

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

            DynamicMappingSwitch dynamicMappingSwitch = dynamicMappingFor.getSwitch();
            EEFDynamicMappingSwitch eefDynamicMappingSwitch = EefFactory.eINSTANCE.createEEFDynamicMappingSwitch();
            eefDynamicMappingSwitch.setSwitchExpression(dynamicMappingSwitch.getSwitchExpression());
            eefDynamicMappingFor.setSwitch(eefDynamicMappingSwitch);

            for (DynamicMappingCase dynamicMappingCase : dynamicMappingSwitch.getCases()) {
                EEFDynamicMappingCase eefDynamicMappingCase = EefFactory.eINSTANCE.createEEFDynamicMappingCase();
                eefDynamicMappingCase.setCaseExpression(dynamicMappingCase.getCaseExpression());

                EEFWidgetDescription widgetDescription = this.createEEFWidgetDescription(dynamicMappingCase.getWidget());
                if (widgetDescription != null) {
                    eefDynamicMappingCase.setWidget(widgetDescription);
                }

                eefDynamicMappingSwitch.getCases().add(eefDynamicMappingCase);
            }

            containerDesc.getDynamicMappings().add(eefDynamicMappingFor);
        }

        group.setContainer(containerDesc);
    }

    private EEFWidgetDescription createEEFWidgetDescription(WidgetDescription widgetDescription) {
        EEFWidgetDescription description = null;
        if (widgetDescription instanceof TextDescription) {
            description = createEEFTextDescription((TextDescription) widgetDescription);
        } else if (widgetDescription instanceof LabelDescription) {
            description = createEEFLabelDescription((LabelDescription) widgetDescription);
        } else if (widgetDescription instanceof CheckboxDescription) {
            description = createEEFCheckboxDescription((CheckboxDescription) widgetDescription);
        } else if (widgetDescription instanceof SelectDescription) {
            description = createEEFSelectDescription((SelectDescription) widgetDescription);
        }

        return description;
    }

    private EEFTextDescription createEEFTextDescription(TextDescription textDescription) {
        EEFTextDescription eefTextDescription = EefFactory.eINSTANCE.createEEFTextDescription();

        eefTextDescription.setIdentifier(textDescription.getIdentifier());
        eefTextDescription.setLabelExpression(textDescription.getLabelExpression());
        eefTextDescription.setValueExpression(textDescription.getValueExpression());

        InitialOperation initialOperation = textDescription.getInitialOperation();
        eefTextDescription.setEditExpression("aql:self.executeOperation('" + EcoreUtil.getURI(initialOperation).toString() + "')");
        return eefTextDescription;
    }

    private EEFLabelDescription createEEFLabelDescription(LabelDescription labelDescription) {
        EEFLabelDescription eefLabelDescription = EefFactory.eINSTANCE.createEEFLabelDescription();

        eefLabelDescription.setIdentifier(labelDescription.getIdentifier());
        eefLabelDescription.setLabelExpression(labelDescription.getLabelExpression());
        return eefLabelDescription;
    }

    private EEFCheckboxDescription createEEFCheckboxDescription(CheckboxDescription checkboxDescription) {
        EEFCheckboxDescription eefCheckboxDescription = EefFactory.eINSTANCE.createEEFCheckboxDescription();

        eefCheckboxDescription.setIdentifier(checkboxDescription.getIdentifier());
        eefCheckboxDescription.setLabelExpression(checkboxDescription.getLabelExpression());
        eefCheckboxDescription.setValueExpression(checkboxDescription.getValueExpression());
        return eefCheckboxDescription;
    }

    private EEFSelectDescription createEEFSelectDescription(SelectDescription selectDescription) {
        EEFSelectDescription eefSelectDescription = EefFactory.eINSTANCE.createEEFSelectDescription();

        eefSelectDescription.setIdentifier(selectDescription.getIdentifier());
        eefSelectDescription.setLabelExpression(selectDescription.getLabelExpression());
        eefSelectDescription.setValueExpression(selectDescription.getValueExpression());
        eefSelectDescription.setCandidatesExpression(selectDescription.getCandidatesExpression());
        eefSelectDescription.setCandidateDisplayExpression(selectDescription.getCandidateDisplayExpression());
        return eefSelectDescription;
    }

}
