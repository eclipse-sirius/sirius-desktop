/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.sections.description.containermappingimport;

// Start of user code imports

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.selection.EObjectSelectionWizard;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.ui.tools.api.properties.ContainerMappingImportSelectionWizardItemsBuilder;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractComboPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the importedMapping property of a ContainerMappingImport
 * object.
 */
public class ContainerMappingImportImportedMappingPropertySection extends AbstractComboPropertySection {
    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractComboPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "ImportedMapping"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractComboPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + "*:"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractComboPropertySection#getFeature()
     */
    protected EReference getFeature() {
        return DescriptionPackage.eINSTANCE.getContainerMappingImport_ImportedMapping();
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractComboPropertySection#getFeatureValue(int)
     */
    protected Object getFeatureValue(int index) {
        return getFeatureValueAt(index);
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractComboPropertySection#isEqual(int)
     */
    protected boolean isEqual(int index) {
        boolean isEqual = false;
        if (getFeatureValueAt(index) == null)
            isEqual = eObject.eGet(getFeature()) == null;
        else
            isEqual = getFeatureValueAt(index).equals(eObject.eGet(getFeature()));
        return isEqual;
    }

    /**
     * Returns the value at the specified index in the choice of values for the
     * feature.
     * 
     * @param index
     *            Index of the value.
     * @return the value at the specified index in the choice of values.
     */
    protected Object getFeatureValueAt(int index) {
        List<?> values = getChoiceOfValues();
        if (values.size() < index || values.size() == 0 || index == -1) {
            return null;
        }
        return values.get(index);
    }

    /**
     * Fetches the list of available values for the feature.
     * 
     * @return The list of available values for the feature.
     */
    protected List<?> getChoiceOfValues() {
        List<?> values = Collections.emptyList();
        List<IItemPropertyDescriptor> propertyDescriptors = getDescriptors();
        for (Iterator<IItemPropertyDescriptor> iterator = propertyDescriptors.iterator(); iterator.hasNext();) {
            IItemPropertyDescriptor propertyDescriptor = iterator.next();
            if (((EStructuralFeature) propertyDescriptor.getFeature(eObject)) == getFeature())
                values = new ArrayList<Object>(propertyDescriptor.getChoiceOfValues(eObject));
        }

        // Start of user code choice of values
        values.remove(eObject);
        // End of user code choice of values
        return values;
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        combo.setToolTipText("Imported container mapping");

        CLabel help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(combo, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText("Imported container mapping");
        nameLabel.setFont(SiriusEditor.getFontRegistry().get("required"));
        // Start of user code create controls
        data = new FormData();
        data.left = new FormAttachment(0, LABEL_WIDTH);
        data.right = new FormAttachment(95, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        combo.setLayoutData(data);

        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(combo, -ITabbedPropertyConstants.HSPACE - 20);
        data.top = new FormAttachment(combo, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);

        Button button = getWidgetFactory().createButton(composite, "...", SWT.PUSH);
        data = new FormData();
        data.left = new FormAttachment(95, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(combo, 0, SWT.CENTER);
        button.setLayoutData(data);

        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final ContainerMappingImportSelectionWizardItemsBuilder builder = new ContainerMappingImportSelectionWizardItemsBuilder((ContainerMapping) eObject, new EObjectQuery(eObject)
                        .getAvailableViewpointsInResourceSet());
                final TreeItemWrapper input = builder.buildMappingInput();

                final Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
                if (propertySheetPage != null) {
                    final AdapterFactory factory = propertySheetPage.getAdapterFactory();
                    final EObjectSelectionWizard wizard = new EObjectSelectionWizard(EObjectSelectionWizard.WIZARD_GENERIC_DIALOG_TITLE, "Please select a container mapping to import", null, input,
                            factory);
                    wizard.setMany(Boolean.FALSE);
                    final WizardDialog dlg = new WizardDialog(shell, wizard);
                    if (dlg.open() == Window.OK) {
                        final EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
                        editingDomain.getCommandStack().execute(
                                SetCommand.create(editingDomain, eObject, DescriptionPackage.eINSTANCE.getContainerMappingImport_ImportedMapping(), wizard.getSelectedEObject()));
                    }
                }

            }
        });
        // End of user code create controls
    }
    // Start of user code user operations
    // End of user code user operations
}
