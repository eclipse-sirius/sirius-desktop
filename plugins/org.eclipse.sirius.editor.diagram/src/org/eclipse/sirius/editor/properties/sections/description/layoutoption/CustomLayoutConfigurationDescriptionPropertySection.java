/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.layoutoption;

import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.editor.Messages;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A property section allowing to display the description of a {@link CustomLayoutConfiguration}.
 */
public class CustomLayoutConfigurationDescriptionPropertySection extends AbstractViewpointPropertySection {

    /**
     * The description widget.
     */
    protected Label description;

    /**
     * The high level composite containing the widgets.
     */
    private Composite composite;

    /**
     * The label for the description.
     */
    private CLabel nameLabel;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        if (tabbedPropertySheetPage instanceof ViewpointPropertySheetPage)
            super.createControls(parent, (ViewpointPropertySheetPage) tabbedPropertySheetPage);
        else
            super.createControls(parent, tabbedPropertySheetPage);

        composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;

        CustomLayoutConfiguration layout = (CustomLayoutConfiguration) eObject;
        Map<String, CustomLayoutAlgorithm> layoutProviderRegistry = DiagramUIPlugin.getPlugin().getLayoutAlgorithms();
        CustomLayoutAlgorithm customLayoutAlgorithm = layoutProviderRegistry.get(layout.getId());
        String descriptionString;
        if (customLayoutAlgorithm == null) {
            descriptionString = MessageFormat.format(Messages.CustomLayoutConfigurationDescriptionPropertySection_noLayoutAlgorithmProviderDescription, layout.getId());
        } else {
            descriptionString = customLayoutAlgorithm.getDescription() == null ? "" : customLayoutAlgorithm.getDescription();
        }
        description = new Label(composite, SWT.WRAP);
        description.setText(descriptionString);
        data = new FormData();
        data.left = new FormAttachment(0, LABEL_WIDTH);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        data.width = 100;
        description.setLayoutData(data);
        description.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        String label = customLayoutAlgorithm != null ? customLayoutAlgorithm.getLabel() : Messages.CustomLayoutConfigurationDescriptionPropertySection_noLayoutAlgorithmProviderName;
        nameLabel = getWidgetFactory().createCLabel(composite, label);
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(description, -ITabbedPropertyConstants.HSPACE - 20);
        data.top = new FormAttachment(description, 0, SWT.TOP);
        nameLabel.setLayoutData(data);

    }

    @Override
    public void dispose() {
        description.dispose();
        nameLabel.dispose();
        super.dispose();
    }

    @Override
    public EAttribute getFeature() {
        return null;
    }

    @Override
    protected void makeReadonly() {
    }

    @Override
    protected void makeWrittable() {
    }

}
