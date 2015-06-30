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
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.editor.properties.sections.description.containermapping.ContainerMappingChildrenPresentationPropertySection;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A section for the childrenPresentation property of a ContainerMapping object.
 * 
 * It expose only the FreeForm and List capabilities but not the experimental
 * region support.
 */
public class ContainerMappingChildrenPresentationPropertySectionSpec extends ContainerMappingChildrenPresentationPropertySection {

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        String hStackButtonText = getText(ContainerLayout.HORIZONTAL_STACK);
        String vStackButtonText = getText(ContainerLayout.VERTICAL_STACK);
        for (Button b : button) {
            if (hStackButtonText.equals(b.getText()) || vStackButtonText.equals(b.getText())) {
                b.setToolTipText("Experimental");
                Font f = b.getFont();
                if (f != null && f.getFontData().length > 0) {
                    FontData fontData = f.getFontData()[0];
                    Font italic = SiriusEditor.getFontRegistry().getItalic(fontData.getName());
                    if (italic != null) {
                        b.setFont(italic);
                    }
                }
            }
        }
    }

    @Override
    protected void makeWrittable() {
        super.makeWrittable();

        if (button != null && eObject instanceof ContainerMapping) {
            Option<String> stackButtonDisabled = shouldDisableCompartiments((ContainerMapping) eObject);

            String hStackButtonText = getText(ContainerLayout.HORIZONTAL_STACK);
            String vStackButtonText = getText(ContainerLayout.VERTICAL_STACK);
            for (Button b : button) {
                if (hStackButtonText.equals(b.getText()) || vStackButtonText.equals(b.getText())) {
                    b.setEnabled(!stackButtonDisabled.some());
                }
            }

            if (group != null) {
                if (stackButtonDisabled.some()) {
                    group.setToolTipText(stackButtonDisabled.get());
                } else {
                    group.setToolTipText(null);
                }
            }
        }
    }

    private Option<String> shouldDisableCompartiments(ContainerMapping containerMapping) {
        ContainerMappingQuery query = new ContainerMappingQuery(containerMapping);

        String message = query.isRegion() ?  "A Region mapping cannot be a RegionContainer mapping.": null;
        if (message == null) {
            for (ContainerMapping subContainer : containerMapping.getAllContainerMappings()) {
                ContainerMappingQuery subQuery = new ContainerMappingQuery(subContainer);
                if (message == null && subQuery.isRegionContainer()) {
                message = "The mapping contains RegionContainer sub mappings, they cannot be Region and RegionContainer.";
                }
            }
        }
        if (message == null && !containerMapping.getAllNodeMappings().isEmpty()) {
            message = "The mapping contains node mappings, it cannot be a RegionContainer mapping.";
        }
        return Options.newSome(message);
    }
}
