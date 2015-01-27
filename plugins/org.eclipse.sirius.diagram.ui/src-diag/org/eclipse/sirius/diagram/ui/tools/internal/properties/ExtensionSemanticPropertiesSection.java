/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ui.tools.api.properties.SiriusExtensiblePropertySource;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * Property section mixing both Extension and Semantic attributes.
 * 
 * @author ymortier
 * 
 */
public class ExtensionSemanticPropertiesSection extends AdvancedPropertySection implements IPropertySourceProvider {

    private List<Object> transformedSelection;

    private IWorkbenchPart part;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySourceProvider#getPropertySource(java.lang.Object)
     */
    public IPropertySource getPropertySource(final Object object) {

        IPropertySource propertySrc = null;
        final AdapterFactory adapterFactory = getAdapterFactory(object);
        if (object instanceof IPropertySource) {
            propertySrc = (IPropertySource) object;
        } else if (object instanceof DSemanticDecorator) {
            propertySrc = new ExtendedPropertySource((DSemanticDecorator) object, adapterFactory);
        } else {
            if (adapterFactory != null) {
                final IItemPropertySource ips = (IItemPropertySource) adapterFactory.adapt(object, IItemPropertySource.class);
                if (ips != null) {
                    propertySrc = new SiriusExtensiblePropertySource(object, ips);
                }
            }
            if (propertySrc == null && object instanceof IAdaptable) {
                propertySrc = (IPropertySource) ((IAdaptable) object).getAdapter(IPropertySource.class);
            }

        }
        return propertySrc;
    }

    /**
     * Get the adapter factory.
     * 
     * @param object
     *            the object
     * @return the retrieved adapter factory
     */
    protected AdapterFactory getAdapterFactory(final Object object) {
        AdapterFactory adapterFactory = null;
        if (object != null) {
            if (part instanceof DDiagramEditor) {
                adapterFactory = ((DDiagramEditor) part).getAdapterFactory();
            } else {
                adapterFactory = DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory();
            }
        }
        return adapterFactory;
    }

    @Override
    protected IPropertySourceProvider getPropertySourceProvider() {
        return this;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(final IWorkbenchPart workbenchPart, final ISelection selection) {
        this.part = workbenchPart;
        if (selection.isEmpty() || !(selection instanceof StructuredSelection)) {
            super.setInput(workbenchPart, selection);
            return;
        }
        final StructuredSelection structuredSelection = (StructuredSelection) selection;
        transformedSelection = new ArrayList<Object>(structuredSelection.size());
        final Iterator<?> it = structuredSelection.iterator();
        while (it.hasNext()) {
            final Object r = transformSelection(it.next());
            if (r != null) {
                transformedSelection.add(r);
            }
        }
        super.setInput(workbenchPart, new StructuredSelection(transformedSelection));
    }

    /**
     * Transform the selection to get the designer element from the GMF one.
     * 
     * @param selected
     *            the GMF element
     * @return the Designer element
     */
    protected Object transformSelection(final Object selected) {

        Object dElement = selected;

        if (selected instanceof EditPart) {
            final Object model = ((EditPart) selected).getModel();
            if (model instanceof View) {
                final EObject element = ((View) model).getElement();
                if (element instanceof DSemanticDecorator) {
                    dElement = element;
                }
            }

        } else if (selected instanceof View) {
            if (((View) selected).getElement() instanceof DSemanticDecorator) {
                dElement = ((View) selected).getElement();
            }
        } else if (selected instanceof IAdaptable) {
            final View view = (View) ((IAdaptable) selected).getAdapter(View.class);
            if (view != null) {
                if (view.getElement() instanceof DSemanticDecorator) {
                    dElement = view.getElement();
                }
            }
        }
        return dElement;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection#refresh()
     */
    @Override
    public void refresh() {
        if (this.transformedSelection != null) {
            final Iterator<?> inputs = this.transformedSelection.iterator();
            boolean isValid = true;
            while (inputs.hasNext() && isValid) {
                final Object input = inputs.next();
                if (input instanceof DSemanticDecorator) {
                    Resource resource = ((DSemanticDecorator) input).eResource();
                    if (resource == null || resource.getResourceSet() == null) {
                        isValid = false;
                    }
                }
            }
            if (!isValid) {
                this.setInput(this.part, StructuredSelection.EMPTY);
            }
        }
        super.refresh();
    }

}
