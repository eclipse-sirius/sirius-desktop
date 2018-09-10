/*******************************************************************************
 * Copyright (c) 2013, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.editor.properties.section.description.containermapping;

import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.editor.properties.sections.description.containermapping.ContainerMappingChildrenPresentationPropertySection;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;

/**
 * A section for the childrenPresentation property of a ContainerMapping object.
 * 
 * It is able to mark as experimental or disable the compartment capabilities to help the user to produce a valid VSM.
 */
public class ContainerMappingChildrenPresentationPropertySectionSpec extends ContainerMappingChildrenPresentationPropertySection {

    @Override
    protected void makeWrittable() {
        super.makeWrittable();

        if (button != null && eObject instanceof ContainerMapping) {
            ContainerMapping containerMapping = (ContainerMapping) eObject;
            Option<String> experimentalStackButtons = shouldMarkCompartmentsExperimental(containerMapping);
            updateControls(experimentalStackButtons);
        }
    }

    private Option<String> shouldMarkCompartmentsExperimental(ContainerMapping containerMapping) {
        boolean experimental = false;
        StringBuilder message = new StringBuilder("Experimental");
        experimental = new ContainerMappingQuery(containerMapping).isRegion();
        if (experimental) {
            message.append(" - the mapping will produce containers which will be Region and RegionContainer");
        }

        for (ContainerMapping subContainerMapping : MappingHelper.getAllContainerMappings(containerMapping)) {
            experimental = experimental || new ContainerMappingQuery(subContainerMapping).isRegionContainer();

            if (experimental) {
                message.append(" - the children mapping will produce containers which will be Region and RegionContainer");
                break;
            }
        }
        message.append(".");
        return experimental ? Options.newSome(message.toString()) : Options.<String> newNone();
    }

    private void updateControls(Option<String> experimentalStackButtons) {
        String hStackButtonText = getText(ContainerLayout.HORIZONTAL_STACK);
        String vStackButtonText = getText(ContainerLayout.VERTICAL_STACK);

        // Update buttons
        boolean needsLayout = false;
        for (Button b : button) {
            if (hStackButtonText.equals(b.getText()) || vStackButtonText.equals(b.getText())) {
                needsLayout = updateButton(b, experimentalStackButtons) || needsLayout;
            }
        }

        // If the section has been created with italic buttons, it might require
        // layout be able to display the buttons with normal font.
        if (needsLayout) {
            composite.layout();
        }
    }

    private boolean updateButton(Button b, Option<String> experimentalStackButtons) {
        boolean needsLayout = false;
        if (experimentalStackButtons.some()) {
            b.setToolTipText(experimentalStackButtons.get());
            Font f = b.getFont();
            if (f != null && f.getFontData().length > 0) {
                FontData fontData = f.getFontData()[0];
                Font italic = SiriusEditor.getFontRegistry().getItalic(fontData.getName());
                if (italic != null) {
                    b.setFont(italic);
                }
            }
        } else {
            b.setToolTipText(null);
            Font f = b.getFont();
            if (f != null && f.getFontData().length > 0) {
                FontData fontData = f.getFontData()[0];
                Font normal = SiriusEditor.getFontRegistry().get(fontData.getName());
                if (normal != null) {
                    b.setFont(normal);
                    needsLayout = true;
                }
            }
        }

        return needsLayout;
    }
}
