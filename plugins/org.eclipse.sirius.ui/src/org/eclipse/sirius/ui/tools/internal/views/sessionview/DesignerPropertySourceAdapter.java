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
package org.eclipse.sirius.ui.tools.internal.views.sessionview;

import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.properties.AbstractCompositeEObjectPropertySource;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * A property source adapter.
 * 
 * @author ymortier
 */
public class DesignerPropertySourceAdapter implements IAdapterFactory {

    private static final Class<?>[] ADAPTERS_LIST = { IPropertySourceProvider.class, IPropertySource.class };

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
     */
    public Class<?>[] getAdapterList() {
        return ADAPTERS_LIST;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object,
     *      java.lang.Class)
     */
    public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
        Object adapter = null;
        if (adapterType == IPropertySource.class && adaptableObject instanceof EObject) {
            final ReflectiveItemProviderAdapterFactory reflectiveItemProviderAdapterFactory = new ReflectiveItemProviderAdapterFactory();
            final IItemPropertySource propertySource = (IItemPropertySource) reflectiveItemProviderAdapterFactory.adapt((EObject) adaptableObject, IItemPropertySource.class);
            adapter = new PropertySource(adaptableObject, propertySource);
        } else if (adapterType == IPropertySourceProvider.class) {
            adapter = new PropertySourceProvider();
        }
        return adapter;
    }

    /**
     * Property source provider.
     * 
     * @author ymortier
     */
    private static class PropertySourceProvider implements IPropertySourceProvider {

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.views.properties.IPropertySourceProvider#getPropertySource(java.lang.Object)
         */
        public IPropertySource getPropertySource(final Object object) {
            IPropertySource propSrc = null;
            final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
            adapterFactory.addAdapterFactory(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
            adapterFactory.addAdapterFactory(DialectUIManager.INSTANCE.createAdapterFactory());
            adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
            adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

            if (object instanceof IPropertySource) {
                propSrc = (IPropertySource) object;
            } else if (object instanceof DDiagramElement) {
                final DDiagramElement diagramElement = (DDiagramElement) object;
                final Iterator<EObject> iterElementsToDestroy = diagramElement.getSemanticElements().iterator();
                final AbstractCompositeEObjectPropertySource propertySource = new AbstractCompositeEObjectPropertySource() {
                    @Override
                    protected AdapterFactory getItemProvidersAdapterFactory() {
                        return adapterFactory;
                    }
                };
                final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(diagramElement);
                while (iterElementsToDestroy.hasNext()) {
                    final EObject semanticElement = iterElementsToDestroy.next();
                    if (!(accessor.isExtension(semanticElement))) {
                        final AdapterFactory af = adapterFactory;
                        if (af != null) {
                            final IItemPropertySource ips = (IItemPropertySource) af.adapt(semanticElement, IItemPropertySource.class);
                            if (ips != null) {
                                final IPropertySource targetPropertySource = new PropertySource(semanticElement, ips);
                                propertySource.addPropertySource(semanticElement, targetPropertySource);
                            }
                        }
                    }
                }
                propSrc = propertySource;
            } else if (object instanceof DSemanticDecorator) {
                final AbstractCompositeEObjectPropertySource propertySource = new AbstractCompositeEObjectPropertySource() {
                    @Override
                    protected AdapterFactory getItemProvidersAdapterFactory() {
                        return adapterFactory;
                    }
                };
                final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor((EObject) object);
                final EObject semanticElement = ((DSemanticDecorator) object).getTarget();
                if (!(accessor.isExtension(semanticElement))) {
                    final AdapterFactory af = adapterFactory;
                    final IItemPropertySource ips = (IItemPropertySource) af.adapt(semanticElement, IItemPropertySource.class);
                    if (ips != null) {
                        final IPropertySource targetPropertySource = new PropertySource(semanticElement, ips);
                        propertySource.addPropertySource(semanticElement, targetPropertySource);
                    }
                }
                propSrc = propertySource;
            } else {
                final AdapterFactory af = adapterFactory;
                final IItemPropertySource ips = (IItemPropertySource) af.adapt(object, IItemPropertySource.class);
                if (ips != null) {
                    return new PropertySource(object, ips);
                }
                if (object instanceof IAdaptable) {
                    propSrc = (IPropertySource) ((IAdaptable) object).getAdapter(IPropertySource.class);
                }
            }
            return propSrc;

        }
    }
}
