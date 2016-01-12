/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import java.util.List;

import org.eclipse.eef.EEFContainerDescription;
import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * Interprets the high-level property views description defined in a Sirius VSM
 * into a lower-level EEFViewDescription suitable for the EEF runtime.
 */
public class ViewDescriptionConverter {
    private final ViewExtensionDescription description;
    
    public ViewDescriptionConverter(ViewExtensionDescription description) {
        this.description = description;
    }

    public EEFViewDescription convert() {
        return convert(description);
    }

    private EEFViewDescription convert(ViewExtensionDescription viewExtensionDescription) {
        EEFViewDescription eefViewDescription = EefFactory.eINSTANCE.createEEFViewDescription();
        eefViewDescription.setIdentifier(viewExtensionDescription.getIdentifier());
        eefViewDescription.setLabelExpression(viewExtensionDescription.getLabelExpression());

        List<GroupDescription> groups = viewExtensionDescription.getGroups();
        for (GroupDescription groupDescription : groups) {
            EEFGroupDescription eefGroupDescription = EefFactory.eINSTANCE.createEEFGroupDescription();
            eefViewDescription.getGroups().add(eefGroupDescription);

            eefGroupDescription.setIdentifier(groupDescription.getIdentifier());
            eefGroupDescription.setSemanticCandidateExpression(groupDescription.getSemanticCandidateExpression());
            eefGroupDescription.setLabelExpression(groupDescription.getLabelExpression());

            EEFContainerDescription eefContainerDescription = EefFactory.eINSTANCE.createEEFContainerDescription();
            eefGroupDescription.setContainer(eefContainerDescription);

            ContainerDescription containerDescription = groupDescription.getContainer();
            List<WidgetDescription> widgets = containerDescription.getWidgets();
            for (WidgetDescription widgetDescription : widgets) {
                if (widgetDescription instanceof TextDescription) {
                    TextDescription textDescription = (TextDescription) widgetDescription;

                    EEFTextDescription eefTextDescription = EefFactory.eINSTANCE.createEEFTextDescription();
                    eefContainerDescription.getWidgets().add(eefTextDescription);

                    eefTextDescription.setIdentifier(textDescription.getIdentifier());
                    eefTextDescription.setLabelExpression(textDescription.getLabelExpression());
                    eefTextDescription.setValueExpression(textDescription.getValueExpression());

                    InitialOperation initialOperation = textDescription.getInitialOperation();
                    eefTextDescription.setEditExpression("aql:self.executeOperation('" + EcoreUtil.getURI(initialOperation).toString() + "')");
                }
            }
        }

        List<PageDescription> pages = viewExtensionDescription.getPages();
        for (PageDescription pageDescription : pages) {
            EEFPageDescription eefPageDescription = EefFactory.eINSTANCE.createEEFPageDescription();
            eefViewDescription.getPages().add(eefPageDescription);

            eefPageDescription.setIdentifier(pageDescription.getIdentifier());
            eefPageDescription.setLabelExpression(pageDescription.getLabelExpression());

            // FIXME OMG THIS IS HORRIBLE!!!ELEVEN!!!
            eefPageDescription.getGroups().add(eefViewDescription.getGroups().get(0));
        }

        return eefViewDescription;
    }
}
