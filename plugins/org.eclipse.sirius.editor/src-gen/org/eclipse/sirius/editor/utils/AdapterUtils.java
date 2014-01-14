/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.utils;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;

/**
 * Usefull methods for EMF adapter factories
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 */
public final class AdapterUtils {
    private static final String ADAPTER_FACTORY_EXTENSION_POINT = "org.eclipse.emf.edit.itemProviderAdapterFactories"; //$NON-NLS-1$

    private static Map<String, AdapterFactoryDescriptor> factories = new HashMap<String, AdapterFactoryDescriptor>();

    static {
        parseExtensionMetadata();
    }

    private AdapterUtils() {
    }

    /**
     * Return the nsURI adapter factory if existing
     * 
     * @param nsURI
     * @return the nsURI adapter factory if existing, null otherwise
     */
    public static AdapterFactory findAdapterFactory(String nsURI) {
        AdapterFactory adapterFactory = null;
        if (factories.containsKey(nsURI))
            adapterFactory = factories.get(nsURI).getAdapterInstance();
        return adapterFactory;
    }

    /**
     * Return the adapter factory for this eobject
     * 
     * @param eObj
     * @return specific adapter factory or null
     */
    public static AdapterFactory findAdapterFactory(EObject eObj) {
        String uri = eObj.eClass().getEPackage().getNsURI();
        return findAdapterFactory(uri);
    }

    private static void parseExtensionMetadata() {
        final IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(ADAPTER_FACTORY_EXTENSION_POINT).getExtensions();
        for (int i = 0; i < extensions.length; i++) {
            IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
            for (int j = 0; j < configElements.length; j++) {
                AdapterFactoryDescriptor desc = parseAdapterFactory(configElements[j]);
                factories.put(desc.getNsURI(), desc);
            }
        }

    }

    private static AdapterFactoryDescriptor parseAdapterFactory(IConfigurationElement configElements) {
        AdapterFactoryDescriptor desc = new AdapterFactoryDescriptor(configElements);
        return desc;
    }
}

final class AdapterFactoryDescriptor {
    String nsURI;

    String className;

    protected IConfigurationElement element;

    /**
     * Constructs a new adapter factory descriptor from an IConfigurationElement
     * 
     * @param configElements
     */
    public AdapterFactoryDescriptor(IConfigurationElement configElements) {
        element = configElements;
        this.nsURI = element.getAttribute("uri"); //$NON-NLS-1$
        this.className = element.getAttribute("class"); //$NON-NLS-1$
    }

    /**
     * 
     * @return the adapter class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * 
     * 
     * @return The adapter nsURI
     */
    public String getNsURI() {
        return nsURI;
    }

    AdapterFactory factory;

    /**
     * 
     * @return the corresponding adapter factory instance
     */
    public AdapterFactory getAdapterInstance() {
        if (factory == null) {
            try {
                factory = (AdapterFactory) element.createExecutableExtension("class"); //$NON-NLS-1$
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
        return factory;
    }
}
