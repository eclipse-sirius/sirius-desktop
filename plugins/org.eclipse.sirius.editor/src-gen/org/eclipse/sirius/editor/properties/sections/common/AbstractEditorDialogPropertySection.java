/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.common;

// Start of user code for imports

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sirius.common.ui.tools.api.dialog.FeatureEditorDialog;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.ui.business.api.dialect.HierarchyLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code for imports

/**
 * An abstract implementation of a section with a non-editable Text and a button
 * allowing to open a
 * {@link org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog
 * FeatureEditorDialog}.
 */
public abstract class AbstractEditorDialogPropertySection extends AbstractViewpointPropertySection {
    /** The text control for the section. */
    protected Text text;

    /** The button control for the section. */
    protected Button button;

    /** Label control of the section. */
    protected CLabel nameLabel;

    /** Main composite **/
    protected Composite composite;

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

        text = getWidgetFactory().createText(composite, "");
        text.setEditable(false);
        data = new FormData();
        data.left = new FormAttachment(0, LABEL_WIDTH);
        data.right = new FormAttachment(95, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        text.setLayoutData(data);

        button = getWidgetFactory().createButton(composite, "...", SWT.PUSH);
        data = new FormData();
        data.left = new FormAttachment(95, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(text, 0, SWT.CENTER);
        button.setLayoutData(data);

        nameLabel = getWidgetFactory().createCLabel(composite, getLabelText());
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(text, -ITabbedPropertyConstants.HSPACE - 20);
        data.top = new FormAttachment(text, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);

        button.addSelectionListener(createButtonListener());
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
     * Creates the selection listener for the "..." button.
     *
     * @return the listener.
     */
    protected SelectionListener createButtonListener() {
        return new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(eObject, getIItemPropertyDescriptor());
                List<?> currentValues = (List<?>) eObject.eGet(getFeature());
                List<?> choiceOfValues = getChoiceOfValues(currentValues);
                FeatureEditorDialog dialog = new FeatureEditorDialog(composite.getShell(), getLabelProvider(propertyDescriptor), eObject, getFeature().getEType(), currentValues,
                        propertyDescriptor.getDisplayName(), choiceOfValues, false, getIItemPropertyDescriptor().isSortChoices(eObject), choiceOfValues != null);

                dialog.open();
                List result = dialog.getResult();

                // Dialog returns null reference if closed/cancelled.
                if (result != null)
                    handleFeatureModified(result);
            }
        };
    }

    protected ILabelProvider getLabelProvider(PropertyDescriptor propertyDescriptor) {
        ILabelProvider labelProvider = propertyDescriptor.getLabelProvider();
        // Start of user code getLabelProvider
        if (getSelection() instanceof TreeSelection) {
            labelProvider = new HierarchyLabelProvider(labelProvider);
        }
        // End of user code getLabelProvider
        return labelProvider;
    }

    /**
     * Handle the modification event given the result of the section's
     * {@link org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog
     * FeatureEditorDialog}.
     */
    protected void handleFeatureModified(List result) {
        boolean equals = isEqual(result);

        if (!equals) {
            EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
            Object value = result;

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
     * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
     */
    public void refresh() {
        if (text != null)
            text.setText(getFeatureAsText());
    }

    /**
     * Get the {@link org.eclipse.emf.edit.provider.IItemPropertyDescriptor
     * IItemPropertyDescriptor} for this feature.
     * 
     * @return The {@link org.eclipse.emf.edit.provider.IItemPropertyDescriptor
     *         IItemPropertyDescriptor} for this feature.
     */
    protected IItemPropertyDescriptor getIItemPropertyDescriptor() {
        IItemPropertyDescriptor itemPropertyDescriptor = null;

        ItemProviderAdapter providerAdapter = null;
        for (Iterator iterator = eObject.eAdapters().iterator(); iterator.hasNext();) {
            Object adapter = iterator.next();
            if (adapter instanceof ItemProviderAdapter)
                providerAdapter = (ItemProviderAdapter) adapter;
        }

        if (providerAdapter != null) {
            List propertyDescriptors = providerAdapter.getPropertyDescriptors(eObject);

            for (Iterator iterator = propertyDescriptors.iterator(); iterator.hasNext();) {
                IItemPropertyDescriptor propertyDescriptor = (IItemPropertyDescriptor) iterator.next();
                if (((EStructuralFeature) propertyDescriptor.getFeature(eObject)) == getFeature())
                    itemPropertyDescriptor = propertyDescriptor;
            }
        }

        return itemPropertyDescriptor;
    }

    /**
     * Fetches the list of available values for the feature.
     * 
     * @return The list of available values for the feature.
     */
    protected List<?> getChoiceOfValues() {
        return getChoiceOfValues(Collections.emptyList());
    }

    /**
     * Fetches the list of available values for the feature.
     * 
     * @param currentValues
     *            the list of current values to remove from available choice of
     *            values.
     * 
     * @return The list of available values for the feature.
     */
    protected List<?> getChoiceOfValues(List<?> currentValues) {
        List<?> list = Collections.emptyList();
        Collection<?> choiceOfValues = getIItemPropertyDescriptor().getChoiceOfValues(eObject);
        choiceOfValues.removeAll(currentValues);
        if (choiceOfValues != null) {
            list = new ArrayList<Object>(choiceOfValues);
        }
        return list;
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
        button.setEnabled(false);
        text.setEnabled(false);
    }

    /**
     * {@inheritDoc}
     */
    protected void makeWrittable() {
        button.setEnabled(true);
        text.setEnabled(true);
    }
}
