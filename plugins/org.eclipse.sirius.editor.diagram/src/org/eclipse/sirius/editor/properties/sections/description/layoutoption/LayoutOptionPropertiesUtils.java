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

import java.util.Collections;

import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * Utility class providing methods to build UI components for properties view displaying layout options.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class LayoutOptionPropertiesUtils {
    /**
     * The name of image used to remove layout option override.
     */
    public static final String DELETE_OPTION_IMAGE_NAME = "Delete_16x16.gif";

    /**
     * The name of image used to add layout option override.
     */
    public static final String ADD_OPTION_IMAGE_NAME = "Add_16x16.gif";

    private LayoutOptionPropertiesUtils() {
        // private
    }

    /**
     * Create the help label showing the layout option description as its tooltip.
     * 
     * @param widgetFactory
     *            used to create the label.
     * @param parentComposite
     *            the composite that will contain the label.
     * @param topControl
     *            the control to be aligned with the help label.
     * @param leftControl
     *            the control that will be at the let of the label.
     * @param helpIcon
     *            the icon for the label.
     * @param layoutOption
     *            the layout option from which the help description is constructed.
     * @return the help label showing the layout option description as its tooltip.
     */
    public static CLabel createHelpLabel(TabbedPropertySheetWidgetFactory widgetFactory, Composite parentComposite, Control topControl, Control leftControl, Image helpIcon,
            LayoutOption layoutOption) {

        CLabel help = widgetFactory.createCLabel(parentComposite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(topControl, 0, SWT.CENTER);
        data.left = new FormAttachment(leftControl);
        help.setLayoutData(data);
        help.setImage(helpIcon);
        String description = LayoutOptionPropertiesUtils.getDescription((CustomLayoutConfiguration) layoutOption.eContainer(), layoutOption);
        if (description == null) {
            description = "";
        }
        help.setToolTipText(description);

        return help;
    }

    /**
     * Returns a button allowing to remove the given layout option when triggered.
     * 
     * @param valueComposite
     *            the composite containing the widget to edit the value of the layout option.
     * @param containerComposite
     *            the composite parent of the value composite and the composite we will create for the button.
     * @param viewpointPropertySheetPage
     *            the property sheet page that will show this created button.
     * @param widgetFactory
     *            used to create SWT widgets.
     * @param layoutOption
     *            the layout option from which a removal button will be created.
     * @return a button allowing to remove the given layout option when triggered.
     */
    public static Button createRemoveOptionButton(Control valueComposite, Composite containerComposite, ViewpointPropertySheetPage viewpointPropertySheetPage,
            TabbedPropertySheetWidgetFactory widgetFactory, LayoutOption layoutOption) {
        Image removeImage = ExtendedImageRegistry.INSTANCE.getImage(DiagramUIPlugin.getPlugin().getImage(DELETE_OPTION_IMAGE_NAME));
        Button removeOverrideButton = widgetFactory.createButton(containerComposite, "", SWT.PUSH);
        FormData data = new FormData();
        data.left = new FormAttachment(valueComposite, 5);
        data.top = new FormAttachment(valueComposite, 0, SWT.CENTER);
        removeOverrideButton.setLayoutData(data);
        removeOverrideButton.setImage(removeImage);
        removeOverrideButton.addSelectionListener(createRemoveOptionButtonListener(layoutOption, viewpointPropertySheetPage.getEditor().getEditingDomain(), viewpointPropertySheetPage));
        return removeOverrideButton;
    }

    /**
     * Create and returns a button listener that will removed a layout option override when triggered.
     * 
     * @param layoutOption
     *            the layout option override that will be removed.
     * @param editingDomain
     *            used to execute removal command.
     * @param tabbedPropertySheetPage
     *            the property sheet page from which a refresh will be done after removing the layout option.
     * @return a button listener that will removed a layout option override when triggered.
     */
    private static SelectionListener createRemoveOptionButtonListener(LayoutOption layoutOption, EditingDomain editingDomain, TabbedPropertySheetPage tabbedPropertySheetPage) {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, layoutOption.eContainer(), DescriptionPackage.eINSTANCE.getCustomLayoutConfiguration_LayoutOptions(),
                        Collections.singletonList(layoutOption)));
                Display.getCurrent().asyncExec(() -> tabbedPropertySheetPage.refresh());
            }
        };
    }

    /**
     * Return the description of the given layout option associated to the layout configuration.
     *
     * @param customLayoutConfiguration
     *            the layout configuration containing the layout option
     * @param layoutOption
     *            the layout option from which we want to retrieve corresponding label.
     * @return the description of the given layout option associated to the layout configuration. An empty string if no
     *         layout configuration corresponding is known.
     */
    public static String getDescription(CustomLayoutConfiguration customLayoutConfiguration, LayoutOption layoutOption) {
        CustomLayoutAlgorithm customLayoutAlgorithm = DiagramUIPlugin.getPlugin().getLayoutAlgorithms().get(customLayoutConfiguration.getId());
        if (customLayoutAlgorithm != null) {
            LayoutOption registerdedlayoutOption = customLayoutAlgorithm.getLayoutOptions().get(layoutOption.getId());
            if (registerdedlayoutOption.getDescription() != null) {
                return registerdedlayoutOption.getDescription();
            }
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * Return the label of the given layout option associated to the layout configuration.
     *
     * @param customLayoutConfiguration
     *            the layout configuration containing the layout option
     * @param layoutOption
     *            the layout option from which we want to retrieve corresponding label.
     * @return the label of the given layout option associated to the layout configuration. An empty string if no layout
     *         configuration corresponding is known.
     */
    public static String getLabel(CustomLayoutConfiguration customLayoutConfiguration, LayoutOption layoutOption) {
        CustomLayoutAlgorithm customLayoutAlgorithm = DiagramUIPlugin.getPlugin().getLayoutAlgorithms().get(customLayoutConfiguration.getId());
        if (customLayoutAlgorithm != null) {
            LayoutOption registerdedlayoutOption = customLayoutAlgorithm.getLayoutOptions().get(layoutOption.getId());
            if (registerdedlayoutOption.getDescription() != null) {
                return registerdedlayoutOption.getLabel();
            }
        }
        return ""; //$NON-NLS-1$
    }
}
