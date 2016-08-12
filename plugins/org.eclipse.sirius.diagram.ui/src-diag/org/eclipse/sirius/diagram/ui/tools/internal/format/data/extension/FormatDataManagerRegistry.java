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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.format.IFormatDataManagerProvider;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManagerForSemanticElementsFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Registry containing all format data manager providers that have been parsed
 * from the
 * {@link FormatDataManagerRegistryListener#FORMAT_DATA_MANAGER_PROVIDER_EXTENSION_POINT}
 * extension point.
 * 
 * @author mporhel
 * 
 */
public final class FormatDataManagerRegistry {
    /**
     * The registered {@link FormatDataManagerDescriptor}s.
     */
    private static final Map<FormatDataManagerDescriptor, SiriusFormatDataManager> EXTENSIONS = Maps.newLinkedHashMap();

    /**
     * Utility classes don't need a default constructor.
     */
    private FormatDataManagerRegistry() {

    }

    /**
     * Adds an extension to the registry, with the given behavior.
     * 
     * @param extension
     *            The extension that is to be added to the registry
     */
    public static void addExtension(FormatDataManagerDescriptor extension) {
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
    public static Collection<FormatDataManagerDescriptor> getRegisteredExtensions() {
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
        for (FormatDataManagerDescriptor extension : getRegisteredExtensions()) {
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
     * @return the format data manager descriptor with the requested id if it
     *         exists.
     */
    public static FormatDataManagerDescriptor getRegisteredExtension(String id) {
        for (FormatDataManagerDescriptor desc : EXTENSIONS.keySet()) {
            if (!StringUtil.isEmpty(desc.getId()) && desc.getId().equals(id)) {
                return desc;
            }
        }
        return null;
    }

    /**
     * Get the {@link SiriusFormatDataManager} found applicable for the given
     * {@link DDiagram}.
     * 
     * The default manager (based on semantic elements) will always be the last
     * returned manager.
     * 
     * @param diagram
     *            the diagram which needs a format data manager.
     * 
     * @return a list of {@link SiriusFormatDataManager} instances.
     */
    public static List<SiriusFormatDataManager> getSiriusFormatDataManagers(DDiagram diagram) {
        List<SiriusFormatDataManager> applicableManagers = Lists.newArrayList();
        for (FormatDataManagerDescriptor descriptor : getRegisteredExtensions()) {
            IFormatDataManagerProvider provider = descriptor.getFormatDataManagerProvider();
            if (provider != null && provider.provides(diagram)) {
                SiriusFormatDataManager formatDataManager = EXTENSIONS.get(descriptor);
                if (formatDataManager == null) {
                    formatDataManager = provider.getFormatDataManager();
                    EXTENSIONS.put(descriptor, formatDataManager);
                }
                applicableManagers.add(formatDataManager);
            }
        }
        applicableManagers.add(SiriusFormatDataManagerForSemanticElementsFactory.getInstance().getSiriusFormatDataManager());
        return applicableManagers;
    }

    /**
     * Get all known {@link SiriusFormatDataManager} .
     * 
     * The default manager (based on semantic elements) will always be the last
     * returned manager.
     * 
     * @return a list of {@link SiriusFormatDataManager} instances.
     */
    public static List<SiriusFormatDataManager> getAllSiriusFormatDataManagers() {
        List<SiriusFormatDataManager> applicableManagers = Lists.newArrayList();
        for (FormatDataManagerDescriptor descriptor : getRegisteredExtensions()) {
            IFormatDataManagerProvider provider = descriptor.getFormatDataManagerProvider();
            if (provider != null) {
                SiriusFormatDataManager formatDataManager = EXTENSIONS.get(descriptor);
                if (formatDataManager == null) {
                    formatDataManager = provider.getFormatDataManager();
                    EXTENSIONS.put(descriptor, formatDataManager);
                }
                applicableManagers.add(formatDataManager);
            }
        }
        applicableManagers.add(SiriusFormatDataManagerForSemanticElementsFactory.getInstance().getSiriusFormatDataManager());
        return applicableManagers;
    }
}
