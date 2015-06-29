/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.common.ui.tools.api.dialog.FeatureEditorDialog;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.HierarchyLabelProvider;
import org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionsUIManager;
import org.eclipse.sirius.viewpoint.provider.ViewpointItemProviderAdapterFactory;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

/**
 * An abstract implementation of a section with a non-editable Text and a button
 * allowing to open a
 * {@link org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog
 * FeatureEditorDialog}.
 */
public abstract class AbstractEditorDialogWithListPropertySection extends AbstractEditorDialogPropertySection {

    /**
     * Creates the selection listener for the "..." button.
     * 
     * @return the listener.
     */
    protected SelectionListener createButtonListener() {
        return new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {

                FeatureEditorDialog dialog = new FeatureEditorDialog(composite.getShell(), getLabelProvider(), eObject, eObject.eClass(), getCurrentValue(), getDefaultLabelText(),
                        getChoiceOfValues(), false, getSortChoice(), getChoiceOfValues() != null);

                dialog.open();
                List result = dialog.getResult();

                // Dialog returns null reference if closed/cancelled.
                if (result != null)
                    handleFeatureModified(result);
            }
        };
    }

    /**
     * 
     * @return all the elements of the list type
     */
    protected abstract List getCurrentValue();

    /**
     * 
     * @return if the list is sorted or not
     */
    protected abstract boolean getSortChoice();

    /**
     * Returns the adapter factory used by this viewer.
     * 
     * @return The adapter factory used by this viewer.
     */
    private AdapterFactory getAdapterFactory() {
        List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        factories.add(DialectUIManager.INSTANCE.createAdapterFactory());
        factories.add(FeatureExtensionsUIManager.INSTANCE.createAdapterFactory());
        factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        factories.add(new ViewpointItemProviderAdapterFactory());
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new EcoreItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());
        return new ComposedAdapterFactory(factories);
    }

    /**
     * Returns the label provider
     * 
     * @return the label provider
     */
    protected ILabelProvider getLabelProvider() {
        AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(getAdapterFactory());
        // Start of user code getLabelProvider
        return new HierarchyLabelProvider(labelProvider);
        // End of user code getLabelProvider
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getFeature()
     */
    protected EReference getFeature() {
        return null;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getFeatureAsText()
     */
    protected String getFeatureAsText() {
        StringBuilder sb = new StringBuilder();

        if (getCurrentValue() != null) {
            List values = getCurrentValue();
            for (Iterator iterator = values.iterator(); iterator.hasNext();) {
                EObject eObj = (EObject) iterator.next();
                sb.append(getAdapterFactoryLabelProvider(eObj).getText(eObj));

                if (iterator.hasNext())
                    sb.append(", ");
            }
        }

        return sb.toString();
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#isEqual(java.util.List)
     */
    protected boolean isEqual(List newList) {
        return newList.equals(getCurrentValue());
    }
}
