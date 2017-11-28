/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.contribution.directeobjectreference;

// Start of user code imports

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.componentization.ViewpointResourceHandler;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.selection.EObjectSelectionWizard;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.DirectEObjectReference;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractComboPropertySection;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.google.common.collect.Iterables;

// End of user code imports

/**
 * A section for the value property of a DirectEObjectReference object.
 */
public class DirectEObjectReferenceValuePropertySection extends AbstractComboPropertySection {
    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "Value"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + "*:"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getFeature()
     */
    protected EReference getFeature() {
        return ContributionPackage.eINSTANCE.getDirectEObjectReference_Value();
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#getFeatureValue(int)
     */
    protected Object getFeatureValue(int index) {
        return getFeatureValueAt(index);
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractComboPropertySection#isEqual(int)
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
     * Returns the value at the specified index in the choice of values for the feature.
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
        if (Movida.isEnabled()) {
            List<EObject> elementsInScope = new ArrayList<>();
            ViewpointResourceHandler vrh = ((org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry) ViewpointRegistry.getInstance()).getSiriusResourceHandler();
            ResourceSet rs = eObject.eResource().getResourceSet();
            for (Resource res : rs.getResources()) {
                if (vrh.handles(res.getURI())) {
                    for (Viewpoint vp : vrh.collectViewpointDefinitions(res)) {
                        Iterables.addAll(elementsInScope, AllContents.of(vp, true));
                    }
                }
            }
            values = elementsInScope;
        }
        // End of user code choice of values
        return values;
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        nameLabel.setFont(SiriusEditor.getFontRegistry().get("required"));
        // Start of user code create controls
        /*
         * We want to add a "Select" button on the right of the text field, but the layout data
         */
        FormData data = new FormData();
        data.left = new FormAttachment(0, LABEL_WIDTH);
        data.right = new FormAttachment(90, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        combo.setLayoutData(data);
        // Force users to use the dialog box instead of directly editing the
        // text file
        combo.setEnabled(false);

        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(combo, -ITabbedPropertyConstants.HSPACE - 20);
        data.top = new FormAttachment(combo, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);

        Button selectionButton = getWidgetFactory().createButton(composite, "Select", SWT.PUSH);
        data = new FormData();
        data.left = new FormAttachment(90, ITabbedPropertyConstants.HSPACE);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(combo, 0, SWT.CENTER);
        selectionButton.setLayoutData(data);
        selectionButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (eObject instanceof DirectEObjectReference) {
                    List<EObject> roots = new ArrayList<>();
                    roots.addAll(new EObjectQuery(eObject).getAvailableViewpointsInResourceSet());
                    AdapterFactory af = SiriusEditPlugin.getPlugin().getItemProvidersAdapterFactory();
                    TreeItemWrapper wrapper = buildWrapper(roots);
                    EObjectSelectionWizard wizard = new EObjectSelectionWizard("Selection", "Please select an object to reference", null, wrapper, af);
                    wizard.setMany(Boolean.FALSE);
                    WizardDialog dlg = new WizardDialog(composite.getShell(), wizard);
                    if (dlg.open() == Window.OK) {
                        EObject value = wizard.getSelectedEObject();
                        EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
                        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, getFeature(), value));
                    }
                }
            }

            private TreeItemWrapper buildWrapper(List<EObject> roots) {
                TreeItemWrapper result = new TreeItemWrapper(null, null);
                for (EObject root : roots) {
                    addElementsAndChildren(result, root);
                }
                return result;
            }

            private void addElementsAndChildren(TreeItemWrapper parent, EObject element) {
                TreeItemWrapper elementWrapper = new TreeItemWrapper(element, parent);
                parent.getChildren().add(elementWrapper);
                for (EObject child : element.eContents()) {
                    addElementsAndChildren(elementWrapper, child);
                }
            }
        });
        // End of user code create controls
    }
    // Start of user code user operations

    // End of user code user operations
}
