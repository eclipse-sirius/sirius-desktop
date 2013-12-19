/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.color;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.business.api.color.RGBValuesProvider;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * An abstract implementation of a section displaying a spinner for an integer
 * property.
 * 
 * @author mporhel
 */
public class RGBColorChooserPropertySection extends AbstractViewpointPropertySection {
    /** The color control for the section. */
    protected CLabel colorLabel;

    /** The button control for the section. */
    protected Button button;

    /** Label control of the section. */
    protected CLabel nameLabel;

    /** Main composite. * */
    protected Composite composite;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;
        data = new FormData();
        data.left = new FormAttachment(0, LABEL_WIDTH);
        data.right = new FormAttachment(95, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);

        colorLabel = getWidgetFactory().createCLabel(composite, "");
        colorLabel.setLayoutData(data);

        button = getWidgetFactory().createButton(composite, "...", SWT.PUSH);
        data = new FormData();
        data.left = new FormAttachment(95, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(colorLabel, 0, SWT.CENTER);
        button.setLayoutData(data);

        nameLabel = getWidgetFactory().createCLabel(composite, getLabelText());
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(colorLabel, -ITabbedPropertyConstants.HSPACE - 20);
        data.top = new FormAttachment(colorLabel, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);

        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final ColorDialog colorDialog = new ColorDialog(composite.getShell());
                colorDialog.setText("RGB Color Selection");
                colorDialog.setRGB(new RGB(255, 255, 255));
                final RGB newColor = colorDialog.open();
                // Dialog returns null reference if closed/cancelled.
                if (newColor != null) {
                    handleFeatureModified(newColor);
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractSiriusPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);

        eObject.eAdapters().add(new AdapterImpl() {
            @Override
            public void notifyChanged(final Notification msg) {
                if (eObject instanceof FixedColor) {
                    final FixedColor newColor = (FixedColor) eObject;
                    /*
                     * do not try to set the color if the widget is disposed
                     */
                    if (!colorLabel.isDisposed()) {
                        colorLabel.setBackground(VisualBindingManager.getDefault().getColorFromRGBValues(new RGBValuesProvider().getRGBValues(newColor)));
                    }
                }

                super.notifyChanged(msg);
            }
        });
    }

    /**
     * Handle the modification event given the result of the section's
     * {@link org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog
     * FeatureEditorDialog}.
     * 
     * @param newColor
     *            the new color
     */
    protected void handleFeatureModified(final RGB newColor) {
        final boolean equals = isEqual(newColor);
        if (!equals) {
            colorLabel.setBackground(VisualBindingManager.getDefault().getColorFromRGB(newColor));
            final EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
            if (eObjectList.size() == 1) {
                // apply the property change to single selected object
                final CompoundCommand compoundCommand = new CompoundCommand();
                compoundCommand.append(SetCommand.create(editingDomain, eObject, DescriptionPackage.eINSTANCE.getFixedColor_Blue(), newColor.blue));
                compoundCommand.append(SetCommand.create(editingDomain, eObject, DescriptionPackage.eINSTANCE.getFixedColor_Green(), newColor.green));
                compoundCommand.append(SetCommand.create(editingDomain, eObject, DescriptionPackage.eINSTANCE.getFixedColor_Red(), newColor.red));
                editingDomain.getCommandStack().execute(compoundCommand);
            } else {
                final CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (final EObject nextObject : eObjectList) {
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, DescriptionPackage.eINSTANCE.getFixedColor_Blue(), newColor.blue));
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, DescriptionPackage.eINSTANCE.getFixedColor_Green(), newColor.green));
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, DescriptionPackage.eINSTANCE.getFixedColor_Red(), newColor.red));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
     */
    @Override
    public void refresh() {
        if (colorLabel != null) {
            RGBValues color = new RGBValuesProvider().getRGBValues((FixedColor) eObject);
            colorLabel.setBackground(VisualBindingManager.getDefault().getColorFromRGBValues(color));
        }
    }

    /**
     * Get the label for the section.
     * 
     * @return The label for the section.
     */
    protected String getLabelText() {
        return "Selected RGB Color:"; //$NON-NLS-1$
    }

    /**
     * Determine if the new list of values is equal to the current property
     * setting.
     * 
     * @param newColor
     *            The new color of values for the property.
     * @return <code>True</code> if the new list of values is equal to the
     *         current property setting, <code>False</code> otherwise.
     */
    protected boolean isEqual(final RGB newColor) {
        boolean equal = true;
        if (newColor != null && eObject instanceof FixedColor) {
            final FixedColor rgbColor = (FixedColor) eObject;
            equal = rgbColor.getBlue() == newColor.blue && rgbColor.getRed() == newColor.red && rgbColor.getGreen() == newColor.green;
        } else {
            refresh();
        }
        return equal;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.editor.properties.sections.AbstractSpinnerPropertySection#getFeatureValue(int)
     */
    protected Object getFeatureValue(final String newText) {
        return toInteger(newText);
    }

    /**
     * Converts the given text to the integer it represents if applicable.
     * 
     * @return The integer the given text represents if applicable,
     *         <code>null</code> otherwise.
     */
    private Integer toInteger(final String text) {
        Integer integerValue = null;
        try {
            integerValue = new Integer(text);
        } catch (final NumberFormatException e) {
            // Not a Integer
        }
        return integerValue;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#dispose()
     */
    @Override
    public void dispose() {
        if (eObject != null) {
            eObject.eAdapters().clear();
        }
        super.dispose();
    }

    @Override
    protected void makeReadonly() {
        button.setEnabled(false);

    }

    @Override
    protected void makeWrittable() {
        button.setEnabled(true);
    }

    @Override
    protected EStructuralFeature getFeature() {
        return DescriptionPackage.eINSTANCE.getFixedColor_Blue();
    }
}
