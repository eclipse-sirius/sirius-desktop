/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.internal.util;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.util.ISaveDialogExtension;

/**
 * Describes a extension as contributed to the
 * {@link ISaveDialogExtensionRegistryListener#SAVE_DIALOG_EXTENSION_POINT}
 * extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class ISaveDialogExtensionDescriptor {

    /**
     * Name of the attribute corresponding to the contributed class's path.
     */
    public static final String SAVE_DIALOG_EXTENSION_CONTRIBUTED_CLASS_NAME = "class"; //$NON-NLS-1$

    /**
     * Configuration element of this descriptor .
     */
    private final IConfigurationElement element;

    /**
     * The path of the contributed class.
     */
    private String extensionClassName;

    /**
     * The {@link ISaveDialogExtension} described by this descriptor.
     */
    private ISaveDialogExtension extension;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param configuration
     *            Configuration element from which to create this descriptor.
     */
    public ISaveDialogExtensionDescriptor(IConfigurationElement configuration) {
        element = configuration;
        extensionClassName = configuration.getAttribute(SAVE_DIALOG_EXTENSION_CONTRIBUTED_CLASS_NAME);
    }

    /**
     * Creates an instance of this descriptor's {@link ISaveDialogExtension} .
     * 
     * @return A new instance of this descriptor's {@link ISaveDialogExtension}.
     */
    public ISaveDialogExtension getSaveDialogExtension() {
        if (extension == null) {
            try {
                extension = (ISaveDialogExtension) element.createExecutableExtension(SAVE_DIALOG_EXTENSION_CONTRIBUTED_CLASS_NAME);
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
