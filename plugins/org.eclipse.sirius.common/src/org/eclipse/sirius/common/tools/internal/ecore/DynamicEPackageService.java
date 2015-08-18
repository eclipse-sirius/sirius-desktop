/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.ecore;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.RegistryReader.PluginClassDescriptor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * A class to fork EMF for dynamic registry.
 * 
 * @author cbrun
 */
public class DynamicEPackageService {

    // CHECKSTYLE:OFF
    static class EPackageDescriptor extends PluginClassDescriptor implements EPackage.Descriptor {
        static class Dynamic extends EPackageDescriptor {
            protected static ResourceSet resourceSet = new ResourceSetImpl() {
                protected Set<URI> uris = new HashSet<URI>();

                @Override
                protected Resource delegatedGetResource(URI uri, boolean loadOnDemand) {
                    try {
                        return uris.add(uri) ? super.delegatedGetResource(uri, loadOnDemand) : null;
                    } finally {
                        uris.remove(uri);
                    }
                }
            };

            public Dynamic(IConfigurationElement element, String attributeName) {
                super(element, attributeName);
            }

            @Override
            public EPackage getEPackage() {
                // First try to see if this class has an eInstance
                //
                try {
                    String location = element.getAttribute(attributeName);
                    if (location != null) {
                        URI locationURI = URI.createURI(location);
                        if (locationURI.isRelative()) {
                            locationURI = URI.createPlatformPluginURI(element.getDeclaringExtension().getContributor().getName() + "/" + location, true); //$NON-NLS-1$
                        }
                        if (!locationURI.hasFragment()) {
                            locationURI = locationURI.appendFragment("/"); //$NON-NLS-1$
                        }
                        return (EPackage) resourceSet.getEObject(locationURI, true);
                    } else {
                        throw new RuntimeException("No location attribute was specified.");
                    }
                } catch (Exception e) {
                    throw new WrappedException(e);
                }
            }
        }

        public EPackageDescriptor(IConfigurationElement element, String attributeName) {
            super(element, attributeName);
        }

        public EPackage getEPackage() {
            // First try to see if this class has an eInstance
            //
            try {
                Class<?> javaClass = Platform.getBundle(element.getDeclaringExtension().getContributor().getName()).loadClass(element.getAttribute(attributeName));
                Field field = javaClass.getField("eINSTANCE"); //$NON-NLS-1$
                Object result = field.get(null);
                return (EPackage) result;
            } catch (ClassNotFoundException e) {
                throw new WrappedException(e);
            } catch (IllegalAccessException e) {
                throw new WrappedException(e);
            } catch (NoSuchFieldException e) {
                throw new WrappedException(e);
            }
        }

        public EFactory getEFactory() {
            return null;
        }
    }
}
