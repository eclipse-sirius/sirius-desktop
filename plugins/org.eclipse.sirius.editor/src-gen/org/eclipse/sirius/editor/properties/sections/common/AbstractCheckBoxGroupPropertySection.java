/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * An abstract implementation of a section displaying check boxes.
 */
public abstract class AbstractCheckBoxGroupPropertySection extends AbstractViewpointPropertySection {
    /** The button controls for the section. */
    protected Button[] button;

    /** Label control of the section. */
    protected CLabel nameLabel;

    /** Main composite **/
    protected Composite composite;

    /**
     * <ul>
     * Set this boolean to select the widgets to display.
     * <li><code>True</code> for a {@link org.eclipse.swt.widgets.Group group}
     * displaying {@link org.eclipse.swt.widgets.Button button}s</li>
     * <li><code>False</code> to display a {@link org.eclipse.swt.custom.CLabel
     * label} followed by the {@link org.eclipse.swt.widgets.Button button}s in
     * a single row</li>
     * </ul>
     */
    protected boolean buttonGroup = true;

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        if (aTabbedPropertySheetPage instanceof ViewpointPropertySheetPage)
            super.createControls(parent, (ViewpointPropertySheetPage) aTabbedPropertySheetPage);
        else
            super.createControls(parent, aTabbedPropertySheetPage);
        composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;

        List values = getChoiceOfValues();
        button = new Button[values.size()];
        int index = 0;

        if (buttonGroup) {
            Group group = getWidgetFactory().createGroup(composite, getLabelText());
            group.setLayout(new RowLayout(SWT.HORIZONTAL));
            for (Iterator<?> iterator = values.iterator(); iterator.hasNext();) {
                String buttonText = iterator.next().toString();
                button[index] = getWidgetFactory().createButton(group, buttonText, SWT.CHECK);
                index++;
            }
            data = new FormData();
            data.left = new FormAttachment(0, LABEL_WIDTH);
            group.setLayoutData(data);
        } else {
            int buttonWidthPercentage = 100 / (values.size() + 1);

            for (Iterator<?> iterator = values.iterator(); iterator.hasNext();) {
                String buttonText = iterator.next().toString();

                button[index] = getWidgetFactory().createButton(composite, buttonText, SWT.CHECK);
                data = new FormData();

                if (index == 0) {
                    data.left = new FormAttachment(buttonWidthPercentage, 0);
                    data.right = new FormAttachment(2 * buttonWidthPercentage, -20);
                    data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
                } else {
                    data.left = new FormAttachment(button[index - 1], 0);
                    data.right = new FormAttachment((index + 2) * buttonWidthPercentage, -20);
                    data.top = new FormAttachment(button[index - 1], 0, SWT.CENTER);
                }

                button[index].setLayoutData(data);
                index++;
            }

            nameLabel = getWidgetFactory().createCLabel(composite, getLabelText());
            data = new FormData();
            data.left = new FormAttachment(0, 0);
            data.right = new FormAttachment(button[0], -ITabbedPropertyConstants.HSPACE);
            data.top = new FormAttachment(button[0], 0, SWT.CENTER);
            nameLabel.setLayoutData(data);
        }

        for (int i = 0; i < button.length; i++) {
            final int buttonIndex = i;
            button[buttonIndex].addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    handleSelectionChanged();
                }
            });
        }
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#aboutToBeShown()
     * @Override
     */
    public void aboutToBeShown() {
        super.aboutToBeShown();
        PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, "org.eclipse.sirius." + eObject.eClass().getName());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        nameLabel.setText(getLabelText());
    }

    /**
     * Handle the modification event.
     */
    public void handleSelectionChanged() {
        boolean equals = isEqual(getSelectedValues());

        if (!equals) {
            EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
            Object value = getSelectedValues();

            if (eObjectList.size() == 1) {
                // apply the property change to single selected object
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, getFeature(), value));
            } else {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (Iterator<EObject> i = eObjectList.iterator(); i.hasNext();) {
                    EObject nextObject = i.next();
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), value));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * Get the list of values corresponding to the currently checked buttons.
     * 
     * @return The list of values corresponding to the currently checked
     *         buttons.
     */
    protected List<?> getSelectedValues() {
        List<?> possibleValues = getChoiceOfValues();
        List<Object> selectedValues = new ArrayList<Object>();

        for (int i = 0; i < button.length; i++)
            if (button[i].getSelection())
                selectedValues.add(possibleValues.get(i));

        return selectedValues;
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
     */
    public void refresh() {
        String[] values = getFeatureAsText().split(", ");
        Arrays.sort(values);

        for (int i = 0; i < button.length; i++) {
            if (Arrays.binarySearch(values, button[i].getText()) >= 0)
                button[i].setSelection(true);
            else
                button[i].setSelection(false);
        }
    }

    /**
     * Determine if the new list of values is equal to the current property
     * setting.
     * 
     * @param newList
     *            The new list of values for the property.
     * @return <code>True</code> if the new list of values is equal to the
     *         current property setting, <code>False</code> otherwise.
     */
    protected abstract boolean isEqual(List<?> newList);

    /**
     * Fetches the list of available values for the feature.
     * 
     * @return The list of available values for the feature.
     */
    protected abstract List<?> getChoiceOfValues();

    /**
     * Get the feature representing the data of this section.
     * 
     * @return The feature for the section.
     */
    protected abstract EStructuralFeature getFeature();

    protected String getDefaultFeatureAsText() {
        String value = new String();
        if (eObject.eGet(getFeature()) != null) {
            value = eObject.eGet(getFeature()).toString();
        }
        return value;
    }

    /**
     * Get the value of the feature as text for the text field of the section.
     * 
     * @return The value of the feature as text.
     */
    protected String getFeatureAsText() {
        final EStructuralFeature eFeature = getFeature();
        final IItemPropertyDescriptor propertyDescriptor = getPropertyDescriptor(eFeature);
        if (propertyDescriptor != null)
            return propertyDescriptor.getLabelProvider(eObject).getText(eObject.eGet(eFeature));
        return getDefaultFeatureAsText();
    }

    protected abstract String getDefaultLabelText();

    /**
     * Get the label for the text field of the section.
     * 
     * @return The label for the text field.
     */
    protected String getLabelText() {
        if (eObject != null) {
            final EStructuralFeature eFeature = getFeature();
            final IItemPropertyDescriptor propertyDescriptor = getPropertyDescriptor(eFeature);
            if (propertyDescriptor != null)
                return propertyDescriptor.getDisplayName(eObject);
        }
        return getDefaultLabelText();
    }

    /**
     * {@inheritDoc}
     */
    protected void makeReadonly() {
        for (int i = 0; i < button.length; i++)
            button[i].setEnabled(false);
    }

    /**
     * {@inheritDoc}
     */
    protected void makeWrittable() {
        for (int i = 0; i < button.length; i++)
            button[i].setEnabled(true);
    }
}
