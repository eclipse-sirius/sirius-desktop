/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.properties.AbstractPropertySection;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.tools.api.properties.SiriusExtensiblePropertySource;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * Properties for the semantic model.
 * 
 * @author ymortier
 */
public class SemanticPropertySection extends AbstractPropertySection implements IPropertySourceProvider {

    private List<Object> transformedSelection;

    private IWorkbenchPart part;

    /**
     * Returns the property source of the specified objet.
     * 
     * @param object
     *            the object.
     * @return the property source of the specified objet.
     */
    @Override
    public IPropertySource getPropertySource(final Object object) {

        IPropertySource propSrc = null;

        if (object instanceof IPropertySource) {
            propSrc = (IPropertySource) object;
        } else if (object instanceof DDiagramElement) {
            final DDiagramElement diagramElement = (DDiagramElement) object;
            final Iterator<EObject> iterElementsToDestroy = diagramElement.getSemanticElements().iterator();
            final CompositeEObjectPropertySource propertySource = new CompositeEObjectPropertySource();
            final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(diagramElement);
            while (iterElementsToDestroy.hasNext()) {
                final EObject semanticElement = iterElementsToDestroy.next();
                if (!(accessor.isExtension(semanticElement))) {
                    final AdapterFactory af = getAdapterFactory(semanticElement);
                    if (af != null) {
                        final IItemPropertySource ips = (IItemPropertySource) af.adapt(semanticElement, IItemPropertySource.class);
                        if (ips != null) {
                            final IPropertySource targetPropertySource = new SiriusExtensiblePropertySource(semanticElement, ips);
                            propertySource.addPropertySource(semanticElement, targetPropertySource);
                        }
                    }
                }
            }
            propSrc = propertySource;
        } else if (object instanceof DSemanticDecorator) {
            final CompositeEObjectPropertySource propertySource = new CompositeEObjectPropertySource();
            final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor((EObject) object);
            final EObject semanticElement = ((DSemanticDecorator) object).getTarget();
            if (!(accessor.isExtension(semanticElement))) {
                final AdapterFactory af = getAdapterFactory(semanticElement);
                if (af != null) {
                    final IItemPropertySource ips = (IItemPropertySource) af.adapt(semanticElement, IItemPropertySource.class);
                    if (ips != null) {
                        final IPropertySource targetPropertySource = new SiriusExtensiblePropertySource(semanticElement, ips);
                        propertySource.addPropertySource(semanticElement, targetPropertySource);
                    }
                }
            }
            propSrc = propertySource;
        } else {
            final AdapterFactory af = getAdapterFactory(object);
            if (af != null) {
                final IItemPropertySource ips = (IItemPropertySource) af.adapt(object, IItemPropertySource.class);
                if (ips != null) {
                    return new SiriusExtensiblePropertySource(object, ips);
                }
            }
            if (object instanceof IAdaptable) {
                propSrc = ((IAdaptable) object).getAdapter(IPropertySource.class);
            }
        }
        return propSrc;

    }

    /**
     * Returns the provider.
     * 
     * @return the provider.
     */
    @Override
    protected IPropertySourceProvider getPropertySourceProvider() {
        return this;
    }

    /**
     * Transform selection to have {@link DSemanticDecorator} instead of {@link EditPart} or null if the semantic
     * element (target) not exists.
     * 
     * @param selection
     *            the currently selected object
     * @return the unwrapped object
     */
    protected Object transformSelection(final Object selection) {
        Object object = selection;
        if (object instanceof EditPart) {
            object = ((EditPart) object).getModel();
        } else if (object instanceof IAdaptable) {
            object = ((IAdaptable) object).getAdapter(View.class);
        }

        if (object instanceof View) {
            object = ((View) object).getElement();
        }

        if (object instanceof DSemanticDecorator) {
            EObject target = ((DSemanticDecorator) object).getTarget();
            if (target == null || target.eResource() == null) {
                object = null;
            }
        }
        return object;
    }

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

    @Override
    public Object getSelectedObject() {
        Object result = null;
        if (!transformedSelection.isEmpty()) {
            result = transformedSelection.get(0);
        }
        return result;
    }

    @Override
    public void dispose() {
        transformedSelection = null;
        part = null;
        super.dispose();
    }

}
