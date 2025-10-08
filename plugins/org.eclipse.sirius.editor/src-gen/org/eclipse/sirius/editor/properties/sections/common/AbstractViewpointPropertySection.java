/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.common;

// Start of user code imports

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.editor.editorPlugin.IAdapterFactoryProvider;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.tools.internal.property.section.SiriusPropertySectionHelper;
import org.eclipse.sirius.viewpoint.description.RepresentationImportDescription;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

// End of user code imports

/**
 * An abstract implementation of a property tab section for the property sheet.
 */
public abstract class AbstractViewpointPropertySection extends AbstractPropertySection {
    /** The label width that will be used for section names. */
    public final static int LABEL_WIDTH = 232;

    /** The property sheet page for this section. */
    protected ViewpointPropertySheetPage propertySheetPage;

    /** Current selected object or first object in the selection when multiple objects are selected. */
    protected EObject eObject;

    /** The list of currently selected objects. */
    protected List<EObject> eObjectList;

    /** Plugin's {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider AdapterFactoryLabelProvider}. */
    protected AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    /** is true if the feature is required **/
    protected boolean isRequired = false;

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createControls(Composite parent, ViewpointPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        this.propertySheetPage = aTabbedPropertySheetPage;

        SiriusEditor editor = this.propertySheetPage.getEditor();
        IWorkbenchPart activePart = editor.getEditorSite().getPage().getActivePart();
        if (activePart instanceof ContentOutline && ((ContentOutline) activePart).getCurrentPage() == editor.getContentOutlinePage()) {
            updateSelection(editor.getContentOutlinePage().getSelection());
        } else {
            updateSelection(editor.getSelection());
        }
    }

    private boolean updateSelection(ISelection selection) {
        if (!(selection instanceof IStructuredSelection)) {
            if (selection instanceof EObject) {
                eObject = (EObject) selection;
                return true;
            }
        } else if (((IStructuredSelection) selection).getFirstElement() instanceof EObject) {
            {
                eObject = (EObject) ((IStructuredSelection) selection).getFirstElement();
                eObjectList = ((IStructuredSelection) selection).toList();
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        if (updateSelection(selection)) {
            updateReadOnlyStatus();
        }
    }

    /**
     * Update the read only status of this property section.
     */
    protected void updateReadOnlyStatus() {
        if (shouldBeReadOnly()) {
            makeReadonly();
        } else {
            makeWrittable();
        }
    }

    protected abstract void makeReadonly();

    protected abstract void makeWrittable();

    protected boolean shouldBeReadOnly() {
        boolean readonly = false;
        // Start of user code common readonly
        // if selected element is imported from a representation description
        RepresentationImportDescription representationImportDescription = SiriusPropertySectionHelper.getRepresentationImportDescriptionInSelection(getSelection());
        if (representationImportDescription != null && !SiriusPropertySectionHelper.isChildOfRepresentationDescription(eObject, representationImportDescription)) {
            readonly = true;
        }
        // End of user code common readonly
        return readonly;
    }

    /**
     * Get the feature for the text field of this section.
     *
     * @return The feature for the text.
     */
    protected abstract EStructuralFeature getFeature();

    /**
     * Fetches the {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider AdapterFactoryLabelProvider}
     * adapted to the given object.
     *
     * @return The plugin's {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     *         AdapterFactoryLabelProvider}}.
     */
    protected AdapterFactoryLabelProvider getAdapterFactoryLabelProvider(EObject eObj) {
        if (adapterFactoryLabelProvider == null) {
            if (getPart() instanceof IAdapterFactoryProvider) {
                adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(((IAdapterFactoryProvider) getPart()).getAdapterFactory());
            } else {
                return new AdapterFactoryLabelProvider(rescueAdapterFactory());
            }
        }
        return adapterFactoryLabelProvider;
    }

    protected AdapterFactory rescueAdapterFactory() {
        List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        return new ComposedAdapterFactory(factories);
    }

    /**
     * Fetches the plugin's {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     * AdapterFactoryLabelProvider}.
     *
     * @return The plugin's {@link org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
     *         AdapterFactoryLabelProvider}}.
     */
    protected AdapterFactoryLabelProvider getAdapterFactoryLabelProvider() {
        return getAdapterFactoryLabelProvider(null);
    }

    /**
     * Get all property descriptors associated with the current semantic object.
     *
     * @return the property descriptors list
     */
    protected List<IItemPropertyDescriptor> getDescriptors() {
        final AdapterFactory adapterFactory = propertySheetPage.getEditor().getAdapterFactory();
        ItemProviderAdapter providerAdapter = (ItemProviderAdapter) adapterFactory.adapt(eObject, IItemPropertySource.class);
        return providerAdapter.getPropertyDescriptors(eObject);
    }

    /**
     * Get the property descriptor associated with the current semantic object for the feature given as parameter.
     *
     * @param eFeature
     *            the feature for which to retrieve the descriptor
     * @return the descriptor, or <code>null</code> if it can not be found
     */
    protected IItemPropertyDescriptor getPropertyDescriptor(final EStructuralFeature eFeature) {
        for (IItemPropertyDescriptor propertyDescriptor : getDescriptors()) {
            if (((EStructuralFeature) propertyDescriptor.getFeature(eObject)).equals(eFeature)) {
                return propertyDescriptor;
            }
        }
        return null;
    }

    /**
     * Creates and return the help icon to show in our label.
     *
     * @return The help icon to show in our label.
     */
    protected Image getHelpIcon() {
        return SiriusEditor.getImageRegistry().get(SiriusEditorPlugin.ICONS_PREFERENCES_HELP);
    }
}
