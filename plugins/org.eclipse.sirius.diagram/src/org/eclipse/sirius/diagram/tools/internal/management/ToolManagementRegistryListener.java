/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.internal.management;

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.diagram.tools.api.management.ToolFilter;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;

/**
 * This listener will allow us to be aware of contribution changes against the toolFilter extension point.
 */
public class ToolManagementRegistryListener implements IRegistryEventListener {

    /** Name of the extension point to parse for extensions. */
    public static final String TOOL_MANAGEMENT_EXTENSION_POINT = DiagramPlugin.ID + ".toolManagement"; //$NON-NLS-1$

    /** Name of the extension point's "toolFilter" tag. */
    private static final String TOOLFILTER_TAG_EXTENSION = "toolFilter"; //$NON-NLS-1$

    /** Name of the toolFilter extension point's tag "class" attribute. */
    private static final String TOOLFILTER_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Register this listener and parse initial contributions.
     */
    public void init() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.addListener(this, TOOL_MANAGEMENT_EXTENSION_POINT);
        parseInitialContributions();
    }

    @Override
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            parseExtension(extension);
        }
    }

    @Override
    public void added(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
    }

    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            final IConfigurationElement[] configElements = extension.getConfigurationElements();
            for (IConfigurationElement elem : configElements) {
                if (TOOLFILTER_TAG_EXTENSION.equals(elem.getName())) {
                    final String extensionClassName = elem.getAttribute(TOOLFILTER_CLASS_ATTRIBUTE);
                    Collection<ToolFilter> providedToolFilters = ToolManagementRegistry.getInstance().getProvidedToolFilters();
                    for (ToolFilter toolFilter : providedToolFilters) {
                        if (extensionClassName.equals(toolFilter.getClass().getName())) {
                            ToolManagementRegistry.getInstance().removeToolFilter(toolFilter);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void removed(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
    }

    /**
     * Though this listener reacts to the extension point changes, there could have been contributions before it's been
     * registered. This will parse these initial contributions.
     */
    public void parseInitialContributions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(TOOL_MANAGEMENT_EXTENSION_POINT).getExtensions()) {
            parseExtension(extension);
        }
    }

    /**
     * Parses a single extension contribution.
     * 
     * @param extension
     *            Parses the given extension and adds its contribution to the registry.
     */
    private void parseExtension(IExtension extension) {
        final IConfigurationElement[] configElements = extension.getConfigurationElements();
        for (IConfigurationElement elem : configElements) {
            try {
                Object contribution = elem.createExecutableExtension(TOOLFILTER_CLASS_ATTRIBUTE); // $NON-NLS-1$
                if (contribution instanceof ToolFilter) {
                    ToolManagementRegistry.getInstance().addToolFilter((ToolFilter) contribution);
                }
            } catch (CoreException e) {
                DiagramPlugin.getDefault().getLog().log(new Status(Status.WARNING, DiagramPlugin.ID, e.getMessage(), e));
            }
        }
    }

    /**
     * Remove this listener and flush the associated registry.
     */
    public void dispose() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.removeListener(this);
        ToolManagementRegistry.getInstance().dispose();
    }

}
