/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.layoutoption;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.EnumOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.editor.Messages;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A property section allowing to open a dialog to select one or more layout options to override.
 */
public class AddOptionOverridePropertySection extends AbstractViewpointPropertySection {

    /**
     * The {@link Button} allowing to launch a dialog to select an option to override.
     */
    protected Button addOverrideButton;

    /**
     * The high level composite containing the widgets.
     */
    private Composite composite;

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        if (tabbedPropertySheetPage instanceof ViewpointPropertySheetPage)
            super.createControls(parent, (ViewpointPropertySheetPage) tabbedPropertySheetPage);
        else
            super.createControls(parent, tabbedPropertySheetPage);

        composite = getWidgetFactory().createFlatFormComposite(parent);

        Image addImage = ExtendedImageRegistry.INSTANCE.getImage(DiagramUIPlugin.getPlugin().getImage(LayoutOptionPropertiesUtils.ADD_OPTION_IMAGE_NAME));
        addOverrideButton = getWidgetFactory().createButton(composite, "", SWT.PUSH);
        addOverrideButton.setImage(addImage);
        addOverrideButton.setText(Messages.AddOptionOverridePropertySection_overrideButtonLabel);
        addOverrideButton.addSelectionListener(createButtonListener());

    }

    /**
     * Return a listener launching the dialog to select options to override. It also execute the command to add the
     * overridden options to the VSM model.
     * 
     * @return a listener launching the dialog to select options to override.
     */
    private SelectionListener createButtonListener() {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<LayoutOption> layoutOptionOverrides = new ArrayList<>();
                Map<String, CustomLayoutAlgorithm> layoutProviderRegistry = DiagramUIPlugin.getPlugin().getLayoutAlgorithms();
                CustomLayoutConfiguration layout = (CustomLayoutConfiguration) eObject;

                CustomLayoutAlgorithm genericLayoutProviderSupplier = layoutProviderRegistry.get(layout.getId());
                Map<String, LayoutOption> layoutOptions = genericLayoutProviderSupplier.getLayoutOptions();
                for (LayoutOption layoutOption : layoutOptions.values()) {
                    if (!layout.getLayoutOptions().stream().anyMatch(option -> option.getId().equals(layoutOption.getId()))) {
                        LayoutOption copy = EcoreUtil.copy(layoutOption);
                        if (copy instanceof EnumOption) {
                            ((EnumOption) copy).getChoices().clear();
                        }
                        copy.setDescription("");
                        layoutOptionOverrides.add(copy);
                    }
                }
                OptionOverrideEditorDialog dialog = new OptionOverrideEditorDialog(composite.getShell(), (CustomLayoutConfiguration) eObject, Messages.AddOptionOverridePropertySection_dialogTitle,
                        layoutOptionOverrides);
                dialog.open();

                Set<LayoutOption> overrriddenOptions = dialog.getResult();
                EditingDomain editingDomain = propertySheetPage.getEditor().getEditingDomain();
                editingDomain.getCommandStack().execute(new AddCommand(editingDomain, layout, DescriptionPackage.eINSTANCE.getCustomLayoutConfiguration_LayoutOptions(), overrriddenOptions));
                Display.getCurrent().asyncExec(() -> propertySheetPage.refresh());
            }
        };
    }

    /**
     * Return the label provider associated to the given descriptor.
     * 
     * @param propertyDescriptor
     *            the property descriptor from which we retrieve the label provider.
     * @return the label provider associated to the given descriptor.
     */
    private ILabelProvider getLabelProvider(PropertyDescriptor propertyDescriptor) {
        ILabelProvider labelProvider = propertyDescriptor.getLabelProvider();
        return labelProvider;
    }

    /**
     * Get the {@link org.eclipse.emf.edit.provider.IItemPropertyDescriptor IItemPropertyDescriptor} for the feature
     * {@link DescriptionPackage#getCustomLayoutConfiguration_LayoutOptions}.
     * 
     * @return The {@link org.eclipse.emf.edit.provider.IItemPropertyDescriptor IItemPropertyDescriptor} for the
     *         feature.
     */
    protected IItemPropertyDescriptor getIItemPropertyDescriptor() {
        IItemPropertyDescriptor itemPropertyDescriptor = null;
        ItemProviderAdapter providerAdapter = null;
        Iterator<?> iterator = eObject.eAdapters().iterator();
        while (iterator.hasNext()) {
            Object adapter = iterator.next();
            if (adapter instanceof ItemProviderAdapter)
                providerAdapter = (ItemProviderAdapter) adapter;
        }
        if (providerAdapter != null) {
            List<?> propertyDescriptors = providerAdapter.getPropertyDescriptors(eObject);
            iterator = propertyDescriptors.iterator();
            while (iterator.hasNext()) {
                IItemPropertyDescriptor propertyDescriptor = (IItemPropertyDescriptor) iterator.next();
                if (((EStructuralFeature) propertyDescriptor.getFeature(eObject)) == DescriptionPackage.eINSTANCE.getCustomLayoutConfiguration_LayoutOptions())
                    itemPropertyDescriptor = propertyDescriptor;
            }
        }

        return itemPropertyDescriptor;
    }

    @Override
    public void dispose() {
        addOverrideButton.dispose();
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
