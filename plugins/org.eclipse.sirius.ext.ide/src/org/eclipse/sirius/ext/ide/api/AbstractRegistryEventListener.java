/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo.
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
package org.eclipse.sirius.ext.ide.api;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;

/**
 * Utility superclass used to process extensions.
 *
 * @author sbegaudeau
 */
public abstract class AbstractRegistryEventListener implements IRegistryEventListener {
    /**
     * This enumeration will be used to distinguish the various states of an
     * incoming event.
     *
     * @author sbegaudeau
     */
    protected enum Action {
        /**
         * An extension is being added.
         */
        ADD,

        /**
         * An extension is being removed.
         */
        REMOVE
    }

    /**
     * The namespace of the extension point.
     */
    private final String namespace;

    /**
     * The identifier of the extension point.
     */
    private final String extensionPointID;

    /**
     * The constructor.
     *
     * @param namespace
     *            The namespace of the extension point
     * @param extensionPointID
     *            The identifier of the extension point
     */
    public AbstractRegistryEventListener(String namespace, String extensionPointID) {
        this.namespace = namespace;
        this.extensionPointID = extensionPointID;
    }

    /**
     * Reads the extension registry for the addition of new extensions.
     *
     * @param extensionRegistry
     *            The extension registry
     */
    public void readRegistry(IExtensionRegistry extensionRegistry) {
        IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(this.namespace, this.extensionPointID);
        if (extensionPoint != null) {
            IConfigurationElement[] configurationElements = extensionPoint.getConfigurationElements();
            for (IConfigurationElement configurationElement : configurationElements) {
                this.processConfigurationElement(configurationElement, Action.ADD);
            }
        }
    }

    /**
     * Processes the configuration elements recursively.
     *
     * @param configurationElement
     *            The configuration element
     * @param action
     *            Indicates whether we have an addition or a removal of the
     *            configuration element
     */
    private void processConfigurationElement(IConfigurationElement configurationElement, Action action) {
        boolean isValid = this.readConfigurationElement(configurationElement, action);
        if (isValid) {
            IConfigurationElement[] children = configurationElement.getChildren();
            for (IConfigurationElement childConfigurationElement : children) {
                this.processConfigurationElement(childConfigurationElement, action);
            }
        }
    }

    /**
     * Reads the given configuration element.
     *
     * @param configurationElement
     *            The configuration element.
     * @param action
     *            Indicates whether we have an addition or a removal of the
     *            configuration element
     * @return <code>true</code> if the configuration element has been read
     *         properly
     */
    private boolean readConfigurationElement(IConfigurationElement configurationElement, Action action) {
        boolean isValid = false;
        if (this.validateConfigurationElement(configurationElement)) {
            if (Action.ADD == action) {
                isValid = this.processAddition(configurationElement);
            } else if (Action.REMOVE == action) {
                isValid = this.processRemoval(configurationElement);
            }
        }
        return isValid;
    }

    /**
     * Validates the given configuration element.
     *
     * @param configurationElement
     *            The configuration element
     * @return <code>true</code> if the configuration element is valid,
     *         <code>false</code> otherwise
     */
    protected abstract boolean validateConfigurationElement(IConfigurationElement configurationElement);

    /**
     * Processes the addition of the given configuration element.
     *
     * @param configurationElement
     *            The configuration element
     * @return <code>true</code> if the configuration element has been properly
     *         added, <code>false</code> otherwise
     */
    protected abstract boolean processAddition(IConfigurationElement configurationElement);

    /**
     * Processes the removal of the given configuration element.
     *
     * @param configurationElement
     *            The configuration element
     * @return <code>true</code> if the configuration element has been properly
     *         removed, <code>false</code> otherwise
     */
    protected abstract boolean processRemoval(IConfigurationElement configurationElement);

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtension[])
     */
    @Override
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (extension.getExtensionPointUniqueIdentifier().equals(this.extensionPointID)) {
                IConfigurationElement[] configurationElements = extension.getConfigurationElements();
                for (IConfigurationElement configurationElement : configurationElements) {
                    this.processConfigurationElement(configurationElement, Action.ADD);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtensionPoint[])
     */
    @Override
    public void added(IExtensionPoint[] extensionPoints) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtension[])
     */
    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (extension.getExtensionPointUniqueIdentifier().equals(this.extensionPointID)) {
                IConfigurationElement[] configurationElements = extension.getConfigurationElements();
                for (IConfigurationElement configurationElement : configurationElements) {
                    this.processConfigurationElement(configurationElement, Action.REMOVE);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtensionPoint[])
     */
    @Override
    public void removed(IExtensionPoint[] extensionPoints) {
        // do nothing
    }

}
