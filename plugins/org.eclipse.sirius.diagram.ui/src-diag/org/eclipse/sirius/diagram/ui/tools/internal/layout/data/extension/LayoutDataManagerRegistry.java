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
package org.eclipse.sirius.diagram.ui.tools.internal.layout.data.extension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ILayoutDataManagerProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManagerForSemanticElementsFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Registry containing all layout data manager providers that have been parsed
 * from the
 * {@link LayoutDataManagerRegistryListener#LAYOUT_DATA_MANAGER_PROVIDER_EXTENSION_POINT}
 * extension point.
 * 
 * @author mporhel
 * 
 */
public final class LayoutDataManagerRegistry {
    /**
     * The registered {@link LayoutDataManagerDescriptor}s.
     */
    private static final Map<LayoutDataManagerDescriptor, SiriusLayoutDataManager> EXTENSIONS = Maps.newLinkedHashMap();

    /**
     * Utility classes don't need a default constructor.
     */
    private LayoutDataManagerRegistry() {

    }

    /**
     * Adds an extension to the registry, with the given behavior.
     * 
     * @param extension
     *            The extension that is to be added to the registry
     */
    public static void addExtension(LayoutDataManagerDescriptor extension) {
        EXTENSIONS.put(extension, null);
    }

    /**
     * Removes all extensions from the registry. This will be called at plugin
     * stopping.
     */
    public static void clearRegistry() {
        EXTENSIONS.clear();
    }

    /**
     * Returns a copy of the registered extensions list.
     * 
     * @return A copy of the registered extensions list.
     */
    public static Collection<LayoutDataManagerDescriptor> getRegisteredExtensions() {
        return Lists.newArrayList(EXTENSIONS.keySet());
    }

    /**
     * Removes a phantom from the registry.
     * 
     * @param extensionClassName
     *            Qualified class name of the sync element which corresponding
     *            phantom is to be removed from the registry.
     */
    public static void removeExtension(String extensionClassName) {
        for (LayoutDataManagerDescriptor extension : getRegisteredExtensions()) {
            if (extension.getExtensionClassName().equals(extensionClassName)) {
                EXTENSIONS.remove(extension);
            }
        }
    }

    /**
     * Get the extension with the given id.
     * 
     * @param id
     *            the requested id.
     * 
     * @return the layout data manager descriptor with the requested id if it
     *         exists.
     */
    public static LayoutDataManagerDescriptor getRegisteredExtension(String id) {
        for (LayoutDataManagerDescriptor desc : EXTENSIONS.keySet()) {
            if (!StringUtil.isEmpty(desc.getId()) && desc.getId().equals(id)) {
                return desc;
            }
        }
        return null;
    }

    /**
     * Get the {@link SiriusLayoutDataManager} found applicable for the given
     * {@link DDiagram}.
     * 
     * The default manager (based on semantic elements) will always be the last
     * returned manager.
     * 
     * @param diagram
     *            the diagram which needs a layout data ma,ager.
     * 
     * @return a list of {@link SiriusLayoutDataManager} instances.
     */
    public static List<SiriusLayoutDataManager> getSiriusLayoutDataManagers(DDiagram diagram) {
        List<SiriusLayoutDataManager> applicableManagers = Lists.newArrayList();
        for (LayoutDataManagerDescriptor descriptor : getRegisteredExtensions()) {
            ILayoutDataManagerProvider provider = descriptor.getLayoutDataManagerProvider();
            if (provider != null && provider.provides(diagram)) {
                SiriusLayoutDataManager layoutDataManager = EXTENSIONS.get(descriptor);
                if (layoutDataManager == null) {
                    layoutDataManager = provider.getLayoutDataManager();
                    EXTENSIONS.put(descriptor, layoutDataManager);
                }
                applicableManagers.add(layoutDataManager);
            }
        }
        applicableManagers.add(SiriusLayoutDataManagerForSemanticElementsFactory.getInstance().getSiriusLayoutDataManager());
        return applicableManagers;
    }

    /**
     * Get all known {@link SiriusLayoutDataManager} .
     * 
     * The default manager (based on semantic elements) will always be the last
     * returned manager.
     * 
     * @return a list of {@link SiriusLayoutDataManager} instances.
     */
    public static List<SiriusLayoutDataManager> getAllSiriusLayoutDataManagers() {
        List<SiriusLayoutDataManager> applicableManagers = Lists.newArrayList();
        for (LayoutDataManagerDescriptor descriptor : getRegisteredExtensions()) {
            ILayoutDataManagerProvider provider = descriptor.getLayoutDataManagerProvider();
            if (provider != null) {
                SiriusLayoutDataManager layoutDataManager = EXTENSIONS.get(descriptor);
                if (layoutDataManager == null) {
                    layoutDataManager = provider.getLayoutDataManager();
                    EXTENSIONS.put(descriptor, layoutDataManager);
                }
                applicableManagers.add(layoutDataManager);
            }
        }
        applicableManagers.add(SiriusLayoutDataManagerForSemanticElementsFactory.getInstance().getSiriusLayoutDataManager());
        return applicableManagers;
    }
}
