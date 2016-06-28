/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.editor.properties.sections.properties.viewextensiondescription;

// Start of user code imports

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.editor.properties.sections.common.AbstractEditorDialogPropertySection;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the metamodels property of a ViewExtensionDescription object.
 */
public class ViewExtensionDescriptionMetamodelsPropertySection extends AbstractEditorDialogPropertySection {
    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#getDefaultLabelText()
     */
    @Override
    protected String getDefaultLabelText() {
        return "Metamodels"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + ":"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#getFeature()
     */
    @Override
    protected EReference getFeature() {
        return PropertiesPackage.eINSTANCE.getViewExtensionDescription_Metamodels();
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#getFeatureAsText()
     */
    @Override
    protected String getFeatureAsText() {
        String string = new String();

        if (eObject.eGet(getFeature()) != null) {
            List<?> values = (List<?>) eObject.eGet(getFeature());
            for (Iterator<?> iterator = values.iterator(); iterator.hasNext();) {
                EObject eObj = (EObject) iterator.next();
                string += getAdapterFactoryLabelProvider(eObj).getText(eObj);
                if (iterator.hasNext()) {
                    string += ", ";
                }
            }
        }

        return string;
    }

    /**
     * @see org.eclipse.sirius.properties.editor.properties.sections.AbstractEditorDialogPropertySection#isEqual(java.util.List)
     */
    @Override
    protected boolean isEqual(List<?> newList) {
        return newList.equals(eObject.eGet(getFeature()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        // Start of user code create controls

        // End of user code create controls
    }

    // Start of user code user operations

    // End of user code user operations
}
