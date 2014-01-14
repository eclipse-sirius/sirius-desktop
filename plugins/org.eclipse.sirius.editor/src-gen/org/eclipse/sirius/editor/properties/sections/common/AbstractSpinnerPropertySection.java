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

import java.util.Iterator;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
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
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * An abstract implementation of a section displaying a spinner for an integer
 * property.
 */
public abstract class AbstractSpinnerPropertySection extends AbstractViewpointPropertySection {
    /** Spinner control of the section. */
    protected Spinner spinner;

    /** String used to save the last change (Key, Mouse) */
    String lastChange = "";

    /** Label control of the section. */
    protected CLabel nameLabel;

    /** Minimum value of the field. */
    protected int minimumValue = 0;

    /** Maximum value of the field. */
    protected int maximumValue = Integer.MAX_VALUE;

    /** Main composite **/
    protected Composite composite;

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        if (tabbedPropertySheetPage instanceof ViewpointPropertySheetPage)
            super.createControls(parent, (ViewpointPropertySheetPage) tabbedPropertySheetPage);
        else
            super.createControls(parent, tabbedPropertySheetPage);
        composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;

        spinner = new Spinner(composite, SWT.BORDER);
        spinner.setMinimum(getMinimumValue());
        spinner.setMaximum(getMaximumValue());
        spinner.setPageIncrement(100);
        data = new FormData();
        data.left = new FormAttachment(0, LABEL_WIDTH);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        spinner.setLayoutData(data);

        nameLabel = getWidgetFactory().createCLabel(composite, getLabelText());
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(spinner, -ITabbedPropertyConstants.HSPACE - 20);
        data.top = new FormAttachment(spinner, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);

        spinner.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                lastChange = "Key";
                if (e.keyCode == SWT.Selection || e.keyCode == SWT.UP || e.keyCode == SWT.DOWN)
                    handleValueModified();
            }

            public void keyReleased(KeyEvent e) {
            };
        });

        spinner.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                if ("Mouse".equals(lastChange))
                    handleValueModified();
            }
        });

        spinner.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                handleValueModified();
            }
        });

        spinner.addListener(SWT.MouseDown, new Listener() {
            public void handleEvent(Event event) {
                lastChange = "Mouse";
            }
        });
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
     * Handle the spinner modified event.
     */
    protected void handleValueModified() {
        int newInt = spinner.getSelection();
        boolean equals = isEqual(String.valueOf(newInt));

        if (!equals) {
            EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
            Object value = getFeatureValue(String.valueOf(newInt));
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
     * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
     */
    public void refresh() {
        int value = Integer.parseInt(getFeatureAsText());
        spinner.setSelection(value);
    }

    public void setMinimumValue(int newValue) {
        minimumValue = newValue;
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public void setMaximumValue(int newValue) {
        maximumValue = newValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    /**
     * Determine if the provided integer value is an equal representation of the
     * current setting of the text property.
     * 
     * @param newText
     *            The new text displayed by the field.
     * @return <code>True</code> if the new text value is equal to the current
     *         property setting, <code>False</code> otherwise.
     */
    protected abstract boolean isEqual(String newText);

    /**
     * Get the feature representing the data of this section.
     * 
     * @return The feature representing the data of this section.
     */
    protected abstract EAttribute getFeature();

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

    /**
     * Get the new value of the feature for the spinner of the section.
     * 
     * @param newString
     *            The new value of the feature as displayed by the spinner.
     * @return The new value of the feature.
     */
    protected abstract Object getFeatureValue(String newText);

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
        spinner.setEnabled(false);
    }

    /**
     * {@inheritDoc}
     */
    protected void makeWrittable() {
        spinner.setEnabled(true);
    }
}
