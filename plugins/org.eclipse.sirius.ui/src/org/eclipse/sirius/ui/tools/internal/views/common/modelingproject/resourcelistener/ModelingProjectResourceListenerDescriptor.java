/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.resourcelistener;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.resourcelistener.IModelingProjectResourceListener;

/**
 * Describes an extension as contributed to the
 * {@link ModelingProjectResourceListenerRegistry#EXTENSION_POINT} extension
 * point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class ModelingProjectResourceListenerDescriptor {
    /**
     * Name of the attribute corresponding to the contributed class's path.
     */
    public static final String CONTRIBUTED_CLASS_NAME = "class"; //$NON-NLS-1$

    /**
     * Configuration element of this descriptor .
     */
    private final IConfigurationElement element;

    /**
     * The path of the contributed class.
     */
    private String extensionClassName;

    /**
     * The {@link IModelingProjectResourceListener} described by this
     * descriptor.
     */
    private IModelingProjectResourceListener extension;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param configuration
     *            Configuration element from which to create this descriptor.
     */
    public ModelingProjectResourceListenerDescriptor(IConfigurationElement configuration) {
        element = configuration;
        extensionClassName = configuration.getAttribute(CONTRIBUTED_CLASS_NAME);
    }

    /**
     * Creates an instance of this descriptor's
     * {@link IModelingProjectResourceListener} .
     * 
     * @return A new instance of this descriptor's
     *         {@link IModelingProjectResourceListener}.
     */
    public IModelingProjectResourceListener getModelingProjectResourceListener() {
        if (extension == null) {
            try {
                extension = (IModelingProjectResourceListener) element.createExecutableExtension(CONTRIBUTED_CLASS_NAME);
            } catch (CoreException e) {
                SiriusTransPlugin.INSTANCE.error(e.getMessage(), e);
            }
        }
        return extension;
    }

    /**
     * Returns the fully qualified name of the contributed class.
     * 
     * @return the fully qualified name of the contributed class
     */
    public String getExtensionClassName() {
        return extensionClassName;
    }

}
