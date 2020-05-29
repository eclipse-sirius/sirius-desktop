/**
 * Copyright (c) 2017, 2020 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Obeo - initial API and implementation
 */
package org.eclipse.sirius.common.tools.internal.ecore;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.sirius.common.tools.api.ecore.EPackageMetaData;
import org.eclipse.sirius.common.tools.api.util.StringUtil;

/**
 * Feeds an EPackageMetaDataRegistry from extension point configuration data.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 * @author pcdavid
 */
public class EPackageMetaDataRegistryReader {

    /** Extension-point ID. */
    private static final String EPACKAGE_META_DATA_EXTENSION_POINT = "org.eclipse.sirius.common.package_meta_data"; //$NON-NLS-1$

    private static final String EPACKAGE_META_DATA_ELEMENT = "ePackageMetaData"; //$NON-NLS-1$

    /** nsURI attribute. */
    private static final String NS_URI_ATTRIBUTE = "nsURI"; //$NON-NLS-1$

    /** Display name attribute. */
    private static final String DISPLAY_NAME_ATTRIBUTE = "displayName"; //$NON-NLS-1$

    /** Documentation attribute. */
    private static final String DOCUMENTATION_ATTRIBUTE = "documentation"; //$NON-NLS-1$

    /** DocumentRoot class names. */
    private static final String DOCUMENT_ROOT_ELEMENT = "documentRoot"; //$NON-NLS-1$
    
    private static final String DOCUMENT_ROOT_CLASS_NAME_ATTRIBUTE = "className"; //$NON-NLS-1$
    
    /** Preferred root element attribute. */
    private static final String SUGGESTED_ROOT_ELEMENT = "suggestedRoot"; //$NON-NLS-1$

    private static final String SUGGESTED_ROOT_CLASS_NAME_ATTRIBUTE = "className"; //$NON-NLS-1$

    private final IExtensionRegistry extensionRegistry;

    private final EPackageMetaDataRegistry metaDataRegistry;

    private IRegistryEventListener listener = new EPackageExtraDataListener();

    /**
     * Creates a new EPackageExtraDataRegistry reading from the specified registry.
     * 
     * @param extRegistry
     *            the registry to read configuration from.
     * @param mdRegistry
     *            the registry to register package metadata into.
     */
    public EPackageMetaDataRegistryReader(IExtensionRegistry extRegistry, EPackageMetaDataRegistry mdRegistry) {
        this.extensionRegistry = extRegistry;
        this.metaDataRegistry = mdRegistry;
    }

    /**
     * Initialize the registry and start listening for dynamic changes.
     */
    public synchronized void start() {
        for (IConfigurationElement cfg : extensionRegistry.getConfigurationElementsFor(EPACKAGE_META_DATA_EXTENSION_POINT)) {
            EPackageMetaData data = parse(cfg);
            if (data != null) {
                metaDataRegistry.register(data);
            }
        }
        this.extensionRegistry.addListener(listener, EPACKAGE_META_DATA_EXTENSION_POINT);
    }

    /**
     * Clears the registry and stop listening for dynamic changes.
     */
    public synchronized void stop() {
        this.extensionRegistry.removeListener(listener);
        metaDataRegistry.clear();
    }

    private EPackageMetaData parse(IConfigurationElement cfg) {
        if (EPACKAGE_META_DATA_ELEMENT.equals(cfg.getName())) {
            EPackageMetaData result = new EPackageMetaData(cfg.getAttribute(NS_URI_ATTRIBUTE));
            result.setDisplayName(cfg.getAttribute(DISPLAY_NAME_ATTRIBUTE));
            result.setDocumentation(cfg.getAttribute(DOCUMENTATION_ATTRIBUTE));
            for (IConfigurationElement child : cfg.getChildren(SUGGESTED_ROOT_ELEMENT)) {
                String className = child.getAttribute(SUGGESTED_ROOT_CLASS_NAME_ATTRIBUTE);
                if (!StringUtil.isEmpty(className)) {
                    result.getSuggestedRoots().add(className.trim());
                }
            }
            for (IConfigurationElement child : cfg.getChildren(DOCUMENT_ROOT_ELEMENT)) {
                String className = child.getAttribute(DOCUMENT_ROOT_CLASS_NAME_ATTRIBUTE);
                if (!StringUtil.isEmpty(className)) {
                    result.getDocumentRootClassNames().add(className.trim());
                }
            }
            return result;
        } else {
            return null;
        }
    }

    private final class EPackageExtraDataListener implements IRegistryEventListener {
        @Override
        public void added(IExtension[] extensions) {
            synchronized (EPackageMetaDataRegistryReader.this) {
                for (IExtension ext : extensions) {
                    for (IConfigurationElement cfg : ext.getConfigurationElements()) {
                        EPackageMetaData data = parse(cfg);
                        if (data != null) {
                            metaDataRegistry.register(data);
                        }
                    }
                }
            }
        }

        @Override
        public void removed(IExtension[] extensions) {
            synchronized (EPackageMetaDataRegistryReader.this) {
                for (IExtension ext : extensions) {
                    for (IConfigurationElement cfg : ext.getConfigurationElements()) {
                        if (EPACKAGE_META_DATA_ELEMENT.equals(cfg.getName())) {
                            String nsURI = cfg.getAttribute(NS_URI_ATTRIBUTE);
                            metaDataRegistry.unregister(nsURI);
                        }
                    }
                }
            }
        }

        @Override
        public void removed(IExtensionPoint[] extensionPoints) {
            // no need to listen to this event
        }

        @Override
        public void added(IExtensionPoint[] extensionPoints) {
            // no need to listen to this event
        }
    }

}
