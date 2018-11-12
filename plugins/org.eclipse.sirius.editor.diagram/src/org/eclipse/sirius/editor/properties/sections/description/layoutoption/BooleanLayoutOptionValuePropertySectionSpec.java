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

import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.editor.properties.sections.description.booleanlayoutoption.BooleanLayoutOptionValuePropertySection;
import org.eclipse.sirius.editor.Messages;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A property section allowing to edit boolean value of {@link BooleanLayoutOption} object.
 * 
 * This section contains a label, an help button, a checkbox widget to edit the value and a removal item button to
 * remove container of the attribute.
 * 
 */
public class BooleanLayoutOptionValuePropertySectionSpec extends BooleanLayoutOptionValuePropertySection {

    /** Help control of the section. */
    protected CLabel help;

    /**
     * The button allowing to remove this layout option override.
     */
    protected Button removeOverrideButton;

    /**
     * The layout option represented by this property section.
     */
    private LayoutOption layoutOption;

    /**
     * Initialize this property section with the semantic element behind.
     * 
     * @param layoutOption
     *            the semantic element behind the section.
     */
    public BooleanLayoutOptionValuePropertySectionSpec(LayoutOption layoutOption) {
        this.layoutOption = layoutOption;
    }

    @Override
    protected String getLabelText() {
        String label = LayoutOptionPropertiesUtils.getLabel((CustomLayoutConfiguration) layoutOption.eContainer(), layoutOption);
        return label == null ? Messages.LayoutOptionValue_defaultLabel : label;
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        eObject = layoutOption;

        // we set the checkbox right position to 94 to let some space for option removal button.
        ((FormData) checkbox.getLayoutData()).right = new FormAttachment(94, 0);

        removeOverrideButton = LayoutOptionPropertiesUtils.createRemoveOptionButton(checkbox, composite, (ViewpointPropertySheetPage) tabbedPropertySheetPage, getWidgetFactory(), layoutOption);

        help = LayoutOptionPropertiesUtils.createHelpLabel(getWidgetFactory(), composite, checkbox, nameLabel, getHelpIcon(), layoutOption);
    }

    @Override
    public void dispose() {
        if (help != null) {
            help.dispose();
        }
        if (removeOverrideButton != null) {
            removeOverrideButton.dispose();
        }
        super.dispose();
    }
}
