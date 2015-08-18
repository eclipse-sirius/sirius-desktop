/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.resource;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.eclipse.sirius.common.tools.DslCommonPlugin;

/**
 * 
 * The factory to use to instantiate the resource set which are keep the model
 * artifacts.
 * 
 * @author cbrun
 * 
 */
public class ResourceSetFactory {

    /** The extension point. */
    private static final String FACTORY_EXTENSION_POINT = DslCommonPlugin.PLUGIN_ID + ".resourceSetFactory"; //$NON-NLS-1$

    private static final String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * One should use the static method to instanciate the class.
     * 
     */
    protected ResourceSetFactory() {

    }

    /**
     * Create a resourceset factory depending on the environment configuration.
     * The factory might have been overridden by a specific bundle.
     * 
     * @return the factory.
     */
    public static ResourceSetFactory createFactory() {
        ResourceSetFactory result = null;
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            final IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(FACTORY_EXTENSION_POINT).getExtensions();
            for (IExtension extension : extensions) {
                final IConfigurationElement[] configElements = extension.getConfigurationElements();
                for (IConfigurationElement configElement : configElements) {
                    try {
                        ResourceSetFactory contributed = (ResourceSetFactory) configElement.createExecutableExtension(CLASS_ATTRIBUTE);
                        if (result == null) {
                            result = contributed;
                        } else {
                            final IStatus status = new Status(IStatus.WARNING, DslCommonPlugin.PLUGIN_ID, "Several overrides are contributed for the resource factory,  "
                                    + configElement.getAttribute(CLASS_ATTRIBUTE) + " will be ignored");
                            DslCommonPlugin.getDefault().getLog().log(status);
                        }
                    } catch (final CoreException e) {
                        DslCommonPlugin.getDefault().error("Impossible to create the resource factory " + configElement.getAttribute(CLASS_ATTRIBUTE), e);
                    } catch (final ClassCastException e) {
                        DslCommonPlugin.getDefault().error("Impossible to create the resource factory " + configElement.getAttribute(CLASS_ATTRIBUTE), e);
                    }

                }
            }
        }
        if (result == null)
            result = new ResourceSetFactory();
        return result;
    }

    /**
     * Create a resourceSet based which might have special initializations based
     * on the given URI.
     * 
     * @param resourceURI
     *            the session model uri.
     * @return the newly created and initialized resourceSet.
     */
    public ResourceSet createResourceSet(URI resourceURI) {
        return new ResourceSetImpl();
    }

}
