/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.format.data.extension;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.format.IFormatDataManagerProvider;

/**
 * Describes an extension as contributed to the
 * {@link FormatDataManagerRegistryListener#FORMAT_DATA_MANAGER_PROVIDER_EXTENSION_POINT}
 * extension point.
 * 
 * @author mporhel
 * 
 */
public class FormatDataManagerDescriptor {

    /**
     * Name of the attribute corresponding to the contributed class's path.
     */
    public static final String FORMAT_DATA_MANAGER_PROVIDER_CLASS_NAME = "class"; //$NON-NLS-1$

    /**
     * Name of the attribute corresponding to the contributed id.
     */
    public static final String FORMAT_DATA_MANAGER_PROVIDER_ID = "id"; //$NON-NLS-1$

    /**
     * Configuration element of this descriptor .
     */
    private final IConfigurationElement element;

    /**
     * The path of the contributed class.
     */
    private String extensionClassName;

    /**
     * The {@link IFormatDataManagerProvider} described by this descriptor.
     */
    private IFormatDataManagerProvider extension;

    private String id;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param configuration
     *            Configuration element from which to create this descriptor.
     */
    public FormatDataManagerDescriptor(IConfigurationElement configuration) {
        element = configuration;
        extensionClassName = configuration.getAttribute(FORMAT_DATA_MANAGER_PROVIDER_CLASS_NAME);
    }

    /**
     * Creates an instance of this descriptor's
     * {@link IFormatDataManagerProvider} .
     * 
     * @return A new instance of this descriptor's
     *         {@link IFormatDataManagerProvider}.
     */
    public IFormatDataManagerProvider getFormatDataManagerProvider() {
        if (extension == null) {
            try {
                extension = (IFormatDataManagerProvider) element.createExecutableExtension(FORMAT_DATA_MANAGER_PROVIDER_CLASS_NAME);
            } catch (CoreException e) {
                SiriusTransPlugin.INSTANCE.error(e.getMessage(), e);
            }
        }
        return extension;
    }

    /**
     * Return the id of the current tab extension.
     * 
     * @return the id of the current tab extension.
     */
    public String getId() {
        if (id == null) {
            id = element.getAttribute(FORMAT_DATA_MANAGER_PROVIDER_ID);
        }
        return id;
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
