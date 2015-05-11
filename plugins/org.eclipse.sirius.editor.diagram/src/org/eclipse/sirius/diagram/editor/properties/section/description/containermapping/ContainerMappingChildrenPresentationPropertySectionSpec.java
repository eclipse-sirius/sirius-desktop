/*******************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.section.description.containermapping;

import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.business.internal.query.ContainerMappingExperimentalQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.editor.properties.sections.description.containermapping.ContainerMappingChildrenPresentationPropertySection;
import org.eclipse.swt.widgets.Button;

/**
 * A section for the childrenPresentation property of a ContainerMapping object.
 * 
 * It expose only the FreeForm and List capabilities but not the experimental
 * region support.
 */
public class ContainerMappingChildrenPresentationPropertySectionSpec extends ContainerMappingChildrenPresentationPropertySection {

    @Override
    protected void makeWrittable() {
        super.makeWrittable();

        if (button != null && eObject instanceof ContainerMapping) {
            boolean stackButtonEnabled = shouldEnableCompartiments((ContainerMapping) eObject);

            String hStackButtonText = getText(ContainerLayout.HORIZONTAL_STACK);
            String vStackButtonText = getText(ContainerLayout.VERTICAL_STACK);
            for (Button b : button) {
                if (hStackButtonText.equals(b.getText()) || vStackButtonText.equals(b.getText())) {
                    b.setEnabled(stackButtonEnabled);
                }
            }
        }
    }

    private boolean shouldEnableCompartiments(ContainerMapping containerMapping) {
        ContainerMappingExperimentalQuery query = new ContainerMappingExperimentalQuery(containerMapping);
        
        boolean enableCompartiments = !query.isRegion();
        if (enableCompartiments) {
            for (ContainerMapping subContainer : containerMapping.getAllContainerMappings()) {
                ContainerMappingExperimentalQuery subQuery = new ContainerMappingExperimentalQuery(subContainer);
                enableCompartiments = enableCompartiments && !subQuery.isRegionContainer();
            }
        }
        if (enableCompartiments) {
            enableCompartiments = enableCompartiments && containerMapping.getAllNodeMappings().isEmpty();
        }
        return enableCompartiments;
    }
}
