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
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.tab;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.util.ImageProvider;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerTabExtension;

/**
 * Describes a extension as contributed to the
 * {@link ModelExplorerTabRegistryListener#MODEL_EXPLORER_TAB_EXTENSION_POINT}
 * extension point.
 * 
 * @author mporhel
 * 
 */
public class ModelExplorerTabDescriptor {

    /**
     * Name of the attribute corresponding to the contributed class's path.
     */
    public static final String MODEL_EXPLORER_TAB_CLASS_NAME = "class"; //$NON-NLS-1$

    /**
     * Name of the attribute corresponding to the contributed icon's relative
     * path.
     */
    public static final String MODEL_EXPLORER_TAB_ICON = "icon"; //$NON-NLS-1$

    /**
     * Name of the attribute corresponding to the contributed id.
     */
    public static final String MODEL_EXPLORER_TAB_ID = "icon"; //$NON-NLS-1$

    /**
     * Configuration element of this descriptor .
     */
    private final IConfigurationElement element;

    /**
     * The path of the contributed class.
     */
    private String extensionClassName;

    /**
     * The {@link IModelExplorerTabExtension} described by this descriptor.
     */
    private IModelExplorerTabExtension extension;

    private ImageDescriptor imageDescriptor;

    private String id;

    /**
     * Instantiates a descriptor with all information.
     * 
     * @param configuration
     *            Configuration element from which to create this descriptor.
     */
    public ModelExplorerTabDescriptor(IConfigurationElement configuration) {
        element = configuration;
        extensionClassName = configuration.getAttribute(MODEL_EXPLORER_TAB_CLASS_NAME);
    }

    /**
     * Creates an instance of this descriptor's
     * {@link IModelExplorerTabExtension} .
     * 
     * @return A new instance of this descriptor's
     *         {@link IModelExplorerTabExtension}.
     */
    public IModelExplorerTabExtension getTabExtension() {
        if (extension == null) {
            try {
                extension = (IModelExplorerTabExtension) element.createExecutableExtension(MODEL_EXPLORER_TAB_CLASS_NAME);
            } catch (CoreException e) {
                SiriusTransPlugin.INSTANCE.error(e.getMessage(), e);
            }
        }
        return extension;
    }

    /**
     * Creates an {@link ImageDescriptor} for the contributed icon.
     * 
     * @return the icon's image descriptor.
     */
    public ImageDescriptor getImageDescriptor() {
        if (imageDescriptor == null) {
            String iconRelPath = element.getAttribute(MODEL_EXPLORER_TAB_ICON);
            if (iconRelPath != null) {
                imageDescriptor = ImageProvider.getImageDescriptor(element.getNamespaceIdentifier() + IPath.SEPARATOR + iconRelPath);
            } else {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
        }
        return imageDescriptor;
    }

    /**
     * Return the id of the current tab extension.
     * 
     * @return the id of the current tab extension.
     */
    public String getId() {
        if (id == null) {
            id = element.getAttribute(MODEL_EXPLORER_TAB_ID);
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
