/**
 * <copyright>
 *
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: DynamicPackageRegistryReader.java,v 1.1 2008/01/08 12:03:16 emerks Exp $
 */
package org.eclipse.sirius.common.tools.internal.ecore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.plugin.RegistryReader;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.internal.ecore.DynamicEPackageService.EPackageDescriptor;

//CHECKSTYLE:OFF
/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.EPackage.Registry#INSTANCE global} package
 * registry. Clients are not expected to use this class directly.
 */
public class DynamicPackageRegistryReader extends RegistryReader {

    public static final String DYNAMIC_PACKAGE_PPID = "dynamic_package"; //$NON-NLS-1$

    static final String TAG_RESOURCE = "resource"; //$NON-NLS-1$

    static final String ATT_URI = "uri"; //$NON-NLS-1$

    static final String ATT_LOCATION = "location"; //$NON-NLS-1$

    public static class PluginClassDescriptor {
        protected IConfigurationElement element;

        protected String attributeName;

        public PluginClassDescriptor(IConfigurationElement element, String attributeName) {
            this.element = element;
            this.attributeName = attributeName;
        }

        public Object createInstance() {
            try {
                return element.createExecutableExtension(attributeName);
            } catch (CoreException e) {
                throw new WrappedException(e);
            }
        }
    }

    public DynamicPackageRegistryReader() {
        super(Platform.getExtensionRegistry(), DslCommonPlugin.getDefault().getBundle().getSymbolicName(), DYNAMIC_PACKAGE_PPID);
    }

    @Override
    protected boolean readElement(IConfigurationElement element, boolean add) {
        if (element.getName().equals(TAG_RESOURCE)) {
            String packageURI = element.getAttribute(ATT_URI);
            if (packageURI == null) {
                logMissingAttribute(element, ATT_URI);
            } else if (element.getAttribute(ATT_LOCATION) == null) {
                logMissingAttribute(element, ATT_LOCATION);
            } else if (add) {
                Object previous = EPackage.Registry.INSTANCE.put(packageURI, new EPackageDescriptor.Dynamic(element, ATT_LOCATION));
                if (previous instanceof PluginClassDescriptor) {
                    PluginClassDescriptor descriptor = (PluginClassDescriptor) previous;
                    EcorePlugin.INSTANCE.log("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a package for '" + packageURI
                            + "'");
                }

                return true;
            } else {
                EPackage.Registry.INSTANCE.remove(packageURI);
                return true;
            }
        }

        return false;
    }
}
